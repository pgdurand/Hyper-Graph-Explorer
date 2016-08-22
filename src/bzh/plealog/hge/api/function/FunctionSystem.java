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

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.function.LongInSetFunc;
import bzh.plealog.hge.function.LongNotInSetFunc;
import bzh.plealog.hge.function.StringInSetFunc;
import bzh.plealog.hge.function.StringNotInSetFunc;

/**
 * This class centralized the set of functions available in the system.
 * 
 * @author Patrick G. Durand
 */
public final class FunctionSystem{

  private static final String FUNC_INTERFACE=Function.class.getName();

  /**key is a function name, value is a Class (implementing Function)*/
  private static Hashtable<String, Class<Function>> _funcTable;

  static {
    _funcTable = new Hashtable<String, Class<Function>>();
    FunctionSystem.addFunction(new LongInSetFunc());
    FunctionSystem.addFunction(new LongNotInSetFunc());
    FunctionSystem.addFunction(new StringInSetFunc());
    FunctionSystem.addFunction(new StringNotInSetFunc());
  }

  /**
   * Return a function by name.
   * 
   * @param funcName a function name
   * @return the corresponding function or null if not found
   * */
  public static Function getFunction(String funcName){
    Function func = null;

    try{
      if (exists(funcName)){
        func = ((Class<Function>) _funcTable.get(funcName)).newInstance();
      }
    }
    catch(Exception ex) {
    }
    return (func);
  }

  /**
   * Figure out whether or not a function exists.
   * 
   * @param funcName function name
   * 
   * @return true or false
   * */
  public static boolean exists(String funcName){
    return (_funcTable.get(funcName)!=null);
  }

  /**
   * Add a new function. Do not add function if it already exists in the system.
   * 
   * @param func the function to add
   * @return false if the function cannot be added to the system
   * @throws FunctionException if an error occurs (className undefined, interface Function not implemented) 
   * */
  public static boolean addFunction (Function func) throws FunctionException {
    return (addFunction(func.getClass().getName(), false));
  }

  /**
   * Add a new function.
   * 
   * @param func the function to add
   * @param force use true to force the system to add function even if its already exists in the system
   * @return false if the function cannot be added to the system
   * @throws FunctionException if an error occurs (className undefined, interface Function not implemented) 
   * */
  public static boolean addFunction (Function func, boolean force) throws FunctionException {
    return (addFunction(func.getClass().getName(), force));
  }

  /**
   * Add a new function by Class name. Do not add function if it already exists in the system.
   * 
   * @param className the function to add
   * @return false if the function cannot be added to the system
   * @throws FunctionException if an error occurs (className undefined, interface Function not implemented) 
   * */
  public static boolean addFunction(String className) throws FunctionException {
    return (addFunction(className, false));
  }

  /**
   * Add a new function by Class name.
   * 
   * @param className the function to add
   * @param force use true to force the system to add function even if its already exists in the system
   * @return false if the function cannot be added to the system
   * @throws FunctionException if an error occurs (className undefined, interface Function not implemented) 
   * */
  @SuppressWarnings("unchecked")
  public static boolean addFunction(String className, boolean force) throws FunctionException {
    Class<Function> cFunc;
    Function        func;
    Object          obj;
    String          fName;
    boolean         ret=false;

    try{
      cFunc = (Class<Function>) Class.forName(className);
      obj = cFunc.newInstance();    
      if (!(obj instanceof Function))
        throw new Exception(" interface not implemented: "+FUNC_INTERFACE);
      func = (Function) obj;
      isAValidFunction(func);
      fName = func.getName();
      if (force==false){
        if (!exists(fName)){
          _funcTable.put(fName, cFunc);
          ret=true;
        }
      }
      else{
        _funcTable.put(fName, cFunc);
        ret=true;
      }
    }
    catch(Exception ex){
      throw new FunctionException(className+" is an invalid Function: "+ex.getMessage());
    }

    return ret;
  }

  /**
   * Checks the validity of a function.
   * 
   * @param func the function to check
   * 
   * @throws FunctionException if function is wrongly defined
   * */
  private static void isAValidFunction(Function func) throws Exception{
    int          i, size, args, val;
    int[]        data, container;

    args = func.arguments();
    if (args!=0){
      data = func.getArgDataTypes();
      container = func.getArgContainerTypes();
      if (data==null || data.length!=args){
        throw new Exception("wrong number of argument data types");
      }
      if (container==null || container.length!=args){
        throw new Exception("wrong number of argument container types");
      }
      size = DGMAttribute.DT_REPR.length;
      for (i=0;i<args;i++){
        if(data[i]<0 || data[i]>=size){
          throw new Exception("data type of argument "+(i+1)+" is not one of: "+ Arrays.asList(DGMAttribute.DT_REPR));
        }
      }
      size = DGMAttribute.CT_REPR.length;
      for (i=0;i<args;i++){
        if(container[i]<0 || container[i]>=size){
          throw new Exception("container type of argument "+(i+1)+" is not one of: "+Arrays.asList(DGMAttribute.CT_REPR));
        }
      }
    }
    val = func.getReturnDataType();
    size = DGMAttribute.DT_REPR.length;
    if(val<0 || val>=size){
      throw new Exception("returned data type is not one of: "+Arrays.asList(DGMAttribute.DT_REPR));
    }
    val = func.getReturnContainerType();
    size = DGMAttribute.CT_REPR.length;
    if(val<0 || val>=size){
      throw new Exception("returned container type is not one of: "+Arrays.asList(DGMAttribute.CT_REPR));
    }
  }

  /**
   * Return a string representation of the set of functions available in the system.
   * 
   * @return string representation of the set of functions available in the system
   * */
  public static String getStringRepr(){
    StringBuffer     szBuf;
    Iterator<String> iter;
    Function         func;

    szBuf = new StringBuffer("Function list: ");
    iter = _funcTable.keySet().iterator();
    if (iter.hasNext()){
      szBuf.append("\n");
      while(iter.hasNext()){
        func = getFunction(iter.next().toString());
        szBuf.append("  "+func.toString());
        if (iter.hasNext()){
          szBuf.append("\n");
        }
      }
    }
    else{
      szBuf.append(" empty.");
    }
    return (szBuf.toString());
  }
}
