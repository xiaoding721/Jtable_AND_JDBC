package com.eaglejump.view;

import com.eaglejump.service.Impl.AdminImpl;
import com.eaglejump.service.Impl.BusinessImpl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Register extends JFrame {
	
	private JTextField businessName;
	private JPasswordField password;
	private JPasswordField repassword;
	
	
	/**
	 * Launch the application.
	 */
	private static Register register;
	protected static void getRegister() {
		if(register == null){
			register = new Register();
		}
		register.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	private Register() {
		
		KeyListener key_Listener = new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					if(Arrays.equals(password.getPassword(), repassword.getPassword())){
						BusinessImpl business = new BusinessImpl();
						business.Insert(businessName.getText(),password.getPassword());
					}
					Login.getLogin();
					dispose();
					register = null;
				}
			}
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("注册");
		//注册
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(e -> {
			if(Arrays.equals(password.getPassword(), repassword.getPassword())){
				BusinessImpl business = new BusinessImpl();
				business.Insert(businessName.getText(),password.getPassword());
			}
			Login.getLogin();
			dispose();
			register = null;
		});
		button.setBounds(158, 229, 93, 23);
		contentPane.add(button);
		
		businessName = new JTextField();
		businessName.setBounds(136, 48, 197, 21);
		contentPane.add(businessName);
		businessName.setColumns(10);
		
		//商家名称
		JLabel label = new JLabel("\u5546\u5BB6\u540D\u79F0");
		label.setBounds(74, 51, 54, 15);
		contentPane.add(label);
		
		password = new JPasswordField();
		password.setBounds(136, 79, 197, 21);
		contentPane.add(password);
		
		repassword = new JPasswordField();
		repassword.setBounds(136, 110, 197, 21);
		contentPane.add(repassword);
		
		//密码
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(74, 82, 54, 15);
		contentPane.add(label_1);
		
		//确认密码
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setBounds(74, 113, 54, 15);
		contentPane.add(label_2);
	}

}
