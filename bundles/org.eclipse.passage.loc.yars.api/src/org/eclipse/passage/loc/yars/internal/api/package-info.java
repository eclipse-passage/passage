/* *****************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/

/**
 * <p>
 * In a sentence, this is all about
 * </p>
 * <ul>
 * <li>defining a <i>storage</i> for a particular type of data</li>
 * <li><i>querying</i> it for any sort (possibly another type) of info</li>
 * <li><i>export</i> the fetch to some output format</li>
 * </ul>
 * 
 * <h3>defining a storage</h3>
 * <p>
 * {@linkplain Storage} is quite a general interface for an original data roster
 * with the only contract - type of the core entries. We do not cover
 * complicated or somehow universal storages here.
 * </p>
 * <p>
 * Typical examples of a storage are
 * </p>
 * <ul>
 * <li>collection of some runtime data, like
 * 
 * <pre>
 *  class ShapesOnScreen implements Storage&lt;Shape>
 * </pre>
 * 
 * </li>
 * <li>list of related data-files
 * 
 * <pre>
 *  class Snapshots implements Storage&lt;java.io.File>
 * </pre>
 * 
 * </li>
 * <li>a single table or a view in a DB
 * 
 * <pre>
 *  class FifthGradeStudents implements Storage&lt;Student>
 * </pre>
 * 
 * </li>
 * </ul>
 * <p>
 * Main development work here is to hide storage structure details and provide
 * concise API for retrieving data of interest.
 * </p>
 *
 * <h3>querying and fetching</h3>
 * <p>
 * To make use of a {@code storage}, a developer equip it with a set of
 * {@linkplain Query}s, which are aware of this {@code storage} particular type
 * and, thus, precise data gathering API.
 * </p>
 * <p>
 * A {@linkplain Query}
 * </p>
 * <ul>
 * <li>identifies oneself,</li>
 * <li>describes a fetching idea and</li>
 * <li>supplies a way to construct an actual data {@code fetch}ers, which are
 * also {@code storage}-aware and can be furnished with set of context-related
 * parameters.</li>
 * </ul>
 * 
 * <p>
 * {@code Fetch} is a querie's working unit, who's responsibility is to retrieve
 * data from the storage. Such a unit should implement interface
 * {@linkplain FetchedData}
 *
 * <p>
 * Say, you design a {@code PageQuery} over a {@code FifthGradeStudents} to
 * query N students at a time from a huge table from the actual DB. It can look
 * like this
 * </p>
 * 
 * <pre>
 * class PageQuery implements Query&lt;FifthGradeStudents, Student, PaginationSettings> {
 * 	&#64;Override
 * 	public String id() {
 * 		return "PAGE";
 * 	}
 *
 * 	&#64;Override
 * 	public String description() {
 * 		return "Fetch paginated fifth-grade students from our school DB";
 * 	}
 *
 * 	&#64;Override
 * 	public FetchedData&lt;FifthGradeStudents, Student> fetch(FifthGradeStudents storage, PaginationSettings params) {
 * 		return new Fetch(params, storage);
 * 	}
 * }
 * </pre>
 * <p>
 * Here {@code Fetch} implements {@linkplain FetchedData}: in the method
 * {@linkplain FetchedData#get()} it interacts with the {@code storage} with the
 * given pagination {@code params} using the actually db interaction API that
 * our {@code FifthGradeStudents} storage supplies. Thus demanded
 * {@code Student} records are gained.
 * </p>
 * <p>
 * And here you expect a particular {@linkplain FetchParams} instance, which
 * keeps page settings like
 * </p>
 * 
 * <pre>
 * class PaginationSettings implements FetchParams {
 *		private final int pageNumber;
 *		private final int pageCapacity;
 *     <...>
 * }
 * </pre>
 * 
 * <p>
 * Thus, the client of your api will do something like this
 * </p>
 * 
 * <pre>
 * FifthGradeStudents base = ...
 * PageQuery query = new PageQuery(); // does not fetch anything by it's own
 * List<Student> pageNo3Content =
 *    query
 *      .fetch(base, new PaginationSettings(3, 50))  //gain an instance of FetchedData, which knows how fetch, but should not do this until demanded
 *      .get()   // and here the actual fetching should happen
 * </pre>
 * 
 * <h3>exporting</h3>
 * <p>
 * We encourage strong data encapsulation, so the exporting is implemented by
 * <i>printers</i> idea.
 * <ul>
 * <li>A class, who's instances are going to be exported, implements
 * {@linkplain ExportData} interface by {@code writing} itself into some
 * {@code media}.</li>
 * <li>As we design single-type focused infrastructure, here we supply
 * {@linkplain ListMedia} interface, which has to be implemented by an <i>output
 * format</i> aware service.</li>
 * </ul>
 * <p>
 * To illustrate the exporting, let's create a math academic record report for
 * our fifth-graders.
 * </p>
 * <ul>
 * <li>Define an analytic query:
 * 
 * <pre>
 * class MathProgress implements Query&lt;FifthGradeStudents, AcademicRecord, AnalysisParams>
 * </pre>
 * 
 * , and here {@code AnalysisParams} class will be responsible for a class title
 * ({@code math}).</li>
 * <li>The fetching ends up in instances of {@code AcademicRecord} class, which
 * we can design like this:
 * 
 * <pre>
 * class AcademicRecord implements ExportData&lt;AcademicRecord> {
 *     private final String name;  // student name, like Wednesday Addams
 *     private final String grade; // say, A+
 *     < ... constructor for name and grade >
 *     
 *     <O> void write(ListMedia<AcademicRecord, O> media){
 *         media.inner(name, "student");
 *         media.inner(grade, "grade"); 
 *     }
 * }
 * </pre>
 * 
 * </li>
 * <li>We have a single academic record been reported, but we also need the
 * whole report to be properly orchesterated. You can design such an
 * orchestrator yourself, or exploit the {@linkplain SingleSwoopExport} like this
 * 
 * <pre>
 *   FifthGradeStudents base = ...
 *   StringBuilder output = new StringBuilder();
 *   new Export<FifthGradeStudents, AcademicRecord>(
 *        new MathProgress().fetch(
 *           base, 
 *           new AnalysisParams("math"))
 *     .write(new Report(output));
 *	String report = output.toString();
 * </pre>
 * 
 * </li>
 * <li>And, finally, we should define an <i>output format</i>: define the
 * mechanics of the {@code Report} recording. It's a {@linkplain ListMedia},
 * which can write {@code AcademicRecord}s to a {@code File}. Let the report
 * have a title, a footer, and list all records as well.
 * 
 * <pre>
 * class Report extends ListMedia&lt;AcademicRecord> {
 * 		private final StringBuilder buffer;
 * 		private final File target;
 * 		private final Counter counter = new Counter(0); 
 *     
 * 		<... construct it with incoming buffer>
 *      
 *      public void start(){ // here comes the header
 *      	buffer.append("the report summarizes math academic records for 5th graders\n");
 *      }
 *      
 *      public void finish(){ // and here is the footer
 *      	buffer.append(counter + " 5th graders are presented\n");
 *      	saveBufferContentToTargetFile();
 *      }
 *      
 *      public void startNode(AcademicRecord node) {
 *      	buffer.append("5th grader math academic record: \n");
 *      }
 *      
 *      public void finishNode(AcademicRecord node) {
 *      	buffer.append("\n");
 *      }
 *      
 *      public void inner(String data, String name) {
 *      	buffer
 *      		.append("\t")
 *      		.append(name)
 *      		.append(" = ")
 *      		.append(data)
 *      		.append("\n");
 *      }      
 * }
 * </pre>
 * 
 * </li>
 * <p>
 * You are going to get a {@code report} like this:
 * </p>
 * 
 * <pre>
 * the report summarizes math academic records for 5th graders
 * 5th grader math academic record: 
 * 		student = Wednesday Addams
 * 		grade = A+
 * 5th grader math academic record: 
 * 		student = Pugsley Addams
 * 		grade = C 
 * 2 5th graders are presented
 * </pre>
 * </ul>
 * 
 * @since 0.1
 */
package org.eclipse.passage.loc.yars.internal.api;
