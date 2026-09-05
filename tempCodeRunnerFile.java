import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EasyEventsExample {
    public static void main(String[] args) {
        // 1. Create window frame
        JFrame frame = new JFrame("Button and Mouse Events");
        frame.setLayout(new FlowLayout());

        // 2. Create UI components
        JButton button = new JButton("Click Me");
        JLabel label = new JLabel("Hover over this text!");

        // 3. Button Click Event (ActionListener)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setText("Button Clicked!");
            }
        });

        // 4. Mouse Event (MouseListener)
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Triggers when mouse enters the label space
                label.setForeground(Color.RED);
                label.setText("Mouse Entered!");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Triggers when mouse leaves the label space
                label.setForeground(Color.BLACK);
                label.setText("Hover over this text!");
            }

            // These 3 methods must be present but can be left empty if not needed
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
        });

        // 5. Add components and show window
        frame.add(button);
        frame.add(label);
        
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
