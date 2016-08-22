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

/**
 * Define a query variable of type vertex.
 * 
 * @author Patrick G. Durand
 */
public class HGEEdgeVarDeclaration extends HGENodeVarDeclaration {
  /**array of strings*/
  protected ArrayList<String> _connectedNodeVars;

  protected HGEEdgeVarDeclaration(){}

  /**
   * Constructor.
   * 
   * @param name name of the variable
   * @param nodeVars list of variables
   * */
  public HGEEdgeVarDeclaration(String name, ArrayList<String> nodeVars) {
    super(name);
    setNodeVars(nodeVars);
  }

  /**
   * @see HGENodeVarDeclaration#clone()
   * */
  public Object clone(){
    HGEEdgeVarDeclaration var;
    var = new HGEEdgeVarDeclaration();
    var.copy(this);
    return var;
  }

  /**
   * Copy the object.
   * 
   * @param src object to copy
   * */
  @SuppressWarnings("unchecked")
  public void copy(HGEEdgeVarDeclaration src){
    super.copy(src);
    this.setNodeVars((ArrayList<String>) src.getNodeVars().clone());
  }

  private void checkVarArray(ArrayList<String> t){
    int i, size;

    if (t==null/* || t.size()<2*/)
      throw new HGEDataHolderException("list of node variables is wrongly defined.");

    size = t.size();
    for(i=0;i<size;i++){
      if ((t.get(i) instanceof String)==false)
        throw new HGEDataHolderException("list of node variables contains an invalid element.");
    }
  }

  /**
   * Set the list of variables.
   * 
   * @param nodeVars list of variables
   * */
  public void setNodeVars(ArrayList<String> nodeVars){
    checkVarArray(nodeVars);
    _connectedNodeVars = nodeVars;
  }

  /**
   * Get the list of variables.
   * 
   * @return nodeVars list of variables
   * */
  public ArrayList<String> getNodeVars(){
    return _connectedNodeVars;
  }

  /**
   * @see Object#toString()
   * */
  public String toString(){
    StringBuffer szBuf;
    int          i, size;

    szBuf = new StringBuffer();
    szBuf.append(getName());
    szBuf.append(":");
    size = getNodeVars().size();
    for(i=0;i<size;i++){
      szBuf.append(getNodeVars().get(i));
      if ((i+1)<size)
        szBuf.append("-");
    }
    if (getType()!=null){
      szBuf.append(" in ");
      szBuf.append(getType().toString());
    }

    return (szBuf.toString());
  }

}
