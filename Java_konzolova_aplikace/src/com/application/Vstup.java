package com.application;

import java.util.Scanner;

public class Vstup {
    static Scanner scanner = new Scanner(System.in);
    public static String PozadujKrestniJmeno() {
        System.out.println("Zadejde prosím křestní jméno subjektu: ");
        String odpoved = scanner.nextLine();
        //Pokud uživatel napsal slovo, bez mezer a čísel, s podporou českých znaků
        if(odpoved.matches("[a-zA-Zěščřžýáíé_]+")) {
            //Jméno se vrátí
            return odpoved;
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat jméno znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujKrestniJmeno();
        }
        return null;
    }

    public static String PozadujPrijmeni() {
        System.out.println("Nyní prosím zadejde příjmení subjektu: ");
        String odpoved = scanner.nextLine();
        //Pokud uživatel napsal slovo, bez mezer a čísel, s podporou českých znaků
        if(odpoved.matches("[a-zA-Zěščřžýáíé_]+")) {
            //Příjmení se uloží do proměnné
            return odpoved;
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat příjmení znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujPrijmeni();
        }
        return null;
    }

    public static int PozadujVek() {
        System.out.println("Teď prosím napiště věk subjektu: ");
        String odpoved = scanner.nextLine();
        if(odpoved.matches("[0-9]+")) {
            return Integer.parseInt(odpoved);
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat věk znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujVek();
        }
        return 0;
    }

    public static String PozadujNoveKrestniJmeno() {
        System.out.println("Zadejde prosím nové křestní jméno subjektu: ");
        String odpoved = scanner.nextLine();
        //Pokud uživatel napsal slovo, bez mezer a čísel, s podporou českých znaků
        if(odpoved.matches("[a-zA-Zěščřžýáíé_]+")) {
            //Jméno se uloží do proměnné
            return odpoved;
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat jméno znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujNoveKrestniJmeno();
        }
        return null;
    }

    public static String PozadujNovePrijmeni() {
        System.out.println("Nyní prosím zadejde nové příjmení subjektu: ");
        String odpoved = scanner.nextLine();
        //Pokud uživatel napsal slovo, bez mezer a čísel, s podporou českých znaků
        if(odpoved.matches("[a-zA-Zěščřžýáíé_]+")) {
            //Příjmení se uloží do proměnné
            return odpoved;
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat příjmení znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujNovePrijmeni();
        }
        return null;
    }

    public static int PozadujNovyVek() {
        System.out.println("Teď prosím napiště nový věk subjektu: ");
        String odpoved = scanner.nextLine();
        if(odpoved.matches("[0-9]+")) {
            return Integer.parseInt(odpoved);
        }
        //Pokud se zadá špatný vstup, bude muset uživatel zadat věk znovu
        else {
            System.out.println("Byl zadán špatný formát. Zopakujte prosím akci.");
            PozadujNovyVek();
        }
        return 0;
    }
}
