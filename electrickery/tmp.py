class A(object):

    def __init__(self, bs):
        self._bs = []
        self.bs = bs

    def get_bs(self):
        """ Property 'bs'.
        """
        print "GETTING PROPERTY 'bs'"
        return self._bs

    def set_bs(self, value):
        print "SETTING PROPERTY 'bs'"
        for b in self._bs:
            b._a = None
        for b in value:
            b._a = self

    bs = property(get_bs, set_bs)

    def add_b(self, *bs):
        for b in bs:
            print "ADDING B"
            b._a = self
            self._bs.append(b)

    def del_b(self, *bs):
        for b in bs:
            b._a = None
            self._bs.remove(b)


class B(object):

    def __init__(self, a, c):
        self._a = None
        self.a = a
        self._c = None
        self.c = c

    def get_a(self):
        """ Property 'a'.
        """
        return self._a

    def set_a(self, value):
        if self._a is not None:
            # Remove the old object.
            self._a.bs = [b for b in self._a.bs if b != self]

        self._a = value
        if self._a is not None:
            self._a.bs.append(self)

    a = property(get_a, set_a)

    def get_c(self):
        """ Property 'c'.
        """
        return self._c

    def set_c(self, value):
        self._c = value
        if self._c is not None:
            self._c.b = self

    c = property(get_c, set_c)


class C(object):
    b = None

    def __init__(self, b):
        self._b = None
        self.b = b

    def get_b(self):
        """ Property 'b'.
        """
        return self._b

    def set_b(self, value):
        if self._b is not None:
            self._b._c = None

        self._b = value
        if self._b is not None:
            self._b._c = self

    b = property(get_b, set_b)

if __name__ == "__main__":
    a = A([])
    b1 = B(None, None)
    
#    a.bs.append(b1)

    b2 = B(None, None)
    a.add_b(b1, b2)

#    c1 = C(b1)

#    a.del_b(b2)

#    print a.bs
#    print b2.a
#    print b1.c
