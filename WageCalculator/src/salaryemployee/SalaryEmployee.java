package salaryemployee;

import employee.Employee;
import payroll.Payroll;

//SalaryEmployee.java
public class SalaryEmployee extends Employee implements Payroll {
 public String name;
 public double salary;

 // Constructor
 public SalaryEmployee(String name, double salary) {
     this.name = name;
     this.salary = salary;
 }

 // Generate payroll for salaried employee
 @Override
 public void generatePayroll() {
     double weeklySalary = salary / 26;
     grossPay = weeklySalary;
     taxes = grossPay * 0.15;
     netPay = grossPay - taxes;
 }

 // toString method to display employee details
 @Override
 public String toString() {
     return "Salary Employee: " + name + "\n" +
            "Salary: $" + salary + "\n" +
            "Gross Pay: $" + grossPay + "\n" +
            "Taxes: $" + taxes + "\n" +
            "Net Pay: $" + netPay;
 }
}
