# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Asset maintenance and inspection package.
"""

from  import 
from iec61968.documentation.documentinheritance import Document

# <<< imports
# @generated
# >>> imports

class TransformerObservations():
    """ Common information captured during transformer inspections and/or diagrnotics.  Note that some properties may be measured through other means and therefore have measurement values in addition to the observed values recorded here. 
    """
    # Dissolved Gas Analysis.  Typical values are: Acceptable, Overheating, Corona, Sparking, Arcing. 
    dga = ''

    # Water Content expressed in parts per million. 
    water_content = ''

    # Oil Quality Analysis-Color 
    oil_color = ''

    # Oil Quality Analysis-Dielectric Strength 
    oil_dielectric_strength = ''

    # Oil Quality Analysis-Neutralization Number - Number - Mg KOH 
    oil_neutralization_number = ''

    # Oil Quality Analysis- inter facial tension (IFT) - number-Dynes/CM 
    oil_ift = ''

    # Hotspot oil temperature. 
    hot_spot_temp = ''

    # Top Oil Temperature 
    top_oil_temp = ''

    # Bushing temperature 
    bushing_temp = ''

    # Pump vibration, with typical values being: nominal, high.  
    pump_vibration = ''

    # The level of oil in the transformer. 
    oil_level = ''

    # Frequency Response Analysis.  Typical values are: Acceptable, Slight Movement, Significant Movement, Failed,  Near Failure.  A graphic of the response diagram, which is a type of document, may be associated with this analysis through the recursive document relationship of the DataSet. 
    freq_resp = ''

    # Overall measure of furfural in oil and mechanical strength of paper. DP, the degree of polymerization, is the strength of the paper.  Furfural is a measure of furfural compounds, often expressed in parts per million.  
    furfural_dp = ''

    data_sets = []

    winding_insulation_pfs = []

    bushing_insultation_pf = []

    transformer_asset = None

    winding_test = []

    # <<< transformer_observations
    # @generated
    def __init__(self, dga='', water_content='', oil_color='', oil_dielectric_strength='', oil_neutralization_number='', oil_ift='', hot_spot_temp='', top_oil_temp='', bushing_temp='', pump_vibration='', oil_level='', freq_resp='', furfural_dp='', data_sets=[], winding_insulation_pfs=[], bushing_insultation_pf=[], transformer_asset=None, winding_test=[], **kw_args):
        """ Initialises a new 'TransformerObservations' instance.
        """
        self.dga = dga
        self.water_content = water_content
        self.oil_color = oil_color
        self.oil_dielectric_strength = oil_dielectric_strength
        self.oil_neutralization_number = oil_neutralization_number
        self.oil_ift = oil_ift
        self.hot_spot_temp = hot_spot_temp
        self.top_oil_temp = top_oil_temp
        self.bushing_temp = bushing_temp
        self.pump_vibration = pump_vibration
        self.oil_level = oil_level
        self.freq_resp = freq_resp
        self.furfural_dp = furfural_dp
        self.data_sets = data_sets
        self.winding_insulation_pfs = winding_insulation_pfs
        self.bushing_insultation_pf = bushing_insultation_pf
        self.transformer_asset = transformer_asset
        self.winding_test = winding_test

        super(TransformerObservations, self).__init__(**kw_args)
    # >>> transformer_observations


class DataSet(Document):
    """ Each time a procedure is executed, a data set is recorded.  Observed results are captured in assoicated measurment values and/or values for properties relevant to the type of procedure performed. 
    """
    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    procedure = None

    transformer_observations = []

    measurement_values = []

    properties = []

    # <<< data_set
    # @generated
    def __init__(self, status='', status_date_time='', status_remarks='', procedure=None, transformer_observations=[], measurement_values=[], properties=[], **kw_args):
        """ Initialises a new 'DataSet' instance.
        """
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.procedure = procedure
        self.transformer_observations = transformer_observations
        self.measurement_values = measurement_values
        self.properties = properties

        super(DataSet, self).__init__(**kw_args)
    # >>> data_set


class WindingInsulation(object):
    """ Winding insulation condition as a result of a test. 
    """
    # Status of Winding Insulation Power Factor as of statusDate: Acceptable, Minor Deterioration or Moisture Absorption, Major Deterioration or Moisture Absorption, Failed. 
    insulation_pfstatus = ''

    # The date and time the insulations was tested. 
    status_date = ''

    # For testType, status of Winding Insulation Resistance as of statusDate.  Typical values are: Acceptable, Questionable, Failed.  
    insulation_resistance = ''

    # As of statusDate, the leakage reactance measured at the 'from' winding  with the 'to' winding short-circuited and all other windings open-circuited. 
    leakage_reactance = ''

    transformer_observations = None

    from_transformer_winding = None

    to_transformer_winding = None

    ground = None

    # <<< winding_insulation
    # @generated
    def __init__(self, insulation_pfstatus='', status_date='', insulation_resistance='', leakage_reactance='', transformer_observations=None, from_transformer_winding=None, to_transformer_winding=None, ground=None, **kw_args):
        """ Initialises a new 'WindingInsulation' instance.
        """
        self.insulation_pfstatus = insulation_pfstatus
        self.status_date = status_date
        self.insulation_resistance = insulation_resistance
        self.leakage_reactance = leakage_reactance
        self.transformer_observations = transformer_observations
        self.from_transformer_winding = from_transformer_winding
        self.to_transformer_winding = to_transformer_winding
        self.ground = ground

        super(WindingInsulation, self).__init__(**kw_args)
    # >>> winding_insulation


class BushingInsulationPF(object):
    """ Bushing insulation power factor condition as a result of a test.
    """
    # Status as of statusDate.  Typical values are: Acceptable, Minor Deterioration or Moisture Absorption, Major Deterioration or Moisture Absorption, Failed. 
    insulation_status = ''

    # The date and time the insulation was tested. 
    status_date = ''

    # The type of test for this bushing.  Examples include: PF Tap to ground (commonly called C2), PF Tap to conductor (commonly called C1). 
    test_type = ''

    transformer_observations = None

    bushing_asset = None

    # <<< bushing_insulation_pf
    # @generated
    def __init__(self, insulation_status='', status_date='', test_type='', transformer_observations=None, bushing_asset=None, **kw_args):
        """ Initialises a new 'BushingInsulationPF' instance.
        """
        self.insulation_status = insulation_status
        self.status_date = status_date
        self.test_type = test_type
        self.transformer_observations = transformer_observations
        self.bushing_asset = bushing_asset

        super(BushingInsulationPF, self).__init__(**kw_args)
    # >>> bushing_insulation_pf


class AssetWorkTriggerVersion(object):
 
    version = ''

 
    date = ''

    # <<< asset_work_trigger_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'AssetWorkTriggerVersion' instance.
        """
        self.version = version
        self.date = date

        super(AssetWorkTriggerVersion, self).__init__(**kw_args)
    # >>> asset_work_trigger_version


