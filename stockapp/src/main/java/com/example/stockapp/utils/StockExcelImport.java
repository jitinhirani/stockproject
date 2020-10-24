package com.example.stockapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.stockapp.models.Category;
import com.example.stockapp.models.Department;
import com.example.stockapp.models.Source;
import com.example.stockapp.models.StockItem;
import com.example.stockapp.models.StockItemType;
import com.example.stockapp.models.StockType;
import com.example.stockapp.repositories.CategoryRepository;
import com.example.stockapp.repositories.DepartmentRepository;
import com.example.stockapp.repositories.SourceRepository;
import com.example.stockapp.repositories.StockItemRepository;
import com.example.stockapp.repositories.StockItemTypeRepository;
import com.example.stockapp.repositories.StockTypeRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockExcelImport {

	@Autowired
	private StockItemRepository stockItemRepository;
	
	@Autowired
	private StockItemTypeRepository stockItemTypeRepository;

	@Autowired
	private SourceRepository sourceRepository;

	@Autowired
	private StockTypeRepository stockTypeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private int firstRowNumWithData;
	
	private static String tempSheetName = null;

	public StockItemRepository getStockItemRepository() {
		return stockItemRepository;
	}

	public void setStockItemRepository(StockItemRepository stockItemRepository) {
		this.stockItemRepository = stockItemRepository;
	}

	public StockItemTypeRepository getStockItemTypeRepository() {
		return stockItemTypeRepository;
	}

	public void setStockItemTypeRepository(StockItemTypeRepository stockItemTypeRepository) {
		this.stockItemTypeRepository = stockItemTypeRepository;
	}

	public SourceRepository getSourceRepository() {
		return sourceRepository;
	}

	public void setSourceRepository(SourceRepository sourceRepository) {
		this.sourceRepository = sourceRepository;
	}

	public StockTypeRepository getStockTypeRepository() {
		return stockTypeRepository;
	}

	public void setStockTypeRepository(StockTypeRepository stockTypeRepository) {
		this.stockTypeRepository = stockTypeRepository;
	}

	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}

	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
//		File file = new File("C:\\Stock_Import_Example.xls");
//		File file = new File(".\\sampleData\\Correction_from_STOCK 2009 final list-20MARCH2011.xls");
//		File file = new File(".\\sampleData\\STOCK 2012 - Jitin 240711.xls");
//		File file = new File(".\\sampleData\\STOCK 2012 - Jitin 23.06.2012.xls");
//		File file = new File(".\\sampleData\\stock1- Jitin 23.06.2012.xls");
//		File file = new File(".\\sampleData\\stock2 - Jitin 22.08.2012.xls");
//		File file = new File(".\\finalData\\stock1- Jitin 23.06.2012.xls");
		
