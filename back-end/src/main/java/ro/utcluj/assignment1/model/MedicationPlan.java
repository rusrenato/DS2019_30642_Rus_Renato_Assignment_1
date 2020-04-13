package ro.utcluj.assignment1.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MedicationPlan {
    @Id
    @GeneratedValue
    private int id;
    private int periodOfTreatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicationplan_medications",
            joinColumns = @JoinColumn(name = "medication_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_intervals_id"))
    private Set<MedicationIntervals> medications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Set<MedicationIntervals> getMedications() {
        return medications;
    }

    public void setMedications(Set<MedicationIntervals> medications) {
        this.medications = medications;
    }

    public int getPeriodOfTreatment() {
        return periodOfTreatment;
    }

    public void setPeriodOfTreatment(int periodOfTreatment) {
        this.periodOfTreatment = periodOfTreatment;
    }

    @Override
    public String toString() {
        return "MedicationPlan{" +
                "id=" + id +
                ", periodOfTreatment=" + periodOfTreatment +

                ", medications=" + medications +
                '}';
    }
}
