

//---------------------------------------------------------
//
// author: thomas diewald
// date: 06.09.2011
//
// example how to use diewald_CV_kit.blobdetection.
//
// it shows some simple marker tracking. the markers are domino images (see the data folder).
// the marker recognition is very simple in this case:
//   found blobs are checked, for how much inner contours they have.
//   so 1 inner contour means yeah, i found "domino 1",
//   2 inner contour means yeah, i found "domino 2", ... and so on.
// 
// of course you can make some smarter tracking, by checking for other marker features too!
//

import java.util.Locale;
import diewald_CV_kit.libraryinfo.*;
import diewald_CV_kit.utility.*;
import diewald_CV_kit.blobdetection.*;
import codeanticode.gsvideo.*;



PFont font;
PImage img_marker_1, img_marker_2, img_marker_1dot;
int size_x, size_y;

BlobDetector blob_detector;
GSCapture video;
PImage maker_imgs[] = new PImage[6];
MarkerDomino marker[] = new MarkerDomino[maker_imgs.length];



public void setup() {

  String image_path = sketchPath+"/data/";
  for (int i = 0; i < marker.length; i++) {
    maker_imgs[i] = loadImage(image_path + "diewald_CV_kit_marker_domino_"+(i+1)+".jpg");
    marker[i] = new MarkerDomino(this, maker_imgs[i], "domino_"+(i+1));
  }

  video = new GSCapture(this, 640, 480, 30);
  size(video.width, video.height);

  blob_detector = new BlobDetector(video.width, video.height);
  blob_detector.setResolution(1);
  blob_detector.computeContours(true);
  blob_detector.computeBlobPixels(true);
  blob_detector.setMinMaxPixels(10*10, video.width*video.height/5);

  blob_detector.setBLOBable(new BLOBable_MARKER(this, video));

  font = createFont("Calibri", 14);
  textFont(font);
  frameRate(200);
}





public void draw() {

  //image(sample_img, 0, 0);

  if (video != null && video.available()) {
    background(255);
    video.read();
    image(video, 0, 0);
    println("");