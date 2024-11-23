package com.example.hostiplemanagementsys;

import com.example.hostiplemanagementsys.user.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../FXML/login.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(Stage stage){
//        userid=
//        User.login();
        System.out.println("Login");
    }
    public static void mai(String[] args){
        launch(args);
    }
}
