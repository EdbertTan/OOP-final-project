package com.journaldev.readfileslinebyline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Color;



public class Robokey extends JFrame { //makes the gui

    public int rmax;
    public int rnow;

    ArrayList<String> adata = new ArrayList<String>();
    public static String[] cdata;

    public class dbcsv {
        //reads the data from the csv file and then converts it into an araylist then into an array
        public int read() {

            String atext;
            int ncount=0;


            BufferedReader reader;

            try {
                reader = new BufferedReader(new FileReader("C:/Users/3dber/IdeaProjects/robokeyfinal/src/robocsv.csv"));
                String line = reader.readLine();

                while (line != null) {
                    atext = line;
                    adata.add(atext);
                    ncount = ncount+1;

                    line = reader.readLine();
                }

                reader.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }

            ncount=ncount-1;
            cdata = new String[ncount];

            for (int i = 0; i < ncount; i = i + 1) {
                cdata[i]=adata.get(i);
            }
            return ncount;
        }

        public String data(int nrow) {
            return cdata[nrow];
        }

    }

    public Robokey() {

        super("Robokey");

        int nfontsize = 14;

        // create panel
        JPanel p1  = new JPanel( new FlowLayout(FlowLayout.LEFT) );

        // create button
        JButton bxy = new JButton("X Y");
        bxy.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create text area and put in scroll pane
        JTextArea torder = new JTextArea(2,20);
        torder.setFont(new Font("Arial", Font.BOLD, nfontsize));
        torder.setForeground(new Color(0, 0, 255));
        torder.setBackground(new Color(255, 255, 255));
        JScrollPane ts1 = new JScrollPane(torder);

        // create button
        JButton bsave = new JButton("SAVE");
        bsave.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create button
        JButton bload = new JButton("LOAD");
        bload.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create button
        JButton bread = new JButton("READ");
        bread.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create button
        JButton bfor = new JButton("<");
        bfor.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create text area and put in scroll able pane
        JTextArea tdata = new JTextArea("",1,15);
        tdata.setFont(new Font("Arial", Font.BOLD, nfontsize));
        tdata.setForeground(new Color(0, 0, 255));
        tdata.setBackground(new Color(255, 255, 255));
        tdata.setEditable(false);
        JScrollPane ts3 = new JScrollPane(tdata);

        // create button
        JButton bnext = new JButton(">");
        bnext.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // create button
        JButton brun = new JButton("RUN");
        brun.setFont(new Font("Arial", Font.BOLD, nfontsize));

        // set up panel and attach buttons and scroll able text area
        JTextArea trules = new JTextArea("B?>TextBox\nT?>Time and Date\nS?>Scrollbox\nR?>RadioButton\nC?>CheckBox",2,10);
        trules.setFont(new Font("Arial", Font.BOLD, nfontsize));
        trules.setForeground(new Color(0, 0, 255));
        trules.setBackground(new Color(255, 255, 255));
        trules.setEditable(false);
        JScrollPane ts2 = new JScrollPane(trules);

        p1.add(bxy);

        p1.add(ts1);

        p1.add(bsave);

        p1.add(bload);

        p1.add(bread);

        p1.add(bfor);

        p1.add(ts3);

        p1.add(bnext);

        p1.add(brun);

        p1.add(ts2);


// =================================================================

        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // setup action on mouse click
                int x=e.getX();
                int y=e.getY();
                bxy.setText("X "+x+" Y "+y);
            }
        });

        // setup action on button click
        bsave.addActionListener(new ActionListener() { // save
            public void actionPerformed(ActionEvent e)
            {
                // write to file
                try {
                    FileWriter myWriter = new FileWriter("C:/Users/3dber/IdeaProjects/robokeyfinal/src/robotxt.txt");

                    String txt = torder.getText();

                    myWriter.write(txt);
                    torder.setText(txt);

                    myWriter.close();
                } catch (IOException e2) {
                    System.out.println("An error occurred.");
                    e2.printStackTrace();
                }
            }
        });

        // setup action on button click
        bload.addActionListener(new ActionListener() { // load
            public void actionPerformed(ActionEvent e)
            {
                // read from file
                try {
                    File myObj = new File("C:/Users/3dber/IdeaProjects/robokeyfinal/src/robotxt.txt");
                    Scanner myReader = new Scanner(myObj);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        torder.setText(data);
                    }
                    myReader.close();
                } catch (FileNotFoundException e2) {
                    System.out.println("An error occurred.");
                    e2.printStackTrace();
                }
            }
        });

        // setup action on button click
        bread.addActionListener(new ActionListener() { // read data
            public void actionPerformed(ActionEvent e)
            {
                String atext;

                dbcsv db = new dbcsv();

                rmax = db.read();
                rnow = 1;

                atext = db.data(rnow);
                String[] asplit = atext.split(",");
                tdata.setText(asplit[0]+">"+asplit[1]);


            }
        });

        // setup action on button click
        bfor.addActionListener(new ActionListener() { // previous data
            public void actionPerformed(ActionEvent e)
            {
                if (rnow>1) {
                    rnow = rnow-1;
                }

                String atext;

                dbcsv db = new dbcsv();

                atext = db.data(rnow);

                String[] asplit = atext.split(",");

                tdata.setText(asplit[0]+">"+asplit[1]);
            }
        });

        // setup action on button click
        bnext.addActionListener(new ActionListener() { // next data
            public void actionPerformed(ActionEvent e)
            {
                if (rnow<rmax-1) {
                    rnow = rnow+1;
                }
                tdata.setText(String.valueOf(rnow));

                String atext;

                dbcsv db = new dbcsv();
                atext = db.data(rnow);
                String[] asplit = atext.split(",");
                tdata.setText(asplit[0]+">"+asplit[1]);

            }
        });

        // setup action on button click
        brun.addActionListener(new ActionListener() { // RUN
            public void actionPerformed(ActionEvent e)
            {
                // get csv data on corespond row
                String atext;

                dbcsv db = new dbcsv();
                atext = db.data(rnow);
                String[] adata = atext.split(",");


                String atext2 = torder.getText();




// ====================================



                String[] commands = atext2.split("#");

                try{
                    Robot r = new Robot();{
                        r.mouseMove(Integer.parseInt(commands[0]), Integer.parseInt(commands[1])); //gets mouse into clicking place
                        r.mousePress(InputEvent.BUTTON1_DOWN_MASK); //clicks mouse to start typing on text box
                        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                        for (int z=0;z< commands.length;z++) {
                            char firstlettercomm = commands[z].charAt(0);
                            if (firstlettercomm=='N'){ //tabs to get to next part of form
                                r.keyPress(KeyEvent.VK_TAB);
                                r.keyRelease(KeyEvent.VK_TAB);
                            }
                            else { //commands if not N (tab)
                                switch (firstlettercomm) {
                                    case 'B': //types name
                                        int commandchoice = Integer.parseInt(commands[z].substring(1));
                                        char[] capletters = adata[commandchoice - 1].toUpperCase().toCharArray();
                                        char[] smallletters = adata[commandchoice-1].toCharArray();
                                        for (int l = 0; l < capletters.length; l++) {
                                            int letterascii = capletters[l];
                                            int identifierascii = smallletters[l];
                                            if (identifierascii<=90){
                                                r.keyPress(KeyEvent.VK_SHIFT);
                                                r.keyPress(letterascii);
                                                r.keyRelease(letterascii);
                                                r.keyRelease(KeyEvent.VK_SHIFT);
                                            }
                                            else if (identifierascii>90){
                                                r.keyPress(letterascii);
                                                r.keyRelease(letterascii);
                                            }

                                        }
                                        break;
                                    case 'T': //types dates and time
                                        int commandchoice3 = Integer.parseInt(commands[z].substring(1));
                                        char[] lets = adata[commandchoice3 - 1].toCharArray();
                                        for (int l = 0; l < lets.length; l++) {
                                            int letterascii = lets[l];
                                            if (lets[l]!=':') {
                                                r.keyPress(letterascii);
                                                r.keyRelease(letterascii);
                                            }
                                        }
                                        r.keyPress(KeyEvent.VK_TAB);
                                        r.keyRelease(KeyEvent.VK_TAB);
                                        break;
                                    case 'R': //chooses radio button
                                        int commchoice = Integer.parseInt(commands[z].substring(1));
                                        int repeatamt = Integer.parseInt(adata[commchoice-1]);
                                        if (repeatamt>1){
                                            for (int i=0;i<repeatamt-1;i++) {
                                                r.keyPress(KeyEvent.VK_DOWN);
                                                r.keyRelease(KeyEvent.VK_DOWN);
                                            }
                                        }
                                        break;
                                    case 'S': //chooses scrollbox
                                        int commandchoice2 = Integer.parseInt(commands[z].substring(1));
                                        int repeatamount = Integer.parseInt(adata[commandchoice2-1]);
                                        if (repeatamount > 1) {
                                            for (int i=0;i<repeatamount-1;i++) {
                                                r.keyPress(KeyEvent.VK_DOWN);
                                                r.keyRelease(KeyEvent.VK_DOWN);
                                            }
                                        }
                                        break;
                                    case 'C': //approve checkbox
                                        r.keyPress(KeyEvent.VK_SPACE);
                                        r.keyRelease(KeyEvent.VK_SPACE);
                                        break;
                                }
                            }


                        }
                    }
                }
                catch (Exception evt) {
                    System.err.println(evt.getMessage());
                }




// =============================













            }
        });


// =================================================================

        p1.addMouseListener(new MouseAdapter() { // get mouse x y position
            @Override
            public void mousePressed(MouseEvent e) { // setup action when panel click
                int x=e.getX();
                int y=e.getY();
                bxy.setText("X "+x+" Y "+y);
            }
        });

        setContentPane(p1);
        setBackground(new Color(0, 50, 0, 5));
        setSize(1250, 80); // 500,500
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) { //real program starts here

        JFrame.setDefaultLookAndFeelDecorated(true);
        Robokey frame = new Robokey();

        frame.setVisible(true);

    }

}