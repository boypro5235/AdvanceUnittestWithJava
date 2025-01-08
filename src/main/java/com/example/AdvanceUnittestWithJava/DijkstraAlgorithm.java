package com.example.AdvanceUnittestWithJava;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DijkstraAlgorithm {

    public static Map<String, Integer> findShortestPaths(Map<String, Map<String, Integer>> graph, String startNode) {
        if (!graph.containsKey(startNode)) {
            throw new IllegalArgumentException("Start node not found in graph");
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        Set<String> unvisitedNodes = new HashSet<>(graph.keySet());

        // Initialize all nodes with max distance
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);  // Default value for all nodes
        }
        distances.put(startNode, 0);

        while (!unvisitedNodes.isEmpty()) {
            String currentNode = getNodeWithSmallestDistance(distances, unvisitedNodes);
            unvisitedNodes.remove(currentNode);

            if (distances.get(currentNode) == Integer.MAX_VALUE) {
                break;
            }

            // Check neighbors
            for (Map.Entry<String, Integer> neighbor : graph.getOrDefault(currentNode, Collections.emptyMap()).entrySet()) {
                int weight = neighbor.getValue();
                if (weight < 0) {
                    throw new IllegalArgumentException("Graph contains negative weight");
                }

                int newDist = distances.get(currentNode) + weight;
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    predecessors.put(neighbor.getKey(), currentNode);
                }
            }
        }

        return distances;
    }


    private static String getNodeWithSmallestDistance(Map<String, Integer> distances, Set<String> unvisitedNodes) {
        String smallestNode = null;
        int smallestDistance = Integer.MAX_VALUE;

        for (String node : unvisitedNodes) {
            int nodeDistance = distances.get(node);
            if (nodeDistance < smallestDistance) {
                smallestDistance = nodeDistance;
                smallestNode = node;
            }
        }

        return smallestNode;
    }
}

