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

public abstract class HGEMapSimpleNode implements Cloneable {

    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;
    protected Object  _userObject;
	protected int     _bfsClr;
    
    protected void copy (HGEMapSimpleNode node){
        this.setUserObject(node.getUserObject());
    }
    
    public void setUserObject(Object userObject){
        _userObject = userObject;
    }

    public Object getUserObject(){
        return _userObject;
    }
    
    public String toString(){
        if (_userObject!=null)
            return (_userObject.toString());
        else
            return (super.toString());
    }
    
    public void setBFSColor(int clr){
        _bfsClr = clr;
    }

    public int getBFSColor(){
        return (_bfsClr);
    }
    
    public int hashCode(){
        if (_userObject!=null)
            return _userObject.toString().hashCode();
        return super.hashCode();
    }
}
