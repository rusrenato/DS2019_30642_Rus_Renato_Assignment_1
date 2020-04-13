package ro.utcluj.assignment1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.assignment1.dto.Mapper;
import ro.utcluj.assignment1.dto.MedicationPlanDTOoutput;
import ro.utcluj.assignment1.dto.UserDTO;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.model.Role;
import ro.utcluj.assignment1.model.User;
import ro.utcluj.assignment1.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    Mapper mapper;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> insertPatient(@RequestBody UserDTO user) {
        System.out.println(user);
        userService.addPatient(mapper.inversMapForCaregiver(user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        userService.addPatient(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getPatient(@Valid @PathVariable("id") int id) {
        return new ResponseEntity<>(mapper.map(userService.getPatientById(id)), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers().stream().map(user -> mapper.map(user)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<UserDTO>> getPatients() {
        return new ResponseEntity<>(userService.getAllUsers().stream().map(user -> mapper.map(user)).filter(x -> x.getRol() == Role.Patient).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/caregivers")
    public ResponseEntity<List<UserDTO>> getCaregivers() {
        return new ResponseEntity<>(userService.getAllUsers().stream().map(user -> mapper.map(user)).filter(x -> x.getRol() == Role.Caregiver).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updatePatient(@RequestBody UserDTO user, @PathVariable("id") int id) {
        userService.update(id, mapper.reverseMap(user));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/logged")
    public ResponseEntity<UserDTO> getLoggedUser() {
        return new ResponseEntity<>(mapper.map(userService.getLoggedUser()), HttpStatus.OK);
    }

    @GetMapping("/caregiver/patients/{id}")
    public ResponseEntity<Set<UserDTO>> getCaregiversPatients(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getCaregiverPatientsList(id).stream().map(user -> mapper.map(user)).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/medicationPlan/{id}")
    public ResponseEntity<Set<MedicationPlanDTOoutput>> getUserMedicationPlan(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserMedicationPlans(id).stream().map(medicationPlan -> mapper.map(medicationPlan)).collect(Collectors.toSet()), HttpStatus.OK);
    }


}
