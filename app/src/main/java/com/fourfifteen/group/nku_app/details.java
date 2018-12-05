package com.fourfifteen.group.nku_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        String detailsTitle = getIntent().getStringExtra("title");
        TextView textView = findViewById(R.id.editText);
        textView.setText(detailsTitle);

        final String detailsDesc = getIntent().getStringExtra("desc");
        TextView textView1 = findViewById(R.id.desc);
        textView1.setText(detailsDesc);

        Button button = findViewById(R.id.emailButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[] { detailsDesc });
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
    }

    @Override
    public void onRestart(){
        recreate();
        super.onRestart();
    }
}