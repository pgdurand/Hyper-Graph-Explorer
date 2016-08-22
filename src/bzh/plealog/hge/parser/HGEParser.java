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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

import bzh.plealog.hge.dataholder.*;

public class HGEParser/*@bgen(jjtree)*/implements HGEParserTreeConstants, HGEParserConstants {/*@bgen(jjtree)*/
  protected JJTHGEParserState jjtree = new JJTHGEParserState();private static String removeQuotes(Token t){
        return (t.image.substring(1, t.image.length()-1));
    }

/*
 * Start - Process a QueryClause
 */
  final public HGEQuery Query() throws ParseException {
     /*@bgen(jjtree) Query */
  HGEQuery jjtn000 = new HGEQuery(JJTQUERY);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(FROM);
      SimpleQuery();
      jj_consume_token(RETURN);
      QueryReturnClause();
      jj_consume_token(SEMICOLON);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public HGESimpleQuery SimpleQuery() throws ParseException {
     /*@bgen(jjtree) SimpleQuery */
  HGESimpleQuery jjtn000 = new HGESimpleQuery(JJTSIMPLEQUERY);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      SimpleQueryDeclaration();
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AMP:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        jj_consume_token(AMP);
        SimpleQueryDeclaration();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHERE:
        jj_consume_token(WHERE);
        QueryWhereClause();
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public HGESimpleQueryDeclaration SimpleQueryDeclaration() throws ParseException {
     /*@bgen(jjtree) SimpleQueryDeclaration */
  HGESimpleQueryDeclaration jjtn000 = new HGESimpleQueryDeclaration(JJTSIMPLEQUERYDECLARATION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      VariableName();
      jj_consume_token(AS);
      QueryClause();
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

/*    HGESimpleQuery SimpleQuery() :
    {}
    {
        ( QueryClause()
          [<WHERE> QueryWhereClause()]
        )
        { return jjtThis; }
    }
*/
  final public HGEQueryClause QueryClause() throws ParseException {
     /*@bgen(jjtree) QueryClause */
  HGEQueryClause jjtn000 = new HGEQueryClause(JJTQUERYCLAUSE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      QueryClauseDeclaration();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(COMMA);
        QueryClauseDeclaration();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void QueryClauseDeclaration() throws ParseException {
    if (jj_2_1(2)) {
      VarEdgeDeclaration();
    } else if (jj_2_2(2)) {
      VarPathDeclaration();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        VarNodeDeclaration();
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

/*
 * End - Process a QueryClause
 */

/*
 * Start - Process a VarNodeDeclaration
 */
  final public HGEVarNodeDeclaration VarNodeDeclaration() throws ParseException {
     /*@bgen(jjtree) VarNodeDeclaration */
     HGEVarNodeDeclaration jjtn000 = new HGEVarNodeDeclaration(JJTVARNODEDECLARATION);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      VariableName();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IN:
        jj_consume_token(IN);
        TypeExpression();
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

/*
 * End - Process a VarNodeDeclaration
 */

/*
 * Start - Process an VarEdgeDeclaration
 */
  final public HGEVarEdgeDeclaration VarEdgeDeclaration() throws ParseException {
     /*@bgen(jjtree) VarEdgeDeclaration */
     HGEVarEdgeDeclaration jjtn000 = new HGEVarEdgeDeclaration(JJTVAREDGEDECLARATION);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      VariableName();
      jj_consume_token(COLON);
      VariableName();
      label_3:
      while (true) {
        jj_consume_token(MINUS);
        VariableName();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MINUS:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IN:
        jj_consume_token(IN);
        TypeExpression();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

/*
 * End - Process an VarEdgeDeclaration
 */

/*
 * Start - Process a VarPathDeclaration
 */
  final public HGEVarPathDeclaration VarPathDeclaration() throws ParseException {
     /*@bgen(jjtree) VarPathDeclaration */
     HGEVarPathDeclaration jjtn000 = new HGEVarPathDeclaration(JJTVARPATHDECLARATION);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      VariableName();
      jj_consume_token(OPENPAR);
      VariableName();
      jj_consume_token(COMMA);
      VariableName();
      jj_consume_token(COMMA);
      PathOperator();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        jj_consume_token(COMMA);
        PathLength();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          jj_consume_token(COMMA);
          PathLength();
          break;
        default:
          jj_la1[7] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      jj_consume_token(CLOSEPAR);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void PathOperator() throws ParseException {
     /*@bgen(jjtree) PathOperator */
     HGEPathOperator jjtn000 = new HGEPathOperator(JJTPATHOPERATOR);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEXT:
        t = jj_consume_token(NEXT);
        break;
      case CONNECT:
        t = jj_consume_token(CONNECT);
        break;
      case UNTIL:
        t = jj_consume_token(UNTIL);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         jjtn000.setValue(new HGEString(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void PathLength() throws ParseException {
     /*@bgen(jjtree) PathLength */
     HGEPathLength jjtn000 = new HGEPathLength(JJTPATHLENGTH);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         jjtn000.setValue(new HGELong(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

/*
 * End - Process a VarPathDeclaration
 */

/*
 * Start - used within a Node/Edge/Path Declaration
 */
  final public void VariableName() throws ParseException {
     /*@bgen(jjtree) VariableName */
     HGEVariableName jjtn000 = new HGEVariableName(JJTVARIABLENAME);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         jjtn000.setValue(new HGEIdentifier(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

/*
 * End - used within a Node/Edge/Path Declaration
 */

/*
 * Start - Process a TypeExpression (declaration of Node/Edge data type expression)
 */
  final public HGETypeExpression TypeExpression() throws ParseException {
     /*@bgen(jjtree) TypeExpression */
  HGETypeExpression jjtn000 = new HGETypeExpression(JJTTYPEEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      OrTypeExpression();
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void OrTypeExpression() throws ParseException {
    AndTypeExpression();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      jj_consume_token(OR);
                     HGEOrTypeExpression jjtn001 = new HGEOrTypeExpression(JJTORTYPEEXPRESSION);
                     boolean jjtc001 = true;
                     jjtree.openNodeScope(jjtn001);
      try {
        AndTypeExpression();
      } catch (Throwable jjte001) {
                     if (jjtc001) {
                       jjtree.clearNodeScope(jjtn001);
                       jjtc001 = false;
                     } else {
                       jjtree.popNode();
                     }
                     if (jjte001 instanceof RuntimeException) {
                       {if (true) throw (RuntimeException)jjte001;}
                     }
                     if (jjte001 instanceof ParseException) {
                       {if (true) throw (ParseException)jjte001;}
                     }
                     {if (true) throw (Error)jjte001;}
      } finally {
                     if (jjtc001) {
                       jjtree.closeNodeScope(jjtn001,  2);
                     }
      }
    }
  }

  final public void AndTypeExpression() throws ParseException {
    UnaryTypeExpression();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_5;
      }
      jj_consume_token(AND);
                  HGEAndTypeExpression jjtn001 = new HGEAndTypeExpression(JJTANDTYPEEXPRESSION);
                  boolean jjtc001 = true;
                  jjtree.openNodeScope(jjtn001);
      try {
        UnaryTypeExpression();
      } catch (Throwable jjte001) {
                  if (jjtc001) {
                    jjtree.clearNodeScope(jjtn001);
                    jjtc001 = false;
                  } else {
                    jjtree.popNode();
                  }
                  if (jjte001 instanceof RuntimeException) {
                    {if (true) throw (RuntimeException)jjte001;}
                  }
                  if (jjte001 instanceof ParseException) {
                    {if (true) throw (ParseException)jjte001;}
                  }
                  {if (true) throw (Error)jjte001;}
      } finally {
                  if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001,  2);
                  }
      }
    }
  }

  final public void UnaryTypeExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
                HGENotTypeExpression jjtn001 = new HGENotTypeExpression(JJTNOTTYPEEXPRESSION);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
      try {
        UnaryTypeExpression();
      } catch (Throwable jjte001) {
                if (jjtc001) {
                  jjtree.clearNodeScope(jjtn001);
                  jjtc001 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte001 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte001;}
                }
                if (jjte001 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte001;}
                }
                {if (true) throw (Error)jjte001;}
      } finally {
                if (jjtc001) {
                  jjtree.closeNodeScope(jjtn001,  1);
                }
      }
      break;
    case OPENPAR:
    case STRING_LITERAL:
      PrimaryTypeExpression();
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void PrimaryTypeExpression() throws ParseException {
        Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING_LITERAL:
      DataType();
      break;
    case OPENPAR:
      jj_consume_token(OPENPAR);
      TypeExpression();
      jj_consume_token(CLOSEPAR);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void DataType() throws ParseException {
     /*@bgen(jjtree) DataType */
     HGEDataType jjtn000 = new HGEDataType(JJTDATATYPE);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         jjtn000.setValue(new HGEString(removeQuotes(t)));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

/*
 * End - Process a TypeExpression (declaration of Node/Edge data type expression)
 */

/*
 * Start - process a QueryWhereClause
 */
  final public HGEQueryWhereClause QueryWhereClause() throws ParseException {
     /*@bgen(jjtree) QueryWhereClause */
  HGEQueryWhereClause jjtn000 = new HGEQueryWhereClause(JJTQUERYWHERECLAUSE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      QueryWhereExpression();
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_6;
        }
        jj_consume_token(COMMA);
        QueryWhereExpression();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void QueryWhereExpression() throws ParseException {
     /*@bgen(jjtree) QueryWhereExpression */
  HGEQueryWhereExpression jjtn000 = new HGEQueryWhereExpression(JJTQUERYWHEREEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      ConditionalOrExpression();
    } catch (Throwable jjte000) {
        if (jjtc000) {
          jjtree.clearNodeScope(jjtn000);
          jjtc000 = false;
        } else {
          jjtree.popNode();
        }
        if (jjte000 instanceof RuntimeException) {
          {if (true) throw (RuntimeException)jjte000;}
        }
        if (jjte000 instanceof ParseException) {
          {if (true) throw (ParseException)jjte000;}
        }
        {if (true) throw (Error)jjte000;}
    } finally {
        if (jjtc000) {
          jjtree.closeNodeScope(jjtn000, true);
        }
    }
  }

  final public void ConditionalOrExpression() throws ParseException {
    ConditionalAndExpression();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_7;
      }
      jj_consume_token(OR);
               HGEOrNode jjtn001 = new HGEOrNode(JJTORNODE);
               boolean jjtc001 = true;
               jjtree.openNodeScope(jjtn001);
      try {
        ConditionalAndExpression();
      } catch (Throwable jjte001) {
               if (jjtc001) {
                 jjtree.clearNodeScope(jjtn001);
                 jjtc001 = false;
               } else {
                 jjtree.popNode();
               }
               if (jjte001 instanceof RuntimeException) {
                 {if (true) throw (RuntimeException)jjte001;}
               }
               if (jjte001 instanceof ParseException) {
                 {if (true) throw (ParseException)jjte001;}
               }
               {if (true) throw (Error)jjte001;}
      } finally {
               if (jjtc001) {
                 jjtree.closeNodeScope(jjtn001,  2);
               }
      }
    }
  }

  final public void ConditionalAndExpression() throws ParseException {
    EqualityExpression();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_8;
      }
      jj_consume_token(AND);
                HGEAndNode jjtn001 = new HGEAndNode(JJTANDNODE);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
      try {
        EqualityExpression();
      } catch (Throwable jjte001) {
                if (jjtc001) {
                  jjtree.clearNodeScope(jjtn001);
                  jjtc001 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte001 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte001;}
                }
                if (jjte001 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte001;}
                }
                {if (true) throw (Error)jjte001;}
      } finally {
                if (jjtc001) {
                  jjtree.closeNodeScope(jjtn001,  2);
                }
      }
    }
  }

  final public void EqualityExpression() throws ParseException {
    RelationalExpression();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
      case NEQ:
      case MAT:
      case NMAT:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
        jj_consume_token(EQ);
                HGEEQNode jjtn001 = new HGEEQNode(JJTEQNODE);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
        try {
          RelationalExpression();
        } catch (Throwable jjte001) {
                if (jjtc001) {
                  jjtree.clearNodeScope(jjtn001);
                  jjtc001 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte001 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte001;}
                }
                if (jjte001 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte001;}
                }
                {if (true) throw (Error)jjte001;}
        } finally {
                if (jjtc001) {
                  jjtree.closeNodeScope(jjtn001,  2);
                }
        }
        break;
      case NEQ:
        jj_consume_token(NEQ);
                HGENENode jjtn002 = new HGENENode(JJTNENODE);
                boolean jjtc002 = true;
                jjtree.openNodeScope(jjtn002);
        try {
          RelationalExpression();
        } catch (Throwable jjte002) {
                if (jjtc002) {
                  jjtree.clearNodeScope(jjtn002);
                  jjtc002 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte002 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte002;}
                }
                if (jjte002 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte002;}
                }
                {if (true) throw (Error)jjte002;}
        } finally {
                if (jjtc002) {
                  jjtree.closeNodeScope(jjtn002,  2);
                }
        }
        break;
      case MAT:
        jj_consume_token(MAT);
                HGEMatNode jjtn003 = new HGEMatNode(JJTMATNODE);
                boolean jjtc003 = true;
                jjtree.openNodeScope(jjtn003);
        try {
          RelationalExpression();
        } catch (Throwable jjte003) {
                if (jjtc003) {
                  jjtree.clearNodeScope(jjtn003);
                  jjtc003 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte003 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte003;}
                }
                if (jjte003 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte003;}
                }
                {if (true) throw (Error)jjte003;}
        } finally {
                if (jjtc003) {
                  jjtree.closeNodeScope(jjtn003,  2);
                }
        }
        break;
      case NMAT:
        jj_consume_token(NMAT);
                HGENMatNode jjtn004 = new HGENMatNode(JJTNMATNODE);
                boolean jjtc004 = true;
                jjtree.openNodeScope(jjtn004);
        try {
          RelationalExpression();
        } catch (Throwable jjte004) {
                if (jjtc004) {
                  jjtree.clearNodeScope(jjtn004);
                  jjtc004 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte004 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte004;}
                }
                if (jjte004 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte004;}
                }
                {if (true) throw (Error)jjte004;}
        } finally {
                if (jjtc004) {
                  jjtree.closeNodeScope(jjtn004,  2);
                }
        }
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void RelationalExpression() throws ParseException {
    AdditiveExpression();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
      case LTE:
      case GT:
      case GTE:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_10;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        jj_consume_token(LT);
              HGELTNode jjtn001 = new HGELTNode(JJTLTNODE);
              boolean jjtc001 = true;
              jjtree.openNodeScope(jjtn001);
        try {
          AdditiveExpression();
        } catch (Throwable jjte001) {
              if (jjtc001) {
                jjtree.clearNodeScope(jjtn001);
                jjtc001 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte001 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte001;}
              }
              if (jjte001 instanceof ParseException) {
                {if (true) throw (ParseException)jjte001;}
              }
              {if (true) throw (Error)jjte001;}
        } finally {
              if (jjtc001) {
                jjtree.closeNodeScope(jjtn001,  2);
              }
        }
        break;
      case GT:
        jj_consume_token(GT);
              HGEGTNode jjtn002 = new HGEGTNode(JJTGTNODE);
              boolean jjtc002 = true;
              jjtree.openNodeScope(jjtn002);
        try {
          AdditiveExpression();
        } catch (Throwable jjte002) {
              if (jjtc002) {
                jjtree.clearNodeScope(jjtn002);
                jjtc002 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte002 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte002;}
              }
              if (jjte002 instanceof ParseException) {
                {if (true) throw (ParseException)jjte002;}
              }
              {if (true) throw (Error)jjte002;}
        } finally {
              if (jjtc002) {
                jjtree.closeNodeScope(jjtn002,  2);
              }
        }
        break;
      case LTE:
        jj_consume_token(LTE);
               HGELENode jjtn003 = new HGELENode(JJTLENODE);
               boolean jjtc003 = true;
               jjtree.openNodeScope(jjtn003);
        try {
          AdditiveExpression();
        } catch (Throwable jjte003) {
               if (jjtc003) {
                 jjtree.clearNodeScope(jjtn003);
                 jjtc003 = false;
               } else {
                 jjtree.popNode();
               }
               if (jjte003 instanceof RuntimeException) {
                 {if (true) throw (RuntimeException)jjte003;}
               }
               if (jjte003 instanceof ParseException) {
                 {if (true) throw (ParseException)jjte003;}
               }
               {if (true) throw (Error)jjte003;}
        } finally {
               if (jjtc003) {
                 jjtree.closeNodeScope(jjtn003,  2);
               }
        }
        break;
      case GTE:
        jj_consume_token(GTE);
               HGEGENode jjtn004 = new HGEGENode(JJTGENODE);
               boolean jjtc004 = true;
               jjtree.openNodeScope(jjtn004);
        try {
          AdditiveExpression();
        } catch (Throwable jjte004) {
               if (jjtc004) {
                 jjtree.clearNodeScope(jjtn004);
                 jjtc004 = false;
               } else {
                 jjtree.popNode();
               }
               if (jjte004 instanceof RuntimeException) {
                 {if (true) throw (RuntimeException)jjte004;}
               }
               if (jjte004 instanceof ParseException) {
                 {if (true) throw (ParseException)jjte004;}
               }
               {if (true) throw (Error)jjte004;}
        } finally {
               if (jjtc004) {
                 jjtree.closeNodeScope(jjtn004,  2);
               }
        }
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void AdditiveExpression() throws ParseException {
    MultiplicativeExpression();
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_11;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
              HGEAddNode jjtn001 = new HGEAddNode(JJTADDNODE);
              boolean jjtc001 = true;
              jjtree.openNodeScope(jjtn001);
        try {
          MultiplicativeExpression();
        } catch (Throwable jjte001) {
              if (jjtc001) {
                jjtree.clearNodeScope(jjtn001);
                jjtc001 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte001 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte001;}
              }
              if (jjte001 instanceof ParseException) {
                {if (true) throw (ParseException)jjte001;}
              }
              {if (true) throw (Error)jjte001;}
        } finally {
              if (jjtc001) {
                jjtree.closeNodeScope(jjtn001,  2);
              }
        }
        break;
      case MINUS:
        jj_consume_token(MINUS);
              HGESubtractNode jjtn002 = new HGESubtractNode(JJTSUBTRACTNODE);
              boolean jjtc002 = true;
              jjtree.openNodeScope(jjtn002);
        try {
          MultiplicativeExpression();
        } catch (Throwable jjte002) {
              if (jjtc002) {
                jjtree.clearNodeScope(jjtn002);
                jjtc002 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte002 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte002;}
              }
              if (jjte002 instanceof ParseException) {
                {if (true) throw (ParseException)jjte002;}
              }
              {if (true) throw (Error)jjte002;}
        } finally {
              if (jjtc002) {
                jjtree.closeNodeScope(jjtn002,  2);
              }
        }
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void MultiplicativeExpression() throws ParseException {
    UnaryExpression();
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
      case DIV:
      case MOD:
        ;
        break;
      default:
        jj_la1[23] = jj_gen;
        break label_12;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        jj_consume_token(MULT);
              HGEMulNode jjtn001 = new HGEMulNode(JJTMULNODE);
              boolean jjtc001 = true;
              jjtree.openNodeScope(jjtn001);
        try {
          UnaryExpression();
        } catch (Throwable jjte001) {
              if (jjtc001) {
                jjtree.clearNodeScope(jjtn001);
                jjtc001 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte001 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte001;}
              }
              if (jjte001 instanceof ParseException) {
                {if (true) throw (ParseException)jjte001;}
              }
              {if (true) throw (Error)jjte001;}
        } finally {
              if (jjtc001) {
                jjtree.closeNodeScope(jjtn001,  2);
              }
        }
        break;
      case DIV:
        jj_consume_token(DIV);
              HGEDivNode jjtn002 = new HGEDivNode(JJTDIVNODE);
              boolean jjtc002 = true;
              jjtree.openNodeScope(jjtn002);
        try {
          UnaryExpression();
        } catch (Throwable jjte002) {
              if (jjtc002) {
                jjtree.clearNodeScope(jjtn002);
                jjtc002 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte002 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte002;}
              }
              if (jjte002 instanceof ParseException) {
                {if (true) throw (ParseException)jjte002;}
              }
              {if (true) throw (Error)jjte002;}
        } finally {
              if (jjtc002) {
                jjtree.closeNodeScope(jjtn002,  2);
              }
        }
        break;
      case MOD:
        jj_consume_token(MOD);
              HGEModNode jjtn003 = new HGEModNode(JJTMODNODE);
              boolean jjtc003 = true;
              jjtree.openNodeScope(jjtn003);
        try {
          UnaryExpression();
        } catch (Throwable jjte003) {
              if (jjtc003) {
                jjtree.clearNodeScope(jjtn003);
                jjtc003 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte003 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte003;}
              }
              if (jjte003 instanceof ParseException) {
                {if (true) throw (ParseException)jjte003;}
              }
              {if (true) throw (Error)jjte003;}
        } finally {
              if (jjtc003) {
                jjtree.closeNodeScope(jjtn003,  2);
              }
        }
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void UnaryExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
      jj_consume_token(MINUS);
            HGEUnaryMinusNode jjtn001 = new HGEUnaryMinusNode(JJTUNARYMINUSNODE);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
      try {
        UnaryExpression();
      } catch (Throwable jjte001) {
            if (jjtc001) {
              jjtree.clearNodeScope(jjtn001);
              jjtc001 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte001 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte001;}
            }
            if (jjte001 instanceof ParseException) {
              {if (true) throw (ParseException)jjte001;}
            }
            {if (true) throw (Error)jjte001;}
      } finally {
            if (jjtc001) {
              jjtree.closeNodeScope(jjtn001,  1);
            }
      }
      break;
    case 49:
      jj_consume_token(49);
            HGENotNode jjtn002 = new HGENotNode(JJTNOTNODE);
            boolean jjtc002 = true;
            jjtree.openNodeScope(jjtn002);
      try {
        UnaryExpression();
      } catch (Throwable jjte002) {
            if (jjtc002) {
              jjtree.clearNodeScope(jjtn002);
              jjtc002 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte002 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte002;}
            }
            if (jjte002 instanceof ParseException) {
              {if (true) throw (ParseException)jjte002;}
            }
            {if (true) throw (Error)jjte002;}
      } finally {
            if (jjtc002) {
              jjtree.closeNodeScope(jjtn002,  1);
            }
      }
      break;
    case OPENPAR:
    case TRUE:
    case FALSE:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case 50:
    case 52:
      PrimaryExpression();
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void PrimaryExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      ListLiteral();
      jj_consume_token(51);
      break;
    case 52:
      jj_consume_token(52);
      SetLiteral();
      jj_consume_token(53);
      break;
    default:
      jj_la1[26] = jj_gen;
      if (jj_2_3(2)) {
        FunctionCall();
      } else if (jj_2_4(2)) {
        VarAccessor();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_LITERAL:
          IntegerLiteral();
          break;
        case FLOATING_POINT_LITERAL:
          FloatLiteral();
          break;
        case TRUE:
        case FALSE:
          BooleanLiteral();
          break;
        case STRING_LITERAL:
          StringLiteral();
          break;
        case CHARACTER_LITERAL:
          CharacterLiteral();
          break;
        case OPENPAR:
          jj_consume_token(OPENPAR);
          QueryWhereExpression();
          jj_consume_token(CLOSEPAR);
          break;
        default:
          jj_la1[27] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  final public void VarAccessor() throws ParseException {
     /*@bgen(jjtree) VarAccessor */
        HGEVarAccessor jjtn000 = new HGEVarAccessor(JJTVARACCESSOR);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);ArrayList al = new ArrayList();
        String    varID=null;
        Token     t;
    try {
      t = jj_consume_token(IDENTIFIER);
                             varID=t.image;
      label_13:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
          ;
          break;
        default:
          jj_la1[28] = jj_gen;
          break label_13;
        }
        jj_consume_token(DOT);
        t = jj_consume_token(IDENTIFIER);
                               al.add(t.image);
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
            if (al.isEmpty())
                jjtn000.setValue(new HGEVarInstance(new HGEIdentifier(varID)));
            else
                jjtn000.setValue(new HGEVarAccess(new HGEIdentifier(varID), al));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void ListLiteral() throws ParseException {
     /*@bgen(jjtree) ListLiteral */
        HGEListLiteral jjtn000 = new HGEListLiteral(JJTLISTLITERAL);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);ArrayList al = new ArrayList();
        HashSet control = new HashSet();
        Token     t;
    try {
      BasicLiteral(al, control);
      label_14:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[29] = jj_gen;
          break label_14;
        }
        jj_consume_token(COMMA);
        BasicLiteral(al, control);
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGEList(al));
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void SetLiteral() throws ParseException {
     /*@bgen(jjtree) SetLiteral */
        HGESetLiteral jjtn000 = new HGESetLiteral(JJTSETLITERAL);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);HashSet set = new HashSet();
        HashSet control = new HashSet();
        Token   t;
    try {
      BasicLiteral(set, control);
      label_15:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[30] = jj_gen;
          break label_15;
        }
        jj_consume_token(COMMA);
        BasicLiteral(set, control);
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGESet(set));
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void BasicLiteral(Collection list, Set control) throws ParseException {
     Token t; String str;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      t = jj_consume_token(INTEGER_LITERAL);
              if (!control.contains(t.image)) {control.add(t.image); list.add(new HGELong(t.image)); }
      break;
    case FLOATING_POINT_LITERAL:
      t = jj_consume_token(FLOATING_POINT_LITERAL);
              if (!control.contains(t.image)) {control.add(t.image); list.add(new HGEDouble(t.image)); }
      break;
    case TRUE:
    case FALSE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        t = jj_consume_token(TRUE);
        break;
      case FALSE:
        t = jj_consume_token(FALSE);
        break;
      default:
        jj_la1[31] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
              if (!control.contains(t.image)) {control.add(t.image); list.add(new HGEBoolean(t.image)); }
      break;
    case STRING_LITERAL:
      t = jj_consume_token(STRING_LITERAL);
                str = t.image.substring(1,t.image.length()-1);
                if (!control.contains(str)) {control.add(str); list.add(new HGEString(str)); }
      break;
    case CHARACTER_LITERAL:
      t = jj_consume_token(CHARACTER_LITERAL);
                str = String.valueOf(t.image.charAt(1));
                if (!control.contains(str)) {control.add(str); list.add(new HGECharacter(str)); }
      break;
    default:
      jj_la1[32] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void IntegerLiteral() throws ParseException {
     /*@bgen(jjtree) IntegerLiteral */
     HGEIntegerLiteral jjtn000 = new HGEIntegerLiteral(JJTINTEGERLITERAL);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGELong(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void FloatLiteral() throws ParseException {
     /*@bgen(jjtree) FloatLiteral */
     HGEFloatLiteral jjtn000 = new HGEFloatLiteral(JJTFLOATLITERAL);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(FLOATING_POINT_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGEDouble(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void CharacterLiteral() throws ParseException {
     /*@bgen(jjtree) CharacterLiteral */
     HGECharacterLiteral jjtn000 = new HGECharacterLiteral(JJTCHARACTERLITERAL);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(CHARACTER_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGECharacter(t.image.charAt(1)));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void StringLiteral() throws ParseException {
     /*@bgen(jjtree) StringLiteral */
     HGEStringLiteral jjtn000 = new HGEStringLiteral(JJTSTRINGLITERAL);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING_LITERAL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGEString(t.image.substring(1,t.image.length()-1)));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void BooleanLiteral() throws ParseException {
     /*@bgen(jjtree) BooleanLiteral */
     HGEBooleanLiteral jjtn000 = new HGEBooleanLiteral(JJTBOOLEANLITERAL);
     boolean jjtc000 = true;
     jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        t = jj_consume_token(TRUE);
        break;
      case FALSE:
        t = jj_consume_token(FALSE);
        break;
      default:
        jj_la1[33] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setValue(new HGEBoolean(t.image));
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void FunctionCall() throws ParseException {
     /*@bgen(jjtree) FunctionCall */
        HGEFunctionCall jjtn000 = new HGEFunctionCall(JJTFUNCTIONCALL);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token        t;
        int          args=0;
        HGEFunction func;
    try {
      t = jj_consume_token(IDENTIFIER);
      jj_consume_token(OPENPAR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS:
      case OPENPAR:
      case TRUE:
      case FALSE:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case 49:
      case 50:
      case 52:
        args = FunctionArgs();
        break;
      default:
        jj_la1[34] = jj_gen;
        ;
      }
      jj_consume_token(CLOSEPAR);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
            func = new HGEFunction(t.image);
            func.setArgs(args);
            jjtn000.setValue(func);
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public int FunctionArgs() throws ParseException {
     int args=0;
    ArgAdditiveExpression();
                                 args++;
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[35] = jj_gen;
        break label_16;
      }
      jj_consume_token(COMMA);
      ArgAdditiveExpression();
                                            args++;
    }
            {if (true) return(args);}
    throw new Error("Missing return statement in function");
  }

  final public void ArgAdditiveExpression() throws ParseException {
    ArgMultiplicativeExpression();
    label_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[36] = jj_gen;
        break label_17;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
              HGEAddNode jjtn001 = new HGEAddNode(JJTADDNODE);
              boolean jjtc001 = true;
              jjtree.openNodeScope(jjtn001);
        try {
          ArgMultiplicativeExpression();
        } catch (Throwable jjte001) {
              if (jjtc001) {
                jjtree.clearNodeScope(jjtn001);
                jjtc001 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte001 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte001;}
              }
              if (jjte001 instanceof ParseException) {
                {if (true) throw (ParseException)jjte001;}
              }
              {if (true) throw (Error)jjte001;}
        } finally {
              if (jjtc001) {
                jjtree.closeNodeScope(jjtn001,  2);
              }
        }
        break;
      case MINUS:
        jj_consume_token(MINUS);
              HGESubtractNode jjtn002 = new HGESubtractNode(JJTSUBTRACTNODE);
              boolean jjtc002 = true;
              jjtree.openNodeScope(jjtn002);
        try {
          ArgMultiplicativeExpression();
        } catch (Throwable jjte002) {
              if (jjtc002) {
                jjtree.clearNodeScope(jjtn002);
                jjtc002 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte002 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte002;}
              }
              if (jjte002 instanceof ParseException) {
                {if (true) throw (ParseException)jjte002;}
              }
              {if (true) throw (Error)jjte002;}
        } finally {
              if (jjtc002) {
                jjtree.closeNodeScope(jjtn002,  2);
              }
        }
        break;
      default:
        jj_la1[37] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void ArgMultiplicativeExpression() throws ParseException {
    ArgUnaryExpression();
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
      case DIV:
      case MOD:
        ;
        break;
      default:
        jj_la1[38] = jj_gen;
        break label_18;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        jj_consume_token(MULT);
              HGEMulNode jjtn001 = new HGEMulNode(JJTMULNODE);
              boolean jjtc001 = true;
              jjtree.openNodeScope(jjtn001);
        try {
          ArgUnaryExpression();
        } catch (Throwable jjte001) {
              if (jjtc001) {
                jjtree.clearNodeScope(jjtn001);
                jjtc001 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte001 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte001;}
              }
              if (jjte001 instanceof ParseException) {
                {if (true) throw (ParseException)jjte001;}
              }
              {if (true) throw (Error)jjte001;}
        } finally {
              if (jjtc001) {
                jjtree.closeNodeScope(jjtn001,  2);
              }
        }
        break;
      case DIV:
        jj_consume_token(DIV);
              HGEDivNode jjtn002 = new HGEDivNode(JJTDIVNODE);
              boolean jjtc002 = true;
              jjtree.openNodeScope(jjtn002);
        try {
          ArgUnaryExpression();
        } catch (Throwable jjte002) {
              if (jjtc002) {
                jjtree.clearNodeScope(jjtn002);
                jjtc002 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte002 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte002;}
              }
              if (jjte002 instanceof ParseException) {
                {if (true) throw (ParseException)jjte002;}
              }
              {if (true) throw (Error)jjte002;}
        } finally {
              if (jjtc002) {
                jjtree.closeNodeScope(jjtn002,  2);
              }
        }
        break;
      case MOD:
        jj_consume_token(MOD);
              HGEModNode jjtn003 = new HGEModNode(JJTMODNODE);
              boolean jjtc003 = true;
              jjtree.openNodeScope(jjtn003);
        try {
          ArgUnaryExpression();
        } catch (Throwable jjte003) {
              if (jjtc003) {
                jjtree.clearNodeScope(jjtn003);
                jjtc003 = false;
              } else {
                jjtree.popNode();
              }
              if (jjte003 instanceof RuntimeException) {
                {if (true) throw (RuntimeException)jjte003;}
              }
              if (jjte003 instanceof ParseException) {
                {if (true) throw (ParseException)jjte003;}
              }
              {if (true) throw (Error)jjte003;}
        } finally {
              if (jjtc003) {
                jjtree.closeNodeScope(jjtn003,  2);
              }
        }
        break;
      default:
        jj_la1[39] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void ArgUnaryExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
      jj_consume_token(MINUS);
            HGEUnaryMinusNode jjtn001 = new HGEUnaryMinusNode(JJTUNARYMINUSNODE);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
      try {
        ArgUnaryExpression();
      } catch (Throwable jjte001) {
            if (jjtc001) {
              jjtree.clearNodeScope(jjtn001);
              jjtc001 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte001 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte001;}
            }
            if (jjte001 instanceof ParseException) {
              {if (true) throw (ParseException)jjte001;}
            }
            {if (true) throw (Error)jjte001;}
      } finally {
            if (jjtc001) {
              jjtree.closeNodeScope(jjtn001,  1);
            }
      }
      break;
    case 49:
      jj_consume_token(49);
            HGENotNode jjtn002 = new HGENotNode(JJTNOTNODE);
            boolean jjtc002 = true;
            jjtree.openNodeScope(jjtn002);
      try {
        ArgUnaryExpression();
      } catch (Throwable jjte002) {
            if (jjtc002) {
              jjtree.clearNodeScope(jjtn002);
              jjtc002 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte002 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte002;}
            }
            if (jjte002 instanceof ParseException) {
              {if (true) throw (ParseException)jjte002;}
            }
            {if (true) throw (Error)jjte002;}
      } finally {
            if (jjtc002) {
              jjtree.closeNodeScope(jjtn002,  1);
            }
      }
      break;
    case OPENPAR:
    case TRUE:
    case FALSE:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case 50:
    case 52:
      ArgPrimaryExpression();
      break;
    default:
      jj_la1[40] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void ArgPrimaryExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      ListLiteral();
      jj_consume_token(51);
      break;
    case 52:
      jj_consume_token(52);
      SetLiteral();
      jj_consume_token(53);
      break;
    default:
      jj_la1[41] = jj_gen;
      if (jj_2_5(2)) {
        FunctionCall();
      } else if (jj_2_6(2)) {
        VarAccessor();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_LITERAL:
          IntegerLiteral();
          break;
        case FLOATING_POINT_LITERAL:
          FloatLiteral();
          break;
        case TRUE:
        case FALSE:
          BooleanLiteral();
          break;
        case STRING_LITERAL:
          StringLiteral();
          break;
        case CHARACTER_LITERAL:
          CharacterLiteral();
          break;
        case OPENPAR:
          jj_consume_token(OPENPAR);
          ArgAdditiveExpression();
          jj_consume_token(CLOSEPAR);
          break;
        default:
          jj_la1[42] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

/**/
/*
 * End - process a QueryWhereClause
 */

/*
 * Start - process a ReturnClause
 */
  final public HGEQueryReturnClause QueryReturnClause() throws ParseException {
     /*@bgen(jjtree) QueryReturnClause */
    HGEQueryReturnClause jjtn000 = new HGEQueryReturnClause(JJTQUERYRETURNCLAUSE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);boolean bDistinct=false;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DISTINCT:
        jj_consume_token(DISTINCT);
                    bDistinct=true;
        break;
      default:
        jj_la1[43] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        jj_consume_token(MULT);
        break;
      case IDENTIFIER:
        VariableName();
        label_19:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case COMMA:
            ;
            break;
          default:
            jj_la1[44] = jj_gen;
            break label_19;
          }
          jj_consume_token(COMMA);
          VariableName();
        }
        break;
      default:
        jj_la1[45] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
                jjtn000.setValue((bDistinct ? HGEBoolean.TRUE : HGEBoolean.FALSE));
                {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  final private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  final private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  final private boolean jj_3_1() {
    if (jj_3R_20()) return true;
    return false;
  }

  final private boolean jj_3R_23() {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_25()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_24() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  final private boolean jj_3_6() {
    if (jj_3R_23()) return true;
    return false;
  }

  final private boolean jj_3_4() {
    if (jj_3R_23()) return true;
    return false;
  }

  final private boolean jj_3_3() {
    if (jj_3R_22()) return true;
    return false;
  }

  final private boolean jj_3_5() {
    if (jj_3R_22()) return true;
    return false;
  }

  final private boolean jj_3R_22() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(OPENPAR)) return true;
    return false;
  }

  final private boolean jj_3R_20() {
    if (jj_3R_24()) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  final private boolean jj_3R_25() {
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  final private boolean jj_3R_21() {
    if (jj_3R_24()) return true;
    if (jj_scan_token(OPENPAR)) return true;
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_3R_21()) return true;
    return false;
  }

  public HGEParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[46];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_0();
      jj_la1_1();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x0,0x20000000,0x8000000,0x0,0x0,0x80,0x0,0x8000000,0x8000000,0x0,0x200000,0x80000,0x500000,0x400000,0x8000000,0x200000,0x80000,0x78000,0x78000,0x7800,0x7800,0xc0,0xc0,0x700,0x700,0x400080,0x0,0x400000,0x2000000,0x8000000,0x8000000,0x0,0x0,0x0,0x400080,0x8000000,0xc0,0xc0,0x700,0x700,0x400080,0x0,0x400000,0x0,0x8000000,0x100,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x100,0x0,0x0,0x4000,0x8,0x0,0x8,0x0,0x0,0x7,0x0,0x0,0x2000,0x2000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x167630,0x140000,0x3630,0x0,0x0,0x0,0x30,0x3630,0x30,0x167630,0x0,0x0,0x0,0x0,0x0,0x167630,0x140000,0x3630,0x40,0x0,0x4000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[6];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public HGEParser(java.io.InputStream stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new HGEParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public HGEParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new HGEParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public HGEParser(HGEParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(HGEParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 46; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[54];
    for (int i = 0; i < 54; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 46; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 54; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 6; i++) {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
          }
        }
        p = p.next;
      } while (p != null);
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
