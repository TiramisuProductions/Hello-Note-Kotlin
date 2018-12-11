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
import hellonote.hellonotekotlin.database.Contact
import hellonote.hellonotekotlin.database.Email
import kotlinx.android.synthetic.main.item_email.view.*

class EmailItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_email, this, true)
        Log.d("here it comes","here it comes")
    }

    fun bind(email: Email) {
        // Update your subviews with the Sample data
        // sample_item_name_text_view.text = sample.name
        textview_contact_name.text = email.name;
        textview_contact_email.text = email.emailId;

    }

}

class EmailAdapter(context: Context) : RecyclerAdapter<Email>(context) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View = EmailItemView(context)

    override fun onBindItemView(view: View, position: Int) {
        Log.d("lol",items[0].name)
        when (view) {

            is EmailItemView -> view.bind(items[position])
        }
    }

}