package com.back.domain.wiseSaying.repository

import com.back.domain.wiseSaying.entity.WiseSaying
import com.back.standard.dto.Page

interface WiseSayingRepository {
    fun save(wiseSaying: WiseSaying): WiseSaying
    fun isEmpty(): Boolean
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun delete(wiseSaying: WiseSaying)
    fun clear()
    fun build()
    fun findByAuthorLike(authorLike: String): List<WiseSaying>
    fun findByAuthorContent(contentLike: String): List<WiseSaying>
    fun findAllPaged(itemsPerPage: Int, pageNo: Int): Page<WiseSaying>
    fun findByKeywordPaged(keywordType: String, keyword: String, itemsPerPage: Int, pageNo: Int): Page<WiseSaying>
}