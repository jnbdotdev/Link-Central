package MainScreen;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ThemeSelection extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	

//=================================================>
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemeSelection frame = new ThemeSelection();
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
	public ThemeSelection() {

		setTitle("Link Central");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThemeSelection.class.getResource("/MainScreen/www.png")));
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
	
		
		JLabel lblTitle = new JLabel("Login");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblTitle.setBounds(10, 11, 420, 50);
		contentPane.add(lblTitle);
		
		JRadioButton rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnWindows.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		rdbtnWindows.setBackground(null);
		rdbtnWindows.setBounds(153, 258, 131, 35);
		contentPane.add(rdbtnWindows);
		
		JRadioButton rdbtnNimbus = new JRadioButton("Nimbus");
		rdbtnNimbus.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnNimbus.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		rdbtnNimbus.setBackground(Color.WHITE);
		rdbtnNimbus.setBounds(10, 258, 131, 35);
		contentPane.add(rdbtnNimbus);
		
		JRadioButton rdbtnMetal = new JRadioButton("Metal");
		rdbtnMetal.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		rdbtnMetal.setBackground(Color.WHITE);
		rdbtnMetal.setBounds(299, 258, 131, 35);
		contentPane.add(rdbtnMetal);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnMetal);
		btnGroup.add(rdbtnWindows);
		btnGroup.add(rdbtnNimbus);
				
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Password byPass = new Password();
				CentralScreen cs = new CentralScreen();
				
				String requestPass = String.valueOf(passwordField.getPassword());
				
				String theme = "";
				
				if(rdbtnMetal.isSelected()) {
					theme = "Metal";
				}else if(rdbtnWindows.isSelected()) {
					theme = "Windows";
				}else if(rdbtnNimbus.isSelected()) {
					theme = "Nimbus";
				}			
				
				if(requestPass.equals(byPass.getPass())) {
					cs.main(theme);
					ThemeSelection.this.dispose();
				}
				
				Link linkClass = new Link();
				boolean stats = linkClass.detectFile();
				if(!stats) {
					linkClass.firstFile();
				}
				
				
			}
		});
		btnLogin.setBounds(153, 300, 131, 50);
		contentPane.add(btnLogin);
		
		JLabel lblSubtitle = new JLabel("Password");
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitle.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblSubtitle.setBounds(10, 83, 420, 35);
		contentPane.add(lblSubtitle);
				
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(85, 129, 274, 35);
		contentPane.add(passwordField);

		
		JLabel lblH3 = new JLabel("Selecione o Tema:");
		lblH3.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblH3.setHorizontalAlignment(SwingConstants.CENTER);
		lblH3.setBounds(10, 216, 420, 35);
		contentPane.add(lblH3);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 440, 361);
		contentPane.add(lblBackground);
		
		
	}
}
