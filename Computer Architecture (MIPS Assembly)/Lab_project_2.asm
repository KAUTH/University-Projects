.data 0x10008000 
	.word 5, 9, 32  
	message1: .asciiz "Select the temperature scale: "
	message2: .asciiz "\nType the desired temperature: " 
	message3: .asciiz "Temperature = " 
	message4: .asciiz " Celsius\n" 
	message5: .asciiz " Fahrenheit\n" 

.text 

.globl main  

main:  
#Οι δυο επόμενες εντολές τοποθετούν στον $gp 
#τον αριθμό 0x10008000, που δείχνει στο μέσον
#των διευθύνσεων του χώρου των στατικών δεδομένων
#στη μνήμη
 
	lui $gp,0x1000 
	ori $gp,$gp,0x8000 
	
	addi $sp,$sp,-4 
	sw $ra,0($sp) 	#---saves the first $ra  
	
	#Μεταφορά στο $f16 του M[$gp]
	
	lwc1 $f16,0($gp) 
	
	#Μετατροπή του αριθμού στον $f16 σε μορφή αριθμού
	#κινητής υποδιαστολής
	
	cvt.s.w $f16, $f16 # $f16=5.0 
	
	lwc1 $f18, 4($gp)  #μεταφορά στον $f18 του Μ[$gp+4]
	
	#Μετατροπή του αριθμού στον $f18 σε μορφή αριθμού 
	#κινητής υποδιαστολής 
	
	cvt.s.w $f18,$f18 # $f18=9.0 
	lwc1 $f14,8($gp) 
	
	cvt.s.w $f14,$f14 # $f14=32.0  

	#prints message1 
	li $v0,4 
	la $a0,message1 
	syscall  

	li $v0,12 		#---reads character <c or f> 
	syscall 
	
	move $s0,$v0 	#---puts character into $s0  

	#prints message2 
	li $v0,4 	
	la $a0,message2 
	syscall  

	#reads the temperature 
	li $v0,6 
	syscall 		#--- f0 = temperature 
	
	mov.s $f12,$f0  

	addi $t0,$zero,102 #--- 102 = ascii code for "f"
	beq $s0,$t0,fahrenheit 
	jal celsius_to_fahrenheit  

	#----prints "temperature = <> Fahrenheit"  
	mov.s $f12,$f0  
	
	li $v0,4 
	la $a0,message3 
	syscall 

	li $v0,2 
	syscall 
	
	li $v0,4 
	la $a0,message5 
	syscall 
	
	j exit  

fahrenheit: 
	jal fahrenheit_to_celcius  

	#----prints "the temperature = <> celsius" 
	mov.s $f12,$f0  
	li $v0,4 
	la $a0,message3 
	syscall 
	
	li $v0,2 
	syscall 
	
	li $v0,4 
	la $a0,message4 
	syscall  

exit: 
	lw $ra,0($sp) 
	addi $sp,$sp,4 
	
	jr $ra 					#--- returns to address 00400018   

fahrenheit_to_celcius:  

	div.s $f20, $f16, $f18 	#--- $f20= 5/9 
	sub.s $f15, $f12, $f14 	#--- f15 = (temperature-32) 
	mul.s $f0,$f15,$f20 	#--- $f0 = result  

	jr $ra  

celsius_to_fahrenheit:  

	div.s $f20,$f18,$f16 	#--- $f20= 9/5 
	mul.s $f15,$f12,$f20  	#--- $f15=temperature*9/5 
	add.s $f0,$f15,$f14 	#--- $f0 = $f15 + 32  
	
	jr $ra 


