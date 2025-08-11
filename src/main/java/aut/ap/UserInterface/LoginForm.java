package aut.ap.UserInterface;

import aut.ap.controller.EmailController;
import aut.ap.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private static final Logger log = LoggerFactory.getLogger(LoginForm.class);
    private final UserController userController;
    private final EmailController emailController;

    public LoginForm(UserController userController, EmailController emailController) {
        this.userController = userController;
        this.emailController = emailController;
        createUI();
    }

    public void createUI() {
        // رنگ‌های تم صورتی و بنفش
        Color pinkColor = new Color(255, 182, 193); // صورتی روشن
        Color purpleColor = new Color(147, 112, 219); // بنفش متوسط
        Color darkPurple = new Color(102, 51, 153); // بنفش تیره
        Color lightPink = new Color(255, 209, 220); // صورتی خیلی روشن

        // Create Frame and configure it.
        JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Email App login");
        loginFrame.setSize(new Dimension(550, 400));
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        loginFrame.getContentPane().setBackground(lightPink);

        // Create tab page.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(pinkColor);
        tabbedPane.setForeground(darkPurple);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Login Panel.
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        loginPanel.setBackground(lightPink);

        // Login panel field.
        JTextField loginEmail = new JTextField();
        styleTextField(loginEmail);
        JPasswordField loginPassword = new JPasswordField();
        stylePasswordField(loginPassword);
        JButton loginButton = new JButton("Login");
        styleButton(loginButton, purpleColor);

        // sign up Panel.
        JPanel signUpPanel = new JPanel(new GridBagLayout());
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        signUpPanel.setBackground(lightPink);

        // sign up panel field.
        JTextField regUsername = new JTextField();
        styleTextField(regUsername);
        JTextField regEmail = new JTextField();
        styleTextField(regEmail);
        JPasswordField regPassword = new JPasswordField();
        stylePasswordField(regPassword);
        JButton regButton = new JButton("Sign Up");
        styleButton(regButton, purpleColor);

        // labels styling
        JLabel userName = createStyledLabel("Username:", darkPurple);
        JLabel email = createStyledLabel("Email:", darkPurple);
        JLabel password = createStyledLabel("Password:", darkPurple);

        // Add component to login panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(email, gbc);

        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        loginPanel.add(loginEmail, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        loginPanel.add(password, gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        loginPanel.add(loginPassword, gbc);

        // Login Button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        loginPanel.add(loginButton, gbc);

        // GridBagConstraints for register panel
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(6, 6, 6, 6);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;

        // Username Label
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        signUpPanel.add(userName, gbc2);

        // Username Field
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        signUpPanel.add(regUsername, gbc2);

        // Email Label
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        signUpPanel.add(createStyledLabel("Email:", darkPurple), gbc2);

        // Email Field
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        signUpPanel.add(regEmail, gbc2);

        // Password Label
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        signUpPanel.add(createStyledLabel("Password:", darkPurple), gbc2);

        // Password Field
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        signUpPanel.add(regPassword, gbc2);

        // Register Button
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        signUpPanel.add(regButton, gbc2);

        // Add Action listeners
        loginButton.addActionListener(action -> {
            String userEmail = loginEmail.getText();
            String userPassword = String.valueOf(loginPassword.getPassword());
            if (userController.login(userEmail, userPassword)) {
                loginFrame.dispose();
                new aut.ap.UserInterface.HomePage(emailController, userController);
            }
            else {
                JOptionPane.showMessageDialog(loginFrame, "Login failed");
            }
        });

        regButton.addActionListener(action -> {
            String userUserName = regUsername.getText();
            String userEmail = regEmail.getText();
            String userPassword = String.valueOf(regPassword.getPassword());
            try {
                userController.register(userUserName, userEmail, userPassword);
                JOptionPane.showMessageDialog(loginFrame, "Congratulation! now login and enjoy.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(loginFrame, "Can't create new account\ntry again.");
            }
        });

        // Add panels to tabbed pane
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Sign Up", signUpPanel);

        // Add tabbedPane to frame
        loginFrame.add(tabbedPane);
        loginFrame.setVisible(true);
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(new Color(102, 51, 153));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(147, 112, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }
    
    private void stylePasswordField(JPasswordField passwordField) {
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(new Color(102, 51, 153));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(147, 112, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JLabel createStyledLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }
}