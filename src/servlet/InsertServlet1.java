package pac1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet1
 */
@WebServlet("/InsertServlet1")
public class InsertServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int subjectID = 0;
		String subjectName = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String url = "jdbc:mysql://localhost:3306/sampledb?characterEncoding=UTF-8&serverTimezone=JST";
		String userName = "user";
		String password = "password";
		int ret = 0;
		response.setContentType("text/html; charset=UTF-8");

		try {
			subjectID = Integer.parseInt(request.getParameter("subjectID"));
			subjectName = request.getParameter("subjectName");
			Class.forName("com.mysql.jdbc.Driver"); // JDBCドライバのロード
			con = DriverManager.getConnection(url, userName, password);
			stmt = con.createStatement();
			ret = stmt.executeUpdate("INSERT INTO SUBJECT VALUES(" + subjectID + ",\'" + subjectName + "\')");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("JDBCドライバがロードできません。");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {

				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch(SQLException sqlEx) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException sqlEX) {

				}
			}
		}


		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>練習6-3</title>");
		out.println("<h1>練習6-3</h1>");
		out.println("<h2>1件追加しました。</h2>");
		out.println("</head>");
		out.println("<body>");
		out.println("科目ID: " + subjectID);
		out.println("<br>");
		out.println("科目名: " + subjectName);
		out.println("</body>");
		out.println("</html>");

	}

}
