package hu.elte.softech.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hu.elte.softech.controller.*;
import hu.elte.softech.entity.*;
import hu.elte.softech.service.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LexiGoAppTests {

	@LocalServerPort
	private int port;

	@Autowired
	private UserController userC;

	@Autowired
	private UserService userS;

	@Autowired
	private TopicService topicS;

	@Autowired
	private EntryService entryS;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void controllerInjectTest() throws Exception {
		assertThat(userC).isNotNull();
	}

	@Test
	public void serviceInjectTest() throws Exception {
		assertThat(entryS).isNotNull();
	}

	//Tag tests
	@Test
	public void getTagReturnTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/tag/156",
				String.class)).contains("156");
	}

	@Test
	public void deleteTagTest() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();

	    String baseUrlDelete = "http://localhost:" + port + "/tag/delete/158";
	    URI uriDelete = new URI(baseUrlDelete);
	    restTemplate.delete(uriDelete);

	    String baseUrlGet= "http://localhost:" + port + "/tag/158";
	    URI uriGet = new URI(baseUrlGet);

	    Tag tagGet = restTemplate.getForObject(uriGet, Tag.class);

//	    ResponseEntity<Tag> result = restTemplate.getForEntity(uriGet, Tag.class);

	    assertThat(tagGet).isNull();
	}

	@Test
	public void checkJoinTableAfterDeleteTagTest() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();

	    String baseUrlDelete = "http://localhost:" + port + "/tag/delete/155";
	    URI uriDelete = new URI(baseUrlDelete);
	    restTemplate.delete(uriDelete);

	    String baseUrlGet= "http://localhost:" + port + "/topics/get";
	    URI uriGet = new URI(baseUrlGet);

	    String GetResult = restTemplate.getForObject(uriGet, String.class);

//	    ResponseEntity<Tag> result = restTemplate.getForEntity(uriGet, Tag.class);

	    assertThat(!GetResult.contains("155"));
	}

	//User tests
	@Test
	public void getUsersListTest() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();

	    final String baseUrl = "http://localhost:" + port + "/users";
	    URI uri = new URI(baseUrl);

	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("username303"));
	}

	@Test
	public void testUserEntity() {
		User testu = new User();
		testu.setEmail("test@");
		testu.setPassword("123");
		testu.setUsername("testu");
		testu.setRole("tester");
		String testUsername = userS.createUserr(testu).getUsername();

	    Assert.assertEquals(testUsername, testu.getUsername());
	}

	//Entry tests
	@Test
	public void editEntryTest() throws URISyntaxException {
		String testValue = "Test Value";

		RestTemplate restTemplate = new RestTemplate();
		String getUrl = "http://localhost:" + port + "/entry/160";
		URI getUri = new URI(getUrl);
	    String editUrl = "http://localhost:" + port + "/entry/edit/160";
	    URI editUri = new URI(editUrl);

	    Entry ened = restTemplate.getForObject(getUri, Entry.class);
	    ened.setValue(testValue);
	    restTemplate.put(editUri, ened);

	    assertThat(restTemplate.getForObject(getUri, String.class)).contains(testValue);
	}

	@Test
	public void checkTopicNameOfEntryTest() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		String getUrlE = "http://localhost:" + port + "/entry/161/get";
		URI getUriE = new URI(getUrlE);

	    EntryWD ewd = restTemplate.getForObject(getUriE, EntryWD.class);

	    String getUrlT = "http://localhost:" + port + "/topic/" + ewd.getTopic().getId().toString();
	    URI getUriT = new URI(getUrlT);
	    Topic topic = restTemplate.getForObject(getUriT, Topic.class);

	    Assert.assertEquals(ewd.getTopic(), topic);
	}

	//Topic tests
	@Test
	public void checkFollowTopicAfterDeleteTopicTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

	    String urlGet= "http://localhost:" + port + "/topic/225/";
	    URI uriGet = new URI(urlGet);
	    String getResult = restTemplate.getForObject(uriGet, String.class);

	    String urlDelete = "http://localhost:" + port + "/topic/delete/225";
	    URI uriDelete = new URI(urlDelete);
	    restTemplate.delete(uriDelete);

	    String urlGetFollowTopic= "http://localhost:" + port + "/user/303/topics/follow";
	    URI uriGetFollowTopic = new URI(urlGetFollowTopic);
	    String getResultFollowTopic = restTemplate.getForObject(uriGetFollowTopic, String.class);

	    assertThat(getResultFollowTopic).doesNotContain(getResult);
	}

}
