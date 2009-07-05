# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Location
from cim14.iec61968.common import PositionPoint
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfGMLSupport"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfGMLSupport"

class GmlSvgParameter(IdentifiedObject):
    """ Refers to an SVG/CSS graphical-formatting parameter. The parameter is identified using the 'name' attribute and the content of the element gives the SVG/CSS-coded value.
    """
    # <<< gml_svg_parameter
    # @generated
    def __init__(self, attribute='', value='', gml_stokes=None, gml_fonts=None, gml_fills=None, **kw_args):
        """ Initialises a new 'GmlSvgParameter' instance.
        """
        # The attribute of the GmlSvgParameter. E.g., for 'Stroke', the following SvgParameters may be used: 'stroke' (color), 'stroke-opacity', 'stroke-width', 'stroke-linejoin', 'stroke-linecap', 'stroke-dasharray', and 'stroke-dashoffset'. Others are not officially supported. 
        self.attribute = attribute
        # The SVG/CSS-coded value of the associated SvgAttribute. 
        self.value = value
        
        self._gml_stokes = []
        if gml_stokes is None:
            self.gml_stokes = []
        else:
            self.gml_stokes = gml_stokes
        self._gml_fonts = []
        if gml_fonts is None:
            self.gml_fonts = []
        else:
            self.gml_fonts = gml_fonts
        self._gml_fills = []
        if gml_fills is None:
            self.gml_fills = []
        else:
            self.gml_fills = gml_fills

        super(GmlSvgParameter, self).__init__(**kw_args)
    # >>> gml_svg_parameter
        
    # <<< gml_stokes
    # @generated
    def get_gml_stokes(self):
        """ 
        """
        return self._gml_stokes

    def set_gml_stokes(self, value):
        for p in self._gml_stokes:
            filtered = [q for q in p.gml_svg_parameters if q != self]
            self._gml_stokes._gml_svg_parameters = filtered
        for r in value:
            if self not in r._gml_svg_parameters:
                r._gml_svg_parameters.append(self)
        self._gml_stokes = value
            
    gml_stokes = property(get_gml_stokes, set_gml_stokes)
    
    def add_gml_stokes(self, *gml_stokes):
        for obj in gml_stokes:
            if self not in obj._gml_svg_parameters:
                obj._gml_svg_parameters.append(self)
            self._gml_stokes.append(obj)
        
    def remove_gml_stokes(self, *gml_stokes):
        for obj in gml_stokes:
            if self in obj._gml_svg_parameters:
                obj._gml_svg_parameters.remove(self)
            self._gml_stokes.remove(obj)
    # >>> gml_stokes

    # <<< gml_fonts
    # @generated
    def get_gml_fonts(self):
        """ 
        """
        return self._gml_fonts

    def set_gml_fonts(self, value):
        for p in self._gml_fonts:
            filtered = [q for q in p.gml_svg_parameters if q != self]
            self._gml_fonts._gml_svg_parameters = filtered
        for r in value:
            if self not in r._gml_svg_parameters:
                r._gml_svg_parameters.append(self)
        self._gml_fonts = value
            
    gml_fonts = property(get_gml_fonts, set_gml_fonts)
    
    def add_gml_fonts(self, *gml_fonts):
        for obj in gml_fonts:
            if self not in obj._gml_svg_parameters:
                obj._gml_svg_parameters.append(self)
            self._gml_fonts.append(obj)
        
    def remove_gml_fonts(self, *gml_fonts):
        for obj in gml_fonts:
            if self in obj._gml_svg_parameters:
                obj._gml_svg_parameters.remove(self)
            self._gml_fonts.remove(obj)
    # >>> gml_fonts

    # <<< gml_fills
    # @generated
    def get_gml_fills(self):
        """ 
        """
        return self._gml_fills

    def set_gml_fills(self, value):
        for p in self._gml_fills:
            filtered = [q for q in p.gml_svg_parameters if q != self]
            self._gml_fills._gml_svg_parameters = filtered
        for r in value:
            if self not in r._gml_svg_parameters:
                r._gml_svg_parameters.append(self)
        self._gml_fills = value
            
    gml_fills = property(get_gml_fills, set_gml_fills)
    
    def add_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            if self not in obj._gml_svg_parameters:
                obj._gml_svg_parameters.append(self)
            self._gml_fills.append(obj)
        
    def remove_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            if self in obj._gml_svg_parameters:
                obj._gml_svg_parameters.remove(self)
            self._gml_fills.remove(obj)
    # >>> gml_fills



class GmlColour(IdentifiedObject):
    """ The solid color that will be used. The color value is RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign. The hexadecimal digits between A and F may be in either uppercase or lowercase. For example, full red is encoded as '#ff0000' (with no quotation marks). If the Stroke cssParameter element is absent, the default color is defined to be black ('#000000').
    """
    # <<< gml_colour
    # @generated
    def __init__(self, blue='', red='', green='', gml_fills=None, gml_fonts=None, gml_strokes=None, **kw_args):
        """ Initialises a new 'GmlColour' instance.
        """
        # The color value for BLUE (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.). 
        self.blue = blue
        # The color value for RED (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.) 
        self.red = red
        # The color value for GREEN (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.) 
        self.green = green
        
        self._gml_fills = []
        if gml_fills is None:
            self.gml_fills = []
        else:
            self.gml_fills = gml_fills
        self._gml_fonts = []
        if gml_fonts is None:
            self.gml_fonts = []
        else:
            self.gml_fonts = gml_fonts
        self._gml_strokes = []
        if gml_strokes is None:
            self.gml_strokes = []
        else:
            self.gml_strokes = gml_strokes

        super(GmlColour, self).__init__(**kw_args)
    # >>> gml_colour
        
    # <<< gml_fills
    # @generated
    def get_gml_fills(self):
        """ 
        """
        return self._gml_fills

    def set_gml_fills(self, value):
        for x in self._gml_fills:
            x.gml_colour = None
        for y in value:
            y.gml_colour = self
        self._gml_fills = value
            
    gml_fills = property(get_gml_fills, set_gml_fills)
    
    def add_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            obj._gml_colour = self
            if obj not in self._gml_fills:
                self._gml_fills.append(obj)
        
    def remove_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            obj._gml_colour = None
            self._gml_fills.remove(obj)
    # >>> gml_fills

    # <<< gml_fonts
    # @generated
    def get_gml_fonts(self):
        """ 
        """
        return self._gml_fonts

    def set_gml_fonts(self, value):
        for x in self._gml_fonts:
            x.gml_colour = None
        for y in value:
            y.gml_colour = self
        self._gml_fonts = value
            
    gml_fonts = property(get_gml_fonts, set_gml_fonts)
    
    def add_gml_fonts(self, *gml_fonts):
        for obj in gml_fonts:
            obj._gml_colour = self
            if obj not in self._gml_fonts:
                self._gml_fonts.append(obj)
        
    def remove_gml_fonts(self, *gml_fonts):
        for obj in gml_fonts:
            obj._gml_colour = None
            self._gml_fonts.remove(obj)
    # >>> gml_fonts

    # <<< gml_strokes
    # @generated
    def get_gml_strokes(self):
        """ 
        """
        return self._gml_strokes

    def set_gml_strokes(self, value):
        for x in self._gml_strokes:
            x.gml_colour = None
        for y in value:
            y.gml_colour = self
        self._gml_strokes = value
            
    gml_strokes = property(get_gml_strokes, set_gml_strokes)
    
    def add_gml_strokes(self, *gml_strokes):
        for obj in gml_strokes:
            obj._gml_colour = self
            if obj not in self._gml_strokes:
                self._gml_strokes.append(obj)
        
    def remove_gml_strokes(self, *gml_strokes):
        for obj in gml_strokes:
            obj._gml_colour = None
            self._gml_strokes.remove(obj)
    # >>> gml_strokes



