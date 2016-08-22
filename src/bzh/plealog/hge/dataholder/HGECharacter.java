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
 * Define a character.
 * 
 * 
 * @author Patrick G. Durand
 * */
public class HGECharacter extends HGEArgumentValue
{
  public static final HGECharacter CONST = new HGECharacter('a');

  private char _value;

  private HGECharacter()
  {
    setDataType(DGMAttribute.DT_CHARACTER);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGECharacter(String val)
  {
    this();
    setCharValue(val.charAt(0));
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGECharacter(char val)
  {
    this();
    setCharValue(val);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGECharacter(HGECharacter val)
  {
    this();
    setCharValue(val.charValue());
  }

  /**
   * @see HGEValue#charValue()
   * */
  public char charValue(){
    return(_value);
  }
  
  /**
   * @see HGEValue#setCharValue(char)
   * */
  public void setCharValue(char val){
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
    return String.valueOf(_value);
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGEValue){
        try{
          return(((HGEValue)obj).charValue()==this.charValue());
        }
        catch(UnsupportedOperationException ex){
          return(false);
        }
      }
      else if (obj instanceof Character){
        return (((Character)obj).charValue()==this.charValue());
      }
    }
    return false;
  }

  /**
   * @see Object#hashCode()
   * */
  public int hashCode() {
    return (int)_value;
  }

}
