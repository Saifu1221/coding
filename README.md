# coding

Tech mahindra questions:
SQL - PL/SQL:
1. select salary from employees table in ascending/descending order.
	select salary from empoyee order by salary desc;
2. select 3rd maximum salary

	a. select salary from employee order by salary desc limit 1,3;
	b.	select min(bill_seq) from (
		select bill_seq from billsummary where account_num='0000002143' and rownum<=3 order by bill_seq desc
		);
	c. 	select * from billsummary s1
		where account_num='0000002143'
		and 3=
		(select count(distinct bill_seq) from billsummary s2  where account_num='0000002143' and s2.bill_seq > s1.bill_seq);
	d. select * from (
		select
				bill_seq
				  ,dense_rank() over (order by bill_seq desc) ranking
			from   billsummary where account_num='0000002143'
			)
		where ranking=3;
   
3. Use of IN
	select salary from employee where emp_id in (1,2,3);

4. What is trigger and trigger syntax
	create trigger triggername
		On tableName
	For [inser] [delete] [update]
	Trigger is named sql block or procedure which gets called automatically
	Syntax:
	CREATE [OR REPLACE] TRIGGER trigger_name
	{BEFORE | AFTER } triggering_event ON table_name
	[FOR EACH ROW]
	[FOLLOWS | PRECEDES another_trigger]
	[ENABLE / DISABLE ]
	[WHEN condition]
	DECLARE
		declaration statements
	BEGIN
		executable statements
	EXCEPTION
		exception_handling statements
	END;

	Example:
	CREATE OR REPLACE TRIGGER display_salary_changes 
	BEFORE DELETE OR INSERT OR UPDATE ON customers 
	FOR EACH ROW 
	WHEN (NEW.ID > 0) 
	DECLARE 
	   sal_diff number; 
	BEGIN 
	   sal_diff := :NEW.salary  - :OLD.salary; 
	   dbms_output.put_line('Old salary: ' || :OLD.salary); 
	   dbms_output.put_line('New salary: ' || :NEW.salary); 
	   dbms_output.put_line('Salary difference: ' || sal_diff); 
	END; 
	/ 
5. Cursor and type
	A cursor is a pointer to this context area. PL/SQL controls the context area through a cursor. 
	A cursor holds the rows (one or more) returned by a SQL statement. 
	The set of rows the cursor holds is referred to as the active set.
	
	Implic cursor : %FOUND, %ISOPEN, %NOTFOUND, and %ROWCOUNT
	
	Explicit cursor : 
		DECLARE 
		   c_id customers.id%type; 
		   c_name customer.name%type; 
		   c_addr customers.address%type; 
		   CURSOR c_customers is 
			  SELECT id, name, address FROM customers; 
		BEGIN 
		   OPEN c_customers; 
		   LOOP 
		   FETCH c_customers into c_id, c_name, c_addr; 
			  EXIT WHEN c_customers%notfound; 
			  dbms_output.put_line(c_id || ' ' || c_name || ' ' || c_addr); 
		   END LOOP; 
		   CLOSE c_customers; 
		END;
		/
6. Primary, unique and foreign keys
	Primary key is a column of table which uniquely identifies each tuple (row) in that table. NOT NULL
	Unique key constraints also identifies an individual tuple uniquely in a relation or table. Only one NULL value
	A table can have only primary key whereas there can be multiple unique key on a table.
	A foreign key is a column or group of columns in a relational database table that provides a link between data in two tables. 
	It acts as a cross-reference between tables because it references the primary key of another table
7. Indexes in oracle

c++

21. static variable initialisation
	className::variableName = valueToBeInitialize
22. Static variable scopes across files
	a.c
	static int count;

	b.c
	static int count;
	
	will give error "error: ‘int count’ previously defined here"

23. Static variable scopes across class
	calss A{
		static int count;
	}
	
	All the objects of class A will has same count variable

24. If three instances of an object are created, how many virtual pointer and how many virtual tables would be created?
	>> Three vptr and one vtable

25. Function overloading

	class A{
		
		void f(int 5){
		
		}
		void f(char c){
		
		}
	}
	will it compile?
	
	>> Yes, of cource
26. Diamond problem

	calss A{
		f1(){
		}
	};

	calss B: public A{

	};

	calss C: public A{

	};


	calss D: public B,public C{
		f1 from A
		f1 from B
	};
	
	Solution:
	call base class as virtual when inheriting
	
	class B: virtual public A{
	
	};
	class C: virtual public A{
	
	};
27. Implement a copy constructor having a char pointer
	class Base{
		private:
			char *ptr;
			int size;
		public:
			Base(){};
			
			Base(const Base &base){
				size = base.size;
				ptr = new char[size+1];
				strcpy(ptr,base.ptr);
			}
		
	};
