package payrollsystem;

import java.util.ArrayList;

public class Payroll {
    private ArrayList<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public double calculateSalary(Employee emp, int hoursWorked) {
        return emp.getHourlyRate() * hoursWorked;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
