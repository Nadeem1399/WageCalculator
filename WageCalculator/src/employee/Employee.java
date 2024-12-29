package employee;

//Employee.java
public abstract class Employee {
 public double grossPay;
 public double taxes;
 public double netPay;

 // Abstract method to be implemented by subclasses
 public abstract void generatePayroll();
}
