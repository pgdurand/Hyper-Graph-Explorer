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

import java.util.Enumeration;
import java.util.List;

import bzh.plealog.hge.api.datamodel.DataGraphModel;

/**
 * Defines a data hyper-graph describes by a DataGraphModel. The entry point of
 * this interface is the create method: it is used to instantiate a concrete graph
 * given a data model and a data source.
 * 
 * @author Patrick G. Durand
 * */
public interface HDataGraph {

  /**
   * Create the data graph. 
   * 
   * @param conn the data connector. This is where the data comes from.
   * @param dgm the data graph model. This is what describes the data.
   */
  public void create(HDBConnector conn, DataGraphModel dgm);

  /**
   * Figure out whether or not this graph contains a particular edge.
   * 
   * @param edge the edge instance to locate in this graph
   * @return true or false
   * */
  public boolean containsEdge(HDGHyperEdge edge);

  /**
   * Figure out whether or not this graph contains a particular vertex.
   * 
   * @param vertex the vertex instance to locate in this graph
   * @return true or false
   * */
  public boolean containsVertex(HDGVertex vertex);

  /**
   * Enumerate over the edges of this graph.
   *  
   * @return an enumerator over the edges of the graph
   * */
  public Enumeration<HDGHyperEdge> edges();

  /**
   * Enumerate over the edges of this graph.
   *  
   * @return an enumerator over the edges of the graph
   * */
  public Enumeration<HDGVertex> vertices();

  /**
   * Enumerate over the edges of a particular vertex.
   * 
   * @param vertex the vertex from which to explore edges
   * @return an enumerator over the edges of the vertex
   * */
  public Enumeration<HDGHyperEdge> edges(HDGVertex vertex);

  /**
   * Enumerate over the links of a particular edge.
   * 
   * @param edge the edge from which to explore links
   * @return an enumerator over the links of the edge
   * */
  public Enumeration<HDGLink> links(HDGHyperEdge edge);

  /**
   * Return possible path between two vertices.
   * 
   * @param from starting vertex
   * @param to ending vertex
   * 
   * @return a enumerator of list of graph objects. Enumerator is empty if no path can be found.
   * */
  public List<HDGLink> findPath(HDGVertex from, HDGVertex to);

  /**
   * Return the number of edges connected to a vertex.
   * 
   * @param vertex the vertex used to compute the degree
   * @return the degree of the vertex
   * */
  public int degree(HDGVertex vertex); 

  /**
   * Return all the vertices reachable from an edge.
   * 
   * @param edge the edge from which to find vertices
   * @return an enumerator over the vertices connected to the edge
   * */
  public Enumeration<HDGVertex> vertices(HDGHyperEdge edge); 

  /**
   * Return the opposite vertices.
   * 
   * @param edge the edge through which one has to locate reachable vertices
   * @param vertex the starting vertex
   * @param obeyOrientation respect link orientation
   * @return an enumerator over the vertices reachable from a vertex through an edge
   * */
  public Enumeration<HDGVertex> oppositeVertices(HDGHyperEdge edge, HDGVertex vertex, boolean obeyOrientation);
}
