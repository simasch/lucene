
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

    public static void main(String[] args) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new SimpleFSDirectory(new File("/Users/simon/temp/search.index"));

        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);

        try (IndexWriter w = new IndexWriter(index, config)) {
            addDoc(w, "Lucene in Action", "193398817");
            addDoc(w, "Lucene for Dummies", "55320055Z");
            addDoc(w, "Managing Gigabytes", "55063554A");
            addDoc(w, "The Art of Computer Science", "9900333X");
        }
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
}
