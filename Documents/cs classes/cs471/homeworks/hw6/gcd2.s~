        .file   "gcdR.c"
        .text
        .p2align 4,,15
        .globl  gcdR
        .type   gcdR, @function
gcdR:
.LFB3:
        .cfi_startproc
        testl   %esi, %esi
        movl    %edi, %eax
        jne     .L8
        jmp     .L11
        .p2align 4,,10
        .p2align 3
.L4:
        movl    %edx, %esi
.L8:
        movl    %eax, %edx
        sarl    $31, %edx
        idivl   %esi
        movl    %esi, %eax
        testl   %edx, %edx
        jne     .L4
        rep
        ret
.L11:
        rep
        ret
        .cfi_endproc
.LFE3:
        .size   gcdR, .-gcdR
        .ident  "GCC: (Debian 4.7.2-5) 4.7.2"
        .section        .note.GNU-stack,"",@progbits
        movl    %eax, %edx
        sarl    $31, %edx
        idivl   %esi
        movl    %esi, %eax
        testl   %edx, %edx
        jne     .L4
        rep
        ret
.L11:
        rep
        ret
        .cfi_endproc
.LFE3:
        .size   gcdR, .-gcdR
        .ident  "GCC: (Debian 4.7.2-5) 4.7.2"
        .section        .note.GNU-stack,"",@progbits

