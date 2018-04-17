


import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import java.io.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import java.io.*;
import java.io.Reader;
import java.util.Iterator;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import java.io.InputStreamReader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriter;



import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class SimplePDFSearch {

    
   private static final String INDEX_DIR = "C:\\Users\\sys\\Desktop\\221116\\MRSE\\Indx";
   static String extension;
  
        
   
 
   SimplePDFSearch()
   {
	  String sf=fupload.sf;
  File txtFile = new File(sf);
 String file_name = txtFile.getName();
 int pos=file_name.indexOf('.');
 String ext=file_name.substring(pos+1);
 if(ext.equals("txt"))
		 {
 System.out.println(txtFile);
  
   String path;
try {
	path = txtFile.getCanonicalPath();
	IndexItem txtIndexItem = readtxtFile(txtFile);

  Indexer indexer = new Indexer(INDEX_DIR);
  indexer.index(txtIndexItem,path);
  indexer.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   
	}
 if(ext.equals("pdf"))
	 {

	   
	   File pdfFile = new File(sf);
	   System.out.println(pdfFile);
 String path;
try {
	path = pdfFile.getCanonicalPath();
	IndexItem pdfIndexItem = indexpdf(pdfFile);
//  // creating an instance of the indexer class and indexing the items
  Indexer indexer = new Indexer(INDEX_DIR);
  indexer.index(pdfIndexItem,path);
  indexer.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 }
 
 if(ext.equals("doc"))
	 {

	   
	   File docFile = new File(sf);
	   System.out.println(docFile);
 String path;
try {
	path = docFile.getCanonicalPath();
	IndexItem docIndexItem = indexdoc(docFile);
//  // creating an instance of the indexer class and indexing the items
  Indexer indexer = new Indexer(INDEX_DIR);
  indexer.index(docIndexItem,path);
  indexer.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 }
   }
  
     public static IndexItem indexpdf(File file) throws IOException {
         PDDocument doc = PDDocument.load(file);
         String content = new PDFTextStripper().getText(doc);
         doc.close();
         return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
         }
     
     
        public static IndexItem readtxtFile(File fileName) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return new IndexItem((long)fileName.getName().hashCode(), fileName.getName(), sb.toString());
    } finally {
        br.close();
    }

  }
        
    
public static IndexItem indexdoc(File file) throws IOException {
		WordExtractor docExtractor = null;
	try{
	
         	FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			// A HWPFDocument used to read document file from FileInputStream
			HWPFDocument doc = new HWPFDocument(fis);

			docExtractor = new WordExtractor(doc);
			} catch (Exception exep) {
			System.out.println(exep.getMessage());
		}
				String[] docArray = docExtractor.getParagraphText();

		for (int i = 0; i < docArray.length; i++) {
			if (docArray[i] != null)
				System.out.println("Line " + i + " : " + docArray[i]);
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < docArray.length; i++) {
			result.append(docArray[i]);
			// result.append( optional separator );
		}
		String content = result.toString();
         return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
         }
         
     
      

public static void main(String args[])
{
	
}
}
