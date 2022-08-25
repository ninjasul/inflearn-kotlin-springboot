package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository <User, Long>, UserRepositoryCustom {
    fun findByName(name: String) : User?
}