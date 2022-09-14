package com.example.project1wordle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    lateinit var guesserBtn: Button // guess or enter button
    lateinit var reset: Button      // reset button
    lateinit var userGuesser: EditText // user input
    lateinit var show1: TextView //copies user input to text
    lateinit var show2: TextView
    lateinit var show3: TextView
    lateinit var check1: TextView // checks if any of the letters are correct
    lateinit var check2: TextView
    lateinit var check3: TextView
    lateinit var reveal: TextView

    var counter = 0 // counter
    var userIn = "" // stores user input
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord() // random words


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userGuesser = findViewById(R.id.userGuess)
        guesserBtn = findViewById(R.id.guessBtn)
        show1 = findViewById(R.id.userShow1)
        show2 = findViewById(R.id.userShow2)
        show3 = findViewById(R.id.userShow3)
        check1 = findViewById(R.id.userCheck1)
        check2 = findViewById(R.id.userCheck2)
        check3 = findViewById(R.id.userCheck3)
        reveal = findViewById(R.id.reveal)
        reset = findViewById(R.id.resetBtn)

        guesserBtn.setOnClickListener {
            counter++
            if (counter == 1) {
                userIn = userGuesser.text.toString()
                show1.text = userIn
                check1.text = checkGuess(userIn)
                userGuesser.text.clear()
            }
            else if (counter == 2) {
                userIn = userGuesser.text.toString()
                show2.text = userIn
                check2.text = checkGuess(userIn)
                userGuesser.text.clear()
            }

            else{
                userIn = userGuesser.text.toString()
                show3.text = userIn
                check3.text = checkGuess(userIn)
                Toast.makeText(this,"You have reachced the limit! "
                    ,Toast.LENGTH_LONG).show()
                reveal.text = wordToGuess
                guesserBtn.isEnabled = false
                userGuesser.text.clear()
            }
            reset.setOnClickListener {
                wordToGuess = FourLetterWordList.getRandomFourLetterWord()
                userGuesser.text.clear()
                counter = 0
                userIn = ""
                show1.text = ""
                check1.text = ""
                show2.text = ""
                check2.text = ""
                show3.text = ""
                check3.text = ""
                reveal.text = ""
                guesserBtn.isEnabled = true
            }

        }

    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}
// gets the random word
object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
}

