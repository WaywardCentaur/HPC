package usfq.proyecto.huasipichai

object Singleton {

    private val mCache: MutableMap<String, Event> = mutableMapOf()
    private val uCache: MutableMap<String, User> = mutableMapOf()


    fun put(key: String, value: Event) {
        when {
            !key.isEmpty() -> mCache.put(key, value)
            else -> throw IllegalArgumentException("Key cannot be empty")
        }
    }


    fun get(key: String): Event? {
        when {
            mCache.containsKey(key) -> return mCache.get(key)
            else -> return null
        }
    }

    fun putU(key: String, value: User) {
        when {
            !key.isEmpty() -> uCache.put(key, value)
            else -> throw IllegalArgumentException("Key cannot be empty")
        }
    }


    fun getU(key: String): User? {
        when {
            uCache.containsKey(key) -> return uCache.get(key)
            else -> return null
        }
    }

}