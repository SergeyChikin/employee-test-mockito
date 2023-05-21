package pro.sky.employeetestmockito.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeetestmockito.model.Employee;
import pro.sky.employeetestmockito.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@RestController
@RequestMapping(path = "/department")


public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> showEmployeesInDepartment(@PathVariable(required = false) int id) {
         return departmentService.showEmployeesInDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public Double getSumSalaryInDep(@PathVariable(required = false) int id) {
        Double sum = Double.valueOf(departmentService.getSumSalaryInDepartment(id));
        return sum;
    }

    @GetMapping(path = "/{id}/salary/max")
    public Object getMaxSalaryInDep(@PathVariable(required = false) int id) {
        Object max = departmentService.getMaxSalaryInDepartment(id);
        return max;
    }

    @GetMapping(path = "/{id}/salary/min")
    public Object getMinSalaryInDep(@PathVariable(required = false) int id) {
        Object min = departmentService.getMinSalaryInDepartment(id);
        return min;
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> showAllEmployeesByDeps() {
        return departmentService.showAllEmployeesByDepartments();
    }
}
