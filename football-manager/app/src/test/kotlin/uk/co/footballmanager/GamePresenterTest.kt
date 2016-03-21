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
    fun testParsingServerDate() {
        val date = parseServerDate("2016-03-17T09:15:00.000Z");

        val cal = Calendar.getInstance();
        cal.set(2016, 2, 17, 9, 15, 0);
        cal.set(Calendar.MILLISECOND, 0);
        val actualDate = cal.time;


        assertEquals(date.time, actualDate.time);
    }
}