package com.example.recyclerviewer

import java.io.Serializable

class UserModel : Serializable {

    var name: String? = null
    var hobby: String? = null
    var id: Int = 0

    fun getIds(): Int {
        return id
    }

    fun setIds(id: Int) {
        this.id = id
    }

    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

    fun getHobbys(): String {
        return hobby.toString()
    }

    fun setHobbys(hobby: String) {
        this.hobby = hobby
    }

}