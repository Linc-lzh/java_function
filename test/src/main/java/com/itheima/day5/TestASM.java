package com.itheima.day5;

import org.springframework.asm.*;

import java.util.function.BinaryOperator;

public class TestASM {
    public static Integer lambda$main$1(Integer a, Integer b) {
        return a + b;
    }

    public static void main(String[] args) throws Exception {
        byte[] dump = dump();
        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                System.out.println(name);
                return super.defineClass(name, dump, 0, dump.length);
            }
        };
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clz = classLoader.loadClass("com.itheima.day5.MyFuncObj");
        System.out.println(clz.getConstructor().newInstance());
        BinaryOperator<Integer> lambda = (BinaryOperator<Integer>) clz.getConstructor().newInstance();
        System.out.println(lambda.apply(1, 2));
    }


    public static byte[] dump() {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(Opcodes.V21, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, "com/itheima/day5/MyFuncObj", "Ljava/lang/Object;Ljava/util/function/BinaryOperator<Ljava/lang/Integer;>;", "java/lang/Object", new String[]{"java/util/function/BinaryOperator"});

        classWriter.visitSource("MyFuncObj.java", null);

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(5, label0);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/itheima/day5/MyFuncObj;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "apply", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(9, label0);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "com/itheima/day5/TestLambda2", "lambda$main$1", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;", false);
            methodVisitor.visitInsn(Opcodes.ARETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/itheima/day5/MyFuncObj;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("a", "Ljava/lang/Integer;", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("b", "Ljava/lang/Integer;", null, label0, label1, 2);
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_BRIDGE | Opcodes.ACC_SYNTHETIC, "apply", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", null, null);
            methodVisitor.visitParameter("noNameParm", Opcodes.ACC_SYNTHETIC);
            methodVisitor.visitParameter("noNameParm", Opcodes.ACC_SYNTHETIC);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(5, label0);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/itheima/day5/MyFuncObj", "apply", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;", false);
            methodVisitor.visitInsn(Opcodes.ARETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/itheima/day5/MyFuncObj;", null, label0, label1, 0);
            methodVisitor.visitMaxs(3, 3);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
