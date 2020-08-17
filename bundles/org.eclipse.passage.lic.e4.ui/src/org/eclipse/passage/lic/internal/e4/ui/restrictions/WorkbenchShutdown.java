package org.eclipse.passage.lic.internal.e4.ui.restrictions;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;

public final class WorkbenchShutdown implements Runnable {

	private final Supplier<Optional<IEclipseContext>> context;

	public WorkbenchShutdown() {
		this(new Treasury());
	}

	public WorkbenchShutdown(Supplier<Optional<IEclipseContext>> context) {
		this.context = context;
	}

	@Override
	public void run() {
		context.get().ifPresent(this::shutdown);
	}

	private void shutdown(IEclipseContext ctx) {
		Optional.ofNullable(ctx.get(IWorkbench.class))//
				.ifPresent(IWorkbench::close);
	}
}
