import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@220.94.225.46:1521:xe";
		String user = "c##green";
		String password = "green1234";

		Connection conn = null;
		PreparedStatement pstmt = null;
//		private Statement stmt;
		ResultSet rs;

		String account;
		String myaccount;
		MemberVo vo = new MemberVo();
		try {

			BankIdAccountDAO dao = new BankIdAccountDAO();
//			dao.list(MemberVo.user);
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT account FROM usermember WHERE id='" + MemberVo.user.getId() + "'";
			System.out.println(MemberVo.user.getId());

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				myaccount = rs.getString("account");
				System.out.println(myaccount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
