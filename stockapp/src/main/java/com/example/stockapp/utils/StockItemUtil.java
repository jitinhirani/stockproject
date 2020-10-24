package com.example.stockapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import com.example.stockapp.models.StockItem;

public class StockItemUtil {

	public static final String STOCK_ITEM_TYPE_REGULAR = "REGULAR";

	public static final String STOCK_ITEM_TYPE_LOAN = "LOAN";

	public static DateFormat dateFormatWithDot = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
	
	public static DateFormat dateFormatWithSlashes = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	
	public static DateFormat dateFormatWithSpaces = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
	
	public static DateFormat dateFormatLongWithSpaces = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
	
	public static DateFormat dateFormatWithHyphen = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	
	// mySQL date formatter
	public static DateFormat reverseDateFormatWithHyphen = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	
	public static DateFormat dateFormatOnlyYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
	
	public static Comparator<StockItem> COMPARATOR_STOCK_NAME_SUB1_SUB2 = new CustomStockNameSub12Comparator();
	
	public static Comparator<StockItem> COMPARATOR_STOCK_NAME_SUB1_SUB2_DATEOFENTRY = new CustomStockNameSub12AndDOEComparator();
	
	public static Comparator<StockItem> COMPARATOR_STOCK_NAME_SUB1_SUB2_SUB3 = new CustomStockNameSub123Comparator();
	
	public static Comparator<StockItem> COMPARATOR_STOCK_NAME_SUB1_SUB2_SUB3_DATEOFENTRY = new CustomStockNameSub123AndDOEComparator();
	
	public static Comparator<StockItem> COMPARATOR_STOCK_DEPT = new StockDepartmentComparator();
	
	public static Comparator<StockItem> COMPARATOR_STOCK_NAME = new StockNameComparator();
	
	public static String removeSingleQuote(String str) {
		if(str != null && str.contains("'")) {
			return str.replace("'", "");
		}
		return str;
	}

	private static class CustomStockNameSub12Comparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			int comparison = 0;
			if(s1 != null && s2 != null) {
				String string1 = s1.getName() + "-" + s1.getSubCategory1() + "-" + s1.getSubCategory2();
				String string2 = s2.getName() + "-" + s2.getSubCategory1() + "-" + s2.getSubCategory2();
				
				comparison = string1.compareTo(string2);
			}
			
			return comparison;
		}
	}
	
	private static class CustomStockNameSub123Comparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			int comparison = 0;
			if(s1 != null && s2 != null) {
				String string1 = s1.getName() + "-" + s1.getSubCategory1() + "-" + s1.getSubCategory2() + "-" + s1.getSubCategory3();
				String string2 = s2.getName() + "-" + s2.getSubCategory1() + "-" + s2.getSubCategory2() + "-" + s2.getSubCategory3();
				
				comparison = string1.compareTo(string2);
			}
			
			return comparison;
		}
	}
	
	private static class CustomStockNameSub12AndDOEComparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			int comparison = 0;
			if(s1 != null && s2 != null) {
				String string1 = s1.getName() + "-" + s1.getSubCategory1() + "-" + s1.getSubCategory2();
				String string2 = s2.getName() + "-" + s2.getSubCategory1() + "-" + s2.getSubCategory2();
				
				int otherComparison = 0; 
				otherComparison = string1.compareTo(string2);
				
				comparison = otherComparison;
				
				if(otherComparison == 0) {
					Date dateOfEntry1 = s1.getDateOfEntry();
					Date dateOfEntry2 = s2.getDateOfEntry();
					
					int dateComparison = 0;
					if(dateOfEntry1 != null && dateOfEntry2 != null) {
						dateComparison = dateOfEntry1.compareTo(dateOfEntry2);
					}
					
					comparison = dateComparison;
				}
			}
			
			return comparison;
		}
	}
	
	private static class CustomStockNameSub123AndDOEComparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			int comparison = 0;
			if(s1 != null && s2 != null) {
				String string1 = s1.getName() + "-" + s1.getSubCategory1() + "-" + s1.getSubCategory2() + "-" + s1.getSubCategory3();
				String string2 = s2.getName() + "-" + s2.getSubCategory1() + "-" + s2.getSubCategory2() + "-" + s2.getSubCategory3();
				
				int otherComparison = 0; 
				otherComparison = string1.compareTo(string2);
				
				comparison = otherComparison;
				
				if(otherComparison == 0) {
					Date dateOfEntry1 = s1.getDateOfEntry();
					Date dateOfEntry2 = s2.getDateOfEntry();
					
					int dateComparison = 0;
					if(dateOfEntry1 != null && dateOfEntry2 != null) {
						dateComparison = dateOfEntry1.compareTo(dateOfEntry2);
					}
					
					comparison = dateComparison;
				}
			}
			
			return comparison;
		}
	}
	
	private static class StockDepartmentComparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			if(s1 != null && s2 != null) {
				if(s1.getDepartment() != null) {
					String dept1 = s1.getDepartment().getName();
					if(dept1 != null && s2.getDepartment() != null) {
						return dept1.compareTo(s2.getDepartment().getName());
					}
				}
			}
			
			return 0;
		}
	}
	
	private static class StockNameComparator implements Comparator<StockItem> {

		public int compare(StockItem s1, StockItem s2) {
			if(s1 != null && s2 != null) {
				if(s1.getName() != null) {
					return s1.getName().compareTo(s2.getName());
				}
			}
			
			return 0;
		}
	}

	public static String convertDateToString(Date inputDate) {
		if(inputDate != null) {
			return dateFormatWithDot.format(inputDate);
		}
		return null;
	}

	public static Date convertStringToDate(String str) {
		if(str != null && !str.isEmpty()) {
			
			int indexOfDot = str.indexOf(".");
			if(indexOfDot > -1) {
				try {
					return dateFormatWithDot.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				int indexOfSlash = str.indexOf("/");
				if(indexOfSlash > -1) {
					try {
						 return dateFormatWithSlashes.parse(str);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	public static Date convertStringToYear(String str) {
		if(str != null && !str.isEmpty()) {
			try {
				// alternate and better logic to parse cases
				// for example - 29.10.2013, 1998.0, etc.
				/*
				String temp[] = str.split(".");
				if(temp != null && temp.length > 0) {
					if(temp.length > 2) {
						setDateOfReceipt(StockItemUtil.dateFormatWithDot.parse(str));
					}

					if(temp.length <= 2) {
						setDateOfReceipt(StockItemUtil.dateFormatOnlyYear.parse(temp[0]));
					}
				}
				 */

				// data coming as 1982.0 so removing . and reading year
				int indexOfDot = str.indexOf(".");
				if(str.length() == 6) {
					String tempString = str.substring(0, indexOfDot);
					if(tempString.length() == 4) {
						return dateFormatOnlyYear.parse(str);
					} else {
						return dateFormatWithDot.parse(str);
					}
				} else if (str.length() > 6) {
					if(indexOfDot > -1) {
						return dateFormatWithDot.parse(str);
					} else {
						int indexOfSlash = str.indexOf("/");
						if(indexOfSlash > -1) {
							return dateFormatWithSlashes.parse(str);
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}	
}
