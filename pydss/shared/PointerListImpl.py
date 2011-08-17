from dss.shared.PointerList import (PointerList,)


class PointerListImpl(object, PointerList):
    numInList = None
    maxAllocated = None
    activeItem = None
    list = None
    incrementSize = None

    def __init__(self, size):
        super(PointerListImpl, self)()
        self.maxAllocated = size
        # default size & increment
        if self.maxAllocated <= 0:
            self.maxAllocated = 10
        self.list = [None] * self.maxAllocated
        self.numInList = 0
        self.activeItem = 0
        # increment is equal to original allocation
        self.incrementSize = self.maxAllocated

    def getFirst(self):
        if self.numInList > 0:
            self.activeItem = 0
            return self.list[self.activeItem]
        else:
            self.activeItem = -1
            return None

    def getNext(self):
        if self.numInList > 0:
            self.activeItem += 1
            if self.activeItem > self.numInList:
                self.activeItem = self.numInList
                return None
            else:
                return self.list[self.activeItem]
        else:
            self.activeItem = -1
            return None

    def getActive(self):
        if self.activeItem > 0 and self.activeItem <= self.numInList:
            return self.get(self.activeItem)
        else:
            return None

    def setNew(self, value):
        self.add(value)

    def clear(self):
        self.activeItem = -1
        self.numInList = 0

    def add(self, p):
        """Returns index of item"""
        self.numInList += 1
        if self.numInList > self.maxAllocated:
            self.maxAllocated = self.maxAllocated + self.incrementSize
            # FIXME: Resize array
            self.list = [None] * self.maxAllocated
        self.list[self.numInList] = p
        self.activeItem = self.numInList
        return self.numInList

    def get(self, i):
        if (i < 1) or (i > self.numInList):
            return None
        else:
            self.activeItem = i
            return self.list[i]

    def size(self):
        return self.numInList

    def getActiveIndex(self):
        return self.activeItem
