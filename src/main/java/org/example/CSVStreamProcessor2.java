package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class CSVStreamProcessor2 {

    public static double calculateAverageAge(String csvFilePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(csvFilePath))) {
            return lines
                    .skip(1) // Skip header
                    .map(line -> {
                        String[] parts = line.split(",");
                        if (parts.length < 2) return null; // Skip malformed lines
                        try {
                            return Integer.parseInt(parts[1].trim()); // Parse Age
                        } catch (NumberFormatException e) {
                            return null; // Skip invalid numbers
                        }
                    })
                    .filter(age -> age != null)
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0); // Return 0.0 if no valid age entries
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\arsene\\CSVStreamProcessor2\\src\\main\\java\\org\\example\\people.csv"; // Use full path if needed
        try {
            double averageAge = calculateAverageAge(filePath);
            System.out.println(String.format("Average age: %.2f", averageAge));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
