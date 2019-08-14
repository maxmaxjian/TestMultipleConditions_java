import java.util.*
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

class Buffer(maxSize: Int) {
    private val buffer: LinkedList<String?>
    private val maxSize: Int
    private val lock: ReentrantLock
    private val lines: Condition
    private val space: Condition
    private var pendingLines: Boolean

    init {
        this.buffer = LinkedList()
        this.maxSize = maxSize
        this.lock = ReentrantLock()
        this.lines = lock.newCondition()
        this.space = lock.newCondition()
        pendingLines = true
    }

    fun insert(line: String?) {
        lock.lock()
        while (buffer.size == maxSize) {
            space.await()
        }
        buffer.offer(line)
        print("${Thread.currentThread().name}: Inserted Line: ${buffer.size}\n")
        lines.signalAll()
        lock.unlock()
    }

    @Synchronized
    fun setPendingLines(pendingLines: Boolean) {
        this.pendingLines = pendingLines
    }

    @Synchronized
    fun hasPendingLines() = pendingLines || buffer.size > 0

    fun get(): String? {
        var line: String? = null
        lock.lock()
        while(buffer.size == 0 && hasPendingLines()) {
            lines.await()
        }
        if (hasPendingLines()) {
            line = buffer.poll()
            print("${Thread.currentThread().name}: Line Readed: ${buffer.size}\n")
            space.signalAll()
        }
        lock.unlock()
        return line
    }
    
}