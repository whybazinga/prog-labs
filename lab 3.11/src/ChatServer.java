
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {

   /* public static class PersInfo {
        public String name;
        public String pass;

        public PersInfo(String n, String p) {
            name = new String(n);
            pass = new String(p);
        }
    }*/

    private static final int PORT = 9001;

    private static HashSet<String> online = new HashSet<String>();

    private static HashSet<String> accounts = new HashSet<String>();

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    /**
     * The appplication main method, which just listens on a port and
     * spawns handler threads.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private String sysname;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                while (true) {
                    out.println("SUBMITNAME");
                    sysname = in.readLine();
                    if (sysname == null) {
                        return;
                    }
                    synchronized (accounts) {
                        name = sysname.substring(0, sysname.indexOf("|"));
                        int flag = 1;
                        for (String tmp : accounts) {
                            if (tmp.substring(0, tmp.indexOf("|")).equals(name)) {
                                if (tmp.equals(sysname)) {
                                    flag = 0;
                                } else {
                                    flag = -1;
                                }
                            }
                        }
                        if (flag == 0) {
                            if (online.contains(sysname)) {
                                flag = -2;
                            }
                        }

                        if (flag == 1) {
                            accounts.add(sysname);
                            online.add(sysname);
                            //password = name.substring(name.indexOf("|") + 1);
                            break;
                        } else if (flag < 0) {
                            if (flag == -1) {
                                out.println("WRONGNAME");
                            }
                            if (flag == -2) {
                                out.println("USERONLINE");
                            }
                        } else {
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                writers.add(out);
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + name + " has entered your chat");
                }

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + " has left your chat");
                    }
                    online.remove(sysname);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}