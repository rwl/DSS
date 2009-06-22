# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" This package contains portions of the model defined by graphic standards such as those proposed by OpenGIS Consortium referred to as the Geography Markup Language (GML) .  In similar fashion as the 'ERP Support' package facilitates integration among electric utility applications (CIM) and enterprise resource planning (ERP) applications, this package facilitates integration with GIS (Geographical Information Systems) and other applications.  Rather than inventing new CIM classes that accomplish similar functionality as in existing GML, the preferred approach is to use and extend 'Gml' classes as appropriate.   Note that care has been taken to seperate the geometry of features from how features can be graphically represented.   GML supports the concept of a geographic feature, which is 'an abstraction of a real world phenomenon; it is a geographic feature if it is associated with a location relative to the Earth'.  So a digital representation of the real world can be thought of as a set of features. The state of a feature is defined by a set of properties, where each property can be thought of as a {name, type, value} triple.    The number of properties a feature may have, together with their names and types, are determined by its type definition.  Geographic features with geometry are those with properties that may be geometry-valued.
"""

from iec61968.core2.locations import Location

# <<< imports
# @generated
# >>> imports

class GmlFeatureStyle():
    """ Styling features means styling a particular aspect or aspects of a feature. We can style feature geometry, topology or display arbitrary text string.
    """
    # In order that that the version numbers can be identified when the SLD pieces are used independently.
    version = ''

    # Identifies the specific feature type that the feature-type style is for.
    feature_type_name = ''

    # The SemanticTypeIdentifier is experimental in GML and is intended to be used to identify what the feature style is suitable to be used for using community-controlled name(s). For example, a single style may be suitable to use with many different feature types.
    semantic_type_identifier = ''

    # The value of this property which is defined as an enumeration specifies the grammar that is used in the content of the gml:featureConstraint element The enumeration allows for three values: Xpath, Xquery and other.
    query_grammar = ''

    # This property is used to further constrain the feature instance set to which the style applies. It is optional and it's value is an XPath expression. If the property does not exist, the style applies to all feature instances selected by featureType or baseType attribute.
    feature_constraint = ''

    # The simplest and most common way of relating features and styles is by using this attribute. It’s value will be the declared name of a feature, instances of which we want to style. For example, if the value is Switch, the FeatureStyle object will simply apply to all Switch features.
    featuretype = ''

    # Another way of selecting the feature instances to which the style applies is to specify, as the value of this attribute, the name of the base type from which feature or features derive.
    basetype = ''

    gml_label_styles = []

    gml_feature_types = []

    # <<< gml_feature_style
    # @generated
    def __init__(self, version='', feature_type_name='', semantic_type_identifier='', query_grammar='', feature_constraint='', featuretype='', basetype='', gml_label_styles=[], gml_feature_types=[], **kw_args):
        """ Initialises a new 'GmlFeatureStyle' instance.
        """
        self.version = version
        self.feature_type_name = feature_type_name
        self.semantic_type_identifier = semantic_type_identifier
        self.query_grammar = query_grammar
        self.feature_constraint = feature_constraint
        self.featuretype = featuretype
        self.basetype = basetype
        self.gml_label_styles = gml_label_styles
        self.gml_feature_types = gml_feature_types

        super(GmlFeatureStyle, self).__init__(**kw_args)
    # >>> gml_feature_style


class GmlGeometryStyle():
    """ GeometryStyle describes the style for one geometry of a feature. Any number of geometry style descriptors can be assigned to one feature style. This is usually required for features with multiple geometry properties.
    """
    # The geometryProperty attribute on the GeometryStyle class specifies the name of the geometry property of a feature to which this GeometryStyle applies.
    geometry_property = ''

    # It is necessary to specify the geometry type using geometryType attribute as well since the application schema of the geometry property may allow different geometries as it's value.
    geometry_type = ''

    # The symbol element specifies a graphical symbol used to render a geometry or a topology. A symbol is a description of graphical attributes of a graphical object without a particular, implicit meaning. It can be a description of a line, circle, polygon or more complex drawing.
    symbol = ''

    gml_label_style = None

    # <<< gml_geometry_style
    # @generated
    def __init__(self, geometry_property='', geometry_type='', symbol='', gml_label_style=None, **kw_args):
        """ Initialises a new 'GmlGeometryStyle' instance.
        """
        self.geometry_property = geometry_property
        self.geometry_type = geometry_type
        self.symbol = symbol
        self.gml_label_style = gml_label_style

        super(GmlGeometryStyle, self).__init__(**kw_args)
    # >>> gml_geometry_style


class GmlLabelStyle():
    """ Describes the style for the text that is to be displayed along with the graphical representation of a feature. The content of the label is not necessarily defined in the GML data set. More precisely, the content can be static text specified in the style itself and the text from the GML data set. Label style has two elements: gml:style that specifies the style and gml:label that is used to compose the label content.
    """
    # Allows both text content and unbounded number of gml:LabelExpression elements. The value of gml:LabelExpression element is an XPath expression that selects the value of some property of the feature.
    label_expression = ''

    # Used to specify the style of the rendered text. The CSS2 styling expressions grammar should be used to express graphic properties
    style = ''

    # Allows us to specify a transformation expression that will be applied to the symbol in the rendering phase. It's type is xsd:string and the value is specified in the SVG specification (transform attribute).
    transform = ''

    gml_feature_style = None

    gml_geometry_styles = []

    gml_topology_styles = []

    # <<< gml_label_style
    # @generated
    def __init__(self, label_expression='', style='', transform='', gml_feature_style=None, gml_geometry_styles=[], gml_topology_styles=[], **kw_args):
        """ Initialises a new 'GmlLabelStyle' instance.
        """
        self.label_expression = label_expression
        self.style = style
        self.transform = transform
        self.gml_feature_style = gml_feature_style
        self.gml_geometry_styles = gml_geometry_styles
        self.gml_topology_styles = gml_topology_styles

        super(GmlLabelStyle, self).__init__(**kw_args)
    # >>> gml_label_style


class GmlTopologyStyle():
    """ Describes the style for one topology property. Similarly to the Geometry style, a feature can have multiple topology properties, thus multiple topology style descriptors can be specified within one feature style.
    """
    gml_lable_style = None

    # <<< gml_topology_style
    # @generated
    def __init__(self, gml_lable_style=None, **kw_args):
        """ Initialises a new 'GmlTopologyStyle' instance.
        """
        self.gml_lable_style = gml_lable_style

        super(GmlTopologyStyle, self).__init__(**kw_args)
    # >>> gml_topology_style


class GmlSymbol():
    """ A symbol describes how a feature is to appear on a map or display. The symbol describes not just the shape that should appear but also such graphical properties as color and opacity.
    """
    # The version of the Symbol
    version = ''

    # The Symbol type
    type = ''

    # The level (of the map) where the symbol exists or the zoom levels at which this diagram object is displayed.  As a way of de-cluttering displays, for example, some symbols and annotations are only shown when zoomed in.
    level = ''

    gml_feature_styles = []

    gml_base_symbol = None

    # <<< gml_symbol
    # @generated
    def __init__(self, version='', type='', level='', gml_feature_styles=[], gml_base_symbol=None, **kw_args):
        """ Initialises a new 'GmlSymbol' instance.
        """
        self.version = version
        self.type = type
        self.level = level
        self.gml_feature_styles = gml_feature_styles
        self.gml_base_symbol = gml_base_symbol

        super(GmlSymbol, self).__init__(**kw_args)
    # >>> gml_symbol


class RULE(object):
    """ Rules are used to group rendering instructions by feature-property conditions and map scales.
    """
    pass
    # <<< rule
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'RULE' instance.
        """

        super(RULE, self).__init__(**kw_args)
    # >>> rule


