
package electrickery.dssdsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class DssDslStandaloneSetup extends DssDslStandaloneSetupGenerated{

	public static void doSetup() {
		new DssDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

