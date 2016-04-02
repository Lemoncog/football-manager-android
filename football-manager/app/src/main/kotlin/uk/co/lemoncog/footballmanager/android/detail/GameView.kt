package uk.co.lemoncog.footballmanager.android.detail

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.GameViewModel
import uk.co.lemoncog.footballmanager.core.StatefulView

class GameView(val root: View) : StatefulView<GameViewModel> {
    val title : TextView = root.findViewById(R.id.game_title) as TextView;
    val whenValue : TextView = root.findViewById(R.id.game_when_value) as TextView;
    val repliesSoFar : TextView = root.findViewById(R.id.game_replies_so_far) as TextView;
    val acceptView = root.findViewById(R.id.game_accept);
    var acceptClicked : (() -> Unit)? = null;
    var viewClicked: (() -> Unit)? = null;

    init {
        acceptView.setOnClickListener {  acceptClicked?.invoke() }
        root.setOnClickListener { viewClicked?.invoke() }
    }

    override fun setViewClickedListener(clicked: () -> Unit) {
        viewClicked = clicked;
    }

    override fun setAcceptClickedListener(clicked: () -> Unit) {
        acceptClicked = clicked;
    }

    override fun show(viewData: GameViewModel) {
        title.text = viewData.name;
        whenValue.text = viewData.dateWhen;
        repliesSoFar.text = "${  viewData.replyCount } replies so far";
        acceptView.isEnabled = !viewData.userInGame;
    }
}