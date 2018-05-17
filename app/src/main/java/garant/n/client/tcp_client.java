package garant.n.client;

import android.os.AsyncTask;
import android.util.Log;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import android.util.Log;

public class tcp_client {

    private String serverMessage;
    public  static String SERVERIP = "10.0.2.2"; //your computer IP address
    public  static  int SERVERPORT = 5959;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    /**
     *  Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public tcp_client(OnMessageReceived listener)
    {
        mMessageListener = listener;
    }

    public void sendMessage(String message){
        Log.e("TCPClient", "TCPClient sendMessage");
        try {
            if (out == null)
                Log.e("Out", "null");
            if (out.checkError())
                Log.e("out", "error");

            if (out != null && !out.checkError()) {
                Log.e("TCPClient", "TCPClient out != null");
                out.println(message);
                out.flush();
            }
        }
        catch (Exception e) {

            Log.e("TCP", "S: Error", e);

        } finally {

        }
    }

    public void stopClient(){
        mRun = false;
    }

    public void run() {
        mRun = true;
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            socket = new Socket(serverAddr, SERVERPORT);
            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (mRun) {
                    serverMessage = in.readLine();
                    if (serverMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;
                }
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);

            } finally {
                socket.close();
            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }
    }

    public interface OnMessageReceived {

        public void messageReceived(String message);

    }
}
