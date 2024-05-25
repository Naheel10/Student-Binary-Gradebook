//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Passing Grade Iterator
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

import java.util.NoSuchElementException;

/**
 * Iterator for traversing the records in a Gradebook in increasing order, while also skipping over
 * StudentRecords who do not have a passing grade. This iterator iterates through the StudentRecord
 * objects with passing grades, only.
 */
public class PassingGradeIterator extends GradebookIterator {
  private StudentRecord next;
  private double passingGrade;

  /**
   * Constructs a new PassingGradeIterator to iterate over the StudentRecords with passing grades in
   * a given Gradebook (StudentRecords with NO passing grades are skipped by this iterator).
   * 
   * @param gradebook The gradebook.
   */
  public PassingGradeIterator(Gradebook gradebook) {
    super(gradebook);

    this.next = gradebook.getMin();
    this.passingGrade = gradebook.PASSING_GRADE;

    advanceToNextPassingGrade();
  }


  /**
   * Private helper method that advances this iterator to the next StudentRecord with a passing
   * grade. Then, it stores it into next. If no more StudentRecord with a passing grade are
   * available in the iteration, this method sets next to null. This method uses super.hasNext() and
   * super.next() in a while loop to operate.
   */
  private void advanceToNextPassingGrade() {

    while (super.hasNext()) {

      StudentRecord record = super.next();

      if (record.getGrade() >= passingGrade) {

        next = record;
        return;
      }
    }

    next = null;
  }

  /**
   * Returns true if the iteration has more elements (if next is not null). (In other words, returns
   * true if next() would return an element rather than throwing an exception.)
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next StudentRecord object with a passing grade in the iteration and advances the
   * iteration to the next record with passing grade.
   */
  @Override
  public StudentRecord next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements with passing grade.");
    }

    StudentRecord currentRecord = next;

    advanceToNextPassingGrade();

    return currentRecord;
  }
}