class GmlStroke():
    """ The Stroke element encapsulates the graphical symbolization parameters for linear geometries.
    """
    # Specifies the level of translucency to use when rendering the stroke. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0
    opacity = ''

    # The absolute width (thickness) of a stroke in pixels encoded as a float. The default is 1.0. Fractional numbers are allowed (with a system-dependent interpretation) but negative numbers are not.
    width = ''

    # Enumerated values telling how line strings should be joined (between line segments). The values are represented as content strings.  The allowed values for line join are 'mitre', 'round', and 'bevel'. The default values are system-dependent.
    linejoin = ''

    # Enumerated values telling how line strings should be capped (at the two ends of the line string). The values are represented as content strings.  The allowed values for line cap are 'butt', 'round', and 'square'. The default values are system-dependent.
    line_cap = ''

    # The name of a defined line style. Usually will be an enumerated value and will be system-specific.
    line_style = ''

    # Encodes a dash pattern as a series of space separated floats. The first number gives the length in pixels of dash to draw, the second gives the amount of space to leave, and this pattern repeats. If an odd number of values is given, then the pattern is expanded by repeating it twice to give an even number of values. Decimal values have a system-dependent interpretation (usually depending on whether antialiasing is being used). The default is to draw an unbroken line.
    dash_array = ''

    # Specifies the distance as a float into the 'stroke-dasharray' pattern at which to start drawing.
    dash_offset = ''

    gml_line_symbols = []

    gml_polygon_symbols = []

    gml_marks = []

    gml_svg_parameters = []

    gml_colour = None

    # <<< gml_stroke
    # @generated
    def __init__(self, opacity='', width='', linejoin='', line_cap='', line_style='', dash_array='', dash_offset='', gml_line_symbols=[], gml_polygon_symbols=[], gml_marks=[], gml_svg_parameters=[], gml_colour=None, **kw_args):
        """ Initialises a new 'GmlStroke' instance.
        """
        self.opacity = opacity
        self.width = width
        self.linejoin = linejoin
        self.line_cap = line_cap
        self.line_style = line_style
        self.dash_array = dash_array
        self.dash_offset = dash_offset
        self.gml_line_symbols = gml_line_symbols
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_marks = gml_marks
        self.gml_svg_parameters = gml_svg_parameters
        self.gml_colour = gml_colour

        super(GmlStroke, self).__init__(**kw_args)
    # >>> gml_stroke


