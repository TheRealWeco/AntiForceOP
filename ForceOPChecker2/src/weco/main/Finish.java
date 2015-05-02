package weco.main;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Finish extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	private JLabel lblCategory;
	private JLabel lblAmount;

	public JTextArea txtDetails = new JTextArea();
	
	public Finish() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		
		scrollPane_3 = new JScrollPane();
		
		lblCategory = new JLabel("Category");
		
		lblAmount = new JLabel("Amount");
		
		JLabel lblDetails = new JLabel("Details");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCategory)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAmount))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDetails)
							.addContainerGap(382, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory)
						.addComponent(lblAmount))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDetails)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		txtDetails.setEditable(false);
		scrollPane.setViewportView(txtDetails);
		
		JTextArea txtAmount = new JTextArea();
		txtAmount.setEditable(false);
		scrollPane_3.setViewportView(txtAmount);
		
		JTextArea txtCat = new JTextArea();
		txtCat.setEditable(false);
		
		txtCat.append("URL"+ "\n");
		txtCat.append("FILE"+ "\n");
		txtCat.append("OP"+ "\n");
		txtCat.append("COMMAND EXECUTION"+ "\n");
		txtCat.append("ASYNCCHATEVENT"+ "\n");
		txtCat.append("CONSOLE"+ "\n");
		txtCat.append("BAN"+ "\n");
		txtCat.append("KICK"+ "\n");
		
		int Async = 0;
		int URL = 0;
		int FILE = 0;
		int OP = 0;
		int EXE = 0;
		int CONSOLE = 0;
		int BAN = 0;
		int KICK = 0;
		
		int num = 0;
		
		for(Result r : Decompile.results){
			if(r.category.equals(Category.ASYNC)){
				Async++;
			}
			if(r.category.equals(Category.URL)){
				URL++;
			}
			if(r.category.equals(Category.F)){
				FILE++;
			}
			if(r.category.equals(Category.OP)){
				OP++;
			}
			if(r.category.equals(Category.EXECUTE)){
				EXE++;
			}
			if(r.category.equals(Category.CONSOLE)){
				CONSOLE++;
			}
			if(r.category.equals(Category.BAN)){
				BAN++;
			}
			if(r.category.equals(Category.KICK)){
				KICK++;
			}
			txtDetails.append("Result_" + num + ":" + "\n");
			txtDetails.append("    WARNING LEVEL: " + r.warningLvl + "\n");
			txtDetails.append("    CATEGORY: " + r.category + "\n");
			txtDetails.append("    FILE: " + r.file + "\n");
			txtDetails.append("    COMPLETE LINE: " + r.message + "\n");

			num++;
		}
		
		txtAmount.append(URL + "\n");
		txtAmount.append(FILE + "\n");
		txtAmount.append(OP + "\n");
		txtAmount.append(EXE + "\n");
		txtAmount.append(Async + "\n");
		txtAmount.append(CONSOLE + "\n");
		txtAmount.append(BAN + "\n");
		txtAmount.append(KICK + "\n");
		
		scrollPane_1.setViewportView(txtCat);
		contentPane.setLayout(gl_contentPane);
	}
}
