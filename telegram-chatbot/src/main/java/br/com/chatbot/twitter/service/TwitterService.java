package br.com.chatbot.twitter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import br.com.chatbot.twitter.api.TwitterAPI;
import br.com.chatbot.twitter.entity.History;
import twitter4j.Status;
import twitter4j.User;

public class TwitterService {
	
	private TwitterAPI api = new TwitterAPI(); 
	public static List<History> listHistory = new ArrayList<History>();

	public String searchHashtag(String hashtag) throws Throwable {
		StringBuilder result = new StringBuilder();
		
		if (!hashtag.startsWith("#")) {
			hashtag = "#" + hashtag;
		}
		List<Status> tweets = api.searchTweets(hashtag, 100);
		
		//busca 5 usuários com mais seguidores
		result.append("Usuários com maiores seguidores:\n");
		result.append(getMostFollowedUsers(tweets));
		
		result.append("\n");
		
		//quantidade de tweets retornados
		result.append("Número de tweets do Brasil retornados: " + getNumberOfPortugueseTweets(tweets));
		
		//quantidade de tweets por hora
		//falta do banco de dados/tempo
		
		
		listHistory.add(new History(hashtag, new Date()));
		
		return result.toString();
	}
	
	private int getNumberOfPortugueseTweets(List<Status> tweets) {
		int count = 0; 
		for (Status status : tweets) {
			if (status.getLang().equals("pt")) count++;
		}
		return count;
	}
	
	private String getMostFollowedUsers(List<Status> tweets) {
		List<User> users = new ArrayList<User>();
		for (Status status : tweets) {
			users.add(status.getUser());
		}
		
		Collections.sort(users, new Sortbyfollowers());
		StringBuilder topFollowedUsers = new StringBuilder();
		int usersCount = 0;
		for (int i=0;usersCount<5; i++) {
			User user = users.get(i);
			if (topFollowedUsers.length()>0) topFollowedUsers.append("\n");
			if (!topFollowedUsers.toString().contains(user.getName())) {
				topFollowedUsers.append(user.getName()+" - "+user.getFollowersCount());
				usersCount++;
			}
		}
		
		return topFollowedUsers.toString();
	}
	
	public String getHistory() {
		if (listHistory.isEmpty()) {
			return "Nenhuma chamada foi realizada até o momento.";
		}
		
		StringBuilder historyLog = new StringBuilder();
		Date maxUpdateDate = null;
		for (History history : listHistory) {
			if (historyLog.length()==0) historyLog.append("Últimas hashtags consultadas:\n");
			historyLog.append(history.getHashtag()+"\n");
			
			if (maxUpdateDate==null) maxUpdateDate = history.getUpdateDate();
			else maxUpdateDate = maxUpdateDate.before(history.getUpdateDate())?history.getUpdateDate():maxUpdateDate;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		historyLog.append("Última data de atualização: "+ sdf.format(maxUpdateDate));
		
		return historyLog.toString();
	}
	
	class Sortbyfollowers implements Comparator<User> 
	{ 
	    public int compare(User a, User b) 
	    { 
	        return b.getFollowersCount() - a.getFollowersCount(); 
	    } 
	} 
	
}
