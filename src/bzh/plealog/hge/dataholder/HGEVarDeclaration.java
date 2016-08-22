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

/**
 * A simple variable declaration.
 * 
 * 
 * @author Patrick G. Durand
 * */
public abstract class HGEVarDeclaration {

  protected String _name;

  /**
   * Copy the variable.
   * 
   * @param src the object to copy
   * */
  public void copy(HGEVarDeclaration src){
    this.setName(src.getName());
  }
  /**
   * Set variable name.
   * 
   * @param name variable name
   * */
  public void setName(String name){
    if (name==null || name.length()<1)
      throw new HGEDataHolderException("variable name is not defined.");
    _name = name;
  }

  /**
   * Get the variable name.
   * 
   * @return the variable name
   * */
  public String getName(){
    return _name;
  }

}
