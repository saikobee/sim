/*
 * ControlFrame.java   --   created on Jan 23, 2012, 4:18:47 PM
 * @author levenick
 */
package pris;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class ControlFrame extends JFrame {

    PrisFrame thePrisFrame;

    public ControlFrame(PrisFrame f) {
        thePrisFrame = f;
        initComponents();
        setBounds(800, 10, 500, 500);
        setVisible(true);
        update();
    }

    public void paint(Graphics g) {
        super.paint(g);

    }

    void setSlider(JSlider aSlider, JTextField aTF) {
        aSlider.setValue(Integer.parseInt(aTF.getText()));
    }

    void setTF(JSlider aSlider, JTextField aTF) {
        aTF.setText("" + aSlider.getValue());
    }

    void update() {
        Params.delay = delaySlider.getValue();
        Params.size = sizeSlider.getValue();
        Params.ccPayoff = ccSlider.getValue();
        Params.dcPayoff = dcSlider.getValue();
        Params.ddPayoff = ddSlider.getValue();
        Params.rounds = roundsSlider.getValue();
        Params.percentCoop = percentSlider.getValue();

        setTF(delaySlider, delayTF);
        setTF(sizeSlider, sizeTF);
        setTF(ccSlider, ccTF);
        setTF(dcSlider, dcTF);
        setTF(ddSlider, ddTF);
        setTF(roundsSlider, roundsTF);
        setTF(percentSlider, percentTF);
    }

    private void initComponents() {

        goStopButton = new JButton();
        delaySlider = new JSlider();
        delayTF = new JTextField();
        sizeTF = new JTextField();
        sizeSlider = new JSlider();
        ccSlider = new JSlider();
        ccTF = new JTextField();
        dcSlider = new JSlider();
        dcTF = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        stepButton = new JButton();
        ddSlider = new JSlider();
        ddTF = new JTextField();
        jLabel5 = new JLabel();
        roundsSlider = new JSlider();
        roundsTF = new JTextField();
        jLabel6 = new JLabel();
        percentSlider = new JSlider();
        percentTF = new JTextField();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        goStopButton.setText("go");
        goStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goStopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(goStopButton);
        goStopButton.setBounds(100, 110, 75, 29);

        delaySlider.setMaximum(1000);
        delaySlider.setMinorTickSpacing(1);
        delaySlider.setSnapToTicks(true);
        delaySlider.setValue(100);
        delaySlider.setAutoscrolls(true);
        delaySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                delaySliderStateChanged(evt);
            }
        });
        getContentPane().add(delaySlider);
        delaySlider.setBounds(40, 30, 190, 29);

        delayTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayTFActionPerformed(evt);
            }
        });
        getContentPane().add(delayTF);
        delayTF.setBounds(260, 30, 110, 28);

        sizeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeTFActionPerformed(evt);
            }
        });
        getContentPane().add(sizeTF);
        sizeTF.setBounds(260, 60, 120, 28);

        sizeSlider.setMaximum(1000);
        sizeSlider.setMinimum(5);
        sizeSlider.setValue(200);
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                sizeSliderStateChanged(evt);
            }
        });
        getContentPane().add(sizeSlider);
        sizeSlider.setBounds(50, 60, 190, 29);

        ccSlider.setMajorTickSpacing(1);
        ccSlider.setPaintTicks(true);
        ccSlider.setSnapToTicks(true);
        ccSlider.setValue(48);
        ccSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                ccSliderStateChanged(evt);
            }
        });
        getContentPane().add(ccSlider);
        ccSlider.setBounds(10, 180, 390, 38);

        ccTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccTFActionPerformed(evt);
            }
        });
        getContentPane().add(ccTF);
        ccTF.setBounds(50, 160, 110, 28);

        dcSlider.setMajorTickSpacing(1);
        dcSlider.setPaintTicks(true);
        dcSlider.setSnapToTicks(true);
        dcSlider.setValue(77);
        dcSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                dcSliderStateChanged(evt);
            }
        });
        getContentPane().add(dcSlider);
        dcSlider.setBounds(20, 260, 390, 38);

        dcTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcTFActionPerformed(evt);
            }
        });
        getContentPane().add(dcTF);
        dcTF.setBounds(50, 240, 110, 28);

        jLabel1.setText("CC");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 160, 18, 16);

        jLabel2.setText("DC");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 240, 45, 16);

        jLabel3.setText("delay");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 20, 33, 16);

        jLabel4.setText("size");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 60, 25, 16);

        stepButton.setText("step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stepButton);
        stepButton.setBounds(200, 110, 75, 29);

        ddSlider.setMajorTickSpacing(1);
        ddSlider.setMaximum(30);
        ddSlider.setPaintTicks(true);
        ddSlider.setSnapToTicks(true);
        ddSlider.setValue(0);
        ddSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                ddSliderStateChanged(evt);
            }
        });
        getContentPane().add(ddSlider);
        ddSlider.setBounds(20, 320, 180, 38);

        ddTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddTFActionPerformed(evt);
            }
        });
        getContentPane().add(ddTF);
        ddTF.setBounds(50, 300, 110, 28);

        jLabel5.setText("DD");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 300, 45, 16);

        roundsSlider.setMajorTickSpacing(1);
        roundsSlider.setMaximum(20);
        roundsSlider.setPaintLabels(true);
        roundsSlider.setPaintTicks(true);
        roundsSlider.setSnapToTicks(true);
        roundsSlider.setValue(1);
        roundsSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                roundsSliderStateChanged(evt);
            }
        });
        getContentPane().add(roundsSlider);
        roundsSlider.setBounds(10, 390, 330, 52);

        roundsTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundsTFActionPerformed(evt);
            }
        });
        getContentPane().add(roundsTF);
        roundsTF.setBounds(150, 370, 110, 28);

        jLabel6.setText("Rounds");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 370, 60, 16);

        percentSlider.setMajorTickSpacing(10);
        percentSlider.setOrientation(JSlider.VERTICAL);
        percentSlider.setPaintTicks(true);
        percentSlider.setSnapToTicks(true);
        percentSlider.setValue(100);
        percentSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                percentSliderStateChanged(evt);
            }
        });
        getContentPane().add(percentSlider);
        percentSlider.setBounds(470, 30, 40, 410);

        percentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentTFActionPerformed(evt);
            }
        });
        getContentPane().add(percentTF);
        percentTF.setBounds(440, 10, 110, 28);

        jLabel7.setText("%coop");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(470, 440, 60, 16);

        jLabel8.setText("0");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(500, 420, 8, 16);

        jLabel9.setText("100");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(500, 30, 24, 20);

        pack();
    }

    private void goStopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        thePrisFrame.toggleRunning();
        if (thePrisFrame.running) {
            goStopButton.setText("stop");
        } else {
            goStopButton.setText("go");
        }
    }

    private void delaySliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void delayTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(delaySlider, delayTF);
        update();
    }

    private void sizeTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(sizeSlider, sizeTF);
        update();
    }

    private void sizeSliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void ccSliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void ccTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(ccSlider, ccTF);
        update();
    }

    private void dcSliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void dcTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(dcSlider, dcTF);
        update();
    }

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {
        goStopButton.setText("go");
        thePrisFrame.step();

    }

    private void ddSliderStateChanged(ChangeEvent evt) {
                update();
    }

    private void ddTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(ddSlider, ddTF);
        update();
    }

    private void roundsSliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void roundsTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(roundsSlider, roundsTF);
        update();
    }

    private void percentSliderStateChanged(ChangeEvent evt) {
        update();
    }

    private void percentTFActionPerformed(java.awt.event.ActionEvent evt) {
        setSlider(percentSlider, percentTF);
        update();
    }

    private JSlider ccSlider;
    private JSlider dcSlider;
    private JSlider ddSlider;
    private JSlider delaySlider;
    private JSlider roundsSlider;
    private JSlider sizeSlider;
    private JSlider percentSlider;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField ccTF;
    private JTextField dcTF;
    private JTextField ddTF;
    private JTextField delayTF;
    private JButton goStopButton;
    private JButton stepButton;
    private JTextField percentTF;
    private JTextField roundsTF;
    private JTextField sizeTF;
}
