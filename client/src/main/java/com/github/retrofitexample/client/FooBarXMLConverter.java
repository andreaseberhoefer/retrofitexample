package com.github.retrofitexample.client;

import static org.joox.JOOX.$;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.joox.Match;
import org.w3c.dom.Element;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class FooBarXMLConverter implements Converter{

	@Override
	public Object fromBody(TypedInput body, Type type)
			throws ConversionException {
		
		try {			
			List<Document> documents = new ArrayList<>();
			InputStream in = body.in();
			Match response = $(in);				
			String cmd = $(response).find("cmd").text();
			
			int count = Integer.parseInt($(response).find("count").text());
			Match magicCountElement = $(response).find("magicCount");
			int magicCount = magicCountElement.text() == null? -1 : Integer.parseInt(magicCountElement.text());
			
			Match allDocuments = response.find("document");
			
			for (Element element : allDocuments) {
				int id = Integer.parseInt($(element).child("id").text());
				String name = $(element).child("name").text();
				String other = $(element).child("other").text();
				String somebody = $(element).child("somebody").text();
				
				documents.add(new Document(id, name,other, somebody));
			}	
			
			return new Result(cmd, count, magicCount, documents);
			
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}

	@Override
	public TypedOutput toBody(Object obj) {
		return null;
	}

}
