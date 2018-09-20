package br.com.ionic.api.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	public static List<Integer> decodeList(String list){
		/*
		String[] vet = list.split(",");
		List<Integer> cat = new ArrayList<>();
		for(int i = 0; i < vet.length; i++) {
			cat.add(Integer.parseInt(vet[i]));
		}
		*/
		return Arrays
				.asList(list.split(","))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
	}
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
