//--------------------------------------------------------------------------------------------
// this tab is just a collection of functions that draw polylines, points, etc.
// you'll see this tab in the other examples too.
//--------------------------------------------------------------------------------------------





//float fps_av = 0;
//float fps_count = 0;

public void printlnFPS() {
  String frame_rate = String.format(Locale.ENGLISH, "speed: %6.2f fps%n", frameRate);
//  println(frame_rate);
//  if( frameCount > 50 ){
//    fps_av+=frameRate;
//    fps_count++;
//    println(String.format(Locale.ENGLISH, "speed_av: %6.2f fps%n", fps_av/fps_count));
//  }
  fill(100, 200, 255);
  text(frame_rate, 10, 20);
}



public void printlnNumberOfBlobs(BlobDetector blob_detector) {
  fill(100, 200, 255);
  text("numb