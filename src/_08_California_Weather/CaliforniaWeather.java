package _08_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton findTemp = new JButton();
    JButton CityWeather = new JButton();
    JButton TempToCity = new JButton();
    HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
    void start() {
        frame.add(panel);
        panel.add(findTemp);
        panel.add(CityWeather);
        panel.add(TempToCity);
        findTemp.setText("Find the weather of a city");
        findTemp.addActionListener(this);
        CityWeather.setText("Find the city from the weather");
        CityWeather.addActionListener(this);
        TempToCity.setText("Find a city from the temperature");
        TempToCity.addActionListener(this);
        frame.setVisible(true);
        frame.pack();
        
        // All city keys have the first letter capitalized of each word
        //String cityName = Utilities.capitalizeWords( "National City" );
        //WeatherData datum = weatherData.get(cityName);
        
        //if( datum == null ) {
            //System.out.println("Unable to find weather data for: " + cityName);
        //} else {
           // System.out.println(cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
        //}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == findTemp) {
			String answer = JOptionPane.showInputDialog(null,"Enter a city name");
			String cityName2 = Utilities.capitalizeWords(answer);
			WeatherData datum2 = weatherData.get(cityName2);
			if( datum2 == null ) {
	            System.out.println("Unable to find weather data for: " + cityName2);
	        } else {
	            System.out.println(cityName2 + " is " + datum2.weatherSummary + " with a temperature of " + datum2.temperatureF + " F");
	        }
		}
		if(e.getSource() == CityWeather) {
			String answer2= JOptionPane.showInputDialog("Enter a weather condition:");
			ArrayList<String> cities = new ArrayList<String>();
			for(String city : weatherData.keySet()) {
				WeatherData datum = weatherData.get(city);
				String weatherCondition = datum.weatherSummary;
				if(weatherCondition.equalsIgnoreCase(answer2)) {
					cities.add(city);
				}
			}
			String citiesNames = "";
			for(String city : cities) {
				citiesNames += city;
				citiesNames += ", ";
			}
			JOptionPane.showMessageDialog(null, citiesNames);

		}
		if(e.getSource() == TempToCity) {
		String mintemp = JOptionPane.showInputDialog(null,"Enter a min temperature");
		String maxtemp = JOptionPane.showInputDialog(null,"Enter a max temperature");
		Double mintemp1 = Double.parseDouble(mintemp);
		Double maxtemp1 = Double.parseDouble(maxtemp);
		ArrayList<String> cities = new ArrayList<String>();
		for(String city : weatherData.keySet()) {
			WeatherData datum = weatherData.get(city);
			Double weatherCondition = datum.temperatureF;
			if(weatherCondition > mintemp1 && weatherCondition < maxtemp1) {
				cities.add(city);
			}
				
			}
		String citiesNames = "";
		for(String city : cities) {
			citiesNames += city;
			citiesNames += ", ";
		}
		JOptionPane.showMessageDialog(null, citiesNames);
		}
		
	}
}

