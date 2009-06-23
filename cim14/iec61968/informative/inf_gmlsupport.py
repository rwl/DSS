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
    # The attribute of the GmlSvgParameter. E.g., for 'Stroke', the following SvgParameters may be used: 'stroke' (color), 'stroke-opacity', 'stroke-width', 'stroke-linejoin', 'stroke-linecap', 'stroke-dasharray', and 'stroke-dashoffset'. Others are not officially supported. 
    attribute = ''

    # The SVG/CSS-coded value of the associated SvgAttribute. 
    value = ''

    gml_stokes = []

    gml_fonts = []

    gml_fills = []

    # <<< gml_svg_parameter
    # @generated
    def __init__(self, attribute='', value='', gml_stokes=[], gml_fonts=[], gml_fills=[], **kw_args):
        """ Initialises a new 'GmlSvgParameter' instance.
        """
        self.attribute = attribute
        self.value = value
        self.gml_stokes = gml_stokes
        self.gml_fonts = gml_fonts
        self.gml_fills = gml_fills

        super(GmlSvgParameter, self).__init__(**kw_args)
    # >>> gml_svg_parameter


class GmlColour(IdentifiedObject):
    """ The solid color that will be used. The color value is RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign. The hexadecimal digits between A and F may be in either uppercase or lowercase. For example, full red is encoded as '#ff0000' (with no quotation marks). If the Stroke cssParameter element is absent, the default color is defined to be black ('#000000').
    """
    # The color value for BLUE (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.). 
    blue = ''

    # The color value for RED (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.) 
    red = ''

    # The color value for GREEN (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.) 
    green = ''

    gml_fills = []

    gml_fonts = []

    gml_strokes = []

    # <<< gml_colour
    # @generated
    def __init__(self, blue='', red='', green='', gml_fills=[], gml_fonts=[], gml_strokes=[], **kw_args):
        """ Initialises a new 'GmlColour' instance.
        """
        self.blue = blue
        self.red = red
        self.green = green
        self.gml_fills = gml_fills
        self.gml_fonts = gml_fonts
        self.gml_strokes = gml_strokes

        super(GmlColour, self).__init__(**kw_args)
    # >>> gml_colour


