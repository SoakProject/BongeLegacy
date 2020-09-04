package org.bonge.patcher;

import org.bonge.bukkitloader.BukkitDownloader;
import org.bonge.launch.BongeLaunch;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class BongeGUIPatcherFrame2 extends JFrame {

    public class NextButtonListener implements ActionListener {

        private File getFile(){
            JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Launch Implementation (Forge,Sponge Vanilla, etc.)" + " Location required", "Input", JOptionPane.PLAIN_MESSAGE);
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Java file", "jar"));
            try {
                chooser.setSelectedFile(getCurrentFile().getParentFile().getParentFile());
            } catch (URISyntaxException uriSyntaxException) {
                JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Could not work out where you are. Make sure Bonge is in the servers mods/plugins folder", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                throw new IllegalStateException("Ended");
            }
            while(true) {
                int result = chooser.showOpenDialog(BongeGUIPatcherFrame2.this);
                if(result == JFileChooser.APPROVE_OPTION){
                    return chooser.getSelectedFile();
                }else if(result == JFileChooser.CANCEL_OPTION){
                    throw new IllegalArgumentException("Cancelled");
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                BongeGUIPatcherFrame2.this.nextButton.setEnabled(false);
                File forgeLocation = null;
                if(BongeGUIPatcherFrame2.this.patchCommonLangBox.isSelected()) {
                    try {
                        forgeLocation = getFile();
                    }catch (IllegalArgumentException e1){
                        return;
                    }
                }
                File bukkitDownload;
                try {
                    bukkitDownload = new File(getCurrentFile().getParentFile().getParentFile(), "config/bonge/download/BukkitAPI-" + BongeLaunch.IMPLEMENTATION_VERSION + ".jar");
                } catch (URISyntaxException uriSyntaxException) {
                    JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Could not work out where you are. Make sure Bonge is in the servers mods/plugins folder", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    throw new IllegalStateException("Ended");
                }

                if(!bukkitDownload.exists()){
                    if(BongeGUIPatcherFrame2.this.downloadBukkitVersionBox.isSelected()) {
                        System.out.println("Downloading: " + bukkitDownload.getAbsolutePath());
                        BukkitDownloader downloader = new BukkitDownloader(BukkitDownloader.DEFAULT_VERSION, bukkitDownload, true);
                        BongeGUIPatcherFrame2.this.downloadBukkitVersionLabel.setMaximum((int) BukkitDownloader.DEFAULT_VERSION.getSizeInBytes());
                        try {
                            downloader.download((v, s) -> BongeGUIPatcherFrame2.this.downloadBukkitVersionLabel.setValue(v.intValue()));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, ioException.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(1);
                            throw new IllegalStateException("Ended");
                        }
                    }else{
                        JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Bukkit Download required", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                        throw new IllegalStateException("Ended");
                    }
                }
                if(!bukkitDownload.exists()){
                    JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Failed to find BukkitAPI", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    return;
                }


                try {
                    File patchedLocation = null;
                    if(forgeLocation != null){
                        patchedLocation = new File(forgeLocation.getParentFile(), "PatchedImpl.jar");
                    }
                    BongeGUIPatcherFrame2.patch(forgeLocation, bukkitDownload, patchedLocation, v -> {
                        JProgressBar bar = BongeGUIPatcherFrame2.this.patchCommonLangLabel;
                        int max = bar.getMaximum();
                        int value = bar.getValue() + 1;
                        String val = "(" + value + "/" + max + "(" + ((value * 100) / max) + "%))" + v;
                        bar.setString(val);
                        bar.setValue(value);
                    }, v -> {
                        JProgressBar bar = BongeGUIPatcherFrame2.this.patchBukkitVersionLabel;
                        int max = bar.getMaximum();
                        int value = bar.getValue() + 1;
                        String val = "(" + value + "/" + max + "(" + ((value * 100) / max) + "%))" + v;
                        bar.setString(val);
                        bar.setValue(value);
                    });
                    getCurrentFile().deleteOnExit();
                    if(forgeLocation != null && forgeLocation.exists()) {
                        JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Patched both. \nPatched-Bonge.jar\nPatchedImpl.jar", "Complete", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Patched. \nPatched-Bonge.jar", "Complete", JOptionPane.INFORMATION_MESSAGE);

                    }
                    System.exit(0);
                } catch (FileNotFoundException e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(BongeGUIPatcherFrame2.this, "Unknown file", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }

            }).start();
        }
    }

    public Runnable checkCheckBox = () -> {
        try {
            Class.forName("org.bukkit.Bukkit");
            BongeGUIPatcherFrame2.this.patchBukkitVersionBox.setVisible(false);
            BongeGUIPatcherFrame2.this.patchBukkitVersionBox.setSelected(false);
            BongeGUIPatcherFrame2.this.downloadBukkitVersionBox.setVisible(false);
            BongeGUIPatcherFrame2.this.downloadBukkitVersionBox.setVisible(false);
        } catch (ClassNotFoundException e) {
            BongeGUIPatcherFrame2.this.patchBukkitVersionBox.setSelected(true);
        }
        File file;
        try {
            file = getCurrentFile().getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }
        System.out.println("Current File: " + file.getAbsolutePath());
        if(!(file.getName().equalsIgnoreCase("mods") || file.getName().equalsIgnoreCase("plugins"))){
            JOptionPane.showMessageDialog(
                    BongeGUIPatcherFrame2.this,
                    "Could not work out the folder Bonge is currently in. Unknown download location. Current location: " + file.getName(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return;
        }
        File bukkitDownload = new File(file.getParentFile(), "config/bonge/download/BukkitAPI-" + BongeLaunch.IMPLEMENTATION_VERSION + ".jar");
        System.out.println(bukkitDownload.getAbsolutePath());
        if(bukkitDownload.exists()){
            BongeGUIPatcherFrame2.this.downloadBukkitVersionBox.setVisible(false);
            BongeGUIPatcherFrame2.this.downloadBukkitVersionBox.setSelected(false);
            return;
        }
        try {
            Files.createDirectory(bukkitDownload.getParentFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private final JRadioButton spongeForgeImplButton = new JRadioButton("Sponge Forge");
    private final JRadioButton otherImplButton = new JRadioButton("Other");

    private final JTextArea infoArea = new JTextArea("Select your Sponge implementation above.\n\n" +
            "Bukkit API is not included within the plugin itself and requires to be patched into the plugin itself. Please note this is 'Bukkit API' and not 'CraftBukkit' (commonly referred to as 'Bukkit') or any forks of CraftBukkit (Such as 'Spigot' or 'Paper'). " +
            "If you do not wish to download Bukkit API because you have already downloaded 'Bukkit API' from another source (Such as Spigots 'BuildTools') then please make sure it is the correct build of Bukkit API, if it is then move it to the downloads folder in Bonge's configuration folder with the name of 'BukkitAPI-" + BongeLaunch.IMPLEMENTATION_VERSION + ".jar'\n\n" +
            "Forge does not allow the loading of some files depending on the location and name of the file, one of the groups of files required for most Bukkit API plugins to run is the Apache-Commons versions. Bukkit plugins require version 2.9 or lower to run correctly, therefore it needs to be patched into either your Minecraft server or your Forge. This does not remove any files.\n\n" +
            "The download URL of the Bukkit API is as follows\n" +
            BukkitDownloader.DEFAULT_VERSION.getDownloadURL().orElse(null));

    private final JProgressBar patchCommonLangLabel = new JProgressBar();
    private final JCheckBox patchCommonLangBox = new JCheckBox();
    private final JProgressBar patchBukkitVersionLabel = new JProgressBar();
    private final JCheckBox patchBukkitVersionBox = new JCheckBox();
    private final JProgressBar downloadBukkitVersionLabel = new JProgressBar();
    private final JCheckBox downloadBukkitVersionBox = new JCheckBox();

    private final JButton nextButton = new JButton("Next");


    public BongeGUIPatcherFrame2(){
        super("Patcher");
        init();
    }

    private void init(){
        this.setSize(800, 400);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.spongeForgeImplButton.addActionListener((e) -> {
            if(this.downloadBukkitVersionBox.isVisible()){
                this.downloadBukkitVersionBox.setSelected(true);
            }
            if(this.patchBukkitVersionBox.isVisible()){
                this.patchBukkitVersionBox.setSelected(true);
            }
            if(this.patchCommonLangBox.isVisible()){
                this.patchCommonLangBox.setSelected(true);
            }
            this.nextButton.setEnabled(true);
        });
        this.otherImplButton.addActionListener((e) -> {
            if(this.downloadBukkitVersionBox.isVisible()){
                this.downloadBukkitVersionBox.setSelected(true);
            }
            if(this.patchBukkitVersionBox.isVisible()){
                this.patchBukkitVersionBox.setSelected(true);
            }
            this.patchCommonLangBox.setSelected(false);
            this.nextButton.setEnabled(true);
        });
        this.patchCommonLangBox.setEnabled(false);
        this.patchBukkitVersionBox.setEnabled(false);
        this.downloadBukkitVersionBox.setEnabled(false);
        this.patchCommonLangLabel.setString("Patch - Apache Common-Lang 2.6");
        this.patchCommonLangLabel.setStringPainted(true);
        this.patchBukkitVersionLabel.setString("Patch - Bukkit " + BongeLaunch.IMPLEMENTATION_VERSION);
        this.patchBukkitVersionLabel.setStringPainted(true);
        this.downloadBukkitVersionLabel.setString("Download - Bukkit " + BongeLaunch.IMPLEMENTATION_VERSION);
        this.downloadBukkitVersionLabel.setStringPainted(true);
        this.infoArea.setLineWrap(true);
        this.infoArea.setWrapStyleWord(true);
        this.infoArea.setEditable(false);
        this.nextButton.addActionListener(new NextButtonListener());
        this.nextButton.setEnabled(false);

        JPanel implPanel = new JPanel();
        implPanel.setLayout(new GridLayout(2, 1));
        implPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Implementation"));
        implPanel.add(this.spongeForgeImplButton);
        implPanel.add(this.otherImplButton);

        ButtonGroup group = new ButtonGroup();
        group.add(this.spongeForgeImplButton);
        group.add(this.otherImplButton);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(implPanel, c);
        c.gridy = 1;
        c.weighty = 1.0;
        this.add(new JScrollPane(this.infoArea), c);


        c.gridy = 2;
        c.gridwidth = 1;
        c.weighty = 0.0;
        this.add(this.patchCommonLangLabel, c);
        c.gridy = 3;
        this.add(this.patchBukkitVersionLabel, c);
        c.gridy = 4;
        this.add(this.downloadBukkitVersionLabel, c);
        c.gridy = 2;
        c.gridx = 1;
        this.add(this.patchCommonLangBox, c);
        c.gridy = 3;
        this.add(this.patchBukkitVersionBox, c);
        c.gridy = 4;
        this.add(this.downloadBukkitVersionBox, c);
        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 3;
        this.add(this.nextButton, c);
    }

    private static File getCurrentFile() throws URISyntaxException {
        return new File(BongeGUIPatcherFrame2.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    }

    public static void patch(File forgeLocation, File bukkitDownload, File patchedLocation, Consumer<String> value, Consumer<String> value1) throws URISyntaxException, IOException{
        File bongeLocation = getCurrentFile();
        ZipEntry entry;
        if(forgeLocation != null && forgeLocation.exists()) {
            patchedLocation.createNewFile();
            value.accept("copying " + forgeLocation.getName() + " to " + patchedLocation.getName());
            FileInputStream fis = new FileInputStream(forgeLocation);
            ZipInputStream zis = new ZipInputStream(fis);
            FileInputStream afis = new FileInputStream(bongeLocation);
            ZipInputStream azis = new ZipInputStream(afis);
            FileOutputStream fos = new FileOutputStream(patchedLocation);
            ZipOutputStream zos = new ZipOutputStream(fos);
            while ((entry = zis.getNextEntry()) != null) {
                value.accept("Copying: " + entry.getName());
                try {
                    zos.putNextEntry(entry);
                }catch (ZipException e){
                    continue;
                }
                int length;
                byte[] bytes = new byte[1024];
                while ((length = zis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
                System.out.println("Copy 1: " + entry.getName());
                zos.closeEntry();
            }

            while ((entry = azis.getNextEntry()) != null) {
                if (!(entry.getName().startsWith("org/apache/commons/lang"))) {
                    continue;
                }
                value.accept("Patching: " + entry.getName());
                zos.putNextEntry(entry);
                int length;
                byte[] bytes = new byte[1024];
                while ((length = azis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
                System.out.println("Copy 1: " + entry.getName());
                zos.closeEntry();
            }
            azis.close();
            zis.close();
            zos.close();
            value.accept("Complete");
        }

        File patchedBonge = new File(bongeLocation.getParentFile(), "Patched-Bonge.jar");
        patchedBonge.createNewFile();
        FileInputStream fis = new FileInputStream(bukkitDownload);
        ZipInputStream zis = new ZipInputStream(fis);
        FileInputStream afis = new FileInputStream(bongeLocation);
        ZipInputStream azis = new ZipInputStream(afis);
        FileOutputStream fos = new FileOutputStream(patchedBonge);
        ZipOutputStream zos = new ZipOutputStream(fos);
        while((entry = azis.getNextEntry()) != null){
            value1.accept("copying " + entry.getName());
            zos.putNextEntry(entry);
            int length;
            byte[] bytes = new byte[1024];
            while ((length = azis.read(bytes)) > 0) {
                zos.write(bytes, 0, length);
            }
            zos.closeEntry();
        }
        azis.close();
        JarFile bongeFile = new JarFile(bongeLocation);
        while((entry = zis.getNextEntry()) != null) {
            if (!(entry.getName().startsWith("org/bukkit"))){
                continue;
            }
            if (bongeFile.getEntry(entry.getName()) != null){
                //replacement already found
                continue;
            }
            value1.accept("Patching: " + entry.getName());
            zos.putNextEntry(entry);
            int length;
            byte[] bytes = new byte[1024];
            while ((length = zis.read(bytes)) > 0) {
                zos.write(bytes, 0, length);
            }
            zos.closeEntry();
        }
        zis.close();
        fis.close();

        zos.close();
        value1.accept("Complete");
    }

    public static void main(String[] args){
        BongeGUIPatcherFrame2 frame = new BongeGUIPatcherFrame2();
        new Thread(frame.checkCheckBox).start();
        frame.setVisible(true);
    }
}
