package com.example.pokerchance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Util {
	
 public static String formatChance(float num) {	
	 return  num != 0 ?  String.format("%2.2f : 1", new BigDecimal(num).setScale(2, RoundingMode.UP).doubleValue()) : "" ; 
 }
	
 public static String formatUnit(float num) { 
	return  num != 0 ? String.format("%2.0f", num) : ""; 
 }
	
 public static String formatPercent(float num) {
	return num != 0 ? String.format("%2.2f", new BigDecimal(num).setScale(2, RoundingMode.UP).doubleValue()) + "%" : ""; 
 }
 

}
