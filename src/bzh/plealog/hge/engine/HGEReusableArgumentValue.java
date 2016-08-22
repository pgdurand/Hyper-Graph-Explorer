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

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.hypergraph.HDGObject;

/**
 * Define a reusable argument.
 * 
 * @author Patrick G. Durand
 */
public class HGEReusableArgumentValue extends HGEArgumentValue {

  private HDGObject _dgObject;
  private boolean  _boolValue;
  private double   _doubleValue;
  private long     _longValue;
  private char     _charValue;
  private String   _stringValue;
  private Set<?>   _setValue;
  private List<?>  _listValue;

  /**
   * @see HGEValue#booleanValue()
   * */
  public boolean booleanValue(){
    if (getContainerType()==DGMAttribute.CT_ATOMIC && getDataType()==DGMAttribute.DT_BOOLEAN)
      return (_boolValue);
    throw new UnsupportedOperationException("booleanValue(): argument is not an atomic boolean");
  }
  /**
   * @see HGEValue#longValue()
   * */
  public long longValue(){
    if (getContainerType()==DGMAttribute.CT_ATOMIC &&
        (getDataType()==DGMAttribute.DT_LONG || getDataType()==DGMAttribute.DT_DATE))
      return (_longValue);
    throw new UnsupportedOperationException("longValue(): argument is not atomic long or date");
  }
  /**
   * @see HGEValue#doubleValue()
   * */
  public double doubleValue(){
    if (getContainerType()!=DGMAttribute.CT_ATOMIC)
      throw new UnsupportedOperationException("doubleValue(): argument is not atomic");
    if (getDataType()==DGMAttribute.DT_DOUBLE)
      return (_doubleValue);
    else if (getDataType()==DGMAttribute.DT_LONG || getDataType()!=DGMAttribute.DT_DATE)
      return ((double)_longValue);
    throw new UnsupportedOperationException("doubleValue(): argument is not a atomic number");
  }
  /**
   * @see HGEValue#charValue()
   * */
  public char charValue(){
    if (getContainerType()==DGMAttribute.CT_ATOMIC && getDataType()==DGMAttribute.DT_CHARACTER)
      return (_charValue);
    throw new UnsupportedOperationException("charValue(): argument is not atomic character");
  }
  /**
   * @see HGEValue#stringValue()
   * */
  public String stringValue(){
    if (getContainerType()==DGMAttribute.CT_ATOMIC && getDataType()==DGMAttribute.DT_STRING)
      return (_stringValue);
    throw new UnsupportedOperationException("stringValue(): argument is not atomic string");
  }

  /**
   * @see HGEValue#dgObjectValue()
   * */
  public HDGObject dgObjectValue(){
    if (getDataType()==DGMAttribute.DT_GRAPH_OBJ)
      return (_dgObject);
    throw new UnsupportedOperationException("dgObjectValue(): argument is not a graph object");
  }

  /**
   * @see HGEValue#listValue()
   * */
  public List<?> listValue(){
    if (getContainerType()==DGMAttribute.CT_LIST)
      return (_listValue);
    throw new UnsupportedOperationException("listValue(): argument is not a list");
  }

  /*note that there are two 't' to avoid conflict with setter methods*/
  /**
   * @see HGEValue#settValue()
   * */
  public Set<?> settValue(){
    if (getContainerType()==DGMAttribute.CT_SET)
      return (_setValue);
    throw new UnsupportedOperationException("settValue(): argument is not a set");
  }

