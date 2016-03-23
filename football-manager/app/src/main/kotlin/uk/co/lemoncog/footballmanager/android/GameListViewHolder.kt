package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.RecyclerView
import android.view.View

class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameView = GameView(itemView);

}