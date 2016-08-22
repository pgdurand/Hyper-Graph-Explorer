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

/**
 * Defines a graph link. It connects a vertex to an hyper edge.
 * 
 * @author Patrick G. Durand
 * */
public interface HDGLink {

  /**
   * The vertex located on one side of a link.
   * 
   *  @return the vertex connected to one end of this link.
   *  */
  public HDGVertex getVertex();

  /**
   * The edge located on the other end of a link.
   * 
   *  @return the edge connected to the other end of this link.
   *  */
  public HDGHyperEdge getHyperEdge();

  /**
   * The link orientation.
   * 
   * @return one of DGMLink constants.
   * */
  public int getOrientation();
}
