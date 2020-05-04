/*******************************************************************************
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
package org.eclipse.passage.loc.yars.internal.api;

import java.util.List;

/**
 * <p>
 * Auxiliary exporter, based on a fetcher, which causes the actual fetch in a
 * lazy way and orcheterates the whole export of fetched data.
 * </p>
 * <p>
 * The unit is not designed for large amount of data as all the data is first
 * fully fetched and only then exported entry by entry.
 * </p>
 * 
 * <p>
 * Having
 * </p>
 * <ul>
 * <li>a source storage {@code Sto}</li>
 * <li>a query {@code Que} over the storage, who's fetches beget instances
 * of</li>
 * <li>an exportable entry class like
 * {@code class Exportable implements ExportedData<Exportable>}, which can
 * {@code write} itself into a {@code media}, and</li>
 * <li>an {@code output format} media, like {@code Json}, that knows how to
 * {@code write} each element of a {@code result list} to the format,</li>
 * </ul>
 * <p>
 * you can trigger the whole writing with something like
 * </p>
 * 
 * <pre>
 * new SingleSwoopExport<Sto, Exportable>(//
 * 		new Que().fetch(getSto(), createParams())//
 * ).write(new Json(file), new Progress.Inane());
 * </pre>
 * 
 * @param <S> type of storage
 * @param <T> type of data ready for export, sub type of {@linkplain ExportData}
 * 
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public final class SingleSwoopExport<S extends Storage<?>, T extends ExportData<T, DosHandleMedia<T>>>
		implements ExportData<T, DosHandleMedia<T>> {

	private final FetchedData<S, T> query;

	public SingleSwoopExport(FetchedData<S, T> query) {
		this.query = query;
	}

	@Override
	public void write(DosHandleMedia<T> media, Progress<T> progress) {
		media.start();
		writeData(media, progress);
		media.finish();
	}

	private void writeData(DosHandleMedia<T> media, Progress<T> progress) {
		List<T> fetch = query.get();
		progress.estimate(fetch.size());
		for (T data : fetch) {
			if (progress.cancelDemanded()) {
				break;
			}
			progress.reportNodeSrart(data);
			media.startNode(data);
			data.write(media, progress);
			media.finishNode(data);
			progress.reportNodeFinish(data);
		}
	}

}
