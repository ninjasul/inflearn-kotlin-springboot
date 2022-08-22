package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService (
    val bookRepository: BookRepository,
    val userRepository: UserRepository,
    val userLoanHistoryRepository: UserLoanHistoryRepository,
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        bookRepository.save(Book(request.name, null))
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.bookName, false).isPresent) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        val book = bookRepository.findByName(request.bookName).orElseThrow (::IllegalArgumentException)
        val user = userRepository.findByName(request.userName).orElseThrow (::IllegalArgumentException)
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName)
            .orElseThrow(::IllegalArgumentException)

        user.returnBook(request.bookName)
    }
}