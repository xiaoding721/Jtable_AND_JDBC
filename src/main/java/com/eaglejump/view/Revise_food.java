package com.eaglejump.view;

import com.eaglejump.service.Services;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.Hashtable;

public class Revise_food extends JFrame {
	
	private final JTextField foodName;
	private final JTextField foodExplain;
	private final JTextField foodPrice;
	
	
	private String foodname;
	private final String businessname;
	/**
	 * Launch the application.
	 */
	private static Revise_food Revise_food;
	protected static void getRevise(Services services,String...name) {
		if(Revise_food == null){
			Revise_food = new Revise_food(services,name);
		}
		Revise_food.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	private Revise_food(Services services,String...getname) {
		if(getname.length!=1) {
			this.foodname = getname[1];
			this.setTitle("修改");
		}else this.setTitle("添加");
		this.businessname = getname[0];
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		foodName = new JTextField();
		foodName.setBounds(132, 46, 208, 21);
		contentPane.add(foodName);
		foodName.setColumns(10);
		
		foodExplain = new JTextField();
		foodExplain.setColumns(10);
		foodExplain.setBounds(132, 77, 208, 21);
		contentPane.add(foodExplain);
		
		foodPrice = new JTextField();
		foodPrice.setColumns(10);
		foodPrice.setBounds(132, 108, 208, 21);
		contentPane.add(foodPrice);
		
		
		//提交
		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(e -> {
			if(foodName.getText().equals("")||foodPrice.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入食品名称和价格", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				Hashtable<String, String> hashtable = new Hashtable<>();
				hashtable.put("foodName", foodName.getText());
				hashtable.put("foodExplain", foodExplain.getText());
				hashtable.put("foodPrice", foodPrice.getText());
				if(foodname !=null){
					services.update(hashtable,services,foodname);
				}else {
					try{
						hashtable.put("businessName", businessname);
						services.Insert(hashtable, services);
					}catch (SQLException exception){
						JOptionPane.showMessageDialog(null, "已添加该食品", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				Backstage.getBackstage(services);
				dispose();
				Revise_food=null;
			}
		});
		button.setBounds(56, 253, 93, 23);
		contentPane.add(button);
		//取消
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(e -> {
			Backstage.getBackstage(services);
			dispose();
			Revise_food=null;
		});
		button_1.setBounds(247, 253, 93, 23);
		contentPane.add(button_1);
		
		//食品名称
		JLabel label = new JLabel("食品名称");
		label.setBounds(56, 49, 66, 15);
		contentPane.add(label);
		//食品介绍
		JLabel label_1 = new JLabel("食品介绍");
		label_1.setBounds(56, 80, 54, 15);
		contentPane.add(label_1);
		//价格
		JLabel label_2 = new JLabel("价格");
		label_2.setBounds(56, 111, 64, 15);
		contentPane.add(label_2);
		
	}
	
}