class GmlDiagramObject(Location):
    """ Any of the magnitudes that serve to define the position of a point by reference to a fixed figure, system of lines, etc.
    """
    # <<< gml_diagram_object
    # @generated
    def __init__(self, gml_raster_symbols=None, gml_point_symbols=None, diagrams=None, gml_polygon_symbols=None, gml_line_symbols=None, gml_coordinate_systems=None, gml_text_symbols=None, **kw_args):
        """ Initialises a new 'GmlDiagramObject' instance.
        """
        
        self._gml_raster_symbols = []
        if gml_raster_symbols is None:
            self.gml_raster_symbols = []
        else:
            self.gml_raster_symbols = gml_raster_symbols
        self._gml_point_symbols = []
        if gml_point_symbols is None:
            self.gml_point_symbols = []
        else:
            self.gml_point_symbols = gml_point_symbols
        self._diagrams = []
        if diagrams is None:
            self.diagrams = []
        else:
            self.diagrams = diagrams
        self._gml_polygon_symbols = []
        if gml_polygon_symbols is None:
            self.gml_polygon_symbols = []
        else:
            self.gml_polygon_symbols = gml_polygon_symbols
        self._gml_line_symbols = []
        if gml_line_symbols is None:
            self.gml_line_symbols = []
        else:
            self.gml_line_symbols = gml_line_symbols
        self._gml_coordinate_systems = []
        if gml_coordinate_systems is None:
            self.gml_coordinate_systems = []
        else:
            self.gml_coordinate_systems = gml_coordinate_systems
        self._gml_text_symbols = []
        if gml_text_symbols is None:
            self.gml_text_symbols = []
        else:
            self.gml_text_symbols = gml_text_symbols

        super(GmlDiagramObject, self).__init__(**kw_args)
    # >>> gml_diagram_object
        
    # <<< gml_raster_symbols
    # @generated
    def get_gml_raster_symbols(self):
        """ 
        """
        return self._gml_raster_symbols

    def set_gml_raster_symbols(self, value):
        for x in self._gml_raster_symbols:
            x.gml_diagram_object = None
        for y in value:
            y.gml_diagram_object = self
        self._gml_raster_symbols = value
            
    gml_raster_symbols = property(get_gml_raster_symbols, set_gml_raster_symbols)
    
    def add_gml_raster_symbols(self, *gml_raster_symbols):
        for obj in gml_raster_symbols:
            obj._gml_diagram_object = self
            if obj not in self._gml_raster_symbols:
                self._gml_raster_symbols.append(obj)
        
    def remove_gml_raster_symbols(self, *gml_raster_symbols):
        for obj in gml_raster_symbols:
            obj._gml_diagram_object = None
            self._gml_raster_symbols.remove(obj)
    # >>> gml_raster_symbols

    # <<< gml_point_symbols
    # @generated
    def get_gml_point_symbols(self):
        """ 
        """
        return self._gml_point_symbols

    def set_gml_point_symbols(self, value):
        for x in self._gml_point_symbols:
            x.gml_diagram_object = None
        for y in value:
            y.gml_diagram_object = self
        self._gml_point_symbols = value
            
    gml_point_symbols = property(get_gml_point_symbols, set_gml_point_symbols)
    
    def add_gml_point_symbols(self, *gml_point_symbols):
        for obj in gml_point_symbols:
            obj._gml_diagram_object = self
            if obj not in self._gml_point_symbols:
                self._gml_point_symbols.append(obj)
        
    def remove_gml_point_symbols(self, *gml_point_symbols):
        for obj in gml_point_symbols:
            obj._gml_diagram_object = None
            self._gml_point_symbols.remove(obj)
    # >>> gml_point_symbols

    # <<< diagrams
    # @generated
    def get_diagrams(self):
        """ 
        """
        return self._diagrams

    def set_diagrams(self, value):
        for p in self._diagrams:
            filtered = [q for q in p.gml_diagram_objects if q != self]
            self._diagrams._gml_diagram_objects = filtered
        for r in value:
            if self not in r._gml_diagram_objects:
                r._gml_diagram_objects.append(self)
        self._diagrams = value
            
    diagrams = property(get_diagrams, set_diagrams)
    
    def add_diagrams(self, *diagrams):
        for obj in diagrams:
            if self not in obj._gml_diagram_objects:
                obj._gml_diagram_objects.append(self)
            self._diagrams.append(obj)
        
    def remove_diagrams(self, *diagrams):
        for obj in diagrams:
            if self in obj._gml_diagram_objects:
                obj._gml_diagram_objects.remove(self)
            self._diagrams.remove(obj)
    # >>> diagrams

    # <<< gml_polygon_symbols
    # @generated
    def get_gml_polygon_symbols(self):
        """ 
        """
        return self._gml_polygon_symbols

    def set_gml_polygon_symbols(self, value):
        for x in self._gml_polygon_symbols:
            x.gml_diagram_object = None
        for y in value:
            y.gml_diagram_object = self
        self._gml_polygon_symbols = value
            
    gml_polygon_symbols = property(get_gml_polygon_symbols, set_gml_polygon_symbols)
    
    def add_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_diagram_object = self
            if obj not in self._gml_polygon_symbols:
                self._gml_polygon_symbols.append(obj)
        
    def remove_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_diagram_object = None
            self._gml_polygon_symbols.remove(obj)
    # >>> gml_polygon_symbols

    # <<< gml_line_symbols
    # @generated
    def get_gml_line_symbols(self):
        """ 
        """
        return self._gml_line_symbols

    def set_gml_line_symbols(self, value):
        for x in self._gml_line_symbols:
            x.gml_diagram_object = None
        for y in value:
            y.gml_diagram_object = self
        self._gml_line_symbols = value
            
    gml_line_symbols = property(get_gml_line_symbols, set_gml_line_symbols)
    
    def add_gml_line_symbols(self, *gml_line_symbols):
        for obj in gml_line_symbols:
            obj._gml_diagram_object = self
            if obj not in self._gml_line_symbols:
                self._gml_line_symbols.append(obj)
        
    def remove_gml_line_symbols(self, *gml_line_symbols):
        for obj in gml_line_symbols:
            obj._gml_diagram_object = None
            self._gml_line_symbols.remove(obj)
    # >>> gml_line_symbols

    # <<< gml_coordinate_systems
    # @generated
    def get_gml_coordinate_systems(self):
        """ 
        """
        return self._gml_coordinate_systems

    def set_gml_coordinate_systems(self, value):
        for p in self._gml_coordinate_systems:
            filtered = [q for q in p.gml_diagram_objects if q != self]
            self._gml_coordinate_systems._gml_diagram_objects = filtered
        for r in value:
            if self not in r._gml_diagram_objects:
                r._gml_diagram_objects.append(self)
        self._gml_coordinate_systems = value
            
    gml_coordinate_systems = property(get_gml_coordinate_systems, set_gml_coordinate_systems)
    
    def add_gml_coordinate_systems(self, *gml_coordinate_systems):
        for obj in gml_coordinate_systems:
            if self not in obj._gml_diagram_objects:
                obj._gml_diagram_objects.append(self)
            self._gml_coordinate_systems.append(obj)
        
    def remove_gml_coordinate_systems(self, *gml_coordinate_systems):
        for obj in gml_coordinate_systems:
            if self in obj._gml_diagram_objects:
                obj._gml_diagram_objects.remove(self)
            self._gml_coordinate_systems.remove(obj)
    # >>> gml_coordinate_systems

    # <<< gml_text_symbols
    # @generated
    def get_gml_text_symbols(self):
        """ 
        """
        return self._gml_text_symbols

    def set_gml_text_symbols(self, value):
        for x in self._gml_text_symbols:
            x.gml_diagram_object = None
        for y in value:
            y.gml_diagram_object = self
        self._gml_text_symbols = value
            
    gml_text_symbols = property(get_gml_text_symbols, set_gml_text_symbols)
    
    def add_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_diagram_object = self
            if obj not in self._gml_text_symbols:
                self._gml_text_symbols.append(obj)
        
    def remove_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_diagram_object = None
            self._gml_text_symbols.remove(obj)
    # >>> gml_text_symbols



class GmlGraphic(IdentifiedObject):
    """ A 'graphic symbol' with an inherent shape, color(s), and possibly size. A 'graphic' can be very informally defined as 'a little picture' and can be of either a raster or vector-graphic source type.
    """
    # <<< gml_graphic
    # @generated
    def __init__(self, y_scale=0.0, symbol_id='', rotation=0.0, min_size=0, size=0, x_scale=0.0, opacity=0.0, gml_point_symbols=None, gml_marks=None, **kw_args):
        """ Initialises a new 'GmlGraphic' instance.
        """
        # Vertical scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbars). 
        self.y_scale = y_scale
        # The identifier of the symbol, if not derived from the type of CIM object (PSR, Asset, Organisation, Document, etc.) gmlSymbolPlacement is associated with. 
        self.symbol_id = symbol_id
        # Gives the rotation of a graphic in the clockwise direction about its center point in decimal degrees, encoded as a floating-point number. Negative values mean counter-clockwise rotation. The default value is 0.0 (no rotation). Note that there is no connection between source geometry types and rotations; the point used for plotting has no inherent direction. Also, the point within the graphic about which it is rotated is format dependent. If a format does not include an inherent rotation point, then the point of rotation should be the centroid. 
        self.rotation = rotation
        # The minimum symbol size allowed. 
        self.min_size = min_size
        # Gives the absolute size of the graphic in pixels encoded as a floatingpoint number. The default size for an object is context-dependent. Negative values are not allowed. 
        self.size = size
        # Horizontal scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbar). 
        self.x_scale = x_scale
        # Specifies the level of translucency to use when rendering the Graphic.The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
        self.opacity = opacity
        
        self._gml_point_symbols = []
        if gml_point_symbols is None:
            self.gml_point_symbols = []
        else:
            self.gml_point_symbols = gml_point_symbols
        self._gml_marks = []
        if gml_marks is None:
            self.gml_marks = []
        else:
            self.gml_marks = gml_marks

        super(GmlGraphic, self).__init__(**kw_args)
    # >>> gml_graphic
        
    # <<< gml_point_symbols
    # @generated
    def get_gml_point_symbols(self):
        """ 
        """
        return self._gml_point_symbols

    def set_gml_point_symbols(self, value):
        for x in self._gml_point_symbols:
            x.gml_graphic = None
        for y in value:
            y.gml_graphic = self
        self._gml_point_symbols = value
            
    gml_point_symbols = property(get_gml_point_symbols, set_gml_point_symbols)
    
    def add_gml_point_symbols(self, *gml_point_symbols):
        for obj in gml_point_symbols:
            obj._gml_graphic = self
            if obj not in self._gml_point_symbols:
                self._gml_point_symbols.append(obj)
        
    def remove_gml_point_symbols(self, *gml_point_symbols):
        for obj in gml_point_symbols:
            obj._gml_graphic = None
            self._gml_point_symbols.remove(obj)
    # >>> gml_point_symbols

    # <<< gml_marks
    # @generated
    def get_gml_marks(self):
        """ 
        """
        return self._gml_marks

    def set_gml_marks(self, value):
        for p in self._gml_marks:
            filtered = [q for q in p.gml_graphics if q != self]
            self._gml_marks._gml_graphics = filtered
        for r in value:
            if self not in r._gml_graphics:
                r._gml_graphics.append(self)
        self._gml_marks = value
            
    gml_marks = property(get_gml_marks, set_gml_marks)
    
    def add_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self not in obj._gml_graphics:
                obj._gml_graphics.append(self)
            self._gml_marks.append(obj)
        
    def remove_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self in obj._gml_graphics:
                obj._gml_graphics.remove(self)
            self._gml_marks.remove(obj)
    # >>> gml_marks



class GmlFill(IdentifiedObject):
    """ Specifies how the area of the geometry will be filled.
    """
    # <<< gml_fill
    # @generated
    def __init__(self, opacity=0.0, gml_colour=None, gml_polygon_symbols=None, gml_marks=None, gml_text_symbols=None, gml_svg_parameters=None, **kw_args):
        """ Initialises a new 'GmlFill' instance.
        """
        # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
        self.opacity = opacity
        
        self._gml_colour = None
        self.gml_colour = gml_colour
        self._gml_polygon_symbols = []
        if gml_polygon_symbols is None:
            self.gml_polygon_symbols = []
        else:
            self.gml_polygon_symbols = gml_polygon_symbols
        self._gml_marks = []
        if gml_marks is None:
            self.gml_marks = []
        else:
            self.gml_marks = gml_marks
        self._gml_text_symbols = []
        if gml_text_symbols is None:
            self.gml_text_symbols = []
        else:
            self.gml_text_symbols = gml_text_symbols
        self._gml_svg_parameters = []
        if gml_svg_parameters is None:
            self.gml_svg_parameters = []
        else:
            self.gml_svg_parameters = gml_svg_parameters

        super(GmlFill, self).__init__(**kw_args)
    # >>> gml_fill
        
    # <<< gml_colour
    # @generated
    def get_gml_colour(self):
        """ 
        """
        return self._gml_colour

    def set_gml_colour(self, value):
        if self._gml_colour is not None:
            filtered = [x for x in self.gml_colour.gml_fills if x != self]
            self._gml_colour._gml_fills = filtered
            
        self._gml_colour = value
        if self._gml_colour is not None:
            if self not in self._gml_colour._gml_fills:
                self._gml_colour._gml_fills.append(self)

    gml_colour = property(get_gml_colour, set_gml_colour)
    # >>> gml_colour

    # <<< gml_polygon_symbols
    # @generated
    def get_gml_polygon_symbols(self):
        """ 
        """
        return self._gml_polygon_symbols

    def set_gml_polygon_symbols(self, value):
        for x in self._gml_polygon_symbols:
            x.gml_fill = None
        for y in value:
            y.gml_fill = self
        self._gml_polygon_symbols = value
            
    gml_polygon_symbols = property(get_gml_polygon_symbols, set_gml_polygon_symbols)
    
    def add_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_fill = self
            if obj not in self._gml_polygon_symbols:
                self._gml_polygon_symbols.append(obj)
        
    def remove_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_fill = None
            self._gml_polygon_symbols.remove(obj)
    # >>> gml_polygon_symbols

    # <<< gml_marks
    # @generated
    def get_gml_marks(self):
        """ 
        """
        return self._gml_marks

    def set_gml_marks(self, value):
        for p in self._gml_marks:
            filtered = [q for q in p.gml_fills if q != self]
            self._gml_marks._gml_fills = filtered
        for r in value:
            if self not in r._gml_fills:
                r._gml_fills.append(self)
        self._gml_marks = value
            
    gml_marks = property(get_gml_marks, set_gml_marks)
    
    def add_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self not in obj._gml_fills:
                obj._gml_fills.append(self)
            self._gml_marks.append(obj)
        
    def remove_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self in obj._gml_fills:
                obj._gml_fills.remove(self)
            self._gml_marks.remove(obj)
    # >>> gml_marks

    # <<< gml_text_symbols
    # @generated
    def get_gml_text_symbols(self):
        """ 
        """
        return self._gml_text_symbols

    def set_gml_text_symbols(self, value):
        for x in self._gml_text_symbols:
            x.gml_fill = None
        for y in value:
            y.gml_fill = self
        self._gml_text_symbols = value
            
    gml_text_symbols = property(get_gml_text_symbols, set_gml_text_symbols)
    
    def add_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_fill = self
            if obj not in self._gml_text_symbols:
                self._gml_text_symbols.append(obj)
        
    def remove_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_fill = None
            self._gml_text_symbols.remove(obj)
    # >>> gml_text_symbols

    # <<< gml_svg_parameters
    # @generated
    def get_gml_svg_parameters(self):
        """ 
        """
        return self._gml_svg_parameters

    def set_gml_svg_parameters(self, value):
        for p in self._gml_svg_parameters:
            filtered = [q for q in p.gml_fills if q != self]
            self._gml_svg_parameters._gml_fills = filtered
        for r in value:
            if self not in r._gml_fills:
                r._gml_fills.append(self)
        self._gml_svg_parameters = value
            
    gml_svg_parameters = property(get_gml_svg_parameters, set_gml_svg_parameters)
    
    def add_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self not in obj._gml_fills:
                obj._gml_fills.append(self)
            self._gml_svg_parameters.append(obj)
        
    def remove_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self in obj._gml_fills:
                obj._gml_fills.remove(self)
            self._gml_svg_parameters.remove(obj)
    # >>> gml_svg_parameters



