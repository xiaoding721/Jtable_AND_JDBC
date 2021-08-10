package com.eaglejump.view;

import com.eaglejump.service.Impl.AdminImpl;
import com.eaglejump.service.Impl.BusinessImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JFrame {
	
	private final JPasswordField password;
	private final JTextField adminName;
	
	private static Login login;
	public static void getLogin() {
		if(login==null){
			login = new Login();
		}
		login.setVisible(true);
	}
	
	private void login(BusinessImpl business, AdminImpl admin){
		String user = adminName.getText();
		char[] pwd = password.getPassword();
		if(user.equals("")||new String(pwd).equals("")){
			JOptionPane.showMessageDialog(null, "用户名或密码错误，请检查您的输入", "用户名或密码错误", JOptionPane.ERROR_MESSAGE);
		}else if(business.Login(user,pwd)){
			Backstage.getBackstage(business,user);
			dispose();
		}else if(admin.Login(user,pwd)){
			Backstage.getBackstage(admin);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "用户名或密码错误，请检查您的输入", "用户名或密码错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Login() {
		KeyListener key_Listener = new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					login(new BusinessImpl(),new AdminImpl());
				}
			}
		};
		this.setTitle("登录");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//登录
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(arg0 -> login(new BusinessImpl(),new AdminImpl()));
		button.setBounds(58, 168, 93, 23);
		contentPane.add(button);
		
		//注册
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(e -> Register.getRegister());
		button_1.setBounds(288, 168, 93, 23);
		contentPane.add(button_1);
		
		adminName = new JTextField(10);
		adminName.setBounds(145, 61, 155, 21);
		contentPane.add(adminName);
		
		password = new JPasswordField();
		password.setBounds(145, 98, 155, 21);
		contentPane.add(password);

		adminName.addKeyListener(key_Listener);
		password.addKeyListener(key_Listener);
		
		//用户名
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(81, 64, 54, 15);
		contentPane.add(label);
		
		//密码
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(81, 101, 54, 15);
		contentPane.add(label_1);
	}
}
