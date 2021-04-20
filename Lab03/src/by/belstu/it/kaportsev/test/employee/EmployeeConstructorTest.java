package by.belstu.it.kaportsev.test.employee;

import by.belstu.it.kaportsev.employee.Employee;
import by.belstu.it.kaportsev.employee.staff.SysAdmin;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Test;

public class EmployeeConstructorTest {
    @BeforeClass
    public static void setUpAll() {
        System.out.println("@BeforeClass");
    }
    @Before
    public void setUp() {
        System.out.println("@Before");
    }

    @Test(timeout = 100)
    public void SerializationMustPassedInMax100Ms() throws Exception {
        Employee employeeTest = new SysAdmin("Olezhik", 18, 100);

        employeeTest.SerializeViaJson("objectsTest.json");
    }

    @Test (expected = Exception.class)
    public void DeserializationNonEmployeeShouldThrowException() throws Exception {
        Employee.DeserializeViaJson("objectsTest.json", Object.class);
    }

    @Test (expected = Exception.class)
    public void newEmployeeWithoutNameShouldThrowException() throws Exception {
        Employee employee = new SysAdmin("", 18, 100);
    }

    @Test (expected = Exception.class)
    public void newEmployeeWithAgeLess18ShouldThrowException() throws Exception {
        Employee employee = new SysAdmin("Oleg", 17, 100);
    }

    @After
    public void tearDown() {
        System.out.println("@After");
    }
    @AfterClass
    public static void tearDownAll() {
        System.out.println("@AfterClass");
    }
}
