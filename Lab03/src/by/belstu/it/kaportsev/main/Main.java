package by.belstu.it.kaportsev.main;

import by.belstu.it.kaportsev.employee.*;
import by.belstu.it.kaportsev.employee.staff.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER;

    static {
        try {
            var fileHandler = new FileHandler("Lab03\\logManager.log");
            LOGGER = Logger.getLogger(Main.class.getName());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            LOGGER.info("Creating instance of type Director");
            Director director = new Director("John", 45, 3500);

            LOGGER.info("Adding 3 instances of type Engineer to the staff.");
            director.getStaff().add(new Engineer("Adam", 25, 700));
            director.getStaff().add(new Engineer("Friedrich", 19, 850));
            director.getStaff().add(new Engineer("Oliver", 19, 850));

            LOGGER.info("Adding 2 instances of type SysAdmin to the staff.");
            director.getStaff().add(new SysAdmin("William", 32, 1900));
            director.getStaff().add(new SysAdmin("Liam", 28, 1300));

            LOGGER.info("Adding 5 instances of type Programmer to the staff(2 juniors, 1 middle, 2 senior).");
            director.getStaff().add(new Programmer("Lucas", 22, 550, Programmer.Qualification.Junior));
            director.getStaff().add(new Programmer("Max", 18, 600, Programmer.Qualification.Junior));
            director.getStaff().add(new Programmer("Benjamin", 23, 600, Programmer.Qualification.Middle));
            director.getStaff().add(new Programmer("Artem", 30, 2700, Programmer.Qualification.Senior));
            director.getStaff().add(new Programmer("Mason", 22, 3200, Programmer.Qualification.Senior));

            LOGGER.info("Call to the method 'director.countStaff()'");
            System.out.println("Count of staff: " + director.countStaff());

            LOGGER.info("Call to the method 'director.sortStaffBySalary()'");
            director.sortStaffBySalary();

            LOGGER.info("Printing elements of staff array using foreach");
            for (Employee el: director.getStaff())
                System.out.println(el.toString());

            System.out.println("----------------------------------------------------");

            LOGGER.info("Call to the method 'director.getStaffWithSkill(EmployeeSkill.ProgrammerJunior)'" +
                    " and printing elements of staff array via foreach");
            for (Employee el: director.getStaffWithSkill(EmployeeSkill.ProgrammerJunior))
                System.out.println(el.toString());

            LOGGER.info("Starting Lab04");
            // Serialization & Deserialization via JSON.
            var sysAdmin = new SysAdmin("William", 32, 1900);
            sysAdmin.SerializeViaJson("Lab03\\files\\objects.json");
            var sysAdmin2 = SysAdmin.DeserializeViaJson("Lab03\\files\\objects.json", SysAdmin.class);
            System.out.println(sysAdmin2);

            // Stream API.
            var countEmployee = director.getStaff().stream().filter((el) -> el.getAge() > 20).count();
            System.out.println(countEmployee);
            var employee = director.getStaff().stream().
                    filter((el)-> el.getSalary() > 1000).min(new Director.SortByEmployees());
            System.out.println(employee);

            // Parsing XML.
            director.ParseXml();
            for (Employee el: director.getStaff())
                System.out.println(el.toString());
        }
        catch(Exception ex)
        {
            LOGGER.warning("An error occurred: "+ ex.getMessage());
        }
    }
}
