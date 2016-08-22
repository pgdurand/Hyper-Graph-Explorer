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
package bzh.plealog.hge.validator;

import java.util.EventObject;

/**
 * Defines a visitor event for graph nodes.
 * 
 * @author Patrick G. Durand
 * */
public class BFSNodeVisitedEvent extends EventObject{

  private static final long serialVersionUID = -347906174348422278L;
  private HGEMapNode _node;

  /**
   * Constructor.
   * 
   * @param graph the visited graph
   * @param node the visited node
   * */
  public BFSNodeVisitedEvent (HGEMapHyperGraph graph, HGEMapNode node){
    super(graph);
    _node = node;
  }

  /**
   * Returns a node.
   * 
   * @return a node
   * */
  public HGEMapNode getNode() {
    return _node;
  }
}
