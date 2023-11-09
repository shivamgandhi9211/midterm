package com.georgiancollege.test1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> cityColumn;
    @FXML
    private TableColumn<Employee, String> provinceColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private CheckBox ontarioOnlyCheckBox;
    @FXML
    private ComboBox<String> areaCodeComboBox;
    @FXML
    private Label noOfEmployeesLabel;

    private ArrayList<Employee> employeeList;
    private ArrayList<Employee> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the Employee List from the database
        employeeList = DBUtility.getEmployeesdb("1");

        // Configure table columns
        configureTableColumns();

        // Initialize the area code filter
        initializeFilter();

        // Initialize the table
        initializeTable();

        // Update the count of employees
        updateEmployeeCount();
    }

    private void configureTableColumns() {
        // Set up table columns to display employee information
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void initializeFilter() {
        // Extract area codes from phone numbers and populate the area code ComboBox
        HashSet<String> areaCodes = extractAreaCodes();
        populateAreaCodeComboBox(areaCodes);
    }

    private HashSet<String> extractAreaCodes() {
        // Extract area codes from phone numbers in the employee list
        HashSet<String> areaCodes = new HashSet<>();
        for (Employee employee : employeeList) {
            String phoneNumber = employee.getPhone();
            if (phoneNumber.length() >= 3) {
                String areaCode = phoneNumber.substring(0, 3);
                areaCodes.add(areaCode);
            }
        }
        return areaCodes;
    }

    private void populateAreaCodeComboBox(HashSet<String> areaCodes) {
        // Populate the area code ComboBox with extracted area codes
        ArrayList<String> sortedAreaCodes = new ArrayList<>(areaCodes);
        sortedAreaCodes.add(0, "All");
        areaCodeComboBox.setItems(FXCollections.observableArrayList(sortedAreaCodes));
        areaCodeComboBox.getSelectionModel().select(0);
    }

    private void initializeTable() {
        // Initialize the table with the filtered employee list
        filteredList = new ArrayList<>(employeeList);
        tableView.getItems().addAll(filteredList);
    }

    @FXML
    void ontarioOnlyCheckBox_OnClick(ActionEvent event) {
        // Apply filters when the Ontario checkbox is clicked
        applyFilter();
    }

    @FXML
    void areaCodeComboBox_OnClick(ActionEvent event) {
        // Apply filters when the area code ComboBox is clicked
        applyFilter();
    }

    private void applyFilter() {
        // Apply filters based on checkbox and ComboBox selections
        filteredList.clear();
        String selectedAreaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
        boolean isFilterActive = ontarioOnlyCheckBox.isSelected();

        for (Employee employee : employeeList) {
            boolean addEmployee = true;
            if (isFilterActive && !employee.getProvince().equals("ON")) {
                addEmployee = false;
            }
            if (!selectedAreaCode.equals("All") && !employee.getPhone().startsWith(selectedAreaCode)) {
                addEmployee = false;
            }
            if (addEmployee) {
                filteredList.add(employee);
            }
        }

        // Update the table and employee count label
        tableView.getItems().setAll(filteredList);
        updateEmployeeCount();
    }

    private void updateEmployeeCount() {
        // Update the label displaying the number of employees
        noOfEmployeesLabel.setText("No. of Employees: " + filteredList.size());
    }
}
