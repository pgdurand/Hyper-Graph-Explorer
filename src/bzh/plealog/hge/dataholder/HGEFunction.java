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
import bzh.plealog.hge.api.function.Function;
import bzh.plealog.hge.api.function.FunctionSystem;
import bzh.plealog.hge.engine.HGEArgumentValue;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;

/**
 * Define a function.
 * 
 * @author Patrick G. Durand
 * */
public class HGEFunction extends HGEValue
{

  private String              _fName;
  private int                 _args;
  private HGEArgumentValue[] _executableArgs;
  private Function            _func;

  private HGEFunction()
  {
    setDataType(-1);
    setContainerType(-1);
  }

  /**
   * Constructor.
   * 
   * @param s the function name
   * */
  public HGEFunction(String s)
  {
    this();
    setName(s);
  }

  /**
   * Constructor.
   * 
   * @param val another function
   * */
  public HGEFunction(HGEFunction val)
  {
    this();
    setName(val.getName());
  }

  /**
   * Get the name of the function.
   * 
   * @return the name of the function.
   * */
  public String getName()
  {
    return _fName;
  }

  /**
   * Set the name of the function.
   * 
   * @param s the name of the function.
   * */
  public void setName(String s)
  {
    _fName = s;
  }

  /**
   * Set the name of the function.
   * 
   * @param val another function.
   * */
  public void setName(HGEFunction val)
  {
    setName(val.getName());
  }

  /**
   * @see Object#toString()*/
  public String toString()
  {
    return getName();
  }

  /**
   * Set the number of arguments to this function.
   * 
   * @param args the number of arguments
   * */
  public void setArgs(int args){
    _args = args;
  }

  /**
   * Get the number of arguments of this function.
   * 
   * @return the number of arguments
   * */
  public int getArgs(){
    return(_args);
  }

  /**
   * @see Object#equals(Object)*/
  public boolean equals (Object obj){
    if (obj!=null  && (obj instanceof HGEFunction)){
      return (((HGEFunction)obj).getName().equalsIgnoreCase(this.getName()));
    }
    return false;
  }

  /**
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   **/
  public void interpret(HGEExecutionContext context, HGEStack stack){
    Function  func;
    HGEValue stackArg;
    int[]     funcArgDTypes;
    int[]     funcArgCTypes;
    int       i, n, max, type;


    //does not control function name existence and arguments number during execution
    //since this is done by the query syntactic/semantic validator
    func = FunctionSystem.getFunction(getName());
    n = func.arguments();
    max=n-1;
    funcArgDTypes = func.getArgDataTypes();
    funcArgCTypes = func.getArgContainerTypes();
    for(i=0;i<n;i++,max--){
      stackArg = (HGEValue) stack.pop();
      type=stackArg.getDataType();
      if (type!=funcArgDTypes[max]){
        throw new HGEDataHolderException("In function "+
            getName()+", data type of argument "+(max+1)+" is invalid. Expected: "+
            DGMAttribute.DT_REPR[funcArgDTypes[max]]+
            ", found: "+DGMAttribute.DT_REPR[type]+".");
      }
      type=stackArg.getContainerType();
      if (type!=funcArgCTypes[max]){
        throw new HGEDataHolderException("In function "+
            getName()+", container type of argument "+(max+1)+" is invalid. Expected: "+
            DGMAttribute.CT_REPR[funcArgDTypes[max]]+
            ", found: "+DGMAttribute.CT_REPR[type]+".");
      }
    }
    switch (func.getReturnContainerType()){
      case DGMAttribute.CT_ATOMIC:
        switch (func.getReturnDataType()){
          case DGMAttribute.DT_STRING:
            stack.push(HGEString.CONST);
            break;
          case DGMAttribute.DT_CHARACTER:
            stack.push(HGECharacter.CONST);
            break;
          case DGMAttribute.DT_DOUBLE:
            stack.push(HGEDouble.CONST);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            stack.push(HGELong.CONST);
            break;
          case DGMAttribute.DT_BOOLEAN:
            stack.push(HGEBoolean.TRUE);
            break;
          default:
            throw new HGEDataHolderException("unknown data type in variable accessor.");
        }
        break;
      case DGMAttribute.CT_LIST:
        switch (func.getReturnDataType()){
          case DGMAttribute.DT_STRING:
            stack.push(HGEList.CONST_STRING);
            break;
          case DGMAttribute.DT_CHARACTER:
            stack.push(HGEList.CONST_CHAR);
            break;
          case DGMAttribute.DT_DOUBLE:
            stack.push(HGEList.CONST_DOUBLE);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            stack.push(HGEList.CONST_INT);
            break;
          case DGMAttribute.DT_BOOLEAN:
            stack.push(HGEList.CONST_BOOL);
            break;
          default:
            throw new HGEDataHolderException("unknown data type in variable accessor.");
        }
        break;
      case DGMAttribute.CT_SET:
        switch (func.getReturnDataType()){
          case DGMAttribute.DT_STRING:
            stack.push(HGESet.CONST_STRING);
            break;
          case DGMAttribute.DT_CHARACTER:
            stack.push(HGESet.CONST_CHAR);
            break;
          case DGMAttribute.DT_DOUBLE:
            stack.push(HGESet.CONST_DOUBLE);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            stack.push(HGESet.CONST_INT);
            break;
          case DGMAttribute.DT_BOOLEAN:
            stack.push(HGESet.CONST_BOOL);
            break;
          default:
            throw new HGEDataHolderException("unknown data type in variable accessor.");
        }
        break;
      default:
        throw new HGEDataHolderException("unknown container type in variable accessor.");
    }
    //allocate a reusable array of args (to be used by evaluate())
    _executableArgs =  new HGEArgumentValue[n];
    _func = func;
  }

  /**
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)
   * */
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    int j,size;

    size=_executableArgs.length;
    for (j=size-1;j>=0;j--){
      _executableArgs[j]= (HGEArgumentValue) stack.pop();
    }
    stack.push(_func.compute(_executableArgs, context));
    for (j=0;j<size;j++){
      _executableArgs[j].setInUse(false);
      _executableArgs[j]=null;
    }
  }

}
