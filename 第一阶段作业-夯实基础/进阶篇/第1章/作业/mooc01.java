package mooc02maven;

import net.sourceforge.pinyin4j.*;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class mooc01 {

		public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination{
			String chineseString = "ÖÐ¹ú¼ÓÓÍ";
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
			format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE); 
			String pinyinString = PinyinHelper.toHanyuPinyinString(chineseString, format," "); 
			System.out.println(chineseString);
			System.out.println(pinyinString); 

		}
	}
