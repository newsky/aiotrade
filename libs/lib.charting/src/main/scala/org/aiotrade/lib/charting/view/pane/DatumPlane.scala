/*
 * Copyright (c) 2006-2007, AIOTrade Computing Co. and Contributors
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *    
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *    
 *  o Neither the name of AIOTrade Computing Co. nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.aiotrade.lib.charting.view.pane

import org.aiotrade.lib.charting.view.ChartView
import org.aiotrade.lib.charting.view.scalar.Scalar
import org.aiotrade.lib.math.timeseries.MasterTSer


/**
 * This is the only Type aware of x-y coordinate and knows how to compute it. 
 * At this time, only ChartPane implements it, other panes should have a referrence
 * DatumPlane as its member, and get the coordinate information from it.
 *
 * @author Caoyuan Deng
 */
trait DatumPlane {
    
  def getWidth: Int
  def getHeight: Int
    
  def isMouseEntered: Boolean
  def yMouse: Int
  def referCursorValue: Float
  def isAutoReferCursorValue: Boolean
    
  def view: ChartView
    
  def masterSer: MasterTSer
    
  /**
   * in DatumPlane, we define this public interface for its users to call in case
   * of being painted earlier than DatumPlane, for example: AxisXPane and AxisYPane.
   * @see Pane#prePaintComponent()
   */
  def computeGeometry: Unit
  def isGeometryValid: Boolean
    
  def nBars: Int
    
  def wBar: Float
    
  def hOne: Float
    
  def maxValue: Float
    
  def minValue: Float
    
  def xb(barIndex: Int): Float
  def bx(x: Float): Int
    
  def xr(row: Int): Float
  def rx(x: Float): Int
    
  def yv(value: Float): Float
  def vy(y: Float): Float
    
  def rb(barIndex: Int): Int
  def br(row: Int): Int
    
  def tb(barIdx :Int): Long
  def bt(time: Long): Int
    
  def tx(x: Float): Long
        
  def hCanvas: Int
    
  def yCanvasLower: Int
    
  def yCanvasUpper: Int
    
  /**
   * @return chart height in pixels, corresponds to the value range (maxValue - minValue)
   */
  def hChart: Int
    
  def yChartLower: Int
    
  def yChartUpper: Int
    
  def valueScalar: Scalar
  def valueScalar_=(valueScalar: Scalar): Unit
    
  def yChartScale: Float
  def yChartScale_=(yChartScale: Float): Unit
    
  def growYChartScale(increment: Float): Unit
    
  def yChartScaleByCanvasValueRange_=(canvasValueRange: Double): Unit
    
  def scrollChartsVerticallyByPixel(increment: Int): Unit
    
}





