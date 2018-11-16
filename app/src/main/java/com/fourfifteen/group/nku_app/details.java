package com.fourfifteen.group.nku_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        String detailsTitle = getIntent().getStringExtra("title");
        TextView textView = findViewById(R.id.editText);
        textView.setText(detailsTitle);

        String detailsDesc = getIntent().getStringExtra("desc");
        TextView textView1 = findViewById(R.id.desc);
        textView1.setText(detailsDesc);
    }

    @Override
    public void onRestart() {
        recreate();
        super.onRestart();
    }
}