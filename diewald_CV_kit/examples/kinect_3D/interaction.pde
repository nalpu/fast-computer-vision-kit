public void mousePressed() {
  detection_area.set(mouseX, mouseY, mouseX, mouseY);
}
public void mouseDragged() {
  int min_x = detection_area.xMin(