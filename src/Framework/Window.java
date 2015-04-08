package Framework;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame{
    private Window() {
        this.setTitle("Pokemonstro");
        
        // Size of the frame.
        this.setSize(480, 320);
        // Puts frame to center of the screen.
        this.setLocationRelativeTo(null);
        // So that frame cannot be resizable by the user.
        this.setResizable(false);
        
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates the instance of the Framework.java that extends the Canvas.java and puts it on the frame.
        this.setContentPane(new Framework());
        
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}