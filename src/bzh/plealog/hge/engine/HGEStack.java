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
package bzh.plealog.hge.engine;

import java.util.EmptyStackException;

/**
 * Define an execution stack.
 * 
 * @author Patrick G. Durand
 * */
public class HGEStack {
  protected HGEValue _elementData[];
  protected int      _elementCount;
  protected int      _capacityIncrement;

  /**
   * Constructor.
   * 
   * @param initialCapacity number of elements to start with
   * @param capacityIncrement size of stack reallocation
   * */
  public HGEStack(int initialCapacity, int capacityIncrement) {
    if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal Capacity: "+
          initialCapacity);
    _elementData = new HGEValue[initialCapacity];
    _capacityIncrement = capacityIncrement;
  }

  /**
   * Constructor.
   * 
   * @param initialCapacity number of elements to start with
   * */
  public HGEStack(int initialCapacity) {
    this(initialCapacity, 0);
  }

  /**
   * Constructor.
   * */
  public HGEStack() {
    this(10);
  }

  /**
   * Push a new value on the stack.
   * 
   * @param item the value to push on the stack
   * @return return method argument
   * */
  public synchronized HGEValue push(HGEValue item) {
    //addElement(item);
    ensureCapacityHelper(_elementCount + 1);
    _elementData[_elementCount++] = item;

    return item;
  }
  /*public synchronized void addElement(HGEValue obj) {
    ensureCapacityHelper(_elementCount + 1);
    _elementData[_elementCount++] = obj;
  }*/

  /**
   * Pop a value from the execution stack.
   * 
   * @return the popped value
   * */
  public synchronized HGEValue pop() {
    HGEValue	obj;
    int	len = size();

    obj = peek();
    removeElementAt(len - 1);

    return obj;
  }

  /**
   * Get the top value on the stack.
   * 
   * @return the top value
   * */
  public synchronized HGEValue peek() {
    int	len = size();

    if (len == 0)
      throw new EmptyStackException();
    return elementAt(len - 1);
  }

  /**
   * Figure our whether or not the execution stack is empty.
   * 
   * @return true or false
   * */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Get the size of the stack.
   * 
   * @return the number of values contained in the execution stack.
   * */
  public synchronized int size() {
    return _elementCount;
  }

  /**
   * Reallocate the stack is needed.
   * 
   * @param minCapacity minimum nb of elements of the stack
   * */
  private void ensureCapacityHelper(int minCapacity) {
    int oldCapacity = _elementData.length;
    if (minCapacity > oldCapacity) {
      Object oldData[] = _elementData;
      int newCapacity = (_capacityIncrement > 0) ?
          (oldCapacity + _capacityIncrement) : (oldCapacity * 2);
          if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
          }
          _elementData = new HGEValue[newCapacity];
          System.arraycopy(oldData, 0, _elementData, 0, _elementCount);
    }
  }

  /**
   * Get the value at a particular position in the stack.
   * 
   * @param index position in the stack
   * 
   * @return the corresponding value
   * @throws ArrayIndexOutOfBoundsException if index is wrong
   * */
  public synchronized HGEValue elementAt(int index) {
    if (index >= _elementCount) {
      throw new ArrayIndexOutOfBoundsException(index + " >= " + _elementCount);
    }
    try {
      return _elementData[index];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException(index + " < 0");
    }
  }
  /**
   * Remove the value at a particular position in the stack.
   * 
   * @param index position in the stack
   * 
   * @throws ArrayIndexOutOfBoundsException if index is wrong
   * */
  public synchronized void removeElementAt(int index) {
    if (index >= _elementCount) {
      throw new ArrayIndexOutOfBoundsException(index + " >= " + 
          _elementCount);
    }
    else if (index < 0) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    int j = _elementCount - index - 1;
    if (j > 0) {
      System.arraycopy(_elementData, index + 1, _elementData, index, j);
    }
    _elementCount--;
    _elementData[_elementCount] = null;
  }

  /**
   * Reset the content of the stack.
   * */
  public synchronized void removeAllElements() {
    // Let gc do its work
    for (int i = 0; i < _elementCount; i++){
      _elementData[i] = null;
    }
    _elementCount = 0;
  }
}
