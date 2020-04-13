package ro.utcluj.assignment1.model;

import javax.persistence.*;

@Entity
public class MedicationIntervals {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medication_id")
    private Medication medication;
    private int intakeIntervals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public int getIntakeIntervals() {
        return intakeIntervals;
    }

    public void setIntakeIntervals(int intakeIntervals) {
        this.intakeIntervals = intakeIntervals;
    }

    @Override
    public String toString() {
        return "MedicationIntervals{" +
                "id=" + id +
                ", medication=" + medication +
                ", intakeIntervals=" + intakeIntervals +
                '}';
    }
}
