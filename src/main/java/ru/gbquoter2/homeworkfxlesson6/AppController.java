package ru.gbquoter2.homeworkfxlesson6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {
    @FXML // если не повесить эту аннотацию над private, то поле не будет видно в других классах
    private TextArea messageArea;
    @FXML
    private TextField messageField;
    @FXML
    private Label welcomeText;
    private SomeClient client;

    public AppController() {
        client = new SomeClient(this);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void clickSendButton(ActionEvent actionEvent) {
        final String message = messageField.getText().trim();
        if (message.isEmpty()) {
            return;
        }
        client.sendMessage(message);
        messageField.clear();
        messageField.requestFocus();
    }

    public void deleteText(ActionEvent actionEvent) {
        messageArea.setText("");
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addMessage(String message) {
        messageArea.appendText(message +"\n");
    }
}