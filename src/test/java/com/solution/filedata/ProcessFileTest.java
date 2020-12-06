package com.solution.filedata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class ProcessFileTest {

    private String[] mockData = new String[]{"C00616920|N|YE|P|201801250300187861|15|IND|WATTS, RICHARD H|ST PETERSBURG|FL|33706|SELF|ATTORNEY|11302019|250||SA013018786150|1201654|||2022620181515929887",
            "C00616920|N|YE|P|201904039145995012|15|IND|BUCKLIN, ROBERT||FL|33541|UNK|UNK|01205201|250||SA013018784813|1201654|||2022620181515929850",
            "C00544908|A|YE|G2018|201904039145995012|15|IND|GONZALEZ, ROBERT J. MR.|MIAMI SPRINGS|FL|33166|FECI|DIRECTOR, CONSTRUCTION|12312018|20||0006958|1322255|||4040420191645959230"};


    @Test
    public void fileCountTest() throws IOException {


        ProcessFile processOp = new ProcessFile();
        long countActual = processOp.fileCount(Stream.of(mockData));
        assertEquals(3, countActual);

    }

    @Test
    public void processFileToGetNamesTest() {
        ProcessFile processOp = new ProcessFile();
        List<String> arrayNames = processOp.processFileToGetNames(Stream.of(mockData),1);
        assertArrayEquals(new String[]{"BUCKLIN, ROBERT"}, (arrayNames).toArray());

    }

    @Test
    public void processFileToGetDonationsEachMonthTest() {
        ProcessFile processOp = new ProcessFile();
        Map<String, Long> donationsPerMonth = processOp.processFileToGetDonationsEachMonth(Stream.of(mockData));

        assertEquals(2, donationsPerMonth.get("2019-04"));
        assertEquals(1, donationsPerMonth.get("2018-01"));

    }

    @Test
    public void processFileToGetFirstNameCountTest() {
        ProcessFile processOp = new ProcessFile();
        Map.Entry<String, Long> donationsPerMonth = processOp.processFileToGetFirstNameCount(Stream.of(mockData));

        assertEquals(2, donationsPerMonth.getValue());
        assertEquals("ROBERT", donationsPerMonth.getKey());

    }
}
