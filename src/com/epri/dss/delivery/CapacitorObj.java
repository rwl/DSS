package com.epri.dss.delivery;

/**
 * Basic capacitor
 * 
 * Implemented as a two-terminal constant impedance (Power Delivery Element)
 * 
 * Bus2 connection defaults to 0 node of Bus1 (if Bus2 has the default bus
 * connection at the time Bus1 is defined.  Therefore, if only Bus1 is
 * specified, a shunt capacitor results. If delta connected, Bus2 is set to
 * node zero of Bus1 and nothing is returned in the lower half of YPrim - all
 * zeroes.
 * 
 * If an ungrounded wye is desired, explicitly set Bus2= and set all nodes the
 * same,
 *   e.g. Bus1.4.4.4   (uses 4th node of Bus1 as neutral point)
 *     or BusNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series capacitor!
 * 
 * A series capacitor is specified simply by setting bus2 and declaring the
 * connection to be Wye.  If the connection is specified as delta, nothing will
 * be connected to Bus2.
 * In fact the number of terminals is set to 1.
 * 
 * Capacitance may be specified as:
 * 
 *   1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
 *       total for all phases (all cans assumed equal). For 1-phase,
 *       kV = capacitor can kV rating. For 2 or 3-phase, kV is line-line three
 *       phase. For more than 3 phases, specify kV as actual can voltage.
 *   2.  Capacitance in uF to be used in each phase.  If specified in this
 *       manner, the given value is always used whether wye or delta.
 *   3.  A nodal C matrix (like a nodal admittance matrix).
 *       If conn=wye then 2-terminal through device
 *       If conn=delta then 1-terminal.
 *       Microfarads.
 *
 */
public interface CapacitorObj extends PDElement {
	
	int getStates(int Idx);
	
	void setStates(int Idx, int Value);
	
	/* 1=kvar, 2=Cuf, 3=Cmatrix */
	void setNumSteps(int Value);
	
	int getNumSteps();

	int getConnection();

	void setConnection(int connection);
	
	boolean addStep();
	
	boolean subtractStep();
	
	int availableSteps();

	double getTotalkvar();

	double getKvrating();

}
