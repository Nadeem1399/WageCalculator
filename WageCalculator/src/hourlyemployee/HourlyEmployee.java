package hourlyemployee;

import employee.Employee;
import payroll.Payroll;

//HourlyEmployee.java
public class HourlyEmployee extends Employee implements Payroll {
 public String name;
 public double hourlyWage;
 public double hoursWorked;

 // Constructor
 public HourlyEmployee(String name, double hourlyWage, double hoursWorked) {
     this.name = name;
     this.hourlyWage = hourlyWage;
     this.hoursWorked = hoursWorked;
 }

 // Generate payroll for hourly employee
 @Override
 public void generatePayroll() {
     grossPay = hourlyWage * hoursWorked;
     taxes = grossPay * 0.15;
     netPay = grossPay - taxes;
 }

 // toString method to display employee details
 @Override
 public String toString() {
     return "Hourly Employee: " + name + "\n" +
            "Hourly Wage: $" + hourlyWage + "\n" +
            "Hours Worked: " + hoursWorked + "\n" +
            "Gross Pay: $" + grossPay + "\n" +
            "Taxes: $" + taxes + "\n" +
            "Net Pay: $" + netPay;
 }
}
