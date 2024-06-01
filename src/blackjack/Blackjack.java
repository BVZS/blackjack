
package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    
    private static final String SEP = System.lineSeparator();
    private static int[] jatekos = new int[20];
    private static int[] oszto = new int[20];
    
    public static void main(String[] args) {
        blackjackJatek();
    }

    private static void blackjackJatek() {
        kiir("A blackjack elkezdődött." + SEP);
        osztasJatekosnak();
        lapKeres();
        
        //osztasOsztonak();
    }
   
    private static void osztasJatekosnak() {
        kiir("... Lapok osztása a játékosnak ..." + SEP);
        while (pontSzamitas(jatekos) < 15) {
            for (int i = 0; i < jatekos.length; i++) {
                if(jatekos[i] == 0) {
                    jatekos[i] = veletlenszeruLap();
                    break;
                }
            }
        }
        jatekosLapokMutatasa();
    }
    
    /*private static void oasztasOsztonak() {
        kiir("... Lapok osztása az osztónak ..." + SEP);
        while (pontSzamitas(jatekos) < 15) {
            for (int i = 0; i < jatekos.length; i++) {
                if(jatekos[i] == 0) {
                    jatekos[i] = veletlenszeruLap();
                    break;
                }
            }
        }
        jatekosLapokMutatasa();
    }*/
    
    private static void osztasEgyLapJatekosnak() {
        for (int i = 0; i < jatekos.length; i++) {
            if(jatekos[i] == 0) {
                jatekos[i] = veletlenszeruLap();
                break;
            }
        }
        jatekosLapokMutatasa();
    }
    
    private static void lapKeres() {
        boolean keresFut = true;
        String valasz = "";
        
        while(keresFut == true) {
            System.out.print("Kérsz lapot? (I/N): ");
            valasz = sc.nextLine();
            
            if(valasz.equals("I") || valasz.equals("N")) {
                keresFut = false;
            } else {
                System.out.print("Kérsz lapot? (I/N): ");
                valasz = sc.nextLine();
            }
        }
        
        if(valasz.equals("I")) { // kér lapot
            osztasEgyLapJatekosnak();
           
            if(pontSzamitas(jatekos) < 19) {
                lapKeres();
            }
        }
    }
    
    
    
    
    
    
    
    public static void jatekosLapokMutatasa() {
        kiir("Játékos: \u001B[32m");
        for (int i = 0; i < jatekos.length; i++) {
            if(jatekos[i] != 0) {
                kiir(jatekos[i] + " ");
            }
        }
        kiir("(" + pontSzamitas(jatekos) + ")\u001B[0m" + SEP);
    }
    
    public static int veletlenszeruLap() {
        int min = 2;
        int max = 11;
        int lap = rnd.nextInt((max - min) + 1) + min;
        return lap;
    }
    
    public static int pontSzamitas(int[] tomb) {
        int osszead = 0;
        for (int i = 0; i < tomb.length; i++) {
            osszead += tomb[i];
        }
        return osszead;
    }
    
    private static void kiir(String szoveg) {
        System.out.print(szoveg);
    }
    
}
