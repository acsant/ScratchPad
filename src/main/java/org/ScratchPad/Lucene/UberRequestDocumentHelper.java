package org.ScratchPad.Lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class UberRequestDocumentHelper {
    /**
     * Request id,Pickup point,Driver id,Status,Request timestamp,Drop timestamp
     * @return
     */
    public static Document getDocumentFromRequest(final String requestId,
                                                  final String pickup,
                                                  final String driverId,
                                                  final String status,
                                                  final String requestTimestamp,
                                                  final String dropTimestamp) {
        final Document document = new Document();
        document.add(new StringField("RequestId", requestId, Field.Store.YES));
        document.add(new TextField("Pickup", pickup, Field.Store.YES));
        document.add(new StringField("DriverId", driverId, Field.Store.YES));
        document.add(new StringField("Status", status, Field.Store.YES));
        document.add(new StringField("RequestTime", requestTimestamp, Field.Store.YES));
        document.add(new StringField("DropOffTime", dropTimestamp, Field.Store.YES));

        return document;
    }
}
