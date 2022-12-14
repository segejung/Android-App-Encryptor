package edu.gatech.seclass.texttool;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

	static final Pattern WORD = Pattern.compile("\\pL+");
	public static void main(String[] args) throws IllegalArgumentException {
		String arg = "";
		String result = "";
		String fcontent = "";
		String r_delimiter = "";
		String k_delimiter = "";
		String d_delimiter = "";
		String x_delimiter = "";
		Boolean writefile = true;
		int i = 0;
		boolean valid_opt = true;
		boolean file_exist = true;
		//see if the file has any argument inside.
		if(args == null || args.length == 0) {
			usage();
		}
		else
		{
			//see if the filename is not 0.
			String filename = args[args.length -1];
			if(filename == null || filename.length() == 0)
				file_not_found();

			if(filename.startsWith("-"))
			{
				usage();
			}
			else
			{
				//read the file to fcontent. try until you get it right.
				try
				{
					Path file_path = Paths.get(filename);
					if(Files.exists(file_path))
					{
						fcontent = readstring(filename);
					}
					else
					{
						file_not_found();
						file_exist = false;
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

				//option 1. encode file with no opts
				//check if there is opt or not
				if(args.length == 1 && file_exist)
				{
					result = empty_opt(fcontent);
				}



				//option 6. invalid opts
				if(valid_opt == false)
				{
					usage();
				}
				else
				{
					//write String to file
					if(file_exist)
					{
						if(writefile)
							writeStringFile(result,filename);
						else
							System.out.println(result);
					}
				}
			}
		}


    }

	private static String readstring(String filename) throws IOException
	{
		String fcontent = "";
		try
		{
			Path file_path = Paths.get(filename);
			byte[] encoded = Files.readAllBytes(file_path);
			fcontent = new String(encoded, "UTF-8");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return fcontent;
	}
	//If no opt, use -w as default. reverse using all non-alphabetic character as delimiter.
	private static String empty_opt(String fcontent) {
		Matcher m = WORD.matcher(fcontent);
		if(!m.find()) return fcontent;
		StringBuilder sb = new StringBuilder(fcontent);
		do {
			for(int ix1 = m.start(), ix2 = m.end() - 1; ix1 < ix2; ix1++, ix2--) {
				sb.setCharAt(ix1, fcontent.charAt(ix2));
				sb.setCharAt(ix2, fcontent.charAt(ix1));
			}
		} while(m.find());
		return sb.toString().trim();
	}

	//If d_opt,
	private static String d_opt(String fcontent, String d_delimiter) {
		//need to have a delimiter. Doesn't do anything.
		return fcontent;
	}
	//encode -w
	// Reverses characters in each word.
	private static String w_opt(String fcontent, String d_delimiter) {
		String delimiter = "\\s";

		if (d_delimiter != "") {
			delimiter = d_delimiter;
		}
		//if there is -w, then reverse characters in words separated by whitespace.
		String[] words = fcontent.split(delimiter);
		String reversedString = "";
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String reverseWord = "";

			for (int j = word.length() - 1; j >= 0; j--) {
				reverseWord = reverseWord + word.charAt(j);
			}
			reversedString = reversedString + reverseWord + " ";
		}

		return reversedString.trim();
	}



	//If r_opt, remove the character from the string. Non alphabetic characters are unaffected.
	private static String r_opt(String fcontent, String r_delimiter, String d_delimiter) {
		String delimiter = " ";
		if (d_delimiter != "") {
			char[] chars = d_delimiter.toCharArray();
			String result2 = "";
			for (int j = 0; j < d_delimiter.length(); j++) {
				String character2 = String.valueOf(fcontent.charAt(j));
				if (!fcontent.matches(String.valueOf(chars[j])))
					result2 = result2.concat(character2);
			}
			String result = "";
			for (int i = 0; i < fcontent.length(); i++) {
				String character = String.valueOf(fcontent.charAt(i));

				if (!character.matches("^[a-zA-Z0-9]*$") || !character.matches(String.valueOf(chars[0])) || !character.matches(String.valueOf(chars[1])) || !character.matches(String.valueOf(chars[2])) || !r_delimiter.contains(character.toUpperCase()) && !r_delimiter.contains(character.toLowerCase()))
					result = result.concat(character);

			}
			return result;
		} else {
			char[] chars = delimiter.toCharArray();
			String result2 = "";
			for (int j = 0; j < delimiter.length(); j++) {
				String character2 = String.valueOf(fcontent.charAt(j));
				if (!fcontent.matches(String.valueOf(chars[j])))
					result2 = result2.concat(character2);
			}
			String result = "";
			for (int i = 0; i < fcontent.length(); i++) {
				String character = String.valueOf(fcontent.charAt(i));

				if (!character.matches("^[a-zA-Z0-9]*$") || !r_delimiter.contains(character.toUpperCase()) && !r_delimiter.contains(character.toLowerCase()))
					result = result.concat(character);

			}
			return result;
		}

	}

	//If k_opt, keep the character from the string. Non alphabetic characters are unaffected.
	private static String k_opt(String fcontent, String k_delimiter, String d_delimiter, String x_delimiter) {
		String delimiter = " ";
		if (d_delimiter != "") {
			if (x_delimiter != "") {
				String result = "";
				for (int i = 0; i < fcontent.length(); i++) {
					String character = String.valueOf(fcontent.charAt(i));

					if (!character.matches("^[a-zA-Z0-9]*$") || character.matches(x_delimiter) || k_delimiter.contains(character.toUpperCase()) || k_delimiter.contains(character.toLowerCase()))
						result = result.concat(character);
				}
				return result;
			} else {
				String result = "";
				for (int i = 0; i < fcontent.length(); i++) {
					String character = String.valueOf(fcontent.charAt(i));

					if (!character.matches("^[a-zA-Z0-9]*$") || character.matches(d_delimiter) || k_delimiter.contains(character.toUpperCase()) || k_delimiter.contains(character.toLowerCase()))
						result = result.concat(character);
				}
				return result;
			}

		} else {
			if (x_delimiter != "") {
				String result = "";
				for (int i = 0; i < fcontent.length(); i++) {
					String character = String.valueOf(fcontent.charAt(i));

					if (!character.matches("^[a-zA-Z0-9]*$") || character.matches(x_delimiter) || k_delimiter.contains(character.toUpperCase()) || k_delimiter.contains(character.toLowerCase()))
						result = result.concat(character);
				}
				return result;
			} else {
				String result = "";
				for (int i = 0; i < fcontent.length(); i++) {
					String character = String.valueOf(fcontent.charAt(i));

					if (!character.matches("^[a-zA-Z0-9]*$") || character.matches(delimiter) || k_delimiter.contains(character.toUpperCase()) || k_delimiter.contains(character.toLowerCase()))
						result = result.concat(character);
				}
				return result;
			}

		}
	}

	//-x: if specified, the capitalize utility will flip the capitalization of all letters in the file,
	// after applying any other transformation.

	public static String x_opt(String fcontent, String x_delimiter, String d_delimiter) {


		String delimiter = "\\s";

		if (d_delimiter != "") {
			return fcontent.replaceAll(d_delimiter, x_delimiter);
		} else {
			return fcontent.replaceAll(delimiter, x_delimiter);
		}


	}


	//PERFECT: If -c, it will reverse the capitalization of lowercase to uppercase and uppercase to lowercase.
	private static String c_opt(String fcontent)
	{
		char[] chars = fcontent.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isUpperCase(c))
			{
				chars[i] = Character.toLowerCase(c);
			}
			else if (Character.isLowerCase(c))
			{
				chars[i] = Character.toUpperCase(c);
			}
		}
		return String.valueOf(chars).replaceAll("\\r"," ");
	}


	private static void writeStringFile(String result, String filename)
	{
		try (PrintWriter out = new PrintWriter(filename))
		{
			out.print(result);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void usage() {
		System.err.println("Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE");
	}
	
	private static void file_not_found()
	{
		System.err.println("File Not Found");
	}

}