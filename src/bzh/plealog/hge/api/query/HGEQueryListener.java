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
package bzh.plealog.hge.api.query;

/**
 * Interface to implement to listen to the query execution engine.
 * 
 * @author Patrick G. Durand
 */
public interface HGEQueryListener {
  /**
   * Method called when a new results is found.
   * 
   * @param result a new result object. DO NOT edit its content.
   */
  public void matchFound(HGEResult result);
  
  public void queryExecutionStarted();

  public void queryExecutionDone();

}
