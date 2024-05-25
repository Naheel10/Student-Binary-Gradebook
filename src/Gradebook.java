//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Gradebook
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: I was finding it difficult implementing BST in a few methods so I asked
// my friend Nischal Bista who simply cleared my concepts a bit which allowed me to
// better understand this project.
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class models a grade book for a specific course used to store student records. Every
 * gradebook is iterable, meaning the enhanced for loop can be used to iterate through its contents.
 */
public class Gradebook implements java.lang.Iterable<StudentRecord> {

  public final java.lang.String course;
  public final double PASSING_GRADE;
  private boolean passingGradeIteratorEnabled;
  private BSTNode<StudentRecord> root;
  private int size;


  /**
   * Constructs an empty Gradebook for a given course and define its passing grade. We assume that
   * that this gradebook iterates through every stored grade (meaning that the passingGradeIterator
   * is not enabled by default).
   * 
   * @param course       name of the course
   * @param passingGrade passing grade of the course
   */
  public Gradebook(String course, double passingGrade) {
    if (course == null || course.trim().isEmpty()) {
      throw new IllegalArgumentException("Course name cannot be null or blank");
    }
    if (passingGrade < 0.0 || passingGrade > 100.0) {
      throw new IllegalArgumentException("Passing grade must be between 0 and 100");
    }
    this.course = course;
    this.PASSING_GRADE = passingGrade;
    this.root = null;
    this.size = 0;
    this.passingGradeIteratorEnabled = false;
  }

  // Method to enable the passing grade iterator
  public void enablePassingGradeIterator() {
    passingGradeIteratorEnabled = true;
  }

  // Method to disable the passing grade iterator
  public void disablePassingGradeIterator() {
    passingGradeIteratorEnabled = false;
  }

  // Method to check if the Gradebook is empty
  public boolean isEmpty() {
    return root == null;
  }

  // Method to return the size of the Gradebook
  public int size() {
    return size;
  }

  /**
   * Adds a new StudentRecord to this Gradebook. This method tries to add record to this tree and
   * updates its size accordingly. Be sure to update the root to the BSTNode returned by the
   * addStudentHelper() method.
   * 
   * @param record to be added to this Gradebook
   */
  public void addStudent(StudentRecord record) {
    if (record == null) {
      throw new IllegalArgumentException("StudentRecord cannot be null");
    }
    root = addStudentHelper(record, root);
    size++;
  }

