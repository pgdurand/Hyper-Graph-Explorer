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
package bzh.plealog.hge.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.datamodel.DataGraphModel;
import bzh.plealog.hge.api.function.Function;
import bzh.plealog.hge.api.function.FunctionSystem;
import bzh.plealog.hge.dataholder.HGEAbstractDataType;
import bzh.plealog.hge.dataholder.HGEBoolean;
import bzh.plealog.hge.dataholder.HGEDType;
import bzh.plealog.hge.dataholder.HGEEdgeDataType;
import bzh.plealog.hge.dataholder.HGEEdgeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEFunction;
import bzh.plealog.hge.dataholder.HGEGraphVarDeclaration;
import bzh.plealog.hge.dataholder.HGEList;
import bzh.plealog.hge.dataholder.HGENodeDataType;
import bzh.plealog.hge.dataholder.HGENodeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEPathVarDeclaration;
import bzh.plealog.hge.dataholder.HGESet;
import bzh.plealog.hge.dataholder.HGEVarAccess;
import bzh.plealog.hge.dataholder.HGEVarDeclaration;
import bzh.plealog.hge.dataholder.HGEVarInstance;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEUtils;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.operator.HGEAdd;
import bzh.plealog.hge.operator.HGEBooleanAnd;
import bzh.plealog.hge.operator.HGEBooleanNot;
import bzh.plealog.hge.operator.HGEBooleanOr;
import bzh.plealog.hge.operator.HGEDivide;
import bzh.plealog.hge.operator.HGEEqual;
import bzh.plealog.hge.operator.HGEGreaterThan;
import bzh.plealog.hge.operator.HGEGreaterThanEqual;
import bzh.plealog.hge.operator.HGELessThan;
import bzh.plealog.hge.operator.HGELessThanEqual;
import bzh.plealog.hge.operator.HGEMatch;
import bzh.plealog.hge.operator.HGEModulo;
import bzh.plealog.hge.operator.HGEMultiply;
import bzh.plealog.hge.operator.HGENotEqual;
import bzh.plealog.hge.operator.HGENotMatch;
import bzh.plealog.hge.operator.HGESubtract;
import bzh.plealog.hge.operator.HGEUnaryMinus;
import bzh.plealog.hge.parser.HGEAddNode;
import bzh.plealog.hge.parser.HGEAndNode;
import bzh.plealog.hge.parser.HGEAndTypeExpression;
import bzh.plealog.hge.parser.HGEBooleanLiteral;
import bzh.plealog.hge.parser.HGECharacterLiteral;
import bzh.plealog.hge.parser.HGEDataType;
import bzh.plealog.hge.parser.HGEDivNode;
import bzh.plealog.hge.parser.HGEEQNode;
import bzh.plealog.hge.parser.HGEFloatLiteral;
import bzh.plealog.hge.parser.HGEFunctionCall;
import bzh.plealog.hge.parser.HGEGENode;
import bzh.plealog.hge.parser.HGEGTNode;
import bzh.plealog.hge.parser.HGEIntegerLiteral;
import bzh.plealog.hge.parser.HGELENode;
import bzh.plealog.hge.parser.HGELTNode;
import bzh.plealog.hge.parser.HGEListLiteral;
import bzh.plealog.hge.parser.HGEMatNode;
import bzh.plealog.hge.parser.HGEModNode;
import bzh.plealog.hge.parser.HGEMulNode;
import bzh.plealog.hge.parser.HGENENode;
import bzh.plealog.hge.parser.HGENMatNode;
import bzh.plealog.hge.parser.HGENotNode;
import bzh.plealog.hge.parser.HGENotTypeExpression;
import bzh.plealog.hge.parser.HGEOrNode;
import bzh.plealog.hge.parser.HGEOrTypeExpression;
import bzh.plealog.hge.parser.HGEPathLength;
import bzh.plealog.hge.parser.HGEPathOperator;
import bzh.plealog.hge.parser.HGEQuery;
import bzh.plealog.hge.parser.HGEQueryClause;
import bzh.plealog.hge.parser.HGEQueryReturnClause;
import bzh.plealog.hge.parser.HGEQueryWhereClause;
import bzh.plealog.hge.parser.HGEQueryWhereExpression;
import bzh.plealog.hge.parser.HGESetLiteral;
import bzh.plealog.hge.parser.HGESimpleQuery;
import bzh.plealog.hge.parser.HGESimpleQueryDeclaration;
import bzh.plealog.hge.parser.HGEStringLiteral;
import bzh.plealog.hge.parser.HGESubtractNode;
import bzh.plealog.hge.parser.HGETypeExpression;
import bzh.plealog.hge.parser.HGEUnaryMinusNode;
import bzh.plealog.hge.parser.HGEVarAccessor;
import bzh.plealog.hge.parser.HGEVarEdgeDeclaration;
import bzh.plealog.hge.parser.HGEVarNodeDeclaration;
import bzh.plealog.hge.parser.HGEVarPathDeclaration;
import bzh.plealog.hge.parser.HGEVariableName;
import bzh.plealog.hge.parser.SimpleNode;


