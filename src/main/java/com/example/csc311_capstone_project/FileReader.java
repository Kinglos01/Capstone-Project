package com.example.csc311_capstone_project;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

public class FileReader {
    private String filePath;
    private PdfReader reader;
    private int numPages;

    public FileReader(String filePath) {
        this.filePath = filePath;
        try {
            reader = new PdfReader(filePath);
        }
        catch (IOException e) {
            System.out.println("File cannot be found!");
        }
        numPages = reader.getNumberOfPages();
    }

    public String getTextFromFile() {
        try {
            String text = PdfTextExtractor.getTextFromPage(reader, 1);
            reader.close();
            return text;
        } catch (IOException e) {
            return null;
        }
    }
}
