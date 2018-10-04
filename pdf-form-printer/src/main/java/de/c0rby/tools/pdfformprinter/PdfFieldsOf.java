package de.c0rby.tools.pdfformprinter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfFieldsOf {

    private final PDDocument pdDocument;
    private final List<PdfFormField> fields = new ArrayList<>();

    public PdfFieldsOf(final PDDocument pdDocument) {
        this.pdDocument = pdDocument;
    }

    public List<PdfFormField> asList() {
        if(fields.isEmpty()) {
            this.extractFields();
        }
        return fields;
    }

    private synchronized void extractFields() {
        final PDDocumentCatalog documentCatalog = pdDocument.getDocumentCatalog();
        final PDAcroForm acroForm = documentCatalog.getAcroForm();

        final Iterator<PDField> fieldIterator = acroForm.getFieldIterator();
        while(fieldIterator.hasNext()) {
            final PDField field = fieldIterator.next();
            final String name = field.getFullyQualifiedName();
            final String value = field.getValueAsString();
            final String type = field.getFieldType();
            fields.add(new PdfFormField(type, name, value));
        }
    }
}
