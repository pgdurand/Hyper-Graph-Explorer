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
import bzh.plealog.hge.operator.HGEUnaryMinus;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGEUnaryMinusTest extends TestCase {
    private HGEDouble           d1;
    private HGELong             l1;
    private HGEStack            stack;
    private HGEUnaryMinus       umOpe;
    private HGEExecutionContext context;
    
	public HGEUnaryMinusTest(String name){
        super(name);
    }

	protected void setUp() {
        d1 = new HGEDouble(5.0);
        l1 = new HGELong(5);
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        umOpe=new HGEUnaryMinus();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGEUnaryMinusTest("testDoubleUM"));
        ts.addTest(new HGEUnaryMinusTest("testLongUM"));
        return (ts);
	}
	
    public void testDoubleUM() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(d1);
        d1.setInUse(true);

        assertTrue(stack.size()==1);
        
        umOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertTrue(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_DOUBLE);
        assertTrue(result.doubleValue()==-d1.doubleValue());
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==true);
        assertTrue(d1.isInUse()==false);
    }

    public void testLongUM() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(l1);
        l1.setInUse(true);

        assertTrue(stack.size()==1);
        
        umOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertTrue(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_LONG);
        assertTrue(result.longValue()==-l1.longValue());
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==true);
        assertTrue(l1.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
