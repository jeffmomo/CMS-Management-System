package com.seveneleven;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class timer {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                // Invoke method(s) to do the work
                reportGenerator.WriteToFile();
                reportGenerator.ReadFromFile("report.txt");
                sendEmail.sendEmailwithAttachment();
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 10, TimeUnit.SECONDS);

    }

}

