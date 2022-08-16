
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
    i