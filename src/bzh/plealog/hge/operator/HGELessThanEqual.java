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
 * Less than or equal operator.
 * */

public class HGELessThanEqual extends HGEOperator {

  private static final String ERROR = "'<=' operation is made on non-numerical values.";
  private static final HGEOperatorException OPEXCEPTION = new HGEOperatorException(ERROR);

  private static final String ERROR_CT = "'<=' operation is made on non-atomic values.";
  private static final HGEOperatorException OPEXCEPTION_CT = new HGEOperatorException(ERROR_CT);

  /**
   * Constructor.
   **/
  public HGELessThanEqual(){
    setName("<=");
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    HGEValue  d1, d2;
    int        type1, type2;

    //get arg2 before arg1 since we use a stack
    d2 = stack.pop();
    type2=d2.getContainerType();
    if (type2!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    type2=d2.getDataType();
    if (type2!=DGMAttribute.DT_DOUBLE && type2!=DGMAttribute.DT_LONG)
      throw OPEXCEPTION;

    d1 = stack.pop();
    type1=d1.getContainerType();
    if (type1!=DGMAttribute.CT_ATOMIC)
      throw OPEXCEPTION_CT;
    type1=d1.getDataType();
    if (type1!=DGMAttribute.DT_DOUBLE && type1!=DGMAttribute.DT_LONG)
      throw OPEXCEPTION;

    if (d1.doubleValue()<=d2.doubleValue()){
      stack.push(HGEBoolean.TRUE);
    }
    else{
      stack.push(HGEBoolean.FALSE);
    }
    d1.setInUse(false);
    d2.setInUse(false);
  }
}
