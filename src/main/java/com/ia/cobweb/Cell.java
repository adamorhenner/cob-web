package com.ia.cobweb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    public Colors color;
    public Tails tails;
    public Integer core;

    public Cell() {
    }

    public Cell(Colors color, Tails tails, Integer core) {
        this.color = color;
        this.tails = tails;
        this.core = core;
    }
}