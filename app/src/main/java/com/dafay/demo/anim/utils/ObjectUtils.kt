package com.dafay.demo.anim.utils

import android.text.TextUtils
import java.lang.reflect.Field

/**
 * Created by  idea on 2019/12/26$ 下午12:22$
 * des:
 */
object ObjectUtils {

    /**
     * 通过字段名从对象或对象的父类中得到字段的值
     * @param `object` 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getValue(any: Any?, fieldName: String?): Any? {
        if (any == null) {
            return null
        }
        if (TextUtils.isEmpty(fieldName)) {
            return null
        }
        var field: Field? = null
        var clazz: Class<*>? = any.javaClass
        while (clazz != Any::class.java) {
            try {
                field = clazz!!.getDeclaredField(fieldName!!)
                field.setAccessible(true)
                return field.get(any)
            } catch (e: Exception) { //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                e.printStackTrace()
            //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
            clazz = clazz!!.superclass
        }
        return null
    }

}