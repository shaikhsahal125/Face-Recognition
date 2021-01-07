package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class HomePageController {
    @FXML
    Button recognizeBtn;

    @FXML
    Button addFaceBtn;


    public void recognizeBtnCtrl(ActionEvent event) throws IOException {
        System.out.println("Recognize Btn clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/recognize.fxml"));

        AnchorPane root = (AnchorPane) loader.load();
        RecognizeController rc = loader.getController();
        rc.hello();

        Stage recogStage = new Stage();
        Scene scene = new Scene(root);
        recogStage.setScene(scene);
        recogStage.setTitle("Detected Faces");
        recogStage.setResizable(false);
        recogStage.show();

        Stage runningStage = (Stage) recognizeBtn.getScene().getWindow();
        runningStage.close();

    }


    public void addFaceBtnCtrl(ActionEvent event) throws IOException {
        System.out.println("Add face Btn clicked");
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/model/addFace.py", "Sahal", "/Users/sahalshaih/Downloads/catTuring.jpeg").redirectErrorStream(true);
        Process process = processBuilder.start();

        Reader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String s;

        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
    }

}
