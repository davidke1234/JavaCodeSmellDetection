package Tests;
 
public class CyclomaticComplexity3 {
   boolean a;
   int bat=0, cat=0;
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
       while (cat > 0) { // 4, while
         fun1();
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
	   do { // 5, do-while
		 fun5();
		} while (g);
     }
   }
	private void fun(){ String x="hi"; }
	private void fun1(){ String x="hi"; }
	private void fun2(){ String x="hi"; }
	private void fun3(){ String x="hi"; }
	private void fun4(){ String x="hi"; }

	private void fun5(){ String x="Jones"; }
	private void fun6(){ String x="Jones"; }
	private void fun7(){ String x="Jones"; }
	private void fun8(){ String x="Jones"; }
	private void fun9(){ String x="Jones"; }
	private void fun10(){ String x="Jones"; }
 }
 