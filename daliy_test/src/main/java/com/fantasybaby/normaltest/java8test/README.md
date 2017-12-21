<head1>Optional</head1> 
<ul>
  <li>of 方法  为非空值创建一个optional对象 如果为空 抛出空指针<br/></li>
  <li>ofNullable 方法 如果为空 可以返回一个为空的optional对象<br/></li>
  <li>get 方法 取出optional对象的值 为空会抛出NoSuchElementException<br/></li>
  <li>isPresent 如果值为空则返回false 反之为true<br/></li>
  <li>ifPresent 如果不为空<br /></li>
  <li>orElse  如果没有值则返回传入值<br /></li>
  <li>map 方法 传入lambda 表达式 可以修改当前值 并且返回一个新的Optional对象<br /></li>
  </ul>
  <head1>Stream</head1>
  Stream是元素的集合，这点让Stream看起来用些类似Iterator；
  可以支持顺序和并行的对原Stream进行汇聚的操作
  http://ifeve.com/stream/
   <ul>
    <li></li>
   </ul> 