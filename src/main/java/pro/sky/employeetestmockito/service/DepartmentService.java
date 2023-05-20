package pro.sky.employeetestmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.employeetestmockito.exceptions.DepartmentNotFoundException;
import pro.sky.employeetestmockito.model.Employee;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class DepartmentService extends EmployeeService{

    public List<Employee> showEmployeesInDepartment(int department) {
        return  Collections.unmodifiableCollection(employees.values()).stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public double getSumSalaryInDepartment(int department) {
        return Collections.unmodifiableCollection(employees.values()).stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee -> (int) Employee.getSalary()).sum();

    }

    public double getMaxSalaryInDepartment(int department) {
        return Collections.unmodifiableCollection(employees.values()).stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee -> (int) Employee.getSalary()).max()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double getMinSalaryInDepartment(int department) {
        return Collections.unmodifiableCollection(employees.values()).stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee -> (int) Employee.getSalary()).min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> showAllEmployeesByDepartments() {
        HashMap<Integer, List<Employee>> map = new HashMap<>();
        List<Employee> employeeList = employees.values().stream().toList();
        for( Employee emp : employeeList){
            if (!map.containsKey(emp.getDepartment())){
                map.put(emp.getDepartment(), new ArrayList<>());
            }
            map.get(emp.getDepartment()).add(emp);
        }
        return map;
    }
}
