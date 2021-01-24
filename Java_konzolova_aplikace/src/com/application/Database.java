package com.application;

import java.sql.*;
import java.util.Scanner;

public class Database {


    // Přidá uživatele do databáze na základě vstupů
    public static void Add(String first_Name, String last_Name, int age) {
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro přidání uživatele
                PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO users (first_name, last_name, age) VALUES (?,?,?)");
                )
        {
            //ochranné prvky
            dotaz.setString(1, first_Name);
            dotaz.setString(2, last_Name);
            dotaz.setString(3, String.valueOf(age));
            //Přidání do db + zapsání ovlivněných řádků
            int ovlivnenoRadku = dotaz.executeUpdate();
            System.out.println("Data byla úspěšně zapsána. Celkem upraveno řádků: " + ovlivnenoRadku);
            //Návrat do menu pomocí metody obsahující instanci třídy MainMenu
            BackToMenu();
        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        }
    }

    //Funkce která po stisknutí klávesy enter navrátí uživatele do hlavního menu
    public static void BackToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Po stisknutí tlačítka enter se vrátíte do menu.");
        try {
            System.in.read();
            System.out.println("Vracím se do menu...");
            MainMenu menu = new MainMenu();
            menu.Show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        }

        //Ukáže všechny aktuální záznamy
    public static void ShowAllRecords() {
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro přidání uživatele
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM users");
                ResultSet vysledky = dotaz.executeQuery();
        ) {
            //Záznamy se načtou z databáze do proměnných, které se poté zobrazí uživateli
            while (vysledky.next()) {
                String jmeno = vysledky.getString("first_name");
                String prijmeni = vysledky.getString("last_name");
                int vek = vysledky.getInt("age");
                System.out.println("\nKrestni jmeno: " + jmeno + "\nPrijmeni: " + prijmeni + "\nVek: " + vek + "\n");
            }
            BackToMenu();
        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        }
    }

    //vrátí true/false na základě pravdivosti, jestli se v databází nachází konkrétní záznam, který je uveden v argumentech
    public static boolean FindRecord(String first_Name, String last_Name, int age) {
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro nalezení požadovaného subjektu
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM users WHERE first_name=? AND last_name=? AND age=?");
        ) {
            //ochrana proti zadání SQL dotazů
            dotaz.setString(1, first_Name);
            dotaz.setString(2, last_Name);
            dotaz.setString(3, String.valueOf(age));
            try(
                    ResultSet vysledek = dotaz.executeQuery();
            ) {
                while(vysledek.next()) {
                    String foundFirst_name = vysledek.getString("first_name");
                    String foundLast_name = vysledek.getString("last_name");
                    int foundAge = vysledek.getInt("age");
                    //pokud se údaje shodují, vrátí se true
                    if(foundFirst_name.equals(first_Name) && foundLast_name.equals(last_Name) && foundAge == age)
                        return true;
                    else return false;
                }
            }

        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        } return false;
    }

    //Smaže zadaný záznam z databáze
    public static void Remove(String first_Name, String last_Name, int age) {
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro nalezení požadovaného subjektu
                PreparedStatement dotaz = spojeni.prepareStatement("DELETE FROM users WHERE first_name=? AND last_name=? AND age=?");
                ) {
            dotaz.setString(1, first_Name);
            dotaz.setString(2, last_Name);
            dotaz.setString(3, String.valueOf(age));
            int ovlivnenoRadku = dotaz.executeUpdate();
            System.out.println("Data byla úspěšně smazána. Celkem upraveno řádků: " + ovlivnenoRadku);
            //Návrat do menu pomocí metody obsahující instanci třídy MainMenu
            BackToMenu();
        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        }
    }

    //Upraví zadaný záznam pomocí nově poskytnutých informací
    public static void Edit(String first_Name, String last_Name, int age, String new_First_Name, String new_Last_Name, int new_Age) {
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                PreparedStatement dotaz = spojeni.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, age = ? WHERE first_name = ? AND last_name = ? AND age = ?")
                ) {
            dotaz.setString(1, new_First_Name);
            dotaz.setString(2, new_Last_Name);
            dotaz.setString(3, String.valueOf(new_Age));
            dotaz.setString(4, first_Name);
            dotaz.setString(5, last_Name);
            dotaz.setString(6, String.valueOf(age));
            int ovlivnenoRadku = dotaz.executeUpdate();
            System.out.println("Data byla úspěšně změněna. Celkem upraveno řádků: " + ovlivnenoRadku);
            //Návrat do menu pomocí metody obsahující instanci třídy MainMenu
            BackToMenu();

        }
        catch (SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        }
    }

    //Vrátí string údajů, které se zapíší do textového souboru, metoda oproti podobné metodě neobsahuje nechtěné volání na menu na konci metody
    public static String ReturnRecords() {
        String text = "";
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro přidání uživatele
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM users");
                ResultSet vysledky = dotaz.executeQuery();
        ) {
            while (vysledky.next()) {
                String jmeno = vysledky.getString("first_name");
                String prijmeni = vysledky.getString("last_name");
                int vek = vysledky.getInt("age");
                text += "\nKrestni jmeno: " + jmeno + "\nPrijmeni: " + prijmeni + "\nVek: " + vek + "\n";
            }
            return text;

        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        } return null;
    }

    //Poskytnuté soubory se nahrají do databáze, opět podobná metoda, ale nevrací počet ovlivněných řádků a návrat do menu.
    public static void LoadToDb(String first_Name, String last_Name, int age) {
        try(
                //Navázání spojení s databází
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/strzelecki_db?serverTimezone=UTC", "root", "1234");
                //SQL dotaz pro přidání uživatele
                PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO users (first_name, last_name, age) VALUES (?,?,?)");
        )
        {
            //ochranné prvky
            dotaz.setString(1, first_Name);
            dotaz.setString(2, last_Name);
            dotaz.setString(3, String.valueOf(age));
            //Přidání do db
            dotaz.executeUpdate();
        }
        catch(SQLException exception) {
            System.out.println("Nastala chyba.");
            exception.printStackTrace();
        }
    }
}

