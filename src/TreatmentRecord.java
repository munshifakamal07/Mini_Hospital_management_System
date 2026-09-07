public class TreatmentRecord {
    private final String treatmentId;
    private final int patientId;
    private final String patientName;
    private final String doctorName;
    private final String treatment;
    private final String treatmentDate;

    public TreatmentRecord(String treatmentId, int patientId, String patientName,
                           String doctorName, String treatment, String treatmentDate) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatment = treatment;
        this.treatmentDate = treatmentDate;
    }

    public String getTreatmentId() { return treatmentId; }
    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public String getTreatment() { return treatment; }
    public String getTreatmentDate() { return treatmentDate; }

    public void display() {
        System.out.println("Treatment ID: " + treatmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Doctor: " + doctorName);
        System.out.println("Treatment: " + treatment);
        System.out.println("Date: " + treatmentDate);
    }
}