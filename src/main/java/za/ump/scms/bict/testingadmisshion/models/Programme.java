/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ump.scms.bict.testingadmisshion.models;

/**
 *
 * @author mphep
 */
public class Programme {
    String programmeId;
    String programmeName;
    int yearsToComplete;
    String programmeFaculty;
    int requiredAPS;
    boolean MathsRequired; 

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public int getYearsToComplete() {
        return yearsToComplete;
    }

    public void setYearsToComplete(int yearsToComplete) {
        this.yearsToComplete = yearsToComplete;
    }

    public String getProgrammeFaculty() {
        return programmeFaculty;
    }

    public void setProgrammeFaculty(String programmeFaculty) {
        this.programmeFaculty = programmeFaculty;
    }

    public int getRequiredAPS() {
        return requiredAPS;
    }

    public void setRequiredAPS(int requiredAPS) {
        this.requiredAPS = requiredAPS;
    }

    public boolean isMathsRequired() {
        return MathsRequired;
    }

    public void setMathsRequired(boolean MathsRequired) {
        this.MathsRequired = MathsRequired;
    }
}
