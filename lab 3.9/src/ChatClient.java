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

    private String getName() {
        return JOptionPane.showInputDialog(
                frame,
                "Choose a user name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void wrongInput() {
        JOptionPane.showMessageDialog(
                frame,
                "You typed nothing, this name is already taken,\nor your name includes forbidden symbols: \".,;" +
                        " '+\" \nPlease, try again.");
    }

    private String getIP() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP:",
                "IP selection",
                JOptionPane.QUESTION_MESSAGE);
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
                String name;
                while (true) {
                    name = getName();
                    if (name != null) {
                        if (!name.equals("") && !some(name, "., ;'+")) {
                            break;
                        } else {
                            this.wrongInput();
                        }
                    } else {
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        throw new IOException();
                    }
                }
                out.println(name);
            } else if (line.startsWith("WRONGNAME")) {
                this.wrongInput();
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