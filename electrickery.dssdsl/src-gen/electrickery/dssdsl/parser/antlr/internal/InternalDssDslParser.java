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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_LINE_CONTINUATION", "RULE_INLINE_COMMENT", "RULE_NEW", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'new'", "'object'", "'='", "'\\n'", "'clear'", "'Circuit'", "'circuit'", "'.'", "'phases'", "'mvasc3'", "'mvasc1'", "'basekV'", "'pu'", "'WireData'", "'{'", "'rDC'", "'rAC'", "'rUnits'", "'gmrAC'", "'gmrUnits'", "'radius'", "'radUnits'", "'normAmps'", "'emergAmps'", "'diameter'", "'}'", "'reduce'", "'LineGeometry'", "'nConds'", "'nPhases'", "'cond'", "'x'", "'h'", "'units'", "'wire'", "'GrowthShape'", "'nPts'", "'year'", "','", "'csvFile'", "'sngFile'", "'dblFile'", "'kron'", "'LineCode'", "'r1'", "'x1'", "'r0'", "'x0'", "'c1'", "'c0'", "'baseFreq'", "'faultRate'", "'pctPerm'", "'repair'", "'rg'", "'xg'", "'rho'", "'neutral'", "'rMatrix'", "'xMatrix'", "'cMatrix'", "'LoadShape'", "'interval'", "'mult'", "'hour'", "'mean'", "'stdDev'", "'qMult'", "'Spectrum'", "'nHarm'", "'harmonic'", "'pctMag'", "'angle'", "'-'", "'E'", "'e'", "'true'", "'false'", "'EAnnotation'", "'source'", "'references'", "'('", "')'", "'eAnnotations'", "'details'", "'contents'", "'ETypeParameter'", "'eBounds'", "'EOperation'", "'ordered'", "'unique'", "'lowerBound'", "'upperBound'", "'eType'", "'eExceptions'", "'eGenericType'", "'eTypeParameters'", "'eParameters'", "'eGenericExceptions'", "'EGenericType'", "'eTypeParameter'", "'eClassifier'", "'eUpperBound'", "'eTypeArguments'", "'eLowerBound'", "'EStringToStringMapEntry'", "'key'", "'value'", "'abstract'", "'interface'", "'EClass'", "'instanceClassName'", "'instanceTypeName'", "'eSuperTypes'", "'eOperations'", "'eStructuralFeatures'", "'eGenericSuperTypes'", "'EObject'", "'EParameter'", "'EDataType'", "'serializable'", "'EEnum'", "'eLiterals'", "'EEnumLiteral'", "'literal'", "'volatile'", "'transient'", "'unsettable'", "'derived'", "'iD'", "'EAttribute'", "'changeable'", "'defaultValueLiteral'", "'containment'", "'EReference'", "'resolveProxies'", "'eOpposite'", "'eKeys'", "'none'", "'mi'", "'km'", "'kft'", "'m'", "'me'", "'ft'", "'in'", "'cm'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_INLINE_COMMENT=8;
    public static final int RULE_STRING=4;
    public static final int RULE_NEW=9;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=6;
    public static final int RULE_WS=12;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=10;
    public static final int RULE_LINE_CONTINUATION=7;

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:87:1: ruleElectrickery returns [EObject current=null] : ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )* ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:92:6: ( ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )* ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )* )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )* )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:2: () ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )*
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:2: ( ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineGeometries_9_0= ruleLineGeometry ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_growthShapes_14_0= ruleGrowthShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_lineCodes_19_0= ruleLineCode ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_loadShapes_24_0= ruleLoadShape ) ) '\\n' ) | ( 'new' ( 'object' '=' )? ( (lv_spectrums_29_0= ruleSpectrum ) ) '\\n' ) | ( (lv_executives_31_0= ruleExecutive ) ) )*
            loop7:
            do {
                int alt7=8;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==14) ) {
                    switch ( input.LA(2) ) {
                    case 15:
                        {
                        int LA7_4 = input.LA(3);

                        if ( (LA7_4==16) ) {
                            switch ( input.LA(4) ) {
                            case 40:
                            case 41:
                                {
                                alt7=2;
                                }
                                break;
                            case 27:
                                {
                                alt7=1;
                                }
                                break;
                            case 56:
                            case 57:
                                {
                                alt7=4;
                                }
                                break;
                            case 49:
                                {
                                alt7=3;
                                }
                                break;
                            case 82:
                                {
                                alt7=6;
                                }
                                break;
                            case 75:
                                {
                                alt7=5;
                                }
                                break;

                            }

                        }


                        }
                        break;
                    case 40:
                    case 41:
                        {
                        alt7=2;
                        }
                        break;
                    case 56:
                    case 57:
                        {
                        alt7=4;
                        }
                        break;
                    case 49:
                        {
                        alt7=3;
                        }
                        break;
                    case 27:
                        {
                        alt7=1;
                        }
                        break;
                    case 75:
                        {
                        alt7=5;
                        }
                        break;
                    case 82:
                        {
                        alt7=6;
                        }
                        break;

                    }

                }
                else if ( (LA7_0==18) ) {
                    alt7=7;
                }


                switch (alt7) {
            	case 1 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:3: ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:3: ( 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n' )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:104:5: 'new' ( 'object' '=' )? ( (lv_wireData_4_0= ruleWireData ) ) '\\n'
            	    {
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery131); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_0_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:108:1: ( 'object' '=' )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==15) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:108:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery142); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_0_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery152); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery185); 

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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery203); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_1_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:1: ( 'object' '=' )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==15) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery214); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_1_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery224); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery257); 

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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery275); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_2_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:190:1: ( 'object' '=' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==15) ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:190:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery286); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_2_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery296); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery329); 

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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery347); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_3_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:231:1: ( 'object' '=' )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==15) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:231:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery358); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_3_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery368); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery401); 

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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery419); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_4_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:1: ( 'object' '=' )?
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==15) ) {
            	        alt5=1;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery430); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_4_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery440); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery473); 

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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery491); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getNewKeyword_1_5_0(), null); 
            	        
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:1: ( 'object' '=' )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==15) ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:3: 'object' '='
            	            {
            	            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery502); 

            	                    createLeafNode(grammarAccess.getElectrickeryAccess().getObjectKeyword_1_5_1_0(), null); 
            	                
            	            match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery512); 

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

            	    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery545); 

            	            createLeafNode(grammarAccess.getElectrickeryAccess().getLineFeedLfKeyword_1_5_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 7 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:6: ( (lv_executives_31_0= ruleExecutive ) )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:350:6: ( (lv_executives_31_0= ruleExecutive ) )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:351:1: (lv_executives_31_0= ruleExecutive )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:351:1: (lv_executives_31_0= ruleExecutive )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:352:3: lv_executives_31_0= ruleExecutive
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getExecutivesExecutiveParserRuleCall_1_6_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_ruleElectrickery573);
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


    // $ANTLR start entryRuleExecutive
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:382:1: entryRuleExecutive returns [EObject current=null] : iv_ruleExecutive= ruleExecutive EOF ;
    public final EObject entryRuleExecutive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecutive = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:383:2: (iv_ruleExecutive= ruleExecutive EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:384:2: iv_ruleExecutive= ruleExecutive EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExecutiveRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_entryRuleExecutive611);
            iv_ruleExecutive=ruleExecutive();
            _fsp--;

             current =iv_ruleExecutive; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExecutive621); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:391:1: ruleExecutive returns [EObject current=null] : ( () 'clear' ( (lv_circuits_2_0= ruleCircuit ) )+ ) ;
    public final EObject ruleExecutive() throws RecognitionException {
        EObject current = null;

        EObject lv_circuits_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:396:6: ( ( () 'clear' ( (lv_circuits_2_0= ruleCircuit ) )+ ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:397:1: ( () 'clear' ( (lv_circuits_2_0= ruleCircuit ) )+ )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:397:1: ( () 'clear' ( (lv_circuits_2_0= ruleCircuit ) )+ )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:397:2: () 'clear' ( (lv_circuits_2_0= ruleCircuit ) )+
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:397:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:398:5: 
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

            match(input,18,FollowSets000.FOLLOW_18_in_ruleExecutive665); 

                    createLeafNode(grammarAccess.getExecutiveAccess().getClearKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:412:1: ( (lv_circuits_2_0= ruleCircuit ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=19 && LA8_0<=20)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:413:1: (lv_circuits_2_0= ruleCircuit )
            	    {
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:413:1: (lv_circuits_2_0= ruleCircuit )
            	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:414:3: lv_circuits_2_0= ruleCircuit
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getExecutiveAccess().getCircuitsCircuitParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleCircuit_in_ruleExecutive686);
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
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:444:1: entryRuleCircuit returns [EObject current=null] : iv_ruleCircuit= ruleCircuit EOF ;
    public final EObject entryRuleCircuit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCircuit = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:445:2: (iv_ruleCircuit= ruleCircuit EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:446:2: iv_ruleCircuit= ruleCircuit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCircuitRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleCircuit_in_entryRuleCircuit723);
            iv_ruleCircuit=ruleCircuit();
            _fsp--;

             current =iv_ruleCircuit; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCircuit733); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:453:1: ruleCircuit returns [EObject current=null] : ( () ( 'Circuit' | 'circuit' ) ( '.' ( (lv_name_4_0= ruleEString ) ) )? ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) ) ) ;
    public final EObject ruleCircuit() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_voltageSources_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:458:6: ( ( () ( 'Circuit' | 'circuit' ) ( '.' ( (lv_name_4_0= ruleEString ) ) )? ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:459:1: ( () ( 'Circuit' | 'circuit' ) ( '.' ( (lv_name_4_0= ruleEString ) ) )? ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:459:1: ( () ( 'Circuit' | 'circuit' ) ( '.' ( (lv_name_4_0= ruleEString ) ) )? ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:459:2: () ( 'Circuit' | 'circuit' ) ( '.' ( (lv_name_4_0= ruleEString ) ) )? ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:459:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:460:5: 
            {
             
                    temp=factory.create(grammarAccess.getCircuitAccess().getCircuitAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getCircuitAccess().getCircuitAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:470:2: ( 'Circuit' | 'circuit' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            else if ( (LA9_0==20) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("470:2: ( 'Circuit' | 'circuit' )", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:470:4: 'Circuit'
                    {
                    match(input,19,FollowSets000.FOLLOW_19_in_ruleCircuit778); 

                            createLeafNode(grammarAccess.getCircuitAccess().getCircuitKeyword_1_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:475:7: 'circuit'
                    {
                    match(input,20,FollowSets000.FOLLOW_20_in_ruleCircuit794); 

                            createLeafNode(grammarAccess.getCircuitAccess().getCircuitKeyword_1_1(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:479:2: ( '.' ( (lv_name_4_0= ruleEString ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:479:4: '.' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,21,FollowSets000.FOLLOW_21_in_ruleCircuit806); 

                            createLeafNode(grammarAccess.getCircuitAccess().getFullStopKeyword_2_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:483:1: ( (lv_name_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:484:1: (lv_name_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:484:1: (lv_name_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:485:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getNameEStringParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCircuit827);
                    lv_name_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
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


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:507:4: ( (lv_voltageSources_5_0= ruleDefaultVoltageSource ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:508:1: (lv_voltageSources_5_0= ruleDefaultVoltageSource )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:508:1: (lv_voltageSources_5_0= ruleDefaultVoltageSource )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:509:3: lv_voltageSources_5_0= ruleDefaultVoltageSource
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getCircuitAccess().getVoltageSourcesDefaultVoltageSourceParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleDefaultVoltageSource_in_ruleCircuit850);
            lv_voltageSources_5_0=ruleDefaultVoltageSource();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getCircuitRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"voltageSources",
            	        		lv_voltageSources_5_0, 
            	        		"DefaultVoltageSource", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


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
    // $ANTLR end ruleCircuit


    // $ANTLR start entryRuleDefaultVoltageSource
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:541:1: entryRuleDefaultVoltageSource returns [EObject current=null] : iv_ruleDefaultVoltageSource= ruleDefaultVoltageSource EOF ;
    public final EObject entryRuleDefaultVoltageSource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefaultVoltageSource = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:542:2: (iv_ruleDefaultVoltageSource= ruleDefaultVoltageSource EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:543:2: iv_ruleDefaultVoltageSource= ruleDefaultVoltageSource EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDefaultVoltageSourceRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleDefaultVoltageSource_in_entryRuleDefaultVoltageSource888);
            iv_ruleDefaultVoltageSource=ruleDefaultVoltageSource();
            _fsp--;

             current =iv_ruleDefaultVoltageSource; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleDefaultVoltageSource898); 

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
    // $ANTLR end entryRuleDefaultVoltageSource


    // $ANTLR start ruleDefaultVoltageSource
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:550:1: ruleDefaultVoltageSource returns [EObject current=null] : ( () ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? ) ) ;
    public final EObject ruleDefaultVoltageSource() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_nPhases_3_0 = null;

        AntlrDatatypeRuleToken lv_mvaSC3_6_0 = null;

        AntlrDatatypeRuleToken lv_mvaSC1_9_0 = null;

        AntlrDatatypeRuleToken lv_baseKV_12_0 = null;

        AntlrDatatypeRuleToken lv_perUnit_15_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:555:6: ( ( () ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:556:1: ( () ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:556:1: ( () ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:556:2: () ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:556:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:557:5: 
            {
             
                    temp=factory.create(grammarAccess.getDefaultVoltageSourceAccess().getVoltageSourceAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getVoltageSourceAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:567:2: ( ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )? )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:567:3: ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )? ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )? ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )? ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )? ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )?
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:567:3: ( 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:567:5: 'phases' '=' ( (lv_nPhases_3_0= ruleEInt ) )
                    {
                    match(input,22,FollowSets000.FOLLOW_22_in_ruleDefaultVoltageSource944); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getPhasesKeyword_1_0_0(), null); 
                        
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleDefaultVoltageSource954); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getEqualsSignKeyword_1_0_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:575:1: ( (lv_nPhases_3_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:576:1: (lv_nPhases_3_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:576:1: (lv_nPhases_3_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:577:3: lv_nPhases_3_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getNPhasesEIntParserRuleCall_1_0_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleDefaultVoltageSource975);
                    lv_nPhases_3_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDefaultVoltageSourceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nPhases",
                    	        		lv_nPhases_3_0, 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:599:4: ( 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:599:6: 'mvasc3' '=' ( (lv_mvaSC3_6_0= ruleEDouble ) )
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleDefaultVoltageSource988); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getMvasc3Keyword_1_1_0(), null); 
                        
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleDefaultVoltageSource998); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getEqualsSignKeyword_1_1_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:607:1: ( (lv_mvaSC3_6_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:608:1: (lv_mvaSC3_6_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:608:1: (lv_mvaSC3_6_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:609:3: lv_mvaSC3_6_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getMvaSC3EDoubleParserRuleCall_1_1_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1019);
                    lv_mvaSC3_6_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDefaultVoltageSourceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"mvaSC3",
                    	        		lv_mvaSC3_6_0, 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:4: ( 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:6: 'mvasc1' '=' ( (lv_mvaSC1_9_0= ruleEDouble ) )
                    {
                    match(input,24,FollowSets000.FOLLOW_24_in_ruleDefaultVoltageSource1032); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getMvasc1Keyword_1_2_0(), null); 
                        
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleDefaultVoltageSource1042); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getEqualsSignKeyword_1_2_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:639:1: ( (lv_mvaSC1_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:640:1: (lv_mvaSC1_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:640:1: (lv_mvaSC1_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:641:3: lv_mvaSC1_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getMvaSC1EDoubleParserRuleCall_1_2_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1063);
                    lv_mvaSC1_9_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDefaultVoltageSourceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"mvaSC1",
                    	        		lv_mvaSC1_9_0, 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:663:4: ( 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:663:6: 'basekV' '=' ( (lv_baseKV_12_0= ruleEDouble ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleDefaultVoltageSource1076); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getBasekVKeyword_1_3_0(), null); 
                        
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleDefaultVoltageSource1086); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getEqualsSignKeyword_1_3_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:671:1: ( (lv_baseKV_12_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:672:1: (lv_baseKV_12_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:672:1: (lv_baseKV_12_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:673:3: lv_baseKV_12_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getBaseKVEDoubleParserRuleCall_1_3_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1107);
                    lv_baseKV_12_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDefaultVoltageSourceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"baseKV",
                    	        		lv_baseKV_12_0, 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:695:4: ( 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:695:6: 'pu' '=' ( (lv_perUnit_15_0= ruleEDouble ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleDefaultVoltageSource1120); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getPuKeyword_1_4_0(), null); 
                        
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleDefaultVoltageSource1130); 

                            createLeafNode(grammarAccess.getDefaultVoltageSourceAccess().getEqualsSignKeyword_1_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:703:1: ( (lv_perUnit_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:704:1: (lv_perUnit_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:704:1: (lv_perUnit_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:705:3: lv_perUnit_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDefaultVoltageSourceAccess().getPerUnitEDoubleParserRuleCall_1_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1151);
                    lv_perUnit_15_0=ruleEDouble();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDefaultVoltageSourceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"perUnit",
                    	        		lv_perUnit_15_0, 
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
    // $ANTLR end ruleDefaultVoltageSource


    // $ANTLR start entryRuleWireData
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:737:1: entryRuleWireData returns [EObject current=null] : iv_ruleWireData= ruleWireData EOF ;
    public final EObject entryRuleWireData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWireData = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:738:2: (iv_ruleWireData= ruleWireData EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:739:2: iv_ruleWireData= ruleWireData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWireDataRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleWireData_in_entryRuleWireData1192);
            iv_ruleWireData=ruleWireData();
            _fsp--;

             current =iv_ruleWireData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleWireData1202); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:746:1: ruleWireData returns [EObject current=null] : ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:751:6: ( ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:752:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:752:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:752:2: () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:752:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:753:5: 
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

            match(input,27,FollowSets000.FOLLOW_27_in_ruleWireData1246); 

                    createLeafNode(grammarAccess.getWireDataAccess().getWireDataKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:767:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:768:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:768:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:769:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWireData1267);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleWireData1277); 

                    createLeafNode(grammarAccess.getWireDataAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:1: ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==29) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:795:3: 'rDC' ( (lv_rDC_5_0= ruleEDouble ) )
                    {
                    match(input,29,FollowSets000.FOLLOW_29_in_ruleWireData1288); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRDCKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:799:1: ( (lv_rDC_5_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:800:1: (lv_rDC_5_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:800:1: (lv_rDC_5_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:801:3: lv_rDC_5_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRDCEDoubleParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1309);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:823:4: ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==30) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:823:6: 'rAC' ( (lv_rAC_7_0= ruleEDouble ) )
                    {
                    match(input,30,FollowSets000.FOLLOW_30_in_ruleWireData1322); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRACKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:827:1: ( (lv_rAC_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:828:1: (lv_rAC_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:828:1: (lv_rAC_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:829:3: lv_rAC_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRACEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1343);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:851:4: ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:851:6: 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) )
                    {
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleWireData1356); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRUnitsKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:855:1: ( (lv_rUnits_9_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:856:1: (lv_rUnits_9_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:856:1: (lv_rUnits_9_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:857:3: lv_rUnits_9_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRUnitsLengthUnitEnumRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1377);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:879:4: ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==32) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:879:6: 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) )
                    {
                    match(input,32,FollowSets000.FOLLOW_32_in_ruleWireData1390); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrACKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:883:1: ( (lv_gmrAC_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:884:1: (lv_gmrAC_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:884:1: (lv_gmrAC_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:885:3: lv_gmrAC_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrACEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1411);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:907:4: ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==33) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:907:6: 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    {
                    match(input,33,FollowSets000.FOLLOW_33_in_ruleWireData1424); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrUnitsKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:911:1: ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:912:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:912:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:913:3: lv_gmrUnits_13_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrUnitsLengthUnitEnumRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1445);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:935:4: ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==34) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:935:6: 'radius' ( (lv_radius_15_0= ruleEDouble ) )
                    {
                    match(input,34,FollowSets000.FOLLOW_34_in_ruleWireData1458); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadiusKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:939:1: ( (lv_radius_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:940:1: (lv_radius_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:940:1: (lv_radius_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:941:3: lv_radius_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadiusEDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1479);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:963:4: ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==35) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:963:6: 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) )
                    {
                    match(input,35,FollowSets000.FOLLOW_35_in_ruleWireData1492); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadUnitsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:967:1: ( (lv_radUnits_17_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:968:1: (lv_radUnits_17_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:968:1: (lv_radUnits_17_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:969:3: lv_radUnits_17_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadUnitsLengthUnitEnumRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1513);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:991:4: ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==36) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:991:6: 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleWireData1526); 

                            createLeafNode(grammarAccess.getWireDataAccess().getNormAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:995:1: ( (lv_normAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:996:1: (lv_normAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:996:1: (lv_normAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:997:3: lv_normAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNormAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1547);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1019:4: ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==37) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1019:6: 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) )
                    {
                    match(input,37,FollowSets000.FOLLOW_37_in_ruleWireData1560); 

                            createLeafNode(grammarAccess.getWireDataAccess().getEmergAmpsKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1023:1: ( (lv_emergAmps_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1024:1: (lv_emergAmps_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1024:1: (lv_emergAmps_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1025:3: lv_emergAmps_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getEmergAmpsEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1581);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1047:4: ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==38) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1047:6: 'diameter' ( (lv_diameter_23_0= ruleEDouble ) )
                    {
                    match(input,38,FollowSets000.FOLLOW_38_in_ruleWireData1594); 

                            createLeafNode(grammarAccess.getWireDataAccess().getDiameterKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1051:1: ( (lv_diameter_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1052:1: (lv_diameter_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1052:1: (lv_diameter_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1053:3: lv_diameter_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getDiameterEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1615);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleWireData1627); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1087:1: entryRuleLineGeometry returns [EObject current=null] : iv_ruleLineGeometry= ruleLineGeometry EOF ;
    public final EObject entryRuleLineGeometry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineGeometry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1088:2: (iv_ruleLineGeometry= ruleLineGeometry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1089:2: iv_ruleLineGeometry= ruleLineGeometry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineGeometryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1663);
            iv_ruleLineGeometry=ruleLineGeometry();
            _fsp--;

             current =iv_ruleLineGeometry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineGeometry1673); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1096:1: ruleLineGeometry returns [EObject current=null] : ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1101:6: ( ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1102:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1102:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1102:2: ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1102:2: ( (lv_reduce_0_0= 'reduce' ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==40) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1103:1: (lv_reduce_0_0= 'reduce' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1103:1: (lv_reduce_0_0= 'reduce' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1104:3: lv_reduce_0_0= 'reduce'
                    {
                    lv_reduce_0_0=(Token)input.LT(1);
                    match(input,40,FollowSets000.FOLLOW_40_in_ruleLineGeometry1716); 

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

            match(input,41,FollowSets000.FOLLOW_41_in_ruleLineGeometry1740); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLineGeometryKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1127:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1128:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1128:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1129:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry1761);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleLineGeometry1771); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1155:1: ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==42) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1155:3: 'nConds' ( (lv_nConds_5_0= ruleEInt ) )
                    {
                    match(input,42,FollowSets000.FOLLOW_42_in_ruleLineGeometry1782); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNCondsKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1159:1: ( (lv_nConds_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1160:1: (lv_nConds_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1160:1: (lv_nConds_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1161:3: lv_nConds_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNCondsEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1803);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1183:4: ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==43) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1183:6: 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleLineGeometry1816); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNPhasesKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1187:1: ( (lv_nPhases_7_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1188:1: (lv_nPhases_7_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1188:1: (lv_nPhases_7_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1189:3: lv_nPhases_7_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNPhasesEIntParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1837);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1211:4: ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==44) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1211:6: 'cond' ( (lv_cond_9_0= ruleEInt ) )
                    {
                    match(input,44,FollowSets000.FOLLOW_44_in_ruleLineGeometry1850); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getCondKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1215:1: ( (lv_cond_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1216:1: (lv_cond_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1216:1: (lv_cond_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1217:3: lv_cond_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getCondEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1871);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1239:4: ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==45) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1239:6: 'x' ( (lv_x_11_0= ruleEDouble ) )
                    {
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleLineGeometry1884); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getXKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1243:1: ( (lv_x_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1244:1: (lv_x_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1244:1: (lv_x_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1245:3: lv_x_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getXEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1905);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1267:4: ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==46) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1267:6: 'h' ( (lv_h_13_0= ruleEDouble ) )
                    {
                    match(input,46,FollowSets000.FOLLOW_46_in_ruleLineGeometry1918); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getHKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1271:1: ( (lv_h_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1272:1: (lv_h_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1272:1: (lv_h_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1273:3: lv_h_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getHEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1939);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1295:4: ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==47) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1295:6: 'units' ( (lv_units_15_0= rulelengthUnit ) )
                    {
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleLineGeometry1952); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getUnitsKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1299:1: ( (lv_units_15_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1300:1: (lv_units_15_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1300:1: (lv_units_15_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1301:3: lv_units_15_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getUnitsLengthUnitEnumRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineGeometry1973);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1323:4: ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==36) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1323:6: 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineGeometry1986); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNormAmpsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1327:1: ( (lv_normAmps_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1328:1: (lv_normAmps_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1328:1: (lv_normAmps_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1329:3: lv_normAmps_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNormAmpsEDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry2007);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1351:4: ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==37) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1351:6: 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) )
                    {
                    match(input,37,FollowSets000.FOLLOW_37_in_ruleLineGeometry2020); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getEmergAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1355:1: ( (lv_emergAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1356:1: (lv_emergAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1356:1: (lv_emergAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1357:3: lv_emergAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getEmergAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry2041);
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

            match(input,48,FollowSets000.FOLLOW_48_in_ruleLineGeometry2053); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getWireKeyword_12(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1383:1: ( ( ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1384:1: ( ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1384:1: ( ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1385:3: ruleEString
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getWireWireDataCrossReference_13_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry2076);
            ruleEString();
            _fsp--;

             
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleLineGeometry2086); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1411:1: entryRuleGrowthShape returns [EObject current=null] : iv_ruleGrowthShape= ruleGrowthShape EOF ;
    public final EObject entryRuleGrowthShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrowthShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1412:2: (iv_ruleGrowthShape= ruleGrowthShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1413:2: iv_ruleGrowthShape= ruleGrowthShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGrowthShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape2122);
            iv_ruleGrowthShape=ruleGrowthShape();
            _fsp--;

             current =iv_ruleGrowthShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleGrowthShape2132); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1420:1: ruleGrowthShape returns [EObject current=null] : ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1425:6: ( ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1426:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1426:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1426:2: () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1426:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1427:5: 
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

            match(input,49,FollowSets000.FOLLOW_49_in_ruleGrowthShape2176); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getGrowthShapeKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleGrowthShape2186); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1445:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==50) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1445:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,50,FollowSets000.FOLLOW_50_in_ruleGrowthShape2197); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1449:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1450:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1450:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1451:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleGrowthShape2218);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1473:4: ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==51) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1473:6: 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,51,FollowSets000.FOLLOW_51_in_ruleGrowthShape2231); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getYearKeyword_4_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleGrowthShape2241); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1481:1: ( (lv_year_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1482:1: (lv_year_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1482:1: (lv_year_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1483:3: lv_year_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape2262);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1505:2: ( ',' ( (lv_year_9_0= ruleEDouble ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==52) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1505:4: ',' ( (lv_year_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleGrowthShape2273); 

                    	            createLeafNode(grammarAccess.getGrowthShapeAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1509:1: ( (lv_year_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1510:1: (lv_year_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1510:1: (lv_year_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1511:3: lv_year_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape2294);
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
                    	    break loop36;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleGrowthShape2306); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1537:3: ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==53) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1537:5: 'csvFile' ( (lv_csvFile_12_0= ruleEString ) )
                    {
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleGrowthShape2319); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getCsvFileKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1541:1: ( (lv_csvFile_12_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1542:1: (lv_csvFile_12_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1542:1: (lv_csvFile_12_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1543:3: lv_csvFile_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getCsvFileEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2340);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1565:4: ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==54) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1565:6: 'sngFile' ( (lv_sngFile_14_0= ruleEString ) )
                    {
                    match(input,54,FollowSets000.FOLLOW_54_in_ruleGrowthShape2353); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getSngFileKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1569:1: ( (lv_sngFile_14_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1570:1: (lv_sngFile_14_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1570:1: (lv_sngFile_14_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1571:3: lv_sngFile_14_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getSngFileEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2374);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1593:4: ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==55) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1593:6: 'dblFile' ( (lv_dblFile_16_0= ruleEString ) )
                    {
                    match(input,55,FollowSets000.FOLLOW_55_in_ruleGrowthShape2387); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getDblFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1597:1: ( (lv_dblFile_16_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1598:1: (lv_dblFile_16_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1598:1: (lv_dblFile_16_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1599:3: lv_dblFile_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getDblFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2408);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleGrowthShape2420); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1633:1: entryRuleLineCode returns [EObject current=null] : iv_ruleLineCode= ruleLineCode EOF ;
    public final EObject entryRuleLineCode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineCode = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1634:2: (iv_ruleLineCode= ruleLineCode EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1635:2: iv_ruleLineCode= ruleLineCode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineCodeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_entryRuleLineCode2456);
            iv_ruleLineCode=ruleLineCode();
            _fsp--;

             current =iv_ruleLineCode; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineCode2466); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1642:1: ruleLineCode returns [EObject current=null] : ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1647:6: ( ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1648:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1648:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1648:2: () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1648:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1649:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1659:2: ( (lv_kron_1_0= 'kron' ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==56) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1660:1: (lv_kron_1_0= 'kron' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1660:1: (lv_kron_1_0= 'kron' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1661:3: lv_kron_1_0= 'kron'
                    {
                    lv_kron_1_0=(Token)input.LT(1);
                    match(input,56,FollowSets000.FOLLOW_56_in_ruleLineCode2518); 

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

            match(input,57,FollowSets000.FOLLOW_57_in_ruleLineCode2542); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLineCodeKeyword_2(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleLineCode2552); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1688:1: ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==43) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1688:3: 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleLineCode2563); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNPhasesKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1692:1: ( (lv_nPhases_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1693:1: (lv_nPhases_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1693:1: (lv_nPhases_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1694:3: lv_nPhases_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNPhasesEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2584);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1716:4: ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==58) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1716:6: 'r1' ( (lv_r1_7_0= ruleEDouble ) )
                    {
                    match(input,58,FollowSets000.FOLLOW_58_in_ruleLineCode2597); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR1Keyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1720:1: ( (lv_r1_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1721:1: (lv_r1_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1721:1: (lv_r1_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1722:3: lv_r1_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR1EDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2618);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1744:4: ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==59) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1744:6: 'x1' ( (lv_x1_9_0= ruleEDouble ) )
                    {
                    match(input,59,FollowSets000.FOLLOW_59_in_ruleLineCode2631); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX1Keyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1748:1: ( (lv_x1_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1749:1: (lv_x1_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1749:1: (lv_x1_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1750:3: lv_x1_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX1EDoubleParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2652);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1772:4: ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==60) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1772:6: 'r0' ( (lv_r0_11_0= ruleEDouble ) )
                    {
                    match(input,60,FollowSets000.FOLLOW_60_in_ruleLineCode2665); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR0Keyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1776:1: ( (lv_r0_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1777:1: (lv_r0_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1777:1: (lv_r0_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1778:3: lv_r0_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR0EDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2686);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1800:4: ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==61) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1800:6: 'x0' ( (lv_x0_13_0= ruleEDouble ) )
                    {
                    match(input,61,FollowSets000.FOLLOW_61_in_ruleLineCode2699); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX0Keyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1804:1: ( (lv_x0_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1805:1: (lv_x0_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1805:1: (lv_x0_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1806:3: lv_x0_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX0EDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2720);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1828:4: ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==62) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1828:6: 'c1' ( (lv_c1_15_0= ruleEDouble ) )
                    {
                    match(input,62,FollowSets000.FOLLOW_62_in_ruleLineCode2733); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC1Keyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1832:1: ( (lv_c1_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1833:1: (lv_c1_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1833:1: (lv_c1_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1834:3: lv_c1_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC1EDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2754);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1856:4: ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==63) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1856:6: 'c0' ( (lv_c0_17_0= ruleEDouble ) )
                    {
                    match(input,63,FollowSets000.FOLLOW_63_in_ruleLineCode2767); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC0Keyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1860:1: ( (lv_c0_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1861:1: (lv_c0_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1861:1: (lv_c0_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1862:3: lv_c0_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC0EDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2788);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1884:4: ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==47) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1884:6: 'units' ( (lv_units_19_0= rulelengthUnit ) )
                    {
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleLineCode2801); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getUnitsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1888:1: ( (lv_units_19_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1889:1: (lv_units_19_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1889:1: (lv_units_19_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1890:3: lv_units_19_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getUnitsLengthUnitEnumRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineCode2822);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1912:4: ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==64) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1912:6: 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) )
                    {
                    match(input,64,FollowSets000.FOLLOW_64_in_ruleLineCode2835); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getBaseFreqKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1916:1: ( (lv_baseFreq_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1917:1: (lv_baseFreq_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1917:1: (lv_baseFreq_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1918:3: lv_baseFreq_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getBaseFreqEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2856);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1940:4: ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==36) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1940:6: 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineCode2869); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNormAmpsKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1944:1: ( (lv_normAmps_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1945:1: (lv_normAmps_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1945:1: (lv_normAmps_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1946:3: lv_normAmps_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNormAmpsEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2890);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1968:4: ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==37) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1968:6: 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) )
                    {
                    match(input,37,FollowSets000.FOLLOW_37_in_ruleLineCode2903); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getEmergAmpsKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1972:1: ( (lv_emergAmps_25_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1973:1: (lv_emergAmps_25_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1973:1: (lv_emergAmps_25_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1974:3: lv_emergAmps_25_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getEmergAmpsEDoubleParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2924);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1996:4: ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==65) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1996:6: 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) )
                    {
                    match(input,65,FollowSets000.FOLLOW_65_in_ruleLineCode2937); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getFaultRateKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2000:1: ( (lv_faultRate_27_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2001:1: (lv_faultRate_27_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2001:1: (lv_faultRate_27_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2002:3: lv_faultRate_27_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getFaultRateEDoubleParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2958);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2024:4: ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==66) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2024:6: 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) )
                    {
                    match(input,66,FollowSets000.FOLLOW_66_in_ruleLineCode2971); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getPctPermKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2028:1: ( (lv_pctPerm_29_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2029:1: (lv_pctPerm_29_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2029:1: (lv_pctPerm_29_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2030:3: lv_pctPerm_29_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getPctPermEIntParserRuleCall_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2992);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2052:4: ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==67) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2052:6: 'repair' ( (lv_repair_31_0= ruleEInt ) )
                    {
                    match(input,67,FollowSets000.FOLLOW_67_in_ruleLineCode3005); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRepairKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2056:1: ( (lv_repair_31_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2057:1: (lv_repair_31_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2057:1: (lv_repair_31_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2058:3: lv_repair_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRepairEIntParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode3026);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2080:4: ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==68) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2080:6: 'rg' ( (lv_rg_33_0= ruleEDouble ) )
                    {
                    match(input,68,FollowSets000.FOLLOW_68_in_ruleLineCode3039); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRgKeyword_18_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2084:1: ( (lv_rg_33_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2085:1: (lv_rg_33_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2085:1: (lv_rg_33_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2086:3: lv_rg_33_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRgEDoubleParserRuleCall_18_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode3060);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2108:4: ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==69) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2108:6: 'xg' ( (lv_xg_35_0= ruleEDouble ) )
                    {
                    match(input,69,FollowSets000.FOLLOW_69_in_ruleLineCode3073); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXgKeyword_19_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2112:1: ( (lv_xg_35_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2113:1: (lv_xg_35_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2113:1: (lv_xg_35_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2114:3: lv_xg_35_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXgEDoubleParserRuleCall_19_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode3094);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2136:4: ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==70) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2136:6: 'rho' ( (lv_rho_37_0= ruleEDouble ) )
                    {
                    match(input,70,FollowSets000.FOLLOW_70_in_ruleLineCode3107); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRhoKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2140:1: ( (lv_rho_37_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2141:1: (lv_rho_37_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2141:1: (lv_rho_37_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2142:3: lv_rho_37_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRhoEDoubleParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode3128);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2164:4: ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==71) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2164:6: 'neutral' ( (lv_neutral_39_0= ruleEInt ) )
                    {
                    match(input,71,FollowSets000.FOLLOW_71_in_ruleLineCode3141); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNeutralKeyword_21_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2168:1: ( (lv_neutral_39_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2169:1: (lv_neutral_39_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2169:1: (lv_neutral_39_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2170:3: lv_neutral_39_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNeutralEIntParserRuleCall_21_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode3162);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2192:4: ( 'rMatrix' ( ( ruleEString ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==72) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2192:6: 'rMatrix' ( ( ruleEString ) )
                    {
                    match(input,72,FollowSets000.FOLLOW_72_in_ruleLineCode3175); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRMatrixKeyword_22_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2196:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2197:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2197:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2198:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRMatrixDoubleMatrix2DCrossReference_22_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode3198);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2212:4: ( 'xMatrix' ( ( ruleEString ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==73) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2212:6: 'xMatrix' ( ( ruleEString ) )
                    {
                    match(input,73,FollowSets000.FOLLOW_73_in_ruleLineCode3211); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXMatrixKeyword_23_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2216:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2217:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2217:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2218:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXMatrixDoubleMatrix2DCrossReference_23_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode3234);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2232:4: ( 'cMatrix' ( ( ruleEString ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==74) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2232:6: 'cMatrix' ( ( ruleEString ) )
                    {
                    match(input,74,FollowSets000.FOLLOW_74_in_ruleLineCode3247); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getCMatrixKeyword_24_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2236:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2237:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2237:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2238:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getCMatrixDoubleMatrix2DCrossReference_24_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode3270);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleLineCode3282); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2264:1: entryRuleLoadShape returns [EObject current=null] : iv_ruleLoadShape= ruleLoadShape EOF ;
    public final EObject entryRuleLoadShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2265:2: (iv_ruleLoadShape= ruleLoadShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2266:2: iv_ruleLoadShape= ruleLoadShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLoadShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_entryRuleLoadShape3318);
            iv_ruleLoadShape=ruleLoadShape();
            _fsp--;

             current =iv_ruleLoadShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLoadShape3328); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2273:1: ruleLoadShape returns [EObject current=null] : ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2278:6: ( ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2279:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2279:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2279:2: () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2279:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2280:5: 
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

            match(input,75,FollowSets000.FOLLOW_75_in_ruleLoadShape3372); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLoadShapeKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3382); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2298:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==50) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2298:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,50,FollowSets000.FOLLOW_50_in_ruleLoadShape3393); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2302:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2303:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2303:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2304:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape3414);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2326:4: ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==76) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2326:6: 'interval' ( (lv_interval_6_0= ruleEInt ) )
                    {
                    match(input,76,FollowSets000.FOLLOW_76_in_ruleLoadShape3427); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getIntervalKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2330:1: ( (lv_interval_6_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2331:1: (lv_interval_6_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2331:1: (lv_interval_6_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2332:3: lv_interval_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getIntervalEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape3448);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2354:4: ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==77) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2354:6: 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,77,FollowSets000.FOLLOW_77_in_ruleLoadShape3461); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMultKeyword_5_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3471); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2362:1: ( (lv_mult_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2363:1: (lv_mult_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2363:1: (lv_mult_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2364:3: lv_mult_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3492);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2386:2: ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==52) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2386:4: ',' ( (lv_mult_11_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleLoadShape3503); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2390:1: ( (lv_mult_11_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2391:1: (lv_mult_11_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2391:1: (lv_mult_11_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2392:3: lv_mult_11_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3524);
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
                    	    break loop65;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleLoadShape3536); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2418:3: ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==78) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2418:5: 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,78,FollowSets000.FOLLOW_78_in_ruleLoadShape3549); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getHourKeyword_6_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3559); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2426:1: ( (lv_hour_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2427:1: (lv_hour_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2427:1: (lv_hour_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2428:3: lv_hour_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3580);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2450:2: ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==52) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2450:4: ',' ( (lv_hour_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleLoadShape3591); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2454:1: ( (lv_hour_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2455:1: (lv_hour_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2455:1: (lv_hour_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2456:3: lv_hour_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3612);
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
                    	    break loop67;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleLoadShape3624); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2482:3: ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==79) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2482:5: 'mean' ( (lv_mean_20_0= ruleEDouble ) )
                    {
                    match(input,79,FollowSets000.FOLLOW_79_in_ruleLoadShape3637); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMeanKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2486:1: ( (lv_mean_20_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2487:1: (lv_mean_20_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2487:1: (lv_mean_20_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2488:3: lv_mean_20_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMeanEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3658);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2510:4: ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==80) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2510:6: 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) )
                    {
                    match(input,80,FollowSets000.FOLLOW_80_in_ruleLoadShape3671); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getStdDevKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2514:1: ( (lv_stdDev_22_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2515:1: (lv_stdDev_22_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2515:1: (lv_stdDev_22_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2516:3: lv_stdDev_22_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getStdDevEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3692);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2538:4: ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==53) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2538:6: 'csvFile' ( (lv_csvFile_24_0= ruleEString ) )
                    {
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleLoadShape3705); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getCsvFileKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2542:1: ( (lv_csvFile_24_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2543:1: (lv_csvFile_24_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2543:1: (lv_csvFile_24_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2544:3: lv_csvFile_24_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getCsvFileEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3726);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2566:4: ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==54) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2566:6: 'sngFile' ( (lv_sngFile_26_0= ruleEString ) )
                    {
                    match(input,54,FollowSets000.FOLLOW_54_in_ruleLoadShape3739); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getSngFileKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2570:1: ( (lv_sngFile_26_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2571:1: (lv_sngFile_26_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2571:1: (lv_sngFile_26_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2572:3: lv_sngFile_26_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getSngFileEStringParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3760);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2594:4: ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==55) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2594:6: 'dblFile' ( (lv_dblFile_28_0= ruleEString ) )
                    {
                    match(input,55,FollowSets000.FOLLOW_55_in_ruleLoadShape3773); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getDblFileKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2598:1: ( (lv_dblFile_28_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2599:1: (lv_dblFile_28_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2599:1: (lv_dblFile_28_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2600:3: lv_dblFile_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getDblFileEStringParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3794);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2622:4: ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==81) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2622:6: 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,81,FollowSets000.FOLLOW_81_in_ruleLoadShape3807); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getQMultKeyword_12_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleLoadShape3817); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2630:1: ( (lv_qMult_31_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2631:1: (lv_qMult_31_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2631:1: (lv_qMult_31_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2632:3: lv_qMult_31_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3838);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2654:2: ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==52) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2654:4: ',' ( (lv_qMult_33_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleLoadShape3849); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2658:1: ( (lv_qMult_33_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2659:1: (lv_qMult_33_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2659:1: (lv_qMult_33_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2660:3: lv_qMult_33_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3870);
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
                    	    break loop74;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleLoadShape3882); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleLoadShape3894); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2698:1: entryRuleSpectrum returns [EObject current=null] : iv_ruleSpectrum= ruleSpectrum EOF ;
    public final EObject entryRuleSpectrum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpectrum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2699:2: (iv_ruleSpectrum= ruleSpectrum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2700:2: iv_ruleSpectrum= ruleSpectrum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSpectrumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_entryRuleSpectrum3930);
            iv_ruleSpectrum=ruleSpectrum();
            _fsp--;

             current =iv_ruleSpectrum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleSpectrum3940); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2707:1: ruleSpectrum returns [EObject current=null] : ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2712:6: ( ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2713:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2713:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2713:2: () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2713:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2714:5: 
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

            match(input,82,FollowSets000.FOLLOW_82_in_ruleSpectrum3984); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getSpectrumKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum3994); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2732:1: ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==83) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2732:3: 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) )
                    {
                    match(input,83,FollowSets000.FOLLOW_83_in_ruleSpectrum4005); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getNHarmKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2736:1: ( (lv_nHarm_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2737:1: (lv_nHarm_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2737:1: (lv_nHarm_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2738:3: lv_nHarm_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getNHarmEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleSpectrum4026);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2760:4: ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==84) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2760:6: 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,84,FollowSets000.FOLLOW_84_in_ruleSpectrum4039); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getHarmonicKeyword_4_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum4049); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2768:1: ( (lv_harmonic_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2769:1: (lv_harmonic_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2769:1: (lv_harmonic_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2770:3: lv_harmonic_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum4070);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2792:2: ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==52) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2792:4: ',' ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleSpectrum4081); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2796:1: ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2797:1: (lv_harmonic_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2797:1: (lv_harmonic_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2798:3: lv_harmonic_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum4102);
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
                    	    break loop77;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleSpectrum4114); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2824:3: ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==85) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2824:5: 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) )
                    {
                    match(input,85,FollowSets000.FOLLOW_85_in_ruleSpectrum4127); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getPctMagKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2828:1: ( (lv_pctMag_12_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2829:1: (lv_pctMag_12_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2829:1: (lv_pctMag_12_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2830:3: lv_pctMag_12_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getPctMagEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum4148);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2852:4: ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==86) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2852:6: 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,86,FollowSets000.FOLLOW_86_in_ruleSpectrum4161); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getAngleKeyword_6_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleSpectrum4171); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2860:1: ( (lv_angle_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2861:1: (lv_angle_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2861:1: (lv_angle_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2862:3: lv_angle_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum4192);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2884:2: ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==52) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2884:4: ',' ( (lv_angle_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleSpectrum4203); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2888:1: ( (lv_angle_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2889:1: (lv_angle_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2889:1: (lv_angle_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2890:3: lv_angle_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum4224);
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
                    	    break loop80;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleSpectrum4236); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2916:3: ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==53) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2916:5: 'csvFile' ( (lv_csvFile_20_0= ruleEString ) )
                    {
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleSpectrum4249); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getCsvFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2920:1: ( (lv_csvFile_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2921:1: (lv_csvFile_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2921:1: (lv_csvFile_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2922:3: lv_csvFile_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getCsvFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleSpectrum4270);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleSpectrum4282); 

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


    // $ANTLR start entryRuleEStructuralFeature
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2956:1: entryRuleEStructuralFeature returns [EObject current=null] : iv_ruleEStructuralFeature= ruleEStructuralFeature EOF ;
    public final EObject entryRuleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStructuralFeature = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2957:2: (iv_ruleEStructuralFeature= ruleEStructuralFeature EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2958:2: iv_ruleEStructuralFeature= ruleEStructuralFeature EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStructuralFeatureRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature4318);
            iv_ruleEStructuralFeature=ruleEStructuralFeature();
            _fsp--;

             current =iv_ruleEStructuralFeature; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStructuralFeature4328); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2965:1: ruleEStructuralFeature returns [EObject current=null] : (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) ;
    public final EObject ruleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject this_EAttribute_0 = null;

        EObject this_EReference_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2970:6: ( (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            int alt83=2;
            switch ( input.LA(1) ) {
            case 139:
                {
                switch ( input.LA(2) ) {
                case 140:
                    {
                    switch ( input.LA(3) ) {
                    case 141:
                        {
                        switch ( input.LA(4) ) {
                        case 142:
                            {
                            int LA83_4 = input.LA(5);

                            if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                                alt83=2;
                            }
                            else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                                alt83=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 143:
                        case 144:
                            {
                            alt83=1;
                            }
                            break;
                        case 147:
                        case 148:
                            {
                            alt83=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 3, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 142:
                        {
                        int LA83_4 = input.LA(4);

                        if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                            alt83=2;
                        }
                        else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                            alt83=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt83=1;
                        }
                        break;
                    case 147:
                    case 148:
                        {
                        alt83=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 2, input);

                        throw nvae;
                    }

                    }
                    break;
                case 141:
                    {
                    switch ( input.LA(3) ) {
                    case 142:
                        {
                        int LA83_4 = input.LA(4);

                        if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                            alt83=2;
                        }
                        else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                            alt83=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt83=1;
                        }
                        break;
                    case 147:
                    case 148:
                        {
                        alt83=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 142:
                    {
                    int LA83_4 = input.LA(3);

                    if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                        alt83=2;
                    }
                    else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                        alt83=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt83=1;
                    }
                    break;
                case 147:
                case 148:
                    {
                    alt83=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 1, input);

                    throw nvae;
                }

                }
                break;
            case 140:
                {
                switch ( input.LA(2) ) {
                case 141:
                    {
                    switch ( input.LA(3) ) {
                    case 142:
                        {
                        int LA83_4 = input.LA(4);

                        if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                            alt83=2;
                        }
                        else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                            alt83=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt83=1;
                        }
                        break;
                    case 147:
                    case 148:
                        {
                        alt83=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 142:
                    {
                    int LA83_4 = input.LA(3);

                    if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                        alt83=2;
                    }
                    else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                        alt83=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt83=1;
                    }
                    break;
                case 147:
                case 148:
                    {
                    alt83=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 2, input);

                    throw nvae;
                }

                }
                break;
            case 141:
                {
                switch ( input.LA(2) ) {
                case 142:
                    {
                    int LA83_4 = input.LA(3);

                    if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                        alt83=2;
                    }
                    else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                        alt83=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt83=1;
                    }
                    break;
                case 147:
                case 148:
                    {
                    alt83=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 3, input);

                    throw nvae;
                }

                }
                break;
            case 142:
                {
                int LA83_4 = input.LA(2);

                if ( ((LA83_4>=147 && LA83_4<=148)) ) {
                    alt83=2;
                }
                else if ( ((LA83_4>=143 && LA83_4<=144)) ) {
                    alt83=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 4, input);

                    throw nvae;
                }
                }
                break;
            case 143:
            case 144:
                {
                alt83=1;
                }
                break;
            case 147:
            case 148:
                {
                alt83=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("2971:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2972:5: this_EAttribute_0= ruleEAttribute
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEAttributeParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_ruleEStructuralFeature4375);
                    this_EAttribute_0=ruleEAttribute();
                    _fsp--;

                     
                            current = this_EAttribute_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2982:5: this_EReference_1= ruleEReference
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEReferenceParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEReference_in_ruleEStructuralFeature4402);
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


    // $ANTLR start entryRuleEString
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3000:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3001:2: (iv_ruleEString= ruleEString EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3002:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString4440);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString4451); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3009:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3014:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3015:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3015:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==RULE_STRING) ) {
                alt84=1;
            }
            else if ( (LA84_0==RULE_ID) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3015:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3015:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString4491); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3023:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString4517); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3038:1: entryRuleEDouble returns [String current=null] : iv_ruleEDouble= ruleEDouble EOF ;
    public final String entryRuleEDouble() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEDouble = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3039:2: (iv_ruleEDouble= ruleEDouble EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3040:2: iv_ruleEDouble= ruleEDouble EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDoubleRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_entryRuleEDouble4563);
            iv_ruleEDouble=ruleEDouble();
            _fsp--;

             current =iv_ruleEDouble.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDouble4574); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3047:1: ruleEDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEDouble() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3052:6: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3053:2: (kw= '-' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==87) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3054:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,87,FollowSets000.FOLLOW_87_in_ruleEDouble4613); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3059:3: (this_INT_1= RULE_INT )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==RULE_INT) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3059:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4631); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1(), null); 
                        

                    }
                    break;

            }

            kw=(Token)input.LT(1);
            match(input,21,FollowSets000.FOLLOW_21_in_ruleEDouble4651); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getEDoubleAccess().getFullStopKeyword_2(), null); 
                
            this_INT_3=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4666); 

            		current.merge(this_INT_3);
                
             
                createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3079:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( ((LA89_0>=88 && LA89_0<=89)) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3079:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3079:2: (kw= 'E' | kw= 'e' )
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==88) ) {
                        alt87=1;
                    }
                    else if ( (LA87_0==89) ) {
                        alt87=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("3079:2: (kw= 'E' | kw= 'e' )", 87, 0, input);

                        throw nvae;
                    }
                    switch (alt87) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3080:2: kw= 'E'
                            {
                            kw=(Token)input.LT(1);
                            match(input,88,FollowSets000.FOLLOW_88_in_ruleEDouble4686); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3087:2: kw= 'e'
                            {
                            kw=(Token)input.LT(1);
                            match(input,89,FollowSets000.FOLLOW_89_in_ruleEDouble4705); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3092:2: (kw= '-' )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==87) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3093:2: kw= '-'
                            {
                            kw=(Token)input.LT(1);
                            match(input,87,FollowSets000.FOLLOW_87_in_ruleEDouble4720); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1(), null); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4737); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3113:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3114:2: (iv_ruleEInt= ruleEInt EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3115:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt4785);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt4796); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3122:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3127:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3128:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3128:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3128:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3128:2: (kw= '-' )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==87) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3129:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,87,FollowSets000.FOLLOW_87_in_ruleEInt4835); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt4852); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3149:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3150:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3151:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean4898);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean4909); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3158:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3163:6: ( (kw= 'true' | kw= 'false' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3164:1: (kw= 'true' | kw= 'false' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3164:1: (kw= 'true' | kw= 'false' )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==90) ) {
                alt91=1;
            }
            else if ( (LA91_0==91) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3164:1: (kw= 'true' | kw= 'false' )", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3165:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEBoolean4947); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3172:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEBoolean4966); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3185:1: entryRuleEAnnotation returns [EObject current=null] : iv_ruleEAnnotation= ruleEAnnotation EOF ;
    public final EObject entryRuleEAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAnnotation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3186:2: (iv_ruleEAnnotation= ruleEAnnotation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3187:2: iv_ruleEAnnotation= ruleEAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation5006);
            iv_ruleEAnnotation=ruleEAnnotation();
            _fsp--;

             current =iv_ruleEAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAnnotation5016); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3194:1: ruleEAnnotation returns [EObject current=null] : ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3199:6: ( ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3200:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3200:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3200:2: () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3200:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3201:5: 
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

            match(input,92,FollowSets000.FOLLOW_92_in_ruleEAnnotation5060); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5070); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3219:1: ( 'source' ( (lv_source_4_0= ruleEString ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==93) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3219:3: 'source' ( (lv_source_4_0= ruleEString ) )
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEAnnotation5081); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getSourceKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3223:1: ( (lv_source_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3224:1: (lv_source_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3224:1: (lv_source_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3225:3: lv_source_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getSourceEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5102);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3247:4: ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==94) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3247:6: 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,94,FollowSets000.FOLLOW_94_in_ruleEAnnotation5115); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getReferencesKeyword_4_0(), null); 
                        
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEAnnotation5125); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftParenthesisKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3255:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3256:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3256:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3257:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5148);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3271:2: ( ',' ( ( ruleEString ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==52) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3271:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEAnnotation5159); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3275:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3276:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3276:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3277:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation5182);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);

                    match(input,96,FollowSets000.FOLLOW_96_in_ruleEAnnotation5194); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightParenthesisKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3295:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==97) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3295:5: 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEAnnotation5207); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationsKeyword_5_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5217); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3303:1: ( (lv_eAnnotations_13_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3304:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3304:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3305:3: lv_eAnnotations_13_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5238);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3327:2: ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==52) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3327:4: ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEAnnotation5249); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3331:1: ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3332:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3332:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3333:3: lv_eAnnotations_15_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5270);
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
                    	    break loop95;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEAnnotation5282); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3359:3: ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==98) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3359:5: 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}'
                    {
                    match(input,98,FollowSets000.FOLLOW_98_in_ruleEAnnotation5295); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getDetailsKeyword_6_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5305); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3367:1: ( (lv_details_19_0= ruleEStringToStringMapEntry ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3368:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3368:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3369:3: lv_details_19_0= ruleEStringToStringMapEntry
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5326);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3391:2: ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )*
                    loop97:
                    do {
                        int alt97=2;
                        int LA97_0 = input.LA(1);

                        if ( (LA97_0==52) ) {
                            alt97=1;
                        }


                        switch (alt97) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3391:4: ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEAnnotation5337); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3395:1: ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3396:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3396:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3397:3: lv_details_21_0= ruleEStringToStringMapEntry
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5358);
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
                    	    break loop97;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEAnnotation5370); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3423:3: ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==99) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3423:5: 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}'
                    {
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEAnnotation5383); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getContentsKeyword_7_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAnnotation5393); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3431:1: ( (lv_contents_25_0= ruleEObject ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3432:1: (lv_contents_25_0= ruleEObject )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3432:1: (lv_contents_25_0= ruleEObject )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3433:3: lv_contents_25_0= ruleEObject
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5414);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3455:2: ( ',' ( (lv_contents_27_0= ruleEObject ) ) )*
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==52) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3455:4: ',' ( (lv_contents_27_0= ruleEObject ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEAnnotation5425); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3459:1: ( (lv_contents_27_0= ruleEObject ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3460:1: (lv_contents_27_0= ruleEObject )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3460:1: (lv_contents_27_0= ruleEObject )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3461:3: lv_contents_27_0= ruleEObject
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5446);
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
                    	    break loop99;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEAnnotation5458); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEAnnotation5470); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3499:1: entryRuleETypeParameter returns [EObject current=null] : iv_ruleETypeParameter= ruleETypeParameter EOF ;
    public final EObject entryRuleETypeParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleETypeParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3500:2: (iv_ruleETypeParameter= ruleETypeParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3501:2: iv_ruleETypeParameter= ruleETypeParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getETypeParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5506);
            iv_ruleETypeParameter=ruleETypeParameter();
            _fsp--;

             current =iv_ruleETypeParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleETypeParameter5516); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3508:1: ruleETypeParameter returns [EObject current=null] : ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
    public final EObject ruleETypeParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_eAnnotations_6_0 = null;

        EObject lv_eAnnotations_8_0 = null;

        EObject lv_eBounds_12_0 = null;

        EObject lv_eBounds_14_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3513:6: ( ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3514:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3514:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3514:2: () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3514:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3515:5: 
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

            match(input,100,FollowSets000.FOLLOW_100_in_ruleETypeParameter5560); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getETypeParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3529:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3530:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3530:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3531:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleETypeParameter5581);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter5591); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3557:1: ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==97) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3557:3: 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleETypeParameter5602); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEAnnotationsKeyword_4_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter5612); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3565:1: ( (lv_eAnnotations_6_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3566:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3566:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3567:3: lv_eAnnotations_6_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter5633);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3589:2: ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )*
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==52) ) {
                            alt101=1;
                        }


                        switch (alt101) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3589:4: ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleETypeParameter5644); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3593:1: ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3594:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3594:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3595:3: lv_eAnnotations_8_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter5665);
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
                    	    break loop101;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleETypeParameter5677); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3621:3: ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==101) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3621:5: 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,101,FollowSets000.FOLLOW_101_in_ruleETypeParameter5690); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEBoundsKeyword_5_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleETypeParameter5700); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3629:1: ( (lv_eBounds_12_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3630:1: (lv_eBounds_12_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3630:1: (lv_eBounds_12_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3631:3: lv_eBounds_12_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter5721);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3653:2: ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )*
                    loop103:
                    do {
                        int alt103=2;
                        int LA103_0 = input.LA(1);

                        if ( (LA103_0==52) ) {
                            alt103=1;
                        }


                        switch (alt103) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3653:4: ',' ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleETypeParameter5732); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3657:1: ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3658:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3658:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3659:3: lv_eBounds_14_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter5753);
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
                    	    break loop103;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleETypeParameter5765); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleETypeParameter5777); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3697:1: entryRuleEOperation returns [EObject current=null] : iv_ruleEOperation= ruleEOperation EOF ;
    public final EObject entryRuleEOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEOperation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3698:2: (iv_ruleEOperation= ruleEOperation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3699:2: iv_ruleEOperation= ruleEOperation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEOperationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_entryRuleEOperation5813);
            iv_ruleEOperation=ruleEOperation();
            _fsp--;

             current =iv_ruleEOperation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEOperation5823); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3706:1: ruleEOperation returns [EObject current=null] : ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3711:6: ( ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3712:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3712:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3712:2: () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3712:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3713:5: 
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

            match(input,102,FollowSets000.FOLLOW_102_in_ruleEOperation5867); 

                    createLeafNode(grammarAccess.getEOperationAccess().getEOperationKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3727:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3728:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3728:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3729:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation5888);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation5898); 

                    createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3755:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==103) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3755:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEOperation5909); 

                            createLeafNode(grammarAccess.getEOperationAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3759:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3760:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3760:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3761:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation5930);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3783:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==104) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3783:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEOperation5943); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3787:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3788:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3788:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3789:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation5964);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3811:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==105) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3811:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEOperation5977); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3815:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3816:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3816:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3817:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation5998);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3839:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==106) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3839:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEOperation6011); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3843:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3844:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3844:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3845:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation6032);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3867:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==107) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3867:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,107,FollowSets000.FOLLOW_107_in_ruleEOperation6045); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3871:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3872:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3872:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3873:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6068);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3887:4: ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==108) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3887:6: 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,108,FollowSets000.FOLLOW_108_in_ruleEOperation6081); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEExceptionsKeyword_9_0(), null); 
                        
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEOperation6091); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftParenthesisKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3895:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3896:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3896:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3897:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6114);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3911:2: ( ',' ( ( ruleEString ) ) )*
                    loop110:
                    do {
                        int alt110=2;
                        int LA110_0 = input.LA(1);

                        if ( (LA110_0==52) ) {
                            alt110=1;
                        }


                        switch (alt110) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3911:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEOperation6125); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3915:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3916:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3916:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3917:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation6148);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop110;
                        }
                    } while (true);

                    match(input,96,FollowSets000.FOLLOW_96_in_ruleEOperation6160); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightParenthesisKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3935:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==97) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3935:5: 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEOperation6173); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEAnnotationsKeyword_10_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6183); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3943:1: ( (lv_eAnnotations_22_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3944:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3944:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3945:3: lv_eAnnotations_22_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation6204);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3967:2: ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==52) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3967:4: ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEOperation6215); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3971:1: ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3972:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3972:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3973:3: lv_eAnnotations_24_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation6236);
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
                    	    break loop112;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEOperation6248); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3999:3: ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==109) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3999:5: 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    {
                    match(input,109,FollowSets000.FOLLOW_109_in_ruleEOperation6261); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericTypeKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4003:1: ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4004:1: (lv_eGenericType_27_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4004:1: (lv_eGenericType_27_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4005:3: lv_eGenericType_27_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericTypeEGenericTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6282);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4027:4: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==110) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4027:6: 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEOperation6295); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeParametersKeyword_12_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6305); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4035:1: ( (lv_eTypeParameters_30_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4036:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4036:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4037:3: lv_eTypeParameters_30_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6326);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4059:2: ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )*
                    loop115:
                    do {
                        int alt115=2;
                        int LA115_0 = input.LA(1);

                        if ( (LA115_0==52) ) {
                            alt115=1;
                        }


                        switch (alt115) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4059:4: ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEOperation6337); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4063:1: ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4064:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4064:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4065:3: lv_eTypeParameters_32_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6358);
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
                    	    break loop115;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEOperation6370); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4091:3: ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==111) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4091:5: 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}'
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEOperation6383); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEParametersKeyword_13_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6393); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4099:1: ( (lv_eParameters_36_0= ruleEParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4100:1: (lv_eParameters_36_0= ruleEParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4100:1: (lv_eParameters_36_0= ruleEParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4101:3: lv_eParameters_36_0= ruleEParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6414);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4123:2: ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )*
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==52) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4123:4: ',' ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEOperation6425); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4127:1: ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4128:1: (lv_eParameters_38_0= ruleEParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4128:1: (lv_eParameters_38_0= ruleEParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4129:3: lv_eParameters_38_0= ruleEParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6446);
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
                    	    break loop117;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEOperation6458); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4155:3: ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==112) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4155:5: 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEOperation6471); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericExceptionsKeyword_14_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEOperation6481); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_14_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4163:1: ( (lv_eGenericExceptions_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4164:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4164:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4165:3: lv_eGenericExceptions_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6502);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4187:2: ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==52) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4187:4: ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEOperation6513); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_14_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4191:1: ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4192:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4192:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4193:3: lv_eGenericExceptions_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6534);
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
                    	    break loop119;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEOperation6546); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_14_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEOperation6558); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4231:1: entryRuleEGenericType returns [EObject current=null] : iv_ruleEGenericType= ruleEGenericType EOF ;
    public final EObject entryRuleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEGenericType = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4232:2: (iv_ruleEGenericType= ruleEGenericType EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4233:2: iv_ruleEGenericType= ruleEGenericType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEGenericTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_entryRuleEGenericType6594);
            iv_ruleEGenericType=ruleEGenericType();
            _fsp--;

             current =iv_ruleEGenericType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEGenericType6604); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4240:1: ruleEGenericType returns [EObject current=null] : ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject lv_eUpperBound_8_0 = null;

        EObject lv_eTypeArguments_11_0 = null;

        EObject lv_eTypeArguments_13_0 = null;

        EObject lv_eLowerBound_16_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4245:6: ( ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4246:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4246:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4246:2: () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4246:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4247:5: 
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

            match(input,113,FollowSets000.FOLLOW_113_in_ruleEGenericType6648); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getEGenericTypeKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleEGenericType6658); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4265:1: ( 'eTypeParameter' ( ( ruleEString ) ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==114) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4265:3: 'eTypeParameter' ( ( ruleEString ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEGenericType6669); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeParameterKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4269:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4270:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4270:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4271:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeParameterETypeParameterCrossReference_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType6692);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4285:4: ( 'eClassifier' ( ( ruleEString ) ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==115) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4285:6: 'eClassifier' ( ( ruleEString ) )
                    {
                    match(input,115,FollowSets000.FOLLOW_115_in_ruleEGenericType6705); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEClassifierKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4289:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4290:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4290:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4291:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEClassifierEClassifierCrossReference_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType6728);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4305:4: ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==116) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4305:6: 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEGenericType6741); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4309:1: ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4310:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4310:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4311:3: lv_eUpperBound_8_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundEGenericTypeParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6762);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4333:4: ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==117) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4333:6: 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEGenericType6775); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsKeyword_6_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEGenericType6785); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4341:1: ( (lv_eTypeArguments_11_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4342:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4342:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4343:3: lv_eTypeArguments_11_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6806);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4365:2: ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )*
                    loop124:
                    do {
                        int alt124=2;
                        int LA124_0 = input.LA(1);

                        if ( (LA124_0==52) ) {
                            alt124=1;
                        }


                        switch (alt124) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4365:4: ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEGenericType6817); 

                    	            createLeafNode(grammarAccess.getEGenericTypeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4369:1: ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4370:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4370:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4371:3: lv_eTypeArguments_13_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6838);
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
                    	    break loop124;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEGenericType6850); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4397:3: ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==118) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4397:5: 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    {
                    match(input,118,FollowSets000.FOLLOW_118_in_ruleEGenericType6863); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getELowerBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4401:1: ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4402:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4402:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4403:3: lv_eLowerBound_16_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getELowerBoundEGenericTypeParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6884);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEGenericType6896); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4437:1: entryRuleEStringToStringMapEntry returns [EObject current=null] : iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF ;
    public final EObject entryRuleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStringToStringMapEntry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4438:2: (iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4439:2: iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringToStringMapEntryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry6932);
            iv_ruleEStringToStringMapEntry=ruleEStringToStringMapEntry();
            _fsp--;

             current =iv_ruleEStringToStringMapEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStringToStringMapEntry6942); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4446:1: ruleEStringToStringMapEntry returns [EObject current=null] : ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_key_4_0 = null;

        AntlrDatatypeRuleToken lv_value_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4451:6: ( ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4452:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4452:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4452:2: () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4452:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4453:5: 
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

            match(input,119,FollowSets000.FOLLOW_119_in_ruleEStringToStringMapEntry6986); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getEStringToStringMapEntryKeyword_1(), null); 
                
            match(input,28,FollowSets000.FOLLOW_28_in_ruleEStringToStringMapEntry6996); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4471:1: ( 'key' ( (lv_key_4_0= ruleEString ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==120) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4471:3: 'key' ( (lv_key_4_0= ruleEString ) )
                    {
                    match(input,120,FollowSets000.FOLLOW_120_in_ruleEStringToStringMapEntry7007); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4475:1: ( (lv_key_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4476:1: (lv_key_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4476:1: (lv_key_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4477:3: lv_key_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7028);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4499:4: ( 'value' ( (lv_value_6_0= ruleEString ) ) )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==121) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4499:6: 'value' ( (lv_value_6_0= ruleEString ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEStringToStringMapEntry7041); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4503:1: ( (lv_value_6_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4504:1: (lv_value_6_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4504:1: (lv_value_6_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4505:3: lv_value_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getValueEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7062);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEStringToStringMapEntry7074); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4539:1: entryRuleEClass returns [EObject current=null] : iv_ruleEClass= ruleEClass EOF ;
    public final EObject entryRuleEClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEClass = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4540:2: (iv_ruleEClass= ruleEClass EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4541:2: iv_ruleEClass= ruleEClass EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEClassRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEClass_in_entryRuleEClass7110);
            iv_ruleEClass=ruleEClass();
            _fsp--;

             current =iv_ruleEClass; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEClass7120); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4548:1: ruleEClass returns [EObject current=null] : ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4553:6: ( ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4554:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4554:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4554:2: () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4554:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4555:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4565:2: ( (lv_abstract_1_0= 'abstract' ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==122) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4566:1: (lv_abstract_1_0= 'abstract' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4566:1: (lv_abstract_1_0= 'abstract' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4567:3: lv_abstract_1_0= 'abstract'
                    {
                    lv_abstract_1_0=(Token)input.LT(1);
                    match(input,122,FollowSets000.FOLLOW_122_in_ruleEClass7172); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4586:3: ( (lv_interface_2_0= 'interface' ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==123) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4587:1: (lv_interface_2_0= 'interface' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4587:1: (lv_interface_2_0= 'interface' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4588:3: lv_interface_2_0= 'interface'
                    {
                    lv_interface_2_0=(Token)input.LT(1);
                    match(input,123,FollowSets000.FOLLOW_123_in_ruleEClass7204); 

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

            match(input,124,FollowSets000.FOLLOW_124_in_ruleEClass7228); 

                    createLeafNode(grammarAccess.getEClassAccess().getEClassKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4611:1: ( (lv_name_4_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4612:1: (lv_name_4_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4612:1: (lv_name_4_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4613:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7249);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7259); 

                    createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4639:1: ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==125) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4639:3: 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) )
                    {
                    match(input,125,FollowSets000.FOLLOW_125_in_ruleEClass7270); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceClassNameKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4643:1: ( (lv_instanceClassName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4644:1: (lv_instanceClassName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4644:1: (lv_instanceClassName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4645:3: lv_instanceClassName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceClassNameEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7291);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4667:4: ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==126) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4667:6: 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) )
                    {
                    match(input,126,FollowSets000.FOLLOW_126_in_ruleEClass7304); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceTypeNameKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4671:1: ( (lv_instanceTypeName_9_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4672:1: (lv_instanceTypeName_9_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4672:1: (lv_instanceTypeName_9_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4673:3: lv_instanceTypeName_9_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceTypeNameEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7325);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4695:4: ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==127) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4695:6: 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,127,FollowSets000.FOLLOW_127_in_ruleEClass7338); 

                            createLeafNode(grammarAccess.getEClassAccess().getESuperTypesKeyword_8_0(), null); 
                        
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEClass7348); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftParenthesisKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4703:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4704:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4704:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4705:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7371);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4719:2: ( ',' ( ( ruleEString ) ) )*
                    loop133:
                    do {
                        int alt133=2;
                        int LA133_0 = input.LA(1);

                        if ( (LA133_0==52) ) {
                            alt133=1;
                        }


                        switch (alt133) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4719:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7382); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4723:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4724:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4724:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4725:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7405);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop133;
                        }
                    } while (true);

                    match(input,96,FollowSets000.FOLLOW_96_in_ruleEClass7417); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightParenthesisKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4743:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==97) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4743:5: 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEClass7430); 

                            createLeafNode(grammarAccess.getEClassAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7440); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4751:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4752:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4752:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4753:3: lv_eAnnotations_18_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7461);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4775:2: ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )*
                    loop135:
                    do {
                        int alt135=2;
                        int LA135_0 = input.LA(1);

                        if ( (LA135_0==52) ) {
                            alt135=1;
                        }


                        switch (alt135) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4775:4: ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7472); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4779:1: ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4780:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4780:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4781:3: lv_eAnnotations_20_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7493);
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
                    	    break loop135;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7505); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4807:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==110) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4807:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEClass7518); 

                            createLeafNode(grammarAccess.getEClassAccess().getETypeParametersKeyword_10_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7528); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4815:1: ( (lv_eTypeParameters_24_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4816:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4816:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4817:3: lv_eTypeParameters_24_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7549);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4839:2: ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==52) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4839:4: ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7560); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4843:1: ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4844:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4844:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4845:3: lv_eTypeParameters_26_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7581);
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
                    	    break loop137;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7593); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4871:3: ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==128) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4871:5: 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}'
                    {
                    match(input,128,FollowSets000.FOLLOW_128_in_ruleEClass7606); 

                            createLeafNode(grammarAccess.getEClassAccess().getEOperationsKeyword_11_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7616); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_11_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4879:1: ( (lv_eOperations_30_0= ruleEOperation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4880:1: (lv_eOperations_30_0= ruleEOperation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4880:1: (lv_eOperations_30_0= ruleEOperation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4881:3: lv_eOperations_30_0= ruleEOperation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass7637);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4903:2: ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )*
                    loop139:
                    do {
                        int alt139=2;
                        int LA139_0 = input.LA(1);

                        if ( (LA139_0==52) ) {
                            alt139=1;
                        }


                        switch (alt139) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4903:4: ',' ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7648); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_11_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4907:1: ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4908:1: (lv_eOperations_32_0= ruleEOperation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4908:1: (lv_eOperations_32_0= ruleEOperation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4909:3: lv_eOperations_32_0= ruleEOperation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass7669);
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
                    	    break loop139;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7681); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_11_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4935:3: ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==129) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4935:5: 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}'
                    {
                    match(input,129,FollowSets000.FOLLOW_129_in_ruleEClass7694); 

                            createLeafNode(grammarAccess.getEClassAccess().getEStructuralFeaturesKeyword_12_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7704); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4943:1: ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4944:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4944:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4945:3: lv_eStructuralFeatures_36_0= ruleEStructuralFeature
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass7725);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4967:2: ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )*
                    loop141:
                    do {
                        int alt141=2;
                        int LA141_0 = input.LA(1);

                        if ( (LA141_0==52) ) {
                            alt141=1;
                        }


                        switch (alt141) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4967:4: ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7736); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4971:1: ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4972:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4972:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4973:3: lv_eStructuralFeatures_38_0= ruleEStructuralFeature
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass7757);
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
                    	    break loop141;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7769); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4999:3: ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==130) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4999:5: 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,130,FollowSets000.FOLLOW_130_in_ruleEClass7782); 

                            createLeafNode(grammarAccess.getEClassAccess().getEGenericSuperTypesKeyword_13_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEClass7792); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5007:1: ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5008:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5008:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5009:3: lv_eGenericSuperTypes_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass7813);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5031:2: ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )*
                    loop143:
                    do {
                        int alt143=2;
                        int LA143_0 = input.LA(1);

                        if ( (LA143_0==52) ) {
                            alt143=1;
                        }


                        switch (alt143) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5031:4: ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEClass7824); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5035:1: ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5036:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5036:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5037:3: lv_eGenericSuperTypes_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass7845);
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
                    	    break loop143;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7857); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEClass7869); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5075:1: entryRuleEObject returns [EObject current=null] : iv_ruleEObject= ruleEObject EOF ;
    public final EObject entryRuleEObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEObject = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5076:2: (iv_ruleEObject= ruleEObject EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5077:2: iv_ruleEObject= ruleEObject EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEObjectRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEObject_in_entryRuleEObject7905);
            iv_ruleEObject=ruleEObject();
            _fsp--;

             current =iv_ruleEObject; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEObject7915); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5084:1: ruleEObject returns [EObject current=null] : ( () 'EObject' ) ;
    public final EObject ruleEObject() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5089:6: ( ( () 'EObject' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5090:1: ( () 'EObject' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5090:1: ( () 'EObject' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5090:2: () 'EObject'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5090:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5091:5: 
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

            match(input,131,FollowSets000.FOLLOW_131_in_ruleEObject7959); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5113:1: entryRuleEParameter returns [EObject current=null] : iv_ruleEParameter= ruleEParameter EOF ;
    public final EObject entryRuleEParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5114:2: (iv_ruleEParameter= ruleEParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5115:2: iv_ruleEParameter= ruleEParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_entryRuleEParameter7995);
            iv_ruleEParameter=ruleEParameter();
            _fsp--;

             current =iv_ruleEParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEParameter8005); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5122:1: ruleEParameter returns [EObject current=null] : ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5127:6: ( ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:2: () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5128:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5129:5: 
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

            match(input,132,FollowSets000.FOLLOW_132_in_ruleEParameter8049); 

                    createLeafNode(grammarAccess.getEParameterAccess().getEParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5143:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5144:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5144:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5145:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter8070);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEParameter8080); 

                    createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5171:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==103) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5171:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEParameter8091); 

                            createLeafNode(grammarAccess.getEParameterAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5175:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5176:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5176:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5177:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter8112);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5199:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==104) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5199:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEParameter8125); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5203:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5204:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5204:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5205:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter8146);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5227:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==105) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5227:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEParameter8159); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5231:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5232:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5232:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5233:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter8180);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5255:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==106) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5255:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEParameter8193); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5259:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5260:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5260:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5261:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter8214);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5283:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==107) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5283:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,107,FollowSets000.FOLLOW_107_in_ruleEParameter8227); 

                            createLeafNode(grammarAccess.getEParameterAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5287:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5288:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5288:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5289:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter8250);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5303:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==97) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5303:6: 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEParameter8263); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEParameter8273); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5311:1: ( (lv_eAnnotations_16_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5312:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5312:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5313:3: lv_eAnnotations_16_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8294);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5335:2: ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==52) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5335:4: ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEParameter8305); 

                    	            createLeafNode(grammarAccess.getEParameterAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5339:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5340:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5340:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5341:3: lv_eAnnotations_18_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8326);
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
                    	    break loop150;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEParameter8338); 

                            createLeafNode(grammarAccess.getEParameterAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5367:3: ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==109) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5367:5: 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    {
                    match(input,109,FollowSets000.FOLLOW_109_in_ruleEParameter8351); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEGenericTypeKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5371:1: ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5372:1: (lv_eGenericType_21_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5372:1: (lv_eGenericType_21_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5373:3: lv_eGenericType_21_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEGenericTypeEGenericTypeParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEParameter8372);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEParameter8384); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5407:1: entryRuleEDataType_Impl returns [EObject current=null] : iv_ruleEDataType_Impl= ruleEDataType_Impl EOF ;
    public final EObject entryRuleEDataType_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEDataType_Impl = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5408:2: (iv_ruleEDataType_Impl= ruleEDataType_Impl EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5409:2: iv_ruleEDataType_Impl= ruleEDataType_Impl EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDataType_ImplRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8420);
            iv_ruleEDataType_Impl=ruleEDataType_Impl();
            _fsp--;

             current =iv_ruleEDataType_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDataType_Impl8430); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5416:1: ruleEDataType_Impl returns [EObject current=null] : ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5421:6: ( ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5422:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5422:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5422:2: () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5422:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5423:5: 
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

            match(input,133,FollowSets000.FOLLOW_133_in_ruleEDataType_Impl8474); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getEDataTypeKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5437:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5438:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5438:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5439:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8495);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl8505); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5465:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==125) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5465:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,125,FollowSets000.FOLLOW_125_in_ruleEDataType_Impl8516); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5469:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5470:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5470:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5471:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8537);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5493:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==126) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5493:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,126,FollowSets000.FOLLOW_126_in_ruleEDataType_Impl8550); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5497:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5498:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5498:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5499:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8571);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5521:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==134) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5521:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,134,FollowSets000.FOLLOW_134_in_ruleEDataType_Impl8584); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5525:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5526:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5526:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5527:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEDataType_Impl8605);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5549:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==97) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5549:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEDataType_Impl8618); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl8628); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5557:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5558:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5558:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5559:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8649);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5581:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==52) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5581:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEDataType_Impl8660); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5585:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5586:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5586:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5587:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8681);
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
                    	    break loop156;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEDataType_Impl8693); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5613:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==110) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5613:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEDataType_Impl8706); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEDataType_Impl8716); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5621:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5622:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5622:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5623:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8737);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5645:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop158:
                    do {
                        int alt158=2;
                        int LA158_0 = input.LA(1);

                        if ( (LA158_0==52) ) {
                            alt158=1;
                        }


                        switch (alt158) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5645:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEDataType_Impl8748); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5649:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5650:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5650:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5651:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8769);
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
                    	    break loop158;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEDataType_Impl8781); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEDataType_Impl8793); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5689:1: entryRuleEEnum returns [EObject current=null] : iv_ruleEEnum= ruleEEnum EOF ;
    public final EObject entryRuleEEnum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5690:2: (iv_ruleEEnum= ruleEEnum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5691:2: iv_ruleEEnum= ruleEEnum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnum_in_entryRuleEEnum8829);
            iv_ruleEEnum=ruleEEnum();
            _fsp--;

             current =iv_ruleEEnum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnum8839); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5698:1: ruleEEnum returns [EObject current=null] : ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5703:6: ( ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5704:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5704:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5704:2: () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5704:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5705:5: 
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

            match(input,135,FollowSets000.FOLLOW_135_in_ruleEEnum8883); 

                    createLeafNode(grammarAccess.getEEnumAccess().getEEnumKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5719:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5720:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5720:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5721:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8904);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum8914); 

                    createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5747:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==125) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5747:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,125,FollowSets000.FOLLOW_125_in_ruleEEnum8925); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5751:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5752:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5752:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5753:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8946);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5775:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==126) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5775:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,126,FollowSets000.FOLLOW_126_in_ruleEEnum8959); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5779:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5780:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5780:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5781:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8980);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5803:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==134) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5803:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,134,FollowSets000.FOLLOW_134_in_ruleEEnum8993); 

                            createLeafNode(grammarAccess.getEEnumAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5807:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5808:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5808:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5809:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEEnum9014);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5831:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==97) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5831:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEEnum9027); 

                            createLeafNode(grammarAccess.getEEnumAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9037); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5839:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5840:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5840:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5841:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum9058);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5863:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop163:
                    do {
                        int alt163=2;
                        int LA163_0 = input.LA(1);

                        if ( (LA163_0==52) ) {
                            alt163=1;
                        }


                        switch (alt163) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5863:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEEnum9069); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5867:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5868:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5868:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5869:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum9090);
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
                    	    break loop163;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnum9102); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5895:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==110) ) {
                alt166=1;
            }
            switch (alt166) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5895:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEEnum9115); 

                            createLeafNode(grammarAccess.getEEnumAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9125); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5903:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5904:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5904:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5905:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum9146);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5927:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop165:
                    do {
                        int alt165=2;
                        int LA165_0 = input.LA(1);

                        if ( (LA165_0==52) ) {
                            alt165=1;
                        }


                        switch (alt165) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5927:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEEnum9157); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5931:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5932:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5932:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5933:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum9178);
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
                    	    break loop165;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnum9190); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5959:3: ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )?
            int alt168=2;
            int LA168_0 = input.LA(1);

            if ( (LA168_0==136) ) {
                alt168=1;
            }
            switch (alt168) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5959:5: 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}'
                    {
                    match(input,136,FollowSets000.FOLLOW_136_in_ruleEEnum9203); 

                            createLeafNode(grammarAccess.getEEnumAccess().getELiteralsKeyword_9_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnum9213); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5967:1: ( (lv_eLiterals_24_0= ruleEEnumLiteral ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5968:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5968:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5969:3: lv_eLiterals_24_0= ruleEEnumLiteral
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9234);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5991:2: ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )*
                    loop167:
                    do {
                        int alt167=2;
                        int LA167_0 = input.LA(1);

                        if ( (LA167_0==52) ) {
                            alt167=1;
                        }


                        switch (alt167) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5991:4: ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEEnum9245); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5995:1: ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5996:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5996:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5997:3: lv_eLiterals_26_0= ruleEEnumLiteral
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9266);
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
                    	    break loop167;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnum9278); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnum9290); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6035:1: entryRuleEEnumLiteral returns [EObject current=null] : iv_ruleEEnumLiteral= ruleEEnumLiteral EOF ;
    public final EObject entryRuleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnumLiteral = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6036:2: (iv_ruleEEnumLiteral= ruleEEnumLiteral EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6037:2: iv_ruleEEnumLiteral= ruleEEnumLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumLiteralRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9326);
            iv_ruleEEnumLiteral=ruleEEnumLiteral();
            _fsp--;

             current =iv_ruleEEnumLiteral; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnumLiteral9336); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6044:1: ruleEEnumLiteral returns [EObject current=null] : ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) ;
    public final EObject ruleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        AntlrDatatypeRuleToken lv_literal_7_0 = null;

        EObject lv_eAnnotations_10_0 = null;

        EObject lv_eAnnotations_12_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6049:6: ( ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6050:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6050:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6050:2: () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6050:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6051:5: 
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

            match(input,137,FollowSets000.FOLLOW_137_in_ruleEEnumLiteral9380); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getEEnumLiteralKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6065:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6066:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6066:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6067:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9401);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnumLiteral9411); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6093:1: ( 'value' ( (lv_value_5_0= ruleEInt ) ) )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==121) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6093:3: 'value' ( (lv_value_5_0= ruleEInt ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEEnumLiteral9422); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6097:1: ( (lv_value_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6098:1: (lv_value_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6098:1: (lv_value_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6099:3: lv_value_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getValueEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEEnumLiteral9443);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6121:4: ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )?
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==138) ) {
                alt170=1;
            }
            switch (alt170) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6121:6: 'literal' ( (lv_literal_7_0= ruleEString ) )
                    {
                    match(input,138,FollowSets000.FOLLOW_138_in_ruleEEnumLiteral9456); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLiteralKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6125:1: ( (lv_literal_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6126:1: (lv_literal_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6126:1: (lv_literal_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6127:3: lv_literal_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getLiteralEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9477);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6149:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )?
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==97) ) {
                alt172=1;
            }
            switch (alt172) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6149:6: 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEEnumLiteral9490); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsKeyword_6_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEEnumLiteral9500); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6157:1: ( (lv_eAnnotations_10_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6158:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6158:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6159:3: lv_eAnnotations_10_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9521);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6181:2: ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )*
                    loop171:
                    do {
                        int alt171=2;
                        int LA171_0 = input.LA(1);

                        if ( (LA171_0==52) ) {
                            alt171=1;
                        }


                        switch (alt171) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6181:4: ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEEnumLiteral9532); 

                    	            createLeafNode(grammarAccess.getEEnumLiteralAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6185:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6186:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6186:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6187:3: lv_eAnnotations_12_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9553);
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
                    	    break loop171;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnumLiteral9565); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEEnumLiteral9577); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6225:1: entryRuleEAttribute returns [EObject current=null] : iv_ruleEAttribute= ruleEAttribute EOF ;
    public final EObject entryRuleEAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAttribute = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6226:2: (iv_ruleEAttribute= ruleEAttribute EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6227:2: iv_ruleEAttribute= ruleEAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAttributeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_entryRuleEAttribute9613);
            iv_ruleEAttribute=ruleEAttribute();
            _fsp--;

             current =iv_ruleEAttribute; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAttribute9623); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6234:1: ruleEAttribute returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6239:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6240:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6240:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6240:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6240:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6241:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6251:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==139) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6252:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6252:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6253:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,139,FollowSets000.FOLLOW_139_in_ruleEAttribute9675); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6272:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==140) ) {
                alt174=1;
            }
            switch (alt174) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6273:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6273:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6274:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,140,FollowSets000.FOLLOW_140_in_ruleEAttribute9707); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6293:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==141) ) {
                alt175=1;
            }
            switch (alt175) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6294:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6294:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6295:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEAttribute9739); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6314:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt176=2;
            int LA176_0 = input.LA(1);

            if ( (LA176_0==142) ) {
                alt176=1;
            }
            switch (alt176) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6315:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6315:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6316:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,142,FollowSets000.FOLLOW_142_in_ruleEAttribute9771); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6335:3: ( (lv_iD_5_0= 'iD' ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==143) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6336:1: (lv_iD_5_0= 'iD' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6336:1: (lv_iD_5_0= 'iD' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6337:3: lv_iD_5_0= 'iD'
                    {
                    lv_iD_5_0=(Token)input.LT(1);
                    match(input,143,FollowSets000.FOLLOW_143_in_ruleEAttribute9803); 

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

            match(input,144,FollowSets000.FOLLOW_144_in_ruleEAttribute9827); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getEAttributeKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6360:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6361:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6361:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6362:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute9848);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEAttribute9858); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6388:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==103) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6388:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEAttribute9869); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6392:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6393:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6393:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6394:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute9890);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6416:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==104) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6416:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEAttribute9903); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6420:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6421:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6421:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6422:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute9924);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6444:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==105) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6444:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEAttribute9937); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6448:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6449:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6449:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6450:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute9958);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6472:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==106) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6472:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEAttribute9971); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6476:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6477:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6477:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6478:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute9992);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6500:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==145) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6500:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,145,FollowSets000.FOLLOW_145_in_ruleEAttribute10005); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6504:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6505:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6505:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6506:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute10026);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6528:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==146) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6528:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,146,FollowSets000.FOLLOW_146_in_ruleEAttribute10039); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6532:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6533:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6533:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6534:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute10060);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6556:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==107) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6556:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,107,FollowSets000.FOLLOW_107_in_ruleEAttribute10073); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getETypeKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6560:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6561:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6561:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6562:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getETypeEClassifierCrossReference_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute10096);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6576:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==97) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6576:6: 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEAttribute10109); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEAnnotationsKeyword_16_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEAttribute10119); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_16_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6584:1: ( (lv_eAnnotations_25_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6585:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6585:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6586:3: lv_eAnnotations_25_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute10140);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6608:2: ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )*
                    loop185:
                    do {
                        int alt185=2;
                        int LA185_0 = input.LA(1);

                        if ( (LA185_0==52) ) {
                            alt185=1;
                        }


                        switch (alt185) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6608:4: ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEAttribute10151); 

                    	            createLeafNode(grammarAccess.getEAttributeAccess().getCommaKeyword_16_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6612:1: ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6613:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6613:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6614:3: lv_eAnnotations_27_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute10172);
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
                    	    break loop185;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEAttribute10184); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getRightCurlyBracketKeyword_16_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6640:3: ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==109) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6640:5: 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    {
                    match(input,109,FollowSets000.FOLLOW_109_in_ruleEAttribute10197); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEGenericTypeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6644:1: ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6645:1: (lv_eGenericType_30_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6645:1: (lv_eGenericType_30_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6646:3: lv_eGenericType_30_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEGenericTypeEGenericTypeParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEAttribute10218);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEAttribute10230); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6680:1: entryRuleEReference returns [EObject current=null] : iv_ruleEReference= ruleEReference EOF ;
    public final EObject entryRuleEReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEReference = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6681:2: (iv_ruleEReference= ruleEReference EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6682:2: iv_ruleEReference= ruleEReference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEReferenceRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEReference_in_entryRuleEReference10266);
            iv_ruleEReference=ruleEReference();
            _fsp--;

             current =iv_ruleEReference; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEReference10276); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6689:1: ruleEReference returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6694:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6695:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6695:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6695:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6695:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6696:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6706:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==139) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6707:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6707:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6708:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,139,FollowSets000.FOLLOW_139_in_ruleEReference10328); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6727:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==140) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6728:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6728:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6729:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,140,FollowSets000.FOLLOW_140_in_ruleEReference10360); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6748:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==141) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6749:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6749:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6750:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEReference10392); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6769:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==142) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6770:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6770:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6771:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,142,FollowSets000.FOLLOW_142_in_ruleEReference10424); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6790:3: ( (lv_containment_5_0= 'containment' ) )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==147) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6791:1: (lv_containment_5_0= 'containment' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6791:1: (lv_containment_5_0= 'containment' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6792:3: lv_containment_5_0= 'containment'
                    {
                    lv_containment_5_0=(Token)input.LT(1);
                    match(input,147,FollowSets000.FOLLOW_147_in_ruleEReference10456); 

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

            match(input,148,FollowSets000.FOLLOW_148_in_ruleEReference10480); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getEReferenceKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6815:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6816:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6816:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6817:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10501);
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

            match(input,28,FollowSets000.FOLLOW_28_in_ruleEReference10511); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6843:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==103) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6843:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEReference10522); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6847:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6848:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6848:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6849:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10543);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6871:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt194=2;
            int LA194_0 = input.LA(1);

            if ( (LA194_0==104) ) {
                alt194=1;
            }
            switch (alt194) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6871:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEReference10556); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6875:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6876:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6876:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6877:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10577);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6899:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==105) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6899:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEReference10590); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6903:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6904:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6904:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6905:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference10611);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6927:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==106) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6927:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEReference10624); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6931:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6932:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6932:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6933:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference10645);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6955:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==145) ) {
                alt197=1;
            }
            switch (alt197) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6955:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,145,FollowSets000.FOLLOW_145_in_ruleEReference10658); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6959:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6960:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6960:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6961:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10679);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6983:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==146) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6983:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,146,FollowSets000.FOLLOW_146_in_ruleEReference10692); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6987:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6988:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6988:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6989:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10713);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7011:4: ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==149) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7011:6: 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    {
                    match(input,149,FollowSets000.FOLLOW_149_in_ruleEReference10726); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getResolveProxiesKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7015:1: ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7016:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7016:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7017:3: lv_resolveProxies_22_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getResolveProxiesEBooleanParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10747);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7039:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==107) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7039:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,107,FollowSets000.FOLLOW_107_in_ruleEReference10760); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getETypeKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7043:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7044:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7044:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7045:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getETypeEClassifierCrossReference_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10783);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7059:4: ( 'eOpposite' ( ( ruleEString ) ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==150) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7059:6: 'eOpposite' ( ( ruleEString ) )
                    {
                    match(input,150,FollowSets000.FOLLOW_150_in_ruleEReference10796); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEOppositeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7063:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7065:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEOppositeEReferenceCrossReference_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10819);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7079:4: ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==151) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7079:6: 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,151,FollowSets000.FOLLOW_151_in_ruleEReference10832); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEKeysKeyword_18_0(), null); 
                        
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEReference10842); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftParenthesisKeyword_18_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7087:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7088:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7088:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7089:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10865);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7103:2: ( ',' ( ( ruleEString ) ) )*
                    loop202:
                    do {
                        int alt202=2;
                        int LA202_0 = input.LA(1);

                        if ( (LA202_0==52) ) {
                            alt202=1;
                        }


                        switch (alt202) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7103:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEReference10876); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_18_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7107:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7108:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7108:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7109:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10899);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop202;
                        }
                    } while (true);

                    match(input,96,FollowSets000.FOLLOW_96_in_ruleEReference10911); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightParenthesisKeyword_18_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7127:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )?
            int alt205=2;
            int LA205_0 = input.LA(1);

            if ( (LA205_0==97) ) {
                alt205=1;
            }
            switch (alt205) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7127:5: 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleEReference10924); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEAnnotationsKeyword_19_0(), null); 
                        
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleEReference10934); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_19_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7135:1: ( (lv_eAnnotations_35_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7136:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7136:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7137:3: lv_eAnnotations_35_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference10955);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7159:2: ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )*
                    loop204:
                    do {
                        int alt204=2;
                        int LA204_0 = input.LA(1);

                        if ( (LA204_0==52) ) {
                            alt204=1;
                        }


                        switch (alt204) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7159:4: ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,52,FollowSets000.FOLLOW_52_in_ruleEReference10966); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_19_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7163:1: ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7164:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7164:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7165:3: lv_eAnnotations_37_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference10987);
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
                    	    break loop204;
                        }
                    } while (true);

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleEReference10999); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightCurlyBracketKeyword_19_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7191:3: ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )?
            int alt206=2;
            int LA206_0 = input.LA(1);

            if ( (LA206_0==109) ) {
                alt206=1;
            }
            switch (alt206) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7191:5: 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    {
                    match(input,109,FollowSets000.FOLLOW_109_in_ruleEReference11012); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEGenericTypeKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7195:1: ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7196:1: (lv_eGenericType_40_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7196:1: (lv_eGenericType_40_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7197:3: lv_eGenericType_40_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEGenericTypeEGenericTypeParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEReference11033);
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

            match(input,39,FollowSets000.FOLLOW_39_in_ruleEReference11045); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7231:1: rulelengthUnit returns [Enumerator current=null] : ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) ;
    public final Enumerator rulelengthUnit() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7235:6: ( ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            int alt207=9;
            switch ( input.LA(1) ) {
            case 152:
                {
                alt207=1;
                }
                break;
            case 153:
                {
                alt207=2;
                }
                break;
            case 154:
                {
                alt207=3;
                }
                break;
            case 155:
                {
                alt207=4;
                }
                break;
            case 156:
                {
                alt207=5;
                }
                break;
            case 157:
                {
                alt207=6;
                }
                break;
            case 158:
                {
                alt207=7;
                }
                break;
            case 159:
                {
                alt207=8;
                }
                break;
            case 160:
                {
                alt207=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("7236:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )", 207, 0, input);

                throw nvae;
            }

            switch (alt207) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:2: ( 'none' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:2: ( 'none' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7236:4: 'none'
                    {
                    match(input,152,FollowSets000.FOLLOW_152_in_rulelengthUnit11093); 

                            current = grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7242:6: ( 'mi' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7242:6: ( 'mi' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7242:8: 'mi'
                    {
                    match(input,153,FollowSets000.FOLLOW_153_in_rulelengthUnit11108); 

                            current = grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7248:6: ( 'km' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7248:6: ( 'km' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7248:8: 'km'
                    {
                    match(input,154,FollowSets000.FOLLOW_154_in_rulelengthUnit11123); 

                            current = grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7254:6: ( 'kft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7254:6: ( 'kft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7254:8: 'kft'
                    {
                    match(input,155,FollowSets000.FOLLOW_155_in_rulelengthUnit11138); 

                            current = grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7260:6: ( 'm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7260:6: ( 'm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7260:8: 'm'
                    {
                    match(input,156,FollowSets000.FOLLOW_156_in_rulelengthUnit11153); 

                            current = grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7266:6: ( 'me' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7266:6: ( 'me' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7266:8: 'me'
                    {
                    match(input,157,FollowSets000.FOLLOW_157_in_rulelengthUnit11168); 

                            current = grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5(), null); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7272:6: ( 'ft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7272:6: ( 'ft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7272:8: 'ft'
                    {
                    match(input,158,FollowSets000.FOLLOW_158_in_rulelengthUnit11183); 

                            current = grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6(), null); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7278:6: ( 'in' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7278:6: ( 'in' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7278:8: 'in'
                    {
                    match(input,159,FollowSets000.FOLLOW_159_in_rulelengthUnit11198); 

                            current = grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7(), null); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7284:6: ( 'cm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7284:6: ( 'cm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7284:8: 'cm'
                    {
                    match(input,160,FollowSets000.FOLLOW_160_in_rulelengthUnit11213); 

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
        public static final BitSet FOLLOW_14_in_ruleElectrickery131 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery142 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery152 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_ruleWireData_in_ruleElectrickery175 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery185 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery203 = new BitSet(new long[]{0x0000030000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery214 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery224 = new BitSet(new long[]{0x0000030000000000L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_ruleElectrickery247 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery257 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery275 = new BitSet(new long[]{0x0002000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery286 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery296 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_ruleElectrickery319 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery329 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery347 = new BitSet(new long[]{0x0300000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery358 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery368 = new BitSet(new long[]{0x0300000000000000L});
        public static final BitSet FOLLOW_ruleLineCode_in_ruleElectrickery391 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery401 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery419 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000800L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery430 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
        public static final BitSet FOLLOW_ruleLoadShape_in_ruleElectrickery463 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery473 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery491 = new BitSet(new long[]{0x0000000000008000L,0x0000000000040000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery502 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleSpectrum_in_ruleElectrickery535 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery545 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_ruleExecutive_in_ruleElectrickery573 = new BitSet(new long[]{0x0000000000044002L});
        public static final BitSet FOLLOW_ruleExecutive_in_entryRuleExecutive611 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExecutive621 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_ruleExecutive665 = new BitSet(new long[]{0x0000000000180000L});
        public static final BitSet FOLLOW_ruleCircuit_in_ruleExecutive686 = new BitSet(new long[]{0x0000000000180002L});
        public static final BitSet FOLLOW_ruleCircuit_in_entryRuleCircuit723 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCircuit733 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleCircuit778 = new BitSet(new long[]{0x0000000007E00002L});
        public static final BitSet FOLLOW_20_in_ruleCircuit794 = new BitSet(new long[]{0x0000000007E00002L});
        public static final BitSet FOLLOW_21_in_ruleCircuit806 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCircuit827 = new BitSet(new long[]{0x0000000007C00002L});
        public static final BitSet FOLLOW_ruleDefaultVoltageSource_in_ruleCircuit850 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleDefaultVoltageSource_in_entryRuleDefaultVoltageSource888 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleDefaultVoltageSource898 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleDefaultVoltageSource944 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleDefaultVoltageSource954 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleDefaultVoltageSource975 = new BitSet(new long[]{0x0000000007800002L});
        public static final BitSet FOLLOW_23_in_ruleDefaultVoltageSource988 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleDefaultVoltageSource998 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1019 = new BitSet(new long[]{0x0000000007000002L});
        public static final BitSet FOLLOW_24_in_ruleDefaultVoltageSource1032 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleDefaultVoltageSource1042 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1063 = new BitSet(new long[]{0x0000000006000002L});
        public static final BitSet FOLLOW_25_in_ruleDefaultVoltageSource1076 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleDefaultVoltageSource1086 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1107 = new BitSet(new long[]{0x0000000004000002L});
        public static final BitSet FOLLOW_26_in_ruleDefaultVoltageSource1120 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleDefaultVoltageSource1130 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleDefaultVoltageSource1151 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWireData_in_entryRuleWireData1192 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleWireData1202 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleWireData1246 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWireData1267 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleWireData1277 = new BitSet(new long[]{0x000000FFE0000000L});
        public static final BitSet FOLLOW_29_in_ruleWireData1288 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1309 = new BitSet(new long[]{0x000000FFC0000000L});
        public static final BitSet FOLLOW_30_in_ruleWireData1322 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1343 = new BitSet(new long[]{0x000000FF80000000L});
        public static final BitSet FOLLOW_31_in_ruleWireData1356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000001FF000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1377 = new BitSet(new long[]{0x000000FF00000000L});
        public static final BitSet FOLLOW_32_in_ruleWireData1390 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1411 = new BitSet(new long[]{0x000000FE00000000L});
        public static final BitSet FOLLOW_33_in_ruleWireData1424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000001FF000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1445 = new BitSet(new long[]{0x000000FC00000000L});
        public static final BitSet FOLLOW_34_in_ruleWireData1458 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1479 = new BitSet(new long[]{0x000000F800000000L});
        public static final BitSet FOLLOW_35_in_ruleWireData1492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000001FF000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1513 = new BitSet(new long[]{0x000000F000000000L});
        public static final BitSet FOLLOW_36_in_ruleWireData1526 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1547 = new BitSet(new long[]{0x000000E000000000L});
        public static final BitSet FOLLOW_37_in_ruleWireData1560 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1581 = new BitSet(new long[]{0x000000C000000000L});
        public static final BitSet FOLLOW_38_in_ruleWireData1594 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1615 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleWireData1627 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1663 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineGeometry1673 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_40_in_ruleLineGeometry1716 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_41_in_ruleLineGeometry1740 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry1761 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLineGeometry1771 = new BitSet(new long[]{0x0001FC3000000000L});
        public static final BitSet FOLLOW_42_in_ruleLineGeometry1782 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1803 = new BitSet(new long[]{0x0001F83000000000L});
        public static final BitSet FOLLOW_43_in_ruleLineGeometry1816 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1837 = new BitSet(new long[]{0x0001F03000000000L});
        public static final BitSet FOLLOW_44_in_ruleLineGeometry1850 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1871 = new BitSet(new long[]{0x0001E03000000000L});
        public static final BitSet FOLLOW_45_in_ruleLineGeometry1884 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1905 = new BitSet(new long[]{0x0001C03000000000L});
        public static final BitSet FOLLOW_46_in_ruleLineGeometry1918 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1939 = new BitSet(new long[]{0x0001803000000000L});
        public static final BitSet FOLLOW_47_in_ruleLineGeometry1952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000001FF000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineGeometry1973 = new BitSet(new long[]{0x0001003000000000L});
        public static final BitSet FOLLOW_36_in_ruleLineGeometry1986 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry2007 = new BitSet(new long[]{0x0001002000000000L});
        public static final BitSet FOLLOW_37_in_ruleLineGeometry2020 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry2041 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleLineGeometry2053 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry2076 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLineGeometry2086 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape2122 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleGrowthShape2132 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_49_in_ruleGrowthShape2176 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleGrowthShape2186 = new BitSet(new long[]{0x00EC008000000000L});
        public static final BitSet FOLLOW_50_in_ruleGrowthShape2197 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleGrowthShape2218 = new BitSet(new long[]{0x00E8008000000000L});
        public static final BitSet FOLLOW_51_in_ruleGrowthShape2231 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleGrowthShape2241 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape2262 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleGrowthShape2273 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape2294 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleGrowthShape2306 = new BitSet(new long[]{0x00E0008000000000L});
        public static final BitSet FOLLOW_53_in_ruleGrowthShape2319 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2340 = new BitSet(new long[]{0x00C0008000000000L});
        public static final BitSet FOLLOW_54_in_ruleGrowthShape2353 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2374 = new BitSet(new long[]{0x0080008000000000L});
        public static final BitSet FOLLOW_55_in_ruleGrowthShape2387 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2408 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleGrowthShape2420 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineCode_in_entryRuleLineCode2456 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineCode2466 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_56_in_ruleLineCode2518 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleLineCode2542 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLineCode2552 = new BitSet(new long[]{0xFC0088B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_43_in_ruleLineCode2563 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2584 = new BitSet(new long[]{0xFC0080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_58_in_ruleLineCode2597 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2618 = new BitSet(new long[]{0xF80080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_59_in_ruleLineCode2631 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2652 = new BitSet(new long[]{0xF00080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_60_in_ruleLineCode2665 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2686 = new BitSet(new long[]{0xE00080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_61_in_ruleLineCode2699 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2720 = new BitSet(new long[]{0xC00080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_62_in_ruleLineCode2733 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2754 = new BitSet(new long[]{0x800080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_63_in_ruleLineCode2767 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2788 = new BitSet(new long[]{0x000080B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_47_in_ruleLineCode2801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000001FF000000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineCode2822 = new BitSet(new long[]{0x000000B000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_64_in_ruleLineCode2835 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2856 = new BitSet(new long[]{0x000000B000000000L,0x00000000000007FEL});
        public static final BitSet FOLLOW_36_in_ruleLineCode2869 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2890 = new BitSet(new long[]{0x000000A000000000L,0x00000000000007FEL});
        public static final BitSet FOLLOW_37_in_ruleLineCode2903 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2924 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007FEL});
        public static final BitSet FOLLOW_65_in_ruleLineCode2937 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2958 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007FCL});
        public static final BitSet FOLLOW_66_in_ruleLineCode2971 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2992 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007F8L});
        public static final BitSet FOLLOW_67_in_ruleLineCode3005 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode3026 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007F0L});
        public static final BitSet FOLLOW_68_in_ruleLineCode3039 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode3060 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007E0L});
        public static final BitSet FOLLOW_69_in_ruleLineCode3073 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode3094 = new BitSet(new long[]{0x0000008000000000L,0x00000000000007C0L});
        public static final BitSet FOLLOW_70_in_ruleLineCode3107 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode3128 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000780L});
        public static final BitSet FOLLOW_71_in_ruleLineCode3141 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode3162 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000700L});
        public static final BitSet FOLLOW_72_in_ruleLineCode3175 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode3198 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000600L});
        public static final BitSet FOLLOW_73_in_ruleLineCode3211 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode3234 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_74_in_ruleLineCode3247 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode3270 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLineCode3282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLoadShape_in_entryRuleLoadShape3318 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLoadShape3328 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleLoadShape3372 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3382 = new BitSet(new long[]{0x00E4008000000000L,0x000000000003F000L});
        public static final BitSet FOLLOW_50_in_ruleLoadShape3393 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape3414 = new BitSet(new long[]{0x00E0008000000000L,0x000000000003F000L});
        public static final BitSet FOLLOW_76_in_ruleLoadShape3427 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape3448 = new BitSet(new long[]{0x00E0008000000000L,0x000000000003E000L});
        public static final BitSet FOLLOW_77_in_ruleLoadShape3461 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3471 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3492 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleLoadShape3503 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3524 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLoadShape3536 = new BitSet(new long[]{0x00E0008000000000L,0x000000000003C000L});
        public static final BitSet FOLLOW_78_in_ruleLoadShape3549 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3559 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3580 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleLoadShape3591 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3612 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLoadShape3624 = new BitSet(new long[]{0x00E0008000000000L,0x0000000000038000L});
        public static final BitSet FOLLOW_79_in_ruleLoadShape3637 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3658 = new BitSet(new long[]{0x00E0008000000000L,0x0000000000030000L});
        public static final BitSet FOLLOW_80_in_ruleLoadShape3671 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3692 = new BitSet(new long[]{0x00E0008000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_53_in_ruleLoadShape3705 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3726 = new BitSet(new long[]{0x00C0008000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_54_in_ruleLoadShape3739 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3760 = new BitSet(new long[]{0x0080008000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_55_in_ruleLoadShape3773 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3794 = new BitSet(new long[]{0x0000008000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_81_in_ruleLoadShape3807 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleLoadShape3817 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3838 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleLoadShape3849 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3870 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLoadShape3882 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleLoadShape3894 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleSpectrum_in_entryRuleSpectrum3930 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleSpectrum3940 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleSpectrum3984 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum3994 = new BitSet(new long[]{0x0020008000000000L,0x0000000000780000L});
        public static final BitSet FOLLOW_83_in_ruleSpectrum4005 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleSpectrum4026 = new BitSet(new long[]{0x0020008000000000L,0x0000000000700000L});
        public static final BitSet FOLLOW_84_in_ruleSpectrum4039 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum4049 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum4070 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleSpectrum4081 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum4102 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleSpectrum4114 = new BitSet(new long[]{0x0020008000000000L,0x0000000000600000L});
        public static final BitSet FOLLOW_85_in_ruleSpectrum4127 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum4148 = new BitSet(new long[]{0x0020008000000000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleSpectrum4161 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleSpectrum4171 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum4192 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleSpectrum4203 = new BitSet(new long[]{0x0000000000200040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum4224 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleSpectrum4236 = new BitSet(new long[]{0x0020008000000000L});
        public static final BitSet FOLLOW_53_in_ruleSpectrum4249 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleSpectrum4270 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleSpectrum4282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature4318 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStructuralFeature4328 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_ruleEStructuralFeature4375 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_ruleEStructuralFeature4402 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString4440 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString4451 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString4491 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString4517 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDouble_in_entryRuleEDouble4563 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDouble4574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleEDouble4613 = new BitSet(new long[]{0x0000000000200040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4631 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_21_in_ruleEDouble4651 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4666 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000000L});
        public static final BitSet FOLLOW_88_in_ruleEDouble4686 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_89_in_ruleEDouble4705 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_87_in_ruleEDouble4720 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4737 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt4785 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt4796 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleEInt4835 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt4852 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean4898 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean4909 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleEBoolean4947 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleEBoolean4966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation5006 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAnnotation5016 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleEAnnotation5060 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5070 = new BitSet(new long[]{0x0000008000000000L,0x0000000E60000000L});
        public static final BitSet FOLLOW_93_in_ruleEAnnotation5081 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5102 = new BitSet(new long[]{0x0000008000000000L,0x0000000E40000000L});
        public static final BitSet FOLLOW_94_in_ruleEAnnotation5115 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEAnnotation5125 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5148 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_52_in_ruleEAnnotation5159 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation5182 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleEAnnotation5194 = new BitSet(new long[]{0x0000008000000000L,0x0000000E00000000L});
        public static final BitSet FOLLOW_97_in_ruleEAnnotation5207 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5217 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5238 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEAnnotation5249 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5270 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAnnotation5282 = new BitSet(new long[]{0x0000008000000000L,0x0000000C00000000L});
        public static final BitSet FOLLOW_98_in_ruleEAnnotation5295 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5305 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5326 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEAnnotation5337 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5358 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAnnotation5370 = new BitSet(new long[]{0x0000008000000000L,0x0000000800000000L});
        public static final BitSet FOLLOW_99_in_ruleEAnnotation5383 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAnnotation5393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5414 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEAnnotation5425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5446 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAnnotation5458 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAnnotation5470 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5506 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleETypeParameter5516 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_100_in_ruleETypeParameter5560 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleETypeParameter5581 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter5591 = new BitSet(new long[]{0x0000008000000000L,0x0000002200000000L});
        public static final BitSet FOLLOW_97_in_ruleETypeParameter5602 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter5612 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter5633 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleETypeParameter5644 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter5665 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleETypeParameter5677 = new BitSet(new long[]{0x0000008000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_101_in_ruleETypeParameter5690 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleETypeParameter5700 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter5721 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleETypeParameter5732 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter5753 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleETypeParameter5765 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleETypeParameter5777 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEOperation_in_entryRuleEOperation5813 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEOperation5823 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_102_in_ruleEOperation5867 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation5888 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation5898 = new BitSet(new long[]{0x0000008000000000L,0x0001FF8200000000L});
        public static final BitSet FOLLOW_103_in_ruleEOperation5909 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation5930 = new BitSet(new long[]{0x0000008000000000L,0x0001FF0200000000L});
        public static final BitSet FOLLOW_104_in_ruleEOperation5943 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation5964 = new BitSet(new long[]{0x0000008000000000L,0x0001FE0200000000L});
        public static final BitSet FOLLOW_105_in_ruleEOperation5977 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation5998 = new BitSet(new long[]{0x0000008000000000L,0x0001FC0200000000L});
        public static final BitSet FOLLOW_106_in_ruleEOperation6011 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation6032 = new BitSet(new long[]{0x0000008000000000L,0x0001F80200000000L});
        public static final BitSet FOLLOW_107_in_ruleEOperation6045 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6068 = new BitSet(new long[]{0x0000008000000000L,0x0001F00200000000L});
        public static final BitSet FOLLOW_108_in_ruleEOperation6081 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEOperation6091 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6114 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_52_in_ruleEOperation6125 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation6148 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleEOperation6160 = new BitSet(new long[]{0x0000008000000000L,0x0001E00200000000L});
        public static final BitSet FOLLOW_97_in_ruleEOperation6173 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6183 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation6204 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEOperation6215 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation6236 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEOperation6248 = new BitSet(new long[]{0x0000008000000000L,0x0001E00000000000L});
        public static final BitSet FOLLOW_109_in_ruleEOperation6261 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6282 = new BitSet(new long[]{0x0000008000000000L,0x0001C00000000000L});
        public static final BitSet FOLLOW_110_in_ruleEOperation6295 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6305 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6326 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEOperation6337 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6358 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEOperation6370 = new BitSet(new long[]{0x0000008000000000L,0x0001800000000000L});
        public static final BitSet FOLLOW_111_in_ruleEOperation6383 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6414 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEOperation6425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6446 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEOperation6458 = new BitSet(new long[]{0x0000008000000000L,0x0001000000000000L});
        public static final BitSet FOLLOW_112_in_ruleEOperation6471 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEOperation6481 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6502 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEOperation6513 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6534 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEOperation6546 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEOperation6558 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEGenericType_in_entryRuleEGenericType6594 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEGenericType6604 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_113_in_ruleEGenericType6648 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEGenericType6658 = new BitSet(new long[]{0x0000008000000000L,0x007C000000000000L});
        public static final BitSet FOLLOW_114_in_ruleEGenericType6669 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType6692 = new BitSet(new long[]{0x0000008000000000L,0x0078000000000000L});
        public static final BitSet FOLLOW_115_in_ruleEGenericType6705 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType6728 = new BitSet(new long[]{0x0000008000000000L,0x0070000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEGenericType6741 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6762 = new BitSet(new long[]{0x0000008000000000L,0x0060000000000000L});
        public static final BitSet FOLLOW_117_in_ruleEGenericType6775 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEGenericType6785 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6806 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEGenericType6817 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6838 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEGenericType6850 = new BitSet(new long[]{0x0000008000000000L,0x0040000000000000L});
        public static final BitSet FOLLOW_118_in_ruleEGenericType6863 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6884 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEGenericType6896 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry6932 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStringToStringMapEntry6942 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_119_in_ruleEStringToStringMapEntry6986 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEStringToStringMapEntry6996 = new BitSet(new long[]{0x0000008000000000L,0x0300000000000000L});
        public static final BitSet FOLLOW_120_in_ruleEStringToStringMapEntry7007 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7028 = new BitSet(new long[]{0x0000008000000000L,0x0200000000000000L});
        public static final BitSet FOLLOW_121_in_ruleEStringToStringMapEntry7041 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry7062 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEStringToStringMapEntry7074 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEClass_in_entryRuleEClass7110 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEClass7120 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_122_in_ruleEClass7172 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
        public static final BitSet FOLLOW_123_in_ruleEClass7204 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
        public static final BitSet FOLLOW_124_in_ruleEClass7228 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7249 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7259 = new BitSet(new long[]{0x0000008000000000L,0xE000400200000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_125_in_ruleEClass7270 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7291 = new BitSet(new long[]{0x0000008000000000L,0xC000400200000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_126_in_ruleEClass7304 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7325 = new BitSet(new long[]{0x0000008000000000L,0x8000400200000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_127_in_ruleEClass7338 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEClass7348 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7371 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7382 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7405 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleEClass7417 = new BitSet(new long[]{0x0000008000000000L,0x0000400200000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_97_in_ruleEClass7430 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7440 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7461 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7472 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7493 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7505 = new BitSet(new long[]{0x0000008000000000L,0x0000400000000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_110_in_ruleEClass7518 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7528 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7549 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7560 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7581 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7593 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000000L,0x0000000000000007L});
        public static final BitSet FOLLOW_128_in_ruleEClass7606 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7616 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass7637 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7648 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass7669 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7681 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000000L,0x0000000000000006L});
        public static final BitSet FOLLOW_129_in_ruleEClass7694 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7704 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000019F800L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass7725 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000019F800L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass7757 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7769 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_130_in_ruleEClass7782 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEClass7792 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass7813 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEClass7824 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass7845 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7857 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEClass7869 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEObject_in_entryRuleEObject7905 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEObject7915 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_131_in_ruleEObject7959 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEParameter_in_entryRuleEParameter7995 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEParameter8005 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_132_in_ruleEParameter8049 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter8070 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEParameter8080 = new BitSet(new long[]{0x0000008000000000L,0x00002F8200000000L});
        public static final BitSet FOLLOW_103_in_ruleEParameter8091 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter8112 = new BitSet(new long[]{0x0000008000000000L,0x00002F0200000000L});
        public static final BitSet FOLLOW_104_in_ruleEParameter8125 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter8146 = new BitSet(new long[]{0x0000008000000000L,0x00002E0200000000L});
        public static final BitSet FOLLOW_105_in_ruleEParameter8159 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter8180 = new BitSet(new long[]{0x0000008000000000L,0x00002C0200000000L});
        public static final BitSet FOLLOW_106_in_ruleEParameter8193 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter8214 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L});
        public static final BitSet FOLLOW_107_in_ruleEParameter8227 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter8250 = new BitSet(new long[]{0x0000008000000000L,0x0000200200000000L});
        public static final BitSet FOLLOW_97_in_ruleEParameter8263 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEParameter8273 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8294 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEParameter8305 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8326 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEParameter8338 = new BitSet(new long[]{0x0000008000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_109_in_ruleEParameter8351 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEParameter8372 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEParameter8384 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8420 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDataType_Impl8430 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_133_in_ruleEDataType_Impl8474 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8495 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl8505 = new BitSet(new long[]{0x0000008000000000L,0x6000400200000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_125_in_ruleEDataType_Impl8516 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8537 = new BitSet(new long[]{0x0000008000000000L,0x4000400200000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_126_in_ruleEDataType_Impl8550 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8571 = new BitSet(new long[]{0x0000008000000000L,0x0000400200000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_134_in_ruleEDataType_Impl8584 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEDataType_Impl8605 = new BitSet(new long[]{0x0000008000000000L,0x0000400200000000L});
        public static final BitSet FOLLOW_97_in_ruleEDataType_Impl8618 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl8628 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8649 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEDataType_Impl8660 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8681 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEDataType_Impl8693 = new BitSet(new long[]{0x0000008000000000L,0x0000400000000000L});
        public static final BitSet FOLLOW_110_in_ruleEDataType_Impl8706 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEDataType_Impl8716 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8737 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEDataType_Impl8748 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8769 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEDataType_Impl8781 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEDataType_Impl8793 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnum_in_entryRuleEEnum8829 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnum8839 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_135_in_ruleEEnum8883 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8904 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum8914 = new BitSet(new long[]{0x0000008000000000L,0x6000400200000000L,0x0000000000000140L});
        public static final BitSet FOLLOW_125_in_ruleEEnum8925 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8946 = new BitSet(new long[]{0x0000008000000000L,0x4000400200000000L,0x0000000000000140L});
        public static final BitSet FOLLOW_126_in_ruleEEnum8959 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8980 = new BitSet(new long[]{0x0000008000000000L,0x0000400200000000L,0x0000000000000140L});
        public static final BitSet FOLLOW_134_in_ruleEEnum8993 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEEnum9014 = new BitSet(new long[]{0x0000008000000000L,0x0000400200000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_97_in_ruleEEnum9027 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9037 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum9058 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEEnum9069 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum9090 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnum9102 = new BitSet(new long[]{0x0000008000000000L,0x0000400000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_110_in_ruleEEnum9115 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9125 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum9146 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEEnum9157 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum9178 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnum9190 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_136_in_ruleEEnum9203 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnum9213 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9234 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEEnum9245 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9266 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnum9278 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnum9290 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9326 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnumLiteral9336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_137_in_ruleEEnumLiteral9380 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9401 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnumLiteral9411 = new BitSet(new long[]{0x0000008000000000L,0x0200000200000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_121_in_ruleEEnumLiteral9422 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEEnumLiteral9443 = new BitSet(new long[]{0x0000008000000000L,0x0000000200000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_138_in_ruleEEnumLiteral9456 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9477 = new BitSet(new long[]{0x0000008000000000L,0x0000000200000000L});
        public static final BitSet FOLLOW_97_in_ruleEEnumLiteral9490 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEEnumLiteral9500 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9521 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEEnumLiteral9532 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9553 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnumLiteral9565 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEEnumLiteral9577 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_entryRuleEAttribute9613 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAttribute9623 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_139_in_ruleEAttribute9675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000001F000L});
        public static final BitSet FOLLOW_140_in_ruleEAttribute9707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000001E000L});
        public static final BitSet FOLLOW_141_in_ruleEAttribute9739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000001C000L});
        public static final BitSet FOLLOW_142_in_ruleEAttribute9771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000018000L});
        public static final BitSet FOLLOW_143_in_ruleEAttribute9803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_144_in_ruleEAttribute9827 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute9848 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAttribute9858 = new BitSet(new long[]{0x0000008000000000L,0x00002F8200000000L,0x0000000000060000L});
        public static final BitSet FOLLOW_103_in_ruleEAttribute9869 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute9890 = new BitSet(new long[]{0x0000008000000000L,0x00002F0200000000L,0x0000000000060000L});
        public static final BitSet FOLLOW_104_in_ruleEAttribute9903 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute9924 = new BitSet(new long[]{0x0000008000000000L,0x00002E0200000000L,0x0000000000060000L});
        public static final BitSet FOLLOW_105_in_ruleEAttribute9937 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute9958 = new BitSet(new long[]{0x0000008000000000L,0x00002C0200000000L,0x0000000000060000L});
        public static final BitSet FOLLOW_106_in_ruleEAttribute9971 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute9992 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000060000L});
        public static final BitSet FOLLOW_145_in_ruleEAttribute10005 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute10026 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_146_in_ruleEAttribute10039 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute10060 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L});
        public static final BitSet FOLLOW_107_in_ruleEAttribute10073 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute10096 = new BitSet(new long[]{0x0000008000000000L,0x0000200200000000L});
        public static final BitSet FOLLOW_97_in_ruleEAttribute10109 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEAttribute10119 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute10140 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEAttribute10151 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute10172 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAttribute10184 = new BitSet(new long[]{0x0000008000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_109_in_ruleEAttribute10197 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEAttribute10218 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEAttribute10230 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_entryRuleEReference10266 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEReference10276 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_139_in_ruleEReference10328 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000187000L});
        public static final BitSet FOLLOW_140_in_ruleEReference10360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000186000L});
        public static final BitSet FOLLOW_141_in_ruleEReference10392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000184000L});
        public static final BitSet FOLLOW_142_in_ruleEReference10424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000180000L});
        public static final BitSet FOLLOW_147_in_ruleEReference10456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
        public static final BitSet FOLLOW_148_in_ruleEReference10480 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10501 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEReference10511 = new BitSet(new long[]{0x0000008000000000L,0x00002F8200000000L,0x0000000000E60000L});
        public static final BitSet FOLLOW_103_in_ruleEReference10522 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10543 = new BitSet(new long[]{0x0000008000000000L,0x00002F0200000000L,0x0000000000E60000L});
        public static final BitSet FOLLOW_104_in_ruleEReference10556 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10577 = new BitSet(new long[]{0x0000008000000000L,0x00002E0200000000L,0x0000000000E60000L});
        public static final BitSet FOLLOW_105_in_ruleEReference10590 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference10611 = new BitSet(new long[]{0x0000008000000000L,0x00002C0200000000L,0x0000000000E60000L});
        public static final BitSet FOLLOW_106_in_ruleEReference10624 = new BitSet(new long[]{0x0000000000000040L,0x0000000000800000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference10645 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000E60000L});
        public static final BitSet FOLLOW_145_in_ruleEReference10658 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10679 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000E40000L});
        public static final BitSet FOLLOW_146_in_ruleEReference10692 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10713 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000E00000L});
        public static final BitSet FOLLOW_149_in_ruleEReference10726 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10747 = new BitSet(new long[]{0x0000008000000000L,0x0000280200000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_107_in_ruleEReference10760 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10783 = new BitSet(new long[]{0x0000008000000000L,0x0000200200000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_150_in_ruleEReference10796 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10819 = new BitSet(new long[]{0x0000008000000000L,0x0000200200000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_151_in_ruleEReference10832 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEReference10842 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10865 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_52_in_ruleEReference10876 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10899 = new BitSet(new long[]{0x0010000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleEReference10911 = new BitSet(new long[]{0x0000008000000000L,0x0000200200000000L});
        public static final BitSet FOLLOW_97_in_ruleEReference10924 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEReference10934 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference10955 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_52_in_ruleEReference10966 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference10987 = new BitSet(new long[]{0x0010008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEReference10999 = new BitSet(new long[]{0x0000008000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_109_in_ruleEReference11012 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEReference11033 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleEReference11045 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_152_in_rulelengthUnit11093 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_153_in_rulelengthUnit11108 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_154_in_rulelengthUnit11123 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_155_in_rulelengthUnit11138 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_156_in_rulelengthUnit11153 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_157_in_rulelengthUnit11168 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_158_in_rulelengthUnit11183 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_159_in_rulelengthUnit11198 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_160_in_rulelengthUnit11213 = new BitSet(new long[]{0x0000000000000002L});
    }


}