package mainWindow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.TextField;


public class MainWindow extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel accountPanel = new JPanel();
		accountPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout(0, 0));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(accountPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(selectPanel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(selectPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(accountPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)))
		);
		
		JButton btnQuit = new JButton("Quit");
		accountPanel.add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnSignUp = new JButton("Sign Up");
		accountPanel.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnLogIn = new JButton("Log-in");
		accountPanel.add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		JButton btnCreate = new JButton("Create Room");
		selectPanel.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRandom = new JButton("Random Room");
		selectPanel.add(btnRandom);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		JList list = new JList();
		gamePanel.add(list);


		JTextArea chatArea = new JTextArea();
		chatPanel.add(chatArea, BorderLayout.CENTER);
		chatArea.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(chatArea);
		chatPanel.add(scroll);
		
		JTextField chatField = new JTextField();
		chatPanel.add(chatField, BorderLayout.SOUTH);
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField chat = (JTextField)e.getSource();
				chatArea.append(">>" + chat.getText() + "\n\n");
				chat.setText("");
			}
		});
		
		
		contentPane.setLayout(gl_contentPane);
	}

}
