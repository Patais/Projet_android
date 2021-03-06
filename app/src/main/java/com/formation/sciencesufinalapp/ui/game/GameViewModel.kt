import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel(){
    private var _score = 0
    val score: Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount

    private var _allWordsList = mutableListOf(
        "Siamois","Persan", "Angora","Main coon","sacré de Birmanie", "Sphinx", "Bleu", "Bengal",
        "Abyssin", "Chartreux", "Manx", "Ocicat", "Somali", "Toyger", "Ragdoll", "Chausie",
        "Savannah", "American Curl", "American Shorthair", "American Wirehair", "Australian Mist",
        "Balinais", "British Longhair", "British Shorthair", "Burmese", "Burmilla", "Ceylan",
        "Chantilly-Tiffany", "Chat bombay", "Chat européen", "Cornish Rex", "Cymric", "Devon Rex",
        "Exotic shorthair", "Himalayen", "Javanais", "Korat", "LaPerm", "Lykoi", "Mau égyptien",
        "Mekong bobtail", "Munchkin", "Nebelung", "Neva Masquerade", "Norvégien", "Pixie-bob",
        "Oriental shorthair", "Scottish fold", "Selkirk Rex", "Sibérien", "Singapura", "Snowshoe",
        "Thaï", "Tonkinois", "Turc de Van", "Anatoli", "Asian", "Bobtail des kouriles", "Bombay",
        "Bobtail Japonais", "Donskoy", "Mandarin", "Minskin", "Ojos Arules", "Peterald", "Safari"
    )
    val allWordsList: MutableList<String>
        get() = _allWordsList

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private val _savedGames = MutableLiveData<MutableList<Map<String,String>>>(mutableListOf())
    val savedGames: LiveData<MutableList<Map<String,String>>>
        get() = _savedGames

    // List of words used in the game
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    fun saveGame(newscore : Map<String,String>){
        _savedGames.value!!.add(newscore)
    }

    // Updates currentWord and currentScrambledWord with the next word.
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    // Re-initializes the game data to restart the game
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }

    // Increases the game score if the player's word is correct.
    private fun increaseScore() {
        _score += 1
    }

    /*
    * Returns true if the player word is correct.
    * Increases the score accordingly.
    */
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    // Returns true if the current word count is less than MAX_NO_OF_WORDS
    fun nextWord(next: Boolean = true): Boolean {
        return if (_currentWordCount < 15) {
            if (next) {
                getNextWord()
            }
            true
        } else false
    }
}