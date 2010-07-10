package electrickery.dssdsl.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import electrickery.dssdsl.services.DssDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDssDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_NEW", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'new'", "'object'", "'='", "'\\n'", "'WireData'", "'{'", "'rDC'", "'rAC'", "'rUnits'", "'gmrAC'", "'gmrUnits'", "'radius'", "'radUnits'", "'normAmps'", "'emergAmps'", "'diameter'", "'}'", "'reduce'", "'LineGeometry'", "'nConds'", "'nPhases'", "'cond'", "'x'", "'h'", "'units'", "'wire'", "'GrowthShape'", "'nPts'", "'year'", "','", "'csvFile'", "'sngFile'", "'dblFile'", "'kron'", "'LineCode'", "'r1'", "'x1'", "'r0'", "'x0'", "'c1'", "'c0'", "'baseFreq'", "'faultRate'", "'pctPerm'", "'repair'", "'rg'", "'xg'", "'rho'", "'neutral'", "'rMatrix'", "'xMatrix'", "'cMatrix'", "'LoadShape'", "'interval'", "'mult'", "'hour'", "'mean'", "'stdDev'", "'qMult'", "'Spectrum'", "'nHarm'", "'harmonic'", "'pctMag'", "'angle'", "'clear\\n'", "'solved'", "'control_busNameRedefined'", "'Circuit'", "'numNodes'", "'generatorDispatchReference'", "'genMultiplier'", "'busNameRedefined'", "'loadMultiplier'", "'defaultGrowthFactor'", "'defaultHourMult'", "'priceSignal'", "'controlQueue'", "'lines'", "'('", "')'", "'loads'", "'shuntCapacitors'", "'feeder'", "'-'", "'.'", "'E'", "'e'", "'true'", "'false'", "'EAnnotation'", "'source'", "'references'", "'eAnnotations'", "'details'", "'contents'", "'ETypeParameter'", "'eBounds'", "'EOperation'", "'ordered'", "'unique'", "'lowerBound'", "'upperBound'", "'eType'", "'eExceptions'", "'eGenericType'", "'eTypeParameters'", "'eParameters'", "'eGenericExceptions'", "'EGenericType'", "'eTypeParameter'", "'eClassifier'", "'eUpperBound'", "'eTypeArguments'", "'eLowerBound'", "'EStringToStringMapEntry'", "'key'", "'value'", "'abstract'", "'interface'", "'EClass'", "'instanceClassName'", "'instanceTypeName'", "'eSuperTypes'", "'eOperations'", "'eStructuralFeatures'", "'eGenericSuperTypes'", "'EObject'", "'EParameter'", "'EDataType'", "'serializable'", "'EEnum'", "'eLiterals'", "'EEnumLiteral'", "'literal'", "'volatile'", "'transient'", "'unsettable'", "'derived'", "'iD'", "'EAttribute'", "'changeable'", "'defaultValueLiteral'", "'containment'", "'EReference'", "'resolveProxies'", "'eOpposite'", "'eKeys'", "'none'", "'mi'", "'km'", "'kft'", "'m'", "'me'", "'ft'", "'in'", "'cm'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_NEW=7;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_INT=6;
    public static final int RULE_WS=10;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;

        public InternalDssDslParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g"; }



     	private DssDslGrammarAccess grammarAccess;
     	
        public InternalDssDslParser(TokenStream input, IAstFactory factory, DssDslGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Electrickery";	
       	}
       	
       	@Override
       	protected DssDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleElectrickery
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:78:1: entryRuleElectrickery returns [EObject current=null] : iv_ruleElectrickery= ruleElectrickery EOF ;
    public final EObject entryRuleElectrickery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElectrickery = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:79:2: (iv_ruleElectrickery= ruleElectrickery EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:80:2: iv_ruleElectrickery= ruleElectrickery EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElectrickeryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleElectrickery_in_entryRuleElectrickery75);
            iv_ruleElectrickery=ruleElectrickery();
            _fsp--;

             current =iv_ruleElectrickery; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElectrickery85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleElectrickery


    // $ANTLR start ruleElectrickery
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:87:1: ruleElectrickery returns [EObject current=null] : ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )* ) ;
    public final EObject ruleElectrickery() throws RecognitionException {
        EObject current = null;

        EObject lv_wireData_4_0 = null;

        EObject lv_lineGeometries_9_0 = null;

        EObject lv_growthShapes_14_0 = null;

        EObject lv_lineCodes_19_0 = null;

        EObject lv_loadShapes_24_0 = null;

        EObject lv_spectrums_29_0 = null;

        EObject lv_executives_31_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:92:6: ( ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )* ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )* )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )* )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:2: () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )*
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:94:5: 
            {
             
                    temp=factory.create(grammarAccess.getElectrickeryAccess().getElectrickeryAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getElectrickeryAccess().getElectrickeryAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:2: ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' ) )*
            loop7:
            do {
                int alt7=8;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==12) ) {
                    switch ( input.LA(2) ) {
                    case 13:
                        {
                        int LA7_4 = input.LA(3);

                        if ( (LA7_4==14) ) {
                            switch ( input.LA(4) ) {
                            case 29:
                            case 30:
                                {
                                alt7=2;
                                }
                                break;
                            case 45:
                            case 46:
                                {
                                alt7=4;
                                }
                                break;
                            case 38:
                                {
                                alt7=3;
                                }
                                break;
                            case 16:
                                {
                                alt7=1;
                                }
                                break;
                            case 71:
                                {
                                alt7=6;
                                }
                                break;
                            case 64:
                                {
                                alt7=5;
                                }
                                break;

                            }

                        }


                        }
                        break;
                    case 45:
                    case 46:
                        {
                        alt7=4;
                        }
                        break;
                    case 71:
                        {
                        alt7=6;
                        }
                        break;
                    case 29:
                    case 30:
                        {
                        alt7=2;
                        }
                        break;
                    case 38:
                        {
                        alt7=3;
                        }
                        break;
                    case 64:
                        {
                        alt7=5;
                        }
                        break;
                    case 16:
                        {
                        alt7=1;
                        }
                        break;

                    }

                }
                else if ( (LA7_0==76) ) {
                    alt7=7;
                }


                switch (alt7) {
            	case 1 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:3: ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:3: ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:5: 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery131); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_0_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:108:1: ( 'object' '=' )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==13) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:108:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery142); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_0_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery152); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_0_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:116:3: ( (lv_wireData_4_0= ruleWireData ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:117:1: (lv_wireData_4_0= ruleWireData )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:117:1: (lv_wireData_4_0= ruleWireData )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:118:3: lv_wireData_4_0= ruleWireData
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getWireDataWireDataParserRuleCall_1_0_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleWireData_in_ruleElectrickery175);
            	    lv_wireData_4_0=ruleWireData();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"wireData",
            	    	        		lv_wireData_4_0, 
            	    	        		"WireData", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery185); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_0_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:145:6: ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:145:6: ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:145:8: 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery203); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_1_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:1: ( 'object' '=' )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==13) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery214); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_1_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery224); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_1_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:157:3: ( (lv_lineGeometries_9_0= ruleLineGeometry ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:158:1: (lv_lineGeometries_9_0= ruleLineGeometry )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:158:1: (lv_lineGeometries_9_0= ruleLineGeometry )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:159:3: lv_lineGeometries_9_0= ruleLineGeometry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineGeometriesLineGeometryParserRuleCall_1_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_ruleElectrickery247);
            	    lv_lineGeometries_9_0=ruleLineGeometry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"lineGeometries",
            	    	        		lv_lineGeometries_9_0, 
            	    	        		"LineGeometry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery257); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_1_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:186:6: ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:186:6: ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:186:8: 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery275); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_2_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:190:1: ( 'object' '=' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==13) ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:190:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery286); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_2_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery296); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_2_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:198:3: ( (lv_growthShapes_14_0= ruleGrowthShape ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:199:1: (lv_growthShapes_14_0= ruleGrowthShape )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:199:1: (lv_growthShapes_14_0= ruleGrowthShape )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:200:3: lv_growthShapes_14_0= ruleGrowthShape
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getGrowthShapesGrowthShapeParserRuleCall_1_2_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_ruleElectrickery319);
            	    lv_growthShapes_14_0=ruleGrowthShape();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"growthShapes",
            	    	        		lv_growthShapes_14_0, 
            	    	        		"GrowthShape", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery329); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_2_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:227:6: ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:227:6: ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:227:8: 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery347); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_3_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:231:1: ( 'object' '=' )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==13) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:231:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery358); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_3_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery368); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_3_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:239:3: ( (lv_lineCodes_19_0= ruleLineCode ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:240:1: (lv_lineCodes_19_0= ruleLineCode )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:240:1: (lv_lineCodes_19_0= ruleLineCode )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:241:3: lv_lineCodes_19_0= ruleLineCode
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineCodesLineCodeParserRuleCall_1_3_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_ruleElectrickery391);
            	    lv_lineCodes_19_0=ruleLineCode();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"lineCodes",
            	    	        		lv_lineCodes_19_0, 
            	    	        		"LineCode", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery401); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_3_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 5 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:268:6: ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:268:6: ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:268:8: 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery419); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_4_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:1: ( 'object' '=' )?
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==13) ) {
            	        alt5=1;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery430); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_4_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery440); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_4_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:280:3: ( (lv_loadShapes_24_0= ruleLoadShape ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:281:1: (lv_loadShapes_24_0= ruleLoadShape )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:281:1: (lv_loadShapes_24_0= ruleLoadShape )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:282:3: lv_loadShapes_24_0= ruleLoadShape
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLoadShapesLoadShapeParserRuleCall_1_4_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_ruleElectrickery463);
            	    lv_loadShapes_24_0=ruleLoadShape();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"loadShapes",
            	    	        		lv_loadShapes_24_0, 
            	    	        		"LoadShape", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery473); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_4_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 6 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:309:6: ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:309:6: ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:309:8: 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n'
            	    {
            	    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery491); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_5_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:1: ( 'object' '=' )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==13) ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:3: 'object' '='
            	            {
            	            match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery502); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_5_1_0(), null); 
            	                
            	            match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery512); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getEqualsSignKeyword_1_5_1_1(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:321:3: ( (lv_spectrums_29_0= ruleSpectrum ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:322:1: (lv_spectrums_29_0= ruleSpectrum )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:322:1: (lv_spectrums_29_0= ruleSpectrum )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:323:3: lv_spectrums_29_0= ruleSpectrum
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getSpectrumsSpectrumParserRuleCall_1_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_ruleElectrickery535);
            	    lv_spectrums_29_0=ruleSpectrum();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"spectrums",
            	    	        		lv_spectrums_29_0, 
            	    	        		"Spectrum", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery545); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_5_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 7 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:6: ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:6: ( ( (lv_executives_31_0= ruleExecutive ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:7: ( (lv_executives_31_0= ruleExecutive ) ) '\\n'
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:7: ( (lv_executives_31_0= ruleExecutive ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:351:1: (lv_executives_31_0= ruleExecutive )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:351:1: (lv_executives_31_0= ruleExecutive )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:352:3: lv_executives_31_0= ruleExecutive
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getExecutivesExecutiveParserRuleCall_1_6_0_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_ruleElectrickery574);
            	    lv_executives_31_0=ruleExecutive();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"executives",
            	    	        		lv_executives_31_0, 
            	    	        		"Executive", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery584); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_6_1(), null); 
            	        

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleElectrickery


    // $ANTLR start entryRuleEStructuralFeature
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:386:1: entryRuleEStructuralFeature returns [EObject current=null] : iv_ruleEStructuralFeature= ruleEStructuralFeature EOF ;
    public final EObject entryRuleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStructuralFeature = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:387:2: (iv_ruleEStructuralFeature= ruleEStructuralFeature EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:388:2: iv_ruleEStructuralFeature= ruleEStructuralFeature EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStructuralFeatureRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature623);
            iv_ruleEStructuralFeature=ruleEStructuralFeature();
            _fsp--;

             current =iv_ruleEStructuralFeature; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStructuralFeature633); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEStructuralFeature


    // $ANTLR start ruleEStructuralFeature
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:395:1: ruleEStructuralFeature returns [EObject current=null] : (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) ;
    public final EObject ruleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject this_EAttribute_0 = null;

        EObject this_EReference_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:400:6: ( (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            int alt8=2;
            switch ( input.LA(1) ) {
            case 146:
                {
                switch ( input.LA(2) ) {
                case 147:
                    {
                    switch ( input.LA(3) ) {
                    case 148:
                        {
                        switch ( input.LA(4) ) {
                        case 149:
                            {
                            int LA8_4 = input.LA(5);

                            if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                                alt8=2;
                            }
                            else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                                alt8=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 154:
                        case 155:
                            {
                            alt8=2;
                            }
                            break;
                        case 150:
                        case 151:
                            {
                            alt8=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 3, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 149:
                        {
                        int LA8_4 = input.LA(4);

                        if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                            alt8=2;
                        }
                        else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                            alt8=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 150:
                    case 151:
                        {
                        alt8=1;
                        }
                        break;
                    case 154:
                    case 155:
                        {
                        alt8=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 2, input);

                        throw nvae;
                    }

                    }
                    break;
                case 148:
                    {
                    switch ( input.LA(3) ) {
                    case 149:
                        {
                        int LA8_4 = input.LA(4);

                        if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                            alt8=2;
                        }
                        else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                            alt8=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 154:
                    case 155:
                        {
                        alt8=2;
                        }
                        break;
                    case 150:
                    case 151:
                        {
                        alt8=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 149:
                    {
                    int LA8_4 = input.LA(3);

                    if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                        alt8=2;
                    }
                    else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                        alt8=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 154:
                case 155:
                    {
                    alt8=2;
                    }
                    break;
                case 150:
                case 151:
                    {
                    alt8=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 1, input);

                    throw nvae;
                }

                }
                break;
            case 147:
                {
                switch ( input.LA(2) ) {
                case 148:
                    {
                    switch ( input.LA(3) ) {
                    case 149:
                        {
                        int LA8_4 = input.LA(4);

                        if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                            alt8=2;
                        }
                        else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                            alt8=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 154:
                    case 155:
                        {
                        alt8=2;
                        }
                        break;
                    case 150:
                    case 151:
                        {
                        alt8=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 149:
                    {
                    int LA8_4 = input.LA(3);

                    if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                        alt8=2;
                    }
                    else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                        alt8=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 150:
                case 151:
                    {
                    alt8=1;
                    }
                    break;
                case 154:
                case 155:
                    {
                    alt8=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 2, input);

                    throw nvae;
                }

                }
                break;
            case 148:
                {
                switch ( input.LA(2) ) {
                case 149:
                    {
                    int LA8_4 = input.LA(3);

                    if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                        alt8=2;
                    }
                    else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                        alt8=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 154:
                case 155:
                    {
                    alt8=2;
                    }
                    break;
                case 150:
                case 151:
                    {
                    alt8=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 3, input);

                    throw nvae;
                }

                }
                break;
            case 149:
                {
                int LA8_4 = input.LA(2);

                if ( ((LA8_4>=154 && LA8_4<=155)) ) {
                    alt8=2;
                }
                else if ( ((LA8_4>=150 && LA8_4<=151)) ) {
                    alt8=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 4, input);

                    throw nvae;
                }
                }
                break;
            case 150:
            case 151:
                {
                alt8=1;
                }
                break;
            case 154:
            case 155:
                {
                alt8=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("401:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:402:5: this_EAttribute_0= ruleEAttribute
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEAttributeParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_ruleEStructuralFeature680);
                    this_EAttribute_0=ruleEAttribute();
                    _fsp--;

                     
                            current = this_EAttribute_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:412:5: this_EReference_1= ruleEReference
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEReferenceParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEReference_in_ruleEStructuralFeature707);
                    this_EReference_1=ruleEReference();
                    _fsp--;

                     
                            current = this_EReference_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEStructuralFeature


    // $ANTLR start entryRuleWireData
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:430:1: entryRuleWireData returns [EObject current=null] : iv_ruleWireData= ruleWireData EOF ;
    public final EObject entryRuleWireData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWireData = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:431:2: (iv_ruleWireData= ruleWireData EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:432:2: iv_ruleWireData= ruleWireData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWireDataRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleWireData_in_entryRuleWireData744);
            iv_ruleWireData=ruleWireData();
            _fsp--;

             current =iv_ruleWireData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleWireData754); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleWireData


    // $ANTLR start ruleWireData
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:439:1: ruleWireData returns [EObject current=null] : ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) ;
    public final EObject ruleWireData() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_rDC_5_0 = null;

        AntlrDatatypeRuleToken lv_rAC_7_0 = null;

        Enumerator lv_rUnits_9_0 = null;

        AntlrDatatypeRuleToken lv_gmrAC_11_0 = null;

        Enumerator lv_gmrUnits_13_0 = null;

        AntlrDatatypeRuleToken lv_radius_15_0 = null;

        Enumerator lv_radUnits_17_0 = null;

        AntlrDatatypeRuleToken lv_normAmps_19_0 = null;

        AntlrDatatypeRuleToken lv_emergAmps_21_0 = null;

        AntlrDatatypeRuleToken lv_diameter_23_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:444:6: ( ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:445:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:445:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:445:2: () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:445:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:446:5: 
            {
             
                    temp=factory.create(grammarAccess.getWireDataAccess().getWireDataAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getWireDataAccess().getWireDataAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,16,FollowSets000.FOLLOW_16_in_ruleWireData798); 

                    createLeafNode(grammarAccess.getWireDataAccess().getWireDataKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:460:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:461:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:461:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:462:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWireData819);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleWireData829); 

                    createLeafNode(grammarAccess.getWireDataAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:488:1: ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==18) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:488:3: 'rDC' ( (lv_rDC_5_0= ruleEDouble ) )
                    {
                    match(input,18,FollowSets000.FOLLOW_18_in_ruleWireData840); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRDCKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:492:1: ( (lv_rDC_5_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:493:1: (lv_rDC_5_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:493:1: (lv_rDC_5_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:494:3: lv_rDC_5_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRDCEDoubleParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData861);
                    lv_rDC_5_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rDC",
                    	        		lv_rDC_5_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:516:4: ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:516:6: 'rAC' ( (lv_rAC_7_0= ruleEDouble ) )
                    {
                    match(input,19,FollowSets000.FOLLOW_19_in_ruleWireData874); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRACKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:520:1: ( (lv_rAC_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:521:1: (lv_rAC_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:521:1: (lv_rAC_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:522:3: lv_rAC_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRACEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData895);
                    lv_rAC_7_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rAC",
                    	        		lv_rAC_7_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:544:4: ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:544:6: 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) )
                    {
                    match(input,20,FollowSets000.FOLLOW_20_in_ruleWireData908); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRUnitsKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:548:1: ( (lv_rUnits_9_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:549:1: (lv_rUnits_9_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:549:1: (lv_rUnits_9_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:550:3: lv_rUnits_9_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRUnitsLengthUnitEnumRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData929);
                    lv_rUnits_9_0=rulelengthUnit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rUnits",
                    	        		lv_rUnits_9_0, 
                    	        		"lengthUnit", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:572:4: ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:572:6: 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) )
                    {
                    match(input,21,FollowSets000.FOLLOW_21_in_ruleWireData942); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrACKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:576:1: ( (lv_gmrAC_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:577:1: (lv_gmrAC_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:577:1: (lv_gmrAC_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:578:3: lv_gmrAC_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrACEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData963);
                    lv_gmrAC_11_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"gmrAC",
                    	        		lv_gmrAC_11_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:600:4: ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:600:6: 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    {
                    match(input,22,FollowSets000.FOLLOW_22_in_ruleWireData976); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrUnitsKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:604:1: ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:605:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:605:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:606:3: lv_gmrUnits_13_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrUnitsLengthUnitEnumRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData997);
                    lv_gmrUnits_13_0=rulelengthUnit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"gmrUnits",
                    	        		lv_gmrUnits_13_0, 
                    	        		"lengthUnit", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:628:4: ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:628:6: 'radius' ( (lv_radius_15_0= ruleEDouble ) )
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleWireData1010); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadiusKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:632:1: ( (lv_radius_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:633:1: (lv_radius_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:633:1: (lv_radius_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:634:3: lv_radius_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadiusEDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1031);
                    lv_radius_15_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"radius",
                    	        		lv_radius_15_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:656:4: ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==24) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:656:6: 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) )
                    {
                    match(input,24,FollowSets000.FOLLOW_24_in_ruleWireData1044); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadUnitsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:660:1: ( (lv_radUnits_17_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:661:1: (lv_radUnits_17_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:661:1: (lv_radUnits_17_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:662:3: lv_radUnits_17_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadUnitsLengthUnitEnumRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1065);
                    lv_radUnits_17_0=rulelengthUnit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"radUnits",
                    	        		lv_radUnits_17_0, 
                    	        		"lengthUnit", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:684:4: ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==25) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:684:6: 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleWireData1078); 

                            createLeafNode(grammarAccess.getWireDataAccess().getNormAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:688:1: ( (lv_normAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:689:1: (lv_normAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:689:1: (lv_normAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:690:3: lv_normAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNormAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1099);
                    lv_normAmps_19_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"normAmps",
                    	        		lv_normAmps_19_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:712:4: ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:712:6: 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleWireData1112); 

                            createLeafNode(grammarAccess.getWireDataAccess().getEmergAmpsKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:716:1: ( (lv_emergAmps_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:717:1: (lv_emergAmps_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:717:1: (lv_emergAmps_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:718:3: lv_emergAmps_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getEmergAmpsEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1133);
                    lv_emergAmps_21_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"emergAmps",
                    	        		lv_emergAmps_21_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:740:4: ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==27) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:740:6: 'diameter' ( (lv_diameter_23_0= ruleEDouble ) )
                    {
                    match(input,27,FollowSets000.FOLLOW_27_in_ruleWireData1146); 

                            createLeafNode(grammarAccess.getWireDataAccess().getDiameterKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:744:1: ( (lv_diameter_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:745:1: (lv_diameter_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:745:1: (lv_diameter_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:746:3: lv_diameter_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getDiameterEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1167);
                    lv_diameter_23_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getWireDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"diameter",
                    	        		lv_diameter_23_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleWireData1179); 

                    createLeafNode(grammarAccess.getWireDataAccess().getRightCurlyBracketKeyword_14(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleWireData


    // $ANTLR start entryRuleLineGeometry
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:780:1: entryRuleLineGeometry returns [EObject current=null] : iv_ruleLineGeometry= ruleLineGeometry EOF ;
    public final EObject entryRuleLineGeometry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineGeometry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:781:2: (iv_ruleLineGeometry= ruleLineGeometry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:782:2: iv_ruleLineGeometry= ruleLineGeometry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineGeometryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1215);
            iv_ruleLineGeometry=ruleLineGeometry();
            _fsp--;

             current =iv_ruleLineGeometry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineGeometry1225); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLineGeometry


    // $ANTLR start ruleLineGeometry
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:789:1: ruleLineGeometry returns [EObject current=null] : ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) ;
    public final EObject ruleLineGeometry() throws RecognitionException {
        EObject current = null;

        Token lv_reduce_0_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_nConds_5_0 = null;

        AntlrDatatypeRuleToken lv_nPhases_7_0 = null;

        AntlrDatatypeRuleToken lv_cond_9_0 = null;

        AntlrDatatypeRuleToken lv_x_11_0 = null;

        AntlrDatatypeRuleToken lv_h_13_0 = null;

        Enumerator lv_units_15_0 = null;

        AntlrDatatypeRuleToken lv_normAmps_17_0 = null;

        AntlrDatatypeRuleToken lv_emergAmps_19_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:794:6: ( ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:2: ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:2: ( (lv_reduce_0_0= 'reduce' ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:796:1: (lv_reduce_0_0= 'reduce' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:796:1: (lv_reduce_0_0= 'reduce' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:797:3: lv_reduce_0_0= 'reduce'
                    {
                    lv_reduce_0_0=(Token)input.LT(1);
                    match(input,29,FollowSets000.FOLLOW_29_in_ruleLineGeometry1268); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getReduceReduceKeyword_0_0(), "reduce"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "reduce", true, "reduce", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,30,FollowSets000.FOLLOW_30_in_ruleLineGeometry1292); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLineGeometryKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:820:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:821:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:821:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:822:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry1313);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleLineGeometry1323); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:848:1: ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:848:3: 'nConds' ( (lv_nConds_5_0= ruleEInt ) )
                    {
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleLineGeometry1334); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNCondsKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:852:1: ( (lv_nConds_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:853:1: (lv_nConds_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:853:1: (lv_nConds_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:854:3: lv_nConds_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNCondsEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1355);
                    lv_nConds_5_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nConds",
                    	        		lv_nConds_5_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:876:4: ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==32) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:876:6: 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) )
                    {
                    match(input,32,FollowSets000.FOLLOW_32_in_ruleLineGeometry1368); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNPhasesKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:880:1: ( (lv_nPhases_7_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:881:1: (lv_nPhases_7_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:881:1: (lv_nPhases_7_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:882:3: lv_nPhases_7_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNPhasesEIntParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1389);
                    lv_nPhases_7_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nPhases",
                    	        		lv_nPhases_7_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:904:4: ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==33) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:904:6: 'cond' ( (lv_cond_9_0= ruleEInt ) )
                    {
                    match(input,33,FollowSets000.FOLLOW_33_in_ruleLineGeometry1402); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getCondKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:908:1: ( (lv_cond_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:909:1: (lv_cond_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:909:1: (lv_cond_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:910:3: lv_cond_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getCondEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1423);
                    lv_cond_9_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"cond",
                    	        		lv_cond_9_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:932:4: ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==34) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:932:6: 'x' ( (lv_x_11_0= ruleEDouble ) )
                    {
                    match(input,34,FollowSets000.FOLLOW_34_in_ruleLineGeometry1436); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getXKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:936:1: ( (lv_x_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:937:1: (lv_x_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:937:1: (lv_x_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:938:3: lv_x_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getXEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1457);
                    lv_x_11_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"x",
                    	        		lv_x_11_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:960:4: ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==35) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:960:6: 'h' ( (lv_h_13_0= ruleEDouble ) )
                    {
                    match(input,35,FollowSets000.FOLLOW_35_in_ruleLineGeometry1470); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getHKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:964:1: ( (lv_h_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:965:1: (lv_h_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:965:1: (lv_h_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:966:3: lv_h_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getHEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1491);
                    lv_h_13_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"h",
                    	        		lv_h_13_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:988:4: ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==36) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:988:6: 'units' ( (lv_units_15_0= rulelengthUnit ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineGeometry1504); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getUnitsKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:992:1: ( (lv_units_15_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:993:1: (lv_units_15_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:993:1: (lv_units_15_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:994:3: lv_units_15_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getUnitsLengthUnitEnumRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineGeometry1525);
                    lv_units_15_0=rulelengthUnit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"units",
                    	        		lv_units_15_0, 
                    	        		"lengthUnit", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1016:4: ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==25) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1016:6: 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleLineGeometry1538); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNormAmpsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1020:1: ( (lv_normAmps_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1021:1: (lv_normAmps_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1021:1: (lv_normAmps_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1022:3: lv_normAmps_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNormAmpsEDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1559);
                    lv_normAmps_17_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"normAmps",
                    	        		lv_normAmps_17_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1044:4: ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==26) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1044:6: 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleLineGeometry1572); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getEmergAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1048:1: ( (lv_emergAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1049:1: (lv_emergAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1049:1: (lv_emergAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1050:3: lv_emergAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getEmergAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1593);
                    lv_emergAmps_19_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"emergAmps",
                    	        		lv_emergAmps_19_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,37,FollowSets000.FOLLOW_37_in_ruleLineGeometry1605); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getWireKeyword_12(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1076:1: ( ( ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1077:1: ( ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1077:1: ( ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1078:3: ruleEString
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getWireWireDataCrossReference_13_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry1628);
            ruleEString();
            _fsp--;

             
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleLineGeometry1638); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getRightCurlyBracketKeyword_14(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLineGeometry


    // $ANTLR start entryRuleGrowthShape
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1104:1: entryRuleGrowthShape returns [EObject current=null] : iv_ruleGrowthShape= ruleGrowthShape EOF ;
    public final EObject entryRuleGrowthShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrowthShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1105:2: (iv_ruleGrowthShape= ruleGrowthShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1106:2: iv_ruleGrowthShape= ruleGrowthShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGrowthShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape1674);
            iv_ruleGrowthShape=ruleGrowthShape();
            _fsp--;

             current =iv_ruleGrowthShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleGrowthShape1684); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleGrowthShape


    // $ANTLR start ruleGrowthShape
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1113:1: ruleGrowthShape returns [EObject current=null] : ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleGrowthShape() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_nPts_4_0 = null;

        AntlrDatatypeRuleToken lv_year_7_0 = null;

        AntlrDatatypeRuleToken lv_year_9_0 = null;

        AntlrDatatypeRuleToken lv_csvFile_12_0 = null;

        AntlrDatatypeRuleToken lv_sngFile_14_0 = null;

        AntlrDatatypeRuleToken lv_dblFile_16_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1118:6: ( ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1119:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1119:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1119:2: () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1119:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1120:5: 
            {
             
                    temp=factory.create(grammarAccess.getGrowthShapeAccess().getGrowthShapeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getGrowthShapeAccess().getGrowthShapeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,38,FollowSets000.FOLLOW_38_in_ruleGrowthShape1728); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getGrowthShapeKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleGrowthShape1738); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1138:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==39) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1138:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,39,FollowSets000.FOLLOW_39_in_ruleGrowthShape1749); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1142:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1143:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1143:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1144:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleGrowthShape1770);
                    lv_nPts_4_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nPts",
                    	        		lv_nPts_4_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1166:4: ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==40) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1166:6: 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,40,FollowSets000.FOLLOW_40_in_ruleGrowthShape1783); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getYearKeyword_4_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleGrowthShape1793); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1174:1: ( (lv_year_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1175:1: (lv_year_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1175:1: (lv_year_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1176:3: lv_year_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape1814);
                    lv_year_7_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"year",
                    	        		lv_year_7_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1198:2: ( ',' ( (lv_year_9_0= ruleEDouble ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==41) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1198:4: ',' ( (lv_year_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleGrowthShape1825); 

                    	            createLeafNode(grammarAccess.getGrowthShapeAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1202:1: ( (lv_year_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1203:1: (lv_year_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1203:1: (lv_year_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1204:3: lv_year_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape1846);
                    	    lv_year_9_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"year",
                    	    	        		lv_year_9_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleGrowthShape1858); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1230:3: ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==42) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1230:5: 'csvFile' ( (lv_csvFile_12_0= ruleEString ) )
                    {
                    match(input,42,FollowSets000.FOLLOW_42_in_ruleGrowthShape1871); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getCsvFileKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1234:1: ( (lv_csvFile_12_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1235:1: (lv_csvFile_12_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1235:1: (lv_csvFile_12_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1236:3: lv_csvFile_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getCsvFileEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape1892);
                    lv_csvFile_12_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"csvFile",
                    	        		lv_csvFile_12_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1258:4: ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==43) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1258:6: 'sngFile' ( (lv_sngFile_14_0= ruleEString ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleGrowthShape1905); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getSngFileKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1262:1: ( (lv_sngFile_14_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1263:1: (lv_sngFile_14_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1263:1: (lv_sngFile_14_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1264:3: lv_sngFile_14_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getSngFileEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape1926);
                    lv_sngFile_14_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sngFile",
                    	        		lv_sngFile_14_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1286:4: ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==44) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1286:6: 'dblFile' ( (lv_dblFile_16_0= ruleEString ) )
                    {
                    match(input,44,FollowSets000.FOLLOW_44_in_ruleGrowthShape1939); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getDblFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1290:1: ( (lv_dblFile_16_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1291:1: (lv_dblFile_16_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1291:1: (lv_dblFile_16_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1292:3: lv_dblFile_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getDblFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape1960);
                    lv_dblFile_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrowthShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"dblFile",
                    	        		lv_dblFile_16_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleGrowthShape1972); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getRightCurlyBracketKeyword_8(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleGrowthShape


    // $ANTLR start entryRuleLineCode
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1326:1: entryRuleLineCode returns [EObject current=null] : iv_ruleLineCode= ruleLineCode EOF ;
    public final EObject entryRuleLineCode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineCode = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1327:2: (iv_ruleLineCode= ruleLineCode EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1328:2: iv_ruleLineCode= ruleLineCode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineCodeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_entryRuleLineCode2008);
            iv_ruleLineCode=ruleLineCode();
            _fsp--;

             current =iv_ruleLineCode; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineCode2018); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLineCode


    // $ANTLR start ruleLineCode
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1335:1: ruleLineCode returns [EObject current=null] : ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) ;
    public final EObject ruleLineCode() throws RecognitionException {
        EObject current = null;

        Token lv_kron_1_0=null;
        AntlrDatatypeRuleToken lv_nPhases_5_0 = null;

        AntlrDatatypeRuleToken lv_r1_7_0 = null;

        AntlrDatatypeRuleToken lv_x1_9_0 = null;

        AntlrDatatypeRuleToken lv_r0_11_0 = null;

        AntlrDatatypeRuleToken lv_x0_13_0 = null;

        AntlrDatatypeRuleToken lv_c1_15_0 = null;

        AntlrDatatypeRuleToken lv_c0_17_0 = null;

        Enumerator lv_units_19_0 = null;

        AntlrDatatypeRuleToken lv_baseFreq_21_0 = null;

        AntlrDatatypeRuleToken lv_normAmps_23_0 = null;

        AntlrDatatypeRuleToken lv_emergAmps_25_0 = null;

        AntlrDatatypeRuleToken lv_faultRate_27_0 = null;

        AntlrDatatypeRuleToken lv_pctPerm_29_0 = null;

        AntlrDatatypeRuleToken lv_repair_31_0 = null;

        AntlrDatatypeRuleToken lv_rg_33_0 = null;

        AntlrDatatypeRuleToken lv_xg_35_0 = null;

        AntlrDatatypeRuleToken lv_rho_37_0 = null;

        AntlrDatatypeRuleToken lv_neutral_39_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1340:6: ( ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1341:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1341:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1341:2: () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1341:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1342:5: 
            {
             
                    temp=factory.create(grammarAccess.getLineCodeAccess().getLineCodeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getLineCodeAccess().getLineCodeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1352:2: ( (lv_kron_1_0= 'kron' ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==45) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1353:1: (lv_kron_1_0= 'kron' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1353:1: (lv_kron_1_0= 'kron' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1354:3: lv_kron_1_0= 'kron'
                    {
                    lv_kron_1_0=(Token)input.LT(1);
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleLineCode2070); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getKronKronKeyword_1_0(), "kron"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "kron", true, "kron", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,46,FollowSets000.FOLLOW_46_in_ruleLineCode2094); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLineCodeKeyword_2(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleLineCode2104); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1381:1: ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==32) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1381:3: 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) )
                    {
                    match(input,32,FollowSets000.FOLLOW_32_in_ruleLineCode2115); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNPhasesKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1385:1: ( (lv_nPhases_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1386:1: (lv_nPhases_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1386:1: (lv_nPhases_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1387:3: lv_nPhases_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNPhasesEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2136);
                    lv_nPhases_5_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nPhases",
                    	        		lv_nPhases_5_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1409:4: ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==47) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1409:6: 'r1' ( (lv_r1_7_0= ruleEDouble ) )
                    {
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleLineCode2149); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR1Keyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1413:1: ( (lv_r1_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1414:1: (lv_r1_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1414:1: (lv_r1_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1415:3: lv_r1_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR1EDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2170);
                    lv_r1_7_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"r1",
                    	        		lv_r1_7_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1437:4: ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==48) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1437:6: 'x1' ( (lv_x1_9_0= ruleEDouble ) )
                    {
                    match(input,48,FollowSets000.FOLLOW_48_in_ruleLineCode2183); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX1Keyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1441:1: ( (lv_x1_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1442:1: (lv_x1_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1442:1: (lv_x1_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1443:3: lv_x1_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX1EDoubleParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2204);
                    lv_x1_9_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"x1",
                    	        		lv_x1_9_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1465:4: ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==49) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1465:6: 'r0' ( (lv_r0_11_0= ruleEDouble ) )
                    {
                    match(input,49,FollowSets000.FOLLOW_49_in_ruleLineCode2217); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR0Keyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1469:1: ( (lv_r0_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1470:1: (lv_r0_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1470:1: (lv_r0_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1471:3: lv_r0_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR0EDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2238);
                    lv_r0_11_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"r0",
                    	        		lv_r0_11_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1493:4: ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==50) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1493:6: 'x0' ( (lv_x0_13_0= ruleEDouble ) )
                    {
                    match(input,50,FollowSets000.FOLLOW_50_in_ruleLineCode2251); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX0Keyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1497:1: ( (lv_x0_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1498:1: (lv_x0_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1498:1: (lv_x0_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1499:3: lv_x0_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX0EDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2272);
                    lv_x0_13_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"x0",
                    	        		lv_x0_13_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1521:4: ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==51) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1521:6: 'c1' ( (lv_c1_15_0= ruleEDouble ) )
                    {
                    match(input,51,FollowSets000.FOLLOW_51_in_ruleLineCode2285); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC1Keyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1525:1: ( (lv_c1_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1526:1: (lv_c1_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1526:1: (lv_c1_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1527:3: lv_c1_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC1EDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2306);
                    lv_c1_15_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"c1",
                    	        		lv_c1_15_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1549:4: ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==52) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1549:6: 'c0' ( (lv_c0_17_0= ruleEDouble ) )
                    {
                    match(input,52,FollowSets000.FOLLOW_52_in_ruleLineCode2319); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC0Keyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1553:1: ( (lv_c0_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1554:1: (lv_c0_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1554:1: (lv_c0_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1555:3: lv_c0_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC0EDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2340);
                    lv_c0_17_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"c0",
                    	        		lv_c0_17_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1577:4: ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==36) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1577:6: 'units' ( (lv_units_19_0= rulelengthUnit ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineCode2353); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getUnitsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1581:1: ( (lv_units_19_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1582:1: (lv_units_19_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1582:1: (lv_units_19_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1583:3: lv_units_19_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getUnitsLengthUnitEnumRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineCode2374);
                    lv_units_19_0=rulelengthUnit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"units",
                    	        		lv_units_19_0, 
                    	        		"lengthUnit", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1605:4: ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==53) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1605:6: 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) )
                    {
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleLineCode2387); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getBaseFreqKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1609:1: ( (lv_baseFreq_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1610:1: (lv_baseFreq_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1610:1: (lv_baseFreq_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1611:3: lv_baseFreq_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getBaseFreqEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2408);
                    lv_baseFreq_21_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"baseFreq",
                    	        		lv_baseFreq_21_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1633:4: ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==25) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1633:6: 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleLineCode2421); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNormAmpsKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1637:1: ( (lv_normAmps_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1638:1: (lv_normAmps_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1638:1: (lv_normAmps_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1639:3: lv_normAmps_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNormAmpsEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2442);
                    lv_normAmps_23_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"normAmps",
                    	        		lv_normAmps_23_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1661:4: ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==26) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1661:6: 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleLineCode2455); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getEmergAmpsKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1665:1: ( (lv_emergAmps_25_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1666:1: (lv_emergAmps_25_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1666:1: (lv_emergAmps_25_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1667:3: lv_emergAmps_25_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getEmergAmpsEDoubleParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2476);
                    lv_emergAmps_25_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"emergAmps",
                    	        		lv_emergAmps_25_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1689:4: ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==54) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1689:6: 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) )
                    {
                    match(input,54,FollowSets000.FOLLOW_54_in_ruleLineCode2489); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getFaultRateKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1693:1: ( (lv_faultRate_27_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1694:1: (lv_faultRate_27_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1694:1: (lv_faultRate_27_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1695:3: lv_faultRate_27_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getFaultRateEDoubleParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2510);
                    lv_faultRate_27_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"faultRate",
                    	        		lv_faultRate_27_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1717:4: ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==55) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1717:6: 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) )
                    {
                    match(input,55,FollowSets000.FOLLOW_55_in_ruleLineCode2523); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getPctPermKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1721:1: ( (lv_pctPerm_29_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1722:1: (lv_pctPerm_29_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1722:1: (lv_pctPerm_29_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1723:3: lv_pctPerm_29_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getPctPermEIntParserRuleCall_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2544);
                    lv_pctPerm_29_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"pctPerm",
                    	        		lv_pctPerm_29_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1745:4: ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==56) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1745:6: 'repair' ( (lv_repair_31_0= ruleEInt ) )
                    {
                    match(input,56,FollowSets000.FOLLOW_56_in_ruleLineCode2557); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRepairKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1749:1: ( (lv_repair_31_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1750:1: (lv_repair_31_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1750:1: (lv_repair_31_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1751:3: lv_repair_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRepairEIntParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2578);
                    lv_repair_31_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"repair",
                    	        		lv_repair_31_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1773:4: ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==57) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1773:6: 'rg' ( (lv_rg_33_0= ruleEDouble ) )
                    {
                    match(input,57,FollowSets000.FOLLOW_57_in_ruleLineCode2591); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRgKeyword_18_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1777:1: ( (lv_rg_33_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1778:1: (lv_rg_33_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1778:1: (lv_rg_33_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1779:3: lv_rg_33_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRgEDoubleParserRuleCall_18_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2612);
                    lv_rg_33_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rg",
                    	        		lv_rg_33_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1801:4: ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==58) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1801:6: 'xg' ( (lv_xg_35_0= ruleEDouble ) )
                    {
                    match(input,58,FollowSets000.FOLLOW_58_in_ruleLineCode2625); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXgKeyword_19_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1805:1: ( (lv_xg_35_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1806:1: (lv_xg_35_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1806:1: (lv_xg_35_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1807:3: lv_xg_35_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXgEDoubleParserRuleCall_19_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2646);
                    lv_xg_35_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"xg",
                    	        		lv_xg_35_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1829:4: ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==59) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1829:6: 'rho' ( (lv_rho_37_0= ruleEDouble ) )
                    {
                    match(input,59,FollowSets000.FOLLOW_59_in_ruleLineCode2659); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRhoKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1833:1: ( (lv_rho_37_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1834:1: (lv_rho_37_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1834:1: (lv_rho_37_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1835:3: lv_rho_37_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRhoEDoubleParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2680);
                    lv_rho_37_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rho",
                    	        		lv_rho_37_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1857:4: ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==60) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1857:6: 'neutral' ( (lv_neutral_39_0= ruleEInt ) )
                    {
                    match(input,60,FollowSets000.FOLLOW_60_in_ruleLineCode2693); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNeutralKeyword_21_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1861:1: ( (lv_neutral_39_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1862:1: (lv_neutral_39_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1862:1: (lv_neutral_39_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1863:3: lv_neutral_39_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNeutralEIntParserRuleCall_21_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2714);
                    lv_neutral_39_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"neutral",
                    	        		lv_neutral_39_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1885:4: ( 'rMatrix' ( ( ruleEString ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==61) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1885:6: 'rMatrix' ( ( ruleEString ) )
                    {
                    match(input,61,FollowSets000.FOLLOW_61_in_ruleLineCode2727); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRMatrixKeyword_22_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1889:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1890:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1890:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1891:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRMatrixDoubleMatrix2DCrossReference_22_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode2750);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1905:4: ( 'xMatrix' ( ( ruleEString ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==62) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1905:6: 'xMatrix' ( ( ruleEString ) )
                    {
                    match(input,62,FollowSets000.FOLLOW_62_in_ruleLineCode2763); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXMatrixKeyword_23_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1909:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1910:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1910:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1911:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXMatrixDoubleMatrix2DCrossReference_23_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode2786);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1925:4: ( 'cMatrix' ( ( ruleEString ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==63) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1925:6: 'cMatrix' ( ( ruleEString ) )
                    {
                    match(input,63,FollowSets000.FOLLOW_63_in_ruleLineCode2799); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getCMatrixKeyword_24_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1929:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1930:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1930:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1931:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getCMatrixDoubleMatrix2DCrossReference_24_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode2822);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleLineCode2834); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getRightCurlyBracketKeyword_25(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLineCode


    // $ANTLR start entryRuleLoadShape
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1957:1: entryRuleLoadShape returns [EObject current=null] : iv_ruleLoadShape= ruleLoadShape EOF ;
    public final EObject entryRuleLoadShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1958:2: (iv_ruleLoadShape= ruleLoadShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1959:2: iv_ruleLoadShape= ruleLoadShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLoadShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_entryRuleLoadShape2870);
            iv_ruleLoadShape=ruleLoadShape();
            _fsp--;

             current =iv_ruleLoadShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLoadShape2880); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLoadShape


    // $ANTLR start ruleLoadShape
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1966:1: ruleLoadShape returns [EObject current=null] : ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) ;
    public final EObject ruleLoadShape() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_nPts_4_0 = null;

        AntlrDatatypeRuleToken lv_interval_6_0 = null;

        AntlrDatatypeRuleToken lv_mult_9_0 = null;

        AntlrDatatypeRuleToken lv_mult_11_0 = null;

        AntlrDatatypeRuleToken lv_hour_15_0 = null;

        AntlrDatatypeRuleToken lv_hour_17_0 = null;

        AntlrDatatypeRuleToken lv_mean_20_0 = null;

        AntlrDatatypeRuleToken lv_stdDev_22_0 = null;

        AntlrDatatypeRuleToken lv_csvFile_24_0 = null;

        AntlrDatatypeRuleToken lv_sngFile_26_0 = null;

        AntlrDatatypeRuleToken lv_dblFile_28_0 = null;

        AntlrDatatypeRuleToken lv_qMult_31_0 = null;

        AntlrDatatypeRuleToken lv_qMult_33_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1971:6: ( ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1972:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1972:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1972:2: () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1972:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1973:5: 
            {
             
                    temp=factory.create(grammarAccess.getLoadShapeAccess().getLoadShapeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getLoadShapeAccess().getLoadShapeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,64,FollowSets000.FOLLOW_64_in_ruleLoadShape2924); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLoadShapeKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleLoadShape2934); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1991:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==39) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1991:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,39,FollowSets000.FOLLOW_39_in_ruleLoadShape2945); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1995:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1996:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1996:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1997:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape2966);
                    lv_nPts_4_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nPts",
                    	        		lv_nPts_4_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2019:4: ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==65) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2019:6: 'interval' ( (lv_interval_6_0= ruleEInt ) )
                    {
                    match(input,65,FollowSets000.FOLLOW_65_in_ruleLoadShape2979); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getIntervalKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2023:1: ( (lv_interval_6_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2024:1: (lv_interval_6_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2024:1: (lv_interval_6_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2025:3: lv_interval_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getIntervalEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape3000);
                    lv_interval_6_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"interval",
                    	        		lv_interval_6_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2047:4: ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==66) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2047:6: 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,66,FollowSets000.FOLLOW_66_in_ruleLoadShape3013); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMultKeyword_5_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleLoadShape3023); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2055:1: ( (lv_mult_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2056:1: (lv_mult_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2056:1: (lv_mult_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2057:3: lv_mult_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3044);
                    lv_mult_9_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"mult",
                    	        		lv_mult_9_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2079:2: ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==41) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2079:4: ',' ( (lv_mult_11_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleLoadShape3055); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2083:1: ( (lv_mult_11_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2084:1: (lv_mult_11_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2084:1: (lv_mult_11_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2085:3: lv_mult_11_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3076);
                    	    lv_mult_11_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"mult",
                    	    	        		lv_mult_11_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3088); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2111:3: ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==67) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2111:5: 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,67,FollowSets000.FOLLOW_67_in_ruleLoadShape3101); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getHourKeyword_6_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleLoadShape3111); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2119:1: ( (lv_hour_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2120:1: (lv_hour_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2120:1: (lv_hour_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2121:3: lv_hour_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3132);
                    lv_hour_15_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"hour",
                    	        		lv_hour_15_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2143:2: ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==41) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2143:4: ',' ( (lv_hour_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleLoadShape3143); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2147:1: ( (lv_hour_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2148:1: (lv_hour_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2148:1: (lv_hour_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2149:3: lv_hour_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3164);
                    	    lv_hour_17_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"hour",
                    	    	        		lv_hour_17_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3176); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2175:3: ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==68) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2175:5: 'mean' ( (lv_mean_20_0= ruleEDouble ) )
                    {
                    match(input,68,FollowSets000.FOLLOW_68_in_ruleLoadShape3189); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMeanKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2179:1: ( (lv_mean_20_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2180:1: (lv_mean_20_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2180:1: (lv_mean_20_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2181:3: lv_mean_20_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMeanEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3210);
                    lv_mean_20_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"mean",
                    	        		lv_mean_20_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2203:4: ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==69) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2203:6: 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) )
                    {
                    match(input,69,FollowSets000.FOLLOW_69_in_ruleLoadShape3223); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getStdDevKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2207:1: ( (lv_stdDev_22_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2208:1: (lv_stdDev_22_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2208:1: (lv_stdDev_22_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2209:3: lv_stdDev_22_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getStdDevEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3244);
                    lv_stdDev_22_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"stdDev",
                    	        		lv_stdDev_22_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2231:4: ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==42) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2231:6: 'csvFile' ( (lv_csvFile_24_0= ruleEString ) )
                    {
                    match(input,42,FollowSets000.FOLLOW_42_in_ruleLoadShape3257); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getCsvFileKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2235:1: ( (lv_csvFile_24_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2236:1: (lv_csvFile_24_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2236:1: (lv_csvFile_24_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2237:3: lv_csvFile_24_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getCsvFileEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3278);
                    lv_csvFile_24_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"csvFile",
                    	        		lv_csvFile_24_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2259:4: ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==43) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2259:6: 'sngFile' ( (lv_sngFile_26_0= ruleEString ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleLoadShape3291); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getSngFileKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2263:1: ( (lv_sngFile_26_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2264:1: (lv_sngFile_26_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2264:1: (lv_sngFile_26_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2265:3: lv_sngFile_26_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getSngFileEStringParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3312);
                    lv_sngFile_26_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sngFile",
                    	        		lv_sngFile_26_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2287:4: ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==44) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2287:6: 'dblFile' ( (lv_dblFile_28_0= ruleEString ) )
                    {
                    match(input,44,FollowSets000.FOLLOW_44_in_ruleLoadShape3325); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getDblFileKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2291:1: ( (lv_dblFile_28_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2292:1: (lv_dblFile_28_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2292:1: (lv_dblFile_28_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2293:3: lv_dblFile_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getDblFileEStringParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3346);
                    lv_dblFile_28_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"dblFile",
                    	        		lv_dblFile_28_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2315:4: ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==70) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2315:6: 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,70,FollowSets000.FOLLOW_70_in_ruleLoadShape3359); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getQMultKeyword_12_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleLoadShape3369); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2323:1: ( (lv_qMult_31_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2324:1: (lv_qMult_31_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2324:1: (lv_qMult_31_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2325:3: lv_qMult_31_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3390);
                    lv_qMult_31_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"qMult",
                    	        		lv_qMult_31_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2347:2: ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==41) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2347:4: ',' ( (lv_qMult_33_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleLoadShape3401); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2351:1: ( (lv_qMult_33_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2352:1: (lv_qMult_33_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2352:1: (lv_qMult_33_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2353:3: lv_qMult_33_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3422);
                    	    lv_qMult_33_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getLoadShapeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"qMult",
                    	    	        		lv_qMult_33_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3434); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3446); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_13(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLoadShape


    // $ANTLR start entryRuleSpectrum
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2391:1: entryRuleSpectrum returns [EObject current=null] : iv_ruleSpectrum= ruleSpectrum EOF ;
    public final EObject entryRuleSpectrum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpectrum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2392:2: (iv_ruleSpectrum= ruleSpectrum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2393:2: iv_ruleSpectrum= ruleSpectrum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSpectrumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_entryRuleSpectrum3482);
            iv_ruleSpectrum=ruleSpectrum();
            _fsp--;

             current =iv_ruleSpectrum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleSpectrum3492); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSpectrum


    // $ANTLR start ruleSpectrum
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2400:1: ruleSpectrum returns [EObject current=null] : ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleSpectrum() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_nHarm_4_0 = null;

        AntlrDatatypeRuleToken lv_harmonic_7_0 = null;

        AntlrDatatypeRuleToken lv_harmonic_9_0 = null;

        AntlrDatatypeRuleToken lv_pctMag_12_0 = null;

        AntlrDatatypeRuleToken lv_angle_15_0 = null;

        AntlrDatatypeRuleToken lv_angle_17_0 = null;

        AntlrDatatypeRuleToken lv_csvFile_20_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2405:6: ( ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2406:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2406:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2406:2: () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2406:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2407:5: 
            {
             
                    temp=factory.create(grammarAccess.getSpectrumAccess().getSpectrumAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getSpectrumAccess().getSpectrumAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,71,FollowSets000.FOLLOW_71_in_ruleSpectrum3536); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getSpectrumKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleSpectrum3546); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2425:1: ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==72) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2425:3: 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) )
                    {
                    match(input,72,FollowSets000.FOLLOW_72_in_ruleSpectrum3557); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getNHarmKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2429:1: ( (lv_nHarm_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2430:1: (lv_nHarm_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2430:1: (lv_nHarm_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2431:3: lv_nHarm_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getNHarmEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleSpectrum3578);
                    lv_nHarm_4_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nHarm",
                    	        		lv_nHarm_4_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2453:4: ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==73) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2453:6: 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,73,FollowSets000.FOLLOW_73_in_ruleSpectrum3591); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getHarmonicKeyword_4_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleSpectrum3601); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2461:1: ( (lv_harmonic_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2462:1: (lv_harmonic_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2462:1: (lv_harmonic_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2463:3: lv_harmonic_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3622);
                    lv_harmonic_7_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"harmonic",
                    	        		lv_harmonic_7_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2485:2: ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==41) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2485:4: ',' ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleSpectrum3633); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2489:1: ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2490:1: (lv_harmonic_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2490:1: (lv_harmonic_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2491:3: lv_harmonic_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3654);
                    	    lv_harmonic_9_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"harmonic",
                    	    	        		lv_harmonic_9_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum3666); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2517:3: ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==74) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2517:5: 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) )
                    {
                    match(input,74,FollowSets000.FOLLOW_74_in_ruleSpectrum3679); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getPctMagKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2521:1: ( (lv_pctMag_12_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2522:1: (lv_pctMag_12_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2522:1: (lv_pctMag_12_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2523:3: lv_pctMag_12_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getPctMagEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3700);
                    lv_pctMag_12_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"pctMag",
                    	        		lv_pctMag_12_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2545:4: ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==75) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2545:6: 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,75,FollowSets000.FOLLOW_75_in_ruleSpectrum3713); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getAngleKeyword_6_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleSpectrum3723); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2553:1: ( (lv_angle_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2554:1: (lv_angle_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2554:1: (lv_angle_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2555:3: lv_angle_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3744);
                    lv_angle_15_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"angle",
                    	        		lv_angle_15_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2577:2: ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==41) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2577:4: ',' ( (lv_angle_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleSpectrum3755); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2581:1: ( (lv_angle_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2582:1: (lv_angle_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2582:1: (lv_angle_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2583:3: lv_angle_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3776);
                    	    lv_angle_17_0=ruleEDouble();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"angle",
                    	    	        		lv_angle_17_0, 
                    	    	        		"EDouble", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum3788); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2609:3: ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==42) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2609:5: 'csvFile' ( (lv_csvFile_20_0= ruleEString ) )
                    {
                    match(input,42,FollowSets000.FOLLOW_42_in_ruleSpectrum3801); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getCsvFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2613:1: ( (lv_csvFile_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2614:1: (lv_csvFile_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2614:1: (lv_csvFile_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2615:3: lv_csvFile_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getCsvFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleSpectrum3822);
                    lv_csvFile_20_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpectrumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"csvFile",
                    	        		lv_csvFile_20_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum3834); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_8(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSpectrum


    // $ANTLR start entryRuleExecutive
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2649:1: entryRuleExecutive returns [EObject current=null] : iv_ruleExecutive= ruleExecutive EOF ;
    public final EObject entryRuleExecutive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecutive = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2650:2: (iv_ruleExecutive= ruleExecutive EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2651:2: iv_ruleExecutive= ruleExecutive EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExecutiveRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_entryRuleExecutive3870);
            iv_ruleExecutive=ruleExecutive();
            _fsp--;

             current =iv_ruleExecutive; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExecutive3880); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExecutive


    // $ANTLR start ruleExecutive
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2658:1: ruleExecutive returns [EObject current=null] : ( () 'clear\\n' ( (lv_circuits_2_0= ruleCircuit ) )+ ) ;
    public final EObject ruleExecutive() throws RecognitionException {
        EObject current = null;

        EObject lv_circuits_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2663:6: ( ( () 'clear\\n' ( (lv_circuits_2_0= ruleCircuit ) )+ ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2664:1: ( () 'clear\\n' ( (lv_circuits_2_0= ruleCircuit ) )+ )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2664:1: ( () 'clear\\n' ( (lv_circuits_2_0= ruleCircuit ) )+ )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2664:2: () 'clear\\n' ( (lv_circuits_2_0= ruleCircuit ) )+
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2664:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2665:5: 
            {
             
                    temp=factory.create(grammarAccess.getExecutiveAccess().getExecutiveAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getExecutiveAccess().getExecutiveAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,76,FollowSets000.FOLLOW_76_in_ruleExecutive3924); 

                    createLeafNode(grammarAccess.getExecutiveAccess().getClearKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2679:1: ( (lv_circuits_2_0= ruleCircuit ) )+
            int cnt76=0;
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( ((LA76_0>=77 && LA76_0<=79)) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2680:1: (lv_circuits_2_0= ruleCircuit )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2680:1: (lv_circuits_2_0= ruleCircuit )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2681:3: lv_circuits_2_0= ruleCircuit
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getExecutiveAccess().getCircuitsCircuitParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleCircuit_in_ruleExecutive3945);
            	    lv_circuits_2_0=ruleCircuit();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getExecutiveRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"circuits",
            	    	        		lv_circuits_2_0, 
            	    	        		"Circuit", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt76 >= 1 ) break loop76;
                        EarlyExitException eee =
                            new EarlyExitException(76, input);
                        throw eee;
                }
                cnt76++;
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExecutive


    // $ANTLR start entryRuleCircuit
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2711:1: entryRuleCircuit returns [EObject current=null] : iv_ruleCircuit= ruleCircuit EOF ;
    public final EObject entryRuleCircuit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCircuit = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2712:2: (iv_ruleCircuit= ruleCircuit EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2713:2: iv_ruleCircuit= ruleCircuit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCircuitRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleCircuit_in_entryRuleCircuit3982);
            iv_ruleCircuit=ruleCircuit();
            _fsp--;

             current =iv_ruleCircuit; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCircuit3992); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCircuit


    // $ANTLR start ruleCircuit
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2720:1: ruleCircuit returns [EObject current=null] : ( ( (lv_solved_0_0= 'solved' ) )? ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )? 'Circuit' ( (lv_name_3_0= ruleEString ) ) '{' ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )? ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )? ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )? ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )? ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )? ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )? ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )? ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )? ( 'controlQueue' ( ( ruleEString ) ) )? ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? '}' ) ;
    public final EObject ruleCircuit() throws RecognitionException {
        EObject current = null;

        Token lv_solved_0_0=null;
        Token lv_control_busNameRedefined_1_0=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_numNodes_6_0 = null;

        AntlrDatatypeRuleToken lv_generatorDispatchReference_8_0 = null;

        AntlrDatatypeRuleToken lv_genMultiplier_10_0 = null;

        AntlrDatatypeRuleToken lv_busNameRedefined_12_0 = null;

        AntlrDatatypeRuleToken lv_loadMultiplier_14_0 = null;

        AntlrDatatypeRuleToken lv_defaultGrowthFactor_16_0 = null;

        AntlrDatatypeRuleToken lv_defaultHourMult_18_0 = null;

        AntlrDatatypeRuleToken lv_priceSignal_20_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2725:6: ( ( ( (lv_solved_0_0= 'solved' ) )? ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )? 'Circuit' ( (lv_name_3_0= ruleEString ) ) '{' ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )? ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )? ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )? ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )? ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )? ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )? ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )? ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )? ( 'controlQueue' ( ( ruleEString ) ) )? ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2726:1: ( ( (lv_solved_0_0= 'solved' ) )? ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )? 'Circuit' ( (lv_name_3_0= ruleEString ) ) '{' ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )? ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )? ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )? ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )? ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )? ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )? ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )? ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )? ( 'controlQueue' ( ( ruleEString ) ) )? ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2726:1: ( ( (lv_solved_0_0= 'solved' ) )? ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )? 'Circuit' ( (lv_name_3_0= ruleEString ) ) '{' ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )? ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )? ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )? ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )? ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )? ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )? ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )? ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )? ( 'controlQueue' ( ( ruleEString ) ) )? ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2726:2: ( (lv_solved_0_0= 'solved' ) )? ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )? 'Circuit' ( (lv_name_3_0= ruleEString ) ) '{' ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )? ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )? ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )? ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )? ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )? ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )? ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )? ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )? ( 'controlQueue' ( ( ruleEString ) ) )? ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2726:2: ( (lv_solved_0_0= 'solved' ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==77) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2727:1: (lv_solved_0_0= 'solved' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2727:1: (lv_solved_0_0= 'solved' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2728:3: lv_solved_0_0= 'solved'
                    {
                    lv_solved_0_0=(Token)input.LT(1);
                    match(input,77,FollowSets000.FOLLOW_77_in_ruleCircuit4035); 

                            createLeafNode(grammarAccess.getCircuitAccess().getSolvedSolvedKeyword_0_0(), "solved"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "solved", true, "solved", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2747:3: ( (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==78) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2748:1: (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2748:1: (lv_control_busNameRedefined_1_0= 'control_busNameRedefined' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2749:3: lv_control_busNameRedefined_1_0= 'control_busNameRedefined'
                    {
                    lv_control_busNameRedefined_1_0=(Token)input.LT(1);
                    match(input,78,FollowSets000.FOLLOW_78_in_ruleCircuit4067); 

                            createLeafNode(grammarAccess.getCircuitAccess().getControl_busNameRedefinedControl_busNameRedefinedKeyword_1_0(), "control_busNameRedefined"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "control_busNameRedefined", true, "control_busNameRedefined", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,79,FollowSets000.FOLLOW_79_in_ruleCircuit4091); 

                    createLeafNode(grammarAccess.getCircuitAccess().getCircuitKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2772:1: ( (lv_name_3_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2773:1: (lv_name_3_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2773:1: (lv_name_3_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2774:3: lv_name_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getNameEStringParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4112);
            lv_name_3_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_3_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleCircuit4122); 

                    createLeafNode(grammarAccess.getCircuitAccess().getLeftCurlyBracketKeyword_4(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2800:1: ( 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==80) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2800:3: 'numNodes' ( (lv_numNodes_6_0= ruleEInt ) )
                    {
                    match(input,80,FollowSets000.FOLLOW_80_in_ruleCircuit4133); 

                            createLeafNode(grammarAccess.getCircuitAccess().getNumNodesKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2804:1: ( (lv_numNodes_6_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2805:1: (lv_numNodes_6_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2805:1: (lv_numNodes_6_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2806:3: lv_numNodes_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getNumNodesEIntParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleCircuit4154);
                    lv_numNodes_6_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"numNodes",
                    	        		lv_numNodes_6_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2828:4: ( 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) ) )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==81) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2828:6: 'generatorDispatchReference' ( (lv_generatorDispatchReference_8_0= ruleEDouble ) )
                    {
                    match(input,81,FollowSets000.FOLLOW_81_in_ruleCircuit4167); 

                            createLeafNode(grammarAccess.getCircuitAccess().getGeneratorDispatchReferenceKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2832:1: ( (lv_generatorDispatchReference_8_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2833:1: (lv_generatorDispatchReference_8_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2833:1: (lv_generatorDispatchReference_8_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2834:3: lv_generatorDispatchReference_8_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getGeneratorDispatchReferenceEDoubleParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4188);
                    lv_generatorDispatchReference_8_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"generatorDispatchReference",
                    	        		lv_generatorDispatchReference_8_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2856:4: ( 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==82) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2856:6: 'genMultiplier' ( (lv_genMultiplier_10_0= ruleEDouble ) )
                    {
                    match(input,82,FollowSets000.FOLLOW_82_in_ruleCircuit4201); 

                            createLeafNode(grammarAccess.getCircuitAccess().getGenMultiplierKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2860:1: ( (lv_genMultiplier_10_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2861:1: (lv_genMultiplier_10_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2861:1: (lv_genMultiplier_10_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2862:3: lv_genMultiplier_10_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getGenMultiplierEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4222);
                    lv_genMultiplier_10_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"genMultiplier",
                    	        		lv_genMultiplier_10_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2884:4: ( 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==83) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2884:6: 'busNameRedefined' ( (lv_busNameRedefined_12_0= ruleEBoolean ) )
                    {
                    match(input,83,FollowSets000.FOLLOW_83_in_ruleCircuit4235); 

                            createLeafNode(grammarAccess.getCircuitAccess().getBusNameRedefinedKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2888:1: ( (lv_busNameRedefined_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2889:1: (lv_busNameRedefined_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2889:1: (lv_busNameRedefined_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2890:3: lv_busNameRedefined_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getBusNameRedefinedEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleCircuit4256);
                    lv_busNameRedefined_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"busNameRedefined",
                    	        		lv_busNameRedefined_12_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2912:4: ( 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==84) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2912:6: 'loadMultiplier' ( (lv_loadMultiplier_14_0= ruleEDouble ) )
                    {
                    match(input,84,FollowSets000.FOLLOW_84_in_ruleCircuit4269); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLoadMultiplierKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2916:1: ( (lv_loadMultiplier_14_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2917:1: (lv_loadMultiplier_14_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2917:1: (lv_loadMultiplier_14_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2918:3: lv_loadMultiplier_14_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getLoadMultiplierEDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4290);
                    lv_loadMultiplier_14_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"loadMultiplier",
                    	        		lv_loadMultiplier_14_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2940:4: ( 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==85) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2940:6: 'defaultGrowthFactor' ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) )
                    {
                    match(input,85,FollowSets000.FOLLOW_85_in_ruleCircuit4303); 

                            createLeafNode(grammarAccess.getCircuitAccess().getDefaultGrowthFactorKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2944:1: ( (lv_defaultGrowthFactor_16_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2945:1: (lv_defaultGrowthFactor_16_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2945:1: (lv_defaultGrowthFactor_16_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2946:3: lv_defaultGrowthFactor_16_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getDefaultGrowthFactorEDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4324);
                    lv_defaultGrowthFactor_16_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"defaultGrowthFactor",
                    	        		lv_defaultGrowthFactor_16_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2968:4: ( 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==86) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2968:6: 'defaultHourMult' ( (lv_defaultHourMult_18_0= ruleEDouble ) )
                    {
                    match(input,86,FollowSets000.FOLLOW_86_in_ruleCircuit4337); 

                            createLeafNode(grammarAccess.getCircuitAccess().getDefaultHourMultKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2972:1: ( (lv_defaultHourMult_18_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2973:1: (lv_defaultHourMult_18_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2973:1: (lv_defaultHourMult_18_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2974:3: lv_defaultHourMult_18_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getDefaultHourMultEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4358);
                    lv_defaultHourMult_18_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"defaultHourMult",
                    	        		lv_defaultHourMult_18_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2996:4: ( 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==87) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2996:6: 'priceSignal' ( (lv_priceSignal_20_0= ruleEDouble ) )
                    {
                    match(input,87,FollowSets000.FOLLOW_87_in_ruleCircuit4371); 

                            createLeafNode(grammarAccess.getCircuitAccess().getPriceSignalKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3000:1: ( (lv_priceSignal_20_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3001:1: (lv_priceSignal_20_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3001:1: (lv_priceSignal_20_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3002:3: lv_priceSignal_20_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getPriceSignalEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleCircuit4392);
                    lv_priceSignal_20_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"priceSignal",
                    	        		lv_priceSignal_20_0, 
                    	        		"EDouble", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3024:4: ( 'controlQueue' ( ( ruleEString ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==88) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3024:6: 'controlQueue' ( ( ruleEString ) )
                    {
                    match(input,88,FollowSets000.FOLLOW_88_in_ruleCircuit4405); 

                            createLeafNode(grammarAccess.getCircuitAccess().getControlQueueKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3028:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3029:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3029:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3030:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getControlQueueControlQueueCrossReference_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4428);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3044:4: ( 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==89) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3044:6: 'lines' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,89,FollowSets000.FOLLOW_89_in_ruleCircuit4441); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLinesKeyword_14_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleCircuit4451); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLeftParenthesisKeyword_14_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3052:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3054:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getLinesLineCrossReference_14_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4474);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3068:2: ( ',' ( ( ruleEString ) ) )*
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==41) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3068:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleCircuit4485); 

                    	            createLeafNode(grammarAccess.getCircuitAccess().getCommaKeyword_14_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3072:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3073:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3073:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3074:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getLinesLineCrossReference_14_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4508);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop88;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleCircuit4520); 

                            createLeafNode(grammarAccess.getCircuitAccess().getRightParenthesisKeyword_14_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3092:3: ( 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==92) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3092:5: 'loads' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,92,FollowSets000.FOLLOW_92_in_ruleCircuit4533); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLoadsKeyword_15_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleCircuit4543); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLeftParenthesisKeyword_15_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3100:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3101:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3101:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3102:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getLoadsLoadCrossReference_15_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4566);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3116:2: ( ',' ( ( ruleEString ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==41) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3116:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleCircuit4577); 

                    	            createLeafNode(grammarAccess.getCircuitAccess().getCommaKeyword_15_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3120:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3121:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3121:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3122:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getLoadsLoadCrossReference_15_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4600);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleCircuit4612); 

                            createLeafNode(grammarAccess.getCircuitAccess().getRightParenthesisKeyword_15_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3140:3: ( 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==93) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3140:5: 'shuntCapacitors' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleCircuit4625); 

                            createLeafNode(grammarAccess.getCircuitAccess().getShuntCapacitorsKeyword_16_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleCircuit4635); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLeftParenthesisKeyword_16_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3148:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3149:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3149:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3150:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getShuntCapacitorsCapacitorCrossReference_16_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4658);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3164:2: ( ',' ( ( ruleEString ) ) )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==41) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3164:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleCircuit4669); 

                    	            createLeafNode(grammarAccess.getCircuitAccess().getCommaKeyword_16_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3168:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3169:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3169:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3170:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getShuntCapacitorsCapacitorCrossReference_16_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4692);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop92;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleCircuit4704); 

                            createLeafNode(grammarAccess.getCircuitAccess().getRightParenthesisKeyword_16_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3188:3: ( 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==94) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3188:5: 'feeder' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,94,FollowSets000.FOLLOW_94_in_ruleCircuit4717); 

                            createLeafNode(grammarAccess.getCircuitAccess().getFeederKeyword_17_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleCircuit4727); 

                            createLeafNode(grammarAccess.getCircuitAccess().getLeftParenthesisKeyword_17_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3196:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3197:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3197:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3198:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getFeederFeederCrossReference_17_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4750);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3212:2: ( ',' ( ( ruleEString ) ) )*
                    loop94:
                    do {
                        int alt94=2;
                        int LA94_0 = input.LA(1);

                        if ( (LA94_0==41) ) {
                            alt94=1;
                        }


                        switch (alt94) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3212:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleCircuit4761); 

                    	            createLeafNode(grammarAccess.getCircuitAccess().getCommaKeyword_17_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3216:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3217:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3217:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3218:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getFeederFeederCrossReference_17_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit4784);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop94;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleCircuit4796); 

                            createLeafNode(grammarAccess.getCircuitAccess().getRightParenthesisKeyword_17_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleCircuit4808); 

                    createLeafNode(grammarAccess.getCircuitAccess().getRightCurlyBracketKeyword_18(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCircuit


    // $ANTLR start entryRuleEString
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3248:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3249:2: (iv_ruleEString= ruleEString EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3250:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString4845);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString4856); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3257:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3262:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3263:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3263:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==RULE_STRING) ) {
                alt96=1;
            }
            else if ( (LA96_0==RULE_ID) ) {
                alt96=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3263:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 96, 0, input);

                throw nvae;
            }
            switch (alt96) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3263:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString4896); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3271:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString4922); 

                    		current.merge(this_ID_1);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEString


    // $ANTLR start entryRuleEDouble
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3286:1: entryRuleEDouble returns [String current=null] : iv_ruleEDouble= ruleEDouble EOF ;
    public final String entryRuleEDouble() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEDouble = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3287:2: (iv_ruleEDouble= ruleEDouble EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3288:2: iv_ruleEDouble= ruleEDouble EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDoubleRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_entryRuleEDouble4968);
            iv_ruleEDouble=ruleEDouble();
            _fsp--;

             current =iv_ruleEDouble.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDouble4979); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEDouble


    // $ANTLR start ruleEDouble
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3295:1: ruleEDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEDouble() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3300:6: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3301:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3301:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3301:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3301:2: (kw= '-' )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==95) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3302:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEDouble5018); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3307:3: (this_INT_1= RULE_INT )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==RULE_INT) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3307:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble5036); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1(), null); 
                        

                    }
                    break;

            }

            kw=(Token)input.LT(1);
            match(input,96,FollowSets000.FOLLOW_96_in_ruleEDouble5056); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getEDoubleAccess().getFullStopKeyword_2(), null); 
                
            this_INT_3=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble5071); 

            		current.merge(this_INT_3);
                
             
                createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3327:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( ((LA101_0>=97 && LA101_0<=98)) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3327:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3327:2: (kw= 'E' | kw= 'e' )
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==97) ) {
                        alt99=1;
                    }
                    else if ( (LA99_0==98) ) {
                        alt99=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("3327:2: (kw= 'E' | kw= 'e' )", 99, 0, input);

                        throw nvae;
                    }
                    switch (alt99) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3328:2: kw= 'E'
                            {
                            kw=(Token)input.LT(1);
                            match(input,97,FollowSets000.FOLLOW_97_in_ruleEDouble5091); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3335:2: kw= 'e'
                            {
                            kw=(Token)input.LT(1);
                            match(input,98,FollowSets000.FOLLOW_98_in_ruleEDouble5110); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3340:2: (kw= '-' )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==95) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3341:2: kw= '-'
                            {
                            kw=(Token)input.LT(1);
                            match(input,95,FollowSets000.FOLLOW_95_in_ruleEDouble5125); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1(), null); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble5142); 

                    		current.merge(this_INT_7);
                        
                     
                        createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_4_2(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEDouble


    // $ANTLR start entryRuleEInt
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3361:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3362:2: (iv_ruleEInt= ruleEInt EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3363:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt5190);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt5201); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEInt


    // $ANTLR start ruleEInt
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3370:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3375:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3376:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3376:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3376:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3376:2: (kw= '-' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==95) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3377:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEInt5240); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt5257); 

            		current.merge(this_INT_1);
                
             
                createLeafNode(grammarAccess.getEIntAccess().getINTTerminalRuleCall_1(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEInt


    // $ANTLR start entryRuleEBoolean
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3397:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3398:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3399:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean5303);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean5314); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEBoolean


    // $ANTLR start ruleEBoolean
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3406:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3411:6: ( (kw= 'true' | kw= 'false' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3412:1: (kw= 'true' | kw= 'false' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3412:1: (kw= 'true' | kw= 'false' )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==99) ) {
                alt103=1;
            }
            else if ( (LA103_0==100) ) {
                alt103=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3412:1: (kw= 'true' | kw= 'false' )", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3413:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEBoolean5352); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3420:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,100,FollowSets000.FOLLOW_100_in_ruleEBoolean5371); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getFalseKeyword_1(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEBoolean


    // $ANTLR start entryRuleEAnnotation
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3433:1: entryRuleEAnnotation returns [EObject current=null] : iv_ruleEAnnotation= ruleEAnnotation EOF ;
    public final EObject entryRuleEAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAnnotation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3434:2: (iv_ruleEAnnotation= ruleEAnnotation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3435:2: iv_ruleEAnnotation= ruleEAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation5411);
            iv_ruleEAnnotation=ruleEAnnotation();
            _fsp--;

             current =iv_ruleEAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAnnotation5421); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEAnnotation


    // $ANTLR start ruleEAnnotation
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3442:1: ruleEAnnotation returns [EObject current=null] : ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) ;
    public final EObject ruleEAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_source_4_0 = null;

        EObject lv_eAnnotations_13_0 = null;

        EObject lv_eAnnotations_15_0 = null;

        EObject lv_details_19_0 = null;

        EObject lv_details_21_0 = null;

        EObject lv_contents_25_0 = null;

        EObject lv_contents_27_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3447:6: ( ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3448:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3448:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3448:2: () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3448:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3449:5: 
            {
             
                    temp=factory.create(grammarAccess.getEAnnotationAccess().getEAnnotationAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,101,FollowSets000.FOLLOW_101_in_ruleEAnnotation5465); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleEAnnotation5475); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3467:1: ( 'source' ( (lv_source_4_0= ruleEString ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==102) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3467:3: 'source' ( (lv_source_4_0= ruleEString ) )
                    {
                    match(input,102,FollowSets000.FOLLOW_102_in_ruleEAnnotation5486); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getSourceKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3471:1: ( (lv_source_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3472:1: (lv_source_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3472:1: (lv_source_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3473:3: lv_source_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getSourceEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5507);
                    lv_source_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"source",
                    	        		lv_source_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3495:4: ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==103) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3495:6: 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEAnnotation5520); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getReferencesKeyword_4_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEAnnotation5530); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftParenthesisKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3503:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3504:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3504:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3505:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5553);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3519:2: ( ',' ( ( ruleEString ) ) )*
                    loop105:
                    do {
                        int alt105=2;
                        int LA105_0 = input.LA(1);

                        if ( (LA105_0==41) ) {
                            alt105=1;
                        }


                        switch (alt105) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3519:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEAnnotation5564); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3523:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3524:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3524:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3525:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5587);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop105;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEAnnotation5599); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightParenthesisKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3543:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==104) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3543:5: 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEAnnotation5612); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationsKeyword_5_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEAnnotation5622); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3551:1: ( (lv_eAnnotations_13_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3552:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3552:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3553:3: lv_eAnnotations_13_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5643);
                    lv_eAnnotations_13_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_13_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3575:2: ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )*
                    loop107:
                    do {
                        int alt107=2;
                        int LA107_0 = input.LA(1);

                        if ( (LA107_0==41) ) {
                            alt107=1;
                        }


                        switch (alt107) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3575:4: ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEAnnotation5654); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3579:1: ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3580:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3580:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3581:3: lv_eAnnotations_15_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5675);
                    	    lv_eAnnotations_15_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_15_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop107;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5687); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3607:3: ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==105) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3607:5: 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}'
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEAnnotation5700); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getDetailsKeyword_6_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEAnnotation5710); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3615:1: ( (lv_details_19_0= ruleEStringToStringMapEntry ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3616:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3616:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3617:3: lv_details_19_0= ruleEStringToStringMapEntry
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5731);
                    lv_details_19_0=ruleEStringToStringMapEntry();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"details",
                    	        		lv_details_19_0, 
                    	        		"EStringToStringMapEntry", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3639:2: ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )*
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==41) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3639:4: ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEAnnotation5742); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3643:1: ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3644:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3644:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3645:3: lv_details_21_0= ruleEStringToStringMapEntry
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5763);
                    	    lv_details_21_0=ruleEStringToStringMapEntry();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"details",
                    	    	        		lv_details_21_0, 
                    	    	        		"EStringToStringMapEntry", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop109;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5775); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3671:3: ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==106) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3671:5: 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}'
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEAnnotation5788); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getContentsKeyword_7_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEAnnotation5798); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3679:1: ( (lv_contents_25_0= ruleEObject ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3680:1: (lv_contents_25_0= ruleEObject )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3680:1: (lv_contents_25_0= ruleEObject )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3681:3: lv_contents_25_0= ruleEObject
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5819);
                    lv_contents_25_0=ruleEObject();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"contents",
                    	        		lv_contents_25_0, 
                    	        		"EObject", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3703:2: ( ',' ( (lv_contents_27_0= ruleEObject ) ) )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==41) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3703:4: ',' ( (lv_contents_27_0= ruleEObject ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEAnnotation5830); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3707:1: ( (lv_contents_27_0= ruleEObject ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3708:1: (lv_contents_27_0= ruleEObject )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3708:1: (lv_contents_27_0= ruleEObject )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3709:3: lv_contents_27_0= ruleEObject
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5851);
                    	    lv_contents_27_0=ruleEObject();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"contents",
                    	    	        		lv_contents_27_0, 
                    	    	        		"EObject", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop111;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5863); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5875); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_8(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEAnnotation


    // $ANTLR start entryRuleETypeParameter
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3747:1: entryRuleETypeParameter returns [EObject current=null] : iv_ruleETypeParameter= ruleETypeParameter EOF ;
    public final EObject entryRuleETypeParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleETypeParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3748:2: (iv_ruleETypeParameter= ruleETypeParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3749:2: iv_ruleETypeParameter= ruleETypeParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getETypeParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5911);
            iv_ruleETypeParameter=ruleETypeParameter();
            _fsp--;

             current =iv_ruleETypeParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleETypeParameter5921); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleETypeParameter


    // $ANTLR start ruleETypeParameter
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3756:1: ruleETypeParameter returns [EObject current=null] : ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
    public final EObject ruleETypeParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_eAnnotations_6_0 = null;

        EObject lv_eAnnotations_8_0 = null;

        EObject lv_eBounds_12_0 = null;

        EObject lv_eBounds_14_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3761:6: ( ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3762:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3762:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3762:2: () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3762:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3763:5: 
            {
             
                    temp=factory.create(grammarAccess.getETypeParameterAccess().getETypeParameterAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getETypeParameterAccess().getETypeParameterAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,107,FollowSets000.FOLLOW_107_in_ruleETypeParameter5965); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getETypeParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3777:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3778:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3778:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3779:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleETypeParameter5986);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getETypeParameterRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleETypeParameter5996); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3805:1: ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==104) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3805:3: 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleETypeParameter6007); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEAnnotationsKeyword_4_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleETypeParameter6017); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3813:1: ( (lv_eAnnotations_6_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3814:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3814:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3815:3: lv_eAnnotations_6_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter6038);
                    lv_eAnnotations_6_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getETypeParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_6_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3837:2: ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==41) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3837:4: ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleETypeParameter6049); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3841:1: ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3842:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3842:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3843:3: lv_eAnnotations_8_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter6070);
                    	    lv_eAnnotations_8_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getETypeParameterRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_8_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop113;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter6082); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3869:3: ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==108) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3869:5: 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,108,FollowSets000.FOLLOW_108_in_ruleETypeParameter6095); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEBoundsKeyword_5_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleETypeParameter6105); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3877:1: ( (lv_eBounds_12_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3878:1: (lv_eBounds_12_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3878:1: (lv_eBounds_12_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3879:3: lv_eBounds_12_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter6126);
                    lv_eBounds_12_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getETypeParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eBounds",
                    	        		lv_eBounds_12_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3901:2: ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )*
                    loop115:
                    do {
                        int alt115=2;
                        int LA115_0 = input.LA(1);

                        if ( (LA115_0==41) ) {
                            alt115=1;
                        }


                        switch (alt115) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3901:4: ',' ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleETypeParameter6137); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3905:1: ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3906:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3906:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3907:3: lv_eBounds_14_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter6158);
                    	    lv_eBounds_14_0=ruleEGenericType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getETypeParameterRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eBounds",
                    	    	        		lv_eBounds_14_0, 
                    	    	        		"EGenericType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop115;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter6170); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter6182); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_6(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleETypeParameter


    // $ANTLR start entryRuleEOperation
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3945:1: entryRuleEOperation returns [EObject current=null] : iv_ruleEOperation= ruleEOperation EOF ;
    public final EObject entryRuleEOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEOperation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3946:2: (iv_ruleEOperation= ruleEOperation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3947:2: iv_ruleEOperation= ruleEOperation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEOperationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_entryRuleEOperation6218);
            iv_ruleEOperation=ruleEOperation();
            _fsp--;

             current =iv_ruleEOperation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEOperation6228); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEOperation


    // $ANTLR start ruleEOperation
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3954:1: ruleEOperation returns [EObject current=null] : ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
    public final EObject ruleEOperation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_ordered_5_0 = null;

        AntlrDatatypeRuleToken lv_unique_7_0 = null;

        AntlrDatatypeRuleToken lv_lowerBound_9_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_11_0 = null;

        EObject lv_eAnnotations_22_0 = null;

        EObject lv_eAnnotations_24_0 = null;

        EObject lv_eGenericType_27_0 = null;

        EObject lv_eTypeParameters_30_0 = null;

        EObject lv_eTypeParameters_32_0 = null;

        EObject lv_eParameters_36_0 = null;

        EObject lv_eParameters_38_0 = null;

        EObject lv_eGenericExceptions_42_0 = null;

        EObject lv_eGenericExceptions_44_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3959:6: ( ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3960:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3960:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3960:2: () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3960:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3961:5: 
            {
             
                    temp=factory.create(grammarAccess.getEOperationAccess().getEOperationAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEOperationAccess().getEOperationAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,109,FollowSets000.FOLLOW_109_in_ruleEOperation6272); 

                    createLeafNode(grammarAccess.getEOperationAccess().getEOperationKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3975:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3976:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3976:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3977:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6293);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEOperation6303); 

                    createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4003:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==110) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4003:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEOperation6314); 

                            createLeafNode(grammarAccess.getEOperationAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4007:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4008:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4008:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4009:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation6335);
                    lv_ordered_5_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"ordered",
                    	        		lv_ordered_5_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4031:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==111) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4031:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEOperation6348); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4035:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4036:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4036:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4037:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation6369);
                    lv_unique_7_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"unique",
                    	        		lv_unique_7_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4059:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==112) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4059:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEOperation6382); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4063:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4064:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4064:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4065:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation6403);
                    lv_lowerBound_9_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lowerBound",
                    	        		lv_lowerBound_9_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4087:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==113) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4087:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,113,FollowSets000.FOLLOW_113_in_ruleEOperation6416); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4091:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4092:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4092:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4093:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation6437);
                    lv_upperBound_11_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"upperBound",
                    	        		lv_upperBound_11_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4115:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==114) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4115:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEOperation6450); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4119:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4120:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4120:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4121:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6473);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4135:4: ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==115) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4135:6: 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,115,FollowSets000.FOLLOW_115_in_ruleEOperation6486); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEExceptionsKeyword_9_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEOperation6496); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftParenthesisKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4143:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4144:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4144:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4145:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6519);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4159:2: ( ',' ( ( ruleEString ) ) )*
                    loop122:
                    do {
                        int alt122=2;
                        int LA122_0 = input.LA(1);

                        if ( (LA122_0==41) ) {
                            alt122=1;
                        }


                        switch (alt122) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4159:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEOperation6530); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4163:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4164:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4164:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4165:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6553);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop122;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEOperation6565); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightParenthesisKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==104) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:5: 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEOperation6578); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEAnnotationsKeyword_10_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEOperation6588); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4191:1: ( (lv_eAnnotations_22_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4192:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4192:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4193:3: lv_eAnnotations_22_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation6609);
                    lv_eAnnotations_22_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_22_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4215:2: ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )*
                    loop124:
                    do {
                        int alt124=2;
                        int LA124_0 = input.LA(1);

                        if ( (LA124_0==41) ) {
                            alt124=1;
                        }


                        switch (alt124) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4215:4: ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEOperation6620); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4219:1: ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4220:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4220:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4221:3: lv_eAnnotations_24_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation6641);
                    	    lv_eAnnotations_24_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_24_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop124;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6653); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4247:3: ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==116) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4247:5: 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEOperation6666); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericTypeKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4251:1: ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4252:1: (lv_eGenericType_27_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4252:1: (lv_eGenericType_27_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4253:3: lv_eGenericType_27_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericTypeEGenericTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6687);
                    lv_eGenericType_27_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eGenericType",
                    	        		lv_eGenericType_27_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4275:4: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==117) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4275:6: 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEOperation6700); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeParametersKeyword_12_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEOperation6710); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4283:1: ( (lv_eTypeParameters_30_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4284:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4284:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4285:3: lv_eTypeParameters_30_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6731);
                    lv_eTypeParameters_30_0=ruleETypeParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eTypeParameters",
                    	        		lv_eTypeParameters_30_0, 
                    	        		"ETypeParameter", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4307:2: ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )*
                    loop127:
                    do {
                        int alt127=2;
                        int LA127_0 = input.LA(1);

                        if ( (LA127_0==41) ) {
                            alt127=1;
                        }


                        switch (alt127) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4307:4: ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEOperation6742); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4311:1: ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4312:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4312:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4313:3: lv_eTypeParameters_32_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6763);
                    	    lv_eTypeParameters_32_0=ruleETypeParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eTypeParameters",
                    	    	        		lv_eTypeParameters_32_0, 
                    	    	        		"ETypeParameter", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop127;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6775); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4339:3: ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==118) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4339:5: 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}'
                    {
                    match(input,118,FollowSets000.FOLLOW_118_in_ruleEOperation6788); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEParametersKeyword_13_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEOperation6798); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4347:1: ( (lv_eParameters_36_0= ruleEParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4348:1: (lv_eParameters_36_0= ruleEParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4348:1: (lv_eParameters_36_0= ruleEParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4349:3: lv_eParameters_36_0= ruleEParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6819);
                    lv_eParameters_36_0=ruleEParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eParameters",
                    	        		lv_eParameters_36_0, 
                    	        		"EParameter", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4371:2: ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )*
                    loop129:
                    do {
                        int alt129=2;
                        int LA129_0 = input.LA(1);

                        if ( (LA129_0==41) ) {
                            alt129=1;
                        }


                        switch (alt129) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4371:4: ',' ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEOperation6830); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4375:1: ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4376:1: (lv_eParameters_38_0= ruleEParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4376:1: (lv_eParameters_38_0= ruleEParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4377:3: lv_eParameters_38_0= ruleEParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6851);
                    	    lv_eParameters_38_0=ruleEParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eParameters",
                    	    	        		lv_eParameters_38_0, 
                    	    	        		"EParameter", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop129;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6863); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4403:3: ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==119) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4403:5: 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,119,FollowSets000.FOLLOW_119_in_ruleEOperation6876); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericExceptionsKeyword_14_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEOperation6886); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_14_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4411:1: ( (lv_eGenericExceptions_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4412:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4412:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4413:3: lv_eGenericExceptions_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6907);
                    lv_eGenericExceptions_42_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eGenericExceptions",
                    	        		lv_eGenericExceptions_42_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4435:2: ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )*
                    loop131:
                    do {
                        int alt131=2;
                        int LA131_0 = input.LA(1);

                        if ( (LA131_0==41) ) {
                            alt131=1;
                        }


                        switch (alt131) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4435:4: ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEOperation6918); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_14_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4439:1: ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4440:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4440:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4441:3: lv_eGenericExceptions_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6939);
                    	    lv_eGenericExceptions_44_0=ruleEGenericType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eGenericExceptions",
                    	    	        		lv_eGenericExceptions_44_0, 
                    	    	        		"EGenericType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop131;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6951); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_14_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6963); 

                    createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_15(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEOperation


    // $ANTLR start entryRuleEGenericType
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4479:1: entryRuleEGenericType returns [EObject current=null] : iv_ruleEGenericType= ruleEGenericType EOF ;
    public final EObject entryRuleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEGenericType = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4480:2: (iv_ruleEGenericType= ruleEGenericType EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4481:2: iv_ruleEGenericType= ruleEGenericType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEGenericTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_entryRuleEGenericType6999);
            iv_ruleEGenericType=ruleEGenericType();
            _fsp--;

             current =iv_ruleEGenericType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEGenericType7009); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEGenericType


    // $ANTLR start ruleEGenericType
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4488:1: ruleEGenericType returns [EObject current=null] : ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject lv_eUpperBound_8_0 = null;

        EObject lv_eTypeArguments_11_0 = null;

        EObject lv_eTypeArguments_13_0 = null;

        EObject lv_eLowerBound_16_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4493:6: ( ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4494:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4494:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4494:2: () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4494:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4495:5: 
            {
             
                    temp=factory.create(grammarAccess.getEGenericTypeAccess().getEGenericTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEGenericTypeAccess().getEGenericTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,120,FollowSets000.FOLLOW_120_in_ruleEGenericType7053); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getEGenericTypeKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleEGenericType7063); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4513:1: ( 'eTypeParameter' ( ( ruleEString ) ) )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==121) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4513:3: 'eTypeParameter' ( ( ruleEString ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEGenericType7074); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeParameterKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4517:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4518:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4518:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4519:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeParameterETypeParameterCrossReference_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType7097);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4533:4: ( 'eClassifier' ( ( ruleEString ) ) )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==122) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4533:6: 'eClassifier' ( ( ruleEString ) )
                    {
                    match(input,122,FollowSets000.FOLLOW_122_in_ruleEGenericType7110); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEClassifierKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4537:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4538:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4538:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4539:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEClassifierEClassifierCrossReference_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType7133);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4553:4: ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==123) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4553:6: 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    {
                    match(input,123,FollowSets000.FOLLOW_123_in_ruleEGenericType7146); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4557:1: ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4558:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4558:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4559:3: lv_eUpperBound_8_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundEGenericTypeParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType7167);
                    lv_eUpperBound_8_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eUpperBound",
                    	        		lv_eUpperBound_8_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4581:4: ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==124) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4581:6: 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,124,FollowSets000.FOLLOW_124_in_ruleEGenericType7180); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsKeyword_6_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEGenericType7190); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4589:1: ( (lv_eTypeArguments_11_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4590:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4590:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4591:3: lv_eTypeArguments_11_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType7211);
                    lv_eTypeArguments_11_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eTypeArguments",
                    	        		lv_eTypeArguments_11_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4613:2: ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )*
                    loop136:
                    do {
                        int alt136=2;
                        int LA136_0 = input.LA(1);

                        if ( (LA136_0==41) ) {
                            alt136=1;
                        }


                        switch (alt136) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4613:4: ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEGenericType7222); 

                    	            createLeafNode(grammarAccess.getEGenericTypeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4617:1: ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4618:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4618:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4619:3: lv_eTypeArguments_13_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType7243);
                    	    lv_eTypeArguments_13_0=ruleEGenericType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eTypeArguments",
                    	    	        		lv_eTypeArguments_13_0, 
                    	    	        		"EGenericType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop136;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEGenericType7255); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4645:3: ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==125) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4645:5: 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    {
                    match(input,125,FollowSets000.FOLLOW_125_in_ruleEGenericType7268); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getELowerBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4649:1: ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4650:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4650:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4651:3: lv_eLowerBound_16_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getELowerBoundEGenericTypeParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType7289);
                    lv_eLowerBound_16_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eLowerBound",
                    	        		lv_eLowerBound_16_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEGenericType7301); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getRightCurlyBracketKeyword_8(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEGenericType


    // $ANTLR start entryRuleEStringToStringMapEntry
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4685:1: entryRuleEStringToStringMapEntry returns [EObject current=null] : iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF ;
    public final EObject entryRuleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStringToStringMapEntry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4686:2: (iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4687:2: iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringToStringMapEntryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry7337);
            iv_ruleEStringToStringMapEntry=ruleEStringToStringMapEntry();
            _fsp--;

             current =iv_ruleEStringToStringMapEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStringToStringMapEntry7347); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEStringToStringMapEntry


    // $ANTLR start ruleEStringToStringMapEntry
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4694:1: ruleEStringToStringMapEntry returns [EObject current=null] : ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_key_4_0 = null;

        AntlrDatatypeRuleToken lv_value_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4699:6: ( ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4700:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4700:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4700:2: () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4700:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4701:5: 
            {
             
                    temp=factory.create(grammarAccess.getEStringToStringMapEntryAccess().getEStringToStringMapEntryAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getEStringToStringMapEntryAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,126,FollowSets000.FOLLOW_126_in_ruleEStringToStringMapEntry7391); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getEStringToStringMapEntryKeyword_1(), null); 
                
            match(input,17,FollowSets000.FOLLOW_17_in_ruleEStringToStringMapEntry7401); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4719:1: ( 'key' ( (lv_key_4_0= ruleEString ) ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==127) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4719:3: 'key' ( (lv_key_4_0= ruleEString ) )
                    {
                    match(input,127,FollowSets000.FOLLOW_127_in_ruleEStringToStringMapEntry7412); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4723:1: ( (lv_key_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4724:1: (lv_key_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4724:1: (lv_key_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4725:3: lv_key_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7433);
                    lv_key_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEStringToStringMapEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"key",
                    	        		lv_key_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4747:4: ( 'value' ( (lv_value_6_0= ruleEString ) ) )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==128) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4747:6: 'value' ( (lv_value_6_0= ruleEString ) )
                    {
                    match(input,128,FollowSets000.FOLLOW_128_in_ruleEStringToStringMapEntry7446); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4751:1: ( (lv_value_6_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4752:1: (lv_value_6_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4752:1: (lv_value_6_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4753:3: lv_value_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getValueEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7467);
                    lv_value_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEStringToStringMapEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"value",
                    	        		lv_value_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEStringToStringMapEntry7479); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getRightCurlyBracketKeyword_5(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEStringToStringMapEntry


    // $ANTLR start entryRuleEClass
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4787:1: entryRuleEClass returns [EObject current=null] : iv_ruleEClass= ruleEClass EOF ;
    public final EObject entryRuleEClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEClass = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4788:2: (iv_ruleEClass= ruleEClass EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4789:2: iv_ruleEClass= ruleEClass EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEClassRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEClass_in_entryRuleEClass7515);
            iv_ruleEClass=ruleEClass();
            _fsp--;

             current =iv_ruleEClass; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEClass7525); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEClass


    // $ANTLR start ruleEClass
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4796:1: ruleEClass returns [EObject current=null] : ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
    public final EObject ruleEClass() throws RecognitionException {
        EObject current = null;

        Token lv_abstract_1_0=null;
        Token lv_interface_2_0=null;
        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_instanceClassName_7_0 = null;

        AntlrDatatypeRuleToken lv_instanceTypeName_9_0 = null;

        EObject lv_eAnnotations_18_0 = null;

        EObject lv_eAnnotations_20_0 = null;

        EObject lv_eTypeParameters_24_0 = null;

        EObject lv_eTypeParameters_26_0 = null;

        EObject lv_eOperations_30_0 = null;

        EObject lv_eOperations_32_0 = null;

        EObject lv_eStructuralFeatures_36_0 = null;

        EObject lv_eStructuralFeatures_38_0 = null;

        EObject lv_eGenericSuperTypes_42_0 = null;

        EObject lv_eGenericSuperTypes_44_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4801:6: ( ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4802:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4802:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4802:2: () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4802:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4803:5: 
            {
             
                    temp=factory.create(grammarAccess.getEClassAccess().getEClassAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEClassAccess().getEClassAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4813:2: ( (lv_abstract_1_0= 'abstract' ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==129) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4814:1: (lv_abstract_1_0= 'abstract' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4814:1: (lv_abstract_1_0= 'abstract' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4815:3: lv_abstract_1_0= 'abstract'
                    {
                    lv_abstract_1_0=(Token)input.LT(1);
                    match(input,129,FollowSets000.FOLLOW_129_in_ruleEClass7577); 

                            createLeafNode(grammarAccess.getEClassAccess().getAbstractAbstractKeyword_1_0(), "abstract"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "abstract", true, "abstract", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4834:3: ( (lv_interface_2_0= 'interface' ) )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==130) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4835:1: (lv_interface_2_0= 'interface' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4835:1: (lv_interface_2_0= 'interface' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4836:3: lv_interface_2_0= 'interface'
                    {
                    lv_interface_2_0=(Token)input.LT(1);
                    match(input,130,FollowSets000.FOLLOW_130_in_ruleEClass7609); 

                            createLeafNode(grammarAccess.getEClassAccess().getInterfaceInterfaceKeyword_2_0(), "interface"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "interface", true, "interface", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,131,FollowSets000.FOLLOW_131_in_ruleEClass7633); 

                    createLeafNode(grammarAccess.getEClassAccess().getEClassKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4859:1: ( (lv_name_4_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4860:1: (lv_name_4_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4860:1: (lv_name_4_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4861:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7654);
            lv_name_4_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_4_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass7664); 

                    createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4887:1: ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==132) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4887:3: 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) )
                    {
                    match(input,132,FollowSets000.FOLLOW_132_in_ruleEClass7675); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceClassNameKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4891:1: ( (lv_instanceClassName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4892:1: (lv_instanceClassName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4892:1: (lv_instanceClassName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4893:3: lv_instanceClassName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceClassNameEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7696);
                    lv_instanceClassName_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceClassName",
                    	        		lv_instanceClassName_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4915:4: ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==133) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4915:6: 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) )
                    {
                    match(input,133,FollowSets000.FOLLOW_133_in_ruleEClass7709); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceTypeNameKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4919:1: ( (lv_instanceTypeName_9_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4920:1: (lv_instanceTypeName_9_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4920:1: (lv_instanceTypeName_9_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4921:3: lv_instanceTypeName_9_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceTypeNameEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7730);
                    lv_instanceTypeName_9_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceTypeName",
                    	        		lv_instanceTypeName_9_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4943:4: ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==134) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4943:6: 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,134,FollowSets000.FOLLOW_134_in_ruleEClass7743); 

                            createLeafNode(grammarAccess.getEClassAccess().getESuperTypesKeyword_8_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEClass7753); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftParenthesisKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4951:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4952:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4952:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4953:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7776);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4967:2: ( ',' ( ( ruleEString ) ) )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==41) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4967:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass7787); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4971:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4972:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4972:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4973:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7810);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop145;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEClass7822); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightParenthesisKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4991:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==104) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4991:5: 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEClass7835); 

                            createLeafNode(grammarAccess.getEClassAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass7845); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4999:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5000:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5000:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5001:3: lv_eAnnotations_18_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7866);
                    lv_eAnnotations_18_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_18_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5023:2: ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==41) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5023:4: ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass7877); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5027:1: ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5028:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5028:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5029:3: lv_eAnnotations_20_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7898);
                    	    lv_eAnnotations_20_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_20_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7910); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5055:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==117) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5055:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEClass7923); 

                            createLeafNode(grammarAccess.getEClassAccess().getETypeParametersKeyword_10_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass7933); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5063:1: ( (lv_eTypeParameters_24_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5064:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5064:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5065:3: lv_eTypeParameters_24_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7954);
                    lv_eTypeParameters_24_0=ruleETypeParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eTypeParameters",
                    	        		lv_eTypeParameters_24_0, 
                    	        		"ETypeParameter", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5087:2: ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==41) ) {
                            alt149=1;
                        }


                        switch (alt149) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5087:4: ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass7965); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5091:1: ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5092:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5092:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5093:3: lv_eTypeParameters_26_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7986);
                    	    lv_eTypeParameters_26_0=ruleETypeParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eTypeParameters",
                    	    	        		lv_eTypeParameters_26_0, 
                    	    	        		"ETypeParameter", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7998); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5119:3: ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==135) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5119:5: 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}'
                    {
                    match(input,135,FollowSets000.FOLLOW_135_in_ruleEClass8011); 

                            createLeafNode(grammarAccess.getEClassAccess().getEOperationsKeyword_11_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass8021); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_11_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5127:1: ( (lv_eOperations_30_0= ruleEOperation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:1: (lv_eOperations_30_0= ruleEOperation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:1: (lv_eOperations_30_0= ruleEOperation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5129:3: lv_eOperations_30_0= ruleEOperation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass8042);
                    lv_eOperations_30_0=ruleEOperation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eOperations",
                    	        		lv_eOperations_30_0, 
                    	        		"EOperation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5151:2: ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==41) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5151:4: ',' ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass8053); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_11_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5155:1: ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5156:1: (lv_eOperations_32_0= ruleEOperation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5156:1: (lv_eOperations_32_0= ruleEOperation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5157:3: lv_eOperations_32_0= ruleEOperation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass8074);
                    	    lv_eOperations_32_0=ruleEOperation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eOperations",
                    	    	        		lv_eOperations_32_0, 
                    	    	        		"EOperation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop151;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass8086); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_11_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5183:3: ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==136) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5183:5: 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}'
                    {
                    match(input,136,FollowSets000.FOLLOW_136_in_ruleEClass8099); 

                            createLeafNode(grammarAccess.getEClassAccess().getEStructuralFeaturesKeyword_12_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass8109); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5191:1: ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5192:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5192:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5193:3: lv_eStructuralFeatures_36_0= ruleEStructuralFeature
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass8130);
                    lv_eStructuralFeatures_36_0=ruleEStructuralFeature();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eStructuralFeatures",
                    	        		lv_eStructuralFeatures_36_0, 
                    	        		"EStructuralFeature", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5215:2: ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )*
                    loop153:
                    do {
                        int alt153=2;
                        int LA153_0 = input.LA(1);

                        if ( (LA153_0==41) ) {
                            alt153=1;
                        }


                        switch (alt153) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5215:4: ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass8141); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5219:1: ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5220:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5220:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5221:3: lv_eStructuralFeatures_38_0= ruleEStructuralFeature
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass8162);
                    	    lv_eStructuralFeatures_38_0=ruleEStructuralFeature();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eStructuralFeatures",
                    	    	        		lv_eStructuralFeatures_38_0, 
                    	    	        		"EStructuralFeature", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop153;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass8174); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5247:3: ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==137) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5247:5: 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,137,FollowSets000.FOLLOW_137_in_ruleEClass8187); 

                            createLeafNode(grammarAccess.getEClassAccess().getEGenericSuperTypesKeyword_13_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEClass8197); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5255:1: ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5256:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5256:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5257:3: lv_eGenericSuperTypes_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass8218);
                    lv_eGenericSuperTypes_42_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eGenericSuperTypes",
                    	        		lv_eGenericSuperTypes_42_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5279:2: ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )*
                    loop155:
                    do {
                        int alt155=2;
                        int LA155_0 = input.LA(1);

                        if ( (LA155_0==41) ) {
                            alt155=1;
                        }


                        switch (alt155) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5279:4: ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEClass8229); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5283:1: ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5284:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5284:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5285:3: lv_eGenericSuperTypes_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass8250);
                    	    lv_eGenericSuperTypes_44_0=ruleEGenericType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eGenericSuperTypes",
                    	    	        		lv_eGenericSuperTypes_44_0, 
                    	    	        		"EGenericType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop155;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass8262); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass8274); 

                    createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_14(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEClass


    // $ANTLR start entryRuleEObject
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5323:1: entryRuleEObject returns [EObject current=null] : iv_ruleEObject= ruleEObject EOF ;
    public final EObject entryRuleEObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEObject = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5324:2: (iv_ruleEObject= ruleEObject EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5325:2: iv_ruleEObject= ruleEObject EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEObjectRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEObject_in_entryRuleEObject8310);
            iv_ruleEObject=ruleEObject();
            _fsp--;

             current =iv_ruleEObject; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEObject8320); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEObject


    // $ANTLR start ruleEObject
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5332:1: ruleEObject returns [EObject current=null] : ( () 'EObject' ) ;
    public final EObject ruleEObject() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5337:6: ( ( () 'EObject' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5338:1: ( () 'EObject' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5338:1: ( () 'EObject' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5338:2: () 'EObject'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5338:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5339:5: 
            {
             
                    temp=factory.create(grammarAccess.getEObjectAccess().getEObjectAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEObjectAccess().getEObjectAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,138,FollowSets000.FOLLOW_138_in_ruleEObject8364); 

                    createLeafNode(grammarAccess.getEObjectAccess().getEObjectKeyword_1(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEObject


    // $ANTLR start entryRuleEParameter
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5361:1: entryRuleEParameter returns [EObject current=null] : iv_ruleEParameter= ruleEParameter EOF ;
    public final EObject entryRuleEParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5362:2: (iv_ruleEParameter= ruleEParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5363:2: iv_ruleEParameter= ruleEParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_entryRuleEParameter8400);
            iv_ruleEParameter=ruleEParameter();
            _fsp--;

             current =iv_ruleEParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEParameter8410); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEParameter


    // $ANTLR start ruleEParameter
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5370:1: ruleEParameter returns [EObject current=null] : ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_ordered_5_0 = null;

        AntlrDatatypeRuleToken lv_unique_7_0 = null;

        AntlrDatatypeRuleToken lv_lowerBound_9_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_11_0 = null;

        EObject lv_eAnnotations_16_0 = null;

        EObject lv_eAnnotations_18_0 = null;

        EObject lv_eGenericType_21_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5375:6: ( ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5376:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5376:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5376:2: () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5376:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5377:5: 
            {
             
                    temp=factory.create(grammarAccess.getEParameterAccess().getEParameterAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEParameterAccess().getEParameterAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,139,FollowSets000.FOLLOW_139_in_ruleEParameter8454); 

                    createLeafNode(grammarAccess.getEParameterAccess().getEParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5391:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5392:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5392:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5393:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter8475);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEParameter8485); 

                    createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5419:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==110) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5419:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEParameter8496); 

                            createLeafNode(grammarAccess.getEParameterAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5423:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5424:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5424:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5425:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter8517);
                    lv_ordered_5_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"ordered",
                    	        		lv_ordered_5_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5447:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==111) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5447:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEParameter8530); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5451:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5452:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5452:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5453:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter8551);
                    lv_unique_7_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"unique",
                    	        		lv_unique_7_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5475:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==112) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5475:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEParameter8564); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5479:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5480:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5480:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5481:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter8585);
                    lv_lowerBound_9_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lowerBound",
                    	        		lv_lowerBound_9_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5503:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==113) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5503:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,113,FollowSets000.FOLLOW_113_in_ruleEParameter8598); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5507:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5508:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5508:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5509:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter8619);
                    lv_upperBound_11_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"upperBound",
                    	        		lv_upperBound_11_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5531:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==114) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5531:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEParameter8632); 

                            createLeafNode(grammarAccess.getEParameterAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5535:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5536:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5536:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5537:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter8655);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5551:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==104) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5551:6: 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEParameter8668); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEParameter8678); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5559:1: ( (lv_eAnnotations_16_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5560:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5560:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5561:3: lv_eAnnotations_16_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8699);
                    lv_eAnnotations_16_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_16_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5583:2: ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )*
                    loop162:
                    do {
                        int alt162=2;
                        int LA162_0 = input.LA(1);

                        if ( (LA162_0==41) ) {
                            alt162=1;
                        }


                        switch (alt162) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5583:4: ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEParameter8710); 

                    	            createLeafNode(grammarAccess.getEParameterAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5587:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5588:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5588:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5589:3: lv_eAnnotations_18_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8731);
                    	    lv_eAnnotations_18_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_18_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop162;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEParameter8743); 

                            createLeafNode(grammarAccess.getEParameterAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5615:3: ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==116) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5615:5: 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEParameter8756); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEGenericTypeKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5619:1: ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5620:1: (lv_eGenericType_21_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5620:1: (lv_eGenericType_21_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5621:3: lv_eGenericType_21_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEGenericTypeEGenericTypeParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEParameter8777);
                    lv_eGenericType_21_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eGenericType",
                    	        		lv_eGenericType_21_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEParameter8789); 

                    createLeafNode(grammarAccess.getEParameterAccess().getRightCurlyBracketKeyword_11(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEParameter


    // $ANTLR start entryRuleEDataType_Impl
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5655:1: entryRuleEDataType_Impl returns [EObject current=null] : iv_ruleEDataType_Impl= ruleEDataType_Impl EOF ;
    public final EObject entryRuleEDataType_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEDataType_Impl = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5656:2: (iv_ruleEDataType_Impl= ruleEDataType_Impl EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5657:2: iv_ruleEDataType_Impl= ruleEDataType_Impl EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDataType_ImplRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8825);
            iv_ruleEDataType_Impl=ruleEDataType_Impl();
            _fsp--;

             current =iv_ruleEDataType_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDataType_Impl8835); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEDataType_Impl


    // $ANTLR start ruleEDataType_Impl
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5664:1: ruleEDataType_Impl returns [EObject current=null] : ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) ;
    public final EObject ruleEDataType_Impl() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_instanceClassName_5_0 = null;

        AntlrDatatypeRuleToken lv_instanceTypeName_7_0 = null;

        AntlrDatatypeRuleToken lv_serializable_9_0 = null;

        EObject lv_eAnnotations_12_0 = null;

        EObject lv_eAnnotations_14_0 = null;

        EObject lv_eTypeParameters_18_0 = null;

        EObject lv_eTypeParameters_20_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5669:6: ( ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5670:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5670:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5670:2: () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5670:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5671:5: 
            {
             
                    temp=factory.create(grammarAccess.getEDataType_ImplAccess().getEDataTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEDataTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,140,FollowSets000.FOLLOW_140_in_ruleEDataType_Impl8879); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getEDataTypeKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5685:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5686:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5686:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5687:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8900);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEDataType_Impl8910); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5713:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==132) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5713:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,132,FollowSets000.FOLLOW_132_in_ruleEDataType_Impl8921); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5717:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5718:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5718:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5719:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8942);
                    lv_instanceClassName_5_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceClassName",
                    	        		lv_instanceClassName_5_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5741:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==133) ) {
                alt166=1;
            }
            switch (alt166) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5741:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,133,FollowSets000.FOLLOW_133_in_ruleEDataType_Impl8955); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5745:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5746:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5746:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5747:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8976);
                    lv_instanceTypeName_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceTypeName",
                    	        		lv_instanceTypeName_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5769:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==141) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5769:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEDataType_Impl8989); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5773:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5774:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5774:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5775:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEDataType_Impl9010);
                    lv_serializable_9_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"serializable",
                    	        		lv_serializable_9_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5797:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==104) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5797:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEDataType_Impl9023); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEDataType_Impl9033); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5805:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5806:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5806:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5807:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl9054);
                    lv_eAnnotations_12_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_12_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5829:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==41) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5829:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEDataType_Impl9065); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5833:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5834:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5834:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5835:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl9086);
                    	    lv_eAnnotations_14_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_14_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop168;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl9098); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5861:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==117) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5861:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEDataType_Impl9111); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEDataType_Impl9121); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5869:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5870:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5870:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5871:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl9142);
                    lv_eTypeParameters_18_0=ruleETypeParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eTypeParameters",
                    	        		lv_eTypeParameters_18_0, 
                    	        		"ETypeParameter", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5893:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop170:
                    do {
                        int alt170=2;
                        int LA170_0 = input.LA(1);

                        if ( (LA170_0==41) ) {
                            alt170=1;
                        }


                        switch (alt170) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5893:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEDataType_Impl9153); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5897:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5898:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5898:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5899:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl9174);
                    	    lv_eTypeParameters_20_0=ruleETypeParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEDataType_ImplRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eTypeParameters",
                    	    	        		lv_eTypeParameters_20_0, 
                    	    	        		"ETypeParameter", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop170;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl9186); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl9198); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_9(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEDataType_Impl


    // $ANTLR start entryRuleEEnum
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5937:1: entryRuleEEnum returns [EObject current=null] : iv_ruleEEnum= ruleEEnum EOF ;
    public final EObject entryRuleEEnum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5938:2: (iv_ruleEEnum= ruleEEnum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5939:2: iv_ruleEEnum= ruleEEnum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnum_in_entryRuleEEnum9234);
            iv_ruleEEnum=ruleEEnum();
            _fsp--;

             current =iv_ruleEEnum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnum9244); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEEnum


    // $ANTLR start ruleEEnum
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5946:1: ruleEEnum returns [EObject current=null] : ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) ;
    public final EObject ruleEEnum() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_instanceClassName_5_0 = null;

        AntlrDatatypeRuleToken lv_instanceTypeName_7_0 = null;

        AntlrDatatypeRuleToken lv_serializable_9_0 = null;

        EObject lv_eAnnotations_12_0 = null;

        EObject lv_eAnnotations_14_0 = null;

        EObject lv_eTypeParameters_18_0 = null;

        EObject lv_eTypeParameters_20_0 = null;

        EObject lv_eLiterals_24_0 = null;

        EObject lv_eLiterals_26_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5951:6: ( ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5952:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5952:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5952:2: () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5952:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5953:5: 
            {
             
                    temp=factory.create(grammarAccess.getEEnumAccess().getEEnumAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEEnumAccess().getEEnumAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,142,FollowSets000.FOLLOW_142_in_ruleEEnum9288); 

                    createLeafNode(grammarAccess.getEEnumAccess().getEEnumKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5967:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5968:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5968:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5969:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum9309);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnum9319); 

                    createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5995:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==132) ) {
                alt172=1;
            }
            switch (alt172) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5995:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,132,FollowSets000.FOLLOW_132_in_ruleEEnum9330); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5999:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6000:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6000:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6001:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum9351);
                    lv_instanceClassName_5_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceClassName",
                    	        		lv_instanceClassName_5_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6023:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==133) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6023:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,133,FollowSets000.FOLLOW_133_in_ruleEEnum9364); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6027:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6028:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6028:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6029:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum9385);
                    lv_instanceTypeName_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"instanceTypeName",
                    	        		lv_instanceTypeName_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6051:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==141) ) {
                alt174=1;
            }
            switch (alt174) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6051:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEEnum9398); 

                            createLeafNode(grammarAccess.getEEnumAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6055:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6056:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6056:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6057:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEEnum9419);
                    lv_serializable_9_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"serializable",
                    	        		lv_serializable_9_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6079:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt176=2;
            int LA176_0 = input.LA(1);

            if ( (LA176_0==104) ) {
                alt176=1;
            }
            switch (alt176) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6079:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEEnum9432); 

                            createLeafNode(grammarAccess.getEEnumAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnum9442); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6087:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6088:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6088:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6089:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum9463);
                    lv_eAnnotations_12_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_12_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6111:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop175:
                    do {
                        int alt175=2;
                        int LA175_0 = input.LA(1);

                        if ( (LA175_0==41) ) {
                            alt175=1;
                        }


                        switch (alt175) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6111:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEEnum9474); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6115:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6116:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6116:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6117:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum9495);
                    	    lv_eAnnotations_14_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_14_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop175;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9507); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6143:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==117) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6143:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEEnum9520); 

                            createLeafNode(grammarAccess.getEEnumAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnum9530); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6151:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6152:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6152:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6153:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum9551);
                    lv_eTypeParameters_18_0=ruleETypeParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eTypeParameters",
                    	        		lv_eTypeParameters_18_0, 
                    	        		"ETypeParameter", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6175:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop177:
                    do {
                        int alt177=2;
                        int LA177_0 = input.LA(1);

                        if ( (LA177_0==41) ) {
                            alt177=1;
                        }


                        switch (alt177) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6175:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEEnum9562); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6179:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6180:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6180:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6181:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum9583);
                    	    lv_eTypeParameters_20_0=ruleETypeParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eTypeParameters",
                    	    	        		lv_eTypeParameters_20_0, 
                    	    	        		"ETypeParameter", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop177;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9595); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6207:3: ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==143) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6207:5: 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}'
                    {
                    match(input,143,FollowSets000.FOLLOW_143_in_ruleEEnum9608); 

                            createLeafNode(grammarAccess.getEEnumAccess().getELiteralsKeyword_9_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnum9618); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6215:1: ( (lv_eLiterals_24_0= ruleEEnumLiteral ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6216:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6216:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6217:3: lv_eLiterals_24_0= ruleEEnumLiteral
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9639);
                    lv_eLiterals_24_0=ruleEEnumLiteral();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eLiterals",
                    	        		lv_eLiterals_24_0, 
                    	        		"EEnumLiteral", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6239:2: ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )*
                    loop179:
                    do {
                        int alt179=2;
                        int LA179_0 = input.LA(1);

                        if ( (LA179_0==41) ) {
                            alt179=1;
                        }


                        switch (alt179) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6239:4: ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEEnum9650); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6243:1: ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6244:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6244:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6245:3: lv_eLiterals_26_0= ruleEEnumLiteral
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9671);
                    	    lv_eLiterals_26_0=ruleEEnumLiteral();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEEnumRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eLiterals",
                    	    	        		lv_eLiterals_26_0, 
                    	    	        		"EEnumLiteral", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop179;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9683); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9695); 

                    createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_10(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEEnum


    // $ANTLR start entryRuleEEnumLiteral
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6283:1: entryRuleEEnumLiteral returns [EObject current=null] : iv_ruleEEnumLiteral= ruleEEnumLiteral EOF ;
    public final EObject entryRuleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnumLiteral = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6284:2: (iv_ruleEEnumLiteral= ruleEEnumLiteral EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6285:2: iv_ruleEEnumLiteral= ruleEEnumLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumLiteralRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9731);
            iv_ruleEEnumLiteral=ruleEEnumLiteral();
            _fsp--;

             current =iv_ruleEEnumLiteral; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnumLiteral9741); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEEnumLiteral


    // $ANTLR start ruleEEnumLiteral
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6292:1: ruleEEnumLiteral returns [EObject current=null] : ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) ;
    public final EObject ruleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        AntlrDatatypeRuleToken lv_literal_7_0 = null;

        EObject lv_eAnnotations_10_0 = null;

        EObject lv_eAnnotations_12_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6297:6: ( ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:2: () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6299:5: 
            {
             
                    temp=factory.create(grammarAccess.getEEnumLiteralAccess().getEEnumLiteralAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEEnumLiteralAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,144,FollowSets000.FOLLOW_144_in_ruleEEnumLiteral9785); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getEEnumLiteralKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6313:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6314:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6314:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6315:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9806);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEEnumLiteralRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnumLiteral9816); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6341:1: ( 'value' ( (lv_value_5_0= ruleEInt ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==128) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6341:3: 'value' ( (lv_value_5_0= ruleEInt ) )
                    {
                    match(input,128,FollowSets000.FOLLOW_128_in_ruleEEnumLiteral9827); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6345:1: ( (lv_value_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6346:1: (lv_value_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6346:1: (lv_value_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6347:3: lv_value_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getValueEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEEnumLiteral9848);
                    lv_value_5_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumLiteralRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"value",
                    	        		lv_value_5_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6369:4: ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==145) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6369:6: 'literal' ( (lv_literal_7_0= ruleEString ) )
                    {
                    match(input,145,FollowSets000.FOLLOW_145_in_ruleEEnumLiteral9861); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLiteralKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6373:1: ( (lv_literal_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6374:1: (lv_literal_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6374:1: (lv_literal_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6375:3: lv_literal_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getLiteralEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9882);
                    lv_literal_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumLiteralRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"literal",
                    	        		lv_literal_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6397:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==104) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6397:6: 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEEnumLiteral9895); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsKeyword_6_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEEnumLiteral9905); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6405:1: ( (lv_eAnnotations_10_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6406:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6406:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6407:3: lv_eAnnotations_10_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9926);
                    lv_eAnnotations_10_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEEnumLiteralRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_10_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6429:2: ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )*
                    loop183:
                    do {
                        int alt183=2;
                        int LA183_0 = input.LA(1);

                        if ( (LA183_0==41) ) {
                            alt183=1;
                        }


                        switch (alt183) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6429:4: ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEEnumLiteral9937); 

                    	            createLeafNode(grammarAccess.getEEnumLiteralAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6433:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6434:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6434:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6435:3: lv_eAnnotations_12_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9958);
                    	    lv_eAnnotations_12_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEEnumLiteralRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_12_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop183;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnumLiteral9970); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnumLiteral9982); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEEnumLiteral


    // $ANTLR start entryRuleEAttribute
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6473:1: entryRuleEAttribute returns [EObject current=null] : iv_ruleEAttribute= ruleEAttribute EOF ;
    public final EObject entryRuleEAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAttribute = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6474:2: (iv_ruleEAttribute= ruleEAttribute EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6475:2: iv_ruleEAttribute= ruleEAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAttributeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_entryRuleEAttribute10018);
            iv_ruleEAttribute=ruleEAttribute();
            _fsp--;

             current =iv_ruleEAttribute; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAttribute10028); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEAttribute


    // $ANTLR start ruleEAttribute
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6482:1: ruleEAttribute returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEAttribute() throws RecognitionException {
        EObject current = null;

        Token lv_volatile_1_0=null;
        Token lv_transient_2_0=null;
        Token lv_unsettable_3_0=null;
        Token lv_derived_4_0=null;
        Token lv_iD_5_0=null;
        AntlrDatatypeRuleToken lv_name_7_0 = null;

        AntlrDatatypeRuleToken lv_ordered_10_0 = null;

        AntlrDatatypeRuleToken lv_unique_12_0 = null;

        AntlrDatatypeRuleToken lv_lowerBound_14_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_16_0 = null;

        AntlrDatatypeRuleToken lv_changeable_18_0 = null;

        AntlrDatatypeRuleToken lv_defaultValueLiteral_20_0 = null;

        EObject lv_eAnnotations_25_0 = null;

        EObject lv_eAnnotations_27_0 = null;

        EObject lv_eGenericType_30_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6487:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6488:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6488:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6488:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6488:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6489:5: 
            {
             
                    temp=factory.create(grammarAccess.getEAttributeAccess().getEAttributeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEAttributeAccess().getEAttributeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6499:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==146) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6500:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6500:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6501:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,146,FollowSets000.FOLLOW_146_in_ruleEAttribute10080); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getVolatileVolatileKeyword_1_0(), "volatile"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "volatile", true, "volatile", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6520:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==147) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6521:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6521:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6522:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,147,FollowSets000.FOLLOW_147_in_ruleEAttribute10112); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getTransientTransientKeyword_2_0(), "transient"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "transient", true, "transient", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6541:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==148) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6542:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6542:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6543:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,148,FollowSets000.FOLLOW_148_in_ruleEAttribute10144); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUnsettableUnsettableKeyword_3_0(), "unsettable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "unsettable", true, "unsettable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6562:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==149) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6563:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6563:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6564:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,149,FollowSets000.FOLLOW_149_in_ruleEAttribute10176); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getDerivedDerivedKeyword_4_0(), "derived"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "derived", true, "derived", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6583:3: ( (lv_iD_5_0= 'iD' ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==150) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6584:1: (lv_iD_5_0= 'iD' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6584:1: (lv_iD_5_0= 'iD' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6585:3: lv_iD_5_0= 'iD'
                    {
                    lv_iD_5_0=(Token)input.LT(1);
                    match(input,150,FollowSets000.FOLLOW_150_in_ruleEAttribute10208); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getIDIDKeyword_5_0(), "iD"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "iD", true, "iD", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,151,FollowSets000.FOLLOW_151_in_ruleEAttribute10232); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getEAttributeKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6608:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6609:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6609:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6610:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute10253);
            lv_name_7_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_7_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEAttribute10263); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6636:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==110) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6636:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEAttribute10274); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6640:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6641:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6641:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6642:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute10295);
                    lv_ordered_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"ordered",
                    	        		lv_ordered_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6664:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==111) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6664:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEAttribute10308); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6668:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6669:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6669:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6670:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute10329);
                    lv_unique_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"unique",
                    	        		lv_unique_12_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6692:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==112) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6692:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEAttribute10342); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6696:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6697:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6697:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6698:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute10363);
                    lv_lowerBound_14_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lowerBound",
                    	        		lv_lowerBound_14_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6720:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==113) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6720:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,113,FollowSets000.FOLLOW_113_in_ruleEAttribute10376); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6724:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6725:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6725:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6726:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute10397);
                    lv_upperBound_16_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"upperBound",
                    	        		lv_upperBound_16_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6748:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt194=2;
            int LA194_0 = input.LA(1);

            if ( (LA194_0==152) ) {
                alt194=1;
            }
            switch (alt194) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6748:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,152,FollowSets000.FOLLOW_152_in_ruleEAttribute10410); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6752:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6753:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6753:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6754:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute10431);
                    lv_changeable_18_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"changeable",
                    	        		lv_changeable_18_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6776:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==153) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6776:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,153,FollowSets000.FOLLOW_153_in_ruleEAttribute10444); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6780:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6781:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6781:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6782:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute10465);
                    lv_defaultValueLiteral_20_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"defaultValueLiteral",
                    	        		lv_defaultValueLiteral_20_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6804:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==114) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6804:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEAttribute10478); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getETypeKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6808:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6809:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6809:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6810:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getETypeEClassifierCrossReference_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute10501);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6824:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==104) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6824:6: 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEAttribute10514); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEAnnotationsKeyword_16_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEAttribute10524); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_16_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6832:1: ( (lv_eAnnotations_25_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6833:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6833:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6834:3: lv_eAnnotations_25_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute10545);
                    lv_eAnnotations_25_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_25_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6856:2: ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )*
                    loop197:
                    do {
                        int alt197=2;
                        int LA197_0 = input.LA(1);

                        if ( (LA197_0==41) ) {
                            alt197=1;
                        }


                        switch (alt197) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6856:4: ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEAttribute10556); 

                    	            createLeafNode(grammarAccess.getEAttributeAccess().getCommaKeyword_16_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6860:1: ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6861:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6861:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6862:3: lv_eAnnotations_27_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute10577);
                    	    lv_eAnnotations_27_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_27_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop197;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAttribute10589); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getRightCurlyBracketKeyword_16_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6888:3: ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==116) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6888:5: 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEAttribute10602); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEGenericTypeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6892:1: ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6893:1: (lv_eGenericType_30_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6893:1: (lv_eGenericType_30_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6894:3: lv_eGenericType_30_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEGenericTypeEGenericTypeParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEAttribute10623);
                    lv_eGenericType_30_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eGenericType",
                    	        		lv_eGenericType_30_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEAttribute10635); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getRightCurlyBracketKeyword_18(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEAttribute


    // $ANTLR start entryRuleEReference
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6928:1: entryRuleEReference returns [EObject current=null] : iv_ruleEReference= ruleEReference EOF ;
    public final EObject entryRuleEReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEReference = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6929:2: (iv_ruleEReference= ruleEReference EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6930:2: iv_ruleEReference= ruleEReference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEReferenceRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEReference_in_entryRuleEReference10671);
            iv_ruleEReference=ruleEReference();
            _fsp--;

             current =iv_ruleEReference; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEReference10681); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEReference


    // $ANTLR start ruleEReference
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6937:1: ruleEReference returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEReference() throws RecognitionException {
        EObject current = null;

        Token lv_volatile_1_0=null;
        Token lv_transient_2_0=null;
        Token lv_unsettable_3_0=null;
        Token lv_derived_4_0=null;
        Token lv_containment_5_0=null;
        AntlrDatatypeRuleToken lv_name_7_0 = null;

        AntlrDatatypeRuleToken lv_ordered_10_0 = null;

        AntlrDatatypeRuleToken lv_unique_12_0 = null;

        AntlrDatatypeRuleToken lv_lowerBound_14_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_16_0 = null;

        AntlrDatatypeRuleToken lv_changeable_18_0 = null;

        AntlrDatatypeRuleToken lv_defaultValueLiteral_20_0 = null;

        AntlrDatatypeRuleToken lv_resolveProxies_22_0 = null;

        EObject lv_eAnnotations_35_0 = null;

        EObject lv_eAnnotations_37_0 = null;

        EObject lv_eGenericType_40_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6942:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6943:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6943:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6943:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6943:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6944:5: 
            {
             
                    temp=factory.create(grammarAccess.getEReferenceAccess().getEReferenceAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEReferenceAccess().getEReferenceAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6954:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==146) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6955:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6955:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6956:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,146,FollowSets000.FOLLOW_146_in_ruleEReference10733); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getVolatileVolatileKeyword_1_0(), "volatile"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "volatile", true, "volatile", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6975:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==147) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6976:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6976:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6977:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,147,FollowSets000.FOLLOW_147_in_ruleEReference10765); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getTransientTransientKeyword_2_0(), "transient"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "transient", true, "transient", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6996:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==148) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6997:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6997:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6998:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,148,FollowSets000.FOLLOW_148_in_ruleEReference10797); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUnsettableUnsettableKeyword_3_0(), "unsettable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "unsettable", true, "unsettable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7017:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==149) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7018:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7018:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7019:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,149,FollowSets000.FOLLOW_149_in_ruleEReference10829); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getDerivedDerivedKeyword_4_0(), "derived"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "derived", true, "derived", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7038:3: ( (lv_containment_5_0= 'containment' ) )?
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==154) ) {
                alt204=1;
            }
            switch (alt204) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7039:1: (lv_containment_5_0= 'containment' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7039:1: (lv_containment_5_0= 'containment' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7040:3: lv_containment_5_0= 'containment'
                    {
                    lv_containment_5_0=(Token)input.LT(1);
                    match(input,154,FollowSets000.FOLLOW_154_in_ruleEReference10861); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getContainmentContainmentKeyword_5_0(), "containment"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "containment", true, "containment", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,155,FollowSets000.FOLLOW_155_in_ruleEReference10885); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getEReferenceKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7063:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7065:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10906);
            lv_name_7_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_7_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleEReference10916); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7091:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt205=2;
            int LA205_0 = input.LA(1);

            if ( (LA205_0==110) ) {
                alt205=1;
            }
            switch (alt205) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7091:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEReference10927); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7095:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7096:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7096:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7097:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10948);
                    lv_ordered_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"ordered",
                    	        		lv_ordered_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7119:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt206=2;
            int LA206_0 = input.LA(1);

            if ( (LA206_0==111) ) {
                alt206=1;
            }
            switch (alt206) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7119:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEReference10961); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7123:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7124:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7124:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7125:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10982);
                    lv_unique_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"unique",
                    	        		lv_unique_12_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7147:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt207=2;
            int LA207_0 = input.LA(1);

            if ( (LA207_0==112) ) {
                alt207=1;
            }
            switch (alt207) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7147:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEReference10995); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7151:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7152:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7152:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7153:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference11016);
                    lv_lowerBound_14_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lowerBound",
                    	        		lv_lowerBound_14_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7175:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt208=2;
            int LA208_0 = input.LA(1);

            if ( (LA208_0==113) ) {
                alt208=1;
            }
            switch (alt208) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7175:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,113,FollowSets000.FOLLOW_113_in_ruleEReference11029); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7179:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7180:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7180:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7181:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference11050);
                    lv_upperBound_16_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"upperBound",
                    	        		lv_upperBound_16_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7203:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt209=2;
            int LA209_0 = input.LA(1);

            if ( (LA209_0==152) ) {
                alt209=1;
            }
            switch (alt209) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7203:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,152,FollowSets000.FOLLOW_152_in_ruleEReference11063); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7207:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7208:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7208:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7209:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference11084);
                    lv_changeable_18_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"changeable",
                    	        		lv_changeable_18_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7231:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt210=2;
            int LA210_0 = input.LA(1);

            if ( (LA210_0==153) ) {
                alt210=1;
            }
            switch (alt210) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7231:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,153,FollowSets000.FOLLOW_153_in_ruleEReference11097); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7235:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7237:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference11118);
                    lv_defaultValueLiteral_20_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"defaultValueLiteral",
                    	        		lv_defaultValueLiteral_20_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7259:4: ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )?
            int alt211=2;
            int LA211_0 = input.LA(1);

            if ( (LA211_0==156) ) {
                alt211=1;
            }
            switch (alt211) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7259:6: 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    {
                    match(input,156,FollowSets000.FOLLOW_156_in_ruleEReference11131); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getResolveProxiesKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7263:1: ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7264:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7264:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7265:3: lv_resolveProxies_22_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getResolveProxiesEBooleanParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference11152);
                    lv_resolveProxies_22_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"resolveProxies",
                    	        		lv_resolveProxies_22_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7287:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt212=2;
            int LA212_0 = input.LA(1);

            if ( (LA212_0==114) ) {
                alt212=1;
            }
            switch (alt212) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7287:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEReference11165); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getETypeKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7291:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7292:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7292:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7293:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getETypeEClassifierCrossReference_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference11188);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7307:4: ( 'eOpposite' ( ( ruleEString ) ) )?
            int alt213=2;
            int LA213_0 = input.LA(1);

            if ( (LA213_0==157) ) {
                alt213=1;
            }
            switch (alt213) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7307:6: 'eOpposite' ( ( ruleEString ) )
                    {
                    match(input,157,FollowSets000.FOLLOW_157_in_ruleEReference11201); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEOppositeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7311:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7312:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7312:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7313:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEOppositeEReferenceCrossReference_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference11224);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7327:4: ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt215=2;
            int LA215_0 = input.LA(1);

            if ( (LA215_0==158) ) {
                alt215=1;
            }
            switch (alt215) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7327:6: 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,158,FollowSets000.FOLLOW_158_in_ruleEReference11237); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEKeysKeyword_18_0(), null); 
                        
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEReference11247); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftParenthesisKeyword_18_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7335:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7336:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7336:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7337:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference11270);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7351:2: ( ',' ( ( ruleEString ) ) )*
                    loop214:
                    do {
                        int alt214=2;
                        int LA214_0 = input.LA(1);

                        if ( (LA214_0==41) ) {
                            alt214=1;
                        }


                        switch (alt214) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7351:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEReference11281); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_18_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7355:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7356:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7356:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7357:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference11304);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop214;
                        }
                    } while (true);

                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEReference11316); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightParenthesisKeyword_18_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7375:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )?
            int alt217=2;
            int LA217_0 = input.LA(1);

            if ( (LA217_0==104) ) {
                alt217=1;
            }
            switch (alt217) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7375:5: 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEReference11329); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEAnnotationsKeyword_19_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEReference11339); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_19_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7383:1: ( (lv_eAnnotations_35_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7384:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7384:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7385:3: lv_eAnnotations_35_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference11360);
                    lv_eAnnotations_35_0=ruleEAnnotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"eAnnotations",
                    	        		lv_eAnnotations_35_0, 
                    	        		"EAnnotation", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7407:2: ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )*
                    loop216:
                    do {
                        int alt216=2;
                        int LA216_0 = input.LA(1);

                        if ( (LA216_0==41) ) {
                            alt216=1;
                        }


                        switch (alt216) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7407:4: ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,41,FollowSets000.FOLLOW_41_in_ruleEReference11371); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_19_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7411:1: ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7412:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7412:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7413:3: lv_eAnnotations_37_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference11392);
                    	    lv_eAnnotations_37_0=ruleEAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"eAnnotations",
                    	    	        		lv_eAnnotations_37_0, 
                    	    	        		"EAnnotation", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop216;
                        }
                    } while (true);

                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEReference11404); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightCurlyBracketKeyword_19_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7439:3: ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )?
            int alt218=2;
            int LA218_0 = input.LA(1);

            if ( (LA218_0==116) ) {
                alt218=1;
            }
            switch (alt218) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7439:5: 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEReference11417); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEGenericTypeKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7443:1: ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7444:1: (lv_eGenericType_40_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7444:1: (lv_eGenericType_40_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7445:3: lv_eGenericType_40_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEGenericTypeEGenericTypeParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEReference11438);
                    lv_eGenericType_40_0=ruleEGenericType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"eGenericType",
                    	        		lv_eGenericType_40_0, 
                    	        		"EGenericType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEReference11450); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getRightCurlyBracketKeyword_21(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEReference


    // $ANTLR start rulelengthUnit
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7479:1: rulelengthUnit returns [Enumerator current=null] : ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) ;
    public final Enumerator rulelengthUnit() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7483:6: ( ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7484:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7484:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            int alt219=9;
            switch ( input.LA(1) ) {
            case 159:
                {
                alt219=1;
                }
                break;
            case 160:
                {
                alt219=2;
                }
                break;
            case 161:
                {
                alt219=3;
                }
                break;
            case 162:
                {
                alt219=4;
                }
                break;
            case 163:
                {
                alt219=5;
                }
                break;
            case 164:
                {
                alt219=6;
                }
                break;
            case 165:
                {
                alt219=7;
                }
                break;
            case 166:
                {
                alt219=8;
                }
                break;
            case 167:
                {
                alt219=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("7484:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )", 219, 0, input);

                throw nvae;
            }

            switch (alt219) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7484:2: ( 'none' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7484:2: ( 'none' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7484:4: 'none'
                    {
                    match(input,159,FollowSets000.FOLLOW_159_in_rulelengthUnit11498); 

                            current = grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7490:6: ( 'mi' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7490:6: ( 'mi' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7490:8: 'mi'
                    {
                    match(input,160,FollowSets000.FOLLOW_160_in_rulelengthUnit11513); 

                            current = grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7496:6: ( 'km' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7496:6: ( 'km' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7496:8: 'km'
                    {
                    match(input,161,FollowSets000.FOLLOW_161_in_rulelengthUnit11528); 

                            current = grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7502:6: ( 'kft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7502:6: ( 'kft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7502:8: 'kft'
                    {
                    match(input,162,FollowSets000.FOLLOW_162_in_rulelengthUnit11543); 

                            current = grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7508:6: ( 'm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7508:6: ( 'm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7508:8: 'm'
                    {
                    match(input,163,FollowSets000.FOLLOW_163_in_rulelengthUnit11558); 

                            current = grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7514:6: ( 'me' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7514:6: ( 'me' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7514:8: 'me'
                    {
                    match(input,164,FollowSets000.FOLLOW_164_in_rulelengthUnit11573); 

                            current = grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5(), null); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7520:6: ( 'ft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7520:6: ( 'ft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7520:8: 'ft'
                    {
                    match(input,165,FollowSets000.FOLLOW_165_in_rulelengthUnit11588); 

                            current = grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6(), null); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7526:6: ( 'in' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7526:6: ( 'in' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7526:8: 'in'
                    {
                    match(input,166,FollowSets000.FOLLOW_166_in_rulelengthUnit11603); 

                            current = grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7(), null); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7532:6: ( 'cm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7532:6: ( 'cm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7532:8: 'cm'
                    {
                    match(input,167,FollowSets000.FOLLOW_167_in_rulelengthUnit11618); 

                            current = grammarAccess.getLengthUnitAccess().getCmEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getCmEnumLiteralDeclaration_8(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulelengthUnit


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleElectrickery_in_entryRuleElectrickery75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElectrickery85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery131 = new BitSet(new long[]{0x0000000000012000L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery142 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery152 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_ruleWireData_in_ruleElectrickery175 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery185 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery203 = new BitSet(new long[]{0x0000000060002000L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery214 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery224 = new BitSet(new long[]{0x0000000060000000L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_ruleElectrickery247 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery257 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery275 = new BitSet(new long[]{0x0000004000002000L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery286 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery296 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_ruleElectrickery319 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery329 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery347 = new BitSet(new long[]{0x0000600000002000L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery358 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery368 = new BitSet(new long[]{0x0000600000000000L});
        public static final BitSet FOLLOW_ruleLineCode_in_ruleElectrickery391 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery401 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery419 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000001L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery430 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleLoadShape_in_ruleElectrickery463 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery473 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery491 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000080L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery502 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_ruleSpectrum_in_ruleElectrickery535 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery545 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_ruleExecutive_in_ruleElectrickery574 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery584 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001000L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature623 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStructuralFeature633 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_ruleEStructuralFeature680 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_ruleEStructuralFeature707 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWireData_in_entryRuleWireData744 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleWireData754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_ruleWireData798 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWireData819 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleWireData829 = new BitSet(new long[]{0x000000001FFC0000L});
        public static final BitSet FOLLOW_18_in_ruleWireData840 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData861 = new BitSet(new long[]{0x000000001FF80000L});
        public static final BitSet FOLLOW_19_in_ruleWireData874 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData895 = new BitSet(new long[]{0x000000001FF00000L});
        public static final BitSet FOLLOW_20_in_ruleWireData908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000FF80000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData929 = new BitSet(new long[]{0x000000001FE00000L});
        public static final BitSet FOLLOW_21_in_ruleWireData942 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData963 = new BitSet(new long[]{0x000000001FC00000L});
        public static final BitSet FOLLOW_22_in_ruleWireData976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000FF80000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData997 = new BitSet(new long[]{0x000000001F800000L});
        public static final BitSet FOLLOW_23_in_ruleWireData1010 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1031 = new BitSet(new long[]{0x000000001F000000L});
        public static final BitSet FOLLOW_24_in_ruleWireData1044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000FF80000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1065 = new BitSet(new long[]{0x000000001E000000L});
        public static final BitSet FOLLOW_25_in_ruleWireData1078 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1099 = new BitSet(new long[]{0x000000001C000000L});
        public static final BitSet FOLLOW_26_in_ruleWireData1112 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1133 = new BitSet(new long[]{0x0000000018000000L});
        public static final BitSet FOLLOW_27_in_ruleWireData1146 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1167 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleWireData1179 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1215 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineGeometry1225 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleLineGeometry1268 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_30_in_ruleLineGeometry1292 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry1313 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLineGeometry1323 = new BitSet(new long[]{0x0000003F86000000L});
        public static final BitSet FOLLOW_31_in_ruleLineGeometry1334 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1355 = new BitSet(new long[]{0x0000003F06000000L});
        public static final BitSet FOLLOW_32_in_ruleLineGeometry1368 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1389 = new BitSet(new long[]{0x0000003E06000000L});
        public static final BitSet FOLLOW_33_in_ruleLineGeometry1402 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1423 = new BitSet(new long[]{0x0000003C06000000L});
        public static final BitSet FOLLOW_34_in_ruleLineGeometry1436 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1457 = new BitSet(new long[]{0x0000003806000000L});
        public static final BitSet FOLLOW_35_in_ruleLineGeometry1470 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1491 = new BitSet(new long[]{0x0000003006000000L});
        public static final BitSet FOLLOW_36_in_ruleLineGeometry1504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000FF80000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineGeometry1525 = new BitSet(new long[]{0x0000002006000000L});
        public static final BitSet FOLLOW_25_in_ruleLineGeometry1538 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1559 = new BitSet(new long[]{0x0000002004000000L});
        public static final BitSet FOLLOW_26_in_ruleLineGeometry1572 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1593 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_37_in_ruleLineGeometry1605 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry1628 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLineGeometry1638 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape1674 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleGrowthShape1684 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleGrowthShape1728 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleGrowthShape1738 = new BitSet(new long[]{0x00001D8010000000L});
        public static final BitSet FOLLOW_39_in_ruleGrowthShape1749 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleGrowthShape1770 = new BitSet(new long[]{0x00001D0010000000L});
        public static final BitSet FOLLOW_40_in_ruleGrowthShape1783 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleGrowthShape1793 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape1814 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleGrowthShape1825 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape1846 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleGrowthShape1858 = new BitSet(new long[]{0x00001C0010000000L});
        public static final BitSet FOLLOW_42_in_ruleGrowthShape1871 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape1892 = new BitSet(new long[]{0x0000180010000000L});
        public static final BitSet FOLLOW_43_in_ruleGrowthShape1905 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape1926 = new BitSet(new long[]{0x0000100010000000L});
        public static final BitSet FOLLOW_44_in_ruleGrowthShape1939 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape1960 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleGrowthShape1972 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineCode_in_entryRuleLineCode2008 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineCode2018 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleLineCode2070 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_46_in_ruleLineCode2094 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLineCode2104 = new BitSet(new long[]{0xFFFF801116000000L});
        public static final BitSet FOLLOW_32_in_ruleLineCode2115 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2136 = new BitSet(new long[]{0xFFFF801016000000L});
        public static final BitSet FOLLOW_47_in_ruleLineCode2149 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2170 = new BitSet(new long[]{0xFFFF001016000000L});
        public static final BitSet FOLLOW_48_in_ruleLineCode2183 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2204 = new BitSet(new long[]{0xFFFE001016000000L});
        public static final BitSet FOLLOW_49_in_ruleLineCode2217 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2238 = new BitSet(new long[]{0xFFFC001016000000L});
        public static final BitSet FOLLOW_50_in_ruleLineCode2251 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2272 = new BitSet(new long[]{0xFFF8001016000000L});
        public static final BitSet FOLLOW_51_in_ruleLineCode2285 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2306 = new BitSet(new long[]{0xFFF0001016000000L});
        public static final BitSet FOLLOW_52_in_ruleLineCode2319 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2340 = new BitSet(new long[]{0xFFE0001016000000L});
        public static final BitSet FOLLOW_36_in_ruleLineCode2353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000FF80000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineCode2374 = new BitSet(new long[]{0xFFE0000016000000L});
        public static final BitSet FOLLOW_53_in_ruleLineCode2387 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2408 = new BitSet(new long[]{0xFFC0000016000000L});
        public static final BitSet FOLLOW_25_in_ruleLineCode2421 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2442 = new BitSet(new long[]{0xFFC0000014000000L});
        public static final BitSet FOLLOW_26_in_ruleLineCode2455 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2476 = new BitSet(new long[]{0xFFC0000010000000L});
        public static final BitSet FOLLOW_54_in_ruleLineCode2489 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2510 = new BitSet(new long[]{0xFF80000010000000L});
        public static final BitSet FOLLOW_55_in_ruleLineCode2523 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2544 = new BitSet(new long[]{0xFF00000010000000L});
        public static final BitSet FOLLOW_56_in_ruleLineCode2557 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2578 = new BitSet(new long[]{0xFE00000010000000L});
        public static final BitSet FOLLOW_57_in_ruleLineCode2591 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2612 = new BitSet(new long[]{0xFC00000010000000L});
        public static final BitSet FOLLOW_58_in_ruleLineCode2625 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2646 = new BitSet(new long[]{0xF800000010000000L});
        public static final BitSet FOLLOW_59_in_ruleLineCode2659 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2680 = new BitSet(new long[]{0xF000000010000000L});
        public static final BitSet FOLLOW_60_in_ruleLineCode2693 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2714 = new BitSet(new long[]{0xE000000010000000L});
        public static final BitSet FOLLOW_61_in_ruleLineCode2727 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode2750 = new BitSet(new long[]{0xC000000010000000L});
        public static final BitSet FOLLOW_62_in_ruleLineCode2763 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode2786 = new BitSet(new long[]{0x8000000010000000L});
        public static final BitSet FOLLOW_63_in_ruleLineCode2799 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode2822 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLineCode2834 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLoadShape_in_entryRuleLoadShape2870 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLoadShape2880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleLoadShape2924 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLoadShape2934 = new BitSet(new long[]{0x00001C8010000000L,0x000000000000007EL});
        public static final BitSet FOLLOW_39_in_ruleLoadShape2945 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape2966 = new BitSet(new long[]{0x00001C0010000000L,0x000000000000007EL});
        public static final BitSet FOLLOW_65_in_ruleLoadShape2979 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape3000 = new BitSet(new long[]{0x00001C0010000000L,0x000000000000007CL});
        public static final BitSet FOLLOW_66_in_ruleLoadShape3013 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLoadShape3023 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3044 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleLoadShape3055 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3076 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3088 = new BitSet(new long[]{0x00001C0010000000L,0x0000000000000078L});
        public static final BitSet FOLLOW_67_in_ruleLoadShape3101 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLoadShape3111 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3132 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleLoadShape3143 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3164 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3176 = new BitSet(new long[]{0x00001C0010000000L,0x0000000000000070L});
        public static final BitSet FOLLOW_68_in_ruleLoadShape3189 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3210 = new BitSet(new long[]{0x00001C0010000000L,0x0000000000000060L});
        public static final BitSet FOLLOW_69_in_ruleLoadShape3223 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3244 = new BitSet(new long[]{0x00001C0010000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_42_in_ruleLoadShape3257 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3278 = new BitSet(new long[]{0x0000180010000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_43_in_ruleLoadShape3291 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3312 = new BitSet(new long[]{0x0000100010000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_44_in_ruleLoadShape3325 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3346 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_70_in_ruleLoadShape3359 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLoadShape3369 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3390 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleLoadShape3401 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3422 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3434 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3446 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleSpectrum_in_entryRuleSpectrum3482 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleSpectrum3492 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleSpectrum3536 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleSpectrum3546 = new BitSet(new long[]{0x0000040010000000L,0x0000000000000F00L});
        public static final BitSet FOLLOW_72_in_ruleSpectrum3557 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleSpectrum3578 = new BitSet(new long[]{0x0000040010000000L,0x0000000000000E00L});
        public static final BitSet FOLLOW_73_in_ruleSpectrum3591 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleSpectrum3601 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3622 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleSpectrum3633 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3654 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum3666 = new BitSet(new long[]{0x0000040010000000L,0x0000000000000C00L});
        public static final BitSet FOLLOW_74_in_ruleSpectrum3679 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3700 = new BitSet(new long[]{0x0000040010000000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleSpectrum3713 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleSpectrum3723 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3744 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleSpectrum3755 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3776 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum3788 = new BitSet(new long[]{0x0000040010000000L});
        public static final BitSet FOLLOW_42_in_ruleSpectrum3801 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleSpectrum3822 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum3834 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExecutive_in_entryRuleExecutive3870 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExecutive3880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleExecutive3924 = new BitSet(new long[]{0x0000000000000000L,0x000000000000E000L});
        public static final BitSet FOLLOW_ruleCircuit_in_ruleExecutive3945 = new BitSet(new long[]{0x0000000000000002L,0x000000000000E000L});
        public static final BitSet FOLLOW_ruleCircuit_in_entryRuleCircuit3982 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCircuit3992 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleCircuit4035 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
        public static final BitSet FOLLOW_78_in_ruleCircuit4067 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_79_in_ruleCircuit4091 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4112 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleCircuit4122 = new BitSet(new long[]{0x0000000010000000L,0x0000000073FF0000L});
        public static final BitSet FOLLOW_80_in_ruleCircuit4133 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleCircuit4154 = new BitSet(new long[]{0x0000000010000000L,0x0000000073FE0000L});
        public static final BitSet FOLLOW_81_in_ruleCircuit4167 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4188 = new BitSet(new long[]{0x0000000010000000L,0x0000000073FC0000L});
        public static final BitSet FOLLOW_82_in_ruleCircuit4201 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4222 = new BitSet(new long[]{0x0000000010000000L,0x0000000073F80000L});
        public static final BitSet FOLLOW_83_in_ruleCircuit4235 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleCircuit4256 = new BitSet(new long[]{0x0000000010000000L,0x0000000073F00000L});
        public static final BitSet FOLLOW_84_in_ruleCircuit4269 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4290 = new BitSet(new long[]{0x0000000010000000L,0x0000000073E00000L});
        public static final BitSet FOLLOW_85_in_ruleCircuit4303 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4324 = new BitSet(new long[]{0x0000000010000000L,0x0000000073C00000L});
        public static final BitSet FOLLOW_86_in_ruleCircuit4337 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4358 = new BitSet(new long[]{0x0000000010000000L,0x0000000073800000L});
        public static final BitSet FOLLOW_87_in_ruleCircuit4371 = new BitSet(new long[]{0x0000000000000040L,0x0000000180000000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleCircuit4392 = new BitSet(new long[]{0x0000000010000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_88_in_ruleCircuit4405 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4428 = new BitSet(new long[]{0x0000000010000000L,0x0000000072000000L});
        public static final BitSet FOLLOW_89_in_ruleCircuit4441 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleCircuit4451 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4474 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleCircuit4485 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4508 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleCircuit4520 = new BitSet(new long[]{0x0000000010000000L,0x0000000070000000L});
        public static final BitSet FOLLOW_92_in_ruleCircuit4533 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleCircuit4543 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4566 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleCircuit4577 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4600 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleCircuit4612 = new BitSet(new long[]{0x0000000010000000L,0x0000000060000000L});
        public static final BitSet FOLLOW_93_in_ruleCircuit4625 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleCircuit4635 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4658 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleCircuit4669 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4692 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleCircuit4704 = new BitSet(new long[]{0x0000000010000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_94_in_ruleCircuit4717 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleCircuit4727 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4750 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleCircuit4761 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit4784 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleCircuit4796 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleCircuit4808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString4845 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString4856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString4896 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString4922 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDouble_in_entryRuleEDouble4968 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDouble4979 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleEDouble5018 = new BitSet(new long[]{0x0000000000000040L,0x0000000100000000L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble5036 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleEDouble5056 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble5071 = new BitSet(new long[]{0x0000000000000002L,0x0000000600000000L});
        public static final BitSet FOLLOW_97_in_ruleEDouble5091 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_98_in_ruleEDouble5110 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEDouble5125 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble5142 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt5190 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt5201 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleEInt5240 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt5257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean5303 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean5314 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleEBoolean5352 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_100_in_ruleEBoolean5371 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation5411 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAnnotation5421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_101_in_ruleEAnnotation5465 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAnnotation5475 = new BitSet(new long[]{0x0000000010000000L,0x000007C000000000L});
        public static final BitSet FOLLOW_102_in_ruleEAnnotation5486 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5507 = new BitSet(new long[]{0x0000000010000000L,0x0000078000000000L});
        public static final BitSet FOLLOW_103_in_ruleEAnnotation5520 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleEAnnotation5530 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5553 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleEAnnotation5564 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5587 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEAnnotation5599 = new BitSet(new long[]{0x0000000010000000L,0x0000070000000000L});
        public static final BitSet FOLLOW_104_in_ruleEAnnotation5612 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAnnotation5622 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5643 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEAnnotation5654 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5675 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5687 = new BitSet(new long[]{0x0000000010000000L,0x0000060000000000L});
        public static final BitSet FOLLOW_105_in_ruleEAnnotation5700 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAnnotation5710 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5731 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEAnnotation5742 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5763 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5775 = new BitSet(new long[]{0x0000000010000000L,0x0000040000000000L});
        public static final BitSet FOLLOW_106_in_ruleEAnnotation5788 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAnnotation5798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5819 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEAnnotation5830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5851 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5863 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5875 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5911 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleETypeParameter5921 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_107_in_ruleETypeParameter5965 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleETypeParameter5986 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleETypeParameter5996 = new BitSet(new long[]{0x0000000010000000L,0x0000110000000000L});
        public static final BitSet FOLLOW_104_in_ruleETypeParameter6007 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleETypeParameter6017 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter6038 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleETypeParameter6049 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter6070 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter6082 = new BitSet(new long[]{0x0000000010000000L,0x0000100000000000L});
        public static final BitSet FOLLOW_108_in_ruleETypeParameter6095 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleETypeParameter6105 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter6126 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleETypeParameter6137 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter6158 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter6170 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter6182 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEOperation_in_entryRuleEOperation6218 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEOperation6228 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleEOperation6272 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6293 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEOperation6303 = new BitSet(new long[]{0x0000000010000000L,0x00FFC10000000000L});
        public static final BitSet FOLLOW_110_in_ruleEOperation6314 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation6335 = new BitSet(new long[]{0x0000000010000000L,0x00FF810000000000L});
        public static final BitSet FOLLOW_111_in_ruleEOperation6348 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation6369 = new BitSet(new long[]{0x0000000010000000L,0x00FF010000000000L});
        public static final BitSet FOLLOW_112_in_ruleEOperation6382 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation6403 = new BitSet(new long[]{0x0000000010000000L,0x00FE010000000000L});
        public static final BitSet FOLLOW_113_in_ruleEOperation6416 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation6437 = new BitSet(new long[]{0x0000000010000000L,0x00FC010000000000L});
        public static final BitSet FOLLOW_114_in_ruleEOperation6450 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6473 = new BitSet(new long[]{0x0000000010000000L,0x00F8010000000000L});
        public static final BitSet FOLLOW_115_in_ruleEOperation6486 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleEOperation6496 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6519 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleEOperation6530 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6553 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEOperation6565 = new BitSet(new long[]{0x0000000010000000L,0x00F0010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEOperation6578 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEOperation6588 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation6609 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEOperation6620 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation6641 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6653 = new BitSet(new long[]{0x0000000010000000L,0x00F0000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEOperation6666 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6687 = new BitSet(new long[]{0x0000000010000000L,0x00E0000000000000L});
        public static final BitSet FOLLOW_117_in_ruleEOperation6700 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEOperation6710 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6731 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEOperation6742 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6763 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6775 = new BitSet(new long[]{0x0000000010000000L,0x00C0000000000000L});
        public static final BitSet FOLLOW_118_in_ruleEOperation6788 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEOperation6798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6819 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEOperation6830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6851 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6863 = new BitSet(new long[]{0x0000000010000000L,0x0080000000000000L});
        public static final BitSet FOLLOW_119_in_ruleEOperation6876 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEOperation6886 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6907 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEOperation6918 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6939 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6951 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6963 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEGenericType_in_entryRuleEGenericType6999 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEGenericType7009 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_120_in_ruleEGenericType7053 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEGenericType7063 = new BitSet(new long[]{0x0000000010000000L,0x3E00000000000000L});
        public static final BitSet FOLLOW_121_in_ruleEGenericType7074 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType7097 = new BitSet(new long[]{0x0000000010000000L,0x3C00000000000000L});
        public static final BitSet FOLLOW_122_in_ruleEGenericType7110 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType7133 = new BitSet(new long[]{0x0000000010000000L,0x3800000000000000L});
        public static final BitSet FOLLOW_123_in_ruleEGenericType7146 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType7167 = new BitSet(new long[]{0x0000000010000000L,0x3000000000000000L});
        public static final BitSet FOLLOW_124_in_ruleEGenericType7180 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEGenericType7190 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType7211 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEGenericType7222 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType7243 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEGenericType7255 = new BitSet(new long[]{0x0000000010000000L,0x2000000000000000L});
        public static final BitSet FOLLOW_125_in_ruleEGenericType7268 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType7289 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEGenericType7301 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry7337 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStringToStringMapEntry7347 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_126_in_ruleEStringToStringMapEntry7391 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEStringToStringMapEntry7401 = new BitSet(new long[]{0x0000000010000000L,0x8000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_127_in_ruleEStringToStringMapEntry7412 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7433 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_128_in_ruleEStringToStringMapEntry7446 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7467 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEStringToStringMapEntry7479 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEClass_in_entryRuleEClass7515 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEClass7525 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_129_in_ruleEClass7577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000000CL});
        public static final BitSet FOLLOW_130_in_ruleEClass7609 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_131_in_ruleEClass7633 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7654 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass7664 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x00000000000003F0L});
        public static final BitSet FOLLOW_132_in_ruleEClass7675 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7696 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x00000000000003E0L});
        public static final BitSet FOLLOW_133_in_ruleEClass7709 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7730 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x00000000000003C0L});
        public static final BitSet FOLLOW_134_in_ruleEClass7743 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleEClass7753 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7776 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass7787 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7810 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEClass7822 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x0000000000000380L});
        public static final BitSet FOLLOW_104_in_ruleEClass7835 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass7845 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7866 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass7877 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7898 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7910 = new BitSet(new long[]{0x0000000010000000L,0x0020000000000000L,0x0000000000000380L});
        public static final BitSet FOLLOW_117_in_ruleEClass7923 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass7933 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7954 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass7965 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7986 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7998 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000000L,0x0000000000000380L});
        public static final BitSet FOLLOW_135_in_ruleEClass8011 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass8021 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass8042 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass8053 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass8074 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass8086 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000000L,0x0000000000000300L});
        public static final BitSet FOLLOW_136_in_ruleEClass8099 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass8109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000CFC0000L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass8130 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass8141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000CFC0000L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass8162 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass8174 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_137_in_ruleEClass8187 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEClass8197 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass8218 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEClass8229 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass8250 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass8262 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass8274 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEObject_in_entryRuleEObject8310 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEObject8320 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_138_in_ruleEObject8364 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEParameter_in_entryRuleEParameter8400 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEParameter8410 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_139_in_ruleEParameter8454 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter8475 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEParameter8485 = new BitSet(new long[]{0x0000000010000000L,0x0017C10000000000L});
        public static final BitSet FOLLOW_110_in_ruleEParameter8496 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter8517 = new BitSet(new long[]{0x0000000010000000L,0x0017810000000000L});
        public static final BitSet FOLLOW_111_in_ruleEParameter8530 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter8551 = new BitSet(new long[]{0x0000000010000000L,0x0017010000000000L});
        public static final BitSet FOLLOW_112_in_ruleEParameter8564 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter8585 = new BitSet(new long[]{0x0000000010000000L,0x0016010000000000L});
        public static final BitSet FOLLOW_113_in_ruleEParameter8598 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter8619 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L});
        public static final BitSet FOLLOW_114_in_ruleEParameter8632 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter8655 = new BitSet(new long[]{0x0000000010000000L,0x0010010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEParameter8668 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEParameter8678 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8699 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEParameter8710 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8731 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEParameter8743 = new BitSet(new long[]{0x0000000010000000L,0x0010000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEParameter8756 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEParameter8777 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEParameter8789 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8825 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDataType_Impl8835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_140_in_ruleEDataType_Impl8879 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8900 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEDataType_Impl8910 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x0000000000002030L});
        public static final BitSet FOLLOW_132_in_ruleEDataType_Impl8921 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8942 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x0000000000002020L});
        public static final BitSet FOLLOW_133_in_ruleEDataType_Impl8955 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8976 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_141_in_ruleEDataType_Impl8989 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEDataType_Impl9010 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEDataType_Impl9023 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEDataType_Impl9033 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl9054 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEDataType_Impl9065 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl9086 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl9098 = new BitSet(new long[]{0x0000000010000000L,0x0020000000000000L});
        public static final BitSet FOLLOW_117_in_ruleEDataType_Impl9111 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEDataType_Impl9121 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl9142 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEDataType_Impl9153 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl9174 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl9186 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl9198 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnum_in_entryRuleEEnum9234 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnum9244 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_142_in_ruleEEnum9288 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum9309 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnum9319 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x000000000000A030L});
        public static final BitSet FOLLOW_132_in_ruleEEnum9330 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum9351 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x000000000000A020L});
        public static final BitSet FOLLOW_133_in_ruleEEnum9364 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum9385 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x000000000000A000L});
        public static final BitSet FOLLOW_141_in_ruleEEnum9398 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEEnum9419 = new BitSet(new long[]{0x0000000010000000L,0x0020010000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_104_in_ruleEEnum9432 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnum9442 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum9463 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEEnum9474 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum9495 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9507 = new BitSet(new long[]{0x0000000010000000L,0x0020000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_117_in_ruleEEnum9520 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnum9530 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum9551 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEEnum9562 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum9583 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9595 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_143_in_ruleEEnum9608 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnum9618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9639 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEEnum9650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9671 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9683 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9695 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9731 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnumLiteral9741 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_144_in_ruleEEnumLiteral9785 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9806 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnumLiteral9816 = new BitSet(new long[]{0x0000000010000000L,0x0000010000000000L,0x0000000000020001L});
        public static final BitSet FOLLOW_128_in_ruleEEnumLiteral9827 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEEnumLiteral9848 = new BitSet(new long[]{0x0000000010000000L,0x0000010000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_145_in_ruleEEnumLiteral9861 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9882 = new BitSet(new long[]{0x0000000010000000L,0x0000010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEEnumLiteral9895 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEEnumLiteral9905 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9926 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEEnumLiteral9937 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9958 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnumLiteral9970 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnumLiteral9982 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_entryRuleEAttribute10018 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAttribute10028 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_146_in_ruleEAttribute10080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000F80000L});
        public static final BitSet FOLLOW_147_in_ruleEAttribute10112 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000F00000L});
        public static final BitSet FOLLOW_148_in_ruleEAttribute10144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000E00000L});
        public static final BitSet FOLLOW_149_in_ruleEAttribute10176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_150_in_ruleEAttribute10208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_151_in_ruleEAttribute10232 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute10253 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAttribute10263 = new BitSet(new long[]{0x0000000010000000L,0x0017C10000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_110_in_ruleEAttribute10274 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute10295 = new BitSet(new long[]{0x0000000010000000L,0x0017810000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_111_in_ruleEAttribute10308 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute10329 = new BitSet(new long[]{0x0000000010000000L,0x0017010000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_112_in_ruleEAttribute10342 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute10363 = new BitSet(new long[]{0x0000000010000000L,0x0016010000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_113_in_ruleEAttribute10376 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute10397 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_152_in_ruleEAttribute10410 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute10431 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000002000000L});
        public static final BitSet FOLLOW_153_in_ruleEAttribute10444 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute10465 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L});
        public static final BitSet FOLLOW_114_in_ruleEAttribute10478 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute10501 = new BitSet(new long[]{0x0000000010000000L,0x0010010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEAttribute10514 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEAttribute10524 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute10545 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEAttribute10556 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute10577 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAttribute10589 = new BitSet(new long[]{0x0000000010000000L,0x0010000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEAttribute10602 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEAttribute10623 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAttribute10635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_entryRuleEReference10671 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEReference10681 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_146_in_ruleEReference10733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000C380000L});
        public static final BitSet FOLLOW_147_in_ruleEReference10765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000C300000L});
        public static final BitSet FOLLOW_148_in_ruleEReference10797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000C200000L});
        public static final BitSet FOLLOW_149_in_ruleEReference10829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_154_in_ruleEReference10861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_155_in_ruleEReference10885 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10906 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEReference10916 = new BitSet(new long[]{0x0000000010000000L,0x0017C10000000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_110_in_ruleEReference10927 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10948 = new BitSet(new long[]{0x0000000010000000L,0x0017810000000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_111_in_ruleEReference10961 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10982 = new BitSet(new long[]{0x0000000010000000L,0x0017010000000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_112_in_ruleEReference10995 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference11016 = new BitSet(new long[]{0x0000000010000000L,0x0016010000000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_113_in_ruleEReference11029 = new BitSet(new long[]{0x0000000000000040L,0x0000000080000000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference11050 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000073000000L});
        public static final BitSet FOLLOW_152_in_ruleEReference11063 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference11084 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000072000000L});
        public static final BitSet FOLLOW_153_in_ruleEReference11097 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference11118 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000070000000L});
        public static final BitSet FOLLOW_156_in_ruleEReference11131 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference11152 = new BitSet(new long[]{0x0000000010000000L,0x0014010000000000L,0x0000000060000000L});
        public static final BitSet FOLLOW_114_in_ruleEReference11165 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference11188 = new BitSet(new long[]{0x0000000010000000L,0x0010010000000000L,0x0000000060000000L});
        public static final BitSet FOLLOW_157_in_ruleEReference11201 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference11224 = new BitSet(new long[]{0x0000000010000000L,0x0010010000000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_158_in_ruleEReference11237 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleEReference11247 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference11270 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_41_in_ruleEReference11281 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference11304 = new BitSet(new long[]{0x0000020000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEReference11316 = new BitSet(new long[]{0x0000000010000000L,0x0010010000000000L});
        public static final BitSet FOLLOW_104_in_ruleEReference11329 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleEReference11339 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference11360 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_41_in_ruleEReference11371 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference11392 = new BitSet(new long[]{0x0000020010000000L});
        public static final BitSet FOLLOW_28_in_ruleEReference11404 = new BitSet(new long[]{0x0000000010000000L,0x0010000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEReference11417 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEReference11438 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEReference11450 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_159_in_rulelengthUnit11498 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_160_in_rulelengthUnit11513 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_161_in_rulelengthUnit11528 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_162_in_rulelengthUnit11543 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_163_in_rulelengthUnit11558 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_164_in_rulelengthUnit11573 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_165_in_rulelengthUnit11588 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_166_in_rulelengthUnit11603 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_167_in_rulelengthUnit11618 = new BitSet(new long[]{0x0000000000000002L});
    }


}