package by.belstu.it.kaportsev.employee.staff;

import by.belstu.it.kaportsev.employee.Employee;
import by.belstu.it.kaportsev.xmlParsing.StaxManager;
import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Engineer extends Employee {
    public Engineer(String name, int age, float salary) throws Exception {
        super(name, age, salary);
    }
}
