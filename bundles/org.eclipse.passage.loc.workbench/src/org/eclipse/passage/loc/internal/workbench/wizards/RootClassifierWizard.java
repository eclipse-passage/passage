package org.eclipse.passage.loc.internal.workbench.wizards;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.jface.dialogs.LicensingResultDialogs;
import org.eclipse.passage.loc.internal.workbench.ClassifierMetadata;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;

/**
 * Creates new root licensing object. Can be asked for a reference to a created
 * instance.
 * 
 * @since 0.6
 *
 */
public final class RootClassifierWizard extends BaseClassifierWizard<RootClassifierWizardPage> {

	/**
	 * Creates a new wizard for root licensing object with given metadata,
	 * initializer and registry
	 * 
	 * @param metadata    describes EMF metadata for an object to be created, must
	 *                    not be <code>null</code>
	 * @param initializer describer initial values for an object to be created, must
	 *                    not be <code>null</code>
	 * @param registry    registry for an object to be created, must not be
	 *                    <code>null</code>
	 * 
	 * @see BaseClassifierWizard
	 * @see ClassifierMetadata
	 * @see ClassifierInitializer
	 * @see EditingDomainRegistry
	 * 
	 */
	public RootClassifierWizard(ClassifierMetadata metadata, ClassifierInitializer initializer,
			EditingDomainRegistry<?> registry) {
		super(metadata, initializer, registry);
	}

	@Override
	protected RootClassifierWizardPage createNewClassifierPage(ClassifierMetadata metadata,
			ClassifierInitializer initializer) {
		return new RootClassifierWizardPage(metadata, initializer, registry.getFileExtension());
	}

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, m -> store(newClassifierPage.path(), newClassifierPage.candidate()));
			return true;
		} catch (Exception exception) {
			process(exception);
			return false;
		}
	}

	protected void store(String path, EObject candidate) {
		URI fileURI = URI.createFileURI(path);
		Resource resource = resourceSet().createResource(fileURI);
		resource.getContents().add(candidate);
		LocWokbench.save(resource);
		registry.registerSource(fileURI.toFileString());
	}

	protected ResourceSet resourceSet() {
		if (registry instanceof IEditingDomainProvider) {
			IEditingDomainProvider edProvider = (IEditingDomainProvider) registry;
			return edProvider.getEditingDomain().getResourceSet();
		}
		return new ResourceSetImpl();
	}

	protected void process(Exception exception) {
		LicensingResultDialogs.openMessageDialog(getShell(), WorkbenchMessages.RootClassifierWizard_title_e_create, //
				LicensingResults.createError(WorkbenchMessages.RootClassifierWizard_message_e_create,
						getClass().getName(), exception));
	}
}
