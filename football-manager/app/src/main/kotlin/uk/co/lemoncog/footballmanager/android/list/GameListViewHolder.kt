package uk.co.lemoncog.footballmanager.android.list

import android.support.v7.widget.RecyclerView
import android.view.View
import uk.co.lemoncog.footballmanager.android.detail.GameView

class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameView = GameView(itemView);

}