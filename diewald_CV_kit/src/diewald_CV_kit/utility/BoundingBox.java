
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

import diewald_CV_kit.blobdetection.Pixel;


/**
 *
 *
 * @author thomas diewald (c) 2011
 *
 */
public final class BoundingBox {
  private float center_x_, center_y_;
  private int x_min_, x_max_, y_min_, y_max_;
  private int size_x_, size_y_;
  
  /**
   * generate a new bounding-box.
   */
  public BoundingBox(){
  }
  /**
   * 
   * generate a new bounding-box, and define its bounds.
   * @param x_min minimum x-pos of the bounding-box.
   * @param y_min minimum y-pos of the bounding-box.
   * @param x_max maximum x-pos of the bounding-box.
   * @param y_max maximum y-pos of the bounding-box.
   */
  public BoundingBox(int x_min, int y_min, int x_max, int y_max){
    set(x_min, y_min, x_max, y_max);
  }
  
  /**
   * 
   * set the bounds of the bounding-box.
   * @param x_min minimum x-pos of the bounding-box.
   * @param y_min minimum y-pos of the bounding-box.
   * @param x_max maximum x-pos of the bounding-box.