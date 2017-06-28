forked from [nekocode/android-parcelable-intellij-plugin-kotlin](https://github.com/nekocode/android-parcelable-intellij-plugin-kotlin)
# Android Parcelable boilerplate code generation

**Inspired by [android-parcelable-intellij-plugin](https://github.com/mcharmas/android-parcelable-intellij-plugin)**

## Installation
Plugin is uploaded to plugin repository. If you like, you can also install it manually:

 0. Download `ParcelableGenerator` [release](https://github.com/nekocode/android-parcelable-intellij-plugin-kotlin/releases)
 0. Open IntelliJ/Android Studio
 0. *Preferences* -> *Plugins* -> *Install plugin from disk...*.
 0. Choose the downloaded jar file

### Dependencies
It depends on the latest version of [`Kotlin`](https://plugins.jetbrains.com/plugin/6954-kotlin) plugin.

## Usage
Just press ALT + Insert (or your equivalent keybinding for code generation) in your editor and select Parcelable. It will auto generate Parcelable boilerplate code for your class.

![](art/usage.gif)

## Now Suported Types
- Types implementing `Parceable` or `Serializable`
- List of Parcelable objects
- Array of Parcelable objects
- Primitive Kotlin types: `String`, `Byte`, `Double`, `Float`, `Int`, `Long`, `Boolean`, `Char`
- Kotlin Array types: `Array<String>`, `ByteArray`, `DoubleArray`, `FloatArray`, `IntArray`, `LongArray`, `CharArray`, `BooleanArray`
- List of any objects **(Warning: validation is not performed)**
- Enum type

## License
```
Copyright (C) 2016 Nekocode (https://github.com/nekocode)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
