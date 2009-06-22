# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Common facilities where multiple utility assets are employed.
"""

from iec61968.assets.assetbasics import Asset
from iec61968.assets.assetbasics import AssetModel

# <<< imports
# @generated
# >>> imports

class AssetContainer(Asset):
    """ An asset that is an aggregation of other assets such as a  conductors, transformers, switchgear, land, fences, buildings, equipment, vehicles, etc.
    """
    assets = []

    properties = []

    # <<< asset_container
    # @generated
    def __init__(self, assets=[], properties=[], **kw_args):
        """ Initialises a new 'AssetContainer' instance.
        """
        self.assets = assets
        self.properties = properties

        super(AssetContainer, self).__init__(**kw_args)
    # >>> asset_container


class CabinetModel(AssetModel):
    """ Documentation for a type of Cabinet of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    cabinets = []

    cabinet_type_asset = None

    # <<< cabinet_model
    # @generated
    def __init__(self, cabinets=[], cabinet_type_asset=None, **kw_args):
        """ Initialises a new 'CabinetModel' instance.
        """
        self.cabinets = cabinets
        self.cabinet_type_asset = cabinet_type_asset

        super(CabinetModel, self).__init__(**kw_args)
    # >>> cabinet_model


class PoleModel(AssetModel):
    """ A type of pole supplied by a given manufacturer.  A pole is a long, slender piece of wood, metal, etc. usually rounded that stands vertically from the ground and is used for mounting various types of overhead equipment.  Dimensions of Pole are specified in associated 'Dimensions'.
    """
    #  Pole species.   Aluminum, Aluminum Davit, Concrete, Fiberglass, Galvanized Davit, Galvanized, Steel Davit Primed, Steel Davit, Steel Standard Primed, Steel, Truncated, Wood-Treated, Wood-Hard, Wood-Salt Treated, Wood-Soft, Wood, Other, Unknown. 
    species = ''

    #  Pole class: 1, 2, 3, 4, 5, 6, 7, H1, H2, Other, Unknown. 
    classification = ''

    poles = []

    pole_type_asset = None

    # <<< pole_model
    # @generated
    def __init__(self, species='', classification='', poles=[], pole_type_asset=None, **kw_args):
        """ Initialises a new 'PoleModel' instance.
        """
        self.species = species
        self.classification = classification
        self.poles = poles
        self.pole_type_asset = pole_type_asset

        super(PoleModel, self).__init__(**kw_args)
    # >>> pole_model


class TowerAssetModel(AssetModel):
    """ A type of tower supplied by a given manufacturer or constructed from a common design. 
    """
    towers = []

    tower_type_asset = None

    # <<< tower_asset_model
    # @generated
    def __init__(self, towers=[], tower_type_asset=None, **kw_args):
        """ Initialises a new 'TowerAssetModel' instance.
        """
        self.towers = towers
        self.tower_type_asset = tower_type_asset

        super(TowerAssetModel, self).__init__(**kw_args)
    # >>> tower_asset_model


