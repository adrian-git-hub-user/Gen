object gen extends App {

  //Here I create a List of objects where each element is of type (String , () => Unit)
  case class FunctionDesc(label: String, fun: () => Unit)
  def f() = {
    println("in f")
  }

  val functionList = List[FunctionDesc](FunctionDesc("a1", f), FunctionDesc("a2", f))

  functionList.foreach(f => f.fun())

  //This works fine but if I want to modify the function List to contain a parameter then have to decide what the parameter value
  //should be when the function is being implemented : 
  case class FunctionDesc2(label: String, fun: (String) => Unit)
  def f2(str: String) = {
    println("in f2")
  }
  def f3(str: String) = {
    println("in f3")
  }
  val functionList2 = List[FunctionDesc2](FunctionDesc2("a3", f2), FunctionDesc2("a4", f3))
  functionList2.foreach(f => f.fun("param value"))

  //Can decide what the function parameter type should before it's invoked ?
  //So instead of 
//val functionList2 = List[FunctionDesc2](FunctionDesc2("a3", f2), FunctionDesc2("a4", f3))
//use : 
//val functionList2 = List[FunctionDesc2](FunctionDesc2("a3", f2("f5")), FunctionDesc2("a4", f2("f6"))


}