package ru.bellintegrator.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.service.GroupService;
import ru.bellintegrator.app.model.Group;

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
    EditorAction editorAction;

    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
    GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
    GroupService groupService = new GroupService(groupGenericDAO);

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
    }

    @FXML
    private void saveButtonClick() {

        group.setName(groupNameTextField.getText());
        group.setNotes(groupNotesTextArea.getText());

        switch (editorAction) {
            case CREATE:
                groupService.addGroup(group);
                break;

            case UPDATE:
                groupService.updateGroup(group);
                break;

        }

        dialogStage.close();

    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

}
