lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T12 : 'E' ;
T13 : 'e' ;
T14 : 'true' ;
T15 : 'false' ;
T16 : 'none' ;
T17 : 'mi' ;
T18 : 'km' ;
T19 : 'kft' ;
T20 : 'm' ;
T21 : 'me' ;
T22 : 'ft' ;
T23 : 'in' ;
T24 : 'cm' ;
T25 : 'new' ;
T26 : '\n' ;
T27 : 'object' ;
T28 : '=' ;
T29 : 'WireData' ;
T30 : '{' ;
T31 : '}' ;
T32 : 'rDC' ;
T33 : 'rAC' ;
T34 : 'rUnits' ;
T35 : 'gmrAC' ;
T36 : 'gmrUnits' ;
T37 : 'radius' ;
T38 : 'radUnits' ;
T39 : 'normAmps' ;
T40 : 'emergAmps' ;
T41 : 'diameter' ;
T42 : 'LineGeometry' ;
T43 : 'wire' ;
T44 : 'nConds' ;
T45 : 'nPhases' ;
T46 : 'cond' ;
T47 : 'x' ;
T48 : 'h' ;
T49 : 'units' ;
T50 : 'GrowthShape' ;
T51 : 'nPts' ;
T52 : 'year' ;
T53 : ',' ;
T54 : 'csvFile' ;
T55 : 'sngFile' ;
T56 : 'dblFile' ;
T57 : 'LineCode' ;
T58 : 'r1' ;
T59 : 'x1' ;
T60 : 'r0' ;
T61 : 'x0' ;
T62 : 'c1' ;
T63 : 'c0' ;
T64 : 'baseFreq' ;
T65 : 'faultRate' ;
T66 : 'pctPerm' ;
T67 : 'repair' ;
T68 : 'rg' ;
T69 : 'xg' ;
T70 : 'rho' ;
T71 : 'neutral' ;
T72 : 'rMatrix' ;
T73 : 'xMatrix' ;
T74 : 'cMatrix' ;
T75 : 'LoadShape' ;
T76 : 'interval' ;
T77 : 'mult' ;
T78 : 'hour' ;
T79 : 'mean' ;
T80 : 'stdDev' ;
T81 : 'qMult' ;
T82 : 'Spectrum' ;
T83 : 'nHarm' ;
T84 : 'harmonic' ;
T85 : 'pctMag' ;
T86 : 'angle' ;
T87 : 'clear\n' ;
T88 : 'Circuit' ;
T89 : 'numNodes' ;
T90 : 'generatorDispatchReference' ;
T91 : 'genMultiplier' ;
T92 : 'busNameRedefined' ;
T93 : 'loadMultiplier' ;
T94 : 'defaultGrowthFactor' ;
T95 : 'defaultHourMult' ;
T96 : 'priceSignal' ;
T97 : 'controlQueue' ;
T98 : 'lines' ;
T99 : '(' ;
T100 : ')' ;
T101 : 'loads' ;
T102 : 'shuntCapacitors' ;
T103 : 'feeder' ;
T104 : '-' ;
T105 : '.' ;
T106 : 'EAnnotation' ;
T107 : 'source' ;
T108 : 'references' ;
T109 : 'eAnnotations' ;
T110 : 'details' ;
T111 : 'contents' ;
T112 : 'ETypeParameter' ;
T113 : 'eBounds' ;
T114 : 'EOperation' ;
T115 : 'ordered' ;
T116 : 'unique' ;
T117 : 'lowerBound' ;
T118 : 'upperBound' ;
T119 : 'eType' ;
T120 : 'eExceptions' ;
T121 : 'eGenericType' ;
T122 : 'eTypeParameters' ;
T123 : 'eParameters' ;
T124 : 'eGenericExceptions' ;
T125 : 'EGenericType' ;
T126 : 'eTypeParameter' ;
T127 : 'eClassifier' ;
T128 : 'eUpperBound' ;
T129 : 'eTypeArguments' ;
T130 : 'eLowerBound' ;
T131 : 'EStringToStringMapEntry' ;
T132 : 'key' ;
T133 : 'value' ;
T134 : 'EClass' ;
T135 : 'instanceClassName' ;
T136 : 'instanceTypeName' ;
T137 : 'eSuperTypes' ;
T138 : 'eOperations' ;
T139 : 'eStructuralFeatures' ;
T140 : 'eGenericSuperTypes' ;
T141 : 'EObject' ;
T142 : 'EParameter' ;
T143 : 'EDataType' ;
T144 : 'serializable' ;
T145 : 'EEnum' ;
T146 : 'eLiterals' ;
T147 : 'EEnumLiteral' ;
T148 : 'literal' ;
T149 : 'EAttribute' ;
T150 : 'changeable' ;
T151 : 'defaultValueLiteral' ;
T152 : 'EReference' ;
T153 : 'resolveProxies' ;
T154 : 'eOpposite' ;
T155 : 'eKeys' ;
T156 : 'reduce' ;
T157 : 'kron' ;
T158 : 'solved' ;
T159 : 'control_busNameRedefined' ;
T160 : 'abstract' ;
T161 : 'interface' ;
T162 : 'volatile' ;
T163 : 'transient' ;
T164 : 'unsettable' ;
T165 : 'derived' ;
T166 : 'iD' ;
T167 : 'containment' ;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29648
RULE_NEW : ('New'|'new');

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29650
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29652
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29654
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29656
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29658
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29660
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 29662
RULE_ANY_OTHER : .;


