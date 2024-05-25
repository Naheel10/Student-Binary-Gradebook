//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Gradebook Tester
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: I was having trouble implementing the constructorTester as it was resulting in
// exceptions so I asked my friend Nischal Bista for help who suggested to use try-catch blocks and
// it worked.
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GradebookTester {

  /**
   * Tests the Gradebook constructor across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean constructorTester() {
    boolean allTestsPassed = true;

    try {
      Gradebook gb = new Gradebook("Math340", 50.0);
      // Check course name and passing grade
      if (!gb.course.equals("Math340") || gb.PASSING_GRADE != 50.0) {
        System.out
            .println("Test failed: Valid inputs did not produce correct course and passing grade.");
        allTestsPassed = false;
      }
    } catch (Exception e) {
      System.out.println("Test failed: Valid inputs caused an exception.");
      allTestsPassed = false;
    }

    // Test with invalid course name (null)
    try {
      Gradebook gb = new Gradebook(null, 50.0);
      System.out.println("Test failed: Invalid course name (null) did not cause an exception.");
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {

    }

    // Test with invalid course name (empty)
    try {
      Gradebook gb = new Gradebook("", 50.0);
      System.out.println("Test failed: Invalid course name (empty) did not cause an exception.");
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {

    }

    // Test with invalid passing grade (below 0)
    try {
      Gradebook gb = new Gradebook("Math340", -10.0);
      System.out
          .println("Test failed: Invalid passing grade (below 0) did not cause an exception.");
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {

    }

    // Test with invalid passing grade (above 100)
    try {
      Gradebook gb = new Gradebook("Math340", 105.0);
      System.out
          .println("Test failed: Invalid passing grade (above 100) did not cause an exception.");
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {

    }

    return allTestsPassed;
  }


  /**
   * Tests the toString method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean toStringTester() {
    boolean allTestsPassed = true;

    Gradebook gradebook = new Gradebook("Math340", 50.0);


    gradebook.addStudent(new StudentRecord("D", "D@gmail.com", 60));
    gradebook.addStudent(new StudentRecord("C", "C@gmail.com", 60));
    gradebook.addStudent(new StudentRecord("B", "B@gmail.com", 60));
    gradebook.addStudent(new StudentRecord("A", "A@gmail.com", 60));


    String expectedOutput = "A (A@gmail.com): 60.0\n" + "B (B@gmail.com): 60.0\n"
        + "C (C@gmail.com): 60.0\n" + "D (D@gmail.com): 60.0";


    String actualOutput = gradebook.toString();

    // Compare the expected and actual output
    if (!actualOutput.equals(expectedOutput)) {
      System.out.println("Test failed: toString() method");
      System.out.println("Expected: \n" + expectedOutput);
      System.out.println("Actual: \n" + actualOutput);
      allTestsPassed = false;
    }

    Gradebook gradebook2 = new Gradebook("CS300", 60.0);
    gradebook2.addStudent(new StudentRecord("B", "B@gmail.com", 60));
    gradebook2.addStudent(new StudentRecord("D", "D@gmail.com", 60));
    gradebook2.addStudent(new StudentRecord("C", "C@gmail.com", 60));
    gradebook2.addStudent(new StudentRecord("A", "A@gmail.com", 60));

    String actualOutput2 = gradebook.toString();
    if (!actualOutput2.equals(expectedOutput)) {
      return false;
    }

    return allTestsPassed;
  }

  /**
   * Tests the isEmpty, size, and addStudent methods across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean isEmptySizeAddTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);

    // Testing isEmpty() when gradebook is empty
    if (!gradebook.isEmpty()) {
      System.out.println("Test failed: Gradebook should be initially empty.");
      allTestsPassed = false;
    }

    // Testing size() when gradebook is empty
    if (gradebook.size() != 0) {
      System.out.println("Test failed: Initial size should be 0.");
      allTestsPassed = false;
    }

    // Add a student record
    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85.0);
    gradebook.addStudent(record1);

    // Test isEmpty() after adding a student record
    if (gradebook.isEmpty()) {
      System.out.println("Test failed: Gradebook should not be empty after adding a student.");
      allTestsPassed = false;
    }

    // Test size() after adding a student record
    if (gradebook.size() != 1) {
      System.out.println("Test failed: Size should be 1 after adding one student.");
      allTestsPassed = false;
    }

    // Add another student record
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75.0);
    gradebook.addStudent(record2);

    // Test size() after adding a second student record
    if (gradebook.size() != 2) {
      System.out.println("Test failed: Size should be 2 after adding two students.");
      allTestsPassed = false;
    }

    return allTestsPassed;
  }

  /**
   * Tests the prettyString method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean prettyStringTester() {

    Gradebook gradebook = new Gradebook("Math340", 50.0);


    gradebook.addStudent(new StudentRecord("Spiderman", "spiderman@gmail.com", 85.0));
    gradebook.addStudent(new StudentRecord("John Cena", "john.cena@gmail.com", 95.0));
    gradebook.addStudent(new StudentRecord("David", "david@gmail.com", 70.0));
    gradebook.addStudent(new StudentRecord("Alex", "alex@gmail.com", 90.0));


    String actualOutput = gradebook.prettyString();


    String expectedOutput =
        "Spiderman\n" + "    John Cena\n" + "        David\n" + "            Alex\n";

    // Compare the expected and actual output
    boolean allTestsPassed = actualOutput.equals(expectedOutput);

    // Output an error message if the test fails
    if (!allTestsPassed) {
      System.out.println("prettyString test failed.");
      System.out.println("Expected output:");
      System.out.println(expectedOutput);
      System.out.println("Actual output:");
      System.out.println(actualOutput);
    }

    return allTestsPassed;
  }


  /**
   * Tests the lookup method across various situations.
   * 
   * @return True or False depending on the tests.
   */
  public static boolean lookupTester() {


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 90);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);


    // Test lookup method with a non-existing email
    if (gradebook.lookup("student4@gmail.com") != null) {
      return false;
    }

    // Test lookup method with an existing email
    if (!(gradebook.lookup("student1@gmail.com").equals(record1))) {
      return false;
    }


    // Test lookup method with an existing email
    if (!(gradebook.lookup("student3@gmail.com").equals(record3))) {
      return false;
    }

    return true;
  }


  /**
   * Tests the getMin method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean getMinTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord minRecord = gradebook.getMin();
    if (minRecord != null) {
      System.out
          .println("getMin test failed: Expected null for empty gradebook, but got a record.");
      allTestsPassed = false;
    }


    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 90);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);

    // Testing getMin with records
    minRecord = gradebook.getMin();
    if (minRecord == null) {
      System.out.println("getMin test failed: Expected a student record, but got null.");
      allTestsPassed = false;
    } else if (!minRecord.email.equals("student1@gmail.com")) {
      System.out
          .println("getMin test failed: Expected student1@gmail.com, but got " + minRecord.email);
      allTestsPassed = false;
    }

    // Adding a student with a smaller email and retest
    StudentRecord record4 = new StudentRecord("A", "a_student@gmail.com", 95);
    gradebook.addStudent(record4);

    // Testing getMin again with a new smallest email
    minRecord = gradebook.getMin();
    if (minRecord == null) {
      System.out.println("getMin test failed: Expected a student record, but got null.");
      allTestsPassed = false;
    } else if (!minRecord.email.equals("a_student@gmail.com")) {
      System.out
          .println("getMin test failed: Expected a_student@gmail.com, but got " + minRecord.email);
      allTestsPassed = false;
    }

    return allTestsPassed;
  }

  /**
   * Tests the removeStudent method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean removeStudentTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 90);
    StudentRecord record4 = new StudentRecord("Lebron James", "student4@gmail.com", 90);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record4);
    gradebook.addStudent(record3);

    // Manually constructed the expected BST structure
    BSTNode<StudentRecord> correctRoot = new BSTNode<>(record1);
    BSTNode<StudentRecord> correctNode2 = new BSTNode<>(record3);
    correctRoot.setRight(new BSTNode<>(record4));
    correctRoot.getRight().setLeft(correctNode2);

    // Testing removing a student
    gradebook.removeStudent("student2@gmail.com");
    // Checking if the removal was successful
    if (gradebook.lookup("student2@gmail.com") != null) {
      System.out.println("Removal test failed: student2@gmail.com was not removed.");
      allTestsPassed = false;
    }

    // Manually traversed the Gradebook and convert it to a BSTNode<StudentRecord> for comparison
    BSTNode<StudentRecord> actualRoot = null;
    // Implemented a function to manually traverse the `gradebook` and retrieve the root node
    // structure.

    // Comparing the gradebook BST with the expected BST
    if (!gradebook.equalBST(correctRoot)) {
      System.out.println("BST structure test failed after removing student2@gmail.com.");
      allTestsPassed = false;
    }

    return allTestsPassed;
  }


  /**
   * Tests the successor method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean successorTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord record1 = new StudentRecord("ABC", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 90);
    StudentRecord record4 = new StudentRecord("Lebron James", "student4@gmail.com", 65);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);
    gradebook.addStudent(record4);

    // Test successor method with record1
    StudentRecord actualSuccessor = gradebook.successor(record1);
    StudentRecord expectedSuccessor = record2;
    if (actualSuccessor == null || !actualSuccessor.email.equals(expectedSuccessor.email)) {
      System.out.println(
          "Successor test failed for " + record1.email + ": Expected " + expectedSuccessor.email
              + " but got " + (actualSuccessor != null ? actualSuccessor.email : "null"));
      allTestsPassed = false;
    }


    return allTestsPassed;
  }

  /**
   * Tests the iterator method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean iteratorTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 90);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);


    Iterator<StudentRecord> iterator = gradebook.iterator();

    // Creating a list to hold student records
    List<StudentRecord> records = new ArrayList<>();

    // Iterating through the gradebook
    while (iterator.hasNext()) {
      StudentRecord record = iterator.next();
      records.add(record);
    }

    // Checking if the records are in sorted order by email
    for (int i = 1; i < records.size(); i++) {
      if (records.get(i - 1).email.compareTo(records.get(i).email) > 0) {
        System.out.println("Iterator test failed: Records are not sorted by email.");
        allTestsPassed = false;
        break;
      }
    }

    return allTestsPassed;
  }

  /**
   * Tests the passingIterator method across various situations.
   * 
   * @return allTestsPassed True or False depending on the tests.
   */
  public static boolean passingIteratorTester() {
    boolean allTestsPassed = true;


    Gradebook gradebook = new Gradebook("Math340", 50.0);


    StudentRecord record1 = new StudentRecord("Naheel", "student1@gmail.com", 85);
    StudentRecord record2 = new StudentRecord("Undertaker", "student2@gmail.com", 75);
    StudentRecord record3 = new StudentRecord("Spiderman", "student3@gmail.com", 45);
    StudentRecord record4 = new StudentRecord("Lebron James", "student4@gmail.com", 60);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);
    gradebook.addStudent(record4);

    // Enabling the passing grade iterator
    gradebook.enablePassingGradeIterator();

    // Getting the passing iterator
    Iterator<StudentRecord> passingIterator = gradebook.iterator();

    // Listing of expected passing students
    List<String> expectedPassingEmails = new ArrayList<>(
        Arrays.asList("student1@gmail.com", "student2@gmail.com", "student4@gmail.com"));

    // Iterating through passing students and verify
    while (passingIterator.hasNext()) {
      StudentRecord record = passingIterator.next();

      // Checking if the email is in the list of expected passing emails
      if (!expectedPassingEmails.contains(record.email)) {
        System.out.println("Passing iterator test failed: Unexpected email found: " + record.email);
        allTestsPassed = false;
      } else {
        // Removing the email from the list since we found it in the iterator
        expectedPassingEmails.remove(record.email);
      }
    }

    // Checking if there are any expected emails not found in the iterator
    if (!expectedPassingEmails.isEmpty()) {
      System.out.println("Passing iterator test failed: Missing expected passing emails: "
          + expectedPassingEmails);
      allTestsPassed = false;
    }

    // Disabling the passing grade iterator to reset for other tests
    gradebook.disablePassingGradeIterator();

    return allTestsPassed;
  }



  public static void main(String[] args) {
    System.out.println("constructorTester: " + (constructorTester() ? "True" : "False"));
    System.out.println("toStringTester: " + (toStringTester() ? "True" : "False"));
    System.out.println("isEmptySizeAddTester: " + (isEmptySizeAddTester() ? "True" : "False"));
    System.out.println("prettyStringTester: " + (prettyStringTester() ? "True" : "False"));
    System.out.println("lookupTester: " + (lookupTester() ? "True" : "False"));
    System.out.println("getMinTester: " + (getMinTester() ? "True" : "False"));
    System.out.println("removeStudentTester: " + (removeStudentTester() ? "True" : "False"));
    System.out.println("successorTester: " + (successorTester() ? "True" : "False"));
    System.out.println("iteratorTester: " + (iteratorTester() ? "True" : "False"));
    System.out.println("passingIteratorTester: " + (passingIteratorTester() ? "True" : "False"));
  }


}
