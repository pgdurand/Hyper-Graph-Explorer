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

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import org.apache.log4j.Logger;




import bzh.plealog.hge.api.datamodel.DataGraphModel;
import bzh.plealog.hge.api.hypergraph.HDGHyperEdge;
import bzh.plealog.hge.api.hypergraph.HDGLink;
import bzh.plealog.hge.api.hypergraph.HDGObject;
import bzh.plealog.hge.api.hypergraph.HDGVertex;
import bzh.plealog.hge.api.hypergraph.HDataGraph;
import bzh.plealog.hge.dataholder.HGEEdgeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEGraphVarDeclaration;
import bzh.plealog.hge.dataholder.HGENodeVarDeclaration;
import bzh.plealog.hge.dataholder.HGEPathVarDeclaration;
import bzh.plealog.hge.dataholder.HGEQueryDeclaration;
import bzh.plealog.hge.dataholder.HGEVarAccess;
import bzh.plealog.hge.dataholder.HGEVarDeclaration;
import bzh.plealog.hge.dataholder.HGEVarInstance;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEResultImplem;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Hyper Graph Explorer execution engine.
 * 
 * 
 * @author Patrick G. Durand
 */
public class HGEEngine {

	private List<String>        _searchMap;
	private HGEQueryDeclaration _query;
	private DataGraphModel      _model;
	private HDataGraph          _dGraph;
	private HGEExecutionContext _context;
	private int[]               _varToIdxMapper;
	private Hashtable<String, Integer> _varNameToResultIdx;
	private int                 _searchMapSize;
	private HGEStack            _evaluator;
	//  private int                 _nResult;
	private boolean             _verbose;
	private long                _runningTime;
	private HashSet<HGEResult>  _result;

	//  private Logger               _logger = Logger.getLogger(HGEEngine.class);

	private HGEEngine(){}

	/**
	 * Constructor.
	 * 
	 * @param model the data model
	 * @param graph the data
	 * @param query the query
	 * @param searchMap a helper search map*/
	public HGEEngine(DataGraphModel model, HDataGraph graph, 
			HGEQueryDeclaration query, List<String> searchMap) {
		this();
		_query = query;
		_dGraph = graph;
		_model = model;
		_searchMap = searchMap;
		_searchMapSize = _searchMap.size();
		createVarToIdxMap();
		_evaluator = new HGEStack();
		_context = new HGEExecutionContext(_model, _dGraph);
	}

	/**
	 * Set the verbose mode of the engine. Default is false.
	 * 
	 * @param b pass true to enable verbose mode.
	 * */
	public void setVerbose(boolean b){
		_verbose=b;
	}

	/**
	 * Return the verbose mode of the engine.
	 * 
	 * @return true of false
	 * */
	public boolean getVerbose(){
		return _verbose;
	}

	/**
	 * Return the running time of the engine.
	 * 
	 * @return running time in milliseconds.
	 * */
	public long getRunningTime(){
		return _runningTime;
	}

	/**
	 * Return the result.
	 *
	 * @return the result.
	 **/
	public HashSet<HGEResult> getResult(){
		return _result;
	}

	private void createVarToIdxMap(){
		HGEVarDeclaration                    var;
		Hashtable<String, Integer>           map;
		Hashtable<String, HGEVarDeclaration> vars;
		Enumeration<String>                  enum1;
		int                                  idx=0, i;

		map = new Hashtable<String, Integer>();

		vars = _query.getAllVars();
		enum1 = vars.keys();
		while(enum1.hasMoreElements()){
			var = (HGEVarDeclaration) vars.get(enum1.nextElement());
			map.put(var.getName(),
					new Integer(idx));
			idx++;
		}
		_varToIdxMapper = new int[_searchMapSize];
		for(i=0;i<_searchMapSize;i++){
			_varToIdxMapper[i] = ((Integer) map.get(_searchMap.get(i))).intValue();
		}
		_varNameToResultIdx = map;
	}

	private boolean evaluateTypeConstraint(HDGObject obj, List<HGEEvaluable> constraint){
		HGEEvaluable eval;
		HGEValue     res;
		int           i, size;
		boolean       bRet=false;

		try {
			if (!_evaluator.isEmpty())
				_evaluator.removeAllElements();
			size = constraint.size();
			for (i=0; i<size; i++){
				eval = (HGEEvaluable) constraint.get(i);
				_context.setVarInstanceObject(obj);
				eval.evaluate(_context, _evaluator);
			}
			res = _evaluator.pop();
			res.setInUse(false);
			bRet = res.booleanValue();
		}
		catch (Exception ex){
			bRet = false;
		}
		return bRet;
	}

