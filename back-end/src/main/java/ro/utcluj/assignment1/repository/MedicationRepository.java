package ro.utcluj.assignment1.repository;

import ro.utcluj.assignment1.model.Medication;

import java.util.List;

public interface MedicationRepository extends BaseRepository<Medication> {
    @Override
    List<Medication> findAll();

    Medication getMedicationById(int id);
}
