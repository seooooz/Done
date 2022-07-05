import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Check2 implements ActionListener {
	JFrame f;
	JButton bMain;
	JLabel lid, ltxid, ltxacc;
	private JTable table;
	BankIdAccountDAO dao;

	public Check2() {
		dao = new BankIdAccountDAO();
		dao.list(MemberVo.user);
		ltxid = new JLabel(MemberVo.user.getId());
		ltxacc = new JLabel(dao.account);
//		ltxid.setHorizontalTextPosition(JLabel.RIGHT);
//		ltxacc.setHorizontalTextPosition(JLabel.RIGHT);
		
		f = new JFrame("메인");

		bMain = new JButton("\uBA54\uC778");
		bMain.addActionListener(this);

		ltxid.setBounds(254, 15, 65, 25);
		ltxacc.setBounds(247, 41, 72, 25);
		bMain.setBounds(12, 12, 57, 30);

		f.setSize(350, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		f.getContentPane().add(bMain);
		f.getContentPane().add(ltxid);
		f.getContentPane().add(ltxacc);
		lid = new JLabel("ID : ", JLabel.RIGHT);
		lid.setBounds(211, 17, 32, 20);
		f.getContentPane().add(lid);

		JLabel linout = new JLabel("\uCD5C\uADFC \uC785\uCD9C\uAE08 \uB0B4\uC5ED");
		linout.setFont(new Font("굴림", Font.PLAIN, 14));
		linout.setBounds(108, 126, 117, 30);
		f.getContentPane().add(linout);

		table = new JTable();
		table.setBounds(289, 514, -245, -331);
		f.getContentPane().add(table);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메인")) {

			new Bank();
			f.setVisible(false);
		}

	}

	public static void main(String[] args) {
		new Check2();
	}
}
