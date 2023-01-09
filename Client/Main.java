package Client;

import Client.Auth.KeyAuth;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public enum Main {
    INSTANCE;

    public KeyAuth keyAuth;

    private String URL = "https://keyauth.win/api/1.1/"; // KeyAuth API URL

    private String ownerId = ""; // You can find out the owner id in the profile settings keyauth.com

    private String appName = ""; // Application name

    public String version = ""; // Application/Client version

    String username = ""; // Username
    String password = ""; // Password

    public void initialize() {
        keyAuth = new KeyAuth(appName, ownerId, version, URL);
        keyAuth.init();

        login(new JFrame());

        keyAuth.login(username, password);

        if(keyAuth.isLoggedIn()) {
            JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password!");
            System.exit(0);
        }
    }

    /*
     * Thanks to some guy on stackoverflow for this code, cuz IDK how to use JFrames, just JOptionPane which is worse...
     */
    public Hashtable<String, String> login(JFrame frame) {
        Hashtable<String, String> logininformation = new Hashtable<String, String>();

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, panel, appName + " Login", JOptionPane.QUESTION_MESSAGE);

        logininformation.put("user", username.getText());
        logininformation.put("pass", new String(password.getPassword()));

        this.username = logininformation.get("user");
        this.password = logininformation.get("pass");

        return logininformation;
    }
}
