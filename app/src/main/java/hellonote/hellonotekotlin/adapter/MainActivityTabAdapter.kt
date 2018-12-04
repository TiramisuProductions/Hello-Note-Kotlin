package hellonote.hellonotekotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import hellonote.hellonotekotlin.fragment.*

class MainActivityTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> EmailFragment()
            2 -> BankAccountFragment()
            3 -> NoteFragment()

            else -> {
                return CallRecordFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Contact"
            1 -> "Email"
            2 -> "Bank Account"
            3 -> "Note"
            4 ->  "Call Recording"
            else -> {
                return "";
            }
        }
    }
}