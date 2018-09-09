package com.fantasybaby.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodAsmAop extends MethodVisitor
{

    public MethodAsmAop(MethodVisitor mv) {
        super(Opcodes.ASM5,mv);
    }
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/fantasybaby/asm/AsmAopChecker",
                "checkSecurity", "()Z",true);
        super.visitCode();
    }


}
