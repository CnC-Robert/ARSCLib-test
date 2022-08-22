import com.reandroid.lib.arsc.chunk.PackageBlock
import com.reandroid.lib.arsc.chunk.TableBlock
import com.reandroid.lib.arsc.chunk.xml.AndroidManifestBlock
import com.reandroid.lib.arsc.chunk.xml.ResXmlBlock
import java.io.File

fun editARSC(newPackageName: String, inputFile: File = File("resources.arsc"), outputFile: File = File("new-resources.arsc")) {
    println("Editing $inputFile...")

    val tableblock = TableBlock()
    tableblock.readBytes(inputFile)

    val packageblock: PackageBlock = tableblock.packageArray.get(0)

    println("Old package name is ${packageblock.packageName}")
    println("New package name is $newPackageName")

    packageblock.packageName = newPackageName

    tableblock.refresh()

    tableblock.writeBytes(outputFile)

}

fun editManifest(newPackageName: String, inputFile: File = File("AndroidManifest.xml"), outputFile: File = File("new-AndroidManifest.xml")) {
    println("Editing $inputFile...")

    val manifestblock = AndroidManifestBlock()
    manifestblock.readBytes(inputFile)

    println("Old package name is ${manifestblock.packageName}")
    println("New package name is $newPackageName")

    manifestblock.packageName = newPackageName

    manifestblock.refresh()

    manifestblock.writeBytes(outputFile)
}

fun editXML(inputFile: File, outputFile: File = File("new-xml.xml")) {
    println("Editing $inputFile...")

    val resxmlblock = ResXmlBlock()
    resxmlblock.readBytes(inputFile)

    println(resxmlblock.resXmlElement)

    resxmlblock.refresh()

    resxmlblock.writeBytes(outputFile)
}

fun main() {
    editARSC("app.revanced.cool.app")
    editManifest("app.revanced.cool.app")
    editXML(File("-2E.xml"))
    println("Done")
}