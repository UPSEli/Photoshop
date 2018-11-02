import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;



public class Main extends Application {

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSCI 240 Group Project");

        StackPane root_photo = new StackPane();
        Scene scene_photo = new Scene(root_photo, 900, 800);

        Label pre_text = new Label("Path to file:");
        TextField textField = new TextField ();
        GridPane gb = new GridPane();
        GridPane.setConstraints(pre_text, 0,0);
        //hb.setSpacing(10);

        StackPane root_start = new StackPane();
        Scene scene_start= new Scene(gp, 500, 500);



        Button switch_button = new Button("See photo");
        switch_button.setOnAction(e-> primaryStage.setScene(scene_photo));

        Button switch_button2 = new Button("Go back");
        switch_button2.setOnAction(f-> primaryStage.setScene(scene_start));



        File file = new File("C:\\Users\\rk\\Desktop\\hDesktop\\spacex-falcon-heavy-launch.jpg");
        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(image);

        root_photo.getChildren().add(iv);
        root_photo.getChildren().add(switch_button2);

        hb.getChildren().add(switch_button);

        primaryStage.setScene(scene_start);
        primaryStage.show();
    }

}