class GmlDiagramObject(Location):
    """ Any of the magnitudes that serve to define the position of a point by reference to a fixed figure, system of lines, etc.
    """
    gml_raster_symbols = []

    gml_point_symbols = []

    diagrams = []

    gml_polygon_symbols = []

    gml_line_symbols = []

    gml_coordinate_systems = []

    gml_text_symbols = []

    # <<< gml_diagram_object
    # @generated
    def __init__(self, gml_raster_symbols=[], gml_point_symbols=[], diagrams=[], gml_polygon_symbols=[], gml_line_symbols=[], gml_coordinate_systems=[], gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlDiagramObject' instance.
        """
        self.gml_raster_symbols = gml_raster_symbols
        self.gml_point_symbols = gml_point_symbols
        self.diagrams = diagrams
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_line_symbols = gml_line_symbols
        self.gml_coordinate_systems = gml_coordinate_systems
        self.gml_text_symbols = gml_text_symbols

        super(GmlDiagramObject, self).__init__(**kw_args)
    # >>> gml_diagram_object


class GmlGraphic(IdentifiedObject):
    """ A 'graphic symbol' with an inherent shape, color(s), and possibly size. A 'graphic' can be very informally defined as 'a little picture' and can be of either a raster or vector-graphic source type.
    """
    # Vertical scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbars). 
    y_scale = 0.0

    # The identifier of the symbol, if not derived from the type of CIM object (PSR, Asset, Organisation, Document, etc.) gmlSymbolPlacement is associated with. 
    symbol_id = ''

    # Gives the rotation of a graphic in the clockwise direction about its center point in decimal degrees, encoded as a floating-point number. Negative values mean counter-clockwise rotation. The default value is 0.0 (no rotation). Note that there is no connection between source geometry types and rotations; the point used for plotting has no inherent direction. Also, the point within the graphic about which it is rotated is format dependent. If a format does not include an inherent rotation point, then the point of rotation should be the centroid. 
    rotation = ''

    # The minimum symbol size allowed. 
    min_size = 0

    # Gives the absolute size of the graphic in pixels encoded as a floatingpoint number. The default size for an object is context-dependent. Negative values are not allowed. 
    size = 0

    # Horizontal scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbar). 
    x_scale = 0.0

    # Specifies the level of translucency to use when rendering the Graphic.The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
    opacity = 0.0

    gml_point_symbols = []

    gml_marks = []

    # <<< gml_graphic
    # @generated
    def __init__(self, y_scale=0.0, symbol_id='', rotation='', min_size=0, size=0, x_scale=0.0, opacity=0.0, gml_point_symbols=[], gml_marks=[], **kw_args):
        """ Initialises a new 'GmlGraphic' instance.
        """
        self.y_scale = y_scale
        self.symbol_id = symbol_id
        self.rotation = rotation
        self.min_size = min_size
        self.size = size
        self.x_scale = x_scale
        self.opacity = opacity
        self.gml_point_symbols = gml_point_symbols
        self.gml_marks = gml_marks

        super(GmlGraphic, self).__init__(**kw_args)
    # >>> gml_graphic


class GmlFill(IdentifiedObject):
    """ Specifies how the area of the geometry will be filled.
    """
    # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
    opacity = 0.0

    gml_colour = None

    gml_polygon_symbols = []

    gml_marks = []

    gml_text_symbols = []

    gml_svg_parameters = []

    # <<< gml_fill
    # @generated
    def __init__(self, opacity=0.0, gml_colour=None, gml_polygon_symbols=[], gml_marks=[], gml_text_symbols=[], gml_svg_parameters=[], **kw_args):
        """ Initialises a new 'GmlFill' instance.
        """
        self.opacity = opacity
        self.gml_colour = gml_colour
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_marks = gml_marks
        self.gml_text_symbols = gml_text_symbols
        self.gml_svg_parameters = gml_svg_parameters

        super(GmlFill, self).__init__(**kw_args)
    # >>> gml_fill


class GmlSelector(IdentifiedObject):
    """ A diagram element that allows selection by a user, i.e. as 'hyperNode' for navigating between diagrams, or as composite object representing multiple grouped objects.
    """
    locations = []

    change_items = []

    # <<< gml_selector
    # @generated
    def __init__(self, locations=[], change_items=[], **kw_args):
        """ Initialises a new 'GmlSelector' instance.
        """
        self.locations = locations
        self.change_items = change_items

        super(GmlSelector, self).__init__(**kw_args)
    # >>> gml_selector


class GmlLabelPlacement(IdentifiedObject):
    """ Used to position a label relative to a point or a line.
    """
    # Type of 'LabelPlacement' which in turn specifies where and how a text label should be rendered relative to a geometry. 
    type = ''

    # Perpendicular distance away from a line to draw a label. The distance is in pixels and is positive to the left-hand side of the line string. Negative numbers mean right. The default offset is 0. 
    offset = ''

    # Y displacement from the main-geometry point to render a text label. 
    displacement_y = ''

    # Clockwise rotation of the label in degrees from the normal direction for a font. 
    rotation = ''

    # Y-coordinate location inside of a label to use for anchoring the label to the main-geometry point. 
    anchor_y = ''

    # X displacement from the main-geometry point to render a text label. 
    displacement_x = ''

    # X-coordinate location inside of a label to use for anchoring the label to the main-geometry point. 
    anchor_x = ''

    gml_text_symbols = []

    # <<< gml_label_placement
    # @generated
    def __init__(self, type='', offset='', displacement_y='', rotation='', anchor_y='', displacement_x='', anchor_x='', gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlLabelPlacement' instance.
        """
        self.type = type
        self.offset = offset
        self.displacement_y = displacement_y
        self.rotation = rotation
        self.anchor_y = anchor_y
        self.displacement_x = displacement_x
        self.anchor_x = anchor_x
        self.gml_text_symbols = gml_text_symbols

        super(GmlLabelPlacement, self).__init__(**kw_args)
    # >>> gml_label_placement


class GmlSymbol(IdentifiedObject):
    """ Describes how a feature is to appear on a map or display. The symbol describes not just the shape that should appear but also such graphical properties as color and opacity.
    """
    # The level (of the map) where the symbol exists or the zoom levels at which this diagram object is displayed. As a way of de-cluttering displays, for example, some symbols and annotations are only shown when zoomed in. 
    level = ''

    # The version of the Symbol. 
    version = ''

    # The Symbol type. 
    type = ''

    gml_feature_styles = []

    gml_base_symbol = None

    # <<< gml_symbol
    # @generated
    def __init__(self, level='', version='', type='', gml_feature_styles=[], gml_base_symbol=None, **kw_args):
        """ Initialises a new 'GmlSymbol' instance.
        """
        self.level = level
        self.version = version
        self.type = type
        self.gml_feature_styles = gml_feature_styles
        self.gml_base_symbol = gml_base_symbol

        super(GmlSymbol, self).__init__(**kw_args)
    # >>> gml_symbol


class GmlValue(IdentifiedObject):
    """ Used for direct representation of values.
    """
 
    value = ''

 
    date_time = ''

 
    time_period = ''

    measurement_value = None

    gml_observation = None

    # <<< gml_value
    # @generated
    def __init__(self, value='', date_time='', time_period='', measurement_value=None, gml_observation=None, **kw_args):
        """ Initialises a new 'GmlValue' instance.
        """
        self.value = value
        self.date_time = date_time
        self.time_period = time_period
        self.measurement_value = measurement_value
        self.gml_observation = gml_observation

        super(GmlValue, self).__init__(**kw_args)
    # >>> gml_value


class GmlBaseSymbol(IdentifiedObject):
    """ Allows referencing and extension of external symbols, which may be stored in a symbol repository. The graphical properties from a referenced external symbol override the ones read in from the base symbol.
    """
    gml_symbols = []

    # <<< gml_base_symbol
    # @generated
    def __init__(self, gml_symbols=[], **kw_args):
        """ Initialises a new 'GmlBaseSymbol' instance.
        """
        self.gml_symbols = gml_symbols

        super(GmlBaseSymbol, self).__init__(**kw_args)
    # >>> gml_base_symbol


class GmlCoordinateSystem(IdentifiedObject):
    """ A coordinate reference system consists of a set of coordinate system axes that is related to the earth through a datum that defines the size and shape of the earth or some abstract reference system such as those used for rendering schemantic diagrams, internal views of items such as cabinets, vaults, etc. Geometries in GML indicate the coordinate reference system in which their measurements have been made.
    """
    # If applicable, the minimum position allowed along the Z axis of the coordinate reference system. 
    z_min = ''

 
    position_unit_name = ''

    # The maximum position allowed along the Y axis of the coordinate reference system. 
    y_max = ''

 
    scale = ''

    # The minimum position allowed along the Y axis of the coordinate reference system. 
    y_min = ''

    # If applicable, the maximum position allowed along the Z axis of the coordinate reference system. 
    z_max = ''

    # The minimum position allowed along the X axis of the coordinate reference system. 
    x_min = ''

    # The maximum position allowed along the X axis of the coordinate reference system. 
    x_max = ''

    gml_diagram_objects = []

    diagrams = []

    gml_positions = []

    # <<< gml_coordinate_system
    # @generated
    def __init__(self, z_min='', position_unit_name='', y_max='', scale='', y_min='', z_max='', x_min='', x_max='', gml_diagram_objects=[], diagrams=[], gml_positions=[], **kw_args):
        """ Initialises a new 'GmlCoordinateSystem' instance.
        """
        self.z_min = z_min
        self.position_unit_name = position_unit_name
        self.y_max = y_max
        self.scale = scale
        self.y_min = y_min
        self.z_max = z_max
        self.x_min = x_min
        self.x_max = x_max
        self.gml_diagram_objects = gml_diagram_objects
        self.diagrams = diagrams
        self.gml_positions = gml_positions

        super(GmlCoordinateSystem, self).__init__(**kw_args)
    # >>> gml_coordinate_system


class GmlLabelStyle(IdentifiedObject):
    """ The style for the text that is to be displayed along with the graphical representation of a feature. The content of the label is not necessarily defined in the GML data set. More precisely, the content can be static text specified in the style itself and the text from the GML data set. Label style has two elements: gml:style that specifies the style and gml:label that is used to compose the label content.
    """
    # Used to specify the style of the rendered text. The CSS2 styling expressions grammar should be used to express graphic properties. 
    style = ''

    # Allows both text content and unbounded number of gml:LabelExpression elements. The value of gml:LabelExpression element is an XPath expression that selects the value of some property of the feature. 
    label_expression = ''

    # Allows us to specify a transformation expression that will be applied to the symbol in the rendering phase. Its type is xsd:string and the value is specified in the SVG specification (transform attribute). 
    transform = ''

    gml_topology_styles = []

    gml_feature_style = None

    gml_geometry_styles = []

    # <<< gml_label_style
    # @generated
    def __init__(self, style='', label_expression='', transform='', gml_topology_styles=[], gml_feature_style=None, gml_geometry_styles=[], **kw_args):
        """ Initialises a new 'GmlLabelStyle' instance.
        """
        self.style = style
        self.label_expression = label_expression
        self.transform = transform
        self.gml_topology_styles = gml_topology_styles
        self.gml_feature_style = gml_feature_style
        self.gml_geometry_styles = gml_geometry_styles

        super(GmlLabelStyle, self).__init__(**kw_args)
    # >>> gml_label_style


class GmlMark(IdentifiedObject):
    """ Defines a 'shape' which has coloring applied to it (i.e. square, circle, triangle, star, ...).
    """
    # Gives the well-known name of the shape of the mark. Allowed values include at least square, circle, triangle, star, cross, and x. 
    well_known_name = ''

    gml_graphics = []

    gml_strokes = []

    gml_fills = []

    # <<< gml_mark
    # @generated
    def __init__(self, well_known_name='', gml_graphics=[], gml_strokes=[], gml_fills=[], **kw_args):
        """ Initialises a new 'GmlMark' instance.
        """
        self.well_known_name = well_known_name
        self.gml_graphics = gml_graphics
        self.gml_strokes = gml_strokes
        self.gml_fills = gml_fills

        super(GmlMark, self).__init__(**kw_args)
    # >>> gml_mark


class GmlPosition(PositionPoint):
    """ Position point with a GML coordinate reference system.
    """
    gml_coordinate_system = None

    # <<< gml_position
    # @generated
    def __init__(self, gml_coordinate_system=None, **kw_args):
        """ Initialises a new 'GmlPosition' instance.
        """
        self.gml_coordinate_system = gml_coordinate_system

        super(GmlPosition, self).__init__(**kw_args)
    # >>> gml_position


class GmlHalo(IdentifiedObject):
    """ A type of Fill that is applied to the backgrounds of font glyphs. The use of halos greatly improves the readability of text labels.
    """
    # The Radius element gives the absolute size of a halo radius in pixels encoded as a floating-point number. The radius is taken from the outside edge of a font glyph to extend the area of coverage of the glyph (and the inside edge of ?holes? in the glyphs). The default radius is one pixel. Negative values are not allowed. 
    radius = ''

    # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
    opacity = 0.0

    gml_text_symbols = []

    # <<< gml_halo
    # @generated
    def __init__(self, radius='', opacity=0.0, gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlHalo' instance.
        """
        self.radius = radius
        self.opacity = opacity
        self.gml_text_symbols = gml_text_symbols

        super(GmlHalo, self).__init__(**kw_args)
    # >>> gml_halo


class GmlFont(IdentifiedObject):
    """ Identifies a font of a certain family, style, and size.
    """
    # True if 'size' is expressed in absolute values. Default is false. 
    absolute_size = False

    # Family name of a font to use. Allowed values are system-dependent. Any number of font-family attributes may be given and they are assumed to be in preferred order. 
    family = ''

    # The style to use for a font. The allowed values are 'normal', 'italic', and 'oblique'. 
    style = ''

    # The amount of weight or boldness to use for a font. Allowed values are 'normal' and 'bold'. 
    weight = ''

    # The size to use for the font in pixels. The default is defined to be 10 pixels, though various systems may have restrictions on what sizes are available. 
    size = ''

    gml_colour = None

    gml_svg_parameters = []

    gml_text_symbols = []

    # <<< gml_font
    # @generated
    def __init__(self, absolute_size=False, family='', style='', weight='', size='', gml_colour=None, gml_svg_parameters=[], gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlFont' instance.
        """
        self.absolute_size = absolute_size
        self.family = family
        self.style = style
        self.weight = weight
        self.size = size
        self.gml_colour = gml_colour
        self.gml_svg_parameters = gml_svg_parameters
        self.gml_text_symbols = gml_text_symbols

        super(GmlFont, self).__init__(**kw_args)
    # >>> gml_font


class GmlTopologyStyle(IdentifiedObject):
    """ The style for one topology property. Similarly to the Geometry style, a feature can have multiple topology properties, thus multiple topology style descriptors can be specified within one feature style.
    """
    gml_lable_style = None

    gml_feature_style = None

    # <<< gml_topology_style
    # @generated
    def __init__(self, gml_lable_style=None, gml_feature_style=None, **kw_args):
        """ Initialises a new 'GmlTopologyStyle' instance.
        """
        self.gml_lable_style = gml_lable_style
        self.gml_feature_style = gml_feature_style

        super(GmlTopologyStyle, self).__init__(**kw_args)
    # >>> gml_topology_style


class GmlGeometryStyle(IdentifiedObject):
    """ The style for one geometry of a feature. Any number of geometry style descriptors can be assigned to one feature style. This is usually required for features with multiple geometry properties.
    """
    # Graphical symbol used to render a geometry or a topology. A symbol is a description of graphical attributes of a graphical object without a particular, implicit meaning. It can be a description of a line, circle, polygon or more complex drawing. 
    symbol = ''

    # It is necessary to specify the geometry type using this attribute as well since the application schema of the geometry property may allow different geometries as its value. 
    geometry_type = ''

    # The name of the geometry property of a feature to which this GeometryStyle applies. 
    geometry_property = ''

    gml_label_style = None

    gml_feature_style = None

    # <<< gml_geometry_style
    # @generated
    def __init__(self, symbol='', geometry_type='', geometry_property='', gml_label_style=None, gml_feature_style=None, **kw_args):
        """ Initialises a new 'GmlGeometryStyle' instance.
        """
        self.symbol = symbol
        self.geometry_type = geometry_type
        self.geometry_property = geometry_property
        self.gml_label_style = gml_label_style
        self.gml_feature_style = gml_feature_style

        super(GmlGeometryStyle, self).__init__(**kw_args)
    # >>> gml_geometry_style


class GmlFeatureType(IdentifiedObject):
    """ Type classification of feature.
    """
    gml_feature_styles = []

    # <<< gml_feature_type
    # @generated
    def __init__(self, gml_feature_styles=[], **kw_args):
        """ Initialises a new 'GmlFeatureType' instance.
        """
        self.gml_feature_styles = gml_feature_styles

        super(GmlFeatureType, self).__init__(**kw_args)
    # >>> gml_feature_type


class GmlObservation(Element):
    """ A GML observation models the act of observing, often with a camera, a person or some form of instrument. An observation feature describes the 'metadata' associated with an information capture event, together with a value for the result of the observation. The basic structures introduced in this class are intended to serve as the foundation for more comprehensive schemas for scientific, technical and engineering measurement schemas.
    """
    # Indicates the result of the observation. 
    result_of = ''

 
    date_time = ''

    # Contains or points to a description of a sensor, instrument or procedure used for the observation. 
    using = ''

    # Contains or points to the specimen, region or station which is the object of the observation 
    target = ''

    gml_values = []

    locations = []

    change_items = []

    # <<< gml_observation
    # @generated
    def __init__(self, result_of='', date_time='', using='', target='', gml_values=[], locations=[], change_items=[], **kw_args):
        """ Initialises a new 'GmlObservation' instance.
        """
        self.result_of = result_of
        self.date_time = date_time
        self.using = using
        self.target = target
        self.gml_values = gml_values
        self.locations = locations
        self.change_items = change_items

        super(GmlObservation, self).__init__(**kw_args)
    # >>> gml_observation


class GmlStroke(IdentifiedObject):
    """ The element encapsulating the graphical symbolization parameters for linear geometries.
    """
    # Enumerated values telling how line strings should be joined (between line segments). The values are represented as content strings.  The allowed values for line join are 'mitre', 'round', and 'bevel'. The default values are system-dependent. 
    linejoin = ''

    # Specifies the level of translucency to use when rendering the stroke. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0 
    opacity = 0.0

    # The name of a defined line style. Usually will be an enumerated value and will be system-specific. 
    line_style = ''

    # Specifies the distance as a float into the 'stroke-dasharray' pattern at which to start drawing. 
    dash_offset = ''

    # Encodes a dash pattern as a series of space separated floats. The first number gives the length in pixels of dash to draw, the second gives the amount of space to leave, and this pattern repeats. If an odd number of values is given, then the pattern is expanded by repeating it twice to give an even number of values. Decimal values have a system-dependent interpretation (usually depending on whether antialiasing is being used). The default is to draw an unbroken line. 
    dash_array = ''

    # The absolute width (thickness) of a stroke in pixels encoded as a float. The default is 1.0. Fractional numbers are allowed (with a system-dependent interpretation) but negative numbers are not. 
    width = 0.0

    # Enumerated values telling how line strings should be capped (at the two ends of the line string). The values are represented as content strings.  The allowed values for line cap are 'butt', 'round', and 'square'. The default values are system-dependent. 
    line_cap = ''

    gml_line_symbols = []

    gml_svg_parameters = []

    gml_colour = None

    gml_polygon_symbols = []

    gml_marks = []

    # <<< gml_stroke
    # @generated
    def __init__(self, linejoin='', opacity=0.0, line_style='', dash_offset='', dash_array='', width=0.0, line_cap='', gml_line_symbols=[], gml_svg_parameters=[], gml_colour=None, gml_polygon_symbols=[], gml_marks=[], **kw_args):
        """ Initialises a new 'GmlStroke' instance.
        """
        self.linejoin = linejoin
        self.opacity = opacity
        self.line_style = line_style
        self.dash_offset = dash_offset
        self.dash_array = dash_array
        self.width = width
        self.line_cap = line_cap
        self.gml_line_symbols = gml_line_symbols
        self.gml_svg_parameters = gml_svg_parameters
        self.gml_colour = gml_colour
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_marks = gml_marks

        super(GmlStroke, self).__init__(**kw_args)
    # >>> gml_stroke


class GmlFeatureStyle(IdentifiedObject):
    """ Used for styling a particular aspect or aspects of a feature, such as geometry, topology or arbitrary text string.
    """
    # This property is used to further constrain the feature instance set to which the style applies. It is optional and its value is an XPath expression. If the property does not exist, the style applies to all feature instances selected by 'featureType' or 'baseType'. 
    feature_constraint = ''

    # Identifies the specific feature type that the feature-type style is for. 
    feature_type_name = ''

    # The SemanticTypeIdentifier is experimental in GML and is intended to be used to identify what the feature style is suitable to be used for using community-controlled name(s). For example, a single style may be suitable to use with many different feature types. 
    semantic_type_identifier = ''

    # The simplest and most common way of relating features and styles is by using this attribute. Its value will be the declared name of a feature, instances of which we want to style. For example, if the featureType = Switch, this FeatureStyle object will simply apply to all Switch features. 
    feature_type = ''

    # Grammar used in the content of the gml:featureConstraint element. Values are: "xquery", "xpath", "other"
    query_grammar = 'xquery'

    # Another way of selecting the feature instances to which the style applies is to specify, as the value of this attribute, the name of the base type from which feature or features derive. 
    base_type = ''

    # Allows version numbers to be identified when the SLD pieces are used independently. 
    version = ''

    gml_symbols = []

    gml_label_styles = []

    gml_feature_types = []

    gml_tobology_styles = []

    gml_geometry_styles = []

    # <<< gml_feature_style
    # @generated
    def __init__(self, feature_constraint='', feature_type_name='', semantic_type_identifier='', feature_type='', query_grammar='xquery', base_type='', version='', gml_symbols=[], gml_label_styles=[], gml_feature_types=[], gml_tobology_styles=[], gml_geometry_styles=[], **kw_args):
        """ Initialises a new 'GmlFeatureStyle' instance.
        """
        self.feature_constraint = feature_constraint
        self.feature_type_name = feature_type_name
        self.semantic_type_identifier = semantic_type_identifier
        self.feature_type = feature_type
        self.query_grammar = query_grammar
        self.base_type = base_type
        self.version = version
        self.gml_symbols = gml_symbols
        self.gml_label_styles = gml_label_styles
        self.gml_feature_types = gml_feature_types
        self.gml_tobology_styles = gml_tobology_styles
        self.gml_geometry_styles = gml_geometry_styles

        super(GmlFeatureStyle, self).__init__(**kw_args)
    # >>> gml_feature_style


class GmlTextSymbol(GmlSymbol):
    """ Used for styling text labels, i.e., for rendering them according to various graphical parameters.
    """
    # The minimum font size allowed. 
    min_font_size = 0

    # The name of the field of the class being annotated. Most objects will include name, description, and aliasName. Many objects may contain other fields such as comment, status, etc. 
    field_id = ''

    # Text-label content. If the value is not provided, then no text will be rendered. 
    label = ''

    # The type-classification of a label. 
    label_type = ''

    # Generic method for capturing all unspecified information pertaining to the TextSymbol. 
    property = ''

    gml_label_placement = None

    gml_diagram_object = None

    gml_font = None

    gml_halo = None

    gml_fill = None

    # <<< gml_text_symbol
    # @generated
    def __init__(self, min_font_size=0, field_id='', label='', label_type='', property='', gml_label_placement=None, gml_diagram_object=None, gml_font=None, gml_halo=None, gml_fill=None, **kw_args):
        """ Initialises a new 'GmlTextSymbol' instance.
        """
        self.min_font_size = min_font_size
        self.field_id = field_id
        self.label = label
        self.label_type = label_type
        self.property = property
        self.gml_label_placement = gml_label_placement
        self.gml_diagram_object = gml_diagram_object
        self.gml_font = gml_font
        self.gml_halo = gml_halo
        self.gml_fill = gml_fill

        super(GmlTextSymbol, self).__init__(**kw_args)
    # >>> gml_text_symbol


class GmlLineGeometry(GmlDiagramObject):
    """ Typically used for rendering linear assets and/or power system resources.
    """
    # For dynamic network update (i.e. colouring) purposes 
    source_side = ''

    # <<< gml_line_geometry
    # @generated
    def __init__(self, source_side='', **kw_args):
        """ Initialises a new 'GmlLineGeometry' instance.
        """
        self.source_side = source_side

        super(GmlLineGeometry, self).__init__(**kw_args)
    # >>> gml_line_geometry


class GmlLineSymbol(GmlSymbol):
    """ Used to style a 'stroke' along a linear geometry type, such as a string of line segments.
    """
    # For dynamic network update (i.e. colouring) purposes 
    source_side = ''

    gml_stroke = None

    gml_diagram_object = None

    # <<< gml_line_symbol
    # @generated
    def __init__(self, source_side='', gml_stroke=None, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlLineSymbol' instance.
        """
        self.source_side = source_side
        self.gml_stroke = gml_stroke
        self.gml_diagram_object = gml_diagram_object

        super(GmlLineSymbol, self).__init__(**kw_args)
    # >>> gml_line_symbol


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
    gml_diagram_object = None

    gml_graphic = None

    # <<< gml_point_symbol
    # @generated
    def __init__(self, gml_diagram_object=None, gml_graphic=None, **kw_args):
        """ Initialises a new 'GmlPointSymbol' instance.
        """
        self.gml_diagram_object = gml_diagram_object
        self.gml_graphic = gml_graphic

        super(GmlPointSymbol, self).__init__(**kw_args)
    # >>> gml_point_symbol


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
    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
    red_sourcename = ''

    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
    blue_sourcename = ''

    # Specifies the level of translucency to use when rendering the Graphic. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0. 
    opacity = 0.0

    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
    green_source_name = ''

    # A single colour channel may be selected to display in grayscale. Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation. 
    gray_sourcename = ''

    # The ReliefFactor gives the amount of exaggeration to use for the height of the 'hills'. A value of around 55 (times) gives reasonable results for Earth-based DEMs. The default value is system-dependent. 
    relief_factor = ''

    # Tells a system how to behave when multiple raster images in a layer overlap each other, for example with satellite-image scenes. 
    overlapbehaviour = ''

    # If the BrightnessOnly flag is 0 (false, default), the shading is applied to the layer being rendered as the current RasterSymbol. If BrightnessOnly is 1 (true), the shading is applied to the brightness of the colors in the rendering canvas generated so far by other layers, with the effect of relief-shading these other layers. 
    brighness_only = False

    gml_diagram_object = None

    # <<< gml_raster_symbol
    # @generated
    def __init__(self, red_sourcename='', blue_sourcename='', opacity=0.0, green_source_name='', gray_sourcename='', relief_factor='', overlapbehaviour='', brighness_only=False, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlRasterSymbol' instance.
        """
        self.red_sourcename = red_sourcename
        self.blue_sourcename = blue_sourcename
        self.opacity = opacity
        self.green_source_name = green_source_name
        self.gray_sourcename = gray_sourcename
        self.relief_factor = relief_factor
        self.overlapbehaviour = overlapbehaviour
        self.brighness_only = brighness_only
        self.gml_diagram_object = gml_diagram_object

        super(GmlRasterSymbol, self).__init__(**kw_args)
    # >>> gml_raster_symbol


class GmlPolygonSymbol(GmlSymbol):
    """ Used to draw a polygon (or other area-type geometries), including filling its interior and stroking its border (outline).
    """
    gml_stroke = None

    gml_diagram_object = None

    gml_fill = None

    # <<< gml_polygon_symbol
    # @generated
    def __init__(self, gml_stroke=None, gml_diagram_object=None, gml_fill=None, **kw_args):
        """ Initialises a new 'GmlPolygonSymbol' instance.
        """
        self.gml_stroke = gml_stroke
        self.gml_diagram_object = gml_diagram_object
        self.gml_fill = gml_fill

        super(GmlPolygonSymbol, self).__init__(**kw_args)
    # >>> gml_polygon_symbol


# <<< inf_gmlsupport
# @generated
# >>> inf_gmlsupport
