package org.eclipse.passage.loc.internal.licenses.ui;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.loc.internal.api.ZeroOneMany;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.internal.workbench.CreateDomainResource;
import org.eclipse.passage.loc.internal.workbench.SelectFromDialog;
import org.eclipse.passage.loc.jface.dialogs.Appearance;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.eclipse.swt.widgets.Shell;

public class SelectLicensePlan implements Supplier<Optional<LicensePlanDescriptor>> {

	private final IEclipseContext context;

	public SelectLicensePlan(IEclipseContext context) {
		this.context = context;
	}

	@Override
	public Optional<LicensePlanDescriptor> get() {
		LicenseRegistry registry = context.get(LicenseRegistry.class);
		Supplier<Iterable<LicensePlanDescriptor>> input = () -> StreamSupport
				.stream(registry.getLicensePlans().spliterator(), false).collect(Collectors.toList());
		ZeroOneMany<LicensePlanDescriptor> zeroOneMany = new ZeroOneMany<>(input);
		String title = LicensesUiMessages.LicensesUi_select_license_plan;
		Appearance appearance = new Appearance(title);
		SelectFromDialog<LicensePlanDescriptor> select = new SelectFromDialog<LicensePlanDescriptor>(
				() -> context.get(Shell.class), appearance);
		return zeroOneMany.choose(new CreateDomainResource<LicensePlanDescriptor>(context,
				Licenses.DOMAIN_NAME, LicensePlanDescriptor.class), select);
	}

}
