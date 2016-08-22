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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import bzh.plealog.hge.dataholder.HGEEdgeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEGraphVarDeclaration;
import bzh.plealog.hge.dataholder.HGENodeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEPathVarDeclaration;
import bzh.plealog.hge.dataholder.HGEQueryDeclaration;
import bzh.plealog.hge.dataholder.HGEVarDeclaration;


public class HGEMapBuilder {

  private HGEQueryDeclaration _query;

  private HGEMapBuilder(){}

  public HGEMapBuilder(HGEQueryDeclaration query){
    this();
    if (query==null || query.getGraphs().isEmpty())
      throw new RuntimeException("query is empty.");
    _query = query;
  }

  public HGEMapHyperGraph convertQuery(){
    HGEMapHyperGraph       g;
    HGEMapNode             node;
    HGEMapHyperEdge        edge;
    Hashtable<String, HGEMapNode> nodes;
    Hashtable<String, HGEMapHyperEdge> edges;
    HGEEdgeVarDeclaration  vEdge;
    HGEPathVarDeclaration  vPath;
    HGEGraphVarDeclaration graph;
    Enumeration<HGEVarDeclaration>             enum2;
    Enumeration<HGEGraphVarDeclaration> enum1;
    Object                  obj;
    String                  name;
    ArrayList<String> al;
    ArrayList<HGEMapNode>               nodeLinked;
    int                     i, size;

    g = new HGEMapHyperGraph();
    nodes = new Hashtable<String, HGEMapNode>();
    edges = new Hashtable<String, HGEMapHyperEdge>();

    enum1 = _query.getGraphs().elements();
    while(enum1.hasMoreElements()){
      graph = enum1.nextElement();
      enum2 = graph.getVars().elements();
      while(enum2.hasMoreElements()){
        obj = enum2.nextElement();
        if (obj instanceof HGENodeVarDeclaration && 
            !(obj instanceof HGEEdgeVarDeclaration)){
          name = ((HGENodeVarDeclaration) obj).getName();
          if (nodes.containsKey(name)==false){
            node = new HGEMapNode(obj);
            nodes.put(name, node);
            g.addNode(node);
          }
        }
      }
    }

    nodeLinked = new ArrayList<HGEMapNode>();
    enum1 = _query.getGraphs().elements();
    while(enum1.hasMoreElements()){
      graph = enum1.nextElement();
      enum2 = graph.getVars().elements();
      while(enum2.hasMoreElements()){
        obj = enum2.nextElement();
        if (obj instanceof HGEEdgeVarDeclaration){
          vEdge = (HGEEdgeVarDeclaration) obj;
          name = vEdge.getName();
          if (edges.containsKey(name)==false){
            al = vEdge.getNodeVars();
            size = al.size();
            nodeLinked.clear();
            for (i=0;i<size;i++){
              nodeLinked.add(nodes.get(al.get(i)));
            }
            edge = g.addHyperEdge(nodeLinked);
            edge.setUserObject(vEdge);
            edges.put(name, edge);
          }
        }
        else if (obj instanceof HGEPathVarDeclaration){
          vPath = (HGEPathVarDeclaration) obj;
          name = vPath.getName();
          if (edges.containsKey(name)==false){
            nodeLinked.clear();
            nodeLinked.add(nodes.get(vPath.getFromNodeVar()));
            nodeLinked.add(nodes.get(vPath.getToNodeVar()));
            edge = g.addHyperEdge(nodeLinked);
            edges.put(name, edge);
            edge.setUserObject(vPath);
          }
        }
      }
    }

    return (g);    
  }

  public List<String> createMap(){
    HGEMapHyperGraph g = convertQuery();
    MyVisitor    visitor = new MyVisitor(g);
    BFSScanner   scanner = new BFSScanner();

    scanner.addVisitor(visitor);
    scanner.scan (g, null);

    return visitor.getMap();
  }

  private class MyVisitor implements BFSVisitor{ 
    private ArrayList<String>   map;
    //private int         nodes=0;
    private HGEMapHyperGraph _graph;

    public MyVisitor(HGEMapHyperGraph graph){
      map = new ArrayList<String>();
      _graph = graph;
    }

    public List<String> getMap(){
      return map;
    }

    /*public int getVisitedNodes(){
      return nodes;
    }*/

    public void visit (BFSNodeVisitedEvent nve){
      //nodes++;
    }

    public void visit (BFSEdgeVisitedEvent eve){
      HGENodeVarDeclaration   vNode;
      HGEEdgeVarDeclaration   vEdge;
      Enumeration<HGEMapNode> enum1;

      vNode = (HGENodeVarDeclaration) eve.getNode().getUserObject();
      vEdge = (HGEEdgeVarDeclaration) eve.getEdge().getUserObject();

      map.add(vNode.getName());
      map.add(vEdge.getName());

      enum1 = _graph.oppositeNodes(eve.getEdge(), eve.getNode());
      while(enum1.hasMoreElements()){
        vNode = (HGENodeVarDeclaration) 
            (enum1.nextElement()).getUserObject();
        map.add(vNode.getName());
      }
    }
  }

}
