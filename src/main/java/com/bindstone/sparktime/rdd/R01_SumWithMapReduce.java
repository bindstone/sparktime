package com.bindstone.sparktime.rdd;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class R01_SumWithMapReduce {

    public static void main(String[] args) {
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        System.out.println("Start execution...");
        List<Double> inputData = new ArrayList<>();
        inputData.add(1.0);
        inputData.add(2.0);
        inputData.add(3.0);
        inputData.add(4.0);
        inputData.add(5.0);
        inputData.add(6.0);

        SparkConf conf = new SparkConf()
                .setAppName("Test")
                .setMaster("local[*]");

        try(JavaSparkContext sc = new JavaSparkContext(conf)) {

            JavaRDD<Double> rdd = sc.parallelize(inputData);

            // Attention -> Collect = Memory Consuming and Overflow Risk
            List<String> map = rdd.map(v1 -> "Val: " + v1).collect();
            map.forEach(System.out :: println);

            // Spark Worker
            Double result = rdd.reduce((v1, v2) -> v1 + v2);
            System.out.println("Sum: " + result);


        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("End execution.");
    }
}
