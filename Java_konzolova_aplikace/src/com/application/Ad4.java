package com.application;

import java.util.Scanner;

public class Ad4 {
    Scanner scanner = new Scanner(System.in);
    String krestni_jmeno;
    String prijmeni;
    int vek;

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
    private void Validace() {
        if(Database.FindRecord(krestni_jmeno, prijmeni, vek)) {
            System.out.println("BYL NALEZEN TENTO SUBJEKT:\n" +
                    "Křestní jméno: " + krestni_jmeno
                    + "\nPříjmení: " + prijmeni
                    + "\nVěk: " + vek
                    + "\nPřejete si tento subjekt smazat z databáze? Y/N\n");
            String odpoved = scanner.nextLine().toLowerCase();
            switch (odpoved) {
                case "y" -> {System.out.println("Mažu subjekt z databáze...");
                    //Smaže informace do databáze
                    Database.Remove(krestni_jmeno, prijmeni, vek);
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
        else {
            System.out.println("Zadaný subjekt se v databázi nenachází nebo je napsaný špatně.");
            Database.BackToMenu();
        }

    }
}
