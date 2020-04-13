package ro.utcluj.assignment1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.assignment1.dto.MedicationIntervalsDTO;
import ro.utcluj.assignment1.dto.MedicationPlanDTO;
import ro.utcluj.assignment1.model.MedicationIntervals;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.repository.MedicationIntervalsRepository;
import ro.utcluj.assignment1.repository.MedicationPlanRepository;
import ro.utcluj.assignment1.repository.MedicationRepository;
import ro.utcluj.assignment1.service.MedicationPlanService;
import ro.utcluj.assignment1.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedicationPlanServiceImpl implements MedicationPlanService {

    @Autowired
    private MedicationPlanRepository medicationPlanRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MedicationIntervalsRepository medicationIntervalsRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public MedicationPlan getMedicationPlanById(int id) {
        MedicationPlan medicationPlan = medicationPlanRepository.getMedicationPlanById(id);
        return medicationPlan;
    }

    @Override
    public List<MedicationPlan> getMedicationPlans() {
        return medicationPlanRepository.findAll();
    }

    @Override
    public void addMedicationPlan(MedicationPlan medicationPlan) {
        medicationPlanRepository.save(medicationPlan);
    }

    @Override
    public void deleteMedicationPlan(int id) {
        MedicationPlan medicationPlan = medicationPlanRepository.getMedicationPlanById(id);
        medicationPlanRepository.delete(medicationPlan);
    }

    @Override
    public void updateMedicationPlan(int id, MedicationPlan medicationPlan) {

    }

    @Override
    public void addMedicationPlan(MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = new MedicationPlan();
        medicationPlan.setPeriodOfTreatment(medicationPlanDTO.getPeriodOfTreatment());
        medicationPlan.setPatient(userService.getPatientById(medicationPlanDTO.getUserId()));

        Set<MedicationIntervalsDTO> medicationIntervalsDTO = new HashSet<>(medicationPlanDTO.getMedicationIntervals());
        Set<MedicationIntervals> medicationIntervals = new HashSet<>();
        for(MedicationIntervalsDTO medicationIntervalsDTO1 : medicationIntervalsDTO) {
            medicationIntervals.add(convertMedicationIntervals(medicationIntervalsDTO1));
        }

        System.out.println(medicationIntervals);
//
//        System.out.println(medicationIntervalsRepository.findAll());
        medicationPlan.setMedications(medicationIntervals);
//        System.out.println(medicationPlan);

        medicationPlanRepository.save(medicationPlan);
    }

    private MedicationIntervals convertMedicationIntervals(MedicationIntervalsDTO medicationIntervalsDTO) {
        MedicationIntervals medicationIntervals = new MedicationIntervals();
        medicationIntervals.setMedication(medicationRepository.getMedicationById(medicationIntervalsDTO.getMedicationId()));
        System.out.println(medicationIntervalsDTO.getMedicationId());
        System.out.println(medicationRepository.getMedicationById(medicationIntervalsDTO.getMedicationId()));
        medicationIntervals.setIntakeIntervals(medicationIntervalsDTO.getIntakeIntervals());

        return medicationIntervals;
    }
}
