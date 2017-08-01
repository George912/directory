package ru.bellintegrator.app.viewmodel;

import javafx.scene.control.Alert;

/**
 * Created by neste_000 on 01.08.2017.
 */
public abstract class AbstractViewModel {

    protected void showAlert(String title, String header, Throwable cause) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(cause.getLocalizedMessage());

        alert.showAndWait();

    }

    protected void showAlert(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();

    }

}
