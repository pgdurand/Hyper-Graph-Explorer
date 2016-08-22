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
import java.util.Iterator;

public class HGEMapHyperEdge extends HGEMapSimpleNode {
    
    private ArrayList<HGEMapNode> _nodes;
    
    public HGEMapHyperEdge(){
        _nodes = new ArrayList<HGEMapNode>();
    }
    
    public HGEMapHyperEdge(Object userObject){
        this();
        setUserObject(userObject);
    }

    public Object clone(){
		HGEMapHyperEdge node = new HGEMapHyperEdge();
        node.copy(this);
        return (node);
    }
    
    protected void copy (HGEMapNode node){
        super.copy(node);
    }

    protected void addNode(HGEMapNode node){
        _nodes.add(node);
    }
    
    protected void removeLink(HGEMapNode node){
        _nodes.remove(node);
    }
    
    public int getConnectivity () {
        return (_nodes.size ());
    }

    public Enumeration<HGEMapNode> getNodes(){
        return new Enumeration<HGEMapNode>() {
            Iterator<HGEMapNode>  iter;
            boolean   bFirst = true;
            
            private void initialize(){
                iter = _nodes.iterator();
                bFirst = false;
            }
            
            public boolean hasMoreElements() {
                if (bFirst)
                    initialize();
                return (iter.hasNext());
            }
            
            public HGEMapNode nextElement() {
                if (bFirst)
                    initialize();
                return (iter.next());
            }
        };
    
    }
    
}
