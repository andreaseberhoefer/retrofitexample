package com.github.retrofitexample.client;

public class Document {

	private final int id;
	private final String name;
	private final String other;
	private final String somebody;
	
	public Document(int id, String name, String other, String somebody) {
		this.id = id;
		this.name = name;
		this.other = other;
		this.somebody = somebody;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getOther() {
		return other;
	}
	
	public String getSomebody() {
		return somebody;
	}
}