	private boolean evaluateWhereConstraint(Object[] result, List<HGEEvaluable> constraint){
		HGEEvaluable eval;
		HGEValue     res;
		String        id;
		int           i, size, idx;
		boolean       bRet=false;

		if (constraint==null || constraint.isEmpty())
			return true;

		try {
			if (!_evaluator.isEmpty())
				_evaluator.removeAllElements();
			size = constraint.size();
			for (i=0; i<size; i++){
				eval = (HGEEvaluable) constraint.get(i);
				if (eval instanceof HGEVarAccess){
					id = ((HGEVarAccess) eval).getVarID().getValue();
					idx = ((Integer) _varNameToResultIdx.get(id)).intValue();
					_context.setVarInstanceObject(result[idx]);//(DGObject: node/edge) 
					eval.evaluate(_context, _evaluator);
				}
				else if (eval instanceof HGEVarInstance){
					id = ((HGEVarInstance) eval).getVarID().getValue();
					idx = ((Integer) _varNameToResultIdx.get(id)).intValue();
					_context.setVarInstanceObject(result[idx]);//List of edges (path)
					eval.evaluate(_context, _evaluator);
				}
				else{
					_context.setVarInstanceObject(null);
					eval.evaluate(_context, _evaluator);
				}
			}
			res = _evaluator.pop();
			res.setInUse(false);
			bRet = res.booleanValue();
		}
		catch (Exception ex){
			//_logger.warn("Error: "+ex.toString());
			bRet = false;
		}
		return bRet;
	}

	/*Optimisation possible: cette fonction est appelee par scanGraph() lorsqu'on avance
    d'un pas dans la recherche. Plutot que d'allouer plein de tableaux, il faudrait
    allouer un tableau de n entrees (n=nb. de pas de recherche). Chacune de ces
    entrees est un tableu de taille m (m= nb. de variables de la requetes). */
	private Object[] copyResult(Object[] result) {
		Object[] copy;

		copy = new Object[result.length];
		System.arraycopy(result,0,copy,0,result.length);
		return(copy);
	}

	/*A result of a query execution is a Set of HGEResult. In each HGEResult,
    data is stored as a key/value pairs set. When a key is a vertex/edge variable,
    value is a graph object (DGVertex/DGEdge). When key is a path variable, value
    is a List (of DGEdge). When key is a graph variable, value is a HGEResult
    object itself.*/
	private void saveResult(Set<HGEResult> rSet, Object[] result){
		Hashtable<String, HGEGraphVarDeclaration> graphs;
		HGEGraphVarDeclaration  gVar;
		Iterator<String>        iter;
		String                  key, keyG;
		HGEResultImplem         res, resG;
		int                     idx, idxG;
		Enumeration<String>     enum1;

		res = new HGEResultImplem();
		res.setDistinctReturnVars(_query.getDistinctReturnVars());

		//"1": returned variables was specified with '*' in the query. So,
		//we store in the result all edge/vertex/path variable instances.
		//"2": if returned variables was specified in the query, we only
		//return those values.
		if (_query.getReturnVars().isEmpty())
			iter = _varNameToResultIdx.keySet().iterator();// 1
		else
			iter = _query.getReturnVars().iterator();// 2

		graphs = _query.getGraphs();   
		while(iter.hasNext()){
			key = iter.next();
			//we have to store value for a graph variable
			if (graphs.containsKey(key)){
				gVar = graphs.get(key);
				enum1 = gVar.getVars().keys();
				resG = new HGEResultImplem();
				while(enum1.hasMoreElements()){
					keyG = enum1.nextElement().toString();
					idxG = _varNameToResultIdx.get(keyG).intValue();
					resG.addValue(keyG, result[idxG]);
				}
				res.addValue(key, resG);
			}
			//we just store a variable
			else{
				idx = _varNameToResultIdx.get(key).intValue();
				res.addValue(key, result[idx]);
			}
		}
		rSet.add(res);
	}

