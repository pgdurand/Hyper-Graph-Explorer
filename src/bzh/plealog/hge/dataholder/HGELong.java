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
 * Define a long.
 * 
 * @author Patrick G. Durand
 * */
public class HGELong extends HGEArgumentValue
{
  public static final HGELong CONST = new HGELong(5l);

  private long _value;

  private HGELong()
  {
    setDataType(DGMAttribute.DT_LONG);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param d the value
   * */
  public HGELong(long d)
  {
    this();
    setLongValue(d);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGELong(HGELong val)
  {
    this();
    setLongValue(val.longValue());
  }

  /**
   * Constructor.
   * 
   * @param s the value
   * */
  public HGELong(String s)
  {
    this();
    _value = 0l;
    setValue(s);
  }

  private void setValue(String s)
  {
    Long d = new Long(s);
    _value = d.longValue();
  }

  /**
   * @see Object#toString()*/
  public String toString()
  {
    Long d = new Long(_value);
    return d.toString();
  }

  /**
   * @see HGEValue#longValue()
   * */
  public long longValue(){
    return(_value);
  }
  /**
   * @see HGEValue#doubleValue()
   * */
  public double doubleValue(){
    return((double) _value);
  }
  public void setLongValue(long val){
    _value=val;
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if  (obj instanceof HGEValue){
        try{
          return(((HGEValue)obj).longValue()==this.longValue());
        }
        catch(UnsupportedOperationException ex){
          return(false);
        }
      }
      else if  (obj instanceof Long){
        return (((Long)obj).longValue()==this.longValue());
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
    return (int)(_value ^ (_value >>> 32));
  }

}
