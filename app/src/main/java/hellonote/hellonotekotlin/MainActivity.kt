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
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.wajahatkarim3.easyvalidation.core.view_ktx.*
import hellonote.hellonotekotlin.adapter.MainActivityTabAdapter
import hellonote.hellonotekotlin.bus.RxBus
import hellonote.hellonotekotlin.bus.RxEvent
import hellonote.hellonotekotlin.database.BankAccount
import hellonote.hellonotekotlin.database.Contact
import hellonote.hellonotekotlin.database.Email
import hellonote.hellonotekotlin.fragment.*
import hellonote.hellonotekotlin.dialogview.CustomDialog
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar


class MainActivity : AppCompatActivity(),ContactFragment.OnFragmentInteractionListener,EmailFragment.OnFragmentInteractionListener,BankAccountFragment.OnFragmentInteractionListener,NoteFragment.OnFragmentInteractionListener,CallRecordFragment.OnFragmentInteractionListener,View.OnClickListener

{
    override fun onClick(v: View?) {
        Log.d("working","working");
        fab_menu.close(true)
        var tab = 0

        when (v?.id){
            R.id.fab_contact -> {tab=0}
            R.id.fab_email   -> {tab=1}
            R.id.fab_bank_account -> {tab=2}
        }


        val customDialog by lazy {
            contentView?.let {
                CustomDialog(AnkoContext.create(ctx, it), tab)
            }

        }

        when(tab){
            0->{
                customDialog?.textinputLayout1?.setHint(getString(R.string.hint_name))
                customDialog?.textinputLayout2?.setHint(getString(R.string.hint_number))
            }
            1->{
                customDialog?.textinputLayout1?.setHint(getString(R.string.hint_name))
                customDialog?.textinputLayout2?.setHint(getString(R.string.hint_email))
            }
            2->{
                customDialog?.textinputLayout1?.setHint(getString(R.string.hint_name))
                customDialog?.textinputLayout2?.setHint(getString(R.string.hint_ac_no))
                customDialog?.textinputLayout3?.setHint(getString(R.string.hint_Others))
            }
        }




        customDialog?.okButton?.setOnClickListener {
            val tab: Int = customDialog.tab
            var valid: Boolean = true


            when (tab) {
                0 -> {
                    if (!customDialog.input1.nonEmpty() || !customDialog.input1.noNumbers()) {
                        customDialog.input1.setError(getString(R.string.error_name))
                        valid = false
                    }
                    if (!customDialog.input2.nonEmpty() || customDialog.input2.noNumbers() || !customDialog.input2.minLength(
                            8
                        ) || !customDialog.input2.maxLength(13)
                    ) {
                        customDialog.input2.setError(getString(R.string.error_number))
                        valid = false
                    }
                }
                1 -> {
                    if (!customDialog.input1.nonEmpty() || !customDialog.input1.noNumbers()) {
                        customDialog.input1.setError(getString(R.string.error_name))
                        valid = false
                    }
                    if (!customDialog.input2.nonEmpty() || !customDialog.input2.validEmail()) {
                        customDialog.input2.setError(getString(R.string.error_email))
                        valid = false
                    }
                }
                2 -> {
                    if (!customDialog.input1.nonEmpty() || !customDialog.input1.noNumbers()) {
                        customDialog.input1.setError(getString(R.string.error_name))
                        valid = false
                    }
                    if (!customDialog.input2.nonEmpty() || customDialog.input2.noNumbers()) {
                        customDialog.input2.setError(getString(R.string.error_ac_no))
                        valid = false
                    }
                    if (!customDialog.input3.nonEmpty() ) {
                        customDialog.input3.setError(getString(R.string.error_number))
                        valid = false
                    }
                }
                3 -> {
                    if (!customDialog.input1.nonEmpty() ) {
                        customDialog.input1.setError(getString(R.string.error_name))
                        valid = false
                    }

                }

            }




            if (valid) {
                when (tab) {
                    0 -> {
                        val contact: Contact = Contact(
                            customDialog.input1.text.toString(),
                            customDialog.input2.text.toString(),
                            "kk",
                            true,
                            false
                        );
                        contact.save();
                    }
                    1 -> {
                        val email: Email = Email(
                            customDialog.input1.text.toString(),
                            customDialog.input2.text.toString(),
                            "kk",
                            true,
                            false
                        );
                        email.save();
                    }
                    2->{
                        val bank: BankAccount = BankAccount(
                            customDialog.input1.text.toString(),
                            customDialog.input2.text.toString(),
                            customDialog.input3.text.toString(),
                            "kk",
                            true,
                            false
                        );
                        bank.save();

                    }
                }

                customDialog.dialog.dismiss()
                val snack = Snackbar.make(rootLayout, "Data Saved", Snackbar.LENGTH_LONG)
                snack.show()
                // var fragment = supportFragmentManager.findFragmentById(contactFragmentId) as ContactFragment
                //  fragment.initRecyler();
                RxBus.publish(RxEvent.EventAddPerson(""))
            }


        }

        customDialog?.cancelButton?.setOnClickListener {
            customDialog.dialog.dismiss()

        }

    }


    override fun onFragmentInteraction(uri: Uri) {

    }

    private var chatHeadService: BubbleService? = null
    private var bound: Boolean = false
    private var contactFragmentId : Int? =  null;



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
        contactFragmentId = fragmentAdapter.getItem(0).id
        hometablayout.setViewPager(viewpager)
        fab_contact.setOnClickListener(this)
        fab_email.setOnClickListener(this)
        fab_bank_account.setOnClickListener(this)

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
