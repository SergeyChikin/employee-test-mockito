package pro.sky.employeetestmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.employeetestmockito.exceptions.DepartmentNotFoundException;
import pro.sky.employeetestmockito.model.Employee;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> showEmployeesInDepartment(int department) {
        return  employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public double getSumSalaryInDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee -> Employee.getSalary()).sum();

    }

    public double getMaxSalaryInDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee -> Employee.getSalary())
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double getMinSalaryInDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee -> Employee.getSalary())
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> showAllEmployeesByDepartments() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