class GmlFill():
    """ Specifies how the area of the geometry will be filled.
    """
    # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0
    opacity = ''

    gml_polygon_symbols = []

    gml_text_symbols = []

    gml_svg_parameters = []

    gml_marks = []

    gml_colour = None

    # <<< gml_fill
    # @generated
    def __init__(self, opacity='', gml_polygon_symbols=[], gml_text_symbols=[], gml_svg_parameters=[], gml_marks=[], gml_colour=None, **kw_args):
        """ Initialises a new 'GmlFill' instance.
        """
        self.opacity = opacity
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_text_symbols = gml_text_symbols
        self.gml_svg_parameters = gml_svg_parameters
        self.gml_marks = gml_marks
        self.gml_colour = gml_colour

        super(GmlFill, self).__init__(**kw_args)
    # >>> gml_fill


class GmlHalo():
    """ A Halo is a type of Fill that is applied to the backgrounds of font glyphs. The use of halos greatly improves the readability of text labels.
    """
    # The Radius element gives the absolute size of a halo radius in pixels encoded as a floating-point number. The radius is taken from the outside edge of a font glyph to extend the area of coverage of the glyph (and the inside edge of “holes” in the glyphs). The default radius is one pixel. Negative values are not allowed.
    radius = ''

    # Specifies the level of translucency to use when rendering the Fill. The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0
    opacity = ''

    gml_text_symbols = []

    # <<< gml_halo
    # @generated
    def __init__(self, radius='', opacity='', gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlHalo' instance.
        """
        self.radius = radius
        self.opacity = opacity
        self.gml_text_symbols = gml_text_symbols

        super(GmlHalo, self).__init__(**kw_args)
    # >>> gml_halo


class GmlFont():
    """ Identifies a font of a certain family, style, and size.
    """
    # Gives the family name of a font to use. Allowed values are system-dependent. Any number of font-family attributes may be given and they are assumed to be in preferred order.
    family = ''

    # The style to use for a font. The allowed values are 'normal', 'italic', and 'oblique'.
    style = ''

    # The amount of weight or boldness to use for a font. Allowed values are 'normal' and 'bold'.
    weight = ''

    # The size to use for the font in pixels. The default is defined to be 10 pixels, though various systems may have restrictions on what sizes are available.
    size = ''

    # True if 'size' is expressed in absolute values.  Default is false.
    absolute_size = ''

    gml_text_symbols = []

    gml_svg_parameters = []

    gml_colour = None

    # <<< gml_font
    # @generated
    def __init__(self, family='', style='', weight='', size='', absolute_size='', gml_text_symbols=[], gml_svg_parameters=[], gml_colour=None, **kw_args):
        """ Initialises a new 'GmlFont' instance.
        """
        self.family = family
        self.style = style
        self.weight = weight
        self.size = size
        self.absolute_size = absolute_size
        self.gml_text_symbols = gml_text_symbols
        self.gml_svg_parameters = gml_svg_parameters
        self.gml_colour = gml_colour

        super(GmlFont, self).__init__(**kw_args)
    # >>> gml_font


class GmlGraphic():
    """ A 'graphic symbol' with an inherent shape, color(s), and possibly size. A 'graphic' can be very informally defined as 'a little picture' and can be of either a raster or vector-graphic source type.
    """
    # Specifies the level of translucency to use when rendering the Graphic.The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0
    opacity = ''

    # Gives the absolute size of the graphic in pixels encoded as a floatingpoint number. The default size for an object is context-dependent. Negative values are not allowed.
    size = ''

    # The minimum symbol size allowed.
    min_size = ''

    # Gives the rotation of a graphic in the clockwise direction about its center point in decimal degrees, encoded as a floating-point number. Negative values mean counter-clockwise rotation. The default value is 0.0 (no rotation). Note that there is no connection between source geometry types and rotations; the point used for plotting has no inherent direction. Also, the point within the graphic about which it is rotated is format dependent. If a format does not include an inherent rotation point, then the point of rotation should be the centroid.
    rotation = ''

    # Horizontal scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbar).
    x_scale = ''

    # Vertical scaling factor of normal symbol - particularly applicable to busbars if not described through a sequence of gmlPositions (e.g., Busbars).
    y_scale = ''

    # The identifier of the symbol, if not derived from the type of CIM object (PSR, Asset, Organisation, Document, etc.) gmlSymbolPlacement is associated with.
    symbol_id = ''

    gml_point_symbols = []

    gml_marks = []

    # <<< gml_graphic
    # @generated
    def __init__(self, opacity='', size='', min_size='', rotation='', x_scale='', y_scale='', symbol_id='', gml_point_symbols=[], gml_marks=[], **kw_args):
        """ Initialises a new 'GmlGraphic' instance.
        """
        self.opacity = opacity
        self.size = size
        self.min_size = min_size
        self.rotation = rotation
        self.x_scale = x_scale
        self.y_scale = y_scale
        self.symbol_id = symbol_id
        self.gml_point_symbols = gml_point_symbols
        self.gml_marks = gml_marks

        super(GmlGraphic, self).__init__(**kw_args)
    # >>> gml_graphic


class GmlMark():
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


class GmlSvgParameter():
    """ A 'SvgParameter' refers to an SVG/CSS graphical-formatting parameter. The parameter is identified using the 'name' attribute and the content of the element gives the SVG/CSS-coded value.
    """
    # The sVGAttribute of the SvgParameter is defined here. (e.g for 'Stroke', the following SvgParameters may be used: 'stroke' (color), 'stroke-opacity', 'stroke-width', 'stroke-linejoin', 'stroke-linecap', 'stroke-dasharray', and 'stroke-dashoffset'. Others are not officially supported.)
    vgattribute = ''

    # The SVG/CSS-coded value of the associated SvgAttribute.
    value = ''

    gml_stokes = []

    gml_fills = []

    gml_fonts = []

    # <<< gml_svg_parameter
    # @generated
    def __init__(self, vgattribute='', value='', gml_stokes=[], gml_fills=[], gml_fonts=[], **kw_args):
        """ Initialises a new 'GmlSvgParameter' instance.
        """
        self.vgattribute = vgattribute
        self.value = value
        self.gml_stokes = gml_stokes
        self.gml_fills = gml_fills
        self.gml_fonts = gml_fonts

        super(GmlSvgParameter, self).__init__(**kw_args)
    # >>> gml_svg_parameter


class GmlBaseSymbol():
    """ The BaseSymbol class allows referencing & extension of external symbols, which may be stored in a symbol repository. The graphical properties from a referenced external symbol override the ones read in from the base symbol.
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


class GmlLabelplacement():
    """ Used to position a label relative to a point or a line string
    """
    # The type of 'LabelPlacement' which in turn specifies where and how a text label should be rendered relative to a geometry.
    type_label_place = ''

    # Gives the X displacement from the main-geometry point to render a text label
    displacement_x = ''

    # Gives the Y displacement from the main-geometry point to render a text label
    displacement_y = ''

    # Gives the x-coordinate location inside of a label to use for anchoring the label to the main-geometry point.
    anchor_x = ''

    # Gives the y-coordinate location inside of a label to use for anchoring the label to the main-geometry point.
    anchor_y = ''

    # The clockwise rotation of the label in degrees from the normal direction for a font.
    rotation = ''

    # The perpendicular distance away from a line to draw a label. The distance is in pixels and is positive to the left-hand side of the line string. Negative numbers mean right. The default offset is 0.
    offset = ''

    gml_text_symbols = []

    # <<< gml_labelplacement
    # @generated
    def __init__(self, type_label_place='', displacement_x='', displacement_y='', anchor_x='', anchor_y='', rotation='', offset='', gml_text_symbols=[], **kw_args):
        """ Initialises a new 'GmlLabelplacement' instance.
        """
        self.type_label_place = type_label_place
        self.displacement_x = displacement_x
        self.displacement_y = displacement_y
        self.anchor_x = anchor_x
        self.anchor_y = anchor_y
        self.rotation = rotation
        self.offset = offset
        self.gml_text_symbols = gml_text_symbols

        super(GmlLabelplacement, self).__init__(**kw_args)
    # >>> gml_labelplacement


class GmlColour():
    """ Gives the solid color that will be used. The color value is RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign. The hexadecimal  digits between A and F may be in either uppercase or lowercase. For example, full red is encoded as “#ff0000” (with no quotation marks). If the “stroke” CssParameter element is absent, the default color is defined to be black (“#000000”).
    """
    # The color value for RED (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.)
    red = ''

    # The color value for GREEN (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.)
    green = ''

    # The color value for BLUE (RGB-encoded using two hexadecimal digits per primary-color component, in the order Red, Green, Blue, prefixed with a hash (#) sign.).
    blue = ''

    gml_fills = []

    gml_strokes = []

    gml_fonts = []

    # <<< gml_colour
    # @generated
    def __init__(self, red='', green='', blue='', gml_fills=[], gml_strokes=[], gml_fonts=[], **kw_args):
        """ Initialises a new 'GmlColour' instance.
        """
        self.red = red
        self.green = green
        self.blue = blue
        self.gml_fills = gml_fills
        self.gml_strokes = gml_strokes
        self.gml_fonts = gml_fonts

        super(GmlColour, self).__init__(**kw_args)
    # >>> gml_colour


class GmlFeatureType():
    """ Type classification of feature
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


class FEATURESTYLE(object):
    pass
    # <<< featurestyle
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'FEATURESTYLE' instance.
        """

        super(FEATURESTYLE, self).__init__(**kw_args)
    # >>> featurestyle


class FEATURETYPE(object):
    pass
    # <<< featuretype
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'FEATURETYPE' instance.
        """

        super(FEATURETYPE, self).__init__(**kw_args)
    # >>> featuretype


class GmlObservation(object):
    """ A GML observation models the act of observing, often with a camera, a person or some form of instrument. An observation feature describes the 'metadata' associated with an information capture event, together with with a value for the result of the observation. The basic structures introduced in this class are intended to serve as the foundation for more comprehensive schemas for scientific, technical and engineering measurement schemas.
    """

    date_time = ''

    # Contains or points to a description of a sensor, instrument or procedure used for the observation.
    using = ''

    # Contains or points to the specimen, region or station which is the object of the observation
    target = ''

    # Indicates the result of the observation.
    result_of = ''

    change_items = []

    gml_mesure = None

    locations = []

    # <<< gml_observation
    # @generated
    def __init__(self, date_time='', using='', target='', result_of='', change_items=[], gml_mesure=None, locations=[], **kw_args):
        """ Initialises a new 'GmlObservation' instance.
        """
        self.date_time = date_time
        self.using = using
        self.target = target
        self.result_of = result_of
        self.change_items = change_items
        self.gml_mesure = gml_mesure
        self.locations = locations

        super(GmlObservation, self).__init__(**kw_args)
    # >>> gml_observation


class GmlValue():
    """ Used for direct representation of values.
    """

    value = ''


    date_time = ''


    time_period = ''

    gml_measure = None

    measurement_value = None

    # <<< gml_value
    # @generated
    def __init__(self, value='', date_time='', time_period='', gml_measure=None, measurement_value=None, **kw_args):
        """ Initialises a new 'GmlValue' instance.
        """
        self.value = value
        self.date_time = date_time
        self.time_period = time_period
        self.gml_measure = gml_measure
        self.measurement_value = measurement_value

        super(GmlValue, self).__init__(**kw_args)
    # >>> gml_value


class GmlMeasure():
    """ The units of a quantity.
    """
    # Identifies the definition of a ratio scale or units by which the numeric value shall be multiplied, or an interval or position scale on which the value occurs.
    unit_of_measure = ''

    gml_values = []

    gml_observations = []

    unit = None

    # <<< gml_measure
    # @generated
    def __init__(self, unit_of_measure='', gml_values=[], gml_observations=[], unit=None, **kw_args):
        """ Initialises a new 'GmlMeasure' instance.
        """
        self.unit_of_measure = unit_of_measure
        self.gml_values = gml_values
        self.gml_observations = gml_observations
        self.unit = unit

        super(GmlMeasure, self).__init__(**kw_args)
    # >>> gml_measure


class GmlCoordinateSystem():
    """ A coordinate reference system consists of a set of coordinate system axes that is related to the earth through a datum that defines the size and shape of the earth or some abstract reference system such as those used for rendering schemantic diagrams, internal views of items such as cabinets, vaults, etc..  Geometries in GML indicate the coordinate reference system in which their measurements have been made.
    """

    scale = ''


    x_min = ''


    y_min = ''


    x_max = ''


    y_max = ''


    position_unit_name = ''

    diagrams = []

    gml_diagram_objects = []

    gml_positions = []

    # <<< gml_coordinate_system
    # @generated
    def __init__(self, scale='', x_min='', y_min='', x_max='', y_max='', position_unit_name='', diagrams=[], gml_diagram_objects=[], gml_positions=[], **kw_args):
        """ Initialises a new 'GmlCoordinateSystem' instance.
        """
        self.scale = scale
        self.x_min = x_min
        self.y_min = y_min
        self.x_max = x_max
        self.y_max = y_max
        self.position_unit_name = position_unit_name
        self.diagrams = diagrams
        self.gml_diagram_objects = gml_diagram_objects
        self.gml_positions = gml_positions

        super(GmlCoordinateSystem, self).__init__(**kw_args)
    # >>> gml_coordinate_system


class LOCATION(object):
    """ The location property describes the generalized location of the feature.  Note that the value of the location property is a complex type but is not an arbitrary GML object.The location of a GML feature can be a geometry, a location string or a location keyword.
    """
    pos = []

    features = []

    # <<< location
    # @generated
    def __init__(self, pos=[], features=[], **kw_args):
        """ Initialises a new 'LOCATION' instance.
        """
        self.pos = pos
        self.features = features

        super(LOCATION, self).__init__(**kw_args)
    # >>> location


class METADATA(object):
    """ Contains or refers to a metadata package that contains metadata properties.
    """
    features = []

    # <<< metadata
    # @generated
    def __init__(self, features=[], **kw_args):
        """ Initialises a new 'METADATA' instance.
        """
        self.features = features

        super(METADATA, self).__init__(**kw_args)
    # >>> metadata


class POS(object):
    """ One of a sequence of numbers designating the position of a point in N-dimensional space
    """
    location = None

    # <<< pos
    # @generated
    def __init__(self, location=None, **kw_args):
        """ Initialises a new 'POS' instance.
        """
        self.location = location

        super(POS, self).__init__(**kw_args)
    # >>> pos


class COORDINATEREFERENCESYSTEM(object):
    """ Association to a coordinate reference system, either referencing or containing the definition of that reference system.
    """
    geometeries = []

    # <<< coordinatereferencesystem
    # @generated
    def __init__(self, geometeries=[], **kw_args):
        """ Initialises a new 'COORDINATEREFERENCESYSTEM' instance.
        """
        self.geometeries = geometeries

        super(COORDINATEREFERENCESYSTEM, self).__init__(**kw_args)
    # >>> coordinatereferencesystem


class ENVELOPE(object):
    """ Envelope defines an extent using a pair of positions defining opposite corners in arbitrary dimensions. The first direct position is the 'lower corner' (a coordinate position consisting of all the minimal ordinates for each dimension for all points within the envelope), the second one the 'upper corner' (a coordinate position consisting of all the maximal ordinates for each dimension for all points within the envelope).
    """
    # A coordinate position consisting of all the minimal ordinates for each dimension for all points within the envelope.
    lower_corner = ''

    # A coordinate position consisting of all the maximal ordinates for each dimension for all points within the envelope.
    upper_corner = ''

    # A number designating the position of a point in N-dimensional space
    pos = ''

    feature_collection = []

    # <<< envelope
    # @generated
    def __init__(self, lower_corner='', upper_corner='', pos='', feature_collection=[], **kw_args):
        """ Initialises a new 'ENVELOPE' instance.
        """
        self.lower_corner = lower_corner
        self.upper_corner = upper_corner
        self.pos = pos
        self.feature_collection = feature_collection

        super(ENVELOPE, self).__init__(**kw_args)
    # >>> envelope


class FEATURE(object):
    """ A geographic feature is an abstraction of a real world phenomenon; it is a geographic feature if it is associated with a location relative to the Earth or an abstract reference system.  So a digital representation of the real world can be thought of as a set of features. The state of a feature is defined by a set of properties, where each property can be thought of as a {name, type, value} triple.
    """
    envelopes = []

    feature_collections = []

    metadata = None

    locations = []

    # <<< feature
    # @generated
    def __init__(self, envelopes=[], feature_collections=[], metadata=None, locations=[], **kw_args):
        """ Initialises a new 'FEATURE' instance.
        """
        self.envelopes = envelopes
        self.feature_collections = feature_collections
        self.metadata = metadata
        self.locations = locations

        super(FEATURE, self).__init__(**kw_args)
    # >>> feature


class FEATURECOLLECTION(object):
    """ A GML Feature Collection is a collection of GML feature instances that can behave as a GML feature.
    """
    features = []

    envelopes = []

    # <<< featurecollection
    # @generated
    def __init__(self, features=[], envelopes=[], **kw_args):
        """ Initialises a new 'FEATURECOLLECTION' instance.
        """
        self.features = features
        self.envelopes = envelopes

        super(FEATURECOLLECTION, self).__init__(**kw_args)
    # >>> featurecollection


class GmlDiagramObject(Location):
    """ Any of the magnitudes that serve to define the position of a point by reference to a fixed figure, system of lines, etc.
    """
    gml_point_symbols = []

    gml_line_symbols = []

    gml_polygon_symbols = []

    gml_text_symbols = []

    gml_raster_symbols = []

    diagrams = []

    gml_coordinate_systems = []

    # <<< gml_diagram_object
    # @generated
    def __init__(self, gml_point_symbols=[], gml_line_symbols=[], gml_polygon_symbols=[], gml_text_symbols=[], gml_raster_symbols=[], diagrams=[], gml_coordinate_systems=[], **kw_args):
        """ Initialises a new 'GmlDiagramObject' instance.
        """
        self.gml_point_symbols = gml_point_symbols
        self.gml_line_symbols = gml_line_symbols
        self.gml_polygon_symbols = gml_polygon_symbols
        self.gml_text_symbols = gml_text_symbols
        self.gml_raster_symbols = gml_raster_symbols
        self.diagrams = diagrams
        self.gml_coordinate_systems = gml_coordinate_systems

        super(GmlDiagramObject, self).__init__(**kw_args)
    # >>> gml_diagram_object


class GmlPosition(object):
    """ Position described by a single set of coordinates within a coordinate reference system.  Note that a sequence of x/y coordinate pairs may be specified to provide the physical location of non-point oriented objects like Cables, Ducts, etc. or for describing an area of an object like a substation or georgraphical zone.
    """
    # The position along the X axis of  the coordinate reference system.
    x_position = ''

    # The position along the Y axis of  the coordinate reference system.
    y_position = ''

    # The position of this position within a series of positions.  It is zero relative.
    sequence_number = ''

    # If applicable,the position along the Z axis of  the coordinate reference system.
    z_position = ''

    location = None

    gml_coordinate_system = None

    # <<< gml_position
    # @generated
    def __init__(self, x_position='', y_position='', sequence_number='', z_position='', location=None, gml_coordinate_system=None, **kw_args):
        """ Initialises a new 'GmlPosition' instance.
        """
        self.x_position = x_position
        self.y_position = y_position
        self.sequence_number = sequence_number
        self.z_position = z_position
        self.location = location
        self.gml_coordinate_system = gml_coordinate_system

        super(GmlPosition, self).__init__(**kw_args)
    # >>> gml_position


class GmlSupportVersion(object):

    version = ''


    date = ''

    # <<< gml_support_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'GmlSupportVersion' instance.
        """
        self.version = version
        self.date = date

        super(GmlSupportVersion, self).__init__(**kw_args)
    # >>> gml_support_version


class GmlSelector():
    """ A diagram element that allows selection by a user, i.e. as 'hyperNode' for navigating between diagrams, or as composite object representing multiple grouped objects
    """
    change_items = []

    locations = []

    # <<< gml_selector
    # @generated
    def __init__(self, change_items=[], locations=[], **kw_args):
        """ Initialises a new 'GmlSelector' instance.
        """
        self.change_items = change_items
        self.locations = locations

        super(GmlSelector, self).__init__(**kw_args)
    # >>> gml_selector


class GmlLineSymbol(GmlSymbol):
    """ A LineSymbol is used to style a 'stroke' along a linear geometry type, such as a string of line segments.
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


class GmlPolygonSymbol(GmlSymbol):
    """ Used to draw a polygon (or other area-type geometries), including filling its interior and stroking its border (outline).
    """
    gml_stroke = None

    gml_fill = None

    gml_diagram_object = None

    # <<< gml_polygon_symbol
    # @generated
    def __init__(self, gml_stroke=None, gml_fill=None, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlPolygonSymbol' instance.
        """
        self.gml_stroke = gml_stroke
        self.gml_fill = gml_fill
        self.gml_diagram_object = gml_diagram_object

        super(GmlPolygonSymbol, self).__init__(**kw_args)
    # >>> gml_polygon_symbol


class GmlTextSymbol(GmlSymbol):
    """ Used for styling text labels
    """
    # Used to provide text-label content. If a Label value is not provided, then no text will be rendered.
    label = ''

    # The type-classification of a label. The Label element is used to provide text-label content.
    label_type = ''

    #  The minimum font size allowed.
    min_font_size = ''

    # A 'TextSymbol' is used to render text labels according to various graphical parameters. 'Property' provides a generic method for capturing all unspecified information pertaining to the TextSymbol.
    property = ''

    # The name of the field of the class being annotated.  Most objects will include name, description, and aliasName.  Many objects may contain other fields such as comment, status, etc.
    field_id = ''

    gml_fill = None

    gml_font = None

    gml_label_placement = None

    gml_diagram_object = None

    gml_halo = None

    # <<< gml_text_symbol
    # @generated
    def __init__(self, label='', label_type='', min_font_size='', property='', field_id='', gml_fill=None, gml_font=None, gml_label_placement=None, gml_diagram_object=None, gml_halo=None, **kw_args):
        """ Initialises a new 'GmlTextSymbol' instance.
        """
        self.label = label
        self.label_type = label_type
        self.min_font_size = min_font_size
        self.property = property
        self.field_id = field_id
        self.gml_fill = gml_fill
        self.gml_font = gml_font
        self.gml_label_placement = gml_label_placement
        self.gml_diagram_object = gml_diagram_object
        self.gml_halo = gml_halo

        super(GmlTextSymbol, self).__init__(**kw_args)
    # >>> gml_text_symbol


class GmlPointSymbol(GmlSymbol):
    """ A PointSymbol is used to draw a 'graphic' at a point.
    """
    gml_graphic = None

    gml_diagram_object = None

    # <<< gml_point_symbol
    # @generated
    def __init__(self, gml_graphic=None, gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlPointSymbol' instance.
        """
        self.gml_graphic = gml_graphic
        self.gml_diagram_object = gml_diagram_object

        super(GmlPointSymbol, self).__init__(**kw_args)
    # >>> gml_point_symbol


class GmlRasterSymbol(GmlSymbol):
    """ Describes how to render raster/matrix-coverage data (e.g., satellite photos, DEMs).
    """
    # Specifies the level of translucency to use when rendering the Graphic.The value is encoded as a floating-point value between 0.0 and 1.0 with 0.0 representing completely transparent and 1.0 representing completely opaque, with a linear scale of translucency for intermediate values. The default value is 1.0
    opacity = ''

    # Tells a system how to behave when multiple raster images in a layer overlap each other, for example with  satellite-image scenes.
    overlapbehaviour = ''

    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation.
    red_sourcename = ''

    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation.
    green_source_name = ''

    # Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation.
    blue_sourcename = ''

    # A single colour channel may be selected to display in grayscale. Colour Channels are identified by a system and data-dependent character identifier. Contrast enhancement may be applied to each channel in isolation.
    gray_sourcename = ''

    # If the BrightnessOnly flag is 0 (false, default), the shading is applied to the layer being rendered as the current RasterSymbol. If BrightnessOnly is 1 (true), the shading is applied to the brightness of the colors in the rendering canvas generated so far by other layers, with the effect of relief-shading these other layers.
    brighness_only = ''

    # The ReliefFactor gives the amount of exaggeration to use for the height of the 'hills'. A value of around 55 (times) gives reasonable results for Earth-based DEMs. The default value is system-dependent.
    relief_factor = ''

    gml_diagram_object = None

    # <<< gml_raster_symbol
    # @generated
    def __init__(self, opacity='', overlapbehaviour='', red_sourcename='', green_source_name='', blue_sourcename='', gray_sourcename='', brighness_only='', relief_factor='', gml_diagram_object=None, **kw_args):
        """ Initialises a new 'GmlRasterSymbol' instance.
        """
        self.opacity = opacity
        self.overlapbehaviour = overlapbehaviour
        self.red_sourcename = red_sourcename
        self.green_source_name = green_source_name
        self.blue_sourcename = blue_sourcename
        self.gray_sourcename = gray_sourcename
        self.brighness_only = brighness_only
        self.relief_factor = relief_factor
        self.gml_diagram_object = gml_diagram_object

        super(GmlRasterSymbol, self).__init__(**kw_args)
    # >>> gml_raster_symbol


class GmlLineGeometry(GmlDiagramObject):
    """ Typically includes all assets from LinearAssetHierarchy package.
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


class GmlPointGeometry(GmlDiagramObject):
    """ Typically includes power system resources (form tand/or physical assets (from PointAssetHierarchy package).
    """
    pass
    # <<< gml_point_geometry
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GmlPointGeometry' instance.
        """

        super(GmlPointGeometry, self).__init__(**kw_args)
    # >>> gml_point_geometry


class GmlPolygonGeometry(GmlDiagramObject):
    """ Polygons are used to to show the footprint of substations, sites, service territories, tax districts, school districts, etc.
    """
    pass
    # <<< gml_polygon_geometry
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GmlPolygonGeometry' instance.
        """

        super(GmlPolygonGeometry, self).__init__(**kw_args)
    # >>> gml_polygon_geometry


class LOCATIONKEYWORD(LOCATION):
    """ A keyword adds an attribute codeSpace to a term, where the value of the (optional) codeSpace should indicate a dictionary, thesaurus, classification scheme, authority, or pattern for the term.
    """
    pass
    # <<< locationkeyword
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'LOCATIONKEYWORD' instance.
        """

        super(LOCATIONKEYWORD, self).__init__(**kw_args)
    # >>> locationkeyword


class LOCATIONSTRING(LOCATION):
    """ A location string is a text string which should describe the location.
    """
    pass
    # <<< locationstring
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'LOCATIONSTRING' instance.
        """

        super(LOCATIONSTRING, self).__init__(**kw_args)
    # >>> locationstring


class GEOMETRY(LOCATION):
    """ Class of object that describes the location, shape or extent of a geographic feature.
    """
    coordinate_reference_systems = []

    # <<< geometry
    # @generated
    def __init__(self, coordinate_reference_systems=[], **kw_args):
        """ Initialises a new 'GEOMETRY' instance.
        """
        self.coordinate_reference_systems = coordinate_reference_systems

        super(GEOMETRY, self).__init__(**kw_args)
    # >>> geometry


class POINT(GEOMETRY):
    """ 0-dimensional geometric primitive, representing a position.
    """
    pass
    # <<< point
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'POINT' instance.
        """

        super(POINT, self).__init__(**kw_args)
    # >>> point


class SURFACE(GEOMETRY):
    """ A Surface is a 2-dimensional primitive and is composed of one or more surface patches. The surface patches are connected to one another. The orientation of the surface is positive ('up'). The orientation of a surface chooses an 'up' direction through the choice of the upward normal, which, if the surface is not a cycle, is the side of the surface from which the exterior boundary appears counterclockwise. Reversal of the surface orientation reverses the curve orientation of each boundary component, and interchanges the conceptual 'up' and 'down' direction of the surface. If the surface is the boundary of a solid, the 'up' direction is usually outward. For closed surfaces, which have no boundary, the up direction is that of the surface patches, which must be consistent with one another. Its included surface patches describe the interior structure of the Surface
    """
    pass
    # <<< surface
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'SURFACE' instance.
        """

        super(SURFACE, self).__init__(**kw_args)
    # >>> surface


class CURVE(GEOMETRY):
    """ 1-dimensional geometric primitive, representing the continuous image of a line.    NOTE The boundary of a curve is the set of points at either end of the curve. If the curve is a cycle, the two ends are identical, and the curve (if topologically closed) is considered to not have a boundary. The first point is called the start point, and the last is the end point. Connectivity of the curve is guaranteed by the 'continuous image of a line' clause. A topological theorem states that a continuous image of a connected set is connected.
    """
    pass
    # <<< curve
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'CURVE' instance.
        """

        super(CURVE, self).__init__(**kw_args)
    # >>> curve


# <<< gml_support
# @generated
# >>> gml_support
