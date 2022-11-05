import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static final int qtdLeitores = 10;
	
	public static void main(String[] args) 
	{
		ArrayList<String> linhas = new ArrayList<String>();
		ArrayList<Runnable> threads = new ArrayList<Runnable>();
		
		lerArquivo(linhas);
		populaListaThreads(threads);
		Collections.shuffle(threads); //Embaralha a lista de threads
		
		threads.forEach((x) -> System.out.println(x));
	}
	
	public static void populaListaThreads(List<Runnable> threads) {
		for (int i = 0; i < 100; i++) {
			if (i < qtdLeitores)
				threads.add(new Leitor());
			else
				threads.add(new Escritor());
		}
	}
	
	public static void lerArquivo(List<String> linhas) {
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "bd.txt"));
			String linha = "";
			
			while (true) {
				if (linha != null) 
					linhas.add(linha);
				else
					break;
				linha = buffer.readLine();
			}
			buffer.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