class GmlSelector(IdentifiedObject):
    """ A diagram element that allows selection by a user, i.e. as 'hyperNode' for navigating between diagrams, or as composite object representing multiple grouped objects.
    """
    # <<< gml_selector
    # @generated
    def __init__(self, locations=None, change_items=None, **kw_args):
        """ Initialises a new 'GmlSelector' instance.
        """
        
        self._locations = []
        if locations is None:
            self.locations = []
        else:
            self.locations = locations
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items

        super(GmlSelector, self).__init__(**kw_args)
    # >>> gml_selector
        
    # <<< locations
    # @generated
    def get_locations(self):
        """ 
        """
        return self._locations

    def set_locations(self, value):
        for p in self._locations:
            filtered = [q for q in p.gml_selectors if q != self]
            self._locations._gml_selectors = filtered
        for r in value:
            if self not in r._gml_selectors:
                r._gml_selectors.append(self)
        self._locations = value
            
    locations = property(get_locations, set_locations)
    
    def add_locations(self, *locations):
        for obj in locations:
            if self not in obj._gml_selectors:
                obj._gml_selectors.append(self)
            self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
            if self in obj._gml_selectors:
                obj._gml_selectors.remove(self)
            self._locations.remove(obj)
    # >>> locations

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.gml_selector = None
        for y in value:
            y.gml_selector = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._gml_selector = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._gml_selector = None
            self._change_items.remove(obj)
    # >>> change_items



class GmlLabelPlacement(IdentifiedObject):
    """ Used to position a label relative to a point or a line.
    """
    # <<< gml_label_placement
    # @generated
    def __init__(self, type='', offset='', displacement_y='', rotation='', anchor_y='', displacement_x='', anchor_x='', gml_text_symbols=None, **kw_args):
        """ Initialises a new 'GmlLabelPlacement' instance.
        """
        # Type of 'LabelPlacement' which in turn specifies where and how a text label should be rendered relative to a geometry. 
        self.type = type
        # Perpendicular distance away from a line to draw a label. The distance is in pixels and is positive to the left-hand side of the line string. Negative numbers mean right. The default offset is 0. 
        self.offset = offset
        # Y displacement from the main-geometry point to render a text label. 
        self.displacement_y = displacement_y
        # Clockwise rotation of the label in degrees from the normal direction for a font. 
        self.rotation = rotation
        # Y-coordinate location inside of a label to use for anchoring the label to the main-geometry point. 
        self.anchor_y = anchor_y
        # X displacement from the main-geometry point to render a text label. 
        self.displacement_x = displacement_x
        # X-coordinate location inside of a label to use for anchoring the label to the main-geometry point. 
        self.anchor_x = anchor_x
        
        self._gml_text_symbols = []
        if gml_text_symbols is None:
            self.gml_text_symbols = []
        else:
            self.gml_text_symbols = gml_text_symbols

        super(GmlLabelPlacement, self).__init__(**kw_args)
    # >>> gml_label_placement
        
    # <<< gml_text_symbols
    # @generated
    def get_gml_text_symbols(self):
        """ 
        """
        return self._gml_text_symbols

    def set_gml_text_symbols(self, value):
        for x in self._gml_text_symbols:
            x.gml_label_placement = None
        for y in value:
            y.gml_label_placement = self
        self._gml_text_symbols = value
            
    gml_text_symbols = property(get_gml_text_symbols, set_gml_text_symbols)
    
    def add_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_label_placement = self
            if obj not in self._gml_text_symbols:
                self._gml_text_symbols.append(obj)
        
    def remove_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_label_placement = None
            self._gml_text_symbols.remove(obj)
    # >>> gml_text_symbols



class GmlSymbol(IdentifiedObject):
    """ Describes how a feature is to appear on a map or display. The symbol describes not just the shape that should appear but also such graphical properties as color and opacity.
    """
    # <<< gml_symbol
    # @generated
    def __init__(self, level='', version='', type='', gml_feature_styles=None, gml_base_symbol=None, **kw_args):
        """ Initialises a new 'GmlSymbol' instance.
        """
        # The level (of the map) where the symbol exists or the zoom levels at which this diagram object is displayed. As a way of de-cluttering displays, for example, some symbols and annotations are only shown when zoomed in. 
        self.level = level
        # The version of the Symbol. 
        self.version = version
        # The Symbol type. 
        self.type = type
        
        self._gml_feature_styles = []
        if gml_feature_styles is None:
            self.gml_feature_styles = []
        else:
            self.gml_feature_styles = gml_feature_styles
        self._gml_base_symbol = None
        self.gml_base_symbol = gml_base_symbol

        super(GmlSymbol, self).__init__(**kw_args)
    # >>> gml_symbol
        
    # <<< gml_feature_styles
    # @generated
    def get_gml_feature_styles(self):
        """ 
        """
        return self._gml_feature_styles

    def set_gml_feature_styles(self, value):
        for p in self._gml_feature_styles:
            filtered = [q for q in p.gml_symbols if q != self]
            self._gml_feature_styles._gml_symbols = filtered
        for r in value:
            if self not in r._gml_symbols:
                r._gml_symbols.append(self)
        self._gml_feature_styles = value
            
    gml_feature_styles = property(get_gml_feature_styles, set_gml_feature_styles)
    
    def add_gml_feature_styles(self, *gml_feature_styles):
        for obj in gml_feature_styles:
            if self not in obj._gml_symbols:
                obj._gml_symbols.append(self)
            self._gml_feature_styles.append(obj)
        
    def remove_gml_feature_styles(self, *gml_feature_styles):
        for obj in gml_feature_styles:
            if self in obj._gml_symbols:
                obj._gml_symbols.remove(self)
            self._gml_feature_styles.remove(obj)
    # >>> gml_feature_styles

    # <<< gml_base_symbol
    # @generated
    def get_gml_base_symbol(self):
        """ 
        """
        return self._gml_base_symbol

    def set_gml_base_symbol(self, value):
        if self._gml_base_symbol is not None:
            filtered = [x for x in self.gml_base_symbol.gml_symbols if x != self]
            self._gml_base_symbol._gml_symbols = filtered
            
        self._gml_base_symbol = value
        if self._gml_base_symbol is not None:
            if self not in self._gml_base_symbol._gml_symbols:
                self._gml_base_symbol._gml_symbols.append(self)

    gml_base_symbol = property(get_gml_base_symbol, set_gml_base_symbol)
    # >>> gml_base_symbol



class GmlValue(IdentifiedObject):
    """ Used for direct representation of values.
    """
    # <<< gml_value
    # @generated
    def __init__(self, value=0.0, date_time='', time_period='', measurement_value=None, gml_observation=None, **kw_args):
        """ Initialises a new 'GmlValue' instance.
        """
 
        self.value = value
 
        self.date_time = date_time
 
        self.time_period = time_period
        
        self._measurement_value = None
        self.measurement_value = measurement_value
        self._gml_observation = None
        self.gml_observation = gml_observation

        super(GmlValue, self).__init__(**kw_args)
    # >>> gml_value
        
    # <<< measurement_value
    # @generated
    def get_measurement_value(self):
        """ 
        """
        return self._measurement_value

    def set_measurement_value(self, value):
        if self._measurement_value is not None:
            filtered = [x for x in self.measurement_value.gml_values if x != self]
            self._measurement_value._gml_values = filtered
            
        self._measurement_value = value
        if self._measurement_value is not None:
            if self not in self._measurement_value._gml_values:
                self._measurement_value._gml_values.append(self)

    measurement_value = property(get_measurement_value, set_measurement_value)
    # >>> measurement_value

    # <<< gml_observation
    # @generated
    def get_gml_observation(self):
        """ 
        """
        return self._gml_observation

    def set_gml_observation(self, value):
        if self._gml_observation is not None:
            filtered = [x for x in self.gml_observation.gml_values if x != self]
            self._gml_observation._gml_values = filtered
            
        self._gml_observation = value
        if self._gml_observation is not None:
            if self not in self._gml_observation._gml_values:
                self._gml_observation._gml_values.append(self)

    gml_observation = property(get_gml_observation, set_gml_observation)
    # >>> gml_observation



class GmlBaseSymbol(IdentifiedObject):
    """ Allows referencing and extension of external symbols, which may be stored in a symbol repository. The graphical properties from a referenced external symbol override the ones read in from the base symbol.
    """
    # <<< gml_base_symbol
    # @generated
    def __init__(self, gml_symbols=None, **kw_args):
        """ Initialises a new 'GmlBaseSymbol' instance.
        """
        
        self._gml_symbols = []
        if gml_symbols is None:
            self.gml_symbols = []
        else:
            self.gml_symbols = gml_symbols

        super(GmlBaseSymbol, self).__init__(**kw_args)
    # >>> gml_base_symbol
        
    # <<< gml_symbols
    # @generated
    def get_gml_symbols(self):
        """ 
        """
        return self._gml_symbols

    def set_gml_symbols(self, value):
        for x in self._gml_symbols:
            x.gml_base_symbol = None
        for y in value:
            y.gml_base_symbol = self
        self._gml_symbols = value
            
    gml_symbols = property(get_gml_symbols, set_gml_symbols)
    
    def add_gml_symbols(self, *gml_symbols):
        for obj in gml_symbols:
            obj._gml_base_symbol = self
            if obj not in self._gml_symbols:
                self._gml_symbols.append(obj)
        
    def remove_gml_symbols(self, *gml_symbols):
        for obj in gml_symbols:
            obj._gml_base_symbol = None
            self._gml_symbols.remove(obj)
    # >>> gml_symbols



