package com.ngagerrard.storage.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FireBaseStoreManager {
    private static final Logger LOG = LoggerFactory.getLogger(FireBaseStoreManager.class);

    public FireBaseStoreManager() {
        try {
            File file = new ClassPathResource("service_account_key.json").getFile();
            InputStream inputStream = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://mychat-26d67.firebaseio.com")
                    .setStorageBucket("mychat-26d67.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Bucket bucket = StorageClient.getInstance().bucket();
        LOG.info("uploadFile info bucket: " + bucket.getName());
        LOG.debug("uploadFile debug bucket: " + bucket.getName());
        bucket.create(filename, multipartFile.getInputStream(), multipartFile.getContentType());
        return filename;
    }

    public byte[] getImage(String name) {
        Bucket bucket = StorageClient.getInstance().bucket();
        return bucket.get(name).getContent();
    }
}
