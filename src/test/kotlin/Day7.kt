import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7_Tests {

    @Test
    fun FileSystem_shouldCreateNewDirectories() {
        val input = "dir a"
        val fs = FileSystem(input)
        assertEquals("a", fs.files[0].files[0].name)
    }

    @Test
    fun FileSystem_shouldChangeWorkingDirectory() {
        val input = "$ cd a"
        val fs = FileSystem(input)
        assertEquals("a", fs.workingDirectory.name)
    }

    @Test
    fun FileSystem_shouldCreateNewDirectoriesFromWorkingDirectory() {
        val input = "$ cd a\n" +
                "dir b"
        val fs = FileSystem(input)
        assertEquals("a", fs.workingDirectory.name)
        assertEquals("b", fs.workingDirectory.files[0].name)
    }

    @Test
    fun FileSystem_shouldNavigateToRootDirectory() {
        val input = "$ cd a\n" +
                "$ cd /"
        val fs = FileSystem(input)
        assertEquals(fs.files[0], fs.workingDirectory)
    }

    @Test
    fun FileSystem_shouldNavigateToPreviousDirectory() {
        val input = "$ cd a\n" +
                "$ cd b\n" +
                "$ cd ..\n" +
                "$ cd .."
        val fs = FileSystem(input)
        assertEquals(fs.files[0], fs.workingDirectory)
    }

    @Test
    fun FileSystem_shouldCreateFiles() {
        val input = "\$ cd /\n" +
                "\$ ls\n" +
                "dir a\n" +
                "14848514 b.txt\n" +
                "8504156 c.dat\n" +
                "dir d"
        val fs = FileSystem(input)
        assertEquals("b.txt", fs.files[0].files[1].name)
        assertEquals(14848514, fs.files[0].files[1].size)
    }

    @Test
    fun FileSystem_shouldCalculateFilesizeOfDirectories() {
        val input = "1000 a.txt\n" +
                "2000 b.txt"
        val fs = FileSystem(input)
        assertEquals(3000, fs.files[0].size)
    }

    @Test
    fun FileSystem_shouldCalculateFilesizeIncludingNestedDirectories() {
        val input = "1000 a.txt\n" +
                "2000 b.txt\n" +
                "$ cd a\n" +
                "5000 c.txt"
        val fs = FileSystem(input)
        assertEquals(8000, fs.files[0].size)
    }

    @Test
    fun FileSystem_shouldCalculateTotalSizeOfSmallDirectories() {
        val input = "\$ cd /\n" +
                "\$ ls\n" +
                "dir a\n" +
                "14848514 b.txt\n" +
                "8504156 c.dat\n" +
                "dir d\n" +
                "\$ cd a\n" +
                "\$ ls\n" +
                "dir e\n" +
                "29116 f\n" +
                "2557 g\n" +
                "62596 h.lst\n" +
                "\$ cd e\n" +
                "\$ ls\n" +
                "584 i\n" +
                "\$ cd ..\n" +
                "\$ cd ..\n" +
                "\$ cd d\n" +
                "\$ ls\n" +
                "4060174 j\n" +
                "8033020 d.log\n" +
                "5626152 d.ext\n" +
                "7214296 k"
        val fs = FileSystem(input)
        val result = fs.getTotalSizeOfSmallDirectories()
        assertEquals(95437, result)
    }

}