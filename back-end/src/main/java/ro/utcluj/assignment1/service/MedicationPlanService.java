package ro.utcluj.assignment1.service;

import ro.utcluj.assignment1.dto.MedicationPlanDTO;
import ro.utcluj.assignment1.model.Medication;
import ro.utcluj.assignment1.model.MedicationPlan;

import java.util.List;

public interface MedicationPlanService {
    MedicationPlan getMedicationPlanById(int id);

    List<MedicationPlan> getMedicationPlans();

    void addMedicationPlan(MedicationPlan medicationPlan);

    void addMedicationPlan(MedicationPlanDTO medicationPlanDTO);

    void deleteMedicationPlan(int id);

    void updateMedicationPlan(int id, MedicationPlan medicationPlan);

}
