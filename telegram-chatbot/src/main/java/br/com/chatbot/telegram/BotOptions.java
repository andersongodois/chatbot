package br.com.chatbot.telegram;

import br.com.chatbot.twitter.service.TwitterService;

public class BotOptions {
	
	private TwitterService ts = new TwitterService();
	
	protected String treatMsg(String message, String userName) throws Throwable {
		
		switch (message) {
		case "/help": 
			return "Bem vindo ao Telegram Chatbot "+ userName +"!\n"
					+ "Neste bot você poderá consultar informações "
					+ "sobre os tweets baseado em hashtags, usuários "
					+ "mais seguidos e histórico de chamadas, para "
					+ "começar basta digitar /start .\n"
					+ "Caso queira consultar uma hashtag, digite "
					+ "/hashtag <palavra> onde palavra é a hashtag "
					+ "que deseja, por exemplo, para #cachorro digite "
					+ "'cachorro'."
					+ "Caso queira consultar o histórico de consultas "
					+ "realizadas e a data da última consulta, digite /historico.";
		case "/start":
			return "Tudo bem "+ userName +", vamos começar então!\n"
					+ "Digite uma das opções abaixo:\n"
					+ "/help para saber mais sobre os comandos disponíveis.\n"
					+ "/hashtag <palavra> para consultar os usuários com mais seguidores e a quantidade de tweets do brasil.\n"
					+ "/historico para ver as hashtags consultadas.";
		case "/historico":
		case "/histórico":
			return ts.getHistory();
		default:
			if (message.startsWith("/hashtag ")) {
				return ts.searchHashtag(message.substring(9));
			} else {
				return "Opção inválida.\n"
					+ "Digite uma das opções abaixo:\n"
					+ "/hashtag <palavra> para consultar os usuários com mais seguidores e a quantidade de tweets do brasil.\n"
					+ "/historico para ver as hashtags consultadas.";
			}
		}
	}
	
}
