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

import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a path variable.
 * 
 * @author Patrick G. Durand
 * */
public class HGEPathVarDeclaration extends HGEVarDeclaration {

  protected String _fromVarName;
  protected String _toVarName;
  protected String _operator;
  protected int    _pathMinLength = HGEValue.UNDEFINED;
  protected int    _pathMaxLength = HGEValue.UNDEFINED;

  protected HGEPathVarDeclaration(){}

  /**
   * Constructor.
   * 
   * @param name name of the variable
   * @param fromNodeVar name of the from variable
   * @param toNodeVar name of the to variable
   * @param operator operator
   * */
  public HGEPathVarDeclaration(String name, String fromNodeVar, String toNodeVar, String operator){
    setName(name);
    setFromNodeVar(fromNodeVar);
    setToNodeVar(toNodeVar);
    setOperator(operator);
  }

  /**
   * @see Object#clone()
   * */
  public Object clone(){
    HGEPathVarDeclaration var;

    var = new HGEPathVarDeclaration();
    var.copy(this);
    return var;
  }

  /**
   * Copy this object.
   * 
   * @param src the object to copy
   * */
  public void copy(HGEPathVarDeclaration src){
    super.copy(src);
    this.setFromNodeVar(src.getFromNodeVar());
    this.setToNodeVar(src.getToNodeVar());
    this.setOperator(src.getOperator());
    this.setMinLength(src.getMinLength());
    this.setMaxLength(src.getMaxLength());
  }

  /**
   * Set the name of the from variable.
   * 
   * @param name name of the variable
   * */
  public void setFromNodeVar(String name){
    if (name==null || name.length()<1)
      throw new HGEDataHolderException("'from' variable name is not defined.");
    _fromVarName = name;
  }

  /**
   * Get the name of the from variable.
   * 
   * @return name of the variable
   * */
  public String getFromNodeVar(){
    return _fromVarName;
  }

  /**
   * Set the name of the to variable.
   * 
   * @param name name of the variable
   * */
  public void setToNodeVar(String name){
    if (name==null || name.length()<1)
      throw new HGEDataHolderException("'to' variable name is not defined.");
    _toVarName = name;
  }

  /**
   * Get the name of the from variable.
   * 
   * @return name of the variable
   * */
  public String getToNodeVar(){
    return _toVarName;
  }

  /**
   * Set the operator.
   * 
   * @param operator operator
   * */
  public void setOperator(String operator){
    _operator = operator;
  }

  /**
   * Get the operator.
   * 
   * @return operator
   * */
  public String getOperator(){
    return (_operator);
  }

  /**
   * Set minimum length of the path.
   * 
   * @param min minimum length of the path
   * */
  public void setMinLength(int min){
    if (min<=0)
      throw new HGEDataHolderException("minimum path length cannot be <= 0.");
    _pathMinLength = min;
  }

  /**
   * Get minimum length of the path.
   * 
   * @return minimum length of the path
   * */
  public int getMinLength(){
    return (_pathMinLength);
  }

  /**
   * Set maximum length of the path.
   * 
   * @param max maximum length of the path
   * */
  public void setMaxLength(int max){
    if (max<=0)
      throw new HGEDataHolderException("maximum path length cannot be <= 0.");
    _pathMaxLength = max;
  }

  /**
   * Get maximum length of the path.
   * 
   * @return maximum length of the path
   * */
  public int getMaxLength(){
    return (_pathMaxLength);
  }

  /**
   * @see Object#toString()
   * */
  public String toString(){
    StringBuffer szBuf = new StringBuffer();

    szBuf.append(getName());
    szBuf.append(" (");
    szBuf.append(getFromNodeVar());
    szBuf.append(", ");
    szBuf.append(getToNodeVar());
    szBuf.append(", ");
    szBuf.append(getOperator());
    if (getMinLength()>0){
      szBuf.append(", ");
      szBuf.append(getMinLength());
      if (getMaxLength()>0){
        szBuf.append(", ");
        szBuf.append(getMaxLength());
      }
    }
    szBuf.append(")");

    return (szBuf.toString());
  }
}
