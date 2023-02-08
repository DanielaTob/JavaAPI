package com.project.apicat;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;

public class CatsService {

    public static void viewCats() throws IOException {

        //1. Trae los datos de la api
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String viewJson = response.body().string();

        //cortar los corchetes del json
        viewJson = viewJson.substring(1, viewJson.length());
        viewJson = viewJson.substring(0, viewJson.length() - 1);

        //crear un objeto de la clase Gson
        Gson gson = new Gson();

        //convertir respuesta de la api a un objeto de tipo cats
        Cats cats = gson.fromJson(viewJson, Cats.class);

        //Redimensionar la imagen
        Image image = null;
        try{
            URL url = new URL(cats.getUrl());
            image = ImageIO.read(url);

            ImageIcon wallpaperCat = new ImageIcon(image);


            if (wallpaperCat.getIconWidth() > 800  || wallpaperCat.getIconHeight()>1200){
                //Redimensionar
                Image wallpaper = wallpaperCat.getImage();
                Image modified = wallpaper.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
                wallpaperCat = new ImageIcon(modified);
            }

            //Menu
            String menu = "Options: \n"
                    + "1. See another image\n"
                    + "2. Fav\n"
                    + "3. Back \n";

            String[] buttons = {
                    "View other image", "Fav", "Back"
            };
            String id_cat = String.valueOf(cats.getUrl());
            String option = (String) JOptionPane.showInputDialog(null, menu, id_cat,
                    JOptionPane.INFORMATION_MESSAGE, wallpaperCat, buttons, buttons[0]);

            int selection = -1;

            for (int i = 0; i < buttons.length; i++){
                if (option.equals(buttons[i])){
                    selection = i;
                }
            }

            switch (selection){
                case 0:
                    viewCats();
                    break;
                case 1:
                    favoriteCat(cats);
                    break;
                default:
                    break;
            }


        }catch(IOException e){
            System.out.println(e);
        }

    }



    public static void favoriteCat(Cats cats){
        try{

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\": \""+ cats.getId()+"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cats.getApikey())
                    .build();
            Response response = client.newCall(request).execute();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void viewFavoriteCats(String apikey){

        try{

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .get()
                    .addHeader("x-api-key", apikey)
                    .build();
            Response response = client.newCall(request).execute();

            //Guardamos el string como una respuesta
            String viewJson = response.body().string();

            //creamos objeto json
            Gson gson = new Gson();

            CatsFav[] arrayCats = gson.fromJson(viewJson, CatsFav[].class);


            if(arrayCats.length > 0){
                int min = 1;
                int max = arrayCats.length;
                int aleatory = (int) (Math.random() * ((max-min) -1)) + min;
                int index = aleatory-1;

                CatsFav catsFav = arrayCats[index];

                //Interface
                Image image = null;
                try{
                    URL url = new URL(catsFav.image.getUrl());
                    image = ImageIO.read(url);

                    ImageIcon wallpaperCat = new ImageIcon(image);


                    if (wallpaperCat.getIconWidth() > 800  || wallpaperCat.getIconHeight()>1200){
                        //Redimensionar
                        Image wallpaper = wallpaperCat.getImage();
                        Image modified = wallpaper.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
                        wallpaperCat = new ImageIcon(modified);
                    }

                    //Menu
                    String menu = "Options: \n"
                            + "1. See another image\n"
                            + "2. Delete Fav\n"
                            + "3. Back \n";

                    String[] buttons = {
                            "View other image", "Delete Fav", "Back"
                    };
                    String id_cat = catsFav.getId();
                    String option = (String) JOptionPane.showInputDialog(null, menu, id_cat,
                            JOptionPane.INFORMATION_MESSAGE, wallpaperCat, buttons, buttons[0]);

                    int selection = -1;

                    for (int i = 0; i < buttons.length; i++){
                        if (option.equals(buttons[i])){
                            selection = i;
                        }
                    }

                    switch (selection){
                        case 0:
                            viewFavoriteCats(apikey);
                            break;
                        case 1:
                            deleteFav(catsFav);
                            break;
                        default:
                            break;
                    }


                }catch(IOException e){
                    System.out.println(e);
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static void deleteFav(CatsFav catsFav){


        try{

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+catsFav.getId()+"")
                    .method("DELETE", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", catsFav.getApikey())
                    .build();
            Response response = client.newCall(request).execute();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
