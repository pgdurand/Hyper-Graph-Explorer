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

/**
 * Data holder exception.
 * 
 * @author Patrick G. Durand
 * */
public class HGEDataHolderException extends RuntimeException {
  private static final long serialVersionUID = -7561542703249677815L;

  /**
   * Constructor.
   * 
   * @param msg describe the exception
   * */  
  public HGEDataHolderException(String msg){
    super(msg);
  }
}
