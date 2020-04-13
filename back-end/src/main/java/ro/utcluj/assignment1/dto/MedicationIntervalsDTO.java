package ro.utcluj.assignment1.dto;

public class MedicationIntervalsDTO {
    private int medicationId;
    private int intakeIntervals;

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getIntakeIntervals() {
        return intakeIntervals;
    }

    public void setIntakeIntervals(int intakeIntervals) {
        this.intakeIntervals = intakeIntervals;
    }

    @Override
    public String toString() {
        return "MedicationIntervalsDTO{" +
                "medicationId=" + medicationId +
                ", intakeIntervals=" + intakeIntervals +
                '}';
    }
}
