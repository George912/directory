package ru.bellintegrator.app.directory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.state.ILoadFromStore;
import ru.bellintegrator.app.state.ISaveToStore;
import ru.bellintegrator.app.state.impl.Loader;
import ru.bellintegrator.app.state.impl.Saver;

public class MainApp extends Application {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private ILoadFromStore loader;
    private ISaveToStore saver;
    private DataManager dataManager = DataManager.getInstance();

    //</editor-fold>

    @Override
    public void init(){

        loader = new Loader(dataManager);
        loader.load();

    }

    @Override
    public void stop(){

        saver = new Saver(dataManager);
        saver.save();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Справочник контактов");
        stage.setScene(scene);
        stage.show();

    }
}