  /**
   * @see HGEValue#setBooleanValue(boolean)
   * */
  public void setBooleanValue(boolean val){
    setDataType(DGMAttribute.DT_BOOLEAN);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _boolValue=val;
  }
  /**
   * @see HGEValue#setLongValue(long)
   * */
  public void setLongValue(long val){
    setDataType(DGMAttribute.DT_LONG);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _longValue=val;
  }
  /**
   * @see HGEValue#setDoubleValue(double)
   * */
  public void setDoubleValue(double val){
    setDataType(DGMAttribute.DT_DOUBLE);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _doubleValue=val;
  }
  /**
   * @see HGEValue#setCharValue(char)
   * */
  public void setCharValue(char val){
    setDataType(DGMAttribute.DT_CHARACTER);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _charValue=val;
  }
  /**
   * @see HGEValue#setStringValue(String)
   * */
  public void setStringValue(String val){
    setDataType(DGMAttribute.DT_STRING);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _stringValue=val;
  }
  /**
   * @see HGEValue#setDgObjectValue(HDGObject)
   * */
  public void setDgObjectValue(HDGObject obj){
    setDataType(DGMAttribute.DT_GRAPH_OBJ);
    setContainerType(DGMAttribute.CT_ATOMIC);
    _dgObject=obj;
  }

  /**
   * @see HGEValue#setListValue(List)
   * */
  public void setListValue(List<?> val){
    setContainerType(DGMAttribute.CT_LIST);
    _listValue=val;
  }

  /*note that there are two 't' to avoid conflict with setter methods*/
  /**
   * @see HGEValue#setSetValue(Set)
   * */
  public void setSetValue(Set<?> val){
    setContainerType(DGMAttribute.CT_SET);
    _setValue=val;
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack) {
    stack.push(this);
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals(Object obj){
    HGEArgumentValue val;

    if (obj==null || !(obj instanceof HGEArgumentValue))
      return false;
    val = (HGEArgumentValue) obj;
    if (this.getContainerType()==DGMAttribute.CT_ATOMIC){
      if (this.getDataType()==DGMAttribute.DT_BOOLEAN){
        return (_boolValue==val.booleanValue());
      }
      else if(this.getDataType()==DGMAttribute.DT_CHARACTER){
        return (_charValue==val.charValue());
      }
      else if(this.getDataType()==DGMAttribute.DT_STRING){
        return (_stringValue.hashCode()==val.stringValue().hashCode());
      }
      else if(this.getDataType()==DGMAttribute.DT_LONG || this.getDataType()==DGMAttribute.DT_DATE){
        return (_longValue==val.longValue());
      }
      else if(this.getDataType()==DGMAttribute.DT_DOUBLE){
        return (_doubleValue==val.doubleValue());
      }
      else if(this.getDataType()==DGMAttribute.DT_GRAPH_OBJ){
        return (_dgObject.equals(val.dgObjectValue()));
      }
      else {
        return false;
      }
    }
    else if (this.getContainerType()==DGMAttribute.CT_LIST) {
      return(_listValue.equals(obj));
    }
    else{
      return(_setValue.equals(obj));
    }
  }
  /**
   * @see Object#hashCode()
   * */

  public int hashCode(){
    if (this.getContainerType()==DGMAttribute.CT_ATOMIC){
      if (this.getDataType()==DGMAttribute.DT_STRING)
        return _stringValue.hashCode();
      else if (this.getDataType()==DGMAttribute.DT_CHARACTER)
        return new Character(_charValue).hashCode();
      else if (this.getDataType()==DGMAttribute.DT_DOUBLE)
        return new Double(_doubleValue).hashCode();
      else if (this.getDataType()==DGMAttribute.DT_LONG)
        return new Long(_longValue).hashCode();
      else if (this.getDataType()==DGMAttribute.DT_DATE)
        return new Long(_longValue).hashCode();
      else if (this.getDataType()==DGMAttribute.DT_BOOLEAN)
        return new Boolean(_boolValue).hashCode();
      else if (this.getDataType()==DGMAttribute.DT_GRAPH_OBJ)
        return _dgObject.hashCode();
      return super.hashCode();
    }else if (this.getContainerType()==DGMAttribute.CT_LIST) {
      return(_listValue.hashCode());
    }
    else{
      return(_setValue.hashCode());
    }
  }

}
