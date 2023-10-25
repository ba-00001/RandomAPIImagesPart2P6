package com.example.randomimagespart2p6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

class MainActivity : AppCompatActivity() {
    var petImageURL = ""
    var textURL1 = ""
    var textURL2 = ""

    lateinit var imageView: ImageView //late initialization
    lateinit var textView1: TextView //late initialization
    lateinit var textView2: TextView //late initialization

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.AnimalPhoto1)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)

        getAPIImageURL()
        Log.d("petImageURL", "pet image URL set")

        val button = findViewById<Button>(R.id.getPhotoButton)

        getNextImage(button, imageView, textView1, textView2)
    }//end of onCreate

    /*

    //doG

        private fun getAPIImageURL() {
            val client = AsyncHttpClient()

            client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JSON?) {
                    val d = Log.d("Dog", "response successful")
                    petImageURL = json?.jsonObject?.getString("message").toString() // get the url
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: okhttp3.Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    response?.toString()?.let { Log.d("Dog Error", it) }
                }


            }]
        }//end of getDogImageURL


        private fun getNextImage(button: Button, imageView: ImageView) {
            button.setOnClickListener {
                getAPIImageURL()

                Glide.with(this)
                    .load(petImageURL)
                    .fitCenter()
                    .into(imageView)
            }
        }//end of getNextImage



     */

    //CAT
    private fun getAPIImageURL() {
        val client = AsyncHttpClient()

        client["https://api.thecatapi.com/v1/images/search", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JSON?) {
                val d = Log.d("Dog", "response successful")
                var resultsJSON = json?.jsonArray?.getJSONObject(0)
                petImageURL = resultsJSON?.getString("url").toString() // get the url
                textURL1 = resultsJSON?.getInt("width").toString() // get the url
                textURL2 = resultsJSON?.getInt("height").toString() // get the url
                //petImageURL = json?.jsonObject?.getString("url").toString() // get the url
            }

            override fun onFailure(
                statusCode: Int,
                headers: okhttp3.Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                response?.toString()?.let { Log.d("Dog Error", it) }
            }


        }]
    }//end of getPetImageURLCAT

    private fun getNextImage(button: Button, imageView2: ImageView,
                             textView1: TextView, textView2: TextView
    ) {
        button.setOnClickListener {
            getAPIImageURL()

            Glide.with(this)
                .load(petImageURL)
                .fitCenter()
                .into(imageView2)

            // Set the text for textView1 and textView2 here using textURL1 and textURL2
            textView1.text = "Width: $textURL1"
            textView2.text = "Height: $textURL2"
        }
    }//end of getNextImageCAT





}//end of MainActivity
