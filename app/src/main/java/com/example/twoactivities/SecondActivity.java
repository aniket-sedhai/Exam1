package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView message;
    private EditText reply_edit;
    private Button reply_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init()
    {
        message = findViewById(R.id.message);

        System.out.println("******************************");
        System.out.println("******************************");
        System.out.println(getIntent());
        System.out.println("******************************");
        System.out.println("******************************");
        Intent intent = getIntent();
        String message_received = intent.getStringExtra("message");

        message.setText(message_received);
        reply_button = findViewById(R.id.reply_button);
        reply_button.setOnClickListener(this);

        reply_edit = (EditText) findViewById(R.id.reply_edit);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.reply_button:
                replyMessage();
                break;

            default:
        }
    }

    public void replyMessage() {
        Intent intent = new Intent(this, MainActivity.class);
        String reply = reply_edit.getText().toString();
        intent.putExtra("reply", reply);
        startActivity(intent);
    }
}