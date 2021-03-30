package by.belstu.it.kaportsev.employee.staff;

import by.belstu.it.kaportsev.employee.Employee;

public class Programmer extends Employee {
    public enum Qualification {
        Junior, Middle, Senior
    }

    private Qualification qualification;

    public Programmer(String name, int age, float salary, Qualification qualification) throws Exception {
        super(name, age, salary);
        this.qualification = qualification;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public boolean CheckQualification(Qualification qualification) {
        return this.qualification == qualification;
    }
}
