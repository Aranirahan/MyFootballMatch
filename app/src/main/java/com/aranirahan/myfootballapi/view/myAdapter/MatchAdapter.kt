package com.aranirahan.myfootballapi.view.myAdapter

import android.annotation.SuppressLint
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.id.*
import com.aranirahan.myfootballapi.model.item.MatchEvent
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamsAdapter(private val matchEvents: List<MatchEvent>, private val listener: (MatchEvent) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matchEvents.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchEvents[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeName: TextView = view.find(tv_home_name)
    private val awayName: TextView = view.find(tv_away_name)
    private val score: TextView = view.find(tv_score_match)
    private val matchDate: TextView = view.find(tv_match_date)
    private val cv: CardView = view.find(cv_match)

    @SuppressLint("SetTextI18n")
    fun bindItem(events: MatchEvent, listener: (MatchEvent) -> Unit) {
        homeName.text = events.strHomeTeam
        awayName.text = events.strAwayTeam
        if (events.intHomeScore != null) {
            score.text = events.intHomeScore + " VS " + events.intAwayScore
        } else {
            score.text = "? VS ?"
        }
        matchDate.text = events.dateEvent
        cv.setOnClickListener { _: View ->
            listener(events)
        }
    }
}

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                id = R.id.cv_match
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(16)
                    rightMargin = dip(16)
                    leftMargin = dip(16)
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    textView {
                        id = R.id.tv_match_date
                        gravity = Gravity.CENTER
                    }.lparams {
                        width = matchParent
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL

                        textView {
                            id = R.id.tv_home_name
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }

                        textView {
                            id = R.id.tv_score_match
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }

                        textView {
                            id = R.id.tv_away_name
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }
                    }
                }
            }
        }
    }
}
