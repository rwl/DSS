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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Electrickery'", "'{'", "'wireData'", "','", "'}'", "'lineGeometries'", "'growthShapes'", "'lineCodes'", "'loadShapes'", "'spectrums'", "'executives'", "'WireData'", "'rDC'", "'rAC'", "'rUnits'", "'gmrAC'", "'gmrUnits'", "'radius'", "'radUnits'", "'normAmps'", "'emergAmps'", "'diameter'", "'reduce'", "'LineGeometry'", "'nConds'", "'nPhases'", "'cond'", "'x'", "'h'", "'units'", "'wire'", "'GrowthShape'", "'nPts'", "'year'", "'csvFile'", "'sngFile'", "'dblFile'", "'kron'", "'LineCode'", "'r1'", "'x1'", "'r0'", "'x0'", "'c1'", "'c0'", "'baseFreq'", "'faultRate'", "'pctPerm'", "'repair'", "'rg'", "'xg'", "'rho'", "'neutral'", "'rMatrix'", "'xMatrix'", "'cMatrix'", "'LoadShape'", "'interval'", "'mult'", "'hour'", "'mean'", "'stdDev'", "'qMult'", "'Spectrum'", "'nHarm'", "'harmonic'", "'pctMag'", "'angle'", "'Executive'", "'command'", "'maxCircuits'", "'-'", "'.'", "'E'", "'e'", "'true'", "'false'", "'EAnnotation'", "'source'", "'references'", "'('", "')'", "'eAnnotations'", "'details'", "'contents'", "'ETypeParameter'", "'eBounds'", "'EOperation'", "'ordered'", "'unique'", "'lowerBound'", "'upperBound'", "'eType'", "'eExceptions'", "'eGenericType'", "'eTypeParameters'", "'eParameters'", "'eGenericExceptions'", "'EGenericType'", "'eTypeParameter'", "'eClassifier'", "'eUpperBound'", "'eTypeArguments'", "'eLowerBound'", "'EStringToStringMapEntry'", "'key'", "'value'", "'abstract'", "'interface'", "'EClass'", "'instanceClassName'", "'instanceTypeName'", "'eSuperTypes'", "'eOperations'", "'eStructuralFeatures'", "'eGenericSuperTypes'", "'EObject'", "'EParameter'", "'EDataType'", "'serializable'", "'EEnum'", "'eLiterals'", "'EEnumLiteral'", "'literal'", "'volatile'", "'transient'", "'unsettable'", "'derived'", "'iD'", "'EAttribute'", "'changeable'", "'defaultValueLiteral'", "'containment'", "'EReference'", "'resolveProxies'", "'eOpposite'", "'eKeys'", "'none'", "'mi'", "'km'", "'kft'", "'m'", "'me'", "'ft'", "'in'", "'cm'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:87:1: ruleElectrickery returns [EObject current=null] : ( () 'Electrickery' '{' ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )? ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )? ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )? ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )? ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )? ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )? ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )? '}' ) ;
    public final EObject ruleElectrickery() throws RecognitionException {
        EObject current = null;

        EObject lv_wireData_5_0 = null;

        EObject lv_wireData_7_0 = null;

        EObject lv_lineGeometries_11_0 = null;

        EObject lv_lineGeometries_13_0 = null;

        EObject lv_growthShapes_17_0 = null;

        EObject lv_growthShapes_19_0 = null;

        EObject lv_lineCodes_23_0 = null;

        EObject lv_lineCodes_25_0 = null;

        EObject lv_loadShapes_29_0 = null;

        EObject lv_loadShapes_31_0 = null;

        EObject lv_spectrums_35_0 = null;

        EObject lv_spectrums_37_0 = null;

        EObject lv_executives_41_0 = null;

        EObject lv_executives_43_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:92:6: ( ( () 'Electrickery' '{' ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )? ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )? ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )? ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )? ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )? ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )? ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () 'Electrickery' '{' ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )? ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )? ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )? ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )? ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )? ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )? ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:1: ( () 'Electrickery' '{' ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )? ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )? ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )? ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )? ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )? ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )? ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:93:2: () 'Electrickery' '{' ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )? ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )? ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )? ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )? ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )? ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )? ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )? '}'
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

            match(input,11,FollowSets000.FOLLOW_11_in_ruleElectrickery129); 

                    createLeafNode(grammarAccess.getElectrickeryAccess().getElectrickeryKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery139); 

                    createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:112:1: ( 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:112:3: 'wireData' '{' ( (lv_wireData_5_0= ruleWireData ) ) ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )* '}'
                    {
                    match(input,13,FollowSets000.FOLLOW_13_in_ruleElectrickery150); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getWireDataKeyword_3_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery160); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_3_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:120:1: ( (lv_wireData_5_0= ruleWireData ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:121:1: (lv_wireData_5_0= ruleWireData )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:121:1: (lv_wireData_5_0= ruleWireData )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:122:3: lv_wireData_5_0= ruleWireData
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getWireDataWireDataParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleWireData_in_ruleElectrickery181);
                    lv_wireData_5_0=ruleWireData();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"wireData",
                    	        		lv_wireData_5_0, 
                    	        		"WireData", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:144:2: ( ',' ( (lv_wireData_7_0= ruleWireData ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==14) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:144:4: ',' ( (lv_wireData_7_0= ruleWireData ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery192); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_3_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:148:1: ( (lv_wireData_7_0= ruleWireData ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:1: (lv_wireData_7_0= ruleWireData )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:149:1: (lv_wireData_7_0= ruleWireData )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:150:3: lv_wireData_7_0= ruleWireData
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getWireDataWireDataParserRuleCall_3_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleWireData_in_ruleElectrickery213);
                    	    lv_wireData_7_0=ruleWireData();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"wireData",
                    	    	        		lv_wireData_7_0, 
                    	    	        		"WireData", 
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
                    	    break loop1;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery225); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_3_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:176:3: ( 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:176:5: 'lineGeometries' '{' ( (lv_lineGeometries_11_0= ruleLineGeometry ) ) ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )* '}'
                    {
                    match(input,16,FollowSets000.FOLLOW_16_in_ruleElectrickery238); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLineGeometriesKeyword_4_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery248); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:184:1: ( (lv_lineGeometries_11_0= ruleLineGeometry ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:185:1: (lv_lineGeometries_11_0= ruleLineGeometry )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:185:1: (lv_lineGeometries_11_0= ruleLineGeometry )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:186:3: lv_lineGeometries_11_0= ruleLineGeometry
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineGeometriesLineGeometryParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_ruleElectrickery269);
                    lv_lineGeometries_11_0=ruleLineGeometry();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"lineGeometries",
                    	        		lv_lineGeometries_11_0, 
                    	        		"LineGeometry", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:208:2: ( ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==14) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:208:4: ',' ( (lv_lineGeometries_13_0= ruleLineGeometry ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery280); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:212:1: ( (lv_lineGeometries_13_0= ruleLineGeometry ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:213:1: (lv_lineGeometries_13_0= ruleLineGeometry )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:213:1: (lv_lineGeometries_13_0= ruleLineGeometry )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:214:3: lv_lineGeometries_13_0= ruleLineGeometry
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineGeometriesLineGeometryParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_ruleElectrickery301);
                    	    lv_lineGeometries_13_0=ruleLineGeometry();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"lineGeometries",
                    	    	        		lv_lineGeometries_13_0, 
                    	    	        		"LineGeometry", 
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
                    	    break loop3;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery313); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:240:3: ( 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:240:5: 'growthShapes' '{' ( (lv_growthShapes_17_0= ruleGrowthShape ) ) ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )* '}'
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleElectrickery326); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getGrowthShapesKeyword_5_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery336); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:248:1: ( (lv_growthShapes_17_0= ruleGrowthShape ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:249:1: (lv_growthShapes_17_0= ruleGrowthShape )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:249:1: (lv_growthShapes_17_0= ruleGrowthShape )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:250:3: lv_growthShapes_17_0= ruleGrowthShape
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getGrowthShapesGrowthShapeParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_ruleElectrickery357);
                    lv_growthShapes_17_0=ruleGrowthShape();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"growthShapes",
                    	        		lv_growthShapes_17_0, 
                    	        		"GrowthShape", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:2: ( ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==14) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:272:4: ',' ( (lv_growthShapes_19_0= ruleGrowthShape ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery368); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:276:1: ( (lv_growthShapes_19_0= ruleGrowthShape ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:277:1: (lv_growthShapes_19_0= ruleGrowthShape )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:277:1: (lv_growthShapes_19_0= ruleGrowthShape )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:278:3: lv_growthShapes_19_0= ruleGrowthShape
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getGrowthShapesGrowthShapeParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_ruleElectrickery389);
                    	    lv_growthShapes_19_0=ruleGrowthShape();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"growthShapes",
                    	    	        		lv_growthShapes_19_0, 
                    	    	        		"GrowthShape", 
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
                    	    break loop5;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery401); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:304:3: ( 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==18) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:304:5: 'lineCodes' '{' ( (lv_lineCodes_23_0= ruleLineCode ) ) ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )* '}'
                    {
                    match(input,18,FollowSets000.FOLLOW_18_in_ruleElectrickery414); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLineCodesKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery424); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:312:1: ( (lv_lineCodes_23_0= ruleLineCode ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:1: (lv_lineCodes_23_0= ruleLineCode )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:313:1: (lv_lineCodes_23_0= ruleLineCode )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:314:3: lv_lineCodes_23_0= ruleLineCode
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineCodesLineCodeParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_ruleElectrickery445);
                    lv_lineCodes_23_0=ruleLineCode();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"lineCodes",
                    	        		lv_lineCodes_23_0, 
                    	        		"LineCode", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:336:2: ( ',' ( (lv_lineCodes_25_0= ruleLineCode ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==14) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:336:4: ',' ( (lv_lineCodes_25_0= ruleLineCode ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery456); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:340:1: ( (lv_lineCodes_25_0= ruleLineCode ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:341:1: (lv_lineCodes_25_0= ruleLineCode )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:341:1: (lv_lineCodes_25_0= ruleLineCode )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:342:3: lv_lineCodes_25_0= ruleLineCode
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLineCodesLineCodeParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_ruleElectrickery477);
                    	    lv_lineCodes_25_0=ruleLineCode();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"lineCodes",
                    	    	        		lv_lineCodes_25_0, 
                    	    	        		"LineCode", 
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery489); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:368:3: ( 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:368:5: 'loadShapes' '{' ( (lv_loadShapes_29_0= ruleLoadShape ) ) ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )* '}'
                    {
                    match(input,19,FollowSets000.FOLLOW_19_in_ruleElectrickery502); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLoadShapesKeyword_7_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery512); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:376:1: ( (lv_loadShapes_29_0= ruleLoadShape ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:377:1: (lv_loadShapes_29_0= ruleLoadShape )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:377:1: (lv_loadShapes_29_0= ruleLoadShape )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:378:3: lv_loadShapes_29_0= ruleLoadShape
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLoadShapesLoadShapeParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_ruleElectrickery533);
                    lv_loadShapes_29_0=ruleLoadShape();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"loadShapes",
                    	        		lv_loadShapes_29_0, 
                    	        		"LoadShape", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:400:2: ( ',' ( (lv_loadShapes_31_0= ruleLoadShape ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==14) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:400:4: ',' ( (lv_loadShapes_31_0= ruleLoadShape ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery544); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:404:1: ( (lv_loadShapes_31_0= ruleLoadShape ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:405:1: (lv_loadShapes_31_0= ruleLoadShape )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:405:1: (lv_loadShapes_31_0= ruleLoadShape )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:406:3: lv_loadShapes_31_0= ruleLoadShape
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getLoadShapesLoadShapeParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_ruleElectrickery565);
                    	    lv_loadShapes_31_0=ruleLoadShape();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"loadShapes",
                    	    	        		lv_loadShapes_31_0, 
                    	    	        		"LoadShape", 
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
                    	    break loop9;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery577); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:432:3: ( 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:432:5: 'spectrums' '{' ( (lv_spectrums_35_0= ruleSpectrum ) ) ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )* '}'
                    {
                    match(input,20,FollowSets000.FOLLOW_20_in_ruleElectrickery590); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getSpectrumsKeyword_8_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery600); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:440:1: ( (lv_spectrums_35_0= ruleSpectrum ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:441:1: (lv_spectrums_35_0= ruleSpectrum )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:441:1: (lv_spectrums_35_0= ruleSpectrum )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:442:3: lv_spectrums_35_0= ruleSpectrum
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getSpectrumsSpectrumParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_ruleElectrickery621);
                    lv_spectrums_35_0=ruleSpectrum();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"spectrums",
                    	        		lv_spectrums_35_0, 
                    	        		"Spectrum", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:464:2: ( ',' ( (lv_spectrums_37_0= ruleSpectrum ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==14) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:464:4: ',' ( (lv_spectrums_37_0= ruleSpectrum ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery632); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:468:1: ( (lv_spectrums_37_0= ruleSpectrum ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:469:1: (lv_spectrums_37_0= ruleSpectrum )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:469:1: (lv_spectrums_37_0= ruleSpectrum )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:470:3: lv_spectrums_37_0= ruleSpectrum
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getSpectrumsSpectrumParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_ruleElectrickery653);
                    	    lv_spectrums_37_0=ruleSpectrum();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"spectrums",
                    	    	        		lv_spectrums_37_0, 
                    	    	        		"Spectrum", 
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
                    	    break loop11;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery665); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:496:3: ( 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==21) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:496:5: 'executives' '{' ( (lv_executives_41_0= ruleExecutive ) ) ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )* '}'
                    {
                    match(input,21,FollowSets000.FOLLOW_21_in_ruleElectrickery678); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getExecutivesKeyword_9_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleElectrickery688); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:504:1: ( (lv_executives_41_0= ruleExecutive ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:505:1: (lv_executives_41_0= ruleExecutive )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:505:1: (lv_executives_41_0= ruleExecutive )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:506:3: lv_executives_41_0= ruleExecutive
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getExecutivesExecutiveParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_ruleElectrickery709);
                    lv_executives_41_0=ruleExecutive();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"executives",
                    	        		lv_executives_41_0, 
                    	        		"Executive", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:528:2: ( ',' ( (lv_executives_43_0= ruleExecutive ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==14) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:528:4: ',' ( (lv_executives_43_0= ruleExecutive ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleElectrickery720); 

                    	            createLeafNode(grammarAccess.getElectrickeryAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:532:1: ( (lv_executives_43_0= ruleExecutive ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:533:1: (lv_executives_43_0= ruleExecutive )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:533:1: (lv_executives_43_0= ruleExecutive )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:534:3: lv_executives_43_0= ruleExecutive
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getElectrickeryAccess().getExecutivesExecutiveParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_ruleElectrickery741);
                    	    lv_executives_43_0=ruleExecutive();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getElectrickeryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"executives",
                    	    	        		lv_executives_43_0, 
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
                    	    break loop13;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery753); 

                            createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleElectrickery765); 

                    createLeafNode(grammarAccess.getElectrickeryAccess().getRightCurlyBracketKeyword_10(), null); 
                

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:572:1: entryRuleEStructuralFeature returns [EObject current=null] : iv_ruleEStructuralFeature= ruleEStructuralFeature EOF ;
    public final EObject entryRuleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStructuralFeature = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:573:2: (iv_ruleEStructuralFeature= ruleEStructuralFeature EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:574:2: iv_ruleEStructuralFeature= ruleEStructuralFeature EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStructuralFeatureRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature801);
            iv_ruleEStructuralFeature=ruleEStructuralFeature();
            _fsp--;

             current =iv_ruleEStructuralFeature; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStructuralFeature811); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:581:1: ruleEStructuralFeature returns [EObject current=null] : (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) ;
    public final EObject ruleEStructuralFeature() throws RecognitionException {
        EObject current = null;

        EObject this_EAttribute_0 = null;

        EObject this_EReference_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:586:6: ( (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )
            int alt15=2;
            switch ( input.LA(1) ) {
            case 135:
                {
                switch ( input.LA(2) ) {
                case 136:
                    {
                    switch ( input.LA(3) ) {
                    case 137:
                        {
                        switch ( input.LA(4) ) {
                        case 138:
                            {
                            int LA15_4 = input.LA(5);

                            if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                                alt15=1;
                            }
                            else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                                alt15=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 143:
                        case 144:
                            {
                            alt15=2;
                            }
                            break;
                        case 139:
                        case 140:
                            {
                            alt15=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 3, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 138:
                        {
                        int LA15_4 = input.LA(4);

                        if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                            alt15=1;
                        }
                        else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                            alt15=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt15=2;
                        }
                        break;
                    case 139:
                    case 140:
                        {
                        alt15=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 2, input);

                        throw nvae;
                    }

                    }
                    break;
                case 137:
                    {
                    switch ( input.LA(3) ) {
                    case 138:
                        {
                        int LA15_4 = input.LA(4);

                        if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                            alt15=1;
                        }
                        else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                            alt15=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt15=2;
                        }
                        break;
                    case 139:
                    case 140:
                        {
                        alt15=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 138:
                    {
                    int LA15_4 = input.LA(3);

                    if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                        alt15=1;
                    }
                    else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 139:
                case 140:
                    {
                    alt15=1;
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt15=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 1, input);

                    throw nvae;
                }

                }
                break;
            case 136:
                {
                switch ( input.LA(2) ) {
                case 137:
                    {
                    switch ( input.LA(3) ) {
                    case 138:
                        {
                        int LA15_4 = input.LA(4);

                        if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                            alt15=1;
                        }
                        else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                            alt15=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 143:
                    case 144:
                        {
                        alt15=2;
                        }
                        break;
                    case 139:
                    case 140:
                        {
                        alt15=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 3, input);

                        throw nvae;
                    }

                    }
                    break;
                case 138:
                    {
                    int LA15_4 = input.LA(3);

                    if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                        alt15=1;
                    }
                    else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt15=2;
                    }
                    break;
                case 139:
                case 140:
                    {
                    alt15=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 2, input);

                    throw nvae;
                }

                }
                break;
            case 137:
                {
                switch ( input.LA(2) ) {
                case 138:
                    {
                    int LA15_4 = input.LA(3);

                    if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                        alt15=1;
                    }
                    else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 143:
                case 144:
                    {
                    alt15=2;
                    }
                    break;
                case 139:
                case 140:
                    {
                    alt15=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 3, input);

                    throw nvae;
                }

                }
                break;
            case 138:
                {
                int LA15_4 = input.LA(2);

                if ( ((LA15_4>=139 && LA15_4<=140)) ) {
                    alt15=1;
                }
                else if ( ((LA15_4>=143 && LA15_4<=144)) ) {
                    alt15=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 4, input);

                    throw nvae;
                }
                }
                break;
            case 139:
            case 140:
                {
                alt15=1;
                }
                break;
            case 143:
            case 144:
                {
                alt15=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("587:1: (this_EAttribute_0= ruleEAttribute | this_EReference_1= ruleEReference )", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:588:5: this_EAttribute_0= ruleEAttribute
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEAttributeParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_ruleEStructuralFeature858);
                    this_EAttribute_0=ruleEAttribute();
                    _fsp--;

                     
                            current = this_EAttribute_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:598:5: this_EReference_1= ruleEReference
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEStructuralFeatureAccess().getEReferenceParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleEReference_in_ruleEStructuralFeature885);
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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:616:1: entryRuleWireData returns [EObject current=null] : iv_ruleWireData= ruleWireData EOF ;
    public final EObject entryRuleWireData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWireData = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:617:2: (iv_ruleWireData= ruleWireData EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:618:2: iv_ruleWireData= ruleWireData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWireDataRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleWireData_in_entryRuleWireData922);
            iv_ruleWireData=ruleWireData();
            _fsp--;

             current =iv_ruleWireData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleWireData932); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:625:1: ruleWireData returns [EObject current=null] : ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:630:6: ( ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:1: ( () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:2: () 'WireData' ( (lv_name_2_0= ruleEString ) ) '{' ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )? ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )? ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )? ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )? ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )? ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )? ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )? ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:631:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:632:5: 
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

            match(input,22,FollowSets000.FOLLOW_22_in_ruleWireData976); 

                    createLeafNode(grammarAccess.getWireDataAccess().getWireDataKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:646:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:647:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:647:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:648:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWireData997);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleWireData1007); 

                    createLeafNode(grammarAccess.getWireDataAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:674:1: ( 'rDC' ( (lv_rDC_5_0= ruleEDouble ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==23) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:674:3: 'rDC' ( (lv_rDC_5_0= ruleEDouble ) )
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleWireData1018); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRDCKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:678:1: ( (lv_rDC_5_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:679:1: (lv_rDC_5_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:679:1: (lv_rDC_5_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:680:3: lv_rDC_5_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRDCEDoubleParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1039);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:702:4: ( 'rAC' ( (lv_rAC_7_0= ruleEDouble ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:702:6: 'rAC' ( (lv_rAC_7_0= ruleEDouble ) )
                    {
                    match(input,24,FollowSets000.FOLLOW_24_in_ruleWireData1052); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRACKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:706:1: ( (lv_rAC_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:707:1: (lv_rAC_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:707:1: (lv_rAC_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:708:3: lv_rAC_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRACEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1073);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:730:4: ( 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==25) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:730:6: 'rUnits' ( (lv_rUnits_9_0= rulelengthUnit ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleWireData1086); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRUnitsKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:734:1: ( (lv_rUnits_9_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:735:1: (lv_rUnits_9_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:735:1: (lv_rUnits_9_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:736:3: lv_rUnits_9_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRUnitsLengthUnitEnumRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1107);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:758:4: ( 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==26) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:758:6: 'gmrAC' ( (lv_gmrAC_11_0= ruleEDouble ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleWireData1120); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrACKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:762:1: ( (lv_gmrAC_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:763:1: (lv_gmrAC_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:763:1: (lv_gmrAC_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:764:3: lv_gmrAC_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrACEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1141);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:786:4: ( 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==27) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:786:6: 'gmrUnits' ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    {
                    match(input,27,FollowSets000.FOLLOW_27_in_ruleWireData1154); 

                            createLeafNode(grammarAccess.getWireDataAccess().getGmrUnitsKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:790:1: ( (lv_gmrUnits_13_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:791:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:791:1: (lv_gmrUnits_13_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:792:3: lv_gmrUnits_13_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getGmrUnitsLengthUnitEnumRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1175);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:814:4: ( 'radius' ( (lv_radius_15_0= ruleEDouble ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:814:6: 'radius' ( (lv_radius_15_0= ruleEDouble ) )
                    {
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleWireData1188); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadiusKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:818:1: ( (lv_radius_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:819:1: (lv_radius_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:819:1: (lv_radius_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:820:3: lv_radius_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadiusEDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1209);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:842:4: ( 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==29) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:842:6: 'radUnits' ( (lv_radUnits_17_0= rulelengthUnit ) )
                    {
                    match(input,29,FollowSets000.FOLLOW_29_in_ruleWireData1222); 

                            createLeafNode(grammarAccess.getWireDataAccess().getRadUnitsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:846:1: ( (lv_radUnits_17_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:847:1: (lv_radUnits_17_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:847:1: (lv_radUnits_17_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:848:3: lv_radUnits_17_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getRadUnitsLengthUnitEnumRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleWireData1243);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:870:4: ( 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==30) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:870:6: 'normAmps' ( (lv_normAmps_19_0= ruleEDouble ) )
                    {
                    match(input,30,FollowSets000.FOLLOW_30_in_ruleWireData1256); 

                            createLeafNode(grammarAccess.getWireDataAccess().getNormAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:874:1: ( (lv_normAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:875:1: (lv_normAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:875:1: (lv_normAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:876:3: lv_normAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getNormAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1277);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:898:4: ( 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==31) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:898:6: 'emergAmps' ( (lv_emergAmps_21_0= ruleEDouble ) )
                    {
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleWireData1290); 

                            createLeafNode(grammarAccess.getWireDataAccess().getEmergAmpsKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:902:1: ( (lv_emergAmps_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:903:1: (lv_emergAmps_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:903:1: (lv_emergAmps_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:904:3: lv_emergAmps_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getEmergAmpsEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1311);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:926:4: ( 'diameter' ( (lv_diameter_23_0= ruleEDouble ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==32) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:926:6: 'diameter' ( (lv_diameter_23_0= ruleEDouble ) )
                    {
                    match(input,32,FollowSets000.FOLLOW_32_in_ruleWireData1324); 

                            createLeafNode(grammarAccess.getWireDataAccess().getDiameterKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:930:1: ( (lv_diameter_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:931:1: (lv_diameter_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:931:1: (lv_diameter_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:932:3: lv_diameter_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getWireDataAccess().getDiameterEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleWireData1345);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleWireData1357); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:966:1: entryRuleLineGeometry returns [EObject current=null] : iv_ruleLineGeometry= ruleLineGeometry EOF ;
    public final EObject entryRuleLineGeometry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineGeometry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:967:2: (iv_ruleLineGeometry= ruleLineGeometry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:968:2: iv_ruleLineGeometry= ruleLineGeometry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineGeometryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1393);
            iv_ruleLineGeometry=ruleLineGeometry();
            _fsp--;

             current =iv_ruleLineGeometry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineGeometry1403); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:975:1: ruleLineGeometry returns [EObject current=null] : ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:980:6: ( ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:981:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:981:1: ( ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:981:2: ( (lv_reduce_0_0= 'reduce' ) )? 'LineGeometry' ( (lv_name_2_0= ruleEString ) ) '{' ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )? ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )? ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )? ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )? ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )? ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )? 'wire' ( ( ruleEString ) ) '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:981:2: ( (lv_reduce_0_0= 'reduce' ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==33) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:982:1: (lv_reduce_0_0= 'reduce' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:982:1: (lv_reduce_0_0= 'reduce' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:983:3: lv_reduce_0_0= 'reduce'
                    {
                    lv_reduce_0_0=(Token)input.LT(1);
                    match(input,33,FollowSets000.FOLLOW_33_in_ruleLineGeometry1446); 

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

            match(input,34,FollowSets000.FOLLOW_34_in_ruleLineGeometry1470); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLineGeometryKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1006:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1007:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1007:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1008:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry1491);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleLineGeometry1501); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1034:1: ( 'nConds' ( (lv_nConds_5_0= ruleEInt ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==35) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1034:3: 'nConds' ( (lv_nConds_5_0= ruleEInt ) )
                    {
                    match(input,35,FollowSets000.FOLLOW_35_in_ruleLineGeometry1512); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNCondsKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1038:1: ( (lv_nConds_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1039:1: (lv_nConds_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1039:1: (lv_nConds_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1040:3: lv_nConds_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNCondsEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1533);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1062:4: ( 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==36) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1062:6: 'nPhases' ( (lv_nPhases_7_0= ruleEInt ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineGeometry1546); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNPhasesKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1066:1: ( (lv_nPhases_7_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1067:1: (lv_nPhases_7_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1067:1: (lv_nPhases_7_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1068:3: lv_nPhases_7_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNPhasesEIntParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1567);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1090:4: ( 'cond' ( (lv_cond_9_0= ruleEInt ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==37) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1090:6: 'cond' ( (lv_cond_9_0= ruleEInt ) )
                    {
                    match(input,37,FollowSets000.FOLLOW_37_in_ruleLineGeometry1580); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getCondKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1094:1: ( (lv_cond_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1095:1: (lv_cond_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1095:1: (lv_cond_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1096:3: lv_cond_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getCondEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineGeometry1601);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1118:4: ( 'x' ( (lv_x_11_0= ruleEDouble ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==38) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1118:6: 'x' ( (lv_x_11_0= ruleEDouble ) )
                    {
                    match(input,38,FollowSets000.FOLLOW_38_in_ruleLineGeometry1614); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getXKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1122:1: ( (lv_x_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1123:1: (lv_x_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1123:1: (lv_x_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1124:3: lv_x_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getXEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1635);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1146:4: ( 'h' ( (lv_h_13_0= ruleEDouble ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==39) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1146:6: 'h' ( (lv_h_13_0= ruleEDouble ) )
                    {
                    match(input,39,FollowSets000.FOLLOW_39_in_ruleLineGeometry1648); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getHKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1150:1: ( (lv_h_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1151:1: (lv_h_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1151:1: (lv_h_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1152:3: lv_h_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getHEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1669);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1174:4: ( 'units' ( (lv_units_15_0= rulelengthUnit ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==40) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1174:6: 'units' ( (lv_units_15_0= rulelengthUnit ) )
                    {
                    match(input,40,FollowSets000.FOLLOW_40_in_ruleLineGeometry1682); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getUnitsKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1178:1: ( (lv_units_15_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1179:1: (lv_units_15_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1179:1: (lv_units_15_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1180:3: lv_units_15_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getUnitsLengthUnitEnumRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineGeometry1703);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1202:4: ( 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==30) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1202:6: 'normAmps' ( (lv_normAmps_17_0= ruleEDouble ) )
                    {
                    match(input,30,FollowSets000.FOLLOW_30_in_ruleLineGeometry1716); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getNormAmpsKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1206:1: ( (lv_normAmps_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1207:1: (lv_normAmps_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1207:1: (lv_normAmps_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1208:3: lv_normAmps_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getNormAmpsEDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1737);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1230:4: ( 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==31) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1230:6: 'emergAmps' ( (lv_emergAmps_19_0= ruleEDouble ) )
                    {
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleLineGeometry1750); 

                            createLeafNode(grammarAccess.getLineGeometryAccess().getEmergAmpsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1234:1: ( (lv_emergAmps_19_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1235:1: (lv_emergAmps_19_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1235:1: (lv_emergAmps_19_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1236:3: lv_emergAmps_19_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getEmergAmpsEDoubleParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineGeometry1771);
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

            match(input,41,FollowSets000.FOLLOW_41_in_ruleLineGeometry1783); 

                    createLeafNode(grammarAccess.getLineGeometryAccess().getWireKeyword_12(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1262:1: ( ( ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1263:1: ( ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1263:1: ( ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1264:3: ruleEString
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLineGeometryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
             
            	        currentNode=createCompositeNode(grammarAccess.getLineGeometryAccess().getWireWireDataCrossReference_13_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineGeometry1806);
            ruleEString();
            _fsp--;

             
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleLineGeometry1816); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1290:1: entryRuleGrowthShape returns [EObject current=null] : iv_ruleGrowthShape= ruleGrowthShape EOF ;
    public final EObject entryRuleGrowthShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrowthShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1291:2: (iv_ruleGrowthShape= ruleGrowthShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1292:2: iv_ruleGrowthShape= ruleGrowthShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGrowthShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape1852);
            iv_ruleGrowthShape=ruleGrowthShape();
            _fsp--;

             current =iv_ruleGrowthShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleGrowthShape1862); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1299:1: ruleGrowthShape returns [EObject current=null] : ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1304:6: ( ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1305:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1305:1: ( () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1305:2: () 'GrowthShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1305:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1306:5: 
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

            match(input,42,FollowSets000.FOLLOW_42_in_ruleGrowthShape1906); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getGrowthShapeKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleGrowthShape1916); 

                    createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1324:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==43) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1324:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleGrowthShape1927); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1328:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1329:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1329:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1330:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleGrowthShape1948);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1352:4: ( 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==44) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1352:6: 'year' '{' ( (lv_year_7_0= ruleEDouble ) ) ( ',' ( (lv_year_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,44,FollowSets000.FOLLOW_44_in_ruleGrowthShape1961); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getYearKeyword_4_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleGrowthShape1971); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1360:1: ( (lv_year_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1361:1: (lv_year_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1361:1: (lv_year_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1362:3: lv_year_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape1992);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1384:2: ( ',' ( (lv_year_9_0= ruleEDouble ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==14) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1384:4: ',' ( (lv_year_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleGrowthShape2003); 

                    	            createLeafNode(grammarAccess.getGrowthShapeAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1388:1: ( (lv_year_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1389:1: (lv_year_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1389:1: (lv_year_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1390:3: lv_year_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getYearEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleGrowthShape2024);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleGrowthShape2036); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1416:3: ( 'csvFile' ( (lv_csvFile_12_0= ruleEString ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==45) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1416:5: 'csvFile' ( (lv_csvFile_12_0= ruleEString ) )
                    {
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleGrowthShape2049); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getCsvFileKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1420:1: ( (lv_csvFile_12_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1421:1: (lv_csvFile_12_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1421:1: (lv_csvFile_12_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1422:3: lv_csvFile_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getCsvFileEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2070);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1444:4: ( 'sngFile' ( (lv_sngFile_14_0= ruleEString ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==46) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1444:6: 'sngFile' ( (lv_sngFile_14_0= ruleEString ) )
                    {
                    match(input,46,FollowSets000.FOLLOW_46_in_ruleGrowthShape2083); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getSngFileKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1448:1: ( (lv_sngFile_14_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1449:1: (lv_sngFile_14_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1449:1: (lv_sngFile_14_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1450:3: lv_sngFile_14_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getSngFileEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2104);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1472:4: ( 'dblFile' ( (lv_dblFile_16_0= ruleEString ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==47) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1472:6: 'dblFile' ( (lv_dblFile_16_0= ruleEString ) )
                    {
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleGrowthShape2117); 

                            createLeafNode(grammarAccess.getGrowthShapeAccess().getDblFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1476:1: ( (lv_dblFile_16_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1477:1: (lv_dblFile_16_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1477:1: (lv_dblFile_16_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1478:3: lv_dblFile_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrowthShapeAccess().getDblFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleGrowthShape2138);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleGrowthShape2150); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1512:1: entryRuleLineCode returns [EObject current=null] : iv_ruleLineCode= ruleLineCode EOF ;
    public final EObject entryRuleLineCode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLineCode = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1513:2: (iv_ruleLineCode= ruleLineCode EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1514:2: iv_ruleLineCode= ruleLineCode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLineCodeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLineCode_in_entryRuleLineCode2186);
            iv_ruleLineCode=ruleLineCode();
            _fsp--;

             current =iv_ruleLineCode; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLineCode2196); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1521:1: ruleLineCode returns [EObject current=null] : ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1526:6: ( ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1527:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1527:1: ( () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1527:2: () ( (lv_kron_1_0= 'kron' ) )? 'LineCode' '{' ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )? ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )? ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )? ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )? ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )? ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )? ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )? ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )? ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )? ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )? ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )? ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )? ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )? ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )? ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )? ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )? ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )? ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )? ( 'rMatrix' ( ( ruleEString ) ) )? ( 'xMatrix' ( ( ruleEString ) ) )? ( 'cMatrix' ( ( ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1527:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1528:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1538:2: ( (lv_kron_1_0= 'kron' ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==48) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1539:1: (lv_kron_1_0= 'kron' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1539:1: (lv_kron_1_0= 'kron' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1540:3: lv_kron_1_0= 'kron'
                    {
                    lv_kron_1_0=(Token)input.LT(1);
                    match(input,48,FollowSets000.FOLLOW_48_in_ruleLineCode2248); 

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

            match(input,49,FollowSets000.FOLLOW_49_in_ruleLineCode2272); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLineCodeKeyword_2(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleLineCode2282); 

                    createLeafNode(grammarAccess.getLineCodeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1567:1: ( 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==36) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1567:3: 'nPhases' ( (lv_nPhases_5_0= ruleEInt ) )
                    {
                    match(input,36,FollowSets000.FOLLOW_36_in_ruleLineCode2293); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNPhasesKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1571:1: ( (lv_nPhases_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1572:1: (lv_nPhases_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1572:1: (lv_nPhases_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1573:3: lv_nPhases_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNPhasesEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2314);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1595:4: ( 'r1' ( (lv_r1_7_0= ruleEDouble ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==50) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1595:6: 'r1' ( (lv_r1_7_0= ruleEDouble ) )
                    {
                    match(input,50,FollowSets000.FOLLOW_50_in_ruleLineCode2327); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR1Keyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1599:1: ( (lv_r1_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1600:1: (lv_r1_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1600:1: (lv_r1_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1601:3: lv_r1_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR1EDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2348);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1623:4: ( 'x1' ( (lv_x1_9_0= ruleEDouble ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==51) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1623:6: 'x1' ( (lv_x1_9_0= ruleEDouble ) )
                    {
                    match(input,51,FollowSets000.FOLLOW_51_in_ruleLineCode2361); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX1Keyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1627:1: ( (lv_x1_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1628:1: (lv_x1_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1628:1: (lv_x1_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1629:3: lv_x1_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX1EDoubleParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2382);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1651:4: ( 'r0' ( (lv_r0_11_0= ruleEDouble ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==52) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1651:6: 'r0' ( (lv_r0_11_0= ruleEDouble ) )
                    {
                    match(input,52,FollowSets000.FOLLOW_52_in_ruleLineCode2395); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getR0Keyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1655:1: ( (lv_r0_11_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1656:1: (lv_r0_11_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1656:1: (lv_r0_11_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1657:3: lv_r0_11_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getR0EDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2416);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1679:4: ( 'x0' ( (lv_x0_13_0= ruleEDouble ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==53) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1679:6: 'x0' ( (lv_x0_13_0= ruleEDouble ) )
                    {
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleLineCode2429); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getX0Keyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1683:1: ( (lv_x0_13_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1684:1: (lv_x0_13_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1684:1: (lv_x0_13_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1685:3: lv_x0_13_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getX0EDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2450);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1707:4: ( 'c1' ( (lv_c1_15_0= ruleEDouble ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==54) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1707:6: 'c1' ( (lv_c1_15_0= ruleEDouble ) )
                    {
                    match(input,54,FollowSets000.FOLLOW_54_in_ruleLineCode2463); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC1Keyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1711:1: ( (lv_c1_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1712:1: (lv_c1_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1712:1: (lv_c1_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1713:3: lv_c1_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC1EDoubleParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2484);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1735:4: ( 'c0' ( (lv_c0_17_0= ruleEDouble ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==55) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1735:6: 'c0' ( (lv_c0_17_0= ruleEDouble ) )
                    {
                    match(input,55,FollowSets000.FOLLOW_55_in_ruleLineCode2497); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getC0Keyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1739:1: ( (lv_c0_17_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1740:1: (lv_c0_17_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1740:1: (lv_c0_17_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1741:3: lv_c0_17_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getC0EDoubleParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2518);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1763:4: ( 'units' ( (lv_units_19_0= rulelengthUnit ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==40) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1763:6: 'units' ( (lv_units_19_0= rulelengthUnit ) )
                    {
                    match(input,40,FollowSets000.FOLLOW_40_in_ruleLineCode2531); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getUnitsKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1767:1: ( (lv_units_19_0= rulelengthUnit ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1768:1: (lv_units_19_0= rulelengthUnit )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1768:1: (lv_units_19_0= rulelengthUnit )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1769:3: lv_units_19_0= rulelengthUnit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getUnitsLengthUnitEnumRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulelengthUnit_in_ruleLineCode2552);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1791:4: ( 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==56) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1791:6: 'baseFreq' ( (lv_baseFreq_21_0= ruleEDouble ) )
                    {
                    match(input,56,FollowSets000.FOLLOW_56_in_ruleLineCode2565); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getBaseFreqKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1795:1: ( (lv_baseFreq_21_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1796:1: (lv_baseFreq_21_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1796:1: (lv_baseFreq_21_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1797:3: lv_baseFreq_21_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getBaseFreqEDoubleParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2586);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1819:4: ( 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==30) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1819:6: 'normAmps' ( (lv_normAmps_23_0= ruleEDouble ) )
                    {
                    match(input,30,FollowSets000.FOLLOW_30_in_ruleLineCode2599); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNormAmpsKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1823:1: ( (lv_normAmps_23_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1824:1: (lv_normAmps_23_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1824:1: (lv_normAmps_23_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1825:3: lv_normAmps_23_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNormAmpsEDoubleParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2620);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1847:4: ( 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==31) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1847:6: 'emergAmps' ( (lv_emergAmps_25_0= ruleEDouble ) )
                    {
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleLineCode2633); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getEmergAmpsKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1851:1: ( (lv_emergAmps_25_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1852:1: (lv_emergAmps_25_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1852:1: (lv_emergAmps_25_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1853:3: lv_emergAmps_25_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getEmergAmpsEDoubleParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2654);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1875:4: ( 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==57) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1875:6: 'faultRate' ( (lv_faultRate_27_0= ruleEDouble ) )
                    {
                    match(input,57,FollowSets000.FOLLOW_57_in_ruleLineCode2667); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getFaultRateKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1879:1: ( (lv_faultRate_27_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1880:1: (lv_faultRate_27_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1880:1: (lv_faultRate_27_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1881:3: lv_faultRate_27_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getFaultRateEDoubleParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2688);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1903:4: ( 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==58) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1903:6: 'pctPerm' ( (lv_pctPerm_29_0= ruleEInt ) )
                    {
                    match(input,58,FollowSets000.FOLLOW_58_in_ruleLineCode2701); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getPctPermKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1907:1: ( (lv_pctPerm_29_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1908:1: (lv_pctPerm_29_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1908:1: (lv_pctPerm_29_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1909:3: lv_pctPerm_29_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getPctPermEIntParserRuleCall_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2722);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1931:4: ( 'repair' ( (lv_repair_31_0= ruleEInt ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==59) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1931:6: 'repair' ( (lv_repair_31_0= ruleEInt ) )
                    {
                    match(input,59,FollowSets000.FOLLOW_59_in_ruleLineCode2735); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRepairKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1935:1: ( (lv_repair_31_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1936:1: (lv_repair_31_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1936:1: (lv_repair_31_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1937:3: lv_repair_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRepairEIntParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2756);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1959:4: ( 'rg' ( (lv_rg_33_0= ruleEDouble ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==60) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1959:6: 'rg' ( (lv_rg_33_0= ruleEDouble ) )
                    {
                    match(input,60,FollowSets000.FOLLOW_60_in_ruleLineCode2769); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRgKeyword_18_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1963:1: ( (lv_rg_33_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1964:1: (lv_rg_33_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1964:1: (lv_rg_33_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1965:3: lv_rg_33_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRgEDoubleParserRuleCall_18_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2790);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1987:4: ( 'xg' ( (lv_xg_35_0= ruleEDouble ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==61) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1987:6: 'xg' ( (lv_xg_35_0= ruleEDouble ) )
                    {
                    match(input,61,FollowSets000.FOLLOW_61_in_ruleLineCode2803); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXgKeyword_19_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1991:1: ( (lv_xg_35_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1992:1: (lv_xg_35_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1992:1: (lv_xg_35_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:1993:3: lv_xg_35_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXgEDoubleParserRuleCall_19_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2824);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2015:4: ( 'rho' ( (lv_rho_37_0= ruleEDouble ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==62) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2015:6: 'rho' ( (lv_rho_37_0= ruleEDouble ) )
                    {
                    match(input,62,FollowSets000.FOLLOW_62_in_ruleLineCode2837); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRhoKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2019:1: ( (lv_rho_37_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2020:1: (lv_rho_37_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2020:1: (lv_rho_37_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2021:3: lv_rho_37_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRhoEDoubleParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLineCode2858);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2043:4: ( 'neutral' ( (lv_neutral_39_0= ruleEInt ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==63) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2043:6: 'neutral' ( (lv_neutral_39_0= ruleEInt ) )
                    {
                    match(input,63,FollowSets000.FOLLOW_63_in_ruleLineCode2871); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getNeutralKeyword_21_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2047:1: ( (lv_neutral_39_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2048:1: (lv_neutral_39_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2048:1: (lv_neutral_39_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2049:3: lv_neutral_39_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getNeutralEIntParserRuleCall_21_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLineCode2892);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2071:4: ( 'rMatrix' ( ( ruleEString ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==64) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2071:6: 'rMatrix' ( ( ruleEString ) )
                    {
                    match(input,64,FollowSets000.FOLLOW_64_in_ruleLineCode2905); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getRMatrixKeyword_22_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2075:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2076:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2076:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2077:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getRMatrixDoubleMatrix2DCrossReference_22_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode2928);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2091:4: ( 'xMatrix' ( ( ruleEString ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==65) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2091:6: 'xMatrix' ( ( ruleEString ) )
                    {
                    match(input,65,FollowSets000.FOLLOW_65_in_ruleLineCode2941); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getXMatrixKeyword_23_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2095:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2096:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2096:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2097:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getXMatrixDoubleMatrix2DCrossReference_23_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode2964);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2111:4: ( 'cMatrix' ( ( ruleEString ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==66) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2111:6: 'cMatrix' ( ( ruleEString ) )
                    {
                    match(input,66,FollowSets000.FOLLOW_66_in_ruleLineCode2977); 

                            createLeafNode(grammarAccess.getLineCodeAccess().getCMatrixKeyword_24_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2115:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2116:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2116:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2117:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getLineCodeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLineCodeAccess().getCMatrixDoubleMatrix2DCrossReference_24_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLineCode3000);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleLineCode3012); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2143:1: entryRuleLoadShape returns [EObject current=null] : iv_ruleLoadShape= ruleLoadShape EOF ;
    public final EObject entryRuleLoadShape() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadShape = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2144:2: (iv_ruleLoadShape= ruleLoadShape EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2145:2: iv_ruleLoadShape= ruleLoadShape EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLoadShapeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLoadShape_in_entryRuleLoadShape3048);
            iv_ruleLoadShape=ruleLoadShape();
            _fsp--;

             current =iv_ruleLoadShape; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLoadShape3058); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2152:1: ruleLoadShape returns [EObject current=null] : ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2157:6: ( ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2158:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2158:1: ( () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2158:2: () 'LoadShape' '{' ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )? ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )? ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )? ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )? ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )? ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )? ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )? ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )? ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )? ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2158:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2159:5: 
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

            match(input,67,FollowSets000.FOLLOW_67_in_ruleLoadShape3102); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLoadShapeKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleLoadShape3112); 

                    createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2177:1: ( 'nPts' ( (lv_nPts_4_0= ruleEInt ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==43) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2177:3: 'nPts' ( (lv_nPts_4_0= ruleEInt ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleLoadShape3123); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getNPtsKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2181:1: ( (lv_nPts_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2182:1: (lv_nPts_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2182:1: (lv_nPts_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2183:3: lv_nPts_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getNPtsEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape3144);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2205:4: ( 'interval' ( (lv_interval_6_0= ruleEInt ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==68) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2205:6: 'interval' ( (lv_interval_6_0= ruleEInt ) )
                    {
                    match(input,68,FollowSets000.FOLLOW_68_in_ruleLoadShape3157); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getIntervalKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2209:1: ( (lv_interval_6_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2210:1: (lv_interval_6_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2210:1: (lv_interval_6_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2211:3: lv_interval_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getIntervalEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleLoadShape3178);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2233:4: ( 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==69) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2233:6: 'mult' '{' ( (lv_mult_9_0= ruleEDouble ) ) ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,69,FollowSets000.FOLLOW_69_in_ruleLoadShape3191); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMultKeyword_5_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleLoadShape3201); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2241:1: ( (lv_mult_9_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2242:1: (lv_mult_9_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2242:1: (lv_mult_9_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2243:3: lv_mult_9_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3222);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2265:2: ( ',' ( (lv_mult_11_0= ruleEDouble ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==14) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2265:4: ',' ( (lv_mult_11_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleLoadShape3233); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2269:1: ( (lv_mult_11_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2270:1: (lv_mult_11_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2270:1: (lv_mult_11_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2271:3: lv_mult_11_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMultEDoubleParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3254);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleLoadShape3266); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2297:3: ( 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==70) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2297:5: 'hour' '{' ( (lv_hour_15_0= ruleEDouble ) ) ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,70,FollowSets000.FOLLOW_70_in_ruleLoadShape3279); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getHourKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleLoadShape3289); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2305:1: ( (lv_hour_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2306:1: (lv_hour_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2306:1: (lv_hour_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2307:3: lv_hour_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3310);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2329:2: ( ',' ( (lv_hour_17_0= ruleEDouble ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==14) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2329:4: ',' ( (lv_hour_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleLoadShape3321); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2333:1: ( (lv_hour_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2334:1: (lv_hour_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2334:1: (lv_hour_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2335:3: lv_hour_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getHourEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3342);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleLoadShape3354); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2361:3: ( 'mean' ( (lv_mean_20_0= ruleEDouble ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==71) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2361:5: 'mean' ( (lv_mean_20_0= ruleEDouble ) )
                    {
                    match(input,71,FollowSets000.FOLLOW_71_in_ruleLoadShape3367); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getMeanKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2365:1: ( (lv_mean_20_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2366:1: (lv_mean_20_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2366:1: (lv_mean_20_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2367:3: lv_mean_20_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getMeanEDoubleParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3388);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2389:4: ( 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==72) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2389:6: 'stdDev' ( (lv_stdDev_22_0= ruleEDouble ) )
                    {
                    match(input,72,FollowSets000.FOLLOW_72_in_ruleLoadShape3401); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getStdDevKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2393:1: ( (lv_stdDev_22_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2394:1: (lv_stdDev_22_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2394:1: (lv_stdDev_22_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2395:3: lv_stdDev_22_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getStdDevEDoubleParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3422);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2417:4: ( 'csvFile' ( (lv_csvFile_24_0= ruleEString ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==45) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2417:6: 'csvFile' ( (lv_csvFile_24_0= ruleEString ) )
                    {
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleLoadShape3435); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getCsvFileKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2421:1: ( (lv_csvFile_24_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2422:1: (lv_csvFile_24_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2422:1: (lv_csvFile_24_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2423:3: lv_csvFile_24_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getCsvFileEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3456);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2445:4: ( 'sngFile' ( (lv_sngFile_26_0= ruleEString ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==46) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2445:6: 'sngFile' ( (lv_sngFile_26_0= ruleEString ) )
                    {
                    match(input,46,FollowSets000.FOLLOW_46_in_ruleLoadShape3469); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getSngFileKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2449:1: ( (lv_sngFile_26_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2450:1: (lv_sngFile_26_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2450:1: (lv_sngFile_26_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2451:3: lv_sngFile_26_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getSngFileEStringParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3490);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2473:4: ( 'dblFile' ( (lv_dblFile_28_0= ruleEString ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==47) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2473:6: 'dblFile' ( (lv_dblFile_28_0= ruleEString ) )
                    {
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleLoadShape3503); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getDblFileKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2477:1: ( (lv_dblFile_28_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2478:1: (lv_dblFile_28_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2478:1: (lv_dblFile_28_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2479:3: lv_dblFile_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getDblFileEStringParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLoadShape3524);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2501:4: ( 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==73) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2501:6: 'qMult' '{' ( (lv_qMult_31_0= ruleEDouble ) ) ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,73,FollowSets000.FOLLOW_73_in_ruleLoadShape3537); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getQMultKeyword_12_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleLoadShape3547); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2509:1: ( (lv_qMult_31_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2510:1: (lv_qMult_31_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2510:1: (lv_qMult_31_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2511:3: lv_qMult_31_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3568);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2533:2: ( ',' ( (lv_qMult_33_0= ruleEDouble ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==14) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2533:4: ',' ( (lv_qMult_33_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleLoadShape3579); 

                    	            createLeafNode(grammarAccess.getLoadShapeAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2537:1: ( (lv_qMult_33_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2538:1: (lv_qMult_33_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2538:1: (lv_qMult_33_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2539:3: lv_qMult_33_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getLoadShapeAccess().getQMultEDoubleParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleLoadShape3600);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleLoadShape3612); 

                            createLeafNode(grammarAccess.getLoadShapeAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleLoadShape3624); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2577:1: entryRuleSpectrum returns [EObject current=null] : iv_ruleSpectrum= ruleSpectrum EOF ;
    public final EObject entryRuleSpectrum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpectrum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2578:2: (iv_ruleSpectrum= ruleSpectrum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2579:2: iv_ruleSpectrum= ruleSpectrum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSpectrumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleSpectrum_in_entryRuleSpectrum3660);
            iv_ruleSpectrum=ruleSpectrum();
            _fsp--;

             current =iv_ruleSpectrum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleSpectrum3670); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2586:1: ruleSpectrum returns [EObject current=null] : ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2591:6: ( ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2592:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2592:1: ( () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2592:2: () 'Spectrum' '{' ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )? ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )? ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )? ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )? ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2592:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2593:5: 
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

            match(input,74,FollowSets000.FOLLOW_74_in_ruleSpectrum3714); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getSpectrumKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleSpectrum3724); 

                    createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2611:1: ( 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==75) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2611:3: 'nHarm' ( (lv_nHarm_4_0= ruleEInt ) )
                    {
                    match(input,75,FollowSets000.FOLLOW_75_in_ruleSpectrum3735); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getNHarmKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2615:1: ( (lv_nHarm_4_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2616:1: (lv_nHarm_4_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2616:1: (lv_nHarm_4_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2617:3: lv_nHarm_4_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getNHarmEIntParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleSpectrum3756);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2639:4: ( 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==76) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2639:6: 'harmonic' '{' ( (lv_harmonic_7_0= ruleEDouble ) ) ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,76,FollowSets000.FOLLOW_76_in_ruleSpectrum3769); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getHarmonicKeyword_4_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleSpectrum3779); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2647:1: ( (lv_harmonic_7_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2648:1: (lv_harmonic_7_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2648:1: (lv_harmonic_7_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2649:3: lv_harmonic_7_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3800);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2671:2: ( ',' ( (lv_harmonic_9_0= ruleEDouble ) ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==14) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2671:4: ',' ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleSpectrum3811); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2675:1: ( (lv_harmonic_9_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2676:1: (lv_harmonic_9_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2676:1: (lv_harmonic_9_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2677:3: lv_harmonic_9_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getHarmonicEDoubleParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3832);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleSpectrum3844); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2703:3: ( 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==77) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2703:5: 'pctMag' ( (lv_pctMag_12_0= ruleEDouble ) )
                    {
                    match(input,77,FollowSets000.FOLLOW_77_in_ruleSpectrum3857); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getPctMagKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2707:1: ( (lv_pctMag_12_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2708:1: (lv_pctMag_12_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2708:1: (lv_pctMag_12_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2709:3: lv_pctMag_12_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getPctMagEDoubleParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3878);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2731:4: ( 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==78) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2731:6: 'angle' '{' ( (lv_angle_15_0= ruleEDouble ) ) ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )* '}'
                    {
                    match(input,78,FollowSets000.FOLLOW_78_in_ruleSpectrum3891); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getAngleKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleSpectrum3901); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2739:1: ( (lv_angle_15_0= ruleEDouble ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2740:1: (lv_angle_15_0= ruleEDouble )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2740:1: (lv_angle_15_0= ruleEDouble )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2741:3: lv_angle_15_0= ruleEDouble
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3922);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2763:2: ( ',' ( (lv_angle_17_0= ruleEDouble ) ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==14) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2763:4: ',' ( (lv_angle_17_0= ruleEDouble ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleSpectrum3933); 

                    	            createLeafNode(grammarAccess.getSpectrumAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2767:1: ( (lv_angle_17_0= ruleEDouble ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2768:1: (lv_angle_17_0= ruleEDouble )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2768:1: (lv_angle_17_0= ruleEDouble )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2769:3: lv_angle_17_0= ruleEDouble
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getAngleEDoubleParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_ruleSpectrum3954);
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

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleSpectrum3966); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2795:3: ( 'csvFile' ( (lv_csvFile_20_0= ruleEString ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==45) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2795:5: 'csvFile' ( (lv_csvFile_20_0= ruleEString ) )
                    {
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleSpectrum3979); 

                            createLeafNode(grammarAccess.getSpectrumAccess().getCsvFileKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2799:1: ( (lv_csvFile_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2800:1: (lv_csvFile_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2800:1: (lv_csvFile_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2801:3: lv_csvFile_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpectrumAccess().getCsvFileEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleSpectrum4000);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleSpectrum4012); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2835:1: entryRuleExecutive returns [EObject current=null] : iv_ruleExecutive= ruleExecutive EOF ;
    public final EObject entryRuleExecutive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecutive = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2836:2: (iv_ruleExecutive= ruleExecutive EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2837:2: iv_ruleExecutive= ruleExecutive EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExecutiveRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleExecutive_in_entryRuleExecutive4048);
            iv_ruleExecutive=ruleExecutive();
            _fsp--;

             current =iv_ruleExecutive; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExecutive4058); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2844:1: ruleExecutive returns [EObject current=null] : ( () 'Executive' '{' ( 'command' ( (lv_command_4_0= ruleEString ) ) )? ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )? '}' ) ;
    public final EObject ruleExecutive() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_command_4_0 = null;

        AntlrDatatypeRuleToken lv_maxCircuits_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2849:6: ( ( () 'Executive' '{' ( 'command' ( (lv_command_4_0= ruleEString ) ) )? ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2850:1: ( () 'Executive' '{' ( 'command' ( (lv_command_4_0= ruleEString ) ) )? ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2850:1: ( () 'Executive' '{' ( 'command' ( (lv_command_4_0= ruleEString ) ) )? ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2850:2: () 'Executive' '{' ( 'command' ( (lv_command_4_0= ruleEString ) ) )? ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2850:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2851:5: 
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

            match(input,79,FollowSets000.FOLLOW_79_in_ruleExecutive4102); 

                    createLeafNode(grammarAccess.getExecutiveAccess().getExecutiveKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleExecutive4112); 

                    createLeafNode(grammarAccess.getExecutiveAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2869:1: ( 'command' ( (lv_command_4_0= ruleEString ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==80) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2869:3: 'command' ( (lv_command_4_0= ruleEString ) )
                    {
                    match(input,80,FollowSets000.FOLLOW_80_in_ruleExecutive4123); 

                            createLeafNode(grammarAccess.getExecutiveAccess().getCommandKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2873:1: ( (lv_command_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2874:1: (lv_command_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2874:1: (lv_command_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2875:3: lv_command_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExecutiveAccess().getCommandEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleExecutive4144);
                    lv_command_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExecutiveRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"command",
                    	        		lv_command_4_0, 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2897:4: ( 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==81) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2897:6: 'maxCircuits' ( (lv_maxCircuits_6_0= ruleEInt ) )
                    {
                    match(input,81,FollowSets000.FOLLOW_81_in_ruleExecutive4157); 

                            createLeafNode(grammarAccess.getExecutiveAccess().getMaxCircuitsKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2901:1: ( (lv_maxCircuits_6_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2902:1: (lv_maxCircuits_6_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2902:1: (lv_maxCircuits_6_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2903:3: lv_maxCircuits_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExecutiveAccess().getMaxCircuitsEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleExecutive4178);
                    lv_maxCircuits_6_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExecutiveRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"maxCircuits",
                    	        		lv_maxCircuits_6_0, 
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleExecutive4190); 

                    createLeafNode(grammarAccess.getExecutiveAccess().getRightCurlyBracketKeyword_5(), null); 
                

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


    // $ANTLR start entryRuleEString
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2937:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2938:2: (iv_ruleEString= ruleEString EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2939:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString4227);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString4238); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2946:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2951:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2952:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2952:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==RULE_STRING) ) {
                alt85=1;
            }
            else if ( (LA85_0==RULE_ID) ) {
                alt85=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2952:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2952:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString4278); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2960:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString4304); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2975:1: entryRuleEDouble returns [String current=null] : iv_ruleEDouble= ruleEDouble EOF ;
    public final String entryRuleEDouble() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEDouble = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2976:2: (iv_ruleEDouble= ruleEDouble EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2977:2: iv_ruleEDouble= ruleEDouble EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDoubleRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDouble_in_entryRuleEDouble4350);
            iv_ruleEDouble=ruleEDouble();
            _fsp--;

             current =iv_ruleEDouble.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDouble4361); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2984:1: ruleEDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEDouble() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2989:6: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2990:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2990:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2990:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2990:2: (kw= '-' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==82) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2991:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,82,FollowSets000.FOLLOW_82_in_ruleEDouble4400); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2996:3: (this_INT_1= RULE_INT )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==RULE_INT) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:2996:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4418); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1(), null); 
                        

                    }
                    break;

            }

            kw=(Token)input.LT(1);
            match(input,83,FollowSets000.FOLLOW_83_in_ruleEDouble4438); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getEDoubleAccess().getFullStopKeyword_2(), null); 
                
            this_INT_3=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4453); 

            		current.merge(this_INT_3);
                
             
                createLeafNode(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3016:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( ((LA90_0>=84 && LA90_0<=85)) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3016:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3016:2: (kw= 'E' | kw= 'e' )
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==84) ) {
                        alt88=1;
                    }
                    else if ( (LA88_0==85) ) {
                        alt88=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("3016:2: (kw= 'E' | kw= 'e' )", 88, 0, input);

                        throw nvae;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3017:2: kw= 'E'
                            {
                            kw=(Token)input.LT(1);
                            match(input,84,FollowSets000.FOLLOW_84_in_ruleEDouble4473); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3024:2: kw= 'e'
                            {
                            kw=(Token)input.LT(1);
                            match(input,85,FollowSets000.FOLLOW_85_in_ruleEDouble4492); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3029:2: (kw= '-' )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==82) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3030:2: kw= '-'
                            {
                            kw=(Token)input.LT(1);
                            match(input,82,FollowSets000.FOLLOW_82_in_ruleEDouble4507); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1(), null); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)input.LT(1);
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEDouble4524); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3050:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3051:2: (iv_ruleEInt= ruleEInt EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3052:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt4572);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt4583); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3059:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3064:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3065:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3065:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3065:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3065:2: (kw= '-' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==82) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3066:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,82,FollowSets000.FOLLOW_82_in_ruleEInt4622); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt4639); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3086:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3087:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3088:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean4685);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean4696); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3095:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3100:6: ( (kw= 'true' | kw= 'false' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3101:1: (kw= 'true' | kw= 'false' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3101:1: (kw= 'true' | kw= 'false' )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==86) ) {
                alt92=1;
            }
            else if ( (LA92_0==87) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3101:1: (kw= 'true' | kw= 'false' )", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3102:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,86,FollowSets000.FOLLOW_86_in_ruleEBoolean4734); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3109:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,87,FollowSets000.FOLLOW_87_in_ruleEBoolean4753); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3122:1: entryRuleEAnnotation returns [EObject current=null] : iv_ruleEAnnotation= ruleEAnnotation EOF ;
    public final EObject entryRuleEAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAnnotation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3123:2: (iv_ruleEAnnotation= ruleEAnnotation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3124:2: iv_ruleEAnnotation= ruleEAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation4793);
            iv_ruleEAnnotation=ruleEAnnotation();
            _fsp--;

             current =iv_ruleEAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAnnotation4803); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3131:1: ruleEAnnotation returns [EObject current=null] : ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3136:6: ( ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3137:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3137:1: ( () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3137:2: () 'EAnnotation' '{' ( 'source' ( (lv_source_4_0= ruleEString ) ) )? ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )? ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )? ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3137:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3138:5: 
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

            match(input,88,FollowSets000.FOLLOW_88_in_ruleEAnnotation4847); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleEAnnotation4857); 

                    createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3156:1: ( 'source' ( (lv_source_4_0= ruleEString ) ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==89) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3156:3: 'source' ( (lv_source_4_0= ruleEString ) )
                    {
                    match(input,89,FollowSets000.FOLLOW_89_in_ruleEAnnotation4868); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getSourceKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3160:1: ( (lv_source_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3161:1: (lv_source_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3161:1: (lv_source_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3162:3: lv_source_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getSourceEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation4889);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3184:4: ( 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==90) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3184:6: 'references' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,90,FollowSets000.FOLLOW_90_in_ruleEAnnotation4902); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getReferencesKeyword_4_0(), null); 
                        
                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEAnnotation4912); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftParenthesisKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3192:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3193:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3193:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3194:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation4935);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3208:2: ( ',' ( ( ruleEString ) ) )*
                    loop94:
                    do {
                        int alt94=2;
                        int LA94_0 = input.LA(1);

                        if ( (LA94_0==14) ) {
                            alt94=1;
                        }


                        switch (alt94) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3208:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEAnnotation4946); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3212:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3213:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3213:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3214:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEAnnotationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getReferencesEObjectCrossReference_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAnnotation4969);
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

                    match(input,92,FollowSets000.FOLLOW_92_in_ruleEAnnotation4981); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightParenthesisKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3232:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}' )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==93) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3232:5: 'eAnnotations' '{' ( (lv_eAnnotations_13_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEAnnotation4994); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getEAnnotationsKeyword_5_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEAnnotation5004); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3240:1: ( (lv_eAnnotations_13_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3241:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3241:1: (lv_eAnnotations_13_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3242:3: lv_eAnnotations_13_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5025);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3264:2: ( ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) ) )*
                    loop96:
                    do {
                        int alt96=2;
                        int LA96_0 = input.LA(1);

                        if ( (LA96_0==14) ) {
                            alt96=1;
                        }


                        switch (alt96) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3264:4: ',' ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEAnnotation5036); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3268:1: ( (lv_eAnnotations_15_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3269:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3269:1: (lv_eAnnotations_15_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3270:3: lv_eAnnotations_15_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getEAnnotationsEAnnotationParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAnnotation5057);
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
                    	    break loop96;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEAnnotation5069); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3296:3: ( 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}' )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==94) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3296:5: 'details' '{' ( (lv_details_19_0= ruleEStringToStringMapEntry ) ) ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )* '}'
                    {
                    match(input,94,FollowSets000.FOLLOW_94_in_ruleEAnnotation5082); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getDetailsKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEAnnotation5092); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3304:1: ( (lv_details_19_0= ruleEStringToStringMapEntry ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3305:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3305:1: (lv_details_19_0= ruleEStringToStringMapEntry )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3306:3: lv_details_19_0= ruleEStringToStringMapEntry
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5113);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3328:2: ( ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==14) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3328:4: ',' ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEAnnotation5124); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3332:1: ( (lv_details_21_0= ruleEStringToStringMapEntry ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3333:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3333:1: (lv_details_21_0= ruleEStringToStringMapEntry )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3334:3: lv_details_21_0= ruleEStringToStringMapEntry
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getDetailsEStringToStringMapEntryParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5145);
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
                    	    break loop98;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEAnnotation5157); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3360:3: ( 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}' )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==95) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3360:5: 'contents' '{' ( (lv_contents_25_0= ruleEObject ) ) ( ',' ( (lv_contents_27_0= ruleEObject ) ) )* '}'
                    {
                    match(input,95,FollowSets000.FOLLOW_95_in_ruleEAnnotation5170); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getContentsKeyword_7_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEAnnotation5180); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3368:1: ( (lv_contents_25_0= ruleEObject ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3369:1: (lv_contents_25_0= ruleEObject )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3369:1: (lv_contents_25_0= ruleEObject )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3370:3: lv_contents_25_0= ruleEObject
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5201);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3392:2: ( ',' ( (lv_contents_27_0= ruleEObject ) ) )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==14) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3392:4: ',' ( (lv_contents_27_0= ruleEObject ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEAnnotation5212); 

                    	            createLeafNode(grammarAccess.getEAnnotationAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3396:1: ( (lv_contents_27_0= ruleEObject ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3397:1: (lv_contents_27_0= ruleEObject )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3397:1: (lv_contents_27_0= ruleEObject )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3398:3: lv_contents_27_0= ruleEObject
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAnnotationAccess().getContentsEObjectParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEObject_in_ruleEAnnotation5233);
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
                    	    break loop100;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEAnnotation5245); 

                            createLeafNode(grammarAccess.getEAnnotationAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEAnnotation5257); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3436:1: entryRuleETypeParameter returns [EObject current=null] : iv_ruleETypeParameter= ruleETypeParameter EOF ;
    public final EObject entryRuleETypeParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleETypeParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3437:2: (iv_ruleETypeParameter= ruleETypeParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3438:2: iv_ruleETypeParameter= ruleETypeParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getETypeParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5293);
            iv_ruleETypeParameter=ruleETypeParameter();
            _fsp--;

             current =iv_ruleETypeParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleETypeParameter5303); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3445:1: ruleETypeParameter returns [EObject current=null] : ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
    public final EObject ruleETypeParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_eAnnotations_6_0 = null;

        EObject lv_eAnnotations_8_0 = null;

        EObject lv_eBounds_12_0 = null;

        EObject lv_eBounds_14_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3450:6: ( ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3451:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3451:1: ( () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3451:2: () 'ETypeParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )? ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3451:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3452:5: 
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

            match(input,96,FollowSets000.FOLLOW_96_in_ruleETypeParameter5347); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getETypeParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3466:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3467:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3467:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3468:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleETypeParameter5368);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleETypeParameter5378); 

                    createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3494:1: ( 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}' )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==93) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3494:3: 'eAnnotations' '{' ( (lv_eAnnotations_6_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleETypeParameter5389); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEAnnotationsKeyword_4_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleETypeParameter5399); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_4_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3502:1: ( (lv_eAnnotations_6_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3503:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3503:1: (lv_eAnnotations_6_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3504:3: lv_eAnnotations_6_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter5420);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3526:2: ( ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==14) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3526:4: ',' ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleETypeParameter5431); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_4_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3530:1: ( (lv_eAnnotations_8_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3531:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3531:1: (lv_eAnnotations_8_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3532:3: lv_eAnnotations_8_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEAnnotationsEAnnotationParserRuleCall_4_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleETypeParameter5452);
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
                    	    break loop102;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleETypeParameter5464); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_4_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3558:3: ( 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}' )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==97) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3558:5: 'eBounds' '{' ( (lv_eBounds_12_0= ruleEGenericType ) ) ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,97,FollowSets000.FOLLOW_97_in_ruleETypeParameter5477); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getEBoundsKeyword_5_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleETypeParameter5487); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getLeftCurlyBracketKeyword_5_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3566:1: ( (lv_eBounds_12_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3567:1: (lv_eBounds_12_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3567:1: (lv_eBounds_12_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3568:3: lv_eBounds_12_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter5508);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3590:2: ( ',' ( (lv_eBounds_14_0= ruleEGenericType ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==14) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3590:4: ',' ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleETypeParameter5519); 

                    	            createLeafNode(grammarAccess.getETypeParameterAccess().getCommaKeyword_5_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3594:1: ( (lv_eBounds_14_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3595:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3595:1: (lv_eBounds_14_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3596:3: lv_eBounds_14_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getETypeParameterAccess().getEBoundsEGenericTypeParserRuleCall_5_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleETypeParameter5540);
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
                    	    break loop104;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleETypeParameter5552); 

                            createLeafNode(grammarAccess.getETypeParameterAccess().getRightCurlyBracketKeyword_5_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleETypeParameter5564); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3634:1: entryRuleEOperation returns [EObject current=null] : iv_ruleEOperation= ruleEOperation EOF ;
    public final EObject entryRuleEOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEOperation = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3635:2: (iv_ruleEOperation= ruleEOperation EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3636:2: iv_ruleEOperation= ruleEOperation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEOperationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_entryRuleEOperation5600);
            iv_ruleEOperation=ruleEOperation();
            _fsp--;

             current =iv_ruleEOperation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEOperation5610); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3643:1: ruleEOperation returns [EObject current=null] : ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3648:6: ( ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3649:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3649:1: ( () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3649:2: () 'EOperation' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )? ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )? ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3649:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3650:5: 
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

            match(input,98,FollowSets000.FOLLOW_98_in_ruleEOperation5654); 

                    createLeafNode(grammarAccess.getEOperationAccess().getEOperationKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3664:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3665:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3665:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3666:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation5675);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEOperation5685); 

                    createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3692:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==99) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3692:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEOperation5696); 

                            createLeafNode(grammarAccess.getEOperationAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3696:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3697:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3697:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3698:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation5717);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3720:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==100) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3720:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,100,FollowSets000.FOLLOW_100_in_ruleEOperation5730); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3724:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3725:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3725:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3726:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEOperation5751);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3748:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==101) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3748:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,101,FollowSets000.FOLLOW_101_in_ruleEOperation5764); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3752:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3753:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3753:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3754:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation5785);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3776:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==102) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3776:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,102,FollowSets000.FOLLOW_102_in_ruleEOperation5798); 

                            createLeafNode(grammarAccess.getEOperationAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3780:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3781:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3781:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3782:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEOperation5819);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3804:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==103) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3804:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEOperation5832); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3808:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3809:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3809:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3810:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation5855);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3824:4: ( 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==104) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3824:6: 'eExceptions' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,104,FollowSets000.FOLLOW_104_in_ruleEOperation5868); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEExceptionsKeyword_9_0(), null); 
                        
                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEOperation5878); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftParenthesisKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3832:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3833:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3833:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3834:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation5901);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3848:2: ( ',' ( ( ruleEString ) ) )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==14) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3848:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEOperation5912); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3852:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3853:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3853:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3854:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEOperationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEExceptionsEClassifierCrossReference_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEOperation5935);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop111;
                        }
                    } while (true);

                    match(input,92,FollowSets000.FOLLOW_92_in_ruleEOperation5947); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightParenthesisKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3872:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}' )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==93) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3872:5: 'eAnnotations' '{' ( (lv_eAnnotations_22_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEOperation5960); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEAnnotationsKeyword_10_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEOperation5970); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3880:1: ( (lv_eAnnotations_22_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3881:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3881:1: (lv_eAnnotations_22_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3882:3: lv_eAnnotations_22_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation5991);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3904:2: ( ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) ) )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==14) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3904:4: ',' ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEOperation6002); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3908:1: ( (lv_eAnnotations_24_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3909:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3909:1: (lv_eAnnotations_24_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3910:3: lv_eAnnotations_24_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEAnnotationsEAnnotationParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEOperation6023);
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
                    	    break loop113;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEOperation6035); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3936:3: ( 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==105) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3936:5: 'eGenericType' ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEOperation6048); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericTypeKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3940:1: ( (lv_eGenericType_27_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3941:1: (lv_eGenericType_27_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3941:1: (lv_eGenericType_27_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3942:3: lv_eGenericType_27_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericTypeEGenericTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6069);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3964:4: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}' )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==106) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3964:6: 'eTypeParameters' '{' ( (lv_eTypeParameters_30_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEOperation6082); 

                            createLeafNode(grammarAccess.getEOperationAccess().getETypeParametersKeyword_12_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEOperation6092); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3972:1: ( (lv_eTypeParameters_30_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3973:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3973:1: (lv_eTypeParameters_30_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3974:3: lv_eTypeParameters_30_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6113);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3996:2: ( ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) ) )*
                    loop116:
                    do {
                        int alt116=2;
                        int LA116_0 = input.LA(1);

                        if ( (LA116_0==14) ) {
                            alt116=1;
                        }


                        switch (alt116) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:3996:4: ',' ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEOperation6124); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4000:1: ( (lv_eTypeParameters_32_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4001:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4001:1: (lv_eTypeParameters_32_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4002:3: lv_eTypeParameters_32_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getETypeParametersETypeParameterParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEOperation6145);
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
                    	    break loop116;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEOperation6157); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4028:3: ( 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==107) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4028:5: 'eParameters' '{' ( (lv_eParameters_36_0= ruleEParameter ) ) ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )* '}'
                    {
                    match(input,107,FollowSets000.FOLLOW_107_in_ruleEOperation6170); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEParametersKeyword_13_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEOperation6180); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4036:1: ( (lv_eParameters_36_0= ruleEParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4037:1: (lv_eParameters_36_0= ruleEParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4037:1: (lv_eParameters_36_0= ruleEParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4038:3: lv_eParameters_36_0= ruleEParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6201);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4060:2: ( ',' ( (lv_eParameters_38_0= ruleEParameter ) ) )*
                    loop118:
                    do {
                        int alt118=2;
                        int LA118_0 = input.LA(1);

                        if ( (LA118_0==14) ) {
                            alt118=1;
                        }


                        switch (alt118) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4060:4: ',' ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEOperation6212); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4064:1: ( (lv_eParameters_38_0= ruleEParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4065:1: (lv_eParameters_38_0= ruleEParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4065:1: (lv_eParameters_38_0= ruleEParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4066:3: lv_eParameters_38_0= ruleEParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEParametersEParameterParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_ruleEOperation6233);
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
                    	    break loop118;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEOperation6245); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4092:3: ( 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}' )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==108) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4092:5: 'eGenericExceptions' '{' ( (lv_eGenericExceptions_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,108,FollowSets000.FOLLOW_108_in_ruleEOperation6258); 

                            createLeafNode(grammarAccess.getEOperationAccess().getEGenericExceptionsKeyword_14_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEOperation6268); 

                            createLeafNode(grammarAccess.getEOperationAccess().getLeftCurlyBracketKeyword_14_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4100:1: ( (lv_eGenericExceptions_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4101:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4101:1: (lv_eGenericExceptions_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4102:3: lv_eGenericExceptions_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6289);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4124:2: ( ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) ) )*
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==14) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4124:4: ',' ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEOperation6300); 

                    	            createLeafNode(grammarAccess.getEOperationAccess().getCommaKeyword_14_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4128:1: ( (lv_eGenericExceptions_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4129:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4129:1: (lv_eGenericExceptions_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4130:3: lv_eGenericExceptions_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEOperationAccess().getEGenericExceptionsEGenericTypeParserRuleCall_14_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEOperation6321);
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
                    	    break loop120;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEOperation6333); 

                            createLeafNode(grammarAccess.getEOperationAccess().getRightCurlyBracketKeyword_14_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEOperation6345); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4168:1: entryRuleEGenericType returns [EObject current=null] : iv_ruleEGenericType= ruleEGenericType EOF ;
    public final EObject entryRuleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEGenericType = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4169:2: (iv_ruleEGenericType= ruleEGenericType EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4170:2: iv_ruleEGenericType= ruleEGenericType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEGenericTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_entryRuleEGenericType6381);
            iv_ruleEGenericType=ruleEGenericType();
            _fsp--;

             current =iv_ruleEGenericType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEGenericType6391); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4177:1: ruleEGenericType returns [EObject current=null] : ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) ;
    public final EObject ruleEGenericType() throws RecognitionException {
        EObject current = null;

        EObject lv_eUpperBound_8_0 = null;

        EObject lv_eTypeArguments_11_0 = null;

        EObject lv_eTypeArguments_13_0 = null;

        EObject lv_eLowerBound_16_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4182:6: ( ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:1: ( () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:2: () 'EGenericType' '{' ( 'eTypeParameter' ( ( ruleEString ) ) )? ( 'eClassifier' ( ( ruleEString ) ) )? ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )? ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )? ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4183:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4184:5: 
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

            match(input,109,FollowSets000.FOLLOW_109_in_ruleEGenericType6435); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getEGenericTypeKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleEGenericType6445); 

                    createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4202:1: ( 'eTypeParameter' ( ( ruleEString ) ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==110) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4202:3: 'eTypeParameter' ( ( ruleEString ) )
                    {
                    match(input,110,FollowSets000.FOLLOW_110_in_ruleEGenericType6456); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeParameterKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4206:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4207:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4207:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4208:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeParameterETypeParameterCrossReference_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType6479);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4222:4: ( 'eClassifier' ( ( ruleEString ) ) )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==111) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4222:6: 'eClassifier' ( ( ruleEString ) )
                    {
                    match(input,111,FollowSets000.FOLLOW_111_in_ruleEGenericType6492); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEClassifierKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4226:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4227:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4227:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4228:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEGenericTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEClassifierEClassifierCrossReference_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEGenericType6515);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4242:4: ( 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) ) )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==112) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4242:6: 'eUpperBound' ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    {
                    match(input,112,FollowSets000.FOLLOW_112_in_ruleEGenericType6528); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4246:1: ( (lv_eUpperBound_8_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4247:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4247:1: (lv_eUpperBound_8_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4248:3: lv_eUpperBound_8_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getEUpperBoundEGenericTypeParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6549);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4270:4: ( 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==113) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4270:6: 'eTypeArguments' '{' ( (lv_eTypeArguments_11_0= ruleEGenericType ) ) ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,113,FollowSets000.FOLLOW_113_in_ruleEGenericType6562); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEGenericType6572); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4278:1: ( (lv_eTypeArguments_11_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4279:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4279:1: (lv_eTypeArguments_11_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4280:3: lv_eTypeArguments_11_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6593);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4302:2: ( ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==14) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4302:4: ',' ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEGenericType6604); 

                    	            createLeafNode(grammarAccess.getEGenericTypeAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4306:1: ( (lv_eTypeArguments_13_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4307:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4307:1: (lv_eTypeArguments_13_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4308:3: lv_eTypeArguments_13_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getETypeArgumentsEGenericTypeParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6625);
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
                    	    break loop125;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEGenericType6637); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4334:3: ( 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==114) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4334:5: 'eLowerBound' ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    {
                    match(input,114,FollowSets000.FOLLOW_114_in_ruleEGenericType6650); 

                            createLeafNode(grammarAccess.getEGenericTypeAccess().getELowerBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4338:1: ( (lv_eLowerBound_16_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4339:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4339:1: (lv_eLowerBound_16_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4340:3: lv_eLowerBound_16_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEGenericTypeAccess().getELowerBoundEGenericTypeParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEGenericType6671);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEGenericType6683); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4374:1: entryRuleEStringToStringMapEntry returns [EObject current=null] : iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF ;
    public final EObject entryRuleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEStringToStringMapEntry = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4375:2: (iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4376:2: iv_ruleEStringToStringMapEntry= ruleEStringToStringMapEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringToStringMapEntryRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry6719);
            iv_ruleEStringToStringMapEntry=ruleEStringToStringMapEntry();
            _fsp--;

             current =iv_ruleEStringToStringMapEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEStringToStringMapEntry6729); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4383:1: ruleEStringToStringMapEntry returns [EObject current=null] : ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleEStringToStringMapEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_key_4_0 = null;

        AntlrDatatypeRuleToken lv_value_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4388:6: ( ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4389:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4389:1: ( () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4389:2: () 'EStringToStringMapEntry' '{' ( 'key' ( (lv_key_4_0= ruleEString ) ) )? ( 'value' ( (lv_value_6_0= ruleEString ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4389:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4390:5: 
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

            match(input,115,FollowSets000.FOLLOW_115_in_ruleEStringToStringMapEntry6773); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getEStringToStringMapEntryKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleEStringToStringMapEntry6783); 

                    createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4408:1: ( 'key' ( (lv_key_4_0= ruleEString ) ) )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==116) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4408:3: 'key' ( (lv_key_4_0= ruleEString ) )
                    {
                    match(input,116,FollowSets000.FOLLOW_116_in_ruleEStringToStringMapEntry6794); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyKeyword_3_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4412:1: ( (lv_key_4_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4413:1: (lv_key_4_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4413:1: (lv_key_4_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4414:3: lv_key_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getKeyEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry6815);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4436:4: ( 'value' ( (lv_value_6_0= ruleEString ) ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==117) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4436:6: 'value' ( (lv_value_6_0= ruleEString ) )
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEStringToStringMapEntry6828); 

                            createLeafNode(grammarAccess.getEStringToStringMapEntryAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4440:1: ( (lv_value_6_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4441:1: (lv_value_6_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4441:1: (lv_value_6_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4442:3: lv_value_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEStringToStringMapEntryAccess().getValueEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEStringToStringMapEntry6849);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEStringToStringMapEntry6861); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4476:1: entryRuleEClass returns [EObject current=null] : iv_ruleEClass= ruleEClass EOF ;
    public final EObject entryRuleEClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEClass = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4477:2: (iv_ruleEClass= ruleEClass EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4478:2: iv_ruleEClass= ruleEClass EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEClassRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEClass_in_entryRuleEClass6897);
            iv_ruleEClass=ruleEClass();
            _fsp--;

             current =iv_ruleEClass; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEClass6907); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4485:1: ruleEClass returns [EObject current=null] : ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4490:6: ( ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4491:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4491:1: ( () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4491:2: () ( (lv_abstract_1_0= 'abstract' ) )? ( (lv_interface_2_0= 'interface' ) )? 'EClass' ( (lv_name_4_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )? ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )? ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )? ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )? ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4491:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4492:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4502:2: ( (lv_abstract_1_0= 'abstract' ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==118) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4503:1: (lv_abstract_1_0= 'abstract' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4503:1: (lv_abstract_1_0= 'abstract' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4504:3: lv_abstract_1_0= 'abstract'
                    {
                    lv_abstract_1_0=(Token)input.LT(1);
                    match(input,118,FollowSets000.FOLLOW_118_in_ruleEClass6959); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4523:3: ( (lv_interface_2_0= 'interface' ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==119) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4524:1: (lv_interface_2_0= 'interface' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4524:1: (lv_interface_2_0= 'interface' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4525:3: lv_interface_2_0= 'interface'
                    {
                    lv_interface_2_0=(Token)input.LT(1);
                    match(input,119,FollowSets000.FOLLOW_119_in_ruleEClass6991); 

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

            match(input,120,FollowSets000.FOLLOW_120_in_ruleEClass7015); 

                    createLeafNode(grammarAccess.getEClassAccess().getEClassKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4548:1: ( (lv_name_4_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4549:1: (lv_name_4_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4549:1: (lv_name_4_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4550:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7036);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7046); 

                    createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4576:1: ( 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) ) )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==121) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4576:3: 'instanceClassName' ( (lv_instanceClassName_7_0= ruleEString ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEClass7057); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceClassNameKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4580:1: ( (lv_instanceClassName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4581:1: (lv_instanceClassName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4581:1: (lv_instanceClassName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4582:3: lv_instanceClassName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceClassNameEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7078);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4604:4: ( 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) ) )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==122) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4604:6: 'instanceTypeName' ( (lv_instanceTypeName_9_0= ruleEString ) )
                    {
                    match(input,122,FollowSets000.FOLLOW_122_in_ruleEClass7091); 

                            createLeafNode(grammarAccess.getEClassAccess().getInstanceTypeNameKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4608:1: ( (lv_instanceTypeName_9_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4609:1: (lv_instanceTypeName_9_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4609:1: (lv_instanceTypeName_9_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4610:3: lv_instanceTypeName_9_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getInstanceTypeNameEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7112);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4632:4: ( 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==123) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4632:6: 'eSuperTypes' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,123,FollowSets000.FOLLOW_123_in_ruleEClass7125); 

                            createLeafNode(grammarAccess.getEClassAccess().getESuperTypesKeyword_8_0(), null); 
                        
                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEClass7135); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftParenthesisKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4640:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4641:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4641:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4642:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7158);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4656:2: ( ',' ( ( ruleEString ) ) )*
                    loop134:
                    do {
                        int alt134=2;
                        int LA134_0 = input.LA(1);

                        if ( (LA134_0==14) ) {
                            alt134=1;
                        }


                        switch (alt134) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4656:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7169); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4660:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4661:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4661:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4662:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEClassRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getESuperTypesEClassCrossReference_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEClass7192);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop134;
                        }
                    } while (true);

                    match(input,92,FollowSets000.FOLLOW_92_in_ruleEClass7204); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightParenthesisKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4680:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}' )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==93) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4680:5: 'eAnnotations' '{' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEClass7217); 

                            createLeafNode(grammarAccess.getEClassAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7227); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4688:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4689:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4689:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4690:3: lv_eAnnotations_18_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7248);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4712:2: ( ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) ) )*
                    loop136:
                    do {
                        int alt136=2;
                        int LA136_0 = input.LA(1);

                        if ( (LA136_0==14) ) {
                            alt136=1;
                        }


                        switch (alt136) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4712:4: ',' ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7259); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4716:1: ( (lv_eAnnotations_20_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4717:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4717:1: (lv_eAnnotations_20_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4718:3: lv_eAnnotations_20_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEClass7280);
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
                    	    break loop136;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7292); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4744:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}' )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==106) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4744:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_24_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEClass7305); 

                            createLeafNode(grammarAccess.getEClassAccess().getETypeParametersKeyword_10_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7315); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_10_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4752:1: ( (lv_eTypeParameters_24_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4753:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4753:1: (lv_eTypeParameters_24_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4754:3: lv_eTypeParameters_24_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7336);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4776:2: ( ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) ) )*
                    loop138:
                    do {
                        int alt138=2;
                        int LA138_0 = input.LA(1);

                        if ( (LA138_0==14) ) {
                            alt138=1;
                        }


                        switch (alt138) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4776:4: ',' ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7347); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_10_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4780:1: ( (lv_eTypeParameters_26_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4781:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4781:1: (lv_eTypeParameters_26_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4782:3: lv_eTypeParameters_26_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getETypeParametersETypeParameterParserRuleCall_10_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEClass7368);
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
                    	    break loop138;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7380); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_10_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4808:3: ( 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}' )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==124) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4808:5: 'eOperations' '{' ( (lv_eOperations_30_0= ruleEOperation ) ) ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )* '}'
                    {
                    match(input,124,FollowSets000.FOLLOW_124_in_ruleEClass7393); 

                            createLeafNode(grammarAccess.getEClassAccess().getEOperationsKeyword_11_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7403); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_11_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4816:1: ( (lv_eOperations_30_0= ruleEOperation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4817:1: (lv_eOperations_30_0= ruleEOperation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4817:1: (lv_eOperations_30_0= ruleEOperation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4818:3: lv_eOperations_30_0= ruleEOperation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass7424);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4840:2: ( ',' ( (lv_eOperations_32_0= ruleEOperation ) ) )*
                    loop140:
                    do {
                        int alt140=2;
                        int LA140_0 = input.LA(1);

                        if ( (LA140_0==14) ) {
                            alt140=1;
                        }


                        switch (alt140) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4840:4: ',' ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7435); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_11_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4844:1: ( (lv_eOperations_32_0= ruleEOperation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4845:1: (lv_eOperations_32_0= ruleEOperation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4845:1: (lv_eOperations_32_0= ruleEOperation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4846:3: lv_eOperations_32_0= ruleEOperation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEOperationsEOperationParserRuleCall_11_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEOperation_in_ruleEClass7456);
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
                    	    break loop140;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7468); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_11_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4872:3: ( 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}' )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==125) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4872:5: 'eStructuralFeatures' '{' ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) ) ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )* '}'
                    {
                    match(input,125,FollowSets000.FOLLOW_125_in_ruleEClass7481); 

                            createLeafNode(grammarAccess.getEClassAccess().getEStructuralFeaturesKeyword_12_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7491); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4880:1: ( (lv_eStructuralFeatures_36_0= ruleEStructuralFeature ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4881:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4881:1: (lv_eStructuralFeatures_36_0= ruleEStructuralFeature )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4882:3: lv_eStructuralFeatures_36_0= ruleEStructuralFeature
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass7512);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4904:2: ( ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) ) )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==14) ) {
                            alt142=1;
                        }


                        switch (alt142) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4904:4: ',' ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7523); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_12_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4908:1: ( (lv_eStructuralFeatures_38_0= ruleEStructuralFeature ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4909:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4909:1: (lv_eStructuralFeatures_38_0= ruleEStructuralFeature )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4910:3: lv_eStructuralFeatures_38_0= ruleEStructuralFeature
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEStructuralFeaturesEStructuralFeatureParserRuleCall_12_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEStructuralFeature_in_ruleEClass7544);
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
                    	    break loop142;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7556); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_12_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4936:3: ( 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}' )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==126) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4936:5: 'eGenericSuperTypes' '{' ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) ) ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )* '}'
                    {
                    match(input,126,FollowSets000.FOLLOW_126_in_ruleEClass7569); 

                            createLeafNode(grammarAccess.getEClassAccess().getEGenericSuperTypesKeyword_13_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEClass7579); 

                            createLeafNode(grammarAccess.getEClassAccess().getLeftCurlyBracketKeyword_13_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4944:1: ( (lv_eGenericSuperTypes_42_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4945:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4945:1: (lv_eGenericSuperTypes_42_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4946:3: lv_eGenericSuperTypes_42_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass7600);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4968:2: ( ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) ) )*
                    loop144:
                    do {
                        int alt144=2;
                        int LA144_0 = input.LA(1);

                        if ( (LA144_0==14) ) {
                            alt144=1;
                        }


                        switch (alt144) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4968:4: ',' ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEClass7611); 

                    	            createLeafNode(grammarAccess.getEClassAccess().getCommaKeyword_13_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4972:1: ( (lv_eGenericSuperTypes_44_0= ruleEGenericType ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4973:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4973:1: (lv_eGenericSuperTypes_44_0= ruleEGenericType )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:4974:3: lv_eGenericSuperTypes_44_0= ruleEGenericType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEClassAccess().getEGenericSuperTypesEGenericTypeParserRuleCall_13_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEClass7632);
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
                    	    break loop144;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7644); 

                            createLeafNode(grammarAccess.getEClassAccess().getRightCurlyBracketKeyword_13_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEClass7656); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5012:1: entryRuleEObject returns [EObject current=null] : iv_ruleEObject= ruleEObject EOF ;
    public final EObject entryRuleEObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEObject = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5013:2: (iv_ruleEObject= ruleEObject EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5014:2: iv_ruleEObject= ruleEObject EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEObjectRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEObject_in_entryRuleEObject7692);
            iv_ruleEObject=ruleEObject();
            _fsp--;

             current =iv_ruleEObject; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEObject7702); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5021:1: ruleEObject returns [EObject current=null] : ( () 'EObject' ) ;
    public final EObject ruleEObject() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5026:6: ( ( () 'EObject' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5027:1: ( () 'EObject' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5027:1: ( () 'EObject' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5027:2: () 'EObject'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5027:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5028:5: 
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

            match(input,127,FollowSets000.FOLLOW_127_in_ruleEObject7746); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5050:1: entryRuleEParameter returns [EObject current=null] : iv_ruleEParameter= ruleEParameter EOF ;
    public final EObject entryRuleEParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEParameter = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5051:2: (iv_ruleEParameter= ruleEParameter EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5052:2: iv_ruleEParameter= ruleEParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEParameterRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEParameter_in_entryRuleEParameter7782);
            iv_ruleEParameter=ruleEParameter();
            _fsp--;

             current =iv_ruleEParameter; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEParameter7792); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5059:1: ruleEParameter returns [EObject current=null] : ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5064:6: ( ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5065:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5065:1: ( () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5065:2: () 'EParameter' ( (lv_name_2_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5065:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5066:5: 
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

            match(input,128,FollowSets000.FOLLOW_128_in_ruleEParameter7836); 

                    createLeafNode(grammarAccess.getEParameterAccess().getEParameterKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5080:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5081:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5081:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5082:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter7857);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEParameter7867); 

                    createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5108:1: ( 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==99) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5108:3: 'ordered' ( (lv_ordered_5_0= ruleEBoolean ) )
                    {
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEParameter7878); 

                            createLeafNode(grammarAccess.getEParameterAccess().getOrderedKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5112:1: ( (lv_ordered_5_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5113:1: (lv_ordered_5_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5113:1: (lv_ordered_5_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5114:3: lv_ordered_5_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getOrderedEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter7899);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5136:4: ( 'unique' ( (lv_unique_7_0= ruleEBoolean ) ) )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==100) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5136:6: 'unique' ( (lv_unique_7_0= ruleEBoolean ) )
                    {
                    match(input,100,FollowSets000.FOLLOW_100_in_ruleEParameter7912); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUniqueKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5140:1: ( (lv_unique_7_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5141:1: (lv_unique_7_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5141:1: (lv_unique_7_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5142:3: lv_unique_7_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUniqueEBooleanParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEParameter7933);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5164:4: ( 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) ) )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==101) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5164:6: 'lowerBound' ( (lv_lowerBound_9_0= ruleEInt ) )
                    {
                    match(input,101,FollowSets000.FOLLOW_101_in_ruleEParameter7946); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLowerBoundKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5168:1: ( (lv_lowerBound_9_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5169:1: (lv_lowerBound_9_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5169:1: (lv_lowerBound_9_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5170:3: lv_lowerBound_9_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getLowerBoundEIntParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter7967);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5192:4: ( 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) ) )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==102) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5192:6: 'upperBound' ( (lv_upperBound_11_0= ruleEInt ) )
                    {
                    match(input,102,FollowSets000.FOLLOW_102_in_ruleEParameter7980); 

                            createLeafNode(grammarAccess.getEParameterAccess().getUpperBoundKeyword_7_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5196:1: ( (lv_upperBound_11_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5197:1: (lv_upperBound_11_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5197:1: (lv_upperBound_11_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5198:3: lv_upperBound_11_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getUpperBoundEIntParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEParameter8001);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5220:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==103) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5220:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEParameter8014); 

                            createLeafNode(grammarAccess.getEParameterAccess().getETypeKeyword_8_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5224:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5225:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5225:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5226:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEParameterRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getETypeEClassifierCrossReference_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEParameter8037);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5240:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}' )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==93) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5240:6: 'eAnnotations' '{' ( (lv_eAnnotations_16_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEParameter8050); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEAnnotationsKeyword_9_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEParameter8060); 

                            createLeafNode(grammarAccess.getEParameterAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5248:1: ( (lv_eAnnotations_16_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5249:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5249:1: (lv_eAnnotations_16_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5250:3: lv_eAnnotations_16_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8081);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5272:2: ( ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) ) )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==14) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5272:4: ',' ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEParameter8092); 

                    	            createLeafNode(grammarAccess.getEParameterAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5276:1: ( (lv_eAnnotations_18_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5277:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5277:1: (lv_eAnnotations_18_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5278:3: lv_eAnnotations_18_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEAnnotationsEAnnotationParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEParameter8113);
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
                    	    break loop151;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEParameter8125); 

                            createLeafNode(grammarAccess.getEParameterAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5304:3: ( 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==105) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5304:5: 'eGenericType' ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEParameter8138); 

                            createLeafNode(grammarAccess.getEParameterAccess().getEGenericTypeKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5308:1: ( (lv_eGenericType_21_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5309:1: (lv_eGenericType_21_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5309:1: (lv_eGenericType_21_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5310:3: lv_eGenericType_21_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEParameterAccess().getEGenericTypeEGenericTypeParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEParameter8159);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEParameter8171); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5344:1: entryRuleEDataType_Impl returns [EObject current=null] : iv_ruleEDataType_Impl= ruleEDataType_Impl EOF ;
    public final EObject entryRuleEDataType_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEDataType_Impl = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5345:2: (iv_ruleEDataType_Impl= ruleEDataType_Impl EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5346:2: iv_ruleEDataType_Impl= ruleEDataType_Impl EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEDataType_ImplRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8207);
            iv_ruleEDataType_Impl=ruleEDataType_Impl();
            _fsp--;

             current =iv_ruleEDataType_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEDataType_Impl8217); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5353:1: ruleEDataType_Impl returns [EObject current=null] : ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5358:6: ( ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5359:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5359:1: ( () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5359:2: () 'EDataType' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5359:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5360:5: 
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

            match(input,129,FollowSets000.FOLLOW_129_in_ruleEDataType_Impl8261); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getEDataTypeKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5374:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5375:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5375:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5376:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8282);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEDataType_Impl8292); 

                    createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5402:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==121) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5402:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEDataType_Impl8303); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5406:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5407:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5407:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5408:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8324);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5430:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==122) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5430:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,122,FollowSets000.FOLLOW_122_in_ruleEDataType_Impl8337); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5434:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5435:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5435:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5436:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEDataType_Impl8358);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5458:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==130) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5458:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,130,FollowSets000.FOLLOW_130_in_ruleEDataType_Impl8371); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5462:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5463:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5463:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5464:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEDataType_Impl8392);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5486:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==93) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5486:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEDataType_Impl8405); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEDataType_Impl8415); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5494:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5495:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5495:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5496:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8436);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5518:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop157:
                    do {
                        int alt157=2;
                        int LA157_0 = input.LA(1);

                        if ( (LA157_0==14) ) {
                            alt157=1;
                        }


                        switch (alt157) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5518:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEDataType_Impl8447); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5522:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5523:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5523:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5524:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8468);
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
                    	    break loop157;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEDataType_Impl8480); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5550:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==106) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5550:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEDataType_Impl8493); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEDataType_Impl8503); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5558:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5559:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5559:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5560:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8524);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5582:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop159:
                    do {
                        int alt159=2;
                        int LA159_0 = input.LA(1);

                        if ( (LA159_0==14) ) {
                            alt159=1;
                        }


                        switch (alt159) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5582:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEDataType_Impl8535); 

                    	            createLeafNode(grammarAccess.getEDataType_ImplAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5586:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5587:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5587:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5588:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEDataType_ImplAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8556);
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
                    	    break loop159;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEDataType_Impl8568); 

                            createLeafNode(grammarAccess.getEDataType_ImplAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEDataType_Impl8580); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5626:1: entryRuleEEnum returns [EObject current=null] : iv_ruleEEnum= ruleEEnum EOF ;
    public final EObject entryRuleEEnum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnum = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5627:2: (iv_ruleEEnum= ruleEEnum EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5628:2: iv_ruleEEnum= ruleEEnum EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnum_in_entryRuleEEnum8616);
            iv_ruleEEnum=ruleEEnum();
            _fsp--;

             current =iv_ruleEEnum; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnum8626); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5635:1: ruleEEnum returns [EObject current=null] : ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5640:6: ( ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5641:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5641:1: ( () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5641:2: () 'EEnum' ( (lv_name_2_0= ruleEString ) ) '{' ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )? ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )? ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )? ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )? ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5641:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5642:5: 
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

            match(input,131,FollowSets000.FOLLOW_131_in_ruleEEnum8670); 

                    createLeafNode(grammarAccess.getEEnumAccess().getEEnumKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5656:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5657:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5657:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5658:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8691);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnum8701); 

                    createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5684:1: ( 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==121) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5684:3: 'instanceClassName' ( (lv_instanceClassName_5_0= ruleEString ) )
                    {
                    match(input,121,FollowSets000.FOLLOW_121_in_ruleEEnum8712); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceClassNameKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5688:1: ( (lv_instanceClassName_5_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5689:1: (lv_instanceClassName_5_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5689:1: (lv_instanceClassName_5_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5690:3: lv_instanceClassName_5_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceClassNameEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8733);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5712:4: ( 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) ) )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==122) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5712:6: 'instanceTypeName' ( (lv_instanceTypeName_7_0= ruleEString ) )
                    {
                    match(input,122,FollowSets000.FOLLOW_122_in_ruleEEnum8746); 

                            createLeafNode(grammarAccess.getEEnumAccess().getInstanceTypeNameKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5716:1: ( (lv_instanceTypeName_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5717:1: (lv_instanceTypeName_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5717:1: (lv_instanceTypeName_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5718:3: lv_instanceTypeName_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getInstanceTypeNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnum8767);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5740:4: ( 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) ) )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==130) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5740:6: 'serializable' ( (lv_serializable_9_0= ruleEBoolean ) )
                    {
                    match(input,130,FollowSets000.FOLLOW_130_in_ruleEEnum8780); 

                            createLeafNode(grammarAccess.getEEnumAccess().getSerializableKeyword_6_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5744:1: ( (lv_serializable_9_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5745:1: (lv_serializable_9_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5745:1: (lv_serializable_9_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5746:3: lv_serializable_9_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getSerializableEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEEnum8801);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5768:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}' )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==93) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5768:6: 'eAnnotations' '{' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEEnum8814); 

                            createLeafNode(grammarAccess.getEEnumAccess().getEAnnotationsKeyword_7_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnum8824); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_7_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5776:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5777:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5777:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5778:3: lv_eAnnotations_12_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum8845);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5800:2: ( ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) ) )*
                    loop164:
                    do {
                        int alt164=2;
                        int LA164_0 = input.LA(1);

                        if ( (LA164_0==14) ) {
                            alt164=1;
                        }


                        switch (alt164) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5800:4: ',' ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEEnum8856); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_7_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5804:1: ( (lv_eAnnotations_14_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5805:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5805:1: (lv_eAnnotations_14_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5806:3: lv_eAnnotations_14_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getEAnnotationsEAnnotationParserRuleCall_7_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnum8877);
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
                    	    break loop164;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnum8889); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_7_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5832:3: ( 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}' )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==106) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5832:5: 'eTypeParameters' '{' ( (lv_eTypeParameters_18_0= ruleETypeParameter ) ) ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )* '}'
                    {
                    match(input,106,FollowSets000.FOLLOW_106_in_ruleEEnum8902); 

                            createLeafNode(grammarAccess.getEEnumAccess().getETypeParametersKeyword_8_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnum8912); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_8_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5840:1: ( (lv_eTypeParameters_18_0= ruleETypeParameter ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5841:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5841:1: (lv_eTypeParameters_18_0= ruleETypeParameter )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5842:3: lv_eTypeParameters_18_0= ruleETypeParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum8933);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5864:2: ( ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) ) )*
                    loop166:
                    do {
                        int alt166=2;
                        int LA166_0 = input.LA(1);

                        if ( (LA166_0==14) ) {
                            alt166=1;
                        }


                        switch (alt166) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5864:4: ',' ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEEnum8944); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_8_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5868:1: ( (lv_eTypeParameters_20_0= ruleETypeParameter ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5869:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5869:1: (lv_eTypeParameters_20_0= ruleETypeParameter )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5870:3: lv_eTypeParameters_20_0= ruleETypeParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getETypeParametersETypeParameterParserRuleCall_8_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleETypeParameter_in_ruleEEnum8965);
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
                    	    break loop166;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnum8977); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_8_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5896:3: ( 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}' )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==132) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5896:5: 'eLiterals' '{' ( (lv_eLiterals_24_0= ruleEEnumLiteral ) ) ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )* '}'
                    {
                    match(input,132,FollowSets000.FOLLOW_132_in_ruleEEnum8990); 

                            createLeafNode(grammarAccess.getEEnumAccess().getELiteralsKeyword_9_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnum9000); 

                            createLeafNode(grammarAccess.getEEnumAccess().getLeftCurlyBracketKeyword_9_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5904:1: ( (lv_eLiterals_24_0= ruleEEnumLiteral ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5905:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5905:1: (lv_eLiterals_24_0= ruleEEnumLiteral )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5906:3: lv_eLiterals_24_0= ruleEEnumLiteral
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9021);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5928:2: ( ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) ) )*
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==14) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5928:4: ',' ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEEnum9032); 

                    	            createLeafNode(grammarAccess.getEEnumAccess().getCommaKeyword_9_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5932:1: ( (lv_eLiterals_26_0= ruleEEnumLiteral ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5933:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5933:1: (lv_eLiterals_26_0= ruleEEnumLiteral )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5934:3: lv_eLiterals_26_0= ruleEEnumLiteral
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumAccess().getELiteralsEEnumLiteralParserRuleCall_9_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_ruleEEnum9053);
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
                    	    break loop168;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnum9065); 

                            createLeafNode(grammarAccess.getEEnumAccess().getRightCurlyBracketKeyword_9_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnum9077); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5972:1: entryRuleEEnumLiteral returns [EObject current=null] : iv_ruleEEnumLiteral= ruleEEnumLiteral EOF ;
    public final EObject entryRuleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEEnumLiteral = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5973:2: (iv_ruleEEnumLiteral= ruleEEnumLiteral EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5974:2: iv_ruleEEnumLiteral= ruleEEnumLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEEnumLiteralRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9113);
            iv_ruleEEnumLiteral=ruleEEnumLiteral();
            _fsp--;

             current =iv_ruleEEnumLiteral; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEEnumLiteral9123); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5981:1: ruleEEnumLiteral returns [EObject current=null] : ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) ;
    public final EObject ruleEEnumLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        AntlrDatatypeRuleToken lv_literal_7_0 = null;

        EObject lv_eAnnotations_10_0 = null;

        EObject lv_eAnnotations_12_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5986:6: ( ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5987:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5987:1: ( () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5987:2: () 'EEnumLiteral' ( (lv_name_2_0= ruleEString ) ) '{' ( 'value' ( (lv_value_5_0= ruleEInt ) ) )? ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5987:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:5988:5: 
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

            match(input,133,FollowSets000.FOLLOW_133_in_ruleEEnumLiteral9167); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getEEnumLiteralKeyword_1(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6002:1: ( (lv_name_2_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6003:1: (lv_name_2_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6003:1: (lv_name_2_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6004:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9188);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnumLiteral9198); 

                    createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6030:1: ( 'value' ( (lv_value_5_0= ruleEInt ) ) )?
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==117) ) {
                alt170=1;
            }
            switch (alt170) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6030:3: 'value' ( (lv_value_5_0= ruleEInt ) )
                    {
                    match(input,117,FollowSets000.FOLLOW_117_in_ruleEEnumLiteral9209); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getValueKeyword_4_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6034:1: ( (lv_value_5_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6035:1: (lv_value_5_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6035:1: (lv_value_5_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6036:3: lv_value_5_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getValueEIntParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEEnumLiteral9230);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6058:4: ( 'literal' ( (lv_literal_7_0= ruleEString ) ) )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==134) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6058:6: 'literal' ( (lv_literal_7_0= ruleEString ) )
                    {
                    match(input,134,FollowSets000.FOLLOW_134_in_ruleEEnumLiteral9243); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLiteralKeyword_5_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6062:1: ( (lv_literal_7_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6063:1: (lv_literal_7_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6063:1: (lv_literal_7_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6064:3: lv_literal_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getLiteralEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEEnumLiteral9264);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6086:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}' )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==93) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6086:6: 'eAnnotations' '{' ( (lv_eAnnotations_10_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEEnumLiteral9277); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsKeyword_6_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEEnumLiteral9287); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getLeftCurlyBracketKeyword_6_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6094:1: ( (lv_eAnnotations_10_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6095:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6095:1: (lv_eAnnotations_10_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6096:3: lv_eAnnotations_10_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9308);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6118:2: ( ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) ) )*
                    loop172:
                    do {
                        int alt172=2;
                        int LA172_0 = input.LA(1);

                        if ( (LA172_0==14) ) {
                            alt172=1;
                        }


                        switch (alt172) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6118:4: ',' ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEEnumLiteral9319); 

                    	            createLeafNode(grammarAccess.getEEnumLiteralAccess().getCommaKeyword_6_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6122:1: ( (lv_eAnnotations_12_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6123:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6123:1: (lv_eAnnotations_12_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6124:3: lv_eAnnotations_12_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEEnumLiteralAccess().getEAnnotationsEAnnotationParserRuleCall_6_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9340);
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
                    	    break loop172;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnumLiteral9352); 

                            createLeafNode(grammarAccess.getEEnumLiteralAccess().getRightCurlyBracketKeyword_6_4(), null); 
                        

                    }
                    break;

            }

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEEnumLiteral9364); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6162:1: entryRuleEAttribute returns [EObject current=null] : iv_ruleEAttribute= ruleEAttribute EOF ;
    public final EObject entryRuleEAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEAttribute = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6163:2: (iv_ruleEAttribute= ruleEAttribute EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6164:2: iv_ruleEAttribute= ruleEAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEAttributeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEAttribute_in_entryRuleEAttribute9400);
            iv_ruleEAttribute=ruleEAttribute();
            _fsp--;

             current =iv_ruleEAttribute; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEAttribute9410); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6171:1: ruleEAttribute returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6176:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6177:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6177:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6177:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_iD_5_0= 'iD' ) )? 'EAttribute' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6177:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6178:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6188:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==135) ) {
                alt174=1;
            }
            switch (alt174) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6189:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6189:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6190:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,135,FollowSets000.FOLLOW_135_in_ruleEAttribute9462); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6209:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==136) ) {
                alt175=1;
            }
            switch (alt175) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6210:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6210:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6211:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,136,FollowSets000.FOLLOW_136_in_ruleEAttribute9494); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6230:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt176=2;
            int LA176_0 = input.LA(1);

            if ( (LA176_0==137) ) {
                alt176=1;
            }
            switch (alt176) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6231:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6231:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6232:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,137,FollowSets000.FOLLOW_137_in_ruleEAttribute9526); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6251:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==138) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6252:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6252:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6253:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,138,FollowSets000.FOLLOW_138_in_ruleEAttribute9558); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6272:3: ( (lv_iD_5_0= 'iD' ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==139) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6273:1: (lv_iD_5_0= 'iD' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6273:1: (lv_iD_5_0= 'iD' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6274:3: lv_iD_5_0= 'iD'
                    {
                    lv_iD_5_0=(Token)input.LT(1);
                    match(input,139,FollowSets000.FOLLOW_139_in_ruleEAttribute9590); 

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

            match(input,140,FollowSets000.FOLLOW_140_in_ruleEAttribute9614); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getEAttributeKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6297:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6298:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6299:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute9635);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEAttribute9645); 

                    createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6325:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==99) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6325:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEAttribute9656); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6329:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6330:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6330:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6331:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute9677);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6353:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==100) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6353:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,100,FollowSets000.FOLLOW_100_in_ruleEAttribute9690); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6357:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6358:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6358:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6359:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute9711);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6381:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==101) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6381:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,101,FollowSets000.FOLLOW_101_in_ruleEAttribute9724); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6385:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6386:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6386:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6387:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute9745);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6409:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==102) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6409:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,102,FollowSets000.FOLLOW_102_in_ruleEAttribute9758); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6413:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6414:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6414:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6415:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEAttribute9779);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6437:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==141) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6437:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEAttribute9792); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6441:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6442:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6442:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6443:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEAttribute9813);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6465:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==142) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6465:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,142,FollowSets000.FOLLOW_142_in_ruleEAttribute9826); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6469:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6470:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6470:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6471:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute9847);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6493:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==103) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6493:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEAttribute9860); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getETypeKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6497:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6498:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6498:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6499:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getETypeEClassifierCrossReference_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEAttribute9883);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6513:4: ( 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}' )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==93) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6513:6: 'eAnnotations' '{' ( (lv_eAnnotations_25_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEAttribute9896); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEAnnotationsKeyword_16_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEAttribute9906); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getLeftCurlyBracketKeyword_16_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6521:1: ( (lv_eAnnotations_25_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6522:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6522:1: (lv_eAnnotations_25_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6523:3: lv_eAnnotations_25_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute9927);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6545:2: ( ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) ) )*
                    loop186:
                    do {
                        int alt186=2;
                        int LA186_0 = input.LA(1);

                        if ( (LA186_0==14) ) {
                            alt186=1;
                        }


                        switch (alt186) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6545:4: ',' ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEAttribute9938); 

                    	            createLeafNode(grammarAccess.getEAttributeAccess().getCommaKeyword_16_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6549:1: ( (lv_eAnnotations_27_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6550:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6550:1: (lv_eAnnotations_27_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6551:3: lv_eAnnotations_27_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEAnnotationsEAnnotationParserRuleCall_16_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEAttribute9959);
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
                    	    break loop186;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEAttribute9971); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getRightCurlyBracketKeyword_16_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6577:3: ( 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==105) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6577:5: 'eGenericType' ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEAttribute9984); 

                            createLeafNode(grammarAccess.getEAttributeAccess().getEGenericTypeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6581:1: ( (lv_eGenericType_30_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6582:1: (lv_eGenericType_30_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6582:1: (lv_eGenericType_30_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6583:3: lv_eGenericType_30_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEAttributeAccess().getEGenericTypeEGenericTypeParserRuleCall_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEAttribute10005);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEAttribute10017); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6617:1: entryRuleEReference returns [EObject current=null] : iv_ruleEReference= ruleEReference EOF ;
    public final EObject entryRuleEReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEReference = null;


        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6618:2: (iv_ruleEReference= ruleEReference EOF )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6619:2: iv_ruleEReference= ruleEReference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEReferenceRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEReference_in_entryRuleEReference10053);
            iv_ruleEReference=ruleEReference();
            _fsp--;

             current =iv_ruleEReference; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEReference10063); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6626:1: ruleEReference returns [EObject current=null] : ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) ;
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
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6631:6: ( ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6632:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6632:1: ( () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}' )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6632:2: () ( (lv_volatile_1_0= 'volatile' ) )? ( (lv_transient_2_0= 'transient' ) )? ( (lv_unsettable_3_0= 'unsettable' ) )? ( (lv_derived_4_0= 'derived' ) )? ( (lv_containment_5_0= 'containment' ) )? 'EReference' ( (lv_name_7_0= ruleEString ) ) '{' ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )? ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )? ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )? ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )? ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )? ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )? ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )? ( 'eType' ( ( ruleEString ) ) )? ( 'eOpposite' ( ( ruleEString ) ) )? ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )? ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )? ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )? '}'
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6632:2: ()
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6633:5: 
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6643:2: ( (lv_volatile_1_0= 'volatile' ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==135) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6644:1: (lv_volatile_1_0= 'volatile' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6644:1: (lv_volatile_1_0= 'volatile' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6645:3: lv_volatile_1_0= 'volatile'
                    {
                    lv_volatile_1_0=(Token)input.LT(1);
                    match(input,135,FollowSets000.FOLLOW_135_in_ruleEReference10115); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6664:3: ( (lv_transient_2_0= 'transient' ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==136) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6665:1: (lv_transient_2_0= 'transient' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6665:1: (lv_transient_2_0= 'transient' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6666:3: lv_transient_2_0= 'transient'
                    {
                    lv_transient_2_0=(Token)input.LT(1);
                    match(input,136,FollowSets000.FOLLOW_136_in_ruleEReference10147); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6685:3: ( (lv_unsettable_3_0= 'unsettable' ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==137) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6686:1: (lv_unsettable_3_0= 'unsettable' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6686:1: (lv_unsettable_3_0= 'unsettable' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6687:3: lv_unsettable_3_0= 'unsettable'
                    {
                    lv_unsettable_3_0=(Token)input.LT(1);
                    match(input,137,FollowSets000.FOLLOW_137_in_ruleEReference10179); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6706:3: ( (lv_derived_4_0= 'derived' ) )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==138) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6707:1: (lv_derived_4_0= 'derived' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6707:1: (lv_derived_4_0= 'derived' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6708:3: lv_derived_4_0= 'derived'
                    {
                    lv_derived_4_0=(Token)input.LT(1);
                    match(input,138,FollowSets000.FOLLOW_138_in_ruleEReference10211); 

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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6727:3: ( (lv_containment_5_0= 'containment' ) )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==143) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6728:1: (lv_containment_5_0= 'containment' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6728:1: (lv_containment_5_0= 'containment' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6729:3: lv_containment_5_0= 'containment'
                    {
                    lv_containment_5_0=(Token)input.LT(1);
                    match(input,143,FollowSets000.FOLLOW_143_in_ruleEReference10243); 

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

            match(input,144,FollowSets000.FOLLOW_144_in_ruleEReference10267); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getEReferenceKeyword_6(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6752:1: ( (lv_name_7_0= ruleEString ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6753:1: (lv_name_7_0= ruleEString )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6753:1: (lv_name_7_0= ruleEString )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6754:3: lv_name_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getNameEStringParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10288);
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

            match(input,12,FollowSets000.FOLLOW_12_in_ruleEReference10298); 

                    createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_8(), null); 
                
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6780:1: ( 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) ) )?
            int alt194=2;
            int LA194_0 = input.LA(1);

            if ( (LA194_0==99) ) {
                alt194=1;
            }
            switch (alt194) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6780:3: 'ordered' ( (lv_ordered_10_0= ruleEBoolean ) )
                    {
                    match(input,99,FollowSets000.FOLLOW_99_in_ruleEReference10309); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getOrderedKeyword_9_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6784:1: ( (lv_ordered_10_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6785:1: (lv_ordered_10_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6785:1: (lv_ordered_10_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6786:3: lv_ordered_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getOrderedEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10330);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6808:4: ( 'unique' ( (lv_unique_12_0= ruleEBoolean ) ) )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==100) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6808:6: 'unique' ( (lv_unique_12_0= ruleEBoolean ) )
                    {
                    match(input,100,FollowSets000.FOLLOW_100_in_ruleEReference10343); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUniqueKeyword_10_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6812:1: ( (lv_unique_12_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6813:1: (lv_unique_12_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6813:1: (lv_unique_12_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6814:3: lv_unique_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUniqueEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10364);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6836:4: ( 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==101) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6836:6: 'lowerBound' ( (lv_lowerBound_14_0= ruleEInt ) )
                    {
                    match(input,101,FollowSets000.FOLLOW_101_in_ruleEReference10377); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLowerBoundKeyword_11_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6840:1: ( (lv_lowerBound_14_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6841:1: (lv_lowerBound_14_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6841:1: (lv_lowerBound_14_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6842:3: lv_lowerBound_14_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getLowerBoundEIntParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference10398);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6864:4: ( 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) ) )?
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==102) ) {
                alt197=1;
            }
            switch (alt197) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6864:6: 'upperBound' ( (lv_upperBound_16_0= ruleEInt ) )
                    {
                    match(input,102,FollowSets000.FOLLOW_102_in_ruleEReference10411); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getUpperBoundKeyword_12_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6868:1: ( (lv_upperBound_16_0= ruleEInt ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6869:1: (lv_upperBound_16_0= ruleEInt )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6869:1: (lv_upperBound_16_0= ruleEInt )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6870:3: lv_upperBound_16_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getUpperBoundEIntParserRuleCall_12_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleEReference10432);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6892:4: ( 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) ) )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==141) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6892:6: 'changeable' ( (lv_changeable_18_0= ruleEBoolean ) )
                    {
                    match(input,141,FollowSets000.FOLLOW_141_in_ruleEReference10445); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getChangeableKeyword_13_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6896:1: ( (lv_changeable_18_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6897:1: (lv_changeable_18_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6897:1: (lv_changeable_18_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6898:3: lv_changeable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getChangeableEBooleanParserRuleCall_13_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10466);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6920:4: ( 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==142) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6920:6: 'defaultValueLiteral' ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    {
                    match(input,142,FollowSets000.FOLLOW_142_in_ruleEReference10479); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralKeyword_14_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6924:1: ( (lv_defaultValueLiteral_20_0= ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6925:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6925:1: (lv_defaultValueLiteral_20_0= ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6926:3: lv_defaultValueLiteral_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getDefaultValueLiteralEStringParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10500);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6948:4: ( 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==145) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6948:6: 'resolveProxies' ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    {
                    match(input,145,FollowSets000.FOLLOW_145_in_ruleEReference10513); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getResolveProxiesKeyword_15_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6952:1: ( (lv_resolveProxies_22_0= ruleEBoolean ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6953:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6953:1: (lv_resolveProxies_22_0= ruleEBoolean )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6954:3: lv_resolveProxies_22_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getResolveProxiesEBooleanParserRuleCall_15_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleEReference10534);
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

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6976:4: ( 'eType' ( ( ruleEString ) ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==103) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6976:6: 'eType' ( ( ruleEString ) )
                    {
                    match(input,103,FollowSets000.FOLLOW_103_in_ruleEReference10547); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getETypeKeyword_16_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6980:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6981:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6981:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6982:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getETypeEClassifierCrossReference_16_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10570);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6996:4: ( 'eOpposite' ( ( ruleEString ) ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==146) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:6996:6: 'eOpposite' ( ( ruleEString ) )
                    {
                    match(input,146,FollowSets000.FOLLOW_146_in_ruleEReference10583); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEOppositeKeyword_17_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7000:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7001:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7001:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7002:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEOppositeEReferenceCrossReference_17_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10606);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7016:4: ( 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')' )?
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==147) ) {
                alt204=1;
            }
            switch (alt204) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7016:6: 'eKeys' '(' ( ( ruleEString ) ) ( ',' ( ( ruleEString ) ) )* ')'
                    {
                    match(input,147,FollowSets000.FOLLOW_147_in_ruleEReference10619); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEKeysKeyword_18_0(), null); 
                        
                    match(input,91,FollowSets000.FOLLOW_91_in_ruleEReference10629); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftParenthesisKeyword_18_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7024:1: ( ( ruleEString ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7025:1: ( ruleEString )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7025:1: ( ruleEString )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7026:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10652);
                    ruleEString();
                    _fsp--;

                     
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7040:2: ( ',' ( ( ruleEString ) ) )*
                    loop203:
                    do {
                        int alt203=2;
                        int LA203_0 = input.LA(1);

                        if ( (LA203_0==14) ) {
                            alt203=1;
                        }


                        switch (alt203) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7040:4: ',' ( ( ruleEString ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEReference10663); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_18_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7044:1: ( ( ruleEString ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7045:1: ( ruleEString )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7045:1: ( ruleEString )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7046:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEReferenceRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	            
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEKeysEAttributeCrossReference_18_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleEReference10686);
                    	    ruleEString();
                    	    _fsp--;

                    	     
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop203;
                        }
                    } while (true);

                    match(input,92,FollowSets000.FOLLOW_92_in_ruleEReference10698); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightParenthesisKeyword_18_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:3: ( 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}' )?
            int alt206=2;
            int LA206_0 = input.LA(1);

            if ( (LA206_0==93) ) {
                alt206=1;
            }
            switch (alt206) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7064:5: 'eAnnotations' '{' ( (lv_eAnnotations_35_0= ruleEAnnotation ) ) ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )* '}'
                    {
                    match(input,93,FollowSets000.FOLLOW_93_in_ruleEReference10711); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEAnnotationsKeyword_19_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleEReference10721); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getLeftCurlyBracketKeyword_19_1(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7072:1: ( (lv_eAnnotations_35_0= ruleEAnnotation ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7073:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7073:1: (lv_eAnnotations_35_0= ruleEAnnotation )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7074:3: lv_eAnnotations_35_0= ruleEAnnotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference10742);
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

                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7096:2: ( ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) ) )*
                    loop205:
                    do {
                        int alt205=2;
                        int LA205_0 = input.LA(1);

                        if ( (LA205_0==14) ) {
                            alt205=1;
                        }


                        switch (alt205) {
                    	case 1 :
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7096:4: ',' ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleEReference10753); 

                    	            createLeafNode(grammarAccess.getEReferenceAccess().getCommaKeyword_19_3_0(), null); 
                    	        
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7100:1: ( (lv_eAnnotations_37_0= ruleEAnnotation ) )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7101:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    {
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7101:1: (lv_eAnnotations_37_0= ruleEAnnotation )
                    	    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7102:3: lv_eAnnotations_37_0= ruleEAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEAnnotationsEAnnotationParserRuleCall_19_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEAnnotation_in_ruleEReference10774);
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
                    	    break loop205;
                        }
                    } while (true);

                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEReference10786); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getRightCurlyBracketKeyword_19_4(), null); 
                        

                    }
                    break;

            }

            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7128:3: ( 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) ) )?
            int alt207=2;
            int LA207_0 = input.LA(1);

            if ( (LA207_0==105) ) {
                alt207=1;
            }
            switch (alt207) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7128:5: 'eGenericType' ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    {
                    match(input,105,FollowSets000.FOLLOW_105_in_ruleEReference10799); 

                            createLeafNode(grammarAccess.getEReferenceAccess().getEGenericTypeKeyword_20_0(), null); 
                        
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7132:1: ( (lv_eGenericType_40_0= ruleEGenericType ) )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7133:1: (lv_eGenericType_40_0= ruleEGenericType )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7133:1: (lv_eGenericType_40_0= ruleEGenericType )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7134:3: lv_eGenericType_40_0= ruleEGenericType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEReferenceAccess().getEGenericTypeEGenericTypeParserRuleCall_20_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEGenericType_in_ruleEReference10820);
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

            match(input,15,FollowSets000.FOLLOW_15_in_ruleEReference10832); 

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
    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7168:1: rulelengthUnit returns [Enumerator current=null] : ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) ;
    public final Enumerator rulelengthUnit() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7172:6: ( ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) ) )
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7173:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            {
            // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7173:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )
            int alt208=9;
            switch ( input.LA(1) ) {
            case 148:
                {
                alt208=1;
                }
                break;
            case 149:
                {
                alt208=2;
                }
                break;
            case 150:
                {
                alt208=3;
                }
                break;
            case 151:
                {
                alt208=4;
                }
                break;
            case 152:
                {
                alt208=5;
                }
                break;
            case 153:
                {
                alt208=6;
                }
                break;
            case 154:
                {
                alt208=7;
                }
                break;
            case 155:
                {
                alt208=8;
                }
                break;
            case 156:
                {
                alt208=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("7173:1: ( ( 'none' ) | ( 'mi' ) | ( 'km' ) | ( 'kft' ) | ( 'm' ) | ( 'me' ) | ( 'ft' ) | ( 'in' ) | ( 'cm' ) )", 208, 0, input);

                throw nvae;
            }

            switch (alt208) {
                case 1 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7173:2: ( 'none' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7173:2: ( 'none' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7173:4: 'none'
                    {
                    match(input,148,FollowSets000.FOLLOW_148_in_rulelengthUnit10880); 

                            current = grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getNoneEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7179:6: ( 'mi' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7179:6: ( 'mi' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7179:8: 'mi'
                    {
                    match(input,149,FollowSets000.FOLLOW_149_in_rulelengthUnit10895); 

                            current = grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMiEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7185:6: ( 'km' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7185:6: ( 'km' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7185:8: 'km'
                    {
                    match(input,150,FollowSets000.FOLLOW_150_in_rulelengthUnit10910); 

                            current = grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKmEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7191:6: ( 'kft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7191:6: ( 'kft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7191:8: 'kft'
                    {
                    match(input,151,FollowSets000.FOLLOW_151_in_rulelengthUnit10925); 

                            current = grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getKftEnumLiteralDeclaration_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7197:6: ( 'm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7197:6: ( 'm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7197:8: 'm'
                    {
                    match(input,152,FollowSets000.FOLLOW_152_in_rulelengthUnit10940); 

                            current = grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMEnumLiteralDeclaration_4(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7203:6: ( 'me' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7203:6: ( 'me' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7203:8: 'me'
                    {
                    match(input,153,FollowSets000.FOLLOW_153_in_rulelengthUnit10955); 

                            current = grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getMeEnumLiteralDeclaration_5(), null); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7209:6: ( 'ft' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7209:6: ( 'ft' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7209:8: 'ft'
                    {
                    match(input,154,FollowSets000.FOLLOW_154_in_rulelengthUnit10970); 

                            current = grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getFtEnumLiteralDeclaration_6(), null); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7215:6: ( 'in' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7215:6: ( 'in' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7215:8: 'in'
                    {
                    match(input,155,FollowSets000.FOLLOW_155_in_rulelengthUnit10985); 

                            current = grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getLengthUnitAccess().getInEnumLiteralDeclaration_7(), null); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7221:6: ( 'cm' )
                    {
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7221:6: ( 'cm' )
                    // ../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g:7221:8: 'cm'
                    {
                    match(input,156,FollowSets000.FOLLOW_156_in_rulelengthUnit11000); 

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
        public static final BitSet FOLLOW_11_in_ruleElectrickery129 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery139 = new BitSet(new long[]{0x00000000003FA000L});
        public static final BitSet FOLLOW_13_in_ruleElectrickery150 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery160 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_ruleWireData_in_ruleElectrickery181 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery192 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_ruleWireData_in_ruleElectrickery213 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery225 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_16_in_ruleElectrickery238 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery248 = new BitSet(new long[]{0x0000000600000000L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_ruleElectrickery269 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery280 = new BitSet(new long[]{0x0000000600000000L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_ruleElectrickery301 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery313 = new BitSet(new long[]{0x00000000003E8000L});
        public static final BitSet FOLLOW_17_in_ruleElectrickery326 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery336 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_ruleElectrickery357 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery368 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_ruleElectrickery389 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery401 = new BitSet(new long[]{0x00000000003C8000L});
        public static final BitSet FOLLOW_18_in_ruleElectrickery414 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery424 = new BitSet(new long[]{0x0003000000000000L});
        public static final BitSet FOLLOW_ruleLineCode_in_ruleElectrickery445 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery456 = new BitSet(new long[]{0x0003000000000000L});
        public static final BitSet FOLLOW_ruleLineCode_in_ruleElectrickery477 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery489 = new BitSet(new long[]{0x0000000000388000L});
        public static final BitSet FOLLOW_19_in_ruleElectrickery502 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleLoadShape_in_ruleElectrickery533 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery544 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleLoadShape_in_ruleElectrickery565 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery577 = new BitSet(new long[]{0x0000000000308000L});
        public static final BitSet FOLLOW_20_in_ruleElectrickery590 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_ruleSpectrum_in_ruleElectrickery621 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_ruleSpectrum_in_ruleElectrickery653 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery665 = new BitSet(new long[]{0x0000000000208000L});
        public static final BitSet FOLLOW_21_in_ruleElectrickery678 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleElectrickery688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_ruleExecutive_in_ruleElectrickery709 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleElectrickery720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_ruleExecutive_in_ruleElectrickery741 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery753 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleElectrickery765 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_entryRuleEStructuralFeature801 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStructuralFeature811 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_ruleEStructuralFeature858 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_ruleEStructuralFeature885 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWireData_in_entryRuleWireData922 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleWireData932 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleWireData976 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWireData997 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleWireData1007 = new BitSet(new long[]{0x00000001FF808000L});
        public static final BitSet FOLLOW_23_in_ruleWireData1018 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1039 = new BitSet(new long[]{0x00000001FF008000L});
        public static final BitSet FOLLOW_24_in_ruleWireData1052 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1073 = new BitSet(new long[]{0x00000001FE008000L});
        public static final BitSet FOLLOW_25_in_ruleWireData1086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000001FF00000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1107 = new BitSet(new long[]{0x00000001FC008000L});
        public static final BitSet FOLLOW_26_in_ruleWireData1120 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1141 = new BitSet(new long[]{0x00000001F8008000L});
        public static final BitSet FOLLOW_27_in_ruleWireData1154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000001FF00000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1175 = new BitSet(new long[]{0x00000001F0008000L});
        public static final BitSet FOLLOW_28_in_ruleWireData1188 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1209 = new BitSet(new long[]{0x00000001E0008000L});
        public static final BitSet FOLLOW_29_in_ruleWireData1222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000001FF00000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleWireData1243 = new BitSet(new long[]{0x00000001C0008000L});
        public static final BitSet FOLLOW_30_in_ruleWireData1256 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1277 = new BitSet(new long[]{0x0000000180008000L});
        public static final BitSet FOLLOW_31_in_ruleWireData1290 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1311 = new BitSet(new long[]{0x0000000100008000L});
        public static final BitSet FOLLOW_32_in_ruleWireData1324 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleWireData1345 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleWireData1357 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineGeometry_in_entryRuleLineGeometry1393 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineGeometry1403 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_33_in_ruleLineGeometry1446 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_34_in_ruleLineGeometry1470 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry1491 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLineGeometry1501 = new BitSet(new long[]{0x000003F8C0000000L});
        public static final BitSet FOLLOW_35_in_ruleLineGeometry1512 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1533 = new BitSet(new long[]{0x000003F0C0000000L});
        public static final BitSet FOLLOW_36_in_ruleLineGeometry1546 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1567 = new BitSet(new long[]{0x000003E0C0000000L});
        public static final BitSet FOLLOW_37_in_ruleLineGeometry1580 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineGeometry1601 = new BitSet(new long[]{0x000003C0C0000000L});
        public static final BitSet FOLLOW_38_in_ruleLineGeometry1614 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1635 = new BitSet(new long[]{0x00000380C0000000L});
        public static final BitSet FOLLOW_39_in_ruleLineGeometry1648 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1669 = new BitSet(new long[]{0x00000300C0000000L});
        public static final BitSet FOLLOW_40_in_ruleLineGeometry1682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000001FF00000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineGeometry1703 = new BitSet(new long[]{0x00000200C0000000L});
        public static final BitSet FOLLOW_30_in_ruleLineGeometry1716 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1737 = new BitSet(new long[]{0x0000020080000000L});
        public static final BitSet FOLLOW_31_in_ruleLineGeometry1750 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineGeometry1771 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_41_in_ruleLineGeometry1783 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineGeometry1806 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleLineGeometry1816 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleGrowthShape_in_entryRuleGrowthShape1852 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleGrowthShape1862 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_42_in_ruleGrowthShape1906 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleGrowthShape1916 = new BitSet(new long[]{0x0000F80000008000L});
        public static final BitSet FOLLOW_43_in_ruleGrowthShape1927 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleGrowthShape1948 = new BitSet(new long[]{0x0000F00000008000L});
        public static final BitSet FOLLOW_44_in_ruleGrowthShape1961 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleGrowthShape1971 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape1992 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleGrowthShape2003 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleGrowthShape2024 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleGrowthShape2036 = new BitSet(new long[]{0x0000E00000008000L});
        public static final BitSet FOLLOW_45_in_ruleGrowthShape2049 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2070 = new BitSet(new long[]{0x0000C00000008000L});
        public static final BitSet FOLLOW_46_in_ruleGrowthShape2083 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2104 = new BitSet(new long[]{0x0000800000008000L});
        public static final BitSet FOLLOW_47_in_ruleGrowthShape2117 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleGrowthShape2138 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleGrowthShape2150 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLineCode_in_entryRuleLineCode2186 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLineCode2196 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_48_in_ruleLineCode2248 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_49_in_ruleLineCode2272 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLineCode2282 = new BitSet(new long[]{0xFFFC0110C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_36_in_ruleLineCode2293 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2314 = new BitSet(new long[]{0xFFFC0100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_50_in_ruleLineCode2327 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2348 = new BitSet(new long[]{0xFFF80100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_51_in_ruleLineCode2361 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2382 = new BitSet(new long[]{0xFFF00100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_52_in_ruleLineCode2395 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2416 = new BitSet(new long[]{0xFFE00100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_53_in_ruleLineCode2429 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2450 = new BitSet(new long[]{0xFFC00100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_54_in_ruleLineCode2463 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2484 = new BitSet(new long[]{0xFF800100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_55_in_ruleLineCode2497 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2518 = new BitSet(new long[]{0xFF000100C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_40_in_ruleLineCode2531 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000001FF00000L});
        public static final BitSet FOLLOW_rulelengthUnit_in_ruleLineCode2552 = new BitSet(new long[]{0xFF000000C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_56_in_ruleLineCode2565 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2586 = new BitSet(new long[]{0xFE000000C0008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_30_in_ruleLineCode2599 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2620 = new BitSet(new long[]{0xFE00000080008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_31_in_ruleLineCode2633 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2654 = new BitSet(new long[]{0xFE00000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_57_in_ruleLineCode2667 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2688 = new BitSet(new long[]{0xFC00000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_58_in_ruleLineCode2701 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2722 = new BitSet(new long[]{0xF800000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_59_in_ruleLineCode2735 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2756 = new BitSet(new long[]{0xF000000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_60_in_ruleLineCode2769 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2790 = new BitSet(new long[]{0xE000000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_61_in_ruleLineCode2803 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2824 = new BitSet(new long[]{0xC000000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_62_in_ruleLineCode2837 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLineCode2858 = new BitSet(new long[]{0x8000000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_63_in_ruleLineCode2871 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLineCode2892 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000007L});
        public static final BitSet FOLLOW_64_in_ruleLineCode2905 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode2928 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000006L});
        public static final BitSet FOLLOW_65_in_ruleLineCode2941 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode2964 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000004L});
        public static final BitSet FOLLOW_66_in_ruleLineCode2977 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLineCode3000 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleLineCode3012 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLoadShape_in_entryRuleLoadShape3048 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLoadShape3058 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleLoadShape3102 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLoadShape3112 = new BitSet(new long[]{0x0000E80000008000L,0x00000000000003F0L});
        public static final BitSet FOLLOW_43_in_ruleLoadShape3123 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape3144 = new BitSet(new long[]{0x0000E00000008000L,0x00000000000003F0L});
        public static final BitSet FOLLOW_68_in_ruleLoadShape3157 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleLoadShape3178 = new BitSet(new long[]{0x0000E00000008000L,0x00000000000003E0L});
        public static final BitSet FOLLOW_69_in_ruleLoadShape3191 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLoadShape3201 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3222 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleLoadShape3233 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3254 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleLoadShape3266 = new BitSet(new long[]{0x0000E00000008000L,0x00000000000003C0L});
        public static final BitSet FOLLOW_70_in_ruleLoadShape3279 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLoadShape3289 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3310 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleLoadShape3321 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3342 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleLoadShape3354 = new BitSet(new long[]{0x0000E00000008000L,0x0000000000000380L});
        public static final BitSet FOLLOW_71_in_ruleLoadShape3367 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3388 = new BitSet(new long[]{0x0000E00000008000L,0x0000000000000300L});
        public static final BitSet FOLLOW_72_in_ruleLoadShape3401 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3422 = new BitSet(new long[]{0x0000E00000008000L,0x0000000000000200L});
        public static final BitSet FOLLOW_45_in_ruleLoadShape3435 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3456 = new BitSet(new long[]{0x0000C00000008000L,0x0000000000000200L});
        public static final BitSet FOLLOW_46_in_ruleLoadShape3469 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3490 = new BitSet(new long[]{0x0000800000008000L,0x0000000000000200L});
        public static final BitSet FOLLOW_47_in_ruleLoadShape3503 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLoadShape3524 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleLoadShape3537 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleLoadShape3547 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3568 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleLoadShape3579 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleLoadShape3600 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleLoadShape3612 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleLoadShape3624 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleSpectrum_in_entryRuleSpectrum3660 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleSpectrum3670 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleSpectrum3714 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleSpectrum3724 = new BitSet(new long[]{0x0000200000008000L,0x0000000000007800L});
        public static final BitSet FOLLOW_75_in_ruleSpectrum3735 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleSpectrum3756 = new BitSet(new long[]{0x0000200000008000L,0x0000000000007000L});
        public static final BitSet FOLLOW_76_in_ruleSpectrum3769 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleSpectrum3779 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3800 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleSpectrum3811 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3832 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleSpectrum3844 = new BitSet(new long[]{0x0000200000008000L,0x0000000000006000L});
        public static final BitSet FOLLOW_77_in_ruleSpectrum3857 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3878 = new BitSet(new long[]{0x0000200000008000L,0x0000000000004000L});
        public static final BitSet FOLLOW_78_in_ruleSpectrum3891 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleSpectrum3901 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3922 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleSpectrum3933 = new BitSet(new long[]{0x0000000000000040L,0x00000000000C0000L});
        public static final BitSet FOLLOW_ruleEDouble_in_ruleSpectrum3954 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleSpectrum3966 = new BitSet(new long[]{0x0000200000008000L});
        public static final BitSet FOLLOW_45_in_ruleSpectrum3979 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleSpectrum4000 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleSpectrum4012 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExecutive_in_entryRuleExecutive4048 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExecutive4058 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_79_in_ruleExecutive4102 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleExecutive4112 = new BitSet(new long[]{0x0000000000008000L,0x0000000000030000L});
        public static final BitSet FOLLOW_80_in_ruleExecutive4123 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleExecutive4144 = new BitSet(new long[]{0x0000000000008000L,0x0000000000020000L});
        public static final BitSet FOLLOW_81_in_ruleExecutive4157 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleExecutive4178 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleExecutive4190 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString4227 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString4238 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString4278 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString4304 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDouble_in_entryRuleEDouble4350 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDouble4361 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleEDouble4400 = new BitSet(new long[]{0x0000000000000040L,0x0000000000080000L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleEDouble4438 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4453 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
        public static final BitSet FOLLOW_84_in_ruleEDouble4473 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_85_in_ruleEDouble4492 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_82_in_ruleEDouble4507 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEDouble4524 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt4572 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt4583 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleEInt4622 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt4639 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean4685 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean4696 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleEBoolean4734 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleEBoolean4753 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_entryRuleEAnnotation4793 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAnnotation4803 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_88_in_ruleEAnnotation4847 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAnnotation4857 = new BitSet(new long[]{0x0000000000008000L,0x00000000E6000000L});
        public static final BitSet FOLLOW_89_in_ruleEAnnotation4868 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation4889 = new BitSet(new long[]{0x0000000000008000L,0x00000000E4000000L});
        public static final BitSet FOLLOW_90_in_ruleEAnnotation4902 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEAnnotation4912 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation4935 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_14_in_ruleEAnnotation4946 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAnnotation4969 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleEAnnotation4981 = new BitSet(new long[]{0x0000000000008000L,0x00000000E0000000L});
        public static final BitSet FOLLOW_93_in_ruleEAnnotation4994 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAnnotation5004 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5025 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEAnnotation5036 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAnnotation5057 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEAnnotation5069 = new BitSet(new long[]{0x0000000000008000L,0x00000000C0000000L});
        public static final BitSet FOLLOW_94_in_ruleEAnnotation5082 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAnnotation5092 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5113 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEAnnotation5124 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_ruleEAnnotation5145 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEAnnotation5157 = new BitSet(new long[]{0x0000000000008000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleEAnnotation5170 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAnnotation5180 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5201 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEAnnotation5212 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
        public static final BitSet FOLLOW_ruleEObject_in_ruleEAnnotation5233 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEAnnotation5245 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEAnnotation5257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_entryRuleETypeParameter5293 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleETypeParameter5303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleETypeParameter5347 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleETypeParameter5368 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleETypeParameter5378 = new BitSet(new long[]{0x0000000000008000L,0x0000000220000000L});
        public static final BitSet FOLLOW_93_in_ruleETypeParameter5389 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleETypeParameter5399 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter5420 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleETypeParameter5431 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleETypeParameter5452 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleETypeParameter5464 = new BitSet(new long[]{0x0000000000008000L,0x0000000200000000L});
        public static final BitSet FOLLOW_97_in_ruleETypeParameter5477 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleETypeParameter5487 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter5508 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleETypeParameter5519 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleETypeParameter5540 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleETypeParameter5552 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleETypeParameter5564 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEOperation_in_entryRuleEOperation5600 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEOperation5610 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleEOperation5654 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation5675 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEOperation5685 = new BitSet(new long[]{0x0000000000008000L,0x00001FF820000000L});
        public static final BitSet FOLLOW_99_in_ruleEOperation5696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation5717 = new BitSet(new long[]{0x0000000000008000L,0x00001FF020000000L});
        public static final BitSet FOLLOW_100_in_ruleEOperation5730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEOperation5751 = new BitSet(new long[]{0x0000000000008000L,0x00001FE020000000L});
        public static final BitSet FOLLOW_101_in_ruleEOperation5764 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation5785 = new BitSet(new long[]{0x0000000000008000L,0x00001FC020000000L});
        public static final BitSet FOLLOW_102_in_ruleEOperation5798 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEOperation5819 = new BitSet(new long[]{0x0000000000008000L,0x00001F8020000000L});
        public static final BitSet FOLLOW_103_in_ruleEOperation5832 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation5855 = new BitSet(new long[]{0x0000000000008000L,0x00001F0020000000L});
        public static final BitSet FOLLOW_104_in_ruleEOperation5868 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEOperation5878 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation5901 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_14_in_ruleEOperation5912 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEOperation5935 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleEOperation5947 = new BitSet(new long[]{0x0000000000008000L,0x00001E0020000000L});
        public static final BitSet FOLLOW_93_in_ruleEOperation5960 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEOperation5970 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation5991 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEOperation6002 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEOperation6023 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEOperation6035 = new BitSet(new long[]{0x0000000000008000L,0x00001E0000000000L});
        public static final BitSet FOLLOW_105_in_ruleEOperation6048 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6069 = new BitSet(new long[]{0x0000000000008000L,0x00001C0000000000L});
        public static final BitSet FOLLOW_106_in_ruleEOperation6082 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEOperation6092 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6113 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEOperation6124 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEOperation6145 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEOperation6157 = new BitSet(new long[]{0x0000000000008000L,0x0000180000000000L});
        public static final BitSet FOLLOW_107_in_ruleEOperation6170 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEOperation6180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6201 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEOperation6212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEParameter_in_ruleEOperation6233 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEOperation6245 = new BitSet(new long[]{0x0000000000008000L,0x0000100000000000L});
        public static final BitSet FOLLOW_108_in_ruleEOperation6258 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEOperation6268 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6289 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEOperation6300 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEOperation6321 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEOperation6333 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEOperation6345 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEGenericType_in_entryRuleEGenericType6381 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEGenericType6391 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleEGenericType6435 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEGenericType6445 = new BitSet(new long[]{0x0000000000008000L,0x0007C00000000000L});
        public static final BitSet FOLLOW_110_in_ruleEGenericType6456 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType6479 = new BitSet(new long[]{0x0000000000008000L,0x0007800000000000L});
        public static final BitSet FOLLOW_111_in_ruleEGenericType6492 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEGenericType6515 = new BitSet(new long[]{0x0000000000008000L,0x0007000000000000L});
        public static final BitSet FOLLOW_112_in_ruleEGenericType6528 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6549 = new BitSet(new long[]{0x0000000000008000L,0x0006000000000000L});
        public static final BitSet FOLLOW_113_in_ruleEGenericType6562 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEGenericType6572 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6593 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEGenericType6604 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6625 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEGenericType6637 = new BitSet(new long[]{0x0000000000008000L,0x0004000000000000L});
        public static final BitSet FOLLOW_114_in_ruleEGenericType6650 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEGenericType6671 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEGenericType6683 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEStringToStringMapEntry_in_entryRuleEStringToStringMapEntry6719 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEStringToStringMapEntry6729 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_115_in_ruleEStringToStringMapEntry6773 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEStringToStringMapEntry6783 = new BitSet(new long[]{0x0000000000008000L,0x0030000000000000L});
        public static final BitSet FOLLOW_116_in_ruleEStringToStringMapEntry6794 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry6815 = new BitSet(new long[]{0x0000000000008000L,0x0020000000000000L});
        public static final BitSet FOLLOW_117_in_ruleEStringToStringMapEntry6828 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEStringToStringMapEntry6849 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEStringToStringMapEntry6861 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEClass_in_entryRuleEClass6897 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEClass6907 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_118_in_ruleEClass6959 = new BitSet(new long[]{0x0000000000000000L,0x0180000000000000L});
        public static final BitSet FOLLOW_119_in_ruleEClass6991 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
        public static final BitSet FOLLOW_120_in_ruleEClass7015 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7036 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7046 = new BitSet(new long[]{0x0000000000008000L,0x7E00040020000000L});
        public static final BitSet FOLLOW_121_in_ruleEClass7057 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7078 = new BitSet(new long[]{0x0000000000008000L,0x7C00040020000000L});
        public static final BitSet FOLLOW_122_in_ruleEClass7091 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7112 = new BitSet(new long[]{0x0000000000008000L,0x7800040020000000L});
        public static final BitSet FOLLOW_123_in_ruleEClass7125 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEClass7135 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7158 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7169 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEClass7192 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleEClass7204 = new BitSet(new long[]{0x0000000000008000L,0x7000040020000000L});
        public static final BitSet FOLLOW_93_in_ruleEClass7217 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7227 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7248 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7259 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEClass7280 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7292 = new BitSet(new long[]{0x0000000000008000L,0x7000040000000000L});
        public static final BitSet FOLLOW_106_in_ruleEClass7305 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7315 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7336 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7347 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEClass7368 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7380 = new BitSet(new long[]{0x0000000000008000L,0x7000000000000000L});
        public static final BitSet FOLLOW_124_in_ruleEClass7393 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7403 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass7424 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7435 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
        public static final BitSet FOLLOW_ruleEOperation_in_ruleEClass7456 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7468 = new BitSet(new long[]{0x0000000000008000L,0x6000000000000000L});
        public static final BitSet FOLLOW_125_in_ruleEClass7481 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000019F80L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass7512 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000019F80L});
        public static final BitSet FOLLOW_ruleEStructuralFeature_in_ruleEClass7544 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7556 = new BitSet(new long[]{0x0000000000008000L,0x4000000000000000L});
        public static final BitSet FOLLOW_126_in_ruleEClass7569 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEClass7579 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass7600 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEClass7611 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEClass7632 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7644 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEClass7656 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEObject_in_entryRuleEObject7692 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEObject7702 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_127_in_ruleEObject7746 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEParameter_in_entryRuleEParameter7782 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEParameter7792 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_128_in_ruleEParameter7836 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter7857 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEParameter7867 = new BitSet(new long[]{0x0000000000008000L,0x000002F820000000L});
        public static final BitSet FOLLOW_99_in_ruleEParameter7878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter7899 = new BitSet(new long[]{0x0000000000008000L,0x000002F020000000L});
        public static final BitSet FOLLOW_100_in_ruleEParameter7912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEParameter7933 = new BitSet(new long[]{0x0000000000008000L,0x000002E020000000L});
        public static final BitSet FOLLOW_101_in_ruleEParameter7946 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter7967 = new BitSet(new long[]{0x0000000000008000L,0x000002C020000000L});
        public static final BitSet FOLLOW_102_in_ruleEParameter7980 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEParameter8001 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L});
        public static final BitSet FOLLOW_103_in_ruleEParameter8014 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEParameter8037 = new BitSet(new long[]{0x0000000000008000L,0x0000020020000000L});
        public static final BitSet FOLLOW_93_in_ruleEParameter8050 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEParameter8060 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8081 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEParameter8092 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEParameter8113 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEParameter8125 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000000L});
        public static final BitSet FOLLOW_105_in_ruleEParameter8138 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEParameter8159 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEParameter8171 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEDataType_Impl_in_entryRuleEDataType_Impl8207 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEDataType_Impl8217 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_129_in_ruleEDataType_Impl8261 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8282 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEDataType_Impl8292 = new BitSet(new long[]{0x0000000000008000L,0x0600040020000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_121_in_ruleEDataType_Impl8303 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8324 = new BitSet(new long[]{0x0000000000008000L,0x0400040020000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_122_in_ruleEDataType_Impl8337 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEDataType_Impl8358 = new BitSet(new long[]{0x0000000000008000L,0x0000040020000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_130_in_ruleEDataType_Impl8371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEDataType_Impl8392 = new BitSet(new long[]{0x0000000000008000L,0x0000040020000000L});
        public static final BitSet FOLLOW_93_in_ruleEDataType_Impl8405 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEDataType_Impl8415 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8436 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEDataType_Impl8447 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEDataType_Impl8468 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEDataType_Impl8480 = new BitSet(new long[]{0x0000000000008000L,0x0000040000000000L});
        public static final BitSet FOLLOW_106_in_ruleEDataType_Impl8493 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEDataType_Impl8503 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8524 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEDataType_Impl8535 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEDataType_Impl8556 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEDataType_Impl8568 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEDataType_Impl8580 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnum_in_entryRuleEEnum8616 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnum8626 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_131_in_ruleEEnum8670 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8691 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnum8701 = new BitSet(new long[]{0x0000000000008000L,0x0600040020000000L,0x0000000000000014L});
        public static final BitSet FOLLOW_121_in_ruleEEnum8712 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8733 = new BitSet(new long[]{0x0000000000008000L,0x0400040020000000L,0x0000000000000014L});
        public static final BitSet FOLLOW_122_in_ruleEEnum8746 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnum8767 = new BitSet(new long[]{0x0000000000008000L,0x0000040020000000L,0x0000000000000014L});
        public static final BitSet FOLLOW_130_in_ruleEEnum8780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEEnum8801 = new BitSet(new long[]{0x0000000000008000L,0x0000040020000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_93_in_ruleEEnum8814 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnum8824 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum8845 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEEnum8856 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnum8877 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEEnum8889 = new BitSet(new long[]{0x0000000000008000L,0x0000040000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_106_in_ruleEEnum8902 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnum8912 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum8933 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEEnum8944 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_ruleETypeParameter_in_ruleEEnum8965 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEEnum8977 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_132_in_ruleEEnum8990 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnum9000 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9021 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEEnum9032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_ruleEEnum9053 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEEnum9065 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEEnum9077 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEEnumLiteral_in_entryRuleEEnumLiteral9113 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEEnumLiteral9123 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_133_in_ruleEEnumLiteral9167 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9188 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnumLiteral9198 = new BitSet(new long[]{0x0000000000008000L,0x0020000020000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_117_in_ruleEEnumLiteral9209 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEEnumLiteral9230 = new BitSet(new long[]{0x0000000000008000L,0x0000000020000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_134_in_ruleEEnumLiteral9243 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEEnumLiteral9264 = new BitSet(new long[]{0x0000000000008000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleEEnumLiteral9277 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEEnumLiteral9287 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9308 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEEnumLiteral9319 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEEnumLiteral9340 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEEnumLiteral9352 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEEnumLiteral9364 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEAttribute_in_entryRuleEAttribute9400 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEAttribute9410 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_135_in_ruleEAttribute9462 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001F00L});
        public static final BitSet FOLLOW_136_in_ruleEAttribute9494 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001E00L});
        public static final BitSet FOLLOW_137_in_ruleEAttribute9526 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001C00L});
        public static final BitSet FOLLOW_138_in_ruleEAttribute9558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001800L});
        public static final BitSet FOLLOW_139_in_ruleEAttribute9590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
        public static final BitSet FOLLOW_140_in_ruleEAttribute9614 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute9635 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAttribute9645 = new BitSet(new long[]{0x0000000000008000L,0x000002F820000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_99_in_ruleEAttribute9656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute9677 = new BitSet(new long[]{0x0000000000008000L,0x000002F020000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_100_in_ruleEAttribute9690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute9711 = new BitSet(new long[]{0x0000000000008000L,0x000002E020000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_101_in_ruleEAttribute9724 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute9745 = new BitSet(new long[]{0x0000000000008000L,0x000002C020000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_102_in_ruleEAttribute9758 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEAttribute9779 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_141_in_ruleEAttribute9792 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEAttribute9813 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x0000000000004000L});
        public static final BitSet FOLLOW_142_in_ruleEAttribute9826 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute9847 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L});
        public static final BitSet FOLLOW_103_in_ruleEAttribute9860 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEAttribute9883 = new BitSet(new long[]{0x0000000000008000L,0x0000020020000000L});
        public static final BitSet FOLLOW_93_in_ruleEAttribute9896 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEAttribute9906 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute9927 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEAttribute9938 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEAttribute9959 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEAttribute9971 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000000L});
        public static final BitSet FOLLOW_105_in_ruleEAttribute9984 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEAttribute10005 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEAttribute10017 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEReference_in_entryRuleEReference10053 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEReference10063 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_135_in_ruleEReference10115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000018700L});
        public static final BitSet FOLLOW_136_in_ruleEReference10147 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000018600L});
        public static final BitSet FOLLOW_137_in_ruleEReference10179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000018400L});
        public static final BitSet FOLLOW_138_in_ruleEReference10211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000018000L});
        public static final BitSet FOLLOW_143_in_ruleEReference10243 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_144_in_ruleEReference10267 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10288 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEReference10298 = new BitSet(new long[]{0x0000000000008000L,0x000002F820000000L,0x00000000000E6000L});
        public static final BitSet FOLLOW_99_in_ruleEReference10309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10330 = new BitSet(new long[]{0x0000000000008000L,0x000002F020000000L,0x00000000000E6000L});
        public static final BitSet FOLLOW_100_in_ruleEReference10343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10364 = new BitSet(new long[]{0x0000000000008000L,0x000002E020000000L,0x00000000000E6000L});
        public static final BitSet FOLLOW_101_in_ruleEReference10377 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference10398 = new BitSet(new long[]{0x0000000000008000L,0x000002C020000000L,0x00000000000E6000L});
        public static final BitSet FOLLOW_102_in_ruleEReference10411 = new BitSet(new long[]{0x0000000000000040L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleEReference10432 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x00000000000E6000L});
        public static final BitSet FOLLOW_141_in_ruleEReference10445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10466 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x00000000000E4000L});
        public static final BitSet FOLLOW_142_in_ruleEReference10479 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10500 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x00000000000E0000L});
        public static final BitSet FOLLOW_145_in_ruleEReference10513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleEReference10534 = new BitSet(new long[]{0x0000000000008000L,0x0000028020000000L,0x00000000000C0000L});
        public static final BitSet FOLLOW_103_in_ruleEReference10547 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10570 = new BitSet(new long[]{0x0000000000008000L,0x0000020020000000L,0x00000000000C0000L});
        public static final BitSet FOLLOW_146_in_ruleEReference10583 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10606 = new BitSet(new long[]{0x0000000000008000L,0x0000020020000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_147_in_ruleEReference10619 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleEReference10629 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10652 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_14_in_ruleEReference10663 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleEReference10686 = new BitSet(new long[]{0x0000000000004000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleEReference10698 = new BitSet(new long[]{0x0000000000008000L,0x0000020020000000L});
        public static final BitSet FOLLOW_93_in_ruleEReference10711 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleEReference10721 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference10742 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleEReference10753 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_ruleEAnnotation_in_ruleEReference10774 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleEReference10786 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000000L});
        public static final BitSet FOLLOW_105_in_ruleEReference10799 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleEGenericType_in_ruleEReference10820 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleEReference10832 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_148_in_rulelengthUnit10880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_149_in_rulelengthUnit10895 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_150_in_rulelengthUnit10910 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_151_in_rulelengthUnit10925 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_152_in_rulelengthUnit10940 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_153_in_rulelengthUnit10955 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_154_in_rulelengthUnit10970 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_155_in_rulelengthUnit10985 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_156_in_rulelengthUnit11000 = new BitSet(new long[]{0x0000000000000002L});
    }


}