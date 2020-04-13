package ro.utcluj.assignment1.service;

import ro.utcluj.assignment1.dto.UserDTO;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getPatientById(int id);

    List<User> getAllUsers();

    void addPatient(User patient);

    void delete(int id);

    void update(int id, User patient);

    User getUserByEmail(String username);

    User getLoggedUser();

    Set<User> getCaregiverPatientsList(int caregiverIid);

    Set<MedicationPlan> getUserMedicationPlans(int id);
}

