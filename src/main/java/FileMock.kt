
class FileMock(size: Int, length: Int) {

    private val content: Array<String> = emptyArray()
    private var index: Int

    init {
        for ( i in 0..size-1) {
            val buffer = StringBuffer(length)
            for (j in 0..length-1) {
                val randomChar = Math.random() * 255
                buffer.append(randomChar as Char)
            }
            content[i] = buffer.toString()
        }
        index = 0
    }

    fun hasMoreLines() = index < content.size

    fun getLine() : String? {
        return when (hasMoreLines()) {
            true -> {
                println("Mock: ${content.size-index}")
                content[index++]
            }
            else -> null
        }
    }
}