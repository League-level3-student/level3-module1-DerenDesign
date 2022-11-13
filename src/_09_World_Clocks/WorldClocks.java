package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
    ClockUtilities clockUtil;
    Timer timer;
    TimeZone timeZone;

    JFrame frame;
    JPanel panel;
    JTextArea textArea;
    JButton addCity;
    String city;
    String dateStr;
    String timeStr;
    ArrayList<CityClocks> cityclocks = new ArrayList<CityClocks>();
    
    public WorldClocks() {
        clockUtil = new ClockUtilities();

        // The format for the city must be: city, country (all caps)
       String city = "Chicago, US";
       // timeZone = clockUtil.getTimeZoneFromCityName(city);
        
       // Calendar calendar = Calendar.getInstance(timeZone);
       // String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
       // String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
       // dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
       // 
       // System.out.println(dateStr);

        // Sample starter program
        frame = new JFrame();
        panel = new JPanel();
        addCity = new JButton();
        addCity.setText("Add City");
        addCity.addActionListener(this);
        
        //textArea = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100, 100);
        frame.add(panel);
        panel.add(addCity);
        //panel.add(textArea);
        //textArea.setText(city + "\n" + dateStr);
        
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
        addClock(city);
        addClock("San Diego, US");
        frame.pack();
        
    }

    @Override
public void actionPerformed(ActionEvent arg0) {
    	
        //Calendar c = Calendar.getInstance(timeZone);
        for(CityClocks city : cityclocks) {
        Calendar c = Calendar.getInstance(city.timezone);
        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
        String timeStr = militaryTime + twelveHourTime;
        
        System.out.println(timeStr);
        
        city.textarea.setText(city.city + "\n" + city.dateStr + "\n" + timeStr);
    
        }
       
        
        if(arg0.getSource() == addCity) {
        	String cityInput = JOptionPane.showInputDialog("Enter a city:");
        	addClock(cityInput);
        }
        
        frame.pack();
    }
    public void addClock(String city) {
    	String dateStr;
        TimeZone timeZone;
 
    	timeZone = clockUtil.getTimeZoneFromCityName(city);
    	Calendar calendar = Calendar.getInstance(timeZone);
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
        JTextArea textArea = new JTextArea();
        panel.add(textArea);
        textArea.setText(city + "\n" + dateStr);
        CityClocks cityclock = new CityClocks();
        cityclock.textarea = textArea;
        cityclock.timezone = timeZone;
        cityclock.dateStr = dateStr;
        cityclock.city = city;
        cityclocks.add(cityclock);
    }
}

