package br.imd.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.request.SendMessage;

public class Telegram {

    final String groupName = "@securitImg";
	
	public void sendMessage(String message) {
		TelegramBot bot = TelegramBotAdapter.build("1015048530:AAGD-36AQowUfUK42jNQmzfgzI8kBbNF70c");
		bot.execute(new SendMessage(groupName, message));
	}
	
	
}
