from __pyjamas__ import (ARGERROR,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.Esolv32Problem import (Esolv32Problem,)
# from com.epri.dss.common.impl.DSSBus.NodeBus import (NodeBus,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)
# from org.apache.commons.lang.mutable.MutableLong import (MutableLong,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class YMatrix(object):
    # Options for building Y matrix
    SERIESONLY = 1
    WHOLEMATRIX = 2

    def __init__(self):
        pass

    @classmethod
    def reCalcAllYPrims(cls):
        ckt = DSSGlobals.activeCircuit
        if ckt.isLogEvents():
            Utilities.logThisEvent('Recalc All Yprims')
        for pElem in ckt.getCktElements():
            pElem.calcYPrim()

    @classmethod
    def reCalcInvalidYPrims(cls):
        """Recalc YPrims only for those circuit elements that have had changes
        since last solution.
        """
        ckt = DSSGlobals.activeCircuit
        if ckt.isLogEvents():
            Utilities.logThisEvent('Recalc Invalid Yprims')
        for pElem in ckt.getCktElements():
            if pElem.isYprimInvalid():
                pElem.calcYPrim()

    @classmethod
    def resetSparseMatrix(cls, *args):
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], CMatrix):
                ysystem, yMatrixSize = _0
            else:
                Y, size = _0
                if Y.longValue() != 0:
                    if cls.deleteSparseSet(Y.longValue()) < 1:
                        # get rid of existing one before making a new one
                        raise Esolv32Problem('Error deleting system Y Matrix in resetSparseMatrix. Problem with sparse matrix solver.')
                    Y.setValue(0)
                # make a new sparse set
                Y.setValue(cls.newSparseSet(size))
                if Y.longValue() < 1:
                    # raise an exception  TODO Check zero based indexing
                    raise Esolv32Problem('Error creating system Y Matrix. Problem with sparse matrix solver.')
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def initializeNodeVbase(cls):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumNodes()):
                break
            nb = ckt.getMapNodeToBus()[i]
            sol.getNodeVBase()[i] = ckt.getBuses()[nb.busRef].getKVBase() * 1000.0
            sol.setVoltageBaseChanged(False)

    @classmethod
    def buildYMatrix(cls, BuildOption, AllocateVI):
        """Builds designated Y matrix for system and allocates solution arrays.

        @throws Esolv32Problem
        """
        CmatArray = None
        # new function to log KLUSolve.DLL function calls
        # setLogFile("KLU_Log.txt", 1);
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if sol.isPreserveNodeVoltages():
            sol.updateVBus()
            # update voltage values stored with bus object
            # the following recounts the number of buses and resets meter zones and feeders
            # if radial but systemNodeMap not set then init for radial got skipped due to script sequence
        if ckt.isBusNameRedefined():
            ckt.reProcessBusDefs()
            # this changes the node references into the system Y matrix!
        YMatrixSize = ckt.getNumNodes()
        _0 = BuildOption
        _1 = False
        while True:
            if _0 == cls.WHOLEMATRIX:
                _1 = True
                cls.resetSparseMatrix(sol.getYSystem(), YMatrixSize)
                sol.setY(sol.getYSystem())
                break
            if (_1 is True) or (_0 == cls.SERIESONLY):
                _1 = True
                cls.resetSparseMatrix(sol.getYSeries(), YMatrixSize)
                sol.setY(sol.getYSeries())
                break
            break
        # tune up the Yprims if necessary
        if sol.isFrequencyChanged():
            cls.reCalcAllYPrims()
        else:
            cls.reCalcInvalidYPrims()
        if DSSGlobals.solutionAbort:
            DSSGlobals.doSimpleMsg('Y matrix build aborted due to error in primitive Y calculations.', 11001)
            return
            # some problem occurred building Yprims
        sol.setFrequencyChanged(False)
        if ckt.isLogEvents():
            _2 = BuildOption
            _3 = False
            while True:
                if _2 == cls.WHOLEMATRIX:
                    _3 = True
                    Utilities.logThisEvent('Building whole Y matrix')
                    break
                if (_3 is True) or (_2 == cls.SERIESONLY):
                    _3 = True
                    Utilities.logThisEvent('Building series Y matrix')
                    break
                break
            # add in Yprims for all devices
        for pElem in ckt.getCktElements():
            if pElem.isEnabled():
                _4 = BuildOption
                _5 = False
                while True:
                    if _4 == cls.WHOLEMATRIX:
                        _5 = True
                        CmatArray = pElem.getYPrimValues(DSSGlobals.ALL_YPRIM)
                        break
                    if (_5 is True) or (_4 == cls.SERIESONLY):
                        _5 = True
                        CmatArray = pElem.getYPrimValues(DSSGlobals.SERIES)
                        break
                    break
                # new function adding primitive Y matrix to KLU system Y matrix
                if CmatArray is not None:
                    if (
                        cls.addPrimitiveMatrix(sol.getY(), pElem.getYorder(), pElem.getNodeRef()[0], CmatArray[0]) < 0
                    ):
                        # TODO Check zero based indexing
                        raise Esolv32Problem('Node index out of range adding to System Y Matrix')
            # if enabled
        # allocate voltage and current vectors if requested
        if AllocateVI:
            if ckt.isLogEvents():
                Utilities.logThisEvent('ReAllocating Solution Arrays')
            sol.setNodeV(Utilities.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1))
            # allocate system voltage array - allow for zero element
            sol.getNodeV()[0] = Complex.ZERO
            # TODO Check zero based indexing
            sol.setCurrents(Utilities.resizeArray(sol.getCurrents(), ckt.getNumNodes() + 1))
            # allocate system current array
            sol.setAuxCurrents(Utilities.resizeArray(sol.getAuxCurrents(), ckt.getNumNodes()))
            # allocate system current array
            if sol.getVMagSaved() is not None:
                sol.setVMagSaved([None] * 0)
            if sol.getErrorSaved() is not None:
                sol.setErrorSaved([None] * 0)
            if sol.getNodeVBase() is not None:
                sol.setNodeVBase([None] * 0)
            sol.setVMagSaved([None] * ckt.getNumNodes())
            # zero fill
            sol.setErrorSaved([None] * ckt.getNumNodes())
            # zero fill
            sol.setNodeVBase([None] * ckt.getNumNodes())
            # zero fill
            cls.initializeNodeVbase()
        _6 = BuildOption
        _7 = False
        while True:
            if _6 == cls.WHOLEMATRIX:
                _7 = True
                sol.setSeriesYInvalid(True)
                # indicate that the series matrix may not match
                sol.setSystemYChanged(False)
                break
            if (_7 is True) or (_6 == cls.SERIESONLY):
                _7 = True
                sol.setSeriesYInvalid(False)
                # systemYChange unchanged
                break
            break
        # seleted RCD only done now on mode change
        # sol.setSolutionInitialized(false);  // require initialization of voltages if Y changed
        if sol.isPreserveNodeVoltages():
            sol.restoreNodeVFromVbus()

    @classmethod
    def addPrimitiveMatrix(cls, *args):
        """None
        ---
        @param id
        @param nOrder
        @param Nodes
        @param Mat
        @return 1 for success, 0 for invalid handle or a node number out of range
        """
        # TODO Auto-generated method stub
        _0 = args
        _1 = len(args)
        if _1 == 4:
            if isinstance(_0[0], CMatrix):
                y, yorder, nodes, mat = _0
                return 0
            else:
                id, nOrder, Nodes, Mat = _0
                return 0
        else:
            raise ARGERROR(4, 4)

    # TODO Auto-generated method stub

    @classmethod
    def checkYMatrixforZeroes(cls):
        """Leave the call to getMatrixElement, but add more diagnostics."""
        # function calls return 0 to indicate failure, 1 for success
        c = None
        sCol = 0
        ckt = DSSGlobals.activeCircuit
        Result = ''
        Y = ckt.getSolution().getY()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumNodes()):
                break
            cls.getMatrixElement(Y, i, i, c)
            if c.abs() == 0.0:
                nb = ckt.getMapNodeToBus()[i]
                Result += String.format.format('%sZero diagonal for bus %s, node %d', DSSGlobals.CRLF, ckt.getBusList().get(nb.busRef), nb.nodeNum)
        # new diagnostics
        cls.getSingularCol(Y, sCol)
        # returns a 0-based node number  TODO Check zero based indexing
        if sCol >= 0:
            nb = ckt.getMapNodeToBus()[sCol]
            Result += String.format.format('%sMatrix singularity at bus %s, node %d', DSSGlobals.CRLF, ckt.getBusList().get(nb.busRef), sCol)
        Cliques = list(ckt.getNumNodes())
        # TODO Check translation
        nIslands = cls.findIslands(Y, ckt.getNumNodes(), Cliques[0])
        if nIslands > 1:
            Result += String.format.format('%sFound %d electrical islands:', DSSGlobals.CRLF, nIslands)
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < nIslands):
                    break
                iCount = 0
                iFirst = 0
                _2 = True
                p = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        p += 1
                    if not (p < ckt.getNumNodes()):
                        break
                    if Cliques[p] == i:
                        iCount += 1
                        if iFirst == 0:
                            iFirst = p + 1
                nb = ckt.getMapNodeToBus()[iFirst]
                Result += String.format.format('%s  #%d has %d nodes, including bus %s (node %d)', DSSGlobals.CRLF, i, iCount, ckt.getBusList().get(nb.busRef), iFirst)
        return Result

    @classmethod
    def findIslands(cls, *args):
        """None
        ---
        @param id
        @param nOrder
        @param pNodes contains the island number for each node
        @return number of islands >= 1 by graph traversal
        """
        # TODO Auto-generated method stub
        _0 = args
        _1 = len(args)
        if _1 == 3:
            if isinstance(_0[0], CMatrix):
                y, numNodes, pNodes = _0
                return 0
            else:
                id, nOrder, pNodes = _0
                return 0
        else:
            raise ARGERROR(3, 3)

    @classmethod
    def getSingularCol(cls, *args):
        """None
        ---
        @param id
        @param Res a column number corresponding to a singularity, or 0 if not singular
        @return
        """
        # TODO Auto-generated method stub
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], CMatrix):
                y, sCol = _0
            else:
                id, Res = _0
                return 0
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def getMatrixElement(cls, *args):
        """None
        ---
        Deprecated, use getCompressedMatrix or getTripletMatrix.
        """
        # TODO Auto-generated method stub
        _0 = args
        _1 = len(args)
        if _1 == 4:
            if isinstance(_0[0], CMatrix):
                y, i, i2, c = _0
            else:
                id, i, j, Value = _0
                return 0
        else:
            raise ARGERROR(4, 4)

    @classmethod
    def newSparseSet(cls, nBus):
        """Returns the non-zero handle of a new sparse matrix, if successful
        must call deleteSparseSet on the valid handle when finished.
        """
        return 0

    @classmethod
    def deleteSparseSet(cls, id):
        """return 1 for success, 0 for invalid handle"""
        return 0

    @classmethod
    def solveSparseSet(cls, *args):
        """Return 1 for success, 2 for singular, 0 for invalid handle
        factors matrix if needed.
        """
        _0 = args
        _1 = len(args)
        if _1 == 3:
            if isinstance(_0[0], CMatrix):
                ysystem, complex, complex2 = _0
                return 0
            else:
                id, x, b = _0
                return 0
        else:
            raise ARGERROR(3, 3)

    @classmethod
    def zeroSparseSet(cls, id):
        """@param id
        @return 1 for success, 0 for invalid handle
        """
        return 0

    @classmethod
    def factorSparseMatrix(cls, id):
        """Does no extra work if the factoring was done previously.

        @param id
        @return 1 for success, 2 for singular, 0 for invalid handle
        """
        # These "get" functions for matrix information all return 1 for success, 0 for invalid handle
        return 0

    @classmethod
    def getSize(cls, id, Res):
        """Res is the matrix order (number of nodes)"""
        return 0

    @classmethod
    def getFlops(cls, id, Res):
        """The following function results are not known prior to factoring.

        @param id
        @param Res the number of floating point operations to factor
        @return
        """
        return 0

    @classmethod
    def getNNZ(cls, *args):
        """@param id
        @param Res number of non-zero entries in the original matrix
        @return
        """
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], CMatrix):
                y, iRes = _0
            else:
                id, Res = _0
                return 0
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def getSparseNNZ(cls, *args):
        """@param id
        @param Res number of non-zero entries in factored matrix
        @return
        """
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], CMatrix):
                y, iRes = _0
            else:
                id, Res = _0
                return 0
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def getRGrowth(cls, id, Res):
        """@param id
        @param Res the pivot element growth factor
        @return
        """
        return 0

    @classmethod
    def getRCond(cls, *args):
        """@param id
        @param Res quick estimate of the reciprocal of condition number
        @return
        """
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], CMatrix):
                y, dRes = _0
            else:
                id, Res = _0
                return 0
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def getCondEst(cls, id, Res):
        """@param id
        @param Res a more accurate estimate of condition number
        @return
        """
        return 0

    @classmethod
    def setLogFile(cls, *args):
        """@param Path
        @param Action 0 (close), 1 (rewrite) or 2 (append)
        @return
        """
        _0 = args
        _1 = len(args)
        if _1 == 2:
            if isinstance(_0[0], char):
                Path, Action = _0
                return 0
            else:
                string, action = _0
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def getCompressedMatrix(cls, id, nColP, nNZ, pColP, pRowIdx, Mat):
        """Fill sparse matrix in compressed column form.

        @param id
        @param nColP
        @param nNZ
        @param pColP must be of length nColP == nBus + 1
        @param pRowIdx length nnz
        @param Mat length nnz
        @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
        """
        return 0

    @classmethod
    def getTripletMatrix(cls, id, nNZ, pRows, pCols, Mat):
        """Fill sparse matrix in triplet form.

        @param id
        @param nNZ
        @param pRows length nnz
        @param pCols length nnz
        @param Mat length nnz
        @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
        """
        return 0

    @classmethod
    def addMatrixElement(cls, id, i, j, Value):
        """Deprecated, use addPrimitiveMatrix instead."""
        return 0

    # TODO Auto-generated method stub
    # TODO Auto-generated method stub
    # TODO Auto-generated method stub
    # TODO Auto-generated method stub
    # TODO Auto-generated method stub
