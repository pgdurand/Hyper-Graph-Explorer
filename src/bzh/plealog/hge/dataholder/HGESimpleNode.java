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

import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a simple node.
 * 
 * @author Patrick G. Durand
 * */
public abstract class HGESimpleNode {

  private HGEValue _value;

  /**
   * Set a value.
   * 
   * @param s the value
   * */
  public void setValue(HGEValue s){
    _value = s;
  }

  /**
   * Get a value.
   * 
   * @return the value
   * */
  public HGEValue getValue(){
    return _value;
  }

  /**
   * @see Object#toString()
   * */
  public String toString(){
    return _value.toString();
  }

}
