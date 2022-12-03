package function;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import db.Database;

public class LoginWindow extends JFrame {
	db.Database data = new db.Database();
	
	private JPanel contentPane;
    /* Panel */
	JPanel basePanel = new JPanel(new BorderLayout());
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel westPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel southPanel = new JPanel();
	
	/* Label */
	JLabel idL = new JLabel("Id");
	JLabel pwL = new JLabel("PW");
	
	/* TextField */
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	/* Button */
	JButton loginBtn = new JButton("Log In");
	JButton exitBtn = new JButton("Quit");
	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Log In");
        setSize(400,540);
        setDefaultCloseOperation(LoginWindow.DISPOSE_ON_CLOSE);
        setLocation(200, 0);
        setVisible(true);
    
    	    	
    	
    		centerPanel.setPreferredSize(new Dimension(260, 80));
    		westPanel.setPreferredSize(new Dimension(210, 75));
    		eastPanel.setPreferredSize(new Dimension(90, 75));
    		southPanel.setPreferredSize(new Dimension(290, 33));
    		
    		/* Label 크기 작업 */
    		idL.setPreferredSize(new Dimension(55, 30));
    		pwL.setPreferredSize(new Dimension(55, 30));
    		
    		/* TextField 크기 작업 */
    		id.setPreferredSize(new Dimension(140, 30));
    		pw.setPreferredSize(new Dimension(140, 30));
    		
    		/* Button 크기 작업 */
    		loginBtn.setPreferredSize(new Dimension(80, 63));
    		exitBtn.setPreferredSize(new Dimension(250, 23));
    		
    		/* Panel 추가 작업 */
    		setContentPane(basePanel);	//panel을 기본 컨테이너로 설정
    		
    		basePanel.add(centerPanel, BorderLayout.CENTER);
    		basePanel.add(southPanel, BorderLayout.SOUTH);
    		centerPanel.add(westPanel, BorderLayout.WEST);
    		centerPanel.add(eastPanel, BorderLayout.EAST);
    		
    		westPanel.setLayout(new FlowLayout());
    		eastPanel.setLayout(new FlowLayout());
    		southPanel.setLayout(new FlowLayout());
    		
    		/* westPanel 컴포넌트 */
    		westPanel.add(idL);
    		westPanel.add(id);
    		westPanel.add(pwL);
    		westPanel.add(pw);
    		
    		/* eastPanel 컴포넌트 */
    		eastPanel.add(loginBtn);
    		
    		/* southPanel 컴포넌트 */
    		southPanel.add(exitBtn);

    		
    		/* Button 이벤트 리스너 추가 */
    		ButtonListener bl = new ButtonListener();
    		
    		loginBtn.addActionListener(bl);
    		exitBtn.addActionListener(bl);

    		
    		setSize(310, 150);
    		setLocationRelativeTo(null);
    		setVisible(true);
    		setResizable(false);
    	
}
    	/* Button 이벤트 리스너 */
    	class ButtonListener implements ActionListener{
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			JButton b = (JButton)e.getSource();
    			/* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
    			String uid = id.getText();
    			String upass = "";
    			for(int i=0; i<pw.getPassword().length; i++) {
    				upass = upass + pw.getPassword()[i];
    			}
    			
    			/* 게임종료 버튼 이벤트 */
    			if(b.getText().equals("Quit")) {
    				System.out.println("Quit");
    				dispose();
    			}
    			
    			
    			/* 로그인 버튼 이벤트 */
    			else if(b.getText().equals("Log In")) {
    				if(uid.equals("") || upass.equals("")) {
    					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "Fail", JOptionPane.ERROR_MESSAGE);
    					System.out.println("Fail > 로그인 정보 미입력");
    				}
    				
    				else if(uid != null && upass != null) {
    					if(db.Database.logincheck(uid, upass)) {	
    						System.out.println("Success");
    						JOptionPane.showMessageDialog(null, "Success");
    						dispose();
    					} else {
    						System.out.println("Fail > 로그인 정보 불일치");
    						JOptionPane.showMessageDialog(null, "Fail");
    					}
    				}
    			}
    		}
    	}
    }