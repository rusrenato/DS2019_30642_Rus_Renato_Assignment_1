package ro.utcluj.assignment1.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcluj.assignment1.model.MedicationPlan;
import ro.utcluj.assignment1.model.User;
import ro.utcluj.assignment1.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class Mapper {

    @Autowired
    private UserService userService;

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(user.getBirthDate() != null)
        userDTO.setBirthDate(format.format(user.getBirthDate()));
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        if(user.getMedicalRecord() == null){
            userDTO.setMedicalRecord("Clean medical");
        } else {
            userDTO.setMedicalRecord(user.getMedicalRecord());
        }
        userDTO.setRol(user.getRol());
        if(user.getCareGiver() != null) {
            userDTO.setCaregiver(user.getCareGiver().getName());
        } else {
            userDTO.setCaregiver("-");
        }
        return userDTO;
    }

    public User inversMapForCaregiver(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setMedicalRecord(userDTO.getMedicalRecord());
        user.setRol(userDTO.getRol());
        user.setAddress(userDTO.getAddress());
        user.setGender(userDTO.getGender());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        try {
            user.setBirthDate(formatter.parse(userDTO.getBirthDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(userDTO.getCaregiver() != null) {
            user.setCareGiver(userService.getUserByEmail(userDTO.getCaregiver()));
        }
        System.out.println(user);
        return user;
    }

    public MedicationPlanDTOoutput map(MedicationPlan medicationPlan ){
        MedicationPlanDTOoutput medicationPlanDTOoutput = new MedicationPlanDTOoutput();
        medicationPlanDTOoutput.setId(medicationPlan.getId());
        medicationPlanDTOoutput.setMedications(medicationPlan.getMedications());
        medicationPlanDTOoutput.setPeriodOfTreatment(medicationPlan.getPeriodOfTreatment());
        medicationPlanDTOoutput.setUserName(medicationPlan.getPatient().getName());

        return medicationPlanDTOoutput;
    }

    public User reverseMap(UserDTO userDTO){
        System.out.println(userDTO);
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if(userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        user.setRol(userDTO.getRol());
        user.setAddress(userDTO.getAddress());
        user.setGender(userDTO.getGender());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        try {
            user.setBirthDate(formatter.parse(userDTO.getBirthDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(userDTO.getCaregiver() != null) {
            user.setCareGiver(userService.getUserByEmail(userDTO.getCaregiver()));
        }

        return user;
    }
}
