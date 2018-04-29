import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Controller{

    @FXML RadioButton celsiusRadio;
    @FXML RadioButton fahrenheitRadio;
    @FXML LineChart<String, Number> tempChart;
    @FXML Label tempLabel;

    private static final int maxReadings = 20;

    private ToggleGroup group = new ToggleGroup();
    private InputSource inputSource;
    private boolean displayInCelsius;
    private double latestTemp;

    private XYChart.Series<String, Number> readingsCelsius = new XYChart.Series<>();
    private XYChart.Series<String, Number> readingsFahrenheit = new XYChart.Series<>();
    private XYChart.Series<String, Number> currReadings;


    private int latestReadNo;

    public Controller(InputSource inputSource){
        this.inputSource = inputSource;
    }

    @FXML public void initialize() {
        celsiusRadio.setToggleGroup(group);
        fahrenheitRadio.setToggleGroup(group);
        celsiusRadio.setSelected(true);

        tempChart.getXAxis().setLabel("Reading #");

        switchModeCelsius();
    }

    public void addTemperature(){
        double tempValue = inputSource.getNextCelsius();
        latestReadNo++;
        latestTemp = tempValue;
        XYChart.Data readingC = new XYChart.Data(Integer.toString(latestReadNo), tempValue);
        XYChart.Data readingF = new XYChart.Data(Integer.toString(latestReadNo), convertToFahrenheit(tempValue));

        readingsCelsius.getData().add(readingC);
        readingsFahrenheit.getData().add(readingF);

        if(readingsCelsius.getData().size() > maxReadings){
            readingsCelsius.getData().remove(0);
        }
        if(readingsFahrenheit.getData().size() > maxReadings){
            readingsFahrenheit.getData().remove(0);
        }
        displayData();
    }

    private double convertToFahrenheit(double celsiusValue){
        return (celsiusValue * 1.8 + 32);
    }

    private void displayData(){
        //reset chart data
        tempChart.setData(FXCollections.observableArrayList());
        //add updated data
        tempChart.getData().add(currReadings);

        //round display-value of latest reading to two-decimals.
        double roundedTemp;
        if(displayInCelsius){
            roundedTemp =  (Math.round(latestTemp * 100)) / 100;
            tempLabel.setText(roundedTemp+ "°C");
        }
        else{
            roundedTemp = (Math.round(convertToFahrenheit(latestTemp) * 100)) / 100;
            tempLabel.setText(roundedTemp + "°F");
        }
    }

    public void switchModeCelsius(){
        displayInCelsius = true;
        tempChart.getYAxis().setLabel("Temperature(°C)");
        currReadings = readingsCelsius;
        displayData();
    }
    public void switchModeFahrenheit(){
        displayInCelsius = false;
        tempChart.getYAxis().setLabel("Temperature (°F)");
        currReadings = readingsFahrenheit;
        displayData();
    }
}