/**
 * Defines an HGE query syntax validator.
 * 
 * @author Patrick G. Durand
 */
public class HGEValidator extends HGEVisitor 
{
  private DataGraphModel _dom;

  private Hashtable<String, HGEVarDeclaration> _allVariables;

  private Hashtable<String, HGEVarDeclaration> _graphVariables;

  private Hashtable<String, HGEGraphVarDeclaration> _graphs;

  private ArrayList<String> _returnVars;

  private ArrayList<HGEEvaluable> _constraints;

  private ArrayList<HGEEvaluable> _typeExpression;

  private ArrayList<Object> _tmpList;

  private boolean   _returnDistinctValues;

  //private HGEValidator(){}

  /**
   * Constructor.
   * 
   * @param dom the data graph model requires to setup this validation object.
   */
  public HGEValidator(DataGraphModel dom) {
    if (dom==null)
      reportError("Data model is not defined.");
    _dom = dom;
    _allVariables = new Hashtable<String, HGEVarDeclaration>();
    _graphVariables = new Hashtable<String, HGEVarDeclaration>();
    _graphs = new Hashtable<String, HGEGraphVarDeclaration>();
    _typeExpression = new ArrayList<HGEEvaluable>();
    _tmpList = new ArrayList<Object>();
    _returnVars = new ArrayList<String>();
    _constraints = new ArrayList<HGEEvaluable>();
  }

  public Hashtable<String, HGEGraphVarDeclaration> getGraphs(){
    return _graphs;
  }

  public ArrayList<HGEEvaluable> getConstraints(){
    return _constraints;
  }

  public ArrayList<String> getReturnVars(){
    return _returnVars;
  }

  public boolean getReturnDistinctVars(){
    return _returnDistinctValues;
  }

  public Object visit(SimpleNode node, Object data){
    return(data);
  }

  public Object visit(HGEQuery node, Object data){
    visitChildren(node, data);
    checkAllVarExist();
    checkQueryConnectivity();
    return(data);
  }

  public Object visit(HGESimpleQuery node, Object data){
    visitChildren(node, data);
    return(data);
  }

  public Object visit(HGESimpleQueryDeclaration node, Object data){
    HGEGraphVarDeclaration  gv;
    String                   gName;

    gName = getIdentifierValue(getChildValue(node, 0));
    if (_allVariables.containsKey(gName))
      reportError("variable "+gName+ " cannot be defined more than once.");
    _graphVariables.clear();
    visitChildren(node, "g");

    gv = new HGEGraphVarDeclaration(gName);
    gv.setVars(new Hashtable<String, HGEVarDeclaration>(_graphVariables));
    _allVariables.put(gName, gv);
    _graphs.put(gName, gv);
    //System.out.println("graph name: "+ gName +", "+_graphVariables.size()+"vars.");
    //System.out.println(gv);
    return(data);
  }

  public Object visit(HGEQueryClause node, Object data){
    visitChildren(node, data);
    return(data);
  }

  public Object visit(HGEVarNodeDeclaration node, Object data) {
    HGENodeVarDeclaration cv;
    String                  cName;

    cName = getIdentifierValue(getChildValue(node, 0));
    if (_allVariables.containsKey(cName))
      reportError("variable "+cName+ " cannot be defined more than once.");

    try{
      _typeExpression.clear();
      visitChildren(node, "c");
    }
    catch (Exception ex){
      reportError("In "+ cName + ": "+ex.getMessage());
    }

    cv = new HGENodeVarDeclaration(cName);	
    if (!_typeExpression.isEmpty())
      cv.setType(new ArrayList<HGEEvaluable>(_typeExpression));
    _allVariables.put(cName, cv);
    _graphVariables.put(cName, cv);
    //System.out.println(cv);

    return(data);
  }

