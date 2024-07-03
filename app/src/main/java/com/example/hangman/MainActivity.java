package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity{

    ImageView picture;
    TextView actualWord;
    TextView attemptedLetters;
    EditText enteredText;
    String word;
    int currentPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        picture = findViewById(R.id.imageView);
        actualWord = findViewById(R.id.actualWord);
        attemptedLetters = findViewById(R.id.attemptedLetters);
        enteredText = findViewById(R.id.enteredText);
        picture.setImageResource(R.drawable.hangman1);
        currentPic = 2;
        actualWord.setText("_");
        try {
            word = RandomWordGenerator.getRandWord(this);
        } catch (Exception e) {
            attemptedLetters.setText(e.toString());
            word = "abc";
        }
        System.out.println(word);
        for (int i = 0; i<word.length()-1;i++){
            actualWord.setText(actualWord.getText()+ " ");
            actualWord.setText(actualWord.getText()+ "_");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submit(View view) {
        boolean alreadyThere = false;
        char guess = enteredText.getText().toString().charAt(0);
        
        for (int i = 0; i<attemptedLetters.getText().length(); i++){
            if (attemptedLetters.getText().charAt(i) == guess){
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            attemptedLetters.setText(attemptedLetters.getText() + "" + guess + ", ");
            boolean changed = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    changed = true;
                    String dashes = actualWord.getText().toString();
                    StringBuilder modified = new StringBuilder(dashes);
                    modified.setCharAt(i * 2, guess);
                    dashes = modified.toString();
                    actualWord.setText(dashes);
                }
            }
            boolean done = true;
            for (int i = 0; i < actualWord.getText().length(); i++) {
                if (actualWord.getText().charAt(i) == '_') {
                    done = false;
                }
            }
            if (done) {
                Intent intent = new Intent(this, GameOverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("result", 0);
                intent.putExtra("word", word);
                this.startActivityForResult(intent, 1, null);
            }

            if (!changed) {
                switch (currentPic) {
                    case 2:
                        picture.setImageResource(R.drawable.hangman2);
                        currentPic++;
                        break;
                    case 3:
                        picture.setImageResource(R.drawable.hangman3);
                        currentPic++;
                        break;
                    case 4:
                        picture.setImageResource(R.drawable.hangman4);
                        currentPic++;
                        break;
                    case 5:
                        picture.setImageResource(R.drawable.hangman5);
                        currentPic++;
                        break;
                    case 6:
                        picture.setImageResource(R.drawable.hangman6);
                        currentPic++;
                        break;
                    case 7:
                        picture.setImageResource(R.drawable.hangman7);
                        Intent intent = new Intent(this, GameOverActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("result", 1);
                        intent.putExtra("word", word);
                        this.startActivityForResult(intent, 1, null);
                }
            }
        }
    }
}