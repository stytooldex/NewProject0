package com.example.blade.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blade.R;
import com.example.blade.util.shell.Shell;
import com.example.blade.util.shell.ShizukuShell;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showWaiting();
    }

    private void showWaiting() {
        ShizukuShell.getInstance().exec(new Shell.Command.Builder("reboot -p").build());
    }


}
