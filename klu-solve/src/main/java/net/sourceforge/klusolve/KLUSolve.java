package net.sourceforge.klusolve;

import java.util.UUID;

public class KLUSolve extends BaseSolve {

	/**
	 * Returns the non-zero handle of a new sparse matrix, if successful
	 * must call deleteSparseSet on the valid handle when finished.
	 */
	public static UUID newSparseSet(int nBus) {
		UUID uuid;
		ISystem sys;

		try {
			uuid = UUID.randomUUID();
			sys = new KLUSystem();
			sys.initialize(nBus, 0, nBus);
			sysMap.put(uuid, sys);
		} catch (Exception e) {
			return null;
		}

		return uuid;
	}

}
