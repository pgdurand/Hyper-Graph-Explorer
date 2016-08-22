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
package bzh.plealog.hge.parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

import bzh.plealog.hge.dataholder.*;

public class HGEParserTokenManager implements HGEParserConstants
{
  public  java.io.PrintStream debugStream = System.out;
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x2000000L) != 0L)
            return 1;
         if ((active0 & 0xfff0380000L) != 0L)
         {
            jjmatchedKind = 46;
            return 41;
         }
         return -1;
      case 1:
         if ((active0 & 0x77f0180000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 1;
            return 41;
         }
         if ((active0 & 0x8800200000L) != 0L)
            return 41;
         return -1;
      case 2:
         if ((active0 & 0x180000L) != 0L)
            return 41;
         if ((active0 & 0x77f0000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 2;
            return 41;
         }
         return -1;
      case 3:
         if ((active0 & 0x6660000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 3;
            return 41;
         }
         if ((active0 & 0x1190000000L) != 0L)
            return 41;
         return -1;
      case 4:
         if ((active0 & 0x4240000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 4;
            return 41;
         }
         if ((active0 & 0x2420000000L) != 0L)
            return 41;
         return -1;
      case 5:
         if ((active0 & 0x40000000L) != 0L)
            return 41;
         if ((active0 & 0x4200000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 5;
            return 41;
         }
         return -1;
      case 6:
         if ((active0 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 6;
            return 41;
         }
         if ((active0 & 0x200000000L) != 0L)
            return 41;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private final int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private final int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private final int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x50000L);
      case 37:
         return jjStopAtPos(0, 10);
      case 38:
         return jjStopAtPos(0, 40);
      case 40:
         return jjStopAtPos(0, 22);
      case 41:
         return jjStopAtPos(0, 23);
      case 42:
         return jjStopAtPos(0, 8);
      case 43:
         return jjStopAtPos(0, 6);
      case 44:
         return jjStopAtPos(0, 27);
      case 45:
         return jjStopAtPos(0, 7);
      case 46:
         return jjStartNfaWithStates_0(0, 25, 1);
      case 47:
         return jjStopAtPos(0, 9);
      case 58:
         jjmatchedKind = 26;
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 59:
         return jjStopAtPos(0, 24);
      case 60:
         jjmatchedKind = 11;
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 61:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 62:
         jjmatchedKind = 13;
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 91:
         return jjStopAtPos(0, 50);
      case 93:
         return jjStopAtPos(0, 51);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x8000080000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x4000000000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x2010000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x100100000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      case 123:
         return jjStopAtPos(0, 52);
      case 125:
         return jjStopAtPos(0, 53);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private final int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         else if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         break;
      case 61:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(1, 12);
         else if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(1, 14);
         else if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(1, 15);
         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(1, 16);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x2080000000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x140000000L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000000L);
      case 110:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(1, 35, 41);
         return jjMoveStringLiteralDfa2_0(active0, 0x400080000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x200100000L);
      case 114:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(1, 21, 41);
         return jjMoveStringLiteralDfa2_0(active0, 0x1010000000L);
      case 115:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 39, 41);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private final int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(2, 19, 41);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000000L);
      case 116:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(2, 20, 41);
         return jjMoveStringLiteralDfa3_0(active0, 0x4c0000000L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private final int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 41);
         break;
      case 104:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(3, 31, 41);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      case 109:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(3, 28, 41);
         break;
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(3, 32, 41);
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private final int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(4, 29, 41);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 37, 41);
         return jjMoveStringLiteralDfa5_0(active0, 0x200000000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000L);
      case 108:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(4, 34, 41);
         break;
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private final int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000L);
      case 110:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(5, 30, 41);
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private final int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000000L);
      case 116:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(6, 33, 41);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private final int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 116:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 38, 41);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private final void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private final void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private final void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}
