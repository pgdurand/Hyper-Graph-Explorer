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
package bzh.plealog.hge.api.hypergraph;

import bzh.plealog.hge.api.datamodel.DGMType;

/**
 * Defines a graph data object.
 * 
 * @author Patrick G. Durand
 * */
public interface HDGObject extends Cloneable{

  /**
   * Get the object data type.
   * 
   * @return  the data type
   * */  
  public DGMType getType();

  /**
   * Set the object data type.
   * 
   * @param type  the data type
   * */  
  public void setType(DGMType type);

  /**
   * Get the user object attached to this graph data object.
   * 
   * @return a user data object
   * */
  public Object getData();

  /**
   * Set the user object attached to this graph data object.
   * 
   * @param data a user data object
   * */
  public void setData(Object data);

  /**
   * Return the value given an object's attribute.
   * 
   * @param attribute attribute name
   * 
   * @return the corresponding attribute value or null if not found.
   * */
  public Object getValue(String attribute);
}
