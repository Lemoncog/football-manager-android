package uk.co.lemoncog.footballmanager

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.parseServerDate
import java.text.SimpleDateFormat
import java.util.*

class GamePresenterTest : StatefulView<GameViewModel> {

    var showCalled: Boolean = false;

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
    fun givenUserAcceptsAGameEventIsEmitted() {
        fail("Implement this sausage")
    }
}