package com.pankaj.java.leetcode;

public class BookcaseShelves1105 {

    public int minHeightShelves (int[][] books, int shelfWidth) {
        return minHeight(books, 0, shelfWidth, shelfWidth, 0);
    }

    private int minHeight (int[][] books, int index, int width, int shelfWidth, int height) {
        if(index == books.length) {
            return height;
        }
        int currentShlef = Integer.MAX_VALUE, nextShelf = Integer.MAX_VALUE;
        if (books[index][0] <= width) {
            currentShlef = minHeight(books, index+1, width - books[index][0], shelfWidth,
                    Math.max(height, books[index][1]));
        }
        nextShelf = height + minHeight(books, index+1, shelfWidth - books[index][0], shelfWidth, books[index][1]);
        return Math.min(currentShlef, nextShelf);
    }

    public static void main(String[] args) {
        BookcaseShelves1105 b = new BookcaseShelves1105();
        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        //int[][] books = {{1, 3}, {2, 4}, {3, 2}};
        System.out.println(b.minHeightShelves(books, 4));
    }
}
