
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class RowsTestCase {
    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    @Test
    void testBuildRow() {
        // test
        def result = new Rows().buildRow("0","1","2","3","4","5","6","7","8")

        assert result[Rows.Q_ID] == "0"
        assert result[Rows.A_SCORE] == "8"
    }
}
