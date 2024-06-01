
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
        
        jatekos[0] = veletlenszeruLap(); 
        jatekos[1] = veletlenszeruLap(); 
        
        lapOsztas("Játékos", jatekos);
        lapKeres();
        
        lapOsztas("Osztó", oszto);
        osztoKerVagyNem();
        
        eredmenyHirdetes();
    }
    
    private static void lapOsztas(String kinek, int[] tomb) {
        while (pontSzamitas(tomb) <= 15) {
            for (int i = 0; i < tomb.length; i++) {
                if(tomb[i] == 0) {
                    tomb[i] = veletlenszeruLap();
                    break;
                }
            }
        }
        lapokMutatasa(kinek, tomb);
    }
    
    private static void osztasEgyLapOsztonak() {
        for (int i = 0; i < oszto.length; i++) {
            if(oszto[i] == 0) {
                oszto[i] = veletlenszeruLap();
                break;
            }
        }
        lapokMutatasa("Osztó", oszto);
    }
    
    private static void lapKeres() {
        boolean keresFut = true;
        String valasz = "";
        
        if(pontSzamitas(jatekos) <= 21) {
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
                lapOsztas("Játékos", jatekos);
                if(pontSzamitas(jatekos) < 19) {
                    lapKeres();
                }
            }
        }
    }
    
    private static void osztoKerVagyNem() {
        if(pontSzamitas(oszto) < 17) {
            if(rnd.nextBoolean()) { // true, tehát kér
                osztasEgyLapOsztonak();
            }
        }
    }
    
    
    
    
    private static void eredmenyHirdetes() {
        if(pontSzamitas(jatekos) > 21) {
            kiir("Bust-olt a játékos. A ház nyert." + SEP);
            System.exit(0);
        }
        
        if(pontSzamitas(jatekos) == pontSzamitas(oszto)) {
            kiir("Push. Döntetlen." + SEP); 
        }
        
        if((pontSzamitas(jatekos) == 21) && (pontSzamitas(oszto) < pontSzamitas(jatekos))) {
            kiir("Blackjack! A játékos nyert." + SEP);
        }
        
        if(pontSzamitas(jatekos) > pontSzamitas(oszto)) {
            kiir("A játékos nyert." + SEP);
        } else {
            if(pontSzamitas(oszto) < 22) {
                kiir("Az osztó nyert." + SEP);
            } else {
                kiir("A játékos nyert." + SEP);
            }
        }
    }
   
    
    
    public static void lapokMutatasa(String kinek, int[] tomb) {
        kiir(kinek + ": \u001B[32m");
        for (int i = 0; i < tomb.length; i++) {
            if(tomb[i] != 0) {
                kiir(tomb[i] + " ");
            }
        }
        kiir("(" + pontSzamitas(tomb) + ")\u001B[0m" + SEP);
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
