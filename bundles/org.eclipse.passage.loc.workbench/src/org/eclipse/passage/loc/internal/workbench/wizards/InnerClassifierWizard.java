package org.eclipse.passage.loc.internal.workbench.wizards;

import java.util.Optional;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.ClassifierMetadata;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.workbench.LocWokbench;

/**
 * Creates new root licensing object. Can be asked for a reference to a created
 * instance.
 * 
 * @since 0.6
 *
 */
public final class InnerClassifierWizard<C> extends BaseClassifierWizard<InnerClassifierWizardPage<C>> {

	private final IEclipseContext context;
	private final SelectRequest<C> request;

	/**
	 * Creates a new wizard for root licensing object with given metadata,
	 * initializer and registry
	 * 
	 * @param metadata    describes EMF metadata for an object to be created, must
	 *                    not be <code>null</code>
	 * @param initializer describer initial values for an object to be created, must
	 *                    not be <code>null</code>
	 * @param registry
	 * @param registry    registry for an object to be created, must not be
	 *                    <code>null</code>
	 * 
	 * @see BaseClassifierWizard
	 * @see ClassifierMetadata
	 * @see ClassifierInitializer
	 * @see EditingDomainRegistry
	 * 
	 */
	public InnerClassifierWizard(ClassifierMetadata metadata, ClassifierInitializer initializer,
			EditingDomainRegistry<?> registry, SelectRequest<C> request, IEclipseContext context) {
		super(metadata, initializer, registry);
		this.request = request;
		this.context = context;
	}

	@Override
	protected InnerClassifierWizardPage<C> createNewClassifierPage() {
		return new InnerClassifierWizardPage<C>(metadata, initializer, request, context);
	}

	@Override
	protected void store() {
		store(newClassifierPage.container(), newClassifierPage.candidate());
	}

	protected void store(Optional<C> container, EObject candidate) {
		if (!container.isPresent()) {
			return;
		}
		EReference reference = candidate.eClass().getEAllReferences().stream()//
				.filter(r -> r.isContainer())//
				.findFirst()//
				.get();
		candidate.eSet(reference, container.get());
		Resource resource = candidate.eResource();
		Optional.ofNullable(resource).ifPresent(r -> LocWokbench.save(r));
	}

}
