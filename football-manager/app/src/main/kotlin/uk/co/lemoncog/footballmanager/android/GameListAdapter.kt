package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.GameViewModel

class GameListAdapter() : RecyclerView.Adapter<GameListViewHolder>() {
    var data : Array<GameViewModel> = emptyArray();
    var gameListClickedListener: GameListClickedListener? = null;


    override fun onBindViewHolder(holder: GameListViewHolder?, position: Int) {
        holder!!.gameView.show(data[position])
        holder!!.gameView.setAcceptClickedListener { gameListClickedListener?.gameViewClicked(position, data[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GameListViewHolder? {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_game, parent, false);
        val viewHolder = GameListViewHolder(view);
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.count();
    }
}