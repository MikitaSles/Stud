import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Runner {
    private static class Segment {
        private double x1, y1, x2, y2;

        public Segment(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public double getLength() {
            return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        }
    }

    public static void main(String[] args) {
        List<Segment> segments = readSegmentsFromFile("C:\\Users\\Nik-Asus\\Desktop\\6.2\\untitled\\src\\in.txt");
        List<Map.Entry<Integer, Integer>> lengthCountList = calculateSegmentLengthCounts(segments);
        sortLengthCountList(lengthCountList);

        for (Map.Entry<Integer, Integer> entry : lengthCountList) {
            System.out.println(entry.getKey() + ";" + entry.getValue());
        }
    }

    private static List<Segment> readSegmentsFromFile(String filePath) {
        List<Segment> segments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Segment segment = parseSegment(line);
                segments.add(segment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return segments;
    }

    private static Segment parseSegment(String line) {
        String[] coordinates = line.split(" ");
        if (coordinates.length == 2) {
            String[] start = coordinates[0].replaceAll("[()]", "").split(";");
            String[] end = coordinates[1].replaceAll("[()]", "").split(";");
            double x1 = Double.parseDouble(start[0]);
            double y1 = Double.parseDouble(start[1]);
            double x2 = Double.parseDouble(end[0]);
            double y2 = Double.parseDouble(end[1]);
            return new Segment(x1, y1, x2, y2);
        } else {
            throw new IllegalArgumentException("Invalid segment format: " + line);
        }
    }

    private static List<Map.Entry<Integer, Integer>> calculateSegmentLengthCounts(List<Segment> segments) {
        Map<Integer, Integer> lengthCounts = new HashMap<>();

        for (Segment segment : segments) {
            int length = (int) Math.round(segment.getLength());
            lengthCounts.put(length, lengthCounts.getOrDefault(length, 0) + 1);
        }

        return new ArrayList<>(lengthCounts.entrySet());
    }

    private static void sortLengthCountList(List<Map.Entry<Integer, Integer>> lengthCountList) {
        lengthCountList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
    }
}
