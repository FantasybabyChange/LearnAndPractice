var fun1 = function(name) {
    print('Hi there from Javascript, ' + name);
    fun2();
    return "greetings from javascript";
};
var fun2 = function(){
    print("function 2 ");
    //we can invoke static method use code below.
    var nashornTest = Java.type('com.fantasybaby.normaltest.java8test.nashorn.NashornTest1');
    var invokeFromJsFile = nashornTest.invokeFromJsFile('hehe');
    nashornTest.invokeFromJsFile(123);
    nashornTest.invokeFromJsFile(true);
    nashornTest.invokeFromJsFile(new Number(23));
    nashornTest.invokeFromJsFileMirror({'name':'name1'});
// class jdk.nashorn.internal.objects.NativeNumber

    nashornTest.invokeFromJsFile(new Date());
// class jdk.nashorn.internal.objects.NativeDate

    nashornTest.invokeFromJsFile(new RegExp());
// class jdk.nashorn.internal.objects.NativeRegExp

    nashornTest.invokeFromJsFile({foo: 'bar'});
    var personObject = new Person("刘",'曦');
    nashornTest.invokeFromJsFileJSObject(personObject);
    print(invokeFromJsFile);
}
var fun3 = function () {
    var intArray = Java.type("int[]");
    var array = new intArray(3);
    array[0] = 1;
    array[1] = 2;
    // will convert to 0
    array[2] = 'hello';
    for(var i = 0;i < array.length;i++){
        print(array[i]);
    }
    //初始化list
    // var ArrayList = Java.type('java.util.ArrayList');
    var list = new java.util.ArrayList();
    // var list = new ArrayList();
    list.add('a');
    list.add('b');
    list.add('c');
    for each (var el in list) print(el);

    //we can use two kinds of method to init map
    // var map = new java.util.HashMap();
    var HashMap = Java.type('java.util.HashMap');
    var  map = new HashMap();
    map.put('foo', 'val1');
    map.put('bar', 'val2');

    for each (var e in map.keySet()) print(e);  // foo, bar

    for each (var e in map.values()) print(e);  // val1, val2

    print(__FILE__, __LINE__, __DIR__);
}
function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function() {
        return this.firstName + " " + this.lastName;
    }
}
