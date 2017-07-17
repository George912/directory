package ru.bellintegrator.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.model.Group;

import java.util.Iterator;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class GroupEditorController {

    private static final Logger log = LoggerFactory.getLogger(GroupEditorController.class);

    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;

    private Stage dialogStage;
    Group group;
    DataManager dataManager;
    EditorAction editorAction;

    public void setGroup(Group group) {

        this.group = group;

        groupNameTextField.setText(group.getName());
        groupNotesTextArea.setText(group.getNotes());

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEditorAction(EditorAction editorAction) {
        this.editorAction = editorAction;
    }

    public GroupEditorController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void saveButtonClick() {

        group.setName(groupNameTextField.getText());
        group.setNotes(groupNotesTextArea.getText());

        switch (editorAction) {
            case CREATE:
                dataManager.addGroup(group);
                break;

            case UPDATE:
                dataManager.updateGroup(group);
                break;

        }

        dialogStage.close();

    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

}