class GmlCoordinateSystem(IdentifiedObject):
    """ A coordinate reference system consists of a set of coordinate system axes that is related to the earth through a datum that defines the size and shape of the earth or some abstract reference system such as those used for rendering schemantic diagrams, internal views of items such as cabinets, vaults, etc. Geometries in GML indicate the coordinate reference system in which their measurements have been made.
    """
    # <<< gml_coordinate_system
    # @generated
    def __init__(self, z_min='', position_unit_name='', y_max='', scale='', y_min='', z_max='', x_min='', x_max='', gml_diagram_objects=None, diagrams=None, gml_positions=None, **kw_args):
        """ Initialises a new 'GmlCoordinateSystem' instance.
        """
        # If applicable, the minimum position allowed along the Z axis of the coordinate reference system. 
        self.z_min = z_min
 
        self.position_unit_name = position_unit_name
        # The maximum position allowed along the Y axis of the coordinate reference system. 
        self.y_max = y_max
 
        self.scale = scale
        # The minimum position allowed along the Y axis of the coordinate reference system. 
        self.y_min = y_min
        # If applicable, the maximum position allowed along the Z axis of the coordinate reference system. 
        self.z_max = z_max
        # The minimum position allowed along the X axis of the coordinate reference system. 
        self.x_min = x_min
        # The maximum position allowed along the X axis of the coordinate reference system. 
        self.x_max = x_max
        
        self._gml_diagram_objects = []
        if gml_diagram_objects is None:
            self.gml_diagram_objects = []
        else:
            self.gml_diagram_objects = gml_diagram_objects
        self._diagrams = []
        if diagrams is None:
            self.diagrams = []
        else:
            self.diagrams = diagrams
        self._gml_positions = []
        if gml_positions is None:
            self.gml_positions = []
        else:
            self.gml_positions = gml_positions

        super(GmlCoordinateSystem, self).__init__(**kw_args)
    # >>> gml_coordinate_system
        
    # <<< gml_diagram_objects
    # @generated
    def get_gml_diagram_objects(self):
        """ 
        """
        return self._gml_diagram_objects

    def set_gml_diagram_objects(self, value):
        for p in self._gml_diagram_objects:
            filtered = [q for q in p.gml_coordinate_systems if q != self]
            self._gml_diagram_objects._gml_coordinate_systems = filtered
        for r in value:
            if self not in r._gml_coordinate_systems:
                r._gml_coordinate_systems.append(self)
        self._gml_diagram_objects = value
            
    gml_diagram_objects = property(get_gml_diagram_objects, set_gml_diagram_objects)
    
    def add_gml_diagram_objects(self, *gml_diagram_objects):
        for obj in gml_diagram_objects:
            if self not in obj._gml_coordinate_systems:
                obj._gml_coordinate_systems.append(self)
            self._gml_diagram_objects.append(obj)
        
    def remove_gml_diagram_objects(self, *gml_diagram_objects):
        for obj in gml_diagram_objects:
            if self in obj._gml_coordinate_systems:
                obj._gml_coordinate_systems.remove(self)
            self._gml_diagram_objects.remove(obj)
    # >>> gml_diagram_objects

    # <<< diagrams
    # @generated
    def get_diagrams(self):
        """ 
        """
        return self._diagrams

    def set_diagrams(self, value):
        for x in self._diagrams:
            x.gml_coordinate_system = None
        for y in value:
            y.gml_coordinate_system = self
        self._diagrams = value
            
    diagrams = property(get_diagrams, set_diagrams)
    
    def add_diagrams(self, *diagrams):
        for obj in diagrams:
            obj._gml_coordinate_system = self
            if obj not in self._diagrams:
                self._diagrams.append(obj)
        
    def remove_diagrams(self, *diagrams):
        for obj in diagrams:
            obj._gml_coordinate_system = None
            self._diagrams.remove(obj)
    # >>> diagrams

    # <<< gml_positions
    # @generated
    def get_gml_positions(self):
        """ 
        """
        return self._gml_positions

    def set_gml_positions(self, value):
        for x in self._gml_positions:
            x.gml_coordinate_system = None
        for y in value:
            y.gml_coordinate_system = self
        self._gml_positions = value
            
    gml_positions = property(get_gml_positions, set_gml_positions)
    
    def add_gml_positions(self, *gml_positions):
        for obj in gml_positions:
            obj._gml_coordinate_system = self
            if obj not in self._gml_positions:
                self._gml_positions.append(obj)
        
    def remove_gml_positions(self, *gml_positions):
        for obj in gml_positions:
            obj._gml_coordinate_system = None
            self._gml_positions.remove(obj)
    # >>> gml_positions



class GmlLabelStyle(IdentifiedObject):
    """ The style for the text that is to be displayed along with the graphical representation of a feature. The content of the label is not necessarily defined in the GML data set. More precisely, the content can be static text specified in the style itself and the text from the GML data set. Label style has two elements: gml:style that specifies the style and gml:label that is used to compose the label content.
    """
    # <<< gml_label_style
    # @generated
    def __init__(self, style='', label_expression='', transform='', gml_topology_styles=None, gml_feature_style=None, gml_geometry_styles=None, **kw_args):
        """ Initialises a new 'GmlLabelStyle' instance.
        """
        # Used to specify the style of the rendered text. The CSS2 styling expressions grammar should be used to express graphic properties. 
        self.style = style
        # Allows both text content and unbounded number of gml:LabelExpression elements. The value of gml:LabelExpression element is an XPath expression that selects the value of some property of the feature. 
        self.label_expression = label_expression
        # Allows us to specify a transformation expression that will be applied to the symbol in the rendering phase. Its type is xsd:string and the value is specified in the SVG specification (transform attribute). 
        self.transform = transform
        
        self._gml_topology_styles = []
        if gml_topology_styles is None:
            self.gml_topology_styles = []
        else:
            self.gml_topology_styles = gml_topology_styles
        self._gml_feature_style = None
        self.gml_feature_style = gml_feature_style
        self._gml_geometry_styles = []
        if gml_geometry_styles is None:
            self.gml_geometry_styles = []
        else:
            self.gml_geometry_styles = gml_geometry_styles

        super(GmlLabelStyle, self).__init__(**kw_args)
    # >>> gml_label_style
        
    # <<< gml_topology_styles
    # @generated
    def get_gml_topology_styles(self):
        """ 
        """
        return self._gml_topology_styles

    def set_gml_topology_styles(self, value):
        for x in self._gml_topology_styles:
            x.gml_lable_style = None
        for y in value:
            y.gml_lable_style = self
        self._gml_topology_styles = value
            
    gml_topology_styles = property(get_gml_topology_styles, set_gml_topology_styles)
    
    def add_gml_topology_styles(self, *gml_topology_styles):
        for obj in gml_topology_styles:
            obj._gml_lable_style = self
            if obj not in self._gml_topology_styles:
                self._gml_topology_styles.append(obj)
        
    def remove_gml_topology_styles(self, *gml_topology_styles):
        for obj in gml_topology_styles:
            obj._gml_lable_style = None
            self._gml_topology_styles.remove(obj)
    # >>> gml_topology_styles

    # <<< gml_feature_style
    # @generated
    def get_gml_feature_style(self):
        """ 
        """
        return self._gml_feature_style

    def set_gml_feature_style(self, value):
        if self._gml_feature_style is not None:
            filtered = [x for x in self.gml_feature_style.gml_label_styles if x != self]
            self._gml_feature_style._gml_label_styles = filtered
            
        self._gml_feature_style = value
        if self._gml_feature_style is not None:
            if self not in self._gml_feature_style._gml_label_styles:
                self._gml_feature_style._gml_label_styles.append(self)

    gml_feature_style = property(get_gml_feature_style, set_gml_feature_style)
    # >>> gml_feature_style

    # <<< gml_geometry_styles
    # @generated
    def get_gml_geometry_styles(self):
        """ 
        """
        return self._gml_geometry_styles

    def set_gml_geometry_styles(self, value):
        for x in self._gml_geometry_styles:
            x.gml_label_style = None
        for y in value:
            y.gml_label_style = self
        self._gml_geometry_styles = value
            
    gml_geometry_styles = property(get_gml_geometry_styles, set_gml_geometry_styles)
    
    def add_gml_geometry_styles(self, *gml_geometry_styles):
        for obj in gml_geometry_styles:
            obj._gml_label_style = self
            if obj not in self._gml_geometry_styles:
                self._gml_geometry_styles.append(obj)
        
    def remove_gml_geometry_styles(self, *gml_geometry_styles):
        for obj in gml_geometry_styles:
            obj._gml_label_style = None
            self._gml_geometry_styles.remove(obj)
    # >>> gml_geometry_styles



class GmlMark(IdentifiedObject):
    """ Defines a 'shape' which has coloring applied to it (i.e. square, circle, triangle, star, ...).
    """
    # <<< gml_mark
    # @generated
    def __init__(self, well_known_name='', gml_graphics=None, gml_strokes=None, gml_fills=None, **kw_args):
        """ Initialises a new 'GmlMark' instance.
        """
        # Gives the well-known name of the shape of the mark. Allowed values include at least square, circle, triangle, star, cross, and x. 
        self.well_known_name = well_known_name
        
        self._gml_graphics = []
        if gml_graphics is None:
            self.gml_graphics = []
        else:
            self.gml_graphics = gml_graphics
        self._gml_strokes = []
        if gml_strokes is None:
            self.gml_strokes = []
        else:
            self.gml_strokes = gml_strokes
        self._gml_fills = []
        if gml_fills is None:
            self.gml_fills = []
        else:
            self.gml_fills = gml_fills

        super(GmlMark, self).__init__(**kw_args)
    # >>> gml_mark
        
    # <<< gml_graphics
    # @generated
    def get_gml_graphics(self):
        """ 
        """
        return self._gml_graphics

    def set_gml_graphics(self, value):
        for p in self._gml_graphics:
            filtered = [q for q in p.gml_marks if q != self]
            self._gml_graphics._gml_marks = filtered
        for r in value:
            if self not in r._gml_marks:
                r._gml_marks.append(self)
        self._gml_graphics = value
            
    gml_graphics = property(get_gml_graphics, set_gml_graphics)
    
    def add_gml_graphics(self, *gml_graphics):
        for obj in gml_graphics:
            if self not in obj._gml_marks:
                obj._gml_marks.append(self)
            self._gml_graphics.append(obj)
        
    def remove_gml_graphics(self, *gml_graphics):
        for obj in gml_graphics:
            if self in obj._gml_marks:
                obj._gml_marks.remove(self)
            self._gml_graphics.remove(obj)
    # >>> gml_graphics

    # <<< gml_strokes
    # @generated
    def get_gml_strokes(self):
        """ 
        """
        return self._gml_strokes

    def set_gml_strokes(self, value):
        for p in self._gml_strokes:
            filtered = [q for q in p.gml_marks if q != self]
            self._gml_strokes._gml_marks = filtered
        for r in value:
            if self not in r._gml_marks:
                r._gml_marks.append(self)
        self._gml_strokes = value
            
    gml_strokes = property(get_gml_strokes, set_gml_strokes)
    
    def add_gml_strokes(self, *gml_strokes):
        for obj in gml_strokes:
            if self not in obj._gml_marks:
                obj._gml_marks.append(self)
            self._gml_strokes.append(obj)
        
    def remove_gml_strokes(self, *gml_strokes):
        for obj in gml_strokes:
            if self in obj._gml_marks:
                obj._gml_marks.remove(self)
            self._gml_strokes.remove(obj)
    # >>> gml_strokes

    # <<< gml_fills
    # @generated
    def get_gml_fills(self):
        """ 
        """
        return self._gml_fills

    def set_gml_fills(self, value):
        for p in self._gml_fills:
            filtered = [q for q in p.gml_marks if q != self]
            self._gml_fills._gml_marks = filtered
        for r in value:
            if self not in r._gml_marks:
                r._gml_marks.append(self)
        self._gml_fills = value
            
    gml_fills = property(get_gml_fills, set_gml_fills)
    
    def add_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            if self not in obj._gml_marks:
                obj._gml_marks.append(self)
            self._gml_fills.append(obj)
        
    def remove_gml_fills(self, *gml_fills):
        for obj in gml_fills:
            if self in obj._gml_marks:
                obj._gml_marks.remove(self)
            self._gml_fills.remove(obj)
    # >>> gml_fills



