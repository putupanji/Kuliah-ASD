package Sudoku;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #3
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class SudokuStartMenu extends JFrame{

    public static void main(String[] args) throws Exception {
        
        //Frame declaration
        JFrame frm = new JFrame("Sudoku Game");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frm.setMinimumSize(new Dimension(800,700));
        
        //Button declaration
        JButton btnStart = new JButton("Start");
        btnStart.setFocusable(false);
        btnStart.setVerticalTextPosition(JButton.BOTTOM);
        btnStart.setFont(new Font("Poppins",Font.BOLD,17));
        btnStart.setForeground(new Color(34,34,34));
        btnStart.setBackground(new Color(242,235,211));     
       
        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frm.setMinimumSize(new Dimension(650,650));

        //Button size
        int btnHeight = 50;
        int btnWidth = 300;

        //Insert background image
        BufferedImage bgImage = ImageIO.read(new File("Sudoku/bg-sudoku.png"));
        Image scaledBgImage = bgImage.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledBgImage);
        JLabel background = new JLabel(backgroundImage);
        background.setIcon(backgroundImage);
        background.setHorizontalAlignment(JLabel.CENTER);

        //Declare menu item
        JMenuItem menuItem;
        JDialog aboutDeveloper;
        JTextArea textArea;
        
        //Menu item
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("                          Menu");
        menu.setSize(1000, 50);;
        menu.setFont(new Font("Poppins",Font.BOLD,17));
        menu.setForeground(new Color(34,34,34));
        menu.setBackground(new Color(242,235,211));  
        
        int menuHeight = 50;
        int menuWidth = 300;
        menu.setPreferredSize(new Dimension(menuWidth, menuHeight));

        menuItem = new JMenuItem("                              About Developer");
        menuItem.setPreferredSize(menu.getPreferredSize());
        aboutDeveloper = new JDialog();
        Icon developerImage = new ImageIcon("foto-fp.jpg");
        JLabel labelImage = new JLabel(developerImage); 
        menuItem.addActionListener(e -> {
           aboutDeveloper.setVisible(true);
        });
        
        //Add the menu item into menu
        menu.add(menuItem);
        menuBar.add(menu);
        frm.add(menuBar);
      
        //Initialize the about menu
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Poppins", Font.PLAIN,13));
        textArea.setWrapStyleWord(true);
        JLabel labelAbout = new JLabel("Capstone Project - Group #3 (2.0 Version)");
        String aboutText = "This Sudoku and TicTacToe game is part of the final project assignment for the Data Structure and Algorithms course. Sudoku is created by applying the DFS algorithm and the Stack data structure.\n\n"+
                 "The following is our project team:\n" +
                 "1. Andika Cahya Sutisna (5026221013)\n" +
                 "2. Muhammad Ahdaf Amali (5026221129)\n" +
                 "3. Putu Panji Wiradharma (5026221170)\n";

        textArea.setText(aboutText);
        textArea.setBackground(aboutDeveloper.getBackground());
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        aboutDeveloper.setLayout(new BorderLayout());
        aboutDeveloper.add(labelImage, BorderLayout.NORTH);
        aboutDeveloper.add(labelAbout, BorderLayout.CENTER);
        aboutDeveloper.add(textArea, BorderLayout.CENTER);
        aboutDeveloper.setSize(500, 350);
        aboutDeveloper.setLocationRelativeTo(null);
        aboutDeveloper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Copyright label
        JLabel Copyright = new JLabel("@Copyright Kelompok 3");
        Copyright.setFont(new Font("Poppins", Font.PLAIN,14));
        Copyright.setForeground(Color.WHITE);

        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        new Main();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                });

            }
            
        });

        //Set bounds of each part of start menu
        frm.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Dimension size = frm.getSize();
                Dimension CopyrightSize = Copyright.getPreferredSize();
                int height = (int) size.getHeight();
                int width = (int) size.getWidth();

                background.setBounds(0, 0, width, height);
                btnStart.setBounds((width * 1 / 2) - (btnWidth / 2), (height * 3 / 4) - (btnHeight / 2), btnWidth, btnHeight);
                menuBar.setBounds((width * 1 / 2) - (menuWidth / 2), (height - menuHeight - 25)* 7/8, menuWidth, menuHeight);

                Copyright.setBounds((width - CopyrightSize.width ) / 2, height * 7 /8, CopyrightSize.width, CopyrightSize.height);
            }
        });

        // Play sound when play game
        try{
        Sound music  = new Sound("words.wav");
        music.play();
        }catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frm.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - frm.getHeight()) / 2);

        frm.add(Copyright);
        frm.add(btnStart);
        frm.add(background);

        frm.setLayout(null);
        frm.setVisible(true);
    }
}