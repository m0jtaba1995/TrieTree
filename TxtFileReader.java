package trietree;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TxtFileReader {

    private static Component frame;
    private static final ArrayList<String> word = new ArrayList();

//    private File openFile() {
//        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        int returnValue = jfc.showOpenDialog(jfc);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = jfc.getSelectedFile();
//            return selectedFile.getAbsoluteFile();
//        } else {
//            JOptionPane.showMessageDialog(frame, "Error while read file. Try agin.");
//            return null;
//        }
//    }
    public ArrayList<String> readFile(String pathFile) throws FileNotFoundException, IOException {
        File file = new File(pathFile);
        word.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String processedLine;
            while ((line = br.readLine()) != null) {
                processedLine = line.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase().replaceAll("( )+", " ");
                word.addAll(Arrays.asList(processedLine.split(" ")));
            }
        }
        return word;
    }
}