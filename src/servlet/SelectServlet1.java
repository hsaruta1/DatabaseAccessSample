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
 * Servlet implementation class SelectServlet1
 */
@WebServlet("/SelectServlet1")
public class SelectServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String url = "jdbc:mysql://localhost:3306/sampledb?characterEncoding=UTF-8&serverTimezone=JST";
		//String url = "jdbc:mysql://localhost:3306/sampledb";
		String userName = "user";
		String password = "password";
		response.setContentType("text/html; charset=UTF-8");

		try {
			Class.forName("com.mysql.jdbc.Driver"); // JDBCドライバのロード
			con = DriverManager.getConnection(url, userName, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SUBJECT");

			while (rs.next()) { // 行の内容取り出しを繰り返し
				// 列の内容取り出し
				String s = "<td>" + rs.getInt("SUBJECTID") + "</td>"
						+ "<td>" + rs.getString("SUBJECTNAME") + "</td>";

				list.add(s);  // レコードをリストに格納
			}

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
		out.println("<title>練習問題6_1</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("<th>科目ID</th>");
		out.println("<th>科目名</th>");
		out.println("</tr>");
		for (String str : list) {
			out.println("<tr>" + str + "</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
