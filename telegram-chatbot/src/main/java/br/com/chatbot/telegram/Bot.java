package br.com.chatbot.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
	
	BotOptions options = new BotOptions();
	
	/**
	 * Method for receiving messages.
	 * 
	 * @param update Contains a message from the user.
	 */
	@Override
	public void onUpdateReceived(Update update) {
		String chatId = update.getMessage().getChatId().toString();
		try {
			String userMessage = update.getMessage().getText();
			String user = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
			String returnMsg = options.treatMsg(userMessage, user);
			sendMsg(chatId, returnMsg);
		} catch (Throwable t) {
			t.printStackTrace();
			if (update.getMessage().getText()!=null)
				sendMsg(chatId, "Ocorreu um erro inesperado, tente novamente.");
		}
	}
	
	/**
	 * Method for creating a message and sending it.
	 * 
	 * @param chatId chat id
	 * @param returnMsg The String that you want to send as a message.
	 */
	public synchronized void sendMsg(String chatId, String returnMsg) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(returnMsg);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns the bot's name, which was specified during registration.
	 * 
	 * @return bot name
	 */
	@Override
	public String getBotUsername() {
		return "tweet_chatbot";
	}

	/**
	 * This method returns the bot's token for communicating with the Telegram
	 * server
	 * 
	 * @return the bot's token
	 */
	@Override
	public String getBotToken() {
		return "xxxxxxxx";
	}
	
}
