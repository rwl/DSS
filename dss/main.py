__copyright__ = "Copyright (C) 2010 Richard Lincoln"
__license__ = "GNU GPLv2" # readline

import sys

from time import time

if sys.platform == "win32":
    try:
        from colorama import init, Fore, Back, Style
        init()
    except ImportError:
        print "For syntax highlighting on Windows please install colorama."
        from dss.ansi import Fore, Back, Style #@UnusedImport
else:
    from dss.ansi import Fore, Back, Style

import atexit
import os
import readline
import rlcompleter
import logging

from dss.executive.Executive import Executive

HISTORY_PATH = os.path.expanduser("~/.dsshistory")

LOG_FILENAME = 'log.dss'

logging.basicConfig(filename=LOG_FILENAME, level=logging.DEBUG)

def save_history(historyPath=HISTORY_PATH):
    import readline
    readline.write_history_file(historyPath)

atexit.register(save_history)


class DSS(object):

    VERSION = "0.1.0.RELEASE"
    REV = "52d5922" # FIXME: get from git

    SPASH_TEXT = """   ___   ____ ____
  / _ \ / __// __/
 / // /_\ \ _\ \\
/____//___//___/    %s [rev %s]

Welcome to DSS. For assistance press TAB or type "help" then hit ENTER.""" % \
    (VERSION, REV)

    DARK_BACKGROUND_COLORS = [Fore.YELLOW,
                              Fore.GREEN,
                              Fore.CYAN]

    LIGHT_BACKGROUND_COLORS = [Fore.BLUE,
                               Fore.MAGENTA]

    COLORS = DARK_BACKGROUND_COLORS if True else LIGHT_BACKGROUND_COLORS

    def __init__(self, prompt="dss> "):
        self.prompt = prompt

        self.executive = Executive()

        if os.path.exists(HISTORY_PATH):
            readline.read_history_file(HISTORY_PATH)


    def run(self):
        exit_code = 0
        t0 = time()

        print self.COLORS[1] + self.SPASH_TEXT + Style.RESET_ALL

#        readline.parse_and_bind('tab: complete')

        while True:
            s = ""
            try:
                s = raw_input(self._prompt())
            except EOFError:
                if True:#self._really():
                    break
                else:
                    print ''
            except KeyboardInterrupt:
                if True:#self._really():
                    exit_code = 130
                    break
                else:
                    print ''

            self.executive.command = s

        print "\nTotal execution time %d seconds." % (time() - t0)

        return exit_code


    def _prompt(self):
        active = self.executive.activeObject
        prompt = self.COLORS[0] + self.prompt + Style.RESET_ALL
        if active is None:
            return prompt
        else:
            prefix = active.__class__.__name__.lower() + "." + active.name
            return self.COLORS[2] + prefix + " " + prompt


    def _really(self):
        really = raw_input("\nDo you really want to exit ([y]/n)? ")
        if (len(really) == 0) or (really.lower() == "y"):
            return True
        else:
            return False


class DSSCompleter(object):

#    commands = ["About", "AllocateLoads", "BuildY", "CalcVoltageBases",
#        "CD", "CktLosses", "Clear", "Close", "CloseDI", "Compile",
#        "Redirect", "Currents", "DI_plot", "Disable", "DOScmd", "Dump",
#        "Edit", "Enable",
#        "Estimate", "Export", "Fileedit", "Get", "Help", "Init", "Interpolate",
#        "Losses", "More", "New", "Open", "PhaseLosses", "Plot", "Powers",
#        "Reconductor", "Reset", "Rotate", "SeqCurrents", "SeqPowers",
#        "SeqVoltages", "Set", "SetkVBase", "Show", "Solve", "Totals",
#        "Varnames", "VarValues", "Vdiff", "Visualize", "Voltages",
#        "YearlyCurves", "Ysc", "Zsc", "Zsc10", "ZscRefresh"]

    _commands = ["about", "allocateLoads", "buildY", "calcVoltageBases",
        "cd", "cktLosses", "clear", "close", "closeDI", "compile",
        "redirect", "currents", "DI_plot", "disable", "cmd", "dump",
        "edit", "enable", "estimate", "export", "fileEdit", "get",
        "help", "init", "interpolate",
        "losses", "more", "new", "open", "phaseLosses", "plot", "powers",
        "reconductor", "reset", "rotate", "seqCurrents", "seqPowers",
        "seqVoltages", "set", "setkVBase", "show", "solve", "totals",
        "varNames", "varValues", "Vdiff", "visualize", "voltages",
        "yearlyCurves", "Ysc", "Zsc", "Zsc10", "ZscRefresh"]

    commands = [c + " " for c in _commands]

    completions = {}

    def __init__(self):
#        self.options = sorted(options)
        return

    def complete(self, prefix, index):

        try:
            matches = self.completions[prefix]
        except KeyError:
            if prefix.lower().startswith("new"):
                parts = prefix.split()
                if len(parts) == 1:
                    matches = ["object="]
                elif len(parts) == 2:
                    pass
            else:
                matches = [value for value in self.commands
                           if value.lower().startswith(prefix.lower())]
            logging.debug('%s matches: %s', repr(prefix), matches)
            self.completions[prefix] = matches

        response = None
#        if index == 0:
#            # This is the first time for this text, so build a match list.
#            if prefix:
#                self.matches = [s
#                                for s in self.commands
#                                if s.lower() and s.startswith(prefix.lower())]
#                logging.debug('%s matches: %s', repr(prefix), self.matches)
#            else:
#                self.matches = self.commands[:]
#                logging.debug('(empty input) matches: %s', self.matches)

        # Return the state'th item from the match list,
        # if we have that many.
        try:
            response = matches[index]
        except IndexError:
            response = None

        logging.debug('complete(%s, %s) => %s',
                      repr(prefix), index, repr(response))

        return response


class ARLCompleter(object):

    def __init__(self):
        self.logic = {
            'build':
                    {
                    'barracks':None,
                    'bar':None,
                    'generator':None,
                    'lab':None
                    },
            'train':
                    {
                    'riflemen':None,
                    'rpg':None,
                    'mortar':None
                    },
            'research':
                    {
                    'armor':None,
                    'weapons':None,
                    'food':None
                    }
            }


    def traverse(self, tokens, tree):
        if tree is None:
            return []
        elif len(tokens) == 0:
            return tree.keys()
        if len(tokens) == 1:
            return [x+' ' for x in tree if x.startswith(tokens[0])]
        else:
            if tokens[0] in tree.keys():
                return self.traverse(tokens[1:],tree[tokens[0]])
            else:
                return []
        return []


    def complete(self, text, state):
        tokens = readline.get_line_buffer().split()
#        if not tokens or readline.get_line_buffer()[-1] == ' ':
#            tokens.append(" ")
        results = self.traverse(tokens, self.logic) + [None]
        return results[state]


def main():
#    readline.set_completer(DSSCompleter().complete)
    readline.set_completer(ARLCompleter().complete)
    readline.parse_and_bind('tab: complete')

    exit_code = DSS().run()
    print "DSS exited with code %d" % exit_code
    sys.exit(exit_code)


if __name__ == '__main__':
    main()
