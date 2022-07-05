import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Check implements ActionListener {
	JFrame f;
	private JTable table;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@220.94.225.46:1521:xe";
	String user = "c##green";
	String password = "green1234";

	Connection conn = null;
	PreparedStatement pstmt = null;
	private Statement stmt;
	private ResultSet rs;
	BankIdAccountDAO dao = new BankIdAccountDAO();

	public Check() {
		dao = new BankIdAccountDAO();
		dao.list(MemberVo.user);
		
		f = new JFrame("메인");

		f.setSize(350, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table = new JTable());
		scrollPane.setBounds(0, 65, 334, 487);
		f.getContentPane().add(scrollPane);
		f.setVisible(true);
		
		try {
			dao.list(MemberVo.user);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(dao.account);
			String sql = "SELECT id, tstype, cash, tsdate FROM USERMEMBER, TSHISTORY "
					+ "WHERE usermember.account= tshistory.receiver "
					+ "AND sender = '" + dao.account + "'"
					+ "ORDER BY tsdate DESC ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String[] columnNames = {"ID", "유형", "금액", "거래 날짜"};
			
			ArrayList<ArrayList<String>> imsiData = new ArrayList<ArrayList<String>>();
			
			if(rs.next()) {
				do {
					ArrayList<String> temp = new ArrayList<String>();
					
					temp.add(rs.getString(1));
					temp.add(rs.getString(2));
					temp.add(rs.getString(3));
					temp.add(rs.getString(4));
					
					imsiData.add(temp);
					
				}while(rs.next());
			}
			rs.close();
			
			String[][] data = new String[imsiData.size()][4];
			
			for(int i = 0; i < imsiData.size(); i++) {
				ArrayList<String> temp = imsiData.get(i);
				for(int j = 0; j < temp.size(); j++) {
					data[i][j] = temp.get(j);
				}
			}
			
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			
			table.setModel(model);
			table.updateUI();
			
		}

		catch (Exception e) {
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

	public static void main(String[] args) {
		new Check();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
