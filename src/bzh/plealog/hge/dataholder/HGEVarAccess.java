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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bzh.plealog.hge.api.datamodel.DGMAttribute;
import bzh.plealog.hge.api.hypergraph.HDGObject;
import bzh.plealog.hge.engine.HGEEvaluable;
import bzh.plealog.hge.engine.HGEExecutionContext;
import bzh.plealog.hge.engine.HGEInterpretable;
import bzh.plealog.hge.engine.HGEReusableArgumentValue;
import bzh.plealog.hge.engine.HGEStack;
import bzh.plealog.hge.engine.HGEValue;


/**
 * Define a variable accessor.
 * 
 * @author Patrick G. Durand
 * */
public class HGEVarAccess extends HGEValue
{
  private HGEIdentifier _varID;
  private ArrayList<?>      _path;
  private String         _strValue;

  private HGEVarAccess()
  {
    setDataType(-1);
    setContainerType(-1);
  }

  /**
   * Constructor.
   * 
   * @param varID id of the variable
   * @param path path to access the data item
   * */
  public HGEVarAccess(HGEIdentifier varID, ArrayList<?> path)
  {
    this();
    setVarID(varID);
    setPath(path);
  }

  /**
   * Get the variable id.
   * @return the variable id
   * */
  public HGEIdentifier getVarID()
  {
    return _varID;
  }

  /**
   * Set the variable id.
   * @param varID the variable id
   * */
  public void setVarID(HGEIdentifier varID){
    if (varID==null)
      throw new HGEDataHolderException("variable accessor is wrongly defined.");
    _varID = varID;
  }

  /**
   * Return the path to access the data item.
   * 
   * @return path to access the data item
   * */
  public ArrayList<?> getPath()
  {
    return _path;
  }

  /**
   * Set the path to access the data item.
   * 
   * @param path path to access the data item
   * */
  public void setPath(ArrayList<?> path)
  {
    StringBuffer szBuf;
    int          i, size;

    if (path==null)
      throw new HGEDataHolderException("variable accessor path is wrongly defined.");

    szBuf = new StringBuffer();
    szBuf.append(_varID.getValue());
    if (!path.isEmpty()){
      szBuf.append(".");
      size = path.size();
      for (i=0; i<size;i++){
        szBuf.append(path.get(i).toString());
        if ((i+1)<size)
          szBuf.append(".");
      }
    }

    _strValue = szBuf.toString();
    _path = path;
  }

  public String toString()
  {
    return _strValue;
  }

  /**
   * During interpretation, this HGEVarAccess is responsible of stacking the returned value
   * of the attribute of a DGObject.
   * 
   * @see HGEInterpretable#interpret(HGEExecutionContext, HGEStack)
   * */
  public void interpret(HGEExecutionContext context, HGEStack stack){
    if (_path.isEmpty()){
      stack.push(HGEString.CONST);
      return;
    }
    switch (getContainerType()){
      case DGMAttribute.CT_ATOMIC:
        switch (getDataType()){
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
        switch (getDataType()){
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
        switch (getDataType()){
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
  }

  private void pushValueOnStack(HGEExecutionContext context, Object value, HGEStack stack){
    HGEReusableArgumentValue result;

    if (value==null){
      stack.push(HGEString.UNKNOWN);
      return;
    }
    result = context.getReusableArg();
    switch (getContainerType()){
      case DGMAttribute.CT_ATOMIC:
        switch (getDataType()){
          case DGMAttribute.DT_STRING:
            result.setStringValue((String) value);
            stack.push(result);
            break;
          case DGMAttribute.DT_CHARACTER:
            result.setCharValue(((Character) value).charValue());
            stack.push(result);
            break;
          case DGMAttribute.DT_DOUBLE:
            result.setDoubleValue(((Double) value).doubleValue());
            stack.push(result);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            result.setLongValue(((Long) value).longValue());
            stack.push(result);
            break;
          case DGMAttribute.DT_BOOLEAN:
            result.setBooleanValue(((Boolean) value).booleanValue());
            stack.push(result);
            break;
          default:
            throw new HGEDataHolderException("unknown value type in variable accessor.");
        }
        break;
      case DGMAttribute.CT_LIST:
        switch (getDataType()){
          case DGMAttribute.DT_STRING:
            result.setListValue((List<?>) value);
            result.setDataType(DGMAttribute.DT_STRING);
            stack.push(result);
            break;
          case DGMAttribute.DT_CHARACTER:
            result.setListValue((List<?>) value);
            result.setDataType(DGMAttribute.DT_CHARACTER);
            stack.push(result);
            break;
          case DGMAttribute.DT_DOUBLE:
            result.setListValue((List<?>) value);
            result.setDataType(DGMAttribute.DT_DOUBLE);
            stack.push(result);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            result.setListValue((List<?>) value);
            result.setDataType(DGMAttribute.DT_LONG);
            stack.push(result);
            break;
          case DGMAttribute.DT_BOOLEAN:
            result.setListValue((List<?>) value);
            result.setDataType(DGMAttribute.DT_BOOLEAN);
            stack.push(result);
            break;
          default:
            throw new HGEDataHolderException("unknown value type in variable accessor.");
        }
        break;
      case DGMAttribute.CT_SET:
        switch (getDataType()){
          case DGMAttribute.DT_STRING:
            result.setSetValue((Set<?>) value);
            result.setDataType(DGMAttribute.DT_STRING);
            stack.push(result);
            break;
          case DGMAttribute.DT_CHARACTER:
            result.setSetValue((Set<?>) value);
            result.setDataType(DGMAttribute.DT_CHARACTER);
            stack.push(result);
            break;
          case DGMAttribute.DT_DOUBLE:
            result.setSetValue((Set<?>) value);
            result.setDataType(DGMAttribute.DT_DOUBLE);
            stack.push(result);
            break;
          case DGMAttribute.DT_DATE:
          case DGMAttribute.DT_LONG:
            result.setSetValue((Set<?>) value);
            result.setDataType(DGMAttribute.DT_LONG);
            stack.push(result);
            break;
          case DGMAttribute.DT_BOOLEAN:
            result.setSetValue((Set<?>) value);
            result.setDataType(DGMAttribute.DT_BOOLEAN);
            stack.push(result);
            break;
          default:
            throw new HGEDataHolderException("unknown value type in variable accessor.");
        }
        break;
    }
  }

  /**
   * During evaluation, this HGEVarAccess is responsible of stacking the returned value
   * of the attribute of a DGObject.
   * 
   * @see HGEEvaluable#evaluate(HGEExecutionContext, HGEStack)*/
  public void evaluate(HGEExecutionContext context, HGEStack stack){
    pushValueOnStack(
        context, 
        ((HDGObject)context.getVarInstanceObject()).getValue(_path.get(0).toString()), 
        stack);
  }
}
