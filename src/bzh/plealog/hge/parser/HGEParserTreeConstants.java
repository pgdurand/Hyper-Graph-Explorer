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

public interface HGEParserTreeConstants
{
  public int JJTQUERY = 0;
  public int JJTSIMPLEQUERY = 1;
  public int JJTSIMPLEQUERYDECLARATION = 2;
  public int JJTQUERYCLAUSE = 3;
  public int JJTVOID = 4;
  public int JJTVARNODEDECLARATION = 5;
  public int JJTVAREDGEDECLARATION = 6;
  public int JJTVARPATHDECLARATION = 7;
  public int JJTPATHOPERATOR = 8;
  public int JJTPATHLENGTH = 9;
  public int JJTVARIABLENAME = 10;
  public int JJTTYPEEXPRESSION = 11;
  public int JJTORTYPEEXPRESSION = 12;
  public int JJTANDTYPEEXPRESSION = 13;
  public int JJTNOTTYPEEXPRESSION = 14;
  public int JJTDATATYPE = 15;
  public int JJTQUERYWHERECLAUSE = 16;
  public int JJTQUERYWHEREEXPRESSION = 17;
  public int JJTORNODE = 18;
  public int JJTANDNODE = 19;
  public int JJTEQNODE = 20;
  public int JJTNENODE = 21;
  public int JJTMATNODE = 22;
  public int JJTNMATNODE = 23;
  public int JJTLTNODE = 24;
  public int JJTGTNODE = 25;
  public int JJTLENODE = 26;
  public int JJTGENODE = 27;
  public int JJTADDNODE = 28;
  public int JJTSUBTRACTNODE = 29;
  public int JJTMULNODE = 30;
  public int JJTDIVNODE = 31;
  public int JJTMODNODE = 32;
  public int JJTUNARYMINUSNODE = 33;
  public int JJTNOTNODE = 34;
  public int JJTVARACCESSOR = 35;
  public int JJTLISTLITERAL = 36;
  public int JJTSETLITERAL = 37;
  public int JJTINTEGERLITERAL = 38;
  public int JJTFLOATLITERAL = 39;
  public int JJTCHARACTERLITERAL = 40;
  public int JJTSTRINGLITERAL = 41;
  public int JJTBOOLEANLITERAL = 42;
  public int JJTFUNCTIONCALL = 43;
  public int JJTQUERYRETURNCLAUSE = 44;


  public String[] jjtNodeName = {
    "Query",
    "SimpleQuery",
    "SimpleQueryDeclaration",
    "QueryClause",
    "void",
    "VarNodeDeclaration",
    "VarEdgeDeclaration",
    "VarPathDeclaration",
    "PathOperator",
    "PathLength",
    "VariableName",
    "TypeExpression",
    "OrTypeExpression",
    "AndTypeExpression",
    "NotTypeExpression",
    "DataType",
    "QueryWhereClause",
    "QueryWhereExpression",
    "OrNode",
    "AndNode",
    "EQNode",
    "NENode",
    "MatNode",
    "NMatNode",
    "LTNode",
    "GTNode",
    "LENode",
    "GENode",
    "AddNode",
    "SubtractNode",
    "MulNode",
    "DivNode",
    "ModNode",
    "UnaryMinusNode",
    "NotNode",
    "VarAccessor",
    "ListLiteral",
    "SetLiteral",
    "IntegerLiteral",
    "FloatLiteral",
    "CharacterLiteral",
    "StringLiteral",
    "BooleanLiteral",
    "FunctionCall",
    "QueryReturnClause",
  };
}
/* JavaCC - OriginalChecksum=0407bff5b0f70b4717d501fb179ed38f (do not edit this line) */
