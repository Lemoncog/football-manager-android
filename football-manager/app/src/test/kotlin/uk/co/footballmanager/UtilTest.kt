package uk.co.footballmanager

import org.junit.Assert
import org.junit.Test
import uk.co.lemoncog.footballmanager.core.util.parseServerDate
import java.util.*

class UtilTest {

    @Test
    fun testParsingServerDate() {
        val date = parseServerDate("2016-03-17T09:15:00.000Z");

        val cal = Calendar.getInstance();
        cal.set(2016, 2, 17, 9, 15, 0);
        cal.set(Calendar.MILLISECOND, 0);
        val actualDate = cal.time;


        Assert.assertEquals(date.time, actualDate.time);
    }
}