package electrickery.resource.test;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import electrickery.common.Circuit;
import electrickery.resource.DSSResource;
import electrickery.resource.impl.DSSResourceImpl;

import junit.framework.TestCase;

public class DSSResourceTest extends TestCase {

    private String circuitName = "4Bus-DY-Bal";

    /**
     * The fixture for this DSSResource test case.
     *
     */
    protected DSSResource fixture = null;

    /**
     * Sets the fixture for this DSSResource test case.
     *
     */
    protected void setFixture(DSSResource fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this DSSResource test case.
     *
     * @generated
     */
    protected DSSResource getFixture() {
        return fixture;
    }

    /**
     * @see junit.framework.TestCase#setUp()
     * @generated NOT
     */
    protected void setUp() throws Exception {
        //URI input = URI.createURI("platform://plugin/electrickery.dss/data/" + circuitName + "/" + circuitName + ".DSS");
        URI input = URI.createURI("data/" + circuitName + "/" + circuitName + ".DSS");

        Resource res = new DSSResourceImpl(input);
        res.load(Collections.EMPTY_MAP);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    protected void tearDown() throws Exception {
        setFixture(null);
    }

    /**
     *
     */
    public void testDSSResource() {
        Circuit ckt = (Circuit) getFixture().getContents().get(0);

//        assertEquals(2, ckt.getLines().size());
//        assertEquals(1, ckt.getTransformers().size());
//        assertEquals(1, ckt.getLoads().size());
    }

}
