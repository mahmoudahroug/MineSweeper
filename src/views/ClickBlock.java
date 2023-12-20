package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.awt.Point;
import java.util.Collection;
import engine.*;
public class ClickBlock implements EventHandler<MouseEvent> {

    public boolean first = true;
    Image flag = new Image("./views/flag.png");
    Image bomb = new Image("./views/bomb.png");
    private GridPane map;
    private Main main;
    private TopBar topBar;
    public ClickBlock(GridPane map, Main main, TopBar topBar){
        this.map = map;
        this.main = main;
        this.topBar = topBar;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Rectangle r = ((Rectangle) mouseEvent.getSource());
        String[] s = r.getId().split(" ");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);
        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            if(first){
                first = false;
                Game.startGame(new Point(x,y));
            }
            else {
                if(Game.map[x][y].isMine()){
                    // lose Screen
                    Scene scene = new Scene(new LoserScreen());
                    main.stage.setScene(scene);
                }
                Game.reveal(new Point(x,y));
                if(Game.checkWin()){
                    // win Screen
                    Scene scene = new Scene(new WinScreen());
                    main.stage.setScene(scene);
                }
            }
        }
        else
            Game.map[x][y].setFlagged(!Game.map[x][y].isFlagged());

        updateGrid();
        topBar.update();

    }

    public void updateGrid(){
        map.getChildren().clear();
        boolean dark = false;
        for(int i = 0; i< Game.mapSize; i++){
            for(int j = 0; j< Game.mapSize; j++){
                if(!Game.map[i][j].isVisible()) {
                    // create cell
                    Rectangle block = new Rectangle();
                    block.setFill(dark? Color.GREEN.brighter() : Color.GREEN);
                    block.setWidth((main.scene.getWidth())/Game.mapSize-0.4);
                    block.setHeight((main.scene.getHeight()-50)/Game.mapSize-0.4);
                    // set coords
                    block.setId(i + " " + j);
                    block.setOnMouseClicked(this);
                    block.setOnMouseEntered(mouseEvent -> {
                        block.setFill(((Color)block.getFill()).brighter());
                    });
                    block.setOnMouseExited(mouseEvent -> {
                        block.setFill(((Color)block.getFill()).darker());
                    });

                    map.add(block, j, i);

                    if(Game.map[i][j].isFlagged()){
                        ImageView b = new ImageView(flag);
                        b.setPreserveRatio(true);
                        b.setFitWidth(main.scene.getWidth()/Game.mapSize -18);
                        map.add(b, j, i);
                    }
                }
                else{
                    if(Game.map[i][j].getAdjacents() > 0) {
                        Label l = new Label(Game.map[i][j].getAdjacents() + "");
                        l.setFont(new Font(30));
                        map.add(l, j, i);
                    }
                }
                dark = !dark;
            }
        }

    }

}
