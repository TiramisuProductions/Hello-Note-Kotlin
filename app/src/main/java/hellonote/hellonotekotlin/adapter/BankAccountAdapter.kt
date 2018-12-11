package hellonote.hellonotekotlin.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.stephenvinouze.advancedrecyclerview.core.adapters.RecyclerAdapter
import hellonote.hellonotekotlin.R
import hellonote.hellonotekotlin.database.BankAccount
import hellonote.hellonotekotlin.database.Contact
import kotlinx.android.synthetic.main.item_bank_account.view.*
import kotlinx.android.synthetic.main.item_contact.view.*

class BankAccountItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bank_account, this, true)
        Log.d("here it comes","here it comes")
    }

    fun bind(bankAccount: BankAccount) {
        // Update your subviews with the Sample data
        // sample_item_name_text_view.text = sample.name
        textview_contact_account_no.text = bankAccount.accountNo
        textview_contact_ifsc.text = bankAccount.ifscCode

    }

}

class BankAccountAdapter(context: Context) : RecyclerAdapter<BankAccount>(context) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View = BankAccountItemView(context)

    override fun onBindItemView(view: View, position: Int) {
        Log.d("lol",items[0].name)
        when (view) {

            is BankAccountItemView -> view.bind(items[position])
        }
    }

}