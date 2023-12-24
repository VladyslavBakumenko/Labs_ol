package com.example.labs_ol

data class EntityArg(var entity: Television? = null) {

    companion object {
        private var instance: EntityArg? = null
        fun getInstance(entity: Television? = null): EntityArg {
                instance = EntityArg(entity)
            return instance!!
        }

        fun justGetInstance(): EntityArg? {
            return instance
        }
    }
}