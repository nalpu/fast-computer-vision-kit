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



package diewald_CV_kit.blobdetection;

import java.util.ArrayList;

import diewald_CV_kit.utility.BoundingBox;



/**
 * the class Contour calculates a closed polyline of a blob.<br>
 * a blob can have only ONE outer contour, and endless inner contours.<br>
 * for example the letter "B" as an image, would have one outer contour, and two inner contours.<br>
 * 
 * @author thomas diewald (c) 2011
 *
 */
public final class Contour {
  
  private final Blob parent_blob_;
  private final int ID_;
  
  private final ArrayList<Pixel> pixels_ = new ArrayList<Pixel>();
  private final BoundingBox boundingbox_;
  

  protected final boolean clock_wise_; // only the outer contour goes in clockwise direction --> important for the convex hull;

  /**
   * 
   * @param parent_blob  the parent blob, this contour is part of
   * @param ID           the id of the contour. also used for checking the direction (clockwise or counter-clockwise).
   * @param x_pos_start  start of the contour - x-position
   * @param y_pos_start  start of the contour - y-position
   */
  protected Contour(Blob parent_blob, int ID, int x_pos_start, int y_pos_start){
    parent_blob_ = parent_blob;
    boundingbox_   = new BoundingBox();
    generate(parent_blob_.blob_detector_.img_pixels_, x_pos_start, y_pos_start);
    updateBoundingBox();
    ID_ = ID;
    
    clock_wise_ = (ID == 0 );
    

  }
  /**
   *----------------------------------------------------------------------------
   * my algorithm uses the "4 - neighborhood only"
   *--------------------------
   *   0  
   * 3 + 1 
   *   2  
   *-------------------------- 
   * 
   * @param pixel_list the array of pixels, with the blob IDs.
   * @param x_pos  start of the contour - x-position
   * @param y_pos  start of the contour - y-position
   * @return tru