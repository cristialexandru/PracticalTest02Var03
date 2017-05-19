package practicaltest02var03.eim.systems.cs.pub.ro.practicaltest02var03;

import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by student on 19.05.2017.
 */
public class ClientThread extends Thread{
    private String address;
    private int port;
    private String word;
    private TextView result;

    private Socket socket;

    public ClientThread(String address, int port, String word, TextView result) {
        this.address = address;
        this.port = port;
        this.word = word;
        this.result = result;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(word);
            printWriter.flush();
            final StringBuilder resultBuilder = new StringBuilder();
            String resulta;
            while ((resulta = bufferedReader.readLine()) != null) {
                resultBuilder.append(result);
            }
            result.post(new Runnable() {
                @Override
                public void run() {
                    result.setText(resultBuilder.toString());
                }
            });
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}
