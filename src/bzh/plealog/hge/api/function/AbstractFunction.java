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
package bzh.plealog.hge.api.function;

import bzh.plealog.hge.api.datamodel.DGMAttribute;

/**
 * Describe an abstract function.
 * 
 * @author Patrick G. Durand
 */
public abstract class AbstractFunction implements Function {

  /**
   * Return a string representation of a function.
   * 
   * @return a string representation of a function
   * */
  public String toString(){
    StringBuffer szBuf;
    int          cType, i, args;
    int[]        data, container;

    szBuf = new StringBuffer(getName());
    szBuf.append("(");
    args = arguments();
    if (args!=0){
      data = getArgDataTypes();
      container = getArgContainerTypes();
      for(i=0;i<args;i++){
        cType = container[i];
        if (cType != DGMAttribute.CT_ATOMIC){
          szBuf.append(DGMAttribute.CT_REPR[cType]);
          szBuf.append(" ");
        }
        szBuf.append(DGMAttribute.DT_REPR[data[i]]);
        if ((i+1)<args){
          szBuf.append(", ");
        }
      }
    }
    szBuf.append(") : ");
    cType = getReturnContainerType();
    if (cType != DGMAttribute.CT_ATOMIC){
      szBuf.append(DGMAttribute.CT_REPR[cType]);
      szBuf.append(" ");
    }
    szBuf.append(DGMAttribute.DT_REPR[getReturnDataType()]);
    return (szBuf.toString());
  }

}
