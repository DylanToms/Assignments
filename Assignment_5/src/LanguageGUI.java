import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author dylan
 */
public class LanguageGUI extends JFrame implements ActionListener {
    //two String[] to hold the words
    String[] engWords;
    String[] spanWords;
    //int to be a random index value
    int randIndex = (int)(Math.random() * 30);
    //create the labels, textfield, button, and timer
    private JLabel instructions;
    private JLabel word;
    private JTextField input;
    private JButton ok;
    private Timer timer;
    
    //constructor for GUI
    public LanguageGUI(String[] arr1, String[] arr2) {
        super();
        
        this.engWords = arr1;
        this.spanWords = arr2;
        
        
        //title is LanguageGUI, size is 450x150        
        setTitle("LanguageGUI");
        setSize(450, 150);
        
        //new GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        //JLabel to be the instructions, will change when the user presses ok to show if they are right or not
        instructions = new JLabel("Enter in the translation:");
        layout.gridx = 3;
        layout.gridy = 0;
        add(instructions, layout);
              
        //JLabel to show the random word the user will translate
        word = new JLabel(engWords[randIndex]);
        layout.gridx = 0;
        layout.gridy = 2;
        add(word, layout);
        
        //JTextfield for the user to type in their answer
        input = new JTextField("", 15);
        layout.gridx = 3;
        layout.gridy = 2;
        input.addActionListener(this);
        add(input, layout);
        
        //JButton 
        ok = new JButton("OK");
        layout.gridx = 7;
        layout.gridy = 2;
        ok.addActionListener(this);
        add(ok, layout);
        
        //timer
        timer = new Timer(3000, new TimerGUI());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Store the user input text to be checked 
        String txtInput = input.getText();
        //check the user input with the spanWords array
        if(spanWords[randIndex].toLowerCase().equals(txtInput.toLowerCase())) {
            instructions.setText("Correct!");
        }else {
            instructions.setText("Incorrect! Answer: " + spanWords[randIndex]);
        }
        //set JTextfield input to be not editable
        input.setEditable(false);
        
        //start the timer when the user presses ok
        timer.start();
    }
    
  
    //method to reset all values
    public void reset() {
        instructions.setText("Enter in the translation:");
        input.setText("");
        input.setEditable(true);
        randIndex = (int)(Math.random() * 30);
        word.setText(engWords[randIndex]);
        ok.setEnabled(true);
        timer.stop();
    }
    
    //inner class for the timer to use
    class TimerGUI implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
        
    }
}


