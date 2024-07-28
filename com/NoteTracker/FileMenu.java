package com.NoteTracker;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class FileMenu {

    public static JFrame frame;
    public static String fileName;
    public static String filePath;
    public static String directory;

    public static void newFile() {
        Frame.textArea.setText(null);
        Frame.frame.setTitle("new");
    }

    public static void openFile() {
        FileDialog filedialog = new FileDialog(frame, "Open File", FileDialog.LOAD);
        filedialog.setVisible(true);

        if (filedialog.getFile() != null) {
            directory = filedialog.getDirectory();
            fileName = filedialog.getFile();
            filePath = directory + fileName;
            Frame.frame.setTitle(fileName);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String Line;
                Frame.textArea.setText("");
                while ((Line = reader.readLine()) != null) {
                    Frame.textArea.append(Line + "\n");
                }
            } catch (IOException e) {
                System.out.println("File not opened");
            }

        }

    }

    public static void saveAs() {
        FileDialog filedialog = new FileDialog(frame, "Save", FileDialog.SAVE);
        filedialog.setVisible(true);

        if (filedialog.getFile() != null) {
            directory = filedialog.getDirectory();
            fileName = filedialog.getFile();
            filePath = directory + fileName;
            Frame.frame.setTitle(fileName);

            try {
                FileWriter writer = new FileWriter(filePath);
                writer.write(Frame.textArea.getText());
                writer.close();
            }
            catch (IOException e) {
                System.out.println("File can no be saved");
            }

        }
    }

    public static void save() {
        if (fileName == null) {
            saveAs();
        }
        else {
            try {
                FileWriter writer = new FileWriter(filePath);
                writer.write(Frame.textArea.getText());
                writer.close();
            }
            catch (IOException e) {
                System.out.println("File can no be saved");
            }
        }
    }
}
