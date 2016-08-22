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
package bzh.plealog.hge.function;

import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;

/**
 * System class used to figure out whether a particular string is not
 * contained within a set of strings.
 * 
 * @author Patrick G. Durand
 */
public class StringNotInSetFunc extends StringInSetFunc {

  /**
   * @see bzh.plealog.hge.api.function.Function#getName()
   * */
  public String getName(){
    return("strNotInSet");
  }

  /**
   * @see bzh.plealog.hge.api.function.Function#compute(HGEArgumentValue[], HGEExecutionContext)
   * */
  public HGEArgumentValue compute(HGEArgumentValue[] obj, HGEExecutionContext context){
    HGEArgumentValue value = super.compute(obj, context);
    value.setBooleanValue(!value.booleanValue());
    return (value);
  }
}
