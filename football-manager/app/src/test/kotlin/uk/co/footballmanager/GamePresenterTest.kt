package uk.co.lemoncog.footballmanager

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.parseServerDate
import java.text.SimpleDateFormat
import java.util.*

class TestDataProvider() : DataProvider<GameModel> {
    override fun get(success: (GameModel) -> Unit, failure: () -> Unit) {
        success(GameModel(0, "Jessica", "JessicaDescr", Date(), Date(), Date()));
    }
}

class GamePresenterTest : StatefulView<GameViewModel>, ActionListener {

    var showCalled: Boolean = false;
    var onTriggered: Boolean = false;

    override fun onTrigger() {
        onTriggered = true;
    }

    override fun show(viewData: GameViewModel) {
        showCalled = true;
    }

    @Test
    @Throws(Exception::class)
    fun givenAGameIsAvailable_ThenDisplayLatestGame() {
        val dataProvider = TestDataProvider();
        val presenter = GamePresenter(this, dataProvider);

        presenter.onReady();

        assertTrue("Expected show to be called", showCalled);
    }

    @Test
    fun givenUserAccepts_ThAGameEventIsEmenitted() {
        val dataProvider = TestDataProvider();
        val presenter = GamePresenter(this, dataProvider);

        presenter.actionListeners.add(this);

        presenter.onAcceptClicked();

        assertTrue("Expected onTriggered to be called", onTriggered);
    }
}