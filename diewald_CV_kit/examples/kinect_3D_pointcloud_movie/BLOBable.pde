
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
  public final BLOBable_Kinect_3D setKinect3d(Kinect3D k3d){
    k3d_ = k3d;
    return this;
  }
  public final BLOBable_Kinect_3D setMinMaxDepth(float min_depth, float max_depth){
    min_depth_cm_ = min_depth;
    max_depth_cm_ = max_depth;
    return this;
  }
  public float getMinDepth(){
    return min_depth_cm_;
  }
  public float getMaxDepth(){
    return max_depth_cm_;
  }
  
  
  // must-have 