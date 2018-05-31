package com.aranirahan.myfootballapi.view.myActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

//class DetailActivity : AppCompatActivity(), DetailView, MainView {
//
//    private lateinit var idHomeTeam: String
//    private lateinit var idAwayTeam: String
//    private lateinit var idEvent: String
//
//    private lateinit var txtHomeName: TextView
//    private lateinit var txtHomeScore: TextView
//    private lateinit var txtHomeFormation: TextView
//    private lateinit var txtHomeGoals: TextView
//    private lateinit var txtHomeShots: TextView
//    private lateinit var txtHomeGoalkeeper: TextView
//    private lateinit var txtHomeDefense: TextView
//    private lateinit var txtHomeForward: TextView
//    private lateinit var txtHomeSubtitutes: TextView
//    private lateinit var txtHomeMidfield: TextView
//    private lateinit var imgHome: ImageView
//
//    private lateinit var txtAwayName: TextView
//    private lateinit var txtAwayScore: TextView
//    private lateinit var txtAwayFormation: TextView
//    private lateinit var txtAwayGoals: TextView
//    private lateinit var txtAwayShots: TextView
//    private lateinit var txtAwayGoalkeeper: TextView
//    private lateinit var txtAwayDefense: TextView
//    private lateinit var txtAwayForward: TextView
//    private lateinit var txtAwaySubtitutes: TextView
//    private lateinit var txtAwayMidfield: TextView
//    private lateinit var imgAway: ImageView
//
//    private lateinit var txtMatchDate: TextView
//
//    private lateinit var progressBar: ProgressBar
//
//    private lateinit var detailPresenter: DetailPresenter
//    private lateinit var detailMatchPresenter: MatchDetailPresenter
//
//    private lateinit var team: Team
//    private lateinit var team2: Team
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        scrollView{
//            linearLayout {
//                orientation = LinearLayout.VERTICAL
//                padding = dip(16)
//
//                progressBar = progressBar {
//
//                }.lparams{
//                    gravity = Gravity.CENTER
//                }
//
//                linearLayout {
//                    orientation = LinearLayout.VERTICAL
//
//                    txtMatchDate = textView {
//                        id = R.id.match_date
//                    }.lparams {
//                        width = wrapContent
//                        height = wrapContent
//                        gravity = Gravity.CENTER
//                    }
//
//                    linearLayout {
//                        padding = dip(16)
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeScore = textView {
//                            id = R.id.home_score_match
//                            textSize = 22f
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = 16
//                        }
//
//                        textView {
//                            text = getString(vs)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayScore = textView {
//                            id = R.id.away_score_match
//                            textSize = 22f
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = 16
//                        }
//                    }.lparams {
//                        gravity = Gravity.CENTER
//                    }
//
//                    // TOP - First
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        // Left
//                        linearLayout {
//                            orientation = LinearLayout.VERTICAL
//
//                            imgHome = imageView {
//                                id = R.id.img_home
//                            }.lparams {
//                                width = dip(150)
//                                height = dip(150)
//                            }
//
//                            txtHomeName = textView {
//                                id = R.id.home_name
//                                textSize = 18f
//                            }.lparams {
//                                gravity = Gravity.CENTER
//                            }
//
//                            txtHomeFormation = textView {
//                                id = R.id.home_formation
//                            }.lparams {
//                                gravity = Gravity.CENTER
//                            }
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        // Right
//                        linearLayout {
//                            orientation = LinearLayout.VERTICAL
//
//                            imgAway = imageView {
//                                id = R.id.img_away
//                            }.lparams {
//                                width = dip(150)
//                                height = dip(150)
//                            }
//
//                            txtAwayName = textView {
//                                id = R.id.away_name
//                                textSize = 18f
//                            }.lparams {
//                                gravity = Gravity.CENTER
//                            }
//
//                            txtAwayFormation = textView {
//                                id = R.id.away_formation
//                            }.lparams {
//                                gravity = Gravity.CENTER
//                            }
//                        }.lparams {
//                            weight = 1f
//                            topMargin = dip(16)
//                        }
//                    }
//
//                    // TOP - Second
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//                        gravity = Gravity.CENTER
//
//                        txtHomeGoals = textView {
//                            id = R.id.home_goals
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            gravity = Gravity.CENTER
//                            rightMargin = 16
//                        }
//
//                        textView {
//                            text = getString(goals)
//                        }.lparams {
//                            weight = 1f
//                            gravity = Gravity.CENTER
//                        }
//
//                        txtAwayGoals = textView {
//                            id = R.id.away_goals
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            gravity = Gravity.CENTER
//                            leftMargin = 16
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                        gravity = Gravity.CENTER
//                    }
//
//                    // TOP - Third
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeShots = textView {
//                            id = R.id.home_shots
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = 16
//                        }
//
//                        textView {
//                            text = getString(shots)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayShots = textView {
//                            id = R.id.away_shots
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = 16
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                        gravity = Gravity.CENTER
//                    }
//
//                    textView {
//                        text = getString(lineup)
//                    }.lparams {
//                        topMargin = dip(16)
//                        gravity = Gravity.CENTER
//                    }
//
//                    // TOP - Fourth
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeGoalkeeper = textView {
//                            id = R.id.home_goalkeeper
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = dip(16)
//                        }
//
//                        textView {
//                            text = getString(goalkeeper)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayGoalkeeper = textView {
//                            id = R.id.away_goalkeeper
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            leftMargin = dip(16)
//                            weight = 1f
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                    }
//
//                    // TOP - Fifth
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeDefense = textView {
//                            id = R.id.home_defense
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = dip(16)
//                        }
//
//                        textView {
//                            text = getString(defense)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayDefense = textView {
//                            id = R.id.away_defense
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = dip(16)
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                    }
//
//                    // TOP - Sixth
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeMidfield = textView {
//                            id = R.id.home_midfield
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = dip(16)
//                        }
//
//                        textView {
//                            text = getString(midfield)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayMidfield = textView {
//                            id = R.id.away_midfield
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = dip(16)
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                    }
//
//                    // TOP - Sixth
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeForward = textView {
//                            id = R.id.home_forward
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = dip(16)
//                        }
//
//                        textView {
//                            text = getString(forward)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwayForward = textView {
//                            id = R.id.away_forward
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = dip(16)
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                    }
//
//                    // TOP - Sixth
//                    linearLayout {
//                        orientation = LinearLayout.HORIZONTAL
//
//                        txtHomeSubtitutes = textView {
//                            id = R.id.home_substitutes
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            rightMargin = dip(16)
//                        }
//
//                        textView {
//                            text = getString(subtitute)
//                            gravity = Gravity.CENTER
//                        }.lparams {
//                            weight = 1f
//                        }
//
//                        txtAwaySubtitutes = textView {
//                            id = R.id.away_substitutes
//                            gravity = Gravity.CENTER
//                            textSize = 18f
//                        }.lparams {
//                            weight = 1f
//                            leftMargin = dip(16)
//                        }
//
//                    }.lparams {
//                        topMargin = dip(16)
//                        bottomMargin = dip(16)
//                    }
//                }
//            }
//        }
//
//        val i = intent
//
//        idHomeTeam = i.getStringExtra(KEY.HOME_ID_KEY)
//        idAwayTeam = i.getStringExtra(KEY.AWAY_ID_KEY)
//        idEvent = i.getStringExtra(KEY.EVENT_ID_KEY)
//
//        val request = APIRepository()
//        val gson = Gson()
//
//        detailPresenter = DetailPresenter(this, request, gson)
//        detailMatchPresenter = MatchDetailPresenter(this, request, gson)
//
//        detailPresenter.getBadgeList(idHomeTeam, idAwayTeam)
//        detailMatchPresenter.getDetailMatch(idEvent)
//    }
//
//    private fun View.visible(){
//        visibility = View.VISIBLE
//    }
//
//    private fun View.invisible(){
//        visibility = View.GONE
//    }
//
//    override fun showLoading() {
//        progressBar.visible()
//    }
//
//    override fun hideLoading() {
//        progressBar.invisible()
//    }
//
//    override fun showTeamsList(data: List<Team>?, data2: List<Team>?) {
//        team = Team(data?.get(0)?.strTeamBadge)
//        team2 = Team(data2?.get(0)?.strTeamBadge)
//        Picasso.with(this).load(data?.get(0)?.strTeamBadge).into(imgHome)
//        Picasso.with(this).load(data2?.get(0)?.strTeamBadge).into(imgAway)
//    }
//
//    override fun showMatchEventList(data: List<Match>?) {
//        txtHomeName.text = data?.get(0)?.strHomeTeam
//        txtHomeScore.text = data?.get(0)?.intHomeScore
//        txtHomeFormation.text = data?.get(0)?.strHomeFormation
//        txtHomeGoals.text = data?.get(0)?.strHomeGoalDetails
//        txtHomeGoalkeeper.text = data?.get(0)?.strHomeLineupGoalkeeper
//        txtHomeShots.text = data?.get(0)?.intHomeShots
//        txtHomeDefense.text = data?.get(0)?.strHomeLineupDefense
//        txtHomeForward.text = data?.get(0)?.strHomeLineupForward
//        txtHomeSubtitutes.text = data?.get(0)?.strHomeLineupSubstitutes
//        txtHomeMidfield.text = data?.get(0)?.strAwayLineupDefense
//
//        txtAwayName.text = data?.get(0)?.strAwayTeam
//        txtAwayScore.text = data?.get(0)?.intAwayScore
//        txtAwayFormation.text = data?.get(0)?.strAwayFormation
//        txtAwayGoals.text = data?.get(0)?.strAwayGoalDetails
//        txtAwayGoalkeeper.text = data?.get(0)?.strAwayLineupGoalkeeper
//        txtAwayShots.text = data?.get(0)?.intAwayShots
//        txtAwayDefense.text = data?.get(0)?.awayDefense
//        txtAwayForward.text = data?.get(0)?.strAwayLineupForward
//        txtAwaySubtitutes.text = data?.get(0)?.strAwayLineupSubstitutes
//        txtAwayMidfield.text = data?.get(0)?.strAwayLineupMidfield
//
//        txtMatchDate.text = data?.get(0)?.dateEvent
//    }
}