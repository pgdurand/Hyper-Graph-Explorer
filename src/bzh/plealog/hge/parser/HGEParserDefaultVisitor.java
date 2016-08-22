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

public class HGEParserDefaultVisitor implements HGEParserVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEQuery node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGESimpleQuery node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGESimpleQueryDeclaration node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEQueryClause node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEVarNodeDeclaration node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEVarEdgeDeclaration node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEVarPathDeclaration node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEPathOperator node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEPathLength node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEVariableName node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGETypeExpression node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEOrTypeExpression node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEAndTypeExpression node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGENotTypeExpression node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEDataType node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEQueryWhereClause node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEQueryWhereExpression node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEOrNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEAndNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEEQNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGENENode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEMatNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGENMatNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGELTNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEGTNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGELENode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEGENode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEAddNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGESubtractNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEMulNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEDivNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEModNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEUnaryMinusNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGENotNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEVarAccessor node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEListLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGESetLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEIntegerLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEFloatLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGECharacterLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEStringLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEBooleanLiteral node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEFunctionCall node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(HGEQueryReturnClause node, Object data){
    return defaultVisit(node, data);
  }
}
/* JavaCC - OriginalChecksum=41ce3981b57be6fcae2a52f907e1978a (do not edit this line) */
