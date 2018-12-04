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
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.orm.SugarRecord
import hellonote.hellonotekotlin.database.BankAccount
import com.orm.SugarRecord.listAll
import hellonote.hellonotekotlin.adapter.MainActivityTabAdapter
import com.tompee.funtablayout.SimpleTabAdapter
import hellonote.hellonotekotlin.database.Contact
import hellonote.hellonotekotlin.fragment.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.ogaclejapan.smarttablayout.SmartTabLayout




class MainActivity : AppCompatActivity(),ContactFragment.OnFragmentInteractionListener,EmailFragment.OnFragmentInteractionListener,BankAccountFragment.OnFragmentInteractionListener,NoteFragment.OnFragmentInteractionListener,CallRecordFragment.OnFragmentInteractionListener

{
    override fun onFragmentInteraction(uri: Uri) {

    }

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
        toolbar_homescreen.setTitle(resources.getString(R.string.app_name))
        val intent = Intent(this, BubbleService::class.java)
        startService(intent)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        val fragmentAdapter = MainActivityTabAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter
        hometablayout.setViewPager(viewpager)

        //hometablayout.setupWithViewPager(viewpager)

       // hometablayout.getTabAt(0)?.setIcon(R.drawable.ic_contact);
        //hometablayout.getTabAt(1)?.setIcon(R.drawable.ic_email);
        //hometablayout.getTabAt(2)?.setIcon(R.drawable.ic_bank);
        //hometablayout.getTabAt(3)?.setIcon(R.drawable.ic_note);
        //hometablayout.getTabAt(4)?.setIcon(R.drawable.ic_note);




        doAsync {  val contact : Contact = Contact("asd","asd","asd","asd",true,"asdd",false);
            contact.save();

        uiThread {
            val snack = Snackbar.make(rootLayout,"This is a simple Snackbar", Snackbar.LENGTH_LONG)
            snack.show()
        }
        }
















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
