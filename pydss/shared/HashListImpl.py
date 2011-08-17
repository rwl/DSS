from dss.shared.HashList import (HashList,)
from dss.common.impl.Utilities import (Utilities,)
# from java.io.File import (File,)
# from java.io.FileNotFoundException import (FileNotFoundException,)
# from java.io.PrintWriter import (PrintWriter,)


class HashListImpl(object, HashList):

    class SubList(object):
        nElem = None
        nAllocated = None
        str = None
        idx = None

    numElementsAllocated = None
    numLists = None
    numElements = None
    stringArray = None
    listArray = None
    allocationInc = None
    lastFind = None
    lastHash = None
    lastSearchString = None
    initialAllocation = None

    def __init__(self, nelements):
        super(HashListImpl, self)()
        self.numElements = 0
        self.initialAllocation = nelements
        self.stringArray = [None] * self.numElements
        self.numLists = self.Math.round(self.Math.sqrt(nelements))
        ElementsPerList = (nelements / self.numLists) + 1
        self.allocationInc = ElementsPerList
        if self.numLists < 1:
            self.numLists = 1
            # make sure at least one list
        self.listArray = [None] * self.numLists
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numLists):
                break
            self.listArray[i] = self.SubList()
            # Allocate initial sublists to zero; allocated on demand
            self.listArray[i].str = [None] * 0
            self.listArray[i].idx = [None] * 0
            self.listArray[i].nAllocated = 0
            self.listArray[i].nElem = 0
        self.numElementsAllocated = 0
        self.lastFind = 0
        self.lastHash = 0
        self.lastSearchString = ''

    def getInitialAllocation(self):
        return self.initialAllocation

    def setInitialAllocation(self, allocation):
        self.initialAllocation = allocation

    def listSize(self):
        return self.numElements

    def resizeSubList(self, subList):
        # resize by reasonable amount
        oldAllocation = subList.nAllocated
        subList.nAllocated = oldAllocation + self.allocationInc
        subList.str = Utilities.resizeArray(subList.str, subList.nAllocated)
        subList.idx = Utilities.resizeArray(subList.idx, subList.nAllocated)

    def hash(self, s):
        # int HashValue = 0;//S.hashCode();
        # for (int i = 0; i < S.length(); i++) {
        # HashValue = ((HashValue << 5) | (HashValue >> 27)) ^ S.charAt(i);
        # }
        HashValue = s.hashCode()
        return self.Math.abs(HashValue % self.numLists)
        # FIXME: negative modulus

    def resizeStrArray(self):
        """Makes the linear string list larger."""
        self.numElementsAllocated += self.allocationInc * self.numLists
        self.stringArray = Utilities.resizeArray(self.stringArray, self.numElementsAllocated)

    def add(self, s):
        ss = s.toLowerCase()
        hashNum = self.hash(ss)
        self.numElements += 1
        if self.numElements > self.numElementsAllocated:
            self.resizeStrArray()
        self.listArray[hashNum].nElem += 1
        if self.listArray[hashNum].nElem > self.listArray[hashNum].nAllocated:
            self.resizeSubList(self.listArray[hashNum])
            # make copy of whole string, lower case
        self.listArray[hashNum].str[self.listArray[hashNum].nElem - 1] = ss
        # increments count to string
        self.stringArray[self.numElements - 1] = ss
        self.listArray[hashNum].idx[self.listArray[hashNum].nElem - 1] = self.numElements - 1
        return self.numElements

    def find(self, s):
        """Repeat find for duplicate string in same hash list."""
        self.lastSearchString = s.toLowerCase()
        self.lastHash = self.hash(self.lastSearchString)
        result = -1
        self.lastFind = -1
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.listArray[self.lastHash].nElem):
                break
            if (
                self.lastSearchString.equalsIgnoreCase(self.listArray[self.lastHash].str[i])
            ):
                result = self.listArray[self.lastHash].idx[i]
                self.lastFind = i
                break
        return result

    def findNext(self):
        """Begins search in same list as last."""
        # TODO: Check zero indexing.
        result = -1
        # default return
        self.lastFind += 1
        # start with next item in hash list
        if self.lastHash > 0 and self.lastHash <= self.numLists:
            _0 = True
            i = self.lastFind
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.listArray[self.lastHash].nElem):
                    break
                if (
                    self.lastSearchString.equalsIgnoreCase(self.listArray[self.lastHash].str[i])
                ):
                    result = self.listArray[self.lastHash].idx[i]
                    self.lastFind = i
                    break
        return result

    def findAbbrev(self, s):
        """Makes a linear search and tests each string until a string is found
        that matches all the characters entered in s.
        """
        result = 0
        if len(s) > 0:
            test1 = s.toLowerCase()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numElements):
                    break
                test2 = self.stringArray[i][:len(test1)]
                if test1.equalsIgnoreCase(test2):
                    result = i
                    break
        return result

    def get(self, i):
        # TODO: Check zero indexing
        return self.stringArray[i] if i > 0 and i <= self.numElements else ''

    def expand(self, newSize):
        """Expands number of elements.

        Creates a new set of string lists and copies the old strings
        into the new, hashing for the new number of lists.
        """
        # int OldNumLists;
        if newSize > self.numElementsAllocated:
            # OldNumLists = NumLists;
            newStringArray = [None] * newSize
            newNumLists = self.Math.sqrt(newSize)
            elementsPerList = (newSize / newNumLists) + 1
            if newNumLists < 1:
                newNumLists = 1
                # make sure at least one list
            newListArray = [None] * newNumLists
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numLists):
                    break
                # TODO: Check zero indexing.
                # Allocate initial sublists
                newListArray[i].str = [None] * elementsPerList
                newListArray[i].idx = [None] * elementsPerList
                newListArray[i].nAllocated = elementsPerList
                newListArray[i].nElem = 0
            self.numLists = newNumLists
            # has to be set so hash function will work
            # Add elements from old hash list to new hash list
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.numElements):
                    break
                # TODO: Check zero indexing
                s = self.stringArray[i]
                hashNum = self.hash(s)
                newListArray[hashNum].nElem += 1
                if newListArray[hashNum].nElem > newListArray[hashNum].nAllocated:
                    self.resizeSubList(newListArray[hashNum])
                newListArray[hashNum].str[newListArray[hashNum].nElem] = s
                newStringArray[self.numElements] = newListArray[hashNum].str[newListArray[hashNum].nElem]
                newListArray[hashNum].idx[newListArray[hashNum].nElem] = i
            # Assign new string and list pointers
            self.stringArray = newStringArray
            self.listArray = newListArray
            self.numElementsAllocated = newSize

    def dumpToFile(self, fname):
        f = File(fname)
        # TODO Auto-generated catch block
        try:
            pw = PrintWriter(f)
            print String.format.format('Number of Hash Lists = %d, Number of Elements = %d', self.numLists, self.numElements)
            print 
            print 'Hash List Distribution'
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numLists):
                    break
                print String.format.format('List = %d, Number of elements = %d', i, self.listArray[i].nElem)
            print 
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.numLists):
                    break
                print String.format.format('List = %d, Number of elements = %d', i, self.listArray[i].nElem)
                _2 = True
                j = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j += 1
                    if not (j < self.listArray[i].nElem):
                        break
                    print '\"' + self.listArray[i].str[j] + '\"  Idx= ' + self.listArray[i].idx[j]
                print 
            print 'LINEAR LISTING...'
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < self.numElements):
                    break
                print i + ' = \"' + self.stringArray[i] + '\"'
            pw.close()
        except FileNotFoundException, e:
            pass # astStmt: [Stmt([]), None]

    def clear(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numLists):
                break
            self.listArray[i].nElem = 0
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.listArray[i].nAllocated):
                    break
                self.listArray[i].str[j] = ''
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.numElementsAllocated):
                break
            self.stringArray[i] = ''
        self.numElements = 0
