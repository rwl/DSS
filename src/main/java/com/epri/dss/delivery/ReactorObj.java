package com.epri.dss.delivery;

/**
 * Basic Reactor
 * 
 * Uses same rules as Capacitor and Fault for connections
 * 
 * Implemented as a two-terminal constant impedance (Power Delivery Element)
 * Defaults to a Shunt Reactor but can be connected as a two-terminal series
 * reactor
 * 
 * If Parallel=Yes, then the R and X components are treated as being in
 * parallel
 * 
 * Bus2 connection defaults to 0 node of Bus1 (if Bus2 has the default bus
 * connection at the time Bus1 is defined.  Therefore, if only Bus1 is
 * specified, a shunt Reactor results.
 * If delta connected, Bus2 is set to node zero of Bus1 and nothing is returned
 * in the lower half of YPrim - all zeroes.
 * 
 * If an ungrounded wye is desired, explicitly set Bus2= and set all nodes the
 * same,
 *   e.g. Bus1.4.4.4   (uses 4th node of Bus1 as neutral point)
 *     or BusNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series Reactor!
 * 
 * A series Reactor is specified simply by setting bus2 and declaring the
 * connection to be Wye.  If the connection is specified as delta, nothing will
 * be connected to Bus2. In fact the number of terminals is set to 1.
 * 
 * Reactance may be specified as:
 * 
 *   1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
 *       total for all phases. For 1-phase, kV = Reactor coil kV rating.
 *       For 2 or 3-phase, kV is line-line three phase. For more than 3 phases,
 *       specify kV as actual coil voltage.
 *   2.  Series Resistance and Reactance in ohns at base frequency to be used
 *       in each phase.  If specified in this manner, the given value is always
 *       used whether wye or delta.
 *   3.  A R and X matrices.
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

	double getKvarrating();

	void setKvarrating(double kvarrating);

	double getKvrating();

	void setKvrating(double kvrating);

	double[] getRmatrix();

	void setRmatrix(double[] rmatrix);

	double[] getGmatrix();

	void setGmatrix(double[] gmatrix);

	double[] getXMatrix();

	void setXMatrix(double[] xMatrix);

	double[] getBmatrix();

	void setBmatrix(double[] bmatrix);

	int getConnection();

	void setConnection(int connection);

	int getSpecType();

	void setSpecType(int specType);

	boolean isIsParallel();

	void setIsParallel(boolean isParallel);

	boolean isRpSpecified();

	void setRpSpecified(boolean rpSpecified);
	
}
