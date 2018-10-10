package com.nhan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class QuizCardBuilder {
	private JFrame mainWindow;
	private JTextArea questionArea;
	private JTextArea answerArea;
	private ArrayList<QuizCard> cardList;

	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
	}

	public void go() {

		mainWindow = new JFrame("Quiz Card Builder");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("Calibri", Font.BOLD, 24);

		questionArea = new JTextArea(6, 20);
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		questionArea.setFont(bigFont);

		JScrollPane qScroller = new JScrollPane(questionArea);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		answerArea = new JTextArea(6, 20);
		answerArea.setFont(bigFont);
		answerArea.setWrapStyleWord(true);
		answerArea.setLineWrap(true);

		JScrollPane aScroller = new JScrollPane(answerArea);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton nextButton = new JButton("Next QuizCard");

		cardList = new ArrayList<QuizCard>();
		JLabel qLabel = new JLabel("Question:");
		JLabel aLabel = new JLabel("Answer:  ");

		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);

		nextButton.addActionListener(new NextCardListener());

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(new newCardListener());
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new saveCardListener());

		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(BorderLayout.CENTER, mainPanel);

		mainWindow.setSize(500, 600);
		mainWindow.setVisible(true);
	}

	class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			QuizCard card = new QuizCard(questionArea.getText(), answerArea.getText());
			cardList.add(card);
			clearCard();
		}

	}

	class newCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardList.clear();
			clearCard();
		}
	}

	class saveCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(mainWindow);
			saveFile(fileSave.getSelectedFile());
		}
	}

	private void clearCard() {
		questionArea.setText("");
		answerArea.setText("");
		questionArea.requestFocus();
	}

	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + " / ");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Couldn't write the card list out!!");
			e.printStackTrace();

		}
	}
}
