//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Student Record
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

/**
 * This class models student course records.
 */
public class StudentRecord implements Comparable<StudentRecord> {
  public final String name;
  public final String email;
  private double grade;

  /**
   * Constructs a new StudentRecord given the name, email, and grade of a given student. If the name
   * or email is null or blank, the constructor throws an IllegalArgumentException. Additionally, if
   * the grade is not between the range 0.0 to 100.0 (inclusive) the constructor throws an
   * IllegalArgumentException.
   * 
   * @param name  the student's name
   * @param email the student's email
   * @param grade the student's grade
   */
  public StudentRecord(String name, String email, double grade) {

    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }

    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty.");
    }

    if (grade < 0.0 || grade > 100.0) {
      throw new IllegalArgumentException("Grade must be in the range 0.0 to 100.0 (inclusive).");
    }

    this.name = name;
    this.email = email;
    this.grade = grade;
  }

  // Getter for grade
  public double getGrade() {
    return grade;
  }

  // Setter for grade
  public void setGrade(double grade) {

    if (grade < 0.0 || grade > 100.0) {
      throw new IllegalArgumentException("Grade must be in the range 0.0 to 100.0 (inclusive).");
    }
    this.grade = grade;
  }

  /**
   * Returns a String representation of this StudentRecord in the following format: "name (email):
   * grade"
   */
  @Override
  public String toString() {
    return name + " (" + email + "): " + grade;
  }

  /**
   * Compares this StudentRecord to other StudentRecord passed as input. StudentRecords are compared
   * with respect to the lexicographical (alphabetical) order of the students emails.
   */
  @Override
  public int compareTo(StudentRecord other) {
    return this.email.compareTo(other.email);
  }

  /**
   * Returns true if the given Object is a StudentRecord with an email that matches the email of
   * this StudentRecord.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true; // Same object reference
    if (o == null || getClass() != o.getClass())
      return false;
    StudentRecord other = (StudentRecord) o;
    return email.equals(other.email);
  }
}
