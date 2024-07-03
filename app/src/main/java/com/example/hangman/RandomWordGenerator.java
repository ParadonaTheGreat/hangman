package com.example.hangman;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
public class RandomWordGenerator {
    public static String getRandWord(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("allWords.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int random = (int)(Math.random()*69898);
        for (int i = 0; i<=random; i++){
            reader.readLine();
        }
        return reader.readLine();
    }
}
