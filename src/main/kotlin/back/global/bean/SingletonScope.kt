package com.back.global.bean

import com.back.domain.system.controller.SystemController
import com.back.domain.wiseSaying.controller.WiseSayingController
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository
import com.back.domain.wiseSaying.repository.WiseSayingRepository
import com.back.domain.wiseSaying.service.WiseSayingService

object SingletonScope {
    val wiseSayingController by lazy { WiseSayingController() }
    val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingRepository: WiseSayingRepository by lazy { WiseSayingFileRepository() }
    val wiseSayingFileRepository by lazy {
        WiseSayingFileRepository()
    }
    val systemController by lazy { SystemController() }
}