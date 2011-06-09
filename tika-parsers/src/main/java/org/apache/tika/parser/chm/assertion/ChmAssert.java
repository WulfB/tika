/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser.chm.assertion;

import java.io.InputStream;

import org.apache.tika.parser.chm.accessor.ChmAccessor;
import org.apache.tika.parser.chm.accessor.ChmLzxcResetTable;
import org.apache.tika.parser.chm.core.ChmCommons;
import org.apache.tika.parser.chm.exception.ChmParsingException;

/**
 * Contains chm extractor assertions
 */
public class ChmAssert {
    /**
     * Checks a validity of the chmBlockSegment parameters
     * 
     * @param data
     *            byte[]
     * @param resetTable
     *            ChmLzxcResetTable
     * @param blockNumber
     *            int
     * @param lzxcBlockOffset
     *            int
     * @param lzxcBlockLength
     *            int
     */
    public static final void assertChmBlockSegment(byte[] data,
            ChmLzxcResetTable resetTable, int blockNumber, int lzxcBlockOffset,
            int lzxcBlockLength) {
        if ((data == null))
            throw new ChmParsingException("data[] is null");

        if ((data.length <= 0))
            throw new ChmParsingException("data[] length should be greater than zero");

        if (resetTable == null)
            throw new ChmParsingException("resetTable is null");

        if (resetTable.getBlockAddress().length <= 1)
            throw new ChmParsingException("resetTable.getBlockAddress().length should be greater than zero");

        if (blockNumber < 0)
            throw new ChmParsingException("blockNumber should be positive number");

        if (lzxcBlockOffset < 0)
            throw new ChmParsingException("lzxcBlockOffset should be positive number");

        if (lzxcBlockLength < 0)
            throw new ChmParsingException("lzxcBlockLength should be positive number");
    }

    /**
     * Checks if InputStream is not null
     * 
     * @param is
     *            InputStream
     */
    public static final void assertInputStreamNotNull(InputStream is) {
        if (is == null)
            throw new ChmParsingException("input sream is null");
    }

    /**
     * Checks validity of ChmAccessor parameters
     * 
     * @param data
     * @param chmItsfHeader
     * @param count
     */
    public static final void assertChmAccessorParameters(byte[] data,
            ChmAccessor<?> chmAccessor, int count) {
        assertByteArrayNotNull(data);
        assertChmAccessorNotNull(chmAccessor);
    }

    /**
     * Checks if byte[] is not null
     * 
     * @param data
     */
    public static final void assertByteArrayNotNull(byte[] data) {
        if (data == null)
            throw new ChmParsingException("byte[] data is null");
    }

    /**
     * Checks if ChmAccessor is not null In case of null throws exception
     * 
     * @param ChmAccessor
     */
    public static final void assertChmAccessorNotNull(ChmAccessor<?> chmAccessor) {
        if (chmAccessor == null)
            throw new ChmParsingException("chm header is null");
    }

    /**
     * Checks validity of the DirectoryListingEntry's parameters In case of
     * invalid parameter(s) throws an exception
     * 
     * @param name_length
     *            length of the chm entry name
     * @param name
     *            chm entry name
     * @param entryType
     *            EntryType
     * @param offset
     * @param length
     */
    public static final void assertDirectoryListingEntry(int name_length,
            String name, ChmCommons.EntryType entryType, int offset, int length) {
        if (name_length < 0)
            throw new ChmParsingException("invalid name length");
        if (name == null)
            throw new ChmParsingException("invalid name");

        if ((entryType != ChmCommons.EntryType.COMPRESSED)
                && (entryType != ChmCommons.EntryType.UNCOMPRESSED))
            throw new ChmParsingException("invalid compressed type, should be EntryType.COMPRESSED | EntryType.UNCOMPRESSED");

        if (offset < 0)
            throw new ChmParsingException("invalid offset");

        if (length < 0)
            throw new ChmParsingException("invalid length");
    }

    public static void assertCopyingDataIndex(int index, int dataLength) {
        if (index >= dataLength)
            throw new ChmParsingException("cannot parse chm file index > data.length");
    }

    /**
     * Checks if int param is greater than zero In case param <=0 throws an
     * exception
     * 
     * @param param
     */
    public static void assertPositiveInt(int param) {
        if (param <= 0)
            throw new ChmParsingException("resetTable.getBlockAddress().length should be greater than zero");
    }
}