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


VERSION = "0.1.0.RELEASE"
REV = "52d5922"

SPASH_TEXT = """   ___   ____ ____
  / _ \ / __// __/
 / // /_\ \ _\ \\
/____//___//___/    %s [rev %s]

Welcome to DSS. For assistance press TAB or type "help" then hit ENTER.""" % \
(VERSION, REV)

DARK_BACKGROUND_COLORS = [Style.BRIGHT + Fore.CYAN,
                          Fore.GREEN,
                          Fore.YELLOW]

LIGHT_BACKGROUND_COLORS = [Fore.BLUE,
                           Fore.MAGENTA]

COLORS = DARK_BACKGROUND_COLORS if True else LIGHT_BACKGROUND_COLORS


def dss(prompt="dss> "):
    t0 = time()

    executive = Executive()

    prompt = COLORS[0] + prompt + Style.RESET_ALL

    print COLORS[1] + SPASH_TEXT + Style.RESET_ALL

    readline.parse_and_bind('tab: complete')

    while True:
        s = ""
        try:
            s = raw_input(prompt)
        except EOFError:
            really = raw_input("\nDo you really want to exit ([y]/n)? ")
            if (len(really) == 0) or (really.lower() == "y"):
                print "Total execution time %d seconds." % (time() - t0)
                sys.exit()
            else:
                print ''
        except KeyboardInterrupt:
            print '\nKeyboardInterrupt'

        executive.command = s

if __name__ == '__main__':
    dss()