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
 * Describes a graph data model. It is used to define the types of vertices and 
 * hyper-edges forming a data graph.
 * 
 * @author Patrick G. Durand
 */
public interface DataGraphModel {

  /**
   * Return the hyper-edge data types.
   * @return hyper-edge data types
   * */
  public Enumeration<DGMHyperEdgeType> getHyperEdgeTypes();
  /**
   * Return the vertex data types.
   * @return vertex data types
   * */
  public Enumeration<DGMVertexType> getVertexTypes();

  /**
   * Return the root hyper-edge data types.
   * @return root hyper-edge data types
   * */
  public Enumeration<DGMHyperEdgeType> getRootHyperEdgeTypes();
  /**
   * Return the root vertex data types.
   * @return root vertex data types
   * */
  public Enumeration<DGMVertexType> getRootVertexTypes();

  /**
   * Return a data type given its name.
   * 
   * @param name type name whatever its type. Can be the name of a vertex or a hyper-edge
   * 
   * @return data type
   * */
  public DGMType getType(String name);
  
  /**
   * Return a hyper-edge data type given its name.
   * 
   * @param name type name
   * 
   * @return data type
   * */
  public DGMHyperEdgeType getHyperEdgeType(String name);

  /**
   * Return a vertex data type given its name.
   * 
   * @param name type name
   * 
   * @return data type
   * */
  public DGMVertexType getVertexType(String name);

  /**
   * Return the name of this data graph model.
   * 
   * @return the name of this data graph model
   * */
  public String getName();
}
