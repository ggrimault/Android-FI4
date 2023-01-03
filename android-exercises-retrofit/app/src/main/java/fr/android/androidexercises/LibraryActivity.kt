package fr.android.androidexercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        /*CoroutineScope(Dispatchers.Main).launch {
            var profile: JSONObject? = null
            withContext(Dispatchers.IO) {
                profile = JSONObject(client.newCall(request).execute().body?.string())
            }
            profile?.run {
                messageTextView.text = getString(R.string.hello, get("name"))
            }
        }*/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // Plant logger cf. Android Timber
        Timber.plant(Timber.DebugTree())

        // TODO build Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr/books/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // TODO create a service
        val service = retrofit.create(HenriPotierService::class.java)

        // TODO listBooks()
        val listBooks = service.getListBooks()
        /*viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.getListBooks()
            }
            state.postValue(LibraryState(books, false))
        }*/

        // TODO enqueue call and display book title
        listBooks.forEach { book ->
            println(book.title)
        }

        // TODO log books
        Timber.d(listBooks.toString())

        // TODO display book as a list

    }

}
