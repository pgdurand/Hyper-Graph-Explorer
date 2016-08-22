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
 * Define a double.
 * 
 * 
 * @author Patrick G. Durand
 * */
public class HGEDouble extends HGEArgumentValue
{
  public static final HGEDouble CONST = new HGEDouble(3.0);

  private double _value;

  private HGEDouble()
  {
    setDataType(DGMAttribute.DT_DOUBLE);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param d the value
   * */
  public HGEDouble(double d)
  {
    this();
    setDoubleValue(d);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGEDouble(HGEDouble val)
  {
    this();
    setDoubleValue(val.doubleValue());
  }

  /**
   * Constructor.
   * 
   * @param s the value
   * */
  public HGEDouble(String s)
  {
    this();
    setValue(s);
  }

  /**
   * Set the value.
   * 
   * @param s the value
   * */
  public void setValue(String s)
  {
    Double d = new Double(s);
    _value = d.doubleValue();
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    Double d = new Double(_value);
    return d.toString();
  }

  /**
   * @see HGEValue#doubleValue()
   * */
  public double doubleValue(){
    return(_value);
  }

  /**
   * @see HGEValue#setDoubleValue(double)
   * */
  public void setDoubleValue(double val){
    _value=val;
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGEValue){
        try{
          return(((HGEValue)obj).doubleValue()==this.doubleValue());
        }
        catch(UnsupportedOperationException ex){
          return(false);
        }
      }
      else if(obj instanceof Double){
        return (((Double)obj).doubleValue()==this.doubleValue());
      }
    }
    return false;
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
  public int hashCode() {
    long bits = Double.doubleToLongBits(_value);
    return (int)(bits ^ (bits >>> 32));
  }

}
