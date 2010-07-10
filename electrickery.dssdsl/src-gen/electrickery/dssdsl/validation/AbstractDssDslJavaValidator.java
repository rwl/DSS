package electrickery.dssdsl.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public class AbstractDssDslJavaValidator extends AbstractDeclarativeValidator {

@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/executive"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2002/Ecore"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/common"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/meter"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/delivery"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/general"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/conversion"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.electrickery.com/control"));
		return result;
	}

}
