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

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.function.AbstractFunction;
import bzh.plealog.hge.api.function.Function;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;


/**
 * Add Long function.
 * 
 * @author Patrick G. Durand
 * */
public class AddLongFunc extends AbstractFunction {
  private static final int ARG_DATA_TYPES[]={DGMAttribute.DT_LONG, DGMAttribute.DT_LONG};
  private static final int ARG_CONTAINER_TYPES[]={DGMAttribute.CT_ATOMIC, DGMAttribute.CT_ATOMIC};

  /**
   * @see Function#getName()
   * */
  public String getName(){
    return("addLong");
  }

  /**
   * @see Function#arguments()
   * */
  public int arguments(){
    return (2);
  }

  /**
   * @see Function#getArgDataTypes()
   * */
  public int[] getArgDataTypes(){
    return (ARG_DATA_TYPES);
  }

  /**
   * @see Function#getContainerTypes()
   * */
  public int[] getArgContainerTypes(){
    return (ARG_CONTAINER_TYPES);
  }

  /**
   * @see Function#getReturnDataType()
   * */
  public int getReturnDataType(){
    return (DGMAttribute.DT_LONG);
  }

  /**
   * @see Function#getReturnContainerType()
   * */
  public int getReturnContainerType(){
    return (DGMAttribute.CT_ATOMIC);
  }

  /**
   * @see Function#compute(HGEArgumentValue[], HGEExecutionContext)
   * */
  public HGEArgumentValue compute(HGEArgumentValue[] obj, HGEExecutionContext context){
    HGEReusableArgumentValue result;
    result = context.getReusableArg();
    result.setLongValue(obj[0].longValue()+obj[1].longValue());
    return result;
  }
}
