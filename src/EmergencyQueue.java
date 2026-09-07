public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    // O(1): add directly at the rear.
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // O(1): remove directly from the front.
    public Patient dequeue() {
        if (front == null) return null;
        Patient patient = front.patient;
        front = front.next;
        if (front == null) rear = null;
        return patient;
    }

    public boolean isEmpty() { return front == null; }

    // O(n): visits every queued patient.
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        System.out.println("Emergency Queue:");
        while (current != null) {
            System.out.println(position + ". Patient ID: " + current.patient.getPatientId()
                    + " - " + current.patient.getPatientName());
            current = current.next;
            position++;
        }
    }
}