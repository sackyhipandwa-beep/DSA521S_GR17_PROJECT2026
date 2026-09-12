// TaskA2.java
class StudentRecord {
    String studentNo;
    String name;
    String serviceType;
    int estimatedServiceTime;

    public StudentRecord(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    @Override
    public String toString() {
        return "[" + studentNo + " | " + name + " | " + serviceType + " | " + estimatedServiceTime + " mins]";
    }
}

class ListNode {
    StudentRecord record;
    ListNode next;

    public ListNode(StudentRecord record) {
        this.record = record;
        this.next = null;
    }
}

class StudentLinkedList {
    private ListNode head;

    public StudentLinkedList() {
        this.head = null;
    }

    // Insert student at specific position (1-based index)
    public void insertStudent(StudentRecord record, int position) {
        ListNode newNode = new ListNode(record);
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            System.out.println("Inserted at position 1: " + record.name);
            return;
        }

        ListNode current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Position out of bounds!");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
        System.out.println("Inserted at position " + position + ": " + record.name);
    }

    // Delete student by Student Number
    public boolean deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty!");
            return false;
        }

        if (head.record.studentNo.equals(studentNo)) {
            System.out.println("Deleted record: " + head.record.name);
            head = head.next;
            return true;
        }

        ListNode current = head;
        while (current.next != null && !current.next.record.studentNo.equals(studentNo)) {
            current = current.next;
        }

        if (current.next != null) {
            System.out.println("Deleted record: " + current.next.record.name);
            current.next = current.next.next;
            return true;
        }

        System.out.println("Student record with ID " + studentNo + " not found.");
        return false;
    }

    // Search student by Student Number
    public StudentRecord searchStudent(String studentNo) {
        ListNode current = head;
        while (current != null) {
            if (current.record.studentNo.equals(studentNo)) {
                return current.record;
            }
            current = current.next;
        }
        return null;
    }

    // Display all students in list
    public void displayStudents() {
        if (head == null) {
            System.out.println("No service records found.");
            return;
        }

        System.out.println("\n--- Student Service Records (Singly Linked List) ---");
        ListNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.record);
            current = current.next;
            index++;
        }
        System.out.println("---------------------------------------------------\n");
    }
}

public class TaskA2 {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        System.out.println("--- Testing Linked List Operations ---");
        
        // Insert records
        list.insertStudent(new StudentRecord("221045678", "Maria", "Registration", 12), 1);
        list.insertStudent(new StudentRecord("222034512", "Tomas", "Student Card", 5), 2);
        list.insertStudent(new StudentRecord("223041876", "Ndapewa", "Fees", 8), 2);
        list.insertStudent(new StudentRecord("221067341", "Simon", "Documents", 4), 4);

        // Display current records
        list.displayStudents();

        // Search operation
        System.out.println("--- Searching for Student 223041876 ---");
        StudentRecord found = list.searchStudent("223041876");
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Record Not Found!");
        }

        // Delete operation
        System.out.println("\n--- Deleting Student 223041876 (Ndapewa) ---");
        list.deleteStudent("223041876");

        // Display list after deletion
        list.displayStudents();
    }
}