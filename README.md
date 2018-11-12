# Command_Interpreter

A simple command interpreter with the following properties:

The interpreter takes commands from the standard input. The commands that will be supported are the following:

        reverse
        count-words
        reverse-words
        get
        set
        calc
	    save
	    load
	    print-all
 

# The "reverse" command has the following syntax:
           reverse <string>
It reverses the letters in the provided string. For example

 	 input:	reverse abra cadabra
 	 output: arbadac arba


# The "count-words" command has the following syntax:
           count-words <string>
It returns the number of words in the provided string. For example

	  input:	count-words abra cadabra
	  output: 	2


# The " reverse-words" command has the following syntax:
           reverse-words <string>
It reverses the order of the words in the provided string. For example

	  input:	reverse-words abra cadabra
  	  output: 	cadabra abra

# The "set" command has the following syntax:
           set <variable> <type> <value>

The command creates a value of the given type and stores it in the given variable.

Available types:

	number
	string
	date

The output of the command is the string "Ok".


# The "get" command has the following syntax:
           get <variable>

It obtains the value of the given variable and prints it out in the format:

           [<type>] <value>
(please see the example at the end). 


# The "calc" command has the following syntax:
           calc <variable1> <variable2> <operation> <variable3>

The command performs a simplistic operation between variable2 and variable3 and stores the result in variable1. The supported operations are listed below.

The output of the command is the string "Ok".


# The "save" command has the following syntax:
	save <file_name>
The command saves variables into a file.
The output of the command is the string "Saved successfully".


# The "load" command has the following syntax:
	load <file_name>
It loads variables from file.
The output of the command is the string "Loading completed successfuly!".

# The "print" command has the following syntax: 
	print-all
It prints information for all the declared variables.


The possible types of the values are the following:

           string
           number
	   date
		   
The operations supported by these types are the following:

           string
                       + <string>      concatenates the two strings
                       + <number>      adds the number as a string to the end
                       * <number>      repeats the string a <number> times
			   - <string>      removes all occurences of secondString from firstString. 
		       		           Length of firstString must be greater
		       	                   than length of second.

           number
                       + <number>      adds the two numbers
                       - <number>       subtracts the two numbers
                       * <number>       multiplies the two numbers
	   
	   date
	   		+ <number> adds a <number> of days
			- <date> finds difference between two dates in days
		       

# Example

			set first string Hello
			Ok
			
			get first
			[string] Hello
			
			get a
			-No such variable
			
			set num number 15
			Ok
			
			calc first first + num
			Ok
			
			get first
			[string] Hello15
