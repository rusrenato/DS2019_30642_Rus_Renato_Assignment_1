package ro.utcluj.assignment1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.assignment1.model.Medication;
import ro.utcluj.assignment1.repository.MedicationRepository;
import ro.utcluj.assignment1.service.MedicationService;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Medication getMedicationById(int id) {
        Medication medication = medicationRepository.getMedicationById(id);
        return medication;
    }

    @Override
    public List<Medication> getMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public void addMedication(Medication medication) {
        medicationRepository.save(medication);
    }

    @Override
    public void deleteMedication(int id) {
        Medication medication = medicationRepository.getMedicationById(id);
        medicationRepository.delete(medication);
    }

    @Override
    public void updateMedication(int id, Medication medication) {

    }
}
