package org.eclipse.passage.loc.internal.workbench.wizards;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.workbench.LocWokbench;

/**
 * Creates new root licensing object. Can be asked for a reference to a created
 * instance.
 * 
 * @param <I> inner classifier to be created
 * @param <R> root classifier to store created if not present
 * 
 */
public final class InnerClassifierWizard<I, R> extends BaseClassifierWizard<InnerClassifierWizardPage<R>> {

	private final Class<I> clazz;
	private final MandatoryService context;
	private final SelectRequest<R> request;

	/**
	 * Creates a new wizard for root licensing object with given metadata,
	 * initializer and registry
	 * 
	 * @param metadata describes EMF metadata for an object to be created, must not
	 *                 be <code>null</code>
	 * @param registry
	 * @param registry registry for an object to be created, must not be
	 *                 <code>null</code>
	 * 
	 * @see BaseClassifierWizard
	 * @see ClassifierMetadata
	 * @see EditingDomainRegistry
	 * 
	 */
	public InnerClassifierWizard(Class<I> clazz, EntityMetadata metadata, EditingDomainRegistry<?> registry,
			SelectRequest<R> request, MandatoryService context) {
		super(metadata, registry);
		this.clazz = clazz;
		this.request = request;
		this.context = context;
	}

	@Override
	protected InnerClassifierWizardPage<R> createNewClassifierPage() {
		Optional<EntityMetadata> find = context.get(ComposableClassMetadata.class).find(clazz);
		if (find.isPresent()) {
			return new InnerClassifierWizardPage<R>(find.get(), request, context);
		}
		// FIXME: AF: provide nice error page
		throw new NoSuchElementException(clazz.getName());
	}

	@Override
	protected void store() {
		store(newClassifierPage.container(), newClassifierPage.candidate());
	}

	protected void store(Optional<R> container, EObject candidate) {
		if (!container.isPresent()) {
			return;
		}
		EReference reference = containerEReference(candidate.eClass()).get();
		candidate.eSet(reference, container.get());
		Resource resource = candidate.eResource();
		Optional.ofNullable(resource).ifPresent(LocWokbench::save);
	}

	private Optional<EReference> containerEReference(EClass eClass) {
		return eClass.getEAllReferences().stream()//
				.filter(EReference::isContainer)//
				.findFirst();
	}

}
