/**
 * FileIO class
 *
 * @author liuxi
 * @date 2018-05-05
 */
// map
def map = ['key1':'value','key2':'value2']
println map.getClass()
map.each {
    entry ->
        println entry.key+"--"+entry.value

}
//list