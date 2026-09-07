import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("MINI HOSPITAL EMERGENCY MANAGEMENT");
        System.out.println("========================================");
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ", 1, 15);
            System.out.println();
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.displayPatientsInOrder();
                case 5 -> addToEmergencyQueue();
                case 6 -> treatNextPatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> completeTreatment();
                case 9 -> removeLatestTreatment();
                case 10 -> treatmentStack.displayStack();
                case 11 -> addPatientVisit();
                case 12 -> searchPatientVisit();
                case 13 -> removePatientVisit();
                case 14 -> displayPatientVisitHistory();
                case 15 -> running = false;
            }
            if (running) pause();
        }
        System.out.println("Thank you for using the hospital system.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients");
        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. Treat Next Emergency Patient");
        System.out.println("7. Display Emergency Queue");
        System.out.println("8. Complete Treatment");
        System.out.println("9. Remove Latest Treatment Record");
        System.out.println("10. Display Treatment History");
        System.out.println("11. Add Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Remove Patient Visit");
        System.out.println("14. Display Patient Visit History");
        System.out.println("15. Exit");
    }

    private static void registerPatient() {
        int id = readPositiveInt("Patient ID: ");
        String name = readRequired("Patient Name: ");
        int age = readPositiveInt("Age: ");
        String contact = readRequired("Contact Number: ");
        String condition = readRequired("Medical Condition: ");
        Patient patient = new Patient(id, name, age, contact, condition);
        if (patientBST.insertPatient(patient)) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("Duplicate Patient ID. Registration rejected.");
        }
    }

    private static void searchPatient() {
        Patient patient = findPatientByPrompt();
        if (patient == null) System.out.println("Patient not found.");
        else patient.display();
    }

    private static void deletePatient() {
        int id = readPositiveInt("Patient ID to delete: ");
        if (patientBST.deletePatient(id)) System.out.println("Patient deleted successfully.");
        else System.out.println("Patient not found.");
    }

    private static void addToEmergencyQueue() {
        Patient patient = findPatientByPrompt();
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println("Patient added to the emergency queue.");
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            System.out.println("Emergency queue is empty.");
            return;
        }
        System.out.println("Patient selected for treatment:");
        patient.display();
        System.out.println("Record the treatment details now.");
        String treatmentId = readRequired("Treatment ID: ");
        String doctor = readRequired("Doctor Name: ");
        String treatment = readRequired("Treatment: ");
        String date = readRequired("Treatment Date: ");
        TreatmentRecord record = new TreatmentRecord(treatmentId, patient.getPatientId(),
                patient.getPatientName(), doctor, treatment, date);
        treatmentStack.push(record);
        patient.getVisitHistory().addVisit(new Visit(treatmentId, date, doctor,
                patient.getMedicalCondition(), treatment));
        System.out.println("Treatment completed, saved to stack, and added to visit history.");
    }

    private static void completeTreatment() {
        // This option supports completing a treatment independently of the queue.
        Patient patient = findPatientByPrompt();
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String treatmentId = readRequired("Treatment ID: ");
        String doctor = readRequired("Doctor Name: ");
        String treatment = readRequired("Treatment: ");
        String date = readRequired("Treatment Date: ");
        treatmentStack.push(new TreatmentRecord(treatmentId, patient.getPatientId(),
                patient.getPatientName(), doctor, treatment, date));
        patient.getVisitHistory().addVisit(new Visit(treatmentId, date, doctor,
                patient.getMedicalCondition(), treatment));
        System.out.println("Treatment completed and recorded.");
    }

    private static void removeLatestTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record == null) System.out.println("Treatment history is empty.");
        else {
            System.out.println("Removed latest treatment record:");
            record.display();
        }
    }

    private static void addPatientVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String visitId = readRequired("Visit ID: ");
        if (patient.getVisitHistory().searchVisit(visitId) != null) {
            System.out.println("Duplicate Visit ID. Visit not added.");
            return;
        }
        String date = readRequired("Visit Date: ");
        String doctor = readRequired("Doctor Name: ");
        String diagnosis = readRequired("Diagnosis: ");
        String treatment = readRequired("Treatment: ");
        patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
        System.out.println("Visit added successfully.");
    }

    private static void searchPatientVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String visitId = readRequired("Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        if (visit == null) System.out.println("Visit not found.");
        else visit.display();
    }

    private static void removePatientVisit() {
        Patient patient = findPatientByPrompt();
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String visitId = readRequired("Visit ID to remove: ");
        if (patient.getVisitHistory().removeVisit(visitId)) System.out.println("Visit removed successfully.");
        else System.out.println("Visit not found.");
    }

    private static void displayPatientVisitHistory() {
        Patient patient = findPatientByPrompt();
        if (patient == null) System.out.println("Patient not found.");
        else {
            System.out.println("Visit History for " + patient.getPatientName() + ":");
            patient.getVisitHistory().displayVisitHistory();
        }
    }

    private static Patient findPatientByPrompt() {
        int id = readPositiveInt("Patient ID: ");
        return patientBST.searchPatient(id);
    }

    private static String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            String value = readRequired(prompt);
            try {
                int number = Integer.parseInt(value);
                if (number > 0) return number;
            } catch (NumberFormatException ignored) {
                // The loop displays a friendly validation message below.
            }
            System.out.println("Please enter a positive whole number.");
        }
    }

    private static int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            String value = readRequired(prompt);
            try {
                int number = Integer.parseInt(value);
                if (number >= minimum && number <= maximum) return number;
            } catch (NumberFormatException ignored) {
                // The loop displays a friendly validation message below.
            }
            System.out.println("Please enter a number from " + minimum + " to " + maximum + ".");
        }
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}