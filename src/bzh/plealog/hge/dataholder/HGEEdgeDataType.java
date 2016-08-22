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

/**
 * Define an edge data type.
 * 
 * @author Patrick G. Durand
 * */
public class HGEEdgeDataType extends HGEAbstractDataType {

  private HGEEdgeDataType()
  {
    setDataType(DGMAttribute.DT_STRING);
    setContainerType(DGMAttribute.CT_ATOMIC);
  }

  /**
   * Constructor.
   * 
   * @param s the data type
   * */
  public HGEEdgeDataType(String s)
  {
    this();
    setValue(s);
  }

  /**
   * Constructor.
   * 
   * @param val the data type
   * */
  public HGEEdgeDataType(HGEString val)
  {
    this();
    setValue(val.stringValue());
  }


  /**
   * Set the data type..
   * 
   * @param val the data type
   * */
  public void setValue(HGEEdgeDataType val)
  {
    setValue(val.getValue());
  }

  /**
   * 
   * @see Object#equals(Object)
   * */
  public boolean equals (Object obj){
    if (obj!=null  && (obj instanceof HGEEdgeDataType)){
      return (((HGEEdgeDataType)obj).getValue().equalsIgnoreCase(this.getValue()));
    }
    return false;
  }

}
