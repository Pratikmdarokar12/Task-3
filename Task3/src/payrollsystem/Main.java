package payrollsystem;

import javax.swing.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        Payroll payroll = new Payroll();
        new PayrollSystemGUI(payroll);
    }
}

class PayrollSystemGUI {
    private Payroll payroll;

    public PayrollSystemGUI(Payroll payroll) {
        this.payroll = payroll;
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        JLabel rateLabel = new JLabel("Hourly Rate:");
        rateLabel.setBounds(10, 50, 80, 25);
        panel.add(rateLabel);

        JTextField rateText = new JTextField(20);
        rateText.setBounds(100, 50, 165, 25);
        panel.add(rateText);

        JLabel hoursLabel = new JLabel("Hours Worked:");
        hoursLabel.setBounds(10, 80, 100, 25);
        panel.add(hoursLabel);

        JTextField hoursText = new JTextField(20);
        hoursText.setBounds(100, 80, 165, 25);
        panel.add(hoursText);

        JButton addButton = new JButton("Add Employee");
        addButton.setBounds(10, 110, 150, 25);
        panel.add(addButton);

        JButton generateButton = new JButton("Generate Pay Stub");
        generateButton.setBounds(170, 110, 150, 25);
        panel.add(generateButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 140, 350, 100);
        panel.add(resultArea);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                double rate = Double.parseDouble(rateText.getText());
                Employee emp = new Employee(name, rate);
                payroll.addEmployee(emp);
                JOptionPane.showMessageDialog(panel, "Employee added successfully!");
            }
        });

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                int hours = Integer.parseInt(hoursText.getText());
                for (Employee emp : payroll.getEmployees()) {
                    if (emp.getName().equals(name)) {
                        double salary = payroll.calculateSalary(emp, hours);
                        resultArea.setText("Employee Name: " + name + "\nHourly Rate: $" + emp.getHourlyRate() + "\nHours Worked: " + hours + "\nTotal Salary: $" + salary);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(panel, "Employee not found!");
            }
        });
    }
}
