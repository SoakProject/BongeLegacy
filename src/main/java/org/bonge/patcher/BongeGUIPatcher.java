package org.bonge.patcher;

import org.bukkit.util.Consumer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class BongeGUIPatcher extends JPanel {

    public class LocateButton implements Runnable {

        @Override
        public void run() {
            JFileChooser chooser = new JFileChooser(new File(BongeGUIPatcher.this.forgeLocation.getText()));
            chooser.setFileFilter(new FileNameExtensionFilter("Java runtime executable", "jar"));
            while(true) {
                int action = chooser.showOpenDialog(BongeGUIPatcher.this);
                if (action == JFileChooser.APPROVE_OPTION) {
                    BongeGUIPatcher.this.forgeLocation.setText(chooser.getSelectedFile().getAbsolutePath());
                    return;
                } else if (action == JFileChooser.CANCEL_OPTION) {
                    return;
                }
            }
        }
    }

    public class PatchButton implements Runnable {

        private void onUpdate(JProgressBar bar, int value, String message){
            int max = bar.getMaximum();
            String val = "(" + value + "/" + max + "(" + ((value * 100)/max) + "%))" + message;
            bar.setString(val);
            bar.setValue(value);

        }

        @Override
        public void run() {
            JProgressBar bar = BongeGUIPatcher.this.percent;
            bar.setMaximum(2057);
            bar.setStringPainted(true);
            File forgeLocation = new File(BongeGUIPatcher.this.forgeLocation.getText());
            File patchedLocation = new File(forgeLocation.getParentFile(), "Patched-" + forgeLocation.getName());
            try {
                BongeGUIPatcher.patch(forgeLocation, patchedLocation, v -> {
                    int max = bar.getMaximum();
                    int value = bar.getValue() + 1;
                    String val = "(" + value + "/" + max + "(" + ((value * 100) / max) + "%))" + v;
                    bar.setString(val);
                    bar.setValue(value);
                });
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(BongeGUIPatcher.this, "Unknown file", "Error", JOptionPane.ERROR_MESSAGE);
                bar.setValue(0);
                bar.setString("Failed");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JTextField forgeLocation = new JTextField();
    private JLabel forgeLabel = new JLabel("Forge Location");
    private JButton searchForForge = new JButton("Search");
    private JButton patch = new JButton("Patch");
    private JProgressBar percent = new JProgressBar();

    private JTextArea reason = new JTextArea("Due to multiple reasons, Forge does not allow the loading of 'org.apache' class files. However, some Bukkit plugins require these files to be loaded to be ran. The solution is to put the files into Forge directly.\n\nTo patch your file, follow the steps\n1) Press the 'Select' button and choose your Forge jar file. \n2) Select 'Patch' wait 2 seconds for the progress bar to complete \n3)A new file will appear as 'Patched-<your forge name>'. Run that as your forge from now on");

    public BongeGUIPatcher(){
        init();
    }

    private void init(){
        File defaultLocation = new File(System.getenv("HOMEPATH"), "Documents");
        this.forgeLocation.setText(defaultLocation.getAbsolutePath());
        this.patch.addActionListener((a) -> new Thread(new PatchButton()).start());
        this.searchForForge.addActionListener(a -> new Thread(new LocateButton()).start());
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

    public static void patch(File forgeLocation, File patchedLocation, Consumer<String> value) throws URISyntaxException, IOException{
        File bongeLocation = new File(BongeGUIPatcher.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        if(!forgeLocation.exists()){
            throw new NoSuchFileException(bongeLocation.getAbsolutePath());
        }
        value.accept("copying " + forgeLocation.getName() + " to " + patchedLocation.getName());
            patchedLocation.createNewFile();
            FileInputStream fis = new FileInputStream(forgeLocation);
            ZipInputStream zis = new ZipInputStream(fis);
            FileOutputStream fos = new FileOutputStream(patchedLocation);
            ZipOutputStream zos = new ZipOutputStream(fos);
            FileInputStream afis = new FileInputStream(bongeLocation);
            ZipInputStream azis = new ZipInputStream(afis);
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {
                value.accept("Copying: " + entry.getName());
                zos.putNextEntry(entry);
                int length;
                byte[] bytes = new byte[1024];
                while ((length = zis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
                zos.closeEntry();
            }

            while((entry = azis.getNextEntry()) != null) {
                if (!entry.getName().startsWith("org/apache/commons/lang")){
                    continue;
                }
                value.accept("Patching: " + entry.getName());
                zos.putNextEntry(entry);
                int length;
                byte[] bytes = new byte[1024];
                while ((length = azis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
                zos.closeEntry();
            }
            zos.close();
            azis.close();
            zis.close();
            value.accept("Complete");
    }
}
