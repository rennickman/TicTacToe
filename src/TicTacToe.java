import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
	
	// Initialize random, frame, panels and text field
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textField = new JLabel();
	
	// Declare array of 9 buttons
	JButton[] buttons = new JButton[9];
	// Declare boolean to store if its player1's turn or not
	boolean player1_turn;
	
	// Constructor
	TicTacToe() {
		
		// Set frame to close by clicking x
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set size of frame
		frame.setSize(800, 800);
		// Set background color to be light shade of black
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		// Set to use a border layout
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Set background color of text field to black
		textField.setBackground(new Color(25, 25, 25));
		// Set color of text as green
		textField.setForeground(new Color(25, 255, 0));
		// Set font for text field
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		// Align the text in center
		textField.setHorizontalAlignment(JLabel.CENTER);
		// Set text of text field
		textField.setText("Tic-Tac-Toe");
		// Set text field as opaque - not transparent
		textField.setOpaque(true);
		
		// Set title panel to use border layout
		title_panel.setLayout(new BorderLayout());
		// Set position of title panel
		title_panel.setBounds(0, 0, 800, 100);
		
		// Set button panel to use grid layout - 3 by 3
		button_panel.setLayout(new GridLayout(3, 3));
		// Set background color of button panel to gray
		button_panel.setBackground(new Color(150, 150, 150));
		
		
		// Loop through the 9 buttons
		for (int i = 0; i < 9; i++) {
			// Assign each button to the buttons array
			buttons[i] = new JButton();
			// Add each button to the button panel
			button_panel.add(buttons[i]);
			// Set the font of each button
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			// Set the buttons not to highlight
			buttons[i].setFocusable(false);
			// Add action listener to each button
			buttons[i].addActionListener(this);
		}
		
		
		// Add text field to title panel
		title_panel.add(textField);
		// Add title panel to frame and position at the top
		frame.add(title_panel, BorderLayout.NORTH);
		// Add button panel to frame
		frame.add(button_panel);
		
		// Call first turn method
		firstTurn();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Loop through the 9 buttons
		for (int i = 0; i < 9; i++) {
			// Check to see if one of the 9 buttons was pressed
			if (e.getSource() == buttons[i]) {
				// Check if its player1's turn
				if (player1_turn) {
					// Check if button already has text
					if (buttons[i].getText() == "") {
						// Set color as blue
						buttons[i].setForeground(new Color(255, 0, 0));
						// Set the text as an X
						buttons[i].setText("X");
						// Change to O's turn
						player1_turn = false;
						textField.setText("O turn");
						// Call check function
						check();
					}
				} else {
					// Check if button already has text
					if (buttons[i].getText() == "") {
						// Set color as red
						buttons[i].setForeground(new Color(0, 0, 255));
						// Set the text as an O
						buttons[i].setText("O");
						// Change to x's turn
						player1_turn = true;
						textField.setText("X turn");
						// Call check function
						check();
					}
				}
			}
		}
		
	}
	
	public void firstTurn() {
		
		// Set a 2 second delay before showing whose turn it is
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Create a random number thats either 0 or 1 - 0 = x's turn, 1 = y's turn
		if (random.nextInt(2) == 0) {
			// Set player1's turn to true
			player1_turn = true;
			// Set text to say x's turn
			textField.setText("X turn");
		} else {
			// Set player1's turn to false
			player1_turn = false;
			// Set text to say o's turn
			textField.setText("O turn");
		}
	}
	
	public void check() {
		
		// Check X win conditions
		// Check if x wins the top row
		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[0].getText() == "X")) {
			xWins(0, 1, 2);
		}
		
		// Check if x wins the middle row
		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
			xWins(3, 4, 5);
		}
		
		// Check if x wins the bottom row
		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(6, 7, 8);
		}
		
		// Check if x wins the left column
		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(0, 3, 6);
		}
		
		// Check if x wins the middle column
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			xWins(1, 4, 7);
		}
		
		// Check if x wins the right column
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(2, 5, 8);
		}
		
		// Check if x wins diagonally
		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(2, 4, 6);
		}
		
		
		// Check o win conditions
		// Check if o wins the top row
		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[0].getText() == "O")) {
			oWins(0, 1, 2);
		}
				
		// Check if o wins the middle row
		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
			oWins(3, 4, 5);
		}
				
		// Check if o wins the bottom row
		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(6, 7, 8);
		}
				
		// Check if o wins the left column
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(0, 3, 6);
		}
				
		// Check if o wins the middle column
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			oWins(1, 4, 7);
		}
				
		// Check if o wins the right column
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O ")) {
			oWins(2, 5, 8);
		}
				
		// Check if o wins diagonally
		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(2, 4, 6);
		}
	}
	
	public void xWins(int a, int b, int c) {
		
		// Set color of winning buttons to green
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		// Loop through the 9 buttons
		for (int i = 0; i < 9; i++) {
			// Disable each of the buttons
			buttons[i].setEnabled(false);
		}
		
		// Add winner to text field
		textField.setText("X wins");
		
	}
	
	public void oWins(int a, int b, int c) {
		// Set color of winning buttons to green
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
				
		// Loop through the 9 buttons
		for (int i = 0; i < 9; i++) {
			// Disable each of the buttons
			buttons[i].setEnabled(false);
		}
				
		// Add winner to text field
		textField.setText("O wins");
	}
}
