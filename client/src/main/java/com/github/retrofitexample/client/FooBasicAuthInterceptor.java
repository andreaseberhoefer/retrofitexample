package com.github.retrofitexample.client;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;

import retrofit.RequestInterceptor;

public class FooBasicAuthInterceptor implements RequestInterceptor{

	@Override
	public void intercept(RequestFacade request) {
		String userAndPassword = "foo:bar";
		String userAndPasswordBase64 = BaseEncoding.base64().encode(userAndPassword.getBytes(Charsets.UTF_8));
		request.addHeader("Authorization", "Basic "+userAndPasswordBase64);
	}

}