class GmlPosition(PositionPoint):
    """ Position point with a GML coordinate reference system.
    """
    # <<< gml_position
    # @generated
    def __init__(self, gml_coordinate_system=None, **kw_args):
        """ Initialises a new 'GmlPosition' instance.
        """
        
        self._gml_coordinate_system = None
        self.gml_coordinate_system = gml_coordinate_system

        super(GmlPosition, self).__init__(**kw_args)
    # >>> gml_position
        
    # <<< gml_coordinate_system
    # @generated
    def get_gml_coordinate_system(self):
        """ 
        """
        return self._gml_coordinate_system

    def set_gml_coordinate_system(self, value):
        if self._gml_coordinate_system is not None:
            filtered = [x for x in self.gml_coordinate_system.gml_positions if x != self]
            self._gml_coordinate_system._gml_positions = filtered
            
        self._gml_coordinate_system = value
        if self._gml_coordinate_system is not None:
            if self not in self._gml_coordinate_system._gml_positions:
                self._gml_coordinate_system._gml_positions.append(self)

    gml_coordinate_system = property(get_gml_coordinate_system, set_gml_coordinate_system)
    # >>> gml_coordinate_system



class GmlHalo(IdentifiedObject):
    """ A type of Fill that is applied to the backgrounds of font glyphs. The use of halos greatly improves the readability of text labels.
    """
    # <<< gml_halo
    # @generated
    def __init__(self, radius='', opacity=0.0, gml_text_symbols=None, **kw_args):
        """ Initialises a new 'GmlHalo' instance.
        """
        # The Radius element gives the absolute size of a halo radius in pixels encoded as a floating-point number. The radius is taken from the outside edge of a font glyph to extend the area of coverage of the glyph (and the inside edge of ?holes? in the glyphs). The default radius is one pixel. Negative values are not allowed. 
        self.radius = radius
        # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
        self.opacity = opacity
        
        self._gml_text_symbols = []
        if gml_text_symbols is None:
            self.gml_text_symbols = []
        else:
            self.gml_text_symbols = gml_text_symbols

        super(GmlHalo, self).__init__(**kw_args)
    # >>> gml_halo
        
    # <<< gml_text_symbols
    # @generated
    def get_gml_text_symbols(self):
        """ 
        """
        return self._gml_text_symbols

    def set_gml_text_symbols(self, value):
        for x in self._gml_text_symbols:
            x.gml_halo = None
        for y in value:
            y.gml_halo = self
        self._gml_text_symbols = value
            
    gml_text_symbols = property(get_gml_text_symbols, set_gml_text_symbols)
    
    def add_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_halo = self
            if obj not in self._gml_text_symbols:
                self._gml_text_symbols.append(obj)
        
    def remove_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_halo = None
            self._gml_text_symbols.remove(obj)
    # >>> gml_text_symbols



class GmlFont(IdentifiedObject):
    """ Identifies a font of a certain family, style, and size.
    """
    # <<< gml_font
    # @generated
    def __init__(self, absolute_size=False, family='', style='', weight='', size='', gml_colour=None, gml_svg_parameters=None, gml_text_symbols=None, **kw_args):
        """ Initialises a new 'GmlFont' instance.
        """
        # True if 'size' is expressed in absolute values. Default is false. 
        self.absolute_size = absolute_size
        # Family name of a font to use. Allowed values are system-dependent. Any number of font-family attributes may be given and they are assumed to be in preferred order. 
        self.family = family
        # The style to use for a font. The allowed values are 'normal', 'italic', and 'oblique'. 
        self.style = style
        # The amount of weight or boldness to use for a font. Allowed values are 'normal' and 'bold'. 
        self.weight = weight
        # The size to use for the font in pixels. The default is defined to be 10 pixels, though various systems may have restrictions on what sizes are available. 
        self.size = size
        
        self._gml_colour = None
        self.gml_colour = gml_colour
        self._gml_svg_parameters = []
        if gml_svg_parameters is None:
            self.gml_svg_parameters = []
        else:
            self.gml_svg_parameters = gml_svg_parameters
        self._gml_text_symbols = []
        if gml_text_symbols is None:
            self.gml_text_symbols = []
        else:
            self.gml_text_symbols = gml_text_symbols

        super(GmlFont, self).__init__(**kw_args)
    # >>> gml_font
        
    # <<< gml_colour
    # @generated
    def get_gml_colour(self):
        """ 
        """
        return self._gml_colour

    def set_gml_colour(self, value):
        if self._gml_colour is not None:
            filtered = [x for x in self.gml_colour.gml_fonts if x != self]
            self._gml_colour._gml_fonts = filtered
            
        self._gml_colour = value
        if self._gml_colour is not None:
            if self not in self._gml_colour._gml_fonts:
                self._gml_colour._gml_fonts.append(self)

    gml_colour = property(get_gml_colour, set_gml_colour)
    # >>> gml_colour

    # <<< gml_svg_parameters
    # @generated
    def get_gml_svg_parameters(self):
        """ 
        """
        return self._gml_svg_parameters

    def set_gml_svg_parameters(self, value):
        for p in self._gml_svg_parameters:
            filtered = [q for q in p.gml_fonts if q != self]
            self._gml_svg_parameters._gml_fonts = filtered
        for r in value:
            if self not in r._gml_fonts:
                r._gml_fonts.append(self)
        self._gml_svg_parameters = value
            
    gml_svg_parameters = property(get_gml_svg_parameters, set_gml_svg_parameters)
    
    def add_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self not in obj._gml_fonts:
                obj._gml_fonts.append(self)
            self._gml_svg_parameters.append(obj)
        
    def remove_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self in obj._gml_fonts:
                obj._gml_fonts.remove(self)
            self._gml_svg_parameters.remove(obj)
    # >>> gml_svg_parameters

    # <<< gml_text_symbols
    # @generated
    def get_gml_text_symbols(self):
        """ 
        """
        return self._gml_text_symbols

    def set_gml_text_symbols(self, value):
        for x in self._gml_text_symbols:
            x.gml_font = None
        for y in value:
            y.gml_font = self
        self._gml_text_symbols = value
            
    gml_text_symbols = property(get_gml_text_symbols, set_gml_text_symbols)
    
    def add_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_font = self
            if obj not in self._gml_text_symbols:
                self._gml_text_symbols.append(obj)
        
    def remove_gml_text_symbols(self, *gml_text_symbols):
        for obj in gml_text_symbols:
            obj._gml_font = None
            self._gml_text_symbols.remove(obj)
    # >>> gml_text_symbols



class GmlTopologyStyle(IdentifiedObject):
    """ The style for one topology property. Similarly to the Geometry style, a feature can have multiple topology properties, thus multiple topology style descriptors can be specified within one feature style.
    """
    # <<< gml_topology_style
    # @generated
    def __init__(self, gml_lable_style=None, gml_feature_style=None, **kw_args):
        """ Initialises a new 'GmlTopologyStyle' instance.
        """
        
        self._gml_lable_style = None
        self.gml_lable_style = gml_lable_style
        self._gml_feature_style = None
        self.gml_feature_style = gml_feature_style

        super(GmlTopologyStyle, self).__init__(**kw_args)
    # >>> gml_topology_style
        
    # <<< gml_lable_style
    # @generated
    def get_gml_lable_style(self):
        """ 
        """
        return self._gml_lable_style

    def set_gml_lable_style(self, value):
        if self._gml_lable_style is not None:
            filtered = [x for x in self.gml_lable_style.gml_topology_styles if x != self]
            self._gml_lable_style._gml_topology_styles = filtered
            
        self._gml_lable_style = value
        if self._gml_lable_style is not None:
            if self not in self._gml_lable_style._gml_topology_styles:
                self._gml_lable_style._gml_topology_styles.append(self)

    gml_lable_style = property(get_gml_lable_style, set_gml_lable_style)
    # >>> gml_lable_style

    # <<< gml_feature_style
    # @generated
    def get_gml_feature_style(self):
        """ 
        """
        return self._gml_feature_style

    def set_gml_feature_style(self, value):
        if self._gml_feature_style is not None:
            filtered = [x for x in self.gml_feature_style.gml_tobology_styles if x != self]
            self._gml_feature_style._gml_tobology_styles = filtered
            
        self._gml_feature_style = value
        if self._gml_feature_style is not None:
            if self not in self._gml_feature_style._gml_tobology_styles:
                self._gml_feature_style._gml_tobology_styles.append(self)

    gml_feature_style = property(get_gml_feature_style, set_gml_feature_style)
    # >>> gml_feature_style



class GmlGeometryStyle(IdentifiedObject):
    """ The style for one geometry of a feature. Any number of geometry style descriptors can be assigned to one feature style. This is usually required for features with multiple geometry properties.
    """
    # <<< gml_geometry_style
    # @generated
    def __init__(self, symbol='', geometry_type='', geometry_property='', gml_label_style=None, gml_feature_style=None, **kw_args):
        """ Initialises a new 'GmlGeometryStyle' instance.
        """
        # Graphical symbol used to render a geometry or a topology. A symbol is a description of graphical attributes of a graphical object without a particular, implicit meaning. It can be a description of a line, circle, polygon or more complex drawing. 
        self.symbol = symbol
        # It is necessary to specify the geometry type using this attribute as well since the application schema of the geometry property may allow different geometries as its value. 
        self.geometry_type = geometry_type
        # The name of the geometry property of a feature to which this GeometryStyle applies. 
        self.geometry_property = geometry_property
        
        self._gml_label_style = None
        self.gml_label_style = gml_label_style
        self._gml_feature_style = None
        self.gml_feature_style = gml_feature_style

        super(GmlGeometryStyle, self).__init__(**kw_args)
    # >>> gml_geometry_style
        
    # <<< gml_label_style
    # @generated
    def get_gml_label_style(self):
        """ 
        """
        return self._gml_label_style

    def set_gml_label_style(self, value):
        if self._gml_label_style is not None:
            filtered = [x for x in self.gml_label_style.gml_geometry_styles if x != self]
            self._gml_label_style._gml_geometry_styles = filtered
            
        self._gml_label_style = value
        if self._gml_label_style is not None:
            if self not in self._gml_label_style._gml_geometry_styles:
                self._gml_label_style._gml_geometry_styles.append(self)

    gml_label_style = property(get_gml_label_style, set_gml_label_style)
    # >>> gml_label_style

    # <<< gml_feature_style
    # @generated
    def get_gml_feature_style(self):
        """ 
        """
        return self._gml_feature_style

    def set_gml_feature_style(self, value):
        if self._gml_feature_style is not None:
            filtered = [x for x in self.gml_feature_style.gml_geometry_styles if x != self]
            self._gml_feature_style._gml_geometry_styles = filtered
            
        self._gml_feature_style = value
        if self._gml_feature_style is not None:
            if self not in self._gml_feature_style._gml_geometry_styles:
                self._gml_feature_style._gml_geometry_styles.append(self)

    gml_feature_style = property(get_gml_feature_style, set_gml_feature_style)
    # >>> gml_feature_style



