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

import bzh.plealog.hge.dataholder.HGELong;

/**
 * Some utility methods.
 * 
 * @author Patrick G. Durand
 * */
public class HGEUtils {
  /**
   * Figures out whether or not a date string representation is formatted as yyyy-mm-dd.
   * 
   * @param str the string to analyze
   * @return true if date format is formatted as yyyy-mm-dd
   */
  public static boolean isDate(String str){
    if (str.length()!=10)
      return false;
    if (str.charAt(4)!='-' && str.charAt(7)!='-')
      return false;
    for (int i=0;i<10;i++){
      if (i==4 || i==7)
        continue;
      if ( !Character.isDigit(str.charAt(i)) )
        return false;
    }
    return true;
  }

  /**
   * Returns an integer representation of a date that must be formatted as yyyy-mm-dd.
   * Since this method does not check the format, please call isDate() first.
   * 
   * @param str the string to analyze
   * @return the integer representation of a date as a HGELong
   */
  public static HGELong getDateHGERepr(String str){
    StringBuffer buf = new StringBuffer();
    buf.append(str.substring(0,4));
    buf.append(str.substring(5,7));
    buf.append(str.substring(8,10));
    return new HGELong(buf.toString());
  }

  /**
   * Returns an integer representation of a date that must be formatted as yyyy-mm-dd.
   * Since this method does not check the format, please call isDate() first.
   * 
   * @param str the string to analyze
   * @return the integer representation of a date as a standard Java Long
   */
  public static Long getDateJavaRepr(String str){
    StringBuffer buf = new StringBuffer();
    buf.append(str.substring(0,4));
    buf.append(str.substring(5,7));
    buf.append(str.substring(8,10));
    return new Long(buf.toString());
  }

}
