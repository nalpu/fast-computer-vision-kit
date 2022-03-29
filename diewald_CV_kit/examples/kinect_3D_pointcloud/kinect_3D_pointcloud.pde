
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
//----------------------------------------