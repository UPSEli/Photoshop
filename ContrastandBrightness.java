import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

class ContrastandBrightnessRun {
    private byte saturate(double val) {
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : (iVal < 0 ? 0 : iVal);
        return (byte) iVal;
    }

    public Mat run(Mat image, double alpha, double beta) {
        /// Read image given by user
        //! [basic-linear-transform-load]
        if (image.empty()) {
            System.out.println("Empty image");
            System.exit(0);
        }
        //! [basic-linear-transform-load]

        //! [basic-linear-transform-output]
        Mat newImage = Mat.zeros(image.size(), image.type());
        //! [basic-linear-transform-output]

        //! [basic-linear-transform-parameters]
        if (alpha < 1 || alpha > 3) {
            throw new IllegalArgumentException("alpha needs to be between 1 and 3!");
        }
        if (beta < 0 || beta > 100) {
            throw new IllegalArgumentException("beta needs to be between 0 and 100!");
        }

        image.convertTo(newImage, -1, alpha, beta);

        //! [basic-linear-transform-operation]

        //! [basic-linear-transform-display]
        /// Show stuff
        //HighGui.imshow("Original Image", image);
        //HighGui.imshow("New Image", newImage);

        /// Wait until user press some key
        //HighGui.waitKey();
        //! [basic-linear-transform-display]
        return newImage;
    }
}

public class ContrastandBrightness {
    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //new ContrastandBrightnessRun().run("monkey.jpg", 1, 50);
    }
}