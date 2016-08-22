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
package bzh.plealog.hge.operator;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;


/**
 * Subtract operator.
 * */
public class HGESubtract extends HGEOperator {

  private static final String ERROR = "'subtract' operation is made on non-numerical values.";
  private static final HGEOperatorException OPEXCEPTION = new HGEOperatorException(ERROR);

  private static final String ERROR_CT = "'subtract' operation is made on non-atomic values.";
  private static final HGEOperatorException OPEXCEPTION_CT = new HGEOperatorException(ERROR_CT);

  /**
   * Constructor.
   **/
  public HGESubtract(){
    setName("-");
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    HGEReusableArgumentValue result;
    HGEValue              val1, val2;
    int                    type1, type2;

    //get arg2 before arg1 since we use a stack
    val2 = stack.pop();
    type2=val2.getContainerType();
    if (type2!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    type2=val2.getDataType();
    if (type2!=DGMAttribute.DT_DOUBLE && type2!=DGMAttribute.DT_LONG)
      throw OPEXCEPTION;

    val1 = stack.pop();
    type1=val1.getContainerType();
    if (type1!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    type1=val1.getDataType();
    if (type1!=DGMAttribute.DT_DOUBLE && type1!=DGMAttribute.DT_LONG)
      throw OPEXCEPTION;

    result = context.getReusableArg();
    if (type1==DGMAttribute.DT_LONG && type2==DGMAttribute.DT_LONG){
      result.setLongValue(val1.longValue()-val2.longValue());
      stack.push(result);
    }
    else {
      result.setDoubleValue(val1.doubleValue()-val2.doubleValue());
      stack.push(result);
    }
    val1.setInUse(false);
    val2.setInUse(false);
  }

}
