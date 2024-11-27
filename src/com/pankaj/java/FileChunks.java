package com.pankaj.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileChunks {

    public static List<File> splitFile (File file, int chunkSizeKB) throws FileNotFoundException, IOException {
        int chunkSize = chunkSizeKB * 1024;
        List<File> chunks;

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[chunkSize];
            int bytesRead;
            int chunkNumber = 0;
            chunks = new ArrayList<>();

            while ((bytesRead = fis.read(buffer)) > 0) {
                File chunkFile = new File(file.getParent(), file.getName().substring(0, 5) + "-part" + ++chunkNumber + ".ogg");
                try (FileOutputStream fos  = new FileOutputStream(chunkFile)) {
                    fos.write(buffer, 0, bytesRead);
                }
                chunks.add(chunkFile);
                System.out.println("Created Chunk: " + chunkFile.getName());
            }
        }
        assembleFiles(chunks, "C:\\ProjectData\\Java\\LearningCenter\\src\\com\\pankaj\\java\\audio_new.ogg");
        return chunks;
    }

    public static void assembleFiles (List<File> chunks, String path) throws IOException{
        try (FileOutputStream fos = new FileOutputStream(new File(path))) {
            byte[] buffer = new byte[1024];
            for (File chunk : chunks) {
                try (FileInputStream fis = new FileInputStream(chunk)) {
                    int byteReads;
                    while ((byteReads = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteReads);
                    }
                }
                System.out.println("Appended chunk: " + chunk.getName());
            }
        }
    }

    public static int solution(String S) {
        int operations = 0;
        long v = Long.parseLong(S, 2);

        while (v > 0) {
            if (v % 2 == 0) {
                v /= 2;
            } else {
                v -= 1;
            }
            operations++;
        }
        return operations;
    }

    public static int sol (String S) {
        int operations = 0;
        StringBuilder sb = new StringBuilder(S);
        int len = sb.length() - 1;
        int count = 0;
        for (int i=0; i<=len; i++) {
            if (sb.charAt(i) == '1') {
                count++;
            }
        }
        while (len >= 0) {
            if (sb.charAt(len) == '1') {
                sb.replace(len, len+1, "0");
                count--;
            } else {
                sb.deleteCharAt(len--);
            }
            operations++;
            if (count == 0) break;
        }

        return operations;
    }

    public static void main(String[] args) throws IOException {
        /*File file = new File("C:\\ProjectData\\Java\\LearningCenter\\src\\com\\pankaj\\java\\audio.ogg");
        try {
            splitFile(file, 31);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        String s = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        System.out.println(sol("011100"));
    }
}
