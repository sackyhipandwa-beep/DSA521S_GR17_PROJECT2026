// Student.java
class Student {
    private String studentNo;
    private String name;
    private String serviceType;
    private int estimatedServiceTime;

    public Student(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNo() { return studentNo; }
    public String getName() { return name; }
    public String getServiceType() { return serviceType; }
    public int getEstimatedServiceTime() { return estimatedServiceTime; }

    @Override
    public String toString() {
        return "[" + studentNo + " | " + name + " | " + serviceType + " | " + estimatedServiceTime + " mins]";
    }
}

// Queue Node
class Node {
    Student student;
    Node next;

    public Node(Student student) {
        this.student = student;
        this.next = null;
    }
}

// Custom Queue Implementation
class StudentQueue {
    private Node front;
    private Node rear;

    public StudentQueue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Student student) {
        Node newNode = new Node(student);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Enqueued: " + student.getName());
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! No students to serve.");
            return null;
        }
        Student dequeuedStudent = front.student;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return dequeuedStudent;
    }

    public Student peek() {
        if (isEmpty()) {
            return null;
        }
        return front.student;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("\n--- Current Waiting Queue (Front to Rear) ---");
        Node current = front;
        int pos = 1;
        while (current != null) {
            System.out.println(pos + ". " + current.student);
            current = current.next;
            pos++;
        }
        System.out.println("--------------------------------------------\n");
    }
}

// Main Class matching the file name TaskA1.java
public class TaskA1 {
    public static void main(String[] args) {
        StudentQueue queue = new StudentQueue();

        System.out.println("--- Enqueuing 6 Students ---");
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("224011223", "Selma", "Academic Enquiry", 10));
        queue.enqueue(new Student("224099887", "Lukas", "Registration Assistance", 15));

        // Display queue after 6 arrivals
        queue.displayQueue();

        System.out.println("--- Serving (Dequeuing) 3 Students ---");
        for (int i = 1; i <= 3; i++) {
            Student served = queue.dequeue();
            if (served != null) {
                System.out.println("Served Student " + i + ": " + served.getName() + " (" + served.getServiceType() + ")");
            }
        }

        // Display queue after 3 services
        queue.displayQueue();
    }
}