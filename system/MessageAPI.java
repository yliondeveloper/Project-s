package xyz.starmc.system;

public class MessageAPI {

	/*
	 * Sistemas de Mensagens mencionado para o plugin todo.*\
	 */
	
	public static String onCommandConsoleBlock = "§c§lERRO §fApenas jogadores podem executar esse comando.";
    public static String onCommandNoPerm = "§c§lERRO §fVocê não possui permissão para executar esse comando.";
    
	public static String onCommandFailed(String commandcomplete) {
		return "§c§lERRO §fComando incorreto, utilize " + commandcomplete;
	}

}
