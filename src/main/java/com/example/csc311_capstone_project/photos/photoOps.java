package com.example.csc311_capstone_project.photos;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobErrorCode;
import com.azure.storage.blob.models.BlobStorageException;
import javafx.scene.image.Image;

import java.io.File;
import java.io.UncheckedIOException;

public class photoOps {
    final static String sasToken = "sas token";
    final static String endpoint = "endpoint";
    protected static BlobServiceClient blobServiceClient;
    protected static BlobContainerClient containerClient;
    public static void connectToClient() {
        blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .sasToken(sasToken)
                .buildClient();

        try {
            containerClient = blobServiceClient.createBlobContainer("photo-container");
        } catch (BlobStorageException e) {
            if (e.getErrorCode().equals(BlobErrorCode.CONTAINER_ALREADY_EXISTS)) {
                throw e;
            }
        }
    }

    public static void uploadImage(File file, String name) {
        BlobClient blobClient = containerClient.getBlobClient(name);
        try {
            blobClient.uploadFromFile(file.getAbsoluteFile().getPath());
        } catch (UncheckedIOException e) {
            System.out.println("Failed to upload image file to DB");
        }
    }
}
