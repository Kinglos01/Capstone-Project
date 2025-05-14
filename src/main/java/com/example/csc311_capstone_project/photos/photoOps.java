package com.example.csc311_capstone_project.photos;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobErrorCode;
import com.azure.storage.blob.models.BlobStorageException;
import javafx.scene.image.Image;

import java.io.*;

public class photoOps {
    final static String sasToken = "sv=2024-11-04&ss=bfqt&srt=sco&sp=rwdlacupiytfx&se=2025-05-31T13:07:43Z&st=2025-05-13T05:07:43Z&spr=https&sig=gzT5%2BNBEWQtfjJE%2FtnFuKEu0X9PWLCUcP7ilzwO2Fyc%3D";
    final static String endpoint = "https://311invoicestorage.blob.core.windows.net/";
    protected static BlobServiceClient blobServiceClient;
    protected static BlobContainerClient containerClient;

    public static void connectToClient() {
        try {
            blobServiceClient = new BlobServiceClientBuilder()
                    .endpoint(endpoint)
                    .sasToken(sasToken)
                    .buildClient();

            try {
                containerClient = blobServiceClient.createBlobContainer("photo-container");
            } catch (BlobStorageException e) {
                if (e.getErrorCode().equals(BlobErrorCode.CONTAINER_ALREADY_EXISTS)) {
                    containerClient = blobServiceClient.getBlobContainerClient("photo-container");
                } else {
                    throw e;
                }
            }

        } catch (Exception e) {
            System.err.println("Azure Blob Storage connection failed: " + e.getMessage());
            e.printStackTrace();
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

    public static InputStream getImageStream(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            blobClient.download(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (BlobStorageException e) {
            System.out.println("Failed to load image from blob: " + e.getMessage());
            return null;
        }
    }

    public static Image getAzureImage(String blobName) {
        try {
            BlobClient blobClient = containerClient.getBlobClient(blobName);
            if (!blobClient.exists()) {
                System.err.println("Blob not found: " + blobName);
                return null;
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            blobClient.download(outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            return new Image(inputStream);
        } catch (Exception e) {
            System.err.println("Error retrieving image: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String getBlobUrl(String blobName) {
        return endpoint + "photo-container/" + blobName + "?" + sasToken;
    }

}
