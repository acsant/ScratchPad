package org.ScratchPad.Lucene;

import org.apache.lucene.codecs.Codec;
import org.apache.lucene.codecs.PostingsFormat;
import org.apache.lucene.codecs.bloom.BloomFilteringPostingsFormat;
import org.apache.lucene.codecs.perfield.PerFieldPostingsFormat;

public class BloomFilterPostingsFormat extends PerFieldPostingsFormat {
    final Codec delegate;
    final String idFieldName;

    BloomFilterPostingsFormat(final Codec delegate,
                              final String idFieldName) {
        this.delegate = delegate;
        this.idFieldName = idFieldName;
    }

    @Override
    public PostingsFormat getPostingsFormatForField(final String s) {
        if (s.equals(idFieldName)) {
            return new BloomFilteringPostingsFormat(delegate.postingsFormat());
        }
        return delegate.postingsFormat();
    }
}
