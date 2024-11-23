package com.example.hostiplemanagementsys;

import com.example.hostiplemanagementsys.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

import static javafx.application.Application.launch;

public class login {
    private String username, password;


    @FXML
    private JButton login;
    @FXML
    private JTextField userid;
    private JPasswordField pass;
    private AnchorPane loginScene;


    public login(String username, String password) {
        User.login(username, password);
    }

    public AnchorPane getLoginScene() {
        return loginScene;
    }

    public static void mai(String[] args){
        launch(args);
    }
}

