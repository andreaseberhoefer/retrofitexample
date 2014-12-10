package com.github.retrofitexample.client;

import java.util.List;

public class Result {

	private final String cmd;
	
	private final int count;
	
	private final int magicCount;
	
	private final List<Document> documents;

	public Result(String cmd, int count, int magicCount,
			List<Document> documents) {
		this.cmd = cmd;
		this.count = count;
		this.magicCount = magicCount;
		this.documents = documents;
	}
	
	
	public String getCmd() {
		return cmd;
	}
	
	public int getCount() {
		return count;
	}
	
	public List<Document> getDocuments() {
		return documents;
	}
	
	public int getMagicCount() {
		return magicCount;
	}
	
}
