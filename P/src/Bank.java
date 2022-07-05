import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Bank implements ActionListener {
	JFrame f;
	JButton bCheck, bTrans;
	JLabel lid, ltxid, lacc, textField;
//	private JTextField textField;
	BankIdAccountDAO dao;
	
	public Bank() {

		// id랑 account 불러오기 (MemberVo static 메소드 사용)
		dao = new BankIdAccountDAO();
		dao.list(MemberVo.user);
		dao.balan(MemberVo.user);
		ltxid = new JLabel(MemberVo.user.getId());
		lacc = new JLabel(dao.account);
		
		f = new JFrame("ZBank");
		f.getContentPane().setBackground(SystemColor.inactiveCaption);
		
		ltxid.setFont(new Font("굴림", Font.BOLD, 14));
		lacc.setFont(new Font("굴림", Font.BOLD, 14));

		bCheck = new JButton("조회");
		bTrans = new JButton("이체");
		bCheck.addActionListener(this);
		bTrans.addActionListener(this);

		ltxid.setBounds(41, 5, 82, 25);
		lacc.setBounds(240, 5, 82, 25);
		bCheck.setBounds(98, 145, 122, 30);
		bTrans.setBounds(180, 199, 122, 30);

		f.setSize(350, 300);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		f.getContentPane().add(bCheck);
		f.getContentPane().add(bTrans);
		f.getContentPane().add(ltxid);
		f.getContentPane().add(lacc);
		lid = new JLabel("ID ", JLabel.RIGHT);
		lid.setFont(new Font("굴림", Font.BOLD, 12));
		lid.setBounds(12, 10, 23, 15);
		f.getContentPane().add(lid);

		textField = new JLabel(Integer.toString(dao.balance)); 
		textField.setBounds(55, 105, 193, 30);
		f.getContentPane().add(textField);

		JLabel lwon = new JLabel("\uC6D0");
		lwon.setForeground(new Color(0, 0, 0));
		lwon.setFont(new Font("굴림", Font.BOLD, 16));
		lwon.setBounds(253, 104, 34, 30);
		f.getContentPane().add(lwon);
		
		JButton bTrans_1 = new JButton("채우기");
		bTrans_1.setBounds(41, 199, 122, 30);
		f.getContentPane().add(bTrans_1);
		bTrans_1.addActionListener(this);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("조회")) {
			new Check();
			f.setVisible(false);
		}
		if (e.getActionCommand().equals("이체")) {
			f.setVisible(false);
			new Trans();
		}
		if (e.getActionCommand().equals("채우기")) {
			f.setVisible(false);
			new Deposit();
		}

	}

	public static void main(String[] args) {
		new Bank();
	}
}