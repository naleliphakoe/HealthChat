
package com.example.healthchat;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoConversations {

    public static HashMap<String, ArrayList<Message>>
    getConversations() {

        HashMap<String, ArrayList<Message>> conversations =
                new HashMap<>();

        ArrayList<Message> mokoena = new ArrayList<>();

        mokoena.add(new Message("You",
                "Hi Doctor, I've been having headaches since yesterday.",
                "09:12", true));

        mokoena.add(new Message("Dr. Thabo Mokoena",
                "Hi! I'm sorry you're experiencing that. How severe is the headache?",
                "09:14", false));

        mokoena.add(new Message("You",
                "It's around a 5/10 and I feel a little nauseous.",
                "09:16", true));

        mokoena.add(new Message("Dr. Thabo Mokoena",
                "Thank you for explaining. Are you experiencing any other symptoms?",
                "09:18", false));

        mokoena.add(new Message("You",
                "No, none of those symptoms.",
                "09:20", true));

        mokoena.add(new Message("Dr. Thabo Mokoena",
                "Let's go through a few more questions to understand your symptoms.",
                "09:21", false));

        conversations.put("Dr. Thabo Mokoena", mokoena);

        ArrayList<Message> dlamini = new ArrayList<>();

        dlamini.add(new Message("You",
                "Hi Nurse Lerato. I have a question about my medication.",
                "10:03", true));

        dlamini.add(new Message("Nurse Lerato Dlamini",
                "Hi! Of course. What would you like to know?",
                "10:05", false));

        dlamini.add(new Message("You",
                "I forgot to take one of my doses this morning.",
                "10:06", true));

        dlamini.add(new Message("Nurse Lerato Dlamini",
                "The right action depends on the medication. What medication are you taking?",
                "10:08", false));

        conversations.put("Nurse Lerato Dlamini", dlamini);

        ArrayList<Message> naidoo = new ArrayList<>();

        naidoo.add(new Message("You",
                "Hi Doctor. I've been experiencing period-related discomfort.",
                "11:22", true));

        naidoo.add(new Message("Dr. Aisha Naidoo",
                "Hi! I'm happy to help you understand what you're experiencing.",
                "11:24", false));

        naidoo.add(new Message("You",
                "The pain started when my period started.",
                "11:25", true));

        naidoo.add(new Message("Dr. Aisha Naidoo",
                "How would you rate the pain from 1 to 10?",
                "11:27", false));

        conversations.put("Dr. Aisha Naidoo", naidoo);

        ArrayList<Message> molefe = new ArrayList<>();

        molefe.add(new Message("You",
                "I've been feeling overwhelmed with university lately.",
                "13:02", true));

        molefe.add(new Message("Dr. Kabelo Molefe",
                "I'm sorry you're feeling overwhelmed. Would you like to tell me more?",
                "13:05", false));

        molefe.add(new Message("You",
                "I have too many things to deal with at once.",
                "13:07", true));

        molefe.add(new Message("Dr. Kabelo Molefe",
                "Breaking your responsibilities into smaller tasks may help.",
                "13:09", false));

        conversations.put("Dr. Kabelo Molefe", molefe);

        ArrayList<Message> clinic = new ArrayList<>();

        clinic.add(new Message("You",
                "Hi, I'd like to book an appointment.",
                "14:15", true));

        clinic.add(new Message("Clinic Support",
                "Hello! What day would you prefer?",
                "14:17", false));

        clinic.add(new Message("You",
                "Thursday afternoon if possible.",
                "14:18", true));

        clinic.add(new Message("Clinic Support",
                "Your request can be reviewed by the clinic. Please confirm availability directly.",
                "14:20", false));

        conversations.put("Clinic Support", clinic);

        return conversations;
    }
}

