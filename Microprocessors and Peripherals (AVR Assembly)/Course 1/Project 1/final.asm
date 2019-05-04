;ergasia1.asm
;Papadopoulos Konstantinos, AEM 8677




.include "m16def.inc"



.cseg  ;tells the assembler that the following code is to be put into program memory
	   ;This is necessary when the .dseg directive was used before

;.org $100	;0x0100 ;set program memory address counter to 0x0100, 
			;set the program counter to a specific value

rjmp RESET

Table:

.dw 8677, 8417, 34423, 33815 ;$21E5: this is the hex value of 8677


;--------------------
.def aem1_H=R16 ; (aem1H)
.def aem1_L=R17 ; (aem1L)
.def aem2_H=R18 ; (aem2H)
.def aem2_L=R19 ; (aem2L)

.def aem1_Hb=R6 ; (aem1H BCD)
.def aem1_Lb=R7 ; (aem1L BCD)
.def aem2_Hb=R8 ; (aem2H BCD)
.def aem2_Lb=R9 ; (aem2L BDC)

;.equ N0=8
;.equ N1=255



;----------

		;  first command of program




RESET:					; set initial value of stack pointer
	ldi r16, low(RAMEND)

	out SPL,r16

	ldi r16, HIGH(RAMEND)
	out SPH, r16
	rjmp main



main:

ldi ZH, high(Table<<1) ; Initialize Z pointer
ldi ZL, low(Table<<1)
lpm aem1_L, Z+; Load constant from program
lpm aem1_H, Z+


lpm aem2_L, Z+; Load constant from program
lpm aem2_H, Z+; 


lpm aem1_Lb, Z+; Load constant from program for BDC
lpm aem1_Hb, Z+


lpm aem2_Lb, Z+; Load constant from program for BCD
lpm aem2_Hb, Z+; maybe remove +


;------------------------ DISPLAY OF AEM1 BCD

	  ldi r21, 0b11111111

	  out DDRB, r21 ;The data direction register of port B is named DDRB

	  mov r21, aem1_Lb

	  rcall display_delay


;----------------------
	  mov r21, aem1_Hb

	  rcall display_delay

;------------------------

 	  mov r21, aem2_Lb

	  rcall display_delay


;----------------------


	  mov r21, aem2_Hb

	  rcall display_delay



;------------------ COMPARE TWO AEM


cp aem1_H, aem2_H ;check if aem1>aem2
breq check		  ; go to check if equal
brge led0		  ; if > go to led0


led1: ldi r21, 0b11111111	
	  
	  out DDRB, r21 ;The data direction register of port B is named DDRB
	  ldi r21, 0b11111110 ; open only led 1

	  out PORTB, r21

	  rcall Delay3

	  rjmp outside

	  



check:	cp aem2_L, aem1_L
		brge led1

led0: ldi r20, 0b11111111 
	  
	  out DDRB, r20 
	  ldi r20, 0b11111110	; open only led 0

	  out PORTB, r20

	  rcall Delay3

	  rjmp outside



outside:


SW7:
		mov r3, aem1_L
		mov r4, aem2_L
		mov r5, aem1_H
		mov r6, aem2_H
		clc
		sub r3, r4
		sbc r5, r6
	

		ldi r21, 0b11111111	; make PORTB output port
	  
	    out DDRB, r21 
	    ldi r21, 0b00000000	; make PORTD input port

        out DDRD, r21	
	
sw7again:	in r21, PIND	; copy state of sw to PORTD
			
			andi r21, 0b11111111 ; mask			

			cpi r21, 0b01111111		; check if switch 7 is pressed
			breq difaem2
			rjmp sw7again
		
		;mov r21, r3		; display in binary the aem difference
		;rcall display_delay

		;mov r21, r5		; display in binary the aem difference
		;rcall display_delay
		 
		

SW6:					; careful AEM2-AEM1<0, need to handle this
		mov r3, aem1_L
		mov r4, aem2_L
		mov r5, aem1_H
		mov r6, aem2_H
		clc
		sub r4, r3
		sbc r6, r5
	

		ldi r21, 0b11111111	; make PORTB output port
	  
	    out DDRB, r21 
	    ldi r21, 0b00000000	; make PORTD input port

        out DDRD, r21	
	
sw6again:	in r21, PIND	; copy state of sw to PORTD
			andi r21, 0b11111111 ; mask	


			cpi r21, 0b10111111		; check if switch 6 is pressed
			breq difaem1
			rjmp sw6again
		
difaem1:	mov r21, r3		; display in binary the aem difference
			rcall display_delay
			mov r21, r5		; display in binary the aem difference
			rcall display_delay
			rjmp hi

difaem2:	mov r21, r4		; display in binary the aem difference
			rcall display_delay
			mov r21, r6		; display in binary the aem difference
			rcall display_delay
			rjmp hi


		close:	ldi r22, 255
				out PORTB, r22	; close led lights
hi:
rjmp hi

	
	
		






;------------


display_delay: com r21

			   out PORTB, r21	; display sequence of bytes and delay

	  		   rcall Delay5		; keep every character on for 5 secs

	  		   ldi r21, 0 ; close led lights for 3 secs (space betwenn characters)

	  		   out PORTB, r21

	  		   rcall Delay3

			   ret 




Delay5: ldi  r20, 102	; delay for 5 secs
    	ldi  r24, 118
    	ldi  r22, 193
	L1: dec  r22
   		brne L1
    	dec  r24
    	brne L1
    	dec  r20
    	brne L1
		ret


Delay3: ldi  r20, 61	; delay for 3 secs
    	ldi  r24, 225
    	ldi  r22, 63
	L2: dec  r22
    	brne L1
    	dec  r24
    	brne L1
    	dec  r20
    	brne L1
    	rjmp PC+1
		ret





;Delay: 		LDI outer_L, low(N0)		; is is going to be called with RCALL
;	   		LDI outer_H, high(N0)
;outer_loop: ldi inner_L, low(N1)	
;	   		LDI inner_H, high(N1)
;inner_loop: nop
;			sbiw inner, 1
;			brne inner_loop
;			sbiw outer,1
;			brne outer_loop
;			ret							; Ttot = 5*Ni*No + 5*No + 8
