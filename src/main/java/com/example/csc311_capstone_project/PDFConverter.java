package com.example.csc311_capstone_project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class with one static method used to convert a PDF into a PNG.
 * @since 5/3/2005
 * @author Nathaniel Rivera
 */
public class PDFConverter {

    /**
     * Converts a given PDF into a PNG.
     * @param filepath Filepath to the PDF
     * @return A File Object which stores the filepath to the image.
     * @since 5/3/2005
     * @author Nathaniel Rivera
     */
    public static File convertPDF(String filepath) {
        String fileEnd = filepath.substring(filepath.lastIndexOf('/'), filepath.length());
        File pngFile = null;

        Pattern spacePattern = Pattern.compile("%20");
        Matcher spaceMatcher = spacePattern.matcher(fileEnd);
        fileEnd = spaceMatcher.replaceAll(" ");

        filepath = filepath.substring(5, filepath.lastIndexOf('/')) + fileEnd;

        System.out.println("File path: " +  filepath);

        try {
            PDDocument document = PDDocument.load(new File(filepath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(
                        page, 300, org.apache.pdfbox.rendering.ImageType.RGB);
                pngFile = new File(filepath + "_" + (page + 1) + ".png");
                ImageIO.write(bim, "png", pngFile);
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pngFile;
    }
}
