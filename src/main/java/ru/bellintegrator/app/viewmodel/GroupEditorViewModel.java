package ru.bellintegrator.app.viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.util.Annunciator;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class GroupEditorViewModel extends AbstractViewModel{

    private static final Logger log = LoggerFactory.getLogger(GroupEditorViewModel.class);

    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;

    private Stage dialogStage;
    Group group;
    EditorAction editorAction;
    private GroupService groupService;

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

    public GroupEditorViewModel(GroupService groupService) {

        this.groupService = groupService;

    }

    @FXML
    private void saveButtonClick() {

        group.setName(groupNameTextField.getText());
        group.setNotes(groupNotesTextArea.getText());

        switch (editorAction) {
            case CREATE:
                log.debug("create group: " + group);
                try {
                    groupService.addGroup(group);

                } catch (DAOException e) {
                    showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
                }
                break;

            case UPDATE:
                log.debug("update group: " + group);
                try {
                    groupService.updateGroup(group);

                } catch (DAOException e) {
                    showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
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
