import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Session extends ActionSupport implements SessionAware{

	private String username;
	private String password;
	private String libNumber;
	private String userType;
	private String u, p;
	
	private String bookTitle;
	private String bookAuthor;
	private String isbnNum;
	
	private String srchBook;
	private String bt;
	
	private Map<String, Object> session;
	
	public Session() {}
	
	public String login() {
		
		String outcome = null;
		String type = "";
		
		if(this.libNumber.charAt(0) == '1') {
			
			type = "Librarian";
			
		}
		else if(this.libNumber.charAt(0) == '2') {
			
			type = "Member";
			
		}
		
		this.userType = type;
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			
			while(rs.next()) {
				 	
				 u = rs.getString(1);
				 p = rs.getString(2);
				 
				 if(username.equalsIgnoreCase(u) && password.equalsIgnoreCase(p) && this.userType.equals("Librarian")) {
					 System.out.println("Matched");
					 session.put("currentUser", username);
					 outcome = "LIB";
					 break;
					 	
					}
				else if(username.equalsIgnoreCase(u) && password.equalsIgnoreCase(p) && this.userType.equals("Member")) { 
					 session.put("currentUser", username);
					 outcome = "MEM";
					 break;
				    }
				 else {
					 
					 outcome = "FAIL";
					 
				 }
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return outcome;
		
	}
	
	public String addBook() {
		
		String outcome = "FAIL";
		
		 Connection insert = null;
			try {
				
				insert = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");	
				PreparedStatement createUser = insert.prepareStatement("insert into book (title, author, isbn) values (?,?,?)");
				
				createUser.setString(1, bookTitle);
				createUser.setString(2, bookAuthor);
				createUser.setString(3, isbnNum);
				
				int userUpdate = createUser.executeUpdate();
				createUser.close();
				
				session.put("addedBook", bookTitle);
				System.out.println(session.get("addedBook"));
				outcome = "SUCCESS";
								
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return outcome;
	}
	
	public String searchBook() {
		
		String outcome = null;
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
			
			while(rs.next()) {
			
				 	
				 bt = rs.getString(1);
			
			
				 if(srchBook.equalsIgnoreCase(bt)) {
					 session.put("bookSearched", srchBook);
					 System.out.println(session.get("bookSearched"));
					 outcome = "SUCCESS";
					 	
					}
				 else {
					 
					 outcome = "FAIL";
					 
				 }
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return outcome;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLibNumber() {
		return libNumber;
	}

	public void setLibNumber(String libNumber) {
		this.libNumber = libNumber;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getIsbnNum() {
		return isbnNum;
	}

	public void setIsbnNum(String isbnNum) {
		this.isbnNum = isbnNum;
	}

	public String getSrchBook() {
		return srchBook;
	}

	public void setSrchBook(String srchBook) {
		this.srchBook = srchBook;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map map) {
		session = map;
		
	}

}
