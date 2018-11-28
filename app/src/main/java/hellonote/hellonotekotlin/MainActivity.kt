package hellonote.hellonotekotlin

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import android.view.Menu

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings


class MainActivity : AppCompatActivity()

{

    private var chatHeadService: BubbleService? = null
    private var bound: Boolean = false


    private val mConnection = object : ServiceConnection{


        override fun onServiceConnected(
            className: ComponentName,
            service: IBinder
        ) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            var binder : BubbleService.LocalBinder = service as BubbleService.LocalBinder
            chatHeadService = binder.getService()
     //       chatHeadService = binder.getService()
            bound = true
            chatHeadService!!.minimize()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, 1234)
            }
        }
        init()


    }


    fun init() {
        setSupportActionBar(toolbar_homescreen)
        toolbar_homescreen.setTitle("Hello Note")
        val intent = Intent(this, BubbleService::class.java)
        startService(intent)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)










    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }



    private fun setupViewPager(viewPager: ViewPager) {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ContactFragment.newInstance("1","2")
            }

            override fun getCount(): Int {
                return 5
            }

            override fun getPageTitle(position: Int): CharSequence? {
                var title = ""
                when (position) {
                    0 -> title = "Contact"
                    1 -> title = "Email"
                    2 -> title = "Bank"
                    3 -> title = "Notes"
                    4 -> title = "Recording"
                }
                return title
            }
        }
    }



}
