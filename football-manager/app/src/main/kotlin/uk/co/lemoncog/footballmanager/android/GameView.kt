package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.GameViewModel
import uk.co.lemoncog.footballmanager.core.StatefulView

class GameView(root: ViewGroup) : StatefulView<GameViewModel> {
    val title : TextView = root.findViewById(R.id.game_title) as TextView;
    val acceptView = root.findViewById(R.id.game_accept);
    var acceptClicked : (() -> Unit)? = null;

    init {
        acceptView.setOnClickListener {  acceptClicked?.invoke() }
    }

    override fun setAcceptClickedListener(clicked: () -> Unit) {
        acceptClicked = clicked;
    }

    override fun show(viewData: GameViewModel) {
        title.text = viewData.name;
    }
}