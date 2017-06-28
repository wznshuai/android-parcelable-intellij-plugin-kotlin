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
package cn.nekocode.plugin.parcelablegenerator;

import cn.nekocode.plugin.parcelablegenerator.typeserializers.CompatPropertyDescriptor;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.madai.android.plugin.utils.KtClassHelper;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.psi.KtClass;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by nekocode on 2015/12/1.
 */
public class GenerateDialog extends DialogWrapper {

    private final LabeledComponent<JPanel> myComponent;
    private CollectionListModel<CompatPropertyDescriptor> myFileds;

    protected GenerateDialog(KtClass ktClass) {
        super(ktClass.getProject());
        setTitle("Select Fields for Parcelable Generation");

        myFileds = new CollectionListModel<>(KtClassHelper.INSTANCE.findAllParams(ktClass));

        JBList fieldList = new JBList(myFileds);
        fieldList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                CompatPropertyDescriptor descriptor = (CompatPropertyDescriptor) value;
                String name = descriptor.propertyDescriptor.getName().asString();
                String type = descriptor.propertyDescriptor.getType().toString();
                Component renderer = super.getListCellRendererComponent(list, name + ": " + type, index, isSelected, cellHasFocus);
                return renderer;
            }
        });

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldList);
        decorator.disableAddAction();
        JPanel panel = decorator.createPanel();
        myComponent = LabeledComponent.create(panel, "Fields to include in Parcelable");

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return myComponent;
    }

    public List<CompatPropertyDescriptor> getSelectedFields() {
        return myFileds.getItems();
    }
}
