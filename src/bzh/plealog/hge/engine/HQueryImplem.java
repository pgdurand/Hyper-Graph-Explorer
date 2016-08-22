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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import bzh.plealog.hge.api.datamodel.DataGraphModel;
import bzh.plealog.hge.api.hypergraph.HDataGraph;
import bzh.plealog.hge.api.query.HGEEngine;
import bzh.plealog.hge.api.query.HGEQuery;
import bzh.plealog.hge.api.query.HGEQueryException;
import bzh.plealog.hge.api.query.HGEResult;
import bzh.plealog.hge.dataholder.HGEQueryDeclaration;
import bzh.plealog.hge.parser.HGEParser;
import bzh.plealog.hge.parser.SimpleNode;
import bzh.plealog.hge.validator.HGEMapBuilder;
import bzh.plealog.hge.validator.HGEValidator;

/**
 * Concrete implementation of HGEQuery.
 */
public class HQueryImplem implements HGEQuery {

  private static final String MODIFICATION_EXCEPTION = "Query cannot be modified.";
  private HGEQueryDeclaration _queryObject;
  private ArrayList<String>   _declaration;
  private ArrayList<String>   _constraint;
  private List<String>        _searchMap;
  private String              _retVars;
  private boolean             _distinct;
  private boolean             _lock;
  private String              _queryRepr;

  /**
   * Constructor.
   **/
  public HQueryImplem(){
    _declaration = new ArrayList<String>();
    _constraint = new ArrayList<String>();
  }

  /**
   * @see HGEQuery#setQuery(String)
   */
  public void setQuery(String query){
    if (_lock)
      throw new HGEQueryException(MODIFICATION_EXCEPTION);
    if (query!=null){
      _queryRepr = query;
      _lock=true;
    }
  }

  private String construct(){
    StringBuffer     query;
    Iterator<String> iter;

    if (_queryRepr!=null)
      return _queryRepr;

    if (_declaration.isEmpty())
      new HGEQueryException("Query does not define any declaration.");

    query = new StringBuffer();
    query.append("from ");
    iter = _declaration.iterator();
    while(iter.hasNext()){
      query.append(iter.next());
      if (iter.hasNext())
        query.append(",\n     ");
    }
    query.append("\n");
    if (!_constraint.isEmpty()){
      query.append("where ");
      iter = _constraint.iterator();
      while(iter.hasNext()){
        query.append(iter.next());
        if (iter.hasNext())
          query.append("\n      and ");
      }
      query.append("\n");
    }
    query.append("return ");
    if (_distinct){
      query.append("distinct ");
    }

    if (_retVars!=null){
      query.append(_retVars);
    }
    else{
      query.append("*");
    }
    query.append(" ;");
    return (query.toString());
  }

  /**
   * @see HGEQuery#compile(DataGraphModel)
   */
  public String compile(DataGraphModel model) {
    HGEParser           parser;
    String              szQuery = null;
    SimpleNode          root;
    HGEQueryDeclaration query;
    HGEMapBuilder       mBuilder;

    if (_queryObject!=null){
      return szQuery;
    }

    try {
      //return the String representation of the query
      szQuery = construct();
      //analyse the syntax of the query
      parser = new HGEParser(new StringReader(szQuery));
      root = parser.Query();
      //check the query against the data model
      HGEValidator validator = new HGEValidator(model);
      root.jjtAccept(validator, null);
      //create a query object
      query = new HGEQueryDeclaration("query");
      query.setGraphs(validator.getGraphs());
      query.setConstraints(validator.getConstraints());
      query.setReturnVars(validator.getReturnVars());
      query.setDistinctReturnVars(validator.getReturnDistinctVars());
      mBuilder = new HGEMapBuilder(query);
      _searchMap = mBuilder.createMap();
      _queryObject = query;
      _lock=true;
    }
    catch(Exception ex){
      throw new HGEQueryException(ex.getMessage());
    }
    return szQuery;
  }

  /**
   * @see HGEQuery#addConstraint(java.lang.String)
   */
  public void addConstraint(String constraint) {
    if (_lock)
      throw new HGEQueryException(MODIFICATION_EXCEPTION);
    if (constraint!=null){
      _constraint.add(constraint);
    }
  }

  /**
   * @see HGEQuery#addDeclaration(java.lang.String)
   */
  public void addDeclaration(String declaration) {
    if (_lock)
      throw new HGEQueryException(MODIFICATION_EXCEPTION);
    if (declaration!=null){
      _declaration.add(declaration);
    }
  }

  /**
   * @see HGEQuery#setReturnVariables(java.lang.String)
   */
  public void setReturnVariables(String ret) {
    if (_lock)
      throw new HGEQueryException(MODIFICATION_EXCEPTION);
    if (ret!=null)
      _retVars = ret;
  }

  /**
   * @see HGEQuery#setReturnDistinct(boolean)
   */
  public void setReturnDistinct(boolean distinct) {
    if (_lock)
      throw new HGEQueryException(MODIFICATION_EXCEPTION);
    _distinct = distinct;
  }

  /**
   * @see HGEQuery#execute(DataGraphModel, HDataGraph)
   */
  public Set<HGEResult> execute(DataGraphModel model, HDataGraph graph) {
    Set<HGEResult>                 result=null;
    HGEEngine     engine;

    try {
      compile(model);
      engine = new HGEEngine(model, graph, _queryObject, _searchMap);
      engine.execute();
      result=engine.getResult();
    }
    catch(Exception ex){
      throw new HGEQueryException(ex.getMessage());
    }
    return result;
  }

}
