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
import hellonote.hellonotekotlin.database.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_contact, this, true)
        Log.d("here it comes","here it comes")
    }

    fun bind(note: Note) {
        // Update your subviews with the Sample data
        // sample_item_name_text_view.text = sample.name
        textview_contact_note.text = note.note


    }

}

class NoteAdapter(context: Context) : RecyclerAdapter<Note>(context) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View = NoteItemView(context)

    override fun onBindItemView(view: View, position: Int) {

        when (view) {

            is NoteItemView -> view.bind(items[position])
        }
    }

}