package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoserScreen extends AnchorPane {
    ImageView lose;
    public LoserScreen(){
        lose =  new ImageView(new Image("./views/lose.jpeg"));
        this.getChildren().add(lose);
    }
}
