package com.aranirahan.myfootballapi.detail.detailTeam.player.playerDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.model.PlayerDetail
import com.aranirahan.myfootballapi.util.invisible
import com.aranirahan.myfootballapi.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var progressBar: ProgressBar
    private lateinit var playerDetailPresenter: PlayerDetailPresenter
    private lateinit var idPlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.title = "Player Detail"

        progressBar = findViewById(R.id.progress_bar_player)

        val myIntent = intent

        idPlayer = myIntent.getStringExtra("id")
        val request = ApiRepository()
        val gson = Gson()

        playerDetailPresenter = PlayerDetailPresenter(this, request, gson)
        playerDetailPresenter.getPlayerList(idPlayer)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<PlayerDetail>) {
        Picasso.get().load(data.get(0).strFanart1).into(iv_player_detail)
        tv_weight.text = data.get(0).strWeight
        tv_height.text = data.get(0).strHeight
        tv_position_player.text = data.get(0).strPosition
        tv_forward_player_detail.text = data.get(0).strDescriptionEN
    }
}
