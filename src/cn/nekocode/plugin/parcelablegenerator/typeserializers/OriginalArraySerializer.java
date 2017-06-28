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
public class OriginalArraySerializer extends TypeSerializer {

    public OriginalArraySerializer(CompatPropertyDescriptor field) {
        super(field);
    }

    public String generateReadValue() {
        if (getProjectionType() == null) {
            return "source.create" + getNoneNullType() + "()";
        } else {
            return "source.create" + getNoneNullProjectionType() + "Array()";
        }
    }

    public String generateWriteValue() {
        if (getProjectionType() == null) {
            return "write" + getNoneNullType() + "(" + getFieldName() + ")";
        } else {
            return "write" + getNoneNullProjectionType() + "Array(" + getFieldName() + ")";
        }
    }
}
