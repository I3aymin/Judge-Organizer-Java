import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class JudgeOrganizer extends OrganizerMethods {

	JFrame frame;
	private JTextField projectsText;
	private JTextField judgesText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JudgeOrganizer window = new JudgeOrganizer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JudgeOrganizer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 310, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel fileInputProjects = new JLabel("File for Projects:");
		fileInputProjects.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileInputProjects.setBounds(30, 11, 108, 33);
		frame.getContentPane().add(fileInputProjects);
		
		JLabel fileInputJudge = new JLabel("File for Judges:");
		fileInputJudge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileInputJudge.setBounds(30, 49, 108, 33);
		frame.getContentPane().add(fileInputJudge);
		
		projectsText = new JTextField();
		projectsText.setBounds(137, 17, 108, 25);
		frame.getContentPane().add(projectsText);
		projectsText.setColumns(10);
		
		judgesText = new JTextField();
		judgesText.setColumns(10);
		judgesText.setBounds(137, 55, 108, 25);
		frame.getContentPane().add(judgesText);
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List projects = new ArrayList<String>();
				List groupedProjects = new ArrayList<ArrayList>();
				
				List judges = new ArrayList<String>();
				List groupedJudges = new ArrayList<ArrayList>();
				
				try {
					projects = readFile(projectsText.getText());
					projects = removeSpaces(projects);
			        groupedProjects = groupProjects(projects);
			        
					judges = readFile(judgesText.getText());
					groupedJudges = groupJudges(judges);
			            
					finalOutput(groupedProjects, groupedJudges);
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "File not found");
				}
				
				
			}
		});
		
		Submit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Submit.setBounds(54, 153, 78, 25);
		frame.getContentPane().add(Submit);
		
		JButton Clear = new JButton("Clear");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				projectsText.setText(null);
				judgesText.setText(null);
				
			}
		});
		
		Clear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Clear.setBounds(147, 153, 78, 25);
		frame.getContentPane().add(Clear);
	}
	
	
}
