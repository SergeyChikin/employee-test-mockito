package pro.sky.employeetestmockito;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeetestmockito.exceptions.DepartmentNotFoundException;
import pro.sky.employeetestmockito.model.Employee;
import pro.sky.employeetestmockito.service.DepartmentService;
import pro.sky.employeetestmockito.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> maxSalaryFromDepParams() {
        return Stream.of(
                Arguments.of(1,72_000 ),
                Arguments.of(2, 57_000),
                Arguments.of(3, 64_000)
        );
    }

    public static Stream<Arguments> minSalaryFromDepTestParams() {
        return Stream.of(
                Arguments.of(1, 70_000),
                Arguments.of(2, 55_000),
                Arguments.of(3, 62_000)
        );
    }

    public static Stream<Arguments> sumSalaryFromDepTestParams() {
        return Stream.of(
                Arguments.of(1, 142_000),
                Arguments.of(2, 112_000),
                Arguments.of(3, 126_000),
                Arguments.of(6, 0)
        );
    }

    public static Stream<Arguments> employeesFromDepTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Юлия", "Коряковцева",70000,1),
                                new Employee("Екатерина", "Перещук",72000,1)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Данил", "Багирян",55000,2),
                                new Employee("Василий", "Гудков",57000,2)
                        )
                ),
                Arguments.of(
                        3,
                        List.of(
                        new Employee("Любовь", "Дятлова",64000,3),
                        new Employee("Егор", "Орехов",62000,3)
                        )
                ),
                Arguments.of(
                        6,
                        Collections.emptyList()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("maxSalaryFromDepParams")
    public void maxSalaryFromDepTest(int department, double expected) {
        Assertions.assertThat(departmentService.getMaxSalaryInDepartment(department))
                .isEqualTo(expected);
    }
    @Test
    public void maxSalaryFromDepIfNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.getMaxSalaryInDepartment(6));
    }

    @ParameterizedTest
    @MethodSource("minSalaryFromDepTestParams")
    public void minSalaryFromDepTest(int department, double expected) {
        Assertions.assertThat(departmentService.getMinSalaryInDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void minSalaryFromDepIfNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.getMinSalaryInDepartment(6));
    }

    @ParameterizedTest
    @MethodSource("sumSalaryFromDepTestParams")
    public void sumSalaryFromDepTest(int department, double expected) {
        Assertions.assertThat(departmentService.getSumSalaryInDepartment(department))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepTestParams")
    public void employeesFromDepTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.showEmployeesInDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void employeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Юлия", "Коряковцева",70000,1),
                        new Employee("Екатерина", "Перещук",72000,1)
                ),
                2,
                List.of(
                        new Employee("Василий", "Гудков",57000,2),
                        new Employee("Данил", "Багирян",55000,2)
                ),
                3,
                List.of(
                        new Employee("Любовь", "Дятлова",64000,3),
                        new Employee("Егор", "Орехов",62000,3)
                ),
                4,
                List.of(
                        new Employee("Яна", "Валуйская",48000,4),
                        new Employee("Александр", "Жердев",52000,4)
                ),
                5,
                Collections.singletonList(new Employee("Михаил", "Низовский",47000,5))
        );
        Assertions.assertThat(departmentService.showAllEmployeesByDepartments())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

}