class DiagnosisDataSet(DataSet):
    """ The result of a problem (typically an asset failure) diagnosis, which is a type of Procedure.  
    """
    # Code for problem category determined during preliminary assessment. 
    preliminary_code = ''

    # Remarks pertaining to preliminary assessment of problem. 
    preliminary_remarks = ''

    # Date and time preliminary assessment of problem was performed. 
    preliminary_date = ''

    # Code for diagnosed probem category. 
    final_code = ''

    # Remarks pertaining to findings during problem diagnosis. 
    final_remarks = ''

    # Cause of problem determined during diagnosis. 
    final_cause = ''

    # Origin of problem determined during diagnosis. 
    final_origin = ''

    # Date and time diagnosis was completed. 
    final_date = ''

    # Root cause of problem determined during diagnosis. 
    root_cause = ''

    # Root origin of problem determined during diagnosis. 
    root_origin = ''

    # Remarks pertaining to root cause findings during problem diagnosis. 
    root_remarks = ''

    # Effect of problem. 
    effect = ''

    # Phase(s) diagnosed. 
    phase = ''

    # Failuer mode, for example: Failure to Insulate; Failure to conduct; Failure to contain oil; Failure to provide ground plane; Other. 
    failure_mode = ''

    # <<< diagnosis_data_set
    # @generated
    def __init__(self, preliminary_code='', preliminary_remarks='', preliminary_date='', final_code='', final_remarks='', final_cause='', final_origin='', final_date='', root_cause='', root_origin='', root_remarks='', effect='', phase='', failure_mode='', **kw_args):
        """ Initialises a new 'DiagnosisDataSet' instance.
        """
        self.preliminary_code = preliminary_code
        self.preliminary_remarks = preliminary_remarks
        self.preliminary_date = preliminary_date
        self.final_code = final_code
        self.final_remarks = final_remarks
        self.final_cause = final_cause
        self.final_origin = final_origin
        self.final_date = final_date
        self.root_cause = root_cause
        self.root_origin = root_origin
        self.root_remarks = root_remarks
        self.effect = effect
        self.phase = phase
        self.failure_mode = failure_mode

        super(DiagnosisDataSet, self).__init__(**kw_args)
    # >>> diagnosis_data_set


class TestDataSet(DataSet):
    """ Test results, usually obtained by a lab or other independent organisation.
    """
    #  Identifier of specimen used in inspection or test. 
    specimen_id = ''

    # Conclusion drawn from test results. 
    conclusion = ''

    # The date the specimen was received by the lab. 
    spec_to_lab_date = ''

    # The date the conclusion form the test was issued by the lab. 
    conclusion_date = ''

    # <<< test_data_set
    # @generated
    def __init__(self, specimen_id='', conclusion='', spec_to_lab_date='', conclusion_date='', **kw_args):
        """ Initialises a new 'TestDataSet' instance.
        """
        self.specimen_id = specimen_id
        self.conclusion = conclusion
        self.spec_to_lab_date = spec_to_lab_date
        self.conclusion_date = conclusion_date

        super(TestDataSet, self).__init__(**kw_args)
    # >>> test_data_set


# <<< assetworktrigger
# @generated
# >>> assetworktrigger
