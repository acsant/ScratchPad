package org.ScratchPad.Lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.codecs.Codec;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Concepts: https://lucene.apache.org/core/9_1_0/core/index.html
 * https://lucenetutorial.com/lucene-in-5-minutes.html
 */
public class DocumentWriter {
    /**
     * org.apache.lucene.analysis defines an abstract Analyzer API for converting text from a Reader into a TokenStream,
     * an enumeration of token Attributes.  A TokenStream can be composed by applying TokenFilters to the output of a
     * Tokenizer.  Tokenizers and TokenFilters are strung together and applied with an Analyzer.
     */
    final StandardAnalyzer standardAnalyzer;
    /**
     * org.apache.lucene.store defines an abstract class for storing persistent data, the Directory, which is a
     * collection of named files written by an IndexOutput and read by an IndexInput.  Multiple implementations are provided,
     * but FSDirectory is generally recommended as it tries to use operating system disk buffer caches efficiently.
     */
    final Directory directory;
    /**
     * org.apache.lucene.index provides two primary classes: IndexWriter, which creates and adds documents to indices;
     * and IndexReader, which accesses the data in the index.
     */
    final IndexWriter indexWriter;

    DocumentWriter(final Path indexPath, final Codec configuredCodec) throws IOException {
        this.standardAnalyzer = new StandardAnalyzer();
        this.directory = FSDirectory.open(indexPath);

        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        indexWriterConfig.setCodec(configuredCodec);

        this.indexWriter = new IndexWriter(this.directory, indexWriterConfig);
    }

    public void addDocument(final Document document) throws IOException {
        this.indexWriter.addDocument(document);
    }

    public void close() throws IOException {
        indexWriter.close();
    }
}
