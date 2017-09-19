/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npgg3cstopwatchfxml;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author nickpistolis
 */
public class Npgg3cStopwatchFXML extends Application {
    private final String appTitle = "Stopwatch";
    private AnalogDigitalStopwatch stopwatch;
    
    private final double sceneWidth = 450.0;
    private final double sceneHeight = 450.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stopwatch = new AnalogDigitalStopwatch();
        Parent root = FXMLLoader.load(getClass().getResource("Nppg3c.fxml"));
        
        Scene scene = new Scene(rootContainer, sceneWidth, sceneHeight);
        
        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
