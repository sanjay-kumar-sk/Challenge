package com.oracle.utils;

import com.oracle.model.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilsTest {

    @Test
    public void testReadFileAndTransform() throws IOException {
        List<Data> dataList = FileUtils.readFileAndTransform("src/main/resources/input.txt");
        assertEquals(5, dataList.size());
    }

    @Test
    public void testReadFileAndTransformWithException() {
        assertThrows(IOException.class, () -> FileUtils.readFileAndTransform(""));
    }

    @Test
    public void testTransform() {
        Data data = FileUtils.transform("3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s");
        assertEquals(new Data(3244332,2346,
                "eu_west","YellowTeam3","ProjectCarrot",4322), data);
    }

}