	private boolean evaluatePath(HGEPathVarDeclaration pVar, List<HDGLink> path){
		int  limit, size;

		if (path==null)
			return false;

		//check path size constraint if any
		size = path.size();
		limit = pVar.getMinLength();
		if (limit!=HGEValue.UNDEFINED){
			if (size<limit) 
				return false;
		}
		limit = pVar.getMaxLength();
		if (limit!=HGEValue.UNDEFINED){
			if (size>limit) 
				return false;
		}
		return(true);
	}

	/*private String spacer(int size){
    StringBuffer szBuf = new StringBuffer();
    for (int i=0;i<size;i++)
      szBuf.append(" ");
    return szBuf.toString();
  }*/

	private void scanEdge(Set<HGEResult> rSet, Object[] result, int from, int idx, int to, 
			HDGVertex vertex, HDGHyperEdge edge){
		HGENodeVarDeclaration  curVar;
		HDGVertex              oppositeVertex, vertex2;
		Enumeration<HDGVertex> enum3;

		if (idx==to){
			//      if (_verbose)
			//        _logger.info(spacer(from)+"  found edge: "+edge);
			result[_varToIdxMapper[from+1]] = edge;
			scanGraph(rSet, copyResult(result), to);
			return;
		}
		//gets the node variable to check
		curVar = (HGENodeVarDeclaration) 
				_query.getVariable(_searchMap.get(idx).toString());
		//gets the associated node instance (may be null)
		vertex2 = (HDGVertex) result[_varToIdxMapper[idx]];
		//for that node variable, scan all edge's nodes, and do not
		//take into account link orientation
		enum3 = _dGraph.oppositeVertices(edge, vertex, false);
		while(enum3.hasMoreElements()){
			oppositeVertex = (HDGVertex) enum3.nextElement();
			if (vertex2!=null){
				if (vertex2==oppositeVertex){
					scanEdge(rSet, copyResult(result), from, idx+1, to, vertex, edge);
				}
			}
			else if(evaluateTypeConstraint(oppositeVertex, curVar.getType())){
				result[_varToIdxMapper[idx]] = oppositeVertex;
				//        if (_verbose)
				//          _logger.info(spacer(from)+"  found node: "+oppositeVertex);
				scanEdge(rSet, copyResult(result), from, idx+1, to, vertex, edge);
			}
		}
	}

