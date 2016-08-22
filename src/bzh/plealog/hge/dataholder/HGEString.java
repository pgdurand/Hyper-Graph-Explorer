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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a string.
 * 
 * @author Patrick G. Durand
 * */
public class HGEString extends HGEArgumentValue
{
  public static final HGEString CONST = new HGEString("a");
  public static final HGEString UNKNOWN = new HGEString("?value?");

  private String       _value;
  private Pattern      _pattern;

  private HGEString()
  {
    setDataType(DGMAttribute.DT_STRING);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param s the value
   * */
  public HGEString(String s)
  {
    this();
    setStringValue(s);
  }

  /**
   * Constructor.
   * 
   * @param val the value
   * */
  public HGEString(HGEString val)
  {
    this();
    setStringValue(val.stringValue());
  }

  /**
   * Append a string to this one.
   * 
   * @param s thge string to appent to this one
   * */
  public void append(String s)
  {
    if (_value==null)
      _value = s;
    else
      _value += s;
    _pattern=null;
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return (stringValue());
  }

  /**
   * @see HGEValue#stringValue()
   * */
  public String stringValue(){
    return _value;
  }

  /**
   * @see HGEValue#setStringValue(String)
   * */
  public void setStringValue(String val){
    _value = val;
    _pattern=null;
  }

  /**
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null){
      if (obj instanceof HGEValue){
        return (((HGEValue)obj).stringValue().equalsIgnoreCase(this.stringValue()));
      }
      else if (obj instanceof String){
        return (((String)obj).equalsIgnoreCase(this.stringValue()));
      }
    }
    return false;
  }

  /**
   * Initialize the pattern matching engine.
   * */
  private void initPattern(){
    if (_pattern!=null)
      return;
    _pattern = Pattern.compile(this.stringValue());
  }

  /**
   * Figure out whether or not an object matches to this string.
   * 
   * @param obj the object to evaluate
   * 
   * @return true is object matches this string, false otherwise
   * */
  public boolean match(Object obj){
    if (obj!=null){
      initPattern();
      Matcher ma;
      if (obj instanceof HGEValue){
        ma = _pattern.matcher(((HGEValue)obj).stringValue());
        return (ma.find());
      }
      else if (obj instanceof String){
        ma = _pattern.matcher((String) obj);
        return (ma.find());
      }
    }
    return false;
  }
  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    stack.push(this);
  }
  /**
   * @see Object#hashCode()
   * */
  public int hashCode(){
    return _value.hashCode();
  }
}
