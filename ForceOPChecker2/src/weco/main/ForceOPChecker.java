package weco.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.iharder.dnd.FileDrop;

@SuppressWarnings("serial")
public class ForceOPChecker extends JFrame {

	private JPanel contentPane;
	public static ForceOPChecker main;
	
	public static void main(String[] args) {
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main = new ForceOPChecker();
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public boolean preGui(){
		
		if(JAD().exists()){
			
		}else{
			JOptionPane.showMessageDialog(this, "Please put 'JAD.exe' in " + workingDir(), "No JAD Found!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public ForceOPChecker() {
		
		if(!preGui()){
			System.exit(1);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decompile.process();
			}
		});
	      new  FileDrop( contentPane, new FileDrop.Listener()
	      {   public void  filesDropped( java.io.File[] files )
	          {   
	    	  JOptionPane.showMessageDialog(main, "File: " + files[0], "", JOptionPane.INFORMATION_MESSAGE);
	    	  File f = new File(workingFile(), "file.jar");
	    	  
	    	  if(f.exists()){
	    		  f.delete();
	    	  }
	    	  
	    	  try {
				Files.copy(files[0].toPath(), f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
	          }
	      
	      });
		JLabel lblDragFileHere = new JLabel("Drag file/plugin here");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(170)
					.addComponent(lblDragFileHere)
					.addContainerGap(187, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addComponent(lblDragFileHere)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}

	
	public String workingDir(){
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		return url.toString().replaceFirst("file:/", "");
	}
	public String workingZipDir(){
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		return url.toString().replaceFirst("file:/", "") + "\\zip";
	}
	public String workingJADDir(){
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		return url.toString().replaceFirst("file:/", "") + "\\jad";
	}
	public File workingFile(){
		return new File(workingDir());
	}
	public File JAD(){
		return new File(workingDir() + "jad.exe");
	}
}
