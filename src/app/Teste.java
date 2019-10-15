package app;

import java.io.File;
import java.io.IOException;


public class Teste {

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\temp\\");

		File[] lista = file.listFiles();
		
		for (int i = 0; i < lista.length; i++) {
			System.out.println(lista[i].getName());
			
			}

		}

	}