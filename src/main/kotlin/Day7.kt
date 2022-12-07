fun main() {


}

class FileSystem(input: String) {
    var files = mutableListOf(Directory("/"))
    var workingDirectory: Directory = files[0]
    var previousWorkingDirectory: Directory = files[0]

    init {
        input.split("\n")
            .forEach {
                val commandParts = it.split(" ")
                when (commandParts[0]) {
                    "dir" -> {
                        workingDirectory.addDirectory(commandParts[1])
                    }

                    "$" -> {
                        when (commandParts[1]) {
                            "cd" -> {
                                val target = commandParts[2]
                                val workingDirectorySnapshot = workingDirectory
                                workingDirectory = when (target) {
                                    "/" -> files[0]
                                    ".." -> previousWorkingDirectory
                                    else -> {
                                        val destinationDirectory = getDestinationDirectory(target)
                                        destinationDirectory as Directory
                                    }
                                }
                                previousWorkingDirectory = workingDirectorySnapshot
                            }
                        }
                    }

                    else -> {
                        // capture file output
                        // #### name.ext
                        workingDirectory.addFile(commandParts[1], commandParts[0].toInt())
                    }
                }
            }
//        println(files)
    }

    private fun getDestinationDirectory(directoryName: String): FileSystemItem {
        val destinationDirectory = workingDirectory.files.filter { dir ->
            dir.name == directoryName
        }
        print("destination directory ${destinationDirectory}")
        if (destinationDirectory.isEmpty()) {
            workingDirectory.addDirectory(directoryName)
            return getDestinationDirectory(directoryName)
        }
        return destinationDirectory[0]
    }
}

open class FileSystemItem(open val name: String, open val size: Int = 0) {

}

class Directory(override val name: String) : FileSystemItem(name) {
    val files = mutableListOf<FileSystemItem>()

    fun addDirectory(name: String) {
        files.add(Directory(name))
    }

    fun addFile(name: String, size: Int) {
        files.add(CustomFile(name, size))
    }
}

class CustomFile(override val name: String, override val size: Int) : FileSystemItem(name) {

}