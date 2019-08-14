
fun main(args: Array<String>) {

    val mock = FileMock(100, 10)
    val buffer = Buffer(20)

    val producer = Producer(mock, buffer)
    val producerThread = Thread(producer, "Producer")

    val consumers = Array<Consumer>(3, {i -> Consumer()})
    val consumersThreads = arrayOf()
}