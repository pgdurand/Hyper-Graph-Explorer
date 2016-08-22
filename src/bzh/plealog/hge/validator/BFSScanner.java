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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Enumeration;

import javax.swing.event.EventListenerList;

/**
 * Defines a graph scanner. This is the main component used to traverse a graph.
 * 
 * @author Patrick G. Durand
 */
public class BFSScanner {

  private EventListenerList _visitors;

  /**
   * Constructor.
   */
  public BFSScanner(){
    _visitors = new EventListenerList();
  }

  /**
   * Notify all listeners that have registered interest for
   * notification on this event type.
   * 
   * @param nve the node event
   */
  protected void fireBFSNodeVisited(BFSNodeVisitedEvent nve) {
    // Guaranteed to return a non-null array
    Object[] listeners = _visitors.getListenerList();
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length-2; i>=0; i-=2) {
      if (listeners[i]==BFSVisitor.class) {
        ((BFSVisitor)listeners[i+1]).visit(nve);
      }
    }
  }

  /**
   * Notify all listeners that have registered interest for
   * notification on this event type.  
   * 
   * @param nve the edge event
   */
  protected void fireBFSEdgeVisited(BFSEdgeVisitedEvent eve) {
    // Guaranteed to return a non-null array
    Object[] listeners = _visitors.getListenerList();
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length-2; i>=0; i-=2) {
      if (listeners[i]==BFSVisitor.class) {
        ((BFSVisitor)listeners[i+1]).visit(eve);
      }
    }
  }

  /**
   * Add a visitor to this scanner.
   * 
   * @param visitor the visitor*/
  public void addVisitor(BFSVisitor visitor){
    if (visitor==null)
      return;
    _visitors.add(BFSVisitor.class, visitor);
  }


  /**
   * Remove a visitor from this scanner.
   * 
   * @param visitor the visitor*/
  public void removeVisitor(BFSVisitor visitor){
    if (visitor==null)
      return;
    _visitors.remove(BFSVisitor.class, visitor);
  }    

  /**
   * Start the graph traversal.
   * 
   * @param graph the graph to explore
   * @param startNode starting node
   */
  public void scan (HGEMapHyperGraph graph, HGEMapNode startNode)
  {
    Iterator<HGEMapNode>         iter;
    HGEMapNode                   curVertex, nextVertex;
    HGEMapHyperEdge              curEdge;
    HGEQueue                     q;
    Enumeration<HGEMapHyperEdge> enum1;
    Enumeration<HGEMapNode>      enum2;
    boolean                      bFirst = true;
    HashSet<HGEMapHyperEdge>     edgesViewed;

    iter = graph.getNodes().iterator();
    while(iter.hasNext()){
      curVertex = (HGEMapNode) iter.next();
      if (bFirst){
        bFirst=false;
        if (startNode==null)
          startNode=curVertex;
      }
      curVertex.setBFSColor(HGEMapNode.WHITE);
    }

    startNode.setBFSColor(HGEMapNode.GRAY);

    q = new HGEQueue();
    curVertex = null ;
    curEdge = null;
    edgesViewed = new HashSet<HGEMapHyperEdge>();
    q.enqueue(startNode);
    fireBFSNodeVisited(new BFSNodeVisitedEvent(graph, startNode));
    while (!(q.empty())) {
      curVertex=(HGEMapNode) q.dequeue();
      enum1 = graph.hyperEdges(curVertex);
      while (enum1.hasMoreElements ()) {
        curEdge = enum1.nextElement();
        if (!edgesViewed.contains(curEdge)){
          edgesViewed.add(curEdge);
          fireBFSEdgeVisited(new BFSEdgeVisitedEvent(graph, curVertex, curEdge));
          enum2 = graph.nodes(curEdge);
          while(enum2.hasMoreElements ()){
            nextVertex = enum2.nextElement();
            if (nextVertex.getBFSColor() == HGEMapNode.WHITE) {
              fireBFSNodeVisited(new BFSNodeVisitedEvent(graph, nextVertex));
              nextVertex.setBFSColor(HGEMapNode.GRAY);
              q.enqueue(nextVertex);
            }
          }
        }
      }
      curVertex.setBFSColor(HGEMapNode.BLACK);
    }
  }
}
