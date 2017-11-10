package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecordBook {
    String name;
    int group;
    List<Session> sessions = new ArrayList();

    RecordBook(String initialInput) {
        StringTokenizer temp = new StringTokenizer(initialInput, " ");
        name = temp.nextToken();
        group = Integer.parseInt(temp.nextToken());
        int sessionCount = Integer.parseInt(temp.nextToken());
        for (int i = 0; i < sessionCount; i++) {
            sessions.add(new Session().fill(temp.nextToken(), temp.nextToken(), temp.nextToken()));
        }
    }

    public void printRecordBook() {
        System.out.println("Name: " + name);
        System.out.println("Group: " + group);
        int counter = 0;
        for (Session a : sessions) {
            System.out.println("Session #" + ((counter++) + 1) + ": ");
            System.out.println("\tMark 1: " + a.mark1);
            System.out.println("\tMark 2: " + a.mark2);
            System.out.println("\tMark 3: " + a.mark3);
        }
    }

    public void printRecordBook(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw, true);
        pw.println("Name: " + name);
        pw.println("Group: " + group);
        int counter = 0;
        for (Session a : sessions) {
            pw.println("Session #" + ((counter++) + 1) + ": ");
            pw.println("\tMark 1: " + a.mark1);
            pw.println("\tMark 2: " + a.mark2);
            pw.println("\tMark 3: " + a.mark3);
        }
        pw.close();
    }

    public int average() {
        int res = 0;
        for (Session a : sessions) {
            res += a.average();
        }
        res = res / sessions.size();
        return res;
    }

    private class Session {
        int mark1;
        int mark2;
        int mark3;

        Session() {
            mark1 = 0;
            mark2 = 0;
            mark3 = 0;
        }

        Session fill(String a, String b, String c) {
            mark1 = Integer.parseInt(a);
            mark2 = Integer.parseInt(b);
            mark3 = Integer.parseInt(c);
            return this;
        }

        private int average() {
            return ((mark1 + mark2 + mark3) / 3);
        }

    }
}
