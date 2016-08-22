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

import java.util.EventListener;

/**
 * Defines the graph visitor interface.
 * 
 * @author Patrick G. Durand
 */
public interface BFSVisitor extends EventListener{

  /**
   * We visit a node.
   * 
   * @param nve the visited node
   */
  public void visit (BFSNodeVisitedEvent nve);

  /**
   * We visit an edge.
   * 
   * @param eve the visited edge
   */
  public void visit (BFSEdgeVisitedEvent eve);
}
