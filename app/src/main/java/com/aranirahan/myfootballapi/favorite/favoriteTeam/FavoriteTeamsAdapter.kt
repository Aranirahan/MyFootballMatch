package com.aranirahan.myfootballapi.favorite.favoriteTeam

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.aranirahan.myfootballapi.R.id.team_badge
import com.aranirahan.myfootballapi.R.id.team_name
import com.aranirahan.myfootballapi.db.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class FavoriteTeamsAdapter(private val favorite: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favoriteTeam.teamBadge).into(teamBadge)
        teamName.text = favoriteTeam.teamName
        itemView.onClick { listener(favoriteTeam) }
    }
}