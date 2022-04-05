
//---------------------------------------------------------
//
// author: thomas diewald
// date: 06.09.2011
//
// example how to use diewald_CV_kit.blobdetection.
// 
// this example needs to have the "dlibs.freenect" library installed!
//
// download: 
//  1) http://thomasdiewald.com/blog/?p=109
//  2) http://thomasdiewald.com/processing/libraries/dLibs_freenect/
//
// the blob detection happens for a list of 3d-points from the kinect.
// only a defined area is tracked, defined by a far/near clipping plane, 
// and a cutted camera-cone.
// the tracked blobs outer contours are drawn into the 3d-sene, 
// and projected onto the z-plane (rotated by 90 degrees).
//
// -------------------------------------------------------
// interaction:
//
// dragged mouse - drag a rectangle to define the detection area
//
// key 'UP'   - lower resolution
// key 'DOWN' - higher resolution
// key 'c'    - print camera orientation settings
//
//---------------------------------------------------------
import java.util.Locale;
import diewald_CV_kit.libraryinfo.*;
import diewald_CV_kit.utility.*;
import diewald_CV_kit.blobdetection.*;

import dLibs.freenect.*;
import dLibs.freenect.constants.*;
import dLibs.freenect.interfaces.*;
import dLibs.freenect.toolbox.*;


import processing.opengl.*;
import peasy.*; // peasycam



PFont font;

//-------------------------------------------------------------------
// kinect
Kinect kinect_;                     
Kinect3D k3d_;   
KinectFrameDepth kinect_depth_;     
KinectTilt kinect_tilt_;

// get width/height --> actually its always 640 x 480
int size_x = VIDEO_FORMAT._RGB_.getWidth(); 
int size_y = VIDEO_FORMAT._RGB_.getHeight(); 


//-------------------------------------------------------------------
// blob detection 
BlobDetector blob_detector;
BLOBable_Kinect_3D blobsable_kinect3d;
BoundingBox detection_area;
int detection_resolution = 1;


float clipping_depth = 15;
float clipping_near = 50;
float clipping_far  = clipping_near + clipping_depth;
float clipping_animstep = .1f;

//-------------------------------------------------------------------
// PeasyCam
PeasyCam cam;   

int scale_screen = 2;
public void setup() {

  size(size_x*scale_screen, size_y*scale_screen, OPENGL);

  //--------------------------------------------------------------------------
  // KINECT STUFF - initialization
  kinect_ = new Kinect(0);  //create a main kinect instance with index 0
  kinect_depth_ = new KinectFrameDepth(DEPTH_FORMAT._11BIT_);  // create a depth instance
  kinect_tilt_  = new KinectTilt();  

  k3d_ = new Kinect3D(); // generate a 3d instance
  k3d_.setFrameRate(30); // set framerate

  kinect_depth_.connect(kinect_);  //connect the created depth instance to the main kinect
  k3d_.connect(kinect_);
  kinect_tilt_.connect(kinect_);

  kinect_tilt_.setTiltDegrees(13);
  initPeasyCam();

  //--------------------------------------------------------------------------
  // BLOB DETECTION STUFF - initialization
  blob_detector = new BlobDetector(size_x, size_y);
  blob_detector.setResolution(detection_resolution);
  blob_detector.computeContours(true);
  blob_d