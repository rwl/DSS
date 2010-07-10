lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'Electrickery' ;
T12 : '{' ;
T13 : 'wireData' ;
T14 : ',' ;
T15 : '}' ;
T16 : 'lineGeometries' ;
T17 : 'growthShapes' ;
T18 : 'lineCodes' ;
T19 : 'loadShapes' ;
T20 : 'spectrums' ;
T21 : 'executives' ;
T22 : 'WireData' ;
T23 : 'rDC' ;
T24 : 'rAC' ;
T25 : 'rUnits' ;
T26 : 'gmrAC' ;
T27 : 'gmrUnits' ;
T28 : 'radius' ;
T29 : 'radUnits' ;
T30 : 'normAmps' ;
T31 : 'emergAmps' ;
T32 : 'diameter' ;
T33 : 'reduce' ;
T34 : 'LineGeometry' ;
T35 : 'nConds' ;
T36 : 'nPhases' ;
T37 : 'cond' ;
T38 : 'x' ;
T39 : 'h' ;
T40 : 'units' ;
T41 : 'wire' ;
T42 : 'GrowthShape' ;
T43 : 'nPts' ;
T44 : 'year' ;
T45 : 'csvFile' ;
T46 : 'sngFile' ;
T47 : 'dblFile' ;
T48 : 'kron' ;
T49 : 'LineCode' ;
T50 : 'r1' ;
T51 : 'x1' ;
T52 : 'r0' ;
T53 : 'x0' ;
T54 : 'c1' ;
T55 : 'c0' ;
T56 : 'baseFreq' ;
T57 : 'faultRate' ;
T58 : 'pctPerm' ;
T59 : 'repair' ;
T60 : 'rg' ;
T61 : 'xg' ;
T62 : 'rho' ;
T63 : 'neutral' ;
T64 : 'rMatrix' ;
T65 : 'xMatrix' ;
T66 : 'cMatrix' ;
T67 : 'LoadShape' ;
T68 : 'interval' ;
T69 : 'mult' ;
T70 : 'hour' ;
T71 : 'mean' ;
T72 : 'stdDev' ;
T73 : 'qMult' ;
T74 : 'Spectrum' ;
T75 : 'nHarm' ;
T76 : 'harmonic' ;
T77 : 'pctMag' ;
T78 : 'angle' ;
T79 : 'Executive' ;
T80 : 'command' ;
T81 : 'maxCircuits' ;
T82 : '-' ;
T83 : '.' ;
T84 : 'E' ;
T85 : 'e' ;
T86 : 'true' ;
T87 : 'false' ;
T88 : 'EAnnotation' ;
T89 : 'source' ;
T90 : 'references' ;
T91 : '(' ;
T92 : ')' ;
T93 : 'eAnnotations' ;
T94 : 'details' ;
T95 : 'contents' ;
T96 : 'ETypeParameter' ;
T97 : 'eBounds' ;
T98 : 'EOperation' ;
T99 : 'ordered' ;
T100 : 'unique' ;
T101 : 'lowerBound' ;
T102 : 'upperBound' ;
T103 : 'eType' ;
T104 : 'eExceptions' ;
T105 : 'eGenericType' ;
T106 : 'eTypeParameters' ;
T107 : 'eParameters' ;
T108 : 'eGenericExceptions' ;
T109 : 'EGenericType' ;
T110 : 'eTypeParameter' ;
T111 : 'eClassifier' ;
T112 : 'eUpperBound' ;
T113 : 'eTypeArguments' ;
T114 : 'eLowerBound' ;
T115 : 'EStringToStringMapEntry' ;
T116 : 'key' ;
T117 : 'value' ;
T118 : 'abstract' ;
T119 : 'interface' ;
T120 : 'EClass' ;
T121 : 'instanceClassName' ;
T122 : 'instanceTypeName' ;
T123 : 'eSuperTypes' ;
T124 : 'eOperations' ;
T125 : 'eStructuralFeatures' ;
T126 : 'eGenericSuperTypes' ;
T127 : 'EObject' ;
T128 : 'EParameter' ;
T129 : 'EDataType' ;
T130 : 'serializable' ;
T131 : 'EEnum' ;
T132 : 'eLiterals' ;
T133 : 'EEnumLiteral' ;
T134 : 'literal' ;
T135 : 'volatile' ;
T136 : 'transient' ;
T137 : 'unsettable' ;
T138 : 'derived' ;
T139 : 'iD' ;
T140 : 'EAttribute' ;
T141 : 'changeable' ;
T142 : 'defaultValueLiteral' ;
T143 : 'containment' ;
T144 : 'EReference' ;
T145 : 'resolveProxies' ;
T146 : 'eOpposite' ;
T147 : 'eKeys' ;
T148 : 'none' ;
T149 : 'mi' ;
T150 : 'km' ;
T151 : 'kft' ;
T152 : 'm' ;
T153 : 'me' ;
T154 : 'ft' ;
T155 : 'in' ;
T156 : 'cm' ;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7230
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7232
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7234
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7236
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7238
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7240
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7242
RULE_ANY_OTHER : .;


