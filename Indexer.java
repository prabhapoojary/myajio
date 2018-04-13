import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.File;
import java.io.IOException;




public class Indexer {
 private IndexWriter writer;
 public static final String FIELD_PATH = "path";
    public Indexer(String indexDir) throws IOException {
        // create the index
        if(writer == null) {
        writer = new IndexWriter(FSDirectory.open(new File(indexDir)), new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36)));
       }

    }





   

    public void index(IndexItem indexItem, String path) throws IOException {
        
        writer.deleteDocuments(new Term(IndexItem.ID, indexItem.getId().toString()));
        Document doc = new Document();
        doc.add(new Field(IndexItem.ID, indexItem.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field(IndexItem.TITLE, indexItem.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field(IndexItem.CONTENT, indexItem.getContent(), Field.Store.YES, Field.Index.ANALYZED));
        // add the document to the index
     	doc.add(new Field(FIELD_PATH, path, Field.Store.YES, Field.Index.NOT_ANALYZED));
        writer.addDocument(doc);
    }

    

    public void close() throws IOException {

        writer.close();

    }

}
