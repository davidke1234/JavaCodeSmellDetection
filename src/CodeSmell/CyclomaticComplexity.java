package CodeSmell;
 
public class CyclomaticComplexity {
   boolean a;
// Cyclomatic Complexity = 11
   int b, c;
   boolean g;
   boolean f;
   boolean e;
   boolean d;
   public void foo() { // 1, function declaration
     while (a || d) { // 2, while
       fun();
     }
     if (d || e) {
       do { // 3, do
         fun();
       } while (d);
     } else if (d == f) {
       while (c > 0) { // 4, while
         fun();
       }
       do { // 5, do-while
         fun1();
       } while (a);
       do { // 5, do-while
           fun2();
         } while (e);
       do { // 5, do-while
           fun3();
         } while (f);
       do { // 5, do-while
           fun4();
         } while (g);
     }
   }
   private void fun(){ String x="hi"; }
   private void fun1(){ String x="hi"; }
   private void fun2(){ String x="hi"; }
   private void fun3(){ String x="hi"; }
   private void fun4(){ String x="hi"; }
   private void fun5(){ String x="hi"; }
   private void fun6(){ String x="hi"; }
   private void fun7(){ String x="hi"; }
   private void fun8(){ String x="hi"; }
   
   
 }
 