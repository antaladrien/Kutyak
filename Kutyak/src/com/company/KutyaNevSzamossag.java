package com.company;

public class KutyaNevSzamossag implements Comparable<KutyaNevSzamossag> {
    String nev;
    int db;

    public KutyaNevSzamossag(String nev, int db) {
        this.nev = nev;
        this.db = db;
    }

    @Override
    public int compareTo(KutyaNevSzamossag kutyaNevSzamossag) {
        if(db < kutyaNevSzamossag.db)
            return -1;
        else if(db > kutyaNevSzamossag.db)
            return 1;
        return 0;
    }
}
