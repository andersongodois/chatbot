package br.com.chatbot.twitter.api;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterAPI {

	private List<Status> tweets = new ArrayList<Status>();

	private Twitter getTwitterinstance() {
		Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
	}

	public List<Status> searchTweets(String hashtag, int quantity) throws TwitterException {
		// busca instancia do twitter
		Twitter twitter = getTwitterinstance();
		// cria qual valor a ser consultado
		Query query = new Query(hashtag);
		// tipo de tweets a consultar
		query.setResultType(Query.RECENT);
		// seta numero de tweets para a busca
		query.count(quantity + 1);
		// realiza a busca dos tweets
		QueryResult result = twitter.search(query);
		// retorna resultados em uma lista do tipo status
		return result.getTweets();
	}
	
}