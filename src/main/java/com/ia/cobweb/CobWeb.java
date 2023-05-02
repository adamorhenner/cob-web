package com.ia.cobweb;

import java.util.HashMap;
import java.util.function.Predicate;

public class CobWeb {
    static void cobweb(Node root, Cell record) {
        if (root.isLeaf()) {
            Node l1 = new Node();
            Node l2 = new Node();
            l1.addCell(root.getCells().get(0));
            l2.addCell(record);

            root.addChild(l1);
            root.addChild(l2);
            root.addCell(record);
        } else {
            HashMap<Integer, Double> CategoryUtilities = new HashMap<>();
            Double bestScore;
            int key = 0;
            root.addCell(record);

            for (Node node : root.getChildren()) {
                node.addCell(record);
                CategoryUtilities.put(root.getChildren().indexOf(node), getCategoryUtility(root));
                node.getCells().removeIf(cell -> cell == record);
            }

            //bestScore = geBestScore(CategoryUtilities, key);

            bestScore = CategoryUtilities.get(0);
            for (int k = 0; k < CategoryUtilities.size(); k++) {
                if (bestScore < CategoryUtilities.get(k)) {
                    key = k;
                    bestScore = CategoryUtilities.get(k);
                }
            }

            Node newChild = new Node();
            newChild.addCell(record);
            root.addChild(newChild);

            if (bestScore > getCategoryUtility(root)) {
                root.getChildren().remove(newChild);
                cobweb(root.getChildren().get(key), record);
            }
        }

    }

    public static double getCategoryUtility(Node root) {
        double rootFrequencies = nodeProbabilites(root);

        return root.getChildren().stream()
                .mapToDouble(child -> {
                    double pNode = (double) child.getCells().size() / root.getCells().size();
                    double childFrequencies = nodeProbabilites(child);
                    return pNode * (childFrequencies - rootFrequencies);
                })
                .average()
                .orElse(0.0);
    }

    static Double nodeProbabilites(Node cluster) {
        Integer listSize = cluster.getCells().size();
        return Math.pow(countLight(cluster) / listSize, 2) +
                Math.pow(countDark(cluster) / listSize, 2) +
                Math.pow(countSingleTail(cluster) / listSize, 2) +
                Math.pow(countDoubleTail(cluster) / listSize, 2) +
                Math.pow(countOneCore(cluster) / listSize, 2) +
                Math.pow(countMultiCore(cluster) / listSize, 2);
    }

    static Double countLight(Node cluster) {
        return countCells(cluster, c -> c.getColor() == Colors.LIGHT);
    }

    static Double countDark(Node cluster) {
        return countCells(cluster, c -> c.getColor() == Colors.DARK);
    }

    static Double countSingleTail(Node cluster) {
        return countCells(cluster, c -> c.getTails() == Tails.SINGLE);
    }

    static Double countDoubleTail(Node cluster) {
        return countCells(cluster, c -> c.getTails() == Tails.DOUBLE);
    }

    static Double countOneCore(Node cluster) {
        return countCells(cluster, c -> c.getCore() == 1);
    }

    static Double countMultiCore(Node cluster) {
        return countCells(cluster, c -> c.getCore() > 1);
    }

    static Double countCells(Node cluster, Predicate<Cell> predicate) {
        return (double) cluster.getCells().stream().filter(predicate).count();
    }
}
