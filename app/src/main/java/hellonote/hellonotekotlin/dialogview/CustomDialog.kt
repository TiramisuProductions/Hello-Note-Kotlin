package hellonote.hellonotekotlin.dialogview

import android.content.DialogInterface
import android.os.Build
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import hellonote.hellonotekotlin.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout


class CustomDialog(ui: AnkoContext<View>,tab : Int) {

    lateinit var dialog: DialogInterface
    lateinit var input1: TextInputEditText
    lateinit var input2: TextInputEditText
    lateinit var input3: TextInputEditText
    lateinit var textinputLayout1: TextInputLayout
    lateinit var textinputLayout2: TextInputLayout
    lateinit var textinputLayout3: TextInputLayout
    lateinit var cancelButton: TextView
    lateinit var okButton: TextView
    var tab :Int  =0;
    private  val ACTIVITY_PADDING = 16

    init {
        this.tab = tab
        with(ui) {

            dialog = alert {
                customView {
                    verticalLayout {
                        padding = dip(16)





                        textinputLayout1 = textInputLayout {

                            input1 = textInputEditText {
                                textSize = 16f

                            }
                        }



                        textinputLayout2 =   textInputLayout {

                            input2 = textInputEditText {
                                textSize = 16f

                            }
                        }

                        if(tab==2){
                            textinputLayout3 =    textInputLayout {

                                input3 = textInputEditText {
                                    textSize = 16f

                                }
                            }
                        }
                        linearLayout {
                            topPadding = dip(24)
                            orientation = LinearLayout.HORIZONTAL
                            horizontalGravity = Gravity.END

                            cancelButton = textView("Cancel") {
                                textSize = 14f
                                textColor = context.resources.getColor(R.color.colorAccent)
                            }.lparams {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    marginEnd = dip(ACTIVITY_PADDING)
                                }
                            }

                            okButton = textView("Submit") {
                                textSize = 14f
                                textColor = context.resources.getColor(R.color.colorAccent)



                            }
                        }

                        // More DSL goes here
                    }
                }
            }.show()
        }
    }
}