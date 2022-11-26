package function;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RoomWindow extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RoomWindow() {
		setTitle("Room");
        setSize(900, 540);
        setDefaultCloseOperation(RoomWindow.DISPOSE_ON_CLOSE);
        setLocation(200, 0);
        setVisible(true);
	}
}