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
    @FXML
    private JPasswordField pass;
    private AnchorPane loginScene;

    Stage stage;

    public <ActionEvent> login(ActionEvent event) {
        userid=userid.getName();
        password=pass.getName();
//        if((User.login(userid, password))=NULL){
//           JOptionPane.showMessageDialog(null,"Invalid Username or Password");
//        }
        System.out.println(username);
    }

    public AnchorPane getLoginScene() {
        return loginScene;
    }


}

