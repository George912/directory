package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class ContactEditorController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField firstPhoneNumberTextField;
    @FXML
    private TextField secondPhoneNumberTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;

    //</editor-fold>


}
