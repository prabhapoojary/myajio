



import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

import java.util.*;

/**
 *
 * @author 
 */
public class Searcher {

	private IndexSearcher searcher;
	public Similarity similarity;
	public static final String FIELD_PATH = "path";
	private static final int String = 0;
	public static String title;
	static ArrayList<fieldset> userList;
	static ArrayList<fieldset1> userList1;
	// float score1;
	public static String sco;
	private static final float Double = 0;
	private QueryParser contentQueryParser;

	public HashMap<String, Double> hm = new HashMap<String, Double>();
	public HashMap<String, Double> getHashmap() {

		return hm;
	}

	public void setHashmap(HashMap<String, Double> hm) {
		this.hm = hm;

	}


	public Searcher(String indexDir) throws IOException {

		searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);


	}





	public void Searcherone(String indexDir,String queryString) throws ParseException,IOException {


		IndexSearcher  searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		if (similarity != null) {
			searcher.setSimilarity(similarity);
		}

		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", analyzer);
		Query query = parser.parse(queryString);

		TopDocs topdocs = searcher.search(query, 100);
		TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
		searcher.search(query, collector);

		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		ScoreDoc[] hits1 = topdocs.scoreDocs;



		userList = new ArrayList<fieldset>();
		for (int i = 0; i < hits1.length; i++) {
			fieldset user = new fieldset();
			Document doc = searcher.doc(hits1[i].doc);
			title = doc.get("title");
			user.setId(title);
			String tour = doc.get("tour");
			float score1 = hits1[i].score;
			//Convert Float to String
			sco=Float.toString(score1);

			user.setName(sco);

			System.out.println( "Value :"+score1);
			hm.put(title, new Double(score1));
			userList.add(user);
		}








		for (int i = 0; i < hits.length; i++) {


			Document hitDoc = searcher.doc(hits[i].doc); 
			String path = hitDoc.get(FIELD_PATH);
			System.out.println("Hit: " + (i+1));

		}



	}



	public void Searchertop(String indexDir,String queryString,int key) throws ParseException,IOException {

		IndexSearcher  searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		if (similarity != null) {
			searcher.setSimilarity(similarity);
		}

		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", analyzer);
		Query query = parser.parse(queryString);

		TopDocs topdocs = searcher.search(query, key);
		TopScoreDocCollector collector = TopScoreDocCollector.create(key, true);
		searcher.search(query, collector);

		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		ScoreDoc[] hits1 = topdocs.scoreDocs;


		userList1 = new ArrayList<fieldset1>();
		for (int i = 0; i < hits1.length; i++) {
			fieldset1 user2 = new fieldset1();
			Document doc = searcher.doc(hits1[i].doc);
			title = doc.get("title");
			user2.setId(title);
			float score1 = hits1[i].score;

			sco=Float.toString(score1);

			user2.setName(sco);

			System.out.println( "Value :"+score1);
			hm.put(title, new Double(score1));
			userList1.add(user2);
		}








		for (int i = 0; i < hits.length; i++) {


			Document hitDoc = searcher.doc(hits[i].doc); 
			float score = hits[i].score;

			System.out.println("Title: " + hitDoc.get("title"));

			System.out.println("File Score: " + sco);

			System.out.println("Content: " + hitDoc.get("content"));
			System.out.println("Path :"+hitDoc.get("path"));
			String path = hitDoc.get(FIELD_PATH);
			System.out.println("Hit: " + (i+1));

		}



	}
public String synonium_queries(String msg){
	 
	
	return msg;
	
}

	public void close() throws IOException {
		searcher.close();
	}

}
