package by.belstu.it.kaportsev.employee.staff;

import java.io.*;
import java.util.*;
import by.belstu.it.kaportsev.employee.*;
import by.belstu.it.kaportsev.xmlParsing.StaxManager;
import com.alibaba.fastjson.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Director extends Employee implements IDirector {
    private ArrayList<Employee> staff;

    public static class SortByEmployees implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Float.compare(o1.getSalary(), o2.getSalary());
        }
    }

    public Director() {
        super();
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

    public void ParseXml() {
        try {
            staff = new ArrayList<>();
            staff.addAll(ParseEngineer());
            staff.addAll(ParseSysAdmin());
            staff.addAll(ParseProgrammer());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private ArrayList<Employee> ParseEngineer() throws Exception {
        var employees = new ArrayList<Employee>();
        var staxManager = new StaxManager(new FileInputStream("Lab03\\files\\xmlObjects.xml"));
        while (staxManager.startElement("Engineer", null)) {
            employees.add(
                    new Engineer(staxManager.getAttribute("name"),
                    Integer.parseInt(staxManager.getAttribute("age")),
                    Float.parseFloat(staxManager.getAttribute("salary"))));
        }
        return employees;
    }

    private ArrayList<Employee> ParseSysAdmin() throws Exception {
        var employees = new ArrayList<Employee>();
        var staxManager = new StaxManager(new FileInputStream("Lab03\\files\\xmlObjects.xml"));
        while (staxManager.startElement("SysAdmin", null)) {
            employees.add(
                    new SysAdmin(staxManager.getAttribute("name"),
                    Integer.parseInt(staxManager.getAttribute("age")),
                    Float.parseFloat(staxManager.getAttribute("salary"))));
        }
        return employees;
    }

    private ArrayList<Employee> ParseProgrammer() throws Exception {
        var employees = new ArrayList<Employee>();
        var staxManager = new StaxManager(new FileInputStream("Lab03\\files\\xmlObjects.xml"));
        while (staxManager.startElement("Programmer", null)) {

            employees.add(new Programmer(
                    staxManager.getAttribute("name"),
                    Integer.parseInt(staxManager.getAttribute("age")),
                    Float.parseFloat(staxManager.getAttribute("salary")),
                    Programmer.Qualification.TakeQualification(staxManager.getAttribute("qualification"))));
        }
        return employees;
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