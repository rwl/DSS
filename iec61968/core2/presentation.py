# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" In addition to providing physical location information, real world objects of the CIM may also be represented on various types of displays, diagrams, maps, etc. through the concept of a diagram object, which is a specialization of Location.  Note that a manner for accomplishing this is elaborated on in the 'GML Support' package.
"""

from iec61968.documentation.documentinheritance import Document

# <<< imports
# @generated
# >>> imports

class Diagram(Document):
    """ GML and/or other means are used for rendering objects on various types (geographic, schematic, other) of displays and maps associated with various coordinate systems. 
    """
    # The type of diagram, for example, schematic, geographic, internal view (e.g., inside a vault or cabinet), design sketch, etc. 
    diagram_type = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    design_locations = []

    gml_coordinate_system = None

    gml_diagram_objects = []

    # <<< diagram
    # @generated
    def __init__(self, diagram_type='', status='', status_date_time='', status_remarks='', design_locations=[], gml_coordinate_system=None, gml_diagram_objects=[], **kw_args):
        """ Initialises a new 'Diagram' instance.
        """
        self.diagram_type = diagram_type
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.design_locations = design_locations
        self.gml_coordinate_system = gml_coordinate_system
        self.gml_diagram_objects = gml_diagram_objects

        super(Diagram, self).__init__(**kw_args)
    # >>> diagram


class PresentationVersion(object):
 
    version = ''

 
    date = ''

    # <<< presentation_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'PresentationVersion' instance.
        """
        self.version = version
        self.date = date

        super(PresentationVersion, self).__init__(**kw_args)
    # >>> presentation_version


class Map(Diagram):
    """ A Map is a type of diagram that is usually printed on paper.  It normally depicts part of the earth's surface, showing utility assets, right of ways, topological data, coordinates, grids, etc.  Maps vary depending on wether they are used for dispatch, design, schematic, etc.
    """
    #  Type of map. 
    map_type = ''

    # Page number for particular set of maps specified by mapType. 
    map_loc_page = ''

    # Map grid number. 
    map_loc_grid = ''

    # <<< map
    # @generated
    def __init__(self, map_type='', map_loc_page='', map_loc_grid='', **kw_args):
        """ Initialises a new 'Map' instance.
        """
        self.map_type = map_type
        self.map_loc_page = map_loc_page
        self.map_loc_grid = map_loc_grid

        super(Map, self).__init__(**kw_args)
    # >>> map


# <<< presentation
# @generated
# >>> presentation
