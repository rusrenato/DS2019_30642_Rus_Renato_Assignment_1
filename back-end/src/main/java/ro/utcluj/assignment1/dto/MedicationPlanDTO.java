package ro.utcluj.assignment1.dto;

import ro.utcluj.assignment1.model.MedicationIntervals;

import java.util.List;

public class MedicationPlanDTO {
    private int id;
    private int periodOfTreatment;
    private int userId;
    private List<MedicationIntervalsDTO> medicationIntervals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodOfTreatment() {
        return periodOfTreatment;
    }

    public void setPeriodOfTreatment(int periodOfTreatment) {
        this.periodOfTreatment = periodOfTreatment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<MedicationIntervalsDTO> getMedicationIntervals() {
        return medicationIntervals;
    }

    public void setMedicationIntervals(List<MedicationIntervalsDTO> medicationIntervals) {
        this.medicationIntervals = medicationIntervals;
    }

    @Override
    public String toString() {
        return "MedicationPlanDTO{" +
                "id=" + id +
                ", periodOfTreatment=" + periodOfTreatment +
                ", userId=" + userId +
                ", medicationIntervals=" + medicationIntervals +
                '}';
    }
}
