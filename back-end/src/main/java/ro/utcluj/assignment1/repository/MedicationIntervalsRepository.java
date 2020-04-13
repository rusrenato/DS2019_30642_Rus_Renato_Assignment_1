package ro.utcluj.assignment1.repository;

import ro.utcluj.assignment1.model.MedicationIntervals;

import java.util.List;

public interface MedicationIntervalsRepository extends BaseRepository<MedicationIntervals> {
    @Override
    List<MedicationIntervals> findAll();

}
