package uk.co.lemoncog.footballmanager

import org.junit.Test

import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.androidcosofretrofit.firstGameReply
import uk.co.lemoncog.footballmanager.androidcosofretrofit.secondGameReply
import uk.co.lemoncog.footballmanager.core.*
import java.util.*

class TestDataProvider() : DataProvider<GameModel, Throwable> {
    override fun get(success: (GameModel) -> Unit, failure: (Throwable) -> Unit) {
        success(GameModel(0, "Jessica", "JessicaDescr", Date(), Date(), Date(), arrayOf(firstGameReply, secondGameReply)));
    }
}

class GamePresenterTest : StatefulView<GameViewModel>, ActionListener {
    override fun showError(throwable: Throwable) {
        throw UnsupportedOperationException()
    }

    override fun setViewClickedListener(clicked: () -> Unit) {
        throw UnsupportedOperationException()
    }

    var showCalled: Boolean = false;
    var onTriggered: Boolean = false;

    override fun setAcceptClickedListener(clicked: () -> Unit) {
    }

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
        val presenter = GamePresenter(this, AuthenticatedUser("test_token", "test@email.com"),  dataProvider);

        presenter.onReady();

        assertTrue("Expected show to be called", showCalled);
    }

    @Test
    fun givenUserAccepts_ThAGameEventIsEmenitted() {
        val dataProvider = TestDataProvider();
        val presenter = GamePresenter(this, AuthenticatedUser("test_token", "test@email.com"), dataProvider);

        presenter.actionListeners.add(this);

        presenter.onAcceptClicked();

        assertTrue("Expected onTriggered to be called", onTriggered);
    }
}