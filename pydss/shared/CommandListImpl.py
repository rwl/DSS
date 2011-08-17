from dss.shared.CommandList import (CommandList,)
from dss.shared.impl.HashListImpl import (HashListImpl,)


class CommandListImpl(object, CommandList):
    commandList = None
    abbrevAllowed = None

    def __init__(self, commands):
        super(CommandListImpl, self)()
        self.commandList = HashListImpl(len(commands))
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(commands)):
                break
            self.commandList.add(commands[i])
        self.abbrevAllowed = True

    def addCommand(self, cmd):
        self.commandList.add(cmd)

    def getCommand(self, cmd):
        result = self.commandList.find(cmd)
        # If no match found on whole command, check for abbrev
        # This routine will generally be faster if one enters the whole command
        if result == -1:
            if self.abbrevAllowed:
                result = self.commandList.findAbbrev(cmd)
        return result

    def get(self, i):
        return self.commandList.get(i)

    def getNumCommands(self):
        return self.commandList.listSize()

    def isAbbrevAllowed(self):
        return self.abbrevAllowed

    def setAbbrevAllowed(self, allowed):
        self.abbrevAllowed = allowed
