package by.belstu.it.kaportsev.test.employee;

import by.belstu.it.kaportsev.employee.Employee;
import by.belstu.it.kaportsev.employee.staff.Director;
import by.belstu.it.kaportsev.employee.staff.Engineer;
import by.belstu.it.kaportsev.employee.staff.Programmer;
import by.belstu.it.kaportsev.employee.staff.SysAdmin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class EmployeeSerializationTest {
    @Parameterized.Parameters (name = "{index}: serialization of {0} = it's deserialization}")
    public static List<Object> employees() throws Exception {
        return Arrays.asList(new Object[] {
                new Engineer("Adam", 25, 700),
                new SysAdmin("William", 32, 1900),
                new Programmer("Lucas", 22, 550, Programmer.Qualification.Junior),
                new Director("John", 45, 3500)
        });
    }

    @Parameterized.Parameter()
    public Object expected;
    public Object actual;

    @Test
    public void SerializationAndDeserializationSameObjMustBeSimilar() throws Exception {
        ((Employee)expected).SerializeViaJson("objectsTest.json");
        actual = Employee.DeserializeViaJson("objectsTest.json", expected.getClass());

        Assert.assertEquals(expected.hashCode(), actual.hashCode());
    }
}
