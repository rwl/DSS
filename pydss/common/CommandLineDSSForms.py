from dss.common.DSSForms import (DSSForms,)
# from java.util.Scanner import (Scanner,)
# from javax.swing.JOptionPane import (JOptionPane,)
import sys
import sys


class CommandLineDSSForms(object, DSSForms):
    sc = Scanner(sys.stdin)
    controlPanelCreated = None
    # private static ControlPanel ControlPanel;
    rebuildHelpForm = None

    def __init__(self):
        pass

    class DSSFormsHolder(object):
        INSTANCE = CommandLineDSSForms()

    @classmethod
    def getInstance(cls):
        return cls.DSSFormsHolder.INSTANCE

    def createControlPanel(self):
        pass

    def exitControlPanel(self):
        pass

    def initProgressForm(self):
        pass

    def progressCaption(self, s):
        pass

    def progressFormCaption(self, s):
        pass

    def progressHide(self):
        pass

    def showControlPanel(self):
        pass

    def showHelpForm(self):
        pass

    def showAboutBox(self):
        pass

    def showPropEditForm(self):
        pass

    def showPctProgress(self, count):
        pass

    def showMessageForm(self, s):
        pass

    def messageDlg(self, msg, err):
        if err:
            print msg
            Result = 0
        else:
            while True:
                print msg
                sys.stdout.write('\nEnter \"Ignore\" or \"Abort\" [Ignore]: ')
                answer = self.sc.next()
                if answer.equalsIgnoreCase('abort'):
                    Result = -1
                    break
                elif answer.equalsIgnoreCase('ignore') or (len(answer) == 0):
                    Result = 0
                    break
        return Result

    def infoMessageDlg(self, msg):
        print msg

    def getDSSExeFile(self):
        return None

    def closeDownForms(self):
        pass

    def showTreeView(self, fname):
        pass

    def makeChannelSelection(self, numFieldsToSkip, filename):
        return False

    def isRebuildHelpForm(self):
        return self.rebuildHelpForm

    def setRebuildHelpForm(self, rebuild):
        self.rebuildHelpForm = rebuild
