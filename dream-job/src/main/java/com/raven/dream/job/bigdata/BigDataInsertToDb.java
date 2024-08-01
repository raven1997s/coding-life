package com.raven.dream.job.bigdata;

/**
 * Description:
 * date: 2024/8/1 14:43
 *
 * @author longjiaocao
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class BigDataInsertToDb {
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Bob", "Alice", "Tom", "Emily", "Michael", "Sarah", "David", "Laura"
    };
    private static final String[] LAST_NAMES = {
            "Doe", "Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Martinez"
    };
    private static final int NUM_RECORDS = 100000;

    public static void main(String[] args) {
        generatorInsertData();
//        generatorInsertSql();
    }

    private static void generatorInsertData() {
        String filePath = Paths.get("").toAbsolutePath().toString() + "/test_data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();
            for (int i = 1; i <= NUM_RECORDS; i++) {
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                int salary = 5000 + random.nextInt(5001); // Salary between 5000 and 10000
                writer.write(String.format("%s,%s,%d%n",  firstName, lastName, salary));
            }
            System.out.println("Insert statements generated successfully in " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generatorInsertSql() {
        String filePath = Paths.get("").toAbsolutePath().toString() + "/insert_statements.sql";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();
            for (int i = 1; i <= NUM_RECORDS; i++) {
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                int salary = 5000 + random.nextInt(5001); // Salary between 5000 and 10000
                writer.write(String.format("INSERT INTO employees ( first_name, last_name, salary) VALUES ( '%s', '%s', %d);%n", firstName, lastName, salary));
            }
            System.out.println("Insert statements generated successfully in " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
