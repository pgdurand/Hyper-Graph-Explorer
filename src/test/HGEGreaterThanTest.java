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
import bzh.plealog.hge.dataholder.HGEDouble;
import bzh.plealog.hge.dataholder.HGELong;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.operator.HGEGreaterThan;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGEGreaterThanTest extends TestCase {
    private HGEDouble           d1, d2;
    private HGELong             l1, l2;
    private HGEStack            stack;
    private HGEGreaterThan      relOpe;
    private HGEExecutionContext context;
    
	public HGEGreaterThanTest(String name){
        super(name);
    }

	protected void setUp() {
        d1 = new HGEDouble(5.0);
        d2 = new HGEDouble(6.0);
        l1 = new HGELong(5);
        l2 = new HGELong(6);
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        relOpe=new HGEGreaterThan();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGEGreaterThanTest("testDoubleGT"));
        ts.addTest(new HGEGreaterThanTest("testLongGT"));
        ts.addTest(new HGEGreaterThanTest("testDoubleLongGT"));
        return (ts);
	}
	
    public void testDoubleGT() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(d1);
        stack.push(d2);
        d1.setInUse(true);
        d2.setInUse(true);

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
        assertTrue(d1.isInUse()==false);
        assertTrue(d2.isInUse()==false);
    }

    public void testLongGT() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(l1);
        stack.push(l2);
        l1.setInUse(true);
        l2.setInUse(true);

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
        assertTrue(l1.isInUse()==false);
        assertTrue(l2.isInUse()==false);
    }

    public void testDoubleLongGT() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(d1);
        stack.push(l2);
        d1.setInUse(true);
        l2.setInUse(true);

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
        assertTrue(d1.isInUse()==false);
        assertTrue(l2.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
