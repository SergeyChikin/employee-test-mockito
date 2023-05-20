package pro.sky.employeetestmockito;
import java.util.stream.Stream;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.employeetestmockito.exceptions.EmployeeNotFoundException;
import pro.sky.employeetestmockito.exceptions.EmployeeStorageIsFullException;
import pro.sky.employeetestmockito.exceptions.InvalidInputException;
import pro.sky.employeetestmockito.model.Employee;
import pro.sky.employeetestmockito.service.EmployeeService;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void addBefore() {
        employeeService.addEmployee("Анастасия", "Водяная", 74000, 1);
        employeeService.addEmployee("Алексей", "Толстов", 64000, 2);
        employeeService.addEmployee("Фёдор", "Небондарчук", 54000, 3);
    }

    @Test
    public void addEmployeeTest() {
        int beforeAddCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Anastasiya", "Vodyanaya", 74000, 1);
        Assertions.assertThat(employeeService.addEmployee("Anastasiya", "Vodyanaya", 74000, 1))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeAddCount + 1);
        Assertions.assertThat(employeeService.findEmployee("Anastasiya", "Vodyanaya")).isEqualTo(expected);
    }

    @Test
    public void removeEmployeeTest() {
        int beforeCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Алексей", "Толстов", 64000, 2);

        Assertions.assertThat(employeeService.removeEmployee("Алексей", "Толстов"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Алексей", "Толстов"));
    }

    @Test
    public void removeIfNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Вася", "Пупкин"));
    }

    @Test
    public void findTest() {
        int beforeCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Анастасия", "Водяная", 74000, 1);

        Assertions.assertThat(employeeService.findEmployee("Анастасия", "Водяная"))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeCount);
    }

    @Test
    public void findIfNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Анаком", "Доширак"));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.getEmployees())
                .hasSize(12)
                .containsExactlyInAnyOrder(
                        new Employee("Данил", "Багирян",55000,2),
                        new Employee("Яна", "Валуйская",48000,4),
                        new Employee("Любовь", "Дятлова",64000,3),
                        new Employee("Василий", "Гудков",57000,2),
                        new Employee("Александр", "Жердев",52000,4),
                        new Employee("Юлия", "Коряковцева",70000,1),
                        new Employee("Михаил", "Низовский",47000,5),
                        new Employee("Егор", "Орехов",62000,3),
                        new Employee("Екатерина", "Перещук",72000,1),
                        new Employee("Анастасия", "Водяная", 74000, 1),
                        new Employee("Алексей", "Толстов", 64000, 2),
                        new Employee("Фёдор", "Небондарчук", 54000, 3)
                );
    }

    @ParameterizedTest
    @MethodSource("addWithNotValidateInputTestParams")
    public void addWithNotValidateInputTestParams(String incorrectLastName) {
        Assertions.assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.addEmployee(incorrectLastName, "Ivanov", 1, 10_0000));
    }

    public static Stream<Arguments> addWithNotValidateInputTestParams() {
        return Stream.of(
                Arguments.of("Иванов1"),
                Arguments.of("Иванов!"),
                Arguments.of("Иванов@")
        );
    }
}
