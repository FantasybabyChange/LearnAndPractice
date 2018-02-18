### nashorn
  http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
  <p>The Nashorn Javascript Engine is part of Java SE 8 and competes with other standalone engines like Google V8 (the engine that powers Google Chrome and Node.js). Nashorn extends Javas capabilities by running dynamic javascript code natively on the JVM</p>
  
  `ScriptEngineManager`
  
  1.Invoking Java Methods from javaScript 
  
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    engine.eval(new FileReader("script.js"));
    Invocable invocable = (Invocable) engine;
     Object result = invocable.invokeFunction(name,param);
     
  2.Invoking Javascript Functions from Java
      <br/>just static function
       
    var MyJavaClass = Java.type('my.package.MyJavaClass');
    var result = MyJavaClass.fun1('John Doe')
  3.ScriptObjectMirror   
     **When passing native javascript objects to java you can utilize the class**
### Language Extension
  ##### Typed Arrays
  <p>The int[] array behaves like a real java int array. But additionally Nashorn performs implicit type conversions under the hood when we're trying to add non-integer values to the array. Strings will be auto-converted to int which is quite handy.
  因为arrays隐式的转换类型 如果是错误的字符会设为0</p>
       
       var IntArray = Java.type("int[]");
   ##### Collections and For Each
    //first way to init list
    var ArrayList = Java.type('java.util.ArrayList');
     var list = new ArrayList();
     //second way
     var list = new java.util.ArrayList();
   ##### Lambda expressions and Streams
   ##### Extending classes
   Java types can simply be extended with the Java.extend
   暂时没有想到用途