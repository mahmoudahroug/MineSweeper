package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;

import engine.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TopBar extends HBox {
    Label flags = new Label(Game.flagCount+"");
    Image flag = new Image("./views/flag.png");
    public TopBar(){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        HBox h = new HBox();
        h.setAlignment(Pos.CENTER_LEFT);
        ImageView fl = new ImageView(flag);
        fl.setPreserveRatio(true);
        fl.setFitHeight(50);
        h.getChildren().add(fl);
        flags.setFont(new Font(30));
        h.getChildren().add(flags);
        getChildren().add(h);
    }
    public void update(){
        flags.setText(Game.flagCount+"");
    }

}
