package ro.utcluj.assignment1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcluj.assignment1.exception.ResourceNotFoundException;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.model.Role;
import ro.utcluj.assignment1.model.User;
import ro.utcluj.assignment1.repository.UserRepository;
import ro.utcluj.assignment1.service.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getPatientById(int id) {
        User patient;
        patient = userRepository.findById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("This patient dose not exist");
        }
        return patient;
    }

    @Override
    public void addPatient(User patient) {
        try {
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            userRepository.save(patient);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Patient already exists", e);
        }
    }

    @Override
    public void delete(int id) {
        User patient = userRepository.findById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("This patient does not exists");
        }
        userRepository.delete(patient);
    }

    @Override
    public void update(int id, User patient) {
        if (userRepository.findById(id) == null) {
            throw new ResourceNotFoundException("This patient does not exists");
        }
        if(patient.getPassword() == null) {
            patient.setPassword(userRepository.findById(id).getPassword());
        }
        patient.setId(id);
        System.out.println("Last : " + patient);
        userRepository.save(patient);
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.findUserByEmail(username);
    }

    public User getLoggedUser() {
        User loggedUser = null;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getName());
        if (authentication.getName() != null) {
            loggedUser = this.userRepository.findUserByEmail(authentication.getName());
        }

        return loggedUser;
    }

    public Set<User> getCaregiverPatientsList(int caregiverId) {
        return userRepository.findUsersByRolAndCareGiver(Role.Patient, getPatientById(caregiverId));
    }

    @Override
    public Set<MedicationPlan> getUserMedicationPlans(int id) {
        return new HashSet<>(userRepository.findById(id).getMedicationPlanList());
    }
}
