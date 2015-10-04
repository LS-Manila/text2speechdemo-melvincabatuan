# Text-to-Speech App (converts text into Speech)

text2speechdemo-melvincabatuan created by Classroom for GitHub

This assignment illustrates the conversion of text into speech in Android platform. It also introduces a ListView widget.


## Problem:

Design and implement an Android app that takes a text file of ESL Telephone Alphabet phrases as input, then, outputs the phrases in a ListView in which when clicked outputs speech.   

phrases.txt
```text
A is for Apple
B is for Boy
C is for Cat
D is for Dog
E is for East
F is for Five
G is for Goat
H is for House
I is for Ice cream
J is for July
K is for King
L is for Lemon
M is for Money
N is for Number
O is for Open
P is for People
Q is for Queen
R is for Red
S is for Summer
T is for Time
U is for Uniform
V is for Visa
W is for Woman
X is for X-ray
Y is for Yellow
Z is for Zebra
```


## Accept

To accept the assignment, click the following URL:

https://classroom.github.com/assignment-invitations/da958c3198ce4ba71af0e72780243aed

## Sample Solution:

https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-melvincabatuan

## Keypoints:

Initialize the TextToSpeech Object:
```Java
private void initializeTTS() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttsLoaded = true;
            }
        });
    }
```

Load the phrases from text file:
```Java
   private void loadPhrases(){
        ESLPhrases = new ArrayList<String>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.phrases));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            ESLPhrases.add(line);
        }
        Log.d(DEBUG_TAG, "ESLPhrases.size() = " + ESLPhrases.size() );
    }
  ```
  
Set-up the ListView:
```Java
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
```

Handle the clicks:
```Java
    private void handleClick(int index) {
        String text = ESLPhrases.get(index);
        if (ttsLoaded) {
            tts.setSpeechRate(0.6f); // 1.0f - Normal speed
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
```


## Submission Procedure with Git: 

```shell
$ cd /path/to/your/android/app/
$ git init
$ git add –all
$ git commit -m "your message, e.x. Assignment 1 submission"
$ git remote add origin <Assignment link copied from assignment github, e.x. https://github.com/DeLaSalleUniversity-Manila/secondactivityassignment-melvincabatuan.git>
$ git push -u origin master
<then Enter Username and Password>
```

Sample:

https://gist.github.com/melvincabatuan/8ad17ab1b1e812f54e55 

## Screenshots:

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-melvincabatuan/blob/master/device-2015-10-04-150554.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-melvincabatuan/blob/master/device-2015-10-04-150622.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-melvincabatuan/blob/master/device-2015-10-04-151534.png)


"*Strive not to be a success, but rather to be of value.*" - Albert Einstein
