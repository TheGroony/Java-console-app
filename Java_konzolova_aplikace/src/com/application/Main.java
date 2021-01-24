package com.application;

public class Main {

    // instance třídy MainMenu obsahující metodu na zobrazení menu
    MainMenu menu = new MainMenu();

    public static void main(String[] args) {
        Main main = new Main();
        main.ShowMenu();
    }

    public void ShowMenu() {
        menu.Show(); //zobrazí menu
    }
}
