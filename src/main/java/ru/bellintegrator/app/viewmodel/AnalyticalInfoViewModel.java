package ru.bellintegrator.app.viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.model.AnalyticalInfo;

public class AnalyticalInfoViewModel extends AbstractViewModel {

    private static final Logger log = LoggerFactory.getLogger(AnalyticalInfoViewModel.class);
    private AnalyticalInfo info;

    @FXML
    private TextField commonUserCountTextField;
    @FXML
    private TextField eachUserContactCountTextField;
    @FXML
    private TextField eachUserGroupCountTextField;
    @FXML
    private TextField avgUserCountInGroupsTextField;
    @FXML
    private TextField avgUserContactCountTextField;
    @FXML
    private TextField inactiveUserCountTextField;

    public AnalyticalInfoViewModel(AnalyticalInfo info) {
        this.info = info;
    }

    @FXML
    private void initialize() {
        commonUserCountTextField.setText(String.valueOf(info.getUserCount()));
        eachUserContactCountTextField.setText(String.valueOf(info.getEachUserContactCount()));
        eachUserGroupCountTextField.setText(String.valueOf(info.getEachUserGroupCount()));
        avgUserCountInGroupsTextField.setText(String.valueOf(info.getAvgUserCountInGroups()));
        avgUserContactCountTextField.setText(String.valueOf(info.getAvgUsersContactCount()));
        inactiveUserCountTextField.setText(String.valueOf(info.getInactiveUserCount()));
    }

}
