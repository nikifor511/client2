package garant.n.client;

import android.os.AsyncTask;
import android.util.Log;

public class tcp_adapter {

    tcp_client mTcpClient;

    public tcp_adapter() {

        mTcpClient = new tcp_client(new tcp_client.OnMessageReceived()
        {
            @Override
            public void messageReceived(String message) {
                Log.e("mTcpClient", message);
            }
        } );
        new connectTask().execute();
    }

    public void SendMessage(String message)
    {
        Log.e("tcp_adaptor", "pre_send");
        new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message);
        Log.e("tcp_adaptor", "post_send");
    }


    public class connectTask extends AsyncTask<Void, String, tcp_client> {

        @Override
        protected tcp_client doInBackground(Void... param) {

            mTcpClient.run();
            return null;
        }
    }

    class SendTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... message) {
            Log.e("Send_ATask", "pre");
            mTcpClient.sendMessage(message[0]);
            Log.e("Send_ATask", "post");
            return null;
        }
    }

}

