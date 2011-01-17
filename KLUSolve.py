# Copyright (C) 2010 Richard Lincoln
#
# This library is free software: you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation: either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY: without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library: if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

from PyTrilinos import Amesos, Epetra, Teuchos

def KLUSolve():
    A = Epetra.CrsMatrix(Epetra.Copy, map, 3)
    x = Epetra.MultiVector()
    b = Epetra.Vector()

    problem = Epetra.LinearProblem(A, x, b)

    # serial: KLU,LAPACK,UMFPACK,SuperLU,TAUCS
    # parallel: SuperLU_DIST,PARDISO,DSCPACK,MUMPS,PARAKLETE
    solver_type = "KLU"

    factory = Amesos.Factory()
    solver = factory.Create(solver_type, problem)

    #if verbose:
    param = Teuchos.ParameterList()
    param.set("PrintTiming", True)
    param.set("PrintStatus", True)
    solver.SetParameters(param)

    ierr = solver.SymbolicFactorization()
    if ierr > 0: print "Error:", ierr
    ierr = solver.NumericFactorization()
    if ierr > 0: print "Error:", ierr
    solver.Solve()
