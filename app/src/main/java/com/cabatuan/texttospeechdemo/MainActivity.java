package com.cabatuan.texttospeechdemo;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public final static String DEBUG_TAG = "MainActivity";
    private TextToSpeech tts;              // Text-to-Speech engine
    private ArrayList<String> ESLPhrases;     // Phrases from text file
    private boolean ttsLoaded = false;   // true when TTS engine is loaded

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mkc
        initializeTTS();
        loadPhrases();
        setupListView();
    }

    private void initializeTTS() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttsLoaded = true;
            }
        });
    }


    private void loadPhrases(){
        ESLPhrases = new ArrayList<String>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.phrases));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            ESLPhrases.add(line);
        }
        Log.d(DEBUG_TAG, "ESLPhrases.size() = " + ESLPhrases.size() );
    }

    private void setupListView(){
        ListView lv = (ListView) findViewById(R.id.alphabetlistview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.my_list_item, ESLPhrases);
        lv.setAdapter(adapter);

        // Set up event listening for clicks on the list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                handleClick(index);
            }
        });
    }

    private void handleClick(int index) {
        String text = ESLPhrases.get(index);
        if (ttsLoaded) {
            tts.setSpeechRate(0.6f);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}