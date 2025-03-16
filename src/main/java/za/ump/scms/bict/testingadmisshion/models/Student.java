/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ump.scms.bict.testingadmisshion.models;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author mphep
 */
public class Student {
    private String studentNumber;
    private String status;
    private String id;
    private String firstName;
    private String surname;
    private LocalDate birthYear;
    private Map<Integer, Programme> programmeChoices = new HashMap<>();

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(LocalDate birthYear) {
        this.birthYear = birthYear;
    }

    public Map<Integer, Programme> getProgrammeChoices() {
        return programmeChoices;
    }

    public void setProgrammeChoices(Map<Integer, Programme> programmeChoices) {
        this.programmeChoices = programmeChoices;
    }
}
