# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Linear assets package.
"""

from iec61968.assets.assetbasics import Asset
from iec61968.assets.pointassethierarchy import ElectricalAsset
from iec61968.assets.pointassethierarchy import ElectricalAssetModel

# <<< imports
# @generated
# >>> imports

class DuctBank(Asset):
    """  A duct bank may contain many ducts.  Each duct contains individual lines that are expressed as linear assets (thereby describing each line's physical asset characteristics), which are each associated with ACLineSegments and other classes describing their electrical characteristics.
    """
    #  Number of ducts in duct bank. 
    num_ducts = ''

    #  Number of circuits in duct bank.  Refer to associations between a duct (LinearAsset) and an ACLineSegment to understand which circuits are in which ducts. 
    num_circuits = ''

    duct_band_type_asset = None

    cable_assets = []

    # <<< duct_bank
    # @generated
    def __init__(self, num_ducts='', num_circuits='', duct_band_type_asset=None, cable_assets=[], **kw_args):
        """ Initialises a new 'DuctBank' instance.
        """
        self.num_ducts = num_ducts
        self.num_circuits = num_circuits
        self.duct_band_type_asset = duct_band_type_asset
        self.cable_assets = cable_assets

        super(DuctBank, self).__init__(**kw_args)
    # >>> duct_bank


class StructureSupport(Asset):
    """  Anchors and Guys are types of Support for Structures ('anchor' or 'guy' should be specified in the 'type' field inherited from Asset).  Anchors are used to hold poles secure.  Anchor type may be concrete, helix, multi-helix, rod, screw, other, unknown.
    """
    #  Size of anchor or guy. 
    size = ''

    #  Direction, specified in degress, of supporting anchor or guy. 
    direction = ''

    #  Length of anchor lead or guy. 
    length = ''

    #  Number of rods used for an anchor. 
    num_rods = ''

    #  Length of rod used for an anchor. 
    rod_length = ''

    secures_structure = None

    # <<< structure_support
    # @generated
    def __init__(self, size='', direction='', length='', num_rods='', rod_length='', secures_structure=None, **kw_args):
        """ Initialises a new 'StructureSupport' instance.
        """
        self.size = size
        self.direction = direction
        self.length = length
        self.num_rods = num_rods
        self.rod_length = rod_length
        self.secures_structure = secures_structure

        super(StructureSupport, self).__init__(**kw_args)
    # >>> structure_support


class LinearConductorAsset(ElectricalAsset):
    """ A conductor asset is the physical asset used to perform the conductor's role. 
    """
    #  Condition is true if linear asset has an insulator around the core material. 
    insulated = ''

    # Horizontal Orientation of conductor: True=horizontal (e.g., transmission and distribution lines);  False=vertical (e.g. a riser for underground to overhead service). 
    orientation = ''

    # Description of the method used for grounding the linear conductor.  For a cable, the grounding/bonding shield may be multi-point, single-point, cross cable, or other. 
    grounding_method = ''

    circuit_section = None

    linear_conductor_type_asset = None

    conductors = []

    linear_conductor_asset_model = None

    conductor_type = None

    # <<< linear_conductor_asset
    # @generated
    def __init__(self, insulated='', orientation='', grounding_method='', circuit_section=None, linear_conductor_type_asset=None, conductors=[], linear_conductor_asset_model=None, conductor_type=None, **kw_args):
        """ Initialises a new 'LinearConductorAsset' instance.
        """
        self.insulated = insulated
        self.orientation = orientation
        self.grounding_method = grounding_method
        self.circuit_section = circuit_section
        self.linear_conductor_type_asset = linear_conductor_type_asset
        self.conductors = conductors
        self.linear_conductor_asset_model = linear_conductor_asset_model
        self.conductor_type = conductor_type

        super(LinearConductorAsset, self).__init__(**kw_args)
    # >>> linear_conductor_asset


class LinearConductorAssetModel(ElectricalAssetModel):
    """ A type of linear conductor made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    # Type of conductor e.g. distribution, transmission 
    conductor_type = ''

    # True if conductor is insultated. 
    insulated = ''

    # Radius of the conductor 
    radius = ''

    # Commonly referred to size for this type of conductor. 
    size = ''

    # Geometric Mean Radius. If the conductor were replaced by a thin walled tube of radius gMR then its reactance would be identical to that of the actual conductor 
    g_mr = ''

    linear_conductor_type_asset = None

    linear_conductor_assets = []

    # <<< linear_conductor_asset_model
    # @generated
    def __init__(self, conductor_type='', insulated='', radius='', size='', g_mr='', linear_conductor_type_asset=None, linear_conductor_assets=[], **kw_args):
        """ Initialises a new 'LinearConductorAssetModel' instance.
        """
        self.conductor_type = conductor_type
        self.insulated = insulated
        self.radius = radius
        self.size = size
        self.g_mr = g_mr
        self.linear_conductor_type_asset = linear_conductor_type_asset
        self.linear_conductor_assets = linear_conductor_assets

        super(LinearConductorAssetModel, self).__init__(**kw_args)
    # >>> linear_conductor_asset_model


class LinearAssetsVersion(object):
 
    version = ''

 
    date = ''

    # <<< linear_assets_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'LinearAssetsVersion' instance.
        """
        self.version = version
        self.date = date

        super(LinearAssetsVersion, self).__init__(**kw_args)
    # >>> linear_assets_version


class OHConductorAsset(LinearConductorAsset):
    """ The physical conductor performing the Conductor (PSR)  role that is used in overhead applications.
    """
    mounting_point = None

    # <<< ohconductor_asset
    # @generated
    def __init__(self, mounting_point=None, **kw_args):
        """ Initialises a new 'OHConductorAsset' instance.
        """
        self.mounting_point = mounting_point

        super(OHConductorAsset, self).__init__(**kw_args)
    # >>> ohconductor_asset


class CableAsset(LinearConductorAsset):
    """ The insultated physical cable for performing the Conductor (PSR) role used in undergrond and other applications..
    """
    duct_bank_type_asset = None

    duct_banks = []

    # <<< cable_asset
    # @generated
    def __init__(self, duct_bank_type_asset=None, duct_banks=[], **kw_args):
        """ Initialises a new 'CableAsset' instance.
        """
        self.duct_bank_type_asset = duct_bank_type_asset
        self.duct_banks = duct_banks

        super(CableAsset, self).__init__(**kw_args)
    # >>> cable_asset


class OHConductorAssetModel(LinearConductorAssetModel):
    """ A type of linear conductor model made by a particular manufacturer (Organisation). Its ElectricalProperties are defined as being per unit length (which is defined by the unitLength attribute)
    """
    ohconductor_type_asset = None

    # <<< ohconductor_asset_model
    # @generated
    def __init__(self, ohconductor_type_asset=None, **kw_args):
        """ Initialises a new 'OHConductorAssetModel' instance.
        """
        self.ohconductor_type_asset = ohconductor_type_asset

        super(OHConductorAssetModel, self).__init__(**kw_args)
    # >>> ohconductor_asset_model


class CableAssetModel(LinearConductorAssetModel):
    """ Documentation for a type of a Cable of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    # Type of insulation material.  Examples include: Asbetos & Varnished cambric, Butyl, EPR, HMWPE, Low Capacitance rubber, TRHMWPE, Oil Paper, Ozone resistant rubber, PILC - Belted, PILC - Unbelted, Rubber, Silicon Rubber, Varnished cambric cloth, Varnished dacron glass, XLPE-Crosslinked polyethylene, TRXLPE, Other 
    insulation_type = ''

    # Thickness of the insulation. 
    insulation_thickness = ''

    # Insulating Compound Name 
    insulating_compound = ''

    # Null if cable does not have an outer jacket.  Otherwise, type of jacket is provided.  Examples include: Linear Low-density Polyethylene, PVC, Polyethylene, Insulating, Semiconducting, Other. 
    outer_jacket = ''

    # Neutral Conductor Design: Concentric Neutral, Copper Tape, Aluminum Tape, Lead insulation, Other. 
    neutral_cond_design = ''

    # True if wire strands are extruded in a way to fill the voids in the cable. 
    strand_fill_flag = ''

    # Maximum Nominal design operating temperature. 
    nominal_temp = ''

    # Type of conductor material: Aluminum, Copper, Other. 
    conductor_material = ''

    # Sheath material: Lead, Copper, Steel, Aluminum, None. 
    sheath_material = ''

    # True if sheath is used as a neutral. 
    sheath_neutral = ''

    # Type of construction: Compacted, Compressed, Sector, Segmental, Solid, Stranded, Other. 
    construction = ''

    # Type of conductor shield: Freeform, Supersmooth, Conventional, Superclean, Other. 
    shield_type = ''

    # The number of conductors physically contained in the cable. 
    num_conductors = ''

    # Center to neutral conductor spacing for a concentric neutral cable (blank for a transmission model). 
    center_to_neutral = ''

    # Diameter of a concentric neutral strand for a concentric neutral cable. 
    diameter_to_neutral = ''

    cable_type_asset = None

    # <<< cable_asset_model
    # @generated
    def __init__(self, insulation_type='', insulation_thickness='', insulating_compound='', outer_jacket='', neutral_cond_design='', strand_fill_flag='', nominal_temp='', conductor_material='', sheath_material='', sheath_neutral='', construction='', shield_type='', num_conductors='', center_to_neutral='', diameter_to_neutral='', cable_type_asset=None, **kw_args):
        """ Initialises a new 'CableAssetModel' instance.
        """
        self.insulation_type = insulation_type
        self.insulation_thickness = insulation_thickness
        self.insulating_compound = insulating_compound
        self.outer_jacket = outer_jacket
        self.neutral_cond_design = neutral_cond_design
        self.strand_fill_flag = strand_fill_flag
        self.nominal_temp = nominal_temp
        self.conductor_material = conductor_material
        self.sheath_material = sheath_material
        self.sheath_neutral = sheath_neutral
        self.construction = construction
        self.shield_type = shield_type
        self.num_conductors = num_conductors
        self.center_to_neutral = center_to_neutral
        self.diameter_to_neutral = diameter_to_neutral
        self.cable_type_asset = cable_type_asset

        super(CableAssetModel, self).__init__(**kw_args)
    # >>> cable_asset_model


# <<< linearassethierarchy
# @generated
# >>> linearassethierarchy
