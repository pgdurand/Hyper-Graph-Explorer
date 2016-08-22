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
import java.util.Iterator;
import java.util.List;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;


/**
 * Define a list.
 * 
 * @author Patrick G. Durand
 * */
public class HGEList extends HGEArgumentValue
{
  public static final HGEList CONST_STRING = new HGEList(DGMAttribute.DT_STRING);
  public static final HGEList CONST_INT = new HGEList(DGMAttribute.DT_LONG);
  public static final HGEList CONST_DOUBLE = new HGEList(DGMAttribute.DT_DOUBLE);
  public static final HGEList CONST_CHAR = new HGEList(DGMAttribute.DT_CHARACTER);
  public static final HGEList CONST_BOOL = new HGEList(DGMAttribute.DT_BOOLEAN);
  public static final HGEList CONST_DGOBJECT = new HGEList(DGMAttribute.DT_GRAPH_OBJ);

  private ArrayList<Object> _list;

  
  private HGEList()
  {
    _list = new ArrayList<Object>();
    setDataType(-1);
    setContainerType(DGMAttribute.CT_LIST);
  }

  /**
   * Constructor.
   * 
   * @param dataType one of DGMAttribute.DT_XXX
   * */
  private HGEList(int dataType){
    this();
    setDataType(dataType);
  }

  /**
   * Constructor.
   * @param val a list of values
   * */
  public HGEList(List<?> val)
  {
    this();
    setValue(val);
  }

  /**
   * Constructor.
   * @param val another list
   * */
  public HGEList(HGEList val)
  {
    this();
    setValue(val.getValue());
  }

  /**
   * Get the list of values.
   * 
   * @return the list of values
   * */
  public List<?> getValue()
  {
    return _list;
  }

  /**
   * Get an value at a particular position in the list.
   * 
   * @return the object
   * */
  public Object getValue(int i)
  {
    return _list.get(i);
  }

  /**
   * @see HGEValue#setListValue(List)
   * */
  public void setListValue(List<?> val){
    setValue(val);
  }

  /*a list of Java basic Objects*/
  private void setValue(List<?> val){
    Iterator<?> iter;

    _list.clear();
    iter = val.iterator();
    while(iter.hasNext()){
      _list.add(iter.next());
    }
  }
  /**
   * @see HGEValue#listValue()
   * */
  public List<?> listValue(){
    return _list;
  }

  /**
   * Return the size of the list.
   * @return size of the list
   * */
  public int size(){
    return _list.size();
  }

  /**
   * Figure out whether or not the list is empty.
   * 
   * @return true or false
   * */
  public boolean isEmpty(){
    return _list.isEmpty();
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return getValue().toString();
  }

  /**
   * Compare two lists of objects.
   * 
   * @param l1 first list
   * @param l2 second list
   * 
   * @return true if two lists are exactly the same. Same size of same objects.
   * */
  private boolean compareList(List<?> l1, List<?> l2){
    int i, size;

    size = l1.size();
    if (size!=l2.size())
      return (false);
    for (i=0;i<size;i++){
      if (l1.get(i).equals(l2.get(i)) == false)
        return (false);
    }
    return (true);
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGEList){
        return (compareList(((HGEList) obj).getValue(),this.getValue()));
      }
      else if(obj instanceof List){
        return (compareList((List<?>) obj,this.getValue()));
      }
    }
    return (false);
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   **/
  public void interpret(HGEExecutionContext context, HGEStack stack){
    stack.push(this);
  }

  /**
   * @see Object#hashCode()
   * */
  public int hashCode(){
    return _list!=null ? _list.hashCode() : super.hashCode();
  }

}
