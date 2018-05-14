package com.metalsack.demouno


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.metalsack.demouno.network.apiResponse.ExpertListResponse
import com.metalsack.demouno.network.models.Result
import com.metalsack.demouno.network.apiHandlers.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.EventBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    public val TAG="MainActivity"
    private lateinit var btnToast: Button
    private lateinit var txtCount: TextView

    val animals: ArrayList<String> = ArrayList()
    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary",
            "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York",
            "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnToast = findViewById<Button>(R.id.btnToast)
        txtCount=findViewById<TextView>(R.id.txtCount)
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, array)
        //listView.setAdapter(adapter)
        animals.addAll(array)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= Recycler_Adapter(animals, this);
        btnRandom.setOnClickListener(View.OnClickListener {
            getRecommendedUser()
        })
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Hello From Kotlin", Toast.LENGTH_SHORT)
        myToast.show()
    }
    fun countMe (view: View) {
        val countString = txtCount.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        txtCount.text = count.toString();
    }
    fun randomMe (view: View) {
        val randomIntent = Intent(this, Main2Activity::class.java)
        val countString = txtCount.text.toString()
        val count = Integer.parseInt(countString)
        randomIntent.putExtra(Main2Activity.TOTAL_COUNT, count)
        startActivity(randomIntent)
    }

    fun getRecommendedUser(){
        val apiService=ApiClient.getRecommendedUsers()
        apiService.getRecommendedUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleUsers,this::handleError)
    }
    fun networkcall(){
        //val apiService = ApiService.create()
        val apiService = ApiClient.search()
        apiService.search("Lagos", "Java")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError)

        /*val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)*/

        /*requestInterface.getData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError)*/

    }

    fun handleUsers(response : ExpertListResponse) {
        Toast.makeText(this,"Response Successfully got",Toast.LENGTH_SHORT).show()
        Log.e(TAG, response.msg.toString())
        Log.e(TAG, response.expertInfo.size.toString())
    }
    private fun handleResponse(androidList: Result) {
        Toast.makeText(this,"Response Successfully got",Toast.LENGTH_SHORT).show()
        Log.e(TAG, androidList.total_count.toString())
    }

    private fun handleError(error: Throwable) {
        Log.e(TAG, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    public class MessageEvent {
        lateinit var Name: String
        lateinit var Age: String
        constructor()
        constructor(name: String){
            this.Name=name
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onMessageEvent(event: MainActivity.MessageEvent) {
        val myToast = Toast.makeText(this@MainActivity,event.Name, Toast.LENGTH_SHORT)
        myToast.show()
    };
}

