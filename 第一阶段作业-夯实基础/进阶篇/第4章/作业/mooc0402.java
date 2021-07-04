package docxtest2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;

public class mooc0402{

	public static void main(String[] args)throws Exception {
		List<String> list=new ArrayList<String>();
		list=readXslx();
		String path="stu1.png";
		generateBarcode(list.get(2),path);
		XWPFDocument doc=openDocx("student.docx");
		Map<String,Object> params=new HashMap<>();
       params.put("{name}",list.get(0));
       params.put("{sex}",list.get(1));
       Map<String,String> picParams=new HashMap<>();
       picParams.put("barcode","stu1.png");
       List<IBodyElement> ibes=doc.getBodyElements();
       for(IBodyElement ib:ibes) {
       	if(ib.getElementType()==BodyElementType.TABLE) {
       		replaceTable(ib,params,picParams,doc);
       	}else {
       		replaceBarcode(ib,params,picParams,doc);
       	}
       }
       writeDocx(doc,new FileOutputStream("stu1.docx"));
       System.out.println("Docx has generated!");
		
       
       PdfOptions options=PdfOptions.create();
       options.fontProvider(new IFontProvider() {

       	public Font getFont(String familyName,String encoding,float size,int style,Color color) {
       		try {
       			BaseFont bfChinese=BaseFont.createFont(
       					"C:\\Windows\\Fonts\\STSONG.TTF",
       					BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
       			Font fontChinese = new Font(bfChinese,size,style,color);
       			if(familyName!=null)
       				fontChinese.setFamily(familyName);
       			return fontChinese;
       		}catch(Throwable e) {
       			e.printStackTrace();
       			return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
       		}
       	}
       });
       PdfConverter.getInstance().convert(doc, new FileOutputStream("stu1.pdf"), options); //pdf
	    System.out.println("Pdf has generated!");
	}
	public static List<String> readXslx() throws IOException {
		 List<String> msg;
		 msg=new ArrayList<String>(); 
		 InputStream stuExc=new FileInputStream("student.xlsx");
		 XSSFWorkbook wb=new XSSFWorkbook(stuExc);
		 boolean stuFlag=false;
		 
		 XSSFSheet sheet=wb.getSheetAt(0);
		 XSSFRow row;
		 XSSFCell cell;
		 
		 Iterator rows=sheet.rowIterator();
		 
		 while(rows.hasNext()) {
			 row=(XSSFRow)rows.next();
			 Iterator cells=row.cellIterator();
			 
			 
			 while(cells.hasNext()) {
				 stuFlag=true;
				 cell=(XSSFCell)cells.next();
				 if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING) {
					msg.add(cell.getStringCellValue()); 
				 }else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) {
					 msg.add(""+cell.getNumericCellValue());
			 }
			
		   }
			 
	     }
		 System.out.println("Read Excel!");
		 if(stuFlag) {
			 return msg;
		 }else {
			 return null;
		 }
		 
   }
	public static void generateBarcode(String msg,String path) {
		File file=new File(path);
		try {
			Code39Bean bean=new Code39Bean();
			
			final int dpi=150;
			final double width=UnitConv.in2mm(2.0f/dpi);
			bean.setWideFactor(3);
			bean.setModuleWidth(width);
			bean.doQuietZone(false);
			
			String format="image/png";			
			BitmapCanvasProvider canvas=new BitmapCanvasProvider(new FileOutputStream(file),format,dpi,
					BufferedImage.TYPE_BYTE_BINARY,false,0);
			//����������
			bean.generateBarcode(canvas, msg);
						
			canvas.finish();
			System.out.println("Generate barcode!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static XWPFDocument openDocx(String url) {
		InputStream in=null;
		try {
			in=new FileInputStream(url);
			XWPFDocument doc=new XWPFDocument(in);
			return doc;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static void writeDocx(XWPFDocument outDoc,OutputStream out) {
		try {
			outDoc.write(out);
			out.flush();
			if(out!=null) {
				try {
					out.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private static Matcher matcher(String str) {
		Pattern pattern=Pattern.compile("\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(str);
		return matcher;
	}
	
	public static void replacePic(XWPFRun run,String imgFile,XWPFDocument doc)throws Exception {
		int format;
		if(imgFile.endsWith(".emf"))
			format=Document.PICTURE_TYPE_EMF;
		else if(imgFile.endsWith(".wmf"))
			format=Document.PICTURE_TYPE_WMF;
		else if(imgFile.endsWith(".pict"))
			format=Document.PICTURE_TYPE_PICT;
		else if(imgFile.endsWith(".jpeg")||imgFile.endsWith(".jpg"))
			format=Document.PICTURE_TYPE_JPEG;
		else if(imgFile.endsWith(".png"))
			format=Document.PICTURE_TYPE_PNG;
		else if(imgFile.endsWith(".dib"))
			format=Document.PICTURE_TYPE_DIB;
		else if(imgFile.endsWith(".gif"))
			format=Document.PICTURE_TYPE_GIF;
		else if(imgFile.endsWith(".tiff"))
			format=Document.PICTURE_TYPE_TIFF;
		else if(imgFile.endsWith(".eps"))
			format=Document.PICTURE_TYPE_EPS;
		else if(imgFile.endsWith(".bmp"))
			format=Document.PICTURE_TYPE_BMP;
		else if(imgFile.endsWith(".wpg"))
			format=Document.PICTURE_TYPE_WPG;
		else {
			System.err.println(
					"Unsupported picture:"+imgFile+".Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
			return;
		}
		if(imgFile.startsWith("http")||imgFile.startsWith("https")) {
			run.addPicture(new URL(imgFile).openConnection().getInputStream(),format,"rpic",Units.toEMU(100),Units.toEMU(100));
			
		}else {
			run.addPicture(new FileInputStream(imgFile), format, "rpic", Units.toEMU(250), Units.toEMU(80));
		}
	}
	public static void replaceTable(IBodyElement para,Map<String,Object> params,
			Map<String,String> picParams,XWPFDocument indoc)throws Exception{
		Matcher matcher;
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		table=(XWPFTable)para;
		rows=table.getRows();
		for(XWPFTableRow row:rows) {
			cells=row.getTableCells();
			int cellsize=cells.size();
			int cellcount=0;
			for(cellcount=0;cellcount<cellsize;cellcount++) {
				XWPFTableCell cell=cells.get(cellcount);
				String runtext="";
				List<XWPFParagraph> ps=cell.getParagraphs();
				for(XWPFParagraph p:ps) {
					for(XWPFRun run:p.getRuns()) {
						runtext=run.text();
						matcher=matcher(runtext);
						if(matcher.find()) {
							if(params!=null) {
								for(String pickey:params.keySet()) {
									if(matcher.group().equals(pickey)) {
										run.setText(params.get(pickey)+"",0);
									}
								}
							}
						}
					}
				}
				
			}
		}
	}
	public static void replaceBarcode(IBodyElement para,Map<String,Object> params,
			Map<String,String> picParams,XWPFDocument indoc)throws Exception{
		 
	    XWPFParagraph p=(XWPFParagraph)para;
	    String runtext="";
	    for(XWPFRun run:p.getRuns()) {
			runtext=run.text();
			if(picParams !=null) {
					for(String pickey:picParams.keySet()) {
						if(runtext.equals(pickey)) {
							run.setText("",0);
							replacePic(run,picParams.get(pickey),indoc);
						}
					}	
			}
	    }
	}
	
	
}