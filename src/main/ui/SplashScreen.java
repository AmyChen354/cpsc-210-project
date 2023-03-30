package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class SplashScreen extends JWindow {
    private JPanel contentPane;
    Image splashScreen;
    ImageIcon imageIcon;

    public SplashScreen(JFrame frame, String message) {
        setBounds(100, 100, 513, 331);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(179, 140, 152, 38);
        contentPane.add(label);

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);

        splashScreen = Toolkit.getDefaultToolkit()
                .getImage("./data/accounts-book-icon-188112145.jpg");
        // Create ImageIcon from Image
        imageIcon = new ImageIcon(splashScreen);
        // Set JWindow size from image size
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // Get current screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Get x coordinate on screen for make JWindow locate at center
        int x = (screenSize.width - getSize().width) / 2;
        // Get y coordinate on screen for make JWindow locate at center
        int y = (screenSize.height - getSize().height) / 2;
        // Set new location for JWindow

        // Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.setVisible(true);
                dispose();
            }
        }, 5000);
    }

    // Paint image onto JWindow
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(splashScreen, 0, 0, this);
    }
}

//    public SplashScreen() {
//        splashScreen = Toolkit.getDefaultToolkit()
//                .getImage("./data/accounts-book-icon-188112145.jpg");
//        // Create ImageIcon from Image
//        imageIcon = new ImageIcon(splashScreen);
//        // Set JWindow size from image size
//        setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
//        // Get current screen size
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        // Get x coordinate on screen for make JWindow locate at center
//        int x = (screenSize.width - getSize().width) / 2;
//        // Get y coordinate on screen for make JWindow locate at center
//        int y = (screenSize.height - getSize().height) / 2;
//        // Set new location for JWindow
//        setLocation(x,y);
//        // Make JWindow visible
//        setVisible(true);
//    }
//
//    // Paint image onto JWindow
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawImage(splashScreen, 0, 0, this);
//    }
//
//    public static void main(String[] args) {
//        SplashScreen splash = new SplashScreen();
//        try {
//            // Make JWindow appear for 5 seconds before disappear
//            Thread.sleep(5000);
//            splash.dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
