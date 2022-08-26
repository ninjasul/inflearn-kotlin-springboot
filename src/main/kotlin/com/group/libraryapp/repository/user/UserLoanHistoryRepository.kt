package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long>