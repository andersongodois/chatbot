package br.com.chatbot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import br.com.chatbot.telegram.Bot;

@WebListener
public class InitializeListener implements ServletContextListener {
	
	TelegramBotsApi telegramBotApi = new TelegramBotsApi();

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		ApiContextInitializer.init();
		try {
			telegramBotApi.registerBot(new Bot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {
	}
}