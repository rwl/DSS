lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : 'new' ;
T15 : 'object' ;
T16 : '=' ;
T17 : '\n' ;
T18 : 'clear' ;
T19 : 'Circuit' ;
T20 : 'circuit' ;
T21 : '.' ;
T22 : 'phases' ;
T23 : 'mvasc3' ;
T24 : 'mvasc1' ;
T25 : 'basekV' ;
T26 : 'pu' ;
T27 : 'WireData' ;
T28 : '{' ;
T29 : 'rDC' ;
T30 : 'rAC' ;
T31 : 'rUnits' ;
T32 : 'gmrAC' ;
T33 : 'gmrUnits' ;
T34 : 'radius' ;
T35 : 'radUnits' ;
T36 : 'normAmps' ;
T37 : 'emergAmps' ;
T38 : 'diameter' ;
T39 : '}' ;
T40 : 'reduce' ;
T41 : 'LineGeometry' ;
T42 : 'nConds' ;
T43 : 'nPhases' ;
T44 : 'cond' ;
T45 : 'x' ;
T46 : 'h' ;
T47 : 'units' ;
T48 : 'wire' ;
T49 : 'GrowthShape' ;
T50 : 'nPts' ;
T51 : 'year' ;
T52 : ',' ;
T53 : 'csvFile' ;
T54 : 'sngFile' ;
T55 : 'dblFile' ;
T56 : 'kron' ;
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
T87 : '-' ;
T88 : 'E' ;
T89 : 'e' ;
T90 : 'true' ;
T91 : 'false' ;
T92 : 'EAnnotation' ;
T93 : 'source' ;
T94 : 'references' ;
T95 : '(' ;
T96 : ')' ;
T97 : 'eAnnotations' ;
T98 : 'details' ;
T99 : 'contents' ;
T100 : 'ETypeParameter' ;
T101 : 'eBounds' ;
T102 : 'EOperation' ;
T103 : 'ordered' ;
T104 : 'unique' ;
T105 : 'lowerBound' ;
T106 : 'upperBound' ;
T107 : 'eType' ;
T108 : 'eExceptions' ;
T109 : 'eGenericType' ;
T110 : 'eTypeParameters' ;
T111 : 'eParameters' ;
T112 : 'eGenericExceptions' ;
T113 : 'EGenericType' ;
T114 : 'eTypeParameter' ;
T115 : 'eClassifier' ;
T116 : 'eUpperBound' ;
T117 : 'eTypeArguments' ;
T118 : 'eLowerBound' ;
T119 : 'EStringToStringMapEntry' ;
T120 : 'key' ;
T121 : 'value' ;
T122 : 'abstract' ;
T123 : 'interface' ;
T124 : 'EClass' ;
T125 : 'instanceClassName' ;
T126 : 'instanceTypeName' ;
T127 : 'eSuperTypes' ;
T128 : 'eOperations' ;
T129 : 'eStructuralFeatures' ;
T130 : 'eGenericSuperTypes' ;
T131 : 'EObject' ;
T132 : 'EParameter' ;
T133 : 'EDataType' ;
T134 : 'serializable' ;
T135 : 'EEnum' ;
T136 : 'eLiterals' ;
T137 : 'EEnumLiteral' ;
T138 : 'literal' ;
T139 : 'volatile' ;
T140 : 'transient' ;
T141 : 'unsettable' ;
T142 : 'derived' ;
T143 : 'iD' ;
T144 : 'EAttribute' ;
T145 : 'changeable' ;
T146 : 'defaultValueLiteral' ;
T147 : 'containment' ;
T148 : 'EReference' ;
T149 : 'resolveProxies' ;
T150 : 'eOpposite' ;
T151 : 'eKeys' ;
T152 : 'none' ;
T153 : 'mi' ;
T154 : 'km' ;
T155 : 'kft' ;
T156 : 'm' ;
T157 : 'me' ;
T158 : 'ft' ;
T159 : 'in' ;
T160 : 'cm' ;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7293
RULE_LINE_CONTINUATION : '\n~';

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7295
RULE_INLINE_COMMENT : '!' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7297
RULE_NEW : ('New'|'new');

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7299
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7301
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7303
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7305
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7307
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7309
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl/src-gen/electrickery/dssdsl/parser/antlr/internal/InternalDssDsl.g" 7311
RULE_ANY_OTHER : .;


