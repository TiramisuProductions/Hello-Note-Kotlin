package hellonote

import com.orm.SugarApp
import hellonote.hellonotekotlin.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class Application : SugarApp(){

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/pt_bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}