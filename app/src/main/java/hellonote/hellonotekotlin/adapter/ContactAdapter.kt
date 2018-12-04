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
import hellonote.hellonotekotlin.data.Sample
import hellonote.hellonotekotlin.database.Contact
import kotlinx.android.synthetic.main.item_contact.view.*
import kotlinx.android.synthetic.main.sample_item_view.view.*

class ItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_contact, this, true)
        Log.d("here it comes","here it comes")
    }

    fun bind(contact: Contact) {
        // Update your subviews with the Sample data
       // sample_item_name_text_view.text = sample.name
       textview_contact_name.text = contact.name
        textview_contact_number.text = contact.contactNumber

    }

}

class ContactAdapter(context: Context) : RecyclerAdapter<Contact>(context) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View = ItemView(context)

    override fun onBindItemView(view: View, position: Int) {
        Log.d("lol",items[0].name)
        when (view) {

            is ItemView -> view.bind(items[position])
        }
    }

}