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
package bzh.plealog.hge.engine;


import bzh.plealog.hge.api.datamodel.DataGraphModel;
import bzh.plealog.hge.api.hypergraph.HDataGraph;


/**
 * Define the execution context of a query.
 * 
 * @author Patrick G. Durand
 * */
public class HGEExecutionContext {

  private DataGraphModel             _model;
  private HDataGraph                 _graph;
  private Object                     _obj;
  private HGEReusableArgumentValue[] _reusableArgs;
  private int                        _reusableArgsIncrement=10;

  private HGEExecutionContext(){}

  /**
   * Constructor. An execution context relies on a data graph model and a data graph.
   * 
   * @param model the data model
   * @param graph the data
   * */
  public HGEExecutionContext(DataGraphModel model, HDataGraph graph){
    this();
    int i;
    _model = model;
    _graph = graph;

    _reusableArgs = new HGEReusableArgumentValue[_reusableArgsIncrement];
    for (i=0;i<_reusableArgsIncrement;i++)
      _reusableArgs[i]=new HGEReusableArgumentValue();
  }

  /**
   * Get the data model.
   * 
   * @return the data model
   * */
  public DataGraphModel getDataGraphModel(){
    return (_model);
  }

  /**
   * Get the data.
   * 
   * @return the data
   * */
  public HDataGraph getDataGraph(){
    return (_graph);
  }

  /**
   * Get the variable instance object.
   * 
   * @return the variable instance object
   * */
  public Object getVarInstanceObject(){
    return (_obj);
  }

  /**
   * Set the variable instance object.
   * 
   * @param obj the variable instance object
   * */
  public void setVarInstanceObject(Object obj){
    _obj=obj;
  }

  /**
   * Get a reusable argument to be used in the query execution system.
   * 
   * @return a reusable argument
   * */
  public synchronized HGEReusableArgumentValue getReusableArg(){
    HGEReusableArgumentValue[] oldTable;
    int                      i, newSize, oldSize;

    oldSize = _reusableArgs.length;
    for(i=0;i<oldSize;i++){
      if (_reusableArgs[i].isInUse()==false){
        _reusableArgs[i].setInUse(true);
        return _reusableArgs[i];
      }
    }
    newSize = oldSize + _reusableArgsIncrement;
    oldTable = _reusableArgs;
    _reusableArgs = new HGEReusableArgumentValue[newSize];
    System.arraycopy(oldTable, 0, _reusableArgs, 0, oldSize);
    for (i=oldSize;i<newSize;i++){
      _reusableArgs[i]=new HGEReusableArgumentValue();
    }
    return _reusableArgs[oldSize];
  }
}
