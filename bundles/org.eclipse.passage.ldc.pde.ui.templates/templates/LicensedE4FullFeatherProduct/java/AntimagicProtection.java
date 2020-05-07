package $packageName$;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class AntimagicProtection {
	@Execute
	public void execute() {
		System.err.println("Put on or take off the protection spell on a target"); //$NON-NLS-1$
	}

	@CanExecute
	public boolean available() {
		System.err.println("here we going to check license for 'antimagic-protection' fature"); //$NON-NLS-1$
		return true;
	}

}