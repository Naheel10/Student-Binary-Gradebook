//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Gradebook Iterator
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for traversing the records in a Gradebook in increasing order without skipping any
 * element.
 */
public class GradebookIterator implements Iterator<StudentRecord> {
  private StudentRecord current;
  private Gradebook gradebook;

  /**
   * Creates a new GradebookIterator to iterate over the given Gradebook and initializes current to
   * references the minimum StudentRecord in the gradebook.
   * 
   * @param gradebook The gradebook.
   */
  public GradebookIterator(Gradebook gradebook) {

    this.gradebook = gradebook;
    this.current = gradebook.getMin();
  }

  /**
   * Returns true if the iteration has more elements (if current is not null). (In other words,
   * returns true if next() would return an element rather than throwing an exception.)
   */
  @Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next element in the iteration (meaning the current StudentRecord from the
   * Gradebook), and advances the current pointer to the next StudentRecord in the gradebook in the
   * increasing order.
   */
  @Override
  public StudentRecord next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements in the iterator.");
    }


    StudentRecord returnRecord = current;
    current = gradebook.successor(current);
    return returnRecord;
  }
}
