package ro.utcluj.assignment1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.assignment1.dto.Mapper;
import ro.utcluj.assignment1.dto.MedicationPlanDTO;
import ro.utcluj.assignment1.dto.MedicationPlanDTOoutput;
import ro.utcluj.assignment1.model.Medication;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.service.MedicationPlanService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicationPlan")
public class MedicationPlanController {

    @Autowired
    private MedicationPlanService medicationPlanService;
    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<MedicationPlanDTOoutput>> getMedicationPlans() {
        return new ResponseEntity<>(medicationPlanService.getMedicationPlans().stream().map(x-> mapper.map(x)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> addMedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO) {
        medicationPlanService.addMedicationPlan(medicationPlanDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
