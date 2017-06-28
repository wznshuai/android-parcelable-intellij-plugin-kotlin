package cn.nekocode.plugin.parcelablegenerator.typeserializers;

import org.jetbrains.kotlin.descriptors.PropertyDescriptor;

/**
 * Created by Wind_Fantasy on 2017/6/27.
 */
public class CompatPropertyDescriptor {
    public PropertyDescriptor propertyDescriptor;
    boolean isConstructorField;

    public CompatPropertyDescriptor(PropertyDescriptor propertyDescriptor, boolean isConstructorField) {
        this.propertyDescriptor = propertyDescriptor;
        this.isConstructorField = isConstructorField;
    }
}
