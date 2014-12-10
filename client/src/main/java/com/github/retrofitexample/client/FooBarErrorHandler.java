package com.github.retrofitexample.client;

import static org.joox.JOOX.$;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FooBarErrorHandler implements ErrorHandler{

	@Override
	public Throwable handleError(RetrofitError error) {
		Response response = error.getResponse();
		if(response.getStatus() == 500){
			try {
				String message = $(response.getBody().in()).find("message").text();
				return new FooBarException(message);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else{
			return new IllegalAccessError();
		}
	}

}
