package controller;

import data.DataManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class GroupEditorController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(GroupEditorController.class);

    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    Group group;
    DataManager dataManager;
    EditorAction editorAction;

    //</editor-fold>

    //<editor-fold desc="методы получения и установки">

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {

        this.group = group;

        groupNameTextField.setText(group.getName());
        groupNotesTextArea.setText(group.getNotes());

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public EditorAction getEditorAction() {
        return editorAction;
    }

    public void setEditorAction(EditorAction editorAction) {
        this.editorAction = editorAction;
    }

    //</editor-fold>

    public GroupEditorController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void saveButtonClick() {

        ObservableList<Group> groupObservableList = dataManager.getGroupObservableList();
        int groupId = group.getId();

        switch (editorAction) {
            case CREATE:

                group.setName(groupNameTextField.getText());
                group.setNotes(groupNotesTextArea.getText());

                groupObservableList.add(group);

                break;

            case UPDATE:

                Iterator<Group> groupIterator = groupObservableList.iterator();

                while (groupIterator.hasNext()) {
                    Group editableGroup = groupIterator.next();

                    if (editableGroup.getId() == groupId) {
                        editableGroup.setName(groupNameTextField.getText());
                        editableGroup.setNotes(groupNotesTextArea.getText());
                    }
                }

                break;

        }

        dialogStage.close();

    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

}
