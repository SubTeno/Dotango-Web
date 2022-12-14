package com.subteno.dotango.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    void init() throws IOException {
        FileInputStream serviceAccount = null;
        serviceAccount = new FileInputStream("D:/dotango-e199d-firebase-adminsdk-99uvi-5961f1bc76.json");

        System.out.println(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

    }

}