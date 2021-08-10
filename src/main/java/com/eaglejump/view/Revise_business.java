package com.eaglejump.view;

import com.eaglejump.service.Services;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.Hashtable;

public class Revise_business extends JFrame {
	
	private final JTextField businessName;
	private final JTextField businessAdd;
	private final JTextField businessEaplain;
	private final JTextField starPrice;
	private final JPasswordField password;
	private final JTextField deliveryPrice;
	private String businessname;
	/**
	 * Launch the application.
	 */
	private static Revise_business reviseBusiness;
	protected static void getRevise(Services services,String...username) {
		if(reviseBusiness == null){
			reviseBusiness = new Revise_business(services,username);
		}
		reviseBusiness.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	private Revise_business(Services services,String...username) {
		if(username.length!=0) {
			businessname = username[0];
			this.setTitle("修改");
		}else this.setTitle("添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		businessName = new JTextField();
		businessName.setBounds(132, 46, 208, 21);
		contentPane.add(businessName);
		businessName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(132, 77, 208, 21);
		contentPane.add(password);
		
		businessAdd = new JTextField();
		businessAdd.setColumns(10);
		businessAdd.setBounds(132, 108, 208, 21);
		contentPane.add(businessAdd);
		
		businessEaplain = new JTextField();
		businessEaplain.setColumns(10);
		businessEaplain.setBounds(132, 139, 208, 21);
		contentPane.add(businessEaplain);
		
		starPrice = new JTextField();
		starPrice.setColumns(10);
		starPrice.setBounds(132, 170, 208, 21);
		contentPane.add(starPrice);
		
		deliveryPrice = new JTextField();
		deliveryPrice.setBounds(132, 201, 208, 21);
		contentPane.add(deliveryPrice);
		deliveryPrice.setColumns(10);
		
		//提交
		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(e -> {
			Hashtable<String, String> hashtable = new Hashtable<>();
			hashtable.put("businessName", businessName.getText());
			hashtable.put("password", new String(password.getPassword()));
			hashtable.put("businessAddress", businessAdd.getText());
			hashtable.put("businessExplain", businessEaplain.getText());
			hashtable.put("starPrice", starPrice.getText());
			hashtable.put("deliveryPrice", deliveryPrice.getText());
			if(businessname != null){
				services.update(hashtable,services, businessname);
			}else {
				try {
					services.Insert(hashtable, services);
				}catch (SQLException exception){
					JOptionPane.showMessageDialog(null, "该用户名被占用", "用户名错误", JOptionPane.ERROR_MESSAGE);
				}
			}
			Backstage.getBackstage(services);
			dispose();
			reviseBusiness = null;
		});
		button.setBounds(56, 253, 93, 23);
		contentPane.add(button);
		//取消
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(e -> {
			Backstage.getBackstage(services);
			dispose();
			reviseBusiness = null;
		});
		button_1.setBounds(247, 253, 93, 23);
		contentPane.add(button_1);
		
		//商家名称
		JLabel label = new JLabel("\u5546\u5BB6\u540D\u79F0");
		label.setBounds(56, 49, 66, 15);
		contentPane.add(label);
		//密码
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(56, 80, 54, 15);
		contentPane.add(label_1);
		//地址
		JLabel label_2 = new JLabel("\u5730\u5740");
		label_2.setBounds(56, 111, 64, 15);
		contentPane.add(label_2);
		//介绍
		JLabel label_3 = new JLabel("\u4ECB\u7ECD");
		label_3.setBounds(56, 142, 54, 15);
		contentPane.add(label_3);
		//起送费
		JLabel label_4 = new JLabel("\u8D77\u9001\u8D39");
		label_4.setBounds(56, 173, 54, 15);
		contentPane.add(label_4);
		//配送费
		JLabel label_5 = new JLabel("\u914D\u9001\u8D39");
		label_5.setBounds(56, 204, 54, 15);
		contentPane.add(label_5);

	}

}
