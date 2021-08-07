/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.publish.maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

import org.apache.maven.model.Developer;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.eclipse.jdt.annotation.NonNull;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public final class CompletePom {

	private final String year;
	private File base;
	private boolean dry;

	public CompletePom() {
		this.year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}

	@Option(name = "-dir", usage = "Sets the base directory to scan for POM files", required = true)
	public void setBase(@NonNull File dir) {
		this.base = dir;
	}

	@Option(name = "-dry", usage = "When set, files are not modified and result is dumped to sysout")
	public void setDry(boolean dry) {
		this.dry = dry;
	}

	public void run() throws IOException {
		System.out.println("Making POMs compliant for Maven Central."); //$NON-NLS-1$
		System.out.println("Processing *.pom files in " + base.getAbsolutePath() + "..."); //$NON-NLS-1$ //$NON-NLS-2$
		Files.find(base.toPath(), 20, (path, basicFileAttributes) -> path.toFile().getPath().endsWith(".pom")) //$NON-NLS-1$
				.forEach(path -> enhancePomFile(path));
		System.out.println("Done."); //$NON-NLS-1$
	}

	private void enhancePomFile(Path path) {
		boolean modified = false;
		Model model = loaded(path.toFile());

		if (model.getName() == null) {
			model.setName(model.getArtifactId());
			modified = true;
		}

		if (model.getUrl() == null) {
			model.setUrl("https://www.eclipse.org/passage"); //$NON-NLS-1$
			modified = true;
		}

		if (model.getLicenses().isEmpty()) {
			License license = new License();
			license.setName("The Eclipse Public License Version 2.0"); //$NON-NLS-1$
			license.setUrl("https://www.eclipse.org/legal/epl-v20.html"); //$NON-NLS-1$
			license.setDistribution("repo"); //$NON-NLS-1$
			model.addLicense(license);
			modified = true;
		}

		if (model.getScm() == null) {
			Scm scm = new Scm();
			scm.setUrl("https://github.com/eclipse-passage/passage.git"); //$NON-NLS-1$
			scm.setConnection("git@github.com:eclipse-passage/passage.git"); //$NON-NLS-1$
			model.setScm(scm);
			modified = true;
		}

		if (model.getDevelopers().isEmpty()) {
			Developer developer = new Developer();
			developer.setId("eclipse"); //$NON-NLS-1$
			developer.setName("Eclipse.org"); //$NON-NLS-1$
			developer.setEmail("info@eclipse.org"); //$NON-NLS-1$
			model.addDeveloper(developer);
			modified = true;
		}
		String group = model.getGroupId();
		String artifactID = model.getArtifactId();
		String version = model.getVersion();
		String description = model.getDescription();

		boolean exists = exists(group, artifactID, version);

		if (modified || dry) {
			if (modified) {
				System.out.println("--------------- Modified: " + path + " exists: " + exists); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				System.out.println("--------------- Unmodified: " + path + " exists " + exists); //$NON-NLS-1$ //$NON-NLS-2$
			}
			save(model, path.toFile());
			if (!exists) {
				String pomFileName = path.getFileName().toString();
				Path publish = path.getParent().resolve(pomFileName.substring(0, pomFileName.length() - 3) + "publish"); //$NON-NLS-1$
				String title = javadocWindowTitle(description, version);
				String footer = javadocFooter();
				savePublish(publish.toFile(), title, footer);
			}
			System.out.println();
		}
	}

	private String javadocWindowTitle(String string, String version) {
		return string + " " + version.substring(0, version.lastIndexOf('.')) + " API Specification"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private String javadocFooter() {
		String product = "Passage"; //$NON-NLS-1$
		return "<font size=\"-1\">Copyright &copy; 2018, " + year //$NON-NLS-1$
				+ "ArSysOp and others" //$NON-NLS-1$
				+ ". Licensed under the <a href=\"https://www.eclipse.org/legal/epl-2.0/\">Eclipse Public License v2.0</a>. All rights reserved.<br/><a href=\"https://bugs.eclipse.org/bugs/enter_bug.cgi?product=" //$NON-NLS-1$
				+ product + "\">Submit a bug or feature</a><br/></font>"; //$NON-NLS-1$
	}

	private Model loaded(File file) {
		try (FileReader reader = new FileReader(file)) {
			return new MavenXpp3Reader().read(reader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void save(Model model, File target) {
		MavenXpp3Writer writer = new MavenXpp3Writer();
		try {
			if (dry) {
				writer.write(System.out, model);
			} else {
				try (FileWriter fw = new FileWriter(target)) {
					writer.write(fw, model);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void savePublish(File target, String title, String footer) {
		try {
			if (dry) {
				System.out.println(title);
				System.out.println(footer);
			} else {
				try (FileOutputStream fos = new FileOutputStream(target)) {
					PrintStream out = new PrintStream(fos, false, "UTF-8"); //$NON-NLS-1$ use j1.4 constructor instead
																			// of j10
					out.println(title);
					out.println(footer);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean exists(String group, String artifact, String version) {
		String spec = String.format(
				"https://oss.sonatype.org/service/local/lucene/search?g=%s&a=%s&v=%s&repositoryId=releases", //$NON-NLS-1$
				group, artifact, version);
		try {
			URL url = new URL(spec);
			try (InputStream in = url.openStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.contains("<totalCount>")) { //$NON-NLS-1$
						return !line.contains("<totalCount>0</totalCount>"); //$NON-NLS-1$
					}
				}
			}
		} catch (IOException ex) {
			// well, it does not exist for whatever reason
		}
		return false;
	}

	public static void main(String[] args) {
		CompletePom instance = new CompletePom();
		CmdLineParser parser = new CmdLineParser(instance);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			return;
		}
		try {
			instance.run();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
