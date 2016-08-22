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
 * Unary minus operator.
 * */
public class HGEUnaryMinus extends HGEOperator {

  private static final String ERROR = "'unary minus' operation is made on non-numerical value.";
  private static final HGEOperatorException OPEXCEPTION = new HGEOperatorException(ERROR);

  private static final String ERROR_CT = "'unary minus' operation is made on non-atomic values.";
  private static final HGEOperatorException OPEXCEPTION_CT = new HGEOperatorException(ERROR_CT);

  /**
   * Constructor.
   **/
  public HGEUnaryMinus(){
    setName("-");
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    HGEReusableArgumentValue result;
    HGEValue              arg;
    int                    type;

    arg = stack.pop();
    type=arg.getContainerType();
    if (type!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    type=arg.getDataType();
    if (type!=DGMAttribute.DT_DOUBLE && type!=DGMAttribute.DT_LONG)
      throw OPEXCEPTION;

    result = context.getReusableArg();
    if (type==DGMAttribute.DT_DOUBLE){
      result.setDoubleValue(-arg.doubleValue());
      stack.push(result);
    }
    else {
      result.setLongValue(-arg.longValue());
      stack.push(result);
    }
    arg.setInUse(false);
  }

}
