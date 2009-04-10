package com.agnaldo4j.prm.testes;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Indexer {
    
    public Indexer(String caminhoIndice) {
        
        
        File prmDir = new File(caminhoIndice);
        if (!prmDir.exists()) prmDir.mkdir();
        File indexDir = new File(prmDir,"luceneIndex");
        IndexWriter writer;
        
        try {
            writer = new IndexWriter(indexDir, new BrazilianAnalyzer(), true);
            writer.setUseCompoundFile(false);
            this.indexDirectory(writer, new File("D:\\SoftExpert\\workflow"));
            writer.optimize();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        String userPath = properties.getProperty("user.home");
        
        userPath += File.separator+".prm";
        new Indexer(userPath);
    }
    
    private void indexDirectory(IndexWriter writer, File dir) throws IOException {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                indexDirectory(writer, f);
            } else if (f.getName().endsWith(".txt")) {
                indexFile(writer, f);
            }
        }
    }
// method to actually index a file using Lucene
    private void indexFile(IndexWriter writer, File f) throws IOException {
        if (f.isHidden() || !f.exists() || !f.canRead()) {
            return;
        }
        System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = new Document();
        doc.add(new Field("oid", "teste", Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("contents", new FileReader(f)));
        writer.addDocument(doc);
    }
}