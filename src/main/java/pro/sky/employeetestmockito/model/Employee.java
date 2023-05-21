package pro.sky.employeetestmockito.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastname;
    private double salary;
    private int department;

    public Employee(String firstName, String lastname, double salary, int department) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(this == null || this.getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastname, employee.lastname) &&
                Double.compare(salary, employee.salary) == 0 && department == employee.department;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastname, salary, department);
    }

    @Override
    public String toString() {
        return "Имя - " + firstName + " ," + " Фамилия - " + lastname + " ;";
    }

}
