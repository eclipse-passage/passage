package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.swt.widgets.Composite;

public interface LicenseFilesControl {

	void install(Composite parent, Consumer<List<Path>> onLicenses);

}
