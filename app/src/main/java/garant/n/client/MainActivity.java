package garant.n.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void on_connect_button_click(View view)
    {
        Log.e("button:", "connect");

    }

    protected void on_send_button_click(View view)
    {

    }
}
