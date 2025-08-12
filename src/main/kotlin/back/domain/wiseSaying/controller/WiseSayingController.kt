package com.back.domain.wiseSaying.controller

import com.back.global.bean.SingletonScope
import com.back.global.rq.Rq

class WiseSayingController {
    private val wiseSayingService = SingletonScope.wiseSayingService

    // 명언 등록 기능
    fun actionWriter(rq: Rq) {
        print("명언 : ")
        val content = readlnOrNull()!!.trim()
        print("작가 : ")
        val author = readlnOrNull()!!.trim()

        val wiseSaying = wiseSayingService.write(content, author)

        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    // 명언 조회 기능
    fun actionList(rq: Rq) {
        if (wiseSayingService.isEmpty()) {
            println("등록된 명인이 없습니다.")
            return
        }

        val keywordType = rq.getParamValue("keywordType", "content")
        val keyword = rq.getParamValue("keyword", "")

        val itemsPerPage = 5
        val pageNo: Int = rq.getParamValueAsInt("page", 1)

        val wiseSayingPage = if (keyword.isNotBlank())
            wiseSayingService.findByKeywordPaged(keywordType, keyword, itemsPerPage, pageNo)
        else
            wiseSayingService.findAllPaged(itemsPerPage, pageNo)

        val wiseSayings = if (keyword.isNotBlank())
            wiseSayingService.findByKeyword(keywordType, keyword)
        else
            wiseSayingService.findAllPaged(itemsPerPage, pageNo)

        if (keyword.isNotBlank()) {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }

        println("번호 / 작가 / 명언")
        println("-----------------------------------")

        wiseSayingPage.content.forEach {
            println("${it.id} : ${it.content} / ${it.author}")
        }

        print("페이지 : ")

        val pageMenu = (1..wiseSayingPage.totalPages)
            .joinToString(" ") {
                if (it == pageNo) "[$it]" else it.toString()
            }

        println(pageMenu)
    }

    // 명언 삭제 기능
    fun actionDelete(rq: Rq) {
        val id = rq.getParamValueAsInt("id")

        if (id == 0) {
            println("id를 정확히 입력해주세요.")
            return
        }
        val wiseSaying = wiseSayingService
            .findById(id)

        if (wiseSaying == null) {
            println("해당 id의 명언이 없습니다.")
            return
        }

        wiseSayingService.delete(wiseSaying)

        println("${id}번 명언을 삭제하였습니다.")
    }

    // 명언 수정 기능
    fun actionModify(rq: Rq) {
        val id = rq.getParamValueAsInt("id")

        if (id == 0) {
            println("id를 정확히 입력해주세요.")
            return
        }

        val wiseSaying = wiseSayingService.findById(id)

        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        println("명언(기존) : ${wiseSaying.content}")
        print("명언 : ")
        val content = readlnOrNull()!!.trim()

        println("작가(기존) : ${wiseSaying.author}")
        print("작가 : ")
        val author = readlnOrNull()!!.trim()

        wiseSayingService.modify(wiseSaying, content, author)

        println("${id}번 명언을 수정하였습니다.")
    }

    fun actionBuild(rq: Rq) {
        wiseSayingService.build()

        println("data.json 파일의 내용이 갱신되었습니다.")
    }
}