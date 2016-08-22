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

import java.util.Hashtable;

/**
 * Define a query variable of type graph.
 * 
 * @author Patrick G. Durand
 * */
public class HGEGraphVarDeclaration extends HGEVarDeclaration {

  protected Hashtable<String, HGEVarDeclaration> _vars;

  protected HGEGraphVarDeclaration(){}

  /**
   * Constructor.
   * 
   * @param name the name of the variable
   * */
  public HGEGraphVarDeclaration(String name){
    setName(name);
  }

  /**
   * Clone this object.
   * 
   * @return a HGEGraphVarDeclaration object
   * */
  public Object clone(){
    HGEGraphVarDeclaration var;

    var = new HGEGraphVarDeclaration();
    var.copy(this);
    return var;
  }

  /**
   * Copy this object.
   * 
   * @param src the source object
   * @return a HGEGraphVarDeclaration object
   * */
  @SuppressWarnings("unchecked")
  public void copy(HGEGraphVarDeclaration src){
    super.copy(src);
    this.setVars((Hashtable<String, HGEVarDeclaration>) src.getVars().clone());
  }

  /**
   * Sets the variables forming this graph declaration.
   * 
   * @param type the variables
   * */
  public void setVars(Hashtable<String, HGEVarDeclaration> type){
    _vars = type;
  }

  /**
   * Returns the variables forming this graph declaration.
   * 
   * @return the variables
   * */
  public Hashtable<String, HGEVarDeclaration> getVars(){
    return _vars;
  }

  /**
   * Returns a string representation of this object.
   * 
   * @return a string representation of this object.
   * */
  public String toString(){
    StringBuffer szBuf = new StringBuffer();

    szBuf.append(getName());
    if (_vars!=null){
      szBuf.append(" as ");
      szBuf.append(getVars().toString());
    }

    return (szBuf.toString());
  }
}
