package cn.thinking.hash;

/**
 * Object默认的实现方式 : return this == obj。只有this和 obj引用同一个对象，才会返回true。
 * 如果用equals来判断 2个对象是否等价，就要重写equals.
 * 按照约定，equals要满足以下规则:
 * （1）自反性：x.equals(x)必须返回true。
 * （2）对称性：x.equals(y)与y.equals(x)的返回值必须相等。
 * （3）传递性：x.equals(y)为true，y.equals(z)也为true，那么x.equals(z)必须为true。
 * （4）一致性：如果对象x和y在equals()中使用的信息都没有改变，那么x.equals(y)值始终不变。
 * （5）非null：x不是null，y为null，则x.equals(y)必须为false。
 *
 * @author ken
 * @ClassName: HSEquals
 * @Description:
 * @date 2018年3月15日 下午5:02:24
 */
public class HSEquals {
    private String id;
    private String unit;

    /**
     * 通过前面的描述我们知道，重写hashCode需要遵守以下原则：
     * （1）如果重写了equals()方法，检查条件“两个对象使用equals()方法判断为相等，则hashCode()方法也应该相等”是否成立，如果不成立，则重写hashCode()方法。
     * （2）hashCode()方法不能太过简单，否则哈希冲突过多。
     * （3）hashCode()方法不能太过复杂，否则计算复杂度过高，影响性能.
     * <p>
     * 关于hashCode()计算过程中，为什么使用了数字31，主要有以下原因：
     * 1、使用质数计算哈希码，由于质数的特性，它与其他数字相乘之后，计算结果唯一的概率更大，哈希冲突的概率更小。
     * 2、使用的质数越大，哈希冲突的概率越小，但是计算的速度也越慢；31是哈希冲突和性能的折中，实际上是实验观测的结果。
     * 3、JVM会自动对31进行优化：31 * i == (i << 5) – i
     * <p>
     * 《Effective Java》中提出了一种简单通用的hashCode算法
     * A、初始化一个整形变量，为此变量赋予一个非零的常数值，比如int hash = 17;
     * B、选取equals方法中用于比较的所有域（之所以只选择equals()中使用的域，是为了保证上述原则的第1条），然后针对每个域的属性进行计算：
     * (1) 如果是boolean值，则计算f ? 1:0
     * (2) 如果是byte\char\short\int,则计算(int)f
     * (3) 如果是long值，则计算(int)(f ^ (f >>> 32))
     * (4) 如果是float值，则计算Float.floatToIntBits(f)
     * (5) 如果是double值，则计算Double.doubleToLongBits(f)，然后返回的结果是long,再用规则(3)去处理long,得到int
     * (6) 如果是对象应用，如果equals方法中采取递归调用的比较方式，那么hashCode中同样采取递归调用hashCode的方式。
     * 否则需要为这个域计算一个范式，比如当这个域的值为null的时候，那么hashCode 值为0.
     * (7) 如果是数组，那么需要为每个元素当做单独的域来处理。java.util.Arrays.hashCode方法包含了8种基本类型数组和引用数组的hashCode计算，算法同上。
     */
    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + (id == null ? 0 : id.hashCode());
        hash = hash * 31 + (unit == null ? 0 : unit.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!(obj instanceof HSEquals))
            return false;

        HSEquals mcd = (HSEquals) obj;
        boolean bUnit = unit != null && unit.equals(mcd.unit);
        boolean bSide = id != null && id.equals(mcd.id);
        return bUnit && bSide;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
