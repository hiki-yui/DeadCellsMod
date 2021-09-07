package deadCellsMod.cn.infinite.stsmod.utils;

import com.megacrit.cardcrawl.localization.Keyword;

import java.util.Arrays;

public class Keywords {

    public Keyword[] keywords;

    public Keyword[] getKeywords() {
        return keywords;
    }

    public void setKeywords(Keyword[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Keywords{" +
                "keywords=" + Arrays.toString(keywords) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keywords keywords1 = (Keywords) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(keywords, keywords1.keywords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(keywords);
    }
}
