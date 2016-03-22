package uk.co.footballmanager

import org.junit.Assert.*
import org.junit.Test
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameReply
import uk.co.lemoncog.footballmanager.core.GameRequestController
import java.util.*

fun createSuccessProvider(): DataProvider<GameReply> {
    return object: DataProvider<GameReply> {
        override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {
            success(GameReply(0, "test", Date()));
        }
    }
}

fun createFailureProvider(): DataProvider<GameReply> {
    return object: DataProvider<GameReply> {
        override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {
            failure();
        }
    }
}

class GameRequestControllerTest {

    var successEventEmitted : Boolean = false;
    var failureEventEmitted : Boolean = false;

    @Test
    fun givenRequestToPlayGameIsMade_AndThereAreSpaces_ThenEmitRequestSuccessEvent() {
        val dataProvider = createSuccessProvider();
        val gameRequestController = GameRequestController(dataProvider);

        gameRequestController.requestToPlay({
            successEventEmitted = true;
        }, {});

        assertTrue("Expected success event to be emitted", successEventEmitted);
    }

    @Test
    fun givenRequestToPayIsMade_AndThereAreNoSpaces_ThenEmitFailureEvent() {
        val dataProvider = createFailureProvider();
        val gameRequestController = GameRequestController(dataProvider);

        gameRequestController.requestToPlay({}, {
            failureEventEmitted = true;
        });

        assertTrue("Expected failure event to be emitted", failureEventEmitted);
    }
}