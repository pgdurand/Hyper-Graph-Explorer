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
import bzh.plealog.hge.dataholder.HGEBoolean;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;


/**
 * Boolean not operator.
 * */
public class HGEBooleanNot extends HGEOperator {

  private static final String ERROR = "'not' operation is made on non-boolean value.";
  private static final HGEOperatorException OPEXCEPTION = new HGEOperatorException(ERROR);

  private static final String ERROR_CT = "'not' operation is made on non-atomic values.";
  private static final HGEOperatorException OPEXCEPTION_CT = new HGEOperatorException(ERROR_CT);

  /**
   * Constructor.
   **/
  public HGEBooleanNot(){
    setName("not");
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    HGEValue arg1;

    arg1 = stack.pop();
    if (arg1.getContainerType()!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    if (arg1.getDataType()!=DGMAttribute.DT_BOOLEAN)
      throw OPEXCEPTION;

    if (arg1.booleanValue()){
      stack.push(HGEBoolean.FALSE);
    }
    else{
      stack.push(HGEBoolean.TRUE);
    }
    arg1.setInUse(false);
  }
}
