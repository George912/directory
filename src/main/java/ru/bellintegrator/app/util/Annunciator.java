package ru.bellintegrator.app.util;

import javafx.scene.control.Alert;

/**
 * Created by neste_000 on 24.07.2017.
 */
public class Annunciator {

    public static void showAlert(String title, String header, Throwable cause) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(cause.getLocalizedMessage());

        alert.showAndWait();

    }

    public static void showAlert(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();

    }

}
