import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javafx.embed.swing.SwingFXUtils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;



public class Main extends Application {

    Stage window;
    TextField path_field, alpha_field, beta_field;
    GridPane HOMEGRID, PHOTOGRID;
    Image image;
    ImageView iv, iv2;
    Scene scene_photo;
    Label not_found, alpha, beta;
    String input;
    Mat preimage;
    Mat modimage;
    Button bac, hist;
    ContrastandBrightness CB;

    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }

    //From Paritosh Gupta on stackoverflow
    private Image mat2Image(Mat frame) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (frame.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = frame.channels() * frame.cols() * frame.rows();
        byte[] b = new byte[bufferSize];
        frame.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(frame.cols(), frame.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return SwingFXUtils.toFXImage(image, null);
    }


        private void load_photo(){
        try {
            //PHOTOGRID.getChildren().remove(iv2);
            HOMEGRID.getChildren().remove(iv2);
            HOMEGRID.getChildren().remove(not_found);
            HOMEGRID.getChildren().removeAll(bac, alpha_field, alpha, beta_field, beta, hist);


            input = path_field.getText();
            if (input.equals("i nut in my butt")){
                window.setScene(scene_photo);
            }
            preimage = Imgcodecs.imread(input);
            image = mat2Image(preimage);

            //iv = new ImageView(image);

            iv2 = new ImageView();
            iv2.setImage(image);
            iv2.setFitWidth(500);
            iv2.setPreserveRatio(true);
            iv2.setSmooth(true);
            iv2.setCache(true);

            HOMEGRID.add(iv2,2,2);
            HOMEGRID.getChildren().addAll(bac, alpha_field, alpha, beta_field, beta, hist);

            //PHOTOGRID.add(iv2, 0, 1);
            //window.setScene(scene_photo);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
            not_found = new Label("didn't find \""+input+'\"');
            not_found.setTextFill(Color.RED);
            HOMEGRID.add(not_found,1,1);
        }

    }
        private void bac(int alpha,int beta){
            HOMEGRID.getChildren().remove(iv2);

            modimage = new ContrastandBrightnessRun().run(preimage,alpha, beta);

            image = mat2Image(modimage);
            //iv = new ImageView(image);

            iv2 = new ImageView();
            iv2.setImage(image);
            iv2.setFitWidth(500);
            iv2.setPreserveRatio(true);
            iv2.setSmooth(true);
            iv2.setCache(true);

            HOMEGRID.add(iv2,2,2);
        }
        private void histogram(){
            HOMEGRID.getChildren().remove(iv2);

            modimage = new HistogramRun().run(preimage);

            image = mat2Image(modimage);
            //iv = new ImageView(image);

            iv2 = new ImageView();
            iv2.setImage(image);
            iv2.setFitWidth(400);
            iv2.setPreserveRatio(true);
            iv2.setSmooth(true);
            iv2.setCache(true);

            HOMEGRID.add(iv2,2,2);
    }


        private void plusbeta(){

        }
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("240 Group Project");

        PHOTOGRID = new GridPane();
        PHOTOGRID.setPadding(new Insets(10,10,10,10));
        PHOTOGRID.setVgap(8);
        PHOTOGRID.setHgap(10);
        scene_photo = new Scene(PHOTOGRID, 850, 500);


        HOMEGRID = new GridPane();
        HOMEGRID.setPadding(new Insets(10,10,10,10));
        HOMEGRID.setVgap(8);
        HOMEGRID.setHgap(10);
        Scene scene_start= new Scene(HOMEGRID, 850, 500);



        Label path_label = new Label("Name of file:");
        GridPane.setConstraints(path_label, 0, 0);

        path_field = new TextField ("monkey.jpg");
        GridPane.setConstraints(path_field, 1, 0);

        Button switch_button = new Button("load photo");
        switch_button.setOnAction(e -> load_photo());
        GridPane.setConstraints(switch_button, 0, 1);

        bac = new Button("update");
        GridPane.setConstraints(bac,0,5);
        bac.setOnAction(e -> bac(Integer.parseInt(alpha_field.getText()), Integer.parseInt(beta_field.getText())));

        hist = new Button("show histogram");
        GridPane.setConstraints(hist,1,5);
        hist.setOnAction(e -> histogram());

        alpha = new Label("alpha value:");
        GridPane.setConstraints(alpha, 0, 3);

        alpha_field = new TextField ("1-3");
        GridPane.setConstraints(alpha_field, 1, 3);

        beta = new Label("beta value:");
        GridPane.setConstraints(beta, 0, 4);

        beta_field = new TextField ("0-100");
        GridPane.setConstraints(beta_field, 1, 4);


        HOMEGRID.getChildren().addAll(path_label, path_field, switch_button);

        Label slabel = new Label("How tf did you get here?! LEAVE");
        Button switch_button2 = new Button("return");
        switch_button2.setOnAction(f-> window.setScene(scene_start));
        PHOTOGRID.add(switch_button2,0,0);
        PHOTOGRID.add(slabel,0,1);

        primaryStage.setScene(scene_start);
        primaryStage.show();

    }


}
