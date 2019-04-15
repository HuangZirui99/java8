
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

 
public class ElevationPlot extends Application {
	
	public static void main(String[] args) {
	    launch(args);
	}
	 
    @Override public void start(Stage stage) throws FileNotFoundException, GPSException {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Distance");
        yAxis.setLabel("Elevation");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);      
        lineChart.setTitle("Elevation Plot");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Walk.csv");
        Track a=new Track();
        a.readFile("./data/walk.csv");
        series.getData().add(new XYChart.Data(0, a.get(0).elevation));
        double distence=0;
        for(int i=1;i<a.size();i++){
        	distence=distence+Point.greatCircleDistance(a.get(i), a.get(i-1));
        	series.getData().add(new XYChart.Data(distence, a.get(i).elevation));
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }

}