28. Smart poniters
	Unique Pointer:
		std::unique_ptr<Type> p(new Type);
		For example:
		std::unique_ptr<int>    p1(new int);
		std::unique_ptr<int[]>  p2(new int[50]);
		std::unique_ptr<Object> p3(new Object("Lamp"));
	
	Shared pointer:
		std::shared_ptr<Type> p(new Type);
		For example:
		std::shared_ptr<int>    p1(new int);
		std::shared_ptr<Object> p2(new Object("Lamp"));

		circular references problem:
		
		class Player{
			private:
				shared_ptr<Player> compainion;
			public:
				Player(){}
				~Player(){}
		};
		
		int main(){
			shared_ptr<Player> rahul = make_shared<Player>();
			shared_ptr<Player> raju  = make_shared<Player>();
			rahul->compainion = raju;
			raju->compainion  = rahul;
		}
			
	Weak Pointer:
		You can only create a std::weak_ptr out of a std::shared_ptr or another std::weak_ptr. For example:

		std::shared_ptr<int> p_shared = std::make_shared<int>(100);
		std::weak_ptr<int>   p_weak1(p_shared);
		std::weak_ptr<int>   p_weak2(p_weak1);
		a std::weak_ptr is also used to break a circular reference. Let's go back to the Player example above and change the member
		variable from std::shared_ptr<Player> companion to std::weak_ptr<Player> companion. In this case we used a std::weak_ptr to 
		dissolve the entangled ownership. The actual dynamically-allocated data stays in the main body, while each Player has now 
		a weak reference to it. Run the code with the change and you will see how the destructor gets called twice, correctly.

29. how to debug coredump without binary name?

30. What is size of an empty class ?
		Size of an empty class is not zero. It is 1 byte generally. It is nonzero to ensure that the two different objects will have different addresses.

31. Difference between structure and union
	


32. Can a class have a reference variable
	Yes it can be!!
	
	#include <iostream>
	using namespace std;
	class test{
	  private:
		int a;
		int &b = a;
		int c;
	  public:
		void setA(int pa){
			a =  pa;
		}
		void printA(){
			cout << "A is "<<a <<endl;
		}
		void printB(){
			cout << "B is "<<b<<endl;
		}
		void setC(int pc){
			c = pc;
		}
		void printC(){
			cout << "C is "<<c <<endl;
		}
		void overRide(){
			b = c;
		}
		
	};
	int main () {
	   // declare simple variables
	   int    i;
	   double d;
	 
	   // declare reference variables
	   int&    r = i;
	   double& s = d;
	   
	   i = 5;
	   cout << "Value of i : " << i << endl;
	   cout << "Value of i reference : " << r  << endl;
	 
	   d = 11.7;
	   cout << "Value of d : " << d << endl;
	   cout << "Value of d reference : " << s  << endl;
	   test t;
	   t.setA(3);
	   t.printA();
	   t.printB();
	   t.setA(33);
	   t.printA();
	   t.printB();
	   t.setC(99);
	   t.overRide();
	   t.printB();
	   return 0;
	}

32. structure padding
	https://www.javatpoint.com/structure-padding-in-c
	
33. Difference between structure and union
	The union variable allocates the memory space equal to the space to hold the largest variable of union. 
	It allows varying types of objects to share the same location.

