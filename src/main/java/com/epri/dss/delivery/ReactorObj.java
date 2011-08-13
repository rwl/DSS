package com.epri.dss.delivery;

/**
 * Uses same rules as Capacitor and Fault for connections
 *
 * Implemented as a two-terminal constant impedance (power delivery element)
 * Defaults to a shunt reactor but can be connected as a two-terminal series
 * reactor
 *
 * If parallel=Yes, then the R and X components are treated as being in
 * parallel
 *
 * bus2 connection defaults to 0 node of bus1 (if bus2 has the default bus
 * connection at the time bus1 is defined.  Therefore, if only bus1 is
 * specified, a shunt reactor results.
 * If delta connected, bus2 is set to node zero of bus1 and nothing is returned
 * in the lower half of YPrim - all zeroes.
 *
 * If an ungrounded wye is desired, explicitly set bus2= and set all nodes the
 * same,
 *   e.g. bus1.4.4.4   (uses 4th node of bus1 as neutral point)
 *     or busNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series Reactor!
 *
 * A series reactor is specified simply by setting bus2 and declaring the
 * connection to be wye.  If the connection is specified as delta, nothing will
 * be connected to bus2. In fact the number of terminals is set to 1.
 *
 * Reactance may be specified as:
 *
 *   1.  kVAr and kV ratings at base frequency.  impedance.  Specify kVAr as
 *       total for all phases. For 1-phase, kV = reactor coil kV rating.
 *       For 2 or 3-phase, kV is line-line three phase. For more than 3 phases,
 *       specify kV as actual coil voltage.
 *   2.  Series resistance and reactance in ohns at base frequency to be used
 *       in each phase.  If specified in this manner, the given value is always
 *       used whether wye or delta.
 *   3.  A, R and X matrices.
 *       If conn=wye then 2-terminal through device
 *       If conn=delta then 1-terminal.
 *       Ohms at base frequency
 *       Note that Rmatrix may be in parallel with Xmatrix (set parallel = Yes)
 *
 */
public interface ReactorObj extends PDElement {

	double getR();

	void setR(double r);

	double getRp();

	void setRp(double rp);

	double getGp();

	void setGp(double gp);

	double getX();

	void setX(double x);

	double getKVArRating();

	void setKVArRating(double kvarrating);

	double getKVRating();

	void setKVRating(double kvrating);

	double[] getRMatrix();

	void setRMatrix(double[] rmatrix);

	double[] getGMatrix();

	void setGMatrix(double[] gmatrix);

	double[] getXMatrix();

	void setXMatrix(double[] xMatrix);

	double[] getBMatrix();

	void setBMatrix(double[] bmatrix);

	int getConnection();

	void setConnection(int connection);

	int getSpecType();

	void setSpecType(int specType);

	boolean isParallel();

	void setParallel(boolean isParallel);

	boolean isRpSpecified();

	void setRpSpecified(boolean rpSpecified);

}
