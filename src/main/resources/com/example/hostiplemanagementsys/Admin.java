package com.example.hostiplemanagementsys;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

public class DatabaseManagerAppWithValidation extends Application {

    private DatabaseManager dbManager;

    @Override
    public void start(Stage primaryStage) {
        // Initialize DatabaseManager
        dbManager = new DatabaseManager();

        // Layout components
        Label lblTableName = new Label("Table Name:");
        TextField txtTableName = new TextField();

        Label lblColumnValues = new Label("Column Values (key=value):");
        TextField txtColumnValues = new TextField();

        Label lblCondition = new Label("Condition (key=value for Update/Delete/Fetch):");
        TextField txtCondition = new TextField();

        Label lblDate = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();

        Label lblTime = new Label("Enter Time (HH:mm):");
        TextField txtTime = new TextField();

        ComboBox<String> operationsDropdown = new ComboBox<>();
        ObservableList<String> operations = FXCollections.observableArrayList("Insert", "Update", "Delete", "Fetch");
        operationsDropdown.setItems(operations);
        operationsDropdown.setPromptText("Select Operation");

        Button btnExecute = new Button("Execute");

        TableView<Map<String, Object>> tableView = new TableView<>();
        tableView.setPlaceholder(new Label("No data to display"));

        VBox vbox = new VBox(10, lblTableName, txtTableName, lblColumnValues, txtColumnValues, lblCondition, txtCondition, lblDate, datePicker, lblTime, txtTime, operationsDropdown, btnExecute, tableView);
        vbox.setPadding(new Insets(10));

        // Button click event
        btnExecute.setOnAction(e -> {
            String tableName = txtTableName.getText();
            String columnValuesText = txtColumnValues.getText();
            String conditionText = txtCondition.getText();
            LocalDate selectedDate = datePicker.getValue();
            String time = txtTime.getText();
            String operation = operationsDropdown.getValue();

            if (tableName.isEmpty() || operation == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Table name and operation are required.");
                return;
            }

            try {
                Map<String, Object> columnValues = parseKeyValuePairs(columnValuesText);
                if (selectedDate != null) {
                    columnValues.put("date", selectedDate.toString());
                }
                if (!time.isEmpty()) {
                    columnValues.put("time", time);
                }

                Map.Entry<String, Object> condition = parseCondition(conditionText);

                if (!validateInputs(columnValues, tableName)) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid input values for the table columns.");
                    return;
                }

                switch (operation) {
                    case "Insert":
                        dbManager.insert(tableName, columnValues);
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Data inserted successfully.");
                        break;
                    case "Update":
                        if (condition == null) {
                            showAlert(Alert.AlertType.ERROR, "Error", "Condition is required for Update.");
                        } else {
                            dbManager.update(tableName, columnValues, condition.getKey(), condition.getValue());
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Data updated successfully.");
                        }
                        break;
                    case "Delete":
                        if (condition == null) {
                            showAlert(Alert.AlertType.ERROR, "Error", "Condition is required for Delete.");
                        } else {
                            dbManager.delete(tableName, condition.getKey(), condition.getValue());
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Data deleted successfully.");
                        }
                        break;
                    case "Fetch":
                        if (condition == null) {
                            showAlert(Alert.AlertType.ERROR, "Error", "Condition is required for Fetch.");
                        } else {
                            List<Map<String, Object>> result = dbManager.fetch(tableName, condition.getKey(), condition.getValue());
                            populateTableView(tableView, result);
                        }
                        break;
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + ex.getMessage());
            }
        });

        // Stage setup
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setTitle("Database Manager with Validation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Parse key-value pairs for column values
    private Map<String, Object> parseKeyValuePairs(String text) {
        Map<String, Object> map = new HashMap<>();
        if (text != null && !text.isEmpty()) {
            String[] pairs = text.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    map.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
        return map;
    }

    // Parse condition for Update/Delete/Fetch
    private Map.Entry<String, Object> parseCondition(String text) {
        if (text != null && !text.isEmpty()) {
            String[] keyValue = text.split("=");
            if (keyValue.length == 2) {
                return new HashMap.SimpleEntry<>(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return null;
    }

    // Validate inputs based on SQL data types
    private boolean validateInputs(Map<String, Object> columnValues, String tableName) {
        Map<String, Integer> columnTypes = dbManager.getColumnTypes(tableName);

        for (String column : columnValues.keySet()) {
            Object value = columnValues.get(column);
            Integer sqlType = columnTypes.get(column);

            if (sqlType == null) {
                continue; // Skip validation if column type is unknown
            }

            switch (sqlType) {
                case Types.INTEGER:
                    if (!(value instanceof Integer || isInteger(value.toString()))) return false;
                    break;
                case Types.VARCHAR:
                    if (!(value instanceof String)) return false;
                    break;
                case Types.DATE:
                    if (!(value instanceof LocalDate || isValidDate(value.toString()))) return false;
                    break;
                case Types.TIME:
                    if (!(isValidTime(value.toString()))) return false;
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    // Helper methods for validation
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDate(String str) {
        try {
            LocalDate.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidTime(String str) {
        return str.matches("\\d{2}:\\d{2}"); // Matches HH:mm format
    }

    // Populate TableView with fetched data
    private void populateTableView(TableView<Map<String, Object>> tableView, List<Map<String, Object>> data) {
        tableView.getColumns().clear();

        if (data.isEmpty()) {
            tableView.setPlaceholder(new Label("No data found."));
            return;
        }

        // Add columns dynamically based on keys
        data.get(0).keySet().forEach(key -> {
            TableColumn<Map<String, Object>, String> column = new TableColumn<>(key);
            column.setCellValueFactory(cellData -> {
                Object value = cellData.getValue().get(key);
                return javafx.beans.property.SimpleStringProperty(value == null ? "null" : value.toString());
            });
            tableView.getColumns().add(column);
        });

        // Add data to the table
        tableView.getItems().clear();
        tableView.getItems().addAll(data);
    }

    // Show alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        dbManager.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
