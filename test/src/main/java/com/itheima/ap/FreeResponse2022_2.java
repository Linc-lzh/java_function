package com.itheima.ap;

public class FreeResponse2022_2 {
    static class Book {
        private String title;
        private double price;

        public Book(String title, double price) {
            this.title = title;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getBookInfo() {
            return title + "-" + price;
        }
    }

    static class TextBook extends Book {
        private int edition;
        public TextBook(String title, double price, int edition) {
            super(title, price);
            this.edition = edition;
        }

        public int getEdition() {
            return edition;
        }

        @Override
        public String getBookInfo() {
            return super.getBookInfo() + "-" + edition;
        }

        public boolean canSubstituteFor(TextBook other) {
            if (getTitle().equals(other.getTitle()) && edition >= other.edition) {
                return true;
            }
            return false;
        }
    }
}
