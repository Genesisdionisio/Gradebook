/* Genesis Dionisio
data 4 
Semester project
*/
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Student implements Serializable { String ID; String Name; char Gender; String ClassLevel; String Age; int Lab1, Lab2, Lab3, Lab4, Lab5, Lab6, Lab7, Lab8, Lab9, Lab10;}
public class GradeBook implements ActionListener 
{

	//Static variables
   static JPanel[] data = new JPanel[15];
	static JPanel[] Card = new JPanel[8];
	static JLabel[] TopLabel = new JLabel[7];
	static JLabel[] Label = new JLabel[10];
	static JTextField[] Text = new JTextField[9];
	static JButton Button[] = new JButton[19];   
	static JComboBox[] ComboBox = new JComboBox[2];
	//Jtable arrays
	static String[][] StudentData;
	static String[] ColumnNames = { " ID ", "Name", "Year", "Gender", "Age", "Lab 01", "Lab 02", "Lab 03 ", "Lab 04", "Lab 05", "Lab 06", "Lab 07", "Lab 08", "Lab 09", "Lab 10"};
	//Used for creating a new class
	static String ClassName = "";
	static Student[] StudentArray = new Student[200];
	static int StudentCount; static int LabNumber, CurrentStudent;	
   //ActionListener
	static ActionListener Action = new GradeBook();
	static CardLayout contentPaneLayout; 
	static Container contentPane;
	static JScrollPane ScrollPane = new JScrollPane();
	static JTable Table = new JTable(); 
	static JScrollPane TempSP = new JScrollPane();

	
	public static void main(String[] args)
   {
		JFrame frame = new JFrame("Genesis' GradeBook");
		contentPane = frame.getContentPane();
		contentPane.setLayout(contentPaneLayout = new CardLayout());
		
		for (int z = 0; z < 8; z += 1)
      {
			Card[z] = new JPanel(new BorderLayout());
		}
		//Card 0
			TopLabel[0] = new JLabel("<html><font size = 5><b>Use The Buttons Below To Manage Students</b></html>", JLabel.CENTER);
			data[0] = new JPanel(new FlowLayout());
			Label[0] = new JLabel("Class Name: "); Label[1] = new JLabel("Number of Students: ");
			Text[0] = new JTextField("", 10); Text[1] = new JTextField("", 10);
			
			data[0].add(Label[0]); data[0].add(Text[0]);
			data[0].add(Label[1]); data[0].add(Text[1]);
			data[1] = new JPanel(new GridLayout(2, 4));
			for (int i = 0; i < 8; i += 1)
			{
				switch(i)
				{
					case 0:
						Button[0] = new JButton("0. Create A New Class"); 
                  break;
					case 1:
						Button[1] = new JButton("1. Load Students From"); 
                  break;
					case 2:
						Button[2] = new JButton("2. Add New Students  "); 
                  break;
					case 3:
						Button[3] = new JButton("3. Enter Lab Scores  "); 
                  break;
					case 4:
						Button[4] = new JButton("4. Sort Students     "); 
                  break;
					case 5:
						Button[5] = new JButton("5. View Students     "); 
                  break;
					case 6:
						Button[6] = new JButton("6. Backup Students To"); 
                  break;
					case 7:
						Button[7] = new JButton("7.        Exit       "); 
                  break;
				}
				data[1].add(Button[i]); 
				Button[i].addActionListener(Action);
			}

			Card[0].add(TopLabel[0], BorderLayout.NORTH); Card[0].add(data[0], BorderLayout.CENTER);
			Card[0].add(data[1], BorderLayout.SOUTH);
		
		//Card 1
			TopLabel[1] = new JLabel("<html><font size = 5><b>Create A New Class</b></html>", JLabel.CENTER);
			data[2] = new JPanel(new FlowLayout());
			data[14] = new JPanel(new FlowLayout());
			Text[2] = new JTextField("", 10); Text[2].addActionListener(Action);
			Label[8] = new JLabel("Class Name: ");
			
			data[2].add(Label[8]); data[2].add(Text[2]);
			
			data[3] = new JPanel(new FlowLayout());
			for (int i = 8; i < 10; i += 1)
			{
				switch(i)
				{
				case 8:
					Button[8] = new JButton("       Create        "); 
               break;
				case 9:
					Button[9] = new JButton("       Cancel        "); 
               break;
				}
				data[3].add(Button[i]); 
				Button[i].addActionListener(Action);
			}
			Card[1].add(TopLabel[1], BorderLayout.NORTH); 
         Card[1].add(data[2], BorderLayout.CENTER); 
			Card[1].add(data[3], BorderLayout.SOUTH);
		
		//Card 2
			TopLabel[2] = new JLabel("<html><font size = 5><b>Load Students From A File</b></html>", JLabel.CENTER);
			data[4] = new JPanel(new FlowLayout());
			Text[3] = new JTextField("", 10);
			Label[9] = new JLabel("Class Name: ");
			
			data[4].add(Label[9]); data[4].add(Text[3]); data[4].add(TopLabel[2]);
			
			data[5] = new JPanel(new FlowLayout());
			for (int i = 10; i < 12; i += 1)
			{
				switch(i)
				{
				case 10:
					Button[10] = new JButton("       Load         "); 
               break;
				case 11:
					Button[11] = new JButton("       Cancel        "); 
               break;
				}
				data[5].add(Button[i]); 
				Button[i].addActionListener(Action);
			}
			Card[2].add(TopLabel[2], BorderLayout.NORTH); Card[2].add(data[4], BorderLayout.CENTER); 
			Card[2].add(data[5], BorderLayout.SOUTH);
		
		//Card 3 
			TopLabel[3] = new JLabel("<html><font size = 5><b>Enter Student Information</b></html>", JLabel.CENTER);
			data[6] = new JPanel(new GridLayout(0, 2));
			Label[2] = new JLabel("SSN: ", JLabel.RIGHT); 
         Label[3] = new JLabel("Name: ", JLabel.RIGHT);  
         Label[4] = new JLabel("Gender: ", JLabel.RIGHT);
			Label[5] = new JLabel("Age: ", JLabel.RIGHT); 
         Label[6] = new JLabel("Academic Level: ", JLabel.RIGHT);
			Text[4] = new JTextField("", 20); 
         Text[5] = Text[5] = new JTextField("", 20);
			Text[6] = new JTextField("", 20);
			String[] StudentGender = { "Male", "Female" }; 
			String[] Year = { "Freshman", "Sophomore", "Junior", "Senior" };
			ComboBox[0] = new JComboBox(StudentGender); 
         ComboBox[0].setSelectedIndex(0);
			ComboBox[1] = new JComboBox(Year); 
         ComboBox[1].setSelectedIndex(0);

			data[6].add(Label[2]); 
         data[6].add(Text[4]); 
         data[6].add(Label[3]); 
			data[6].add(Text[5]); 
         data[6].add(Label[4]); 
         data[6].add(ComboBox[0]);
			data[6].add(Label[5]); 
         data[6].add(Text[6]); 
         data[6].add(Label[6]);
			data[6].add(ComboBox[1]);
			
			data[7] = new JPanel(new FlowLayout());
			for (int i = 12; i < 14; i += 1)
			{
				switch(i)
				{
				case 12:
					Button[12] = new JButton("Save And Add Student "); 
               break;
				case 13:
					Button[13] = new JButton("       Cancel        "); 
               break;
				}
				data[7].add(Button[i]); 
				Button[i].addActionListener(Action);
			}
			Card[3].add(TopLabel[3], BorderLayout.NORTH); 
         Card[3].add(data[6], BorderLayout.EAST);
			Card[3].add(data[7], BorderLayout.SOUTH);
		
		//Card 4 
			TopLabel[4] = new JLabel("<html><font size = 5><b>Enter Lab Scores</b></html>", JLabel.CENTER);
			data[8] = new JPanel(new FlowLayout());
			Label[7] = new JLabel("Lab Number: "); 
         Label[8] = new JLabel("Score For: ");
			Text[7] = new JTextField("", 10); 
         Text[8] = new JTextField("", 20);
			Text[7].addActionListener(Action); 
         Text[8].addActionListener(Action);
			
			data[8].add(Label[7]); 
         data[8].add(Text[7]); 
			data[8].add(Label[8]); 
         data[8].add(Text[8]);
			
			data[9] = new JPanel(new FlowLayout());
			Button[14] = new JButton("       Cancel        ");
			data[9].add(Button[14]); 
			Button[14].addActionListener(Action);
			
			Card[4].add(TopLabel[4], BorderLayout.NORTH); Card[4].add(data[8], BorderLayout.CENTER);
			Card[4].add(data[9], BorderLayout.SOUTH);
		
		//Card 5 
			TopLabel[5] = new JLabel("<html><font size = 5><b>Sort Students</b></html>", JLabel.CENTER);
			data[10] = new JPanel(new FlowLayout());
			JRadioButton IDList = new JRadioButton("By ID"); 
         JRadioButton NameList = new JRadioButton("By Name");
			
			data[10].setVisible(true); 
         data[10].add(IDList); 
         data[10].add(NameList);
			
			data[11] = new JPanel(new FlowLayout());
			for (int i = 15; i < 17; i += 1)
			{
				switch(i)
				{
				case 15:
					Button[15] = new JButton("Save And Add Student "); 
               break;
				case 16:
					Button[16] = new JButton("       Cancel        ");
                break;
				}
				data[11].add(Button[i]); 
				Button[i].addActionListener(Action);
			}
			Card[5].add(TopLabel[5], BorderLayout.NORTH); 
         Card[5].add(data[10], BorderLayout.CENTER);
			Card[5].add(data[11], BorderLayout.SOUTH);
		
		//Card 6 
			TopLabel[6] = new JLabel("<html><font size = 5><b>Student List</b></html>", JLabel.CENTER);

			data[13] = new JPanel(new FlowLayout());
			for (int i = 17; i < 19; i += 1)
			{
				switch(i)
				{
				case 17:
					Button[17] = new JButton("   Delete Student    "); 
               break;
				case 18:
					Button[18] = new JButton("       Cancel        "); 
               break;
				}
				data[13].add(Button[i]); 
				Button[i].addActionListener(Action);
			}
			Card[6].add(TopLabel[6], BorderLayout.NORTH); 
         Card[6].add(data[13], BorderLayout.SOUTH);	
			Card[6].add(ScrollPane); 


			contentPane.add("Card[0]", Card[0]);
			contentPane.add("Card[1]", Card[1]);
			contentPane.add("Card[2]", Card[2]);
			contentPane.add("Card[3]", Card[3]);
			contentPane.add("Card[4]", Card[4]);
			contentPane.add("Card[5]", Card[5]);
			contentPane.add("Card[6]", Card[6]);
			contentPane.add("Card[7]", Card[7]);
			contentPaneLayout.show(contentPane, "Card[0]");
			
			frame.pack();
			frame.setSize(725, 300);
			frame.setResizable(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
	}
	public void actionPerformed (ActionEvent e) 
	{
		Object source = e.getSource();
		if (source == Button[0]) { contentPaneLayout.show(contentPane, "Card[1]");}
		if (source == Button[1]) { contentPaneLayout.show(contentPane, "Card[2]");}
		if (source == Button[2]) { contentPaneLayout.show(contentPane, "Card[3]");}
		if (source == Button[3]) { contentPaneLayout.show(contentPane, "Card[4]");}
		if (source == Button[4]) { contentPaneLayout.show(contentPane, "Card[5]");}
		if (source == Button[5]) 
		{
			String[][] StudentData = new String[StudentCount][15];
			for (int i = 0; i < StudentCount; i += 1)
			{

				StudentData[i][0] = StudentArray[i].ID; 
            StudentData[i][1] = StudentArray[i].Name;
				StudentData[i][2] = StudentArray[i].ClassLevel; 
            StudentData[i][4] = StudentArray[i].Age;
				StudentData[i][3] = StudentArray[i].Gender + ""; 
            StudentData[i][5] = StudentArray[i].Lab1 + "";
				StudentData[i][6] = StudentArray[i].Lab2 + ""; 
            StudentData[i][7] = StudentArray[i].Lab3 + "";
				StudentData[i][8] = StudentArray[i].Lab4 + ""; 
            StudentData[i][9] = StudentArray[i].Lab5 + "";
				StudentData[i][10] = StudentArray[i].Lab6 + ""; 
            StudentData[i][11] = StudentArray[i].Lab7 + "";
				StudentData[i][12] = StudentArray[i].Lab8 + ""; 
            StudentData[i][13] = StudentArray[i].Lab9 + "";
				StudentData[i][14] = StudentArray[i].Lab10 + "";
			}
			Table = new JTable(StudentData, ColumnNames); 
			TempSP = new JScrollPane(Table);
			ScrollPane.setViewport(TempSP.getViewport());
			contentPaneLayout.show(contentPane, "Card[6]");
		}
		if (source == Button[6]) 
		{
				try { FileOutputStream FOS = new FileOutputStream(ClassName, false);
				ObjectOutputStream OOS = new ObjectOutputStream(FOS);
				for (int i = 0; i < StudentCount; i += 1)
				{
					OOS.writeObject(StudentArray[i]);
				}
				OOS.close();
			} catch (FileNotFoundException x) { x.toString(); } catch(IOException x) { x.printStackTrace(); }
		}
		if (source == Button[9] || source == Button[11] || source == Button[13] ||
			source == Button[14] || source == Button[16] || source == Button[18] ||
			source == Button[8]) 
		{ contentPaneLayout.show(contentPane, "Card[0]");}

		if (source == Button[8]) { ClassName = Text[2].getText(); StudentCount = 0;
								   Text[0].setText(ClassName); Text[1].setText("" + StudentCount); }
		if (source == Button[10])
		{
			ClassName = Text[3].getText(); Text[0].setText(ClassName);
			StudentCount = 0;
			try { FileInputStream FIS = new FileInputStream(ClassName);
				ObjectInputStream OIS = new ObjectInputStream(FIS);
				while (true) {
					StudentArray[StudentCount] = (Student)OIS.readObject(); StudentCount++;
				}
			} catch(EOFException x) {
			} catch(Exception x) { x.printStackTrace(); 
			}
			Text[0].setText(ClassName); Text[1].setText("" + StudentCount);
			contentPaneLayout.show(contentPane, "Card[0]");
		}
		if (source == Button[12]) 
		{
			Student Temporary = new Student();
			Temporary.ID = Text[4].getText();
			Temporary.Name = Text[5].getText();
			Temporary.Age = Text[6].getText();
			Temporary.Gender = ((String)ComboBox[0].getSelectedItem()).charAt(0);
			Temporary.ClassLevel = ((String)ComboBox[1].getSelectedItem());
			StudentArray[StudentCount] = Temporary;
			StudentCount++; String IntToString = String.valueOf(StudentCount); Text[1].setText(IntToString);
			Text[4].setText(""); Text[5].setText(""); Text[6].setText("");
			ComboBox[0].setSelectedIndex(0); ComboBox[1].setSelectedIndex(0);
		}
		
		if (source == Text[7])
		{
			CurrentStudent = 0;
			LabNumber = Integer.valueOf(Text[7].getText()).intValue();
			Label[8].setText("Score For " + StudentArray[CurrentStudent].Name + ": ");
		}
		if (source == Text[8])
		{
			int Score = Integer.valueOf(Text[8].getText()).intValue();
			switch (LabNumber)
			{
				case 1: StudentArray[CurrentStudent].Lab1 = Score; 
            break;
				case 2: StudentArray[CurrentStudent].Lab2 = Score; 
            break;
				case 3: StudentArray[CurrentStudent].Lab3 = Score; 
            break;
				case 4: StudentArray[CurrentStudent].Lab4 = Score; 
            break;
				case 5: StudentArray[CurrentStudent].Lab5 = Score; 
            break;
				case 6: StudentArray[CurrentStudent].Lab6 = Score; 
            break;
				case 7: StudentArray[CurrentStudent].Lab7 = Score; 
            break;
				case 8: StudentArray[CurrentStudent].Lab8 = Score; 
            break;
				case 9: StudentArray[CurrentStudent].Lab9 = Score; 
            break;
				case 10: StudentArray[CurrentStudent].Lab10 = Score; 
            break;
				default: System.out.println("Invalid Class");
			}
			CurrentStudent += 1;
			Text[8].setText("");
			if (CurrentStudent >= StudentCount) { LabNumber = 0; Text[7].setText(LabNumber + ""); Text[8].setText(""); Label[8].setText("Score For: "); return; }
			else { Label[8].setText("Score For " + StudentArray[CurrentStudent].Name + ": "); }
		}
		if (source == Button[17])
		{
			int StudentIndex = Table.getSelectedRow();
			for (int i = StudentIndex; i < StudentCount; i += 1)
			{
				StudentArray[i] = StudentArray[i + 1];
			}
			StudentCount--;
			String[][] StudentData = new String[StudentCount][15];
			for (int i = 0; i < StudentCount; i += 1)
			{
				StudentData[i][0] = StudentArray[i].ID; StudentData[i][1] = StudentArray[i].Name;
				StudentData[i][2] = StudentArray[i].ClassLevel; StudentData[i][4] = StudentArray[i].Age;
				StudentData[i][3] = StudentArray[i].Gender + ""; StudentData[i][5] = StudentArray[i].Lab1 + "";
				StudentData[i][6] = StudentArray[i].Lab2 + ""; StudentData[i][7] = StudentArray[i].Lab3 + "";
				StudentData[i][8] = StudentArray[i].Lab4 + ""; StudentData[i][9] = StudentArray[i].Lab5 + "";
				StudentData[i][10] = StudentArray[i].Lab6 + ""; StudentData[i][11] = StudentArray[i].Lab7 + "";
				StudentData[i][12] = StudentArray[i].Lab8 + ""; StudentData[i][13] = StudentArray[i].Lab9 + "";
				StudentData[i][14] = StudentArray[i].Lab10 + "";
			}
			Table = new JTable(StudentData, ColumnNames); 
			JScrollPane TempSP2 = new JScrollPane(Table);
			ScrollPane.setViewport(TempSP2.getViewport());
			Text[1].setText(StudentCount + "");
			contentPaneLayout.show(contentPane, "Card[6]");
		}
		if (source == Button[7]) { System.exit(0);}
	}
}