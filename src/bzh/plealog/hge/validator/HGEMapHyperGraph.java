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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;

public class HGEMapHyperGraph {

  private HashSet<HGEMapNode> _nodes;
  private HashSet<HGEMapHyperEdge> _edges;

  public HGEMapHyperGraph(){
    _nodes = new HashSet<HGEMapNode>();
    _edges = new HashSet<HGEMapHyperEdge>();
  }

  public Object clone(){
    HGEMapHyperGraph graph = new HGEMapHyperGraph();
    graph.copy(this);
    return (graph);
  }

  protected void copy (HGEMapHyperGraph graph){
    Hashtable<HGEMapNode,HGEMapNode>        cnodes;
    HGEMapNode                 node, cnode;
    HGEMapHyperEdge            edge;
    Iterator<HGEMapNode>       iter1;
    Iterator<HGEMapHyperEdge>  iter2;
    ArrayList<HGEMapNode>      al;
    Enumeration<HGEMapNode>    enum1;

    cnodes = new Hashtable<HGEMapNode,HGEMapNode>();
    iter1 = graph.getNodes().iterator();
    while(iter1.hasNext()){
      node = iter1.next();
      cnode = (HGEMapNode) node.clone();
      this.addNode(cnode);
      cnodes.put(node, cnode);
    }
    al = new ArrayList<HGEMapNode>();
    iter2 = graph.getHyperEdges().iterator();
    while(iter2.hasNext()){
      edge = iter2.next();
      enum1 = graph.nodes(edge);
      while(enum1.hasMoreElements()){
        al.add(cnodes.get(enum1.nextElement()));
      }
      this.addHyperEdge(al);
      al.clear();
    }
  }

  public void setNodes(HashSet<HGEMapNode> nodes){
    _nodes = nodes;
  }

  public HashSet<HGEMapNode> getNodes(){
    return _nodes;
  }

  public void setHyperEdges(HashSet<HGEMapHyperEdge> edges){
    _edges = edges;
  }

  public HashSet<HGEMapHyperEdge> getHyperEdges(){
    return _edges;
  }

  public void addNode(HGEMapNode node){
    _nodes.add(node);
  }

  public HGEMapHyperEdge addHyperEdge(List<HGEMapNode> nodes){
    HGEMapHyperEdge edge;
    int              i, size;

    if (nodes==null || nodes.size()<2)
      throw new RuntimeException("nodes list is invalid");
    size = nodes.size();
    edge = new HGEMapHyperEdge();
    for(i=0;i<size;i++){
      //if (nodes.get(i) instanceof HGEMapHyperEdge)
        //    throw new RuntimeException("not a node");
      addLink((HGEMapNode) nodes.get(i), edge);
    }
    _edges.add(edge);
    return edge;
  }

  private void addLink(HGEMapNode node, HGEMapHyperEdge edge){
    if (_nodes.contains(node)==false)
      throw new RuntimeException("No node");
    node.addEdge(edge);
    edge.addNode(node);
  }

  public Enumeration<HGEMapHyperEdge> hyperEdges(){
    return new Enumeration<HGEMapHyperEdge>() {
      Iterator<HGEMapHyperEdge>  iter;
      boolean   bFirst = true;

      private void initialize(){
        iter = _edges.iterator();
        bFirst = false;
      }

      public synchronized boolean hasMoreElements() {
        if (bFirst)
          initialize();
        return (iter.hasNext());
      }

      public synchronized HGEMapHyperEdge nextElement() {
        return (iter.next());
      }
    };
  }

  public Enumeration<HGEMapNode> nodes(){
    return new Enumeration<HGEMapNode>() {
      Iterator<HGEMapNode>  iter;
      boolean   bFirst = true;

      private void initialize(){
        iter = _nodes.iterator();
        bFirst = false;
      }

      public synchronized boolean hasMoreElements() {
        if (bFirst)
          initialize();
        return (iter.hasNext());
      }

      public synchronized HGEMapNode nextElement() {
        return (iter.next());
      }
    };
  }

  public Enumeration<HGEMapNode> nodes(final HGEMapHyperEdge edge){
    return (edge.getNodes());
  }


  public Enumeration<HGEMapHyperEdge> hyperEdges(final HGEMapNode node){
    return (node.getEdges());
  }

  /*on the returned Enumeration: one must call hasMoreElements() before any
   * calls of nextElement()*/
  public Enumeration<HGEMapNode> oppositeNodes(final HGEMapHyperEdge edge, final HGEMapNode node){
    return new Enumeration<HGEMapNode>() {
      Enumeration<HGEMapNode> enum1;
      boolean     bFirst = true;
      ArrayList<HGEMapNode>   al;
      Iterator<HGEMapNode>    iter;

      private void initialize(){
        enum1 = edge.getNodes();
        al = new ArrayList<HGEMapNode>();
        while(enum1.hasMoreElements()){
          al.add(enum1.nextElement());
        }
        al.remove(node);
        iter = al.iterator();
        bFirst=false;
      }

      public synchronized boolean hasMoreElements() {
        if (bFirst)
          initialize();
        return (iter.hasNext());
      }

      public synchronized HGEMapNode nextElement() {
        if (bFirst)
          initialize();
        return (iter.next());
      }
    };
  }

