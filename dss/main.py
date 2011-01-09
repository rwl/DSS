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

import readline

from dss.executive.Executive import Executive

class DSS(object):

    VERSION = "0.1.0.RELEASE"
    REV = "52d5922"

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


    def run(self):
        t0 = time()

        print self.COLORS[1] + self.SPASH_TEXT + Style.RESET_ALL

        readline.parse_and_bind('tab: complete')

        while True:
            s = ""
            try:
                s = raw_input(self._prompt())
            except EOFError:
                if self._really():
                    break
                else:
                    print ''
            except KeyboardInterrupt:
                if self._really():
                    break
                else:
                    print ''

            self.executive.command = s

        print "Total execution time %d seconds." % (time() - t0)
        return 0


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


def main():
    exit_code = DSS().run()
    sys.exit(exit_code)


if __name__ == '__main__':
    main()