class GmlFeatureType(IdentifiedObject):
    """ Type classification of feature.
    """
    # <<< gml_feature_type
    # @generated
    def __init__(self, gml_feature_styles=None, **kw_args):
        """ Initialises a new 'GmlFeatureType' instance.
        """
        
        self._gml_feature_styles = []
        if gml_feature_styles is None:
            self.gml_feature_styles = []
        else:
            self.gml_feature_styles = gml_feature_styles

        super(GmlFeatureType, self).__init__(**kw_args)
    # >>> gml_feature_type
        
    # <<< gml_feature_styles
    # @generated
    def get_gml_feature_styles(self):
        """ 
        """
        return self._gml_feature_styles

    def set_gml_feature_styles(self, value):
        for p in self._gml_feature_styles:
            filtered = [q for q in p.gml_feature_types if q != self]
            self._gml_feature_styles._gml_feature_types = filtered
        for r in value:
            if self not in r._gml_feature_types:
                r._gml_feature_types.append(self)
        self._gml_feature_styles = value
            
    gml_feature_styles = property(get_gml_feature_styles, set_gml_feature_styles)
    
    def add_gml_feature_styles(self, *gml_feature_styles):
        for obj in gml_feature_styles:
            if self not in obj._gml_feature_types:
                obj._gml_feature_types.append(self)
            self._gml_feature_styles.append(obj)
        
    def remove_gml_feature_styles(self, *gml_feature_styles):
        for obj in gml_feature_styles:
            if self in obj._gml_feature_types:
                obj._gml_feature_types.remove(self)
            self._gml_feature_styles.remove(obj)
    # >>> gml_feature_styles



class GmlObservation(Element):
    """ A GML observation models the act of observing, often with a camera, a person or some form of instrument. An observation feature describes the 'metadata' associated with an information capture event, together with a value for the result of the observation. The basic structures introduced in this class are intended to serve as the foundation for more comprehensive schemas for scientific, technical and engineering measurement schemas.
    """
    # <<< gml_observation
    # @generated
    def __init__(self, result_of='', date_time='', using='', target='', gml_values=None, locations=None, change_items=None, **kw_args):
        """ Initialises a new 'GmlObservation' instance.
        """
        # Indicates the result of the observation. 
        self.result_of = result_of
 
        self.date_time = date_time
        # Contains or points to a description of a sensor, instrument or procedure used for the observation. 
        self.using = using
        # Contains or points to the specimen, region or station which is the object of the observation 
        self.target = target
        
        self._gml_values = []
        if gml_values is None:
            self.gml_values = []
        else:
            self.gml_values = gml_values
        self._locations = []
        if locations is None:
            self.locations = []
        else:
            self.locations = locations
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items

        super(GmlObservation, self).__init__(**kw_args)
    # >>> gml_observation
        
    # <<< gml_values
    # @generated
    def get_gml_values(self):
        """ 
        """
        return self._gml_values

    def set_gml_values(self, value):
        for x in self._gml_values:
            x.gml_observation = None
        for y in value:
            y.gml_observation = self
        self._gml_values = value
            
    gml_values = property(get_gml_values, set_gml_values)
    
    def add_gml_values(self, *gml_values):
        for obj in gml_values:
            obj._gml_observation = self
            if obj not in self._gml_values:
                self._gml_values.append(obj)
        
    def remove_gml_values(self, *gml_values):
        for obj in gml_values:
            obj._gml_observation = None
            self._gml_values.remove(obj)
    # >>> gml_values

    # <<< locations
    # @generated
    def get_locations(self):
        """ 
        """
        return self._locations

    def set_locations(self, value):
        for p in self._locations:
            filtered = [q for q in p.gml_observatins if q != self]
            self._locations._gml_observatins = filtered
        for r in value:
            if self not in r._gml_observatins:
                r._gml_observatins.append(self)
        self._locations = value
            
    locations = property(get_locations, set_locations)
    
    def add_locations(self, *locations):
        for obj in locations:
            if self not in obj._gml_observatins:
                obj._gml_observatins.append(self)
            self._locations.append(obj)
        
    def remove_locations(self, *locations):
        for obj in locations:
            if self in obj._gml_observatins:
                obj._gml_observatins.remove(self)
            self._locations.remove(obj)
    # >>> locations

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.gml_observation = None
        for y in value:
            y.gml_observation = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._gml_observation = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._gml_observation = None
            self._change_items.remove(obj)
    # >>> change_items



class GmlStroke(IdentifiedObject):
    """ The element encapsulating the graphical symbolization parameters for linear geometries.
    """
    # <<< gml_stroke
    # @generated
    def __init__(self, linejoin='', opacity=0.0, line_style='', dash_offset='', dash_array='', width=0.0, line_cap='', gml_line_symbols=None, gml_svg_parameters=None, gml_colour=None, gml_polygon_symbols=None, gml_marks=None, **kw_args):
        """ Initialises a new 'GmlStroke' instance.
        """
        # Enumerated values telling how line strings should be joined (between line segments). The values are represented as content strings.  The allowed values for line join are 'mitre', 'round', and 'bevel'. The default values are system-dependent. 
        self.linejoin = linejoin
        # Specifies the level of translucency to use when rendering the stroke. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
        self.opacity = opacity
        # The name of a defined line style. Usually will be an enumerated value and will be system-specific. 
        self.line_style = line_style
        # Specifies the distance as a float into the 'stroke-dasharray' pattern at which to start drawing. 
        self.dash_offset = dash_offset
        # Encodes a dash pattern as a series of space separated floats. The first number gives the length in pixels of dash to draw, the second gives the amount of space to leave, and this pattern repeats. If an odd number of values is given, then the pattern is expanded by repeating it twice to give an even number of values. Decimal values have a system-dependent interpretation (usually depending on whether antialiasing is being used). The default is to draw an unbroken line. 
        self.dash_array = dash_array
        # The absolute width (thickness) of a stroke in pixels encoded as a float. The default is 1.0. Fractional numbers are allowed (with a system-dependent interpretation) but negative numbers are not. 
        self.width = width
        # Enumerated values telling how line strings should be capped (at the two ends of the line string). The values are represented as content strings.  The allowed values for line cap are 'butt', 'round', and 'square'. The default values are system-dependent. 
        self.line_cap = line_cap
        
        self._gml_line_symbols = []
        if gml_line_symbols is None:
            self.gml_line_symbols = []
        else:
            self.gml_line_symbols = gml_line_symbols
        self._gml_svg_parameters = []
        if gml_svg_parameters is None:
            self.gml_svg_parameters = []
        else:
            self.gml_svg_parameters = gml_svg_parameters
        self._gml_colour = None
        self.gml_colour = gml_colour
        self._gml_polygon_symbols = []
        if gml_polygon_symbols is None:
            self.gml_polygon_symbols = []
        else:
            self.gml_polygon_symbols = gml_polygon_symbols
        self._gml_marks = []
        if gml_marks is None:
            self.gml_marks = []
        else:
            self.gml_marks = gml_marks

        super(GmlStroke, self).__init__(**kw_args)
    # >>> gml_stroke
        
    # <<< gml_line_symbols
    # @generated
    def get_gml_line_symbols(self):
        """ 
        """
        return self._gml_line_symbols

    def set_gml_line_symbols(self, value):
        for x in self._gml_line_symbols:
            x.gml_stroke = None
        for y in value:
            y.gml_stroke = self
        self._gml_line_symbols = value
            
    gml_line_symbols = property(get_gml_line_symbols, set_gml_line_symbols)
    
    def add_gml_line_symbols(self, *gml_line_symbols):
        for obj in gml_line_symbols:
            obj._gml_stroke = self
            if obj not in self._gml_line_symbols:
                self._gml_line_symbols.append(obj)
        
    def remove_gml_line_symbols(self, *gml_line_symbols):
        for obj in gml_line_symbols:
            obj._gml_stroke = None
            self._gml_line_symbols.remove(obj)
    # >>> gml_line_symbols

    # <<< gml_svg_parameters
    # @generated
    def get_gml_svg_parameters(self):
        """ 
        """
        return self._gml_svg_parameters

    def set_gml_svg_parameters(self, value):
        for p in self._gml_svg_parameters:
            filtered = [q for q in p.gml_stokes if q != self]
            self._gml_svg_parameters._gml_stokes = filtered
        for r in value:
            if self not in r._gml_stokes:
                r._gml_stokes.append(self)
        self._gml_svg_parameters = value
            
    gml_svg_parameters = property(get_gml_svg_parameters, set_gml_svg_parameters)
    
    def add_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self not in obj._gml_stokes:
                obj._gml_stokes.append(self)
            self._gml_svg_parameters.append(obj)
        
    def remove_gml_svg_parameters(self, *gml_svg_parameters):
        for obj in gml_svg_parameters:
            if self in obj._gml_stokes:
                obj._gml_stokes.remove(self)
            self._gml_svg_parameters.remove(obj)
    # >>> gml_svg_parameters

    # <<< gml_colour
    # @generated
    def get_gml_colour(self):
        """ 
        """
        return self._gml_colour

    def set_gml_colour(self, value):
        if self._gml_colour is not None:
            filtered = [x for x in self.gml_colour.gml_strokes if x != self]
            self._gml_colour._gml_strokes = filtered
            
        self._gml_colour = value
        if self._gml_colour is not None:
            if self not in self._gml_colour._gml_strokes:
                self._gml_colour._gml_strokes.append(self)

    gml_colour = property(get_gml_colour, set_gml_colour)
    # >>> gml_colour

    # <<< gml_polygon_symbols
    # @generated
    def get_gml_polygon_symbols(self):
        """ 
        """
        return self._gml_polygon_symbols

    def set_gml_polygon_symbols(self, value):
        for x in self._gml_polygon_symbols:
            x.gml_stroke = None
        for y in value:
            y.gml_stroke = self
        self._gml_polygon_symbols = value
            
    gml_polygon_symbols = property(get_gml_polygon_symbols, set_gml_polygon_symbols)
    
    def add_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_stroke = self
            if obj not in self._gml_polygon_symbols:
                self._gml_polygon_symbols.append(obj)
        
    def remove_gml_polygon_symbols(self, *gml_polygon_symbols):
        for obj in gml_polygon_symbols:
            obj._gml_stroke = None
            self._gml_polygon_symbols.remove(obj)
    # >>> gml_polygon_symbols

    # <<< gml_marks
    # @generated
    def get_gml_marks(self):
        """ 
        """
        return self._gml_marks

    def set_gml_marks(self, value):
        for p in self._gml_marks:
            filtered = [q for q in p.gml_strokes if q != self]
            self._gml_marks._gml_strokes = filtered
        for r in value:
            if self not in r._gml_strokes:
                r._gml_strokes.append(self)
        self._gml_marks = value
            
    gml_marks = property(get_gml_marks, set_gml_marks)
    
    def add_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self not in obj._gml_strokes:
                obj._gml_strokes.append(self)
            self._gml_marks.append(obj)
        
    def remove_gml_marks(self, *gml_marks):
        for obj in gml_marks:
            if self in obj._gml_strokes:
                obj._gml_strokes.remove(self)
            self._gml_marks.remove(obj)
    # >>> gml_marks



