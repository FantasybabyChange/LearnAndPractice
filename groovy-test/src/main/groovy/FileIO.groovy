/**
 * FileIO class
 *
 * @author liuxi
 * @date 2018-05-05
 */
// map
def map = ['key1':'value','key2':'value2']
println map.getClass()
def dealMap(map){
    map.each {
        entry ->
            println entry.key+"--"+entry.value

    }
}
dealMap (map)

//list
def list = ['abc','abcd','abcde']
list.each {a->println a}
def listSize = list*.size()
listSize.each {b->println b }

//Closures
//        { [closureParameters -> ] statements }
       /* {
            ->
            print "hello world"
        }*/
     def cl1 =   {
            a ->
                print "hello $a"
        }
cl1("ee")
cl1.call("ee")

