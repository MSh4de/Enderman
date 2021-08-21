public class Test {

    private boolean OnFire = true, Crouched = true, Sprinting = true, Eating_Drinking_Blocking = true, Invisible = true;

    public Test() {

        byte b0 = 0;

        if (OnFire) {
            b0 = (byte) (b0 | 0x01);
        }

        if (Crouched) {
            b0 = (byte) (b0 | 0x02);
        }

        if (Sprinting) {
            b0 = (byte) (b0 | 0x04);
        }

        if (Eating_Drinking_Blocking) {
            b0 = (byte) (b0 | 0x08);
        }
        if (Invisible) {
            b0 = (byte) (b0 | 0x10);
        }




        System.out.println(b0);

    }

    public static void main(String[] args) {
        new Test();
    }

}
