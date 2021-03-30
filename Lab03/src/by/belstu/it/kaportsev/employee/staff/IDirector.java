package by.belstu.it.kaportsev.employee.staff;

import by.belstu.it.kaportsev.employee.*;

import java.util.ArrayList;

public interface IDirector {
    int countStaff();
    void sortStaffBySalary();
    ArrayList<Employee> getStaffWithSkill(EmployeeSkill skill);
}
