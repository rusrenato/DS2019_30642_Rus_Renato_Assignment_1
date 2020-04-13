package ro.utcluj.assignment1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.assignment1.dto.MedicationIntervalsDTO;
import ro.utcluj.assignment1.model.MedicationIntervals;
import ro.utcluj.assignment1.service.MedicationIntervalsService;

import java.util.List;

@RestController
@RequestMapping("/medicationIntervals")
public class MedicationIntervalsController {
    @Autowired
    MedicationIntervalsService medicationIntervalsService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> addMedicationsIntervals(@RequestBody MedicationIntervalsDTO medicationIntervals) {
        System.out.println(medicationIntervals);
        medicationIntervalsService.addMedicationIntervals(medicationIntervals);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicationIntervals>> getMedicationIntervals() {
        return new ResponseEntity<>(medicationIntervalsService.getMedicationIntervals(), HttpStatus.OK);
    }
}