private final void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}
private final void jjCheckNAddStates(int start)
{
   jjCheckNAdd(jjnextStates[start]);
   jjCheckNAdd(jjnextStates[start + 1]);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private final int jjMoveNfa_0(int startState, int curPos)
{
   int[] nextStates;
   int startsAt = 0;
   jjnewStateCnt = 41;
   int i = 1;
   jjstateSet[0] = startState;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 41:
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(24);
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAddStates(0, 6);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(7, 9);
                  else if (curChar == 39)
                     jjAddStates(10, 11);
                  else if (curChar == 46)
                     jjCheckNAdd(1);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAddTwoStates(1, 2);
                  break;
               case 3:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(4);
                  break;
               case 5:
                  if (curChar == 39)
                     jjAddStates(10, 11);
                  break;
               case 6:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if (curChar == 39 && kind > 44)
                     kind = 44;
                  break;
               case 9:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 10:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(11, 7);
                  break;
               case 11:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 12:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 13:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(11);
                  break;
               case 14:
                  if (curChar == 34)
                     jjCheckNAddStates(7, 9);
                  break;
               case 15:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 17:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 18:
                  if (curChar == 34 && kind > 45)
                     kind = 45;
                  break;
               case 19:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(12, 15);
                  break;
               case 20:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 21:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 22:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(20);
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAddStates(0, 6);
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(26);
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 28:
                  if (curChar == 46)
                     jjCheckNAdd(29);
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAddTwoStates(29, 30);
                  break;
               case 31:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(32);
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(33, 34);
                  break;
               case 35:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(36);
                  break;
               case 36:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(36);
                  break;
               case 37:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAddTwoStates(37, 38);
                  break;
               case 39:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(40);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(40);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 41:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     jjCheckNAdd(24);
                  }
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     jjCheckNAddTwoStates(23, 24);
                  }
                  break;
               case 0:
               case 23:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 2:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(16, 17);
                  break;
               case 6:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 8:
                  if (curChar == 92)
                     jjAddStates(18, 20);
                  break;
               case 9:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 15:
                  jjCheckNAddStates(7, 9);
                  break;
               case 16:
                  if (curChar == 92)
                     jjAddStates(21, 23);
                  break;
               case 17:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 24:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(24);
                  break;
               case 30:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(24, 25);
                  break;
               case 34:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(26, 27);
                  break;
               case 38:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(28, 29);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 15:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(7, 9);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 41 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   26, 27, 28, 33, 34, 37, 38, 15, 16, 18, 6, 8, 15, 16, 20, 18, 
   3, 4, 9, 10, 12, 17, 19, 21, 31, 32, 35, 36, 39, 40, 
};
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, "\53", "\55", "\52", "\57", "\45", "\74", 
"\74\75", "\76", "\76\75", "\75\75", "\41\75", "\72\72", "\41\72", "\141\156\144", 
"\156\157\164", "\157\162", "\50", "\51", "\73", "\56", "\72", "\54", "\146\162\157\155", 
"\167\150\145\162\145", "\162\145\164\165\162\156", "\160\141\164\150", "\156\145\170\164", 
"\143\157\156\156\145\143\164", "\165\156\164\151\154", "\151\156", "\164\162\165\145", 
"\146\141\154\163\145", "\144\151\163\164\151\156\143\164", "\141\163", "\46", null, null, null, null, 
null, null, null, null, "\41", "\133", "\135", "\173", "\175", };
public static final String[] lexStateNames = {
   "DEFAULT", 
};
static final long[] jjtoToken = {
   0x3e77ffffffffc1L, 
};
static final long[] jjtoSkip = {
   0x3eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[41];
private final int[] jjstateSet = new int[82];
protected char curChar;
public HGEParserTokenManager(SimpleCharStream stream)
{
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}
public HGEParserTokenManager(SimpleCharStream stream, int lexState)
{
   this(stream);
   SwitchTo(lexState);
}
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private final void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 41; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   Token t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   String im = jjstrLiteralImages[jjmatchedKind];
   t.image = (im == null) ? input_stream.GetImage() : im;
   t.beginLine = input_stream.getBeginLine();
   t.beginColumn = input_stream.getBeginColumn();
   t.endLine = input_stream.getEndLine();
   t.endColumn = input_stream.getEndColumn();
   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

public Token getNextToken() 
{
  int kind;
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {   
   try   
   {     
      curChar = input_stream.BeginToken();
   }     
   catch(java.io.IOException e)
   {        
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

}
