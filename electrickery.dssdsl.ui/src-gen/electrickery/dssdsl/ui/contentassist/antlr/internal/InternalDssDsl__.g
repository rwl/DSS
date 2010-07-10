lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T14 : 'Circuit' ;
T15 : 'circuit' ;
T16 : 'E' ;
T17 : 'e' ;
T18 : 'true' ;
T19 : 'false' ;
T20 : 'none' ;
T21 : 'mi' ;
T22 : 'km' ;
T23 : 'kft' ;
T24 : 'm' ;
T25 : 'me' ;
T26 : 'ft' ;
T27 : 'in' ;
T28 : 'cm' ;
T29 : 'new' ;
T30 : '\n' ;
T31 : 'object' ;
T32 : '=' ;
T33 : 'clear' ;
T34 : '.' ;
T35 : 'phases' ;
T36 : 'mvasc3' ;
T37 : 'mvasc1' ;
T38 : 'basekV' ;
T39 : 'pu' ;
T40 : 'WireData' ;
T41 : '{' ;
T42 : '}' ;
T43 : 'rDC' ;
T44 : 'rAC' ;
T45 : 'rUnits' ;
T46 : 'gmrAC' ;
T47 : 'gmrUnits' ;
T48 : 'radius' ;
T49 : 'radUnits' ;
T50 : 'normAmps' ;
T51 : 'emergAmps' ;
T52 : 'diameter' ;
T53 : 'LineGeometry' ;
T54 : 'wire' ;
T55 : 'nConds' ;
T56 : 'nPhases' ;
T57 : 'cond' ;
T58 : 'x' ;
T59 : 'h' ;
T60 : 'units' ;
T61 : 'GrowthShape' ;
T62 : 'nPts' ;
T63 : 'year' ;
T64 : ',' ;
T65 : 'csvFile' ;
T66 : 'sngFile' ;
T67 : 'dblFile' ;
T68 : 'LineCode' ;
T69 : 'r1' ;
T70 : 'x1' ;
T71 : 'r0' ;
T72 : 'x0' ;
T73 : 'c1' ;
T74 : 'c0' ;
T75 : 'baseFreq' ;
T76 : 'faultRate' ;
T77 : 'pctPerm' ;
T78 : 'repair' ;
T79 : 'rg' ;
T80 : 'xg' ;
T81 : 'rho' ;
T82 : 'neutral' ;
T83 : 'rMatrix' ;
T84 : 'xMatrix' ;
T85 : 'cMatrix' ;
T86 : 'LoadShape' ;
T87 : 'interval' ;
T88 : 'mult' ;
T89 : 'hour' ;
T90 : 'mean' ;
T91 : 'stdDev' ;
T92 : 'qMult' ;
T93 : 'Spectrum' ;
T94 : 'nHarm' ;
T95 : 'harmonic' ;
T96 : 'pctMag' ;
T97 : 'angle' ;
T98 : '-' ;
T99 : 'EAnnotation' ;
T100 : 'source' ;
T101 : 'references' ;
T102 : '(' ;
T103 : ')' ;
T104 : 'eAnnotations' ;
T105 : 'details' ;
T106 : 'contents' ;
T107 : 'ETypeParameter' ;
T108 : 'eBounds' ;
T109 : 'EOperation' ;
T110 : 'ordered' ;
T111 : 'unique' ;
T112 : 'lowerBound' ;
T113 : 'upperBound' ;
T114 : 'eType' ;
T115 : 'eExceptions' ;
T116 : 'eGenericType' ;
T117 : 'eTypeParameters' ;
T118 : 'eParameters' ;
T119 : 'eGenericExceptions' ;
T120 : 'EGenericType' ;
T121 : 'eTypeParameter' ;
T122 : 'eClassifier' ;
T123 : 'eUpperBound' ;
T124 : 'eTypeArguments' ;
T125 : 'eLowerBound' ;
T126 : 'EStringToStringMapEntry' ;
T127 : 'key' ;
T128 : 'value' ;
T129 : 'EClass' ;
T130 : 'instanceClassName' ;
T131 : 'instanceTypeName' ;
T132 : 'eSuperTypes' ;
T133 : 'eOperations' ;
T134 : 'eStructuralFeatures' ;
T135 : 'eGenericSuperTypes' ;
T136 : 'EObject' ;
T137 : 'EParameter' ;
T138 : 'EDataType' ;
T139 : 'serializable' ;
T140 : 'EEnum' ;
T141 : 'eLiterals' ;
T142 : 'EEnumLiteral' ;
T143 : 'literal' ;
T144 : 'EAttribute' ;
T145 : 'changeable' ;
T146 : 'defaultValueLiteral' ;
T147 : 'EReference' ;
T148 : 'resolveProxies' ;
T149 : 'eOpposite' ;
T150 : 'eKeys' ;
T151 : 'reduce' ;
T152 : 'kron' ;
T153 : 'abstract' ;
T154 : 'interface' ;
T155 : 'volatile' ;
T156 : 'transient' ;
T157 : 'unsettable' ;
T158 : 'derived' ;
T159 : 'iD' ;
T160 : 'containment' ;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28314
RULE_LINE_CONTINUATION : '\n~';

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28316
RULE_INLINE_COMMENT : '!' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28318
RULE_NEW : ('New'|'new');

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28320
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28322
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28324
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28326
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28328
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28330
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28332
RULE_ANY_OTHER : .;


