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
import bzh.plealog.hge.dataholder.HGEString;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.operator.HGENotEqual;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGENotEqualTest extends TestCase {
    private HGEString           s1, s2;
    private HGEStack            stack;
    private HGENotEqual         relOpe;
    private HGEExecutionContext context;
    
	public HGENotEqualTest(String name){
        super(name);
    }

	protected void setUp() {
        s1 = new HGEString("a");
        s2 = new HGEString("b");
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        relOpe=new HGENotEqual();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGENotEqualTest("testNotEqualTrue"));
        ts.addTest(new HGENotEqualTest("testNotEqualFalse"));
        return (ts);
	}
	
    public void testNotEqualTrue() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(s1);
        stack.push(s1);

        assertTrue(stack.size()==2);
        
        relOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result instanceof HGEBoolean);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==false);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(s1.isInUse()==false);
        assertTrue(s1.isInUse()==false);
    }

    public void testNotEqualFalse() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(s1);
        stack.push(s2);

        assertTrue(stack.size()==2);
        
        relOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==true);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(s1.isInUse()==false);
        assertTrue(s2.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