class AssetContainersVersion(object):
 
    version = ''

 
    date = ''

    # <<< asset_containers_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'AssetContainersVersion' instance.
        """
        self.version = version
        self.date = date

        super(AssetContainersVersion, self).__init__(**kw_args)
    # >>> asset_containers_version


class EndDeviceModel(AssetModel):
    """ Documentation for a type of End Device  of a particular product model made by a manufacturer (Organisation).  There are typically many instances of an asset associated with a single asset model.
    """
    end_device_assets = []

    end_device_type_asset = None

    # <<< end_device_model
    # @generated
    def __init__(self, end_device_assets=[], end_device_type_asset=None, **kw_args):
        """ Initialises a new 'EndDeviceModel' instance.
        """
        self.end_device_assets = end_device_assets
        self.end_device_type_asset = end_device_type_asset

        super(EndDeviceModel, self).__init__(**kw_args)
    # >>> end_device_model


class SubstationAsset(AssetContainer):
    """ A grouping of assets such as conductors, transformers, switchgear, etc..  When located on the ground surface, it is usually surrounded by some kine of fence with a locked gate.  It may also be located inside buildings, in underground vaults, and on structures.
    """
    # The type of substation such as Air Cooled, Gas Insultated. 
    type_substation = ''

    # Classification of the substation such as transmission, sub-transmission, distribution, customer. 
    sub_class = ''

    substation = None

    # <<< substation_asset
    # @generated
    def __init__(self, type_substation='', sub_class='', substation=None, **kw_args):
        """ Initialises a new 'SubstationAsset' instance.
        """
        self.type_substation = type_substation
        self.sub_class = sub_class
        self.substation = substation

        super(SubstationAsset, self).__init__(**kw_args)
    # >>> substation_asset


class Cabinet(AssetContainer):
    """ Cabinets are enclosures that offer protection to the equipment they contain and/or safety to people/animals outside the cabinet.   
    """
    # The type of cabinet. 
    type_cabinet = ''

    cabinet_model = None

    # <<< cabinet
    # @generated
    def __init__(self, type_cabinet='', cabinet_model=None, **kw_args):
        """ Initialises a new 'Cabinet' instance.
        """
        self.type_cabinet = type_cabinet
        self.cabinet_model = cabinet_model

        super(Cabinet, self).__init__(**kw_args)
    # >>> cabinet


class Structure(AssetContainer):
    """ A structure, such as a vault, pole, tower, for holding assets such as conductors, transformers, switchgear, etc. 
    """
    # Visible height of structure above ground level for overhead construction (e.g., Pole or Tower) or below ground level for an underground vault, manhole, etc..   Refer to associated 'Dimensions' for  other types of dimensions.  
    height = ''

    #  True if weeds are to be removed around asset. 
    weed_abate = ''

    #  Date weed were last removed. 
    weed_rem_date = ''

    #  Type of fumigant. 
    fumigant = ''

    #  Date fumigant was last applied. 
    fumigant_apply_date = ''

    structure_supports = []

    # <<< structure
    # @generated
    def __init__(self, height='', weed_abate='', weed_rem_date='', fumigant='', fumigant_apply_date='', structure_supports=[], **kw_args):
        """ Initialises a new 'Structure' instance.
        """
        self.height = height
        self.weed_abate = weed_abate
        self.weed_rem_date = weed_rem_date
        self.fumigant = fumigant
        self.fumigant_apply_date = fumigant_apply_date
        self.structure_supports = structure_supports

        super(Structure, self).__init__(**kw_args)
    # >>> structure


class UGStructure(Structure):
    """  Underground Structure.  Typical structure types are: BURD, Enclosure, Hand Hole, Manhole, Pad/Slab, Subsurface Enclosure, Trench, Tunnel, Vault, Pull/Splice Box.
    """
    #  True if vault is ventilating. 
    ventilation = ''

    #  Date vault was sealed. 
    sealing_date = ''

    #  Date sealing warranty expires. 
    sealing_warranty_expires = ''

    #  Name of underground structure type: BURD, Enclosure, Hand Hole, Manhole, Pad/Slab, Subsurface Enclosure, Trench, Tunnel, Vault, Pull/Splice Box. 
    type_ugstruc = ''

    # Primary material of underground structure. 
    material = ''

    # <<< ugstructure
    # @generated
    def __init__(self, ventilation='', sealing_date='', sealing_warranty_expires='', type_ugstruc='', material='', **kw_args):
        """ Initialises a new 'UGStructure' instance.
        """
        self.ventilation = ventilation
        self.sealing_date = sealing_date
        self.sealing_warranty_expires = sealing_warranty_expires
        self.type_ugstruc = type_ugstruc
        self.material = material

        super(UGStructure, self).__init__(**kw_args)
    # >>> ugstructure


class Facility(AssetContainer):
    """ A facility may contain buildings, storage facilities, switching facilities, power generation, manufacturing facilities, maintenance facilities, etc.
    """
    # They type of site such as Building Facility, Storage Facility, Switching Facility, Plant, Generation Facility, etc. 
    type_facility = ''

    # <<< facility
    # @generated
    def __init__(self, type_facility='', **kw_args):
        """ Initialises a new 'Facility' instance.
        """
        self.type_facility = type_facility

        super(Facility, self).__init__(**kw_args)
    # >>> facility


class Tower(Structure):
    """ A large structure used for carry transmission lines, subtransmission lines, and/or other equipment/lines (e.g., communication).  Dimensions of the Tower are specified in associated Dimensions class.
    """
    # The construction structure on the tower. 
    tower_construction = ''

    tower_asset_model = None

    # <<< tower
    # @generated
    def __init__(self, tower_construction='', tower_asset_model=None, **kw_args):
        """ Initialises a new 'Tower' instance.
        """
        self.tower_construction = tower_construction
        self.tower_asset_model = tower_asset_model

        super(Tower, self).__init__(**kw_args)
    # >>> tower


class Pole(Structure):
    """  A specific pole.
    """
    #  Pole treatment: Full, Butt, Natural, Gray Stain, Green Stain, Penta, Other, Unknown. 
    treatment = ''

    #  Pole base: Asphalt, Cement, Dirt, Other, Unknown. 
    base = ''

    #  Pole preservative: Creosote, Cellon, Naphthena, Penta, Chemonite, Other, Unknown. 
    preservative = ''

    #  Date pole was last treated with preservative. 
    treated_date = ''

    #  True if a block of material has been attached to base of pole in ground for stability.  This technique is used primarily when anchors can not be used. 
    breast_block = ''

    # The framing structure mounted on the pole. 
    pole_construction = ''

    #  Joint pole agreement reference number. 
    jpa_ref_num = ''

    support_streetlights = []

    pole_model = None

    # <<< pole
    # @generated
    def __init__(self, treatment='', base='', preservative='', treated_date='', breast_block='', pole_construction='', jpa_ref_num='', support_streetlights=[], pole_model=None, **kw_args):
        """ Initialises a new 'Pole' instance.
        """
        self.treatment = treatment
        self.base = base
        self.preservative = preservative
        self.treated_date = treated_date
        self.breast_block = breast_block
        self.pole_construction = pole_construction
        self.jpa_ref_num = jpa_ref_num
        self.support_streetlights = support_streetlights
        self.pole_model = pole_model

        super(Pole, self).__init__(**kw_args)
    # >>> pole


class Manhole(UGStructure):
    """ A manhole provides access at key locations to underground cables, equipment, etc. housed inside a protective vault.
    """
    # The type of manhole. 
    man_hole_type = ''

    # <<< manhole
    # @generated
    def __init__(self, man_hole_type='', **kw_args):
        """ Initialises a new 'Manhole' instance.
        """
        self.man_hole_type = man_hole_type

        super(Manhole, self).__init__(**kw_args)
    # >>> manhole


# <<< assetcontainers
# @generated
# >>> assetcontainers
