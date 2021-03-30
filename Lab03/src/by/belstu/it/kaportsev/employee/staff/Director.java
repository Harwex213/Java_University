package by.belstu.it.kaportsev.employee.staff;

import java.util.*;
import by.belstu.it.kaportsev.employee.*;


public class Director extends Employee implements IDirector {
    private ArrayList<Employee> staff;

    static class SortByEmployees implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Float.compare(o1.getSalary(), o2.getSalary());
        }
    }

    public Director(String name, int age, float salary) throws Exception {
        super(name, age, salary);
        this.staff = new ArrayList<>();
    }

    public ArrayList<Employee> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Employee> staff) {
        this.staff = staff;
    }

    @Override
    public int countStaff() {
        return staff.size();
    }

    @Override
    public void sortStaffBySalary() {
        staff.sort(new SortByEmployees());
    }

    @Override
    public ArrayList<Employee> getStaffWithSkill(EmployeeSkill skill) {
        var resultArray = new ArrayList<Employee>();
        boolean typeIsCorrect;

        for (var employee:
             staff) {
            switch (skill)
            {
                case Engineer -> typeIsCorrect = employee instanceof Engineer;
                case SysAdmin -> typeIsCorrect = employee instanceof SysAdmin;
                case ProgrammerJunior -> typeIsCorrect = employee instanceof Programmer &&
                        ((Programmer) employee).CheckQualification(Programmer.Qualification.Junior);
                case ProgrammerMiddle -> typeIsCorrect = employee instanceof Programmer &&
                        ((Programmer) employee).CheckQualification(Programmer.Qualification.Middle);
                case ProgrammerSenior -> typeIsCorrect = employee instanceof Programmer &&
                        ((Programmer) employee).CheckQualification(Programmer.Qualification.Senior);
                default -> typeIsCorrect = false;
            }

            if (typeIsCorrect) {
                resultArray.add(employee);
            }
        }

        return resultArray;
    }
}