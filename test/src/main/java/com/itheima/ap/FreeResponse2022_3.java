package com.itheima.ap;

import java.util.ArrayList;

public class FreeResponse2022_3 {
    static class Review {
        private int rating;
        private String comment;

        public Review(int rating, String comment) {
            this.rating = rating;
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public String getComment() {
            return comment;
        }
    }

    static class ReviewAnalysis {
        private Review[] allReviews;
        public double getAverageRating() {
            double total = 0.0;
            for (Review review : allReviews) {
                total += review.getRating();
            }
            return total / allReviews.length;
        }
        public ArrayList<String> collectComments() {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < allReviews.length; i++) {
                String comment = allReviews[i].getComment();
                if (comment.indexOf("!") >= 0) {
                    String last = comment.substring(comment.length() - 1);
                    if(!last.equals(".") &&  !last.equals("!")) {
                        comment += ".";
                    }
                    list.add(i + "-" + comment);
                }
            }
            return list;
        }
    }
}
