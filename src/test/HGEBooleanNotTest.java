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
import bzh.plealog.hge.operator.HGEBooleanNot;
import junit.framework.*;



/**
 * Some simple tests.
 *
 */
public class HGEBooleanNotTest extends TestCase {
    private HGEBoolean          b1, b2;
    private HGEStack            stack;
    private HGEBooleanNot       boolOpe;
    private HGEExecutionContext context;
    
	public HGEBooleanNotTest(String name){
        super(name);
    }

	protected void setUp() {
        b1 = new HGEBoolean(true);
        b2 = new HGEBoolean(false);
        stack = new HGEStack();
        context = new HGEExecutionContext(null, null);
        boolOpe=new HGEBooleanNot();
	}

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new HGEBooleanNotTest("testNotTrue"));
        ts.addTest(new HGEBooleanNotTest("testNotFalse"));
        return (ts);
	}
	
    public void testNotTrue() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(b1);

        assertTrue(stack.size()==1);
        
        boolOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result instanceof HGEBoolean);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==false);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(b1.isInUse()==false);
    }

    public void testNotFalse() {
        HGEValue              result;
        
        assertTrue(stack.size()==0);

        stack.push(b2);

        assertTrue(stack.size()==1);
        
        boolOpe.evaluate(context, stack);

        assertTrue(stack.size()==1);
        
        result = stack.pop();
        
        assertFalse(result instanceof HGEReusableArgumentValue);
        assertTrue(result.getContainerType()==DGMAttribute.CT_ATOMIC);
        assertTrue(result.getDataType()==DGMAttribute.DT_BOOLEAN);
        assertTrue(result.booleanValue()==true);
        assertTrue(stack.size()==0);
        assertTrue(result.isInUse()==false);
        assertTrue(b2.isInUse()==false);
    }

	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