	private void scanGraph(Set<HGEResult> rSet, Object[] result, int from){
		HGENodeVarDeclaration nVar, curVar;
		HGEEdgeVarDeclaration eVar;
		HGEPathVarDeclaration pVar;
		HGEVarDeclaration     var;
		HDGVertex              vertex;
		HDGVertex              vertex2;
		HDGHyperEdge           edge;
		Enumeration<HDGHyperEdge> enum1;
		Enumeration<HDGVertex>    enum2;
		List<HDGLink>          path;
		int                    nNodeVars, i, idx;

		if(from>=_searchMapSize){
			//      if (_verbose)
			//        _logger.info(spacer(from)+"check query constraints");
			//entering here: we have a result! :-)
			if (evaluateWhereConstraint(result, _query.getConstraints())){
				saveResult(rSet, result);
				//        _nResult++;
				//        if (_verbose)
				//          _logger.info(spacer(from)+"ok: save result ("+_nResult+")");
			}
			return;
		}
		else {
			//entering here: needs to find an instance (vertex, edge, path) for 'var'
			var = _query.getVariable(_searchMap.get(from+1).toString());
			if (var instanceof HGEEdgeVarDeclaration){
				//we are looking for an edge. So, starting from the previously
				//found 'vertex', scan all its edges and locate one that validates
				//'eVar'
				eVar = (HGEEdgeVarDeclaration) var;
				//nb. of nodes (connected to eVar) to find
				nNodeVars = eVar.getNodeVars().size()-1;
				//vertex variable from which we start the search
				nVar = (HGENodeVarDeclaration) 
						_query.getVariable(_searchMap.get(from).toString());
				//vertex instance associated to nVar
				vertex = (HDGVertex) result[_varToIdxMapper[from]];
				//start - print help
				if (_verbose){
					StringBuffer szBuf = new StringBuffer();
					for (i=0;i<nNodeVars;i++){
						idx = from+2+i;
						curVar = (HGENodeVarDeclaration) 
								_query.getVariable(_searchMap.get(idx).toString());
						szBuf.append(curVar.getName());
						szBuf.append(" (");
						szBuf.append(result[_varToIdxMapper[idx]]);
						szBuf.append(")");
						if ((i+1)<nNodeVars)
							szBuf.append(" - ");
					}
					/*_logger.info(spacer(from)+
              "search: "+eVar.getName()+" ("+result[_varToIdxMapper[from+1]]+")"+
              " from: "+nVar.getName()+" ("+vertex+")"+
              " to: "+szBuf.toString());*/
				}
				//stop - print help
				enum1 = _dGraph.edges(vertex);
				while(enum1.hasMoreElements()){
					edge = (HDGHyperEdge) enum1.nextElement();
					//          if (_verbose)
					//            _logger.info(spacer(from)+" check: "+edge);
					if (evaluateTypeConstraint(edge, eVar.getType())){
						//start the scan of all edge's connected vertices
						scanEdge(rSet, copyResult(result), from, from+2, 
								from+2+nNodeVars, vertex, edge);
					}
				}
			}
			else if (var instanceof HGEPathVarDeclaration) {
				//we are looking for a path, given the already found 'vertex'.
				pVar = (HGEPathVarDeclaration) var;
				vertex = (HDGVertex) result[_varToIdxMapper[from]];
				nVar = (HGENodeVarDeclaration) 
						_query.getVariable(_searchMap.get(from+2).toString());
				vertex2 = (HDGVertex) result[_varToIdxMapper[from+2]];
				if (vertex2==null){
					//second vertex not yet found. Get a list of all vertices from the
					//graph, check each of them and for each valid vertex, get a path
					//between it and 'vertex'.
					enum2 = _dGraph.vertices();
					while(enum2.hasMoreElements()){
						vertex2 = enum2.nextElement();
						if (evaluateTypeConstraint(vertex2, nVar.getType())){
							path = _dGraph.findPath(vertex, vertex2);
							if (evaluatePath(pVar, path)){
								result[_varToIdxMapper[from+1]] = path;
								result[_varToIdxMapper[from+2]]=vertex2;
								//if (_verbose)
								//  _logger.info(spacer(from)+"  found path for "+pVar.getName());
								scanGraph(rSet, copyResult(result), from+3);
							}
						}
					}
				}
				else{
					//here: 'vertex' and 'vertex2' are known, so just need to get 
					//paths
					path = _dGraph.findPath(vertex, vertex2);
					if (evaluatePath(pVar, path)){
						result[_varToIdxMapper[from+1]] = path;
						//if (_verbose)
						//  _logger.info(spacer(from)+"  found path for "+pVar.getName());
						scanGraph(rSet, copyResult(result), from+3);
					}
				}
			}
		}
	}

	/*private void cleanResult(Object[] result){
        for(int i=0;i<_query.getAllVars().size();i++)
            result[i]=null;
    }*/

	/**
	 * Start the execution.
	 * 
	 * @return false if a error occurred.
	 * */
	public boolean execute(){

		Object[]               result;
		HDGVertex              vertex;
		Enumeration<HDGVertex> enum1;
		HGENodeVarDeclaration  nVar;
		//    int                    nVertices=0;
		HashSet<HGEResult>     rSet;
		long                   time;
		boolean                bRet = true;

		time = System.currentTimeMillis();
		rSet = new HashSet<HGEResult>();

		try {
			result = new Object[_query.getAllVars().size()];
			enum1 = _dGraph.vertices();
			nVar = (HGENodeVarDeclaration) 
					_query.getVariable(_searchMap.get(0).toString());
			//      _nResult=0;
			while(enum1.hasMoreElements()){
				vertex = enum1.nextElement();
				if (evaluateTypeConstraint(vertex, nVar.getType())){
					result[_varToIdxMapper[0]] = vertex;
					//if (_verbose)
					//  _logger.info("Start scan with: "+nVar.getName()+" ("+vertex.toString()+")");
					scanGraph(rSet, copyResult(result), 0);
				}
				//        nVertices++;
			}
		}
		catch (Exception ex){
			//since logger has been removed, should we throw a RuntimeException ?
			//_logger.warn("Query engine failed: "+ex.toString());
			bRet=false;
		}
		_runningTime = System.currentTimeMillis() - time;
		_result = rSet;
		return bRet;
	}

}
