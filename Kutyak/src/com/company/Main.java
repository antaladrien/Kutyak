package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static Kutya legidosebb(ArrayList<Kutya> kutyak) {
        Kutya legidosebb = kutyak.get(0);
        for (int i = 0; i < kutyak.size(); i++) {
            if(legidosebb.eletkor < kutyak.get(i).eletkor) {
                legidosebb = kutyak.get(i);
            }
        }
        return legidosebb;
    }

    public static double atlagEletkor(ArrayList<Kutya> kutyak) {
        double atlag = 0.0;
        for (int i = 0; i < kutyak.size(); i++) {
            atlag += kutyak.get(i).eletkor;
        }
        return atlag / kutyak.size();
    }


    public static HashMap<String, Integer> hanyFajtaADatumnal(ArrayList<Kutya> kutyak) {
        HashMap<String, Integer> fajtak = new HashMap<>();
        for (int i = 0; i < kutyak.size(); i++) {
            if(kutyak.get(i).utolsoEllenorzes.compareTo("2018.01.10") == 0) {
                String fajtanev = kutyak.get(i).magyar_tipus;
                if(fajtak.containsKey(fajtanev)) {
                    int db = fajtak.get(fajtanev);
                    db++;
                    fajtak.replace(fajtanev, db);
                } else {
                    fajtak.put(fajtanev, 1);
                }
            }
        }
        return fajtak;
    }

    public static HashMap<String, Integer> hanyKutyaAznap(ArrayList<Kutya> kutyak) {
        HashMap<String, Integer> kutyaAznap = new HashMap<>();
        for (int i = 0; i < kutyak.size(); i++) {
            String kulcs = kutyak.get(i).utolsoEllenorzes;
            if(kutyaAznap.containsKey(kulcs)) {
                int db = kutyaAznap.get(kulcs);
                db++;
                kutyaAznap.replace(kulcs, db);
            } else {
                kutyaAznap.put(kulcs, 1);
            }
        }
        return kutyaAznap;
    }

    public static HashMap.Entry<String, Integer>
            maximumForgalmasNap(HashMap<String, Integer> hanyKutya)
    {
        HashMap.Entry<String, Integer> maximumForgalmuNap =
                new HashMap.SimpleEntry<String, Integer>("1999.01.01", 0);
        for (HashMap.Entry<String, Integer> element : hanyKutya.entrySet()) {
            if(maximumForgalmuNap.getValue() < element.getValue()) {
                maximumForgalmuNap = element;
            }
        }
        return maximumForgalmuNap;
    }


    public static HashMap<String, Integer> kutyanevSzamossag(ArrayList<Kutya> kutyak) {
        HashMap<String, Integer> kutyanevek = new HashMap<>();
        for (int i = 0; i < kutyak.size(); i++) {
            String kulcs = kutyak.get(i).nev;
            if(kutyanevek.containsKey(kulcs)) {
                int db = kutyanevek.get(kulcs);
                db++;
                kutyanevek.replace(kulcs, db);
            } else {
                kutyanevek.put(kulcs, 1);
            }
        }
        return kutyanevek;
    }


    public static void main(String[] args) {
        String sor;
        ArrayList<Kutya> kutyak = new ArrayList<>();

        try (FileReader fr = new FileReader("kutyak.csv");
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine();
            while ( (sor = br.readLine()) != null) {
                String[] valtozok = sor.split(";");

                Kutya k = new Kutya(
                        Integer.parseInt(valtozok[0]),
                        valtozok[1],
                        valtozok[1],
                        valtozok[2],
                        Integer.parseInt(valtozok[3]),
                        valtozok[4]
                );
                kutyak.add(k);
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        int kutyaNevekSzama = 0;
        try (FileReader fr = new FileReader("KutyaNevek.csv");
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine();
            while ( (sor = br.readLine()) != null) {
                ++kutyaNevekSzama;
                String[] valtozok = sor.split(";");

                for (int i = 0; i < kutyak.size(); i++) {
                    if(kutyak.get(i).nev.compareTo(valtozok[0]) == 0) {
                        kutyak.get(i).nev = valtozok[1];
                    }
                }
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


        try (FileReader fr = new FileReader("KutyaFajtak.csv");
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine();
            while ( (sor = br.readLine()) != null) {
                String[] valtozok = sor.split(";");

                for (int i = 0; i < kutyak.size(); i++) {
                    if(kutyak.get(i).magyar_tipus.compareTo(valtozok[0]) == 0) {
                        kutyak.get(i).magyar_tipus = valtozok[1];
                        if(valtozok.length < 3) {
                            kutyak.get(i).angol_tipus = valtozok[1];
                        } else {
                            kutyak.get(i).angol_tipus = valtozok[2];
                        }
                    }
                }
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

/*
        //Kiírás
        for (int i = 0; i < kutyak.size(); i++) {
            System.out.println(kutyak.get(i));
        }
*/

        System.out.println("3. feladat: Kutyanevek száma: " + kutyaNevekSzama);

        String szam = String.format("%.2f", atlagEletkor(kutyak));
        szam = szam.replace(".", ",");
        System.out.println("6. feladat: Kutyák átlagéletkora: " +
                szam);

        Kutya legidosebbKutya = legidosebb(kutyak);
        System.out.println("7. feladat: Legidősebb kutya neve és fajtája: " +
                legidosebbKutya.nev + ", " + legidosebbKutya.magyar_tipus);


        HashMap<String, Integer> fajtak = hanyFajtaADatumnal(kutyak);
        System.out.println("8. feladat: Január 10.-én vizsgált kutya fajták: ");
        for (HashMap.Entry<String, Integer> element : fajtak.entrySet()) {
            System.out.println(element.getKey() + ": "
                    + element.getValue() + " kutya");
        }

        HashMap<String, Integer> hanyKutya = hanyKutyaAznap(kutyak);
        HashMap.Entry<String, Integer> maxForgalom =
                maximumForgalmasNap(hanyKutya);
        System.out.println("9. feladat: Legjobban leterhelt nap: " +
                maxForgalom.getKey() + ".: " + maxForgalom.getValue() + " kutya");

        try (FileWriter fw = new FileWriter("Névstatisztika.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            HashMap<String, Integer> kutyaNevek = kutyanevSzamossag(kutyak);

            ArrayList<KutyaNevSzamossag> rendezettKutyak =
                    new ArrayList<>();

            for (HashMap.Entry<String, Integer> element :
                    kutyaNevek.entrySet()) {

                KutyaNevSzamossag kn = new
                        KutyaNevSzamossag(
                                element.getKey(), element.getValue());

                rendezettKutyak.add(kn);
            }

            Collections.sort(rendezettKutyak);
            Collections.reverse(rendezettKutyak);
            for (int i = 0; i < rendezettKutyak.size(); i++) {
                bw.write(rendezettKutyak.get(i).nev +
                        ";" + rendezettKutyak.get(i).db);
                bw.newLine();
            }


        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        System.out.println("10. feladat: névstatisztika.txt");

    }
}
