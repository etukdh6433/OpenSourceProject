package function;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;


public class SignupWindow extends JFrame {

	JPanel panel = new JPanel();

	
	/* Label */
	JLabel idL = new JLabel("ID");
	JLabel pwL = new JLabel("PW");
	
	/* TextField */
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	/* Button */
	/* Button */
	JButton joinBtn = new JButton("Sign Up");
	JButton cancelBtn = new JButton("Back");


	/**
	 * Create the frame.
	 */
	public SignupWindow() {
		setTitle("Sign Up");
        setLocation(200, 0);

		
		/* Label 크기 작업 */
		idL.setPreferredSize(new Dimension(55, 30));
		pwL.setPreferredSize(new Dimension(55, 30));
		
		/* TextField 크기 작업 */
		id.setPreferredSize(new Dimension(140, 30));
		pw.setPreferredSize(new Dimension(140, 30));
		
		/* Button 크기 작업 */
		joinBtn.setPreferredSize(new Dimension(95, 25));
		cancelBtn.setPreferredSize(new Dimension(95, 25));
		
		/* Panel 추가 작업 */
		setContentPane(panel);
		
		panel.add(idL);
		panel.add(id);
		
		panel.add(pwL);
		panel.add(pw);
		
		panel.add(cancelBtn);
		panel.add(joinBtn);
		
		/* Button 이벤트 리스너 추가 */
		ButtonListener bl = new ButtonListener();
		
		cancelBtn.addActionListener(bl);
		joinBtn.addActionListener(bl);
		
		setSize(250, 150);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setResizable(false);


    	
}
	/* Button 이벤트 리스너 */
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			/* TextField에 입력된 회원 정보들을 변수에 초기화 */
			String uid = id.getText();
			String upass = "";
			for(int i=0; i<pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}
			
			/* 가입취소 버튼 이벤트 */
			if(b.getText().equals("Back")) {
				dispose();
			}
			
			/* 가입하기 버튼 이벤트 */
			else if(b.getText().equals("Sign Up")) {
				if(uid.equals("") || upass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter all information.", "Sign Up Fail", JOptionPane.ERROR_MESSAGE);
					System.out.println("Sign Up Fail > Not entered");
				}
				
				else if(!uid.equals("") && !upass.equals("")) {
					if(db.Database.joinCheck(uid, upass)) {
						System.out.println("Sign Up Success");
						JOptionPane.showMessageDialog(null, "Sign Up Success");
						dispose();
					} else {
						System.out.println("Sign Up Fail");
						JOptionPane.showMessageDialog(null, "Sign Up Fail");
						id.setText("");
						pw.setText("");
					}


    				}
    			}
    		}
    	}
    }