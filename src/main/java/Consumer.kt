import java.util.*

class Consumer(
    private val buffer: Buffer
) : Runnable {

    override fun run() {
        while (buffer.hasPendingLines()) {
            val line = buffer.get()
            processLine(line)
        }
    }

    private fun processLine(line: String?) {
        val random = Random()
        Thread.sleep(random.nextInt(100).toLong())
    }
}