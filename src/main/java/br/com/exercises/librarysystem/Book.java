package br.com.exercises.librarysystem;

public class Book {
    private String title;
    private String code;
    private boolean isAvailable;

    public Book(String title, String code) {
        if (isValid(code)) {
            this.title = title;
            this.code = code;
            isAvailable = true;
        }
    }

    private boolean isValid(String code) {
        if (code.length() != 7) return false;

        char[] charArray = code.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i == 3 && charArray[i] != '-') return false;

            if (i < 3 && !Character.isLetter(charArray[i])) return false;

            if (i > 3 && !Character.isDigit(charArray[i])) return false;
        }

        return true;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getBookAsString() {
        return "Title: " + title +
                "\nCode: " + code +
                "\nAvailable: " + (isAvailable ? "yes" : "no");
    }
}
