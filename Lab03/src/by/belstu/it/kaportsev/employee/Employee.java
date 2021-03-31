package by.belstu.it.kaportsev.employee;

import by.belstu.it.kaportsev.xmlParsing.StaxManager;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Employee {
    private String name;
    private int age;
    private float salary;

    public Employee() { }

    public Employee(String name, int age, float salary) throws Exception
    {
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

    public void SerializeViaJson() throws IOException {
        var outputStream = new FileOutputStream("Lab03\\files\\objects.json");
        var bytesOutput = JSON.toJSONBytes(this);
        outputStream.write(bytesOutput);
    }

    public static Employee DeserializeViaJson(Type clazz) throws IOException {
        var inputStream = new FileInputStream("Lab03\\files\\objects.json");
        var bytesInput = inputStream.readAllBytes();
        return JSON.parseObject(bytesInput, clazz);
    }

    @Override
    public String toString() {
        return this.getClass() + ":: name: " + this.name + ", age: " + this.age + ", salary: " + this.salary + '.';
    }
}