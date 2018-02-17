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
function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function() {
        return this.firstName + " " + this.lastName;
    }
}
