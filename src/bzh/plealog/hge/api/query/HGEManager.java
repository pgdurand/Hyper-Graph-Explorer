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

import bzh.plealog.hge.engine.HQueryImplem;

/**
 * Define the Hyper Graph Explorer Query Manager system.
 * 
 * @author Patrick G. Durand
 */
public abstract class HGEManager {

  private HGEManager(){}

  /**
   * Create a new query.
   * 
   * @return a new query
   * */
  public static HGEQuery newHQuery(){
    return new HQueryImplem();
  }

}
