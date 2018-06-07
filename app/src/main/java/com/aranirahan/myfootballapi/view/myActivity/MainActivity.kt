package com.aranirahan.myfootballapi.view.myActivity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.LinearLayout
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)
                textView("FOOTBALL MATCH SCHEDULE") {
                    id = R.id.match_date
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER
                }

                button("Past Match") {
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick {
                        startActivity<PastMatchActivity>("name" to "m")
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(24)
                }
                button("Next Match") {
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick {
                        startActivity<NextMatchActivity>("name" to "m")
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(6)
                }
            }
        }
    }
}

