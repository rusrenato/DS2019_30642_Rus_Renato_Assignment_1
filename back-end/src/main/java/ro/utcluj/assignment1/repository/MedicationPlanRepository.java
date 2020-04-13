package ro.utcluj.assignment1.repository;

import com.fasterxml.jackson.databind.ser.Serializers;
import ro.utcluj.assignment1.model.MedicationPlan;

import java.util.List;

public interface MedicationPlanRepository extends BaseRepository<MedicationPlan> {
    @Override
    List<MedicationPlan> findAll();

    MedicationPlan getMedicationPlanById(int id);
}
