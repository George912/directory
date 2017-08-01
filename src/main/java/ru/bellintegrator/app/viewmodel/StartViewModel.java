package ru.bellintegrator.app.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;

import java.util.List;

/**
 * Created by neste_000 on 01.08.2017.
 */
public class StartViewModel {

    private static final Logger log = LoggerFactory.getLogger(StartViewModel.class);

    private Stage stage;
    private List<String> daoTypeNameList;
    private DAOFactoryType daoFactoryType;

    @FXML
    private ComboBox<String> daoTypeComboBox;

    public StartViewModel(List<String> daoTypeNameList) {
        this.daoTypeNameList = daoTypeNameList;
    }

    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(daoTypeNameList);
        daoTypeComboBox.setItems(items);
    }

    @FXML
    private void saveButtonClick() {
        String selectedItem = daoTypeComboBox.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            daoFactoryType = DAOFactoryType.getTypeByName(selectedItem);
        }

        stage.close();
    }

    @FXML
    private void cancelButtonClick() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public DAOFactoryType getDaoFactoryType() {
        return daoFactoryType;
    }

}
