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
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.util.Collection;
public class WinScreen extends AnchorPane{
    ImageView win;
    public WinScreen(){
        win =  new ImageView(new Image("./views/win.jpeg"));
        this.getChildren().add(win);
    }


}
