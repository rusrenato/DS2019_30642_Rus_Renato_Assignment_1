package ro.utcluj.assignment1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.assignment1.model.Medication;
import ro.utcluj.assignment1.service.MedicationService;

import java.util.List;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public ResponseEntity<List<Medication>> getMedications() {
        return new ResponseEntity<>(medicationService.getMedications(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addMedication(@RequestBody Medication medication) {
        medicationService.addMedication(medication);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedication(@PathVariable int id){
        medicationService.deleteMedication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