  @SuppressWarnings("unchecked")
  public Object visit(HGEVarEdgeDeclaration node, Object data){
    HGEEdgeVarDeclaration av;
    String                 aName;

    aName = getIdentifierValue(getChildValue(node, 0));
    if (_allVariables.containsKey(aName))
      reportError("variable "+aName+ " cannot be defined more than once.");

    try{
      _typeExpression.clear();
      _tmpList.clear();
      visitChildren(node, "a");
      _tmpList.remove(0);
    }
    catch (Exception ex){
      reportError("In "+ aName + ": "+ex.getMessage());
    }
    av = new HGEEdgeVarDeclaration(aName,(ArrayList<String>) _tmpList.clone());	
    if (!_typeExpression.isEmpty())
      av.setType(new ArrayList<HGEEvaluable>(_typeExpression));
    _allVariables.put(aName, av);
    _graphVariables.put(aName, av);
    //System.out.println(av);
    return(data);
  }

  public Object visit(HGEVarPathDeclaration node, Object data){
    HGEPathVarDeclaration pv;
    String                 pName, fName, tName, ope;

    pName = getIdentifierValue(getChildValue(node, 0));

    if (_allVariables.containsKey(pName))
      reportError("variable "+pName+ " cannot be defined more than once.");

    fName = getIdentifierValue(getChildValue(node, 1));
    tName = getIdentifierValue(getChildValue(node, 2));
    ope = getStringValue(getChildValue(node, 3));

    pv = new HGEPathVarDeclaration(pName, fName, tName, ope);

    if (node.jjtGetNumChildren()>4){
      pv.setMinLength((int) getIntegerValue(getChildValue(node, 4)));
      if (node.jjtGetNumChildren()>5){
        pv.setMaxLength((int) getIntegerValue(getChildValue(node, 5)));
      }
    }
    _allVariables.put(pName, pv);
    _graphVariables.put(pName, pv);
    //System.out.println(pv);

    return(data);
  }

  public Object visit(HGEPathOperator node, Object data){
    return(data);
  }

  public Object visit(HGEPathLength node, Object data){
    return(data);
  }

  public Object visit(HGEVariableName node, Object data){
    if (data.equals("a")){
      _tmpList.add(getIdentifierValue(node.getValue()));
    }
    else if (data.equals("r")){
      _returnVars.add(getIdentifierValue(node.getValue()));
    }
    return(data);
  }

  public Object visit(HGETypeExpression node, Object data){
    visitChildren(node, data);
    return(data);
  }

  public Object visit(HGEOrTypeExpression node, Object data){
    visitChildren(node, data);
    _typeExpression.add(new HGEBooleanOr());
    return(data);
  }

  public Object visit(HGEAndTypeExpression node, Object data){
    visitChildren(node, data);
    _typeExpression.add(new HGEBooleanAnd());
    return(data);
  }

  public Object visit(HGENotTypeExpression node, Object data){
    visitChildren(node, data);
    _typeExpression.add(new HGEBooleanNot());
    return(data);
  }

  public Object visit(HGEDataType node, Object data) {
    String              name;
    HGEAbstractDataType dType = null;

    name = getStringValue(node.getValue());

    if (data.equals("c")){
      if (_dom.getVertexType(name)==null)
        reportError(name + " is not a Class data type.");
      else
        dType = new HGENodeDataType(name);
    }
    else if (data.equals("a")){
      if (_dom.getHyperEdgeType(name)==null)
        reportError(name + " is not an Association data type.");
      else
        dType = new HGEEdgeDataType(name);
    }
    else {
      reportError(name + " is neither a node nor an edge data type.");
    }

    _typeExpression.add(dType);
    return(data);
  }

