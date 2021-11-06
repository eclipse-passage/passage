package $packageName$;

import java.util.Optional;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.FrameworkSupplier;
import org.eclipse.passage.lic.execute.DefaultFramework;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

@Component
public final class DemoFrameworkSupplier implements FrameworkSupplier {

	private final Framework framework = new DefaultFramework(
			() -> FrameworkUtil.getBundle(DemoFrameworkSupplier.class));

	@Override
	public Optional<Framework> get() {
		return Optional.of(framework);
	}

}
