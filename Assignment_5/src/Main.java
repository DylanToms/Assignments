import javax.swing.JFrame;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author dylan
 */
public class Main {

  
    public static void main(String[] args) {
        //create two arrays to hold the words
        String[] engWords = new String[30];
        String[] spanWords = new String[30];
        
        //try finding the files if not then print stack
        try {
            File f1 = new File("english.txt");
            File f2 = new File("spanish.txt");
        
            Scanner fileScan = new Scanner(f1);
            Scanner fileScan2 = new Scanner(f2);
            //for loop to read the words into the arrays
            for(int i = 0; i < engWords.length; i++) {
                engWords[i] = fileScan.nextLine();
                spanWords[i] = fileScan2.nextLine();
            }
            
            
            
            
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        //create LanguageGUI object, set to close on x, set visible
        LanguageGUI newGUI = new LanguageGUI(engWords, spanWords);
        newGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGUI.setVisible(true);
        
    }
    
}
