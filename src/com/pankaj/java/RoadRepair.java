package com.pankaj.java;

import java.util.ArrayList;
import java.util.List;

public class RoadRepair {

    enum PotholeState {
        POTHOLE,
        CLEAN
    }

    static final PotholeState[][] STR_TO_STATE = {
            {PotholeState.CLEAN, PotholeState.POTHOLE},
            {PotholeState.POTHOLE, PotholeState.CLEAN}
    };

    public static List<List<PotholeState>> readLanes(String l1, String l2) {
        List<List<PotholeState>> lanes = new ArrayList<>();
        for (int i=0; i<l1.length(); i++) {
            List<PotholeState> lane = new ArrayList<>();
            /*lane.add(STR_TO_STATE[0][l1.charAt(i) == '.' ? 0 : 1]);
            lane.add(STR_TO_STATE[1][l2.charAt(i) == '.' ? 0 : 1]);*/
            lane.add(l1.charAt(i) == '.' ? PotholeState.CLEAN : PotholeState.POTHOLE);
            lane.add(l2.charAt(i) == '.' ? PotholeState.CLEAN : PotholeState.POTHOLE);
            lanes.add(lane);
        }
        return lanes;
    }

    private static int maxRepairableHelper (List<PotholeState> l1, List<PotholeState> l2) {
        int[] l1AvoidedPotholes = new int[l1.size()];
        int[] l2AvoidedPotholes = new int[l2.size()];

        for (int i=l1.size()-2; i>=0; i--) {
            l1AvoidedPotholes[i] = l1AvoidedPotholes[i+1];
            if (l1.get(i+1) == PotholeState.POTHOLE) {
                l1AvoidedPotholes[i]++;
            }
        }

        for (int i=1; i<l2.size(); i++) {
            l2AvoidedPotholes[i] = l2AvoidedPotholes[i-1];
            if (l2.get(i-1) == PotholeState.POTHOLE) {
                l2AvoidedPotholes[i]++;
            }
        }

        int maxRepairableSegments = 0;
        for (int i=0; i<l1.size(); i++) {
            maxRepairableSegments = Math.max(maxRepairableSegments, l1AvoidedPotholes[i] + l2AvoidedPotholes[i]);
        }

        return maxRepairableSegments;
    }

    public static int maxRepairableSegments (List<List<PotholeState>> road) {
        List<PotholeState> lane1 = new ArrayList<>();
        List<PotholeState> lane2 = new ArrayList<>();

        for (List<PotholeState> lane : road) {
            lane1.add(lane.get(0));
            lane2.add(lane.get(1));
        }

        return Math.max(maxRepairableHelper(lane1, lane2), maxRepairableHelper(lane2, lane1));
    }

    public static void main(String[] args) {
        String l1 = "..XX.X.";
        String l2 = "X.X.X..";
        List<List<PotholeState>> lanes = readLanes(l1 ,l2);
        System.out.println(maxRepairableSegments(lanes));
    }
}