34. SOLID prinicipal
	Single Responsibility Principle (SRP)
		>>“a class should have only one reason to change” which means every class should have a single responsibility or single job or single purpose.
	Open/Closed Principle
		>>This principle states that “software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification” 
		  which means you should be able to extend a class behavior, without modifying it.
	Liskov’s Substitution Principle (LSP)
		>>Derived or child classes must be substitutable for their base or parent classes(farmer's son is replacement of farmer)
	Interface Segregation Principle (ISP)
		>>do not force any client to implement an interface which is irrelevant to them (separate menu card for veg,non-veg and jain customers)
	Dependency Inversion Principle (DIP)
		>>High-level modules/classes should not depend on low-level modules/classes. Both should depend upon abstractions.
          Abstractions should not depend upon details. Details should depend upon abstractions.
	
	https://www.geeksforgeeks.org/solid-principle-in-programming-understand-with-real-life-examples/
	
34. RAII

35. Type casting
	Dynamic casting: dynamic_cast can be used only with pointers and references to objects. Its purpose is to ensure that the result of the type conversion 
	is a valid complete object of the requested class.
	eg1:
		class Base{};
		class Derived : public Base{};
		
		Base *bptr;
		Derived *dptr;
		
		bptr = new Derived;
		
		dptr = dynamic_cast<Derived*>(bptr); //fine
		
		Base *bptr2 = new Base;
		
		dptr = dynamic_cast<Derived*>(bptr2);//it would return null
		//Its dow casting, its not legitimate
	eg2:
		class Base{};
		class Derived1 : public Base{};
		class Derived2 : public Base{};
		
		Base *bptr;
		Derived1 *dptr1;
		Derived2 *dptr2;
		
		bptr = dynamic_cast<Derived1>(dptr1);// fine
        dptr2 = dynamic_cast<Derived2>(bptr);//will give null, down cast		

35. How to allocate and initialise memory with 0;

35. How to find size of structure without using sizeof 
	#include<stdio.h>

	struct student{
		int roll;
		char name[100];
		float marks;
	};

	int main(){

	  struct student *ptr = 0;

	  ptr++;
	  printf("Size of the structure student:  %d",ptr);

	  return 0;
	}

sorting a vector/map of objects

ps -ef | grep "http"

find . -name "safraz" -exec rm -rf

file.txt 

chmod 
4 2 1 
chmod 100 file.txt
ugo 

names.txt
sarfraz

sed s/sarfraz/md fileN


class Base{
	public: 
		char ptr*;
		Bas(){};
		
		Base(const Base &b){
			ptr = malloc(sizeof(b.ptr));
			*ptr = *b.ptr;
		}
}

vector, list,map,q,dq


1->2->3->4->2
Map<>



slow,fast
slow = slow->nex;
fast = fast->next->next;

type casts


C++,Oracle

Smart poniters,
unique,shared,weak
shared,        

A p1 1

B p2 1

lamda function,
auto,
foreach,


[empid]employee
[empid][deptid]dept

coredump

structure and union
printing second last element
how to initialise memory
memcpy etc
{
int str;
char name[20]
} str;

str1[10];
str2[20];
strcpy(str1,str2) ??
p[2]->name

File fp;

fprintf();

fp.close();



solid prinicipal
name handling
RAII
default and para

    sor(objectVector,objectVector.size(),[](int id1,int id2){return id1>id2 ? id1:id2});

    char*ptr="hello";
    ptr++;
    print(ptr):// output //ello

    ptr[0]=M; //compilation error
    print(ptr);// output
    
    
    ptr = "hi"
    
    class test
    {
    int id;
    public:
    test(int i):id(i){}
    
    };
    
    test obj1(2), obj2(1), obj(3);
    
    vector<test> objectVector;
    objectVector.push(obj1);
    objectVector.push(obj2);
    objectVector.push(obj3);
    
    sor(objectVector,objectVector.size(),[](int id1,int id2){return id1>id2 ? id1:id2});
    
    
   ========================================================GAP==================================================================
   
   class Base{
	public:
		int printName(){
			cout<<"Im base class";
		}
};

class Derived: public Base{
	public:
		int printName(){
			cout<<"Im derived class";
		}
	
};

int main(){
	Derived d;
	Base *b  = d;
	
	b->printName();
}


employee {emp id, empname, emplsal, desig};
select * from employee order by empsal desc limit 1,2;
1->50k, 2->40k,3->40k,4->20k,5->20k;
select distinct empsal from 
(select empsal from employee order by empsal desc  ) temp;


create or replace package packageName 
As
global variables
functions();

procuderes..


create or replace package body packageName 
functions();

create or replace trigger triggername on tableName

before UPDATE
AS

package.spc
package.bdy
salplus $DATABASE
sqlplus username/password@instanceOdDB

>@package.spc


&

sed -p 2,5 fileName


can we call private method?

/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;

class Base{
	public:
		virtual int printName(){
			cout<<"Im base class";
		}
};

class Derived: public Base{
	private:
		int printName(){
			cout<<"Im derived class";
		}
	
};

int main()
{
    Derived d;
	Base *b  = &d;
	
	b->printName();
    return 0;
}



class Base{
	public:
		int printName(){
			cout<<"Im base class";
		}
};

class Derived: public Base{
	public:
		int printName(){
			cout<<"Im derived class";
		}
	
};

int main(){
	Derived d;
	Base *b  = d;
	
	b->printName();
}


employee {emp id, empname, emplsal, desig};
select * from employee order by empsal desc limit 1,2;
1->50k, 2->40k,3->40k,4->20k,5->20k;
select distinct empsal from 
(select empsal from employee order by empsal desc  ) temp;


create or replace package packageName 
As
global variables
functions();

procuderes..


create or replace package body packageName 
functions();

create or replace trigger triggername on tableName

before UPDATE
AS

package.spc
package.bdy
salplus $DATABASE
sqlplus username/password@instanceOdDB

>@package.spc


&

sed -p 2,5 fileName


can we call private method?

/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;

class Base{
	public:
		virtual int printName(){
			cout<<"Im base class";
		}
};

class Derived: public Base{
	private:
		int printName(){
			cout<<"Im derived class";
		}
	
};

int main()
{
    Derived d;
	Base *b  = &d;
	
	b->printName();
    return 0;
}



#include <iostream> 
#include <csignal> 
using namespace std; 

void signal_handler( int signal_num ) { 
cout << "The interrupt signal is (" << signal_num << "). \n"; 
	
// terminate program 
exit(signal_num); 
} 

int main () { 
signal(SIGABRT, signal_handler); 
// register signal SIGABRT and signal handler 

while(true) 
	cout << "Hello GeeksforGeeks..." << endl; 
return 0; 
} 

#Priority Queue
Priority queues are a type of container adapters, specifically designed such that the first element of the queue is the greatest of all elements in the queue and elements are in non increasing order (hence we can see that each element of the queue has a priority {fixed order}).
priority_queue<int> maxHeap; 
priority_queue<int,vector<int>,greater<int>> minHeap;


##########################Open text#########################
1. Linked list finding and counting loop length, running code.
2. What is iterator how internally it works
3. Issue with collection while using multi threading
4. How can exectue three threads one by one
