package br.com.chatbot.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.chatbot.twitter.TwitterAPI;

@Path("/chatbot")
public class ChatbotService {

	private TwitterAPI twitter = new TwitterAPI();
	
	@GET
	@Path("/getTweets")
	public Response getLastHashtagMessage(@QueryParam("hashtag") String hashtag, @QueryParam("quantity") int quantity) {
		
		try {
			if (!hashtag.startsWith("#"))
				hashtag = "#" + hashtag;
			
			List<String> tweets = twitter.searchTweets(hashtag, quantity);
			
			return Response.status(200).entity(tweets.toString()).build();
		} catch (Throwable t) {
			return Response.status(400).entity("Ocorreu um erro inesperado para a hashtag: " + hashtag + ", causado por: " + t.getMessage()).build();
		}
	}

}