class GmlFeatureStyle(IdentifiedObject):
    """ Used for styling a particular aspect or aspects of a feature, such as geometry, topology or arbitrary text string.
    """
    # <<< gml_feature_style
    # @generated
    def __init__(self, feature_constraint='', feature_type_name='', semantic_type_identifier='', feature_type='', query_grammar='xquery', base_type='', version='', gml_symbols=None, gml_label_styles=None, gml_feature_types=None, gml_tobology_styles=None, gml_geometry_styles=None, **kw_args):
        """ Initialises a new 'GmlFeatureStyle' instance.
        """
        # This property is used to further constrain the feature instance set to which the style applies. It is optional and its value is an XPath expression. If the property does not exist, the style applies to all feature instances selected by 'featureType' or 'baseType'. 
        self.feature_constraint = feature_constraint
        # Identifies the specific feature type that the feature-type style is for. 
        self.feature_type_name = feature_type_name
        # The SemanticTypeIdentifier is experimental in GML and is intended to be used to identify what the feature style is suitable to be used for using community-controlled name(s). For example, a single style may be suitable to use with many different feature types. 
        self.semantic_type_identifier = semantic_type_identifier
        # The simplest and most common way of relating features and styles is by using this attribute. Its value will be the declared name of a feature, instances of which we want to style. For example, if the featureType = Switch, this FeatureStyle object will simply apply to all Switch features. 
        self.feature_type = feature_type
        # Grammar used in the content of the gml:featureConstraint element. Values are: "xquery", "xpath", "other"
        self.query_grammar = query_grammar
        # Another way of selecting the feature instances to which the style applies is to specify, as the value of this attribute, the name of the base type from which feature or features derive. 
        self.base_type = base_type
        # Allows version numbers to be identified when the SLD pieces are used independently. 
        self.version = version
        
        self._gml_symbols = []
        if gml_symbols is None:
            self.gml_symbols = []
        else:
            self.gml_symbols = gml_symbols
        self._gml_label_styles = []
        if gml_label_styles is None:
            self.gml_label_styles = []
        else:
            self.gml_label_styles = gml_label_styles
        self._gml_feature_types = []
        if gml_feature_types is None:
            self.gml_feature_types = []
        else:
            self.gml_feature_types = gml_feature_types
        self._gml_tobology_styles = []
        if gml_tobology_styles is None:
            self.gml_tobology_styles = []
        else:
            self.gml_tobology_styles = gml_tobology_styles
        self._gml_geometry_styles = []
        if gml_geometry_styles is None:
            self.gml_geometry_styles = []
        else:
            self.gml_geometry_styles = gml_geometry_styles

        super(GmlFeatureStyle, self).__init__(**kw_args)
    # >>> gml_feature_style
        
    # <<< gml_symbols
    # @generated
    def get_gml_symbols(self):
        """ 
        """
        return self._gml_symbols

    def set_gml_symbols(self, value):
        for p in self._gml_symbols:
            filtered = [q for q in p.gml_feature_styles if q != self]
            self._gml_symbols._gml_feature_styles = filtered
        for r in value:
            if self not in r._gml_feature_styles:
                r._gml_feature_styles.append(self)
        self._gml_symbols = value
            
    gml_symbols = property(get_gml_symbols, set_gml_symbols)
    
    def add_gml_symbols(self, *gml_symbols):
        for obj in gml_symbols:
            if self not in obj._gml_feature_styles:
                obj._gml_feature_styles.append(self)
            self._gml_symbols.append(obj)
        
    def remove_gml_symbols(self, *gml_symbols):
        for obj in gml_symbols:
            if self in obj._gml_feature_styles:
                obj._gml_feature_styles.remove(self)
            self._gml_symbols.remove(obj)
    # >>> gml_symbols

    # <<< gml_label_styles
    # @generated
    def get_gml_label_styles(self):
        """ 
        """
        return self._gml_label_styles

    def set_gml_label_styles(self, value):
        for x in self._gml_label_styles:
            x.gml_feature_style = None
        for y in value:
            y.gml_feature_style = self
        self._gml_label_styles = value
            
    gml_label_styles = property(get_gml_label_styles, set_gml_label_styles)
    
    def add_gml_label_styles(self, *gml_label_styles):
        for obj in gml_label_styles:
            obj._gml_feature_style = self
            if obj not in self._gml_label_styles:
                self._gml_label_styles.append(obj)
        
    def remove_gml_label_styles(self, *gml_label_styles):
        for obj in gml_label_styles:
            obj._gml_feature_style = None
            self._gml_label_styles.remove(obj)
    # >>> gml_label_styles

    # <<< gml_feature_types
    # @generated
    def get_gml_feature_types(self):
        """ 
        """
        return self._gml_feature_types

    def set_gml_feature_types(self, value):
        for p in self._gml_feature_types:
            filtered = [q for q in p.gml_feature_styles if q != self]
            self._gml_feature_types._gml_feature_styles = filtered
        for r in value:
            if self not in r._gml_feature_styles:
                r._gml_feature_styles.append(self)
        self._gml_feature_types = value
            
    gml_feature_types = property(get_gml_feature_types, set_gml_feature_types)
    
    def add_gml_feature_types(self, *gml_feature_types):
        for obj in gml_feature_types:
            if self not in obj._gml_feature_styles:
                obj._gml_feature_styles.append(self)
            self._gml_feature_types.append(obj)
        
    def remove_gml_feature_types(self, *gml_feature_types):
        for obj in gml_feature_types:
            if self in obj._gml_feature_styles:
                obj._gml_feature_styles.remove(self)
            self._gml_feature_types.remove(obj)
    # >>> gml_feature_types

    # <<< gml_tobology_styles
    # @generated
    def get_gml_tobology_styles(self):
        """ 
        """
        return self._gml_tobology_styles

    def set_gml_tobology_styles(self, value):
        for x in self._gml_tobology_styles:
            x.gml_feature_style = None
        for y in value:
            y.gml_feature_style = self
        self._gml_tobology_styles = value
            
    gml_tobology_styles = property(get_gml_tobology_styles, set_gml_tobology_styles)
    
    def add_gml_tobology_styles(self, *gml_tobology_styles):
        for obj in gml_tobology_styles:
            obj._gml_feature_style = self
            if obj not in self._gml_tobology_styles:
                self._gml_tobology_styles.append(obj)
        
    def remove_gml_tobology_styles(self, *gml_tobology_styles):
        for obj in gml_tobology_styles:
            obj._gml_feature_style = None
            self._gml_tobology_styles.remove(obj)
    # >>> gml_tobology_styles

    # <<< gml_geometry_styles
    # @generated
    def get_gml_geometry_styles(self):
        """ 
        """
        return self._gml_geometry_styles

    def set_gml_geometry_styles(self, value):
        for x in self._gml_geometry_styles:
            x.gml_feature_style = None
        for y in value:
            y.gml_feature_style = self
        self._gml_geometry_styles = value
            
    gml_geometry_styles = property(get_gml_geometry_styles, set_gml_geometry_styles)
    
    def add_gml_geometry_styles(self, *gml_geometry_styles):
        for obj in gml_geometry_styles:
            obj._gml_feature_style = self
            if obj not in self._gml_geometry_styles:
                self._gml_geometry_styles.append(obj)
        
    def remove_gml_geometry_styles(self, *gml_geometry_styles):
        for obj in gml_geometry_styles:
            obj._gml_feature_style = None
            self._gml_geometry_styles.remove(obj)
    # >>> gml_geometry_styles



class GmlTextSymbol(GmlSymbol):
    """ Used for styling text labels, i.e., for rendering them according to various graphical parameters.
    """
    # <<< gml_text_symbol
    # @generated
    def __init__(self, min_font_size=0, field_id='', label='', label_type='', property='', gml_label_placement=None, gml_diagram_object=None, gml_font=None, gml_halo=None, gml_fill=None, **kw_args):
        """ Initialises a new 'GmlTextSymbol' instance.
        """
        # The minimum font size allowed. 
        self.min_font_size = min_font_size
        # The name of the field of the class being annotated. Most objects will include name, description, and aliasName. Many objects may contain other fields such as comment, status, etc. 
        self.field_id = field_id
        # Text-label content. If the value is not provided, then no text will be rendered. 
        self.label = label
        # The type-classification of a label. 
        self.label_type = label_type
        # Generic method for capturing all unspecified information pertaining to the TextSymbol. 
        self.property = property
        
        self._gml_label_placement = None
        self.gml_label_placement = gml_label_placement
        self._gml_diagram_object = None
        self.gml_diagram_object = gml_diagram_object
        self._gml_font = None
        self.gml_font = gml_font
        self._gml_halo = None
        self.gml_halo = gml_halo
        self._gml_fill = None
        self.gml_fill = gml_fill

        super(GmlTextSymbol, self).__init__(**kw_args)
    # >>> gml_text_symbol
        
    # <<< gml_label_placement
    # @generated
    def get_gml_label_placement(self):
        """ 
        """
        return self._gml_label_placement

    def set_gml_label_placement(self, value):
        if self._gml_label_placement is not None:
            filtered = [x for x in self.gml_label_placement.gml_text_symbols if x != self]
            self._gml_label_placement._gml_text_symbols = filtered
            
        self._gml_label_placement = value
        if self._gml_label_placement is not None:
            if self not in self._gml_label_placement._gml_text_symbols:
                self._gml_label_placement._gml_text_symbols.append(self)

    gml_label_placement = property(get_gml_label_placement, set_gml_label_placement)
    # >>> gml_label_placement

    # <<< gml_diagram_object
    # @generated
    def get_gml_diagram_object(self):
        """ 
        """
        return self._gml_diagram_object

    def set_gml_diagram_object(self, value):
        if self._gml_diagram_object is not None:
            filtered = [x for x in self.gml_diagram_object.gml_text_symbols if x != self]
            self._gml_diagram_object._gml_text_symbols = filtered
            
        self._gml_diagram_object = value
        if self._gml_diagram_object is not None:
            if self not in self._gml_diagram_object._gml_text_symbols:
                self._gml_diagram_object._gml_text_symbols.append(self)

    gml_diagram_object = property(get_gml_diagram_object, set_gml_diagram_object)
    # >>> gml_diagram_object

    # <<< gml_font
    # @generated
    def get_gml_font(self):
        """ 
        """
        return self._gml_font

    def set_gml_font(self, value):
        if self._gml_font is not None:
            filtered = [x for x in self.gml_font.gml_text_symbols if x != self]
            self._gml_font._gml_text_symbols = filtered
            
        self._gml_font = value
        if self._gml_font is not None:
            if self not in self._gml_font._gml_text_symbols:
                self._gml_font._gml_text_symbols.append(self)

    gml_font = property(get_gml_font, set_gml_font)
    # >>> gml_font

    # <<< gml_halo
    # @generated
    def get_gml_halo(self):
        """ 
        """
        return self._gml_halo

    def set_gml_halo(self, value):
        if self._gml_halo is not None:
            filtered = [x for x in self.gml_halo.gml_text_symbols if x != self]
            self._gml_halo._gml_text_symbols = filtered
            
        self._gml_halo = value
        if self._gml_halo is not None:
            if self not in self._gml_halo._gml_text_symbols:
                self._gml_halo._gml_text_symbols.append(self)

    gml_halo = property(get_gml_halo, set_gml_halo)
    # >>> gml_halo

    # <<< gml_fill
    # @generated
    def get_gml_fill(self):
        """ 
        """
        return self._gml_fill

    def set_gml_fill(self, value):
        if self._gml_fill is not None:
            filtered = [x for x in self.gml_fill.gml_text_symbols if x != self]
            self._gml_fill._gml_text_symbols = filtered
            
        self._gml_fill = value
        if self._gml_fill is not None:
            if self not in self._gml_fill._gml_text_symbols:
                self._gml_fill._gml_text_symbols.append(self)

    gml_fill = property(get_gml_fill, set_gml_fill)
    # >>> gml_fill



