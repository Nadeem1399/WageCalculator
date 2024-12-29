package wagecalculator;

import javax.swing.*;

import employee.Employee;
import hourlyemployee.HourlyEmployee;
import salaryemployee.SalaryEmployee;

import java.awt.*;
import java.util.ArrayList;

public class WageCalculator extends JFrame {
    private JComboBox<String> employeeTypeComboBox;
    private JTextField nameField, wageField, hoursField, salaryField;
    private JTextArea txtOutput;
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private JButton submitButton, showReportButton;

    public WageCalculator() {
        // Set up the frame
        setTitle("Wage Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Employee Type Selection
        employeeTypeComboBox = new JComboBox<>(new String[] { "Select", "Hourly", "Salary" });
        add(employeeTypeComboBox);

        // Name Input Field
        nameField = new JTextField(20);
        nameField.setEnabled(false); // Initially disabled until employee type is selected
        add(new JLabel("Name:"));
        add(nameField);

        // Hourly Wage and Hours Input Fields
        wageField = new JTextField(10);
        wageField.setEnabled(false);
        add(new JLabel("Hourly Wage:"));
        add(wageField);

        hoursField = new JTextField(10);
        hoursField.setEnabled(false);
        add(new JLabel("Hours Worked:"));
        add(hoursField);

        // Salary Input Field
        salaryField = new JTextField(10);
        salaryField.setEnabled(false);
        add(new JLabel("Salary:"));
        add(salaryField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        add(submitButton);

        // Output Text Area
        txtOutput = new JTextArea(10, 40);
        txtOutput.setEditable(false);
        add(new JScrollPane(txtOutput));

        // Show Report Button
        showReportButton = new JButton("Show Report");
        add(showReportButton);

        // Event Listeners
        employeeTypeComboBox.addActionListener(e -> toggleFields());
        submitButton.addActionListener(e -> submitEmployee());
        showReportButton.addActionListener(e -> showReport());
    }

    // Toggle fields based on employee type
    private void toggleFields() {
        String selectedType = (String) employeeTypeComboBox.getSelectedItem();
        
        // Always enable the name field
        nameField.setEnabled(true);

        if (selectedType.equals("Hourly")) {
            wageField.setEnabled(true);
            hoursField.setEnabled(true);
            salaryField.setEnabled(false);
            submitButton.setEnabled(true);
        } else if (selectedType.equals("Salary")) {
            wageField.setEnabled(false);
            hoursField.setEnabled(false);
            salaryField.setEnabled(true);
            submitButton.setEnabled(true);
        } else {
            // Disable all fields if 'Select' is chosen
            wageField.setEnabled(false);
            hoursField.setEnabled(false);
            salaryField.setEnabled(false);
            submitButton.setEnabled(false);
        }
    }

    // Validate and submit employee
    private void submitEmployee() {
        String name = nameField.getText();
        String employeeType = (String) employeeTypeComboBox.getSelectedItem();

        if (name.isEmpty() || employeeType.equals("Select") ||
            (employeeType.equals("Hourly") && (wageField.getText().isEmpty() || hoursField.getText().isEmpty())) ||
            (employeeType.equals("Salary") && salaryField.getText().isEmpty())) {

            JOptionPane.showMessageDialog(this, "Please fill out all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = null;
        if (employeeType.equals("Hourly")) {
            try {
                double wage = Double.parseDouble(wageField.getText());
                double hours = Double.parseDouble(hoursField.getText());
                employee = new HourlyEmployee(name, wage, hours);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Wage and hours must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (employeeType.equals("Salary")) {
            try {
                double salary = Double.parseDouble(salaryField.getText());
                employee = new SalaryEmployee(name, salary);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        employee.generatePayroll();
        employeeList.add(employee);

        txtOutput.setText(employee.toString());
    }

    // Show report with all employees
    private void showReport() {
        StringBuilder report = new StringBuilder();
        for (Employee emp : employeeList) {
            report.append(emp.toString()).append("\n\n");
        }
        txtOutput.setText(report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WageCalculator app = new WageCalculator();
            app.setVisible(true);
        });
    }
}
