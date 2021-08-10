package com.eaglejump.view;

import com.eaglejump.service.Impl.AdminImpl;
import com.eaglejump.service.Services;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

public class Backstage extends JFrame {
	private final JTable table;
	private final JTextField Search;
	private final String businessname;
	
	/**
	 * Launch the application.
	 */
	private static Backstage backstage;
	protected static void getBackstage(Services services,String...username) {
		if(backstage ==null){
			backstage = new Backstage(services,username);
		}
		backstage.setVisible(true);
	}
	
	//初始化表
	private void Searchfun(Services services,String text){
		ArrayList<Object> searchData;
		if(services instanceof AdminImpl){
			searchData = services.Search("%" + text + "%");
		}else searchData = services.Search(businessname,"%" + text + "%");
		ArrayList<Object> Vector = services.updateTable(services,searchData);
		((DefaultTableModel)table.getModel()).getDataVector().clear();
		
		
		Vector<String> headVectors = (Vector<String>) Vector.get(0);
		Vector<Vector<String>> dataVectors = (Vector<Vector<String>>) Vector.get(1);
		DefaultTableModel dtmView = new DefaultTableModel(dataVectors, headVectors);
		table.setModel(dtmView);
	}
	
	/**
	 * Create the frame.
	 */
	private Backstage(Services services,String...username) {
		//键盘监听
		KeyListener key_Listener = new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					Searchfun(services,Search.getText());
				}
			}
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 573, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if(username.length!=0){
			businessname = username[0];
		}
		else {
			businessname = "管理员";
		}
		this.setTitle(businessname+"后台");
		
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane JSP=  new  JScrollPane(table);
		JSP.setBounds(10, 10, 434, 259);
		contentPane.add(JSP);
		Searchfun(services,"%");
		
		Search = new JTextField();
		Search.setBounds(113, 280, 276, 21);
		contentPane.add(Search);
		Search.setColumns(10);
		Search.addKeyListener(key_Listener);
		
		//搜索商家
		JButton button = new JButton("\u641C\u7D22");
		button.addActionListener(e -> Searchfun(services,Search.getText()));
		button.setBounds(10, 279, 93, 23);
		contentPane.add(button);
		
		//添加
		JButton add = new JButton("\u6DFB\u52A0");
		add.addActionListener(e -> {
			if(services instanceof AdminImpl){
				Revise_business.getRevise(services);//管理员无需传入
			}else {
				Revise_food.getRevise(services,businessname);//传入操作的用户
			}
		});
		add.setBounds(454, 30, 93, 23);
		contentPane.add(add);
		
		//修改
		JButton revise = new JButton("\u4FEE\u6539");
		revise.addActionListener(e -> {
			try {
				int count = table.getSelectedRow();//获取你选中的行号（记录）
				String getname = table.getValueAt(count, 0).toString();
				if (services instanceof AdminImpl)
					Revise_business.getRevise(services, getname);
				 else
					Revise_food.getRevise(services, businessname, getname);
			}catch(Exception exception){
				JOptionPane.showMessageDialog(null, "请选择你要修改的数据", "选择", JOptionPane.ERROR_MESSAGE);
			}
		});
		revise.setBounds(454, 80, 93, 23);
		contentPane.add(revise);
		
		//删除
		JButton delete = new JButton("\u5220\u9664");
		delete.addActionListener(e -> {
			try {
				int count = table.getSelectedRow();//获取你选中的行号（记录）
				String getname = table.getValueAt(count, 0).toString();
				if (services instanceof AdminImpl) {
					services.Delete(getname);
				} else {
					services.Delete(businessname, getname);
				}
				Searchfun(services,"%");
			}catch (Exception exception){
				JOptionPane.showMessageDialog(null, "请选择你要删除的数据", "选择", JOptionPane.ERROR_MESSAGE);
			}
		});
		delete.setBounds(454, 130, 93, 23);
		contentPane.add(delete);
		
		JButton reflush = new JButton("刷新");
		reflush.addActionListener(e -> Searchfun(services,"%"));
		reflush.setBounds(454, 180, 93, 23);
		contentPane.add(reflush);
		
		JLabel label = new JLabel(businessname);
		label.setBounds(454, 230, 54, 15);
		contentPane.add(label);
		
		//登出
		JButton exit = new JButton("\u767B\u51FA");
		exit.addActionListener(e -> {
			Login.getLogin();
			dispose();
			backstage = null;
		});
		exit.setBounds(454, 280, 93, 23);
		contentPane.add(exit);
	}
}
