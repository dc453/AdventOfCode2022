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
        println(fs.files)
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
        println("test")
        println(fs.files[0].files)
        println(fs.workingDirectory)
        assertEquals("b.txt", fs.files[0].files[1].name)
        assertEquals(14848514, fs.files[0].files[1].size)
    }

}