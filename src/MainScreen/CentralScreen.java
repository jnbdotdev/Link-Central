package MainScreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CentralScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField txtLinkName;
	
	public static int repeatClick = 0;
	DefaultListModel<String> lstModel = new DefaultListModel<>();
	
	/**
	 * Launch the application.
	 */
		
	public static void main(String tema) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (tema.equals(info.getName())) {
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
					CentralScreen frame = new CentralScreen();
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
	public CentralScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CentralScreen.class.getResource("/MainScreen/www.png")));
		setTitle("Link Central");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		/*
		if(!this.isAuthNeed()) {
			passwordField.setEditable(false);
			passwordField.setEnabled(false);
		}
		*/
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(526, 326, 190, 49);
		contentPane.add(scrollPane_1);
		
		JTextField txtLinkCorp = new JTextField();
		txtLinkCorp.setEditable(false);
		txtLinkCorp.setHorizontalAlignment(SwingConstants.CENTER);
		txtLinkCorp.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane_1.setViewportView(txtLinkCorp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(258, 139, 258, 411);
		contentPane.add(scrollPane);

		JList<String> lstLinks = new JList<>();
		lstLinks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Link contentLink = new Link();
				
				String linkSelected = lstLinks.getSelectedValue();
				
				ArrayList<String> contentItems = contentLink.listItens();
				for(int i = 0; i < contentItems.size(); i++) {
					String linkContent = contentItems.get(i);
					String linkContentSplit[] = linkContent.split(":");
					String linkCorp = linkContentSplit[1];
					String linkName = linkContentSplit[0];
					if(linkName.equals(linkSelected)) {
						txtLinkCorp.setText("");
						linkCorp = linkCorp.replaceAll(" ", "");
						txtLinkCorp.setText(linkCorp);
						txtLinkName.setText(linkName);
					}
				}
				
			}
		});
		
		lstLinks.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		DefaultListCellRenderer render = (DefaultListCellRenderer) lstLinks.getCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		fillList();
				
		lstLinks.setModel(lstModel);
		scrollPane.setViewportView(lstLinks);
		
		JLabel lblTitle = new JLabel("Link Central");
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 764, 58);
		contentPane.add(lblTitle);
		
		JButton btnNew = new JButton("Novo");
		btnNew.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/diskette.png")));
		btnNew.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/diskette2.png")));
		btnNew.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/diskette.png")));
		btnNew.setVerticalAlignment(SwingConstants.CENTER);
		btnNew.setHorizontalAlignment(SwingConstants.CENTER);
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtLinkName.setText("");
				txtLinkCorp.setText("");
				txtLinkName.setEditable(true);
				txtLinkCorp.setEditable(true);
				
			}
		});
		btnNew.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnNew.setFocusPainted(false);
		btnNew.setBounds(526, 208, 248, 58);
		contentPane.add(btnNew);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtSearch.setBounds(10, 80, 648, 48);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnOpen = new JButton("Abrir");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Link link = new Link();
				File file = link.fileLinks();
				
				String linkName = "", linkCorp = "";
				
				ArrayList<String> iList = link.readImported(file);		
				
				ArrayList<String> fList = link.importLinks(iList);		
										
				for(int i = 0; i < fList.size(); i++) {
					String[] content = fList.get(i).split(":");
					linkName = content[0];
					linkCorp = content[1];
					link.saveLink(linkName, linkCorp);
				}
				
				txtLinkName.setText("");
				txtLinkCorp.setText("");
				fillList();
				
			}
		});
		btnOpen.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/folder2.png")));
		btnOpen.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/folder.png")));
		btnOpen.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/folder2.png")));
		btnOpen.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnOpen.setBounds(526, 139, 248, 58);
		btnOpen.setFocusPainted(false);
		contentPane.add(btnOpen);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				
				if(repeatClick == 0) {
					txtLinkName.setEditable(true);
					txtLinkCorp.setEditable(true);
					System.out.println(repeatClick);
					repeatClick++;
				}else
				if(repeatClick == 1) {
					System.out.println(repeatClick);
					int opt = JOptionPane.showConfirmDialog(null,"Deseja realizar a alteração?");
					System.out.println("Opção: "+opt);
					if(opt == 0){
						repeatClick++;
						JOptionPane.showMessageDialog(null, "Clique em 'Alterar' novamente para confirmar a alteração");
					} if(opt == 1){
						repeatClick = 0;
						txtLinkName.setEditable(false);
						txtLinkCorp.setEditable(false);
						txtLinkName.setText("");
						txtLinkCorp.setText("");
					} if(opt == 2){
						repeatClick = 0;
						txtLinkName.setEditable(false);
						txtLinkCorp.setEditable(false);
						txtLinkName.setText("");
						txtLinkCorp.setText("");
					}
					
				}else
				if(repeatClick == 2) {
					System.out.println("test: "+repeatClick);
					txtLinkName.setEditable(false);
					txtLinkCorp.setEditable(false);
					
					String linkName = txtLinkName.getText();
					String linkCorp = txtLinkCorp.getText();
					
					String contentToChange = linkName + ":" + linkCorp;
					int indexToChange = lstLinks.getSelectedIndex();
					Link link = new Link();
					try {
						link.updateList(contentToChange, indexToChange);
						
						fillList();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					repeatClick = 0;
				}
			}
		});
		btnAlterar.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/exchange.png")));
		btnAlterar.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/exchange2.png")));
		btnAlterar.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/exchange.png")));
		btnAlterar.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnAlterar.setBounds(526, 423, 248, 58);
		btnAlterar.setFocusPainted(false);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Link link = new Link();
				int indexToDelete = lstLinks.getSelectedIndex();
				int opt = JOptionPane.showConfirmDialog(null,"Deseja realizar a exclusão?");
				if(opt == 0) {
					try {
						link.deleteItem(indexToDelete);
						
						txtLinkName.setText("");
						txtLinkCorp.setText("");
						fillList();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/bin.png")));
		btnExcluir.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/bin2.png")));
		btnExcluir.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/bin.png")));
		btnExcluir.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnExcluir.setBounds(526, 492, 248, 58);
		btnExcluir.setFocusPainted(false);
		contentPane.add(btnExcluir);
		
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				ThemeSelection ts = new ThemeSelection();

				ts.setVisible(true);
				CentralScreen.this.dispose();
			
			}
		});
		btnBack.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/left.png")));
		btnBack.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/left (1).png")));
		btnBack.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/left.png")));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(10, 11, 36, 58);
		btnBack.setBorderPainted(false);
		btnBack.setBorder(null);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 139, 238, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(0, 0, 238, 361);
		panel.add(lblImg);
		
		JButton btnUpload = new JButton("UPLOAD IMAGE");
		btnUpload.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnUpload.setFocusPainted(false);
		btnUpload.setBounds(1, 361, 236, 49);
		panel.add(btnUpload);
		
		txtLinkName = new JTextField();
		txtLinkName.setEditable(false);
		txtLinkName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtLinkName.setHorizontalAlignment(SwingConstants.CENTER);
		txtLinkName.setToolTipText("Link Name");
		txtLinkName.setBounds(526, 277, 248, 38);
		contentPane.add(txtLinkName);
		txtLinkName.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnAdd.setFocusPainted(false);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(txtLinkName.isEditable() && txtLinkCorp.isEditable()) {
					Link link = new Link();

					String linkName = txtLinkName.getText();
					String linkCorp = txtLinkCorp.getText();
					
					link.saveLink(linkName, linkCorp);
					
					txtLinkName.setEditable(false);
					txtLinkCorp.setEditable(false);
					
					JOptionPane.showMessageDialog(null, "Link Successfully Added!");
					
					txtLinkName.setText("");
					txtLinkCorp.setText("");
					fillList();
				}
			}
		});
		btnAdd.setBounds(619, 386, 62, 26);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnCancel.setFocusPainted(false);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				txtLinkName.setEditable(false);
				txtLinkCorp.setEditable(false);
			
			}
		});
		btnCancel.setBounds(691, 386, 83, 26);
		contentPane.add(btnCancel);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				txtLinkName.setText("");
				txtLinkCorp.setText("");
			
			}
		});
		btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnClear.setFocusPainted(false);
		btnClear.setBounds(526, 386, 82, 26);
		contentPane.add(btnClear);
		
		JButton btnWeb = new JButton("");
		btnWeb.setFocusPainted(false);
		btnWeb.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/global-searchw32.png")));
		btnWeb.setRolloverIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/global-search32.png")));
		btnWeb.setPressedIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/global-searchw32.png")));
		btnWeb.setBounds(725, 326, 49, 49);
		contentPane.add(btnWeb);
		
		JButton btnSearch = new JButton("");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				fillList();
				String searchItem = txtSearch.getText();
				
				btnAdd.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnOpen.setEnabled(false);
				btnNew.setEnabled(false);
				btnAlterar.setEnabled(false);
				
				for(int i = 0; i < lstModel.size(); i++) {
					String listRow = lstModel.get(i);
					if(listRow.contains(searchItem)){
						lstModel.clear();
						lstModel.addElement(listRow);

					}
				}				

			}
		});
		//btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/icons8-search-30.png")));
		btnSearch.setBounds(726, 80, 48, 48);
		btnSearch.setFocusPainted(false);
		contentPane.add(btnSearch);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLinkName.setText("");
				txtLinkCorp.setText("");
				txtSearch.setText("");
				fillList();
			
			}
		});
		btnUpdate.setIcon(new ImageIcon(CentralScreen.class.getResource("/MainScreen/processamento-de-dados.png")));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBounds(668, 80, 48, 48);
		contentPane.add(btnUpdate);
	}
	

	public void fillList() {
		
		Link getLink = new Link();

		lstModel.clear();
		
		if(getLink.detectFile()) {
			ArrayList<String> itens = getLink.listItens();
			
			for(int i=0; i < itens.size(); i++) {
				String linkContent = itens.get(i);
				String linkContentSplit[] = linkContent.split(":");
				String linkName = linkContentSplit[0];
				lstModel.addElement(linkName);
			}
		}
	}
}
