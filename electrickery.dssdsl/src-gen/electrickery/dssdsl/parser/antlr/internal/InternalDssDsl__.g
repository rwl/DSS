lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T12 : 'new' ;
T13 : 'object' ;
T14 : '=' ;
T15 : '\n' ;
T16 : 'WireData' ;
T17 : '{' ;
T18 : 'rDC' ;
T19 : 'rAC' ;
T20 : 'rUnits' ;
T21 : 'gmrAC' ;
T22 : 'gmrUnits' ;
T23 : 'radius' ;
T24 : 'radUnits' ;
T25 : 'normAmps' ;
T26 : 'emergAmps' ;
T27 : 'diameter' ;
T28 : '}' ;
T29 : 'reduce' ;
T30 : 'LineGeometry' ;
T31 : 'nConds' ;
T32 : 'nPhases' ;
T33 : 'cond' ;
T34 : 'x' ;
T35 : 'h' ;
T36 : 'units' ;
T37 : 'wire' ;
T38 : 'GrowthShape' ;
T39 : 'nPts' ;
T40 : 'year' ;
T41 : ',' ;
T42 : 'csvFile' ;
T43 : 'sngFile' ;
T44 : 'dblFile' ;
T45 : 'kron' ;
T46 : 'LineCode' ;
T47 : 'r1' ;
T48 : 'x1' ;
T49 : 'r0' ;
T50 : 'x0' ;
T51 : 'c1' ;
T52 : 'c0' ;
T53 : 'baseFreq' ;
T54 : 'faultRate' ;
T55 : 'pctPerm' ;
T56 : 'repair' ;
T57 : 'rg' ;
T58 : 'xg' ;
T59 : 'rho' ;
T60 : 'neutral' ;
T61 : 'rMatrix' ;
T62 : 'xMatrix' ;
T63 : 'cMatrix' ;
T64 : 'LoadShape' ;
T65 : 'interval' ;
T66 : 'mult' ;
T67 : 'hour' ;
T68 : 'mean' ;
T69 : 'stdDev' ;
T70 : 'qMult' ;
T71 : 'Spectrum' ;
T72 : 'nHarm' ;
T73 : 'harmonic' ;
T74 : 'pctMag' ;
T75 : 'angle' ;
T76 : 'clear\n' ;
T77 : 'solved' ;
T78 : 'control_busNameRedefined' ;
T79 : 'Circuit' ;
T80 : 'numNodes' ;
T81 : 'generatorDispatchReference' ;
T82 : 'genMultiplier' ;
T83 : 'busNameRedefined' ;
T84 : 'loadMultiplier' ;
T85 : 'defaultGrowthFactor' ;
T86 : 'defaultHourMult' ;
T87 : 'priceSignal' ;
T88 : 'controlQueue' ;
T89 : 'lines' ;
T90 : '(' ;
T91 : ')' ;
T92 : 'loads' ;
T93 : 'shuntCapacitors' ;
T94 : 'feeder' ;
T95 : '-' ;
T96 : '.' ;
T97 : 'E' ;
T98 : 'e' ;
T99 : 'true' ;
T100 : 'false' ;
T101 : 'EAnnotation' ;
T102 : 'source' ;
T103 : 'references' ;
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
T129 : 'abstract' ;
T130 : 'interface' ;
T131 : 'EClass' ;
T132 : 'instanceClassName' ;
T133 : 'instanceTypeName' ;
T134 : 'eSuperTypes' ;
T135 : 'eOperations' ;
T136 : 'eStructuralFeatures' ;
T137 : 'eGenericSuperTypes' ;
T138 : 'EObject' ;
T139 : 'EParameter' ;
T140 : 'EDataType' ;
T141 : 'serializable' ;
T142 : 'EEnum' ;
T143 : 'eLiterals' ;
T144 : 'EEnumLiteral' ;
T145 : 'literal' ;
T146 : 'volatile' ;
T147 : 'transient' ;
T148 : 'unsettable' ;
T149 : 'derived' ;
T150 : 'iD' ;
T151 : 'EAttribute' ;
T152 : 'changeable' ;
T153 : 'defaultValueLiteral' ;
T154 : 'containment' ;
T155 : 'EReference' ;
T156 : 'resolveProxies' ;
T157 : 'eOpposite' ;
T158 : 'eKeys' ;
T159 : 'none' ;
T160 : 'mi' ;
T161 : 'km' ;
T162 : 'kft' ;
T163 : 'm' ;
T164 : 'me' ;
T165 : 'ft' ;
T166 : 'in' ;
T167 : 'cm' ;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7541
RULE_NEW : ('New'|'new');

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7543
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7545
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7547
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7549
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7551
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7553
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7555
RULE_ANY_OTHER : .;


