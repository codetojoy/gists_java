package net.codetojoy;

import java.util.*;
import java.util.stream.*;

import net.codetojoy.data.*;

class CaseIdCursor {
    String lastCaseId;
}

public class ParserMain {
    private DataSource dataSource = new SimpleDataSource();

    public static void main(String... args) {
        try {
            ParserMain parserMain = new ParserMain();
            int which = 2;

            if (which == 1) {
                parserMain.onBeginProcessingList();
            } else if (which == 2) {
                parserMain.onBeginProcessingStream();
            }
        } catch (Exception ex) {
            System.err.println("TRACER caught exception ex: " + ex.getMessage());
        }
        System.out.println("Ready.");
    }

    protected void onBeginProcessingStream() {
        Stream<String> dataInfoStream = dataSource.getData();

        final CaseIdCursor caseIdCursor = new CaseIdCursor();
        dataInfoStream.forEach(dataInfoString -> processDataInfoStream(dataInfoString, caseIdCursor));
    }

    protected void processDataInfoStream(String dataInfoString, CaseIdCursor caseIdCursor) {
        DataInfo dataInfo = dataSource.getDataInfo(dataInfoString);
        String caseId = dataInfo.caseId;

        if (! caseId.equals(caseIdCursor.lastCaseId)) {
            // new boundary, so done
            caseIdCursor.lastCaseId = caseId;
            System.out.println("TRACER caseId : " + caseId);
        }
    }

    protected void onBeginProcessingList() {
        Stream<String> dataInfoStream = dataSource.getData();
        List<String> dataInfoStrings = dataInfoStream.collect(Collectors.toList());

        String lastCaseId = null;
        for (String dataInfoString : dataInfoStrings) {
            lastCaseId = processDataInfoList(dataInfoString, lastCaseId);
        }
    }

    protected String processDataInfoList(String dataInfoString, String lastCaseId) {
        String result = lastCaseId;
        DataInfo dataInfo = dataSource.getDataInfo(dataInfoString);
        String caseId = dataInfo.caseId;
        System.out.println("TRACER caseId : " + caseId);
        /*

        if (! caseId.equals(lastCaseId)) {
            // new boundary, so done
            lastCaseId = caseId;
        }
        */

        return result;
    }
}
