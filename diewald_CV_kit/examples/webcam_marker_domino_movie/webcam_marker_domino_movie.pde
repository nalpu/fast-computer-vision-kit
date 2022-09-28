

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
// -------------------------------------------------------
// interaction:
// key 's' - start making movie
// key 'e' - stop making movie
//---------------------------------------------------------
import java.util.Locale;
import diewald_CV_kit.libraryinfo.*;
import diewald_CV_kit.utility.*;
import diewald_CV_kit.blobdetection.*;
import codeanticode.gsvideo.*;
import processing.video.*;

MovieMaker mm;  // Declare MovieMaker object
boolean make_movie = !true;



PFont font;
PImage img_marker_1, img_marker_2, img_marker_1dot;