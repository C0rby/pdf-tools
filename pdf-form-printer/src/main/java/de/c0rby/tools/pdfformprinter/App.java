package de.c0rby.tools.pdfformprinter;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        if(args.length != 1) {
            printUsage();
            System.exit(0);
        }

        final PDDocument pdDoc;
        try {
            pdDoc = PDDocument.load(new File(args[0]));
        } catch (IOException e) {
            System.err.println("Failed to load PDF: " + e.getMessage());
            System.exit(1);
            return;
        }
        final List<PdfFormField> fields = new PdfFieldsOf(pdDoc).asList();
        for (final PdfFormField field : fields) {
            System.out.println(field.toString());
        }
    }

    public static void printUsage() {
        System.out.println("pdfformprinter <PDF File>");
    }
}
