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

import bzh.plealog.hge.dataholder.HGEBoolean;
import bzh.plealog.hge.dataholder.HGECharacter;
import bzh.plealog.hge.dataholder.HGEIdentifier;
import bzh.plealog.hge.dataholder.HGELong;
import bzh.plealog.hge.dataholder.HGEString;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.parser.HGEParserVisitor;
import bzh.plealog.hge.parser.SimpleNode;

/**
 * Abstract implementation of a parser visitor.
 * 
 * @author Patrick G. Durand
 */
public abstract class HGEVisitor implements HGEParserVisitor {
    
    protected Object visitChild(SimpleNode simplenode, int i, Object obj){
        obj = simplenode.jjtGetChild(i).jjtAccept(this, obj);
        return obj;
    }

    protected Object visitChildren(SimpleNode simplenode, Object obj){
        obj = simplenode.childrenAccept(this, obj);
        return obj;
    }
    
    protected HGEValue getChildValue(SimpleNode simplenode, int i){
        return ((SimpleNode)simplenode.jjtGetChild(i)).getValue();
    }

    protected boolean getBooleanValue(HGEValue value){
        return ((HGEBoolean)value).booleanValue();
    }

    protected long getIntegerValue(HGEValue value){
        return ((HGELong)value).longValue();
    }

    protected String getStringValue(HGEValue value){
        return ((HGEString)value).stringValue();
    }

    protected String getIdentifierValue(HGEValue value){
        return ((HGEIdentifier)value).getValue();
    }

    protected char getCharacterValue(HGEValue value){
        return ((HGECharacter)value).charValue();
    }

    protected void reportError (String message) throws HGEParserException{
        throw new HGEParserException(message);
    }
    
}
