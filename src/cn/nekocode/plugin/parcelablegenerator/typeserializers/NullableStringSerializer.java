/*
 * Copyright (C) 2016 Nekocode (https://github.com/nekocode)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.nekocode.plugin.parcelablegenerator.typeserializers;

/**
 * Created by nekocode on 2016/2/2.
 */
public class NullableStringSerializer extends TypeSerializer {

    public NullableStringSerializer(CompatPropertyDescriptor field) {
        super(field);
    }

    public String generateReadValue() {
        return "source.readString()";
    }

    public String generateWriteValue() {
        return "writeString(" + getFieldName() + ")";
    }
}
