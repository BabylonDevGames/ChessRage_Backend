package com.ibabylon.chessrage;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@SpringBootApplication
public class ChessRageApplication {


	public static void main(String[] args) throws IOException{

		ClassLoader classLoader = ChessRageApplication.class.getClassLoader();

		//File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());
		//File file = new ClassPathResource("serviceAccount.json").getFile();
		ClassPathResource serviceAccount = new ClassPathResource("serviceAccount.json");
		//FileInputStream serviceAccount = new FileInputStream(file.);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(ChessRageApplication.class, args);
	}

}
