
package nppg3csuperviz;
import nppg3csuperviz.Visualizer;
import static java.lang.Integer.min;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Nick Pistolis
 */
public class Nppg3cMyViz implements Visualizer {
    
    private final String name = "Nppg3cMyViz";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private final Double bandHeightPercentage = 0.5;// smaller circles
    private final Double minEllipseRadius = 5.0;  // smaller circles
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    private Double halfBandWidth =0.0;
    
    private final Double startHue = 147.0;
    
    private Ellipse[] ellipses;
    
    public Nppg3cMyViz() {
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        halfBandWidth = width / 2;
        ellipses = new Ellipse[numBands];
        
        for (int i = 0; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterY(bandWidth / 8 + bandWidth * i);//to make it up and down
            ellipse.setCenterX(height / 2);// to make it up and down
            ellipse.setRadiusY(bandWidth / 2);// to make it up and down
            ellipse.setRadiusX(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
        }

    }
    
    @Override
    public void end() {
         if (ellipses != null) {
             for (Ellipse ellipse : ellipses) {
                 vizPane.getChildren().remove(ellipse);
             }
            ellipses = null;
        } 
    }
    
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (ellipses == null) {
            return;
        }
        
        Integer num = min(ellipses.length, magnitudes.length);
        
        for (int i = 0; i < num; i++) {
            ellipses[i].setRadiusX( ((60.0 + magnitudes[i])/60.0) * halfBandWidth + minEllipseRadius);
            ellipses[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
        }
    }
}
