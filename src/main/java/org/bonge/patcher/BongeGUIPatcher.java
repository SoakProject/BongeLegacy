package org.bonge.patcher;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BongeGUIPatcher extends JPanel {

    private JTextField forgeLocation = new JTextField(new File(".").getAbsolutePath());
    private JLabel forgeLabel = new JLabel("Forge Location");
    private JButton searchForForge = new JButton("Search");
    private JButton patch = new JButton("Patch");
    private JProgressBar percent = new JProgressBar();

    private JTextArea reason = new JTextArea("Due to multiple reasons, Forge does not allow the loading of 'org.apache' class files. However, some Bukkit plugins require these files to be loaded to be ran. The solution is to put the files into Forge directly.");

    public BongeGUIPatcher(){
        init();
    }

    private void init(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        this.add(this.forgeLabel, c);
        c.gridx = 1;
        c.weightx = 1.0;
        this.add(this.forgeLocation, c);
        c.gridx = 2;
        c.weightx = 0.0;
        this.add(this.searchForForge, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.gridwidth = 3;
        this.add(this.patch, c);
        c.gridy = 2;
        this.add(this.percent, c);
        c.weighty = 1.0;
        c.gridy = 3;
        this.reason.setEditable(false);
        this.reason.setBackground(null);
        this.reason.setLineWrap(true);
        this.reason.setWrapStyleWord(true);
        this.add(this.reason, c);
    }
}
