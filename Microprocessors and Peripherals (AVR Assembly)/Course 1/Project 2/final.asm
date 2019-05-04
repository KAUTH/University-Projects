;ergasia2.asm
;Papadopoulos Konstantinos, AEM 8677
;Group 3, Team 5
;Purpose: ...

.include "m16def.inc"


.cseg  ;tells the assembler that the following code is to be put into program memory
	   ;This is necessary when the .dseg directive was used before

;.org $100	;0x0100 ;set program memory address counter to 0x0100, 
			;set the program counter to a specific value



rjmp RESET	; ----first command of program

;---defining aliases for registers
.def mask = r17	;the mask for checking if switch has been pressed
.def swcounter = r18	;how many switches have been pressed until now
.def sw1 = r8	;switch that is pressed first 
.def sw2 = r9	;switch that is pressed second and so on...
.def sw3 = r10
.def sw4 = r11
.def sw5 = r12
.def sw6 = r13
.def sw7 = r14
;---


RESET:						; set initial value of stack pointer
	ldi r16, low(RAMEND)

	out SPL,r16

	ldi r16, HIGH(RAMEND)
	out SPH, r16
	rjmp main



main: 

;initialization code
;---setting PORTB as output port
ldi r16, 0b11111111
out DDRB, r16		;The data direction register of port B is named DDRB

;---setting PORTD as input port
ldi r16, 0b00000000
out DDRD, r16

;---setting Z register to show address (beginning from 0x60)
ldi ZL, 0x66
ldi ZH, 0x00


;main program
;--A) waiting loop, checking as flag SW0
get_switch0:
	in r16, PIND	;copy state of SW0 to PORTD (when pressed is 0b11111110)
	andi r16,$0F	;clear upper nibble of r16 (0b00001111)
	cpi r16, 0b00001110	;checks if SW0 is pressed
	brne get_switch0

;--B) wait for every switch to be pressed at least once and save the order they are pressed
;	  if a switch is pressed more than once save only the first value
;	  when all sw are pressed blink with 1sec frequency
ldi mask, 255			;make mask 0b11111111
ldi swcounter, 8	;SW must be pressed 7 times
get_switch_all:
	in r16, PIND
	mov r15,r16		;save initial state of r16, input of SW
	or r15, mask	;logic or r15 with mask
	cp r15, r16		;if r15 is equal to r16 the SW has already been pressed before
	breq get_switch_all	;return and wait for other switch
	and mask, r16	;updating mask by puting zero in the right bit if SW is pressed
	st Z+, r16		;saving SW pressed in DATA MEMORY
	dec swcounter;	
	brne get_switch_all	;if swcounter is 0 then all 7 SW have been pressed
;blink with 1sec frequency
;rcall blink
;rcall blink

;--C) 
;Z register shows address and decreases by one to get all SW values from DATA MEMORY 
;ld sw7, Z
;ld sw6, -Z
;ld sw5, -Z
;ld sw4, -Z
;ld sw3, -Z
;ld sw2, -Z
;ldi ZL, 0x60
;ld sw1, Z

;ldi swcounter, 7	;all 7 LEDs must light

get_switch:
	rcall blink
	in r22, PIND	;copy state of SW0 to PORTD (when pressed is 0b11111110)
	andi r22,$0F	;clear upper nibble of r16 (0b00001111)
	cpi r22, 0b00001101	;checks if SW1 is pressed
	breq SW_1
	cpi r22, 0b00001011	;checks if SW2 is pressed
	breq SW_2
	cpi r22, 0b00000111	;checks if SW3 is pressed
	breq SW_3
	brne get_switch		;if none is pressed keep waiting


SW_1:
	
	ldi swcounter, 7	;all 7 LEDs must light
	ldi ZL, 0x66		;starts again at the beggining of where the SWs were stored
	inc ZL				;first SW is stored in Z+1 
	ld sw1, Z+
	out PORTB, sw1
check:	in r22, PIND	
	andi r22,$0F
	cpi r22, 0b00001011	;checks if SW2 is pressed
	breq SW_2
	brne check
	;rjmp get_switch	; ---CHECK!!

SW_2:
	in r22, PIND	;copy state of SW0 to PORTD (when pressed is 0b11111110)
	andi r22,$0F	;clear upper nibble of r16 (0b00001111)
	cpi r22, 0b00000111	;checks if SW3 is pressed
	breq SW_3
	ld sw2, Z+			;it is sw2 only the first time, then it is sw3, sw4...
	out PORTB, sw2
	rcall delay5calc
	dec swcounter		;counts how many lights have left to light
	breq SW_4
	brne SW_2


	

SW_3:
	rcall blink
	in r22, PIND	;copy state of SW0 to PORTD (when pressed is 0b11111110)
	andi r22,$0F	;clear upper nibble of r16 (0b00001111)
	cpi r22, 0b00001011	;checks if SW2 is pressed
	breq SW_2
	cpi r22, 0b00001101	;checks if SW1 is pressed
	breq SW_1
	brne SW_3			;if none is pressed keep blinking


;--D) the program has ENDED, open all LEDs 
SW_4:
	ldi r22, 0b00000000
	out PORTB, r22	;program has ended, all the lights are on

end:
	rjmp end	;end of program	




;--subroutine that keeps lights open for 1sec and then closed for 1sec
blink:
	ldi r16, 0 ; open led lights for 1 sec (space betwenn characters)
	out PORTB, r16
	rcall delay1calc
	ldi r16, 255 ; close led lights for 1 sec (space betwenn characters)
	out PORTB, r16
	rcall delay1calc
ret




; delay subroutine
; Delay 3 999 997 cycles
; 999ms 999us 250 ns at 4.0 MHz -- approx 1 sec
delay1:
    		 ldi  r19, 21
L1delay0:    ldi  r20, 75
L1delay1:    ldi  r21, 190
L1delay2:	 dec  r21
    		 brne L1delay2
    		 dec  r20
    		 brne L1delay1
    		 dec  r19
    		 brne L1delay0
    		 nop

		ret


; Delay 19 999 997 cycles
; 4s 999ms 999us 250 ns at 4.0 MHz -- approx 5 sec
delay5:
    		 ldi  r19, 102
L2delay0:    ldi  r20, 118
L2delay1:    ldi  r21, 193
L2delay2:	 dec  r21
   			 brne L2delay2
   			 dec  r20
   			 brne L2delay1
   			 dec  r19
   			 brne L2delay0
		ret

	


;--as seen in the calculator
delay1calc:
			 ldi  r19, 21
		     ldi  r20, 75
    		 ldi  r21, 190
L1:			 dec  r21
    		 brne L1
    		 dec  r20
    		 brne L1
    		 dec  r19
    		 brne L1
    		 nop

		ret




;--as seen in the calculator
delay5calc:
		   	 ldi  r19, 102
		     ldi  r20, 118
		     ldi  r21, 193
L2:			 dec  r21
   			 brne L2
   			 dec  r20
   			 brne L2
   			 dec  r19
   			 brne L2
		ret
