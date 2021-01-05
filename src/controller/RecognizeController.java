package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class RecognizeController {
    @FXML
    private Button browseBtn;

    @FXML
    private Button proceedBtn;

    @FXML
    private TextField inputPath;


    public void hello() {
        System.out.println("hello");
    }

    public void browse(ActionEvent event) {
        System.out.println("Browse btn clicked");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File Types", "*.png", "*.jpg", "*.jpeg"));

        File file = fileChooser.showOpenDialog(browseBtn.getScene().getWindow());
        inputPath.setText(file.getPath());

    }

    public void proceed(ActionEvent event) {
        System.out.println("Proceed btn clicked");
    }
}
