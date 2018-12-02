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
    private List<PdfFormField> fields; 

    public PdfFieldsOf(final PDDocument pdDocument) {
        this.pdDocument = pdDocument;
    }

    public List<PdfFormField> asList() {
        if(fields == null) {
            this.extractFields();
        }
        return fields;
    }

    private void extractFields() {
	this.fields = new ArrayList<>();

        final PDDocumentCatalog documentCatalog = pdDocument.getDocumentCatalog();
        final PDAcroForm acroForm = documentCatalog.getAcroForm();
	if(acroForm == null) {
          return;
        }

        final Iterator<PDField> fieldIterator = acroForm.getFieldIterator();
        while(fieldIterator.hasNext()) {
            final PDField field = fieldIterator.next();
            final String name = field.getFullyQualifiedName();
            final String value = field.getValueAsString();
            final String type = field.getFieldType();
            this.fields.add(new PdfFormField(type, name, value));
        }
    }
}
