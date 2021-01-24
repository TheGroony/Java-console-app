package com.application;

import java.util.Scanner;

public class Ad3 {
    Scanner scanner = new Scanner(System.in);
    String krestni_jmeno;
    String nove_Krestni_Jmeno;
    String prijmeni;
    String nove_Prijmeni;
    int vek;
    int novy_Vek;

    public void Start() {
        // Požádání, validace křestního jména a zapsání do proměnné
        krestni_jmeno = Vstup.PozadujKrestniJmeno();
        // Požádání, validace příjmení a zapsání do proměnné
        prijmeni = Vstup.PozadujPrijmeni();
        // Požádání, validace věku a zapsání do proměnné
        vek = Vstup.PozadujVek();
        // Shrnutí uživatelského vstupu
        Validace();
    }

    private void Validace() { // Pokud se zadaný subjekt shoduje se subjektem v databázi, uživatel ho bude moct upravit
        if(Database.FindRecord(krestni_jmeno, prijmeni, vek)) {
            System.out.println("BYL NALEZEN TENTO SUBJEKT:\n" +
                    "Křestní jméno: " + krestni_jmeno
                    + "\nPříjmení: " + prijmeni
                    + "\nVěk: " + vek
                    + "\nPřejete si tento subjekt upravit? Y/N\n");
            String odpoved = scanner.nextLine().toLowerCase();
            switch (odpoved) {
                case "y" -> {
                    Uprava(); // Přejte se na menu úpravy
                }
                case "n" -> {
                    System.out.println("V tom případě otevírám hlavní menu...");
                    MainMenu menu = new MainMenu();
                    menu.Show();
                }
                default -> {
                    System.out.println("Zadán neplatný příkaz, zkuste to znovu.");
                    Validace();
                }
            }
        }
        else { //Pokud se v databázi nic takového nenachází, přejde se zpět do menu
            System.out.println("Zadaný subjekt se v databázi nenachází nebo je napsaný špatně.");
            Database.BackToMenu();
        }

    }

    private void Uprava() { //Zde uživatel postupně zadá nové informace
        nove_Krestni_Jmeno = Vstup.PozadujNoveKrestniJmeno();
        nove_Prijmeni = Vstup.PozadujPrijmeni();
        novy_Vek = Vstup.PozadujNovyVek();
        ValidaceUpravy();
    }

    // shrnutí, kde uživatel vidí staré i nové informace
    private void ValidaceUpravy() {
            System.out.println("SHRNUTÍ:\n" +
                    "Staré křestní jméno: " + krestni_jmeno
                    + "\nNOVÉ KŘESTNÍ JMÉNO: " + nove_Krestni_Jmeno
                    + "\nStaré příjmení: " + prijmeni
                    + "\nNOVÉ PŘÍJMENÍ: " + nove_Prijmeni
                    + "\nStarý věk: " + vek
                    + "\nNOVÝ VĚK: " + novy_Vek
                    + "\nPřejete si tento subjekt upravit následujícím způsobem? Y/N\n");
            String odpoved = scanner.nextLine().toLowerCase();
            switch (odpoved) {
                case "y" -> {
                    Database.Edit(krestni_jmeno, prijmeni, vek, nove_Krestni_Jmeno, nove_Prijmeni, novy_Vek); // metoda upravující záznam
                }
                case "n" -> {
                    System.out.println("V tom případě otevírám hlavní menu...");
                    MainMenu menu = new MainMenu();
                    menu.Show();
                }
                default -> {
                    System.out.println("Zadán neplatný příkaz, zkuste to znovu.");
                    ValidaceUpravy();
                }
            }

    }
}
