package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * @author: Joo-Wang John Lee
 * This class is for setting up the Level object that will be used in the game.
 * It will make queries to the SQLite database to send data to other classes.
 */

public class Level {
	static Connection conn = null;
	private int levelId;
	private String levelDescription;
	private ArrayList<Sign> signList;
	private static ArrayList<Level> levelList;
	
	public Level() {
	}
	
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
	 * This function is for selecting the current level to have active in the game.
	 * Also, each level has a configuration of signs based on difficulty and this 
	 * function will query the database to fill the list of signs to display.
	 */
	public void selectLevel(int selectedLevelId) {
		openConnection();
		this.signList = new ArrayList<Sign>();
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM level WHERE levelId = ?");
			ps1.setInt(1, selectedLevelId);
			ResultSet rs = ps1.executeQuery();
			
			while (rs.next()) {
				this.levelDescription = rs.getString("description");
			}
			
			// Fill in list of sign configurations for the level.			
			PreparedStatement ps2 = conn.prepareStatement("SELECT sign.* FROM sign INNER JOIN configLevelSign "
														+ "ON sign.signId = configLevelSign.signId "
														+ "WHERE configLevelSign.levelId = ?");
			ps2.setInt(1, selectedLevelId);
			rs = ps2.executeQuery();
			
			while (rs.next()) {
				Sign sign = new Sign();
				sign.setSignId(rs.getInt("signId"));
				sign.setDescription(rs.getString("description"));
				sign.setSignName(rs.getString("signName"));
				sign.setSignPoints(rs.getInt("signPoints"));
				sign.setSignSpeed(rs.getInt("signSpeed"));
				sign.setImageLocation(rs.getString("imageLocation"));
				
				signList.add(sign);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
	
	/*
	 * Query database to fill list of levels.
	 */
	public static void fillLevelList() {
		Statement stmt = null;
		openConnection();
		String query = "SELECT * FROM level";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Level level = new Level();
				level.levelId = rs.getInt("levelId");
				level.levelDescription = rs.getString("description");
				levelList.add(level);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/*
	 * Get and set methods for attributes.
	 */
	
	public int getLevelId() {
		return this.levelId;
	}
	
	public String getLevelDescription() {
		return this.levelDescription;
	}
	
	public ArrayList<Level> getLevelList() {
		return levelList;
	}
	
	public ArrayList<Sign> getSignList() {
		return signList;
	}
	
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
}