  /**
   * Recursive helper method to add a record to the subtree rooted at node
   * 
   * @param record new Student to add
   * @param node   root of a subtree
   * @return the new root of this BST after adding the record to this tree
   */
  protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record,
      BSTNode<StudentRecord> node) {
    if (node == null) {
      return new BSTNode<>(record);
    }
    int comparison = record.compareTo(node.getData());
    if (comparison < 0) {
      node.setLeft(addStudentHelper(record, node.getLeft()));
    } else if (comparison > 0) {
      node.setRight(addStudentHelper(record, node.getRight()));
    } else {
      throw new IllegalStateException("Duplicate StudentRecord not allowed");
    }
    return node;
  }

  /**
   * Finds a StudentRecord given the associated email address
   * 
   * @param email email address of a student
   * @return the Student associated with the email argument if there is a match, or null otherwise
   */
  public StudentRecord lookup(String email) {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty.");
    }


    StudentRecord target = new StudentRecord("Target Student", email, 0);


    return lookupHelper(target, root);
  }

  /**
   * Recursive helper method which looks for a given StudentRecord given in the BST rooted at node
   * 
   * @param target the StudentRecord to search in the subtree rooted at node
   * @param node   root of a subtree of this BST
   * @return the StudentRecord which matches the one passed as input if a match is found in the
   *         subtree rooted at node, or null if no match found
   */
  protected static StudentRecord lookupHelper(StudentRecord target, BSTNode<StudentRecord> node) {
    if (node == null) {
      return null;
    }

    int comparison = target.email.compareToIgnoreCase(node.getData().email);


    if (comparison == 0) {
      return node.getData();
    } else if (comparison < 0) {

      return lookupHelper(target, node.getLeft());
    } else {

      return lookupHelper(target, node.getRight());
    }
  }

  /**
   * Searches for the StudentRecord associated with the provided input email in this BST and checks
   * whether it has a passing grade for this course.
   * 
   * @param email the email of the StudentRecord to find
   * @return A String indicating whether the student having the input email has a passing or failing
   *         grade.
   */
  public String checkPassingCourse(String email) {
    if (email == null || email.isBlank()) {
      return "Invalid email input.";
    }
    StudentRecord student = lookup(email);
    if (student == null) {
      return "No match found.";
    }
    if (student.getGrade() >= PASSING_GRADE) {
      return student.toString() + ": PASS";
    } else {
      return student.toString() + ": FAIL";
    }
  }

  /**
   * Returns the StudentRecord with the lexicographically smallest email in this BST, or null if
   * this Gradebook is empty.
   * 
   * @return the StudentRecord with the lexicographically smallest email in this BST
   */
  protected StudentRecord getMin() {
    return getMinHelper(root);
  }

  /**
   * Returns the smallest StudentRecord (with respect to the result of Student.compareTo() method)
   * in the subtree rooted at node
   * 
   * @param node root of a subtree of a binary search tree
   * @return the smallest Student in the subtree rooted at node, or null if the node is null
   */
  protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return null;
    }

    if (node.getLeft() == null) {
      return node.getData();
    } else {

      return getMinHelper(node.getLeft());
    }
  }


  /**
   * Returns the successor of a target StudentRecord (smallest value in the BST that is larger than
   * the target), or returns null if there is no successor in this Gradebook.
   * 
   * @param target the StudentRecord to find the successor of
   * @return the successor of the target in the Gradebook, or null if none exists
   */
  protected StudentRecord successor(StudentRecord target) {
    if (target == null) {
      return null;
    }
    return successorHelper(target, root);
  }

  /**
   * Returns the successor of a target StudentRecord within the subtree (smallest value in the
   * subtree that is larger than the target), or returns null if there is no successor in this
   * subtree.
   * 
   * @param target the StudentRecord to find the successor of
   * @param node   the subtree to search for a successor to the target
   * @return the successor of the target in the subtree rooted at node, or null if none exists
   */
  protected static StudentRecord successorHelper(StudentRecord target,
      BSTNode<StudentRecord> node) {

    StudentRecord successor = null;


    while (node != null) {

      if (target.compareTo(node.getData()) < 0) {

        successor = node.getData();
        node = node.getLeft();
      } else {

        node = node.getRight();
      }
    }

    return successor;
  }

  /**
   * Deletes a StudentRecord from this Gradebook given their email, or throws a
   * NoSuchElementException if there is no StudentRecord with the given email.
   * 
   * @param email the email of the student to delete
   */
  public void removeStudent(String email) {

    if (email == null || email.trim().isEmpty()) {
      System.out.println("Invalid email provided: Email cannot be null or empty.");
      return;
    }


    String normalizedEmail = email.trim().toLowerCase();


    BSTNode<StudentRecord> currentNode = root;
    BSTNode<StudentRecord> parentNode = null;
    StudentRecord studentRecordToRemove = null;

    while (currentNode != null) {
      int comparison = normalizedEmail.compareToIgnoreCase(currentNode.getData().email);

      if (comparison < 0) {

        currentNode = currentNode.getLeft();
      } else if (comparison > 0) {

        currentNode = currentNode.getRight();
      } else {

        studentRecordToRemove = currentNode.getData();
        break;
      }
    }

    if (studentRecordToRemove != null) {
      root = removeStudentHelper(studentRecordToRemove, root);
    } else {
      System.out.println("No matching student record found for email: " + email);
    }
  }



  /**
   * Deletes the matching StudentRecord with toDrop if it is found within this tree, or otherwise
   * throws a NoSuchElementException.
   * 
   * @param record
   * @param node
   * @return
   */
  protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop,
      BSTNode<StudentRecord> node) {
    if (node == null) {
      return null; // Base case: node not found
    }

    if (toDrop.compareTo(node.getData()) < 0) {

      node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
    } else if (toDrop.compareTo(node.getData()) > 0) {

      node.setRight(removeStudentHelper(toDrop, node.getRight()));
    } else {

      if (node.getLeft() == null) {

        return node.getRight();
      } else if (node.getRight() == null) {

        return node.getLeft();
      } else {

        BSTNode<StudentRecord> minRight = node.getRight();
        while (minRight.getLeft() != null) {
          minRight = minRight.getLeft();
        }

        node.setData(minRight.getData());

        node.setRight(removeStudentHelper(minRight.getData(), node.getRight()));
      }
    }
    return node;
  }



  /**
   * Returns a String representation of the contents of this Gradebook in increasing order
   * 
   * @return an in-order String representation of this Gradebook
   */
  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    sb.append(toStringHelper(root));

    return sb.toString().trim();
  }


  /**
   * Returns a String representation of the subtree rooted at node in increasing order
   * 
   * @param node root of a subtree
   * @return an in-order String representation of the subtree rooted at node
   */
  protected static String toStringHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return "";
    }
    String left = toStringHelper(node.getLeft());
    String right = toStringHelper(node.getRight());

    StudentRecord data = node.getData();
    String formattedData = String.format("%s (%s): %.1f\n", data.name, data.email, data.getGrade());

    return left + formattedData + right;
  }



  /**
   * Method to get a String representation of the tree structure
   * 
   * @return a String representation of the structure of this BST
   */
  public String prettyString() {
    return prettyStringHelper(root, 0);
  }

  /**
   * Returns a decreasing-order String representation of the structure of this subtree, indented by
   * four spaces for each level of depth in the larger tree.
   *
   * @author Ashley Samuelson
   * @param node  current subtree within the larger tree
   * @param depth depth of the current node within the larger tree
   * @return a String representation of the structure of this subtree
   */
  protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {
    if (node == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(prettyStringHelper(node.getRight(), depth + 1));
    sb.append(" ".repeat(4 * depth)).append(node.getData().name).append("\n");
    sb.append(prettyStringHelper(node.getLeft(), depth + 1));
    return sb.toString();
  }



  /**
   * Calls the compareBST method which is a helper method for this
   * 
   * @param node tree to compare this Gradebook to
   * @return true if the given tree looks identical to the root of this Gradebook
   */
  public boolean equalBST(BSTNode<StudentRecord> node) {
    return compareBST(root, node);
  }

  /**
   * Recursively compares and returns true if this BST has an identical layout (all subtrees equal)
   * to the given tree.
   * 
   * @param a one of the subtrees
   * @param b the other subtree
   * @return true if the given tree looks identical to the root of this Gradebook.
   */
  private boolean compareBST(BSTNode<StudentRecord> a, BSTNode<StudentRecord> b) {
    if (a == null && b == null)
      return true;

    if (a == null || b == null)
      return false;
    return a.getData().equals(b.getData()) && compareBST(a.getLeft(), b.getLeft())
        && compareBST(a.getRight(), b.getRight());
  }


  /**
   * Returns an iterator over the student records in this gradebook in the increasing order. If the
   * passing grade iterator is enabled, this method returns an iterator that iterates through
   * records with passing grades only while skipping the ones that fail to pass.
   */
  public Iterator<StudentRecord> iterator() {
    return new Iterator<StudentRecord>() {
      private Stack<BSTNode<StudentRecord>> stack = new Stack<>();
      private BSTNode<StudentRecord> currentNode;

      {
        currentNode = root;
        while (currentNode != null) {
          stack.push(currentNode);
          currentNode = currentNode.getLeft();
        }
      }

      @Override
      public boolean hasNext() {
        return !stack.isEmpty();
      }

      @Override
      public StudentRecord next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        BSTNode<StudentRecord> node = stack.pop();
        StudentRecord record = node.getData();

        currentNode = node.getRight();
        while (currentNode != null) {
          stack.push(currentNode);
          currentNode = currentNode.getLeft();
        }

        if (!passingGradeIteratorEnabled || record.getGrade() >= PASSING_GRADE) {
          return record;
        }

        return next();
      }
    };
  }

}
