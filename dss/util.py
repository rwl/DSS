# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

"""Defines convenient pyparsing constructs and token converters.

@see: sparser.py by Tim Cera timcera@earthlink.net"""

from numpy import zeros, diag, array, ndarray

from pyparsing import \
    TokenConverter, oneOf, string, Literal, Group, Word, Optional, Combine, \
    sglQuotedString, dblQuotedString, restOfLine, nums

def triarray(lower):
    """Returns a symmetric array given only the lower triangular form. For
    example:
        >>> a = triarray([[1.2], [3.0, 1.2], [3.0, 3.0, 1.2]])
        >>> a[0, 2]
        3.0
    """
    c = 1
    for row in lower:
        assert len(row) == c
        c += 1

    n = len(lower[-1])

    a = zeros((n, n))

    for i, row in enumerate(lower):
        j = len(row)
        if j == n:
            a[i, :] = row
        else:
            a[i, :j] = row

    return symmetrize(a)


def symmetrize(a):
    return a + a.T - diag(a.diagonal())


class SymNDArray(ndarray):
    def __setitem__(self, (i, j), value):
        ndarray.__setitem__(self, (i, j), value)
        ndarray.__setitem__(self, (j, i), value)


def symarray(input_array):
    """Returns a symmetrized version of input_array; further assignments to
    the array are automatically symmetrized."""
    return symmetrize(array(input_array)).view(SymNDArray)

## Parsing util

class ToBoolean(TokenConverter):
    """ Converter to make token boolean """

    def postParse(self, instring, loc, tokenlist):
        """ Converts the first token to boolean """

        return bool(tokenlist[0])


class ToInteger(TokenConverter):
    """ Converter to make token into an integer """

    def postParse(self, instring, loc, tokenlist):
        """ Converts the first token to an integer """

        return int(tokenlist[0])


class ToFloat(TokenConverter):
    """ Converter to make token into a float """

    def postParse(self, instring, loc, tokenlist):
        """ Converts the first token into a float """

        return float(tokenlist[0])


decimal_sep = "."

sign = oneOf("+ -")

scolon = Literal(";").suppress()

matlab_comment = Group(Literal('%') + restOfLine).suppress()
psse_comment = Literal('@!') + Optional(restOfLine)

# part of printables without decimal_sep, +, -
special_chars = string.replace(
    '!"#$%&\'()*,./:;<=>?@[\\]^_`{|}~', decimal_sep, ""
)

boolean = ToBoolean(ToInteger(Word("01", exact=1))).setName("bool")

integer = ToInteger(
    Combine(Optional(sign) + Word(nums))
).setName("integer")

positive_integer = ToInteger(
    Combine(Optional("+") + Word(nums))
).setName("integer")

negative_integer = ToInteger(
    Combine("-" + Word(nums))
).setName("integer")

real = ToFloat(
    Combine(
        Optional(sign) +
        Word(nums) +
        Optional(decimal_sep + Word(nums)) +
        Optional(oneOf("E e") + Optional(sign) + Word(nums))
    )
).setName("real")

#real = ToFloat(
#       Combine(Optional(sign) +
#               Word(nums) +
#               decimal_sep +
#               Optional(Word(nums)) +
#               Optional(oneOf("E e") +
#                        Word(nums)))).setName("real")

positive_real = ToFloat(
    Combine(
        Optional("+") + Word(nums) + decimal_sep + Optional(Word(nums)) +
        Optional(oneOf("E e") + Word(nums))
    )
).setName("real")

negative_real = ToFloat(
    Combine(
        "-" + Word(nums) + decimal_sep + Optional(Word(nums)) +
        Optional(oneOf("E e") + Word(nums))
    )
).setName("real")

q_string = (sglQuotedString | dblQuotedString).setName("q_string")

# add other characters we should skip over between interesting fields
#integer_junk = Optional(
#    Suppress(Word(alphas + special_chars + decimal_sep))
#).setName("integer_junk")
#
#real_junk = Optional(
#    Suppress(Word(alphas + special_chars))
#).setName("real_junk")
#
#q_string_junk = SkipTo(q_string).setName("q_string_junk")

# punctuation
colon  = Literal(":")
lbrace = Literal("{")
rbrace = Literal("}")
lbrack = Literal("[")
rbrack = Literal("]")
lparen = Literal("(")
rparen = Literal(")")
equals = Literal("=")
comma  = Literal(",")
dot    = Literal(".")
slash  = Literal("/")
bslash = Literal("\\")
star   = Literal("*")
semi   = Literal(";")
at     = Literal("@")
minus  = Literal("-")
tilde  = Literal("~")

comma_sep = comma.suppress()


def make_unique_name(base, existing=[], format="%s_%s"):
    """Returns a name, unique within a context, based on the specified name.

    base: the desired base name of the generated unique name.
    existing: a sequence of the existing names to avoid returning.
    format: a formatting specification for how the name is made unique.
    """
    count = 2
    name = base
    while name in existing:
        name = format % (base, count)
        count += 1

    return name


if __name__ == "__main__":
    import doctest
    doctest.testmod()
