


//---------------------------------------------------------------------------------------------------- 
void initPeasyCam() {
  cam = new PeasyCam(this, 0, 0, 350, 400);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(100000);
  cam.setRotations(2.9, -0.2, 3.07); 

  cam.setDistance (381.16);
  cam.setRotations(  0.18,    0.52,   -0.12);
  cam.lookAt      (-44.28,   36.57, -250.13);

}


public void printCamSettings(PeasyCam camera) {
  float[] la  = camera.getLookAt();
  float[] rot = camera.getRotations();
  float[] pos = camera.getPosition();
  flo