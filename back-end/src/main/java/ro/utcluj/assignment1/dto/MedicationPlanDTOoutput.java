package ro.utcluj.assignment1.dto;

import ro.utcluj.assignment1.model.MedicationIntervals;

import java.util.List;
import java.util.Set;

public class MedicationPlanDTOoutput {
    private int id;
    private int periodOfTreatment;
    private String userName;
    private Set<MedicationIntervals> medications;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<MedicationIntervals> getMedications() {
        return medications;
    }

    public void setMedications(Set<MedicationIntervals> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "MedicationPlanDTOoutput{" +
                "id=" + id +
                ", periodOfTreatment=" + periodOfTreatment +
                ", userName='" + userName + '\'' +
                ", medications=" + medications +
                '}';
    }
}
