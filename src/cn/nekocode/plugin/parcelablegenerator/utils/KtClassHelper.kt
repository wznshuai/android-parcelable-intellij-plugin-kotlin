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
package com.madai.android.plugin.utils

import cn.nekocode.plugin.parcelablegenerator.typeserializers.CompatPropertyDescriptor
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.asJava.elements.KtLightElement
import org.jetbrains.kotlin.caches.resolve.KotlinCacheService
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.lazy.ResolveSession
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor
import java.util.*

/**
 * Created by nekocode on 2016/2/19.
 */
object KtClassHelper {
    fun findParams(ktClass: KtClass): List<ValueParameterDescriptor> {
        val list = ArrayList<KtElement>()
        list.add(ktClass)

        val resolveSession = KotlinCacheService.getInstance(ktClass.project).getResolutionFacade(list).getFrontendService(ResolveSession::class.java)
        val classDescriptor = resolveSession.getClassDescriptor(ktClass, NoLookupLocation.FROM_IDE)
        val valueParameters = ArrayList<ValueParameterDescriptor>()
        //        if (classDescriptor.isData()) {
        val constructorDescriptor = classDescriptor.unsubstitutedPrimaryConstructor
        val memberScope = (classDescriptor as LazyClassDescriptor).unsubstitutedMemberScope
        val set = memberScope.getVariableNames()
        val i = set.toTypedArray()

        val list1 = classDescriptor.declaredCallableMembers as ArrayList<*>
        if (constructorDescriptor != null) {
            val allParameters = constructorDescriptor.valueParameters

            for (parameter in allParameters) {
                valueParameters.add(parameter)
            }
        }
        //        }

        return valueParameters
    }

    fun findAllParams(ktClass: KtClass): List<CompatPropertyDescriptor>? {
        val list = ArrayList<KtElement>()
        list.add(ktClass)

        val resolveSession = KotlinCacheService.getInstance(ktClass.project).getResolutionFacade(list).getFrontendService(ResolveSession::class.java)
        val classDescriptor = resolveSession.getClassDescriptor(ktClass, NoLookupLocation.FROM_IDE)
        val constructorParameters = classDescriptor.unsubstitutedPrimaryConstructor?.valueParameters
//        val memberScope = (classDescriptor as LazyClassDescriptor).unsubstitutedMemberScope

        var propertyDescriptors: List<PropertyDescriptor>? = ((classDescriptor as LazyClassDescriptor).declaredCallableMembers as? List<*>)?.filterIsInstance<PropertyDescriptor>()
        val compatPropertyLists = arrayListOf<CompatPropertyDescriptor>()
        if(null != propertyDescriptors && null != constructorParameters){

            for(valueParam in constructorParameters){
                propertyDescriptors = propertyDescriptors!!.filter { it ->
                    var b = true
                    if(valueParam.name.asString() ==  it.name.asString()){
                        b = false
                        compatPropertyLists.add(CompatPropertyDescriptor(it, true))
                    }
                    b
                }
            }

            if(null != propertyDescriptors){
                for (pro in propertyDescriptors) {
                    compatPropertyLists.add(CompatPropertyDescriptor(pro, false))
                }
            }
        }
//        if(memberScope is LazyClassMemberScope){
//            val set = memberScope.getVariableNames()
//            for(name in set){
//                val it = memberScope.doGetProperties(name).iterator()
//                if(it.hasNext()){
//                    val p = it.next()
//                }
//            }
//        }


        return compatPropertyLists
    }

    fun findConstructorPropertyIsInPropertyDescriptors(constructorParam: ValueParameterDescriptor, propertyDescriptors: List<PropertyDescriptor>): Boolean {
        propertyDescriptors.stream().peek {

        }
        return false
    }


    fun getKtClassForElement(psiElement: PsiElement): KtClass? {
        if (psiElement is KtLightElement<*, *>) {
            val origin = psiElement.kotlinOrigin
            if (origin != null) {
                return getKtClassForElement(origin)
            } else {
                return null
            }

        } else if (psiElement is KtClass && !psiElement.isEnum() &&
                !psiElement.isInterface() &&
                !psiElement.isAnnotation() &&
                !psiElement.isSealed()) {
            return psiElement

        } else {
            val parent = psiElement.parent
            if (parent == null) {
                return null
            } else {
                return getKtClassForElement(parent)
            }
        }
    }
}