  @SuppressWarnings("unchecked")
  public Object visit(HGEQueryWhereClause node, Object data){
    HGEExecutionContext context;
    HGEStack            stack;
    int                  i, size;

    _tmpList.clear();
    visitChildren(node, data);
    //System.out.println("visit constraint: "+_tmpList.toString());
    _constraints = (ArrayList<HGEEvaluable>) _tmpList.clone();
    //System.out.println("constraint: "+_constraints.toString());
    size = _constraints.size();
    stack = new HGEStack();
    context = new HGEExecutionContext(null, null);
    try{
      for(i=0;i<size;i++){
        ((HGEInterpretable)_constraints.get(i)).interpret(context, stack);
        //System.out.println("last stacked is:"+stack.peek().getClass().getName());
        //System.out.println(stack);
      }
      if (stack.size()!=1 && (stack.peek() instanceof HGEBoolean)==false)
        reportError("constraint expression does not return a single boolean value.");
      stack.pop().setInUse(false);
    }
    catch (Exception ex){
      reportError("constraint expression is invalid: " + ex.getMessage());
    }

    return(data);
  }

  public Object visit(HGEQueryWhereExpression node, Object data){
    visitChildren(node, data);
    return(data);
  }

  public Object visit(HGEOrNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEBooleanOr());
    return(data);
  }

  public Object visit(HGEAndNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEBooleanAnd());
    return(data);
  }

  public Object visit(HGEEQNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEEqual());
    return(data);
  }

  public Object visit(HGENENode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGENotEqual());
    return(data);
  }
  public Object visit(HGEMatNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEMatch());
    return(data);
  }
  public Object visit(HGENMatNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGENotMatch());
    return(data);
  }

  public Object visit(HGELTNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGELessThan());
    return(data);
  }

  public Object visit(HGEGTNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEGreaterThan());
    return(data);
  }

  public Object visit(HGELENode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGELessThanEqual());
    return(data);
  }

  public Object visit(HGEGENode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEGreaterThanEqual());
    return(data);
  }

  public Object visit(HGEAddNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEAdd());
    return(data);
  }

  public Object visit(HGESubtractNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGESubtract());
    return(data);
  }

  public Object visit(HGEMulNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEMultiply());
    return(data);
  }

  public Object visit(HGEDivNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEDivide());
    return(data);
  }

  public Object visit(HGEModNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEModulo());
    return(data);
  }

  public Object visit(HGEUnaryMinusNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEUnaryMinus());
    return(data);
  }

  public Object visit(HGENotNode node, Object data){
    visitChildren(node, data);
    _tmpList.add(new HGEBooleanNot());
    return(data);
  }

  public Object visit(HGEVarAccessor node, Object data){
    HGEValue       nodeValue;
    HGEVarAccess   varAccess;
    HGEVarInstance varInstance;
    DGMAttribute    dAtt;
    String          varName, dName, att;
    List<?>         path, types;
    Object          obj;
    int             i, size;

    nodeValue = (HGEValue) node.getValue();
    if (nodeValue instanceof HGEVarAccess){//varAccess=v1.Length
      varAccess = (HGEVarAccess) nodeValue;
      varName = varAccess.getVarID().getValue();
      if (_allVariables.containsKey(varName)==false)
        reportError("Attribute accessor variable "+varName+" is used in the constraint, but is not defined.");
      obj = _allVariables.get(varName);
      if (obj instanceof HGEGraphVarDeclaration)
        reportError("Attribute accessor variable "+varName+" refers to a graph: it does not have any attributes.");
      if (obj instanceof HGEPathVarDeclaration)
        reportError("Attribute accessor variable "+varName+" refers to a path: it does not have any attributes.");
      path = varAccess.getPath();
      if (path.size()!=1)
        reportError("In "+varAccess.toString()+": path size is invalid.");
      //System.out.println("var is: "+obj.toString());
      types = ((HGENodeVarDeclaration)obj).getType();
      size = types.size();
      att = path.get(0).toString();
      //System.out.println("att:"+att);
      for(i=0;i<size;i++){
        obj = types.get(i);
        if (obj instanceof HGEDType){
          dName = ((HGEDType)obj).getType();
          //System.out.println("node dName:"+dName);
          dAtt = _dom.getType(dName).getAttribute(att);
          if (dAtt==null){
            reportError("Attribute accessor variable "+
                varAccess.toString()+
                ": attribute "+att+" does not exist in "+dName+".");
          }
          varAccess.setDataType(dAtt.getJavaType());
          varAccess.setContainerType(dAtt.getContainerType());
        }
      }
    }
    else{//varInstance=instance (node, edge, path) associated to v1
      varInstance = (HGEVarInstance) nodeValue;
      varName = varInstance.getVarID().getValue();
      if (_allVariables.containsKey(varName)==false)
        reportError("Instance variable "+varName+" is used in the constraint, but is not defined.");
      obj = _allVariables.get(varName);
      if (obj instanceof HGEGraphVarDeclaration)//graph not allowed for now
        reportError("Instance variable "+varName+" refers to a graph: not allowed in the constraint.");
      varInstance.setDataType(DGMAttribute.DT_GRAPH_OBJ);
      if (obj instanceof HGEPathVarDeclaration)//path:list of edges
        varInstance.setContainerType(DGMAttribute.CT_LIST);
      else//node, edge: atomic
        varInstance.setContainerType(DGMAttribute.CT_ATOMIC);
    }
    _tmpList.add(nodeValue);
    return(data);
  }

  public Object visit(HGEIntegerLiteral node, Object data){
    visitChildren(node, data);
    _tmpList.add(node.getValue());
    return(data);
  }

  public Object visit(HGEFloatLiteral node, Object data){
    visitChildren(node, data);
    _tmpList.add(node.getValue());
    return(data);
  }

  public Object visit(HGECharacterLiteral node, Object data){
    visitChildren(node, data);
    _tmpList.add(node.getValue());
    return(data);
  }

  public Object visit(HGEStringLiteral node, Object data){
    visitChildren(node, data);
    String str = node.getValue().toString();
    if (HGEUtils.isDate(str))
      _tmpList.add(HGEUtils.getDateHGERepr(str));
    else
      _tmpList.add(node.getValue());
    return(data);
  }

  public Object visit(HGEBooleanLiteral node, Object data){
    visitChildren(node, data);
    _tmpList.add(node.getValue());
    return(data);
  }

  public Object visit(HGEListLiteral node, Object data){
    HGEList   eList;

    visitChildren(node, data);
    eList = (HGEList) node.getValue();
    eList.setDataType(checkCollection(eList.getValue()));
    _tmpList.add(eList);
    //System.out.println(eList.getClass().getName()+": "+node.getValue());
    return(data);
  }

  public Object visit(HGESetLiteral node, Object data){
    HGESet    eSet;

    visitChildren(node, data);
    eSet = (HGESet) node.getValue();
    eSet.setDataType(checkCollection(eSet.getValue()));
    _tmpList.add(eSet);
    //System.out.println(eSet.getClass().getName()+": "+node.getValue());
    return(data);
  }

  public Object visit(HGEFunctionCall node, Object data){
    HGEFunction func;
    Function     declaredFunc;

    visitChildren(node, data);
    func = (HGEFunction) node.getValue();
    declaredFunc = FunctionSystem.getFunction(func.getName());
    if (declaredFunc==null)
      reportError("Function "+func.getName()+" is not defined.");
    if (declaredFunc.arguments()!=func.getArgs()){
      reportError("Invalid number of arguments in function "+
          func.getName()+". Expected: "+declaredFunc.arguments()+
          ", Found: "+func.getArgs()+".");
    }
    _tmpList.add(func);
    return(data);
  }

  public Object visit(HGEQueryReturnClause node, Object data){
    visitChildren(node, "r");
    _returnDistinctValues = node.getValue().booleanValue();
    return(data);
  }

  private int checkCollection(Collection<?> coll){
    int          dataType=-1;
    boolean      bFirst=true;
    HGEValue     value;
    Iterator<?>  iter;

    iter = coll.iterator();
    while(iter.hasNext()){
      value = (HGEValue) iter.next();
      if (bFirst){
        bFirst=false;
        dataType=value.getDataType();
        continue;
      }
      if(dataType!=value.getDataType())
        reportError("collection is made of various data types.");
    }
    return (dataType);
  }


  private void checkAllVarExist(){
    HGEEdgeVarDeclaration edge;
    HGEPathVarDeclaration path;
    Enumeration<?>        enum1;
    Object                obj;
    String                var;
    ArrayList<?>          al;
    int                   i, size;

    enum1 = _allVariables.elements();
    while(enum1.hasMoreElements()){
      obj = enum1.nextElement();
      if (obj instanceof HGEEdgeVarDeclaration){
        edge = (HGEEdgeVarDeclaration) obj;
        al = edge.getNodeVars();
        size = al.size();
        for (i=0;i<size;i++){
          var = al.get(i).toString();
          if (_allVariables.containsKey(var)==false)
            reportError("In edge "+edge.getName()+", "+var+" is not defined.");
        }
      }
      else if (obj instanceof HGEPathVarDeclaration){
        path = (HGEPathVarDeclaration) obj;
        var = path.getFromNodeVar();
        if (_allVariables.containsKey(var)==false)
          reportError("In path "+path.getName()+", "+var+" is not defined.");
        var = path.getToNodeVar();
        if (_allVariables.containsKey(var)==false)
          reportError("In path "+path.getName()+", "+var+" is not defined.");
      }
    }
  }

  private HGEMapHyperGraph convertQuery(){
    HGEMapHyperGraph       g;
    HGEMapNode             node;
    HGEMapHyperEdge        edge;
    Hashtable<String, HGEMapNode>      nodes;
    Hashtable<String, HGEMapHyperEdge> edges;
    HGEEdgeVarDeclaration  vEdge;
    HGEPathVarDeclaration  vPath;
    Enumeration<HGEVarDeclaration>     enum1;
    Object                  obj;
    String                  name;
    ArrayList<String>       al;
    ArrayList<HGEMapNode>   nodeLinked;
    int                     i, size;

    g = new HGEMapHyperGraph();
    nodes = new Hashtable<String, HGEMapNode>();
    edges = new Hashtable<String, HGEMapHyperEdge>();

    enum1 = _allVariables.elements();
    while(enum1.hasMoreElements()){
      obj = enum1.nextElement();
      if (obj instanceof HGENodeVarDeclaration && 
          !(obj instanceof HGEEdgeVarDeclaration)){
        name = ((HGENodeVarDeclaration) obj).getName();
        if (nodes.containsKey(name)==false){
          node = new HGEMapNode(name);
          nodes.put(name, node);
          g.addNode(node);
        }
      }
    }

    nodeLinked = new ArrayList<HGEMapNode>();
    enum1 = _allVariables.elements();
    while(enum1.hasMoreElements()){
      obj = enum1.nextElement();
      if (obj instanceof HGEEdgeVarDeclaration){
        vEdge = (HGEEdgeVarDeclaration) obj;
        name = vEdge.getName();
        if (edges.containsKey(name)==false){
          al = vEdge.getNodeVars();
          size = al.size();
          nodeLinked.clear();
          for (i=0;i<size;i++){
            nodeLinked.add(nodes.get(al.get(i)));
          }
          edge = g.addHyperEdge(nodeLinked);
          edge.setUserObject(name);
          edges.put(name, edge);
        }
      }
      else if (obj instanceof HGEPathVarDeclaration){
        vPath = (HGEPathVarDeclaration) obj;
        name = vPath.getName();
        if (edges.containsKey(name)==false){
          nodeLinked.clear();
          nodeLinked.add(nodes.get(vPath.getFromNodeVar()));
          nodeLinked.add(nodes.get(vPath.getToNodeVar()));
          edge = g.addHyperEdge(nodeLinked);
          edges.put(name, edge);
          edge.setUserObject(name);
        }
      }
    }

    return (g);    
  }

  private void checkQueryConnectivity(){
    HGEMapHyperGraph g = convertQuery();
    MyBFSVisitor visitor = new MyBFSVisitor();
    BFSScanner   scanner = new BFSScanner();

    scanner.addVisitor(visitor);
    scanner.scan (g, null);
    if (visitor.getVisitedNodes()!=g.getNodes().size())
      reportError("query graph is not a single component.");

  }

  private class MyBFSVisitor implements BFSVisitor{ 
    private int nodes=0;

    public int getVisitedNodes(){
      return nodes;
    }

    public void visit (BFSNodeVisitedEvent nve){
      nodes++;
    }

    public void visit (BFSEdgeVisitedEvent eve){
    }
  }

}
