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
package bzh.plealog.hge.api.query;

import java.util.Set;

import bzh.plealog.hge.api.datamodel.DataGraphModel;
import bzh.plealog.hge.api.hypergraph.HDataGraph;


/**
 * Define a query. Such an query is defines as an hyper-graph which is used
 * to locate sub-graph within a data graph. 
 * 
 * @author Patrick G. Durand
 * */
public interface HGEQuery {

  /**
   * Setup a new query given a query string.
   * 
   * @param query the query string
   * */
  public void setQuery(String query);

  /**
   * Prepare the query. 
   * 
   * @param model the graph data model on which this query lies upon
   * 
   * @return a string representation of the query
   * */
  public String compile(DataGraphModel model);

  /**
   * Add a new constraint.
   * 
   * @param constraint the string representation of the constraint
   * */
  public void addConstraint(String constraint);

  /**
   * Add a new declaration.
   * 
   * @param declaration the string representation of the declaration
   * */
  public void addDeclaration(String declaration);

  /**
   * Set the variables of the query.
   * 
   * @param ret the variables
   * */
  public void setReturnVariables(String ret);

  /**
   * Figure out whether or not the query system will have to return distinct result.
   * 
   * @param distinct use true to request the system to return distinct result
   * */
  public void setReturnDistinct(boolean distinct);

  /**
   * Execute the query a data graph.
   * 
   * @param model the data graph model
   * @param graph the data graph
   * 
   * @return the results
   * 
   * */
  public Set<HGEResult> execute(DataGraphModel model, HDataGraph graph);
}
