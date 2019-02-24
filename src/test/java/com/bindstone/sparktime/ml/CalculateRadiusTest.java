package com.bindstone.sparktime.ml;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class CalculateRadiusTest {

    @Test
    public void mlCalculateTest() {
        File file = new File("./target/radiusFile.csv");
        generateTestFile(file);
        M01_RadiusCalculation.calculate(file);
    }


    private static void generateTestFile(File file) {
        String nl = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("width,height,radius").append(nl);

        for(int i = 1; i <= 50; i ++) {
            for(int j = 1; j <= 50; j++) {
                sb.append(i + "," + j + "," + (i + i + j + j)).append(nl);
            }
        }

        try {
            FileUtils.writeStringToFile(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

