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
import javafx.geometry.Insets;



public class Main extends Application {

    Stage window;
    File file;
    TextField path_field;
    StackPane root_photo;
    Image image;
    ImageView iv;
    Scene scene_photo;

    public static void main(String[] args){
        launch(args);
    }

    public void loadphoto(){
        String u = "";
        try {
            String input = path_field.getText();
            u = input;
            image = new Image(input);
            iv = new ImageView(image);

            root_photo.getChildren().add(iv);
            window.setScene(scene_photo);
        }
        catch(IllegalArgumentException e){
            System.out.println("Didnt find "+ u);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("spagheGUI");

        root_photo = new StackPane();
        scene_photo = new Scene(root_photo, 900, 800);

        GridPane HOMEGRID = new GridPane();
        HOMEGRID.setPadding(new Insets(10,10,10,10));
        HOMEGRID.setVgap(8);
        HOMEGRID.setHgap(10);

        Label path_label = new Label("Path to file:");
        GridPane.setConstraints(path_label, 0, 0);

        path_field = new TextField ("photo.jpg");
        GridPane.setConstraints(path_field, 1, 0);

        Scene scene_start= new Scene(HOMEGRID, 500, 500);


        Button switch_button = new Button("See photo");
        switch_button.setOnAction(e -> loadphoto());
        GridPane.setConstraints(switch_button, 0, 1);

        Button switch_button2 = new Button("Go back");
        switch_button2.setOnAction(f-> window.setScene(scene_start));

        //image = new Image("treebg.jpg");
        //iv = new ImageView(image);

        //root_photo.getChildren().add(iv);



        root_photo.getChildren().add(switch_button2);
        HOMEGRID.getChildren().addAll(path_label, path_field, switch_button);


        primaryStage.setScene(scene_start);
        primaryStage.show();
    }


}
