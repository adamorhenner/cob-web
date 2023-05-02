package com.ia.cobweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CobWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(CobWebApplication.class, args);

		Node root = new Node();
		root.addCell(new Cell(Colors.LIGHT, Tails.SINGLE, 1));
		CobWeb.cobweb(root, new Cell(Colors.DARK, Tails.DOUBLE, 2));
		CobWeb.cobweb(root, new Cell(Colors.LIGHT, Tails.SINGLE, 2));
		//adicionando uma celular dark double
		//CobWeb.cobweb(root, new Cell(Colors.DARK, Tails.DOUBLE, 2));

		System.out.println("Arvore principal: " + root.toString());
		System.out.println("Filhos: " + root.getChildren().toString());
		System.out.println("--------------------------------------------");
		int index = 1 ;
		for (Node node : root.getChildren()) {
			System.out.println("---- Filho da arvore principal: " + index + "-----");
			System.out.println("Nó: " + node.toString());
			System.out.println("Filhos: " + node.getChildren().toString());
			System.out.println("Celulas: " + node.getCells().toString());
			index++;
		}

	}

}
