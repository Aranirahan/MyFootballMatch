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
import com.aranirahan.myfootballapi.model.Favorite
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FavoriteMatchAdapter(private val favorite: List<Favorite>
                           , private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(FavoriteMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeName: TextView = view.find(tv_home_name)
    private val awayName: TextView = view.find(tv_away_name)
    private val score: TextView = view.find(tv_score_match)
    private val matchDate: TextView = view.find(tv_match_date)
    private val cv: CardView = view.find(cv_match)

    @SuppressLint("SetTextI18n")
    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        homeName.text = favorite.strHomeTeam
        awayName.text = favorite.strAwayTeam
        if (favorite.intHomeScore != null) {
            score.text = favorite.intHomeScore + " VS " + favorite.intAwayScore
        } else {
            score.text = "? VS ?"
        }
        matchDate.text = favorite.dateEvent
        cv.setOnClickListener { _: View ->
            listener(favorite)
        }
    }
}

class FavoriteMatchUI : AnkoComponent<ViewGroup> {
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
