
//-------------------------------------------------------------------
void drawPointCloud(int resolution ) {  
  // get the kinects 3d-data (by reference)
  KinectPoint3D kinect_3d[] = k3d_.get3D();


  int cam_w_ = size_x;
  int cam_h_ = size_y;

  strokeWeight(1f); 

  int default_color = color(0);
  stroke( default_color );

  KinectPoint3D point;
  for (int y = 0; y < cam_h_-resolution ; y += resolution) {
    for (int x = 0; x< cam_w_-resolution ; x += resolution) {
      int index1 = y*cam_w_+x;
      point = kinect_3d[index1];

      if (point.z > 0 ) continue; // 