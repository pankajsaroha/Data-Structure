package com.pankaj.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Given two arrays start[] and end[] such that start[i] is the starting time of ith meeting and end[i]
// is the ending time of ith meeting. Return the minimum number of rooms required to attend all meetings.
public class MeetingRooms2 {

    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        public Meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.start - o.start;
        }
    }

    //approach1
    public int minMeetingRoom (int[] start, int[] end) {
        List<Meeting> list = new ArrayList<>();
        int count = 1;
        for (int i=0; i<start.length; i++) {
            list.add(new Meeting(start[i], end[i]));
        }
        Collections.sort(list);
        for(int i=1; i<list.size(); i++) {
            if (list.get(i-1).end > list.get(i).start) {
                count++;
            }
        }
        return count;
    }

    //approach2
    public int minMeetingRooms (int[][] meetings) {
        int[] time = new int[20];
        for (int[] meeting : meetings) {
            time[meeting[0]]++;
            time[meeting[1]]--;
        }
        int res = time[0];
        for (int i=1; i<20; i++) {
            time[i] += time[i-1];
            res = Math.max(res, time[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        MeetingRooms2 s = new MeetingRooms2();
        int[] start = {1, 10, 7};
        int[] end = {4, 15, 10};
        System.out.println(s.minMeetingRoom(start, end));
        System.out.println(s.minMeetingRooms(
                new int[][] {
                        {1, 4},
                        {10, 15},
                        {7, 11},
                        {4, 7}
                }
        ));
    }
}
