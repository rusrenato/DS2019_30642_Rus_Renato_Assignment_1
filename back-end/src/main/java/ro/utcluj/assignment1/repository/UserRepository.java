package ro.utcluj.assignment1.repository;

import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.model.Role;
import ro.utcluj.assignment1.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends BaseRepository<User> {
    @Override
    List<User> findAll();
    User findById(int id);
    User findUserByEmail(String email);
    Set<User> findUsersByRolAndCareGiver(Role rol, User caregiver);

}
