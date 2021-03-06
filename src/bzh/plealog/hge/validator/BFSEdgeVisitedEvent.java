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
 * Defines a visitor event for graph edges.
 * 
 * @author Patrick G. Durand
 * */
public class BFSEdgeVisitedEvent extends EventObject{

  private static final long serialVersionUID = -2880981703547173921L;
    private HGEMapHyperEdge _edge;
    private HGEMapNode      _node;
    
    /**
     * Constructor.
     * 
     * @param graph the visited graph
     * @param node the visited node
     * @param edge the visited edge
     * */
    public BFSEdgeVisitedEvent (HGEMapHyperGraph graph, HGEMapNode node, HGEMapHyperEdge edge){
        super(graph);
        _edge = edge;
        _node = node;
    }

    /**
     * Returns an edge.
     * 
     * @return an edge
     * */
    public HGEMapHyperEdge getEdge() {
        return _edge;
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
