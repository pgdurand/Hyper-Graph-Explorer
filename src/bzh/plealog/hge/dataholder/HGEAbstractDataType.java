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


import bzh.plealog.hge.api.datamodel.DGMType;
import bzh.plealog.hge.api.hypergraph.HDGObject;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define an abstract data type.
 * 
 * @author Patrick G. Durand
 * */
public abstract class HGEAbstractDataType extends HGEValue implements HGEDType {

  protected String _value;

  /**
   * Get the value.
   * 
   * @return the value
   * */
  public String getValue()
  {
    return _value;
  }

  /**
   * Set the value.
   * 
   * @param s the value
   * */
  public void setValue(String s)
  {
    _value = s;
  }

  /**
   * @see Object#toString()
   * */
  public String toString()
  {
    return _value;
  }

  /**
   * @see HGEDType#getType()
   * */
  public String getType()
  {
    return _value;
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
  }

  /**
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)
   * */
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    DGMType objType;
    String  curTypeName;

    objType = ((HDGObject)context.getVarInstanceObject()).getType();
    curTypeName = this.getType();

    if (objType.getName().equals(curTypeName)){
      stack.push(HGEBoolean.TRUE);
      return;
    }
    scanType(curTypeName, objType.getParent(), stack);
  }
  private void scanType(String curTypeName, DGMType objType, HGEStack stack){
    if (objType==null){
      stack.push(HGEBoolean.FALSE);
      return;
    }
    if (curTypeName.equals(objType.getName())){
      stack.push(HGEBoolean.TRUE);
      return;
    }
    scanType(curTypeName, objType.getParent(), stack);

  }
}
