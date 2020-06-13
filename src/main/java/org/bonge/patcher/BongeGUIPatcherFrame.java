package org.bonge.patcher;

import javax.swing.*;

public class BongeGUIPatcherFrame extends JFrame {

    public BongeGUIPatcherFrame(){
        super("Bonge Patcher");
        init();
    }

    private void init(){
        this.setContentPane(new BongeGUIPatcher());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
    }

    @Override
    public BongeGUIPatcher getContentPane() {
        return (BongeGUIPatcher) super.getContentPane();
    }

    public static final void main(String[] args){
        BongeGUIPatcherFrame frame = new BongeGUIPatcherFrame();
        frame.setVisible(true);
    }
}
