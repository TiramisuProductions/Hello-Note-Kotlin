package hellonote.hellonotekotlin

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.stephenvinouze.advancedrecyclerview.core.adapters.RecyclerAdapter
import hellonote.hellonotekotlin.data.Sample
import kotlinx.android.synthetic.main.sample_item_view.view.*

class SampleItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.sample_item_view, this, true)
        Log.d("here it comes","here it comes");
    }

    fun bind(sample: Sample) {
        // Update your subviews with the Sample data
        sample_item_name_text_view.text = sample.name

    }

}

class SampleAdapter(context: Context) : RecyclerAdapter<Sample>(context) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View = SampleItemView(context)

    override fun onBindItemView(view: View, position: Int) {
        Log.d("lol",items[0].name)
        when (view) {

            is SampleItemView -> view.bind(items[position])
        }
    }

}