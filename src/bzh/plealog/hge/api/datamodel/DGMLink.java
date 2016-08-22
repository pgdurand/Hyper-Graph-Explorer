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
 * A data model link. A link connects a vertex with an hyper-edge.
 * 
 * @author Patrick G. Durand
 */
public interface DGMLink {

  /**In-out oriented link*/
  public static final int BOTH = 0;
  /**Out oriented link*/
  public static final int OUT  = 1;
  /**In oriented link*/
  public static final int IN   = 2;

  /**Possible orientation link names*/
  public static final String ORIENTATION_REPR[]={"not oriented","in","out"};

  /**
   * Return link name
   * @return link name
   * */
  public String getName();

  /**
   * Return vertex type
   * @return vertex type
   */
  public DGMVertexType getVertexType();

  /**
   * Return orientation. 
   * 
   * @return one of BOTH, OUT or IN.
   */
  public int getOrientation(); 
}
