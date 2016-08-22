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
package bzh.plealog.hge.api.function;

import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;

/**
 * Describe a function.
 * 
 * Note: the implementation of a function should overrides toString() to give its description.
 * 
 * @author Patrick G. Durand
 * */
public interface Function{

  /**
   * Return the name of the function.
   * 
   * @return name of the function.
   */
  public String getName();

  /**
   * Number of arguments accepted by this function.
   * 
   * @return number of arguments*/
  public int arguments();

  /**
   * Define the argument's data type accepted by this function.
   * 
   * @return only one of DGMAttribute.DT_XXX constants*/
  public int[] getArgDataTypes();

  /**
   * Define the argument's container type accepted by this function.
   * 
   * @return only one of DGMAttribute.CT_XXX constants*/
  public int[] getArgContainerTypes();

  /**
   * Define the data type returned by this function.
   * 
   * @return only one of DGMAttribute.DT_XXX constants*/
  public int getReturnDataType();

  /**
   * Define the container type returned by this function.
   * 
   * @return only one of DGMAttribute.CT_XXX constants*/
  public int getReturnContainerType();

  /**
   * Execute a function.
   * 
   * @param obj function's arguments
   * @param context execution context
   * @return the result of the function
   * */
  public HGEArgumentValue compute(HGEArgumentValue[] obj, HGEExecutionContext context);
}
