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
package test;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.dataholder.HGEBoolean;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.operator.HGEBooleanOr;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGEBooleanOrTest extends TestCase {
    private HGEBoolean          b1, b2, b3, b4;
    private HGEStack            stack;
    private HGEBooleanOr        boolOpe;
    private HGEExecutionContext context;
    
	public HGEBooleanOrTest(String name){
        super(name);
    }

	protected void setUp() {
        b1 = new HGEBoolean(true);
        b2 = new HGEBoolean(true);
        b3 = new HGEBoolean(false);
        b4 = new HGEBoolean(false);
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        boolOpe=new HGEBooleanOr();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGEBooleanOrTest("testOrTrue"));
        ts.addTest(new HGEBooleanOrTest("testOrFalse"));
        ts.addTest(new HGEBooleanOrTest("testOrTrueFalse"));
        return (ts);
	}
	
    public void testOrTrue() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(b1);
        stack.push(b2);

        assertTrue(stack.size()==2);
        
        boolOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result instanceof HGEBoolean);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==true);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(b1.isInUse()==false);
        assertTrue(b2.isInUse()==false);
    }

    public void testOrFalse() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(b3);
        stack.push(b4);

        assertTrue(stack.size()==2);
        
        boolOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==false);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(b3.isInUse()==false);
        assertTrue(b4.isInUse()==false);
    }

    public void testOrTrueFalse() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(b1);
        stack.push(b4);

        assertTrue(stack.size()==2);
        
        boolOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==true);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(b1.isInUse()==false);
        assertTrue(b4.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
