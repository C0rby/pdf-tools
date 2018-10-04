package de.c0rby.tools.pdfformprinter;

public class PdfFormField {
    private String type;
    private String name;
    private String value;

    public PdfFormField(final String type, final String name, final String value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String type() {
        return type;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder field = new StringBuilder();
        field.append(name)
                .append('[').append(value).append("] ")
                .append(type);
        return field.toString();
    }
}
