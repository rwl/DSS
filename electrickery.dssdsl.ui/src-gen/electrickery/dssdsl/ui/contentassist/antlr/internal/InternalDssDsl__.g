lexer grammar InternalDssDsl;
@header {
package electrickery.dssdsl.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T11 : 'E' ;
T12 : 'e' ;
T13 : 'true' ;
T14 : 'false' ;
T15 : 'none' ;
T16 : 'mi' ;
T17 : 'km' ;
T18 : 'kft' ;
T19 : 'm' ;
T20 : 'me' ;
T21 : 'ft' ;
T22 : 'in' ;
T23 : 'cm' ;
T24 : 'Electrickery' ;
T25 : '{' ;
T26 : '}' ;
T27 : 'wireData' ;
T28 : ',' ;
T29 : 'lineGeometries' ;
T30 : 'growthShapes' ;
T31 : 'lineCodes' ;
T32 : 'loadShapes' ;
T33 : 'spectrums' ;
T34 : 'executives' ;
T35 : 'WireData' ;
T36 : 'rDC' ;
T37 : 'rAC' ;
T38 : 'rUnits' ;
T39 : 'gmrAC' ;
T40 : 'gmrUnits' ;
T41 : 'radius' ;
T42 : 'radUnits' ;
T43 : 'normAmps' ;
T44 : 'emergAmps' ;
T45 : 'diameter' ;
T46 : 'LineGeometry' ;
T47 : 'wire' ;
T48 : 'nConds' ;
T49 : 'nPhases' ;
T50 : 'cond' ;
T51 : 'x' ;
T52 : 'h' ;
T53 : 'units' ;
T54 : 'GrowthShape' ;
T55 : 'nPts' ;
T56 : 'year' ;
T57 : 'csvFile' ;
T58 : 'sngFile' ;
T59 : 'dblFile' ;
T60 : 'LineCode' ;
T61 : 'r1' ;
T62 : 'x1' ;
T63 : 'r0' ;
T64 : 'x0' ;
T65 : 'c1' ;
T66 : 'c0' ;
T67 : 'baseFreq' ;
T68 : 'faultRate' ;
T69 : 'pctPerm' ;
T70 : 'repair' ;
T71 : 'rg' ;
T72 : 'xg' ;
T73 : 'rho' ;
T74 : 'neutral' ;
T75 : 'rMatrix' ;
T76 : 'xMatrix' ;
T77 : 'cMatrix' ;
T78 : 'LoadShape' ;
T79 : 'interval' ;
T80 : 'mult' ;
T81 : 'hour' ;
T82 : 'mean' ;
T83 : 'stdDev' ;
T84 : 'qMult' ;
T85 : 'Spectrum' ;
T86 : 'nHarm' ;
T87 : 'harmonic' ;
T88 : 'pctMag' ;
T89 : 'angle' ;
T90 : 'Executive' ;
T91 : 'command' ;
T92 : 'maxCircuits' ;
T93 : '-' ;
T94 : '.' ;
T95 : 'EAnnotation' ;
T96 : 'source' ;
T97 : 'references' ;
T98 : '(' ;
T99 : ')' ;
T100 : 'eAnnotations' ;
T101 : 'details' ;
T102 : 'contents' ;
T103 : 'ETypeParameter' ;
T104 : 'eBounds' ;
T105 : 'EOperation' ;
T106 : 'ordered' ;
T107 : 'unique' ;
T108 : 'lowerBound' ;
T109 : 'upperBound' ;
T110 : 'eType' ;
T111 : 'eExceptions' ;
T112 : 'eGenericType' ;
T113 : 'eTypeParameters' ;
T114 : 'eParameters' ;
T115 : 'eGenericExceptions' ;
T116 : 'EGenericType' ;
T117 : 'eTypeParameter' ;
T118 : 'eClassifier' ;
T119 : 'eUpperBound' ;
T120 : 'eTypeArguments' ;
T121 : 'eLowerBound' ;
T122 : 'EStringToStringMapEntry' ;
T123 : 'key' ;
T124 : 'value' ;
T125 : 'EClass' ;
T126 : 'instanceClassName' ;
T127 : 'instanceTypeName' ;
T128 : 'eSuperTypes' ;
T129 : 'eOperations' ;
T130 : 'eStructuralFeatures' ;
T131 : 'eGenericSuperTypes' ;
T132 : 'EObject' ;
T133 : 'EParameter' ;
T134 : 'EDataType' ;
T135 : 'serializable' ;
T136 : 'EEnum' ;
T137 : 'eLiterals' ;
T138 : 'EEnumLiteral' ;
T139 : 'literal' ;
T140 : 'EAttribute' ;
T141 : 'changeable' ;
T142 : 'defaultValueLiteral' ;
T143 : 'EReference' ;
T144 : 'resolveProxies' ;
T145 : 'eOpposite' ;
T146 : 'eKeys' ;
T147 : 'reduce' ;
T148 : 'kron' ;
T149 : 'abstract' ;
T150 : 'interface' ;
T151 : 'volatile' ;
T152 : 'transient' ;
T153 : 'unsettable' ;
T154 : 'derived' ;
T155 : 'iD' ;
T156 : 'containment' ;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28130
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28132
RULE_INT : ('0'..'9')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28134
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28136
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28138
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28140
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../electrickery.dssdsl.ui/src-gen/electrickery/dssdsl/ui/contentassist/antlr/internal/InternalDssDsl.g" 28142
RULE_ANY_OTHER : .;


