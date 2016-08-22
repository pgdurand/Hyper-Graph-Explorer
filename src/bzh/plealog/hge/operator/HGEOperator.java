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

import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;

/**
 * Defines an abstract operator. It is mostly a basic implementation of HGEInterpretable
 * and HGEEvaluable interfaces.
 * */
public abstract class HGEOperator implements HGEInterpretable, HGEEvaluable{

  protected String _name;

  /**
   * Copy the content of an operator.
   * 
   * @param src the source content
   */
  public void copy(HGEOperator src){
    this.setName(src.getName());
  }

  /**
   * Set the operator name or symbol.
   * 
   * @param name name or symbol of the operator
   * */
  public void setName(String name){
    _name = name;
  }

  /**
   * Get the operator name or symbol.
   * 
   * @return name or symbol of the operator
   * */
  public String getName(){
    return _name;
  }

  /**
   * @see Object#toString()
   * 
   * */
  public String toString(){
    return _name;
  }

  /**
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)
   * */
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    interpret(context, stack);
  }

}
