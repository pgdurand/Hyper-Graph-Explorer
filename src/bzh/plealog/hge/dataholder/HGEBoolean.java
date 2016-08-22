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

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a boolean.
 * 
 * @author Patrick G. Durand
 * */
public class HGEBoolean extends HGEArgumentValue
{
  private boolean _value;

  public final static HGEBoolean TRUE = new HGEBoolean(true);
  public final static HGEBoolean FALSE = new HGEBoolean(false);

  private HGEBoolean()
  {
    setDataType(DGMAttribute.DT_BOOLEAN);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }
  /**
   * Constructor.
   * 
   * @param val supposed to be either true or false
   * */
  public HGEBoolean(String val)
  {
    this();
    _value = ( (val!=null && val.equalsIgnoreCase("true")) ? true : false);
  }

  /**
   * Constructor.
   * 
   * @param val true or false
   * */
  public HGEBoolean(boolean val)
  {
    this();
    _value = false;
    setBooleanValue(val);
  }

  /**
   * Constructor.
   * 
   * @param val another HGEBoolean
   * */
  public HGEBoolean(HGEBoolean val)
  {
    this();
    _value = false;
    setBooleanValue(val.booleanValue());
  }

  /**
   * @see HGEValue#booleanValue()
   */
  public boolean booleanValue(){
    return (_value);
  }

  /**
   * @see HGEValue#setBooleanValue(boolean)
   */
  public void setBooleanValue(boolean val){
    _value=val;
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   **/
  public void interpret(HGEExecutionContext context, HGEStack stack){
    stack.push(this);
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return _value ? "true" : "false";
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGEValue){
        try{
          return(((HGEValue)obj).booleanValue()==this.booleanValue());
        }
        catch(UnsupportedOperationException ex){
          return(false);
        }
      }
      else if (obj instanceof Boolean){
        return(((Boolean)obj).booleanValue()==this.booleanValue());
      }
    }
    return false;
  }

  /**
   * @see Object#hashCode()
   * */
  public int hashCode() {
    return _value ? 1231 : 1237;
  }
}
