package ro.utcluj.assignment1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Email
    @Column(unique = true)
    @Length(min=3)
    private String email;
    private String password;
    private Date birthDate;
    private String gender;
    private String address;
    private String medicalRecord;
    private Role rol;

    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL)
    private List<MedicationPlan> medicationPlanList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caregiver_id")
    private User careGiver;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public User getCareGiver() {
        return (this.rol == Role.Patient && careGiver != null) ? convert(careGiver) : careGiver;
    }

    public void setCareGiver(User careGiver) {
        this.careGiver = careGiver;
    }

    public List<MedicationPlan> getMedicationPlanList() {
        return medicationPlanList;
    }

    public void setMedicationPlanList(List<MedicationPlan> medicationPlanList) {
        this.medicationPlanList = medicationPlanList;
    }

    private User convert(User careGiver) {
        User newCaregiver = new User();
        newCaregiver.setId(careGiver.getId());
        newCaregiver.setName(careGiver.getName());
        newCaregiver.setBirthDate(careGiver.getBirthDate());
        newCaregiver.setGender(careGiver.getGender());
        newCaregiver.setAddress(careGiver.getAddress());
        newCaregiver.setRol(careGiver.getRol());
        newCaregiver.setMedicalRecord(careGiver.getMedicalRecord());
        return newCaregiver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", medicalRecord='" + medicalRecord + '\'' +
                ", rol=" + rol +

                ", medicationPlanList=" + medicationPlanList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
