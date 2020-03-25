package bpawl.lochat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import bpawl.lochat.ui.main.ChatMap;
import bpawl.lochat.ui.main.Signing;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Signing.newInstance())
                    .commitNow();
        }
    }
}
