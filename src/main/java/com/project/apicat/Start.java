package com.project.apicat;

import javax.swing.*;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        int optionMenu = -1;
        String[] buttons = {
                "1. View Cats",
                "2. View Favorite Cats",
                "3. Exit"
        };

        do {

            //Menu principal
            String option = (String) JOptionPane.showInputDialog(null, "Java Cats", "Principal Menu",
                    JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            //Validacion de que opcion seleccinó el usuario
            for (int i = 0; i < buttons.length; i++){
                if (option.equals(buttons[i])){
                    optionMenu = i;
                }
            }

            switch (optionMenu){
                case 0:
                    CatsService.viewCats();
                    break;
                case 1:
                    Cats cats = new Cats();
                    CatsService.viewFavoriteCats(cats.getApikey());
                    break;
                default:
                    break;

            }

        }while(optionMenu != 1);
    }
}
