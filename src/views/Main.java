package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;


import java.io.FileInputStream;
import java.io.IOException;
import engine.*;

import javax.swing.plaf.synth.ColorType;

public class Main extends Application {
    Stage stage;
    Scene scene;
    TopBar topBar;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game.initialize();

        stage = primaryStage;
        BorderPane root = new BorderPane();
        // put topBar
        topBar = new TopBar();
        root.setTop(topBar);

        // minsweeper grid
        GridPane grid = new GridPane();
        //controller
        ClickBlock cB = new ClickBlock(grid, this, topBar);
        // Column and row constraints
        for(int i = 0; i< Game.mapSize; i++){
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / Game.mapSize);
            grid.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / Game.mapSize);
            grid.getRowConstraints().add(row);
        }

        scene = new Scene(root, 700, 700);
        boolean dark = false;
        for(int i = 0; i< Game.mapSize; i++){
            for(int j = 0; j< Game.mapSize; j++){
                Rectangle block = new Rectangle();
                block.setFill(dark? Color.GREEN.brighter() : Color.GREEN);
                block.setWidth((scene.getWidth())/Game.mapSize-0.4);
                block.setHeight((scene.getHeight()-50)/Game.mapSize-0.4);
                block.setOnMouseClicked(cB);

                block.setOnMouseEntered(mouseEvent -> {
                    block.setFill(((Color)block.getFill()).brighter());
                });
                block.setOnMouseExited(mouseEvent -> {
                    block.setFill(((Color)block.getFill()).darker());
                });
                block.setId(i + " " + j);
//                block.setOnAction(cB);
//                block.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // This makes the button fill the cell
//                GridPane.setHgrow(block, javafx.scene.layout.Priority.ALWAYS);
//                GridPane.setVgrow(block, javafx.scene.layout.Priority.ALWAYS);
                grid.add(block, j, i, 1, 1);
                dark = !dark;
            }
        }
        grid.setGridLinesVisible(true);

        // all stats
        HBox stats = new HBox();
        Label numFlags = new Label(""+ Game.numMines);

        root.setCenter(grid);

        primaryStage.setTitle("Mine Sweeper");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
