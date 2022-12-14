package com.example.twoactivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button send_button;
    private EditText message_edittext;
    private TextView reply_view;
    private TextView reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /***
     * initialization function
     */
    private void init()
    {
        reply_view = findViewById(R.id.reply_view);
        reply = findViewById(R.id.reply);
        send_button = findViewById(R.id.send_button);
        send_button.setOnClickListener(this);
        message_edittext = (EditText) findViewById(R.id.message_edit);

    }

    /***
     * This function performs action when a button/view is clicked.
     * @param view the component that is clicked on the screen
     */
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.send_button:
                sendMessage();
                break;

            default:
        }
    }

    /**
     * Sends message to the second activity
     */
    public void sendMessage() {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = message_edittext.getText().toString();
        intent.putExtra("message", message);
        mStartForResult.launch(intent);
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK)
                    {
                        Intent intent = result.getData();
                        reply.setVisibility(View.VISIBLE);
                        reply_view.setVisibility(View.VISIBLE);
                        reply.setText(intent.getStringExtra("reply"));
                        message_edittext.setText("");
                        getCurrentFocus().clearFocus();
                    }
                }
            }
    );
}