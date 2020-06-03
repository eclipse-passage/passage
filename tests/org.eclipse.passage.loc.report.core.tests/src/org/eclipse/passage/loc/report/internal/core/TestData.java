package org.eclipse.passage.loc.report.internal.core;

import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.Storage;

@SuppressWarnings("restriction")
public interface TestData<S extends Storage<?>> {

	Set<String> csv();

	S storage();

}
