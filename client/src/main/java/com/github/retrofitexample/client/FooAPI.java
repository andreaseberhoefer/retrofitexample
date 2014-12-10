package com.github.retrofitexample.client;

import retrofit.http.GET;
import retrofit.http.Query;

public interface FooAPI {

	@GET("/bar?cmd=stupidThings")
	public Result doStupidThings(@Query("count")int count, @Query("fields") String fields);
	
	@GET("/bar?cmd=stupidThings&magic=true&magicCount=true")
	public Result doStupidThingsWithMagic(@Query("count")int count, @Query("fields") String fields);
	
	@GET("/bar?cmd=crazy")
	public Result doSomethingCrazy(@Query("count")int count, @Query("fields") String fields);
	
	@GET("/bar?cmd=crazy&magic=true&magicCount=true")
	public Result doSomethingCrazyWithMagic(@Query("count")int count, @Query("fields") String fields);
	
	@GET("/bar")
	public Result doSomethingWithountCommmand();
	
}
