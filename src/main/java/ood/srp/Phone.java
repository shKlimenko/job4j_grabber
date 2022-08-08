package ood.srp;

import java.util.Calendar;

public class Phone {
    private final long number;
    private String userName;
    private final Calendar date;
    private boolean isActive;

    public Phone(long number, String userName, Calendar date) {
        this.number = number;
        this.userName = userName;
        this.date = date;
    }

    public void sendMessage(String text) {
        System.out.println("Sending message...");
    }

    public long getNumber() {
        return number;
    }

        public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
