package com.ildango.capstone

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.ildango.capstone.mywishlist.MyWishListActivity
import com.ildango.capstone.databinding.ActivityMainBinding
import com.ildango.capstone.myalarmlist.MyAlarmListActivity
import com.ildango.capstone.myinfo.GetInfoActivity
import com.ildango.capstone.result.ResultActivity

class MainActivity : AppCompatActivity(), BottomSheetClickListener {

    private var _binding: ActivityMainBinding?= null
    private val binding get() = _binding!!
    private val bottomSheet = BottomSheetFragment()

    private var waitTime = 0L

    companion object Constants {
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkOnBoarding()
    }

    override fun onStart() {
        super.onStart()
        setLogoImage()
        setSearchView()
        setBottomSheet()
        setNotificationTrigger()
    }

    private fun setNotificationTrigger(){
        binding.btnNotificationTrigger.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification(){
        val title = "중세시대"
        val message = "아이폰 13의 새로운 가격을 확인하세요!"

        val intent = Intent(this, ProductDetailActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        val notificationID = Random.nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val pendingIntent = getActivity(this,0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_HIGH).apply {
            description = "Channel Description"
            enableLights(true)
        }
        notificationManager.createNotificationChannel(channel)
    }



    private fun checkOnBoarding() {
        val pref:SharedPreferences = getSharedPreferences("Information", MODE_PRIVATE)
        val isFirst = pref.getBoolean("isFirst", false)

        if(!isFirst) {
            val intent = Intent(this@MainActivity, OnBoardingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setLogoImage() {
        Glide.with(this)
            .load(R.raw.logo)
            .into(binding.imgLogo)
    }

    private fun setBottomSheet() {
        binding.viewUnderSearchBar.setOnTouchListener(object:OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeTop() {
                bottomSheet.show(supportFragmentManager, BottomSheetFragment.TAG)
            }
        })
    }

    private fun setSearchView() {
        binding.searchView.setQuery("", false)
        binding.searchView.clearFocus()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })
    }

    override fun onButtonClicked(id: Int) {
        when(id) {
            R.id.btn_favorite -> {
                Intent(this, MyWishListActivity::class.java).run { startActivity(this) }
            }
            R.id.btn_alarm -> {
                Intent(this, MyAlarmListActivity::class.java).run { startActivity(this) }
            }
            R.id.btn_setInfo -> {
                Intent(this, GetInfoActivity::class.java).run { startActivity(this) }
            }
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            CustomSnackBar.make(binding.root, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다!").show()
        } else
            finish()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}