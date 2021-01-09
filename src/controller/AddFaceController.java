package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class AddFaceController {

    @FXML
    Button browseBtn;

    @FXML
    Button addBtn;

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

    public void add(ActionEvent event) throws IOException {

        String imgPath = inputPath.getText().trim();
        String name = nameInput.getText().trim();

        if (imgPath.equals("") || name.equals("")){
            // todo popup for not entering enough information
            System.out.println("Did not enter enough information");
            return;
        }

        if (!new File(imgPath).exists()) {
            // todo alert for file does not exist
            System.out.println("File does not exist");
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
    }
}
