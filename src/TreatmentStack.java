public class TreatmentStack {
    private TreatmentNode top;

    // O(1): push at the top of the linked stack.
    public void push(TreatmentRecord record) {
        TreatmentNode newNode = new TreatmentNode(record);
        newNode.next = top;
        top = newNode;
    }

    // O(1): pop from the top of the stack.
    public TreatmentRecord pop() {
        if (top == null) return null;
        TreatmentRecord record = top.record;
        top = top.next;
        return record;
    }

    public boolean isEmpty() { return top == null; }

    // O(n): visits each record from newest to oldest.
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }
        TreatmentNode current = top;
        while (current != null) {
            current.record.display();
            System.out.println("------------------------------");
            current = current.next;
        }
    }
}