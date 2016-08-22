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
 * An hyper-edge type.
 * 
 * @author Patrick G. Durand
 */
public interface DGMHyperEdgeType extends DGMType {

  /**
   * Size of the hyper-edges.
   * 
   * @return number of links
   * */
  public int size();

  /**
   * Return a link by its name.
   * 
   * @param name name of the link to retrieve
   * @return the link
   **/
  public DGMLink getLink(String name);

  /**
   * Return an enumeration over links defining this hyper-edge.
   * @return an enumeration over links defining this hyper-edge
   */
  public Enumeration<DGMLink> getLinks();

}
