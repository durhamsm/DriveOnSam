package Objects;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

/*
 * @author: Joo-Wang John Lee
 * This class is for setting up the SQLite database for the application.
 */

public class DatabaseCreate {
	static Connection conn = null;
	
	public DatabaseCreate() {
		
	}
	
	/*
	 * Set up all tables.
	 */
	public void execute() {
		setupCarTable();
		setupSignTable();
		setupLevelTable();
		setupConfigLevelSignTable();
		writeQuizFile();
	}
	
	/*
	 * Open JDBC connection to link to SQLite game database.
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
	 * Create table to store car information if it does not exist
	 * and re-insert data into the car information table.
	 */
	private static void setupCarTable() {
		try {
			openConnection();
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:DriveOn.db");
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			String query = new String();
			
			DatabaseMetaData dbm = conn.getMetaData();
		    ResultSet rs = dbm.getTables(null, null, "car", null);
			
		    if (!rs.next()) {			
				query = "CREATE TABLE car " +
		                   	   "(carId INT PRIMARY KEY     NOT NULL," +
		                   	   " carName           VARCHAR(45), " + 
		                   	   " imageLocation     VARCHAR(100), " + 
		                   	   " drivingImageLocation     VARCHAR(100), " + 
		                   	   " color     		   VARCHAR(45), " + 
		                   	   " isSelected        BIT)"; 
				stmt.executeUpdate(query);
		    }
		    
		    PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM car");
		    rs = ps1.executeQuery();
			
		    if (!rs.next()) {
				query = "INSERT INTO car " +
						"(carId, " +
						" carName, " +
						" imageLocation, " +
						" drivingImageLocation, " +
						" color, " +
						" isSelected) " +
						"VALUES " +
						"(1, " +
						" 'Blue car', " +
						" 'bluecar.jpg'," +
						" 'blueCar.png'," +
						" 'blue'," +
						" 0 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO car " +
						"(carId, " +
						" carName, " +
						" imageLocation, " +
						" drivingImageLocation, " +
						" color, " +
						" isSelected) " +
						"VALUES " +
						"(2, " +
						" 'Red car', " +
						" 'redcar.jpg'," +
						" 'redCar.png'," +
						" 'red'," +
						" 0 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO car " +
						"(carId, " +
						" carName, " +
						" imageLocation, " +
						" drivingImageLocation, " +
						" color, " +
						" isSelected) " +
						"VALUES " +
						"(3, " +
						" 'Yellow car', " +
						" 'yellowcar.jpg'," +
						" 'yellowCar.png'," +
						" 'yellow'," +
						" 0 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO car " +
						"(carId, " +
						" carName, " +
						" imageLocation, " +
						" drivingImageLocation, " +
						" color, " +
						" isSelected) " +
						"VALUES " +
						"(4, " +
						" 'White car', " +
						" 'whitecar.jpg'," +
						" 'whiteCar.png'," +
						" 'white'," +
						" 0 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO car " +
						"(carId, " +
						" carName, " +
						" imageLocation, " +
						" drivingImageLocation, " +
						" color, " +
						" isSelected) " +
						"VALUES " +
						"(5, " +
						" 'Black car', " +
						" 'blackcar.jpg'," +
						" 'blackCar.png'," +
						" 'black'," +
						" 0 )";
				stmt.executeUpdate(query);
		    }
			
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * Create table to store sign information if it does not exist
	 * and re-insert data into the sign information table.
	 */
	private static void setupSignTable() {
		try {
			openConnection();
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:DriveOn.db");
			conn.setAutoCommit(true);
			String query = new String();			
			stmt = conn.createStatement();
			
			DatabaseMetaData dbm = conn.getMetaData();
		    ResultSet rs = dbm.getTables(null, null, "sign", null);
			
		    if (!rs.next()) {
				query = "CREATE TABLE sign " +
		                   	   "(signId 			INT PRIMARY KEY     NOT NULL," +
		                   	   " signName           VARCHAR(45), " + 
		                   	   " description        VARCHAR(100), " + 
		                   	   " signSpeed			INT," +
		                   	   " imageLocation 		VARCHAR(45), " +
		                   	   " signPoints         INT)";
				stmt.executeUpdate(query);
		    }
		    
		    PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM sign");
		    rs = ps1.executeQuery();
				
		    if (!rs.next()) {
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(1, " +
						" 'Stop', " +
						" 'Stop sign'," +
						" 0," +
						" 'Stop-Sign.gif'," +
						" 5 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(2, " +
						" 'Yield', " +
						" 'Yield sign'," +
						" 30," +
						" 'Yield.gif'," +
						" 5 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(3, " +
						" 'Speed Limit 70', " +
						" 'Speed limit 70 MPH'," +
						" 70," +
						" 'speed-limit-70.png'," +
						" 5 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(4, " +
						" 'Speed Limit 55', " +
						" 'Speed limit 55 MPH'," +
						" 55," +
						" 'speed-limit-55.gif'," +
						" 5 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(5, " +
						" 'Speed Limit 45', " +
						" 'Speed limit 45 MPH'," +
						" 45," +
						" 'speed-limit-45.jpg'," +
						" 5 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(6, " +
						" 'Speed Limit 30', " +
						" 'Speed limit 30 MPH'," +
						" 30," +
						" 'speed-limit-30.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(7, " +
						" 'Speed Limit 25', " +
						" 'Speed limit 25 MPH'," +
						" 25," +
						" 'speed-limit-25.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(8, " +
						" 'Speed Limit 20', " +
						" 'Speed limit 20 MPH'," +
						" 20," +
						" 'speed-limit-20.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(9, " +
						" 'School bus stop', " +
						" 'School bus stop sign'," +
						" 20," +
						" 'school-bus-stop.png'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(10, " +
						" 'Pedestrian crosswalk', " +
						" 'Pedestrian crosswalk sign'," +
						" 30," +
						" 'crosswalk.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(11, " +
						" 'Construction ahead', " +
						" 'Construction ahead sign'," +
						" 45," +
						" 'construction-ahead.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO sign " +
						"(signId, " +
						" signName, " +
						" description, " +
						" signSpeed, " +
						" imageLocation, " +
						" signPoints) " +
						"VALUES " +
						"(12, " +
						" 'Flagger', " +
						" 'Flagger sign'," +
						" 30," +
						" 'flagger.jpg'," +
						" 10 )";
				stmt.executeUpdate(query);
		    }
			
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * Create table to store level information if it does not exist
	 * and re-insert data into the level information table.  There
	 * are three levels: Easy, Medium and Hard.
	 */
	private static void setupLevelTable() {
		try {
			openConnection();
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:DriveOn.db");
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			String query = new String();
			
			DatabaseMetaData dbm = conn.getMetaData();
		    ResultSet rs = dbm.getTables(null, null, "level", null);
		    
		    if (!rs.next()) {			
				query = "CREATE TABLE level " +
		                   	   "(levelId 		   INT PRIMARY KEY     NOT NULL, " +
		                   	   " description       VARCHAR(100))"; 
				stmt.executeUpdate(query);
		    }
		    
		    PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM level");
		    rs = ps1.executeQuery();
			
		    if (!rs.next()) {
		    	query = "INSERT INTO level " +
						"(levelId, " +
						" description) " +
						"VALUES " +
						"(0, " +
						" 'No level passed')";
				stmt.executeUpdate(query);
		    	
				query = "INSERT INTO level " +
						"(levelId, " +
						" description) " +
						"VALUES " +
						"(1, " +
						" 'Amateur')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO level " +
						"(levelId, " +
						" description) " +
						"VALUES " +
						"(2, " +
						" 'Mediocre')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO level " +
						"(levelId, " +
						" description) " +
						"VALUES " +
						"(3, " +
						" 'Professional')";
				stmt.executeUpdate(query);
		    }
			
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * Create table to store level configuration information if it does not exist
	 * and re-insert data to associate level with the signs to display at each 
	 * level of difficulty.
	 */
	private static void setupConfigLevelSignTable() {
		int configCount = 0;		
		Random random = new Random();
		
		try {
			openConnection();
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:DriveOn.db");
			conn.setAutoCommit(true);
			String query = new String();
			stmt = conn.createStatement();
			
			DatabaseMetaData dbm = conn.getMetaData();
		    ResultSet rs = dbm.getTables(null, null, "configLevelSign", null);
			
		    if (!rs.next()) {
				query = "CREATE TABLE configLevelSign " +
		                   	   "(configLevelSignId 	INT PRIMARY KEY     NOT NULL," +
		                   	   " levelId       		INT," +
		                   	   " signId				INT)"; 
				stmt.executeUpdate(query);
		    }
		    
		    PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM configLevelSign");
		    rs = ps1.executeQuery();	    
			
		    if (!rs.next()) {
			    // For easy level, add Stop, Yield, Speed limit 70, 55 and 45 MPH signs.
				for (int i = 1 ; i <= 5 ; i++) {
					configCount++;
					query = "INSERT INTO configLevelSign " +
							"(configLevelSignId, " +
							" levelId, " +
							" signId) " +
							"VALUES " +
							"(" + String.valueOf(configCount) + ", " +
							" 1, " +
							String.valueOf(i) + ")";
					stmt.executeUpdate(query);
			    }
				
				// For medium level, add Stop, Yield, Speed limit 70, 55, 45, 30, 25 and 20
				// MPH signs, school bus stop and pedestrian crosswalk signs.
				for (int i = 1 ; i <= 10 ; i++) {					
					configCount++;
					query = "INSERT INTO configLevelSign " +
							"(configLevelSignId, " +
							" levelId, " +
							" signId) " +
							"VALUES " +
							"(" + String.valueOf(configCount) + ", " +
							" 2, " +
							String.valueOf(i) + ")";
					stmt.executeUpdate(query);
			    }
				
				// For hard level, add Stop, Yield, Speed limit 45, 30, 25 and 20 MPH signs, 
				// school bus stop, pedestrian crosswalk signs, construction zone and flagger
				// signs, all randomized to total 22 signs.
				for (int i = 1 ; i <= 10 ; i++) {					
					int signId = (i <= 2) ? random.nextInt(2) + 1 :random.nextInt(8) + 5;
					
					configCount++;
					query = "INSERT INTO configLevelSign " +
							"(configLevelSignId, " +
							" levelId, " +
							" signId) " +
							"VALUES " +
							"(" + String.valueOf(configCount) + ", " +
							" 3, " +
							String.valueOf(signId) + ")";
					stmt.executeUpdate(query);
			    }
				
				configCount++;
				query = "INSERT INTO configLevelSign " +
						"(configLevelSignId, " +
						" levelId, " +
						" signId) " +
						"VALUES " +
						"(" + String.valueOf(configCount) + ", " +
						" 3, " +
						" 11)";
				stmt.executeUpdate(query);
				
				for (int i = 1 ; i <= 10 ; i++) {
					int signId = (i <= 2) ? random.nextInt(2) + 1 :random.nextInt(8) + 5;
					
					configCount++;
					query = "INSERT INTO configLevelSign " +
							"(configLevelSignId, " +
							" levelId, " +
							" signId) " +
							"VALUES " +
							"(" + String.valueOf(configCount) + ", " +
							" 3, " +
							String.valueOf(signId) + ")";
					stmt.executeUpdate(query);
			    }
				
				configCount++;
				query = "INSERT INTO configLevelSign " +
						"(configLevelSignId, " +
						" levelId, " +
						" signId) " +
						"VALUES " +
						"(" + String.valueOf(configCount) + ", " +
						" 3, " +
						" 12)";
				stmt.executeUpdate(query);
		    }
			
			stmt.close();		    
			conn.close();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void writeQuizFile() {
		File f = new File("traffic-quiz.xml");

		if (!f.exists()) {		
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
				// root elements
				Document doc = docBuilder.newDocument();
				Element quiz = doc.createElement("quiz");
				doc.appendChild(quiz);
			
				Element question = doc.createElement("question");
				quiz.appendChild(question);
			
				Element qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("1"));
				question.appendChild(qId);
			
				Element qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("What should you do when you see this sign?"));
				question.appendChild(qText);
				
				Element qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode("stop.png"));
				question.appendChild(qImg);
				
				Element option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Stop"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Stop and proceed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow down and proceed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Race ahead"));
				question.appendChild(option);
				
				Element correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("2"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("What should you do when you see this sign?"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode("yield.png"));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Stop"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Stop and proceed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow down and proceed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Race ahead"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("3"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When you enter a roundabout or a circular intersection, you must proceed:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("in a clockwise direction"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("in the opposite direction"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("in a counter clockwise direction"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("towards the left"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
				
	
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("4"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If a tornado is nearby, you must:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("drive your car with maximum speed to avoid it"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("take a detour to the top of the bridge"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("close all the doors and turn on your headlights"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stop, exit your car immediately, go to a low-lying area"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
	
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("5"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("With any turning vehicle, the rear wheels follow a _________ than the front wheels."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("slower path"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("faster path"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("longer path"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("shorter path"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
	
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("6"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If you are driving near a school, you must slow down to _________ for the school zone."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("10 mph below the established speed limit"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("the lowest speed limit"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("35 mph"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("45 mph"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("2"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("7"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("Defensive drivers stay _________ behind the vehicle ahead while driving."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least two to three seconds"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least five seconds"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least four seconds"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least nine to ten seconds"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("8"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If the speed limit is 50 mph or greater, you must give proper turn signal ________ before turning or changing lanes."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least 100 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least 50 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least 200 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("at least 300 feet"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("9"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("A red and white triangular sign at an intersection means"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow down if an emergency vehicle is approaching."));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Look both ways as you cross the intersection."));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Always come to a full stop at the intersection."));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow down and be prepared to stop if necessary."));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("10"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("What should you do if fog closes in completely while driving, and visibility is reduced to near zero?"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Increase the speed and take a detour"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Use low-beams"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Use high-beams"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Carefully pull as far off the road as possible, and stop"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("11"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("In a situation with a flat tire or blow-out, you should first:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("hold the steering wheel firmly"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("not apply brakes"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("not slow your car"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("not take your foot off the gas pedal"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("12"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("The likelihood of an accident increases if a driver is under the influence of:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("softly playing music"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a cup of tea"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("alcohol, drugs or prescription medication"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a cup of coffee"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("13"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("The first rule of a safe and legal turn is:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("to reduce the speed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("to cut corners while turning"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("to move into the proper lane well before the turn"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("to increase the speed"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("14"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("Indiana law requires the driver and all passengers to use seat belts at all times when a vehicle is:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("in operation"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("moving at a speed greater than 50 mph"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("moving on highways"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("moving at a speed greater than 35 mph"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("15"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When visibility is diminished, drivers must use low-beams when:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("within 200 feet of an oncoming vehicle"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("approaching a railroad crossing at night"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("within 500 feet of an oncoming vehicle"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("approaching an intersection or traffic island at night"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("16"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("The maximum speed limit in a highway work zone must not exceed:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("45 mph in any location"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("55 mph in any location"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("70 mph in any location"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("60 mph in any location"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
	
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("17"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When driving in urban residential areas, vehicles may not exceed:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("60 mph, or the posted speed limit"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("45 mph, or the posted speed limit"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("50 mph"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("30 mph, or the posted speed limit"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
	
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("18"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("A yield sign indicates that a driver must slow down when approaching an intersection and _________ if a vehicle or pedestrian with the right-of-way is approaching from another direction."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("be prepared to make a turn"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("be prepared to come to a complete stop"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("be prepared to increase the speed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("be prepared to make a U-turn"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("2"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("19"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("Flashing arrow boards are often used to indicate:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a detour ahead"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a stop sign ahead"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("no entry ahead"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a yield sign ahead"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("20"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("Which of the following statements is true when driving on highways?"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Fast-moving vehicles must enter the highway through the deceleration lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow-moving vehicles should use the left lane and fast-moving vehicles should use right lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Slow-moving vehicles should use the right lane and fast-moving vehicles should use left lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("Fast-moving vehicles should overtake slower vehicles from right side"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("21"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When operating a vehicle with a steering wheel interlock system, never turn _________ while the vehicle is in motion."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("the ignition to the lock position"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("the headlights into the on position"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("the engine into the off position"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("the gear into the higher position"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("22"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("You must avoid _________ while crossing railroad tracks."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stopping or shifting gears"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("using high-beam lights"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("using brakes"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("using low-beam lights"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
				
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("23"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If you are involved in an accident, you must:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stop and provide information to others involved in the accident"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("leave the scene of an accident"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stop other vehicles on the road"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("not provide information to others involved in the accident"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("24"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When you see a stationary emergency vehicle with flashing lights, you must:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("continue with the same speed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("increase the speed of your vehicle"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("move into the left lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("slow down and move into a lane that is not adjacent to the emergency vehicle"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("25"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If another vehicle is passing you on the left-hand side of the road, allow the other vehicle to pass safely, and:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("do not increase your speed"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("pull your vehicle to the left lane of the road"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("pull your vehicle to the right edge of the road"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("increase your speed to follow the vehicle"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("26"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("It is dangerous and illegal to try to pass other vehicles when _________ of an intersection, railroad crossing, bridge, viaduct or tunnel."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("you are within 200 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("you are within 500 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("you are within 100 feet"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("you are within 600 feet"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("3"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("27"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If you are approaching an intersection with a non-operating signal, you should:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("slow down at the intersection and proceed with caution"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stop before entering the intersection"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("make a right turn at the intersection"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("stop after entering the intersection"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("2"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("28"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("If your vehicle's conventional disc or drum brakes suddenly fail, you should immediately:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("shift to a higher gear, if possible, and not pump the brake pedal"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("pull off the road and stop"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("shift to a higher gear, if possible, and pump the brake pedal fast and hard several times"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("shift to a low gear, if possible, and pump the brake pedal fast and hard several times"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("29"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("A red flashing light at an intersection is equivalent to:"));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a flashing yellow light"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a yield sign"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a 'do not enter' sign"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("a stop sign"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("4"));
				question.appendChild(correctAnswer);
			
				question = doc.createElement("question");
				quiz.appendChild(question);
			
				qId = doc.createElement("qId");
				qId.appendChild(doc.createTextNode("30"));
				question.appendChild(qId);
			
				qText = doc.createElement("qText");
				qText.appendChild(doc.createTextNode("When you enter an interstate on-ramp, you must stay to the right and _________ to allow your car to merge with traffic."));
				question.appendChild(qText);
				
				qImg = doc.createElement("qImg");
				qImg.appendChild(doc.createTextNode(""));
				question.appendChild(qImg);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("increase your speed in the acceleration lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("increase your speed in the deceleration lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("decrease your speed in the acceleration lane"));
				question.appendChild(option);
				
				option = doc.createElement("option");
				option.appendChild(doc.createTextNode("decrease your speed in the deceleration lane"));
				question.appendChild(option);
				
				correctAnswer = doc.createElement("correctAnswer");
				correctAnswer.appendChild(doc.createTextNode("1"));
				question.appendChild(correctAnswer);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("traffic-quiz.xml"));			
				transformer.transform(source, result);
			
			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}
	}
}
