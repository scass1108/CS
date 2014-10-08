        .file   "gcdR.c"
        .text
        .globl  gcdR
        .type   gcdR, @function
gcdR:
.LFB0:
        .cfi_startproc
        pushq   %rbp
        .cfi_def_cfa_offset 16
        .cfi_offset 6, -16
        movq    %rsp, %rbp
        .cfi_def_cfa_register 6
        subq    $16, %rsp
        movl    %edi, -4(%rbp)		# push first parameter i
        movl    %esi, -8(%rbp)		# push second paramenter j
        cmpl    $0, -8(%rbp)		# compare 0 with j
        jne     .L2			# jump if not equal
        movl    -4(%rbp), %eax		# return i by moving it to eax
        jmp     .L3
.L2:
        movl    -4(%rbp), %eax
        movl    %eax, %edx
        sarl    $31, %edx
        idivl   -8(%rbp)
        movl    -8(%rbp), %eax
        movl    %edx, %esi
        movl    %eax, %edi
        call    gcdR
.L3:
        leave
        .cfi_def_cfa 7, 8
        ret
        .cfi_endproc
.LFE0:
        .size   gcdR, .-gcdR
        .ident  "GCC: (Debian 4.7.2-5) 4.7.2"
        .section        .note.GNU-stack,"",@progbits

