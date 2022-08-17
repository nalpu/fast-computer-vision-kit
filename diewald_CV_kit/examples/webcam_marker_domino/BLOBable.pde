
//new BLOBable class, that implements the BLOBable-interface.
public final class BLOBable_MARKER implements BLOBable{
  int width_, height_;
  private float hsb_[] = new float[3];
  private float mousex_val_, mousey_val_;
  private String name_;
  private PApplet papplet_;
  private PImage img_;
  
  public BLOBable_MARKER(PApplet papplet, PImage img){
    papplet_ = papplet;
    img_ = img;
  }
  
  //@Override
  public final void init() {
    name_ = this.getClass().getSimpleName(); 
  }
  
  //@Override
  public final void updateOnFrame( int width, int height) {
    width_ = width;
    height_ = height;
    mousex_val_ = PApplet.map(papplet_.mouseX, 0, papplet_.width,  0, 100);
    mousey_val_ = PApplet.map(papplet_.mouseY, 0, pa