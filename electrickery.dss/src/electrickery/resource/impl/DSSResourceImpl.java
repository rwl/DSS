/**
 *
 */
package electrickery.resource.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import electrickery.ElectrickeryPackage;
import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.ControlPackage;
import electrickery.conversion.ConversionPackage;
import electrickery.resource.DSSResource;

/**
 * @author rwl
 *
 */
public class DSSResourceImpl extends ResourceImpl implements
        DSSResource {

    protected EList<Circuit> circuits = new BasicEList<Circuit>(1);

    /**
     * The last new object.
     */
    protected EObject activeObject;

    /**
     * Arguments may be delimited by commas or whitespace (spaces or tabs).
     */
    protected static final String DELIM = "[,\\s]+";

    /**
     * The map from name to EClass.
     */
    protected Map<String, EClass> eNameToEClassMap;

    protected EList<EPackage> electrickeryPackages;

    /**
     *
     */
    public DSSResourceImpl() {
        eNameToEClassMap = new HashMap<String, EClass>(32);
        for (EPackage ePackage : ElectrickeryPackage.eINSTANCE.getESubpackages())
            mapEClassNames(ePackage);
    }

    /**
     * @param uri
     */
    public DSSResourceImpl(URI uri) {
        super(uri);
    }


    /**
     * Called to load the resource.
     * @param inputStream the stream
     * @param options the load options.
     * @exception IOException
     */
    @Override
    protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        parseFile(inputStream);
        for (int i = 0; i < this.circuits.size(); i++)
            this.getContents().add(circuits.get(i));
    }

    /**
     *
     * @param inputStream
     */
    protected void parseFile(InputStream inputStream) {

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter(DELIM);

        while (scanner.hasNextLine()) {
            String cmd = scanner.next();

            if (cmd.equalsIgnoreCase("Clear")) {
                parseClear(scanner);
            } else if (cmd.equalsIgnoreCase("New")) {
                parseNew(scanner);
            } else if (cmd.equalsIgnoreCase("Compile") || cmd.equalsIgnoreCase("Redirect")) {
                parseCompileRedirect(scanner);
            }
        }
    }

    /**
     * Clears all circuit element definitions.
     *
     * @param scanner
     */
    protected void parseClear(Scanner scanner) {
        this.circuits.clear();
    }

    /**
     * Adds an element described on the remainder of the line to the active circuit.
     *
     * @param scanner
     */
    protected void parseNew(Scanner scanner) {
        // New object=circuit.ieee123
        // ~ basekv=4.16 Bus1=150 pu=1.00 R1=0 X1=0.0001 R0=0 X0=0.0001

        String[] parts = parseParam(scanner);
        String cls = parts[0];
        if (parts.length == 2) {
            if (!parts[0].equalsIgnoreCase("object"))
                // TODO: Throw and exception.
                System.out.println("Invalid New command: " + parts[0] + "=" + parts[1]);
            cls = parts[1];
        }

        // Create the object and optionally name it.
        String[] clsParts = parseClassAndName(cls);
        createFromName(clsParts[0]);
        if (clsParts.length > 1)
            // FIXME: Create a Named base class for Ciruit and CircuitElement,
            ((CircuitElement) this.activeObject).setName(clsParts[1]);

        if (this.activeObject instanceof Circuit)
            this.circuits.add((Circuit) this.activeObject);

        while (scanner.hasNext()) {
            parts = parseParam(scanner);
            EStructuralFeature feature;
            String value;
            if (parts.length == 1) {
                feature = DSSResource.LINECODE_FEATURES[0];
                value = parts[0];
                if (value.equals("\n") && scanner.next("~") == null)
                    break;
            } else {
                feature = this.activeObject.eClass().getEStructuralFeatures().get(0);
                value = parts[1];
            }
            this.activeObject.eSet(feature, value);
        }
    }

    /**
     * Simply redirects the parser to take input directly from a text file.
     *
     * @param scanner
     */
    protected void parseCompileRedirect(Scanner scanner) {
        String fileName = scanner.next();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        parseFile(inputStream);
    }

    /**
     * Continues editing the active object last selected by a New.
     *
     * @param scanner
     */
    protected void parseMore(Scanner scanner) {

    }

    /**
     * Opens a specified terminal conductor switch.
     *
     * @param scanner
     */
    protected void parseOpen(Scanner scanner) {

    }

    /**
     *
     * @param scanner
     * @return
     */
    protected String[] parseParam(Scanner scanner) {
        String param = scanner.next();
        if (param.startsWith("\"") || param.startsWith("\'")) {
            while (!param.endsWith("\"") || !param.endsWith("\'")) {
                param += scanner.next();
            }
            param.replace("\"", "");
            param.replace("\'", "");
        }
        String[] parts = param.split("="); // TODO: Check Regex.
        if (parts.length < 1 || parts.length > 2)
            // TODO: Throw and exception.
            System.out.println("Unrecognised parameter: " + param);
        return parts;
    }

    /**
     *
     * @param cls
     * @return
     */
    protected String[] parseClassAndName(String cls) {
        String[] parts = cls.split("."); // TODO: Check Regex.
        if (parts.length < 1 || parts.length > 2)
            // TODO: Throw and exception.
            System.out.println("Invalid object argument: " + cls);
        return parts;
    }

    /**
     *
     * @param name
     */
    protected void createFromName(String name) {
        EClass cls = eNameToEClassMap.get(name);
        EObject eObject = cls.getEPackage().getEFactoryInstance().create(cls);
        activeObject = eObject;
    }

    /**
     *
     * @param ePackage
     * @param name
     * @return
     */
    public void mapEClassNames(EPackage ePackage) {
        List<EClassifier> eClassifiers = ePackage.getEClassifiers();
        for (EClassifier eClassifier : eClassifiers) {
            if (eClassifier instanceof EClass) {
                if (!((EClass) eClassifier).isAbstract())
                    // Ignore case.
                    eNameToEClassMap.put(eClassifier.getName().toLowerCase(),
                            (EClass) eClassifier);
            }
        }
    }

}