  /*public List getConnectedNodes(HGEMapNode node){
        ArrayList al;
        Iterator  iter;
        HGEMapEdge     edge;

        al = new ArrayList();
        iter = _edges.iterator();
        while(iter.hasNext()){
            edge = (HGEMapEdge) iter.next();
            if (edge.getSource().equals(node))
                al.add(edge.getTarget());
        }
        return (al);
    }*/

  public String toString(){
    StringBuffer szBuf;
    Iterator<HGEMapNode>     iter;

    szBuf = new StringBuffer();
    szBuf.append("Nodes:"+"\n");
    iter = _nodes.iterator();
    while(iter.hasNext()){
      szBuf.append("  "+iter.next().toString()+"\n");
    }
    return(szBuf.toString());
  }

  public void test_fill(){
    Hashtable<String, HGEMapNode> nodes = new Hashtable<String, HGEMapNode>();
    HGEMapNode             node;
    HGEMapHyperEdge        edge;
    ArrayList<HGEMapNode>  al;

    node = new HGEMapNode("v1");
    this.addNode(node);
    nodes.put(node.toString(), node);
    node = new HGEMapNode("v2");
    this.addNode(node);
    nodes.put(node.toString(), node);
    node = new HGEMapNode("v3");
    this.addNode(node);
    nodes.put(node.toString(), node);
    node = new HGEMapNode("v4");
    this.addNode(node);
    nodes.put(node.toString(), node);
    node = new HGEMapNode("v5");
    this.addNode(node);
    nodes.put(node.toString(), node);

    al = new ArrayList<HGEMapNode>();
    al.add(nodes.get("v1"));
    al.add(nodes.get("v2"));
    al.add(nodes.get("v3"));
    edge = this.addHyperEdge(al);
    edge.setUserObject("e1");

    al.clear();
    al.add(nodes.get("v2"));
    al.add(nodes.get("v4"));
    al.add(nodes.get("v5"));
    edge = this.addHyperEdge(al);
    edge.setUserObject("e2");

    al.clear();
    al.add(nodes.get("v4"));
    al.add(nodes.get("v4"));
    al.add(nodes.get("v5"));
    edge = this.addHyperEdge(al);
    edge.setUserObject("e3");

  }

  public String test_Content(){
    StringBuffer     szBuf;
    Enumeration<HGEMapNode>      enum1, enum3;
    Enumeration<HGEMapHyperEdge> enum2;
    HGEMapNode      node, opNode;
    HGEMapHyperEdge edge;

    szBuf = new StringBuffer();
    szBuf.append("Nodes:\n  ");
    enum1 = this.nodes();
    while(enum1.hasMoreElements()){
      node = enum1.nextElement();
      szBuf.append(node.toString());
      if (enum1.hasMoreElements())
        szBuf.append(" , ");
    }
    szBuf.append("\nHyperEdges:\n  ");
    enum2 = this.hyperEdges();
    while(enum2.hasMoreElements()){
      edge = enum2.nextElement();
      szBuf.append(edge.toString());
      if (enum1.hasMoreElements())
        szBuf.append(",");
    }

    szBuf.append("\nNodes connections:\n");
    enum1 = this.nodes();
    while(enum1.hasMoreElements()){
      node = enum1.nextElement();
      szBuf.append("  "+node.toString()+": ");
      enum2 = this.hyperEdges(node);
      while(enum2.hasMoreElements()){
        edge = enum2.nextElement();
        szBuf.append(edge.toString());
        if (enum2.hasMoreElements())
          szBuf.append(",");
      }
      if (enum1.hasMoreElements())
        szBuf.append("\n");
    }

    szBuf.append("\nHyperEdges connections:\n");
    enum2 = this.hyperEdges();
    while(enum2.hasMoreElements()){
      edge = enum2.nextElement();
      szBuf.append("  "+edge.toString()+": ");
      enum1 = this.nodes(edge);
      while(enum1.hasMoreElements()){
        node = enum1.nextElement();
        szBuf.append(node.toString());
        if (enum2.hasMoreElements())
          szBuf.append(",");
      }
      if (enum1.hasMoreElements())
        szBuf.append("\n");
    }

    szBuf.append("\nNodes opposite connections:\n");
    enum1 = this.nodes();
    while(enum1.hasMoreElements()){
      node = enum1.nextElement();
      enum2 = this.hyperEdges(node);
      while(enum2.hasMoreElements()){
        edge = enum2.nextElement();
        szBuf.append("  "+node.toString()+": "+edge.toString()+": ");
        enum3 = this.oppositeNodes(edge, node);
        while(enum3.hasMoreElements()){
          opNode = enum3.nextElement();
          szBuf.append(opNode.toString());
          if (enum3.hasMoreElements())
            szBuf.append(",");
        }
        if (enum2.hasMoreElements())
          szBuf.append("\n");
      }
      if (enum1.hasMoreElements())
        szBuf.append("\n");
    }


    return(szBuf.toString());
  }

  public static void main(String args[]) {
    HGEMapHyperGraph graph = new HGEMapHyperGraph();

    graph.test_fill();
    System.out.println(graph.test_Content());
  }
}
