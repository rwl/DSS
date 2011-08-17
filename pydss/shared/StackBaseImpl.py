from dss.shared.StackBase import (StackBase,)


class StackBaseImpl(object, StackBase):
    numItems = None
    increment = None
    maxItems = None

    def __init__(self, initSize):
        super(StackBaseImpl, self)()
        self.maxItems = initSize
        self.increment = initSize
        self.numItems = 0

    def clear(self):
        self.numItems = 0

    def size(self):
        return self.numItems
