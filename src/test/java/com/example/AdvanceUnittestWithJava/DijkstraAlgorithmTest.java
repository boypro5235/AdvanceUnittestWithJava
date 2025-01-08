package com.example.AdvanceUnittestWithJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@SpringBootTest
public class DijkstraAlgorithmTest {

    @Autowired
    private DijkstraAlgorithm dijkstraAlgorithm;  // Tiêm đối tượng DijkstraAlgorithm (nếu bạn có class này là một Bean Spring)

    @Test
    public void testFindShortestPaths_basicGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 1, "C", 4));
        graph.put("B", Map.of("A", 1, "C", 2, "D", 5));
        graph.put("C", Map.of("A", 4, "B", 2, "D", 1));
        graph.put("D", Map.of("B", 5, "C", 1));

        Map<String, Integer> result = dijkstraAlgorithm.findShortestPaths(graph, "A");

        assertEquals(0, result.get("A"));
        assertEquals(1, result.get("B"));
        assertEquals(3, result.get("C"));
        assertEquals(4, result.get("D"));
    }

    @Test
    public void testFindShortestPaths_withNegativeWeight() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 1));
        graph.put("B", Map.of("A", -1, "C", 2));
        graph.put("C", Map.of("B", 2));

        // Dijkstra không hỗ trợ trọng số âm, nên kết quả không hợp lệ hoặc phát sinh lỗi
        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.findShortestPaths(graph, "A"));
    }

    @Test
    public void testFindShortestPaths_emptyGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.findShortestPaths(graph, "A"));
    }

    @Test
    public void testFindShortestPaths_largeGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        // Thêm một số lượng lớn các nút và cạnh vào đồ thị để kiểm tra hiệu suất
        for (int i = 0; i < 1000; i++) {
            String node = String.valueOf(i);
            graph.put(node, new HashMap<>());
            if (i > 0) {
                graph.get(String.valueOf(i - 1)).put(node, 1);
                graph.get(node).put(String.valueOf(i - 1), 1);
            }
        }

        Map<String, Integer> result = dijkstraAlgorithm.findShortestPaths(graph, "0");

        // Kiểm tra với một vài nút
        assertEquals(0, result.get("0"));
        assertEquals(1, result.get("1"));
        assertEquals(999, result.get("999"));
    }

    @Test
    public void testFindShortestPaths_invalidStartNode() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 1));
        graph.put("B", Map.of("A", 1));

        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.findShortestPaths(graph, "C"));
    }
}


