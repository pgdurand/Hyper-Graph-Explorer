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

public class HGEMapNode extends HGEMapSimpleNode {
	protected HashSet<HGEMapHyperEdge> _edges;
    
    public HGEMapNode(){
        _edges = new HashSet<HGEMapHyperEdge>();
    }
    
    public HGEMapNode(Object userObject){
        this();
        setUserObject(userObject);
    }

    public Object clone(){
        HGEMapNode node = new HGEMapNode();
        node.copy(this);
        return (node);
    }
    
    protected void copy (HGEMapNode node){
        super.copy(node);
    }
    
    protected void addEdge(HGEMapHyperEdge link){
        _edges.add(link);
    }
	
    protected void removeLink(HGEMapHyperEdge link){
        _edges.remove(link);
    }
    
    public int getConnectivity () {
		return (_edges.size ());
	}

    public Enumeration<HGEMapHyperEdge> getEdges(){
        return new Enumeration<HGEMapHyperEdge>() {
            Iterator<HGEMapHyperEdge>  iter;
            boolean   bFirst = true;
            
            private void initialize(){
                iter = _edges.iterator();
                bFirst = false;
            }
            
            public boolean hasMoreElements() {
                if (bFirst)
                    initialize();
                return (iter.hasNext());
            }
            
            public HGEMapHyperEdge nextElement() {
                if (bFirst)
                    initialize();
                return (iter.next());
            }
        };
    
    }
}
