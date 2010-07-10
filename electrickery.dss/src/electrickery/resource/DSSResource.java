/**
 *
 */
package electrickery.resource;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import electrickery.general.GeneralPackage;

/**
 * @author rwl
 *
 */
public interface DSSResource extends Resource {

    public static final EStructuralFeature[] LINECODE_FEATURES = {
        GeneralPackage.Literals.LINE_CODE__NPHASES,
        GeneralPackage.Literals.LINE_CODE__R1,
        GeneralPackage.Literals.LINE_CODE__X1,
        GeneralPackage.Literals.LINE_CODE__R0,
        GeneralPackage.Literals.LINE_CODE__X0,
        GeneralPackage.Literals.LINE_CODE__C1,
        GeneralPackage.Literals.LINE_CODE__C0,
        GeneralPackage.Literals.LINE_CODE__UNITS,
        GeneralPackage.Literals.LINE_CODE__RMATRIX,
        GeneralPackage.Literals.LINE_CODE__CMATRIX,
        GeneralPackage.Literals.LINE_CODE__BASE_FREQ,
        GeneralPackage.Literals.LINE_CODE__NORM_AMPS,
        GeneralPackage.Literals.LINE_CODE__EMERG_AMPS,
        GeneralPackage.Literals.LINE_CODE__FAULT_RATE,
        GeneralPackage.Literals.LINE_CODE__PCT_PERM,
        GeneralPackage.Literals.LINE_CODE__REPAIR,
        GeneralPackage.Literals.LINE_CODE__KRON,
        GeneralPackage.Literals.LINE_CODE__RG,
        GeneralPackage.Literals.LINE_CODE__XG,
        GeneralPackage.Literals.LINE_CODE__RHO,
        GeneralPackage.Literals.LINE_CODE__NEUTRAL
    };
}
