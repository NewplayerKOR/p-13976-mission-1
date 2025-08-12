package com.back.global.app

import java.nio.file.Path

object AppConfig {
    val dbDirPath: Path
        get() {
            return Path.of("data/db/dev")
        }
}