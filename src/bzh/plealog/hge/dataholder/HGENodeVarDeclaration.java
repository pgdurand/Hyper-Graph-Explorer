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

import java.util.ArrayList;

import bzh.plealog.hge.engine.HGEEvaluable;

/**
 * A vertex variable declaration.
 * 
 * @author Patrick G. Durand
 * */
public class HGENodeVarDeclaration extends HGEVarDeclaration {

  protected ArrayList<HGEEvaluable> _typeExpression;

  protected HGENodeVarDeclaration(){}

  /**
   * Constructor.
   * 
   * @param name variable name
   * */
  public HGENodeVarDeclaration(String name){
    setName(name);
  }

  /**
   * @see Object#clone()
   * */
  public Object clone(){
    HGENodeVarDeclaration var;

    var = new HGENodeVarDeclaration();
    var.copy(this);
    return var;
  }

  /**
   * Copy the variable.
   * 
   * @param src the object to copy
   * */
  public void copy(HGENodeVarDeclaration src){
    super.copy(src);
    this.setType(new ArrayList<HGEEvaluable>(src.getType()));
  }

  /**
   * Check type.
   * */
  private void checkTypeArray(ArrayList<HGEEvaluable> t){
    if (t==null || t.size()<1)
      throw new HGEDataHolderException("list of data type is wrongly defined.");
  }

  /**
   * Set the type.
   * 
   * @param type the type
   * */
  public void setType(ArrayList<HGEEvaluable> type){
    checkTypeArray(type);
    _typeExpression = type;
  }

  /**
   * Get the type.
   * 
   * @return the type
   * */
  public ArrayList<HGEEvaluable> getType(){
    return _typeExpression;
  }

  /**
   * @see Object#toString()
   * */
  public String toString(){
    StringBuffer szBuf = new StringBuffer();

    szBuf.append(getName());
    if (_typeExpression!=null){
      szBuf.append(" in ");
      szBuf.append(getType().toString());
    }

    return (szBuf.toString());
  }
}
