import os
import csv
import win32com.client

from numpy import array
from scipy.sparse import csc_matrix, csr_matrix, lil_matrix, vstack
from scipy.io import mmwrite

basedir = os.path.join("..", "..", "IEEETestCases", "123Bus")
basefile = os.path.join(basedir, "IEEE123Master.dss")
outdir = os.path.join(basedir, "ckt123")

engine = win32com.client.Dispatch("OpenDSSEngine.DSS")
engine.Start("0")
dss_text = engine.Text
dss_text.Command = "clear"
dss_text.Command = "compile " + basefile
circuit = engine.ActiveCircuit

dss_text.Command = 'solve'
solution = circuit.Solution

ckt_name = circuit.Name

##exported = ["Voltages", "Currents", "Powers", "Loads", "Yprim", "Y"]

export = "Yprim"
dss_text.Command = "export " + export
csvfile = os.path.join(basedir, ckt_name + "_EXP_" + export.upper() + ".CSV")
reader = csv.reader(open(csvfile, "rb"))

name = reader.next()[0]
rows = []
for row in reader:
   if len(row) == 1:
      
      s = vstack(rows, "csr")
      mmwrite(os.path.join(outdir, name + "_yprim.mtx"), s)
      
      name = row[0]
      rows = []
      continue
   
   m = csr_matrix(
      [complex(float(row[i]),
               float(row[i+1])) for i in range(0, len(row)-1, 2)]
   )
   rows.append(m)

##if solution.Converged:
##   for i in range(nckt):
##      element = circuit.CktElements(i)
##      yprim = array(element.YPrim)
##else:
##   print "Failed to converge."
