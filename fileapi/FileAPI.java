package xyz.starmc.fileapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileAPI {

	public static HashMap<String, String> botfunction = new HashMap<String, String>();
	public static HashMap<String, String> botfunctionsecondary = new HashMap<String, String>();

	public static String ReadFile(String file) throws IOException {
		BufferedReader Buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream("/root/BotAPI/" + file), "UTF-8"));
		String linha = Buffer.readLine();
		while (linha != null) {
			return linha;
		}
		Buffer.close();
		return "Erro total.";
	}

	public static void Converter() throws IOException {
		File file = new File("/root/BotAPI/");
		File afile[] = file.listFiles();
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			String caracter1 = arquivos.getName().toLowerCase().replace(" .yml", "");
			botfunction.put(caracter1.toLowerCase(), ReadFile(arquivos.getName()));
		}
	}

}
