package garant.n.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    tcp_adapter my_adamter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void on_connect_button_click(View view)
    {
        Log.e("button:", "connect");
        my_adamter = new tcp_adapter();
    }

    protected void on_send_button_click(View view)
    {
        Log.e("button:", "send");
        my_adamter.SendMessage("hi :)");

    }
}
