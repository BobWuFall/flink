/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.orc.vector;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;

/**
 * This column vector is used to adapt hive's BytesColumnVector to Flink's BytesColumnVector.
 */
public class OrcBytesColumnVector extends AbstractOrcColumnVector implements
		org.apache.flink.table.dataformat.vector.BytesColumnVector {

	private BytesColumnVector vector;

	public OrcBytesColumnVector(BytesColumnVector vector) {
		super(vector);
		this.vector = vector;
	}

	@Override
	public Bytes getBytes(int i) {
		int rowId = vector.isRepeating ? 0 : i;
		byte[][] data = vector.vector;
		int[] start = vector.start;
		int[] length = vector.length;
		return new Bytes(data[rowId], start[rowId], length[rowId]);
	}
}
