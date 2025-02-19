package com.example.exercise_ii;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    String tag="EVH_Demo: ";
    private long lastTime = 0;
    private EditText nameInput, phoneInput, commentInput;

    private void EventLogger() {
        String eventName = new Throwable().getStackTrace()[1].getMethodName();
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastTime;
        if (lastTime != 0) {
            Log.d(tag, tag + eventName + "Time since last call: "+ timeElapsed);
        }
        lastTime = currentTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventLogger();

        showFeedbackDialog();
    }
    protected void showFeedbackDialog() {
        Log.d(tag, tag + "Opening feedback dialog");
        LayoutInflater inflater = getLayoutInflater();

        android.view.View dialogView = inflater.inflate(R.layout.feedback_dialog, null);

        nameInput = dialogView.findViewById(R.id.nameInput);
        phoneInput = dialogView.findViewById(R.id.phoneInput);
        commentInput = dialogView.findViewById(R.id.commentInput);
        Button sendButton = dialogView.findViewById(R.id.sendButton);
        Button resetButton = dialogView.findViewById(R.id.resetButton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String phone = phoneInput.getText().toString();
                String comment = commentInput.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty() && !comment.isEmpty()) {
                    Log.d(tag, tag + "Feedback Submitted!");
                    Log.d(tag, tag + "Name: "+ name);
                    Log.d(tag, tag + "Phone: "+ phone);
                    Log.d(tag, tag + "Comment: "+ comment);
                }
                else {
                    Log.d(tag, tag + "Missing Information.");
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.setText("");
                phoneInput.setText("");
                commentInput.setText("");
            }
        });
    }

    protected void onStart() {
        super.onStart();
        EventLogger();
    }
    protected void onRestart() {
        super.onRestart();
        EventLogger();
    }
    protected void onResume() {
        super.onResume();
        EventLogger();
    }
    protected void onPause() {
        super.onPause();
        EventLogger();
    }
    protected void onStop() {
        super.onStop();
        EventLogger();
    }
    protected void onDestroy() {
        super.onDestroy();
        EventLogger();
    }
}