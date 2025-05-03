package com.example.csc311_capstone_project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFConverter {

    public static File convertPDF(String filepath) {
        String pdfFilePath = filepath.substring(5);
        String outputPngPrefix = filepath.substring(5, filepath.length() - 4);
        File pngFile = null;
        System.out.println("File path: " +  pdfFilePath);

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(
                        page, 300, org.apache.pdfbox.rendering.ImageType.RGB);
                pngFile = new File(outputPngPrefix + "_" + (page + 1) + ".png");
                ImageIO.write(bim, "png", pngFile);
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pngFile);
        return pngFile;
    }
}
