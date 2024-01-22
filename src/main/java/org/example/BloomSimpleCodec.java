package org.example;

import org.apache.lucene.codecs.*;

public class BloomSimpleCodec extends Codec {
    final Codec delegate;
    final BloomFilterPostingsFormat bloomFilterPostingsFormat;

    protected BloomSimpleCodec(final String name,
                               final Codec delegate,
                               final String idFieldName) {
        super(name);
        this.delegate = delegate;
        this.bloomFilterPostingsFormat = new BloomFilterPostingsFormat(delegate, idFieldName);
    }

    @Override
    public PostingsFormat postingsFormat() {
        return bloomFilterPostingsFormat;
    }

    @Override
    public DocValuesFormat docValuesFormat() {
        return delegate.docValuesFormat();
    }

    @Override
    public StoredFieldsFormat storedFieldsFormat() {
        return delegate.storedFieldsFormat();
    }

    @Override
    public TermVectorsFormat termVectorsFormat() {
        return delegate.termVectorsFormat();
    }

    @Override
    public FieldInfosFormat fieldInfosFormat() {
        return delegate.fieldInfosFormat();
    }

    @Override
    public SegmentInfoFormat segmentInfoFormat() {
        return delegate.segmentInfoFormat();
    }

    @Override
    public NormsFormat normsFormat() {
        return delegate.normsFormat();
    }

    @Override
    public LiveDocsFormat liveDocsFormat() {
        return delegate.liveDocsFormat();
    }

    @Override
    public CompoundFormat compoundFormat() {
        return delegate.compoundFormat();
    }

    @Override
    public PointsFormat pointsFormat() {
        return delegate.pointsFormat();
    }

    @Override
    public KnnVectorsFormat knnVectorsFormat() {
        return delegate.knnVectorsFormat();
    }
}
