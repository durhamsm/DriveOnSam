package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.image.Image;

/*
 * @author: Joo-Wang John Lee
 * This class is for setting up the Car object that will be used in the game.
 * It will make queries to the SQLite database to send data to other classes.
 */

public class Car {
	static Connection conn = null;
	private int carId;
	private String carName;
	private Image carImage;
	private String imageLocation;
	private String color;
	private String drivingImageLocation;
	private int speed;
	public static ArrayList<Car> carList = new ArrayList<Car>();
	
	public Car() {	}
	
	/*
	 * Open connection to SQLite database.
	 */	
	private static void openConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:DriveOn.db");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This function is for selecting the current car to have active in the game.
	 */
	public void selectCar(int selectedCarId) {
		try {
			openConnection();
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM car WHERE carId = ?");
			ps1.setInt(1, selectedCarId);
			ResultSet rs = ps1.executeQuery();
			
			while (rs.next()) {
				this.carId = selectedCarId;
				this.carName = rs.getString("carName");
				this.imageLocation = rs.getString("imageLocation");
				this.drivingImageLocation = rs.getString("drivingImageLocation");
				this.color = rs.getString("color");
			}
			
			
			PreparedStatement ps2 = conn.prepareStatement("UPDATE car SET isSelected = true WHERE carId = ?");
			ps2.setInt(1,  selectedCarId);
			PreparedStatement ps3 = conn.prepareStatement("UPDATE car SET isSelected = false WHERE carId <> ?");
			ps3.setInt(1,  selectedCarId);
			conn.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
	
	/*
	 * Query database to fill list of cars (useful for display and selection
	 * of car options).
	 */
	public static void fillCarList() {
		try {
			Statement stmt = null;
			String query = "SELECT * FROM car";
			openConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Car car = new Car();
				car.carId = rs.getInt("carId");
				car.carName = rs.getString("carName");
				car.imageLocation = rs.getString("imageLocation");
				car.color = rs.getString("color");
				car.drivingImageLocation = rs.getString("drivingImageLocation");
				carList.add(car);
			}
			conn.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/*
	 * Get and set methods for attributes.
	 */

	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	public void setDrivingImageLocation(String drivingImageLocation) {
		this.drivingImageLocation = drivingImageLocation;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setCarImage(Image carImage) {
		this.carImage = carImage;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	 
	public int getCarId() {
		return this.carId;
	}
	
	public String getCarName() {
		return this.carName;
	}
	
	public String getImageLocation() {
		return this.imageLocation;
	}
	
	public String getDrivingImageLocation() {
		return this.drivingImageLocation;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public Image getCarImage() {
		return this.carImage;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public static ArrayList<Car> getCarList() {
		return carList;
	}
}
