
class Producer(
    private val mock: FileMock,
    private val buffer: Buffer
) : Runnable {

    override fun run() {
        buffer.setPendingLines(true)
        while (mock.hasMoreLines()) {
            val line = mock.getLine()
            buffer.insert(line)
        }
        buffer.setPendingLines(false)
    }
}