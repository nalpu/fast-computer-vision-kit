
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

import diewald_CV_kit.utility.PixelColor;

/**
*
* the class Blob represents the blobs/labels/regions found in an image.<br>
* 
* 
* @author thomas diewald (c) 2011
*
*/
public final class Blob {
  protected final BlobDetector blob_detector_;
  
  private final ArrayList<PixelRow> pixelrows_ = new ArrayList<PixelRow>(150);
  private PixelColor color_ = new PixelColor((int)(Math.random()*255f), (int)(Math.random()*255f), (int)(Math.random()*255f)); 
  private final ArrayList<Contour> contours_ = new ArrayList<Contour>(5);
  private Pixel pixels_[];
  private int ID_;
  private int number_of_pixels_;
  private int outer_contour_start_x_, outer_contour_start_y_;


  /**
   * the blob gets initialized after the blobdetection process.
   * the given pixelrow gets linked, and with it all the other pixelrows, linked to this one.
   * so its an recursive linking of pixelrows to this blob.
   * heres a small problem on a very big recursion depth, which can cause an stackoverflow!!!!!
   * 
   * 
   * @param blob_detector  parent blobdetector
   * @param ID             id of the blob
   * @param pixelrow       the pixel row that gets linked to the blob
   * @param stack_version  if false, the blob is generated by recursively iterating the adjacent pixelrows
   */
  protected Blob(BlobDetector blob_detector, int ID, PixelRow pixelrow){ 
    blob_detector_ = blob_detector;
    ID_ = ID;

    pixelrow.linkWithBlob(this); // recursively generate a blob of adjacent pixelrows

    
    updateNumberOfPixels(); 
  }
  /*
  protected Blob(BlobDetector blob_detector, int ID, PixelRow pixelrow, boolean stack_version ){ 
    blob_detector_ = blob_detector;
    ID_ = ID;
    
    if( !stack_version )
      pixelrow.linkWithBlob(this); // recursively generate a blob of adjacent pixelrows
    else
      PixelRow.linkWithBlob(this, pixelrow); // ... using a stack
    
    updateNumberOfPixels(); 
  }
  */
  
  
  
  /**
   * add another pixelrow to this blob.
   * 
   * @param pixelrow the pixelrow to add
   */
  protected final void addPixelRow( PixelRow pixelrow ){
    pixelrows_.add(pixelrow);
  }
  
  /**
   * generates contours for this blob
   */
  protected final void updateContours(){
    // get outer(first) contour
    int contour_index = 0;
    contours_.add( new Contour(this, contour_index++, outer_contour_start_x_, outer_contour_start_y_)); // outer contour
    
    // now get possible inner contours
    for(int i = 0; i < pixelrows_.size(); i++){
      PixelRow pr = pixelrows_.get(i);
      if( !blob_detector_.img_pixels_[pr.y_][pr.xs_].border_ ) {
        contours_.add( new Contour(this, contour_index++, pr.xs_, pr.y_));
      }
    }
  }
  
  
  
  
  /**
   * computes the number of pixels.
   * AND the coordinates where the outer contour will start
   */
  private final void updateNumberOfPixels(){
    number_of_pixels_ = 0;
 
    // find min x-value of a blob, to get the outer contour first
    outer_contour_start_x_ = blob_detector_.width_;
    outer_contour_start_y_ = 0;
    for(int i = 0; i < pixelrows_.size(); i++){
      PixelRow pr = pixelrows_.get(i);
      number_of_pixels_ += pr.xe_ - pr.xs_ + 1; // count number of pixels
      if( pr.xs_ < outer_contour_start_x_ ){
        outer_contour_start_x_ = pr.xs_;
        outer_contour_start_y_ = pr.y_;
      }
      if( pr.xs_ == outer_contour_start_x_ && pr.y_ < outer_contour_start_y_){
        outer_contour_start_x_ = pr.xs_;
        outer_contour_start_y_ = pr.y_;
      }

    }
    number_of_pixels_ *= blob_detector_.pixel_jump_;
  }
  
  /**
   *  generate an array of pixels, that are part of this blob.
   */
  protected final void updatePixels(){
    pixels_ = new Pixel[number_of_pixels_/blob_detector_.pixel_jump_];
    int index = 0;
    for(int i = 0; i < pixelrows_.size(); i++){
      PixelRow pr = pixelrows_.get(i);
      for(int x = pr.xs_; x <= pr.xe_; x++)
        pixels_[index++] = blob_detector_.img_pixels_[pr.y_][x];
    }
  }
  


  
  /**
   * set this blobs color.
   * 
   * @param color the new color of the blob.
   */
  public final void setColor(PixelColor color){
    color_ = color;
  }
  
  /**
   *  get the blobs current color.
   *  
   * @return the current blob-color.
   */
  public final PixelColor getColor(){
    return color_;
  }
  
  /**
   * get all the pixelrows of this blob.
   * usually there's no need for this.
   * 
   * @return a list of all the pixelrows
   */
  public final ArrayList<PixelRow> getPixelRows(){
    return pixelrows_;
  }
  
  /**
   * give the blob a new ID. 
   *  
   * @param ID the new id.
   */
  public final void setID(int ID){
    ID_ = ID;
  }
  /**
   * returns the id of the blob.
   * @return the id of the blob.
   */
  public final int getID(){
    return ID_;
  }
  
  /**
   * returns a list of all contours of this blob.<br>
   * there's only ONE outer contour, and there can be endless inner contours.<br>
   * the outer contour has a clockwise rotation. All the inner ones have a counter-clockwise rotation.<br>
   * 
   * 
   * @return list of contours.
   */
  public final ArrayList<Contour> getContours(){
    return contours_;
  }
  /**
   * get the total number of pixels of this blob. which is also the area-size of the blob.
   * @return number of pixels
   */
  public final int getNumberOfPixels(){
    return number_of_pixels_;
  }
  
  /**
   * get the list of blob-pixels.
   * @return list of blob-pixels
   */
  public final Pixel[] getPixels(){
    return pixels_;
  }
  

}