class GmlLineGeometry(GmlDiagramObject):
    """ Typically used for rendering linear assets and/or power system resources.
    """
    # <<< gml_line_geometry
    # @generated
    def __init__(self, source_side='', **kw_args):
        """ Initialises a new 'GmlLineGeometry' instance.
        """
        # For dynamic network update (i.e. colouring) purposes 
        self.source_side = source_side
        

        super(GmlLineGeometry, self).__init__(**kw_args)
    # >>> gml_line_geometry
        


class GmlLineSymbol(GmlSymbol):
    """ Used to style a 'stroke' along a linear geometry type, such as a string of line segments.
    """
    # <<< gml_line_symbol
    # @generated
    def __init__(self, source_side='', gml_stroke=None, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlLineSymbol' instance.
        """
        # For dynamic network update (i.e. colouring) purposes 
        self.source_side = source_side
        
        self._gml_stroke = None
        self.gml_stroke = gml_stroke
        self._gml_diagram_object = None
        self.gml_diagram_object = gml_diagram_object

        super(GmlLineSymbol, self).__init__(**kw_args)
    # >>> gml_line_symbol
        
    # <<< gml_stroke
    # @generated
    def get_gml_stroke(self):
        """ 
        """
        return self._gml_stroke

    def set_gml_stroke(self, value):
        if self._gml_stroke is not None:
            filtered = [x for x in self.gml_stroke.gml_line_symbols if x != self]
            self._gml_stroke._gml_line_symbols = filtered
            
        self._gml_stroke = value
        if self._gml_stroke is not None:
            if self not in self._gml_stroke._gml_line_symbols:
                self._gml_stroke._gml_line_symbols.append(self)

    gml_stroke = property(get_gml_stroke, set_gml_stroke)
    # >>> gml_stroke

    # <<< gml_diagram_object
    # @generated
    def get_gml_diagram_object(self):
        """ 
        """
        return self._gml_diagram_object

    def set_gml_diagram_object(self, value):
        if self._gml_diagram_object is not None:
            filtered = [x for x in self.gml_diagram_object.gml_line_symbols if x != self]
            self._gml_diagram_object._gml_line_symbols = filtered
            
        self._gml_diagram_object = value
        if self._gml_diagram_object is not None:
            if self not in self._gml_diagram_object._gml_line_symbols:
                self._gml_diagram_object._gml_line_symbols.append(self)

    gml_diagram_object = property(get_gml_diagram_object, set_gml_diagram_object)
    # >>> gml_diagram_object



class GmlPointGeometry(GmlDiagramObject):
    """ Typically used for rendering power system resources and/or point assets.
    """
    pass
    # <<< gml_point_geometry
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GmlPointGeometry' instance.
        """
        

        super(GmlPointGeometry, self).__init__(**kw_args)
    # >>> gml_point_geometry
        


class GmlPointSymbol(GmlSymbol):
    """ Used to draw a 'graphic' at a point.
    """
    # <<< gml_point_symbol
    # @generated
    def __init__(self, gml_diagram_object=None, gml_graphic=None, **kw_args):
        """ Initialises a new 'GmlPointSymbol' instance.
        """
        
        self._gml_diagram_object = None
        self.gml_diagram_object = gml_diagram_object
        self._gml_graphic = None
        self.gml_graphic = gml_graphic

        super(GmlPointSymbol, self).__init__(**kw_args)
    # >>> gml_point_symbol
        
    # <<< gml_diagram_object
    # @generated
    def get_gml_diagram_object(self):
        """ 
        """
        return self._gml_diagram_object

    def set_gml_diagram_object(self, value):
        if self._gml_diagram_object is not None:
            filtered = [x for x in self.gml_diagram_object.gml_point_symbols if x != self]
            self._gml_diagram_object._gml_point_symbols = filtered
            
        self._gml_diagram_object = value
        if self._gml_diagram_object is not None:
            if self not in self._gml_diagram_object._gml_point_symbols:
                self._gml_diagram_object._gml_point_symbols.append(self)

    gml_diagram_object = property(get_gml_diagram_object, set_gml_diagram_object)
    # >>> gml_diagram_object

    # <<< gml_graphic
    # @generated
    def get_gml_graphic(self):
        """ 
        """
        return self._gml_graphic

    def set_gml_graphic(self, value):
        if self._gml_graphic is not None:
            filtered = [x for x in self.gml_graphic.gml_point_symbols if x != self]
            self._gml_graphic._gml_point_symbols = filtered
            
        self._gml_graphic = value
        if self._gml_graphic is not None:
            if self not in self._gml_graphic._gml_point_symbols:
                self._gml_graphic._gml_point_symbols.append(self)

    gml_graphic = property(get_gml_graphic, set_gml_graphic)
    # >>> gml_graphic



class GmlPolygonGeometry(GmlDiagramObject):
    """ Used to show the footprint of substations, sites, service territories, tax districts, school districts, etc.
    """
    pass
    # <<< gml_polygon_geometry
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GmlPolygonGeometry' instance.
        """
        

        super(GmlPolygonGeometry, self).__init__(**kw_args)
    # >>> gml_polygon_geometry
        


class GmlRasterSymbol(GmlSymbol):
    """ Describes how to render raster/matrix-coverage data (e.g., satellite photos, DEMs).
    """
    # <<< gml_raster_symbol
    # @generated
    def __init__(self, red_sourcename='', blue_sourcename='', opacity=0.0, green_source_name='', gray_sourcename='', relief_factor='', overlapbehaviour='', brighness_only=False, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlRasterSymbol' instance.
        """
        # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
        self.red_sourcename = red_sourcename
        # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
        self.blue_sourcename = blue_sourcename
        # Specifies the level of translucency to use when rendering the Graphic. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0. 
        self.opacity = opacity
        # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
        self.green_source_name = green_source_name
        # A single colour channel may be selected to display in grayscale. Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
        self.gray_sourcename = gray_sourcename
        # The ReliefFactor gives the amount of exaggeration to use for the height of the 'hills'. A value of around 55 (times) gives reasonable results for Earth-based DEMs. The default value is system-dependent. 
        self.relief_factor = relief_factor
        # Tells a system how to behave when multiple raster images in a layer overlap each other, for example with satellite-image scenes. 
        self.overlapbehaviour = overlapbehaviour
        # If the BrightnessOnly flag is 0 (false, default), the shading is applied to the layer being rendered as the current RasterSymbol. If BrightnessOnly is 1 (true), the shading is applied to the brightness of the colors in the rendering canvas generated so far by other layers, with the effect of relief-shading these other layers. 
        self.brighness_only = brighness_only
        
        self._gml_diagram_object = None
        self.gml_diagram_object = gml_diagram_object

        super(GmlRasterSymbol, self).__init__(**kw_args)
    # >>> gml_raster_symbol
        
    # <<< gml_diagram_object
    # @generated
    def get_gml_diagram_object(self):
        """ 
        """
        return self._gml_diagram_object

    def set_gml_diagram_object(self, value):
        if self._gml_diagram_object is not None:
            filtered = [x for x in self.gml_diagram_object.gml_raster_symbols if x != self]
            self._gml_diagram_object._gml_raster_symbols = filtered
            
        self._gml_diagram_object = value
        if self._gml_diagram_object is not None:
            if self not in self._gml_diagram_object._gml_raster_symbols:
                self._gml_diagram_object._gml_raster_symbols.append(self)

    gml_diagram_object = property(get_gml_diagram_object, set_gml_diagram_object)
    # >>> gml_diagram_object



class GmlPolygonSymbol(GmlSymbol):
    """ Used to draw a polygon (or other area-type geometries), including filling its interior and stroking its border (outline).
    """
    # <<< gml_polygon_symbol
    # @generated
    def __init__(self, gml_stroke=None, gml_diagram_object=None, gml_fill=None, **kw_args):
        """ Initialises a new 'GmlPolygonSymbol' instance.
        """
        
        self._gml_stroke = None
        self.gml_stroke = gml_stroke
        self._gml_diagram_object = None
        self.gml_diagram_object = gml_diagram_object
        self._gml_fill = None
        self.gml_fill = gml_fill

        super(GmlPolygonSymbol, self).__init__(**kw_args)
    # >>> gml_polygon_symbol
        
    # <<< gml_stroke
    # @generated
    def get_gml_stroke(self):
        """ 
        """
        return self._gml_stroke

    def set_gml_stroke(self, value):
        if self._gml_stroke is not None:
            filtered = [x for x in self.gml_stroke.gml_polygon_symbols if x != self]
            self._gml_stroke._gml_polygon_symbols = filtered
            
        self._gml_stroke = value
        if self._gml_stroke is not None:
            if self not in self._gml_stroke._gml_polygon_symbols:
                self._gml_stroke._gml_polygon_symbols.append(self)

    gml_stroke = property(get_gml_stroke, set_gml_stroke)
    # >>> gml_stroke

    # <<< gml_diagram_object
    # @generated
    def get_gml_diagram_object(self):
        """ 
        """
        return self._gml_diagram_object

    def set_gml_diagram_object(self, value):
        if self._gml_diagram_object is not None:
            filtered = [x for x in self.gml_diagram_object.gml_polygon_symbols if x != self]
            self._gml_diagram_object._gml_polygon_symbols = filtered
            
        self._gml_diagram_object = value
        if self._gml_diagram_object is not None:
            if self not in self._gml_diagram_object._gml_polygon_symbols:
                self._gml_diagram_object._gml_polygon_symbols.append(self)

    gml_diagram_object = property(get_gml_diagram_object, set_gml_diagram_object)
    # >>> gml_diagram_object

    # <<< gml_fill
    # @generated
    def get_gml_fill(self):
        """ 
        """
        return self._gml_fill

    def set_gml_fill(self, value):
        if self._gml_fill is not None:
            filtered = [x for x in self.gml_fill.gml_polygon_symbols if x != self]
            self._gml_fill._gml_polygon_symbols = filtered
            
        self._gml_fill = value
        if self._gml_fill is not None:
            if self not in self._gml_fill._gml_polygon_symbols:
                self._gml_fill._gml_polygon_symbols.append(self)

    gml_fill = property(get_gml_fill, set_gml_fill)
    # >>> gml_fill



# <<< inf_gmlsupport
# @generated
# >>> inf_gmlsupport
