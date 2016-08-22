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


import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.hypergraph.HDGObject;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a data object. 
 * 
 * @author Patrick G. Durand
 */
public class HGEDGObject extends HGEArgumentValue
{
  public static final HGEDGObject CONST = new HGEDGObject();

  private HDGObject _gObject;

  private HGEDGObject()
  {
    setDataType(DGMAttribute.DT_GRAPH_OBJ);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param obj the value*/
  public HGEDGObject(HDGObject obj)
  {
    this();
    setDgObjectValue(obj);
  }

  /**
   * @see Object#toString()*/
  public String toString()
  {
    if (_gObject!=null)
      return _gObject.toString();
    else
      return "No HDGObject";
  }

  /**
   * @see HGEValue#dgObjectValue()
   * */
  public HDGObject dgObjectValue(){
    return(_gObject);
  }

  /**
   * @see HGEValue#setDgObjectValue(HDGObject)
   * */
  public void setDgObjectValue(HDGObject obj){
    _gObject=obj;
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   **/
  public void interpret(HGEExecutionContext context, HGEStack stack){
    stack.push(HGEDGObject.CONST);
  }

  /* Note:
     Evaluate for HGEDGObject is done by HGEVarInstance evaluate()
     public void evaluate(HGEExecutionContext context, Stack stack){
        this.setValue(context.getDGObject());
        stack.push(this);
    }*/
}
