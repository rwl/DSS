from dss.general.NamedObject import (NamedObject,)
# from java.util.UUID import (UUID,)


class NamedObjectImpl(object, NamedObject):
    pathName = None
    localName = None
    displayName = None
    uuid = None

    def __init__(self, className):
        super(NamedObjectImpl, self)()
        self.pathName = className
        self.localName = ''
        self.displayName = ''
        self.uuid = None

    def finalize(self):
        if self.uuid is not None:
            self.uuid = None

    def getDisplayName(self):
        if self.displayName == '':
            return self.pathName + '_' + self.localName
        else:
            return self.displayName

    def setDisplayName(self, value):
        self.displayName = value

    def getQualifiedName(self):
        return self.pathName + '.' + self.localName

    def getUUID(self):
        if self.uuid is None:
            self.uuid = UUID.randomUUID()
        return self.uuid

    def setUUID(self, value):
        # if (pGuid == null) {}
        self.uuid = value

    def getID(self):
        return str(self.getUUID())

    def getCIM_ID(self):
        return self.UUIDToCIMString(self.getUUID())

    def getDSSClassName(self):
        return self.pathName

    def setDSSClassName(self, value):
        self.pathName = value

    def getLocalName(self):
        return self.localName

    def setLocalName(self, value):
        self.localName = value

    def UUIDToCIMString(self, uUID):
        s = str(uUID)
        return s[1:-2]
