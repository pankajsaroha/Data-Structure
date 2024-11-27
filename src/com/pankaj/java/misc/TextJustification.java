package com.pankaj.java.misc;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    // Not working
    //Working - TextJustification2

    static int startIndex = 0;
    static int processed = 0;
    public List<String> fullJustify(String[] words, int maxwidth) {
        List<String> list = new ArrayList<>();
        while (startIndex < words.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(getLine(words, maxwidth));
            int length = sb.length();
            int spaceReq = maxwidth - length;
            if (processed > 1 || startIndex < words.length) {
                list.add(addSpace(sb, spaceReq, processed-1));
            } else {
                list.add(lastLine(sb, spaceReq));
            }
        }
        return list;
    }

    private String getLine(String words[], int maxwidth) {
        StringBuilder sb = new StringBuilder();
        processed = 0;
        while (startIndex < words.length && sb.length() + words[startIndex].length() < maxwidth) {
            processed++;
            sb.append(words[startIndex++] + " ");
        }
        return sb.toString().trim();
    }

    private String addSpace(StringBuilder sb, int spaceRequired, int gap) {
        int mandate = spaceRequired / gap;
        int rem = spaceRequired % gap;
        String wd[] = sb.toString().split(" ");
        sb = new StringBuilder();
        StringBuilder space = new StringBuilder();
        for (int i=0; i<=mandate; i++) {
            space.append(" ");
        }
        for (int i=0; i<wd.length-1; i++) {
            sb.append(wd[i]);
            sb.append(space);
            if (rem > 0) {
                sb.append(" "); rem--;
            }
        }
        sb.append(wd[wd.length-1]);
        return sb.toString();
    }

    private String lastLine(StringBuilder sb, int spaceReq) {
        StringBuilder space = new StringBuilder();
        for (int i=0; i<spaceReq; i++) {
            space.append(" ");
        }
        sb.append(space);
        return sb.toString();
    }

    public static void main(String[] args) {
        TextJustification t = new TextJustification();
        String words[] = {"This", "is", "an", "example", "of", "text", "justification."};

        System.out.println(t.fullJustify(words, 16));
    }
}
