package com.itis.template

data class User(val name: String) {

    operator fun plusAssign(user: User) {
        this.name + user.name
    }

    operator fun plus(user: User) {
        this.name + user.name
    }
}
