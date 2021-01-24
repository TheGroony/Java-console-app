package com.application;

import java.util.Scanner;

public class MainMenu {

    Ad1 vytvorZaznam = new Ad1();
    Ad2 zobrazZaznamy = new Ad2();
    Ad3 upravZaznam = new Ad3();
    Ad4 vymazZaznam = new Ad4();
    Ad5 StahnoutZaznamy = new Ad5();
    Ad6 NahratZaznamy = new Ad6();
    Ad7 ukoncitProgram = new Ad7();

    public void Show() {
        // Vypíše možné funkce programu, text je zde zalomený pro lepší orientaci
        System.out.print("Hezký den, přejete si:\n\n" +
                         "1)Vytvořit záznam\n" +
                         "2)Zobrazit všechny záznamy\n" +
                         "3)Upravit záznam\n" +
                         "4)Smazat záznam\n" +
                         "5)Stáhnout všechny záznamy\n" +
                         "6)Nahrát záznamy ze souboru\n" +
                         "7)Ukončit program\n\n");
        // Zavolá metodu, která bude pracovat se vstupem uživatele v menu
        Interaction();
    }

    void Interaction() {
        Scanner scanner = new Scanner(System.in);
        // Požádá uživatele o vstup
        System.out.print("Vaše volba: ");
        // Pokud uživatel vložil skutečně číslo, ne nic jiného
        if(scanner.hasNextInt()) {
            int volba = scanner.nextInt();
            // Pokud uživatel vložil číslo v požadovaném rozmezí
            if(volba > 0 && volba < 8) {
                // Pokud je vše v pořádku, zavolá se funkce, která zavolá požadovanou akci
                Akce(volba);
            }
            // Pokud uživatel vložil špatný vstup, vyhodí se mu chybová hláška a bude o vstup požádán znovu
            else {
                ChybovaHlaska();
                Interaction();
            }
        }
        else {
            ChybovaHlaska();
            Interaction();
        }
    }

     void ChybovaHlaska() {
        System.out.print("Zadán nepodporovaný nebo špatný vstup, zopakujte prosím akci.\n");
    }

     void Akce(int volba) {
         //Zavolá požadovanou metodu skrze instanci třídy
         switch (volba) {
             case 1 -> {
                 System.out.println("Nyní vytvoříte nový záznam.");
                 vytvorZaznam.Start();
             }
             case 2 -> {
                 System.out.println("Vypisuji všechny záznamy z databáze...");
                 zobrazZaznamy.Start();
             }
             case 3 -> {
                 System.out.println("Nyní upravíte libovolný záznam.");
                 upravZaznam.Start();
             }
             case 4 -> {
                 System.out.println("Nyní smažete libovolný záznam.");
                 vymazZaznam.Start();
             }
             case 5 -> {
                 StahnoutZaznamy.Start();
             }
             case 6 -> {
                 NahratZaznamy.Start();
             }
             case 7 -> {
                 System.out.println("Vypínám program...");
                 ukoncitProgram.Start();
             }
         }
    }
}
