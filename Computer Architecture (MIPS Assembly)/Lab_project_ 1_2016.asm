.data 0x10000000				#or just .data, it is by default at 0x10000000 
Message1: .asciiz "Please insert an integer number: "
Message2: .asciiz "\n" 
Message3: .asciiz "The factorial of " 
Message4: .asciiz " is: "  

.text 
	.globl main		#declared global so as to be accesible from other files

	main:  
		#---------prints Message1
		li $v0,4
		la $a0,Message1 
		syscall  

		li $v0,5	#reads the integer
		syscall 
		
		move $t1,$v0  

		#----------prints Message2 
		li $v0,4 
		la $a0,Message2 
		syscall  
	
		addi $sp,$sp,-4 
		sw $ra,0($sp) #save the first $ra  

		move $a0,$t1 # prepare the arguments 

		jal fact
		move $s0,$v0 # result in s0   

		li $v0,4 #print the factorial of <n> is  
		la $a0,Message3 
		syscall 
	
		move $a0,$t1 
		li $v0,1 
		syscall 

		li $v0,4 
		la $a0,Message4 
		syscall 

		move $a0,$s0 
		li $v0,1 
		syscall  

		#-----------return to exit 
		lw $ra,0($sp) 
		addi $sp,$sp,4 
		jr $ra # return to address 0x00400018  

	fact: 
		addi $sp,$sp,-8 		# adjust stack for 2 items  
		sw $ra,4($sp)  			# save return address  
		sw $a0,0($sp)  			# save argument 
		slti $t0,$a0,1 			# test for n < 1
		beq $t0,$zero,L1 
		addi $v0,$zero,1 		# if so, result is 1 
		addi $sp,$sp,8  		# pop 2 items from stack 
		jr $ra  				# and return

	L1: 	
		addi $a0,$a0,-1 		# else decrement n  
		jal fact   				# recursive call   
		lw $a0,0($sp)   		# restore original n  
		lw $ra,4($sp)   		# and return address
		addi $sp,$sp,8  		# pop two items from stack
		mul $v0,$a0,$v0 		# multiply to get result 
		jr $ra  				# and return 