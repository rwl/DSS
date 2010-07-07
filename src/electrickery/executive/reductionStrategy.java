/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>reduction Strategy</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.executive.ExecutivePackage#getreductionStrategy()
 * @model
 * @generated
 */
public enum reductionStrategy implements Enumerator {
	/**
	 * The '<em><b>Stubs</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STUBS_VALUE
	 * @generated
	 * @ordered
	 */
	STUBS(0, "Stubs", "Stubs"),

	/**
	 * The '<em><b>Merge Parallel</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MERGE_PARALLEL_VALUE
	 * @generated
	 * @ordered
	 */
	MERGE_PARALLEL(1, "MergeParallel", "MergeParallel"),

	/**
	 * The '<em><b>Breakloops</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BREAKLOOPS_VALUE
	 * @generated
	 * @ordered
	 */
	BREAKLOOPS(2, "Breakloops", "Breakloops"),

	/**
	 * The '<em><b>Tapends</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAPENDS_VALUE
	 * @generated
	 * @ordered
	 */
	TAPENDS(3, "Tapends", "Tapends"),

	/**
	 * The '<em><b>Ends</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENDS_VALUE
	 * @generated
	 * @ordered
	 */
	ENDS(4, "Ends", "Ends"),

	/**
	 * The '<em><b>Switches</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SWITCHES_VALUE
	 * @generated
	 * @ordered
	 */
	SWITCHES(5, "Switches", "Switches");

	/**
	 * The '<em><b>Stubs</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merges short branches with impedance less than Zmag (default = 0.02 ohms).
	 * <!-- end-model-doc -->
	 * @see #STUBS
	 * @model name="Stubs"
	 * @generated
	 * @ordered
	 */
	public static final int STUBS_VALUE = 0;

	/**
	 * The '<em><b>Merge Parallel</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merges lines that have been found to be in parallel.
	 * <!-- end-model-doc -->
	 * @see #MERGE_PARALLEL
	 * @model name="MergeParallel"
	 * @generated
	 * @ordered
	 */
	public static final int MERGE_PARALLEL_VALUE = 1;

	/**
	 * The '<em><b>Breakloops</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Disables one of the lines at the head of a loop.
	 * <!-- end-model-doc -->
	 * @see #BREAKLOOPS
	 * @model name="Breakloops"
	 * @generated
	 * @ordered
	 */
	public static final int BREAKLOOPS_VALUE = 2;

	/**
	 * The '<em><b>Tapends</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Eliminates all buses except those at the feeder ends, at tap points and where the feeder turns by greater than maxangle degrees.
	 * <!-- end-model-doc -->
	 * @see #TAPENDS
	 * @model name="Tapends"
	 * @generated
	 * @ordered
	 */
	public static final int TAPENDS_VALUE = 3;

	/**
	 * The '<em><b>Ends</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Eliminates dangling ends only.
	 * <!-- end-model-doc -->
	 * @see #ENDS
	 * @model name="Ends"
	 * @generated
	 * @ordered
	 */
	public static final int ENDS_VALUE = 4;

	/**
	 * The '<em><b>Switches</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merges switches with downline lines and eliminates dangling switches.
	 * <!-- end-model-doc -->
	 * @see #SWITCHES
	 * @model name="Switches"
	 * @generated
	 * @ordered
	 */
	public static final int SWITCHES_VALUE = 5;

	/**
	 * An array of all the '<em><b>reduction Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final reductionStrategy[] VALUES_ARRAY =
		new reductionStrategy[] {
			STUBS,
			MERGE_PARALLEL,
			BREAKLOOPS,
			TAPENDS,
			ENDS,
			SWITCHES,
		};

	/**
	 * A public read-only list of all the '<em><b>reduction Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<reductionStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>reduction Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static reductionStrategy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			reductionStrategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>reduction Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static reductionStrategy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			reductionStrategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>reduction Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static reductionStrategy get(int value) {
		switch (value) {
			case STUBS_VALUE: return STUBS;
			case MERGE_PARALLEL_VALUE: return MERGE_PARALLEL;
			case BREAKLOOPS_VALUE: return BREAKLOOPS;
			case TAPENDS_VALUE: return TAPENDS;
			case ENDS_VALUE: return ENDS;
			case SWITCHES_VALUE: return SWITCHES;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private reductionStrategy(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //reductionStrategy
