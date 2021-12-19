

//new BLOBable class, that implements the BLOBable-interface.
public final class BLOBable_Letters implements BLOBable{
  int width_, height_;
  private float hsb_[] = new float[3];
  private float mousex_val_, mousey_val_;
  private String name_; 
  
  private PApplet papplet_;
  private PImage img_;
  
  public BLOBable_Letters(PApplet papplet, PImage img){
    papplet_ = papplet;
    img_ = img;
  }
  public final void setImg(PImage img){
    img_ = img;
  }
  
  //@Override
  public final void init() {
    name_ = this.getClass().getSimpleName(); 
  }
  
  //@Override
  public 