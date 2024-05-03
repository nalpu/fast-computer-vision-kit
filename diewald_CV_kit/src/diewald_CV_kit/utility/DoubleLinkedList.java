
/**
 * 
 * diewald_CV_kit v1.1
 * 
 * this library provides simple tools needed in computer-vision.
 * 
 * 
 * 
 *   (C) 2012    Thomas Diewald
 *               http://www.thomasdiewald.com
 *   
 *   last built: 12/13/2012
 *   
 *   download:   http://thomasdiewald.com/processing/libraries/diewald_CV_kit/
 *   source:     https://github.com/diwi/diewald_CV_kit 
 *   
 *   tested OS:  osx,windows
 *   processing: 1.5.1, 2.07
 *
 *
 *
 *
 * This source is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License is available on the World
 * Wide Web at <http://www.gnu.org/copyleft/gpl.html>. You can also
 * obtain it by writing to the Free Software Foundation,
 * Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */



package diewald_CV_kit.utility;

import java.util.ArrayList;
import java.util.List;



/**
 * just a common double linked list.<br>
 * <br>
 * 
 * 
 * @author thomas diewald (c) 2011
 *
 */
public final class DoubleLinkedList<E>{
  private Node<E> first   = null;
  private Node<E> current = null;
  private int size = 0;
  private int current_position = 0;
  
  private Node<E> marker = null;
  
  public DoubleLinkedList(){
  }
  
  /**
   * add a new node-value to the list, AFTER the current position.
   * @param node_value the new node value.
   */
  public final void add(E node_value){
    if (current == null){
      current = new Node<E>(node_value);
      current.next = current;
      current.prev = current;
      current_position = 0;
      first = current;
      marker = first;
    } else {
      Node<E> tmp = current;
      current = new Node<E>(node_value);
      current.prev = tmp;
      current.next = tmp.next;
      current.next.prev = current;
      current.prev.next = current; 
      current_position++;
    }