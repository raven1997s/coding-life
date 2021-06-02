package com.raven.autowired;

/**
 * @PackageName: com.raven.autowired
 * @ClassName: BuildClass
 * @Blame: raven
 * @Date: 2021-06-01 11:18
 * @Description:
 */
public class BuildClass {
    private String name;

    private String getName() {
        return name;
    }

    public static void main(String[] args) {
        String name = new BuildClass().getName();
    }

    /**
     * E:\develop\coding-life\spring\src\main\java\com\raven\autowired>javac BuildClass.java
     *
     * E:\develop\coding-life\spring\src\main\java\com\raven\autowired>javap -verbose BuildClass.class
     * Classfile /E:/develop/coding-life/spring/src/main/java/com/raven/autowired/BuildClass.class
     *   Last modified 2021-6-1; size 437 bytes
     *   MD5 checksum ac120dc00314ef1d489dcab8fa779290
     *   Compiled from "BuildClass.java"
     * public class com.raven.autowired.BuildClass
     *   minor version: 0
     *   major version: 52
     *   flags: ACC_PUBLIC, ACC_SUPER
     * Constant pool:
     *    #1 = Methodref          #6.#19         // java/lang/Object."<init>":()V
     *    #2 = Fieldref           #3.#20         // com/raven/autowired/BuildClass.name:Ljava/lang/String;
     *    #3 = Class              #21            // com/raven/autowired/BuildClass
     *    #4 = Methodref          #3.#19         // com/raven/autowired/BuildClass."<init>":()V
     *    #5 = Methodref          #3.#22         // com/raven/autowired/BuildClass.getName:()Ljava/lang/String;
     *    #6 = Class              #23            // java/lang/Object
     *    #7 = Utf8               name
     *    #8 = Utf8               Ljava/lang/String;
     *    #9 = Utf8               <init>
     *   #10 = Utf8               ()V
     *   #11 = Utf8               Code
     *   #12 = Utf8               LineNumberTable
     *   #13 = Utf8               getName
     *   #14 = Utf8               ()Ljava/lang/String;
     *   #15 = Utf8               main
     *   #16 = Utf8               ([Ljava/lang/String;)V
     *   #17 = Utf8               SourceFile
     *   #18 = Utf8               BuildClass.java
     *   #19 = NameAndType        #9:#10         // "<init>":()V
     *   #20 = NameAndType        #7:#8          // name:Ljava/lang/String;
     *   #21 = Utf8               com/raven/autowired/BuildClass
     *   #22 = NameAndType        #13:#14        // getName:()Ljava/lang/String;
     *   #23 = Utf8               java/lang/Object
     * {
     *   public com.raven.autowired.BuildClass();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=1, args_size=1
     *          0: aload_0
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *          4: return
     *       LineNumberTable:
     *         line 10: 0
     *
     *   public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=2, args_size=1
     *          0: new           #3                  // class com/raven/autowired/BuildClass
     *          3: dup
     *          4: invokespecial #4                  // Method "<init>":()V
     *          7: invokespecial #5                  // Method getName:()Ljava/lang/String;
     *         10: astore_1
     *         11: return
     *       LineNumberTable:
     *         line 18: 0
     *         line 19: 11
     * }
     * SourceFile: "BuildClass.java"
     */
}
