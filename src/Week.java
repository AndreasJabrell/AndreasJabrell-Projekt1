import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Week {
    JFrame frame;
    JPanel jPanelThisWeek = new JPanel();
    JPanel jPanelNextWeek = new JPanel();
    JPanel pnlBtnNextWeek = new JPanel();
    public static JPanel[] arrayPanel = new JPanel[7];
    public static JPanel[] arrayPanel2 = new JPanel[7];
    public static JLabel[] arrayLabel = new JLabel[7];
    JButton[] arrayButton = new JButton[7];
    JButton[] arrayButton2 = new JButton[7];
    JTextArea[] arrayTextArea = new JTextArea[7];
    JTextArea[] arrayTextArea2 = new JTextArea[7];
    BorderLayout borderLayout = new BorderLayout();
    GridLayout gridLayout = new GridLayout(0, 8);
    CardLayout cardLayout = new CardLayout();
    JButton btnChangeWeek = new JButton("Hoppa till nästa vecka");
    JButton btnChangeWeekBack = new JButton("Hoppa tillbaka");
    Border blackline = BorderFactory.createLineBorder(Color.black);
    public static final String[] veckodagar = {"måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag"};
    public static Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    Container container;

    Week() {
        //skapa ett nytt fönster
        frame = new JFrame("Kalender");
        //sätt storlek på fönstret
        frame.setSize(1700, 800);
        frame.setLayout(cardLayout);
        container = frame.getContentPane();
        container.setLayout(cardLayout);
        //bestäm vad som ska hända när vi stänger fönstret
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //skapar en container(som är ihopkopplad med min frame) där jag kan plocka ut vilken dag som ska synas i cardLayout
        container.add("1", jPanelThisWeek);
        container.add("2", jPanelNextWeek);
        //sätter Gridlayout på veckan i kolumner
        jPanelThisWeek.setLayout(gridLayout);
        jPanelNextWeek.setLayout(gridLayout);
        pnlBtnNextWeek.setLayout(gridLayout);
        //knappar för att byta till och från veckorna, hoppar vidare i cardlayout
        btnChangeWeek.addActionListener(actionEvent -> cardLayout.next(container));
        btnChangeWeekBack.addActionListener(actionEvent -> cardLayout.next(container));
        //for loop går igenom alla delar som ska synas i min kalender och sätter ut dem i de kolumner som angetts i gridlayout
        //skapar en panel, label, textarea och button som sätts ut på rätt ställen med borderlayout
        for (int i = 0; i <= 6; i++) {
            JPanel pnlDay = new JPanel();
            JLabel lblDay = new JLabel(veckodagar[i], SwingConstants.CENTER);
            //sätter ut fontstorlek på alla, "dagens" dag skrivs över med aktuell font längre fram
            lblDay.setFont(f1);
            JButton btnDay = new JButton("Lägg till event");
            btnDay.setActionCommand(i + "");
            btnDay.addActionListener(actionEvent -> {
                //string med värdet e.getActionCommand
                String btnPush = actionEvent.getActionCommand();
                /*int i som konverterar strängen ovan till en int för att kunna användas i if satsen nedan, hitta rätt knapp
                att koppla handlingen till*/
                int i1 = Integer.parseInt(btnPush);
                if (arrayButton[i1].getText().equals("Lägg till event")) {
                    arrayPanel[i1].add(arrayTextArea[i1], BorderLayout.CENTER);
                    arrayTextArea[i1].setEnabled(true);
                    arrayTextArea[i1].setEditable(true);
                    arrayButton[i1].setText("Bekräfta");
                } else {
                    arrayTextArea[i1].setEnabled(false);
                    arrayTextArea[i1].setEditable(false);
                    arrayButton[i1].setText("Lägg till event");
                }

            });
            pnlDay.setBorder(blackline);
            pnlDay.setLayout(borderLayout);
            pnlDay.add(lblDay, BorderLayout.NORTH);
            pnlDay.add(btnDay, BorderLayout.SOUTH);
            arrayLabel[i] = lblDay;
            arrayPanel[i] = pnlDay;
            arrayButton[i] = btnDay;
            arrayTextArea[i] = new JTextArea("Vad vill du hitta på idag?");
            jPanelThisWeek.add(pnlDay);
            frame.setVisible(true);
        }
        //lägger till knapp för att byta vecka
        jPanelThisWeek.add(btnChangeWeek);
        //tar in en metod från Day klassen för att ta fram vilken dag det är i dag och markera ut den
        Day.WhatDay();

        //nästan likadan for-loop som tidigare, enda skillnaden är vilken panel allt läggs i, vilket är nästa veckas
        for (int i = 0; i <= 6; i++) {
            JPanel pnlDay = new JPanel();
            JLabel lblDay = new JLabel(veckodagar[i], SwingConstants.CENTER);
            lblDay.setFont(f1);
            JButton btnDay = new JButton("Lägg till event");
            btnDay.setActionCommand(i + "");
            //har fått uppdatera lite i knappen för att allt ska sättas till rätt vecka
            btnDay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //string med värdet e.getActionCommand
                    String btnPush = actionEvent.getActionCommand();
                    //int i som konverterar strängen ovan till en int för att kunna användas i if satsen nedan, hitta rätt knapp
                    //att koppla handlingen till
                    int i = Integer.parseInt(btnPush);

                    if (arrayButton2[i].getText().equals("Lägg till event")) {
                        arrayPanel2[i].add(arrayTextArea2[i], BorderLayout.CENTER);
                        arrayTextArea2[i].setEnabled(true);
                        arrayTextArea2[i].setEditable(true);
                        arrayButton2[i].setText("Bekräfta");
                    } else {
                        arrayTextArea2[i].setEnabled(false);
                        arrayTextArea2[i].setEditable(false);
                        arrayButton2[i].setText("Lägg till event");
                    }
                }

            });
            pnlDay.setBorder(blackline);
            pnlDay.setLayout(borderLayout);
            pnlDay.add(lblDay, BorderLayout.NORTH);
            pnlDay.add(btnDay, BorderLayout.SOUTH);
            arrayLabel[i] = lblDay;
            arrayPanel2[i] = pnlDay;
            arrayButton2[i] = btnDay;
            arrayTextArea2[i] = new JTextArea("Vad vill du hitta på idag?");
            jPanelNextWeek.add(pnlDay);
            frame.setVisible(true);
        }
        //lägger till knappen för att byta tillbaka till nuvarande vecka
        jPanelNextWeek.add(btnChangeWeekBack);
        //tar in nästa metod från Day klassen, för att ta fram kommande måndag
        Day.WhatDayNextWeek();
        //set visible så allt syns, svårt med kalender annars
        frame.setVisible(true);
    }
}
