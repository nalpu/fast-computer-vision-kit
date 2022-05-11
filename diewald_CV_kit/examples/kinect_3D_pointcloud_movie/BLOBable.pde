
//new BLOBable class, that implements the BLOBable-interface.
public final class BLOBable_Kinect_3D implements BLOBable{
  private int width_, height_;
  private String name_; 
  private PApplet papplet_;
  
  private Kinect3D k3d_;
  private KinectPoint3D k3d_points_[];
  
  private float min_depth_cm_ = 80;
  private float max_depth_cm_ = 130;
         
  
  public BLOBable_Kinect_3D(PApplet papplet){
    papplet_ = papplet;
  }
  
  // customized settings
  public final BLOB