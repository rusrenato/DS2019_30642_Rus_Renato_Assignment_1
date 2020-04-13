package ro.utcluj.assignment1.service;

import ro.utcluj.assignment1.dto.MedicationIntervalsDTO;
import ro.utcluj.assignment1.model.MedicationIntervals;

import java.util.List;

public interface MedicationIntervalsService {

    void addMedicationIntervals(MedicationIntervalsDTO medicationIntervals);

    List<MedicationIntervals> getMedicationIntervals();
}
