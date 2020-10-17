package com.company;

public class Kutya {
    int id;
    String magyar_tipus;
    String angol_tipus;
    String nev;
    int eletkor;
    String utolsoEllenorzes;

    public Kutya(int id, String magyar_tipus, String angol_tipus, String nev, int eletkor, String utolsoEllenorzes) {
        this.id = id;
        this.magyar_tipus = magyar_tipus;
        this.angol_tipus = angol_tipus;
        this.nev = nev;
        this.eletkor = eletkor;
        this.utolsoEllenorzes = utolsoEllenorzes;
    }

    @Override
    public String toString() {
        return "Kutya{" +
                "id=" + id +
                ", magyar_tipus='" + magyar_tipus + '\'' +
                ", angol_tipus='" + angol_tipus + '\'' +
                ", nev='" + nev + '\'' +
                ", eletkor=" + eletkor +
                ", utolsoEllenorzes='" + utolsoEllenorzes + '\'' +
                '}';
    }
}
