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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a set.
 * 
 * @author Patrick G. Durand
 * */
public class HGESet extends HGEArgumentValue
{
  public static final HGESet CONST_STRING = new HGESet(DGMAttribute.DT_STRING);
  public static final HGESet CONST_INT = new HGESet(DGMAttribute.DT_LONG);
  public static final HGESet CONST_DOUBLE = new HGESet(DGMAttribute.DT_DOUBLE);
  public static final HGESet CONST_CHAR = new HGESet(DGMAttribute.DT_CHARACTER);
  public static final HGESet CONST_BOOL = new HGESet(DGMAttribute.DT_BOOLEAN);

  private HashSet<Object>   _set;

  private HGESet()
  {
    _set = new HashSet<Object>();
    setDataType(-1);
    setContainerType(DGMAttribute.CT_SET);
  }

  private HGESet(int dataType){
    this();
    setDataType(dataType);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGESet(HGESet val)
  {
    this();
    setValue(val.getValue());
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGESet(Set<?> val)
  {
    this();
    setValue(val);
  }

  /**
   * Get the value.
   * 
   * @return the set
   * */
  public Set<?> getValue()
  {
    return _set;
  }

  /**
   * Return an iterator over the content of this set.
   * 
   * @return an iterator over the content of this set*/
  public Iterator<?> getValues()
  {
    return _set.iterator();
  }

  /**
   * @see HGEValue#settValue()
   * */
  public Set<?> settValue(){
    return(_set);
  }

  /**
   * @see HGEValue#setSetValue(Set)
   * */
  public void setSetValue(Set<?> val){
    setValue(val);
  }

  /**
   * Return the size of the set.
   */
  public int size(){
    return _set.size();
  }

  /**
   * Figure out whether or not this set is empty.
   * 
   * @return true or false
   * */
  public boolean isEmpty(){
    return _set.isEmpty();
  }

  /**Figure out whether or not this set contains an object.
   * 
   * @return true or false
   * */
  public boolean contains(Object obj){
    return (_set.contains(obj));
  }

  /*a Set of Java basic Objects*/
  private void setValue(Set<?> set)
  {
    Iterator<?> iter;
    _set.clear();
    iter = set.iterator();
    while(iter.hasNext()){
      _set.add(iter.next());
    }
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return getValue().toString();
  }

  /**
   * Compare two sets of objects.
   * 
   * @param set1 first set
   * @param set2 second set
   * 
   * @return true if two sets are exactly the same. Same size of same objects.
   * */
  private boolean compareSet(Set<?> set1, Set<?> set2){
    Iterator<?> iter;

    if (set1.size()!=set2.size())
      return (false);
    iter = set1.iterator();
    while (iter.hasNext()){
      if (set2.contains(iter.next()) == false)
        return (false);
    }
    return (true);
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGESet){
        return (compareSet(((HGESet) obj).getValue(), _set));
      }
      else if(obj instanceof Set){
        return (compareSet((Set<?>) obj, _set));
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
    return _set!=null ? _set.hashCode() : super.hashCode();
  }

}
