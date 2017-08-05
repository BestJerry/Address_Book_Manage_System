package Model;

import net.sourceforge.pinyin4j.*;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Scanner;

public class Hanyu {

	/*public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
		String temp = input.next();
		System.out.println(getStringPinYin(temp));
	}*/


    private HanyuPinyinOutputFormat format = null;

    private static String[] pinyin;

    public Hanyu() {
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public String getCharacterPinYin(char c) {
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        // ���c���Ǻ��֣�toHanyuPinyinStringArray�᷵��null
        if (pinyin == null)
            return null;
        // ֻȡһ������������Ƕ����֣���ȡ��һ������
        return pinyin[0];
    }

    //ת��һ���ַ���

    public String getStringPinYin(String str) {

        StringBuilder sb = new StringBuilder();
        String tempPinyin = null;

        for (int i = 0; i < str.length(); i++) {
            tempPinyin = getCharacterPinYin(str.charAt(i));
            if (tempPinyin == null) {
                sb.append(str.charAt(i));
            } else {
                sb.append(tempPinyin);
            }
        }

        return sb.toString();
    }

}
