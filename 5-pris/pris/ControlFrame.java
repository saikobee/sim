/*
 * ControlFrame.java   --   created on Jan 23, 2012, 4:18:47 PM
 * @author levenick
 */
package pris;

import java.awt.*;
import javax.swing.*;

public class ControlFrame extends javax.swing.JFrame {

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

    //  WARNING: Do NOT modify the Generated Code!
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        goStopButton = new javax.swing.JButton();
        delaySlider = new javax.swing.JSlider();
        delayTF = new javax.swing.JTextField();
        sizeTF = new javax.swing.JTextField();
        sizeSlider = new javax.swing.JSlider();
        ccSlider = new javax.swing.JSlider();
        ccTF = new javax.swing.JTextField();
        dcSlider = new javax.swing.JSlider();
        dcTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stepButton = new javax.swing.JButton();
        ddSlider = new javax.swing.JSlider();
        ddTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        roundsSlider = new javax.swing.JSlider();
        roundsTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        percentSlider = new javax.swing.JSlider();
        percentTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        delaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
        sizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sizeSliderStateChanged(evt);
            }
        });
        getContentPane().add(sizeSlider);
        sizeSlider.setBounds(50, 60, 190, 29);

        ccSlider.setMajorTickSpacing(1);
        ccSlider.setPaintTicks(true);
        ccSlider.setSnapToTicks(true);
        ccSlider.setValue(48);
        ccSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
        dcSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
        ddSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
        roundsSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
        percentSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        percentSlider.setPaintTicks(true);
        percentSlider.setSnapToTicks(true);
        percentSlider.setValue(100);
        percentSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
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
    }// </editor-fold>//GEN-END:initComponents

    private void goStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goStopButtonActionPerformed
        thePrisFrame.toggleRunning();
        if (thePrisFrame.running) {
            goStopButton.setText("stop");
        } else {
            goStopButton.setText("go");
        }
}//GEN-LAST:event_goStopButtonActionPerformed

    private void delaySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_delaySliderStateChanged
        update();
    }//GEN-LAST:event_delaySliderStateChanged

    private void delayTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayTFActionPerformed
        setSlider(delaySlider, delayTF);
        update();
    }//GEN-LAST:event_delayTFActionPerformed

    private void sizeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeTFActionPerformed
        setSlider(sizeSlider, sizeTF);
        update();
    }//GEN-LAST:event_sizeTFActionPerformed

    private void sizeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sizeSliderStateChanged
        update();
    }//GEN-LAST:event_sizeSliderStateChanged

    private void ccSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ccSliderStateChanged
        update();
    }//GEN-LAST:event_ccSliderStateChanged

    private void ccTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccTFActionPerformed
        setSlider(ccSlider, ccTF);
        update();
    }//GEN-LAST:event_ccTFActionPerformed

    private void dcSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dcSliderStateChanged
        update();
    }//GEN-LAST:event_dcSliderStateChanged

    private void dcTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcTFActionPerformed
        setSlider(dcSlider, dcTF);
        update();
    }//GEN-LAST:event_dcTFActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        goStopButton.setText("go");
        thePrisFrame.step();

    }//GEN-LAST:event_stepButtonActionPerformed

    private void ddSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ddSliderStateChanged
                update();
    }//GEN-LAST:event_ddSliderStateChanged

    private void ddTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddTFActionPerformed
        setSlider(ddSlider, ddTF);
        update();
    }//GEN-LAST:event_ddTFActionPerformed

    private void roundsSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_roundsSliderStateChanged
        update();
    }//GEN-LAST:event_roundsSliderStateChanged

    private void roundsTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundsTFActionPerformed
        setSlider(roundsSlider, roundsTF);
        update();
    }//GEN-LAST:event_roundsTFActionPerformed

    private void percentSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_percentSliderStateChanged
        update();
    }//GEN-LAST:event_percentSliderStateChanged

    private void percentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentTFActionPerformed
        setSlider(percentSlider, percentTF);
        update();
    }//GEN-LAST:event_percentTFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider ccSlider;
    private javax.swing.JTextField ccTF;
    private javax.swing.JSlider dcSlider;
    private javax.swing.JTextField dcTF;
    private javax.swing.JSlider ddSlider;
    private javax.swing.JTextField ddTF;
    private javax.swing.JSlider delaySlider;
    private javax.swing.JTextField delayTF;
    private javax.swing.JButton goStopButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSlider percentSlider;
    private javax.swing.JTextField percentTF;
    private javax.swing.JSlider roundsSlider;
    private javax.swing.JTextField roundsTF;
    private javax.swing.JSlider sizeSlider;
    private javax.swing.JTextField sizeTF;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
} // class ControlFrame

