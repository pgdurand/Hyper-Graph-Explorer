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
package bzh.plealog.hge.dataholder;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define an identifier.
 * 
 * @author Patrick G. Durand
 * */
public class HGEIdentifier extends HGEValue
{
    private String _value;

    private HGEIdentifier()
    {
        setDataType(DGMAttribute.DT_STRING);
        setContainerType(DGMAttribute.CT_ATOMIC);
    }

    /**
     * Constructor.
     * 
     * @param s the identifier
     * */
    public HGEIdentifier(String s)
    {
        this();
        setValue(s);
    }

    /**
     * Constructor.
     * 
     * @param val the identifier
     * */
    public HGEIdentifier(HGEString val)
    {
        this();
        setValue(val.stringValue());
    }

    /**
     * Get the value.
     * 
     * @return the value
     * */
    public String getValue()
    {
        return _value;
    }

    /**
     * Set the value.
     * 
     * @param s the value
     * */
    public void setValue(String s)
    {
        _value = s;
    }

    /**
     * Set the value.
     * 
     * @param val the value
     * */
    public void setValue(HGEIdentifier val)
    {
        setValue(val.getValue());
    }

    /**
     * @see Object#toString()
     * */
    public String toString()
    {
        return _value;
    }
    
    /**
     * @see Object#equals(Object)
     * */
    public boolean equals (Object obj){
        if (obj!=null  && (obj instanceof HGEIdentifier)){
            return (((HGEIdentifier)obj).getValue().equalsIgnoreCase(this.getValue()));
        }
        return false;
    }


    /**
     * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
     **/
    public void interpret(HGEExecutionContext context, HGEStack stack){
    }
}
