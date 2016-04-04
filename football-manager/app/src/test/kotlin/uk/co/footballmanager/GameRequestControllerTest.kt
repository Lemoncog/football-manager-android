package uk.co.footballmanager

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameReply
import uk.co.lemoncog.footballmanager.core.GameRequestController
import java.util.*

class GameRequestControllerTest {

    var successEventEmitted : Boolean = false;

    @Test @Ignore
    fun givenRequestToPlayGameIsMade_AndThereAreSpaces_ThenEmitRequestSuccessEvent() {
        val gameRequestController = GameRequestController(AuthenticatedUser("test_token", "test@email.com"));

        gameRequestController.requestToPlay(1, {
            successEventEmitted = true;
        }, {});

        assertTrue("Expected success event to be emitted", successEventEmitted);
    }
}