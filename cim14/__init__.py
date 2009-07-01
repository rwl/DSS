# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.


# <<< imports
# @generated
# >>> imports

ns_prefix = "cim"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#"

class Element(object):
 
    uri = ''

    def get_model(self):
        """ 
        """
        return self._model

    def set_model(self, value):
        if self._model is not None:
            filtered = [x for x in self.model.elements if x != self]
            self._model._elements = filtered
            
        self._model = value
        if self._model is not None:
            self._model._elements.append(self)

    model = property(get_model, set_model)

    # <<< element
    # @generated
    def __init__(self, uri='', model=None, **kw_args):
        """ Initialises a new 'Element' instance.
        """
        self.uri = uri
        self._model = None
        self.model = model

        super(Element, self).__init__(**kw_args)
    # >>> element


class Model(object):
    def get_elements(self):
        """ 
        """
        return self._elements

    def set_elements(self, value):
        for x in self._elements:
            x._model = None
        for y in value:
            y._model = self
        self._elements = value
            
    elements = property(get_elements, set_elements)
    
    def add_elements(self, *elements):
        for obj in elements:
            obj._model = self
            self._elements.append(obj)
        
    def remove_elements(self, *elements):
        for obj in elements:
            obj._model = None
            self._elements.remove(obj)

    # <<< model
    # @generated
    def __init__(self, elements=[], **kw_args):
        """ Initialises a new 'Model' instance.
        """
        self._elements = []
        self.elements = elements

        super(Model, self).__init__(**kw_args)
    # >>> model


class CombinedVersion(Element):
    """ The combined version denotes the versions of the subpackages that have been combined into the total CIIMmodel. This is a convenience instead of having to look at each subpackage.
    """
    # Form is IEC61970CIMXXvYY_IEC61968CIMXXvYY_combined where XX is the major CIM package version and the YY is the minor version, and different packages could have different major and minor versions.   For example IEC61970CIM13v18_IEC61968CIM10v16_combined.  Additional packages might be added in the future. 
    version = ''

    # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
    date = ''

    # <<< combined_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'CombinedVersion' instance.
        """
        self.version = version
        self.date = date

        super(CombinedVersion, self).__init__(**kw_args)
    # >>> combined_version


# <<< cim14
# @generated
# >>> cim14
