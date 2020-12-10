


import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Collections;



public class SudokuWindow extends JFrame {
	private Solver solver =  new Solver();
	private int[][] pregrid= new int[9][9]; 
	
	
	
	public SudokuWindow() {
		SwingUtilities.invokeLater(()-> createWindow("Sudoku",500,500));
	}

	private void createWindow(String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();		

		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(9,9));
		centerPanel.setPreferredSize(new Dimension(500,500));
		pane.add(centerPanel,BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(200, 100));
		southPanel.setBorder(new EmptyBorder(5,10,5,10));
		pane.add(southPanel, BorderLayout.SOUTH);

		
		
		SButton[][] buttongrid =new SButton[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++) {
				buttongrid[i][j]= new SButton("0");
				SButton tempButton=buttongrid[i][j];
				if((i/3+j/3)%2==0) {
					buttongrid[i][j].setBackground(Color.lightGray);
				}else {
					buttongrid[i][j].setBackground(Color.WHITE);
				}
				final Integer inneri = new Integer(i);
				final Integer innerj = new Integer(j);  //Verkar som att detta är nödvändigt för att komma åt index:n i actionlistener:n.
				buttongrid[i][j].addActionListener(new ActionListener() {
					
			        public void actionPerformed(ActionEvent ae) {
			        	 	//Här händer saker när man trycker på knappen
			        	 	//Gör en pregrid där dessa värden även sätts in, denna pregrid skickas sedan in i solve.
			        		buttongrid[inneri][innerj].val=buttongrid[inneri][innerj].val+1;
			        	 	if(buttongrid[inneri][innerj].val>9) {
			        	 		buttongrid[inneri][innerj].val=0;
			        	 	}
			        	 	tempButton.setText(String.valueOf(buttongrid[inneri][innerj].val));
			        	 	pregrid[inneri][innerj] = buttongrid[inneri][innerj].val;
			        	 	solver.setNumbers(pregrid);
			        	 	
			          }
			       });
				centerPanel.add(buttongrid[i][j]);
			}
		}
		centerPanel.updateUI();
		
		JButton solveButton = new JButton("Solve");	
		solveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {

				if(solver.is_ok()){
					solver.setNumbers(pregrid);
					if(solver.solve()) {
						for(int i=0;i<9;i++){
							for(int j=0;j<9;j++) {
								
								buttongrid[i][j].setText(String.valueOf(solver.getNumber(i,j)));
								}
							}
					} else {
						JOptionPane.showMessageDialog(frame, "The sudoku lacks solution.");
					}

				} 
				else {
					JOptionPane.showMessageDialog(frame, "Error: Bad starting grid.");
					
					
				}
				
				
				
			}
		});		
		southPanel.add(solveButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				for(int[] row: pregrid) {
					Arrays.fill(row, 0);
				}
				solver.setNumbers(pregrid);
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++) {
						buttongrid[i][j].setText(String.valueOf(solver.getNumber(i,j)));
						buttongrid[i][j].val = 0;
						}
					}
				centerPanel.updateUI();
			}
		});	
		southPanel.add(clearButton);
		

		
		pane.setPreferredSize(new Dimension(width, height));
		
		frame.pack();
		frame.setVisible(true);
	}
	private class SButton extends JButton {

		private static final long serialVersionUID = 1L;
		
		public int val=0;
		
		public SButton(String text) {
			super(text);
		}
		
	}
	

}
