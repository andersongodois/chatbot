package br.com.chatbot.twitter;

import java.util.List;
import java.util.stream.Collectors;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterAPI {

	private Twitter getTwitterinstance() {
		Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
	}

	public List<String> searchtweets(String hashtag) throws TwitterException {
		// busca instancia do twitter
		Twitter twitter = getTwitterinstance();
		// cria qual valor a ser consultado
		Query query = new Query(hashtag);
		//seta numero de tweets para a busca
		query.count(100);
		// realiza a busca dos tweets
		QueryResult result = twitter.search(query);
		// retorna resultados em uma lista do tipo status
		List<Status> statuses = result.getTweets();
		// retorna o metodo com a lista das mensagens dos tweets
		return statuses.stream().map(item -> item.getText()).collect(Collectors.toList());
	}

	public static void main(String[] args) throws Exception {
		TwitterAPI tu = new TwitterAPI();
		List<String> tweets = tu.searchtweets("#coronavirusnobrasil");
		System.out.println(tweets);
	}

}