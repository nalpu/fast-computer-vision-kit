
//---------------------------------------------------------
//
// author: thomas diewald
// date: 06.09.2011
//
// example how to use diewald_CV_kit.blobdetection.
// 
// this example needs to have the dlibs.freenect library installed!
//
// download: 
//  1) http://thomasdiewald.com/blog/?p=109
//  2) http://thomasdiewald.com/processing/libraries/dLibs_freenect/
//
// -------------------------------------------------------
// interaction:
//
// key 'UP'   - lower resolution
// key 'DOWN' - higher resolution
// key 'b'   - draw boundingsboxes of blobs
// key 'f'   - fill blobs
//
//---------------------------------------------------------
import java.util.Locale;
import diewald_CV_kit.libraryinfo.*;
import diewald_CV_kit.utility.*;
import diewald_CV_kit.blobdetection.*;

import dLibs.freenect.*;
import dLibs.freenect.constants.*;
import dLibs.freenect.interfaces.*;


PFont font;

//-------------------------------------------------------------------
// kinect
Kinect kinect_;                     
KinectFrameVideo kinect_video_;     
KinectFrameDepth kinect_depth_;     
KinectTilt kinect_tilt_;

// get width/height --> actually its always 640 x 480
int size_x = VIDEO_FORMAT._RGB_.getWidth(); 
int size_y = VIDEO_FORMAT._RGB_.getHeight(); 
PImage video_frame_, depth_frame_;  // images

//-------------------------------------------------------------------
// blob detection 
BlobDetector blob_detector;
BoundingBox detection_area;
int detection_resolution = 1;
boolean draw_blobs_boundingsbox  = true;
boolean draw_filled_blobs        = true;

public void setup() {

  size(size_x, size_y*2);


  //--------------------------------------------------------------------------
  // KINECT STUFF - initialization
  kinect_ = new Kinect(0);  //create a main kinect instance with index 0

    kinect_video_ = new KinectFrameVideo(VIDEO_FORMAT._RGB_  );      // create a video instance, RGB
  kinect_depth_ = new KinectFrameDepth(DEPTH_FORMAT._11BIT_);      // create a depth instance
  kinect_tilt_  = new KinectTilt();                                // create a Tilt instance

  kinect_video_.setFrameRate(30);
  kinect_depth_.setFrameRate(30);


  kinect_video_.connect(kinect_);  // connect the created video instance to the main kinect
  kinect_depth_.connect(kinect_);  // connect the created depth instance to the main kinect
  kinect_tilt_ .connect(kinect_);  // connect Tilt to Kinect

  kinect_tilt_.setTiltDegrees(10);  // set tilt degrees

  // create a PImage for video/depth
  video_frame_ = createImage(VIDEO_FORMAT._RGB_  .getWidth(), VIDEO_FORMAT._RGB_     .getHeight(), RGB);
  depth_frame_ = createImage(DEPTH_FORMAT._11BIT_.getWidth(), DEPTH_FORMAT._11BIT_   .getHeight(), RGB);


  //--------------------------------------------------------------------------
  // BLOB DETECTION STUFF - initialization
  blob_detector = new BlobDetector(size_x, size_y);
  blob_detector.setResolution(detection_resolution);
  blob_detector.computeContours(true);
  blob_detector.computeBlobPixels(!true);
  blob_detector.setMinMaxPixels(10*10, size_x*size_y);

  blob_detector.setBLOBable(new BLOBable_Kinect_2D(this).setKinectDepth(kinect_depth_));

  detection_area = new BoundingBox(0, 0, size_x, size_y);
  blob_detector.setDetectingArea(detection_area);

  //--------------------------------------------------------------------------
  //  FONT, FRAMERATE, ...
  font = createFont("Calibri", 14);
  textFont(font);
  frameRate(200);
}




public void draw() {
  assignPixels( video_frame_, kinect_video_);
  assignPixels( depth_frame_, kinect_depth_);


  image(depth_frame_, 0, 0);
  image