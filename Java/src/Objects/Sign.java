package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import org.apache.log4j.Logger;

/*
 * @author: Joo-Wang John Lee
 * This class is for setting up the Sign object that will be used in the game.
 * It will make queries to the SQLite database to send data to other classes.
 */

public class Sign {
	private static final Logger logger = Logger.getLogger("Sign");
	static Connection conn = null;
	private int signId;
	private String signName;
	private String description;
	private int signSpeed;
	private String imageLocation;
	private Image signImage;
	private int signPoints;
	public static ArrayList<Sign> signList = new ArrayList<Sign>();
	
	public Sign() {	}
	
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
	 * Select sign to use.
	 */
	public void selectSign(int selectedSignId) {
		openConnection();
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM sign WHERE signId = ?");
			ps1.setInt(1, selectedSignId);
			ResultSet rs = ps1.executeQuery();
			
			while (rs.next()) {
				this.signId = selectedSignId;
				this.signName = rs.getString("signName");
				this.description = rs.getString("description");
				this.signSpeed = rs.getInt("signSpeed");
				this.imageLocation = rs.getString("imageLocation");
				this.signImage = new Image(imageLocation);
				this.signPoints = rs.getInt("signPoints");
			}
			
			PreparedStatement ps2 = conn.prepareStatement("UPDATE sign SET isSelected = true WHERE signId = ?");
			ps2.setInt(1,  selectedSignId);
			PreparedStatement ps3 = conn.prepareStatement("UPDATE sign SET isSelected = false WHERE signId <> ?");
			ps3.setInt(1,  selectedSignId);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
	
	/*
	 * Query database to fill list of signs.
	 */
	public static List<Sign> fillSignList() {
		Statement stmt = null;
		openConnection();
		String query = "SELECT * FROM sign";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Sign sign = new Sign();
				sign.signId = rs.getInt("signId");
				sign.signName = rs.getString("signName");
				sign.description = rs.getString("description");
				sign.signSpeed = rs.getInt("signSpeed");
				sign.imageLocation = rs.getString("imageLocation");
				sign.signImage = new Image(sign.imageLocation);
				sign.signPoints = rs.getInt("signPoints");
				signList.add(sign);
			}
		} catch (SQLException ex) {
			logger.info("SQLException: " + ex.getMessage());
			logger.info("SQLState: " + ex.getSQLState());
			logger.info("VendorError: " + ex.getErrorCode());
		}

		return signList;
	}
	
	/*
	 * Get and set methods for attributes.
	 */
	
	public int getSignId() {
		return this.signId;
	}
	
	public String getSignName() {
		return this.signName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getSignSpeed() {
		return this.signSpeed;
	}
	
	public String getImageLocation() {
		return this.imageLocation;
	}
	
	public int getSignPoints() {
		return this.signPoints;
	}
	
	public Image getSignImage() {
		return this.signImage;
	}
	
	public static ArrayList<Sign> getSignList() {
		return signList;
	}
	
	public void setSignId(int signId) {
		this.signId = signId;
	}
	
	public void setSignName(String signName) {
		this.signName = signName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setSignSpeed(int signSpeed) {
		this.signSpeed = signSpeed;
	}
	
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	public void setSignPoints(int signPoints) {
		this.signPoints = signPoints;
	}
	
	public void setSignImage(Image signImage) {
		this.signImage = signImage;
	}
}
