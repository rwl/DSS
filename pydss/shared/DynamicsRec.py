

class DynamicsRec(object):
    """Variables needed for dynamics and user-written models."""
    # time vars
    h = None
    # time step size in sec for dynamics
    t = None
    # sec from top of hour
    tstart = None
    tstop = None
    # 0 = new time step; 1 = same time step as last iteration
    iterationFlag = None
    # PEAKSNAP, DAILYMODE, YEARLYMODE, MONTECARLO, etc. (see DSSGlobals)
    solutionMode = None
    # FIXME Generate getters and setters
