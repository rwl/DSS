from dss.general.impl.LineConstantsImpl import (LineConstantsImpl,)
from dss.general.OHLineConstants import (OHLineConstants,)


class OHLineConstantsImpl(LineConstantsImpl, OHLineConstants):

    def __init__(self, NumConductors):
        super(OHLineConstantsImpl, self)(NumConductors)
