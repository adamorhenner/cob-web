package com.ia.cobweb;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node {
    private List<Cell> cells = new ArrayList<Cell>();
    private Node parent;
    private List<Node> children = new ArrayList<Node>();

    public Node() {}

    public void addChild(Node newNode) {
        this.children.add(newNode);
        newNode.parent = this;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    public boolean isLeaf() {
        return this.children.isEmpty();
    }

}
