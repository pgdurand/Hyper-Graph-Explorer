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

import java.util.LinkedList;

/**
 * A simple queue.
 * 
 * @author Patrick G. Durand
 */
public class HGEQueue 
{

	private LinkedList<Object> items;

	/**
	 * Create an empty queue
	 */
	 
	public HGEQueue ()
	{
		items = new LinkedList<Object>();
	}
	
	/**
	 * Inserts a new element at the rear of the queue.
	 * @param element element to be inserted.
	 */
	
	public synchronized Object enqueue (Object element)
	{
		items.add (element);
		return element;
	}
	
	/**
	 * Removes the element at the top of the queue.
	 * @return the removed element.
	 */

	public synchronized Object dequeue () 
	{
		if (items.size()== 0)	
			return null ;
		return items.removeFirst();		
	}

	/**
	 * @return the number of elements at the queue.
	 */

	public synchronized int size()
	{
		return items.size();
	}

	/**
	 * @return true of the queue is empty.
	 */	

	public synchronized boolean empty()
	{
		return (size()==0);
	}

}
