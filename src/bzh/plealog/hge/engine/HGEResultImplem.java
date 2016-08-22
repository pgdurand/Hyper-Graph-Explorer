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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;

import bzh.plealog.hge.api.query.HGEResult;

/** 
 * Implementation of HGEResult interface. In each HGEResult,
 * data is stored as set of key/value pairs. When a key is a vertex/edge variable,
 * value is a graph object (HDGVertex/HDGEdge). When key is a path variable, value
 * is a List of HDGEdge. When key is a graph variable, value is a HGEResult
 * object.
 * 
 * @author Patrick G. Durand
 */
public class HGEResultImplem implements HGEResult{
  private Hashtable<String, Object> _result;
  private String                    _stringRepr;
  private ArrayList<String>         _order;
  private int                       _hashCode;
  private boolean                   _codeComputed;
  private boolean                   _distinct;

  /**
   * Constructor.
   * */
  public HGEResultImplem(){
    _result = new Hashtable<String, Object>();
    _order = new ArrayList<String>();
  }

  public void setDistinctReturnVars(boolean d){
    _distinct = d;
  }

  /**
   * Figure whether or not one has to return distinct values.
   * 
   * @return true if query has to return distinct values.
   */
  public boolean getDistinctReturnVars(){
    return (_distinct);
  }

  /**
   * Add a new result item into the result.
   * 
   * @param varName the variable name for which a value has been located in the graph
   * @param obj can be a HDGVertex, a HDGEdge, a List-of HDGEdges (i.e. a path) or
   * a HGEResult (i.e. a graph).
   * */
  public void addValue(String varName, Object obj){
    _result.put(varName, obj);
    _order.add(varName);
    _stringRepr=null;
    _codeComputed=false;
  }

  /**
   * Return a data value given a query variable name.
   * 
   * @return can be a HDGVertex, a HDGEdge, a List-of HDGEdges (i.e. a path) or
   * a HGEResult (i.e. a graph)
   * */
  public Object getValue(String varName){
    return (_result.get(varName));
  }

  /**
   * @see Object#hashCode()
   * */
  public int hashCode(){
    String[]         table;
    Iterator<Object> iter;
    StringBuffer     szBuf;
    int              i, size;

    if (_codeComputed)
      return _hashCode;

    if (!_distinct){
      _hashCode = super.hashCode();
    }
    else{
      size = _result.size();
      table = new String[size];
      iter = _result.values().iterator();
      i=0;
      while(iter.hasNext()){
        table[i]=iter.next().toString();
        i++;
      }
      Arrays.sort(table);
      szBuf=new StringBuffer();
      for(i=0;i<size;i++){
        szBuf.append(table[i]);
      }
      _hashCode = szBuf.toString().hashCode();
    }
    _codeComputed=true;
    return (_hashCode);
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals(Object obj){
    if (obj==null || !(obj instanceof HGEResultImplem))
      return false;
    else
      return (this.hashCode()==((HGEResultImplem)obj).hashCode());
  }

  /**
   * @see Object#toString()
   * */
  public String toString(){
    StringBuffer     szBuf;
    Iterator<String> iter;
    String           key;

    if (_stringRepr!=null)
      return (_stringRepr);

    szBuf = new StringBuffer("[");
    iter = _order.iterator();
    while(iter.hasNext()){
      key = iter.next().toString();
      szBuf.append(key);
      szBuf.append("=");
      szBuf.append(getValue(key).toString());
      if (iter.hasNext())
        szBuf.append(" , ");
    }
    szBuf.append("]");
    _stringRepr = szBuf.toString();
    return (_stringRepr);
  }
}
