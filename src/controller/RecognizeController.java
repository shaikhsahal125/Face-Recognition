package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

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

    public void proceed(ActionEvent event) throws IOException {
        System.out.println("Proceed btn clicked");
        String imgPath = inputPath.getText().trim();

        if (imgPath.equals("")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error!");
            errorAlert.setContentText("You did not enter enough information.\nPlease make sure you enter values into both fields.");
            errorAlert.showAndWait();
            inputPath.clear();
            System.out.println("Did not enter enough information");
            return;
        }

        if (!new File(imgPath).exists()) {
            System.out.println("File does not exist");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error!");
            errorAlert.setContentText("The file does not exist.\nPlease make sure you enter a correct path.");
            errorAlert.showAndWait();
            inputPath.clear();
            return;
        }

        ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/model/recognize.py",  imgPath).redirectErrorStream(true);
        Process process = processBuilder.start();

        Reader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String s;

        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
    }
}
