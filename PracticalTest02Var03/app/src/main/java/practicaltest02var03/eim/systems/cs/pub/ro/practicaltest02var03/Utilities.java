package practicaltest02var03.eim.systems.cs.pub.ro.practicaltest02var03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by student on 19.05.2017.
 */

    public class Utilities {

        public static BufferedReader getReader(Socket socket) throws IOException {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public static PrintWriter getWriter(Socket socket) throws IOException {
            return new PrintWriter(socket.getOutputStream(), true);
        }
}