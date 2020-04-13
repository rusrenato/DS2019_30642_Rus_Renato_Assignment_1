package ro.utcluj.assignment1.service;

import ro.utcluj.assignment1.model.Medication;

import java.util.List;

public interface MedicationService {
    Medication getMedicationById(int id);

    List<Medication> getMedications();

    void addMedication(Medication medication);

    void deleteMedication(int id);

    void updateMedication(int id, Medication medication);
}
