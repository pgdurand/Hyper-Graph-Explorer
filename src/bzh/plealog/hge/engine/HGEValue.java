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

import java.util.List;
import java.util.Set;

import bzh.plealog.hge.api.hypergraph.HDGObject;


/**
 * Define a basic implementation of value that is both evaluable and interpretable 
 * in the execution system.
 * 
 * @author Patrick G. Durand
 * */
public abstract class HGEValue implements HGEInterpretable, HGEEvaluable {

  public static final int UNDEFINED = -1;

  protected int     _dataType;
  protected int     _containerType;
  protected boolean _inUse;

  /**
   * Default constructor.
   */
  public HGEValue(){ }

  /**
   * Set whether or not this value is used in the system.
   * 
   * @param inUse pass true if the value is used, false otherwise
   * */
  public void setInUse(boolean inUse){
    _inUse=inUse;
  }

  /**
   * Figure out whether or not this value is used in the system.
   * 
   * @return true if the value is used, false otherwise
   * */
  public boolean isInUse(){
    return _inUse;
  }

  /**
   * Set the data type.
   * 
   * @param dt one of DGMAttribute.DT_XXX
   * */
  public void setDataType(int dt){
    _dataType=dt;
  }

  /**
   * Get the data type.
   * 
   * @return one of DGMAttribute.DT_XXX
   * */
  public int getDataType(){
    return _dataType;
  }

  /**
   * Get the container type.
   * 
   * @return one of DGMAttribute.CT_XXX
   * */
  public int getContainerType(){
    return _containerType;
  }

  /**
   * Set the container type.
   * 
   * @param ct one of DGMAttribute.CT_XXX
   * */
  public void setContainerType(int ct){
    _containerType = ct;
  }

  /**
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)
   * */
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    interpret(context, stack);
  }

  /**
   * Get the boolean value associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public boolean booleanValue(){
    throw new UnsupportedOperationException("booleanValue()");
  }

  /**
   * Get the long value associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public long longValue(){
    throw new UnsupportedOperationException("longValue()");
  }

  /**
   * Get the double value associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * @return the value
   * */
  public double doubleValue(){
    throw new UnsupportedOperationException("doubleValue()");
  }
  /**
   * Get the char value associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public char charValue(){
    throw new UnsupportedOperationException("charValue()");
  }
  /**
   * Get the string value associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public String stringValue(){
    throw new UnsupportedOperationException("stringValue()");
  }

  /**
   * Get the data graph object associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public HDGObject dgObjectValue(){
    throw new UnsupportedOperationException("dgObjectValue()");
  }

  /**
   * Get the list of data associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  public List<?> listValue(){
    throw new UnsupportedOperationException("listValue()");
  }

  /**
   * Get the Set of data associated to this value.
   * 
   * Not implemented here, always throws an UnsupportedOperationException.
   * 
   * @return the value
   * */
  /*note that there are two 't' to avoid conflict with setter methods*/
  public Set<?> settValue(){
    throw new UnsupportedOperationException("settValue()");
  }

  /**
   * Set the boolean value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setBooleanValue(boolean val){
    throw new UnsupportedOperationException("setBooleanValue()");
  }
  /**
   * Set the long value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setLongValue(long val){
    throw new UnsupportedOperationException("setLongValue()");
  }
  /**
   * Set the double value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setDoubleValue(double val){
    throw new UnsupportedOperationException("setDoubleValue()");
  }
  /**
   * Set the char value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setCharValue(char val){
    throw new UnsupportedOperationException("setCharValue()");
  }
  /**
   * Set the string value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setStringValue(String val){
    throw new UnsupportedOperationException("setStringValue()");
  }

  /**
   * Set the data graph object associated to this value.
   * @param obj the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setDgObjectValue(HDGObject obj){
    throw new UnsupportedOperationException("setDgObjectValue()");
  }

  /**
   * Set the List value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  public void setListValue(List<?> val){
    throw new UnsupportedOperationException("setListValue()");
  }

  /**
   * Set the Set value associated to this value.
   * @param val the value
   * Not implemented here, always throws an UnsupportedOperationException.
   * */
  /*note that there are two 't' to avoid conflict with setter methods*/
  public void setSetValue(Set<?> val){
    throw new UnsupportedOperationException("setSetValue()");
  }

}
