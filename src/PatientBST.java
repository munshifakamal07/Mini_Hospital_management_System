public class PatientBST {
    private PatientNode root;

    // Average O(log n), worst-case O(n) when the tree becomes unbalanced.
    public boolean insertPatient(Patient patient) {
        if (root == null) {
            root = new PatientNode(patient);
            return true;
        }
        return insert(root, patient);
    }

    private boolean insert(PatientNode current, Patient patient) {
        if (patient.getPatientId() == current.patient.getPatientId()) {
            return false;
        }
        if (patient.getPatientId() < current.patient.getPatientId()) {
            if (current.left == null) {
                current.left = new PatientNode(patient);
                return true;
            }
            return insert(current.left, patient);
        }
        if (current.right == null) {
            current.right = new PatientNode(patient);
            return true;
        }
        return insert(current.right, patient);
    }

    // Average O(log n), worst-case O(n).
    public Patient searchPatient(int patientId) {
        PatientNode current = root;
        while (current != null) {
            if (patientId == current.patient.getPatientId()) return current.patient;
            current = patientId < current.patient.getPatientId() ? current.left : current.right;
        }
        return null;
    }

    // Average O(log n), worst-case O(n); two-child deletion uses the successor.
    public boolean deletePatient(int patientId) {
        if (searchPatient(patientId) == null) return false;
        root = delete(root, patientId);
        return true;
    }

    private PatientNode delete(PatientNode current, int patientId) {
        if (patientId < current.patient.getPatientId()) {
            current.left = delete(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = delete(current.right, patientId);
        } else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            PatientNode successor = findMinimum(current.right);
            current.patient = successor.patient;
            current.right = delete(current.right, successor.patient.getPatientId());
        }
        return current;
    }

    private PatientNode findMinimum(PatientNode current) {
        while (current.left != null) current = current.left;
        return current;
    }

    // O(n), because every node is visited once.
    public void displayPatientsInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        displayInOrder(root);
    }

    private void displayInOrder(PatientNode current) {
        if (current == null) return;
        displayInOrder(current.left);
        current.patient.display();
        System.out.println("------------------------------");
        displayInOrder(current.right);
    }
}