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
package bzh.plealog.hge.dataholder;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import bzh.plealog.hge.engine.HGEEvaluable;

/**
 * Define a query.
 * 
 * @author Patrick G. Durand
 * */
public class HGEQueryDeclaration extends HGEVarDeclaration{

  protected Hashtable<String, HGEGraphVarDeclaration> _graphs;
  protected ArrayList<HGEEvaluable> _constraints;
  protected ArrayList<String> _returnVars;
  protected Hashtable<String, HGEVarDeclaration> _allVars;
  protected boolean   _distinct;

  protected HGEQueryDeclaration(){}

  /**
   * Constructor.
   * 
   * @param name name of the query
   * 
   * */
  public HGEQueryDeclaration(String name){
    setName(name);
  }
  /**
   * @see Object#clone()
   * */
  public Object clone(){
    HGEQueryDeclaration var;

    var = new HGEQueryDeclaration();
    var.copy(this);
    return var;
  }
  /**
   * Copy this object.
   * 
   * @param src the object to copy
   * */
  public void copy(HGEQueryDeclaration src){
    super.copy(src);
    this.setGraphs(new Hashtable<String, HGEGraphVarDeclaration>(src.getGraphs()));
    this.setConstraints(new ArrayList<HGEEvaluable>(src.getConstraints()));
    this.setReturnVars(new ArrayList<String>(src.getReturnVars()));
  }

  private void createAllVarMap(){
    HGEGraphVarDeclaration                graph;
    Hashtable<String, HGEVarDeclaration>  vars;
    String                                key;
    Enumeration<String>                   enum1, enum2;

    _allVars = new Hashtable<String, HGEVarDeclaration>();
    enum1 = _graphs.keys();
    while(enum1.hasMoreElements()){
      key = enum1.nextElement().toString();
      graph = (HGEGraphVarDeclaration) _graphs.get(key);
      //_allVars.put(key, graph);
      vars = graph.getVars();
      enum2 = vars.keys();
      while(enum2.hasMoreElements()){
        key = enum2.nextElement().toString();
        _allVars.put(key, vars.get(key));
      }
    }
  }

  /**
   * Set the graph variable declarations.
   * 
   * @param graphs graph variable declarations
   * */
  public void setGraphs(Hashtable<String, HGEGraphVarDeclaration> graphs){
    _graphs = graphs;
    createAllVarMap();
  }

  /**
   * Get the graph variable declarations.
   * 
   * @return graph variable declarations
   * */
  public Hashtable<String, HGEGraphVarDeclaration> getGraphs(){
    return _graphs;
  }

  /**
   * Set the constraints of this query.
   * 
   * @param constraints list of constraints
   * */
  public void setConstraints(ArrayList<HGEEvaluable> constraints){
    _constraints = constraints;
  }

  /**
   * Get the constraints of this query.
   * 
   * @return list of constraints
   * */
  public ArrayList<HGEEvaluable> getConstraints(){
    return _constraints;
  }

  /**
   * Set the list of variables for which the query engine has to return corresponding values.
   * 
   * @param returnVars list of variables
   * */
  public void setReturnVars(ArrayList<String> returnVars){
    _returnVars = returnVars;
  }

  /**
   * Set the list of variables for which the query engine has to return corresponding values.
   * Return empty means that the user request to see all the variables in the result.
   * 
   * @return list of variables
   * */
  public ArrayList<String> getReturnVars(){
    return _returnVars;
  }

  /**
   * Return all variable declarations but graph ones.
   * 
   * @return all variable declarations but graph ones
   * */
  public Hashtable<String, HGEVarDeclaration> getAllVars(){
    return _allVars;
  }
  /**
   * Return a variable declaration.
   * 
   * @param name name of the variable
   * 
   * @return corresponding variable declaration. Does not work for graph variables. In such
   * a case, if argument name targets graph variable, then this method returns null.
   * */
  public HGEVarDeclaration getVariable(String name){
    return ((HGEVarDeclaration) _allVars.get(name));
  }

  /**
   * Setup this query to return distinct values.
   * 
   * @param d true or false
   * */
  public void setDistinctReturnVars(boolean d){
    _distinct = d;
  }

  /**
   * Figure out is this query return distinct values.
   * 
   * @return true or false
   * */
  public boolean getDistinctReturnVars(){
    return (_distinct);
  }
}
