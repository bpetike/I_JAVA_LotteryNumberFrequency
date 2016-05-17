package hu.codecool;

import drawevent.DrawEvent;
import drawevent.DrawEvent735;

public class Main {

    public static void main(String[] args) {
        DrawEvent event = new DrawEvent735().parse("1999;46;;0;0 Ft;81;150 116 Ft;3562;3 414 Ft;54974;590 Ft;1;4;13;15;17;20;24;1;19;22;26;29;31;35");
        System.out.println(event);
    }
}
