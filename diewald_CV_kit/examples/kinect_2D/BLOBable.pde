

//new BLOBable class, that implements the BLOBable-interface.
public final class BLOBable_Kinect_2D implements BLOBable{
  int width_, height_;
  private String name_; 
  
  private PApplet papplet_;
  private KinectFrameDepth kinect_depth_;
  private int[] kinect_depth_values;
  
  public BLOBable_Kinect_2D(PApplet papplet){
    papplet_ = papplet;
  }
  
  public BLOBab