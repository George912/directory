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
public class GroupEditorController {

    private static final Logger log = LoggerFactory.getLogger(GroupEditorController.class);

    //<editor-fold desc="поля">

    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    //</editor-fold>

}
