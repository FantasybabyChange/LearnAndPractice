### nashorn
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
