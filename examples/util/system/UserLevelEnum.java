package com.homevip.util.system;

/**
 * 员工级别
 *
 * @author panpan
 */
public class UserLevelEnum {
    /**
     * 私有化构造方法
     */
    private UserLevelEnum() {
    }

    /**
     * 静态初始化器，线程安全
     */
    public static final UserLevelEnum instance = new UserLevelEnum();

    public static UserLevelEnum getInstance() {
        return instance;
    }

    /**
     * 服务师级别
     *
     * @author panpan
     */
    public static enum EnumUserLevel {
        _11("新人"), _1("初级"), _2("专业"), _3("高级"), _4("资深"), _5("王牌");

        public final String value;

        EnumUserLevel(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.value;
        }

        public String[] getMonthLevels(){
            String[] arr = new String[2];
            arr[0] = "" + getCode();
            arr[1] = getValue();
            return arr;
        }
    }

    /**
     * 包月员工级别
     *
     * @author panpan
     */
    public static enum EnumMonthLevel {
        _11("新人"), _1("专业"), _2("高级"), _3("资深"), _4("王牌");

        public final String value;

        EnumMonthLevel(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.value;
        }

        public String[] getMonthLevels(){
            String[] arr = new String[2];
            arr[0] = "" + getCode();
            arr[1] = getValue();
            return arr;
        }
    }

    /**
     * 包月育婴员工级别
     *
     * @author panpan
     */
    public static enum EnumYuYingLevel {
        _11("新人"), _1("专业"), _2("高级"), _3("金牌");

        public final String value;

        EnumYuYingLevel(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.value;
        }

        public String[] getMonthLevels(){
            String[] arr = new String[2];
            arr[0] = "" + getCode();
            arr[1] = getValue();
            return arr;
        }
    }

    /**
     * 包月月子员工级别
     *
     * @author panpan
     */
    public static enum EnumYueZiLevel {
        _11("新人"), _1("1星"), _2("2星"), _3("3星"), _4("4星"), _5("5星"), _6("6星"), _7("金牌");

        public final String value;

        EnumYueZiLevel(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.value;
        }

        public String[] getMonthLevels(){
            String[] arr = new String[2];
            arr[0] = "" + getCode();
            arr[1] = getValue();
            return arr;
        }
    }

}
