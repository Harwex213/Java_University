package by.belstu.it.kaportsev.basejava;
import java.util.Objects;

/**
 * Class WrapperString
 * @author Oleg Kaportsev
 * @version 1.0
 */
public class WrapperString
{
    /**
     * This is main string
     * @value "string value"
     * */
    private String str;

    /** Constructor
     * @param str
     * */
    public WrapperString(String str)
    {
        this.str = str;
    }

    /** Getter
     * @return String
     * */
    public String getStr()
    {
        return str;
    }

    /** Setter
     * @param str
     * @return String
     * */
    public void setStr(String str)
    {
        this.str = str;
    }

    /** Overloaded equals function
     * @param o
     * @return String
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperString that = (WrapperString) o;
        return Objects.equals(str, that.str);
    }

    /** Overloaded hashCode function
     * @return int
     * */
    @Override
    public int hashCode()
    {
        return Objects.hash(str);
    }

    /** replace
     * @param oldChar
     * @param newChar
     * @return void
     * */
    public void replace(char oldChar, char newChar)
    {
        str.replace(oldChar, newChar);
    }

    /** delete
     * @param c
     * @return void
     * */
    public void delete(char c){}

}