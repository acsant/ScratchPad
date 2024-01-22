package org.ScratchPad.Lucene;

import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.document.Document;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {
        final InputStream uberDataSetStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("uber_request_data.csv");
        boolean hasReadHeaders = false;
        final Collection<Document> luceneDocuments = new ArrayList<>();
        int i = 0;
        try (final InputStreamReader isr = new InputStreamReader(uberDataSetStream);
             final BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!hasReadHeaders) {
                    hasReadHeaders = true;
                    continue;
                }

                final String[] data = line.split(",");
                // Request id,Pickup point,Driver id,Status,Request timestamp,Drop timestamp
                final Document document = UberRequestDocumentHelper.getDocumentFromRequest(data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5]);

                if (i >= 10) {
                    break;
                }
                luceneDocuments.add(document);
                i += 1;
            }
            uberDataSetStream.close();
        }

        indexSimpleFormat(luceneDocuments);
        indexSimpleBloomFormat(luceneDocuments);
    }

    public static void indexSimpleFormat(final Collection<Document> documents) throws IOException {
        final Path indexPath = Path.of("/tmp/uberRequests/index/simple");
        final SimpleTextCodec simpleTextCodec = new SimpleTextCodec();
        final DocumentWriter documentWriter = new DocumentWriter(indexPath, simpleTextCodec);
        for(final Document document : documents) {
            documentWriter.addDocument(document);
        }
        documentWriter.close();
    }

    public static void indexSimpleBloomFormat(final Collection<Document> documents) throws IOException {
        final Path indexPath = Path.of("/tmp/uberRequests/index/bloomSimple");
        final BloomSimpleCodec simpleTextCodec = new BloomSimpleCodec("BloomFilterCodec",
                new SimpleTextCodec(),
                "RequestId");
        final DocumentWriter documentWriter = new DocumentWriter(indexPath, simpleTextCodec);
        for(final Document document : documents) {
            documentWriter.addDocument(document);
        }
        documentWriter.close();
    }
}