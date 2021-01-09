package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class AddFaceController {

    @FXML
    Button browseBtn;

    @FXML
    Button addBtn;

    @FXML
    Button backBtn;

    @FXML
    TextField inputPath;

    @FXML
    TextField nameInput;

    public void start() {
        System.out.println("Hello from AddFaceController");
    }

    public void browse(ActionEvent event) {
        System.out.println("Browse btn clicked");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File Types", "*.png", "*.jpg", "*.jpeg"));

        File file = fileChooser.showOpenDialog(browseBtn.getScene().getWindow());
        inputPath.setText(file.getPath());

    }

    /*
     * @perm event
     * @return void
     *
     */
    public void add(ActionEvent event) throws IOException {

        String imgPath = inputPath.getText().trim();
        String name = nameInput.getText().trim();

        if (imgPath.equals("") || name.equals("")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error!");
            errorAlert.setContentText("You did not enter enough information.\nPlease make sure you enter values into both fields.");
            errorAlert.showAndWait();
            inputPath.clear();
            nameInput.clear();
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

        ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/model/addFace.py", name, imgPath).redirectErrorStream(true);
        Process process = processBuilder.start();

        Reader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String s;

        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }



        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Success");
        confirmationAlert.setHeaderText("Successfully added a new face");
        confirmationAlert.setContentText("Do you want to add another face ?");
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            inputPath.clear();
            nameInput.clear();
        } else {
            FXMLLoader newLoader = new FXMLLoader();
            newLoader.setLocation(getClass().getResource("/view/home_page.fxml"));

            AnchorPane root = newLoader.load();

            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Recognize People");
            newStage.setResizable(false);
            newStage.show();

            Stage currentStage = (Stage) addBtn.getScene().getWindow();
            currentStage.close();
        }

    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader newLoader = new FXMLLoader();
        newLoader.setLocation(getClass().getResource("/view/home_page.fxml"));

        AnchorPane root = newLoader.load();

        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.setTitle("Recognize People");
        newStage.setResizable(false);
        newStage.show();

        Stage currentStage = (Stage) backBtn.getScene().getWindow();
        currentStage.close();
    }

}
