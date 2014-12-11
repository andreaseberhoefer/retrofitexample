package client;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;

import com.github.retrofitexample.client.Document;
import com.github.retrofitexample.client.FooAPI;
import com.github.retrofitexample.client.FooBarErrorHandler;
import com.github.retrofitexample.client.FooBarException;
import com.github.retrofitexample.client.FooBarXMLConverter;
import com.github.retrofitexample.client.FooBasicAuthInterceptor;
import com.github.retrofitexample.client.Result;

public class TestFooApi {

	@Test
	public void testCrazy(){
		FooAPI api = createAPI();
		
		int count = 12;
		Result result = api.doSomethingCrazy(count, "");
		
		assertThat(result.getCount()).isEqualTo(count);
		assertThat(result.getCmd()).isEqualTo("crazy");
		
		List<Document> documents = result.getDocuments();
		for (Document doc : documents) {
			assertThat(doc.getName()).isNotEmpty();
			assertThat(doc.getOther()).isNull();
			assertThat(doc.getSomebody()).isNull();
		}		
	}

	@Test
	public void testCrazyWithFields(){
		FooAPI api = createAPI();
		
		int count = 12;
		Result result = api.doSomethingCrazy(count, "somebody,other");
		
		assertThat(result.getCount()).isEqualTo(count);
		assertThat(result.getCmd()).isEqualTo("crazy");
		
		List<Document> documents = result.getDocuments();
		for (Document doc : documents) {
			assertThat(doc.getName()).isNotEmpty();
			assertThat(doc.getOther()).isNotEmpty();
			assertThat(doc.getSomebody()).isNotEmpty();
		}		
	}
	
	@Test
	public void testSomethingStupid(){
		FooAPI api = createAPI();
		
		int count = 12;
		Result result = api.doStupidThings(count, "somebody, other");
	
		assertThat(result.getCount()).isEqualTo(count);
		assertThat(result.getCmd()).isEqualTo("stupidThings");
		
		List<Document> documents = result.getDocuments();
		assertThat(documents).hasSize(count);
	}

	@Test
	public void testSomethingStupidWithMagic(){
		FooAPI api = createAPI();
		
		int count = 12;
		Result result = api.doStupidThingsWithMagic(count, "somebody, other");
		
		assertThat(result.getCount()).isEqualTo(count);
		assertThat(result.getCmd()).isEqualTo("stupidThings");
		
		List<Document> documents = result.getDocuments();
		assertThat(documents).hasSize(result.getMagicCount());
	}
	
	
	@Test
	public void testExceptionHandling(){
		FooAPI api = createAPI();
		try {
			api.doSomethingWithountCommmand();
		} catch (Exception e) {
			assertThat(e).isInstanceOf(FooBarException.class);
			assertThat(e.getMessage()).isEqualTo("Missing command");
		}
	}
	

	private FooAPI createAPI() {
		RestAdapter adapter = new Builder()
		.setEndpoint("http://localhost:8080/foo")
		.setRequestInterceptor(new FooBasicAuthInterceptor())
		.setConverter(new FooBarXMLConverter())
		.setErrorHandler(new FooBarErrorHandler())
		.build();
		
		FooAPI api = adapter.create(FooAPI.class);
		return api;
	}
}
