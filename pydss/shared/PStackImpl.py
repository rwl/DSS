from dss.shared.impl.StackBaseImpl import (StackBaseImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.PStack import (PStack,)


class PStackImpl(StackBaseImpl, PStack):
    items = None

    def __init__(self, initSize):
        super(PStackImpl, self)(initSize)
        self.items = [None] * initSize

    def push(self, p):
        self.numItems += 1
        if self.numItems > self.maxItems:
            self.maxItems += self.increment
            self.items = Utilities.resizeArray(self.items, self.maxItems)
        self.items[self.numItems] = p

    def pop(self):
        if self.numItems > 0:
            Result = self.items[self.numItems - 1]
            self.numItems -= 1
        else:
            Result = None
        return Result
