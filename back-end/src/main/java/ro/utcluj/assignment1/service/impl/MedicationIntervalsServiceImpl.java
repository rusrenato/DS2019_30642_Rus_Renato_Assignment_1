package ro.utcluj.assignment1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.assignment1.dto.MedicationIntervalsDTO;
import ro.utcluj.assignment1.model.MedicationIntervals;
import ro.utcluj.assignment1.repository.MedicationIntervalsRepository;
import ro.utcluj.assignment1.repository.MedicationRepository;
import ro.utcluj.assignment1.service.MedicationIntervalsService;

import java.util.List;

@Service
public class MedicationIntervalsServiceImpl implements MedicationIntervalsService {
    @Autowired
    MedicationIntervalsRepository medicationIntervalsRepository;
    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public void addMedicationIntervals(MedicationIntervalsDTO medicationIntervalsDTO) {
        MedicationIntervals medicationIntervals = new MedicationIntervals();
        medicationIntervals.setMedication(medicationRepository.getMedicationById(medicationIntervalsDTO.getMedicationId()));
        System.out.println(medicationIntervalsDTO.getMedicationId());
        System.out.println(medicationRepository.getMedicationById(medicationIntervalsDTO.getMedicationId()));
        medicationIntervals.setIntakeIntervals(medicationIntervalsDTO.getIntakeIntervals());
        medicationIntervalsRepository.save(medicationIntervals);
    }

    @Override
    public List<MedicationIntervals> getMedicationIntervals() {
        return medicationIntervalsRepository.findAll();
    }
}
