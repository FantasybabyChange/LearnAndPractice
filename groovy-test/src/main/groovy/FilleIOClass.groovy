/**
 * FilleIOClass class
 *
 * @author liuxi
 * @date 2018-05-05
 */
class FileIOClass {
    static void main(String[] args) {
        def file = new File("G:\\workplace\\javaSEWorkPlace\\groovy-test\\src\\test\\resources\\testFile.txt")
        def targetFile = new File("G:\\workplace\\javaSEWorkPlace\\groovy-test\\src\\test\\resources\\testTargetFile.txt")
        file.eachLine {line->
            println line
        }

        targetFile.withOutputStream {
            output ->file.withInputStream {
                input ->
                    output << input
                }
            }
        }
    }

