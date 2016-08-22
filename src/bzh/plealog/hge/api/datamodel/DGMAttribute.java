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
package bzh.plealog.hge.api.datamodel;

/**
 * A data model attribute.
 * 
 * @author Patrick G. Durand
 */
public interface DGMAttribute {
  /**Attribute is of type string.*/
  public static final int DT_STRING    = 0;
  /**Attribute is of type boolean.*/
  public static final int DT_BOOLEAN   = 1;
  /**Attribute is of type character.*/
  public static final int DT_CHARACTER = 2;
  /**Attribute is of type double.*/
  public static final int DT_DOUBLE    = 3;
  /**Attribute is of type long.*/
  public static final int DT_LONG      = 4;
  /**Attribute is of type graph object.*/
  public static final int DT_GRAPH_OBJ = 5;
  /**Attribute is of type date.*/
  public static final int DT_DATE      = 6;

  /**Attribute container is of type list.*/
  public static final int CT_LIST      = 0;
  /**Attribute container is of type set.*/
  public static final int CT_SET       = 1;
  /**Attribute container is of type atomic.*/
  public static final int CT_ATOMIC    = 2;

  /**String representation of data types.*/
  public static final String[] DT_REPR={"string","boolean","char","double","long","graphObject","date"};
  /**String representation of container types.*/
  public static final String[] CT_REPR={"list of","set of","atomic"};

  /**
   * Return the name of the attribute.
   * 
   * @return attribute's name
   * */
  public String getName();

  /**
   * Return the data type.
   * 
   * @return one of DT_XXX constants
   * */
  public int getJavaType(); //getDataType

  /**
   * Return the container type.
   * 
   * @return one of CT_XXX constants
   * */
  public int getContainerType();
}
