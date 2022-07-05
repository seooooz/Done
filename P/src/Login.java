import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {
	private MemberDAO dao;
	JFrame f;
	JButton bLogin, bNew;
	JLabel lid, lpw;
	JTextField id;
	JPasswordField pf; // 비밀번호 안보이게

	public Login() {
		dao = new MemberDAO();
		f = new JFrame("메인");
		f.getContentPane().setBackground(SystemColor.inactiveCaption);
		id = new JTextField();

		bLogin = new JButton("로그인");
		bNew = new JButton("회원가입");
		bLogin.addActionListener(this);
		bNew.addActionListener(this);

		pf = new JPasswordField();
		id.setBounds(82, 78, 220, 25);
		pf.setBounds(82, 118, 220, 25);
		bLogin.setBounds(28, 199, 122, 30);
		bNew.setBounds(180, 199, 122, 30);

		f.setSize(350, 300);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		f.getContentPane().add(bLogin);
		f.getContentPane().add(bNew);
		f.getContentPane().add(id);
		f.getContentPane().add(pf);
		lid = new JLabel("ID : ", JLabel.RIGHT);
		lid.setBounds(42, 83, 23, 15);
		f.getContentPane().add(lid);
		lpw = new JLabel("PW : ", JLabel.RIGHT);
		lpw.setBounds(42, 123, 30, 15);
		f.getContentPane().add(lpw);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("로그인")) {
			if (id.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
				id.grabFocus();
				return;
			} else if (pf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
				pf.grabFocus();
				return;
			} else {

				MemberVo vo = new MemberVo(id.getText(), pf.getText());
				boolean b = dao.list(vo);

				if (b == true) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "성공", JOptionPane.PLAIN_MESSAGE);
					MemberVo.userinit(vo);
					f.setVisible(false);
					new Bank();

				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패", "실패", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if (e.getActionCommand().equals("회원가입")) {
			f.setVisible(false);
			new NewLogin();
		}

	}

	public static void main(String[] args) {
		new Login();
	}
}
