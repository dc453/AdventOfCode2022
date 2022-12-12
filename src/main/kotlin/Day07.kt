import java.io.File

fun main() {

    val input = File("src/main/inputs/Day07.txt")
        .readText()
    val fs = FileSystem(input)
    println("Day 7, part 1: ${fs.getTotalSizeOfSmallDirectories()}")
    println("Day 7, part 2: ${fs.getOptimalDirectoryToDelete().fileSize}")

}

private const val SMALL_DIRECTORY_SIZE = 100000

class FileSystem(input: String) {

    var files = mutableListOf(Directory("/"))
    var workingDirectory: Directory = files[0]
    private var directoryNavigationQueue: MutableList<Directory> = mutableListOf(files[0])
    private var totalSizeOfSmallDirectories = 0
    private val availableDirectories: MutableList<Directory> = mutableListOf<Directory>()

    init {
        createFileStructure(input)
    }

    private fun createFileStructure(input: String) {
        input.split("\n")
            .forEach {
                val commandParts = it.split(" ")
                when (commandParts[0]) {
                    "dir" -> createDirectory(commandParts[1])
                    "$" -> {
                        when (commandParts[1]) {
                            "cd" -> {
                                when (val target = commandParts[2]) {
                                    "/" -> changeDirectoryToRoot()
                                    ".." -> changeDirectoryUpLevel()
                                    else -> changeDirectory(target)
                                }
                            }
                        }
                    }
                    else -> {
                        // capture file output
                        // #### name.ext
                        createFile(commandParts)
                    }
                }
            }
    }

    private fun createDirectory(directoryName: String) {
        workingDirectory.addDirectory(directoryName)
    }

    private fun createFile(commandParts: List<String>) {
        workingDirectory.addFile(commandParts[1], commandParts[0].toInt())
    }

    private fun changeDirectory(target: String) {
        val destinationDirectory = getDestinationDirectory(target)
        directoryNavigationQueue.add(destinationDirectory as Directory)
        workingDirectory = directoryNavigationQueue.last()
    }

    private fun changeDirectoryUpLevel() {
        directoryNavigationQueue = directoryNavigationQueue.dropLast(1)
            .toMutableList()
        workingDirectory = directoryNavigationQueue.last()
    }

    private fun changeDirectoryToRoot() {
        directoryNavigationQueue = mutableListOf(files[0])
        workingDirectory = directoryNavigationQueue.last()
    }

    private fun getDestinationDirectory(directoryName: String): FileSystemItem {
        val destinationDirectory = workingDirectory.files.filter { dir ->
            dir.name == directoryName
        }
        if (destinationDirectory.isEmpty()) {
            createDirectory(directoryName)
            return getDestinationDirectory(directoryName)
        }
        return destinationDirectory[0]
    }

    private fun updateTotalSizeOfSmallDirectories(files: List<Directory>) {
        files.forEach {
            if (it.fileSize < SMALL_DIRECTORY_SIZE) {
                totalSizeOfSmallDirectories += it.fileSize
            }
            if (it.files.filterIsInstance<Directory>().isNotEmpty()) {
                updateTotalSizeOfSmallDirectories(it.files.filterIsInstance<Directory>())
            }
        }
    }

    private fun updateAvailableDirectories(files: List<Directory>) {
        files.forEach {
            availableDirectories.add(it)
            if (it.files.filterIsInstance<Directory>().isNotEmpty()) {
                updateAvailableDirectories(it.files.filterIsInstance<Directory>())
            }
        }
    }


    fun getTotalSizeOfSmallDirectories(): Int {
        val directories = files.toList()
        updateTotalSizeOfSmallDirectories(directories)
        return totalSizeOfSmallDirectories
    }

    fun getAvailableSpace(): Int {
        return 70000000 - files[0].fileSize
    }

    fun getOptimalDirectoryToDelete(): Directory {
        updateAvailableDirectories(files.toList())
        return availableDirectories
            .filter { it.fileSize + getAvailableSpace() > 30000000 }
            .sortedByDescending { it.fileSize }
            .takeLast(1)[0]
    }
}

open class FileSystemItem(open val name: String, open val fileSize: Int = 0)

class Directory(override val name: String) : FileSystemItem(name) {
    val files = mutableListOf<FileSystemItem>()
    override val fileSize: Int
        get() = files.sumOf { it.fileSize }

    fun addDirectory(name: String) {
        files.add(Directory(name))
    }

    fun addFile(name: String, size: Int) {
        files.add(CustomFile(name, size))
    }
}

class CustomFile(override val name: String, override val fileSize: Int) : FileSystemItem(name)