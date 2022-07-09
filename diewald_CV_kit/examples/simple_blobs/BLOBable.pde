
//new BLOBable class, that implements the BLOBable-interface.
public final class BLOBable_GRADIENT implements BLOBable{
  int width_, height_;
  private float hsb_[] = new float[3];
  private float mousex_val_, mousey_val_;
  private String name_; 
  private PApplet papplet_;
  private PImage img_;
  
  public BLOBable_GRADIENT(PApplet papplet, PImage img){
    papplet_ = papplet;
    img_ = img;
  }
  
  //@Override
  public final void init() {
    name_ = this.getClass()