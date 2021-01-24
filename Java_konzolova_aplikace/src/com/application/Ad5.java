package com.application;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Ad5 {
    public void Start() { //Uživatel zadá jméno textového souboru, který se vytvoří
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadejte písemný název souboru bez přípony a diakritiky, který chcete vytvořit a zapsat do něj údaje z databáze: ");
        String odpoved = scanner.nextLine();
        if(odpoved.matches("[a-zA-Z_]+")) {
            String nazev = odpoved + ".txt";
            CreateFile(nazev);
        }
        else {
            System.out.println("Byl zadán špatný název souboru, zopakujte prosím akci");
            Start();
        }
    }

    private void CreateFile(String nazev) {
        try {
            File soubor = new File(nazev);
            if(!soubor.exists()) { //Soubor se vytvoří, jen pokud ještě neexistuje
                if(soubor.createNewFile()) {
                    System.out.println("Soubor " + nazev + " úspěšně vytvořen.");
                    System.out.println("Nahrávám data z databáze do souboru...");
                    FileWriter zapsat = new FileWriter(nazev);
                    zapsat.write(Database.ReturnRecords()); // metoda databáze vracející subjekty ve String formě
                    zapsat.close();
                    System.out.println("Data byla úspěšně nahrána.");
                    Database.BackToMenu();
                }
            }
            else {
                System.out.println("Soubor se stejným názvem již existuje. Zadejte prosím název jiný.");
                Start();
            }
        }
        catch(Exception e) {
            System.out.println("Nastala chyba.");
            e.printStackTrace();
        }

    }
}
