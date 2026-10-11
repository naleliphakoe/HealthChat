
package com.example.healthchat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private ScrollView chatScrollView;
    private EditText messageInput;
    private ImageButton sendButton;
    private TextView chatTitle;
    private TextView chatSubtitle;

    private String professionalName;
    private String profession;
    private String patientName;

    private HashMap<String, ArrayList<Message>> conversations;
    private ArrayList<Message> currentConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        professionalName = getIntent().getStringExtra("professionalName");
        profession = getIntent().getStringExtra("profession");
        patientName = getIntent().getStringExtra("patientName");

        if (professionalName == null) {
            professionalName = "Clinic Support";
        }

        if (profession == null) {
            profession = "Healthcare Professional";
        }

        if (patientName == null) {
            patientName = "Patient";
        }

        chatContainer = findViewById(R.id.chatContainer);
        chatScrollView = findViewById(R.id.chatScrollView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        chatTitle = findViewById(R.id.chatTitle);
        chatSubtitle = findViewById(R.id.chatSubtitle);

        chatTitle.setText(professionalName);
        chatSubtitle.setText(profession);

        conversations = DemoConversations.getConversations();

        currentConversation = conversations.get(professionalName);

        if (currentConversation == null) {
            currentConversation = new ArrayList<>();
            conversations.put(professionalName, currentConversation);
        }

        loadConversation();

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void loadConversation() {
        chatContainer.removeAllViews();

        for (Message message : currentConversation) {
            addMessageToScreen(
                    message.getText(),
                    message.getTime(),
                    message.isFromUser()
            );
        }

        scrollToBottom();
    }

    private void sendMessage() {
        String text = messageInput.getText()
                .toString().trim();

        if (text.isEmpty()) {
            return;
        }

        String time = getCurrentTime();

        Message userMessage = new Message(
                "You", text, time, true);

        currentConversation.add(userMessage);

        addMessageToScreen(text, time, true);

        messageInput.setText("");
        scrollToBottom();

        // Simulate a reply from the selected professional.
        String reply = generateProfessionalReply(text);

        String replyTime = getCurrentTime();

        Message professionalMessage = new Message(
                professionalName,
                reply,
                replyTime,
                false);

        currentConversation.add(professionalMessage);

        addMessageToScreen(reply, replyTime, false);

        scrollToBottom();
    }

    private String generateProfessionalReply(String userMessage) {
        String text = userMessage.toLowerCase(Locale.ROOT);

        if (text.contains("hello") ||
                text.contains("hi ") ||
                text.equals("hi") ||
                text.startsWith("hey")) {
            return "Hello " + patientName +
                    "! Thank you for reaching out. How can I help you today?";
        }

        if (text.contains("appointment") ||
                text.contains("book") ||
                text.contains("schedule")) {
            return "I can help with your appointment enquiry. " +
                    "Please tell me your preferred date and time. " +
                    "The clinic will need to confirm availability.";
        }

        if (text.contains("headache") ||
                text.contains("pain") ||
                text.contains("fever") ||
                text.contains("nausea") ||
                text.contains("symptom")) {
            return "Thank you for explaining your symptoms. " +
                    "Could you tell me when they started and whether " +
                    "they are getting worse? I cannot diagnose you through this demo chat.";
        }

        if (text.contains("medicine") ||
                text.contains("medication") ||
                text.contains("dose") ||
                text.contains("tablet")) {
            return "Medication advice depends on the specific medicine " +
                    "and your circumstances. Please check with a pharmacist " +
                    "or healthcare professional before changing your dose.";
        }

        if (text.contains("stress") ||
                text.contains("anxious") ||
                text.contains("overwhelmed") ||
                text.contains("sad")) {
            return "Thank you for sharing that with me. I'm sorry things " +
                    "feel difficult. Would you like to tell me more about " +
                    "what has been troubling you?";
        }

        if (text.contains("thank")) {
            return "You're welcome! Is there anything else you would like to ask?";
        }

        return "Thank you for your message, " + patientName +
                ". Could you please tell me a little more so I can " +
                "understand your concern?";
    }

    private void addMessageToScreen(
            String message, String time, boolean fromUser) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(fromUser ? Gravity.END : Gravity.START);

        LinearLayout.LayoutParams rowParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        rowParams.setMargins(0, dp(5), 0, dp(5));
        row.setLayoutParams(rowParams);

        LinearLayout bubble = new LinearLayout(this);
        bubble.setOrientation(LinearLayout.VERTICAL);
        bubble.setPadding(dp(14), dp(10), dp(14), dp(8));

        LinearLayout.LayoutParams bubbleParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        0.78f);

        bubbleParams.setMargins(
                fromUser ? dp(45) : 0,
                0,
                fromUser ? 0 : dp(45),
                0);

        bubble.setLayoutParams(bubbleParams);

        GradientDrawable background = new GradientDrawable();
        background.setColor(fromUser
                ? Color.rgb(39, 112, 92)
                : Color.WHITE);

        background.setCornerRadius(dp(16));
        bubble.setBackground(background);

        TextView messageText = new TextView(this);
        messageText.setText(message);
        messageText.setTextSize(15);
        messageText.setTextColor(fromUser
                ? Color.WHITE
                : Color.rgb(35, 45, 42));

        bubble.addView(messageText);

        TextView timeText = new TextView(this);
        timeText.setText(time);
        timeText.setTextSize(10);
        timeText.setTextColor(fromUser
                ? Color.rgb(220, 240, 232)
                : Color.GRAY);

        LinearLayout.LayoutParams timeParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        timeParams.topMargin = dp(5);
        timeParams.gravity = Gravity.END;
        timeText.setLayoutParams(timeParams);

        bubble.addView(timeText);
        row.addView(bubble);
        chatContainer.addView(row);
    }

    private String getCurrentTime() {
        return new SimpleDateFormat(
                "HH:mm", Locale.getDefault())
                .format(new Date());
    }

    private int dp(float value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private void scrollToBottom() {
        chatScrollView.post(() ->
                chatScrollView.fullScroll(View.FOCUS_DOWN));
    }
}
