import java.awt.*;  
import javax.swing.*;
import java.awt.event.*;

public class MagicSquare implements ActionListener{  
    
    int row = 3;
    int col = 3;
    //Create a 1D button array (using the rows to help with total size)
    JButton[] a = new JButton[row*col];
    JLabel sums[] = new JLabel [row + col + 1];
    int sumNum[] = new int[8];
    //String statusMsg = "Magic Squares is running";
    String statusMsg = "Magic Squares is running";
    JLabel status;
    JFrame f;
    JButton reset;
    
    MagicSquare() 
    {  
        for (int i = 0 ; i < a.length ; i++)
        {
            // create the buttons using (i+1 as the label)
            a[i] = new JButton(String.valueOf(i+1));
            
            //add the Action Listener
            a[i].addActionListener(this);
            //set the action Command
            a[i].setActionCommand(String.valueOf(i));
            //set the background colour using the pickClr(i+1) subroutine
            pickClr(i+1);
            //change the font on the button label
            a[i].setFont(new Font("Arial",Font.PLAIN,20));
            //set the size of each button to 50 by 50
            a[i].setPreferredSize(new Dimension(50,50));
        }
        //setup totals for each row
        for (int i = 0 ; i < sums.length ; i++)
        {
            sums [i] = new JLabel ("  " + sumNum [i]);
            sums [i].setFont (new Font ("Arial", Font.PLAIN, 10));
            sums [i].setPreferredSize (new Dimension (50, 50));
        }

        //setup and populate panel 
        JPanel panel=new JPanel();  
        panel.setSize(200, 200);
        panel.setLayout(new GridLayout(row, col));
        
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        JLabel title = new JLabel("MAGIC SQUARE");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setHorizontalAlignment(title.CENTER);
        top.add(title,BorderLayout.NORTH);
        
        JLabel instructions = new JLabel ("Numbers in each row, column, and diagonal must add to the same number");
        instructions.setFont(new Font("Arial",Font.PLAIN,10));
        instructions.setHorizontalAlignment(title.CENTER);
        top.add(instructions,BorderLayout.SOUTH);

        JPanel sumPanel = new JPanel(new GridLayout(8,1));
        for (int i = 0; i < sums.length; i++) {
            sumPanel.add(sums[i]);
        }
        f.add(sumPanel, BorderLayout.WEST);
        
        //add buttons one by one        
        panel.add(a[0]);
        panel.add(a[1]);
        panel.add(a[2]);
        panel.add(a[3]);
        panel.add(a[4]);
        panel.add(a[5]);
        panel.add(a[6]);
        panel.add(a[7]);
        panel.add(a[8]);
        
        //reset button
        reset = new JButton("RESET");
        reset.setActionCommand("reset");
        reset.addActionListener(this);
        
        
        //setup the main frame
        JFrame.setDefaultLookAndFeelDecorated (true);
        f= new JFrame("MAGIC SQUARE"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        f.add(panel, BorderLayout.CENTER);
        f.setSize(400,400);
        status = new JLabel(statusMsg);
        f.add(status,BorderLayout.SOUTH);
        f.add(top,BorderLayout.NORTH);
        f.add(reset,BorderLayout.EAST);
        f.setVisible(true);    
    }
    
    public void actionPerformed (ActionEvent e)
    {
        if (e.getActionCommand ().equals ("reset"))
        {
            //add reset code
            for (int i=0;i<a.length;i++)
            {
                a[i].setText(String.valueOf(i+1));
                
                int val = Integer.parseInt(a[i].getText());
                a[i].setBackground(pickClr(val));
            }
            updateSums();
        }
        else
        {
            int index = Integer.parseInt(e.getActionCommand());
            int x = index / row;
            int y = index % row;
            int num = Integer.parseInt (a [index].getText ());
            //process a click code here
            num ++;
            if (num >= 10)
            {
                num = 1;
            }
            a[index].setText(String.valueOf(num));
            a[index].setBackground(pickClr(num));
            updateSums();
            boolean win = winner();
            status.setText("Have you won? " + win);
            
        } 
    }//end actionPerformed
    
    //use to set background colours of the squares
    public Color pickClr (int n)
    {
        Color clr[] = {Color.red, Color.orange, Color.yellow, Color.cyan, Color.green, Color.magenta, Color.pink, Color.white, Color.lightGray};
        return (clr [n - 1]);
    }//end pickClr
    
    //updates the button look and value after pressed.
    public void updateSums ()
    {
        int[] n = new int[9];
    
        for (int i = 0; i < a.length; i++) {
            n[i] = Integer.parseInt(a[i].getText());
        }

        // rows
        sumNum[0] = n[0] + n[1] + n[2];
        sumNum[1] = n[3] + n[4] + n[5];
        sumNum[2] = n[6] + n[7] + n[8];

        // columns
        sumNum[3] = n[0] + n[3] + n[6];
        sumNum[4] = n[1] + n[4] + n[7];
        sumNum[5] = n[2] + n[5] + n[8];

        // diagonals
        sumNum[6] = n[0] + n[4] + n[8];
        sumNum[7] = n[2] + n[4] + n[6];
    }//end updateSums
    
     public boolean winner ()
    {
        int check = sumNum[0];
        
        for (int i = 1; i < sumNum.length; i++) {
            if (sumNum[i] != check) return false;
        }

        int[] nums = new int[10];
        for (JButton b : a) {
            nums[Integer.parseInt(b.getText())]++;
        }

        for (int i = 1; i <= 9; i++) {
            if (nums[i] != 1) return false;
        }

        return true;      
    }

    public static void main(String args[])  
    {  
        new MagicSquare();  
    }  
}

