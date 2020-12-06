package com.solution.filedata;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessFile {

    public static void main(String args[]) {
        ProcessFile processOp = new ProcessFile();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/itcont.txt"))){

            Supplier<Stream<String>> supplier = () -> reader.lines();

            List<String> name431 = processOp.processFileToGetNames(supplier.get(), 430);
            System.out.println("Name at 431st index: " + name431.get(0));

            List<String> names43241 = processOp.processFileToGetNames(supplier.get(), 43241);
            System.out.println("Name at 43241st index: " + names43241.get(0));

            long count = processOp.fileCount(supplier.get());
            System.out.println("File count: " +count);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/itcont.txt"))){

            Map<String, Long> donationsEachMonth = processOp.processFileToGetDonationsEachMonth(reader.lines());
            System.out.println("Donations each month: " + donationsEachMonth);


        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/itcont.txt"))){

            Map.Entry<String, Long> nameCount = processOp.processFileToGetFirstNameCount(reader.lines());
            System.out.println("Count per name: " + nameCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long fileCount(Stream<String> lines) {
        return lines.count();
    }

    public List<String> processFileToGetNames(Stream<String> lines, long index) {
        return lines.map((eachLine) -> eachLine.split("\\|")[7]).skip(index).limit(1).collect(Collectors.toList());
    }

    public Map<String, Long> processFileToGetDonationsEachMonth(Stream<String> lines) {
        return lines.parallel().map((eachLine) -> eachLine
                .split("\\|")[4].substring(0,6))
                .map((eachDate) -> eachDate.substring(0, 4) + "-" + eachDate.substring(4))
                .collect(Collectors.groupingBy((eachDate) -> eachDate, Collectors.counting()));
    }

    public Map.Entry<String, Long> processFileToGetFirstNameCount(Stream<String> lines) {
        Optional<Map.Entry<String, Long>> namesCount =  lines.parallel().map((eachLine) -> eachLine
                .split("\\|")[7])
                .map((eachName) -> eachName == null || eachName.length() == 0 ? "" : eachName.substring(eachName.indexOf(",") + 1).trim())
                .map((eachName) -> eachName.indexOf(" ") > -1 ? eachName.substring(0, eachName.indexOf(" ")).trim() : eachName.trim())
                .collect(Collectors.groupingBy((eachName) -> eachName, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());

        if(namesCount.isPresent()) {
            return namesCount.get();
        }

        return null;
    }


}
