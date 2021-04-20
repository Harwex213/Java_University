package by.belstu.it.kaportsev.employee;

import by.belstu.it.kaportsev.employee.staff.Director;
import by.belstu.it.kaportsev.employee.staff.Engineer;
import by.belstu.it.kaportsev.employee.staff.Programmer;
import by.belstu.it.kaportsev.employee.staff.SysAdmin;
import com.alibaba.fastjson.JSON;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Properties;

public abstract class Employee {
    private String name;
    private int age;
    private float salary;

    public Employee() { }

    public Employee(String name, int age, float salary) throws Exception {
        if(name == null || name.equals(""))
            throw new Exception("Un correct name");

        if(age < 18)
            throw new Exception("person " + name + " can't work with us, because of his age(less then 18)");

        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void SerializeViaJson(String path) throws IOException {
        var outputStream = new FileOutputStream(path);
        var bytesOutput = JSON.toJSONBytes(this);
        outputStream.write(bytesOutput);
    }

    public static Employee DeserializeViaJson(String path, Type clazz) throws Exception {
        var inputStream = new FileInputStream(path);
        var bytesInput = inputStream.readAllBytes();
        var returnObject = JSON.parseObject(bytesInput, clazz);
        if (returnObject instanceof Employee)
            return JSON.parseObject(bytesInput, clazz);
        else
            throw new Exception("Incorrect Type in Deserialization");
    }

    @Override
    public String toString() {
        return this.getClass() + ":: name: " + this.name + ", age: " + this.age + ", salary: " + this.salary + '.';
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}