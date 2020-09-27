
package net.codetojoy

class Rows {
    static def Q_ID = "Q_ID"
    static def GROUP = "GROUP"
    static def TIER = "TIER"
    static def LEVEL = "LEVEL"
    static def Q_PREFIX = "Q_PREFIX"
    static def Q_TEXT = "Q_TEXT"
    static def A_ID = "A_ID"
    static def A_TEXT = "A_TEXT"
    static def A_SCORE = "A_SCORE"

    static def COLS = [Q_ID, GROUP, TIER, LEVEL, Q_PREFIX, Q_TEXT, A_ID, A_TEXT, A_SCORE]
    static def NUM_COLS = COLS.size()

    def buildRow(String... args) {
        def row = [:]

        assert NUM_COLS == args.size()
        NUM_COLS.times { i ->
            def key = "${COLS[i]}"
            row[key] = args[i]
        }

        return row
    }

    def getRows() {
        def rows = []

        // Q1
        buildRow("100", "1", "1", "1", "Q1", "what?", "500", "Yes", "1")
        buildRow("100", "1", "1", "1", "Q1", "what?", "501", "No", "0")

        // Q2 -> Q2.1
        buildRow("200", "2", "1", "1", "Q2", "who?", "502", "Red", "0")
        buildRow("200", "2", "1", "1", "Q2", "who?", "503", "Blue", "1")
        buildRow("200", "2", "1", "1", "Q2", "who?", "504", "Green", "0")
            buildRow("201", "2", "2", "1", "Q2.1", "why?", "500", "Yes", "1")
            buildRow("201", "2", "2", "1", "Q2.1", "why?", "501", "No", "0")

        // Q3 -> Q3.1 -> Q3.2
        buildRow("300", "3", "1", "1", "Q3", "when?", "502", "Red", "0")
        buildRow("300", "3", "1", "1", "Q3", "when?", "503", "Blue", "1")
        buildRow("300", "3", "1", "1", "Q3", "when?", "504", "Green", "0")
            buildRow("301", "3", "2", "1", "Q3.1", "how?", "", "", "")
                buildRow("302", "3", "2", "2", "Q3.1.1", "which?", "500", "Yes", "1")
                buildRow("302", "3", "2", "2", "Q3.1.1", "which?", "501", "No", "0")

        return rows
    }
}
