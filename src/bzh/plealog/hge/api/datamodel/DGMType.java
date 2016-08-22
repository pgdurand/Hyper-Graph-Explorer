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
package bzh.plealog.hge.api.datamodel;

import java.util.Enumeration;

/**
 * A basic data model type.
 * 
 * @author Patrick G. Durand
 */
public interface DGMType {

  /**
   * Return type's name.
   * 
   * @return type's name
   */
  public String getName();

  /**
   * Return an attribute.
   * 
   * @param name attribute's name
   * @return an attribute
   */
  public DGMAttribute getAttribute(String name);

  /**
   * Return an enumeration over attributes
   * 
   * @return an enumeration over attributes
   */
  public Enumeration<DGMAttribute> getAttributes();

  /**
   * Return parent's type.
   * 
   * @return parent's type
   */
  public DGMType getParent();

  /**
   * Return children's types
   * 
   * @return children's types
   */
  public Enumeration<DGMType> getChildren();

}
