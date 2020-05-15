/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication17;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author ريما
 */
public class JavaFXApplication17 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane jpaPane = FXMLLoader.load(getClass().getResource("StudentApp.fxml"));
        Scene scene = new Scene(jpaPane);
        primaryStage.setTitle("JPA App");
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
