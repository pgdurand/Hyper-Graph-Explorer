/*  Copyright 2003-2016 Patrick G. Durand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bzh.plealog.hge.parser;

public interface HGEParserConstants {

  int EOF = 0;
  int PLUS = 6;
  int MINUS = 7;
  int MULT = 8;
  int DIV = 9;
  int MOD = 10;
  int LT = 11;
  int LTE = 12;
  int GT = 13;
  int GTE = 14;
  int EQ = 15;
  int NEQ = 16;
  int MAT = 17;
  int NMAT = 18;
  int AND = 19;
  int NOT = 20;
  int OR = 21;
  int OPENPAR = 22;
  int CLOSEPAR = 23;
  int SEMICOLON = 24;
  int DOT = 25;
  int COLON = 26;
  int COMMA = 27;
  int FROM = 28;
  int WHERE = 29;
  int RETURN = 30;
  int PATH = 31;
  int NEXT = 32;
  int CONNECT = 33;
  int UNTIL = 34;
  int IN = 35;
  int TRUE = 36;
  int FALSE = 37;
  int DISTINCT = 38;
  int AS = 39;
  int AMP = 40;
  int INTEGER_LITERAL = 41;
  int FLOATING_POINT_LITERAL = 42;
  int EXPONENT = 43;
  int CHARACTER_LITERAL = 44;
  int STRING_LITERAL = 45;
  int IDENTIFIER = 46;
  int LETTER = 47;
  int DIGIT = 48;

  int DEFAULT = 0;

  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"==\"",
    "\"!=\"",
    "\"::\"",
    "\"!:\"",
    "\"and\"",
    "\"not\"",
    "\"or\"",
    "\"(\"",
    "\")\"",
    "\";\"",
    "\".\"",
    "\":\"",
    "\",\"",
    "\"from\"",
    "\"where\"",
    "\"return\"",
    "\"path\"",
    "\"next\"",
    "\"connect\"",
    "\"until\"",
    "\"in\"",
    "\"true\"",
    "\"false\"",
    "\"distinct\"",
    "\"as\"",
    "\"&\"",
    "<INTEGER_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<CHARACTER_LITERAL>",
    "<STRING_LITERAL>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "\"!\"",
    "\"[\"",
    "\"]\"",
    "\"{\"",
    "\"}\"",
  };

}
