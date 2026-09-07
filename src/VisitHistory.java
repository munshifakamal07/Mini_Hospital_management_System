public class VisitHistory {
    private VisitNode head;

    // O(1): add at the beginning of the singly linked list.
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        newNode.next = head;
        head = newNode;
    }

    // O(n): search by visit ID and unlink the matching node.
    public boolean removeVisit(String visitId) {
        VisitNode current = head;
        VisitNode previous = null;
        while (current != null) {
            if (current.visit.getVisitId().equalsIgnoreCase(visitId)) {
                if (previous == null) head = current.next;
                else previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // O(n): visit IDs are not sorted.
    public Visit searchVisit(String visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId().equalsIgnoreCase(visitId)) return current.visit;
            current = current.next;
        }
        return null;
    }

    // O(n): display every visit.
    public void displayVisitHistory() {
        if (head == null) {
            System.out.println("No visits found for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            current.visit.display();
            System.out.println("------------------------------");
            current = current.next;
        }
    }
}