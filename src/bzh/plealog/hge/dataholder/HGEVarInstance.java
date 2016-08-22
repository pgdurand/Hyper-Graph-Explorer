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

import java.util.List;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.hypergraph.HDGObject;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a variable instance.
 * 
 * @author Patrick G. Durand
 * */
public class HGEVarInstance extends HGEValue
{
  public static final HGEVarInstance CONST = new HGEVarInstance();

  private HGEIdentifier _varID;

  private HGEVarInstance()
  {
    setDataType(-1);
    setContainerType(-1);
  }

  /**
   * Constructor.
   * 
   * @param varID variable id
   * */
  public HGEVarInstance(HGEIdentifier varID)
  {
    this();
    setVarID(varID);
  }

  /**
   * Get the variable id.
   * 
   * @return the variable id
   * */
  public HGEIdentifier getVarID()
  {
    return _varID;
  }

  /**
   * Set the variable id.
   * 
   * @param varID the variable id
   * */
  public void setVarID(HGEIdentifier varID){
    if (varID==null)
      throw new HGEDataHolderException("variable accessor is wrongly defined.");
    _varID = varID;
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return _varID.getValue();
  }

  /**
   * During interpretation this HGEVarInstance is responsible of stacking either a HGEDGObject
   * (for node/edge instance) of a HGEList of DGObject (for path).
   * 
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   **/
  public void interpret(HGEExecutionContext context, HGEStack stack){
    if (getContainerType()==DGMAttribute.CT_LIST)//path
      stack.push(HGEList.CONST_DGOBJECT);
    else
      stack.push(HGEDGObject.CONST);
  }

  /**
   * During evaluation, this HGEVarInstance is responsible of stacking either a HGEDGObject
   * (for node/edge instance) of a HGEList of DGObject (for path).
   * 
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)
   * */
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    HGEReusableArgumentValue val;


    val = context.getReusableArg();
    if (getContainerType()==DGMAttribute.CT_LIST){//path
      val.setListValue((List<?>) context.getVarInstanceObject());
      val.setDataType(DGMAttribute.DT_GRAPH_OBJ);
      stack.push(val);
    }
    else{//node/edge, i.e. a DGObject
      val.setDgObjectValue((HDGObject) context.getVarInstanceObject());
      stack.push(val);
    }
  }
}
