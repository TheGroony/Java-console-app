package com.application;

import java.io.File;
import java.util.Scanner;

public class Ad6 {
    public void Start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadejte název textového souboru, ze kterého chcete nahrát data do databáze:");
        String odpoved = scanner.nextLine();
        //Název souboru nesmí obsahovat žádné speciální znaky ani diakritiku
        if(odpoved.matches("[a-zA-Z_]+")) {
            String nazev = odpoved + ".txt";
            //Metoda, která nahraje subjekty z databáze do souboru
            LoadFile(nazev);
        }
        else {
            System.out.println("Byl zadán špatný název souboru, zopakujte prosím akci");
            Start();
        }
    }

    private void LoadFile(String nazev) {
        try {
            File file = new File(nazev);
            // Pokud soubor existuje, spustí se akce
            if(file.exists()) {
                // čtení souboru
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String first_Name;
                    String last_Name;
                    int age;
                        String data = scanner.nextLine();
                        // Z každého řádku se vystřihne jméno, příjmení a věk, které se poté nahrají do proměnné
                        if(data.contains("jmeno") && data.contains("Prijmeni") && data.contains("Vek")){
                            first_Name = data.substring(data.lastIndexOf("jmeno") + 7, data.lastIndexOf("Prijmeni"));
                            last_Name = data.substring(data.lastIndexOf("Prijmeni") + 10, data.lastIndexOf("Vek"));
                            age = Integer.parseInt(data.substring(data.lastIndexOf("Vek") + 5, data.lastIndexOf("Vek") + 7));
                            //Subjekty ze souboru se do databáze zapíšou jen tehdy, pokud v databázi ještě nejsou, aby se předešlo duplikátům
                            if(!Database.FindRecord(first_Name, last_Name, age)) {
                                Database.LoadToDb(first_Name, last_Name, age);
                            }
                           else {
                                System.out.println("Subjekt " + first_Name + last_Name + "se již v databázi nachází, přechází na další...");
                            }
                        }
                    }
                System.out.println("Nahrání bylo úspěšné");
                Database.BackToMenu();
                }
            else {
                System.out.println("Tento soubor neexistuje.");
                Start();
            }
        }
        catch(Exception e) {
            System.out.println("Nastala chyba.");
            e.printStackTrace();
        }
    }
}
