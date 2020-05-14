package $packageName$;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import $packageName$.magic.service.Creature;
import org.eclipse.swt.graphics.Image;

final class CreatureLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}

	@Override
	public void dispose() {
		// do nothing
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false; // do nothing
	}

	@Override
	public Image getImage(Object element) {
		return null; // FIXME: !
	}

	@Override
	public String getText(Object element) {
		return ((Creature) element).name();
	}

}
