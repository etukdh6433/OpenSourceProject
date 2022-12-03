package function;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;
import db.Database;
import java.awt.BorderLayout;

public class RoomWindow extends JFrame {
	private JPanel contentPane;
	
	String userId = "root";
	db.Database data = new db.Database();
	
	/**
	 * Create the frame.
	 */
	public RoomWindow() {
		setTitle("Room");
        setSize(900, 540);
        setDefaultCloseOperation(RoomWindow.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
//		Test : Quit 버튼 클릭 시 gamelistdata 최신화
        JButton btnQuit = new JButton("Quit");
        getContentPane().add(btnQuit, BorderLayout.CENTER);
        btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.gameOrder(userId, "delete");
				dispose();
			}
		});
        setVisible(true);
        
	}
}