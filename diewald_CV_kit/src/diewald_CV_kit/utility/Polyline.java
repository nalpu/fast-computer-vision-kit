
/**
 * 
 * diewald_CV_kit v1.1
 * 
 * this library provides simple tools needed in computer-vision.
 * 
 * 
 * 
 *   (C) 2012    Thomas Diewald
 *               http://www.thomasdiewald.com
 *   
 *   last built: 12/13/2012
 *   
 *   download:   http://thomasdiewald.com/processing/libraries/diewald_CV_kit/
 *   source:     https://github.com/diwi/diewald_CV_kit 
 *   
 *   tested OS:  osx,windows
 *   processing: 1.5.1, 2.07
 *
 *
 *
 *
 * This source is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License is available on the World
 * Wide Web at <http://www.gnu.org/copyleft/gpl.html>. You can also
 * obtain it by writing to the Free Software Foundation,
 * Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */




package diewald_CV_kit.utility;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import diewald_CV_kit.blobdetection.Contour;
import diewald_CV_kit.blobdetection.Pixel;
/**
*
* the class Polyline provides some convenient static methods to edit/analyse/etc. polygons.<br>
* <br>
* 
* 
* @author thomas diewald (c) 2011
*
*/
public abstract class Polyline {
  private Polyline(){ 
  }

 
  
  //----------------------------------------------------------------------------
  // AREA
  //----------------------------------------------------------------------------
  /**
   * returns the surface area of the polyline
   * 
   * @param polyline the polyline to get the length from
   * @return returns the surface area of the polyline
   */
  public static final float AREA( Pixel[] polyline ){
    Pixel p0, p1;
    float area = 0;
    for(int idx = 0; idx < polyline.length-1; idx++){
      p0 = polyline[idx];
      p1 = polyline[idx+1];
      area += (p0.x_ + p1.x_)*(p1.y_ - p0.y_);
    }
    area *= (area < 0) ? -.5f : +.5f;
    return area;
  }
  
  /**
   * returns the surface area of the polyline
   * 
   * @param polyline the polyline to get the length from
   * @return returns the surface area of the polyline
   */
  public static final float AREA( List<Pixel> polyline ){
    List<Pixel> list = polyline;
    Pixel p0, p1;
    float area = 0;
    for(int idx = 0; idx < list.size()-1; idx++){
      p0 = list.get(idx);
      p1 = list.get(idx+1);
      area += (p0.x_ + p1.x_)*(p1.y_ - p0.y_);
    }
    area *= (area < 0) ? -.5f : +.5f;
    return area;
  }
  
  /**
   * returns the surface area of the contour
   * 
   * @param contour the polyline to get the length from
   * @return returns the surface area of the contour
   */
  public static final float AREA(Contour contour){
    return AREA(contour.getPixels());
  }
  
  /**
   * returns the surface area of the convex_hull
   * 
   * @param convex_hull the polyline to get the length from
   * @return returns the surface area of the convex_hull
   */
  public static final float AREA( ConvexHullDiwi convex_hull ){
    DoubleLinkedList<Pixel> list = convex_hull.get();
    list.gotoFirst();
    Pixel p0, p1;
    float area = 0;
    for(int cvh_idx = 0; cvh_idx < list.size(); cvh_idx++){
      p0 = list.getCurrentNode().get();
      list.gotoNext();
      p1 =  list.getCurrentNode().get();
      area += (p0.x_ + p1.x_)*(p1.y_ - p0.y_);
    }
    area *= (area < 0) ? -.5f : +.5f;
    return area;
  }