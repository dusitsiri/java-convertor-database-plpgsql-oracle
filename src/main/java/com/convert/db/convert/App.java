package com.convert.db.convert;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String string = "decode (a.tf_status ,'NTF','NT','TF','TR','TR'  ) tf_status ,";
    	
    	String[] decodeArry = string.substring(string.indexOf("(")+1, string.indexOf(")")).split(",");
    	
    	for (int i = 0; i < decodeArry.length; i++) {
			String string1 = decodeArry[i];
			System.out.println(string1);
		}
    }
}