//		File file = new File(".\\finalData\\Sample Stock Data.xls");
		
		File file = new File("\\finalData");
		System.out.println(file.getAbsolutePath());
		
		// TODO: CAUTION-This will delete all stock items from database before starting import
		StockExcelImport stockImport = new StockExcelImport();
		StockItemRepository stockRepo = stockImport.getStockItemRepository();
		if(stockRepo != null) {
			stockRepo.deleteAll();
			stockRepo.resetSequence();
		}
		
		// if(file != null && file.exists() && file.isDirectory()) {
		// 	File[] importFiles = file.listFiles();
			
		// 	if(importFiles != null && importFiles.length > 0) {
		// 		for(File importFile : importFiles) {
		// 			System.out.println(importFile.getAbsolutePath());
		// 			if (importFile != null && importFile.exists() && importFile.isFile()) {
		// 				if(importFile.getName().contains("Stock as on today")) {
		// 					StockItemType regular = stockImport.getStockItemTypeRepository().findByName(StockItemUtil.STOCK_ITEM_TYPE_REGULAR);
		// 					stockImport.importStockFromExcel(importFile, regular);
		// 				} else {
		// 					StockItemType loan = stockImport.getStockItemTypeRepository().findByName(StockItemUtil.STOCK_ITEM_TYPE_LOAN);
		// 					stockImport.importStockFromExcel(importFile, loan);
		// 				}
		// 			} else {
		// 				System.out.println("Problems while reading file: " + importFile.getName());
		// 			}
		// 		}
		// 	}
		// }
	}

	public boolean importStockFromExcel(File excelFile, StockItemType stockItemType) {
		if (excelFile != null && excelFile.exists() && excelFile.isFile()) {
			try {
				FileInputStream inputStream = new FileInputStream(excelFile);
				Workbook workbook = WorkbookFactory.create(inputStream);
				//IStockItemDAO stockItemDAO = StockAppDAOFactory.getStockItemDAO();
				StockItemRepository stockRepo = this.getStockItemRepository();

				if (workbook != null && stockRepo != null) {
					int countOfImportedStockItems = 0;
					for(int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
						Sheet sheet = workbook.getSheetAt(sheetNum);
						if (sheet != null) {
							
							List<Cell> headerCellList = this.getHeaderCells(sheet);
							int firstRowNumWithData = -1;
							if (this.firstRowNumWithData > 0) {
								firstRowNumWithData = this.firstRowNumWithData;
							}

							if (headerCellList != null && !headerCellList.isEmpty() && firstRowNumWithData > 0L) {
								for (int rowNum = firstRowNumWithData; rowNum <= sheet.getLastRowNum(); rowNum++) {
									Row row = sheet.getRow(rowNum);
									if (row != null) {
//										try {
										StockItem stockItem = this.createStockItem(headerCellList, row, stockItemType);
										if (stockItem != null) {
											stockRepo.save(stockItem);
											countOfImportedStockItems++;
										}
//										} catch(Exception e) {
//											e.printStackTrace();
//											break;
//										}										
									}
								}
							}
						}
					}
					
					System.out.println("------------------------ " + excelFile.getName() + ": Total Imported Records --> " + countOfImportedStockItems + " --------------------------------\n\n");
				}
			} catch (InvalidFormatException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
	
// 	public boolean importStockFromExcel(InputStream inputStream, String itemType) {
// 		StockExcelImport stockImport = new StockExcelImport();
// 		if (inputStream != null) {
// 			try {
// 				Workbook workbook = WorkbookFactory.create(inputStream);
// 				IStockItemDAO stockItemDAO = StockAppDAOFactory.getStockItemDAO();
// 				if (workbook != null && stockItemDAO != null) {
// 					int countOfImportedStockItems = 0;
// 					for(int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
// 						Sheet sheet = workbook.getSheetAt(sheetNum);
// 						if (sheet != null) {
							
// 							List<Cell> headerCellList = stockImport.getHeaderCells(sheet);
// 							int firstRowNumWithData = -1;
// 							if (stockImport.firstRowNumWithData > 0) {
// 								firstRowNumWithData = stockImport.firstRowNumWithData;
// 							}

// 							if (headerCellList != null && !headerCellList.isEmpty() && firstRowNumWithData > 0L) {
// 								for (int rowNum = firstRowNumWithData; rowNum <= sheet.getLastRowNum(); rowNum++) {
// 									Row row = sheet.getRow(rowNum);
// 									if (row != null) {
// //										try {
// 										StockItem stockItem = stockImport.createStockItem(headerCellList, row, itemType);
// 										if (stockItem != null) {
// 											stockItemDAO.addStockItem(stockItem);
// 											countOfImportedStockItems++;
// 										}
// //										} catch(Exception e) {
// //											e.printStackTrace();
// //											break;
// //										}
// 									}
// 								}
// 							}
// 						}
// 					}
					
// 					System.out.println("------------------- Total Imported Records --> " + countOfImportedStockItems + " -----------------------\n\n");
// 				}
// 			} catch (InvalidFormatException e) {
// 				e.printStackTrace();
// 				return false;
// 			} catch (IOException e) {
// 				e.printStackTrace();
// 				return false;
// 			}
// 		}

// 		return true;
// 	}

	private StockItem createStockItem(List<Cell> headerCellList, Row row, StockItemType stockItemType) //	throws Exception
	{
		StockItem stockItem = null;
//		try {
			if (headerCellList != null && !headerCellList.isEmpty() && row != null) {
				// creating blank stock item
				stockItem = new StockItem();
				stockItem.setStockItemType(stockItemType);

				// printing department name only for once when it is new or changed
				if(tempSheetName == null) {
					tempSheetName = row.getSheet().getSheetName();
					System.out.println("Processing Department -->  " + tempSheetName + " \nProcessing Row --> " + (row.getRowNum()+1));
				} else {
					if(!tempSheetName.equalsIgnoreCase(row.getSheet().getSheetName())) {
						tempSheetName = row.getSheet().getSheetName();
						System.out.println("Processing Department -->  " + tempSheetName + " \nProcessing Row --> " + (row.getRowNum()+1));
					} else {
						System.out.println("Processing Row --> " + (row.getRowNum()+1));
					}
				}
				
				for (int cellnum = 0; cellnum < headerCellList.size(); cellnum++) {
					Cell headerCell = headerCellList.get(cellnum);
					int rowLastCellNum = row.getLastCellNum();
					if (cellnum >= 0 && cellnum < rowLastCellNum) {
						Cell cell = row.getCell(cellnum);
						if (cell != null) {
//							System.out.println("Processing Column --> " + headerCell.getStringCellValue());
							String value = "";
							try {
								if(CellType.NUMERIC.equals(cell.getCellType())) {
									if(DateUtil.isCellDateFormatted(cell)) {
										Date date = cell.getDateCellValue();
										if(date != null) {
											value += "" + StockItemUtil.dateFormatWithSlashes.format(date);
										}
									} else {
										value += "" + cell.getNumericCellValue();
									}
								} else if(CellType.STRING.equals(cell.getCellType())) {
									value += cell.getStringCellValue();
								} else if(CellType.BOOLEAN.equals(cell.getCellType())) {
									value += "" + cell.getBooleanCellValue();
								} else {
									value += cell.getStringCellValue();
								}
							} catch (Exception e) {
								System.out.println("Processing Column --> " + headerCell.getStringCellValue());
								System.out.println("Exception - ");
								e.printStackTrace();
								// severe error breaking the import
								System.exit(0);
							}

							String headerCellName = headerCell.getStringCellValue();
							if (headerCellName != null && !headerCellName.isEmpty() && value != null) {
								headerCellName = headerCellName.toLowerCase();
								value = value.trim();
								if(headerCellName.contains("s.no.") || headerCellName.contains("s. no.") || headerCellName.contains("s no")) {
									// System.out.println("Processing S. No. --> " + value);
									continue;
								} else if (headerCellName.contains("date") && headerCellName.contains("entry")) {
									stockItem.setDateOfEntry(StockItemUtil.convertStringToDate(value));
									continue;
								} else if (headerCellName.contains("name") && headerCellName.contains("item")) {
									stockItem.setName(value);
									continue;
								} else if (headerCellName.contains("sub") && headerCellName.contains("category")) { 
									if(headerCellName.contains("1")) {
										stockItem.setSubCategory1(value);
										continue;
									} else if(headerCellName.contains("2")) {
										stockItem.setSubCategory2(value);
										continue;
									} else if(headerCellName.contains("3")) {
										stockItem.setSubCategory3(value);
										continue;
									}
								} else if (headerCellName.contains("date") && headerCellName.contains("receipt")) {
									stockItem.setDateOfReceipt(StockItemUtil.convertStringToYear(value));
									continue;
								}  else if (headerCellName.contains("letter no")) {
									// TODO: check added for letter number. pls uncomment when needed
									// if(value == null || value.isEmpty()) {
										// System.err.println("Letter no is empty");
										// throw new NullPointerException();
									// } else {
									// stockItem.setLetterNumber(value);
									// continue;
									// }
									stockItem.setLetterNumber(value);
								} else if (headerCellName.contains("source")) {
									if(value.isEmpty()) {
										System.err.println("Source is blank.");
										continue;
									}
									if(value.toLowerCase().contains("beas")) {
										value = "Dera";
									}
									
									Source source = null;
									source = this.getSourceRepository().findByName(value);
									if (source != null && source.getName() != null) {
										stockItem.setSource(source);
										continue;
									} else {
										System.err.println("source not found - " + value);
									}
								} else if (headerCellName.contains("sentto") || headerCellName.contains("sent to")) {
									stockItem.setSentTo(value.toLowerCase());
									continue;
								} else if (headerCellName.contains("received") && headerCellName.contains("from")) {
									stockItem.setReceivedFrom(value.toLowerCase());
									continue;
								} else if (headerCellName.contains("size")) {
									stockItem.setSize(value);
									continue;
								} else if (headerCellName.contains("cost")) {
									if(value.isEmpty()) {
										continue;
									}
									stockItem.setCost(Double.parseDouble(value));
									continue;
								} else if ((headerCellName.contains("qty") 
										|| headerCellName.contains("quantity")) && !headerCellName.contains("balance")) {
									if(value.isEmpty()) {
										continue;
									}
									stockItem.setQuantity(Double.valueOf(value).longValue());
									
									StockType stockType = null;
									if (headerCellName.contains("received")) {
										stockType = this.getStockTypeRepository().findByName("Received");
										if (stockType != null) {
											stockItem.setStockType(stockType);
											continue;
										}
									} else if (headerCellName.contains("transfer")) {
										stockType = this.getStockTypeRepository().findByName("Transfer");
										if (stockType != null) {
											stockItem.setStockType(stockType);
											continue;
										}
									} else if (headerCellName.contains("loan")) {
										stockType = this.getStockTypeRepository().findByName("Loan");
										if (stockType != null) {
											stockItem.setStockType(stockType);
											continue;
										}
									} else if (headerCellName.contains("written off")) {
										stockType = this.getStockTypeRepository().findByName("Written Off");
										if (stockType != null) {
											stockItem.setStockType(stockType);
											continue;
										}
									}
								} else if (headerCellName.contains("dept")) {
									if(value.isEmpty()) {
										System.err.println("Department is blank.");
										continue;
									}

									Department department = null;
									department = this.getDepartmentRepository().findByName(value);

									if (department != null && department.getName() != null) {
										stockItem.setDepartment(department);
										continue;
									} else {
										System.err.println("Department not found - " + value);
									}
								} else if (headerCellName.contains("category")) {
									if(value.isEmpty()) {
										System.err.println("Category is blank.");
										continue;
									}

									Category category = null;
									category = this.getCategoryRepository().findByName(value);

									if (category != null && category.getName() != null) {
										stockItem.setCategory(category);
										continue;
									} else {
										System.err.println("Category not found - " + value);
									}
								} else if (headerCellName.contains("remarks")) {
									stockItem.setRemarks(value);
									continue;
								}
							}
						}
					}
				}
				System.out.println("-------------------------------------");
			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		return stockItem;
	}

	private List<Cell> getHeaderCells(Sheet sheet) {
		List<Cell> headerCells = new ArrayList<Cell>();
		Row rowData = null;
		if (sheet != null) {
			// find the first non-empty row
			boolean found = false;
			int rowNum = 0;
			while (rowNum < sheet.getLastRowNum()) {
				rowData = sheet.getRow(rowNum);
				int columnNumber = 0;
				if (rowData != null) {
					columnNumber = rowData.getPhysicalNumberOfCells();
					if (columnNumber > 0) {
						for (Iterator<Cell> cit = rowData.cellIterator(); cit.hasNext();) {
							Cell cell = cit.next();
							String stringValue = cell.getStringCellValue();
							if (stringValue != null && stringValue.toLowerCase().contains("name")) {
								found = true;
								break;
							}
						}

						if (found) {
							firstRowNumWithData = rowNum + 1;
							break;
						}
					}
				}
				rowNum++;
			}

			if (found && rowData != null) {
				for (Iterator<Cell> cit = rowData.cellIterator(); cit.hasNext();) {
					Cell cell = cit.next();
					if (cell != null && cell.getStringCellValue() != null) {
						headerCells.add(cell);
					}
				}
			}
		}
		return headerCells;
	}

}
