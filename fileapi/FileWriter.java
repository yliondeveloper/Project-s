package xyz.starmc.fileapi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {

	public static void FilePacket(String Resposta, String Pergunta) throws IOException {
		String ConvertPergunta = Pergunta.toLowerCase().replace("�", "u").replace("�", "a").replace("�", "i")
				.replace("�", "e").replace("�", "o").replace("�", "a").replace("�", "e").replace("�", "c")
				.replace("como", "").replace("qual", "").replace("porque", "").replace("quando", "").replace("onde", "")
				.replace("quanto", "").replace("posso", "").replace("como", "").replace("por que", "").replace("?", "").replace("eu", "").replace("tu", "").replace("ele", "").replace("ela", "").replace("nos", "")
				.replace("nois", "").replace("vos", "").replace("eles", "").replace("elas", "").replace("pq", "")
				.replace("posso", "").replace("ql", "").replace("vejo", "").replace("olho", "");
		File FileBotName = new File("/root/BotAPI/"
				+ ConvertPergunta
				+ ".yml");
		if (!FileBotName.exists()) {
			FileBotName.createNewFile();
		}
		try {
			OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream("/root/BotAPI/"
					 + ConvertPergunta + ".yml"), "UTF-8");
			bufferOut.write(Resposta);
			bufferOut.flush();
			bufferOut.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
