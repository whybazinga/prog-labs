import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    public ChatClient() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.setBounds(100, 100, 400, 600);

        textField.addActionListener(new ActionListener() {
            //send message on enter
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    private String getRegistration() throws IOException {
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {
                "Username:", username,
                "Password:", password
        };

        while (true) {
            int option = JOptionPane.showConfirmDialog(frame, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                if ((!username.getText().equals("") && !some(username.getText(), "., ;'+\"\\|:"))
                        && (!password.getText().equals("") && !some(password.getText(), "., ;'+\"\\|:"))) {
                    return username.getText() + "|" + password.getText();
                } else {
                    this.mWrongInput();
                }
            } else {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                throw new IOException();
            }
        }
    }

    private void mWrongInput() {
        JOptionPane.showMessageDialog(
                frame,
                "Input error");
    }

    private void mUserOnline() {
        JOptionPane.showMessageDialog(
                frame,
                "User is already logged in");
    }

    private String getIP() {
        String result = JOptionPane.showInputDialog(
                frame,
                "Enter IP:",
                "IP selection",
                JOptionPane.QUESTION_MESSAGE);
        if (result.equals("")) {
            return "localhost";
        } else {
            return result;
        }
    }

    private static boolean some(String word, String forcheck) {
        for (char l : word.toCharArray()) {
            for (char f : forcheck.toCharArray()) {
                if (l == f) {
                    return true;
                }
            }
        }
        return false;
    }

    private void run() throws IOException {
        String serverAddress = getIP();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                String name = getRegistration();
                out.println(name);
            } else if (line.startsWith("USERONLINE")) {
                this.mUserOnline();
            } else if (line.startsWith("WRONGNAME")) {
                this.mWrongInput();
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}