package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.filechooser.FileNameExtensionFilter;

import BackEnd.MosaicMain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	// Buttons/Labels to display
	private JLabel instruction1Label;
	private JLabel instruction2Label;
	private JButton browseButtonPic;
	private JButton browseButtonFolder;
	private JButton create;
	
	private String picAddress = "";
	private String folderAddress = "";
	
	private JLabel pictureSelected;
	private JLabel folderSelected;
	
	private MosaicMain mosaic;
	
	public MainFrame(){
		
		super("Mosaic Creator");
		// Lets OS decide where to place the window
		setLocationByPlatform(true);
		// Sets default dimensions
		setSize(600, 400);
		// Entire program exits when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Try to make it look like a native application
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		
		// Initialise buttons and labels
		instruction1Label = new JLabel ("Select target picture");
		instruction2Label = new JLabel ("Select picture from target folder");
		pictureSelected = new JLabel ("Selected Picture:");
		folderSelected = new JLabel ("Selected Folder:");
		browseButtonPic = new JButton ("Browse"); 
		browseButtonPic.addActionListener(this);
		browseButtonFolder = new JButton ("Browse");
		browseButtonFolder.addActionListener(this);
		
		create = new JButton ("Create Mosaic");
		create.addActionListener(this);
		
		//
		Box leftBox = Box.createVerticalBox();
		leftBox.add(instruction2Label);
		leftBox.add(browseButtonFolder);
		leftBox.add(folderSelected);
		
		Box rightBox = Box.createVerticalBox();
		rightBox.add(instruction1Label);
		rightBox.add(browseButtonPic);
		rightBox.add(pictureSelected);
		
		// Layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(leftBox, BorderLayout.EAST);
		getContentPane().add(Box.createRigidArea(new Dimension(200,0)));
		getContentPane().add(rightBox, BorderLayout.WEST);
		getContentPane().add(create, BorderLayout.SOUTH);
		pack();
	}
	
	/**
	 * Used to create pop up dialogue boxes
	 * @param infoMessage
	 * 					Message to display in dialogue box
	 * @param titleBar
	 * 					Title of dialogue box
	 */
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Creates pop up file selection UI
     * @param description
     * 					Description of files to select
     * @param fileTypes
     * 				`	Files types to select
     * @return String representation of file address
     */
    public String fileChooser(String description, String[] fileTypes){
    	JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        description, fileTypes);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(getContentPane());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		     return chooser.getSelectedFile().getAbsolutePath();
		    }
		return "";
    }
	
	public void actionPerformed(ActionEvent event) {
		// Determine which button was pushed
		Object source = event.getSource();
		
		if (source == browseButtonPic) {
			String[] fileTypes = {"jpg", "jpeg"};
			picAddress = fileChooser("JPEG Files", fileTypes);
			pictureSelected.setText("Selected Pictures: " + picAddress);
			pack();
		}
		
		else if (source == browseButtonFolder){
			String[] fileTypes = {"jpg", "jpeg"};
			folderAddress = fileChooser("JPEG Files", fileTypes);
			if (!folderAddress.equals("")){
				int cutIndex = folderAddress.lastIndexOf("\\");
				folderAddress = folderAddress.substring(0, cutIndex);
				// slice address
			}
			folderSelected.setText("Selected Folder: " + folderAddress);
			pack();
		}
		
		else if (source == create) {
			if (picAddress.equals("")) {
				infoBox("Please select a target picture.", "No File Selected");			}
			
			else if (folderAddress.equals("")) {
				infoBox("Please select a picture from target folder.", "No File Selected");			}
			
			else {
				//MosaicMain.MosaicMain(picAddress, folderAddress);
				try {
					createMosaic(picAddress, folderAddress);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void createMosaic (String picAddress, String folderAddress) throws IOException{
		mosaic = new MosaicMain(picAddress, folderAddress);
		//mosaic.processTarget();
	}
	
}
