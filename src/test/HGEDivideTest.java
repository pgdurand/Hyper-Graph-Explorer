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
import bzh.plealog.hge.dataholder.HGEDouble;
import bzh.plealog.hge.dataholder.HGELong;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;
import bzh.plealog.hge.operator.HGEDivide;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGEDivideTest extends TestCase {
    private HGEDouble           d1, d2;
    private HGELong             l1, l2;
    private HGEStack            stack;
    private HGEDivide           adder;
    private HGEExecutionContext context;
    
	public HGEDivideTest(String name){
        super(name);
    }

	protected void setUp() {
        d1 = new HGEDouble(5.0);
        d2 = new HGEDouble(6.0);
        l1 = new HGELong(5);
        l2 = new HGELong(6);
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        adder=new HGEDivide();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGEDivideTest("testDoubleDiv"));
        ts.addTest(new HGEDivideTest("testLongDiv"));
        ts.addTest(new HGEDivideTest("testDoubleLongDiv"));
        return (ts);
	}
	
    public void testDoubleDiv() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(d1);
        stack.push(d2);

        assertTrue(stack.size()==2);
        
        adder.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertTrue(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_DOUBLE);
        assertTrue(result.doubleValue()==5.0/6.0);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==true);
        assertTrue(d1.isInUse()==false);
        assertTrue(d2.isInUse()==false);
    }

    public void testLongDiv() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(l1);
        stack.push(l2);

        assertTrue(stack.size()==2);
        
        adder.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertTrue(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_LONG);
        assertTrue(result.longValue()==5/6);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==true);
        assertTrue(l1.isInUse()==false);
        assertTrue(l2.isInUse()==false);
    }

    public void testDoubleLongDiv() {
        HGEValue              result;
        assertTrue(stack.size()==0);

        stack.push(l1);
        stack.push(d2);

        assertTrue(stack.size()==2);
        
        adder.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertTrue(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_DOUBLE);
        assertTrue(result.doubleValue()==5.0/6.0);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==true);
        assertTrue(l1.isInUse()==false);
        assertTrue(d2.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
